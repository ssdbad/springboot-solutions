package zipFile.exception;

import java.util.Date;

public class ExceptionResponse {
    
    private Date timestamp;
    private String message;
    private String details;
    private int statusCode;

    public ExceptionResponse(Date timeStamp, String message, String details, int statusCode){
        this.timestamp = timeStamp;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ExceptionResponse [details=" + details + ", message=" + message + ", statusCode=" + statusCode
                + ", timestamp=" + timestamp + "]";
    }
}
