<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//String store_id = request.getParameter("store_id");
	String store_id = "3";
	
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/public/clientPage/";
	//String openid = session.getAttribute("openid").toString();
	String openid = "sdff165496ew4cdsz";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<base href='<%=basePath%>'>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>name</title>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
 <link href="css/mui.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/style.css" />
<link href="http://at.alicdn.com/t/font_l6a0fwucxvzehfr.css" rel="stylesheet" type="text/css" />

<style>
.right_c ul li{ overflow:hidden; position:relative;}
</style>
</head>

<script type="text/javascript"> 
	        !function(J){function H(){var d=E.getBoundingClientRect().width;var e=(d/7.5>100*B?100*B:(d/7.5<42?42:d/7.5));E.style.fontSize=e+"px",J.rem=e}var G,F=J.document,E=F.documentElement,D=F.querySelector('meta[name="viewport"]'),B=0,A=0;if(D){var y=D.getAttribute("content").match(/initial\-scale=([\d\.]+)/);y&&(A=parseFloat(y[1]),B=parseInt(1/A))}if(!B&&!A){var u=(J.navigator.appVersion.match(/android/gi),J.navigator.appVersion.match(/iphone/gi)),t=J.devicePixelRatio;B=u?t>=3&&(!B||B>=3)?3:t>=2&&(!B||B>=2)?2:1:1,A=1/B}if(E.setAttribute("data-dpr",B),!D){if(D=F.createElement("meta"),D.setAttribute("name","viewport"),D.setAttribute("content","initial-scale="+A+", maximum-scale="+A+", minimum-scale="+A+", user-scalable=no"),E.firstElementChild){E.firstElementChild.appendChild(D)}else{var s=F.createElement("div");s.appendChild(D),F.write(s.innerHTML)}}J.addEventListener("resize",function(){clearTimeout(G),G=setTimeout(H,300)},!1),J.addEventListener("pageshow",function(b){b.persisted&&(clearTimeout(G),G=setTimeout(H,300))},!1),H()}(window);
	        if (typeof(M) == 'undefined' || !M){
	            window.M = {};
	        }
	    </script>
