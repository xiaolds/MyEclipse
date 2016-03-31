<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=gbk" %>
<%@ page import="com.datasure.shop.*" %>
<%@ page import="com.datasure.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的购物车</title>
    
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
    
    <center>
    	 <!-- 显示用户名称 -->
    <c:out value="${sessionScope.name }"></c:out>
    <c:out value="我的商品"></c:out>
    	
    	<%--从数据库获取商品列表 --%>
    	<%! List<Product> list = null; %>
    	<%
    		list = ProductDAO.getProduct(new User((String)session.getAttribute("name")));
    	 %>
    	
    	
    	<form action="MyStore" method="post">
    	<table border="1">
    	<tr>
    		<th>商品</th>
    		<th>价格</th>
    		<th>库存数量</th>
    		<th>购买数量</th>
    	</tr>
    	<%
    	for(Product p: list){
    	 %>
    	 <tr>
    	 	<th><%=p.getName() %></th>
    	 	<th><%=p.getPrice() %></th>
    	 	<th><%=p.getNumbers() %></th>
    	 	<th><input type="text" name="${p.getName() }"></input></th>
    	 </tr>
    	 <%
    	 }
    	  %>
    	
    </table>
    </form>
    <form action="mystore.jsp" method="get">
    	<input type="submit" value="添加到购物车"></input>
    </form>
    <form action="index.jsp">
    	<input type="submit" value="退出">
    </form>	
    </center>
    
    
  </body>
</html>
