package com.webchatOil.action;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import net.sf.json.JSONObject;

import com.derrick.WeChat;

@SuppressWarnings("unused")
public class jspSign {

	public static String getTicketToken (){
		 String ticketToken = null;
		try {
			String access_token = WeChat.getAccessTokenWhenIfIsExpire() ;
			if (WeChat.webAuth.getSaveTicket() == null || WeChat.webAuth.getSaveTicket().getTicket() == null){
				Map<String,Object> objMap = WeChat.getTicket(access_token);
				WeChat.webAuth.setTicket(objMap);
				ticketToken = (String)objMap.get("ticket");
			}
			else {
				ticketToken = WeChat.getTicketTokenWhenIfIsExpire();
			}
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticketToken;
	}
}
