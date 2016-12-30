package com.derrick;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.derrick.domain.Data4Button;
import com.derrick.domain.Data4Menu;
import com.derrick.exception.WeixinException;
import com.derrick.oauth.Menu;
import com.derrick.util.HttpKit;

public class MenuTest { 
private static final String APP_ID = "wxe5b94b7d3defa56c";
private static final String APP_SECRET = "33e43685ea403ed985ac9ea447d6265a";
private static final String  GRANT_TYPE = "client_credential";
// 获取access_token的时间
private static long getTime;

// 当前获取的access_token(不用每次获取)
private static String access_token;
// 获取tokenURL
public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, WeixinException {
		Menu menu = new Menu();
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
		menu.createMenu(getAccessToken(), menus);
		System.out.println(menu.createMenu(getAccessToken(), menus)); 
	}
	
	private static String getAccessToken() throws WeixinException, IOException, ExecutionException, InterruptedException{
		if(null != access_token){// 已经获取了access_token
			long currentTime = System.currentTimeMillis();
			if((currentTime - getTime) < 7200000 ){// 过期了  | access_token有效期为7200秒
				return access_token;
			}
		}
		
		// 从服务端从新获取access_token
/*		String url = URL_ACCESSTOKEN + "?" + "grant_type="+GRANT_TYPE+"&appid="+Config.APPID+"&secret="+Config.SECRET;*/
		StringBuilder url = new StringBuilder();
		url.append(URL_ACCESSTOKEN);
		url.append("?");
		url.append("grant_type=").append(GRANT_TYPE);
		url.append("&appid=").append(APP_ID);
		url.append("&secret=").append(APP_SECRET); 
		
		System.out.println(url.toString()); 
	    String json = HttpKit.post(url.toString(), ""); 
		getTime = System.currentTimeMillis(); 
		JSONObject obj = JSON.parseObject(json);
		String access_token = obj.getString("access_token");
		if(null == access_token){// 错误
			throw new WeixinException(json); 
		}
		return access_token;
	}

}
