package com.derrick.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias ( "xml" )
public class OutMessage {
	
	@XStreamAlias ( "ToUserName" )
	private String	ToUserName;
	
	@XStreamAlias ( "FromUserName" )
	private String	FromUserName;
	
	@XStreamAlias ( "CreateTime" )
	private Long	CreateTime;
	
	@XStreamAlias ( "FuncFlag" )
	private int		FuncFlag	= 0;

	public String getToUserName() {
		return ToUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}