<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=gbk" %>
<%@ page import="com.datasure.shop.*" %>
<%@ page import="com.datasure.sql.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>�ҵĹ��ﳵ</title>
    
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
    <!-- ���� -->
    <h1 align="center">�����̳�</h1><br>
    
    <center>
    	 <!-- ��ʾ�û����� -->
    <c:out value="${sessionScope.name }"></c:out>
    <c:out value="�ҵ���Ʒ"></c:out>
    	
    	<%--�����ݿ��ȡ��Ʒ�б� --%>
    	<%! List<Product> list = null; %>
    	<%
    		list = ProductDAO.getProduct(new User((String)session.getAttribute("name")));
    	 %>
    	
    	
    	<form action="MyStore" method="post">
    	<table border="1">
    	<tr>
    		<th>��Ʒ</th>
    		<th>�۸�</th>
    		<th>�������</th>
    		<th>��������</th>
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
    	<input type="submit" value="��ӵ����ﳵ"></input>
    </form>
    <form action="index.jsp">
    	<input type="submit" value="�˳�">
    </form>	
    </center>
    
    
  </body>
</html>
