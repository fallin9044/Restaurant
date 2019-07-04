<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>点菜单</title>
    
<link rel="stylesheet" href="/restaurant/css/main.css">
<link rel="stylesheet" href="/restaurant/css/menu.css">
<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">
<link rel="stylesheet" href="/restaurant/css/bgstretcher.css">
    
<script type="text/javascript" src="/restaurant/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/restaurant/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/restaurant/js/bgstretcher.js"></script>
<script type="text/javascript" src="/restaurant/js/bgstretcher.js"></script>

<!--轮播js-->
<script type="text/javascript">

	$(document).ready(function(){
        //  Initialize Backgound Stretcher	   
		$('.demoo').bgStretcher({

			images: ['/restaurant/images/banner1.jpg','/restaurant/images/banner2.jpg','/restaurant/images/banner3.jpg'],

			imageWidth: 800, 

			imageHeight: 135, 

			slideDirection: 'N',

			slideShowSpeed: 1000,

			transitionEffect: 'fade',

			sequenceMode: 'normal',

		});

		

	});

</script>
</head>



<body style="height: 100%;">
    <div id="left" class="col-md-3 col-sm-3">
        
        <div id="left_top" class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
            <img src="/restaurant/images/w-logo.jpg" style="width:100%;">
        </div>
        
        <div id="left_bottom" class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10" 
             style="background-color: aqua;">
            订单
        </div>
    </div>
    
    
    <div id="right" class="col-md-offset-3 col-md-9">
        
        <div id="right_top">
            <div class="demoo" style="width:99.5%; height:100%;"></div>
        </div>
        
        <div id="right_mid" class=" col-md-11 col-sm-12" style="overflow: auto">
        <ul>
            <c:forEach items="${requestScope.dishes}" var="dish">
            <li>
						<span width="">
						<img src="/restaurant/images/w-logo.jpg" />
						</span>
						</li>
					</c:forEach>
		</ul>
        </div>
        
        <div id="right_bottom">
            <p align="center">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
        </div>
    </div>
    

</body>
</html>
