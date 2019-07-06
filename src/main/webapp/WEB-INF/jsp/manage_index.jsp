<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>餐厅经理主页</title>
    
    <link rel="stylesheet" href="/restaurant/css/main.css">
	<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">
    <script src="/restaurant/js/jquery-3.4.1.min.js"></script>
    <script src="/restaurant/js/bootstrap.min.js"></script>
</head>

<body>
    
    <div class="background" >
        <!-- 导航栏 -->
        <div class="header" >
            <ul class="nav nav-pills">、
                <li role="presentation" class="list active"><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="#">考勤管理</a></li>
                <li role="presentation" class="list"><a href="#">预定管理</a></li>
                <li role="presentation" class="list"><a href="#">菜品管理</a></li>
                <li role="presentation" class="list"><a href="#">流水管理</a></li>
            </ul>
        </div>
        <!-- 员工表格  -->
        <div class="sheet">
            <div class="title">
                <h1>服务员名单</h1>
                <button id = "addW_btn" type="button" class="btn btn-default">添加服务员
                </button>
            </div>
            <div>
            <table class="table table-striped" style="text-align: center;">
                <thead>
                    <tr>
                        <th style="text-align: center;">服务员ID</th>
                        <th style="text-align: center;">姓名</th>
                        <th style="text-align: center;">性别</th>
                        <th style="text-align: center;">电话</th>
                        <th style="text-align: center;">编辑/删除</th>
                    </tr>
                </thead>
                
                <tbody>
                
                <c:forEach items="${requestScope.personStream}" var="person">
					<tr>
						<td>${person.id}</td>
						<td>${person.personName}</td>
						<td>${person.sex}</td>
						<td>${person.personTele}</td>
						<td><button type="button" class="btn btn-default"><a>edit</a></button>
                            <button type="button" class="btn btn-default"><a>delete</a></button></td>
		
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
        
        <!--页尾-->
        <div class="footer" >
            <p align="center">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
        </div>
        
    </div>
</body>

<script type="text/javascript">
	$("#addW_btn").click(function(){
		window.location.href = "/restaurant/addWaiter";
	})

</script>
</html>
