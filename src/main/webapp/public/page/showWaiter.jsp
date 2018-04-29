<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"	href="../css/css.css">
<link rel="stylesheet" type="text/css"	href="../easyui/css/easyui.css">
<link rel="stylesheet" type="text/css"	href="../easyui/css/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
<script type="text/javascript"	src="../easyui/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
<!--  <script type="text/javascript"	src="../easyui/js/jquery-1.9.1.js"></script> -->
<script type="text/javascript"	src="../easyui/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function() {
		var flag;//判断新增还是修改

		$('#tab').datagrid({
			title : '用户列表',
			iconCls : 'icon-edit',//图标 
			//width : '400px',
			height : 'auto',
			nowrap : false,//当true的时候，会显示在一行里
			striped : true,//各行变色
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			url : '../../swaiter/list.action',
			//sortName: 'code', 
			//sortOrder: 'desc', 
			remoteSort : false,
			loadMsg : '数据正在加载，请等待......',
			idField : 'id',
			singleSelect : true,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			checkOnSelect : true,
			selectOnCheck : true,
			//	remoteSort : false,
			//	sortName:'userName',
			//	sortOrder:'asc',
			rowStyler : function(index, record) {
				//console.info(index);
				//console.info(record);
				/*if(record.age > 5000){
				 return "background:red";
				 }*/
			},
			frozenColumns : [[
			//你把 ck 换成 QID 试试
			{
				field : 'QID',
				checkbox : true
			} ]],
			columns : [ [ {
				field : 'id',
				title : 'id',
				hidden : true
			}, {
				field : 'waiterNo',
				title : '编号',
				width : '120px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterName',
				title : '姓名',
				width : '50px',
				align : 'center',
				sortable : true
			},{
				field : 'waiterType',
				title : '类型',
				width : '50px',
				align : 'center',
				formatter : function(value){
					//0订单未确认，1订单确认，2服务人员上门，3订单完成,4拒绝订单
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
			},{
				field : 'waiterAge',
				title : '年龄',
				width : '50px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterIdcard',
				title : '身份证号',
				width : '170px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterPrice',
				title : '价格（/26天）',
				width : '90px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterProvince',
				title : '籍贯(省)',
				width : '50px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterCity',
				title : '籍贯(市)',
				width : '50px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterBabycount',
				title : '带宝宝总数',
				width : '90px',
				align : 'center',
				sortable : true
			}, {
				field : 'waiterCharacteristics',
				title : '特征',
				width : '100px',
				align : 'left',
				sortable : true
			}, {
				field : 'waiterPlatformMsg',
				title : '认证信息',
				width : '300px',
				align : 'left',
				sortable : true
			}, {
				field : 'waiterExperience',
				title : '从业经历',
				width : '300px',
				align : 'left',
				sortable : true
			}, {
				field : 'waiterConstellation',
				title : '星座',
				width : '50px',
				align : 'left',
				sortable : true
			}, {
				field : 'waiterStarlevel',
				title : '星级',
				width : '50px',
				align : 'left',
				formatter : function(value){
					if(value == 1){
						return "一星";
					}else if(value == 2){
						return "二星";
					}else if(value == 3){
						return "三星";
					}else if(value == 4){
						return "四星";
					}else if(value == 5){
						return "五星";
					}else{
						return "";
					}
				}
			}, {
				field : 'waiterEducation',
				title : '学历',
				width : '50px',
				align : 'left',
				formatter : function(value){
					if(value == 1){
						return "小学";
					}else if(value == 2){
						return "初中";
					}else if(value == 3){
						return "高中";
					}else if(value == 4){
						return "大专";
					}else if(value == 5){
						return "中专";
					}else if(value == 6){
						return "本科";
					}else{
						return "";
					}
				}
			}, {
				field : 'jobTitle',
				title : '职称',
				width : '50px',
				align : 'left',
				sortable : true
			}, {
				field : 'remark',
				title : '备注',
				width : '100px',
				align : 'left',
				sortable : true
			}] ],
			
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					flag = 'add';
					$("#my_dialog").dialog({
							title : "新增用户",
			                width:900,
			                height:450, //显示效果中拖动且缩短了高度
			                left:400,
			                top:10,
						    closed: false,
						    cache: false,
			                //title:"对话框1",
			                resizable:true,//可缩放 
						    href: 'addWaiter.jsp',
						    onClose:function(){
					        	//alert("关闭了");
					        },
			                buttons:[
								{  
								    text:'保存',
								    handler:function(){
								    	var data = {};
								    	var formArray = $("#waiterForm").serializeArray();
								    	 $.each(formArray, function() {
								    		 data[this.name] = this.value;
							    	     });
								    	 $.ajax({
												url : "../../swaiter/CRUDMsg",
												data : {
													"dataObject" : JSON.stringify(data)
												},
												type : 'post',
												async : true,
												success : function(result) {
													alert(result);
													$("#my_dialog").dialog("close");
												}
											});
									}
								},{
			                        text:'关闭',
			                        handler:function(){
			                            //关闭本对话框
			                            $("#my_dialog").dialog("close");
			                        }
			                    }
			                ]
			            }); 
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					flag = 'edit';
					//获取选中行数据
					var row = $('#tab').datagrid('getSelected');
					if(row == null){
						alert("请选择一行数据进行编辑");
						return false;
					}
					$("#my_dialog").dialog({
			                width:900,
			                height:450, //显示效果中拖动且缩短了高度
			                left:400,
			                top:10,
			                title : "编辑用户",
						    closed: false,
						    cache: false,
			                //title:"对话框1",
			                resizable:true,//可缩放 
						    href: 'addWaiter.jsp',
						    onClose:function(){
					        	//刷新数据
					        },
					        onOpen:function(){
					        	if(flag == 'edit'){
						        	$.ajax({
										url : "../../swaiter/getSWaiterMsg",
										type : "post",
										data : {id : row.id},
										async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
										dataType : "json",
										success : function(result) {
											if(result.SWaiterMsg){
												$("#waiterNo").removeAttr("disabled");
												var formArray = $("#waiterForm").serializeArray();
												var datas = result.SWaiterMsg;
												var waiterImageurl = datas["waiterImageurl"];
												$("#headImg").attr("src","../../imageShow?fileId="+waiterImageurl);
										    	$.each(formArray, function() {
										    		var name = this.name;
										    		var val = this.value;
										    		if(this.name == "waiterType"){
										    			$('#waiterType').combobox('setValue', result.SWaiterMsg[this.name]);
										    		}else if(this.name == "waiterEducation"){
										    			$('#waiterEducation').combobox('setValue', result.SWaiterMsg[this.name]);
										    		}else if(this.name == "waiterStarlevel"){
										    			$('#waiterStarlevel').combobox('setValue', result.SWaiterMsg[this.name]);
										    		}else if(this.name == "waiterConstellation"){
										    			$('#waiterConstellation').combobox('setValue', result.SWaiterMsg[this.name]);
										    		}else{
										    			$("#"+this.name).val(result.SWaiterMsg[this.name]);
										    		}
									    	    });
										    	$("#waiterNo").attr("disabled","disabled");
										    	var rlist = datas.rlist;
												if(rlist.length > 0){
													var ids = "";
													var rflag = false;
													for (var i = 0; i < rlist.length; i++) {
														var atta = rlist[i];
														var attaid = atta["relationId"];
														var attaname = atta["relationName"];
												    	$("#attacDiv").append("<div style=\"line-height: 20px;\"><a href=\"../../attach-download?attachId="+attaid+"\">"+attaname+"</a><img src=\"../image/delete.png\" onclick=\"deletePic(this,"+attaid+")\" style=\"margin-left:5px;position: relative;margin-right: 20px;width: 12px;height:12px;\"></div>");
												    	if(rflag){
												    		ids += ids + "," + attaid;
												    	}
												    	rflag = true;
													}
													//给附件ID的 input赋值 
						        					$("attachId").val(ids);
												}
											}else{
												//alert("加载数据异常,请联系管理员！");
											}
										},
										error : function(errorMsg) {
											//请求失败时执行该函数  
											//alert("加载数据失败,请联系管理员！");
										}
									});
					        	}
					        	
					        },
			                buttons:[
								{
								    text:'保存',
								    handler:function(){
								    	var data = {};
								    	var formArray = $("#waiterForm").serializeArray();
								    	 $.each(formArray, function() {
								    		 data[this.name] = this.value;
							    	     });
								    	 if(data){
								    		 //验证数据的格式
								    		 if(validateIdCard(data['waiterIdcard']) && validateNumber(data['waiterIdcard']) && validateNumber(data['waiterBabycount'])){
								    		 }else{
								    			 return false;
								    		 }
								    	 }
								    	 $.ajax({
												url : "../../swaiter/CRUDMsg",
												data : {
													"dataObject" : JSON.stringify(data)
												},
												type : 'post',
												async : true,
												success : function(result) {
													alert(result);
													$("#my_dialog").dialog("close");
													//刷新列表数据
											    	$("#tab").datagrid("reload");
												}
										});
									}
								},{
			                        text:'关闭',
			                        handler:function(){
			                            //关闭本对话框
			                            $("#my_dialog").dialog("close");
			                        }
			                    }
			                ]
			            }); 
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var arr = $("#tab").datagrid("getSelections");
					if (arr.length <= 0) {
						$.messager.show({
							title : '提示信息',
							msg : '只能选择一行记录进行删除!'
						});
					} else {
						$.messager.confirm("提示信息", "确认删除吗", function(r) {
							if (r) {
								var ids = '';
								for (var i = 0; i < arr.length; i++) {
									ids += arr[i].id + ",";
								}
								ids = ids.substring(0, ids.length - 1);
								$.ajax({
									url : '../../swaiter/deleteMsgById',
									data : {ids : ids},
									type : 'post',
									async : true,
									success : function(result){
										$("#tab").datagrid("reload");
										$("#tab").datagrid('unselectAll'); //清空idFiled
										$.messager.show({
											title : '提示信息',
											msg : '操作成功!'
										});
									}
								});
	
							} else {
								return;
							}
						})
					}
				}
			}, '-', {
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					$("#lay").layout('expand', 'north');
				}
			} ],
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

		$("#btn2").click(function() {
			$("#my_dialog").dialog('close');
		});

		$("#searchbtn").click(function() {
			$("#tab").datagrid('load', serializeForm($("#mysearch")));
		});

		$("#clearbtn").click(function() {
			$("#mysearch").form('clear');
			$("#tab").datagrid('load', {});
		});
		//正数验证
		function validateNumber(num){
			var regNumber= /^[+]{0,1}(\d+)$/;
			if(!regNumber.test(num)){
				alert("请输入整数数字");
				return false;
			}else{
				return true;
			}
		}
		/*
		 * 身份证15位编码规则：dddddd yymmdd xx p
		 * dddddd：6位地区编码
		 * yymmdd: 出生年(两位年)月日，如：910215
		 * xx: 顺序编码，系统产生，无法确定
		 * p: 性别，奇数为男，偶数为女
		 * 
		 * 身份证18位编码规则：dddddd yyyymmdd xxx y
		 * dddddd：6位地区编码
		 * yyyymmdd: 出生年(四位年)月日，如：19910215
		 * xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
		 * y: 校验码，该位数值可通过前17位计算获得
		 * 
		 * 前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
		 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
		 * 如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替
		 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
		 * i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
		 */
		function validateIdCard(idCard){
			 debugger
			//15位和18位身份证号码的正则表达式
			var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

		 	//如果通过该验证，说明身份证格式正确，但准确性还需计算
		 	if(regIdCard.test(idCard)){
		  		if(idCard.length==18){
			   		var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
			   		var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
			   		var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
			   		for(var i=0;i<17;i++){
			    		idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
			   		}
	
				   	var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
				   	var idCardLast=idCard.substring(17);//得到最后一位身份证号码
	
				    //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
				   	if(idCardMod==2){
				   		if(idCardLast=="X"||idCardLast=="x"){
				     		alert("恭喜通过验证啦！");
							return true;
				    	}else{
				     		alert("身份证号码错误！");
							return false;
				    	}
				   	}else{
				    	//用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
				    	if(idCardLast==idCardY[idCardMod]){
				     		alert("恭喜通过验证啦！");
							return true;
				    	}else{
				     		alert("身份证号码错误！");
							return false;
				    	}
				   	}
			  	} 
			 }else{
			 	alert("身份证格式不正确!");
				return false;
			 }
			return false;
		}
		 
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
</head>
<body>
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="north" title="用户查询" collapsed=true style="height: 100px;">
			<form id="mysearch" method="post">
					用户名:<input name="userName" class="easyui-validatebox" required="true" missingMessage="请输入姓名" value="" /> 
					年龄:<input	name="age" class="easyui-validatebox" required="true" missingMessage="请输入年龄" value="" /> 
					<a id="searchbtn" class="easyui-linkbutton">查询</a> 
					<a id="clearbtn" class="easyui-linkbutton">清空</a>
			</form>
		</div>
		<div region="center">
			<table id="tab"></table>
		</div>
	</div>

	<div id="my_dialog" title="新增用户" modal=true draggable=false	class="easyui-dialog" closed=true style="width: 700px; height: 400px;">
	</div>
</body>
</html>