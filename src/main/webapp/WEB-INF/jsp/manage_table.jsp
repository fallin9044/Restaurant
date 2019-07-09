<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>餐厅餐桌管理</title>

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
                <li role="presentation" class="list "><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/check">考勤管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list active"><a href="/restaurant/manage/table">餐桌管理</a></li>
                <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>
			</ul>
		</div>
		<div class="modal fade" id="addTable" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加餐桌</h4>
					</div>
					<div class="modal-body" style="height: 60px">

						<div class="col-lg-4 ">
							<input type="text" class="form-control" id="new_id"
								placeholder="餐桌编号">
						</div>
						<div class="col-lg-4">
							<input type="text" class="form-control" id="new_number"
								placeholder="餐桌容量">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" onclick="addTable()">添加</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="sheet">
			<div class="title">
				<h1>餐桌列表</h1>
				<button id="addW_btn" type="button" class="btn btn-default"
					data-toggle="modal" data-target="#addTable">添加餐桌</button>
			</div>
			<div style="float:right">
			<p>按时间查询可预约餐桌</p>
			<input type="datetime-local" id="searchTime"><button class="btn btn-default" onclick="searchTable()" style="height:30px">搜索</button>
			</div>
			<div>
				<table class="table table-striped" style="text-align: center;">
					<thead>
						<tr>
							<th style="text-align: center;">餐桌编号</th>
							<th style="text-align: center;">餐桌容量</th>
							<th style="text-align: center;">就餐状态</th>
							<th style="text-align: center;">删除</th>
							<th style="text-align: center;">预约管理</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${requestScope.tables}" var="table">
							<tr>
								<td>${table.tableId}</td>
								<td>${table.tableNum}人</td>
								<c:if test="${table.tableState != '2'}">
								<td>非就餐</td>
								</c:if>
								<c:if test="${table.tableState == '2'}">
								<td style="color:#fe1111">就餐中</td>
								</c:if>
								<td> 
									<button type="button" class="btn btn-default"
										onclick="deleteTable(${table.tableId})">删除</button>
								</td>
								<td> 
									<button type="button" class="btn btn-default"
										onclick="reserveManage(${table.tableId})">预约管理</button>
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

$(function(){
	var time="${requestScope.datetime}";
	if(time != ''){
		$("#searchTime").val(time);
	}
	
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
})
function addTable(){
	
	var id = $("#new_id").val();
	var number = $("#new_number").val();
	
	if(id==null||id==''){
		alert("您没有输入编号！");
		return;
	}
	if(number==null||number==''){
		alert("您没有输入容量！");
		return;
	}
	$.ajax({
		url:"/restaurant/manage/addTable",
		data:{id:id,number:number},
		type:"post",
		success:function(msg){
			console.log(msg.isrepeat);
			if (msg == 1) {
				alert("添加成功！"); 
				location.reload();
			} else {
				alert("该编号已存在或者添加失败！"); 
			} 
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	
	});
}
function reserveManage(id){
	location.href="/restaurant/manage/reserveList?tableId="+id;
}
function deleteTable(id){
	$.ajax({
		url:"/restaurant/manage/deleteTable",
		data:{id:id},
		type:"post",
		success:function(msg){
			console.log(msg.isrepeat);
			if (msg == 1) {
				alert("删除成功！"); 
				location.reload();
			} else {
				alert("删除失败！"); 
			} 
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	
	});
}

function searchTable(){
	var time = $("#searchTime").val().replace(/T/g, " ");
	if(time==null||time==''){
		location.href="/restaurant/manage/table";
		return;
	}
	else location.href="/restaurant/manage/searchresult?date="+time;
	
}
</script>
</html>
