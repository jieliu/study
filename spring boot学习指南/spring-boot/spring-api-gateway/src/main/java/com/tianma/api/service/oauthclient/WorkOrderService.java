package com.tianma.api.service.oauthclient;

import com.tianma.api.domain.oauthclient.WorkOrder;

import java.sql.Timestamp;

/**
 * Created by zhengpeiwei on 16/4/21.
 */
public interface WorkOrderService {
    WorkOrder getWorkOrderAT(String agentid, String tenantid);

    WorkOrder getWorkOrderUT(String userid, String tenantid);

    int postWorkOrder(String agenid, String userid, String tenantid, Timestamp time, String remark, String label);
}
