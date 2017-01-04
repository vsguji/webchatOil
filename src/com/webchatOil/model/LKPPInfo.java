package com.webchatOil.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 生产计划信息表
 */
@Entity
@Table(name="LKPPInfo")
public class LKPPInfo implements Serializable {
	private static final long serialVersionUID = 21L;
	private int PP_id; 
	private int PP_count;
	private Time PP_startTime;
	private Time PP_endTime;
	private String PP_per;
	private String PP_state;
	
   public void setPP_id(int ppid){
	   this.PP_id = ppid;
   }
   public int getPP_id(){
	   return this.PP_id;
   }
   
   public void setPP_count(int count){
	   this.PP_count = count;
   }
   public int getPP_count(){
	   return this.PP_count;
   }
   
   public void setPP_startTime(Time startTime){
	   this.PP_startTime = startTime;
   }
   public Time getPP_startTime(){
	   return this.PP_startTime;
   }
   
   public void setPP_endTime(Time endTime){
	   this.PP_endTime = endTime;
   }
   public Time getPP_endTime(){
	   return this.PP_endTime;
   }
   
   public void setPP_per(String per){
	   this.PP_per = per;
   }
   public String getPP_per(){
	   return this.PP_per;
   }
   
   public void setPP_state(String state){
	   this.PP_state = state;
   }
   public String getPP_state(){
	   return this.PP_state;
   }
}
