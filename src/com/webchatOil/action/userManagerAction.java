package com.webchatOil.action;
import java.util.ArrayList;
import java.util.List;
import com.webchatOil.model.Userinfo;
public class userManagerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;

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
		
	}
	
	/**
	 * 删除用户
	 */
	public void doRemoveOlderUser(Userinfo oldUser){
		
	}
	
}
