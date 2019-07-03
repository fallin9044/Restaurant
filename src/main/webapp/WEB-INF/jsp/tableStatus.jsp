<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/restaurant/css/bootstrap.min.css" />
<script type="text/javascript" src="/restaurant/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/restaurant/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<ul class="nav nav-pills">
  <li role="presentation" class="active"><a href="#">Home</a></li>
  <li role="presentation"><a href="#">Profile</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
  <input type="submit" id="waiter_exit_btn" value="Exit"/>
</ul>
	<div class="container">
		<div class="row">
		<div class="col-md-8 col-lg-8 col-md-offset-4 col-lg-offset-4">
			<table class="table table-striped">
				<caption>餐桌状态</caption>
				<thead>
					<tr>
						<th>编号</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.tablestatus}" var="table">
						<tr>
							<td>${table.tableNum}</td>
							<c:if test="${table.tableState == '0'}">
								<td>空闲</td>
							</c:if>
							<td>${table.tableNum}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
	</div>
</body>

<script type="text/javascript">
$(function(){ 
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
});</script>
</html>