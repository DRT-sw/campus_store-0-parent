<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.drt.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					<li><a href="goods/query/for/search.html">商品列表</a></li>
					<li class="active">更新</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">商品信息</div>
					<div class="panel-body">
						<form:form action="goods/update" method="post" modelAttribute="goods">
						<input type="hidden" name="pageNum" value="${param.pageNum }" />
						<input type="hidden" name="goodsID" value="${goods.getId() }" />
							<div class="form-group">
								<label for="exampleInputPassword1">商品名称</label> 
								<form:input path="goodsName" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">商品价格</label> 
								<form:input path="goodsPrice" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">库存</label>
								<form:input path="goodsStock" cssClass="form-control" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">商品的图片路径</label>
								<form:input path="goodsImgPath" cssClass="form-control" />
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
								<i class="glyphicon glyphicon-edit"></i> 更新
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>