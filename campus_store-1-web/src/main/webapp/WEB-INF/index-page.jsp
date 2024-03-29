<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/carousel.css">
<link rel="stylesheet" href="css/pagination.css" />
<link rel="stylesheet" href="css/main.css">
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="script/docs.min.js"></script>
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/back-to-top.js"></script>

<style>
h3 {
	font-weight: bold;
}

.myfount {
	font-size: 18px;
}

#footer {
	padding: 15px 0;
	background: #fff;
	border-top: 1px solid #ddd;
	text-align: center;
}

#topcontrol {
	color: #fff;
	z-index: 99;
	width: 30px;
	height: 30px;
	font-size: 20px;
	background: #222;
	position: relative;
	right: 14px !important;
	bottom: 11px !important;
	border-radius: 3px !important;
}

#topcontrol:after {
	/*top: -2px;*/
	left: 8.5px;
	content: "\f106";
	position: absolute;
	text-align: center;
	font-family: FontAwesome;
}

#topcontrol:hover {
	color: #fff;
	background: #18ba9b;
	-webkit-transition: all 0.3s ease-in-out;
	-moz-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;
}
/* 侧栏导航 */
.sideBox {
	padding: 10px;
	height: 220px;
	background: #fff;
	margin-bottom: 10px;
	overflow: hidden;
}

.sideBox .hd {
	height: 30px;
	line-height: 30px;
	background: #f60;
	padding: 0 10px;
	text-align: center;
	overflow: hidden;
}

.sideBox .hd .more {
	color: #fff;
}

.sideBox .hd h3 span {
	font-weight: bold;
	font-size: 14px;
	color: #fff;
}

.sideBox .bd {
	padding: 5px 0 0;
}

#sideMenu .bd li {
	margin-bottom: 2px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	overflow: hidden;
}

#sideMenu .bd li a {
	display: block;
	background: #EAE6DD;
}

#sideMenu .bd li a:hover {
	background: #D5CFBF;
}

/* 列表页 */
#mainBox {
	margin-bottom: 10px;
	padding: 10px;
	background: #fff;
	overflow: hidden;
}

#mainBox .mHd {
	border-bottom: 2px solid #09c;
	height: 30px;
	line-height: 30px;
}

#mainBox .mHd h3 {
	display: initial;
	*display: inline;
	zoom: 1;
	padding: 0 15px;
	background: #09c;
	color: #fff;
}

#mainBox .mHd h3 span {
	color: #fff;
	font-size: 14px;
	font-weight: bold;
}

#mainBox .path {
	float: right;
}

/* 位置导航 */
.path {
	height: 30px;
	line-height: 30px;
	padding-left: 10px;
}

.path a, .path span {
	margin: 0 5px;
}

/* 文章列表 */
.newsList {
	padding: 10px;
	text-align: left;
}

.newsList li {
	background: url("../images/share/point.png") no-repeat 2px 14px;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
}

.newsList li a {
	display: inline-block;
	*display: inline;
	zoom: 1;
	font-size: 14px;
}

.newsList li .date {
	float: right;
	color: #999;
}

.newsList li.split {
	margin-bottom: 10px;
	padding-bottom: 10px;
	border-bottom: 1px dotted #ddd;
	height: 0px;
	line-height: 0px;
	overflow: hidden;
}

/* 通用带图片的信息列表_普通式 */
.picList {
	padding: 10px;
	text-align: left;
}

.picList li {
	margin: 0 5px;
	height: 190px;
}

h3.break {
	font-size: 16px;
	display: block;
	white-space: nowrap;
	word-wrap: normal;
	overflow: hidden;
	text-overflow: ellipsis;
}

