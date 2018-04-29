<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"	href="../easyui/css/easyui.css">
<link rel="stylesheet" type="text/css"	href="../easyui/css/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
<script type="text/javascript"	src="../easyui/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"	src="../easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript">

	//datagrid加载完后合并指定单元格  
	function mergeCells(data){
	    var arr =[{mergeFiled:"deliveryCost",premiseFiled:"orderNum"} ,    //合并列的field数组及对应前提条件filed（为空则直接内容合并）  
	              {mergeFiled:"address",premiseFiled:"orderNum"},  
	              {mergeFiled:"phonenum",premiseFiled:"orderNum"},  
	              {mergeFiled:"ordertime",premiseFiled:"orderNum"},  
	              {mergeFiled:"amount",premiseFiled:"orderNum"},  
	              {mergeFiled:"orderstate",premiseFiled:"orderNum"}
	             ];   
	    var dg = $("#tab");   //要合并的datagrid中的表格id  
	    var rowCount = dg.datagrid("getRows").length;  
	    var cellName;  
	    var span;  
	    var perValue = "";  
	    var curValue = "";  
	    var perCondition="";  
	    var curCondition="";  
	    var flag=true;  
	    var condiName="";  
	    var length = arr.length - 1;  
	    for (i = length; i >= 0; i--) {  
	        cellName = arr[i].mergeFiled;  
	        condiName=arr[i].premiseFiled;  
	        if(condiName){  
	            flag=false;  
	        }  
	        perValue = "";  
	        perCondition="";  
	        span = 1;  
	        for (row = 0; row <= rowCount; row++) {  
	            if (row == rowCount) {  
	                curValue = "";  
	                curCondition="";  
	            } else {  
	                curValue = dg.datagrid("getRows")[row][cellName];  
	                /* if(cellName=="ORGSTARTTIME"){//特殊处理这个时间字段 
	                    curValue =formatDate(dg.datagrid("getRows")[row][cellName],""); 
	                } */  
	                if(!flag){  
	                    curCondition=dg.datagrid("getRows")[row][condiName];  
	                }  
	            }  
	            if (perValue == curValue&&(flag||perCondition==curCondition)) {  
	                span += 1;  
	            } else {  
	                var index = row - span;  
	                dg.datagrid('mergeCells', {  
	                    index : index,  
	                    field : cellName,  
	                    rowspan : span,  
	                    colspan : null  
	                });  
	                span = 1;  
	                perValue = curValue;  
	                if(!flag){  
	                    perCondition=curCondition;  
	                }  
	            }  
	        }  
	    }  
	}  
	
	$(function() {
		//获取状态的下拉数据
		$.get("../../orderInfo/getOrderStatus.action",function(data){//后台请求  
		    var options=$("#orderstatus").combobox('options');  
		    options.textField="text";  
		    options.valueField="id";  
		    //加载数据  
		    $("#orderstatus").combobox("loadData",data);
		    if(data && data.length > 0){
		    	if(data[0].selected == true){
		    		$("#searchbtn").click();
		    	}
		    }
		},"json");    
		
		var flag;//判断新增还是修改
		$('#tab').datagrid({
			//title : '用户列表',
			iconCls : 'icon-edit',//图标 
			height : 'auto',
			nowrap : false,//当true的时候，会显示在一行里
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			url : '../../orderInfo/hotelList.action',
			remoteSort : false,
			loadMsg : '数据正在加载，请等待......',
			idField : 'id',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			checkOnSelect : false,
			selectOnCheck : false,
			rowStyler : function(index, record) {
			},
			columns : [ [
             {
            	field : 'orderId',
            	title : '订单ID',
            	hidden : true
             },{
            	field : 'orderNum',
            	title : '订单号',
            	hidden : true
             },{
            	field : 'storeName',
            	title : '酒店名',
            	align : 'center',
            	width : '13%'
             },{
				field : 'content',
				title : '菜品',
				align :	'center',
				width : '10%'
			}, {
				field : 'num',
				title : '数量',
				width : '3%',
				align :	'center',
				sortable : true
			}, {
				field : 'amount',
				title : '金额',
				width : '5%',
				align :	'center',
				sortable : true
			}, {
				field : 'deliveryCost',
				title : '配送费',
				width : '5%',
				align :	'center',
				sortable : true
			}, {
				field : 'address',
				title : '地址',
				width : '15%',
				align : 'center',
				sortable : true
			}, {
				field : 'phonenum',
				title : '电话',
				width : '10%',
				align : 'center',
				sortable : true
			} , {
				field : 'ordertime',
				title : '下单时间',
				width : '10%',
				align : 'center',
				sortable : true,
				formatter : function(value){
                    var date = new Date(value);
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
	                var h = date.getHours(); //获取系统时，
	                var mi =date.getMinutes(); //分
	                var s = date.getSeconds(); //秒
	                if(m < 10){
	                	m = "0"+m;
	                }
	                if(d < 10){
	                	d = "0"+d;
	                }
	                if(h < 10){
	                	h = "0"+h;
	                }
	                if(mi < 10){
	                	mi = "0"+mi;
	                }
	                if(s < 10){
	                	s = "0"+s;
	                }
	                return y + '-' +m + '-' + d +" "+h+":"+mi+":"+s; 
                }
			} , {
				field : 'finishTime',
				title : '完成时间',
				width : '10%',
				align : 'center',
				sortable : true,
				formatter : function(value){
					if(value){
						var date = new Date(value);
		                var y = date.getFullYear();
		                var m = date.getMonth() + 1;
		                var d = date.getDate();
		                var h = date.getHours(); //获取系统时，
		                var mi =date.getMinutes(); //分
		                var s = date.getSeconds(); //秒
		                if(m < 10){
		                	m = "0"+m;
		                }
		                if(d < 10){
		                	d = "0"+d;
		                }
		                if(h < 10){
		                	h = "0"+h;
		                }
		                if(mi < 10){
		                	mi = "0"+mi;
		                }
		                if(s < 10){
		                	s = "0"+s;
		                }
		                return y + '-' +m + '-' + d +" "+h+":"+mi+":"+s; 
					}else{
						return "";
					}
				}
			}, {
				field : 'amount',
				title : '评分',
				width : '5%',
				align : 'center',
				sortable : true,
				formatter : function(value){
					return "";
				}
			}, {
				field : 'orderstate',
				title : '状态',//0订单未确认，1订单确认还未送出，2订单完成
				width : '10%',
				align : 'center',
				sortable : true,
				formatter : function(value){
					if(value == 0){
						return "待订单确认";
					}else if(value == 1){
						return "待派送";
					}else if(value == 2){
						return "派送中";
					}else if(value == 3){
						return "完成";
					}else if(value == 4){
						return "拒接";
					}
					return "订单未确认";
				}
			}
			]],onLoadSuccess : function(data){
				//合并行数据
				mergeCells(null);
			}
		});
		
		
		//设置分页控件 
		var p = $('#tab').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 5, 10, 15, 20, 50 ],//可以设置每页记录条数的列表 
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页 共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录',
			onBeforeRefresh : function() {
				$(this).pagination('loading');
				// alert('before refresh');
				$(this).pagination('loaded');
				$('#tab').datagrid('reload');
			}
		});
		$("#searchbtn").click(function() {
			$("#tab").datagrid('load', serializeForm($("#mysearch")));
		});

		$("#clearbtn").click(function() {
			$("#mysearch").form('clear');
			$("#tab").datagrid('load', {});
		});

		function serializeForm(form) {
			var obj = {};
			$.each(form.serializeArray(),
					function(index) {
						if (obj[this['name']]) {
							obj[this['name']] = obj[this['name']] + ','	+ this['value'];
						} else {
							obj[this['name']] = this['value'];
						}
					});
			return obj;
		}
	});
</script>
<style type="text/css">
body {
	font-family: verdana, helvetica, arial, sans-serif;
	padding: 5px;
	font-size: 12px;
	margin: 0;
}
</style>
</head>
<body>

	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="north" title="用户查询" collapsed=true style="height: 100px;">
			<form id="mysearch" method="post">
				菜品:<input name="content" class="easyui-validatebox" 	required="true" missingMessage="请输入菜品名" value="" /> 
				<!-- 年龄:<input	name="age" class="easyui-validatebox" required="true" missingMessage="请输入年龄" value="" /> -->
				状态:<input id="orderstatus" name="orderstatus"  class="easyui-combobox" >  
				<a id="searchbtn" class="easyui-linkbutton">查询</a>
				<a id="clearbtn"	class="easyui-linkbutton">清空</a>
			</form>
		</div>
		<div region="center">
			<table id="tab"></table>
		</div>
	</div>

	 <div id="my_dialog" title="新增用户" modal=true draggable=false
		class="easyui-dialog" closed=true style="width: 700px; height: 400px;">
	</div> 
</body>

</html>