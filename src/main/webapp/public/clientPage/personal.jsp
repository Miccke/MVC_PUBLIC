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
			try {
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("result = " + result);
				JSONObject object = JSONObject.fromObject(result);
				access_token = object.getString("access_token");
				refresh_token = object.getString("refresh_token");
				openid = object.getString("openid");
				System.out.println(access_token + " " + refresh_token + " "+ openid);
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String refresh_token1 = "";
		{
			String scopeUrl = "https:/"
					+ "/api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx32c7f58bed37cf1f&grant_type=refresh_token&refresh_token="
					+ refresh_token;
			HttpMethod method = new GetMethod(scopeUrl);
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("result = " + result);
				JSONObject object1 = JSONObject.fromObject(result.toString());
				String access_token1 = object1.getString("access_token");
				refresh_token1 = object1.getString("refresh_token");
				String openid1 = object1.getString("openid");
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			try {
				httpclient.executeMethod(method);
				String result = new String(method.getResponseBody(), "utf-8");
				System.out.println("result = " + result);
				JSONObject object2 = JSONObject.fromObject(result.toString());
				access_token2 = object2.getString("access_token");
				refresh_token2 = object2.getString("refresh_token");
				openid2 = object2.getString("openid");
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
				if(object.getString("headimgurl").equals("/0")){
					session.setAttribute("headimgurl","images/logot.png");
				}else{
					session.setAttribute("headimgurl",object.getString("headimgurl"));
				}	
				System.out.println(session.getAttribute("headimgurl").toString());
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
	
	if(session.getAttribute("openid").toString()!= null){//说明是由index界面跳转至
		openid = session.getAttribute("openid").toString();
		nickname = session.getAttribute("nickname").toString(); 
		int sex = Integer.parseInt(session.getAttribute("sex").toString()); 
		province = session.getAttribute("province").toString(); 
		String city = session.getAttribute("city").toString(); 
		String country = session.getAttribute("country").toString(); 
		headimgurl = session.getAttribute("headimgurl").toString(); 
		String privilege = session.getAttribute("privilege").toString(); 
		String language = session.getAttribute("language").toString(); 
		System.out.println(nickname+sex+province+city+country+headimgurl+privilege+language);	
		
	}else{
		System.out.println("khkhkjhhjkhkjjjjjjjjjjjjjj");
	}
	
}
%>    
    
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />

<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css" rel="stylesheet" type="text/css" />
<title>我的</title>

</head>

<body style="background-color:#f3f3f3">

<!--top
<div class="top_c">
	<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">我的</p>
</div>-->
<!--头部-->
<div class="header_gls"> 
<div style="height:2.7em;"></div>
	<a href="./personal.jsp">
     	<img src="<%=headimgurl%>" style="border-radius:2.5em; width:5em; height:5em;">
        <p style="color:#000;font-size:16px;"><%=nickname%></p>
    </a>
</div>

<!--列表-->
<div style="margin-top:5px;">
		<div style="background-color:#FFF; height:4em;  padding-left:1em; padding-right:1em; padding-top:0.8em">
			<a href="convenience_person.jsp">
				<p class="biao"><img src="images/db/my_dinner_icon.png" style="width:1.5em; margin-right:1em"/>我的订餐<i class="iconfont icon-jiantou"></i></p>
			</a>
		</div>
        <div style="background-color:#FFF; height:4em;  padding-left:1em; padding-right:1em; padding-top:0.8em; margin-top:1px">
			<a href="my_server.jsp">
				<p class="biao"><img src="images/db/my_server_icon.png" style="width:1.5em; margin-right:1em"/>我的服务<i class="iconfont icon-jiantou"></i></p>
			</a>
		</div>
</div>

<!--退出登录-->
<div lass="lie_b">
	<div class="one" style="background-color:white;margin:0.716em 0 0 0">  
			<p onclick="WeixinJSBridge.call('closeWindow');" class="exit" style="text-align:center;padding-top:10px;color:black;font-size:1.15em; font-family:'微软雅黑'; line-height:2.5em">退出登陆</p>
		
	</div>
</div>
<!--footer-->
<div class="footer"  style="text-align:center;">
	<div class="container"  style="text-align:center;">
        <div class="row"  style="text-align:center;">
            <div class="col-xs-3">
                <a href="index.jsp" class="dao">
                  <!--  <i class="iconfont icon-shouye-copy-copy-copy" style="color:#246fc0;"></i>-->
                    <i class="iconfont"><img src="images/db/tab_home.png" style="width:1.5em"/></i>
                    <span class="nav_ti">首页</span>
                </a>
            </div>
            <div class="col-xs-3">
                <a href="convenience.jsp" class="dao">
                   <!-- <i class="iconfont icon-quanbu"></i>-->
                   <i class="iconfont" style="color:#246fc0;"><img src="images/db/tab_order.png" style="width:1.5em"/></i>
                    <span class="nav_ti">订单</span>
                </a>
            </div>
            <div class="col-xs-3">
                <a href="personal.jsp" class="dao">
                	<i class="iconfont" style="color:#246fc0;"><img src="images/db/tab_my_hlight.png" style="width:1.5em"/></i>
                    <!--<i class="iconfont icon-information"></i>-->
                    <span class="nav_ti"  style="color:#FF7979;">我的</span>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
<script type="text/javascript">
/* 	$('.exit').click(function(){
		$.get("../../public/clientPage/getPage",function(data){
			location.href='index.jsp';
		})
	}) */
</script>		
</body>
</html>
