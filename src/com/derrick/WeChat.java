package com.derrick;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.derrick.domain.Articles;
import com.derrick.domain.Attachment;
import com.derrick.domain.InMessage;
import com.derrick.domain.OutMessage;
import com.derrick.domain.TextOutMessage;
import com.derrick.impl.MessageProcessingHandler;
import com.derrick.oauth.*;
import com.derrick.util.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import freemarker.log.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *  微信公众平台开发模式(JAVA) SDK
* @ClassName: WeChat 
* @Description: TODO(微信常用的API) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:41:51 
*
 */
public class WeChat {
    private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
    private static final String DEFAULT_HANDLER = "com.derrick.impl.DefaultMessageProcessingHandlerImpl";
    private static final String GET_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
    private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
    private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
    // 新增其他类型永久素材
    private static final String  UPLOAD_add_material ="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";
    // 上传图文消息内的图片获取URL 
    private static final String UPLOAD_uploadimg = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=";
    // 新增永久图文素材
    private static final String UPLOAD_add_news = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=";
    private static Class<?> messageProcessingHandlerClazz = null;
    /**
     * 消息操作接口
     */
    public static final Message message = new Message();
    /**
     * 菜单操作接口
     */
    public static final Menu menu = new Menu();
    /**
     * 用户操作接口
     */
    public static final User user = new User();
    /**
     * 分组操作接口
     */
    public static final Group group = new Group();

    /**
     * 分组操作接口
     */
    public static final Qrcod qrcod = new Qrcod(); 
     
    /**
     *  新添加 "网页授权获取用户基本信息"接口
     */
    public static final Oauth webAuth = new Oauth();
    
