<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Welcome ZT starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
		  /* function login() {
				var username = $('#username').val();
				var password = $('#password').val();
				if (username == "") {
					alert("账号不能为空");
					return false;
				}
				if (password == "" && username != "") {
					alert("请输入密码");
					return false;
				}
				$.post("user/login", {
					loginName : username,
					passWord : password
				}, function(data) {
					debugger
					if (data) {
						location.href = "login/main.jsp";
					} else {
						alert("账号或密码错误");
					}
				}, "json");
			} */
 </script>
 
 <style type="text/css">
 	body{
 		background-color: #FFFFFF;
 		text-align: center; 
 	}
 	.formDIv{
 		margin: 0 auto; 
 		position: absolute;
 		overflow: visible; 
 		/* position: fixed;  */
 		margin-top: 155px; 
 		/* float: right; */ 
 		margin-left: 830px;
 		font-size: 28px;
		color: #343434;
		font-family: "Microsoft YaHei";
		font-weight: bold;
 	}
 	.inputText{
 		border-left-width: 0px; 
	 	border-top-width: 0px;
	 	border-right-width: 0px; 
	 	border-bottom-color: black; 
	 	line-height: 35px; width: 300px;
	 	font-size: 20px;
	 	color: #9d98b0;
		font-family: "Microsoft YaHei";
 	}
 	.inputSubmit{
 		background:url(image/loginBtn.png);
 		width: 400px;
 		height: 65px;
 		font-size: 20px;
 		border: none;
 		color: white;
 	}
 </style>
</head>
<body>
	<div style="height: 140px;min-width: 1300px">
			<img alt="" width="40" height="40" style="margin-right: 1000px;margin-top: 50px;" src="image/ty-3.png">
			<img alt="" width="41" height="41" style="margin-left:1100px;" src="image/ty-2.png">
	</div>
	
	<div style="margin: 0 auto;width:1300px;background-image:url(image/backImage.png); height: 650px;">
		<div class="formDiv">
			<p>信伊健康医家后台管理登录</p>
			<font color="red" style=" font-size: 21px;">${requestScope.message }</font>
			
			<form method="post" action="user/login">
				<div>
					<img alt="" src="image/user.png" width="18" height="22">
					<input type="text" id="loginName" name="loginName" value="请输入用户名" maxlength="16" onfocus="if(this.value == '请输入用户名') this.value =''"  class="inputText"/>
				</div>
				<div style="margin-top: 55px;">
					<img alt="" src="image/pwd.png" width="18" height="22">
					<input type="password" name="passWord" id="passWord" maxlength="20" class="inputText" >
				</div>
				<div style=" margin-top: 70px; ">
					<input type="submit" value="登录" class="inputSubmit">
				</div>
			</form>
		</div>
	</div>
	
	<div style="height:100px;min-width: 1300px">
		<img alt="" width="40" height="40" style="margin-left:800px;" src="image/ty-1.png">
	</div>
</body>
</html>
