<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改菜品信息</title>
    
    <link rel="stylesheet" href="/restaurant/css/main.css">
	<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">
    <script src="/restaurant/js/jquery-3.4.1.min.js"></script>
    <script src="/restaurant/js/bootstrap.min.js"></script>

</head>

<body>
    <!-- 顶部导航栏  -->
    <div class="background" > 
        <div class="header" >
            <ul class="nav nav-pills">、
                <li role="presentation" class="list"><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/check">考勤管理</a></li>
                <li role="presentation" class="list active"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manage/table">餐桌管理</a></li>
                <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>
            </ul>
        </div>
   
    <div class="sheet">
        <div align="center" style="margin-bottom: 5%">
          <h3>菜品信息菜品</h3>
        </div>
        <div class="col-md-10 col-md-offset-1">
            <div class="form-horizontal" >
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">菜品名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name"
                                 value="${requestScope.dish.dishName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">菜品价格</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="price"
                                  value="${requestScope.dish.dishPrice}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">菜品图片(1M以内)</label>
                        <div class="col-sm-6">
                            <input type="file" id="picture" name="file">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">是/否被推荐</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="recommend"
                                value="${requestScope.recommend}">
                        </div>
                    </div>
                    <!-- 提交和重置按钮 -->
                    <div class="form-group">
                     <button id="edit_dish_btn" class="btn btn-success">修改</button>
                    </div>
                </div>
            </div>
        
    </div>
  
     <!-- 页尾   -->
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

	$("#edit_dish_btn").click(function(){
	
		var name = $("#name").val();
		var price = $("#price").val();
		var picture = $("#picture")[0].files[0];
		var pictureName='${requestScope.dish.dishPicture}';
		var recommend = $("#recommend").val();
		var id = "${requestScope.dish.dishId}"
		
		
		if(name==null||name==''){
			alert("请输入有效的菜品名称！");
			return;
		}
		if(price==null||price==''||!/^[0-9]+$/.test(price)){
			alert("请输入有效的价格！");
			return;
		}

		if(recommend!="是"&&recommend!="否"){
			alert("请输入菜品是/否被推荐！");
			return;
		}else{ 
			if(recommend=='是')
				recommend = 1;
			else
				recommend = 0;
		}
		var formdata = new FormData();
		formdata.append("dishName", name);
		formdata.append("dishPrice",price);
		formdata.append("dishDesc",'');
		formdata.append("dishPicture", pictureName);
		formdata.append("isrecommend", recommend);
		formdata.append("picture",picture);
		formdata.append("dishId",id);
		
		$.ajax({
			url:"/restaurant/editDish",
			data:formdata,
			type:"post",
			contentType: false,
			processData: false,
			success:function(msg){
				console.log(msg);
				if (msg.isrepeat == 0) {
					alert("修改菜品成功！"); 
					window.location.href = "/restaurant/manageDish?start=0";
				} else {
					alert("菜品名称已经存在！请重新输入！"); 
				} 
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		
		});
	});
</script>
</html>