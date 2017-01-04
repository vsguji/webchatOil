package com.webchatOil.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 原料退货表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKStuffCancelOrderInfo")
public class LKStuffCancelOrderInfo implements Serializable{
	private static final long serialVersionUID = 12L;
	private int Rm_id;   // 退货编号
	private int Rm_count;  // 退货数量
	private Time Rm_date;   //退化时间
	private String Rm_price; // 退货单价
	private String Rm_haspay; // 已退货金额
	private String Rm_result; // 退货原因
	private String Rm_needpay; // 应退金额
	
	public void setRm_id(int mid ){
		this.Rm_id = mid;
	}
	
	public int getRm_id(){
		return this.Rm_id;
	}
	
	public void setRm_count(int count ){
		this.Rm_count = count;
	}
	
	public int getRm_count(){
		return this.Rm_count;
	}
	
	public void setRm_date(Time date ){
		this.Rm_date = date;
	}
	
	public Time getRm_date(){
		return this.Rm_date;
	}
	
	public void setRm_price(String price ){
		this.Rm_price = price;
	}
	
	public String getRm_price(){
		return this.Rm_price;
	}
	
	public void setRm_haspay(String pay){
		this.Rm_haspay = pay;
	}
	
	public String getRm_haspay(){
		return this.Rm_haspay;
	}
	
	public void setRm_result(String result){
		this.Rm_result = result;
	}
	
	public String getRm_result(){
		return this.Rm_result;
	}
	
	public void setRm_needpay(String needpay){
		this.Rm_needpay = needpay;
	}
	
	public String getRm_needpay(){
		return this.Rm_needpay;
	}
}
