package com.tianma.api.service.oauthclient;

import com.tianma.api.domain.oauthclient.ServiceInfo;

/**
 * Created by zhengpeiwei on 16/4/18.
 */
public  interface ServiceInfoService {
    public ServiceInfo loadByIds(String id1, String id2);

    public int PutInfo(ServiceInfo info, Boolean good);


}
