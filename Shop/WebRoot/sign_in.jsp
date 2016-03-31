<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=gbk" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册页面</title>
    
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
       <!-- 标题 -->
    <h1 align="center">东东商城</h1><br>
    <h2 align="center">注册页面</h2>
    <!-- 表单 -->
    <center>
    <form action="login" method="post">
    	姓名：<input type="text" name="name"></input><br>
    	密码：<input type="password" name="password"></input><p>
    	<input type="submit" value="提交" class="Login"></input>
    	<input type="reset" value="重置"></input><br>
    </form>

    </center>
  </body>
</html>
