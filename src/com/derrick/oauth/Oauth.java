package com.derrick.oauth;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.alibaba.fastjson.JSON;
import com.derrick.util.ConfKit;
import com.derrick.util.HttpKit;
import com.webchatOil.util.JsonUtils;

import net.sf.json.JSONObject;
/**
 * 
* @ClassName: Oauth 
* @Description: TODO(微信Oauth和支付工具类) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:48:41 
*
 */
public class Oauth {

    private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
    private static final String TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private String appid;
    private String secret;
  
    public static WebAccessToken accessToken;
    public static jspTicketAccessToken ticketToken;
    
    public Oauth() {
        super();
        this.appid = ConfKit.get("AppId");
        this.secret = ConfKit.get("AppSecret");
    }
    
	public Oauth(String appid, String secret) {
        super();
        this.appid = appid;
        this.secret = secret;
    }

    /**
     * 请求code
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String getCode() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("response_type", "code");
        params.put("redirect_uri", URLEncoder.encode(ConfKit.get("redirect_uri"), "UTF-8"));
        params.put("scope", "snsapi_userinfo"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "STATE#wechat_redirect");
        String para = Pay.createSign(params, false);
        return CODE_URI + "?" + para;
    }

    /**
     * 通过code 换取 access_token
     * @param code
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public String getToken(String code) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("secret", getSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        return HttpKit.get(TOKEN_URI, params);
    }

    /**
     * 刷新 access_token
     * @param refreshToken
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public String getRefreshToken(String refreshToken) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        return HttpKit.get(REFRESH_TOKEN_URI, params);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    
    /**
     * access_token map 转 javaBean
     * 缓存全局 access_token
     */
    public void setAccessToken(Map<String, Object> token){
    	JSONObject object =JSONObject.fromObject(token);
    	WebAccessToken tokenObj = (WebAccessToken)JSONObject.toBean(object, WebAccessToken.class);
    	// 存储json 到本地
    	String path = Oauth.localPath();
    	JsonUtils.writeJson(path, object, "token");
    	accessToken = tokenObj;
    }
   
    /**
     * 本地json 转 javaBean
     * @return
     */
    public WebAccessToken getSaveToken(){
    	String path = localPath();
    	path += "token.json";
    	String savedObj = JsonUtils.readJson(path);
    	if (JsonUtils.isJson(savedObj)){
    		JSONObject object = JSONObject.fromObject(savedObj);
        	WebAccessToken tokenObj = (WebAccessToken)JSONObject.toBean(object, WebAccessToken.class);
    		return tokenObj;	
    	}
    	else {
    		System.out.println("error json :" + savedObj );
    	}
    	return null;
    }
    
    /*
     * jsapi_ticket map 转 javaBean
     * 缓存全局 ticket 
     */
    public void setTicket(Map<String, Object> ticket){
    	JSONObject object =JSONObject.fromObject(ticket);
    	jspTicketAccessToken ticketObj = (jspTicketAccessToken)JSONObject.toBean(object, jspTicketAccessToken.class);
    	// 存储json 到本地
    	String path = Oauth.localPath();
    	JsonUtils.writeJson(path, object, "ticket");
    	ticketToken = ticketObj;
    }
    
    /*
     * 获取 ticket 
     */
    public jspTicketAccessToken getSaveTicket(){
    	String path = localPath();
    	path += "ticket.json";
    	File file = new File(path);
    	if (file.exists()){ 
    		String savedObj = JsonUtils.readJson(path);
        	if (JsonUtils.isJson(savedObj)){
        		JSONObject object = JSONObject.fromObject(savedObj);
        		jspTicketAccessToken ticketObj = (jspTicketAccessToken)JSONObject.toBean(object, jspTicketAccessToken.class);
        		return ticketObj;	
        	}
        	else {
        		System.out.println("error json :" + savedObj );
        	}
    	}
    	return null;
    }
    
    /**
     * 当前资源路径
     */
    @SuppressWarnings("unused")
	public static String localPath(){
    	return Thread.currentThread().getContextClassLoader().getResource("/").getPath();
    }
}
