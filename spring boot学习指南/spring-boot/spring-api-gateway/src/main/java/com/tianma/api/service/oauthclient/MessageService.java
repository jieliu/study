package com.tianma.api.service.oauthclient;

import com.tianma.api.domain.oauthclient.Message;

import java.util.List;

/**
 * Created by zhengpeiwei on 16/4/11.
 */
public interface MessageService {

    public List<Message> findMessages(String name);

    public int insertMessages(Message message);
}
