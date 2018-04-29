<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.zxpublic.vo.User" %>
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
	$(function() {
		//获取状态的下拉数据
		$.get("../../serveInfo/getServeStatus.action",function(data){//后台请求  
		    var options=$("#servetatuscomb").combobox('options');  
		    options.textField="text";  
		    options.valueField="id";  
		    //加载数据  
		    $("#servetatuscomb").combobox("loadData",data);
		    if(data && data.length > 0){
		    	if(data[0].selected == true){
		    		$("#searchbtn").click();
		    	}
		    }
		},"json");    
		//获取状态的下拉数据
		$.get("../../serveInfo/getServeType.action",function(data){//后台请求  
		    var options=$("#servetypecomb").combobox('options');  
		    options.textField="text";  
		    options.valueField="id";  
		    //加载数据  
		    $("#servetypecomb").combobox("loadData",data);
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
			//width : '400px',
			height : 'auto',
			nowrap : false,//当true的时候，会显示在一行里
			//striped : true,//各行变色
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			url : '../../serveInfo/servicList.action',
			//sortName: 'code', 
			//sortOrder: 'desc', 
			remoteSort : false,
			loadMsg : '数据正在加载，请等待......',
			idField : 'id',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			checkOnSelect : false,
			selectOnCheck : false,
			rowStyler : function(index, record) {
				//console.info(index);
				//console.info(record);
				/*if(record.age > 5000){
				 return "background:red";
				 }*/
			},
			
			/*
			//陪护对象
			private String escortperson;
			//自理情况
			private String selfcare; */
			
			columns : [ [
             {
            	field : 'id',
            	title : 'ID',
            	hidden : true
             },{
				field : 'userName',
				title : '姓名',
				align :	'center',
				width : '3%'
			}, {
				field : 'userPhone',
				title : '联系电话',
				width : '11%',
				align :	'center',
				sortable : true
			}, {
				field : 'waiterno',
				title : '服务人员工号',
				width : '5%',
				align :	'center',
				sortable : true
			},{
				field : 'waiterType',
				title : '类型',
				width : '3%',
				align :	'center',
				sortable : true,
				formatter : function(value){
					//1月嫂，2催乳师，3陪护师
					if(value == 1){
						return "月嫂";
					}else if(value == 2){
						return "催乳师";
					}else if(value == 3){
						return "陪护师";
					}else{
						return "";
					}
				}
			},  {
				field : 'remark',
				title : '备注',
				width : '10%',
				align : 'center',
				sortable : true
			} , {
				field : 'startTime',
				title : '下单时间',
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
		                return y + '-' +m + '-' + d + ' '+h+':'+mi+':'+s;
					}else{
						return "";
					}
				}
			}, {
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
		                return y + '-' +m + '-' + d + ' '+h+':'+mi+':'+s;
					}else{
						return "";
					}
				}
			}, {
				field : 'serveState',
				title : '状态',//0订单未确认，1订单确认还未送出，2订单完成
				width : '10%',
				align : 'center',
				sortable : true,
				formatter : function(value){
					//0订单未确认，1订单确认，2服务人员上门，3订单完成,4拒绝订单
					if(value == 0){
						return "订单未确认";
					}else if(value == 1){
						return "订单确认";
					}else if(value == 2){
						return "服务人员上门";
					}else if(value == 3){
						return "订单完成";
					}else if(value == 4){
						return "拒绝订单";
					}
					return "订单未确认";
				}
			},{
				field:'operate',
				title: '<div style="width:116px">操作</div>',
				width: '128px',
				align: 'center',
				formatter: function (value, rowData, rowIndex) {
					var str = "";
					if(rowData.serveState == 0){
						str = "<button class='confirmServeBtn' id='order_"+rowData.id+"'>确认订单</button> <button class='refusedServeBtn' id='order_"+rowData.id+"'>拒绝</button>";  
					}else if(rowData.serveState == 1){
						str = "<button class='sendServeBtn' id='order_"+rowData.id+"'>服务人员上门</button>";
					}
	        		return str;
	        	}
			}
			]],onLoadSuccess : function(data){
				//确认订单
				$(".confirmServeBtn").click(function(){
					//截取订单ID
					var id = this.id.substr(6,this.id.length);
					confirmServe(id);
				});
				//派送订单
				$(".sendServeBtn").click(function(){
					//截取订单ID
					var id = this.id.substr(6,this.id.length);
					sendServe(id);
				});
				//拒接订单
				$(".refusedServeBtn").click(function(){
					//截取订单ID
					var id = this.id.substr(6,this.id.length);
					refusedServeBtn(id);
				})
			}
		});
		//确认订单操作
		function confirmServe(serveId){
			 $.ajax({
					url : "../../serveInfo/confirmServe.action?serveId="+serveId,
					type : "post",
					async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
					dataType : "json",
					success : function(result) {
						 if (result != null && result.success) {
								$.messager.alert('操作提示','操作成功');
								//刷新数据列表数据
								$("#tab").datagrid('load', serializeForm($("#mysearch")));
						} else {
							//返回的数据为空时显示提示信息  
							alert("确认订单操作失败,请联系管理员！");
						} 
					},
					error : function(errorMsg) {
						//请求失败时执行该函数  
						alert("确认订单操作失败,请联系管理员！");
					}
				});
		}
		//派送订单操作
		function sendServe(serveId){
			$.ajax({
				url : "../../serveInfo/sendServe.action?serveId="+serveId,
				type : "post",
				async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
				dataType : "json",
				success : function(result) {
					if (result != null && result.success) {
						$.messager.alert('操作提示','操作成功');
						//刷新数据列表数据
						$("#tab").datagrid('load', serializeForm($("#mysearch")));
					}else{
						//返回的数据为空时显示提示信息  
						alert("派送订单操作失败,请联系管理员！");
					}
				},
				error : function(errorMsg) {
					//请求失败时执行该函数  
					alert("派送订单操作失败,请联系管理员！");
				}
			});
		}
		//拒接订单操作
		function refusedServeBtn(serveId){
			$.ajax({
				url : "../../serveInfo/refusedServeBtn.action?serveId="+serveId,
				type : "post",
				async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
				dataType : "json",
				success : function(result) {
					if (result != null && result.success) {
						$.messager.alert('操作提示','操作成功');
						//刷新数据列表数据
						$("#tab").datagrid('load', serializeForm($("#mysearch")));
					}else{
						//返回的数据为空时显示提示信息  
						alert("拒接订单操作失败,请联系管理员！");
					}
				},
				error : function(errorMsg) {
					//请求失败时执行该函数  
					alert("拒接订单操作失败,请联系管理员！");
				}
			});
		}
		
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
		<div region="north" title="订单查询" collapsed=true style="height: 100px;">
			<form id="mysearch" method="post">
				姓名:<input name="username" class="easyui-validatebox" 	required="true" missingMessage="请输入姓名" value="" /> 
				<!-- 年龄:<input	name="age" class="easyui-validatebox" required="true" missingMessage="请输入年龄" value="" /> -->
				类型:<input id="servetypecomb" name="servetype"  class="easyui-combobox" >  
				状态:<input id="servetatuscomb" name="servestate"  class="easyui-combobox" >
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