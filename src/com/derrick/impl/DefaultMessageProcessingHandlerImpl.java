/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.derrick.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
	private static List<Long> msgids = ConfKit.msgsList();
	
	@Override
	public void allType(InMessage msg){
		if (msgids.contains(msg.getMsgId()) == false){
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
		else {
			 System.out.println("重复消息 ： " + msg.getMsgId() + ":" + msg.getMsgId());
		}
		 msgids.add(msg.getMsgId());
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
					 Map<String, Object> mapToken = new HashMap<String, Object>();
					 mapToken.put("access_token", accessToken);
					 WeChat.webAuth.setAccessToken(mapToken);
				}
				String filePath = this.getClass().getResource("/").getPath() + "images/huangdou.jpg";
				Map<String, Object> backMap = WeChat.uploadOtherMedia(accessToken, "image", filePath);
				String weixinPicUrl = (String) backMap.get("url");
				System.out.println(backMap);
				NewsOutMessage newMsg = new NewsOutMessage();
				newMsg.setTitle("Hello world");
				List<Articles> list = new ArrayList<Articles>();
				Articles sendArt = new Articles();
				sendArt.setTitle("Hello world Title");
				sendArt.setDescription("Hello world SubTitle");
				sendArt.setPicUrl(weixinPicUrl);
				sendArt.setUrl("http://www.baidu.com");
				list.add(sendArt);
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
	    switch (msg.getEvent()) {
		case "LOCATION":
			break;
		case "subscribe":
			// 创建底部菜单
			 ConfKit.eventSource.notifyListenerEventWithMessage(msg);
			// 订阅频道
			TextOutMessage out = new TextOutMessage();
			out.setContent("感谢您的关注,༺乐淘༻为您服务.");
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
