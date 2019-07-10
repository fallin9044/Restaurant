<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/restaurant/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/restaurant/css/main.css">

<script type="text/javascript" src="/restaurant/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/restaurant/js/bootstrap.min.js"></script>
<title>服务员选桌子页面</title>
</head>
<body>

    <div class="background" >
        
        <div class="header" style="height:8%;text-align: center;font-size: 30px;">
            顾客是上帝
            <a href=":javascript:void(0)" id="waiter_exit_btn" style="float:right">登出</a>
        </div>
        <div align="center" style="margin-top: 10%">
          <h3>餐桌状态</h3>
        </div>
        <div class="mid-body" style="margin-top: 2%;">
            <!-- 每天的签到表格  -->
			<div class="sheet" style="margin-top: 1%;">
				<table class="table table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>几人桌</th>
						<th>状态</th>
						<th>操作</th>
						<th>预约时间/就餐时间</th>
						<th>预约电话</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.tablestatus}" var="table">
						
						<tr>
							<td>${table.tableId}</td>
							<td>${table.tableNum}</td>
							<c:if test="${table.tableState == '0'}">
								<td>空闲</td>
								<td onclick=getTableNum(this)><input type="submit" value="占座"/></td>
								<td>无</td>
								<td>无</td>
							</c:if>
							<c:if test="${table.tableState == '1'}">
								<c:forEach items="${requestScope.reserves}" var="reserve">
									<c:if test="${table.tableId ==reserve.tableId}">
										<td>预约</td>
										<td onclick=getTableNum(this)><input type="submit" value="就座"/></td>
										<td>${reserve.reserveTime}</td>
										<td>${reserve.reserveTele}</td>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${table.tableState == '2'}">
								<td>在吃</td>
								<td onclick=getTableNum(this)><input type="submit" value="点餐"/></td>
								<td>${table.startTime}</td>
								<td>无</td>
							</c:if>
							
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
            
        </div>
    
    
        <div class="footer" >
            <p align="center">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
        </div>

    </div>
</body>

<script type="text/javascript">
function getTableNum(element){

	var tableId = $(element.parentElement).children("td").eq(0).html();
	var tableState = $(element.parentElement).children("td").eq(2).html();
	
	if(tableState=="空闲"||tableState=="预约"){
		$.ajax({
			url:"/restaurant/waiter/takeTable",
			data:{tableState:tableState,tableId:tableId},
			type:"post",
			success:function(msg){
				console.log(msg);
				if (msg.flag == -1) {
					alert("已经占座，请刷新页面！"); 
				} else {
					alert("占座成功！"); 
				}
				window.location.href = "/restaurant/waiter/tableStatus";
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
			}
		
		});
		
	}else if(tableState=="在吃"){
		window.location.href = "/restaurant/waiter/orderDish?tableId="+tableId;
	}
	
}</script>

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