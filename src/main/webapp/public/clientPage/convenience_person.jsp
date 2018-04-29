<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openid = "ort3e1XdNZ9tkdtup92pgUW0m3D8";	
//String openid = session.getAttribute("openid").toString();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />

<title>历史订单</title>
<style type="text/css">
.starWrapper {
	padding: 5px;
	padding-top:25%;
	padding-left:45%;
}

.starWrapper img {
	cursor: pointer;
}
</style>
<link href="http://at.alicdn.com/t/font_jvc3xp5ob1uac3di.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	window.onload = function() {
		var caidanlist = "";
		$.ajaxSetup({
			async : false
		});

		$.get("../../orderInfo/stateEndlist",{openid:'<%=openid%>'},
						function(data) {
							var orderNumtemp = '';
							for (var i = 0; i < data.length; i++) {
								var caiming = '';
								var orderstate = "";
								if (data[i].orderstate == 3) {
									orderstate = "订单完成";
								}
								
								var EndTime = new Date(data[i].sendtime);
								var orderNum = data[i].orderNum;
								var strorId = data[i].storeId;
								var storeName = data[i].storeName;
								var delivery_cost;
								$.get("../../storeInfo/singleStore", {
									storeId : data[i].storeId
								}, function(data) {
									delivery_cost = data.deliveryCost;
									storeImgUrl = data.storeUrl
								}, "json");
								var num = data[i].num;
								var numarray = num.split(",");

								var content = data[i].content;
								var contentarray = content.split(",");
							
								var score;
								var pingjiaString;
								var onclickValue;
								if(data[i].orderScore == 0){
									pingjiaString = '评价';
									onclickValue = '" onclick="pingjia(this);"' ;
								}
								if(data[i].orderScore != 0){
									pingjiaString = '已评价';
									onclickValue = '"' ;
								}
								
								var dingdan = '';
								var dingdanPrice = 0;
								for (var j = 0; j < contentarray.length; j++) {
									//查询菜名
									$.get("../../dishInfo/singledish", {
										dishId : contentarray[j]
									}, function(data) {
										caiming = data.dishName;
										dishPrice = data.dishPrice;
										dingdanPrice += parseFloat(dishPrice)
												* parseInt(numarray[j])
									}, "json");

									dingdan += '<li><div class="dingdan_bottom_left">'
											+ '<span style="float:left">'
											+ caiming
											+ '</span>'
											+ '</div>'
											+ '<div class="dingdan_bottom_right">'
											+ '<span>x '
											+ numarray[j]
											+ '</span>'
											+ '<span class="dingdan_bottom_price">￥'
											+ dishPrice
											+ '</span>'
											+ '</div>'
											+ '</li>';
								}
								//多个单子合成一个

								caidanlist += '<div class="on_d" style="width:100%;float:left">'
										+ '<div class="sp_pr">'
										+ '<img src="../../imageShow?fileId='+storeImgUrl+'" class="dingdan_shanjia" style="border-radius:1.5em;width:3em; 	height:3em;"/>'
										+ '<div class="text_p">' + '<p>'
										+ storeName
										+ '</p>'
										+ '</div>'
										+ '<div class="dingdan_top_right">'
										+ '<sapn class="dingdan_state">'
										+ orderstate
										+ '</span><br/>'
										+ '<span class="countDown">'
										+ '</span>'
										+ '<span  onclick="againDingdan(this);" id="'+orderNum+'" class="'+ strorId	+ '"><img src="images/order_again.png" style=" width:5.6em; height:2.2em; margin-right:0"/></span>'
										+ '</div>'
										+ '</div>'
										+ '<div class="button">'
										+ '<ul style="list-style:none">'
										+ '<span  class="'+orderNum+'">'
										+ dingdan
										+ '</span>'
										+ '<li>'
										+ '<div class="dingdan_bottom_left">'
										+ '<span style="float:left">配送费</span>'
										+ '</div>'
										+ '<div class="dingdan_bottom_right">'
										+ '<span class="dingdan_bottom_price">￥'
										+ parseFloat(delivery_cost)
										+ '</span>'
										+ '</div>'
										+ '</li>'
										+ '<li>'
										+ '<span class="dingdan_bottom_price">共2件商品，付款 <b>￥'
										+ (parseFloat(dingdanPrice.toFixed(2)) + parseFloat(delivery_cost))
										+ '</b></span>'
										+ '</li>'
										+ '</ul>'
										+ '</div>'
										+ '<div style="border-bottom: 1px solid #EEEEEE; width: 90%;margin-left: 5%;"></div>'
										+ '<div style="width: 100%;">'
										+ '<div id="'
										+ orderNum
										+ '" class="'
										+ strorId
										+ '" onclick="againDingdan(this);" style="width: 50%;text-align: center;height: 35px;font-size: 18px;padding-top: 5px;border-right:1px #B9B9B9 solid;border-bottom: 2px #EEEEEE solid;float: left">再来一单</div>'
										+ '<div id="'
										+ orderNum
										+ '" class="'
										+ strorId
										+ onclickValue
										+'style="width: 50%;text-align: center;height: 35px;font-size: 18px;padding-top: 5px;border-bottom: 2px #EEEEEE solid;float: right">'+pingjiaString+'</div>'
										+ '</div>' + '</div>';
								$(".add_state_dingdan").html(caidanlist);
							}
						}, "json");
	};
	function againDingdan(again) {
		var dataObject = new Object();
		dataObject.orderNum = again.id;
		orderNum = again.id;
		dataObject.strorId = again.className;
		$.ajax({
			url : "../../orderInfo/insertAgainOrder",
			data : {
				"dataObject" : JSON.stringify(dataObject)
			},
			type : 'post',
			async : true,
			success : function(result) {
				alert('新的订单已经生成');
				location.href = "convenience.jsp";
			}
		});
	}
	function pingjia1(){
		alert('评分未开放');
	}
	
	var orderNumPingjia;
	function pingjia(pingjia) {
		$('.window').css('display', 'block');
		orderNumPingjia = pingjia.id;
	}
	
	var starNum;
	function rate(obj, oEvent) {
		$('.window').click(function() {
			$('.window').css('display', 'block');
		})
		var imgSrc = 'images/pingfen/score_star_grey_big.png'; 
		var imgSrc_2 = 'images/pingfen/score_star_yellow_big.png';
		//--------------------------------------------------------------------------- 
		if (obj.rateFlag)
			return;
		var e = oEvent || window.event;
		var target = e.target || e.srcElement;
		var imgArray = obj.getElementsByTagName("img");
		for (var i = 0; i < imgArray.length; i++) {
			imgArray[i]._num = i;
			imgArray[i].onclick = function() {
				if (obj.rateFlag)
					return;
				obj.rateFlag = true;
				starNum = this._num + 1;
				$('.submitPing').click(function(){
					var dataObject = new  Object();
					dataObject.starNum = starNum+"";
					dataObject.orderNum = orderNumPingjia;
					$.ajax({
						url : "../../orderInfo/orderScore",
						data : {
							"dataObject" : JSON.stringify(dataObject)
						},
						type : 'post',
						async : true,
						success : function(result) {
							location.href = "convenience_person.jsp";
						}
					});
				})
				
				
			};
		}
		if (target.tagName == "IMG") {
			for (var j = 0; j < imgArray.length; j++) {
				if (j <= target._num) {
					imgArray[j].src = imgSrc_2;
				} else {
					imgArray[j].src = imgSrc;
				}
			}
		} else {
			for (var k = 0; k < imgArray.length; k++) {
				imgArray[k].src = imgSrc;
			}
		}
	}
