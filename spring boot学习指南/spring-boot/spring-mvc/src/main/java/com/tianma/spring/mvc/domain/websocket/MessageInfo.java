package com.tianma.spring.mvc.domain.websocket;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jie on 17-2-19.
 */
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 6367726218945501780L;

    private String id;

    private String name;

    public MessageInfo(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
