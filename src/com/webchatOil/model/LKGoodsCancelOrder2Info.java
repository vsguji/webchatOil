package com.webchatOil.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商品退货单表
 * @author lipeng
 *
 */
@Entity
@Table(name="LKGoodsCancelOrder2Info")
public class LKGoodsCancelOrder2Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private int Rg_id ; //  退货编号
	private int Rg_count ; // 退货数量
	private Time Rg_date; // 退货日期
	private String Rg_price; // 退货单价
	private String Rg_needpay; // 应付金额
	private String Rg_haspay; // 已退金额
	private String Rg_result; // 退货原因
	
	public void setRg_id(int rid){
		this.Rg_id = rid;
	}
	public int getRg_id(){
		return this.Rg_id;
	}
	
	public void setRg_count(int count){
		this.Rg_count = count;
	}
	public int getRg_count(){
		return this.Rg_count;
	}
	
	public void setRg_date(Time date){
		this.Rg_date = date;
	}
	public Time getRg_date(){
		return this.Rg_date;
	}
	
	public void setRg_price(String price){
		this.Rg_price = price;
	}
	public String getRg_price(){
		return this.Rg_price;
	}
	
	public void setRg_needpay(String needpay){
		this.Rg_needpay = needpay;
	}
	public String getRg_needpay(){
		return this.Rg_needpay;
	}
	
	public void setRg_haspay(String pay){
		this.Rg_haspay = pay;
	}
	public String getRg_haspay(){
		return this.Rg_haspay;
	}
	
	public void setRg_result(String result){
		this.Rg_result = result;
	}
	public String getRg_result(){
		return this.Rg_result;
	}
}
