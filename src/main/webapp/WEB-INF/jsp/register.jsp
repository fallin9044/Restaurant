<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/restaurant/js/jquery-3.4.1.min.js"></script>
<title>Register Page</title>
</head>
<body>

	username: <input type="text" name="name" id="name"/>
	<br><br>
	password: <input type="password" name="password" id="password"/>
	<br><br>
	<input type="submit" id="user_logging_btn" value="Submit"/>

</body>

<script type="text/javascript">
$(function(){ 
	$("#user_logging_btn").click(function(){
	
		var name = $("#name").val();
		var password = $("#password").val();
		if(name==null||name==''){
			alert("您没有输入账号名！");
			return;
		}
		if(password==null||password==''){
			alert("您没有输入密码！");
			return;
		}
		
		$.ajax({
			url:"/restaurant/logging",
			data:{name:name,password:password},
			type:"post",
			success:function(msg){
				console.log(msg);
				if (msg.authority != -1) {
					if(msg.authority == 2){
						window.location.href = "/restaurant/waiter/tableStatus";
					}else{
						window.location.href = "/restaurant/manager";
					}
				} else {
					alert("账号密码错误！"); 
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		
		});
	});
});</script>
</html>