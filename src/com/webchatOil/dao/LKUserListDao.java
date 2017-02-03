package com.webchatOil.dao;

import java.util.List;
import com.webchatOil.model.LKUserinfo;

public interface LKUserListDao {
   
	/**
	 * 获取所有客户
	 * @return
	 */
	public List getAllUsers(final String hql,final int offset,final int length);
	
	
	/**
	 * 根据课程代码获取单个客户信息
	 * @return 
	 */
	public List getSingleUserInfo(String hql,String userID);
	
	
	/**
	 * 获取总记录数
	 * 
	 * @param hql
	 * 			查询条件
	 * @return	总记录数
	 */
	public int getAllRowCount(String hql);
	
	
	/**
	 * 新客户
	 * @param LKUserinfo
	 * 		  新客户实体类
	 * @return
	 */
	public boolean newCourse(final LKUserinfo course);
}