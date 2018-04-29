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

<title>营养餐厅列表</title>
</head>
<body>
<!--
<div class="top_c" style="background: #FFFFFF">
	<a href="index.jsp " class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">营养餐厅列表</p>
</div>
<div style="margin-top: 3.8em"></div>
-->
<div class="product">

</div>
<!-- <button id="getBBS" style="width:1000px;height:600px;font-size:150px;" onclick="submitOrderInfoClick();">获取地理位置</button>
 --></body>
<script src="js/jweixin-1.2.0.js"></script>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script>
window.onload = function(){
	function temp(){
		var value = '';  
	    $.ajax({  
	        url: "../../localtion/signature",  
	        async:false, 
	        dataType:'json',
	        success: function (data) {  
	        	value = data;
	        	//console.log(value)
	        }  
	    });  
	    return value;  
	};
	var obj = temp();
	console.log(obj)
	
	//注入配置config接口
	wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: obj.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
	    timestamp: parseInt(obj.timestamp,10), // 必填，生成签名的时间戳
	    nonceStr: obj.nonceStr, // 必填，生成签名的随机串
	    signature: obj.signature,// 必填，签名，见附录1
	    jsApiList: ['checkJsApi','openLocation','getNetworkType','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	
	wx.ready(function(){
		wx.getLocation({
		    timestamp: 0, // 位置签名时间戳，仅当需要兼容6.0.2版本之前时提供
		    nonceStr: '', // 位置签名随机串，仅当需要兼容6.0.2版本之前时提供
		    addrSign: '', // 位置签名，仅当需要兼容6.0.2版本之前时提供，详见附录4
		    success: function (res) {
		    	alert(1)
		        var longitude = res.longitude; // 纬度，浮点数，范围为90 ~ -90
		        var latitude = res.latitude; // 经度，浮点数，范围为180 ~ -180。
		        var speed = res.speed; // 速度，以米/每秒计
		        var accuracy = res.accuracy; // 位置精度
		        console.log('*******************************************************************************');
				console.log('纬度'+longitude +' 经度'+ latitude);
				$.get("../../storeInfo/storlist",{longitude:longitude,latitude:latitude},function(data){
					
					var storelist = "";
					for(var i=0;i<data.length;i++){	
					//	storelist += '<div class="sp_pr gls_index_dianjia_div"><a href="business.jsp?store_id='+data[i].storeId+'"><img src="'+data[i].storeUrl+'" class="index_dianjia"><div class="text_p">'
						storelist += '<div class="sp_pr gls_index_dianjia_div"><a href="business.jsp?store_id='+data[i].storeId+'"><img src="../../imageShow?fileId='+data[i].storeUrl+'" class="index_dianjia"><div class="text_p">'
									+'<p>'+data[i].storeName+'</p>'
									+'<div class="xing"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></div>'
									+'<span class="yue">月销量<em>'+data[i].salesVolume+'</em>件</span>'
									+'<span class="yue gls_index_bottom">起送价<em class="em_s">￥'+data[i].minimum+'</em>配送<em>￥'+data[i].deliveryCost+'</em></span>'
									+'</div><div class="jul">'+data[i].distance+'米</div></a></div>';
					}
					$(".product").html(storelist);
				},"json");
		    }
		});
		 
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});
	
	wx.error(function(res){
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	
	
	//获取地理位置接口
	

}

</script>
</html>

