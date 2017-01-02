package com.derrick.oauth;

public class WebAccessToken {
   public  String access_token;
   public  String refresh_token;
   public  int expires_in;
   public  String openid;
   public  String scope;
   private long timeStamp=System.currentTimeMillis()/1000;
   
    public String getAccess_token() {
		return access_token;
	}
    
	public void setAccess_token(String token) {
		this.access_token = token;
	}
   
	public String getRefresh_token() {
			return refresh_token;
	}
	    
	public void setRefresh_token(String token) {
			this.refresh_token = token;
	}
		
    public int getExpires_in() {
			return expires_in;
	}
    
	public void setExpires_in(int token) {
			this.expires_in = token;
	}
		
	public String getOpenid() {
			return openid;
	}
	    
	public void setOpenid(String openid) {
			this.openid = openid;
	}
	
	public String getScope() {
			return scope;
	}
 
	public void setScope(String scope) {
			this.scope = scope;
	}
	
	public boolean isExpire(){
		  return System.currentTimeMillis()/1000<(timeStamp+expires_in)?false:true;
	 }
}
