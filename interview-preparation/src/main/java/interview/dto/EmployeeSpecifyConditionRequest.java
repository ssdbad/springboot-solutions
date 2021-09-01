package interview.dto;

public class EmployeeSpecifyConditionRequest {

	private Long sourceDataSourceId;
	
	private String sourceDataSourceName;
	
	private String sourceDatabaseObject;
	
	private String sourceObjectAttribute;
	
	private String joinCondition;
	
	private String targetDataSourceName;
	
	private Long targetDataSourceId;
	
	private String targetDatabaseObject;
	
	private String targetObjectAttribute;

	public String getTargetDataSourceName() {
		return targetDataSourceName;
	}

	public void setTargetDataSourceName(String targetDataSourceName) {
		this.targetDataSourceName = targetDataSourceName;
	}
	
	public Long getTargetDataSourceId() {
		return targetDataSourceId;
	}

	public void setTargetDataSourceId(Long targeDataSourceId) {
		this.targetDataSourceId = targeDataSourceId;
	}

	public Long getSourceDataSourceId() {
		return sourceDataSourceId;
	}

	public String getSourceDataSourceName() {
		return sourceDataSourceName;
	}

	public void setSourceDataSourceId(Long sourceDataSourceId) {
		this.sourceDataSourceId = sourceDataSourceId;
	}

	public void setSourceDataSourceName(String sourceDataSourceName) {
		this.sourceDataSourceName = sourceDataSourceName;
	}

	public String getSourceDatabaseObject() {
		return sourceDatabaseObject;
	}

	public String getSourceObjectAttribute() {
		return sourceObjectAttribute;
	}

	public String getJoinCondition() {
		return joinCondition;
	}

	public String getTargetDatabaseObject() {
		return targetDatabaseObject;
	}

	public String getTargetObjectAttribute() {
		return targetObjectAttribute;
	}


	public void setSourceDatabaseObject(String sourceDatabaseObject) {
		this.sourceDatabaseObject = sourceDatabaseObject;
	}

	public void setSourceObjectAttribute(String sourceObjectAttribute) {
		this.sourceObjectAttribute = sourceObjectAttribute;
	}

	public void setJoinCondition(String joinCondition) {
		this.joinCondition = joinCondition;
	}

	public void setTargetDatabaseObject(String targetDatabaseObject) {
		this.targetDatabaseObject = targetDatabaseObject;
	}

	public void setTargetObjectAttribute(String targetObjectAttribute) {
		this.targetObjectAttribute = targetObjectAttribute;
	} 
}
