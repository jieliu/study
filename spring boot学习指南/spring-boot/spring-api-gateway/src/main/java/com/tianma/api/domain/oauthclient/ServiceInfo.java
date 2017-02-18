package com.tianma.api.domain.oauthclient;

/**
 * Created by zhengpeiwei on 16/4/18.
 */
public class ServiceInfo {
    String tennatid;

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getTenantid() {
        return tennatid;
    }

    public void setTenantid(String tenantid) {
        this.tennatid = tenantid;
    }

    public int getAgent_level() {
        return agent_level;
    }

    public void setAgent_level(int agent_level) {
        this.agent_level = agent_level;
    }

    public int getService_num() {
        return service_num;
    }

    public void setService_num(int service_num) {
        this.service_num = service_num;
    }

    public int getTotal_opinion_num() {
        return total_opinion_num;
    }

    public void setTotal_opinion_num(int total_opinion_num) {
        this.total_opinion_num = total_opinion_num;
    }

    public int getHigh_opinion_num() {
        return high_opinion_num;
    }

    public void setHigh_opinion_num(int high_opinion_num) {
        this.high_opinion_num = high_opinion_num;
    }

    public String getOpinion_lable() {
        return opinion_label;
    }

    public void setOpinion_lable(String opinion_lable) {
        this.opinion_label = opinion_lable;
    }

    String agentid;
    int agent_level;
    int service_num;
    int total_opinion_num;
    int high_opinion_num;
    String opinion_label;

}
