package com.tianma.api.util;

import org.springframework.http.HttpStatus;


public class ResponseEntity<T> {
    private String message;
    private HttpStatus status;


    public ResponseEntity(String s, HttpStatus unauthorized) {
        this.message=s;
        this.status=unauthorized;

    }

    public void setMessage(String msg){
        this.message=msg;
    }
    public void setHs(HttpStatus h){
        this.status=h;
    }
    public String getMessage(){
        return this.message;
    }
    public HttpStatus getStatus(){
        return this.status;
    }

    public  String toString(){
        return "message:"+message+"\n" +
                "status:" +status+"\n";
    }
}
