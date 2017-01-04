package com.webchatOil.model;

import java.io.Serializable;

/**
 *  原料订单信息表
 * @author lipeng
 *
 */
public class LKStuffOrderInfo implements Serializable {
	private static final long serialVersionUID = 8L;
	private int Mo_id;        // 原料订单编号
	private String Mo_price;  // 购入单价
	private int Mo_count;     // 购入数量
	private String Mo_needpay; // 应付金额
	private String Mo_pay;    // 实付金额
	private String Mo_remark; // 备注
	
	public int getMo_id() {
		return this.Mo_id;
	}

	public void setMo_id(int mid) {
		this.Mo_id = mid;
	}
	
	public int getMo_price() {
		return this.Mo_id;
	}

	public void setMo_price(String price) {
		this.Mo_price = price;
	}
	
	public int getMo_count() {
		return this.Mo_id;
	}

	public void setMo_count(int count) {
		this.Mo_count = count;
	}
}
