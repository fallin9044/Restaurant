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
                <li role="presentation" class="list active"><a href="manage_index.html">服务员管理</a></li>
                <li role="presentation" class="list"><a href="manage_check.html">考勤管理</a></li>
                <li role="presentation" class="list"><a href="manage_advance.html">预定管理</a></li>
                <li role="presentation" class="list"><a href="manage_dish.html">菜品管理</a></li>
                <li role="presentation" class="list"><a href="manage_order.html">流水管理</a></li>
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
                                 pplaceholder="${requestScope.person.personName}" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_sex"
                                 placeholder="${requestScope.person.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_sex"
                                 placeholder="${requestScope.person.personTele}">
                        </div>
                    </div>
                <div class="form-group">
                        <label for="inputPassword" class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="waiter_password"
                                placeholder="${requestScope.person。password}">
                        </div>
                    </div>
                    <!-- 提交和重置按钮 -->
                    <div class="form-group">
                     <button type="button" class="btn btn-success"> Submit</button>
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
</html>