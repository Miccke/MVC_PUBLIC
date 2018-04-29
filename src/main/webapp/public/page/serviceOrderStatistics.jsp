<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.zxpublic.vo.User" %>
<% User currUser = (User)session.getAttribute("user"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="danci" style="float: left; height: 400px; width:70%;min-width:500px;"></div>

	<script type="text/javascript" src="../../js/echarts.min.js"></script>
	<script type="text/javascript"	src="../easyui/js/jquery-1.8.3.min.js"></script>
	
	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('danci'));
		option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['酒店订单统计','服务订单统计']
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
			            name:'酒店订单统计',
			            type:'line',
			            stack: '总量',
			            symbol: 'none',
			            itemStyle: {
			                normal: {
			                	 label: { show: true} 
			                    /*areaStyle: {
			                        // 区域图，纵向渐变填充
			                        color : (function (){
			                             var zrColor = require('zrender/tool/color');
			                            return zrColor.getLinearGradient(
			                                0, 200, 0, 400,
			                                [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
			                            ) 
			                        })()
			                    }*/
			                }
			            },
			            data:[
			                /* 180, 132, 31, 334, 
			                //{value:90, symbol:'droplet',symbolSize:5},
			              90,
			                230, 210 */
			            ]
			        },
			        {
			            name:'服务订单统计',
			            type:'line',
			            stack: '总量',
			            symbol: 'none',
			            itemStyle: {
			                normal: {
			                	 label: { show: true} 
			        /*areaStyle: {
			                        // 区域图，纵向渐变填充
			                        color : (function (){
			                             var zrColor = require('zrender/tool/color');
			                            return zrColor.getLinearGradient(
			                                0, 200, 0, 400,
			                                [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
			                            ) 
			                        })()
			                    }*/
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
					 
			//myChart.setOption(option);  

			 $.ajax({
					url : "../../serveInfo/statistics.action",
					type : "post",
					async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
					dataType : "json",
					success : function(result) {
						if (result != null) {
							debugger
							//for (var i = 0; i < result.result.length > 0; i++) {
								option.series[0].data =result.series;
								option.xAxis[0].data=result.xAxis; 
								option.series[1].data=result.serve;
							//}
							//option1.xAxis[0].data = option2.xAxis[0].data = option.xAxis[0].data;
							 myChart.setOption(option);
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
	</script>
</body>
</html>