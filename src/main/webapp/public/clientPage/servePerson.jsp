<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/" + "public/clientPage/";
%>	
<%
    String waiterType = request.getParameter("waiterType").toString();
	//String waiterType = "3";
	System.out.println("11111111111111111"+waiterType);
	String waiterNo = request.getParameter("waiterNo").toString();
	//String waiterNo = "No.0000000011";
	System.out.println("11111111111111111"+waiterNo);
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
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1"/>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="http://at.alicdn.com/t/font_jvc3xp5ob1uac3di.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.circliful.css" rel="stylesheet" type="text/css" />


<title>${sWaiterMessage.waiterName}的<%=typeName%>资料</title>
<style type="text/css">
	ul li{
		list-style:none;}
</style>

<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery.circliful.min.js" type="text/javascript"></script>
<script src="js/jsRequestParam.js" type="text/javascript"></script>

</head>
<body>
<!--
<div class="top_c" style="background: #FFFFFF; border:none; font-size:16px">
	<a href="serve.jsp?waiterType=<%=waiterType%>" class="iconfont icon-jiantou-copy-copy"></a>
	<p class="titi">${sWaiterMessage.waiterName}的月嫂资料</p>
</div>
<div style="margin-top: 3em"></div>-->
<div style="text-align:center; color:#FFF; background:url(images/yue_bg.png); width:100%; float:left; z-index:0">
	<!--<div style="border-radius:57.5px; background:#FFF; width:115px; height:115px; margin:0 auto; margin-top:16px;background: rgba(256,256,256,0.3)">
    	<img src="../../imageShow?fileId=${sWaiterMessage.waiterImageurl}" style="border-radius:54px; width:108px; height:108px; margin-top:3.5px;"/>
    </div>-->
	
	<div style="border-radius:47.5px; background:#FFF; width:95px; height:95px; margin:0 auto; margin-top:16px;background: rgba(256,256,256,0.3)">
    	<img src="../../imageShow?fileId=${sWaiterMessage.waiterImageurl}" style="border-radius:44px; width:88px; height:88; margin-top:3.1px;"/>
    </div>
	
    <p style="margin-top:10px;">
    	<span style="font-size:16px; font-family:'微软雅黑' ">${sWaiterMessage.waiterName}</span>
        <span style="margin-left:20px; font-size:14px;">${sWaiterMessage.waiterPrice}元</span><span>/</span><span style="font-size:12px;" id="servemoney"></span>
    </p>
    <p style="margin-top:10px;">
    	<span style="font-size:14px;">${sWaiterMessage.waiterAge}岁</span>
        <span style="margin-left:10px;">|</span>
        <span style="margin-left:10px;">${sWaiterMessage.waiterProvince}${sWaiterMessage.waiterCity}人</span>
        <span id="baby">
        	<span style="margin-left:10px;">|</span>
        	<span style="margin-left:10px;">带过${sWaiterMessage.waiterBabycount}个宝宝</span>
        </span>	
    </p>
    <p style="margin-top:10px;" class="characteristics">
    	<!-- <span style="border:1px #FFFFFF solid; height:20px; border-radius:10px;">&nbsp;&nbsp;双胞胎经验&nbsp;&nbsp;</span>
   		<span style="border:1px #FFFFFF solid; height:20px; border-radius:10px; margin-left:10px;">&nbsp;&nbsp;早产儿经验&nbsp;&nbsp;</span> -->
    </p>  
    <div style="margin-top:10px;background-color: rgba(256,256,256,0.1); height:40px; float:left; width:100%">
    	<p style="margin-top:10px;" class="showCardName">
            <!-- <span style="font-size:14px;">身份证<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span>
            <span style="font-size:14px; margin-left:20px;">健康证<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span>
            <span style="font-size:14px; margin-left:20px;">母婴护理师证<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span>
            <span style="font-size:14px; margin-left:20px;">育婴师证<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span> -->
  	    </p>
    </div> 
</div>
<div style="float:left; height:36px; background:#fff; width:100%; border-bottom:6px #f1f1f1 solid; text-align:center;">
    <p style=" margin-top:5px;">
    	<span style=" font-size:12px; color:#000;">基本资料</span>
	</p>
</div>
<div style=" float:left; width:100%;">
	<p style="margin-top:7px; margin-left:12px;">
    	<span style=" font-size:14px;">从业经历</span>
   	</p>
    <div style="border-top:1px #f1f1f1 solid; float:left; width:100%">
    	<p style="margin-top:13.5px; padding-left:12px; padding-right:12px;">
    		<span style="padding-left:12px;">${sWaiterMessage.waiterExperience}</span>
    	</p>
    </div>
