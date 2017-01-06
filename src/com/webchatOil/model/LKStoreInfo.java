package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LKStoreInfo")
public class LKStoreInfo implements Serializable {
	private static final long serialVersionUID = 20L;
	private int G_id;   // 商品编号
	private int S_id;   // 仓库编号
	
	public void setG_id(int gid){
		this.G_id = gid;
	}
	public int getG_id(){
		return this.G_id;
	}
	
	public void setS_id(int sid){
		this.S_id = sid;
	}
	public int getS_id(){
		return this.S_id;
	}
}
