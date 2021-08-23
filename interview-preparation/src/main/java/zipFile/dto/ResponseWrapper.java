package zipFile.dto;

public class ResponseWrapper {
    
    private Object payload;
    private int statusCode;
    private String message;

    public ResponseWrapper(){

    }

    public ResponseWrapper(Object payload, int statusCode, String message){
        this.payload = payload;
        this.statusCode = statusCode;
        this.message = message;
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

    public void setMessage(String message) {
        this.message = message;
    }
}