<script type="text/javascript">
	function $(_id) {
		return document.getElementById(_id);
	}
	var delivery_cost;
	var miniMoney;
	function add_dishNum(){
		$.ajaxSetup({
			async : false
		})
		$.get("../../storeInfo/singleStore", {
			storeId :<%=store_id%>
		}, function(data) {
			delivery_cost = data.deliveryCost;
			miniMoney = data.minimum;
		}, "json");
		var obj = document.getElementsByClassName('caiming_name');
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = function() {
				debugger
				this.getElementsByTagName("p")[1].getElementsByTagName("span")[0].innerHTML = '<i class="iconfont icon-jianshao shangjia_xuan_add">';
				var oNumber = this.getElementsByTagName("p")[1].getElementsByTagName("span")[1].innerText;
				
				if(oNumber<1) {
					oNumber = 0;
				}
				this.getElementsByTagName("p")[1].getElementsByTagName("span")[1].innerText= parseInt((parseInt(oNumber)) + 1);
				
				//检查表格中是否已存在当前点击的菜品的记录
				var o ;
				if(document.getElementsByTagName('tbody')[0].getElementsByTagName('td')){
					o = document.getElementsByTagName('tbody')[0].getElementsByTagName('td');
				}
			 	for (var j = 0; j < o.length; j++)
					if (o[j].getAttribute('code') == this.getAttribute('code')){
						var oNumber = o[j].parentNode.getElementsByTagName('td')[2].getElementsByClassName('text_box')[0].innerText;
						o[j].parentNode.getElementsByTagName('td')[2].getElementsByClassName('text_box')[0].innerText = parseInt((parseInt(oNumber)) + 1);				
						calcTotalPrice();
						sum();
						return;
					}
 			
				//向tbody添加记录
				var oTbody = document.getElementsByTagName('tbody')[0];
				var newRow = oTbody.insertRow();
				newRow.setAttribute("id", this.getAttribute('id'));

				var cell_1 = newRow.insertCell(0);
				cell_1.setAttribute('code', this.getAttribute('code'));
				cell_1.innerHTML = this.getAttribute('code');

				var cell_2 = newRow.insertCell(1);
				cell_2.setAttribute('price', this.getAttribute('price'));
				cell_2.innerHTML = '<span class="j_ge">￥'
						+ this.getAttribute('price') + '</span>';

				var cell_3 = newRow.insertCell(2);
				cell_3.innerHTML = '<span type="button" class="decrease"><span  id="min" class="iconfont icon-jianshao"></span></span>'
						+ '<span class="text_box"/>1</span>'
						+ '<span type="button" class="increase"><span id="add" class="iconfont icon-tubiao225"></span></span>';
				
				
				//绑定按钮事件
				var oButton = document.getElementsByTagName('span');
				for (var k = 0; k < oButton.length; k++) {
					if (oButton[k].className == 'increase') {
						oButton[k].onclick = increase;
					}
					if (oButton[k].className == 'decrease') {
						oButton[k].onclick = decrease;
					}
				}
				calcTotalPrice();
				sum();
			}
		}		
	}
	
	function delete_dishNum(){
		var obj = document.getElementsByClassName('caiming_name');
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = function() {
				debugger
				var oNumber = this.getElementsByTagName("p")[1].getElementsByTagName("span")[1].innerText;
				//只有一份时点击减少按钮，删除该行记录
				if ((parseInt(oNumber)) == 1 && oNumber) {
					this.getElementsByTagName("p")[1].getElementsByTagName("span")[0].innerHTML = '<i></i>';
					this.getElementsByTagName("p")[1].getElementsByTagName("span")[1].innerHTML = '';
				} else if (oNumber){
					this.getElementsByTagName("p")[1].getElementsByTagName("span")[1].innerText = parseInt((parseInt(oNumber)) - 1);
				}
				//检查表格中是否已存在当前点击的菜品的记录
				var o = document.getElementsByTagName('tbody')[0].getElementsByTagName('td');
			 	for (var j = 0; j < o.length; j++)
					if (o[j].getAttribute('code') == this.getAttribute('code')){
						var _oNumber = o[j].parentNode.getElementsByTagName('td')[2].getElementsByClassName('text_box')[0].innerText;
						console.log(o[j].getAttribute('code')+'-------------------'+this.getAttribute('code'))
						if ((parseInt(_oNumber)) == 1) {
							//o[j].parentNode.parentNode.removeChild(o[j].parentNode);
							o[j].parentNode.remove();
						} else{
							o[j].parentNode.getElementsByTagName('td')[2].getElementsByClassName('text_box')[0].innerText = parseInt((parseInt(oNumber)) - 1);
						}
						//				
						calcTotalPrice();
						sum();
						return;
					}
				
				//计算总价
				calcTotalPrice();
				sum();
			}
		}	
	}
	
	function calcTotalPrice() {
		var obj;
		var sum = 0;
		if(document.getElementsByTagName('tbody')[0].getElementsByTagName('tr')){
			obj= document.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		}
		
		for (var i = 0; i < obj.length; i++) {
			var o = obj[i].getElementsByTagName('td');
			sum += parseFloat(o[1].getAttribute('price'))
					* parseInt(o[2].getElementsByClassName('text_box')[0].innerText);
		}
		document.getElementsByClassName('text-left')[0]
				.getElementsByTagName('span')[0].innerHTML = parseFloat(sum.toFixed(2))
				+ parseFloat(delivery_cost);
		
		
	}

	function increase() {
		debugger
		var oNumber = this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText;
		var _num = parseInt((parseInt(oNumber)) + 1);
		this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText = _num;
		calcTotalPrice();
		sum();
		$("li[id="+this.parentNode.parentNode.id+"] p span")[1].innerHTML = _num;
	}

	function decrease() {
		debugger
		var _id = this.parentNode.parentNode.id;
		var oNumber = this.parentNode.parentNode.getElementsByTagName('td')[2]
				.getElementsByClassName('text_box')[0].innerText;
		var _num = parseInt((parseInt(oNumber)) - 1);

		$("li[id="+ _id +"] p span")[1].innerHTML = _num;
		//只有一份时点击减少按钮，删除该行记录
		if ((parseInt(oNumber)) == 1) {
			$("li[id="+ _id +"] p span")[0].innerHTML = "<i></i>";
			$("li[id="+ _id +"] p span")[1].innerHTML = "";
			this.parentNode.parentNode.remove();
		} else{
			this.parentNode.parentNode.getElementsByTagName('td')[2]
					.getElementsByClassName('text_box')[0].innerText = _num;
		}
		calcTotalPrice();
		sum();
	}

	function sum() {
		var obj = document.getElementsByTagName('tbody')[0]
				.getElementsByTagName('tr'), sum = 0;
		for (var i = 0; i < obj.length; i++) {
			var o = obj[i].getElementsByTagName('td');
			sum += parseInt(o[2].getElementsByClassName('text_box')[0].innerText);
		}
		document.getElementsByClassName('footer')[0]
				.getElementsByTagName('span')[0].getElementsByTagName('i')[0].innerHTML = sum;
		//计算已经选择的菜品的价格之和
		
		var showSum = $('#showSum').html();
		if(parseInt(showSum)>=parseInt(miniMoney)){
			var showSubmit = '<span class="jie" onClick="dingdanSubmit()">提交</span>';
			$('.tijiao').html(showSubmit);
		}else{
			var showSubmit1 = '<span class="jie" onClick="dingdanSubmit()" style="background:#999;">￥'+miniMoney+'起送</span>';
			$('.tijiao').html(showSubmit1);
		}
	}
