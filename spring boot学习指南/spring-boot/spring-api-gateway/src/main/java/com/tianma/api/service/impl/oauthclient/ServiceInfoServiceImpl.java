package com.tianma.api.service.impl.oauthclient;

import com.tianma.api.domain.oauthclient.ServiceInfo;
import com.tianma.api.service.oauthclient.ServiceInfoService;
import com.tianma.api.support.ClientDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/18.
 */
@Service
public class ServiceInfoServiceImpl implements ServiceInfoService {


    @Autowired
    private ClientDao dao;

    @Override
    public ServiceInfo loadByIds(@Param("tenantid") String id1, @Param("agentid")String id2) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantid", id1);
        param.put("agentid", id2);
        return dao.get("egoo.ServiceInfo",".getInfoByIds",param);
    }

    @Override
    public int PutInfo(ServiceInfo info,Boolean good) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantid", info.getTenantid());
        param.put("agentid", info.getAgentid());

        ServiceInfo newServiceInfo=dao.get("egoo.ServiceInfo", ".getInfoByIds", param);

        if(newServiceInfo==null){//没有记录就插一条
            param.put("opinion_label",info.getOpinion_lable());
            param.put("service_num", 1);
            param.put("total_opinion_num", 1);
            if(good==true){
            param.put("high_opinion_num", 1);}
            else param.put("high_opinion_num",0);
            return dao.insert("egoo.ServiceInfo",".insertinfo",param);
        }
        else//有的话就更新
        {
            param.put("opinion_label",newServiceInfo.getOpinion_lable()+" "+info.getOpinion_lable());
            param.put("service_num", newServiceInfo.getService_num()+1);
            param.put("total_opinion_num", newServiceInfo.getTotal_opinion_num()+1);

           if(good==true)
           {
                param.put("high_opinion_num", newServiceInfo.getHigh_opinion_num()+1);
           }
            else
           {
               param.put("high_opinion_num", newServiceInfo.getHigh_opinion_num());
           }
            return dao.update("egoo.ServiceInfo",".updateinfo",param);
        }
    }


}
