<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<link rel="stylesheet" href="css/style.css">
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${ orderId }</h1>
	
		<a href="index">继续购物</a>
		<a href="order/all">我的订单</a>
		
	</div>
	

</body>
</html>