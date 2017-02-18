package com.tianma.api.domain.oauthclient;

import java.sql.Timestamp;

/**
 * Created by zhengpeiwei on 16/4/8.
 */
public class Approval {

    String resourceId;
    String clientId;
    String scopesOf;
    String status;
    Timestamp expire;

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp expire) {
        this.expire = expire;
    }







    public String getScopesof() {
        return scopesOf;
    }

    public void setScopesof(String scopesof) {
        scopesof = scopesof;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Approval{" +
                "resourceId='" + resourceId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", scopesof='" + scopesOf + '\'' +
                ", status='" + status + '\'' +
                ", expire=" + expire +
                '}';
    }
}
