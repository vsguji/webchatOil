<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*" %>
 <%@ page import="com.derrick.domain.UserInfo;"%>  
<%@ taglib prefix="s" uri="/struts-tags"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
<title>意见留言</title>
<style type="text/css">
  *{margin:0; padding:0}
  table{border:1px dashed #B9B9DD;font-size:12pt}
  td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
</style>
</head>
<body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<a>网页授权</a>
<% 
  // 获取由OAuthServlet中传入的参数
  UserInfo user = (UserInfo)request.getAttribute("snsUserInfo"); 
  if(null != user) {
%>
<table width="100%" cellspacing="0" cellpadding="0">
  <tr><td width="20%">属性</td><td width="80%">值</td></tr>
  <tr><td>OpenID</td><td><%=user.getOpenid()%></td></tr>
  <tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
  <tr><td>性别</td><td><%=user.getSex()%></td></tr>
  <tr><td>国家</td><td><%=user.getCountry()%></td></tr>
  <tr><td>省份</td><td><%=user.getProvince()%></td></tr>
  <tr><td>城市</td><td><%=user.getCity()%></td></tr>
  <tr><td>头像</td><td><%=user.getHeadimgurl()%></td></tr>
  <tr><td>特权</td><td><%=user.getProvince()%></td></tr>
</table>
<%
  }
  else 
   out.print("用户不同意授权,未获取到用户信息！");
%>
</body>
</html>