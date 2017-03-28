package com.egoo.sso.rest.model;

/**
 * Created by zuowenxia on 2017/3/28.
 */
public class Message {

    private String text;
    private String username;
    private String createdAt;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}