</script>
<script type="text/javascript">
	window.onload = function() {
		
		//显示底部配送费
		$.get("../../storeInfo/singleStore", {
			storeId : <%=store_id%>
		}, function(data) {
			var delivery_cost = data.deliveryCost;
			document.getElementsByClassName('text-left')[0]
					.getElementsByTagName('span')[0].innerHTML = delivery_cost;
			var showSubmit = '<span class="jie" onClick="dingdanSubmit()" style="background:#999;">￥'+data.minimum+'起送</span>';
			$('.tijiao').html(showSubmit);
		}, "json");
		
		
		
		
	};

	//顶部显示商家信息
	function showStoreInfo(){
		$.get("../../storeInfo/singleStore", {
			storeId : <%=store_id%>
		}, function(data) {
			var storeInfo = '<div class="container_gls" style="width: 100%; float: left;">'
					+'<div class="zero"	style="width: 100%; border-top: 6px solid #f3f3f3; float: left">'
					+'<span class="fu" style="margin-top: 10px; margin-left: 15px;">'
					+'<img src="images/bussins/shop_tel.png" width="15" height="15" />'
					+'<span style="font-size: 14px; margin-left: 20px"> '
					+'<a href="tel:'+data.storePhone+'">'+data.storePhone+'</a>' 
					+'<span style="margin-left: 10px;">(点击拨打商家电话)</span>'
					+'</span>'
					+'</span>'
					+'</div>'
					+'<div class="zero" style="width: 100%; border-top: 1px solid #f3f3f3; float: left">'
					+'<span class="fu" style="margin-top: 8px; margin-left: 15px;">'
					+'<img src="images/bussins/shop_address.png" width="12" height="18" />'
					+'<span style="font-size: 14px; margin-left: 20px">'
					+data.storeAddress+'</span>'
					+'</span>'
					+'</div>'
					+'<div class="zero"	style="width: 100%; border-top: 6px solid #f3f3f3; float: left">'
					+'<span class="fu" style="margin-top: 8px; margin-left: 15px;">'
					+'<img src="images/bussins/shop_sendtime.png" width="15" height="15" />' 
					+'<span style="font-size: 14px; margin-left: 20px">营业时间：</span>'
					+'<span>08:00-20:00</span>'
					+'</span>'
					+'</div>'
					+'<div class="zero" style="width: 100%; border-top: 1px solid #f3f3f3; float: left">'
					+'<span class="fu" style="margin-top: 8px; margin-left: 15px;">'
					+'<img src="images/bussins/shop_sendtime.png" width="15" height="15" />'
					+'<span style="font-size: 14px; margin-left: 20px">'
					+'<span>配送服务：</span> <span>￥'+data.minimum+'起送丨配送费￥'+data.deliveryCost+'</span>'
					+'</span>'
					+'</span>'
					+'</div>'
					+'</div>';	
				$(".sh_name").html(storeInfo);
			}, "json");
	}
</script>
<body>

<!--top
	<div class="top_c">
		<a href="storelist.jsp" class="iconfont icon-jiantou-copy-copy"></a>
		<p class="titi">掌上餐厅</p>
	</div>
