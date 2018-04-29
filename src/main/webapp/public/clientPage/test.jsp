<%@page import="com.zxpublic.util.HttpXmlClient"%>
<%@page import="java.util.UUID"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

String wxUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx32c7f58bed37cf1f&secret=52e4def5eb3b8d36defe10af7dad4398";
String access_token="";
{
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
	
	access_token = object.getString("access_token");
}
String ticketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi"; 
String jsapi_ticket = "";
{
	URL url1 = new URL(ticketURL); //url地址
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
	
	jsapi_ticket = object1.getString("ticket");
}

//获取签名signature
String nonceStr = UUID.randomUUID().toString();
String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//获取请求url
//以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
String url2 = "http://localhost:8081/mvc_public/public/clientPage/";  
String str = "jsapi_ticket=" + jsapi_ticket +
        "&nonceStr=" + nonceStr +
        "&timestamp=" + timestamp +
        "&url=" + url2;

System.out.println(str);
//sha1加密
String signature = HttpXmlClient.SHA1(str);

System.out.println("signature: "+signature);

System.out.println("11111111111111111111111111");




%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p onclick="submitOrderInfoClick();">asdassad</p>
</body>
</html>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">
function submitOrderInfoClick(){
	/* var appId ;
	var timestamp;
	var nonceStr;
	var signature;
	 $.get("../../localtion/signature",function(data){
		alert(data)
		appId = data.appId;
		timestamp = data.timestamp;
		nonceStr = data.nonceStr;
		signature = data.signature;
		alert(data);
	},"json")  */
	    wx.config({
	        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	        appId: 'wx32c7f58bed37cf1f', // 必填，企业号的唯一标识，此处填写企业号corpid
	        timestamp: parseInt(<%=timestamp%>,10), // 必填，生成签名的时间戳
	        nonceStr: <%=nonceStr%>, // 必填，生成签名的随机串
	        signature: <%=signature%>,// 必填，签名，见附录1
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
}
</script>