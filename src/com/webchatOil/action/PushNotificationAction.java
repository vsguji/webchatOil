package com.webchatOil.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.webchatOil.util.RequestTypeAnnotation;
import com.webchatOil.util.RequestTypeAnnotation.RequestType;

import javapns.notification.*;
import javapns.devices.*;
import javapns.devices.implementations.basic.BasicDevice;

public class PushNotificationAction extends BaseAction{
	private static final long serialVersionUID = 1111L;
	HttpServletRequest request = getRequest();
	HttpServletResponse response = getResponse();
	private Logger logger = Logger.getLogger(activtiyAction.class);
	
	/**
	 * 添加远程推送通知
	 */
	@RequestTypeAnnotation(RequestType.GET)
	public void regsistPushNotification(){
		  String deviceToken = request.getParameter("deviceToken");
	        String alert = "我的push测试";//push的内容
	        int badge = 100;//图标小红圈的数值
	        String sound = "default";//铃音

	        List<String> tokens = new ArrayList<String>();
	        tokens.add(deviceToken);
	        String certificatePath = this.getClass().getResource("/").getPath() + "push.p12";
	        String certificatePassword = "12345";//此处注意导出的证书密码不能为空因为空密码会报错
	        boolean sendCount = true;

	        try
	        {
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert(alert); // 消息内容
	            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
	            if (!StringUtils.isBlank(sound))
	            {
	                payLoad.addSound(sound);//铃音
	            }
	            PushNotificationManager pushManager = new PushNotificationManager();
	            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	            // 发送push消息
	            if (sendCount)
	            {
	                Device device = new BasicDevice();
	                device.setToken(tokens.get(0));
	                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
	                notifications.add(notification);
	            }
	            else
	            {
	                List<Device> device = new ArrayList<Device>();
	                for (String token : tokens)
	                {
	                    device.add(new BasicDevice(token));
	                }
	                notifications = pushManager.sendNotifications(payLoad, device);
	            }
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	            int failed = failedNotifications.size();
	            int successful = successfulNotifications.size();
	            pushManager.stopConnection();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
    }
}
