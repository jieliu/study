package com.tianma.api.service.oauthclient;

import com.tianma.api.domain.oauthclient.DN;

/**
 * Created by zhengpeiwei on 16/4/22.
 */
public interface UserRegisterService {

        public DN loadDN(String tenantId);

        public int putDN(String tenantId, String id, String password, String host);
}
