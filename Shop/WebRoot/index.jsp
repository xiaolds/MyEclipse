<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=gbk" %>


<%--登陆页面 --%>
<!-- 使用标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>欢迎登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <%--销毁Session,退出登录 --%>
    <%session.invalidate();%>
    
    <!-- 标题 -->
    <h1 align="center">东东商城</h1><br>
    
    <!-- 表单 -->
    <center>
    <form action="login" method="post">
    	姓名：<input type="text" name="name"></input><br>
    	密码：<input type="password" name="password"></input><p>
    	<input type="submit" value="登陆" class="Login"></input>
    	<input type="reset" value="重置"></input><br>
    </form>
    
    <form action="login" method="get">
    	<input type="submit" name="signin" value="注册">
    </form>
    
    <form action="login" method="get">
    	<input type="submit" name="hangup" value="随便逛逛">
    </form>

    </center>
  </body>
</html>
