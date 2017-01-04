package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 原料购入表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKStuffBuyInfo")
public class LKStuffBuyInfo implements Serializable {
	private static final long serialVersionUID = 11L;
	private int E_id;  // 员工编号
	private int C_id;  // 公司编号
	private int M_id;  // 原料编号
	private int Mo_id; // 原料订单编号
	
	public int getE_id(){
		return this.E_id;
	}
	public void setE_id(int eid){
		this.E_id = eid;
	}
	
	public int getC_id(){
		return this.C_id;
	}
	public void setC_id(int cid){
		this.C_id = cid;
	}
	
	public int getM_id(){
		return this.M_id;
	}
	public void setM_id(int mid){
		this.M_id = mid;
	}
	
	public int getMo_id(){
		return this.Mo_id;
	}
	public void setMo_id(int moid){
		this.Mo_id = moid;
	}
}

