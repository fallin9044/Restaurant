<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>餐厅经理考勤管理</title>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/bootstrap.min.css">

<script src="js/bootstrap.min.js"></script>
</head>

<body>

	<div class="background">
		<!--导航栏-->
		<div class="header">
			<ul class="nav nav-pills">
				<li role="presentation" class="list"><a
					href="manage_index.html">服务员管理</a></li>
				<li role="presentation" class="list active"><a
					href="manage_check.html">考勤管理</a></li>
				<li role="presentation" class="list"><a
					href="manage_advance.html">预定管理</a></li>
				<li role="presentation" class="list"><a href="manage_dish.html">菜品管理</a></li>
				<li role="presentation" class="list"><a
					href="manage_order.html">流水管理</a></li>
			</ul>
		</div>



		<!-- 每天的签到表格  -->
		<div class="sheet">
			<table class="table table-striped">
			<caption><h3 align="center">签到管理</h3></caption>
				<thead>
					<tr>
						<th>服务员编号</th>
						<th>服务员姓名</th>
						<th>打卡日期</th>
						<th>打卡时间</th>
						<th>是否打卡</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.persons}" var="person">
					<tr>
					    <td>${person.person_id}</td>
						<td>${person.person_name}</td>
						
						<td>${}</td>
						<td>Yes</td>
						<td></td>
					</tr>
				    </c:forEach>
						
	
				</tbody>
			</table>
			<!-- 翻页 -->
			<div align="right">
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>
		</div>



		<div class="footer">
			<p align="center" style="margin: 0">wychen1234567890</p>
		</div>

	</div>
</body>
</html>

