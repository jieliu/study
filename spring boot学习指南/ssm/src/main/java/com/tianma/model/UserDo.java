package com.tianma.model;

/**
 * Created by zuowenxia on 2017/3/31.
 */
public class UserDo {

    private String userId;
    private String password;
    private String role;
    private Integer active;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDo{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
