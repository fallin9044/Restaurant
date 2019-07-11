<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改</title>
    
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
                <li role="presentation" class="list active"><a href="/restaurant/managerIndex">服务员管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/check">考勤管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manageDish">菜品管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manager/orderStream">流水管理</a></li>
                <li role="presentation" class="list"><a href="/restaurant/manage/table">餐桌管理</a></li>
                <li role="presentation" id="waiter_exit_btn" class="list" style="float:right"><a href="javascript:void(0)">注销</a></li>
            </ul>
        </div>
   
    <div class="sheet">
        <div align="center" style="margin-bottom: 5%">
          <h3>服务员信息修改</h3>
        </div>
        
        <div class="col-md-10 col-md-offset-1">
            <div class="form-horizontal">
                    
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">服务员姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_name"
                                 value="${requestScope.person.personName}" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_sex"
                                 value="${requestScope.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_tele"
                                 value="${requestScope.person.personTele}">
                        </div>
                    </div>
                <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_password"
                                value="${requestScope.person.password}">
                        </div>
                    </div>
                    <!-- 提交和重置按钮 -->
                    
                     <button id="edit_waiter_btn" type="button" class="btn btn-success">提交</button>
                    
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
	$("#edit_waiter_btn").click(function(){
		
		var waiterid = ${requestScope.person.id};
		var waitername = $("#waiter_name").val();
		var sex = $("#waiter_sex").val();
		var telephone = $("#waiter_tele").val();
		var password = $("#waiter_password").val();
		
		if(waitername==null||waitername==''){
			alert("您没有输入姓名！");
			return;
		}
		if(sex==null||sex==''){
			alert("您没有输入性别！");
			return;
		}
		if(sex!='男'&&sex!='女'){
			alert("请按规范输入“男”或“女”");
			return;
		}
		if(telephone==null||telephone==''){
			alert("您没有输入电话号码！");
			return;
		}
		if(password==null||password==''){
			alert("您没有输入密码！");
			return;
		}

		
		if(waitername=='${requestScope.person.personName}'&&sex=='${requestScope.sex}'&&telephone=='${requestScope.person.personTele}'&&password=='${requestScope.person.password}'){
			alert("您未做任何修改！");
			return;
		}
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
		if(telephone.length!=11||!myreg.test(telephone)){
			alert("请输入11位有效手机号")
			return
		}
		if(!/^[a-zA-Z0-9]{8,16}$/.test(password)){
			alert("请输入8-16位字母或数字")
			return
		}
		$.ajax({
			url:"/restaurant/editSubmit",
			data:{waiterid:waiterid,waitername:waitername,sex:sex,telephone:telephone,password:password},
			type:"post",
			
			success:function(msg){
				if (msg.isrepeat == 1) {
					window.location.href = "/restaurant/managerIndex";
				} else {
					alert("该服务员已被注册！"); 
				} 
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		
		});
	});
</script>
</html>