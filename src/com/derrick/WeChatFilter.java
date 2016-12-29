package com.derrick;

import com.derrick.util.Tools;

import org.apache.log4j.Logger;

import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 微信公众平台开发模式(JAVA) SDK
* @ClassName: WeChatFilter 
* @Description: TODO(请求拦截) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:42:40 
*
 */
public class WeChatFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(WeChatFilter.class);
    private String			conf			= "classPath:wechat.properties";
    private String			defaultHandler	= "com.derrick.MessageProcessingHandlerImpl";
	private Properties		p;
    
    @Override
    public void destroy() {
        LOGGER.info("WeChatFilter已经销毁");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Boolean isGet = request.getMethod().equals("GET");

        if (isGet) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        String xmlMsg = Tools.inputStream2String(in);
        LOGGER.debug("输入消息:[" + xmlMsg + "]");
        String xml = WeChat.processing(xmlMsg);
        response.getWriter().write(xml);
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token;
        String outPut = "error";
        if (pathInfo != null) {
            _token = pathInfo.substring(1);
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");//
            // 验证
            if (WeChat.checkSignature(_token, signature, timestamp, nonce)) {
                outPut = echostr;
            }
        }
        response.getWriter().write(outPut);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    	String cf = config.getInitParameter("conf");
		if (cf != null) {
			conf = cf;
		}
		String classPath = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
		conf = conf.replace("classPath:", classPath);
		p = new Properties();
		File pfile = new File(conf);
		if (pfile.exists()) {
			try {
				p.load(new FileInputStream(pfile));
			} catch (FileNotFoundException e) {
				LOGGER.error("未找到wechat.properties", e);
			} catch (IOException e) {
				LOGGER.error("wechat.properties读取异常", e);
			}
		}
		LOGGER.info("WeChatFilter已经启动！");
    }

}
