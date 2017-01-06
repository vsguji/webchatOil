package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商品销售订单表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKSoleOrderInfo")
public class LKSoleOrderInfo implements Serializable{
	private static final long serialVersionUID = 29L;
	private int So_id;    // 销售订单编号
	private String So_price; // 商品单价
	private int So_count;     // 商品数量 
	private String So_needpay; // 应付金额
	private String So_pay;     // 实付金额
	private String So_remark;  // 备注
	
	public void setSo_id(int sid){
		this.So_id = sid;
	}
	public int getSo_id(){
		return this.So_id;
	}
	
	public void setSo_price(String price){
		this.So_price = price;
	}
	public String getSo_price(){
		return this.So_price;
	}
	
	public void setSo_count(int count){
		this.So_count = count;
	}
	public int getSo_count(){
		return this.So_count;
	}
	
	public void setSo_needpay(String pay){
		this.So_needpay = pay;
	}
	public String getSo_needpay(){
		return this.So_needpay;
	}
	
	public void setSo_pay(String pay){
		this.So_pay = pay;
	}
	public String getSo_pay(){
		return this.So_pay;
	}
	
	public void setSo_remark(String remark){
		this.So_remark = remark;
	}
	public String getSo_remark(){
		return this.So_remark;
	}
}
