package com.tianma.api.domain.oauthclient;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by zhengpeiwei on 16/4/11.
 */
public class Message {


    String sender;
    String receiver;
    int msgtype;
    String content;
    Date sendDate;
    Time sendTime;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }



    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Time getSendTime() {
        return sendTime;
    }

    public void setSendTime(Time sendTime) {
        this.sendTime = sendTime;
    }



}
