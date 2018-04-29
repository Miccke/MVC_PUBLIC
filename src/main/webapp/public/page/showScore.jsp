<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
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
			url : 'user/list',
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
			frozenColumns : [ [
			//你把 ck 换成 QID 试试
			{
				field : 'QID',
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'uid',
				title : 'uid',
				width : '10%'
			}, {
				field : 'loginName',
				title : '账号',
				width : '20%',
				sortable : true
			}, {
				field : 'nickName',
				title : '昵称',
				width : '20%',
				sortable : true
			}, {
				field : 'uEMail',
				title : '邮箱',
				width : '20%',
				sortable : true
			}, {
				field : 'uSex',
				title : '性别',
				width : '10%',
				sortable : true
			}, {
				field : 'uAge',
				title : '年龄',
				width : '15%',
				align : 'right',
				sortable : true
			} ] ],
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					flag = 'add';
					$("#my_dialog").dialog({
						title : "新增用户"
					})
					$("#form_user").get(0).reset();
					$("#my_dialog").dialog('open');
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					flag = 'edit';
					var arr = $("#tab").datagrid("getSelections");
					if (arr.length != 1) {
						$.messager.show({
							title : '提示信息',
							msg : '只能选择一行记录进行修改'
						});
					} else {
						$("#my_dialog").dialog({
							title : "修改用户"
						})
						$("#my_dialog").dialog('open');
						$("#form_user").get(0).reset();
						$("#form_user").form('load', {
							id : arr[0].id,
							userName : arr[0].userName,
							age : arr[0].age
						});

					}
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
								$.post('/egoTest/user/deleteUser.do', {
									ids : ids
								}, function(result) {
									$("#tab").datagrid("reload");
									$("#tab").datagrid('unselectAll'); //清空idFiled
									$.messager.show({
										title : '提示信息',
										msg : '操作成功!'
									});
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

		$("#btn1").click(
				function() {
					if ($('#form_user').form('validate')){
						$.ajax({
							type : 'POST',
							url : flag == 'add' ? '/egoTest/user/saveUser.do'
									: '/egoTest/user/updateUser.do',
							data : $('#form_user').serialize(),
							dataType : 'json',
							success : function(data) {
								//关闭窗口
								$("#my_dialog").dialog('close');
								//刷新datagrid
								$("#tab").datagrid('reload');
								//提示信息
								$.messager.show({
									title : data.status,
									msg : data.message
								});
							},
							error : function(data) {
								$.messager.show({
									title : '提示信息',
									msg : '错误返回'
								})
							}
						});
					} else {
						$.messager.show({
							title : '提示信息',
							msg : '数据验证不通过，不能保存！'
						})
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
		<div region="expand" title="用户查询" collapsed=true style="height: 100px;">
			<form id="mysearch" method="post">
				用户名:<input name="userName" class="easyui-validatebox"
					required="true" missingMessage="请输入姓名" value="" /> 年龄:<input
					name="age" class="easyui-validatebox" required="true"
					missingMessage="请输入年龄" value="" /> <a id="searchbtn"
					class="easyui-linkbutton">查询</a> <a id="clearbtn"
					class="easyui-linkbutton">清空</a>
			</form>
		</div>
		<div region="center">
			<table id="tab"></table>
		</div>
	</div>

	<div id="my_dialog" title="新增用户" modal=true draggable=false
		class="easyui-dialog" closed=true style="width: 700px; height: 400px;">
		<form id="form_user" action="">
			<table align="center">
				<tr>
					<td>编号:</td>
					<td><input type="text" id="id" name="id"
						class="easyui-validatebox" required="true" missingMessage="请输入编号" /></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input type="text" id="userName" name="userName"
						class="easyui-validatebox" required="true" missingMessage="请输入姓名" /></td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td><input type="password" id="age" name="age"
						class="easyui-validatebox" required="true" missingMessage="请输入年龄" /></td>
				</tr>
				<tr>
					<td colspan="2"><a id="btn1" class="easyui-linkbutton">确定</a>
						<a id="btn2" class="easyui-linkbutton">取消</a></td>
				</tr>

			</table>
		</form>
	</div>
</body>

</html>