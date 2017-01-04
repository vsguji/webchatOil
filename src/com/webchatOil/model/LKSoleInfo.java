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
}
