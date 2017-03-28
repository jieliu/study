package com.egoo.sso.server.model.common;

import java.io.Serializable;

public class Error implements Serializable {

    private static final long serialVersionUID = 7660756960387438399L;

    private int code; // Error code
    private String message; // Error message

    public Error() {

    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
