package com.webchatOil.util;

import java.util.EventObject;

import com.derrick.domain.InMessage;

public class EventListenerObject extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	private InMessage msg;
	
	public EventListenerObject(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public void setMsg(InMessage message){
		this.msg = message;
	}
   
	public InMessage getMsg(){
		return this.msg;
	}
}
