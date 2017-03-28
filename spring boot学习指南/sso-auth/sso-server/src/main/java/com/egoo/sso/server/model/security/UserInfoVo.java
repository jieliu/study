package com.egoo.sso.server.model.security;

import com.egoo.sso.server.util.Role;

/**
 * Created by fiboliu on 16-9-29.
 */
public class UserInfoVo {

    private String userId;

    private String password;

    private String tenantId;

    private Role role;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    private String agentId;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
