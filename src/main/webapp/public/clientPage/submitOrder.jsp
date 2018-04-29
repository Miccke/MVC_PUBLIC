<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	String openId = "jguyujgubjihuiy87i222";	
 	String storeId = session.getAttribute("storeId").toString();
	String storeName = session.getAttribute("storeName").toString();
	String deliveryCost = session.getAttribute("deliveryCost").toString();
	String content = session.getAttribute("content").toString();
	String amount = session.getAttribute("amount").toString();
	String uid = session.getAttribute("uid").toString();
	String addId = request.getParameter("addId");
//	String openId = session.getAttribute("openid").toString();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>提交订单</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link href="http://at.alicdn.com/t/font_l6a0fwucxvzehfr.css" rel="stylesheet" type="text/css" />
</head>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>



<body>
<!--列表-->
<div class="deta" style="margin-top:0; margin-bottom:0; margin-top:10px; margin-bottom:10px">
    <div class="zhuang" onClick="" style="font-size:12px;border-bottom:1px solid #eee; padding-top:10px; padding-bottom:10px;"> 	

    </div>
    <p class="d_min">
    	<label>添加备注</label>
    	<input type="text"/>
    </p>
    <div class="leb">
    	<a href="business.html" class="d_ti"><img src="images/submitOrder/order_shop_icon.png" style="border-radius:5%"><%=storeName%><i class="iconfont icon-jiantou"></i></a>
        <div class="container">
        	<table border="0" cellpadding="0" cellspacing="0" class="table">
                <tbody class="tbody_trs">
                
                </tbody>
            </table>
            <span class="pei" style="margin-right:5px;">配送费<i>￥<%=deliveryCost%></i></span>
            <span class="hui">总计￥<%=amount%><i>实付 <em>￥<%=amount%></em></i></span>
        </div>
    </div>
</div>

<div class="button bu_fi">
    <a href="javascript:void(0)" class="liji" onClick="dingdanSubmit();">立即付款</a>
    <a href="javascript:void(0)" class="qu" onClick="dingdanreset();">取消订单</a>
</div>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
<script type="text/javascript">

	var content = '<%=content%>';
	content=content.replace('\"','"');
	var myobj=eval(content); 
	var order_dish = '';
	for(var i=0;i<myobj.length;i++){ 	
		order_dish +='<tr><td  id="'+myobj[i].dish_id+'">'+myobj[i].dish_name+'</td>'
			        +'<td><span class="j_ge" style="color:#aaa;">×'+myobj[i].num+'</span></td>'
			        +'<td style="text-align:right"><span class="j_ge" style="color:#444;">￥'+myobj[i].price+'</span></td></tr>';
	} 
	$('.tbody_trs').html(order_dish);
</script>
<script type="text/javascript">
		function dingdanSubmit() {
			debugger;
			var dataObject = new Object();
			
			dataObject.realname = $("#userName").html();
			dataObject.storeId =<%=storeId%>+ '';
			var uid='';
			var storeName='';

			dataObject.uid = '<%=uid%>';
			dataObject.storeName = '<%=storeName%>';

			var list_content = new Array();
			var s3 = document.getElementsByTagName("table")[0]; //获取第一个表格 购物车的表格
			for (var i = 0; i < s3.rows.length; i++) {
				var object = new Object();
				object.dish_id = s3.rows[i].cells[0].id;
				object.num = s3.rows[i].cells[1]
						.getElementsByClassName('j_ge')[0].innerText.substring(1);
				object.price = s3.rows[i].cells[2]
						.getElementsByClassName('j_ge')[0].innerText.substring(1);
						
				list_content.push(object);
			}
			dataObject.content = JSON.stringify(list_content);
			dataObject.address =  $("#userAddress").html().substring(5);
			dataObject.ordertime = getNowFormatDate();
			dataObject.phonenum = $("#userPhone").html();
			dataObject.amount = '<%=amount%>';
			dataObject.orderstate = '0';
			dataObject.openid = '<%=openId%>';		
 			$.ajax({
				url : "../../orderInfo/insertOrder",
				//data : JSON.stringify({'dataObject':dataObject}),
				data : {
					"dataObject" : JSON.stringify(dataObject)
				},
				type : 'post',
				async : true,
				success : function(result) {
					location.href = "storelist.jsp";
				}
			}); 
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
		
		function dingdanreset(){
			location.href="storelist.jsp";
		}
	</script>
</body>
</html>

<script type="text/javascript">
	$.post("../../userAddress/getAddress",{openId:'<%=openId%>',addId:'<%=addId%>'},function(data){
		debugger;
		var zhuangValue = '';
		if(data){
			zhuangValue = '<span style="color:#333" id="userName">'+data.userName+'</span>'
		        +'<span style="margin-left:60px;color:#333" id="userPhone">'+data.userPhone+'</span><br>'
		        +'<span style="color:#999" id="userAddress">收货地址：'+data.userAddress+'</span>'
		        +'<a href="add_address.jsp?openid=<%=openId%>" style="float:right;margin-right:10px; color:#fe6e12">更改收货信息</a>';
		}else{
			zhuangValue = '<a href="add_address.jsp?openid=<%=openId%>"><img src="images/qietu/history_server_worktime.png" width="15px;" height="15px"/>'
		        +'<span style="margin-left:10px; font-size:12px; color:#fe6e12">编辑收货人信息</span></a>';
		}
		$('.zhuang').html(zhuangValue);
	},"json")
</script>
