package com.tianma.api.domain.oauthclient;

/**
 * Created by zhengpeiwei on 16/4/7.
 */
public class Client {

    private String client_id;
    private String client_secret;
    private String scope;
    private String resource_ids;
    private String authorized_grant_types;
    private Long access_token_validity;
    private String authorities;
    private Boolean autoapprove;
    private String additional_information;


    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }



    public Boolean getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(Boolean autoapprove) {
        this.autoapprove = autoapprove;
    }



    public String getAuthorized_grant_types() {
        return authorized_grant_types;
    }

    public void setAuthorized_grant_types(String authorized_grant_types) {
        this.authorized_grant_types = authorized_grant_types;
    }

    public Long getAccess_token_validity() {
        return access_token_validity;
    }

    public void setAccess_token_validity(Long access_token_validity) {
        this.access_token_validity = access_token_validity;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }




    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getScope() {
        return scope;
    }

    public String getResource_ids() {
        return resource_ids;
    }


    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", scopesof='" + scope + '\'' +
                ", resource_ids='" + resource_ids + '\'' +
                '}';
    }
}
