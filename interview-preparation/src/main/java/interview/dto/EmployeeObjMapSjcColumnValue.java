package interview.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeObjMapSjcColumnValue {

	private Long sourceId;
	
	private List<String> intraSourceJoinCondition;
	

	public Long getSourceId() {
		return sourceId;
	}

	public List<String> getIntraSourceJoinCondition() {
		if(null == intraSourceJoinCondition) {
			intraSourceJoinCondition = new ArrayList<>();
		}
		return intraSourceJoinCondition;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public void setIntraSourceJoinCondition(List<String> intraSourceJoinCondition) {
		this.intraSourceJoinCondition = intraSourceJoinCondition;
	}
}