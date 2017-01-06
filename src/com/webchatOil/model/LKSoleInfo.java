package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LKSoleInfo")
public class LKSoleInfo implements Serializable {
	private static final long serialVersionUID = 27L;
	private int E_id;  // 员工编号
	private int G_id;  // 商品编号
	private int Cu_id; // 客户编号
	private int So_id; // 销售订单编号
	
	public void setE_id(int eid){
		this.E_id = eid;
	}
	public int getE_id(){
		return this.E_id;
	}
	
	public void setG_id(int gid){
		this.G_id = gid;
	}
	public int getG_id(){
		return this.G_id;
	}
	
	public void setCu_id(int cuid){
		this.Cu_id = cuid;
	}
	public int getCu_id(){
		return this.Cu_id;
	}
	
	public void setSo_id(int soid){
		this.So_id = soid;
	}
	public int getSo_id(){
		return this.So_id;
	}
}
