<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>餐桌预约管理</title>

<link rel="stylesheet" href="/restaurant/css/main.css">
<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">

<script src="/restaurant/js/jquery-3.4.1.min.js"></script>
<script src="/restaurant/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="background">
		<!-- 导航栏 -->
		<div class="header">
			<ul class="nav nav-pills">
                <li role="presentation" class="list"><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/check">考勤管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list active"><a href="/restaurant/manage/table">餐桌管理</a></li>
                <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>
			</ul>
		</div>
		<div class="modal fade" id="addReserve" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加餐桌</h4>
					</div>
					<div class="modal-body" style="height: 60px">
						<div class="col-lg-2 ">
							预约时间
						</div>
						<div class="col-lg-5 ">
							<input type="datetime-local" class="form-control" id="new_time"
								>
						</div>
						<div class="col-lg-2 ">
							预留电话
						</div>
						<div class="col-lg-3">
							<input type="text" class="form-control" id="new_tele"
								placeholder="电话">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" onclick="addReserve()">添加</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="sheet">
			<div class="title">
				<h1>'${requestScope.tableId}'号餐桌预约列表</h1><a href="/restaurant/manage/table">返回餐桌列表</a>
				<button id="addW_btn" type="button" class="btn btn-default"
					data-toggle="modal" data-target="#addReserve">新的预约</button>
			</div>
			<div>
				<table class="table table-striped" style="text-align: center;">
					<thead>
						<tr>
							<th style="text-align: center;">预约时间</th>
							<th style="text-align: center;">预约手机号</th>
							<th style="text-align: center;">取消预约</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${requestScope.reserves}" var="reserve">
							<tr>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${reserve.reserveTime}" /></td>
								<td>${reserve.reserveTele}</td>
								<td> 
									<button type="button" class="btn btn-default"
										onclick="deleteReserve(${reserve.reserveId})">取消预约</button>
								</td>

							</tr>
						</c:forEach>


					</tbody>
				</table>
				<!-- 翻页 -->
			</div>
		</div>

		<!--页尾-->
		<div class="footer">
			<p align="center">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
		</div>

	</div>
</body>


<script type="text/javascript">

$("#waiter_exit_btn").click(function(){
	
	$.ajax({
		url:"/restaurant/waiter/exit",
		data:{},
		type:"post",
		success:function(msg){
			window.location.href = "/restaurant/register";
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
		}
	
	});
});

function addReserve(){
	
	var time = $("#new_time").val().replace(/T/g, " ");
	console.log(time)
	var tele = $("#new_tele").val();
	var tableId = '${requestScope.tableId}';
	if(time==null||time==''){
		alert("您没有输入时间！");
		return;
	}
	if(tele==null||tele==''){
		alert("您没有输入手机号！");
		return;
	}
	$.ajax({
		url:"/restaurant/manage/addReserve",
		data:{time:time,tele:tele,tableId:tableId},
		type:"post",
		success:function(msg){
			if (msg == 1) {
				alert("添加预约成功！"); 
				location.reload();
			} else {
				alert("操作失败，请检查时间是否冲突！"); 
			} 
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	
	});
}

function deleteReserve(id){
	$.ajax({
		url:"/restaurant/manage/deleteReserve",
		data:{id:id},
		type:"post",
		success:function(msg){
			if (msg == 1) {
				alert("取消成功！"); 
				location.reload();
			} else {
				alert("取消失败！"); 
			} 
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	
	});
}
</script>
</html>
