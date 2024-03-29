<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>餐厅经理流水管理</title>

	<link rel="stylesheet" type="text/css" href="/restaurant/css/main.css">
    <link rel="stylesheet" type="text/css" href="/restaurant/css/bootstrap.min.css" />

    <script type="text/javascript" src="/restaurant/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/restaurant/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="background">
        
        <div class="header" >
            <ul class="nav nav-pills">
                <li role="presentation" class="list"><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/check">考勤管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list active"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manage/table">餐桌管理</a></li>
                <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>
            </ul>
        </div>
    
        <!-- 流水管理表格  -->
        
        <div class="sheet">
            <div class="title">
                <h1>流水记录</h1>
                <c:if test="${requestScope.detail == '-1' }">
                <a href="?detail=今日"><button type="button" class="btn btn-default">今日</button></a>
                <a href="?detail=最近一周"><button type="button" class="btn btn-default">最近一周</button></a>
                <a href="?detail=最近一月"><button type="button" class="btn btn-default">最近一月</button></a>
                <a href="?detail=-1"><button type="button" class="btn btn-info" >全部</button></a>
                </c:if>
                <c:if test="${requestScope.detail == '今日' }">
                <a href="?detail=今日"><button type="button" class="btn btn-info">今日</button></a>
                <a href="?detail=最近一周"><button type="button" class="btn btn-default">最近一周</button></a>
                <a href="?detail=最近一月"><button type="button" class="btn btn-default">最近一月</button></a>
                <a href="?detail=-1"><button type="button" class="btn btn-default" >全部</button></a>
                </c:if>
                <c:if test="${requestScope.detail == '最近一周' }">
                <a href="?detail=今日"><button type="button" class="btn btn-default">今日</button></a>
                <a href="?detail=最近一周"><button type="button" class="btn btn-info">最近一周</button></a>
                <a href="?detail=最近一月"><button type="button" class="btn btn-default">最近一月</button></a>
                <a href="?detail=-1"><button type="button" class="btn btn-default" >全部</button></a>
                </c:if>
                <c:if test="${requestScope.detail == '最近一月' }">
                <a href="?detail=今日"><button type="button" class="btn btn-default">今日</button></a>
                <a href="?detail=最近一周"><button type="button" class="btn btn-default">最近一周</button></a>
                <a href="?detail=最近一月"><button type="button" class="btn btn-info">最近一月</button></a>
                <a href="?detail=-1"><button type="button" class="btn btn-default" >全部</button></a>
                </c:if>
            </div>
            
            <div style="text-align:right">
            
           		<h4>总收入：${requestScope.streamTotal}￥</h4>
           
           	</div>
           	<br>
            
            <div>
            <table class="table table-striped" style="text-align: center;">
                <thead>
                    <tr>
                        <th style="text-align: center;">订单ID</th>
                        <th style="text-align: center;">服务员编号</th>
                        <th style="text-align: center;">桌号</th>
                        <th style="text-align: center;">日期/时间</th>
                        <th style="text-align: center;">花销</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.orderStream}" var="order">
						<tr>
							<td>${order.orderId}</td>
							<td>${order.personId}</td>
							<td>${order.tableId}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.orderTime}" /></td>
							<td>${order.total}￥</td>
						</tr>
					</c:forEach>
                </tbody>
            </table>
            <!-- 翻页 -->
             <div align="center">
				<ul class="pagination">

					<li><a href="?start=0&detail=${requestScope.detail}">首 页</a></li>
					<li><a href="?start=${requestScope.pre}&detail=${requestScope.detail}">上一页</a></li>
					<li><a>${requestScope.count}/${requestScope.total}</a></li>
					<li><a href="?start=${requestScope.next}&detail=${requestScope.detail}">下一页</a></li>
					<li><a href="?start=${requestScope.last}&detail=${requestScope.detail}">末 页</a></li>
				</ul>
              </div>
              
            </div>
        </div>
        
    
        <div class="footer" >
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
});</script>

</html>