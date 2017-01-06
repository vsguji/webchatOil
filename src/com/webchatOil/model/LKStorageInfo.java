package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LKStorageInfo")
public class LKStorageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    private int S_id;  // 客户编号
    private String S_name; // 客户名称
    private String S_num; // 联系方式
    private String S_alarmnum; // 地址
    private String S_address;   // 负责人
    private String S_phone; // 备注
    
    public void setS_id(int cuid){
    	this.S_id = cuid;
    }
    public int getS_id(){
    	return this.S_id;
    }
    
    public void setS_name(String name){
    	this.S_name = name;
    }
    public String getS_name(){
    	return this.S_name;
    }
    
    public void setS_num(String num){
    	this.S_num = num;
    }
    public String getS_num(){
    	return this.S_num;
    }
    
    public void setS_alarmnum(String alarmnum){
    	this.S_alarmnum = alarmnum;
    }
    public String getS_alarmnum(){
    	return this.S_alarmnum;
    }
    
    public void setS_address(String address){
    	this.S_address = address;
    }
    public String getS_address(){
    	return this.S_address;
    }
    
    public void setS_phone(String phone){
    	this.S_phone = phone;
    }
    public String getCu_remark(){
    	return this.S_phone;
    }
}
