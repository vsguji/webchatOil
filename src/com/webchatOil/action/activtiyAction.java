package com.webchatOil.action;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.webchatOil.action.BaseAction;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.UserService;
import com.webchatOil.util.EventListenerObject;
import com.webchatOil.util.BarEventListener;
import com.alibaba.fastjson.JSON;
import com.derrick.WeChatFilter;
import com.derrick.WeChat;
import com.derrick.domain.Data4Button;
import com.derrick.domain.Data4Menu;
import com.derrick.domain.InMessage;
import com.derrick.domain.UserInfo;
import com.derrick.exception.WeixinMenuOutOfBoundException;
import com.derrick.exception.WeixinSubMenuOutOfBoundException;
import com.derrick.oauth.Menu;
import com.derrick.oauth.Oauth;
import com.derrick.oauth.User;
import com.derrick.util.ConfKit;


//Spring注解@Scope("prototype")
//Spring在Action上面注解@Scope("prototype")
//
//表示每次接收一个请求创建一个Action对象..
//
//如若改成其他,例如单例模式,则很多请求公用同一个Action.
//
//一个注册的例子,如果没加上这个注解,注册完成后,下一个请求再注册一次,Action里会保留上一次注册的信息..
// 保证线程安全
public class activtiyAction extends BaseAction implements BarEventListener {
	private static final long serialVersionUID = 3321845277376234101L;
	private static WeChatFilter filter = new WeChatFilter();
	HttpServletRequest request = getRequest();
	HttpServletResponse response = getResponse();
	private Logger logger = Logger.getLogger(activtiyAction.class);
	private int recAuthorTime = 0;
	/**
	 * 采用注解的方式引入Service类
	 */
	@Autowired
    private UserService userService;
	
	private int page;
	
	private PageBean pageBean;
	/**
	 * 客户信息的列表
	 */
	private List usersInfo;
	/*
	 * 服务器认证
	 */
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	@SuppressWarnings("deprecation")
	public void doGet() throws Exception{
		// 接口配置
		filter.doFilter(request, response, null);
		int code = response.getStatus();
		// 测试订阅号 自定义菜单
		  if (code == HttpServletResponse.SC_OK && ConfKit.createMenuStatusCode == 0 ) {
			  System.out.println("ok");
			  logger.log(Priority.DEBUG, "ok");
			  ConfKit.eventSource.addListener(this); // 注册监听 底部菜单
			  ConfKit.createMenuStatusCode = 1;
		  }
	}
	
	/*
	 * 创建自定义菜单
	 *  // 数据库操作,根据用户权限动态显示菜单
	   // 1.直接点击关注，视为新用户
	   // 2.通过link,引导用户订阅，成为目标用户。
	 */
	
	public void setupMenu(String userid) throws Exception{
		 Map<String, Object>  accessTokenMap = WeChat.getAccessTokenMap();
		    if (userid.length() > 0){
		    	accessTokenMap.put("openid", userid);
		    }
			WeChat.webAuth.setAccessToken(accessTokenMap);
			
			if (WeChat.webAuth.getSaveToken().getAccess_token() != null && WeChat.webAuth.getSaveToken().isExpire()){
				String refreshToken = WeChat.webAuth.getRefreshToken(WeChat.webAuth.getSaveToken().getAccess_token());
				accessTokenMap.replace(refreshToken, refreshToken);
			}
		   // 创建按钮
		   Data4Button btn = null;
//		  String userid0 = "oy-F2t1TtETlOjqXkAJG6whKY9nQ";
		   if (isExist(userid) == false){ // 用户不存在，添加新用户
			   // 1.拉取客户基本信息
			   UserInfo baseInfo = WeChat.user.getUserInfo(WeChat.webAuth.getSaveToken().getAccess_token(), userid);
			   LKUserinfo newLkUserinfo = new LKUserinfo();
			   newLkUserinfo.setWechatID(baseInfo.getOpenid());
			   newLkUserinfo.setE_name(baseInfo.getNickname());
			   newLkUserinfo.setWechatPic(baseInfo.getHeadimgurl());
			   newLkUserinfo.setSex(Integer.toString( baseInfo.getSex()));
			   newLkUserinfo.setPopedom("employee");
			   userService.insertNewUser(newLkUserinfo);
			   btn = build2Items();
			   logger.log(Priority.DEBUG, "添加新目标用户");
		   }
		   else {
			   String userTypeString = getUserType(userid).trim();
			   if (userTypeString.equals("admin")) {
				   btn = buildI1tems();
			   }
			   else if (userTypeString.equals("employee")) {
				   btn = build2Items();
			   }
		   }
		   // 创建菜单项
		   if (btn != null) {
			   Menu menu = WeChat.menu; 
			   String menus = JSON.toJSONString(btn);
			   menu.createMenu(WeChat.webAuth.getSaveToken().getAccess_token(), menus);
			   System.out.println(menu.createMenu(WeChat.webAuth.getSaveToken().getAccess_token(), menus));
		   }
		   else {
			   System.out.println("error");
		   }
	}
	
