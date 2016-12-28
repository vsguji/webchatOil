package com.derrick.domain;

/**
 * 
* @ClassName: TextOutMessage 
* @Description: TODO(输出文字消息) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:46:02 
*
 */
public class TextOutMessage extends OutMessage {

	private String	MsgType	= "text";
	// 文本消息
	private String	Content;
	
	public TextOutMessage() {
	}
	
	public TextOutMessage(String content) {
		Content = content;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