    /**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String appid = ConfKit.get("AppId");
        String secret = ConfKit.get("AppSecret");
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

    
    /**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getAccessTokenMap() throws Exception {
        String appid = ConfKit.get("AppId");
        String secret = ConfKit.get("AppSecret");
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map;
    }
    /**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appid, String secret) throws Exception {
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

    /*
     * access_token 获取处理 (包含过期处里)
     */
    public static String getAccessTokenWhenIfIsExpire(){
    	try {
    		if (webAuth.getSaveToken().isExpire()) {
    			System.out.println("accessToken 过期，调用 getRefreshToken 接口");
    			String refreshToken = WeChat.webAuth.getRefreshToken(WeChat.webAuth.getSaveToken().getAccess_token());
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("access_token", refreshToken);
    			map.put("refresh_token", refreshToken);
    			webAuth.setAccessToken(map);
    			return (String) map.get("access_token");
    		}
    		else {
    			System.out.println("accessToken 没有过期,调用缓存数据");
    			return webAuth.getSaveToken().getAccess_token();
    		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    }
    
    /*
     * jsapi_ticket 获取处理 (包含过期处里)
     */
    public static String getTicketTokenWhenIfIsExpire(){
    	try {
    		if (webAuth.getSaveTicket().isExpire()) {
    			System.out.println("ticket 过期，调用 getTicket 接口");
    			Map<String, Object> map = getTicket(WeChat.webAuth.getSaveToken().getAccess_token());
    			webAuth.setTicket(map);
    			return (String) map.get("ticket");
    		}
    		else {
    			System.out.println("accessToken 没有过期,调用缓存数据");
    			return webAuth.getSaveTicket().getTicket();
    		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    }
    
    
    /**
     * 支付反馈
     *
     * @param openid
     * @param feedbackid
     * @return
     * @throws Exception
     */
    public static boolean payfeedback(String openid, String feedbackid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("feedbackid", feedbackid);
        String jsonStr = HttpKit.get(PAYFEEDBACK_URL, map);
        Map<String, Object> jsonMap = JSONObject.parseObject(jsonStr);
        return "0".equals(jsonMap.get("errcode").toString());
    }

    /**
     * 签名检查
     *
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        return Tools.checkSignature(token, signature, timestamp, nonce);
    }

    /**
     * 根据接收到用户消息进行处理
     *
     * @param responseInputString 微信发送过来的xml消息体
     * @return
     */
    public static String processing(String responseInputString) {
    	System.out.println("接收到的消息:" + responseInputString);
        InMessage inMessage = parsingInMessage(responseInputString);
        OutMessage oms = null;
        // 加载处理器
        if (messageProcessingHandlerClazz == null) {
            // 获取自定消息处理器，如果自定义处理器则使用默认处理器。
            String handler = ConfKit.get("MessageProcessingHandlerImpl");
            handler = handler == null ? DEFAULT_HANDLER : handler;
            try {
                messageProcessingHandlerClazz = Thread.currentThread().getContextClassLoader().loadClass(handler);
            } catch (Exception e) {
                throw new RuntimeException("messageProcessingHandler Load Error！");
            }
        }
        String xml = "";
        String type = "";
        try {
            MessageProcessingHandler messageProcessingHandler = (MessageProcessingHandler) messageProcessingHandlerClazz.newInstance();
            //取得消息类型
             type = inMessage.getMsgType();
            System.out.println("接收到的消息类型" + type);
            Method getOutMessage = messageProcessingHandler.getClass().getMethod("getOutMessage");
            Method alMt = messageProcessingHandler.getClass().getMethod("allType", InMessage.class);
            Method mt = messageProcessingHandler.getClass().getMethod(type + "TypeMsg", InMessage.class);

            alMt.invoke(messageProcessingHandler, inMessage);

            if (mt != null) {
                mt.invoke(messageProcessingHandler, inMessage);
            }

            Object obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
            }
            //调用事后处理
            try {
                Method aftMt = messageProcessingHandler.getClass().getMethod("afterProcess", InMessage.class, OutMessage.class);
                aftMt.invoke(messageProcessingHandler, inMessage, oms);
            } catch (Exception e) {
            }

            obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
                setMsgInfo(oms, inMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (oms != null) {
            // 把发送对象转换为xml输出
        	XStream xs = XStreamFactory.init(true);
        	// 自动解析注解
        	xs.autodetectAnnotations(true);
            xs.alias("xml", oms.getClass());
            xs.alias("item", Articles.class);
            xml = xs.toXML(oms);
           System.out.println("输出消息1:["+xml+"]");
        }
        return xml;
    }

    /**
     * 设置发送消息体
     *
     * @param oms
     * @param msg
     * @throws Exception
     */
    private static void setMsgInfo(OutMessage oms, InMessage msg) throws Exception {
        if (oms != null) {
            Class<?> outMsg = oms.getClass().getSuperclass();
            Field CreateTime = outMsg.getDeclaredField("CreateTime");
            Field ToUserName = outMsg.getDeclaredField("ToUserName");
            Field FromUserName = outMsg.getDeclaredField("FromUserName");

            ToUserName.setAccessible(true);
            CreateTime.setAccessible(true);
            FromUserName.setAccessible(true);

            CreateTime.set(oms, new Date().getTime());
            ToUserName.set(oms, msg.getFromUserName());
            FromUserName.set(oms, msg.getToUserName());
        }
    }

    /**
     * 消息体转换
     *
     * @param responseInputString
     * @return
     */
    private static InMessage parsingInMessage(String responseInputString) {
        //转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(responseInputString);
        return msg;
    }

    /**
     * 获取媒体资源
     *
     * @param accessToken
     * @param mediaId
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static Attachment getMedia(String accessToken, String mediaId) throws IOException, ExecutionException, InterruptedException {
        String url = GET_MEDIA_URL + accessToken + "&media_id=" + mediaId;
        return HttpKit.download(url);
    }

    /**
     * 上传素材文件
     *
     * @param type
     * @param file
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> uploadMedia(String accessToken, String type, File file) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException, ExecutionException, InterruptedException {
        String url = UPLOAD_MEDIA_URL + accessToken + "&type=" + type;
        String jsonStr = HttpKit.upload(url, file);
        return JSON.parseObject(jsonStr, Map.class);
    }
    
    
    /**
     * 新增其他类型永久素材
     *
     * @param type
     * @param file
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @SuppressWarnings("unchecked")
   	public static Map<String, Object> uploadOtherMedia(String accessToken, String type, String filePath) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException, ExecutionException, InterruptedException {
           String url = UPLOAD_add_material + accessToken;
           Map<String, String> textMap = new HashMap<String, String>(); 
           textMap.put("type", type); 
           Map<String, String> fileMap = new HashMap<String, String>(); 
           fileMap.put("media", filePath); 
              
           String jsonStr = HttpKit.uploadOtherMaterial(url, textMap, fileMap);
           return JSON.parseObject(jsonStr, Map.class);
       }
    

    /**
     * 获得jsapi_ticket（有效期7200秒)
     *
     * @param accessToken
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public static JSONObject getTicket(String accessToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        String jsonStr = HttpKit.get(JSAPI_TICKET.concat(accessToken));
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 生成jsApi的签名信息
     *
     * @param jsapiTicket
     * @param url
     * @return
     */
    public static Map<String, String> jsApiSign(String jsapiTicket, String url) {
        return JsApiSign.sign(jsapiTicket, url);
    }

    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     *
     * @param request
     * @return
     */
    public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
            Matcher m = p.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && NumberUtils.toInt(version) >= 5);
        }
        return false;
    }
}
