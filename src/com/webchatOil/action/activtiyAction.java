package com.webchatOil.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webchatOil.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.derrick.WeChatFilter;
import com.derrick.WeChat;
import com.derrick.domain.Data4Button;
import com.derrick.domain.Data4Menu;
import com.derrick.oauth.Menu;
import com.derrick.util.ConfKit;

public class activtiyAction extends BaseAction {
	private static final long serialVersionUID = 3321845277376234101L;
	private static WeChatFilter filter = new WeChatFilter();
	public void doGet() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
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
	
	public void setupMenu() throws Exception{
		// 自定义菜单
	   String accessToken = WeChat.getAccessToken();
	    Menu menu = WeChat.menu;
	   // 创建按钮
	   Data4Button btn = new Data4Button();
	   // 创建一级菜单
	   Data4Menu menu1 = new Data4Menu("view", "关于柚米01", "http://yomi8.com/about/");
	   Data4Menu menu2 = new Data4Menu("view", "柚米注册01", "http://yomi8.com/youmi/register.html");
	   Data4Menu menu3 = new Data4Menu("click", "下载柚米01", "Btn_5");
	   // 菜单之间的关系
	   btn.addMenu(menu1);
	   btn.addMenu(menu2);
	   btn.addMenu(menu3);
	   // Object -> json
	   String menus = JSON.toJSONString(btn);
	   menu.createMenu(accessToken, menus);
	   System.out.println(menu.createMenu(accessToken, menus));
	}
}
