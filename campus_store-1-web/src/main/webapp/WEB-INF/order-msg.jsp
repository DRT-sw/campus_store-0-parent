<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<title>我的订单</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/style.css">
<script src="jquery/jquery-2.1.1.min.js"></script>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/DRTLogo.png" >
			<span class="wel_word">我的订单</span>
				

	</div>
	
	<div id="main">
		<table>
			<tr>
				<th>商品名称</th>
				<th>商品数量</th>
				<th>单价</th>
				<th>总价</th>
				<th>订单号</th>
			</tr>
			<c:forEach items="${ items }" var="item">
			<tr>
				<td>${ item.name }</td>
				<td>${ item.count }</td>
				<td>${ item.price }</td>
				<td>${ item.totalMoney }</td>
				<td>${ item.orderId }</td>
			</tr>
			</c:forEach>
		</table>
			<div style="text-align:center;" ><a href="order/all">返回订单总页</a></div> 
		<%-- <c:if test="${ sessionScope.admin == null }">
		</c:if> --%>
		<%-- <c:if test="${ sessionScope.admin != null }">
			<div style="text-align:center;" ><a href="managerOrderServlet?action=orders">返回订单总页</a></div> 
		</c:if> --%>
		
		
	
	</div>

</body>
</html>