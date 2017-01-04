package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门信息表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKDepartmentInfo")
public class LKDepartmentInfo implements Serializable{
	private static final long serialVersionUID = 26L;
	private int D_id;  // 部门编号
	private String D_name; // 部门名称
	private String D_per; // 负责人
	private String D_phone; // 电话
	private String D_add; // 地址
	
	public void setD_id(int did){
		this.D_id = did;
	}
	public int  getD_id(){
	   return this.D_id;
	}
	
	public void setD_name(String name){
		this.D_name = name;
	}
	public String  getD_name(){
	   return this.D_name;
	}
	
	public void setD_per(String per){
		this.D_per = per;
	}
	public String  getD_per(){
	   return this.D_per;
	}
	
	public void setD_phone(String phone){
		this.D_phone = phone;
	}
	public String  getD_phone(){
	   return this.D_phone;
	}
	
	public void setD_add(String add){
		this.D_add = add;
	}
	public String  getD_add(){
	   return this.D_add;
	}
}
