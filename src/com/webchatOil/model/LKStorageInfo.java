package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LKStorageInfo")
public class LKStorageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    private int Cu_id;  // 客户编号
    private String Cu_name; // 客户名称
    private String Cu_phone; // 联系方式
    private String Cu_Address; // 地址
    private String Cu_per;   // 负责人
    private String Cu_remark; // 备注
    
    public void setCu_id(int cuid){
    	this.Cu_id = cuid;
    }
    public int getCu_id(){
    	return this.Cu_id;
    }
    
    public void setCu_name(String name){
    	this.Cu_name = name;
    }
    public String getCu_name(){
    	return this.Cu_name;
    }
    
    public void setCu_phone(String phone){
    	this.Cu_phone = phone;
    }
    public String getCu_phone(){
    	return this.Cu_phone;
    }
    
    public void setCu_Address(String address){
    	this.Cu_Address = address;
    }
    public String getCu_Address(){
    	return this.Cu_Address;
    }
    
    public void setCu_per(String per){
    	this.Cu_per = per;
    }
    public String getCu_per(){
    	return this.Cu_per;
    }
    
    public void setCu_remark(String remark){
    	this.Cu_remark = remark;
    }
    public String getCu_remark(){
    	return this.Cu_remark;
    }
}
