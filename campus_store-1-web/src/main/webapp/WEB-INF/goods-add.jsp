<%@page import="com.sun.tools.internal.ws.wsdl.document.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.drt.util.*"%>
<!DOCTYPE html>
<html lang="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/to/main/page.html">首页</a></li>
					<li><a href="admin/query/for/search.html">数据111111111列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						商品信息
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form action="goods/save" method="post" role="form">
							<div class="form-group">
								<label for="exampleInputPassword1">商品名称</label> <input
									type="text" name="goodsName" class="form-control"
									id="exampleInputPassword1" placeholder="请输入商品名称" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">商品价格</label> <input
									type="text" name="goodsPrice" class="form-control"
									id="exampleInputPassword1" placeholder="请输入商品价格" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">库存</label> <input type="text"
									name="goodsStock" class="form-control" id="exampleInputEmail1"
									placeholder="请输入库存数量" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">商品的图片路径</label> <input type="text"
									name="goodsImgPath" class="form-control" id="exampleInputEmail1"
									placeholder="请输入商品的图片路径" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">商品分类</label> 
								<br>
								<select name="goodsCategory" class="form-control">
									<option value="<%=GoodsCategory.FOOD %>">食品类</option>
									<option value="<%=GoodsCategory.DRINK %>">饮品类</option>
									<option value="<%=GoodsCategory.CIGARETTES %>">香烟</option>
									<option value="<%=GoodsCategory.OTHER %>">其他</option>
								</select>
							</div>
							<button type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 新增
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="bs-callout bs-callout-info">
						<h4>测试标题1</h4>
						<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
					</div>
					<div class="bs-callout bs-callout-info">
						<h4>测试标题2</h4>
						<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
