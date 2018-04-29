<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.httpclient.HttpClient"%>
<%@page import="org.apache.commons.httpclient.HttpException"%>
<%@page import="org.apache.commons.httpclient.HttpMethod"%>
<%@page import="org.apache.commons.httpclient.methods.GetMethod"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openid = "ort3e1QHZIY-ksgnOLd30_u9C5LY";	
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />

<link href="http://at.alicdn.com/t/font_jvc3xp5ob1uac3di.css"
	rel="stylesheet" type="text/css" />
<title>订单</title>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function showTime(EndTime) {
		var NowTime = new Date();
		var t = EndTime.getTime() - NowTime.getTime() + 10800000;
		var h = 00;
		var m = 00;
		var s = 00;
		if (t >= 00) {
			h = Math.floor(t / 1000 / 60 / 60 % 24);
			if (h < 10) {
				h = "0" + h;
			}
			m = Math.floor(t / 1000 / 60 % 60);
			if (m < 10) {
				m = "0" + m;
			}
			s = Math.floor(t / 1000 % 60);
			if (s < 10) {
				s = "0" + s;
			}
		}
		return h + ":" + m + ":" + s;
	}
	
	var currentPage = 0;
	var caidanlist = '';
	function add_dishOrder() {
		//指定请求为同步请求
		$.ajaxSetup({
			async : false
		});
		
		var showMore = '';
		var pageNo;
		
		$.get("../../orderInfo/getPageNo",{openid:'<%=openid%>'},function(data) {
			pageNo = data;
		});
		//调一次方法则当前页加1
		currentPage++;
		if(pageNo>=currentPage){
			$.get("../../orderInfo/statelist",{openid:'<%=openid%>',currentPage:currentPage},function(data) {
				try{
					if(currentPage+1>pageNo){
						showMore = '没有更多了。。。'
						$('.showMore').html(showMore);
					}else{
						showMore = '显示更多。。。'
						$('.showMore').html(showMore);
					}
					
					var orderNumtemp = '';
				for (var i = 0; i < data.length; i++) {
					var caiming = '';
					var orderstate = "";
					if (data[i].orderstate == 0) {
						orderstate = "等待接单";
					}
					if (data[i].orderstate == 1) {
						orderstate = "订单已确认";
					}
					if (data[i].orderstate == 2) {
						orderstate = "订单派送中";
					}
					if (data[i].orderstate == 4) {
						orderstate = "商家拒绝订单";
					}
					if (data[i].orderstate == 3) {
						orderstate = "订单完成";
					}
					var EndTime = new Date(data[i].sendtime);
					var countDown;
					countDown = showTime(EndTime);
					setTimeout(showTime, 1000);
	
					if (countDown == "0:0:0") {
						countDown = "";
					}
					var strorId = data[i].storeId;
					var orderNum = data[i].orderNum;
					var storeName = data[i].storeName;
					var delivery_cost;
					var store_Phone = '';
					$.get("../../storeInfo/singleStore", {
						storeId : data[i].storeId
					}, function(data) {
						delivery_cost = data.deliveryCost;
						storeImgUrl = data.storeUrl;
						store_Phone = data.storePhone
					}, "json");
					var num = data[i].num;
					var numarray = num.split(",");
	
					var content = data[i].content;
					var contentarray = content.split(",");
	
					var dingdan = '';
					var dingdanPrice = 0;
					var dishNum = contentarray.length;
					
					//拼接子单列表
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
					};
					//多个单子合成一个
					caidanlist += '<div class="on_d">'
							+ '<div class="sp_pr">'
							+ '<img src="business.jsp?store_id='+storeImgUrl+'" class="dingdan_shanjia" style="border-radius:1.5em;width:3em; 	height:3em;"/>'
							+ '<div class="text_p">' + '<p>'
							+ storeName
							+ '</p>'
							+ '<a href="tel:'+store_Phone+'" style="color:#fe6e12">'+store_Phone+'</a>'
							+ '</div>'
							+ '<div class="dingdan_top_right">'
							+ '<sapn class="dingdan_state">'
							+ orderstate
							+ '</span><br/>'
							+ '<span class="countDown">'
							+ countDown
							+ '</span>'
							+ '<img src="images/order_again.png" style=" width:5.6em; height:2.2em; margin-right:0" onclick="againDingdan(this);" id="'+orderNum+'" class="'+strorId+'"/>'
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
							+ '<span class="dingdan_bottom_price">共'+dishNum+'份菜品，付款 <b>￥'
							+ (parseFloat(dingdanPrice.toFixed(2)) + parseFloat(delivery_cost))
							+ '</b></span>'
							+ '</li>'
							+ '</ul>'
							+ '</div>' + '</div>';
					$(".lie .lie_top").html('<div style="height:3em;"></div>'+ caidanlist);
				}
				}catch(e){
					//alert(e.toString());
				}
			}, "json");
		}
	};
	
	window.onload = add_dishOrder;	//页面加载完毕后执行
	
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
	/**
	 * 传入日期，返回字符串
	 * @param date
	 * @returns {String}
	 */
	this.getDateStr = function(date){
		if(date != null && date != ""){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var dateStr = y +'-'+ m +'-'+ d;
			return dateStr;
		}else{
			return null;
		}
	}

	function serve_dingdan(){
		$('.shop').css('display','block').siblings().css('display','none');
		$('.footer').css('display','block');

		$.ajaxSetup({
			async : false
		});
		var temp = '<%=openid%>';
		$.get("../../sWaiterOrder/serverNoEndlist",{openid:temp},function(data){
			debugger;
			var serve_dingdan = "";
			for(var i = 0;i<data.length;i++){
				
				var serveInfotype = data[i].waiterType;
				//1月嫂，2催乳师，3陪护师
	
				var serve_type = "";
				if(serveInfotype == 1){
					serve_type = "月嫂";
				}
				if(serveInfotype == 2){
					serve_type = "催乳师";
				}
				if(serveInfotype == 3){
					serve_type = "陪护师";
				}
				var dutytime = "";
				if(data[i].dutyTime != null){
					var timeTo = new Date(data[i].dutyTime);
					dutytime = '到岗时间：'+getDateStr(timeTo);
				}else{
					dutytime = "";
				}
				var servestate = "";
				if (data[i].serveState == 0) {
					servestate = "等待接单";
				}
				if (data[i].serveState == 1) {
					servestate = "订单已确认";
				}
				if (data[i].serveState == 2) {
					servestate = "服务上门中";
				}
				if (data[i].serveState == 4) {
					servestate = "订单被拒绝";
				}
				if (data[i].serveState == 3) {
					servestate = "订单完成";
				}
				
				serve_dingdan+='<div style="border-bottom:1px solid #CCC">'
			    	+'<div>'
			    	+'<p style="margin-top:10px;">'
			    	+'<img class="server_logo" src="images/serveIcon/order_server_icon.png" style="width:18px;height:20px;"/>'
			    	+'<span class="serve_type" style="margin-left:10px;">'+serve_type+'</span>'
			    	+'<span class="age" style="margin-left:20px;">年龄：20</span>'
			    	+'<span style="float:right; ">'+dutytime+'</span>'
			    	+'</p>'
			    	+'</div>'
			    	+'<div style="border-bottom: 1px solid #eeeeee; width: 100%;">'
			    	+'</div>'
			    	+'<div class="list_content">'
			    	+'<p style="font-size: 16px;margin-top:10px;">'
			    	+'<span style="margin-left:10px;">'+data[i].userName+'</span>'
			    	+'<span style="margin-left:30px;"><a href="tel:'+data[i].userPhone+'" style="color:#fe6e12">'+data[i].userPhone+'</a></span>'
			    	+'<span style="float:right; margin-right:20px;" class="dingdan_state">'+servestate+'</span>'
			    	+'</p>'
			    	+'<p class="jian" style="margin-left:10px;"	>备注：'+data[i].remark+'</p>'
			    	+'</div>'
			    	+'</div>';
			}
			
			$('.shop .shop_top').html('<div style="height:3em;"></div>'+ serve_dingdan+'<div style="height:3.8em;"></div>');
		},"json")
	}
	
