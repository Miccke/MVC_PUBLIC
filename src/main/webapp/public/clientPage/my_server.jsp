<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//	String openid = session.getAttribute("openid").toString();
	String openid = "ort3e1XdNZ9tkdtup92pgUW0m3D8";
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1"/>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css" rel="stylesheet" type="text/css" />
<title>我的服务</title>
</head>
<body>
<!--
<div class="top_c" style="background: #FFFFFF">
	<a href="personal.jsp " class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">服务</p>
</div>-->
<div class="server_list_div">

</div>
</body>
</html>
<script src="js/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">
	 $.get("../../sWaiterOrder/serverlist",{openid:'<%=openid%>'},function(data){
		var server_list = "";
		for(var i=0;i<data.length;i++){
			var serveType = data[i].sWaiterMessage.waiterType;
			var serveMoney = '';
			if(serveType == 1){
				serveMoney = '26天';
			}
			if(serveType == 2){
				serveMoney = '单次';
			}
			if(serveType == 3){
				serveMoney = '天';
			}
			var startTo = new Date(data[i].startTime);
			var finishTo = new Date(data[i].finishTime);
			var start = getDateStr(startTo);
			var finish = getDateStr(finishTo);
			server_list +='<div class="server_list">'
						+'<div style="height: 4em">'
						+'<img class="server_img" src="images/score_star_yellow.png"/>'
						+'<span class="time">'+start+'--'+finish+'</span>'
						+'</div>'
						+'<div style="border-bottom: 1px solid #eeeeee; width: 90%;margin-left: 5%;"></div>'
						+'<div class="list_content">'
					 	+'<img class="header_img" src="../../imageShow?fileId='+data[i].sWaiterMessage.waiterImageurl+'"/>' 
						+'<div class="content_text">'
						+'<p style="padding-top: 10px;font-size: 16px">'+data[i].sWaiterMessage.waiterName+'</p>'
						+'<p class="jian" style="margin: -8px 0 5px 0">年龄：'+data[i].sWaiterMessage.waiterAge+'岁</p>'
						+'<p style="font-size: 15px;color: #ED7779">'+data[i].sWaiterMessage.waiterPrice+'元/'+serveMoney+'</p>'
						+'</div>'
						+'</div>'
						+'</div>';
		}
		$(".server_list_div").html(server_list);
	},"json") 
	
	/**
	 * 传入日期，返回字符串
	 * @param date
	 * @returns {String}
	 */
	this.getDateStr = function(date){
		if(date != null && date != ""){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var h = date.getHours(); 
		    var M = date.getMinutes(); 
		    var s = date.getSeconds();
		   
		    if(h<10){
		    	h = '0'+h;
		    }
		    if(M<10){
		    	M = '0'+M;
		    }
		    if(s<10){
		    	s = '0'+s;
		    }
		    
			var dateStr = y +'/'+ m +'/'+ d+' '+h+':'+M+':'+s;
			return dateStr;
		}else{
			return null;
		}
	}
	
</script>