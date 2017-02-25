package com.tianma.api.service.oauthclient;

import com.tianma.api.domain.oauthclient.Approval;
import com.tianma.api.domain.oauthclient.Client;

import java.util.List;

/**
 * Created by zhengpeiwei on 16/4/7.
 */
public interface ClientService {



    Client findById(String id);

    Client findByIdAndSecret(String id, String secret);

    List<Client> allClient();

    List<Approval> findApprovalByClient(String clientId);

    List<Approval> findByApiName(StringBuffer apiname);

    int saveCodeforClient(String name, String code);

    String findcode(String code);

    List findCodeByClient(String client_id);

    int updateClient(Client client);
}
