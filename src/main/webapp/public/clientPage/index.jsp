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
	String nickname = "";
	String province = "";
	String headimgurl = "";
	String jsapi_ticket = "";
	String signature = "";
	String timestamp = "";
	String nonceStr = "";

	String openid = "";
	if (session.getAttribute("openid") != null) {
		openid = session.getAttribute("openid").toString();
		nickname = session.getAttribute("nickname").toString();
		int sex = Integer.parseInt(session.getAttribute("sex")
				.toString());
		province = session.getAttribute("province").toString();
		String city = session.getAttribute("city").toString();
		String country = session.getAttribute("country").toString();
		headimgurl = session.getAttribute("headimgurl").toString();
		String privilege = session.getAttribute("privilege").toString();
		String language = session.getAttribute("language").toString();

		System.out.println("1111111111111111111111111111111"+openid);
	} else{
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
	
				HttpMethod method = new GetMethod(wxUrl);
				HttpClient httpclient = new HttpClient();		
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("--------------------------------------------");
				System.out.println("wxUrl result = " + result);
				
				
				 JSONObject object = JSONObject.fromObject(result);
				 /*if(result.indexOf("errcode") >= 0){
			 		response.sendRedirect("../clientPage/index.jsp");
			 		return;
				} */
				access_token = object.getString("access_token");
				refresh_token = object.getString("refresh_token");
				openid = object.getString("openid");
				System.out.println(access_token + " " + refresh_token + " "
						+ openid);
			}
			String refresh_token1 = "";
			{
				String scopeUrl = "https:/"
						+ "/api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx32c7f58bed37cf1f&grant_type=refresh_token&refresh_token="
						+ refresh_token;
				HttpMethod method = new GetMethod(scopeUrl);
				HttpClient httpclient = new HttpClient();		
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("scopeUrl result = " + result);
				JSONObject object1 = JSONObject.fromObject(result.toString());
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
	
				HttpMethod method = new GetMethod(access_tokenUrl);
				HttpClient httpclient = new HttpClient();		
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("access_tokenUrl result = " + result);
				JSONObject object2 = JSONObject.fromObject(result.toString());
				access_token2 = object2.getString("access_token");
				refresh_token2 = object2.getString("refresh_token");
				openid2 = object2.getString("openid");
			}
			{
				String userInfoURL = "https://api.weixin.qq.com/sns/userinfo?access_token="
						+ access_token2 + "&openid=" + openid2 + "&lang=zh_CN ";			
				HttpMethod method = new GetMethod(userInfoURL);
				HttpClient httpclient = new HttpClient();
				
				try {
					httpclient.executeMethod(method);
					String result = new String(method.getResponseBody(), "utf-8");
					System.out.println("getWeiXinUserInfo result = " + result);
					JSONObject object = JSONObject.fromObject(result.toString());
					openid = object.getString("openid");
					nickname = object.getString("nickname");
					int sex = object.getInt("sex");
					province = object.getString("province");
					String city = object.getString("city");
					String country = object.getString("country");
					headimgurl = object.getString("headimgurl");
					String privilege = object.getString("privilege");
					String language = object.getString("language");
					
					session.setAttribute("openid",object.getString("openid"));
					session.setAttribute("nickname",object.getString("nickname"));
					session.setAttribute("sex", object.getInt("sex"));
					session.setAttribute("province",object.getString("province"));
					session.setAttribute("city",object.getString("city"));
					session.setAttribute("country",object.getString("country"));
					session.setAttribute("headimgurl",object.getString("headimgurl"));
					if(object.getString("headimgurl").equals("/0")){
						session.setAttribute("headimgurl","images/logot.png");
					}else{
						session.setAttribute("headimgurl",object.getString("headimgurl"));
					}
					session.setAttribute("privilege",object.getString("privilege"));
					session.setAttribute("language",object.getString("language"));
	
					System.out.println(nickname+sex+province+city+country+headimgurl+privilege+language);		
				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} 
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
<title style="text-align:center">首页</title>
<style>
.item img {
	width: 100%;
}
</style>

</head>


<body>

	<!--top
	<div class="top_c">
		<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi" onclick="submitOrderInfoClick();">首页</p>
	</div>-->
<div class="top">
	<div class="container">
    	<div class="row">
            <div class="col-xs-2" style="float:right">
            	<a href="chatting.jsp">
                	<img alt="" src="images/zixun.png" width="30px" height="30px" style="margin-top:20px;">
                	<!-- <span icon-zixun"></span> -->
                </a>
            </div>
        </div>
    </div>
</div>
	<!--banner-->
	<div style="height: 0.714em;"></div>
<div id="carousel-example-generic" class="carousel slide banner" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox" style="width:97%;margin-left:1.5%">
    <div class="item active">
      <a href="peihushi_info.html"><img class="img-responsive big" src="images/lbpic1.jpg"></a>
    </div>
    <div class="item">
      <a href="yuesao_info.html"><img class="img-responsive big" src="images/lbpic2.jpg"></a>
    </div>
    <div class="item">
      <a href="cuirushi_info.html"><img class="img-responsive big" src="images/lbpic3.jpg"></a>
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
		<div style="width:97%;margin-left:1.5%">
			<div class="serve_left">
				<div class="cuirushi" style="text-align: right">
					<a href="serve_select.jsp?waiterType=2"> <img
						src="images/db/index_cuirushi_img.png"
						style="margin-top: 10px; float: left; width: 6.1em; height: 5em; margin-left: 10px">
						<br />
						<br /> <br /> <span
						style="margin-right: 10px; margin-top: 1.4em; font-size: 1.1em; color: #000">催乳师</span>
						<p style="margin-right: 10px; color: #999; font-size: 0.8em;">您的乳房管理专家</p>
					</a>
				</div>
				<div class="peihushi">
					<a href="serve_select.jsp?waiterType=3">
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
							<br />
							<br />
						</div>
					</a>
				</div>
			</div>
	
			<div class="yuesao">
				<a href="serve_select.jsp?waiterType=1"><br />
					<br /> <span
					style="margin-right: 0.714em; margin-top: 0.714em; font-size: 1.1em; margin-left: 0.714em; color: #000">专业月嫂</span>
					<p style="margin-left: 10px; color: #999; font-size: 0.8em">专业值得信赖</p>
					<img src="images/db/index_yuesao_img.png"
					style="height: 7em; margin-bottom: 0px; margin-left: 2.14em; margin-top: 20px">
				</a>
			</div>
		</div>
		
		<div style="width:97%; margin-left:1.5%">
  
		    <div style="float:left; width:100%; background:#ffead5; height:150px; margin-top:0.714em;margin-right:50px;">
				<a href="storelist.jsp">
			    	<div style="width:50%; float:left; margin-top:2em;">
			          <span style="font-size:1.1em; color:#000; margin-left:1.3em;">营养餐</span><br>
			                <span style="font-size:0.8em; color:#999; margin-left:2em;">营养全面，食品安全</span><br>
			                <span style="font-size:0.8em; color:#999; margin-left:2em;">吃的放心，吃的健康</span>
			        </div>      
			        <div  style="width:50%; float:left">
			                <img src="images/db/index_dinner_img.png" style="margin-top:0.714em ; float:left; width:10.3em; height:8em;"/>
			            <br/><br/>
			        </div>
			    </a>
			</div>
		</div>
	</div>
<div style="height:5em; float:left; width:100%"></div>

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
</body>
</html>
