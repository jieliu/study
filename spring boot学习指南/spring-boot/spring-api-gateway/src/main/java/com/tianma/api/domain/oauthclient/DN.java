package com.tianma.api.domain.oauthclient;

/**
 * Created by zhengpeiwei on 16/4/20.
 */
public class DN {

    String id;

    public DN(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String password;
}
