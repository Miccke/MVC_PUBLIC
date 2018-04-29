<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String waiterType = request.getParameter("waiterType").toString();
	System.out.println(waiterType);
	String typeName = "";
	if(waiterType.equals("1")){
		typeName = "月嫂";
	}
	if(waiterType.equals("2")){
		typeName = "催乳师";
	}
	if(waiterType.equals("3")){
		typeName = "陪护师";
	}
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1"/>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="http://at.alicdn.com/t/font_jvc3xp5ob1uac3di.css" rel="stylesheet" type="text/css" />

<title><%=typeName%>服务</title>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>

<script type="text/javascript">
	window.onload=$.get("../../swaiter/swaiterlist",{waiterType:<%=waiterType%>},function(data){
		var servelist = "";
		for(var i=0;i<data.length;i++){
			servelist +='<div class="server_list"> '
				+'<div class="list_content">'
				+'<a href="../../swaiter/swaiterMessage?waiterNo='+data[i].waiterNo+'&waiterType=<%=waiterType%>">'
				+'<img class="header_img_serve" src="../../imageShow?fileId='+data[i].waiterImageurl+'" style="width:5.5em;height:5.5em"/>'
				+'<div class="content_text">'
				+'<p style="padding-top:15px;"><span style="font-size:16px; color:#000">'+data[i].waiterName+'</span><span style="padding-left:30px;">'+data[i].jobTitle+'</span></p>'
				+'<p style="color: #ED7779"><span style="font-size:18px;">'+data[i].waiterPrice+'</span>元/26天</p>'
				+'<p class="jian" style="margin-top:10px;"><span>'+data[i].waiterAge+'岁</span><span style="margin-left:10px;margin-right:10px">|</span><span>'+data[i].waiterNativeplace+'人</span><span style="margin-left:10px;margin-right:10px">|</span><span><span>带过'+data[i].waiterBabycount+'个宝宝</span></p>	'	
				+'</div>'
				+'</a>'
				+'</div>'
				+'</div>';
		}
		$('.showServelist').html(servelist);
	},"json")
</script>
</head>

<body>
<!--
<div class="top_c" style="background: #FFFFFF">
	<a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">服务</p>
</div>
<div style="margin-top: 3em"></div>-->
<div class="showServelist">
</div>
</body>
</html>


