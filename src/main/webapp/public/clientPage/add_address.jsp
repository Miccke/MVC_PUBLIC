<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//String openid = request.getParameter("openid");
	String openid = "ort3e1XdNZ9tkdtup92pgUW0m3D8";
	System.out.println("add:"+openid);
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>添加收货地址</title>

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

<body>
<br/>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var openid = '<%=openid%>';
	$.post("../../userAddress/listAll",{openid:openid},function(data) {
							var listAddress = '';
							console.log(data)
							for(var i=0;i<data.length;i++){	
								listAddress += '<li style="margin-top:20px;float:left; width:100%" class="addressli" id="'+data[i].addId+'">'
										+ '<div style="width:70%; float:left">'
										+ '<span style="color:#333;display:inline-block; width:50%" id="userName">'
										+ data[i].userName
										+ '</span>'
										+ '<span style="display:inline-block; width:50%;color:#333" id="userPhone">'
										+ data[i].userPhone
										+ '</span><br>'
										+ '<span style="color:#999" id="userAddress">'
										+ data[i].userAddress
										+ '</span>'
										+ '</div>'
										+ '<div style="width:25%;float:right;margin-right:5%">'
										+ '<a href="javascript:void(0)" onClick="checkAddress();" style="color:#fe6e12"">选择</a>'
										+ '<a href="javascript:void(0)" style="margin-left:20px; color:#fe6e12" onClick="updateAddress();">修改</a>'
										+ '</div>' + '</li>';
							}
							$('.liDiv').html(listAddress);
						},"json")
</script>
	<form action="../../userAddress/insertAddress" method="post">
    	<div class="zhi">
        	<span style="margin-left:20px;">姓名</span>
            <input type="text" name="name" id="name" size="60" maxlength="60" style="color:#666"  placeholder="请输入收货人姓名">
        </div> 
        <div class="zhi">
        	<span style="margin-left:20px;">手机</span>
            <input type="text" name="phone" id="phone" size="60" maxlength="60" style="color:#666"  placeholder="请填写收货手机号">
        </div> 
        <div class="zhi">
        	<span style="margin-left:20px;">地址</span>
            <input type="text" name="address" id="address" size="60" maxlength="60" style="color:#666"  placeholder="请输入收货地址">
            <input name="openid" type="hidden" id="openid" value="<%=openid%>">
            <input name="addId" type="hidden" id="addId" value="">
        </div> 
        <div class="container" >
<!--             <a  href="javascript:void(0);" style="margin-top:3em;"><input type="submit" value="提交" class="deng"/></a> -->
            <a  href="javascript:void(0);" style="margin-top:3em;" onClick="submitAddress();" class="deng">提交</a>
        </div>
    </form>
    <div style="width:100%">
    	<ul class="liDiv">
    		
    	</ul>
    </div>
    
</body>
<script type="text/javascript">
	function submitAddress(){
		
		var name = $.trim($('#name').val());
		var phone = $.trim($('#phone').val());
		var address = $.trim($('#address').val());
		var addId = $.trim($('#addId').val());
		var openid = $.trim($('#openid').val());
		if(check(name,phone)){
			$.post("../../userAddress/insertAddress",{name:name,phone:phone,address:address,addId:addId,openid:openid},function(data){
				if(data == 0){
					alert('保存地址失败，请联系管理员');
				}
				else{
					alert('地址提交成功')
					location.href='submitOrder.jsp?addId='+data;
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

<script type="text/javascript">
	function checkAddress(){
		var obj = document.getElementsByClassName('addressli');
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = function() {
				var addId = this.getAttribute('id');
				location.href='submitOrder.jsp?addId='+addId;
			}
		} 
	}
	function updateAddress(){
		var obj = document.getElementsByClassName('addressli');
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = function() {
				var addId = this.getAttribute('id')
				var name = this.getElementsByTagName("span")[0].innerHTML;
				var phone = this.getElementsByTagName("span")[1].innerHTML;
				var address = this.getElementsByTagName("span")[2].innerHTML; 
				
				document.getElementById("name").value = name;
				document.getElementById("phone").value = phone;
				document.getElementById("address").value = address;	
				document.getElementById("addId").value = addId;	
			}
		}       
	}
</script>
</html>

 