<%@page import="com.zxpublic.util.HttpXmlClient"%>
<%@page import="java.util.UUID"%>
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

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/" + "public/clientPage/";
	String jsapi_ticket = "";
	String signature = "";
	String timestamp = "";
	String nonceStr = "";
	String code=request.getParameter("code");
	// code = "051I8YpY1q4Y8V0bXksY1OtXpY1I8Yp2";
	System.out.println("URL参数：" + code );
	if(code != null){
		String refresh_token = "";
		String access_token = "";
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
			connection.setRequestProperty("Content-Type","application/json");
			connection.connect();
			OutputStream os = connection.getOutputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String lines;
			StringBuffer sbf = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sbf.append(lines);
			}
			System.out.println(sbf.toString());
			JSONObject object = JSONObject.fromObject(sbf.toString());

			access_token = object.getString("access_token");
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
			HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
			connection1.setDoInput(true);
			connection1.setDoOutput(true);
			connection1.setRequestMethod("GET");
			connection1.setUseCaches(false);
			connection1.setInstanceFollowRedirects(true);
			connection1.setRequestProperty("Content-Type","application/json");
			connection1.connect();
			OutputStream os1 = connection1.getOutputStream();

			BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection1.getInputStream(),"UTF-8"));
			String lines1;
			StringBuffer sbf1 = new StringBuffer();
			while ((lines1 = reader1.readLine()) != null) {
				lines1 = new String(lines1.getBytes(), "utf-8");
				sbf1.append(lines1);
			}
			System.out.println("sbf1: "+sbf1.toString());

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
			HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
			connection2.setDoInput(true);
			connection2.setDoOutput(true);
			connection2.setRequestMethod("GET");
			connection2.setUseCaches(false);
			connection2.setInstanceFollowRedirects(true);
			connection2.setRequestProperty("Content-Type","application/json");
			connection2.connect();
			OutputStream os2 = connection2.getOutputStream();

			BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream(),"UTF-8"));
			String lines2;
			StringBuffer sbf2 = new StringBuffer();
			while ((lines2 = reader2.readLine()) != null) {
				lines2 = new String(lines2.getBytes(), "utf-8");
				sbf2.append(lines2);
			}
			System.out.println("sbf2: "+sbf2.toString());

			JSONObject object2 = JSONObject.fromObject(sbf2.toString());
			access_token2 = object2.getString("access_token");
			refresh_token2 = object2.getString("refresh_token");
			openid2 = object2.getString("openid");
		}
		{
			String userInfoURL = "https://api.weixin.qq.com/sns/userinfo?access_token="
					+ access_token2 + "&openid=" + openid2 + "&lang=zh_CN ";
					
			
			
			URL url3 = new URL(userInfoURL); //url地址
			System.out.println("URL3============"+url3);
			
			HttpMethod method = new GetMethod(userInfoURL);
			HttpClient httpclient = new HttpClient();
			
			try {
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("getWeiXinUserInfo result = " + result);
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			HttpURLConnection connection3 = (HttpURLConnection) url3.openConnection();
			connection3.setDoInput(true);
			connection3.setDoOutput(true);
			connection3.setRequestMethod("GET");
			connection3.setUseCaches(false);
			connection3.setInstanceFollowRedirects(true);
			connection3.setRequestProperty("Content-Type","application/json");
			connection3.connect();
			OutputStream os3 = connection3.getOutputStream();

			BufferedReader reader3 = new BufferedReader(new InputStreamReader(connection3.getInputStream(),"UTF-8"));
			String lines3;
			StringBuffer sbf3 = new StringBuffer();
			while ((lines3 = reader3.readLine()) != null) {
				lines3 = new String(lines3.getBytes(), "utf-8");
				sbf3.append(lines3);
			}
			System.out.println("sbf3: "+sbf3.toString());
			
			
			JSONObject object = JSONObject.fromObject(sbf3.toString());
			String openid = object.getString("openid");
			String nickname = object.getString("nickname");
			int sex = object.getInt("sex");
			String province = object.getString("province");
			String city = object.getString("city");
			String country = object.getString("country");
			String headimgurl = object.getString("headimgurl");
			String privilege = object.getString("privilege");
			String language = object.getString("language");
			
			session.setAttribute("openid",object.getString("openid"));
			session.setAttribute("nickname",object.getString("nickname"));
			session.setAttribute("sex", object.getInt("sex"));
			session.setAttribute("province",object.getString("province"));
			session.setAttribute("city",object.getString("city"));
			session.setAttribute("country",object.getString("country"));
			session.setAttribute("headimgurl",object.getString("headimgurl"));
			session.setAttribute("privilege",object.getString("privilege"));
			session.setAttribute("language",object.getString("language"));

			System.out.println(nickname+sex+province+city+country+headimgurl+privilege+language);		
		}
		
		/* String singAccess_token = ""; 
		{ 
			String accessURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx32c7f58bed37cf1f&secret=52e4def5eb3b8d36defe10af7dad4398";

			URL url5 = new URL(accessURL); //url地址
			HttpURLConnection connection5 = (HttpURLConnection) url5
					.openConnection();
			connection5.setDoInput(true);
			connection5.setDoOutput(true);
			connection5.setRequestMethod("GET");
			connection5.setUseCaches(false);
			connection5.setInstanceFollowRedirects(true);
			connection5.setRequestProperty("Content-Type",
					"application/json");
			connection5.connect();
			OutputStream os5 = connection5.getOutputStream();

			BufferedReader reader5 = new BufferedReader(new InputStreamReader(connection5.getInputStream(),"UTF-8"));
			String lines5;
			StringBuffer sbf5 = new StringBuffer();
			while ((lines5 = reader5.readLine()) != null) {
				lines5 = new String(lines5.getBytes(), "utf-8");
				sbf5.append(lines5);
			}
			System.out.println(sbf5.toString());
			JSONObject object = JSONObject.fromObject(sbf5.toString());
			singAccess_token = object.getString("access_token");
		 }
		
		{

			String localURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+singAccess_token+"&type=jsapi";

			URL url4 = new URL(localURL); //url地址
			HttpURLConnection connection4 = (HttpURLConnection) url4.openConnection();
			connection4.setDoInput(true);
			connection4.setDoOutput(true);
			connection4.setRequestMethod("GET");
			connection4.setUseCaches(false);
			connection4.setInstanceFollowRedirects(true);
			connection4.setRequestProperty("Content-Type","application/json");
			connection4.connect();
			OutputStream os4 = connection4.getOutputStream();

			BufferedReader reader4 = new BufferedReader(new InputStreamReader(connection4.getInputStream(),"UTF-8"));
			String lines4;
			StringBuffer sbf4 = new StringBuffer();
			while ((lines4 = reader4.readLine()) != null) {
				lines4 = new String(lines4.getBytes(), "utf-8");
				sbf4.append(lines4);
			}
			System.out.println(sbf4.toString());
			JSONObject object = JSONObject.fromObject(sbf4.toString());
		    jsapi_ticket = object.getString("ticket");
		  //获取签名signature
		    nonceStr = UUID.randomUUID().toString();
		    timestamp = Long.toString(System.currentTimeMillis() / 1000);
		    //获取请求url
		    //以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
	        String url = "http://localhost:8081/mvc_public/public/clientPage/";  
		    String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + nonceStr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
		    //sha1加密
		    signature = HttpXmlClient.SHA1(str);
		
		  } */
	} 
	
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />

