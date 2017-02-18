package com.tianma.api.pojo;

public class Seats {

    private int id;

    private String phoneNumber;

    private String business_group_id;//业务组 business group

    private String password;

    private String zuoxi_id;

    public String getBusiness_group_id() {
        return business_group_id;
    }

    public void setBusiness_group_id(String business_group_id) {
        this.business_group_id = business_group_id;
    }

    public String getZuoxi_id() {
        return zuoxi_id;
    }

    public void setZuoxi_id(String zuoxi_id) {
        this.zuoxi_id = zuoxi_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String toString(){
        return "id:"+id+"," +
                "phoneNumber:" +phoneNumber+"," +
                "zuoxi_id:" +zuoxi_id+"," +
                "business_group_id:" +business_group_id+"," +
                "password:" +password;
    }//the end of this function
}