</script>
</head>
<body style="height: auto">
	<!--top
	<div class="top_c">
		<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi">订单</p>
	</div>-->
	<!--头部-->
	<div class="pos_gls">
		<div class="container">
			<div class="row titll">
				<div class="col-xs-4" style="line-height: 3em;">
					<div style="width: 70%; margin-left: 20%; font-size:16px; border-bottom:3px #FFA980 solid; color:#FFA980" class="shangjia_caidan_top shangjia_caidan_top_left" >营养餐订单</div>
				</div>
				<div class="col-xs-4" style="line-height: 3em">
					<div style="width: 70%; margin-left: 20%; font-size:16px; " class="shangjia_caidan_top shangjia_caidan_top_right1" onclick="serve_dingdan()">母婴护理订单</div>
				</div>
			</div>
		</div>
	</div>

<div>
	<div class="lie">
		<div class="lie_top" style="float:left; width:100%">
		</div>
		<div class="lie_bottom" style="float:left; width:100%; text-align:center">
			<a href="javascript:void(0)" onClick="add_dishOrder();" class="showMore"></a>
		</div>
		<div style="height:5em;float:left; width:100%"></div>
	</div>
	<div class="shop">
		<div class="shop_top" style="float:left; width:100%">
		</div>
		<div class="shop_bottom" style="float:left; width:100%; text-align:center" >
			<a href="javascript:void(0)" onClick="add_dishOrder();">显示更多</a>
		</div>
	</div>
	<!--footer-->
	<div class="footer" style="text-align: center;">
		<div class="container" style="text-align: center;">
			<div class="row" style="text-align: center;">

				<div class="col-xs-3">
					<a href="index.jsp" class="dao"> <!--  <i class="iconfont icon-shouye-copy-copy-copy""></i>-->
						<i class="iconfont""><img src="images/db/tab_home.png"
							style="width: 1.5em" /></i> <span class="nav_ti">首页</span>
					</a>
				</div>

				<div class="col-xs-3">
					<a href="convenience.jsp" class="dao"> <!-- <i class="iconfont icon-quanbu"></i>-->
						<i class="iconfont""><img src="images/db/tab_order_hlight.png"
							style="width: 1.5em" /></i> <span class="nav_ti" style="color:#FF7979;">订单</span>
					</a>
				</div>

				<div class="col-xs-3">
					<a href="personal.jsp" class="dao"> <i class="iconfont""><img
							src="images/db/tab_my.png" style="width: 1.5em" /></i> <!--<i class="iconfont icon-information"></i>-->
						<span class="nav_ti">我的</span>
					</a>
				</div>

			</div>
		</div>
	</div>
</div>
	
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
