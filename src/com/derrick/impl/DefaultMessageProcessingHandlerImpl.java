/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.derrick.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;

import com.derrick.WeChat;
import com.derrick.domain.Articles;
import com.derrick.domain.Attachment;
import com.derrick.domain.InMessage;
import com.derrick.domain.NewsOutMessage;
import com.derrick.domain.OutMessage;
import com.derrick.domain.TemplateData;
import com.derrick.domain.TextOutMessage;
import com.derrick.util.ConfKit;
import com.webchatOil.action.activtiyAction;

public class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage outMessage;
	private Logger logger = Logger.getLogger(DefaultMessageProcessingHandlerImpl.class);
	
	@Override
	public void allType(InMessage msg){
	 switch (msg.getMsgType()) {
	    case "event":
	    	eventTypeMsg(msg);
		   break;
	    case "text":
	    	textTypeMsg(msg);
	      break;    
    	default:
		  break;
	   }
	}
	
	@Override
	public void textTypeMsg(InMessage msg) {
		String msgContentString  = msg.getContent();
		if (msgContentString.contains("大豆") == true){ // 商品存储表
			String accessToken;
			try {
				accessToken = WeChat.webAuth.getSaveToken().access_token; 
				if (accessToken == null) {
					accessToken = WeChat.getAccessToken();
					//WeChat.webAuth.setAccessToken(accessToken);
				}
				String baseurString = this.getClass().getResource("/webchatOil").getPath();
				String baseurString1 = this.getClass().getResource("/webchatOil").getPath() + "static/image/huangdou.jpg";
				String url = "/Library/Tomcat/webapps/webchatOil/static/image/huangdou.jpg";
				Map<String, Object> backMap = WeChat.uploadMedia(accessToken, "thumb", new File(url));
				System.out.println(backMap);
				String media_id = (String) backMap.get("thumb_media_id");
				Attachment mediaMent = WeChat.getMedia(accessToken, media_id);
				NewsOutMessage newMsg = new NewsOutMessage();
				newMsg.setTitle("Hello world");
				List<Articles> list = new ArrayList<Articles>();
				Articles at1 = new Articles();
				at1.setTitle("Hello world Title");
				at1.setDescription("Hello world SubTitle");
				String baseUrlString = ConfKit.baseUrlString;
				at1.setPicUrl(baseUrlString + "static/image/huangdou.jpg");
				at1.setUrl("http://www.baidu.com");
				list.add(at1);
				newMsg.setArticles(list);
				setOutMessage(newMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgContentString.contains("通知") == true) {
			try {
				 String accessTokenNormal = WeChat.getAccessToken();
				 TemplateData tempData = new TemplateData(msg.getFromUserName(), "");
				  WeChat.message.templateSend(accessTokenNormal, tempData);
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		else {
			TextOutMessage out = new TextOutMessage();
			out.setContent("您的消息已经收到！");
			setOutMessage(out);
		}
	}
	@Override
	public void locationTypeMsg(InMessage msg) {
	}

	@Override
	public void imageTypeMsg(InMessage msg) {
	}
	
	@Override
	public void videoTypeMsg(InMessage msg) {
	}
	
	@Override
	public void voiceTypeMsg(InMessage msg) {
	}

	@Override
	public void linkTypeMsg(InMessage msg) {
	}
	
	@Override
	public void verifyTypeMsg(InMessage msg) {}

	@Override
	public void eventTypeMsg(InMessage msg) {
		System.out.println(msg.getEvent());
		System.out.println(msg.getEventKey());
	    switch (msg.getEvent()) {
		case "LOCATION":
			break;
		case "subscribe":
			// 创建底部菜单
			 ConfKit.eventSource.notifyListenerEventWithMessage(msg);
			// 订阅频道
			TextOutMessage out = new TextOutMessage();
			out.setContent("感谢您的关注,༺乐淘༻为你服务.");
			setOutMessage(out);
			break;
		case "unsubscribe":// 取消订阅频道  删除历史消息
			break;
		default:
			break;
		}
	}
	
	@Override
	public void setOutMessage(OutMessage outMessage) {
		this.outMessage = outMessage;
	}
	
	@Override
	public void afterProcess(InMessage inMessage,OutMessage outMessage) {
	}
	
	@Override
	public OutMessage getOutMessage() {
		return outMessage;
	}
}