-->
	<!--头部-->
	
	<div class="pos_gls">
		<div class="container">
			<div class="row titll">
				<div class="col-xs-4" style="line-height: 3em;">
					<div style="width: 60%; font-size:16px; margin-left: 20%;border-bottom:3px #FFA980 solid; color:#FFA980"
						class="shangjia_caidan_top shangjia_caidan_top_left">点菜</div>
				</div>
				<div class="col-xs-4" style="line-height: 3em">
					<div style="width: 60%; margin-left: 20%;font-size:16px;"
						class="shangjia_caidan_top shangjia_caidan_top_right"  onClick="showStoreInfo();">商家</div>
				</div>
			</div>
		</div>
	</div>

	<!--列表-->
<div>
    <div class="lie">
        <div class="mui-content mui-row mui-fullscreen search-menu">
			<div class="mui-col-xs-3">
				<div id="segmentedControls" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-vertical">
					
				</div>
			</div>
			<div id="segmentedControlContents" class="mui-col-xs-9" >
			</div>
		</div>
	</div>
	
	<div class="shop">
	    	<img src="images/gdsgf4.jpg" class="img_sh">
	        <div class="sh_name">
	        </div>
	    </div>
	</div>

<!--遮罩层-->
	<div class="window"></div>
	<div class="gou">
		<p class="wu_t">
			<i></i>购物车<!--<a href="#"><span class="iconfont icon-iconfontshanchu"></span>清空</a>-->
		</p>
		<div class="container">
			<table id="order" border="0" cellpadding="0" cellspacing="0"
				class="table">
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<!--footer-->
	<div class="footer">
		<span class="iconfont icon-iconfontgouwuche"> <i>0</i>
		</span>
		<div class="jia">
			<blockquote>
				<p style="margin: auto; font-size: 2em" class="text-left">
					￥<span id="showSum">5.00</span>
				</p>
			</blockquote>
		</div>
		<span class="tijiao">
			
		</span>
	</div>
<script src="js/mui.min.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/iscroll.js"></script>
<script>
	$(function(){
		var w_he= $(window).height();
		var t_he=$(".top_c").height();
		var f_he=$(".footer").height();
		var p_he=$(".pos").height();
		var l_he=w_he-t_he-f_he-p_he;
		var m_to=t_he+p_he;
		$(".left_c,.right_c").css({height:l_he});
		$(".lie,.ping,.shop").css({marginTop:m_to});
		var Scroll = new iScroll('left',{hScrollbar:false, vScrollbar:true,hScroll:false,vScroll:true,useTransform: false});
		var Scroll = new iScroll('right',{hScrollbar:false, vScrollbar:true,hScroll:false,vScroll:true,useTransform: false});
	})
</script>
<script type="text/javascript">
//显示餐厅的所有菜品
function showDish(){
	
	$.get("../../dishInfo/dishlist",
		{
			store_id :<%=store_id%>
		},
		function(data) { 
			var caidanlist = "";
			var contents = document.getElementById("segmentedControlContents");
			var html = [];
			for (var m = 0; m < data.length; m++) {
				html.push('<div id="content' + m + '"  class="mui-control-content" ><ul class="mui-table-view">'+'<h2><span class="fl">分类' +data[m].dishtype+ '</span></h2>');//分类
				for (var i = 0; i < data.length-1; i++) {'<li class="mui-table-view-cell">'
				//	caidanlist += '<div id="content' + i + '" class="mui-control-content"><ul class="mui-table-view">'+'<h2><span class="fl">' + menulist[i-1] + '</span></h2>'
	//				caidanlist += '<li code="'+data[i].dishName+'" class="caiming_name" price="'+data[i].dishPrice+'" id="'+data[i].dishId+'" style="float:left;width:100%">'
					html.push('<li code="'+data[i].dishName+'" class="caiming_name mui-table-view-cell" price="'+data[i].dishPrice+'" id="'+data[i].dishId+'" style="float:left;width:100%">'
						+ '<img src="../../imageShow?fileId='+data[i].dishUrl+'" class="sp_b" id=xy'+data[i].dishtypeId+'>'
						+ '<div class="text">'
						+ '<p id="620548">'
						+ data[i].dishName
						+ '</p>'
						+ '<span class="jian">规格：'
						+ data[i].dishSpecification
						+ '</span>'
						+ '<div>'
						+ '<span class="jian">月售：'
						+ data[i].dishMonthsales
						+ '</span>'
						+ '</div>'
						+ '<div class="money">'
						+ '<span>'
						+ data[i].dishPrice
						+ '</span>'
						+'<span style="margin-left:10px" class="icon_DA"><p style="float:right">'
		                +'<span  onClick="delete_dishNum();"></span>'
		               	+'<span></span>'
		                +'<span onClick="add_dishNum();"><i class="iconfont icon-tubiao225 shangjia_xuan_add"></i></span>'
		                +'</p></span>'
						+ '</div>'
						+ '</div>'
		                +'</li>');
				}
				html.push('</ul></div>');
			}
			//showDish();
			
			contents.innerHTML = html.join('');
			//$("#segmentedControlContents").html('<div id="content' + i + '" class="mui-control-content" style=""><ul class="mui-table-view">'+'<h2><span class="fl"></span></h2>'+'<li>&nbsp;</li>'+caidanlist+'</ul></div>');
		}, "json");
}



