package com.tianma.api.service.impl.oauthclient;

import com.tianma.api.domain.oauthclient.Message;
import com.tianma.api.service.oauthclient.MessageService;
import com.tianma.api.support.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/11.
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private ClientDao dao;



    @Override
    public List<Message> findMessages(String name) {
        return dao.getList("egoo.domain.oauthclient.Message", ".allMessageInvoke",name);
    }

    @Override
    public int insertMessages(Message message) {
        Map map=new HashMap<String,Object>();
        map.put("sender",message.getSender());
        map.put("receiver",message.getReceiver());
        map.put("msgtype",new Integer(message.getMsgtype()));
        map.put("content",message.getContent());
        map.put("sendDate",message.getSendDate());
        map.put("sendTime",message.getSendTime());
        return dao.insert("egoo.domain.oauthclient.Message",".insertMessage",map);
    }
}
