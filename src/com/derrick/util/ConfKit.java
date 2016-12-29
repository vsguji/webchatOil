package com.derrick.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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

	static {
		try {
			//play框架下要用这种方式加载
			//props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechat.properties"));
			props.load(ConfKit.class.getResourceAsStream("/WebContent/WEB-INF/classes/wechat.properties"));
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
}
