package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Table;

import javax.persistence.Entity;

/**
 * 进货公司信息表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKStockInfo")
public class LKStockInfo implements Serializable{
	private static final long serialVersionUID = 24L;
    private int C_id;   // 公司编号
    private String C_name; // 公司名称
    private String C_phone;// 联系方式
    private String C_address;// 地址
    private String C_pre; //  负责人
 
    public void setC_id(int cid){
    	this.C_id = cid;
    }
    public int getC_id(){
    	return this.C_id;
    }
    
    public void setC_name(String name){
    	this.C_name = name;
    }
    public String getC_name(){
    	return this.C_name;
    }
    
    public void setC_phone(String phone){
    	this.C_phone = phone;
    }
    public String getC_phone(){
    	return this.C_phone;
    }
    
    public void setC_address(String address){
    	this.C_address = address;
    }
    public String getC_address(){
    	return this.C_phone;
    }
    
    public void setC_pre(String C_pre){
    	this.C_pre = C_pre;
    }
    public String getC_pre(){
    	return this.C_pre;
    }
}
