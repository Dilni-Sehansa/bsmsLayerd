package lk.ijse.bsms.layered.entity;

public class Profile {
    private Long userId;
    private String userName;
    private String role;
    private String password;

    public Profile() {
    }

    public Profile(Long userId, String userName, String role, String password) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
