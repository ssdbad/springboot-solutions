package zipFile.dto;

public class HealthCheckResponse {
    private String healthCheck;
    private String healthStatus;
    private Long healthAppId;
    
    public String getHealthCheck() {
        return healthCheck;
    }
    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck;
    }
    public String getHealthStatus() {
        return healthStatus;
    }
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    public Long getHealthAppId() {
        return healthAppId;
    }
    public void setHealthAppId(Long healthAppId) {
        this.healthAppId = healthAppId;
    }

    
}
