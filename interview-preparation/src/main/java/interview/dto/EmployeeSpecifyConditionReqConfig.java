package interview.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import interview.util.Action;
import interview.util.Util;

public class EmployeeSpecifyConditionReqConfig {

	private Long domainId;
	
	private Action action;
	
	private Long objectMapId;
	
	private List<EmployeeSpecifyConditionRequest> specifyConditions;

	public Long getDomainId() {
		return domainId;
	}

	public Long getObjectMapId() {
		return objectMapId;
	}

	public void setObjectMapId(Long objectMapId) {
		this.objectMapId = objectMapId;
	}
	
	public List<EmployeeSpecifyConditionRequest> getSpecifyConditions() {
		return specifyConditions;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public void setSpecifyConditions(List<EmployeeSpecifyConditionRequest> specifyConditions) {
		this.specifyConditions = specifyConditions;
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	@JsonIgnore
	public String getSourceJoinCondition() {
		Set<Long> sourceIds = new HashSet<>();
		List<EmployeeObjMapSjcColumnValue> sources = new ArrayList<>();
		List<String> interJoinCondition = new ArrayList<>();
		EmployeeObjMapColumnValue sourceCondValue = new EmployeeObjMapColumnValue();
		sourceCondValue.setDomainId(this.domainId);

		specifyConditions.stream().forEach(condition -> {
			
			List<String> intraJoinconditions = new ArrayList<>();
			StringBuilder joinCondition = new StringBuilder();
			if(null !=condition.getJoinCondition() && !condition.getJoinCondition().isEmpty()) {
				joinCondition.append(condition.getSourceDataSourceName()).append(".")
						.append(condition.getSourceDatabaseObject()).append(".")
						.append(condition.getSourceObjectAttribute());
				if (condition.getJoinCondition().equalsIgnoreCase("Right outer join")) {
					joinCondition.append("(+)");
				}
				joinCondition.append("=");
				joinCondition.append(condition.getTargetDataSourceName()).append(".")
						.append(condition.getTargetDatabaseObject()).append(".")
						.append(condition.getTargetObjectAttribute());
				if (condition.getJoinCondition().equalsIgnoreCase("Left outer join")) {
					joinCondition.append("(+)");
				}
				sourceIds.add(condition.getSourceDataSourceId());
				if(!condition.getTargetDataSourceId().equals(0l))
					sourceIds.add(condition.getTargetDataSourceId());
			
			}
			if (condition.getSourceDataSourceId().equals(condition.getTargetDataSourceId())) {
				EmployeeObjMapSjcColumnValue sourceJoinCondition = new EmployeeObjMapSjcColumnValue();
				sourceJoinCondition.setSourceId(condition.getSourceDataSourceId());
				intraJoinconditions.add(joinCondition.toString());
				sourceJoinCondition.setIntraSourceJoinCondition(intraJoinconditions);
				sources.add(sourceJoinCondition);
			} else {
				interJoinCondition.add(joinCondition.toString());
			}
		});

		sourceCondValue.setSources(sources);
		sourceCondValue.setInterSourceJoinCondition(interJoinCondition);
       
		List<Long> objectSourceId = sourceCondValue.getSources().stream().map(f->f.getSourceId()).collect(Collectors.toList());
		sourceIds.removeAll(objectSourceId);
		sourceIds.forEach(missedId->{
			EmployeeObjMapSjcColumnValue sourceJoinCondition = new EmployeeObjMapSjcColumnValue();
			sourceJoinCondition.setSourceId(missedId);
			sources.add(sourceJoinCondition);
		});
		return Util.convertObjectToJson(sourceCondValue);
	}
	
	@JsonIgnore
	public String getSourceConnectValue() {
		return null;
	}
}
