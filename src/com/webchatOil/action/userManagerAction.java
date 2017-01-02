package com.webchatOil.action;
import java.util.ArrayList;
import java.util.List;

import com.webchatOil.model.Userinfo;
import com.webchatOil.service.UserService;
public class userManagerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private UserService userService;
    private String message;
    private List<Userinfo> users;
	/**
	 * 检查用户权限
	 * @return
	 */
	public Userinfo doCheckUser(){
		return null;
	}
	
	/**
	 * 获取所有用户
	 */
	public List<Userinfo> doGetUser(){
		users = userService.findAll();
		return null;
	}
	
	/**
	 * 根据条件,获取范围内用户
	 */
	public List<Userinfo> doGetUserWithElment(String element0){
		return null;
	}
	
	/**
	 *  注册新用户
	 */
	public void doRegsisterUser(Userinfo newUser){
		 userService.createUser(newUser);
		 message = "创建用户'" + newUser.getUuid() + "'成功"; 
	}
	
	/**
	 * 删除用户
	 */
	public void doRemoveOlderUser(Userinfo oldUser){
		 userService.deleteUser(oldUser.getUuid());  
	     message = "删除用户'" + oldUser.getUuid() + "'成功";
	}
	
	/**
	 * 更新用户信息
	 */
	public void doUpdateOlderUser(Userinfo oldUser){
		 userService.updateUser(oldUser);  
	     message = "修改用户'" + oldUser.getUuid() + "'成功";
	}
	
	public String getMessage() {  
	        return message;  
	}  
	  
	public void setMessage(String message) {  
	        this.message = message;  
	}  
	  
     public List<Userinfo> getUsers() {  
	        return users;  
	 }  
	  
	 public void setUsers(List<Userinfo> users) {  
	        this.users = users;  
	 }  
}
