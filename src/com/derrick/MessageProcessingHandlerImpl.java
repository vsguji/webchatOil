package com.derrick;

import com.derrick.domain.InMessage;
import com.derrick.domain.OutMessage;
import com.derrick.domain.TextOutMessage;
import com.derrick.impl.MessageProcessingHandler;

public class MessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage outMessage;
	/** 订阅 */
	public static final String SUBSCRIBE = "subscribe";
	/** 取消订阅 */
	public static final String UNSUBSCRIBE = "unsubscribe";
	
	@Override
	public void allType(InMessage msg){
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的消息已经收到！");
		setOutMessage(out);
	}
	
	@Override
	public void textTypeMsg(InMessage msg) {
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
		if(msg.getEvent().equals(SUBSCRIBE)){   //首次关注事件处理
			TextOutMessage out = new TextOutMessage();
			out.setContent("感谢您的关注，机器人Derrick-Hoh为你服务.");
			setOutMessage(out);
		}else{
			System.out.println("感谢您的关注，我们会努力做的更好" + msg.getFromUserName());
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
