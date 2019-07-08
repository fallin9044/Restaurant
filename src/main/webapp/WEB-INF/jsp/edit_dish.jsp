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
                <li role="presentation" class="list "><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="#">考勤管理</a></li>
                <li role="presentation" class="list"><a href="#">预定管理</a></li>
                <li role="presentation" class="list active"><a href="#">菜品管理</a></li>
                <li role="presentation" class="list"><a href="#">流水管理</a></li>
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
                        <label for="description" class="col-sm-3 control-label">菜品简介</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="desc"
                                 value="${requestScope.dish.dishDesc}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">图片名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="picture"
                                value="${requestScope.dish.dishPicture}">
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

	$("#edit_dish_btn").click(function(){
	
		var name = $("#name").val();
		var price = $("#price").val();
		var desc = $("#desc").val();
		var picture = $("#picture").val();
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
		if(desc==""||desc==null){
			alert("请输入菜品描述！");
			return;
		}
		if(picture==""||picture==null){
			alert("请输入菜品的图片名称！");
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
		
		$.ajax({
			url:"/restaurant/editDish",
			data:{dishId:id,dishName:name,dishPrice:price,dishDesc:desc,dishPicture:picture,isrecommend:recommend},
			type:"post",
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