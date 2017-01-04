package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 原料信息表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKStuffInfo")
public class LKStuffInfo implements Serializable{
	
	private static final long serialVersionUID = 10L;
	private int M_id;     // 原料编号
	private String M_name;  // 名称
	private int M_unit;		// 原料单位
	private String m_spec;  // 原料规格
	
	public int getM_id() {
		return this.M_id;
	}

	public void setM_id(int eid) {
		this.M_id = eid;
	}
	
	public String getM_name() {
		return this.M_name;
	}

	public void setM_name(String name) {
		this.M_name = name;
	}
	
	public int getM_unit() {
		return this.M_unit;
	}

	public void setM_unit(int unit) {
		this.M_unit = unit;
	}
	
	public String getM_spec() {
		return this.m_spec;
	}

	public void setM_spec(String spec) {
		this.m_spec = spec;
	}
}
