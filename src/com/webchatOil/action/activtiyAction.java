package com.webchatOil.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.WebConnection;

import org.apache.commons.lang.ObjectUtils.Null;

import com.webchatOil.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.derrick.WeChatFilter;
import com.derrick.WeChat;
import com.derrick.domain.Data4Button;
import com.derrick.domain.Data4Menu;
import com.derrick.domain.InMessage;
import com.derrick.domain.UserInfo;
import com.derrick.oauth.Menu;
import com.derrick.oauth.Oauth;
import com.derrick.oauth.Pay;
import com.derrick.oauth.User;
import com.derrick.oauth.WebAccessToken;
import com.derrick.util.ConfKit;
import com.derrick.util.HttpKit;
import com.derrick.util.Tools;

public class activtiyAction extends BaseAction {
	private static final long serialVersionUID = 3321845277376234101L;
	private static WeChatFilter filter = new WeChatFilter();
	HttpServletRequest request = getRequest();
	HttpServletResponse response = getResponse();
	/*
	 * 服务器认证
	 */
	public void doGet() throws Exception{
		
		// 接口配置
		filter.doFilter(request, response, null);
		int code = response.getStatus();
		// 测试订阅号 自定义菜单
		  if (code == HttpServletResponse.SC_OK && ConfKit.createMenuStatusCode == 0 ) {
			  System.out.println("ok");
			  setupMenu();
			  ConfKit.createMenuStatusCode = 1;
		  }
	}
	
	/*
	 * 创建自定义菜单
	 */
	public void setupMenu() throws Exception{
		// 自定义菜单
	   String accessToken = WeChat.getAccessToken();
	    Menu menu = WeChat.menu; 
	   String path = request.getContextPath();
	   // 创建按钮
	   Data4Button btn = new Data4Button();
	   // 创建一级菜单
	   Data4Menu menu1 = new Data4Menu("click", "供求信息","Btn_0");
	   Data4Menu menu2 = new Data4Menu("click", "粮食购销", "Btn_1");
	   Data4Menu menu3 = new Data4Menu("view", "意见留言",  path + "recAuthAction");
	   // 创建二级菜单
	   // 1.供求信息
	   Data4Menu menu1_0 = new Data4Menu("view", "发布求购信息","http://www.baidu.com");
	   Data4Menu menu1_1 = new Data4Menu("view", "发布出售信息", "http://www.baidu.com");
	   menu1.addSubMenu(menu1_0);
	   menu1.addSubMenu(menu1_1);
	   // 2.粮食购销
	   Data4Menu menu2_0 = new Data4Menu("view", "线上下单","http://www.baidu.com");
	   Data4Menu menu2_1 = new Data4Menu("view", "扫码支付","http://www.baidu.com");
	   Data4Menu menu2_2 = new Data4Menu("view", "历史记录","http://www.baidu.com");
	   menu2.addSubMenu(menu2_0);;
	   menu2.addSubMenu(menu2_1);
	   menu2.addSubMenu(menu2_2);
	   // 3.留言
	   
	   // 菜单之间的关系
	   btn.addMenu(menu1);
	   btn.addMenu(menu2);
	   btn.addMenu(menu3);
	   // Object -> json
	   String menus = JSON.toJSONString(btn);
	   menu.createMenu(accessToken, menus);
	   System.out.println(menu.createMenu(accessToken, menus));
	}
	
	/*
	 * 网页授权
	 */
	 public void recAuthAction() throws Exception{
		 String code = request.getParameter("code");
		 Oauth createAuthOauth = WeChat.webAuth;
		 if (code != null){
			 System.out.println("yes");
		 }
		 else {
			 System.out.println("no");
		 }
		 if (code != null && code.length() > 0) {
//			 if (createAuthOauth.accessToken == null) {
//				 String accessTokenString = createAuthOauth.getToken(code);
//				  createAuthOauth.setAccessToken(accessTokenString);
//			 }
//			 else if (createAuthOauth.accessToken.isExpire()) { 
//				 System.out.println("token 过期");
//				 String oauthURlString = createAuthOauth.getCode();
//				 response.sendRedirect(oauthURlString);
//				 return;
			   String accessTokenString = createAuthOauth.getToken(code);
			    createAuthOauth.setAccessToken(accessTokenString);
//			 }
			 // 这个地方 accessToken 问题：上面 accessTokenString 里面是从网页授权获取一个accessToken
			 // 获取用户基本信息 是用普通的 accessToken
			 String accessTokenNormal = WeChat.getAccessToken();
			 User currentUser = WeChat.user;
			 UserInfo currentUserInfo = currentUser.getUserInfo(accessTokenNormal, createAuthOauth.accessToken.openid);
			 System.out.println(currentUserInfo.getNickname());
			 request.setAttribute("snsUserInfo", currentUserInfo);
//			 response.sendRedirect(request.getContextPath() + "/chatOil.jsp"); 
			 request.getRequestDispatcher("/chatOil.jsp").forward(request, response);
			 System.out.println(request.getContextPath() + "/chatOil.jsp");
		 }
		 else {
			 System.out.println("意见留言--自动跳转到微信浏览器授权页面");
			 String oauthURlString = createAuthOauth.getCode();
			 response.sendRedirect(oauthURlString);
		 }
	 }
}
