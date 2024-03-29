<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script type="text/javascript">

	$(function(){
		// 对分页导航条显示进行初始化
		initPagination();
		
		// 全选/全不选功能
		$("#summaryBox").click(function() {
			// this代表当前多选框对象（DOM对象）
			$(".itemBox").prop("checked",this.checked);
		});
		
		//批量删除绑定函数
		$("#batchRemoveBtn").click(function() {
			
			var adminIds = new Array();
			var adminNames = new Array();
			
			$(".itemBox:checked").each(function(){
				var adminId = $(this).attr("adminId");
				
				// 当前checkbox对象 包含checkbox的td 当前td的下一个兄弟元素，也就是下一个td 下一个td的标签内部的文本
				var loginName = $(this).parent("td").next().text();
	
				adminIds.push(adminId);
				adminNames.push(loginName);
			});
			
			if (adminIds.length == 0) {
				alert("请勾选您要删除的记录！");
				return;
			}
			
			var confirmResult = confirm("您确定要删除【" + adminNames + "】的信息吗？");
			
			// 如果用户点击了取消，那么让函数停止执行
			if (!confirmResult) {
				return;
			}
			
			//执行删除
			doRemove(adminIds)
		});
		
		//单个删除绑定函数
		$(".uniqueRemoveBtn").click(function(){
			var adminId = $(this).attr("adminId");
			
			var loginName = $(this).parents("tr").children("td:eq(2)").text();
			
			var confirmResult = confirm("您真的要删除【"+loginName+"】这条记录吗？");
			
			if(!confirmResult) {
				return ;
			}
			
			var adminIds = new Array();
			
			adminIds.push(adminId);
			
			doRemove(adminIds)
		});
		
		
	});

	//导航条初始化
	function initPagination() {
	
		// 声明变量存储总记录数
		var totalRecord = ${requestScope['PAGE-INFO'].total};
		
		// 声明变量存储分页导航条显示时的属性设置
		var paginationProperties = {
			num_edge_entries:3,			//边缘页数
			num_display_entries:5,		//主体页数
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
	
		window.location.href = "goods/query/for/search.html?pageNum="+pageNum+"&keyword=${param.keyword}";
	
		return false;
	}  
	
	//删除指定id的用户   传入id json格式	
	function doRemove(adminIds){
		
		var requestBody = JSON.stringify(adminIds);
		
		$.ajax({
			"url" : "goods/batch/remove.json",
			"type" : "post",
			"contentType" : "application/json;charset=UTF-8",
			"data" : requestBody, 
			"dataType" : "json", 
			"success" : function(response) {
				//console.log(response);

				var result = response.result;

				if (result == "SUCCESS") {//${requestScope['PAGE-INFO'].pageNum}当前页
					window.location.href = "goods/query/for/search.html?pageNum=${requestScope['PAGE-INFO'].pageNum}&keyword=${param.keyword}";
				}

				if (result == "FAILED") {
					alert(response.message);
					return;
				}

			},
			"error" : function(response) {
				alert(response.message);
				return;
			}
		});
	}


</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form action="goods/query/for/search" class="form-inline"
							role="form" style="float: left;" method="post">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input name="keyword" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<a href="goods/to/add/page" class="btn btn-primary"
							style="float: right;"><i class="glyphicon glyphicon-plus"></i>
							新增</a> <br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryBox" type="checkbox"></th>
										<th>商品名称</th>
										<th>商品价格</th>
										<th>销量</th>
										<th>库存</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty requestScope['PAGE-INFO'].list }">
										<tr>
											<td style="text-align: center;" colspan="6">抱歉！没有符合您要求的查询结果！</td>
										</tr>
									</c:if>

									<c:if test="${!empty requestScope['PAGE-INFO'].list }">
										<c:forEach items="${requestScope['PAGE-INFO'].list }"
											var="goods" varStatus="myStatus">
											<tr>
												<td>${myStatus.count }</td>
												<td><input adminId="${goods.id }" class="itemBox"
													type="checkbox"></td>
												<td>${goods.goodsName }</td>
												<td>${goods.goodsPrice }</td>
												<td>${goods.goodsSales }</td>
												<td>${goods.goodsStock }</td>
												<td>
													<button type="button" class="btn btn-success btn-xs">
														<i class=" glyphicon glyphicon-check"></i>
													</button> 
													<a href="goods/to/edit/page?goodsId=${goods.id }&pageNum=${requestScope['PAGE-INFO'].pageNum}"
														class="btn btn-primary btn-xs">
														<i class=" glyphicon glyphicon-pencil"></i>
													</a>
													<button adminId="${goods.id }" type="button"
														class="btn btn-danger btn-xs uniqueRemoveBtn">
														<i class=" glyphicon glyphicon-remove"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="7" align="center">
											<div id="Pagination" class="pagination">
												<!-- 这里显示分页 -->
											</div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
