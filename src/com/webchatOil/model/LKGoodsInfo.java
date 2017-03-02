package com.webchatOil.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 *  商品信息表
 */
@Entity
@Table(name="tb_goodsinfo",schema ="wechatdb")		// 表名注解
public class LKGoodsInfo implements Serializable {
	private static final long serialVersionUID = 15L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int G_id; // 商品编号
	private String G_name; // 商品名称
	private String G_time;  // 生产时间
	private String G_spec; // 商品规格
	private int  G_count; // 商品数量
	private String G_remark; //备注
	private String G_unit; // 商品单位
	
	public void setG_id(int gid){
		this.G_id = gid;
	}
	public int getG_id(){
		return this.G_id;
	}
	public void setG_name(String name){
		this.G_name = name;
	}
	public String getG_name(){
		return this.G_name;
	}
	public void setG_time(String time){
		this.G_time = time;
	}
	public String getG_time(){
		return this.G_time;
	}
	
	public void setG_spec(String spec){
		this.G_spec = spec;
	}
	public String getG_spec(){
		return this.G_spec;
	}
	
	public void setG_count(int count){
		this.G_count = count;
	}
	public int getG_count(){
		return this.G_count;
	}
	
	public void setG_remark(String remark){
		this.G_remark = remark;
	}
	public String getG_remark(){
		return this.G_remark;
	}
	
	public void setG_unit(String unit){
		this.G_unit = unit;
	}
	public String getG_unit(){
		return this.G_unit;
	}
}
