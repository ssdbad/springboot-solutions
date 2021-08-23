package zipFile.dto;

public class User {
    private String userId;
    private String[] userRoles;// ADMIN_USER {EDIT,READ}, BUSINESS_USER
    private String[] authorities;// {EDIT,DELETE,ADD}
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String[] getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }
    public String[] getAuthorities() {
        return authorities;
    }
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
    
}