function showDishType(){
	//显示右侧菜品类型
	 $.get("../../dishType/menulist",{storeId:<%=store_id%>},function(data){
		debugger;
		var html = [];
		if(data){
			var dishTypeList = '';
			alert('--------------------------------------')
			for(var i=0;i<data.length;i++){
				//dishTypeList +='<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '"> ' +data[i].dishtype+ '' +  '</a>';
				html.push('<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '"> ' +data[i].dishtype+ '' +  '</a>');
			}
			// $('#segmentedControls').html(dishTypeList); 
			 var controls = document.getElementById("segmentedControls");
			 controls.innerHTML = html.join('');
		}
	},"json") 
	
/* 	var html = [];
		var dishTypeList = '';
		for(var i=0;i<3;i++){
			//dishTypeList +='<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '"> ' +data[i].dishtype+ '' +  '</a>';
			html.push('<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '"> 水产海鲜</a>');
		}
		// $('#segmentedControls').html(dishTypeList); 
	var controls = document.getElementById("segmentedControls");
	controls.innerHTML = html.join(''); */
	
}
</script>
<script>
//链接跳转问题
			mui('body').on('tap', 'a', function() {
				document.location.href = this.href;
			});
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			var controls = document.getElementById("segmentedControls");
			var contents = document.getElementById("segmentedControlContents");
	        var menulist=new Array("水果蔬菜","肉禽蛋类","水产海鲜","烘焙熟食","粮油副食","零食酒水","乳品母婴","厨房用品","厨房用品");
			var html = [];
			var i = 1,
				j = 1,
				m = 9, //左侧选项卡数量+1
				n = 20; //每个选项卡列表数量+1
			for (; i < m; i++) {
				html.push('<a class="mui-control-item" data-index="' + (i - 1) + '" href="#content' + i + '"> ' + menulist[i-1] + '' +  '</a>');
			}
			//controls.innerHTML = html.join('');
			showDishType();
			
			html = [];
	/* 		for (i = 1; i < m; i++) {
				html.push('<div id="content' + i + '" class="mui-control-content"><ul class="mui-table-view">'+'<h2><span class="fl">' + menulist[i-1] + '</span></h2>');//分类
				for (j = 1; j < n; j++) {
					var small= new Array("莓类","百香果","草莓","橙.柚.柑","瓜类","梨","芒果","苹果");
				    var imgArr = ['images/f1.jpg','images/f2.jpg','images/f3.jpg','images/f4.jpg','images/f5.jpg','images/f6.jpg','images/f7.jpg','images/f8.jpg','images/f9.jpg'];
					html.push('<li class="mui-table-view-cell">' + '<img src="' + imgArr[j-1] + '"/>' +'<p> '+small[j-1] +'</p> ' + '</li>');
				}
				html.push('</ul></div>');
			} */
			showDish();
			
			contents.innerHTML = html.join('');
			//默认选中第一个
			//controls.querySelector('.mui-control-item').classList.add('mui-active');
			(	
				function() {
				var controlsElem = document.getElementById("segmentedControls");
				var contentsElem = document.getElementById("segmentedControlContents");
				var controlListElem = controlsElem.querySelectorAll('.mui-control-item');
				var contentListElem = contentsElem.querySelectorAll('.mui-control-content');
				var controlWrapperElem = controlsElem.parentNode;
				var controlWrapperHeight = controlWrapperElem.offsetHeight;
				var controlMaxScroll = controlWrapperElem.scrollHeight - controlWrapperHeight;//左侧类别最大可滚动高度
				//var controlHeight = controlListElem[0].offsetHeight;//左侧类别每一项的高度
				contentsElem.addEventListener('scroll', function() {
					var scrollTop = contentsElem.scrollTop;
					for (var i = 0; i < contentListElem.length; i++) {
						var offsetTop = contentListElem[i].offsetTop - 80;
						var offset = Math.abs(offsetTop - scrollTop);
//						console.log("i:"+i+",scrollTop:"+scrollTop+",offsetTop:"+offsetTop+",offset:"+offset);
						console.log(offsetTop);
						console.log(scrollTop-(contentListElem[i].offsetHeight / 2));
						console.log(i);
						if (scrollTop-(contentListElem[i].offsetHeight / 2) + 50 <= offsetTop) {
							onScroll(i);
							break;
						}
					}
				});
				var lastIndex = 0;
				//监听content滚动
				var onScroll = function(index) {
					if (lastIndex !== index) {
						lastIndex = index;
						var lastActiveElem = controlsElem.querySelector('.mui-active');
						lastActiveElem && (lastActiveElem.classList.remove('mui-active'));
						var currentElem = controlsElem.querySelector('.mui-control-item:nth-child(' + (index + 1) + ')');
						currentElem.classList.add('mui-active');
						//简单处理左侧分类滚动，要么滚动到底，要么滚动到顶
					}
				};
				//滚动到指定content
				var scrollTo = function(index) {
					contentsElem.scrollTop = contentListElem[index].offsetTop;
				};
				mui(controlsElem).on('tap', '.mui-control-item', function(e) {
					scrollTo(this.getAttribute('data-index'));
					return false;
				});
			}
		)();
</script>


<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/index.js" type="text/javascript"></script>
	<script type="text/javascript">
		function dingdanSubmit() {
			debugger;
			var dataObject = new Object();
			
			dataObject.storeId =<%=store_id%>+ ''; //商家ID 
			var uid;
			var miniMoney;
			$.get("../../storeInfo/singleStore", 
					{storeId :<%=store_id%>	}, 
				function(data) {
					dataObject.uid = data.uid+'';
					dataObject.storeName = data.storeName;//商家名称
					dataObject.deliveryCost = data.deliveryCost;//配送费
					miniMoney = data.minimum;
				}, "json");

			var list_content = new Array();
			var s3 = document.getElementsByTagName("table")[0]; //获取第一个表格 购物车的表格
			if(s3.rows.length>0){
				for (var i = 0; i < s3.rows.length; i++) {
					var object = new Object();
					object.dish_name = s3.rows[i].cells[0].innerText;	//菜品名称
					object.price = s3.rows[i].cells[1]
							.getElementsByClassName('j_ge')[0].innerText   
							.substring(1);								 //菜品价格
					//菜品ID
					object.dish_id = s3.rows[i].id;
					//菜品数量
					object.num = s3.rows[i].cells[2]
							.getElementsByClassName('text_box')[0].innerText;
					list_content.push(object);
				}
				dataObject.content = JSON.stringify(list_content);
				dataObject.amount = document.getElementsByClassName('text-left')[0]
						.getElementsByTagName('span')[0].innerText;	
				if(parseInt(dataObject.amount)<parseInt(miniMoney)){
					alert('未达到商家起送价，再拼一下');
				}else{
					$.ajax({
						url : "../../orderInfo/setSession",
						//data : JSON.stringify({'dataObject':dataObject}),
						data : {
							"dataObject" : JSON.stringify(dataObject)
						},
						type : 'post',
						async : true,
						success : function(result) {
							location.href='submitOrder.jsp';
						}
					});
				}
			}else{
				alert('未选择菜品，选择后提交');
			}
			
		}

		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month
					+ seperator1 + strDate + " " + date.getHours() + seperator2
					+ date.getMinutes() + seperator2 + date.getSeconds();
			return currentdate;
		}
	</script>

</body>
</html>
