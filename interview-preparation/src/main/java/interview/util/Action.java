package interview.util;

public enum Action {

	SAVE("SAVE"),UPDATE("UPDATE");
	
	String actionType;
	
	private Action(String actionType) {
		this.actionType = actionType;
	}
	
	public String getActionType() {
		return actionType;
	}

}
