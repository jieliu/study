package com.tianma.api.service.impl.oauthclient;

import com.tianma.api.domain.oauthclient.DN;
import com.tianma.api.service.oauthclient.UserRegisterService;
import com.tianma.api.support.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/22.
 */
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    ClientDao dao;

    @Override
    public DN loadDN(String tenantId) {
        return dao.get("egoo.ServiceInfo",".getDNforuser",tenantId);
        //取不到则返回null
    }

    @Override
    public int putDN(String tenantId,String id,String password,String host) {
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("tenantId", tenantId);
        param.put("id", id);
        param.put("password",password);
        param.put("host",host);
        return dao.insert("egoo.ServiceInfo",".putDNforUser",param);//调Mapper
        //返回整数 1就是成功
    }
}
