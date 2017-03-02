package com.webchatOil.action;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.derrick.WeChat;
import com.derrick.oauth.Oauth;
import com.google.gson.Gson;
import com.mysql.fabric.Response;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.UserService;
import com.webchatOil.util.RequestTypeAnnotation;
import com.webchatOil.util.RequestTypeAnnotation.RequestType;

public class userManagerAction extends BaseAction {
	
	private static final long serialVersionUID = 4L;
	@Autowired
	private UserService userService;
    private String message;
    private List<LKUserinfo> users;
    private int size;
    private int index;
    private Logger logger = Logger.getLogger(userManagerAction.class);
    
    public void setSize(int size){
    	this.size = size;
    }
    
    public int getSize(){
    	return this.size;
    }
    
    public void setIndex(int size){
    	this.index = size;
    }
    
    public int getIndex(){
    	return this.index;
    }
        
    public String execute() {
        System.out.println("size = " + size);
        return SUCCESS;
    }
    
    /*
     * 获取用户唯一标识
     */
    @RequestTypeAnnotation(RequestType.GET)
    public void getUserOpenid(){
    	 Gson newGson = new Gson();
    	 Oauth createAuthOauth = WeChat.webAuth;
    	 String openId = createAuthOauth.getSaveToken().getOpenid();
    	 logger.log(Priority.DEBUG, "getUserOpenid :" + openId);
    	 System.out.println("getUserOpenid:" + openId);
	      try {
	    	  Map<String, Object> rsponseMap = new HashMap<String, Object>();
	    	  rsponseMap.put("status", 200);
	    	  rsponseMap.put("openId", openId);
	    	  getResponse().getWriter().write(newGson.toJson(rsponseMap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
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
	
	public void doGetUsers(){
		Gson json = new Gson();
		PageBean currentPageBean  = userService.getAllUsersInfo(size, index);
		List tList =  currentPageBean.getList();
		JSONArray tArray = new JSONArray(tList);
		Map< String, Object> jsonTree = new HashMap<String, Object>();
		jsonTree.put("list", tArray);
		String jsonString =  json.toJson(jsonTree);
	      try {
	    	  getResponse().getWriter().write(jsonString );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("goto json :" + json.toJson(tList));
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
		// userService.createUser(newUser);
		 message = "创建用户'" + newUser.getE_id() + "'成功"; 
	}
	
	/**
	 * 删除用户
	 */
	public void doRemoveOlderUser(LKUserinfo oldUser){
		// userService.deleteUser(oldUser.getE_id());  
	     message = "删除用户'" + oldUser.getE_id() + "'成功";
	}
	
	/**
	 * 更新用户信息
	 */
	public void doUpdateOlderUser(LKUserinfo oldUser){
		// userService.updateUser(oldUser);  
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