<link href="http://at.alicdn.com/t/font_jvc3xp5ob1uac3di.css" rel="stylesheet" type="text/css" />
<style>
.item img {
	width: 100%;
}
</style>

</head>


<body>

	<!--top-->
	<div class="top_c">
		<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi" onclick="submitOrderInfoClick();">首页</p>
	</div>
	<!--banner-->
	<div style="height: 3em;"></div>
<div id="carousel-example-generic" class="carousel slide banner" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="img-responsive big" src="images/lbpic1.jpg">
    </div>
    <div class="item">
      <img class="img-responsive big" src="images/lbpic2.jpg">
    </div>
    <div class="item">
      <img class="img-responsive big" src="images/lbpic3.jpg">
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

	<!--hot-->



	<div class="gls_serve" style="color: #000">
		<div class="serve_left">
			<div class="cuirushi" style="text-align: right">
				<a href="cuirushi.jsp"> <img
					src="images/db/index_cuirushi_img.png"
					style="margin-top: 10px; float: left; width: 6.1em; height: 5em; margin-left: 10px">
					<img src="images/db/index_enter_pink.png"
					style="width: 1.5em; height: 1.5em; float: right; margin-top: 10px; margin-right: 10px"><br />
					<br /> <br /> <span
					style="margin-right: 10px; margin-top: 1.4em; font-size: 1.1em; color: #000">催乳师</span>
					<p style="margin-right: 10px; color: #999; font-size: 0.8em;">您的乳房管理专家</p>
				</a>

			</div>
			<div class="peihushi">
				<a href="peihushi.jsp">
					<div class="peihushi_left" style="width: 50%; float: left">
						<span
							style="margin-right: 10px; margin-top: 10px; margin-left: 10px; font-size: 1.1em; color: #000">陪护师</span>
						<ul style="color: #999; font-size: 0.8em">
							<li>健康养老</li>
							<li>健康养老</li>
							<li>健康养老</li>
						</ul>

					</div>
					<div class="peihushi_right" style="width: 50%; float: left;">
						<img src="images/db/index_nurse_img.png"
							style="margin-top: 0.714em; float: left; width: 3.4em; height: 6em; margin-left: 1.4em;">
						<img src="images/db/index_enter_yellow.png"
							style="width: 1.5em; height: 1.5em; float: right; margin-top: 10px; margin-right: 0.714em"><br />
						<br />
					</div>
				</a>
			</div>
		</div>

		<div class="yuesao">
			<a href="yuesao.jsp"> <img src="images/db/index_enter_bule.png"
				style="width: 1.5em; height: 1.5em; float: right; margin-top: 0.714em; margin-right: 0.714em"><br />
				<br /> <span
				style="margin-right: 0.714em; margin-top: 0.714em; font-size: 1.1em; margin-left: 0.714em; color: #000">专业月嫂</span>
				<p style="margin-left: 10px; color: #999; font-size: 0.8em">专业值得信赖</p>
				<img src="images/db/index_yuesao_img.png"
				style="height: 7em; margin-bottom: 0px; margin-left: 2.14em; margin-top: 20px">
			</a>
		</div>

	</div>

	<div class="product">
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
		<div class="sp_pr gls_index_dianjia_div">
			<a href="business.html"> <img src="images/gdsgf4.jpg"
				class="index_dianjia">
				<div class="text_p">
					<p>李记烤肉饭</p>
					<div class="xing">
						<span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
					</div>
					<span class="yue">月销量<em>1215</em>件
					</span> <span class="yue gls_index_bottom">起送价<em class="em_s">￥200</em>配送<em>￥20</em></span>
				</div>
				<div class="jul">107.25米</div>
			</a>
		</div>
	</div>

	<!--footer-->
	<div class="footer" style="text-align: center;">
		<div class="container" style="text-align: center;">
			<div class="row" style="text-align: center;">
				<div class="col-xs-3">
					<a href="index.jsp" class="dao"> <!--  <i class="iconfont icon-shouye-copy-copy-copy" style="color:#246fc0;"></i>-->
						<i class="iconfont"><img src="images/db/tab_home_hlight.png"
							style="width: 1.5em" /></i> <span class="nav_ti"
						style="color: #FF7979;">首页</span>
					</a>
				</div>
				<div class="col-xs-3">
					<a href="convenience.jsp" class="dao"> <!-- <i class="iconfont icon-quanbu"></i>-->
						<i class="iconfont" style="color: #246fc0;"><img
							src="images/db/tab_order.png" style="width: 1.5em" /></i> <span
						class="nav_ti">订单</span>
					</a>
				</div>
				<div class="col-xs-3">
					<a href="personal.jsp" class="dao"> <i class="iconfont"
						style="color: #246fc0;"><img src="images/db/tab_my.png"
							style="width: 1.5em" /></i> <!--<i class="iconfont icon-information"></i>-->
						<span class="nav_ti">我的</span>
					</a>
				</div>
			</div>
		</div>
	</div>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/iscroll.js"></script>
	<script>
	$(function(){
		var t_shu=$(".scroll a").width();
		var g_shu=$(".scroll a").length;
		var u_shu=g_shu*t_shu+g_shu*28+'px';
		$(".scroll").css({width:u_shu});
		var Scroll = new iScroll('wrapper',{hScrollbar:false, vScrollbar:false,hScroll:true,vScroll:false,});
	})
	
