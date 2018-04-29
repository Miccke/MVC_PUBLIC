<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String nickname = "";
	String province = "";
	String headimgurl = "";
	if (session != null) {
		String openid = session.getAttribute("openid").toString();
		nickname = session.getAttribute("nickname").toString();
		int sex = Integer.parseInt(session.getAttribute("sex")
				.toString());
		province = session.getAttribute("province").toString();
		String city = session.getAttribute("city").toString();
		String country = session.getAttribute("country").toString();
		headimgurl = session.getAttribute("headimgurl").toString();
		String privilege = session.getAttribute("privilege").toString();
		String language = session.getAttribute("language").toString();

		System.out.println(nickname + sex + province + city + country
				+ headimgurl + privilege + language);
	} else {

		String code = request.getParameter("code");
		//String code = "081Q10Fz1kCYCh0U8WGz1SIXEz1Q10FL";
		System.out.println("URL参数：" + code);
		String refresh_token = "";
		{
			String wxUrl = "https:/"
					+ "/api.weixin.qq.com/sns/oauth2/access_token?appid=wx32c7f58bed37cf1f&secret=52e4def5eb3b8d36defe10af7dad4398&code="
					+ code + "&grant_type=authorization_code";

			URL url = new URL(wxUrl); //url地址
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.connect();
			OutputStream os = connection.getOutputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sbf = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sbf.append(lines);
			}
			System.out.println(sbf.toString());
			JSONObject object = JSONObject.fromObject(sbf.toString());

			String access_token = object.getString("access_token");
			refresh_token = object.getString("refresh_token");
			String openid = object.getString("openid");
			System.out.println(access_token + " " + refresh_token + " "
					+ openid);
		}
		String refresh_token1 = "";
		{
			String scopeUrl = "https:/"
					+ "/api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx32c7f58bed37cf1f&grant_type=refresh_token&refresh_token="
					+ refresh_token;
			URL url1 = new URL(scopeUrl); //url地址
			HttpURLConnection connection1 = (HttpURLConnection) url1
					.openConnection();
			connection1.setDoInput(true);
			connection1.setDoOutput(true);
			connection1.setRequestMethod("GET");
			connection1.setUseCaches(false);
			connection1.setInstanceFollowRedirects(true);
			connection1.setRequestProperty("Content-Type",
					"application/json");
			connection1.connect();
			OutputStream os1 = connection1.getOutputStream();

			BufferedReader reader1 = new BufferedReader(
					new InputStreamReader(connection1.getInputStream()));
			String lines1;
			StringBuffer sbf1 = new StringBuffer();
			while ((lines1 = reader1.readLine()) != null) {
				lines1 = new String(lines1.getBytes(), "utf-8");
				sbf1.append(lines1);
			}
			System.out.println(sbf1.toString());

			JSONObject object1 = JSONObject.fromObject(sbf1.toString());
			String access_token1 = object1.getString("access_token");
			refresh_token1 = object1.getString("refresh_token");
			String openid1 = object1.getString("openid");
		}
		String access_token2 = "";
		String refresh_token2 = "";
		String openid2 = "";
		{
			String access_tokenUrl = "https:/"
					+ "/api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx32c7f58bed37cf1f&grant_type=refresh_token&refresh_token="
					+ refresh_token1;

			URL url2 = new URL(access_tokenUrl); //url地址
			HttpURLConnection connection2 = (HttpURLConnection) url2
					.openConnection();
			connection2.setDoInput(true);
			connection2.setDoOutput(true);
			connection2.setRequestMethod("GET");
			connection2.setUseCaches(false);
			connection2.setInstanceFollowRedirects(true);
			connection2.setRequestProperty("Content-Type",
					"application/json");
			connection2.connect();
			OutputStream os2 = connection2.getOutputStream();

			BufferedReader reader2 = new BufferedReader(
					new InputStreamReader(connection2.getInputStream()));
			String lines2;
			StringBuffer sbf2 = new StringBuffer();
			while ((lines2 = reader2.readLine()) != null) {
				lines2 = new String(lines2.getBytes(), "utf-8");
				sbf2.append(lines2);
			}
			System.out.println(sbf2.toString());

			JSONObject object2 = JSONObject.fromObject(sbf2.toString());
			access_token2 = object2.getString("access_token");
			refresh_token2 = object2.getString("refresh_token");
			openid2 = object2.getString("openid");
		}
		{
			String userInfoURL = "https://api.weixin.qq.com/sns/userinfo?access_token="
					+ access_token2
					+ "&openid="
					+ openid2
					+ "&lang=zh_CN ";

			URL url3 = new URL(userInfoURL); //url地址
			HttpURLConnection connection3 = (HttpURLConnection) url3
					.openConnection();
			connection3.setDoInput(true);
			connection3.setDoOutput(true);
			connection3.setRequestMethod("GET");
			connection3.setUseCaches(false);
			connection3.setInstanceFollowRedirects(true);
			connection3.setRequestProperty("Content-Type",
					"application/json");
			connection3.connect();
			OutputStream os3 = connection3.getOutputStream();

			BufferedReader reader3 = new BufferedReader(
					new InputStreamReader(connection3.getInputStream()));
			String lines3;
			StringBuffer sbf3 = new StringBuffer();
			while ((lines3 = reader3.readLine()) != null) {
				lines3 = new String(lines3.getBytes(), "utf-8");
				sbf3.append(lines3);
			}
			System.out.println(sbf3.toString());
			JSONObject object = JSONObject.fromObject(sbf3.toString());
			String openid = object.getString("openid");
			nickname = object.getString("nickname");
			int sex = object.getInt("sex");
			province = object.getString("province");
			String city = object.getString("city");
			String country = object.getString("country");
			headimgurl = object.getString("headimgurl");
			String privilege = object.getString("privilege");
			String language = object.getString("language");

			session.setAttribute("openid", object.getString("openid"));
			session.setAttribute("nickname",
					object.getString("nickname"));
			session.setAttribute("sex", object.getInt("sex"));
			session.setAttribute("province",
					object.getString("province"));
			session.setAttribute("city", object.getString("city"));
			session.setAttribute("country", object.getString("country"));
			session.setAttribute("headimgurl",
					object.getString("headimgurl"));
			session.setAttribute("privilege",
					object.getString("privilege"));
			session.setAttribute("language",
					object.getString("language"));

			System.out.println(nickname + sex + province + city
					+ country + headimgurl + privilege + language);
		}

	}
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

	window.onload = function() {
		var caidanlist = "";
		$.ajaxSetup({
			async : false
		});

		$.get("../../orderInfo/statelist",
						function(data) {
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
								var orderNum = data[i].orderNum;
								var storeName = data[i].storeName;
								var delivery_cost;
								$.get("../../storeInfo/singleStore", {
									storeId : data[i].storeId
								}, function(data) {
									delivery_cost = data.deliveryCost;
								}, "json");
								var num = data[i].num;
								var numarray = num.split(",");

								var content = data[i].content;
								var contentarray = content.split(",");

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

								caidanlist += '<div class="on_d">'
										+ '<div class="sp_pr">'
										+ '<img src="images/gdsgf4.jpg" class="dingdan_shanjia" style="border-radius:1.5em;width:3em; 	height:3em;"/>'
										+ '<div class="text_p">' + '<p>'
										+ storeName
										+ '</p>'
										+ '</div>'
										+ '<div class="dingdan_top_right">'
										+ '<sapn class="dingdan_state">'
										+ orderstate
										+ '</span><br/>'
										+ '<span class="countDown">'
										+ countDown
										+ '</span>'
										+ '<a href="#"><img src="images/order_again.png" style=" width:5.6em; height:2.2em; margin-right:0"/></a>'
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
										+ '</div>' + '</div>';
								$(".lie").html(
										'<div style="height:5.7em;"></div>'
												+ caidanlist);

							}
						}, "json");

	};
</script>
</head>
<body style="height: auto">
	<!--top-->
	<div class="top_c">
		<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi">订单</p>
	</div>
	<!--头部-->
	<div class="pos">
		<div class="container">
			<div class="row titll">
				<div class="col-xs-4" style="line-height: 3em;">
					<div style="width: 60%; margin-left: 20%;" class="shangjia_caidan_top shangjia_caidan_top_left">营养餐订单</div>
				</div>
				<div class="col-xs-4" style="line-height: 3em">
					<div style="width: 60%; margin-left: 20%;" class="shangjia_caidan_top shangjia_caidan_top_right">服务订单</div>
				</div>
			</div>
		</div>
	</div>

<div>
	<div class="lie"></div>
	<div class="shop">222</div>
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
							style="width: 1.5em" /></i> <span class="nav_ti">订单</span>
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
