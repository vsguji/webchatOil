package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 员工负责商品表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKEmployeeTaskInfo")
public class LKEmployeeTaskInfo implements Serializable{
	private static final long serialVersionUID = 22L;
	private int E_id ; // 员工编号
	private int G_id ; // 商品编号
	
	public void setE_id(int eid ){
		this.E_id = eid;
	}
	public int getE_id(){
		return this.E_id;
	}
	
	public void setG_id(int gid ){
		this.G_id = gid;
	}
	public int getG_id(){
		return this.G_id;
	}
}

