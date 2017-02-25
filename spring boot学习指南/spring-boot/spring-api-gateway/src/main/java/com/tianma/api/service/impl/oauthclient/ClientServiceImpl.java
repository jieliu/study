package com.tianma.api.service.impl.oauthclient;

import com.tianma.api.domain.oauthclient.Approval;
import com.tianma.api.domain.oauthclient.Client;
import com.tianma.api.service.oauthclient.ClientService;
import com.tianma.api.support.ClientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/7.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);


    @Autowired
    private ClientDao dao;

    public Client findById(String id){
        return dao.get("egoo.ClientMapper",".selectClientById",id);

    }

    @Override
    public Client findByIdAndSecret(String id, String secret) {

        Map map=new HashMap<String,Object>();
        map.put("id",id);
        map.put("secret",secret);
        return dao.get("egoo.ClientMapper",".selectClientByIdAndSecret",map);
    }

    public List<Client> allClient(){
        return dao.getList("egoo.ClientMapper", ".allClientInvoke");

    }

    public List<Approval> findApprovalByClient(String clientId){

        return dao.getList("egoo.ClientMapper",".selectAuthInfo",clientId);

    }

    @Override
    public List<Approval> findByApiName(StringBuffer apiname) {
        String apiName=new String(apiname);
        log.info("apiset's name is:"+apiname.toString());
        log.info("apiset's  string name is:"+apiName);

        return dao.getList("egoo.ClientMapper",".selectClientforSingleapi",apiName);
    }

    @Override
    public int saveCodeforClient(String name, String code) {
        Map map=new HashMap<String,Object>();
        map.put("name",name);
        map.put("code",code);
        return dao.insert("egoo.ClientMapper",".savecodebyclient",map);
    }

    @Override
    public String findcode(String code) {
        return dao.get("egoo.ClientMapper",".selectcode",code);
    }

    @Override
    public List findCodeByClient(String client_id) {
        return dao.getList("egoo.ClientMapper", ".selectcodebyclientandsecret", client_id);
    }

    @Override
    public int updateClient(Client client) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("client_id",client.getClient_id());
        map.put("client_secret",client.getClient_secret());
        map.put("validity",client.getAccess_token_validity());
        map.put("addinfo",client.getAdditional_information());
        map.put("authotype",client.getAuthorized_grant_types());
        map.put("resourceid",client.getResource_ids());
        map.put("scope",client.getScope());
        return dao.update("egoo.ClientMapper",".updateClient",map);
    }

}
