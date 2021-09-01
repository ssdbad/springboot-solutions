package interview.dto;

public class StandardResponseWrapper {

	private Object payload;
	private int statusCode;
	private String message;

	public StandardResponseWrapper() {
		
	}
	
	public StandardResponseWrapper(Object payload, int statusCode, String customMessage) {
		super();
		this.payload = payload;
		this.statusCode = statusCode;
		this.message = customMessage;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String customMessage) {
		this.message = customMessage;
	}
	
	@Override
	public String toString() {
		return "StandardResponseWrapper [payload=" + payload + ", statusCode=" + statusCode + ", customMessage=" + message
				+ "]";
	}
}
