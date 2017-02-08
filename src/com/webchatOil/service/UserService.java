package com.webchatOil.service;
import java.util.List;    
import com.webchatOil.model.LKUserinfo; 
import com.webchatOil.po.page.PageBean;
  
public interface UserService {  
  
	/**
	 * 获取所有客户分页信息
	 * @return
	 */
	public PageBean getAllUsersInfo(int pageSize,int currentPage);
	
	/**
	 * 根据单个客户信息
	 * @return 
	 */
	public List getSingleUserInfo(String userID);
	
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
	 * @param user
	 * 			新客户信息实体类
	 * @return
	 */
	public boolean insertNewUser(final LKUserinfo user);
	
	/**
	 * 更新客户信息
	 * @param user 
	 *        更新客户信息实体类
	 * @return 
	 */
	public boolean updateNewUser(final LKUserinfo user);
	
	/**
	 * 删除客户信息
	 * @param user 
	 *        删除客户信息实体类
	 * @return 
	 */
	public boolean deleteNewUser(final LKUserinfo user);
}  