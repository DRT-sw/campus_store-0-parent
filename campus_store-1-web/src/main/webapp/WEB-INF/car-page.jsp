<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
	$(function(){
		// 查找删除，绑定单击事件
		$("a.deleteClass").click(function(){
			return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗?");
		});
		// change事件，会检查你离开后，和原来是不是不相同，如果不相同就会触发事件
		$("input.updateClass").change(function(){
			var newCount = this.value;
			var id = $(this).attr("itemId");
			if (confirm("你确定要修改【" + $(this).parent().parent().find("td:first").text() + "】数量为：" + newCount + " 吗?")) {
				// 点击了确认
				if(newCount<0){
					alert("商品价格不能小于0");
					return false;
				}
				location.href = "car/update?id="+id+"&count="+newCount;
			} else {
				// 点击了取消
				this.value = this.defaultValue;
			}
		
		});
		
		// 清空购物车单击事件
		$("#clearCart").click(function(){
			return confirm("你确定清空购物车吗?");
		});
	});
</script>
</head>
<body>

	<div id="header">
		<span class="wel_word">商品购物车</span>
		
		<span class="cart_span"><a href="index">继续购物</a></span>
		<span class="cart_span"><a href="order/all">我的订单</a></span>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			
			<c:if test="${ not empty sessionScope.cart.items }">
				<c:forEach items="${ sessionScope.cart.items }" var="entry">
					<tr>
						<td>${ entry.value.name }</td>
						<td><input type="text" name="count"
							value="${ entry.value.count }" itemId="${ entry.value.id }"
							style="width: 60px;" class="updateClass" /></td>
						<td>${ entry.value.price }</td>
						<td>${ entry.value.totalMoney }</td>
						<td>
							<a class="deleteClass" href="car/delete?id=${ entry.value.id }">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${ empty sessionScope.cart.items }">
				<tr>
					<td colspan="5"><a href="index">当前购物车为空！亲，赶快去购物吧！</a></td>
				</tr>
			</c:if>


		</table>
		<c:if test="${ not empty sessionScope.cart.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有
					<span class="b_count">${ sessionScope.cart.totalCount }</span>件商品
				</span> 
				<span class="cart_span">总金额
					<span class="b_price">${ sessionScope.cart.totalMoney }</span>元
				</span> 
				<span class="cart_span"> 
					<a id="clearCart" href="car/clear">清空购物车</a>
				</span> 
				
				<!-- 未完成 -->
				<span class="cart_span">
					<a href="order/createOrder">去结账</a>
				</span>
				
				
				
			</div>
		</c:if>

	</div>

</body>
</html>