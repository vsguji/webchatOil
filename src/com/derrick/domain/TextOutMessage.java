package com.derrick.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
* @ClassName: TextOutMessage 
* @Description: TODO(输出文字消息) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:46:02 
*
 */
@XStreamAlias ( "xml" )
public class TextOutMessage extends OutMessage {
	@XStreamAlias("MsgType")
	private String	MsgType	= "text";
	// 文本消息
	@XStreamAlias("Content")
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
