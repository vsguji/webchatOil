package com.derrick.oauth;

/*
 * jsticket 
 */
public class jspTicketAccessToken {
   
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;
	
	public void setErrcode(int code){
		this.errcode = code;
	}
	public int getErrcode(){
		return this.errcode;
	}
	public void setErrmsg(String msg){
		this.errmsg = msg;
	}
	public String getErrmsg(){
		return this.errmsg;
	}
	public void setTicket(String ticket){
		this.ticket = ticket;
	}
	public String getTicket(){
		return this.ticket;
	}
	public void setExpires_in(int expire){
		this.expires_in = expire;
	}
	public int getExpires_in(){
		return this.expires_in;
	}
}