</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>
	<!--<script src="js/index_store_info.js" type="text/javascript"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  -->
<script type="text/javascript">
try{
	Window.onload=$.get("../../storeInfo/storlist",function(data){
		var storelist = "";
		for(var i=0;i<data.length;i++){	
			storelist += '<div class="sp_pr gls_index_dianjia_div"><a href="business.jsp?store_id='+data[i].storeId+'"><img src="'+data[i].storeUrl+'" class="index_dianjia"><div class="text_p">'
						+'<p>'+data[i].storeName+'</p>'
						+'<div class="xing"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></div>'
						+'<span class="yue">月销量<em>'+data[i].salesVolume+'</em>件</span>'
						+'<span class="yue gls_index_bottom">起送价<em class="em_s">￥'+data[i].minimum+'</em>配送<em>￥'+data[i].deliveryCost+'</em></span>'
						+'</div><div class="jul">'+data[i].distance+'米</div></a></div>';
		}
		$(".product").html(storelist);
	},"json");
}catch(e){
}


/*var appId ;
var timestamp;
var noncestr;
var signature;
function submitOrderInfoClick(){
	
 	var appId ;
	var timestamp;
	var noncestr;
	var signature;
	 $.get("../../localtion/signature",function(data){
		alert(data)

		
		appId = data.appId;
		timestamp = data.timestamp;
		noncestr = data.noncestr;
		signature = data.signature;
		alert(data);
	},"json") 
	    wx.config({
	        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	        appId: 'wx32c7f58bed37cf1f', // 必填，企业号的唯一标识，此处填写企业号corpid
	        timestamp: parseInt(timestamp,10), // 必填，生成签名的时间戳
	        nonceStr: noncestr, // 必填，生成签名的随机串
	        signature: signature,// 必填，签名，见附录1
	        jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    });
	   
	    wx.ready(function(){
	    });
		
	    wx.error(function(res){
	    });
	
  wx.getLocation({
	 
	    success: function (res) {
	    	alert("小宝鸽获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude + "）" );
        },
        fail: function(error) {
        	AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
        }
	}); 
}*/
</script>
</body>
</html>
