<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>


<script>
function temp(){
	var value = '';  
    $.ajax({  
        url: "../localtion/signature",  
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
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: obj.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
    timestamp: parseInt(obj.timestamp,10), // 必填，生成签名的时间戳
    nonceStr: obj.nonceStr, // 必填，生成签名的随机串
    signature: obj.signature,// 必填，签名，见附录1
    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
 
    
/*     debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${appId}', // 必填，企业号的唯一标识，此处填写企业号corpid
    timestamp: parseInt("${timestamp}",10), // 必填，生成签名的时间戳
    nonceStr: '${noncestr}', // 必填，生成签名的随机串
    signature: '${signature}',// 必填，签名，见附录1
    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2 */
});
wx.ready(function(){
});

wx.error(function(res){
});
</script>
</head>
<body>
<button id="getBBS" style="width:1000px;height:600px;font-size:150px;" onclick="submitOrderInfoClick();">获取地理位置</button>
</body>
<script type="text/javascript">

function submitOrderInfoClick(){
	var lat = '';
	var longi = ''
  wx.getLocation({
	    success: function (res) {
	    	console.log(res)
        },
        fail: function(error) {
        	AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
        }
	});
}
</script>
</html>