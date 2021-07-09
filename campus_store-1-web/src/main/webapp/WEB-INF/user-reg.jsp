<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script type="text/javascript" src="static/script/jquery-2.1.1.min.js"></script>
<style>
.errorMsg {
	font-size: 20px;
	color: red;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#sub_btn").click(function(){
			var usernameText = $("#loginAcct").val();
			var usernamePatt = /^\w{5,12}$/;
			if ( !usernamePatt.test(usernameText) ) {
				$("span.errorMsg").text("用户名必须由字母，数字下划线组成，并且长度为5到12位");
				return false;
			}
			
			//1 先获取密码输入框中的内容
			var passwordText = $("#userPswd").val();
			//2 创建正则表达式对象
			var passwodPatt = /^\w{5,12}$/;
			//3 调用test方法验证结果
			if ( !passwodPatt.test(passwordText) ) {
				//4 如果不合法，要提示用户，并阻止提交。
				$("span.errorMsg").text("密码必须由字母，数字下划线组成，并且长度为5到12位");
				return false;
			}
			
			var repwdText = $("#SecondPswd").val();
			if ( repwdText != passwordText ) {
				$("span.errorMsg").text("确认密码和密码不一致!");
				return false;
			}
			
			var codeText = $("#userName").val();
			codeText = $.trim( codeText );
			if ( codeText == "" ) {
				$("span.errorMsg").text("用户名不能为空!");
				return false;
			}
			
			// 1 先获取邮箱内容
			var emailText = $("#email").val();
			// 2 创建邮箱正则表达式
			var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			// 3 使用test方法验证结果
			if ( !emailPatt.test(emailText) ) {
				// 4 不合法时提示用户，阻止提交
				$("span.errorMsg").text("邮箱格式不合法!");
				return false;
			}
			
			$("span.errorMsg").text("");
			return true;


		});
	});
</script>


</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="index.html" style="font-size: 32px;">校园小商店平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<form class="form-signin" action="user/reg" method="post">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户注册
			</h2>
			<span class="errorMsg">${ requestScope.msg }</span>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" name="loginAcct" id="loginAcct" 
					placeholder="请输入注册账号" autofocus> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" name="userPswd" id="userPswd"
					placeholder="请输入注册密码" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" name="SecondPswd" id="SecondPswd"
					placeholder="请再次输入注册密码" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" name="userName" id="userName"
					placeholder="请用户昵称" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" name="email" id="email"
					placeholder="请输入手机号" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>

			<input class="btn btn-lg btn-success btn-block" type="submit" id="sub_btn"
				value="注册" />
			<br>
			<a href="user/login" class="btn btn-lg btn-success btn-block">返回登录</a>
			<br>
			<a href="index" class="btn btn-lg btn-success btn-block">回主页</a>
		</form>

	</div>
</body>
</html>
