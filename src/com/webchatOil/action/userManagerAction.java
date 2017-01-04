package com.webchatOil.action;
import java.util.ArrayList;
import java.util.List;

import com.webchatOil.model.LKUserinfo;
import com.webchatOil.service.UserService;
public class userManagerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private UserService userService;
    private String message;
    private List<LKUserinfo> users;
	/**
	 * 检查用户权限
	 * @return
	 */
	public LKUserinfo doCheckUser(){
		return null;
	}
	
	/**
	 * 获取所有用户
	 */
	public List<LKUserinfo> doGetUser(){
		users = userService.findAll();
		return null;
	}
	
	/**
	 * 根据条件,获取范围内用户
	 */
	public List<LKUserinfo> doGetUserWithElment(String element0){
		return null;
	}
	
	/**
	 *  注册新用户
	 */
	public void doRegsisterUser(LKUserinfo newUser){
		 userService.createUser(newUser);
		 message = "创建用户'" + newUser.getE_id() + "'成功"; 
	}
	
	/**
	 * 删除用户
	 */
	public void doRemoveOlderUser(LKUserinfo oldUser){
		 userService.deleteUser(oldUser.getE_id());  
	     message = "删除用户'" + oldUser.getE_id() + "'成功";
	}
	
	/**
	 * 更新用户信息
	 */
	public void doUpdateOlderUser(LKUserinfo oldUser){
		 userService.updateUser(oldUser);  
	     message = "修改用户'" + oldUser.getE_id() + "'成功";
	}
	
	public String getMessage() {  
	        return message;  
	}  
	  
	public void setMessage(String message) {  
	        this.message = message;  
	}  
	  
     public List<LKUserinfo> getUsers() {  
	        return users;  
	 }  
	  
	 public void setUsers(List<LKUserinfo> users) {  
	        this.users = users;  
	 }  
}
