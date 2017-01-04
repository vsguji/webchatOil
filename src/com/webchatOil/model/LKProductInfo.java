package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 生产表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKProductInfo")
public class LKProductInfo implements Serializable{
	private static final long serialVersionUID = 17L;
    private int G_id ; // 商品编号
    private int M_id ; // 原料编号
    private int PP_id; // 计划编号
    
    public void setG_id(int gid){
    	this.G_id = gid;
    }
    public int getG_id(){
    	return this.G_id;
    }
    
    public void setM_id(int mid){
    	this.M_id = mid;
    }
    public int getM_id(){
    	return this.M_id;
    }
    
    public void setPP_id(int ppid){
    	this.PP_id = ppid;
    }
    public int getPP_id(){
    	return this.PP_id;
    }
}