</script>
<script type="text/javascript" src="js/againDingdan.js"></script>
</head>
<body style="height: auto">
	<!--top
	<div class="top_c">
		<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi">订单</p>
	</div>-->


	<div class="add_state_dingdan"></div>
	<div class="window">
		<div style="width:240px;height:190px;margin-top:50%;margin-left:20%; background:url(images/pingfen/history_order_prompt_bg.png); background-size:100% 100%" >
			<p style="padding-top:100px;padding-left:55px;" class="starWrapper" onmouseover="rate(this,event)">
					<img src="images/pingfen/score_star_grey_big.png" width="25px" height="25px" id="1" class="imgP"/>
					<img src="images/pingfen/score_star_grey_big.png" width="25px" height="25px" id="2" class="imgP"/>
					<img src="images/pingfen/score_star_grey_big.png" width="25px" height="25px" id="3" class="imgP"/>
					<img src="images/pingfen/score_star_grey_big.png" width="25px" height="25px" id="4" class="imgP"/>
					<img src="images/pingfen/score_star_grey_big.png" width="25px" height="25px" id="5" class="imgP"/> 
			</p>
			<p style="padding-top:23px; font-size:18px; padding-left:50px;" > 
				<span class="exitPing">取消</span>
				<span class="submitPing" style="margin-left:65px;">确定</span>
			</p>
		</div> 
	</div>

	<!--footer
	<div class="footer" style="text-align: center;">
		<div class="container" style="text-align: center;">
			<div class="row" style="text-align: center;">

				<div class="col-xs-3">
					<a href="index.jsp" class="dao">  <i class="iconfont icon-shouye-copy-copy-copy""></i>
						<i class="iconfont""><img src="images/db/tab_home.png"
							style="width: 1.5em" /></i> <span class="nav_ti">首页</span>
					</a>
				</div>

				<div class="col-xs-3">
					<a href="convenience.jsp" class="dao">  <i class="iconfont icon-quanbu"></i>
						<i class="iconfont""><img src="images/db/tab_order_hlight.png"
							style="width: 1.5em" /></i> <span class="nav_ti">订单</span>
					</a>
				</div>

				<div class="col-xs-3">
					<a href="personal.jsp" class="dao"> <i class="iconfont""><img
							src="images/db/tab_my.png" style="width: 1.5em" /></i> <i class="iconfont icon-information"></i>
						<span class="nav_ti">我的</span>
					</a>
				</div>

			</div>
		</div>
	</div>-->

	</div>
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/iscroll.js"></script>
	<script>
		$(function() {
			var w_he = $(window).height();
			var t_he = $(".top_c").height();
			var f_he = $(".footer").height();
			var l_he = w_he - t_he - f_he;
			$(".left_c,.right_c").css({
				height : l_he
			});
			var Scroll = new iScroll('left', {
				hScrollbar : false,
				vScrollbar : true,
				hScroll : false,
				vScroll : true,
				useTransform : false
			});
			var Scroll = new iScroll('right', {
				hScrollbar : false,
				vScrollbar : true,
				hScroll : false,
				vScroll : true,
				useTransform : false
			});
		})
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>

</body>
</html>
