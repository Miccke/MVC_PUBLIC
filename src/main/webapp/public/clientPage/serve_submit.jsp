<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String openid = session.getAttribute("openid").toString();
	String openid = "ort3e1RebXo8REJmiJVuzseG9F3Y";
	//String waiterType = request.getParameter("waiterType").toString();
	//String waiterNo = request.getParameter("waiterNo").toString();
	String waiterType = "1";
	String timeStirng = "";
	if(waiterType.equals("1")){
		timeStirng = "预产期";
	}else if(waiterType.equals("2")){
		timeStirng = "到岗日期";
	}else if(waiterType.equals("2")){
		timeStirng = "到岗日期";
	}
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="user-scalable=no,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1" />

<link rel="stylesheet" href="css/calendar.css">
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/demo.css">

<title>提交信息</title>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/JQRadio.js"></script>
<script src="js/jsRequestParam.js" type="text/javascript"></script>
<script>
	$(function() {
		$("#radio01").JQRadio({});
	});
</script>
</head>

<body>


	<div class="person_info"
		style=" float: left; width: 100%">

		<div style="width: 60%; float: left">
			<div style="float: left; margin-top: 20px;">
				<span style="font-size: 16px; color: gray; margin-left: 1em">姓名</span>
				<input type="text" placeholder="请输入姓名" class="waiterName"
					style="-webkit-appearance: none; border: none; margin-left: 40px; font-size: 16px; width:100px;">
			</div>
		</div>
		<div class="radio_box" id="radio01" style="float: right; width: 40%;">
			<!--隐藏表单 radioVal 的值即为选中项li的id值-->
			<input type="hidden" value="" class="radioVal" name="" />
			<ul>
				<li id="r1" class="selected" value="0">先生</li>
				<li id="r2" value="1">女士</li>
			</ul>
		</div>
		<br> <br> <br> <br> <br>
		<p style="border-bottom: 1px #E1E1E1 solid; width: 100%"></p>

		<p class="write">
			<span style="font-size: 16px; color: gray; margin-left: 1em">手机号</span>
			<input type="text" placeholder="请输入手机号" class="waiterPhone"
				style="-webkit-appearance: none; border: none; margin-left: 25px; font-size: 16px;">
		</p>
		<p style="border-bottom: 1px #E1E1E1 solid; width: 100%"></p>
		<p class="write">
			<span style="font-size: 16px; color: gray; margin-left: 1em"><%=timeStirng%></span>
			<span onClick="showTime();" class="timeshow"
				style="margin-left: 25px; font-size: 16px;">2017-08-12</span>
		</p>
		<p style="border-bottom: 1px #E1E1E1 solid; width: 100%"></p>
		<p class="write">
			<span style="font-size: 16px; color: gray; margin-left: 1em">备注</span>
			<input type="text" placeholder="备注信息..." class="remark"
				style="-webkit-appearance: none; border: none; margin-left: 40px; font-size: 16px;">
		</p>
		<p style="border-bottom: 1px #E1E1E1 solid; width: 100%"></p>
	</div>
	<div class="window changeTime" style="display: none">
		<div id="calendar" class="calendar"></div>
	</div>
	<div
		style="position: fixed; bottom: 0; width: 100%; text-align: center;"
		onClick="serve_order_submit();">
		<img src="images/cuirushi/button_submit.png" width="100%" />
	</div>
</body>
</html>
<script src="js/calendar.js" type="text/javascript"></script>
<script type="text/javascript">
  	function showTime(){
		$('.changeTime').css('display','block');
	}
</script>
<script type="text/javascript">
		function check_li(a) {
			if (a.childNodes[1].getAttribute("src", 2) == "cr_requirement_nochoose.png") {
				a.childNodes[1].src = "cr_requirement_choose.png";
				a.childNodes[0].checked = true;
			} else {
				a.childNodes[1].src = "cr_requirement_nochoose.png";
				a.childNodes[0].checked = false;
			}
		}
	</script>
<script type="text/javascript">
	function show(){		
		var a = document.getElementsByClassName('selected')[0].innerText;
		alert(a)
	}
	
	function serve_order_submit(){			
		var dataObject = new  Object();
		
		var request=new UrlSearch(); //实例化
		var waiterNo = request.waiterNo;
		
		dataObject.waiterType = '<%=waiterType%>';
		dataObject.userName = $.trim( $('.waiterName').val());
		dataObject.userSex = $.trim($('.selected').val());
		dataObject.userPhone = $.trim( $('.waiterPhone').val());
		dataObject.startTime = document.getElementsByClassName('timeshow')[0].innerText ;
		dataObject.openid = '<%=openid%>';
		dataObject.waiterNo = waiterNo;
		dataObject.remark = $.trim( $('.remark').val());
		if(check(dataObject.userName,dataObject.userPhone)){
			$.ajax({
				url : "../../sWaiterOrder/insert",
				data : {"dataObject":JSON.stringify(dataObject)},
				type  : 'post',
				async : true,
				success : function(result) {
					location.href='convenience.jsp'; 	
				}
			});
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

