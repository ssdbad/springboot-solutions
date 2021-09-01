package interview.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class EmployeeObjMapColumnValue {

	private Long domainId;
	
	private List<EmployeeObjMapSjcColumnValue> sources;
	
	private List<String> interSourceJoinCondition;

	public List<String> getInterSourceJoinCondition() {
		if(null == interSourceJoinCondition) {
			interSourceJoinCondition = new ArrayList<>();
		}
		return interSourceJoinCondition;
	}

	public void setInterSourceJoinCondition(List<String> interSourceJoinCondition) {
		this.interSourceJoinCondition = interSourceJoinCondition;
	}

	public Long getDomainId() {
		return domainId;
	}

	public List<EmployeeObjMapSjcColumnValue> getSources() {
		return sources;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public void setSources(List<EmployeeObjMapSjcColumnValue> sources) {
		this.sources = sources;
	}
}
