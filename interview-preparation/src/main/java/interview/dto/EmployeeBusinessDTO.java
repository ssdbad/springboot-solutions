package interview.dto;

import interview.util.Action;

public class EmployeeBusinessDTO {

	private long domainId;
	private String domainName;
	private String domainDescription;
	private long processId;
	private long protocolProcessId;
	private Action action;

	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainDescription() {
		return domainDescription;
	}

	public void setDomainDescription(String domainDescription) {
		this.domainDescription = domainDescription;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public long getProtocolProcessId() {
		return protocolProcessId;
	}

	public void setProtocolProcessId(long protocolProcessId) {
		this.protocolProcessId = protocolProcessId;
	}

}