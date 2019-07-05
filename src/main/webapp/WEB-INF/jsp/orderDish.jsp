<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>点菜单</title>

<link rel="stylesheet" href="/restaurant/css/menu.css">
<link rel="stylesheet" href="/restaurant/css/bootstrap.min.css">
<link rel="stylesheet" href="/restaurant/css/bgstretcher.css">
<script type="text/javascript" src="/restaurant/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/restaurant/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/restaurant/js/bgstretcher.js"></script>
<script type="text/javascript" src="/restaurant/js/tab.js"></script>

<!--轮播js-->
<script type="text/javascript">
	$(document).ready(
			function() {
					  TabBlock.init();
					initialInformation();
					loadYixuan();
				//  Initialize Backgound Stretcher	   
				$('.demoo').bgStretcher(
						{

							images : [ '/restaurant/images/banner1.jpg',
									'/restaurant/images/banner2.jpg',
									'/restaurant/images/banner3.jpg' ],

							imageWidth : 800,

							imageHeight : 135,

							slideDirection : 'N',

							slideShowSpeed : 1000,

							transitionEffect : 'fade',

							sequenceMode : 'normal',

						});

			});
</script>
</head>



<body style="height: 100%;">
	<div id="left" class="col-md-3 col-sm-3">

		<div id="left_top"
			class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
			<img src="/restaurant/images/w-logo.jpg" style="width: 100%;">
		</div>

		<div id="left_bottom"
			class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
	<figure class="tabBlock">
	<div class="tabBlock-tabs" >
    <div class="tabBlock-tab is-active" style="float:left">已点菜品</div>
    <div class="tabBlock-tab" >在选菜品</div>
    </div>
  <div class="tabBlock-content">
    <div class="tabBlock-pane">
    <div style="height:60%">
      <ul style="list-style-type: none" id="yixuanC">
      </ul>
     </div>
     <p id="totalMoney">总价:</p>
     <button type="button" class="btn btn-danger">结算</button>
    </div>
    <div class="tabBlock-pane">
      <div style="height:60%">
      <ul style="list-style-type: none" id="weixuanC" style="float:left">
      	<li>未选择</li>
      </ul>
      </div>
    <button type="button" class="btn btn-danger" onclick="addMenu()">提交</button>
    </div>
  </div>
		</div>
	</div>


	<div id="right" class="col-md-offset-3 col-md-9">

		<div id="right_top">
			<div class="demoo" style="width: 99.5%; height: 100%;"></div>
		</div>

		<div id="right_mid" class=" col-md-12 col-sm-12">
				<c:forEach items="${requestScope.dishes}" var="dish">
					<div id="menu_item" > <img height="154.5" width="250"
							src="/restaurant/images/w-logo.jpg" />
						
						<div style="float: left;padding-top:8px;"><span style="font-weight: 900">${dish.dishName}(￥${dish.dishPrice})</span></div>
						<button type="button" class="btn btn-danger" style="margin-right:4px;border-radius: 22px;width: 34px;height: 34px;"
						onclick="dishMinus(${dish.dishId})">
						<span>-</span>
						</button>
						<span id="${dish.dishId}" class="countDish"  name="${dish.dishName}">0</span>
						<button type="button" class="btn btn-danger" style="margin-left:4px;border-radius: 22px;width: 34px;height: 34px;"
						onclick="dishPlus(${dish.dishId})">
							<span>+</span>
						</button>
					
					</div>
					
				</c:forEach>
			
		</div>

		<div id="right_bottom">
			<p align="center" style="margin-top:9px;margin-bottom: 0px">西安"海上捞"空中花园餐厅 2019 Powered by wychen</p>
		</div>
	</div>


</body>
<script type="text/javascript">
var DishInformation = {};

function dishPlus(id){
	var num=parseInt($("#"+id).html());
	$("#"+id).html(num+1);
	loadWeixuan()
};

function addMenu(){
	var tableId = '${requestScope.tableId}';
	var menus = [];
	$(".countDish").each(function(){
		if($(this).html() != '0'){
			let m = new Object();
			m.tableId=tableId;
			m.dishId=$(this).attr("id");
			m.dishNumber=$(this).html();
			menus.push(m);
		}
	  });
	console.log(menus)
	$.ajax({
		url:"/restaurant/waiter/addMenu",
		data:{
			menus:JSON.stringify(menus)
		},
		type:"post",
		success:function(msg){
			alert(msg);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
		}
	
	});
	
}

function initialInformation(){
	var dishes=JSON.parse('${requestScope.dishes}')
	for(let i of dishes){
		DishInformation[i.dishId]=i;
	}
	console.log(DishInformation);
};
function dishMinus(id){
	var num=$("#"+id).html();
	if(num == '0'){
		$("#"+id).html('0')
	}
	else $("#"+id).html(num-1);
	loadWeixuan()
};

function loadYixuan(){
	var div1=$("#yixuanC");
	var zong=$("#totalMoney")
	var money=0;
	var str='';
	var menus=JSON.parse('${requestScope.existingMenu}');
	for(i of menus){
		str+='<li>'+DishInformation[i.dishId].dishName+'X'+i.dishNumber+'</li>';
		money+=DishInformation[i.dishId].dishPrice*i.dishNumber;
	}
	zong.html('总价：'+money);
	if(str == '') div1.html('未点菜')
	else div1.html(str);
}

function loadWeixuan(){
	var div1=$("#weixuanC");
	var str='';
	$(".countDish").each(function(){
		if($(this).html() != '0')
	    str+='<li>'+$(this).attr("name")+'X'+$(this).html()+'</li>'
	  });
	if(str == '') div1.html('未选择')
	else div1.html(str);
};

</script>
</html>
