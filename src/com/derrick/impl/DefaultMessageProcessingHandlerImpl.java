/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.derrick.impl;

import java.util.ArrayList;
import java.util.List;

import com.derrick.WeChat;
import com.derrick.domain.Articles;
import com.derrick.domain.InMessage;
import com.derrick.domain.NewsOutMessage;
import com.derrick.domain.OutMessage;
import com.derrick.domain.TemplateData;
import com.derrick.domain.TextOutMessage;
import com.derrick.util.ConfKit;

public class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage outMessage;
	
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
			NewsOutMessage newMsg = new NewsOutMessage();
			newMsg.setTitle("Hello world");
			newMsg.setUrl("http://www.baidu.com");
			List<Articles> list = new ArrayList<Articles>();
			Articles at1 = new Articles();
			at1.setTitle("Hello world Title");
			at1.setDescription("Hello world SubTitle");
			list.add(at1);
			newMsg.setArticles(list);
			setOutMessage(newMsg);
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
