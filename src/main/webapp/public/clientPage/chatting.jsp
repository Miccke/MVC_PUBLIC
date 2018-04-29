<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openid = request.getParameter("openid");
	//String openid = "ort3e1XdNZ9tkdtup92pgUW0m3D8";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>咨询专页</title>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<style>
body {
	background: #f3f3f3
}

.zhi input {
	width: 70%;
	margin-left: 20px;
}

.zhi {
	border-bottom: 1px solid #ececec;
	margin-top:10px;
}

.zhi span {
	float: left;
}

.form-group {
	width: 70%;
	float: left;
	margin-bottom: 0;
}

.form-control {
	border: none;
	box-shadow: none;
	padding: 0;
	color: #666;
}
</style>
</head>

<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<body>
	<h3 style="width:100%; background:#fe6e12; color:#FABDA3; padding:10px 0 10px 10px" >信伊健康服务咨询</h3>
	<form action="../../userAddress/insertAddress" method="post">
    	<div class="zhi">
        	<span style="margin-left:20px;">姓名</span>
            <input type="text" name="name" id="name" size="60" maxlength="60" style="color:#666"  placeholder="请输入咨询人姓名">
        </div> 
        <div class="zhi">
        	<span style="margin-left:20px;">手机</span>
            <input type="text" name="phone" id="phone" size="60" maxlength="60" style="color:#666"  placeholder="请填写咨询人手机号">
        </div> 
        <div class="zhi">
        	<span style="margin-left:20px;">咨询内容</span>
            <textarea name="content" id="content" style="color:#666;width:70%" placeholder="请输入咨询人"></textarea>
            <input name="openid" type="hidden" id="openid" value="<%=openid%>">
        </div> 
        <div class="container">
            <a  href="javascript:void(0);" style="margin-top:3em;" onClick="submitAddress();" class="deng">提交</a>
        </div>
    </form>
</body>
<script type="text/javascript">
	function submitAddress(){
		var userName = $.trim($('#name').val());
		var userPhone = $.trim($('#phone').val());
		var chattingContent = $.trim($('#content').val());
		var openId = $.trim($('#openid').val());
	    if(check(userName,userPhone)){
			$.post("../../chatting/insert",{userName:userName,userPhone:userPhone,chattingContent:chattingContent,openId:openId},function(data){
				if(data == 0){
					alert('系统问题，请联系管理员');
				}
				else{
					alert('咨询意见提交成功')
					location.href='index.jsp';
				}
			},"json")
		} 
	}
	
	function  check(name,phone) {
	    var re = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	    var reg=/^[\u0391-\uFFE5]+$/; 
	    if (!re.test(phone)&&reg.test(name)) {
	        alert('手机号码填写不规范');	     
	        return false;
	    } 
	    if (re.test(phone)&&!reg.test(name)) {
	        alert('姓名填写不规范');	 
	        return false;
	    }
	    if (!re.test(phone)&&!reg.test(name)) {
	        alert('姓名和手机号码填写不规范');
	        return false;
	    }
	    return true;
	}
</script>

</html>

 