<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>餐厅经理主页</title>

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
						<th>Waiter-ID</th>
						<th>Time</th>
						<th>Date</th>
						<th>Clork or Not</th>
						<th>Punch</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>001</td>
						<td>8:10:11</td>
						<td>2019-07-03</td>
						<td>Yes</td>
						<td><button type="button" class="btn btn-default">
								<a href="#">P</a>
							</button></td>
					</tr>
					<tr>
						<td>001</td>
						<td>8:10:12</td>
						<td>2019-07-03</td>
						<td>Yes</td>
						<td><button type="button" class="btn btn-default">
								<a href="#">P</a>
							</button></td>
					</tr>
					<tr>
						<td>001</td>
						<td>8:10:13</td>
						<td>2019-07-03</td>
						<td>No</td>
						<td><button type="button" class="btn btn-default">
								<a href="#">P</a>
							</button></td>
					</tr>
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
=======
    
    <link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
    
    <script src="js/bootstrap.min.js"></script>
	
	<style type="text/css">
	table
       {
		   border-collapse:collapse;
	       width:60%;
       }
		
	    th{
			height:25px;
		}
		
		td{
			
			height:25px;
		}

       table,th, td
       {
           border: 1px solid black;
		   text-align:center;
		   font-family: sans-serif;
		   font-size: 16px;
       }
		
		.table1{
			position:absolute;
			margin-left: 20%;
			margin-top: 8%;
		}
	</style>	
</head>

<body>
    
    <div class="background" >
        
        <div class="header" >
            <ul class="nav nav-pills">、
                <li role="presentation" class="list"><a href="manage_index.html">服务员管理</a></li>
                <li role="presentation" class="list active"><a href="manage_check.html">考勤管理</a></li>
                <li role="presentation" class="list"><a href="manage_advance.html">预定管理</a></li>
                <li role="presentation" class="list"><a href="manage_dish.html">菜品管理</a></li>
                <li role="presentation" class="list"><a href="manage_order.html">流水管理</a></li>
            </ul>
        </div>
    <div>
			
			<table class="table1">
				<tr>
					<th>服务员编号</th>
					<th>服务员姓名</th>
					<th>是否出勤</th>
					<th>打卡时间</th>	
				</tr>
				
				<tr>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				</tr>
					
				<tr>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				</tr>
			</table>
	    </div>
	</p>
	<p>
    
        <div class="footer" >
            <p align="center" style="margin: 0">wychen1234567890</p> 
        </div>
    </div>
</body>
</html>
