<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=gbk" %>


<%--��½ҳ�� --%>
<!-- ʹ�ñ�ǩ -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>��ӭ��½</title>
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
    <%--����Session,�˳���¼ --%>
    <%session.invalidate();%>
    
    <!-- ���� -->
    <h1 align="center">�����̳�</h1><br>
    
    <!-- �� -->
    <center>
    <form action="login" method="post">
    	������<input type="text" name="name"></input><br>
    	���룺<input type="password" name="password"></input><p>
    	<input type="submit" value="��½" class="Login"></input>
    	<input type="reset" value="����"></input><br>
    </form>
    
    <form action="login" method="get">
    	<input type="submit" name="signin" value="ע��">
    </form>
    
    <form action="login" method="get">
    	<input type="submit" name="hangup" value="�����">
    </form>

    </center>
  </body>
</html>
