package com.webchatOil.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/** 
 *  商品上传图片表
 */
@Entity
@Table(name="tb_goodsPic",schema ="wechatdb")		// 表名注解
public class LKGoodsPic implements Serializable{
	private static final long serialVersionUID = 100000000L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int C_id;
	// 用户id
	 @Column(unique=true,nullable = false)
	private String userid;
	// 上传时间：年月日+时分
	private String datetime;
	// 上传图片
	private String picPaths;
	
	 public void setC_id(int index){
		 this.C_id = index;
	 }
	 
	 public int getC_id(){
		 return this.C_id;
	 }
	
	 public void setUserid(String uid){
		 this.userid = uid;
	 }
	 
	 public String getUserid(){
		 return this.userid;
	 }
	 
	 public void setDatetime(String dateAndtime){
		 this.datetime = dateAndtime;
	 }
	 
	 public String getDatetime(){
		 return this.datetime;
	 }
	 
	 public void setPicPaths(String   picPaths){
		 this.picPaths = picPaths;
	 }
	 
	 public String   getPicPaths(){
		 return this.picPaths;
	 }
	 
	 public LKGoodsPic(){
		 
	 }
}
