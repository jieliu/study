package com.tianma.api.service.impl.oauthclient;

import com.tianma.api.domain.oauthclient.WorkOrder;
import com.tianma.api.service.oauthclient.WorkOrderService;
import com.tianma.api.support.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/21.
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    ClientDao clientDao;

    @Override
    public WorkOrder getWorkOrderAT(String agentid, String tenantid) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantid", tenantid);
        param.put("agentid", agentid);
        return clientDao.get("egoo.domain.oauthclient.WorkOrder",".getworkorderAT",param);
    }

    @Override
    public WorkOrder getWorkOrderUT(String userid, String tenantid) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantid", tenantid);
        param.put("userid", userid);
        return clientDao.get("egoo.domain.oauthclient.WorkOrder",".getworkorderUT",param);
    }

    @Override
    public int postWorkOrder(String agentid, String userid, String tenantid, Timestamp time, String remark, String label) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantid", tenantid);
        param.put("userid", userid);
        param.put("agentid",agentid);
        param.put("time",time);
        param.put("remark",remark);
        param.put("label",label);
        return clientDao.insert("egoo.domain.oauthclient.WorkOrder",".postworkorder",param);
    }
}
