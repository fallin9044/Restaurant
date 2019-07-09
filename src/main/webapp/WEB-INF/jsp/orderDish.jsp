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

<a href="/restaurant/waiter/tableStatus"><button type="button" class="btn btn-default"><h1>回去</h1></button></a>

<body style="height: 100%;">
	<div id="left" class="col-md-3 col-sm-3">

		<div id="left_top"
			class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
			<img src="/restaurant/images/w-logo.jpg" style="width: 100%;">
		</div>

		<div id="left_bottom"
			class="col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
	<figure class="tabBlock" style="margin-top:30px">
	<div class="tabBlock-tabs" >
    <div class="tabBlock-tab is-active" style="float:left">已点菜品</div>
    <div class="tabBlock-tab" style="float:left">在选菜品</div>
    </div>
    <br>
  <div class="tabBlock-content">
    <div class="tabBlock-pane">
    <div class="tab-menu">
      <ul style="list-style-type: none;padding-inline-start:0px" id="yixuanC">
      </ul>
     </div>
     <span>总价：</span>
     <span id="totalMoney">0</span>
     <button type="button" class="btn btn-danger" style="float:right" onclick="settleAccount()">结算</button>
    </div>
    <div class="tabBlock-pane">
      <div class="tab-menu">
      <ul style="list-style-type: none;padding-inline-start:0px" id="weixuanC" style="float:left">
      	<li>未选择</li>
      </ul>
      </div>
      <span>总价：</span>
     <span id="IngtotalMoney">0</span>
    <button type="button" class="btn btn-danger" style="float:right" onclick="addMenu()">提交</button>
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

function changeMenuState(id){
	$.ajax({
		url:"/restaurant/waiter/changDishState",
		data:{
			'menuId':id,
			},
		type:"post",
		success:function(msg){
			if(msg == 'success')
				$("[mid='"+id+"']").replaceWith('<span style="float:right">已经上菜</span>')
			else alert(msg)
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
		}
	
	});
};


function settleAccount(){

		var tableId = '${requestScope.tableId}'
		var money = $("#totalMoney").html()
		if(money == '0'){
			alert('还未点菜')
			return
		}
		if(window.confirm('确认要结算吗？')){
		$.ajax({
			url:"/restaurant/waiter/settleAccount",
			data:{
				'tableId':tableId,
				'total':money,
			},
			type:"post",
			success:function(msg){
				alert(msg);
				location.href = '/restaurant/waiter/tableStatus';
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
			}
		
		});
	}
}

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
	if(menus.length == 0){
		alert("你还没有选菜");
		return;
	}
	if(window.confirm('确认要提交吗？')){
	console.log(menus)
	$.ajax({
		url:"/restaurant/waiter/addMenu",
		data:{
			menus:JSON.stringify(menus)
		},
		type:"post",
		success:function(msg){
			alert(msg);
			location.reload();
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
		}
	
	});
	}
}

function initialInformation(){
	var dishes=JSON.parse('${requestScope.dishes}')
	for(let i of dishes){
		DishInformation[i.dishId]=i;
	}
	console.log(DishInformation);
};

function dishPlus(id){
	var num=parseInt($("#"+id).html());
	$("#"+id).html(num+1);
	loadWeixuan()
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
		var link;
		console.log(i)
		if(i.dishState == 0) link='<a href="javascript:void(0);" style="float:right"'+' mid="'+i.menuId+'" onclick=changeMenuState('+i.menuId+')'+'>上菜</a>';
		else link='<span style="float:right">已经上菜</span>';
		str+='<li><span>'+DishInformation[i.dishId].dishName+'X'+i.dishNumber+'     </span>'+link+'</li>';
		money+=DishInformation[i.dishId].dishPrice*i.dishNumber;
	}
	zong.html(money);
	if(str == '') div1.html('未点菜')
	else div1.html(str);
}

function loadWeixuan(){
	var div1=$("#weixuanC");
	var str='';
	var zong=$("#IngtotalMoney")
	var money=0;
	$(".countDish").each(function(){
		if($(this).html() != '0')
	    str+='<li>'+$(this).attr("name")+'X'+$(this).html()+'</li>'
		money+=DishInformation[$(this).attr("id")].dishPrice*$(this).html();
	  });
	zong.html(money);
	if(str == '') div1.html('未选择')
	else div1.html(str);
};

</script>
</html>
