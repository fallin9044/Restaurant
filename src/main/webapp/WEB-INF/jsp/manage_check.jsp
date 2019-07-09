<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>餐厅经理考勤管理</title>

<link rel="stylesheet" href="/restaurant/css/main.css">
<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">

<script src="/restaurant/js/bootstrap.min.js"></script>
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
						<th>是否打卡</th>
						<th>打卡日期</th>
						<th>打卡时间</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${requestScope.persons}"  var="person" varStatus="loop">
					<tr>
					    <td>${person.id}</td>
						<td>${person.personName}</td>
						<c:if test="${ifAttending[loop.count-1] == '出勤'}"><td style="color:#64ec22">${ifAttending[loop.count-1]}</td></c:if>
						<c:if test="${ifAttending[loop.count-1] == '缺勤'}"><td>${ifAttending[loop.count-1]}</td></c:if>
						<td>${dates[loop.count-1]}</td>
						<td>${times[loop.count-1]}</td>
					</tr>
					</c:forEach>	
	
				</tbody>
			</table>
			<!-- 翻页 -->
			             <div align="center">
                <ul class="pagination">
                   <li><a href="?start=0">首页</a></li>
                   <li><a href="?start=${requestScope.pre}">上一页</a></li>
                   <li><a>${requestScope.count}/${requestScope.total}</a></li>
                   <li><a href="?start=${requestScope.next}">下一页</a></li>
                   <li><a href="?start=${requestScope.last}">末 页</a></li>
                </ul>
              </div>
		</div>



		<div class="footer">
			<p align="center" style="margin: 0">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
		</div>

	</div>
</body>
</html>