h3.break>a {
	text-decoration: none;
}
/* .Pagination{
	text-align: center;
	margin:0 auto;
} */
</style>
</head>
<script type="text/javascript">

	$(function() {
		// 对分页导航条显示进行初始化
		initPagination();
		
		// 给加入购物车绑定单击事件
		$("button.addItemClass").click(function(){
			// 获取属性的值（自定义的属性）
			var id = $(this).attr("itemId");
			// 发起请求给CartServlet程序，添加商品
			location.href = "car/add?id="+id;
		});
		
	});

	// 导航条初始化
	function initPagination() {
	
		// 声明变量存储总记录数
		var totalRecord = ${requestScope['PAGE-INFO'].total};
		
		// 声明变量存储分页导航条显示时的属性设置
		var paginationProperties = {
			num_edge_entries:3,			//边缘页数
			num_display_entries:10,		//主体页数
			callback:pageselectCallback,	//回调函数
			items_per_page:${requestScope['PAGE-INFO'].pageSize},	//每页显示数据数量，就是pageSize
			current_page:${requestScope['PAGE-INFO'].pageNum - 1},//当前页页码
			prev_text:"上一页",			//上一页文本
			next_text:"下一页"			//下一页文本
		};
	
		// 显示分页导航条
		$("#Pagination").pagination(totalRecord, paginationProperties);
	}

	// 在每一次点击“上一页”、“下一页”、“页码”时执行这个函数跳转页面
	function pageselectCallback(pageIndex, jq) {
	
		// pageIndex从0开始，pageNum从1开始
		var pageNum = pageIndex + 1;
	
		window.location.href = "goods/goods-get?pageNum="+pageNum+"&keyword=${param.keyword}";
	
		return false;
	}  
		

</script>
<body>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="index.html" style="font-size: 32px;">校园小商店平台</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse"
						style="float: right;">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="user/login.html">登录</a></li>
							<li><a href="user/to/reg.html">注册</a></li>
							<li><a href="car/page.html">购物车</a></li>
							<li><a>|</a></li>
							<li><a href="login-page.html">管理员登录</a></li>
							<li><a></a></li>
							
							<li style="padding-top: 8px;">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default btn-success dropdown-toggle"
										data-toggle="dropdown">
										<i class="glyphicon glyphicon-user"></i>
										${sessionScope['LOGIN-ADMIN'].userName } <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a href="user/logout">
											<i class="glyphicon glyphicon-off"></i> 退出系统</a>
										</li>
									</ul>
								</div>
							</li>
							
						</ul>
					</div>
				</div>
			</nav>

		</div>
	</div>


	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active"
				onclick="#"
				style="cursor: pointer;">
				<img src="img/carousel-1.jpg" alt="First slide">
			</div>
			<div class="item" onclick="#"
				style="cursor: pointer;">
				<img src="img/carousel-2.jpg" alt="Second slide">
			</div>
			<div class="item" onclick="#"
				style="cursor: pointer;">
				<img src="img/carousel-3.jpg" alt="Third slide">
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>



	<div class="container marketing">

		<!-- /.row -->

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="box ui-draggable" id="mainBox">
						<div class="mHd" style="">
							<div class="path">
								<a href="projects.html"> </a>
							</div>
							<h3>
								All <small style="color: #FFF;">所有商品</small>
							</h3>
						</div>
						<div class="mBd" style="padding-top: 10px;">
							<div class="row">
								<c:if test="${empty requestScope['PAGE-INFO'].list}">
									<tr>
										<td style="text-align: center;" colspan="6">抱歉！没有符合您要求的查询结果！</td>
									</tr>
								</c:if>
								<c:if test="${!empty requestScope['PAGE-INFO'].list}">
									<c:forEach items="${requestScope['PAGE-INFO'].list}"
										var="goods" varStatus="myStatus">
										<div class="col-md-3">
											<div class="thumbnail">
												<img alt="300x200" src="${goods.goodsImgPath } " />
												<div class="caption">
													<h3 class="break">
														<a href="project.html">${goods.goodsName }</a>
													</h3>
													<table width="230">
														<tr align="center">
															<td>
																<div style="float: left;">
																	<i title="价格"></i>
																	<div class="myfount">￥${goods.goodsPrice }</div>
																</div>
															</td>
															<td>
																<div style="float: right;">
																	<button itemId="${ goods.id }" class="addItemClass">加入购物车</button>
																</div>
															</td>
														</tr>
														<tr align="center">
															<td>
																<div style="float: left;">
																	<i title="库存"></i>
																	<div class="myfount">库存：${goods.goodsStock }</div>
																</div>
															</td>
															<td>
																<div style="float: right;">
																	<i title="销量"></i>
																	<div class="myfount">销量：${goods.goodsSales }</div>
																</div>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
						<div id="Pagination" class="pagination"
							style="text-align: center;">
							<!-- 这里显示分页 -->
						</div>



					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>