	/*
	 * 底部菜单项1 admin,manage
	 */
	 public Data4Button buildI1tems() throws WeixinSubMenuOutOfBoundException, WeixinMenuOutOfBoundException{
		   String urlPath = ConfKit.baseUrlString;
		   String localPath = ConfKit.getLocalPath();
		   String fullLocalPath = localPath + "jsp/feed/members.jsp";
		   String fullUrlPath = urlPath + "jsp/feed/members.jsp";
		   System.out.println("fullLocalPath: " + fullLocalPath);
		   System.out.println("fullUrlPath: " + fullUrlPath);
		   String uiString = ConfKit.baseUIString;
		   String goodsString = uiString + "html/goods.html";
		   String updateGoodsString = uiString + "html/goods/filesUpload/uploadGoods.html";
		   String feedsString = uiString + "html/employees.html";
		   Data4Button btn = new Data4Button();
		   Data4Menu menu1 = new Data4Menu("view", "供求列表",fullUrlPath);
		   Data4Menu menu2 = new Data4Menu("click", "发布商品", "goodsBtn");
		   Data4Menu menu2_0 = new Data4Menu("view", "时间轴",goodsString);
		   Data4Menu menu2_1 = new Data4Menu("view", "发布新商品",updateGoodsString);
		   menu2.addSubMenu(menu2_0);
		   menu2.addSubMenu(menu2_1);
		   Data4Menu menu3 = new Data4Menu("click", "客户列表", "employeesBtn");
		   btn.addMenu(menu1);
		   btn.addMenu(menu2);
		   Data4Menu menu3_0 = new Data4Menu("view", "供应商",feedsString);
		   Data4Menu menu3_1 = new Data4Menu("view", "消费者",feedsString);
		   menu3.addSubMenu(menu3_0);
		   menu3.addSubMenu(menu3_1);
		   btn.addMenu(menu3);
		   return btn;
	}
	 
	 /*
	  * 底部菜单项2 employee
	  */
	 public Data4Button build2Items() throws WeixinSubMenuOutOfBoundException, WeixinMenuOutOfBoundException{
		  String path = ConfKit.baseUrlString;
		  String uiString = ConfKit.baseUIString;
		  String goodsString = uiString + "html/goods.html";
		  Data4Button btn = new Data4Button();
		 // 创建一级菜单
		   Data4Menu menu1 = new Data4Menu("click", "供求信息","Btn_0");
		   Data4Menu menu2 = new Data4Menu("click", "粮食购销", "Btn_1");
		   Data4Menu menu3 = new Data4Menu("view", "意见留言",  path + "recAuthAction");
		   // 创建二级菜单
		   // 1.供求信息
		   Data4Menu menu1_0 = new Data4Menu("view", "发布求购信息",goodsString);
		   Data4Menu menu1_1 = new Data4Menu("view", "发布出售信息", goodsString);
		   menu1.addSubMenu(menu1_0);
		   menu1.addSubMenu(menu1_1);
		   // 2.粮食购销
		   Data4Menu menu2_0 = new Data4Menu("view", "线上下单",goodsString);
		   Data4Menu menu2_1 = new Data4Menu("view", "扫码支付",goodsString);
		   Data4Menu menu2_2 = new Data4Menu("view", "历史记录",goodsString);
		   menu2.addSubMenu(menu2_0);;
		   menu2.addSubMenu(menu2_1);
		   menu2.addSubMenu(menu2_2);
		   // 3.留言
		   btn.addMenu(menu1);
		   btn.addMenu(menu2);
		   btn.addMenu(menu3);
		   return btn;
	}

	 /*
	  * 验证用户是否存在
	  */
	 public boolean isExist(String userid){
		 List currentUserinfo = userService.getSingleUserInfo(userid);
		 if (currentUserinfo.size() != 0){
			 return true;
		 }
		 return false;
	 }
	 
	/*
	 * 获取用户权限
	 */
	public String getUserType(String userid){
	    List currentUserinfo = userService.getSingleUserInfo(userid);
	    LKUserinfo item0 = (LKUserinfo) currentUserinfo.get(0);
		return item0.getPopedom();
	}

	public String getAllInfo() throws Exception {
		pageBean = userService.getAllUsersInfo(3, page);
		usersInfo = pageBean.getList();
		return SUCCESS;
	}
	