</div>
<!-- <div style="float:left; width:100%;border-top:6px #f1f1f1 solid">
	<p style="margin-top:7px; margin-left:12px;">
    	<span style="float:left; font-size:14px;">工作档期</span>
        <span style="float:right"><a href="#"  style=" font-size:12px; color:#000; padding-right:12px;">查看更多</a></span>
   	</p>
    <div style="float:left; width:100%; height:120px; margin-top:10px; border-top:1px #f1f1f1 solid; border-bottom:6px #f1f1f1 solid; text-align:center"> 
        <div style=" float:left; margin-left:30px; margin-top:10px;" id="myStat2" data-dimension="90" data-text="08月" data-width="5" data-fontsize="12" data-percent="100" data-fgcolor="#ff8a73" data-bgcolor="#eee"></div>
        <div style=" float:left; margin-left:20px; margin-top:10px;" id="myStat3" data-dimension="90" data-text="09月" data-width="5" data-fontsize="12" data-percent="75" data-fgcolor="#ff8a73" data-bgcolor="#eee"></div>
        <div style=" float:left; margin-left:20px; margin-top:10px;" id="myStat4" data-dimension="90" data-text="10月" data-width="5" data-fontsize="12" data-percent="25" data-fgcolor="#ff8a73" data-bgcolor="#eee"></div>
 	</div>
</div> -->
<div style="float:left; width:100%;border-top:1px #f1f1f1 solid">
	<p style="margin-top:7px; margin-left:12px;">
    	<span style=" font-size:14px;">照片</span>
   	</p>
    <div style="border-top:1px #f1f1f1 solid; float:left; width:100%;text-align:center">
    	<p style="margin-top:7px;" class="showImages">
            <!-- <span><img src="images/timg(1).png" width="112" height="108"/></span>
            <span style="margin-left:3px;"><img src="images/timg(2).png" width="112" height="108"/></span>
            <span style="margin-left:3px;"><img src="images/timg(3).png" width="112" height="108"/></span> -->
        </p>
    </div>
</div>
<div style=" float:left; width:100%; border-top:6px #f1f1f1 solid">
	<p style="margin-top:7px; margin-left:12px;">
    	<span style=" font-size:14px;">信伊健康医家</span>
   	</p>
    <div style="border-top:1px #f1f1f1 solid; float:left; width:100%">
    	<p style="margin-top:13.5px; padding-left:12px; padding-right:12px;">
    		<span style="padding-left:12px;">${sWaiterMessage.waiterPlatformMsg}</span>
    	</p>
    </div>
</div>
<!-- <div style=" float:left; width:100%; border-top:6px #f1f1f1 solid">
	<p style="margin-top:7px; margin-left:12px;">
    	<span style="font-size:14px;">妈妈评语</span>
   	</p>
    <div style="float:left; width:100%; border-top:1px #f1f1f1 solid; border-bottom:1px #f1f1f1 solid">
    	<ul>
        	<li style=" float:left; border-bottom:1px #f1f1f1 solid">
            	<div style="width:10%; float:left; margin-top:10px;">
                	<img src="images/yue_head.png" height="30" width="30"/>
                </div>
                <div  style="width:90%; float:left;  margin-top:10px;">
                	<span>张女士</span>：<span style="margin-left:3px; color:#999">深圳-罗湖区翠竹路</span>
                    <span style="float:right"><img src="images/score_star_yellow.png" height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/></span><br>
                	<span>刘阿姨很不错，会照顾孩子会照顾产妇，煮的饭菜也很好吃，极力推荐！</span><br>
                    <p style="float:right; font-size:12px;"><img src="images/yue_time.png" height="11" width="11"/>2017-08-11</p>
                </div>  
            </li>
         	<li style=" float:left; border-bottom:1px #f1f1f1 solid">
            	<div style="width:10%; float:left; margin-top:10px;">
                	<img src="images/yue_head.png" height="30" width="30"/>
                </div>
                <div  style="width:90%; float:left;  margin-top:10px;">
                	<span>张女士</span>：<span style="margin-left:3px; color:#999">深圳-罗湖区翠竹路</span>
                    <span style="float:right"><img src="images/score_star_yellow.png" height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/></span><br>
                	<span>刘阿姨很不错，会照顾孩子会照顾产妇，煮的饭菜也很好吃，极力推荐！</span><br>
                    <p style="float:right; font-size:12px;"><img src="images/yue_time.png" height="11" width="11"/>2017-08-11</p>
                </div>  
            </li>
            <li style=" float:left;">
            	<div style="width:10%; float:left; margin-top:10px;">
                	<img src="images/yue_head.png" height="30" width="30"/>
                </div>
                <div  style="width:90%; float:left;  margin-top:10px;">
                	<span>张女士</span>：<span style="margin-left:3px; color:#999">深圳-罗湖区翠竹路</span>
                    <span style="float:right"><img src="images/score_star_yellow.png" height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/><img src="images/score_star_yellow.png"height="11" width="11"/></span><br>
                	<span>刘阿姨很不错，会照顾孩子会照顾产妇，煮的饭菜也很好吃，极力推荐！</span><br>
                    <p style="float:right; font-size:12px;"><img src="images/yue_time.png" height="11" width="11"/>2017-08-11</p>
                </div>  
            </li>
        </ul>
       <div style="float:left; height:22px; background:#fff; width:100%; border-top:1px #f1f1f1 solid; text-align:center;">
            <p>
           		 <a href="" style=" font-size:12px;  color:#999;">查看更多评价</a>
            </p>
        </div>
    </div>
