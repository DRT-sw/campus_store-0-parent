<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/style.css">
<script src="jquery/jquery-2.1.1.min.js"></script>
</head>
<body>

	<div id="header">
		<span class="wel_word">我的订单</span>
		<a href="index">继续购物</a>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<!-- <td>详情</td> -->
			</tr>
			<c:forEach items="${ myOrders }" var="order">
				<tr>
					<td>${ order.createTime }</td>
					<td>${ order.totalMoney }</td>
					<td><c:choose>
							<c:when test="${ order.status == 0 }">
								未发货
							</c:when>
							<c:when test="${ order.status == 1 }">
								<a
									href="clientOrderServlet?action=receivedOrder&orderId=${ order.orderId }">确认收货</a>
							</c:when>
							<c:when test="${ order.status == 2 }">
								已签收
							</c:when>
						</c:choose></td>
					<%-- <td><a
						href="order/details?orderId=${ order.orderId }">查看详情</a></td> --%>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>