	/*
	 * 网页授权
	 */
	 public void recAuthAction() throws Exception{
		 String code = request.getParameter("code");
		 Oauth createAuthOauth = WeChat.webAuth;
		 if (code != null){
			 System.out.println("yes");
		 }
		 else {
			 System.out.println("no");
		 }
		 if (code != null) {
			 if (createAuthOauth.accessToken == null) {
				 String accessTokenString = createAuthOauth.getToken(code);
				 Map<String, Object> map1 = new HashMap<String, Object>();
				   map1.put("access_token", accessTokenString);
				  createAuthOauth.setAccessToken(map1);
				  recAuthorTime = 0;
			 }
			 else if (createAuthOauth.accessToken.isExpire() && recAuthorTime == 0) { 
				 System.out.println("token 过期");
				 String oauthURlString = createAuthOauth.getCode();
				 response.sendRedirect(oauthURlString);
				 recAuthorTime = 1;
				 return;
			 }
			   
			 // 这个地方 accessToken 问题：上面 accessTokenString 里面是从网页授权获取一个accessToken
			 // 获取用户基本信息 是用普通的 accessToken
			 String accessTokenNormal = WeChat.getAccessToken();
			 User currentUser = WeChat.user;
			 UserInfo currentUserInfo = currentUser.getUserInfo(accessTokenNormal, createAuthOauth.accessToken.getOpenid());
			 System.out.println(currentUserInfo.getNickname());
			 request.setAttribute("snsUserInfo", currentUserInfo);
//			 response.sendRedirect(request.getContextPath() + "/chatOil.jsp"); 
			 request.getRequestDispatcher("/chatOil.jsp").forward(request, response);
			 System.out.println(request.getContextPath() + "/chatOil.jsp");
		 }
		 else {
			 if (createAuthOauth.accessToken == null && code != null) {
				 String accessTokenString = createAuthOauth.getToken(code);
				  Map<String, Object> map1 = new HashMap<String, Object>();
				   map1.put("access_token", accessTokenString);
				  createAuthOauth.setAccessToken(map1);
				  recAuthorTime = 0;
			 }
			 else if (createAuthOauth.accessToken != null && createAuthOauth.accessToken.isExpire() && recAuthorTime == 0) { 
				 System.out.println("token 过期");
				 String oauthURlString = createAuthOauth.getCode();
				 response.sendRedirect(oauthURlString);
				 recAuthorTime = 1;
				 return;
			 }
			 System.out.println("意见留言--自动跳转到微信浏览器授权页面");
			 String oauthURlString = createAuthOauth.getCode();
			 response.sendRedirect(oauthURlString);
		 }
	 }

	 /*
	  * (non-Javadoc)
	  * @see com.webchatOil.util.InterfaceEventListener#process(com.webchatOil.util.EventListenerObject)
	  * 回调:创建底部 菜单项
	  */
	@Override
	public void process(EventListenerObject event) {
		// TODO Auto-generated method stub
		System.out.println("notificate message success!!");
		InMessage msgObjInMessage = event.getMsg();
		try {
			setupMenu(msgObjInMessage.getFromUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/*
	 * 测试方式
	 */
	public String doTest() throws ServletException, IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, InterruptedException, ExecutionException{
		   String path = this.getClass().getResource("/").getPath() + "images/huangdou.jpg";
		    File file1 = new File(path);
		    System.out.println(file1.exists());
		   // request.getRequestDispatcher("/jsp/feed/members.jsp").forward(request, response);
		    // jssdk 验证
		    String accessToken = WeChat.getAccessTokenWhenIfIsExpire();
		    String jsptickect = WeChat.getTicket(accessToken).toString();
		    System.out.println("jsptickect : " + jsptickect);
		    return "success";
	   }
	
	/*
	 * 上传永久图片素材
	 */
	public void uploadMedia() {
		String accessTokenString = "EvogJPq1eHHFj9X4uu2OpMjU6RtYXpHORhKjPHfHgJAWI1yCRMraI1PYZE8iWCEJwUHN8Jot0mucn2KKhIyaxJ2TBqTKxz-lRhvqSyEAyrUAPUdAHAODI";
		String filePath = this.getClass().getResource("/").getPath() + "images/huangdou.jpg";
		try {
			Map<String, Object> resultMap = WeChat.uploadOtherMedia(accessTokenString, "image", filePath);
			System.out.println("result : " + resultMap);
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException | ExecutionException
				| InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 上传永久视频素材
	 */
	public void uploadMediaVideo(){
		
	}
	
	/*
	 * 获取永久素材列表
	 */
	public void getMediaLists(){
		
	}
	
	/*
	 * 获取永久素材总数 
	 */
	public void getMediaCount(){
		
	}
	
	/*
	 * 获取临时素材
	 */
	public void getTemporeMedia(){
		
	}
	
	/*
	 * 新增临时素材
	 */
	public void addTemporeMedia(){
		
	}
	
	/*
	 * 删除临时素材
	 */
	public void deleteTemporeMedia(){
		
	}
	
	/*
	 * 修改临时素材
	 */
	public void modifyTemporeMedia(){
		
	}
}
