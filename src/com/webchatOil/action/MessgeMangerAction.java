package com.webchatOil.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.derrick.WeChat;
import com.derrick.domain.Articles;
import com.derrick.domain.UserInfo;
import com.derrick.oauth.WebAccessToken;
import com.derrick.util.ConfKit;
import com.google.gson.Gson;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.service.UserService;
import com.webchatOil.util.RequestTypeAnnotation;
import com.webchatOil.util.RequestTypeAnnotation.RequestType;

/**
 * 消息管理
 * @author lipeng
 *
 */
public class MessgeMangerAction extends BaseAction{
	private static final long serialVersionUID = 100L;
	HttpServletRequest request = getRequest();
	HttpServletResponse response = getResponse();
	private Logger logger = Logger.getLogger(MessgeMangerAction.class);
	
	@Autowired
	private UserService userService;
	/*
	 * 给客户发送消息 "提示新发布商品"
	 */
	@RequestTypeAnnotation(RequestType.POST)
	public void sendMessageToCustomers() throws UnsupportedEncodingException, IOException{
	
		 Gson rspGson = new Gson();
		 Map<String, Object> rspMap = new HashMap<String, Object>();
		 // 接受body数据：
		 InputStreamReader inputReader = new InputStreamReader( 
				 request.getInputStream(), "UTF-8"); 
		 BufferedReader bufferReader = new BufferedReader(inputReader); 
		 StringBuilder sb = new StringBuilder(); 
		 String line = null; 
		 while ((line = bufferReader.readLine()) != null) { 
		 sb.append(line); 
		 } 
		 
		 @SuppressWarnings("unchecked")
		Map<String, Object> toJson = rspGson.fromJson(sb.toString(), Map.class);
		 // 1
		String title = "最新商品:" + (String) toJson.get("Msg_Title");
		String description0 = (String) toJson.get("Msg_Sub1Title");
		String description1 = (String) toJson.get("Msg_Sub2Title");
		String description2 = (String) toJson.get("Msg_Content");
		// 2.
		String description = "商品价格:" + description0 + ";" + "商品数量:" + description1 + ";" +"商品简介:" + description2;
		// 3.
		 String uiString = ConfKit.baseUIString;
		 String feedsString = uiString + "html/employees.html";
	    // 给全部用户发商品消息	 
	    List<LKUserinfo> customers = userService.getAllUsers();
	    WebAccessToken singleAccessToken = WeChat.webAuth.getSaveToken();
	    String accessToken = singleAccessToken.getAccess_token();
	    for (int i = 0; i < customers.size(); i++) {
		    LKUserinfo useinfo = customers.get(i);
		    if (useinfo.getWechatID() ==  singleAccessToken.getOpenid()){
		    	continue;
		    }
		    try {
		    	List<Articles> articles = new ArrayList<Articles>();
		    	Articles art1 = new Articles();
		    	art1.setTitle(title);
		    	art1.setDescription(description);
		    	//art1.setPicUrl("");
		    	art1.setUrl(feedsString);
		    	articles.add(art1);
			 String resultString = WeChat.message.SendNews(accessToken, useinfo.getWechatID(), articles);
			 System.out.println("send msg :" + resultString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	     rspMap.put("status", 200);
	     rspMap.put("content", "ok");
	     getResponse().getWriter().write(rspGson.toJson(rspMap));
	}
}
