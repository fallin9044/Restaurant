<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Waiter_Welcome_LogIn</title>
	
	<link rel="stylesheet" href="/restaurant/css/main.css">
	<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">
	
	<script src="/restaurant/js/jquery-3.4.1.min.js"></script>
	<script src="/restaurant/js/bootstrap.min.js"></script>
    <script src="/restaurant/js/prefixfree.min.js"></script>
</head>
<body>
<div style="height:100%;width: 100%;position:absolute">
		<img id = 'photo' class='photo'  src="/restaurant/images/welcome1.jpg" alt="" />
		<img id = 'photo' class='photo'  src="/restaurant/images/welcome2.jpg" alt="" />
        <img id = 'photo' class='photo'  src="/restaurant/images/welcome3.jpg" alt="" />
        <img id = 'photo' class='photo'  src="/restaurant/images/welcome4.jpeg" alt="" />
	</div>
    
    
    <div class="col-sm-offset-4 col-sm-4 col-md-offset-4 col-md-4" style="margin-top: 6%;">
        
            
                <div class="col-md-offset-1 col-md-10">
                    <div class="form-horizontal">
                        <span class="heading">
                            <img src="/restaurant/images/w-logo.jpg" style="width:70%;margin-top: 2%;">
                        </span>
                        <div class="form-group">
                            <input type="text" id="tele" class="form-control"  placeholder="账号">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" id="password" class="form-control" placeholder="密码">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div >
                            <button id="user_logging_btn" class="btn btn-default" style="background-color:#E70012">登录</button>
                        </div>
                    </div>
                </div>
            
        
    </div>

</body>

<script type="text/javascript">
	$("#user_logging_btn").click(function(){
	
		var tele = $("#tele").val();
		var password = $("#password").val();
		if(tele==null||tele==''){
			alert("您没有输入账号名！");
			return;
		}
		if(password==null||password==''){
			alert("您没有输入密码！");
			return;
		}
		
		$.ajax({
			url:"/restaurant/logging",
			data:{tele:tele,password:password},
			type:"post",
			success:function(msg){
				console.log(msg);
				if(msg.authority == 2){
					window.location.href = "/restaurant/waiter/tableStatus";
				}else if(msg.authority == 1){
					window.location.href = "/restaurant/managerIndex";
				}else{
					alert("账号密码错误！"); 
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		
		});
	});
</script>
</html>