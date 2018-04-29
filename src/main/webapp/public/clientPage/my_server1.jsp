<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1"/>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script type="text/javascript">
try{
	window.onload = function() {
		$.ajaxSetup({
			async : false
		});
		var server_list = "";
		$.get("../../serveInfo/serverlist",function(data){
			
			for(var i=0;i<data.length;i++){
				server_list +='<div class="server_list">'
							+'<div style="height: 4em">'
							+'<img class="server_img" src="images/score_star_yellow.png"/>'
							+'<span class="time">2018.01.01 - 2019.01.01 (2个月)</span>'
							+'</div>'
							+'<div style="border-bottom: 1px solid #eeeeee; width: 90%;margin-left: 5%;"></div>'
							+'<div class="list_content">'
							+'<img class="header_img" src="images/index_nurse_img.png"/>'
							+'<div class="content_text">'
							+'<p style="padding-top: 10px;font-size: 16px">月嫂姓名</p>'
							+'<p class="jian" style="margin: -8px 0 5px 0">年龄：42&nbsp;任职：2年</p>'
							+'<p style="font-size: 15px;color: #ED7779">12000元/月</p>'
							+'</div>'
							+'</div>'
							+'</div>';
			}
			$(".server_list_div").html(server_list);
		},"json")
	}
}catch(e){
	alert(e.toString())
}
</script>


<div class="top_c" style="background: #FFFFFF">
	<a href="personal.jsp" class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">我的服务</p>
</div>
<div style="margin-top: 3em"></div>

<div class="server_list_div">

</div>
</body>
</html>
