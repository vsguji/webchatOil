package com.derrick.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import com.webchatOil.util.EventListenerSource;
/**
 * 
* @ClassName: ConfKit 
* @Description: TODO(配置文件) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:50:13 
*
 */
public class ConfKit {

	private static Properties props = new Properties();
	public static  int createMenuStatusCode = 0; //默认为0 未创建
	public static  String baseUrlString = null; // 后端地址
	public static  String baseUIString = null; // 前端地址
    public  static EventListenerSource eventSource = null;
	static {
		try {
			//play框架下要用这种方式加载
			//props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechat.properties"));
			props.load(ConfKit.class.getResourceAsStream("/wechat.properties"));
			eventSource = new EventListenerSource(); // 监听
			baseUrlString = props.getProperty("baseUrl");
			baseUIString = props.getProperty("baseUI");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
     
	public static String get(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
        props = p;
    }
    
    public static String getLocalPath (){
    	return Thread.currentThread().getContextClassLoader().getResource("/").getPath();
    }
    
	public static List<Long> msgsList() {
		// TODO Auto-generated method stub
		return new ArrayList<Long>(); // 等待改进
	}
}
