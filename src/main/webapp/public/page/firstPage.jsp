<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.zxpublic.vo.User" %>
<% User currUser = (User)session.getAttribute("user"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.datadiv{
		width: 246px;
		height: 42px;
		margin-top: 14px;
	}
	.datadiv2{
		color: #fff;
		text-align: center;
		padding-top: 10px;
		font-family: 微软雅黑;
	}
</style>
</head>
<body>

	<div id="danci" style="float: left; height: 400px; width:  70%;min-width: 500px;"></div>
	<div style=" width: 300px; margin-left: 50px; float: left; margin-top: 50px; " id="dataLev">
		
	</div>
	<script type="text/javascript" src="../../js/echarts.min.js"></script>
	<script type="text/javascript"	src="../easyui/js/jquery-1.8.3.min.js"></script>
	
	<script type="text/javascript">
		var utype = '<%=currUser.getuType()%>';//获取用户类型
		//utyp==1显示平台数据，==2显示酒店数据
		var myChart = echarts.init(document.getElementById('danci'));
		if(utype == '1'){
			/* <div class="datadiv" style="background-image: url('../../image/bg1.png');">
			</div>
			<div class="datadiv" style="background-image: url('../../image/bg2.png');">
			</div>
			<div class="datadiv" style="background-image: url('../../image/bg3.png');">
			</div>
			<div class="datadiv" style="background-image: url('../../image/bg4.png');">
			</div> */
			var html =  "<div class=\"datadiv\" style=\"background-image: url('../../image/bg1.png');\"><div class=\"datadiv2\" id=\"dataContentDiv1\"></div></div>"
				html += "<div class=\"datadiv\" style=\"background-image: url('../../image/bg2.png');\"><div class=\"datadiv2\" id=\"dataContentDiv2\"></div></div>";
				html += "<div class=\"datadiv\" style=\"background-image: url('../../image/bg3.png');\"><div class=\"datadiv2\" id=\"dataContentDiv3\"></div></div>";
				html += "<div class=\"datadiv\" style=\"background-image: url('../../image/bg4.png');\"><div class=\"datadiv2\" id=\"dataContentDiv4\"></div></div>";
			$("#dataLev").html(html);
			option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['服务订单统计']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : [/* '周一','周二','周三','周四','周五','周六','周日' */]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'服务订单统计',
			            type:'line',
			            stack: '总量',
			            symbol: 'none',
			            itemStyle: {
			                normal: {
			                	 label: { show: true} 
			                    //areaStyle: {
			                    //    // 区域图，纵向渐变填充
			                    //    color : (function (){
			                    //        /* var zrColor = require('zrender/tool/color');
			                    //        return zrColor.getLinearGradient(
			                    //            0, 200, 0, 400,
			                    //            [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
			                    //        ) */
			                    //    })()
			                    //}
			                }
			            },
			            data:[
			                /* 180, 132, 31, 334, 
			                //{value:90, symbol:'droplet',symbolSize:5},
			              90,
			                230, 210 */
			            ]
			        }
			    ]
			};
					 
			 $.ajax({
					url : "../../serveInfo/statistics2.action?uid="+uid,
					type : "post",
					async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
					dataType : "json",
					success : function(result) {
						if (result != null) {
							option.series[0].data =result.serve;
							option.xAxis[0].data=result.xAxis; 
							myChart.setOption(option);
							$("#dataContentDiv1").html("月嫂"+result.type1);
							$("#dataContentDiv2").html("催乳师"+result.type2);
							$("#dataContentDiv3").html("陪护"+result.type3);
							$("#dataContentDiv4").html("异常订单"+result.type4);
						} else {
							//返回的数据为空时显示提示信息  
							alert("图表请求数据为空！");
						}
					},
					error : function(errorMsg) {
						//请求失败时执行该函数  
						//alert("图表请求数据失败,请联系管理员！");
					}
				});
		}else{

			var html =  "<div class=\"datadiv\" style=\"background-image: url('../../image/bg1.png');\"><div class=\"datadiv2\" id=\"dataContentDiv5\"></div></div>"
				html += "<div class=\"datadiv\" style=\"background-image: url('../../image/bg4.png');\"><div class=\"datadiv2\" id=\"dataContentDiv6\"></div></div>";
			$("#dataLev").html(html);
			
			var uid = '<%=currUser.getUid()%>';//获取用户ID
			option = {
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['订单统计']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : [/* '周一','周二','周三','周四','周五','周六','周日' */]
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'订单统计',
				            type:'line',
				            stack: '总量',
				            symbol: 'none',
				            itemStyle: {
				                normal: {
				                	label: { show: true} 
				        			//,emphasis: { label: { show: true} }
			        				/*areaStyle: {
				                        // 区域图，纵向渐变填充
				                        color : (function (){
				                            /* var zrColor = require('zrender/tool/color');
				                            return zrColor.getLinearGradient(
				                                0, 200, 0, 400,
				                                [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
				                            ) 
				                        })()
				                    }*/
				                }
				            },
				            data:[
				            ]
				        }
				    ]
				};
						 
				 $.ajax({
						url : "../../orderInfo/statistics2.action?uid="+uid,
						type : "post",
						async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
						dataType : "json",
						success : function(result) {
							if (result != null) {
								option.series[0].data =result.series;
								option.xAxis[0].data=result.xAxis; 
								myChart.setOption(option);
								$("#dataContentDiv5").html("正常订单"+result.type5);
								$("#dataContentDiv6").html("异常订单"+result.type6);
							} else {
								//返回的数据为空时显示提示信息  
								//alert("图表请求数据为空！");
							}
						},
						error : function(errorMsg) {
							//请求失败时执行该函数  
							//alert("图表请求数据失败,请联系管理员！");
						}
					});
		}
	</script>
</body>
</html>