</div> -->
<div style="float:left; height:49px; background:#fa6143; width:100%; border-top:6px #f1f1f1 solid; text-align:center;">
      <p style=" margin-top:13px;">
           <a href="serve_submit.jsp?waiterNo=<%=waiterNo%>&waiterType=<%=waiterType%>" style=" font-size:16px; color:#fff;">预约<%=typeName%></a>
     </p>
</div>
</body>
</html>
<script type="text/javascript">
$( document ).ready(function() {
		$('#myStat2').circliful();
		$('#myStat3').circliful();
		$('#myStat4').circliful();
    });

window.onload = function(){

	//界面
	var serveType = '<%=waiterType%>';
	var baby = '';
	var servemoney = '';
	if(serveType == '1'){
		baby = '<span style="margin-left:10px;">|</span><span style="margin-left:10px;">带过${sWaiterMessage.waiterBabycount}个宝宝</span>';
		servemoney = '26天';
	}
	if(serveType == '2'){
		servemoney = '单次';
	}
	if(serveType == '3'){
		servemoney = '天';
	}
	$('#servemoney').html(servemoney);
	$('#baby').html(baby);
	
	//经验卡片
	var waiterCharacteristics = "${sWaiterMessage.waiterCharacteristics}";
	var characteristics = waiterCharacteristics.split(/[,，]/);
	var showChara = "";
	for(var i=0;i<characteristics.length;i++){
		if(characteristics.length == 1 && characteristics[0] == ""){
			showChara = '暂无经验';
		}else{
			if(i==0){
			showChara = '<span style="border:1px #FFFFFF solid; height:20px; border-radius:10px;">&nbsp;&nbsp;'+characteristics[0]+'&nbsp;&nbsp;</span>';
			}else{
				showChara +='<span style="border:1px #FFFFFF solid; height:20px; border-radius:10px; margin-left:10px;">&nbsp;&nbsp;'+characteristics[i]+'&nbsp;&nbsp;</span>';
			}
		}
		
	} 
	$('.characteristics').html(showChara);
	
	//认证卡片
	var card_name = "${sWaiterCertificates.cardName}";
	var cardName = card_name.split(/[,，]/);
	var showCardName = "";
	for(var j= 0;j<cardName.length;j++){
		if(cardName.length == 1 && cardName[0] == ""){
			showCardName = '尚未认证';
		}else{
			if(j==0){
			showCardName = '<span style="font-size:14px;">'+cardName[0]+'<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span>';
			}else{
				showCardName += '<span style="font-size:14px; margin-left:20px;">'+cardName[j]+'<img src="images/yue_ch.png" style="width:11px; height:11px; margin-left:5px;"/></span>';
			}
		}
		
	}
	$(".showCardName").html(showCardName);
	
	var attachId = "${sWaiterMessage2.attachId}";
	alert(attachId);
	var imageId = attachId.split(",");
	var imageshow = '';
	if(imageId.length == 1 && imageId[0] == ""){
		imageshow = '平台尚未上传照片';
	}else{
		if(imageId[0]){
			imageshow += '<span><img src="../../imageShow?fileId='+imageId[0]+'" width="112" height="108"/></span>';	
		}
		if(imageId[1]){
			imageshow += '<span style="margin-left:3px;"><img src="../../imageShow?fileId='+imageId[1]+'" width="112" height="108"/></span>';
		}
		if(imageId[2]){
			imageshow += '<span style="margin-left:3px;"><img src="../../imageShow?fileId='+imageId[2]+'" width="112" height="108"/></span>';
		}
	}
	$('.showImages').html(imageshow);		
}	
</script>
