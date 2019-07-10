<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单管理</title>

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
                <li role="presentation" class="list active"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manage/table">餐桌管理</a></li>
 
                 <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>           </ul>
        </div>
    
        <!-- 菜单管理表格  -->
        
        <div class="sheet">
            <div class="title">
                <h1>所有菜品</h1>
                <a href="/restaurant/addDishPage"><button type="button" class="btn btn-default">添加新菜</button></a>
            </div>
            
            <div>
            <table class="table table-striped" style="text-align: center;">
                <thead>
                    <tr>
                        <th style="text-align: center;">菜品ID</th>
                        <th style="text-align: center;">菜名</th>
                        <th style="text-align: center;">价格</th>
                        <th style="text-align: center;">是否推荐</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.dishStream}" var="dish">
						<tr>
							<td>${dish.dishId}</td>
							<td>${dish.dishName}</td>
							<td>${dish.dishPrice}</td>
							<td>${dish.isrecommend}</td>
							<td><button type="button" class="btn btn-default" onclick="edit('${dish.dishName}')">
							edit</button>
                            <button  type="button" class="btn btn-default" onclick="delete1(${dish.dishId})">
                            delete</button>
		                    <button  type="button" class="btn btn-default" onclick="changeRecommend(${dish.dishId})">
                            recommend</button>
                            </td>
						</tr>
					</c:forEach>
                </tbody>
            </table>
            <!-- 翻页 -->
             <div align="center">
				<ul class="pagination">
					<li><a href="?start=0&detail=${requestScope.detail}">首 页</a></li>
					<li><a href="?start=${requestScope.pre}&detail=${requestScope.detail}">上一页</a></li>
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
});
     
	function edit(dishName){		
        var name = dishName;
		window.location.href = "/restaurant/editDishPage?dishName="+name;
	};
	
	function delete1(dishId){
		$.ajax({
			url:"/restaurant/deleteDish",
			data:{dishId:dishId},
			type:"post",
			success:function(msg){	
				alert("删除菜品成功!");
				window.location.href = "/restaurant/manageDish?start=0"
			}
		});
	};
	
	function changeRecommend(dishId)
	{
		$.ajax({
			url:"/restaurant/changeRecommend",
			data:{dishId:dishId},
			type:"post",
			success:function(msg){		
				alert("修改推荐成功!");
				window.location.href = "/restaurant/manageDish?start=0"
			}
		});
	}
</script>

</html>