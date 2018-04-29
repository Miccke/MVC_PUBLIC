<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.zxpublic.vo.User" %>
<%
	User user = (User)session.getAttribute("user");
	//判断用户是否登录，未登录则返回登录页面
	if(user == null){
		response.sendRedirect("login.jsp"); 
		return;
	}
%>
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
<script type="text/javascript"	src="../easyui/js/jquery.easyui.min.js"></script>
</head>

<body>
    <script type="text/javascript">
	    var uid =  '<%=user.getUid()%>';
	    //加载数据 
	    $.ajax({
			url : "../../user/getUserMsg",
			type : "post",
			data : {id : uid},
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
			dataType : "json",
			success : function(result) {
				if(result.user){
					//$("#waiterNo").removeAttr("disabled");
					var formArray = $("#userForm").serializeArray();
					var datas = result.user;
					var imageUrl = datas["imageUrl"];
					$("#headImg").attr("src","../../imageShow?fileId="+imageUrl);
					$("#loginName").text(datas['loginName']);
			    	$.each(formArray, function() {
			    		var name = this.name;
			    		var val = this.value;
		    			$("#"+this.name).val(datas[this.name]);
		    	    });
				}else{
					//alert("加载数据异常,请联系管理员！");
				}
			},
			error : function(errorMsg) {
				//请求失败时执行该函数  
				//alert("加载数据失败,请联系管理员！");
			}
		});
	    //修改数据
	    function saveMsg(){
	    	var data = {};
	    	var formArray = $("#userForm").serializeArray();
	    	$.each(formArray, function() {
	    		data[this.name] = this.value;
    	    });
	    	if(data){
	    		 //验证数据的格式
	    		 if(validateNumber(data['uAge']) && checkMobile(data['mobilphone']) && checkPhone(data['telephone']) && checkMail(data['uEMail'])){
	    		 }else{
	    			 return false;
	    		 }
	    	 }
	    	 $.ajax({
	 				url : "../../user/updateUserMsg",
					data : {
						"dataObject" : JSON.stringify(data)
					},
					type : 'post',
					async : true,
					success : function(result) {
						alert(result);
						/* $("#my_dialog").dialog("close");
						//刷新列表数据
				    	$("#tab").datagrid("reload"); */
					}
			});
	    }
	    function uploadFile(obj, type) {
		    $.ajaxFileUpload({
			        url : "../../attaUpload?type=headImg",  
			        secureuri : false,// 一般设置为false  
			        fileElementId : "fileUpload"+type,// 文件上传表单的id <input type="file" id="fileUpload" name="file" />  
			        dataType : 'JSON',// 返回值类型 一般设置为json  
			        data:null,//{'type': type, "type2":2},  
			        success : function(result){// 服务器成功响应处理函数  
			        	var res = JSON.parse(result);
			        	var ids = "";
			        	
			        	if(res.length > 0){
			        		var flag = false;
			        		var id = "";
			        		for(var i=0;i<res.length;i++){
			        			var name = res[i].name;
			        			id = res[i].id;
			        			$("#headImg").attr("src","../../imageShow?fileId="+id);
			        		}
			        		
	        				//为空，则直接赋值
	        				$("#imageUrl").val(id);
			        	}
			        },error : function(erre){// 服务器响应失败处理函数  
			            console.log("服务器异常");
			        }
			    });  
			    return false;  
			}
	    //邮箱验证
	    function checkMail(szMail){ 
	    	var szReg=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/; 
	    	var bChk=szReg.test(szMail); 
	    	if(!bChk){
	    		alert("邮箱格式错误，请重新输入！")
	    	}
    		return bChk;
    	} 
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
		//手机
		function checkMobile(str){
			if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(str))){
				alert("不是完整的11位手机号或者正确的手机号前七位");
				return false;
			}
			return true;
		} 
		//座机
		function checkPhone(str){
			var re = /^0\d{2,3}-?\d{7,8}$/;
			if(re.test(str)){
				return true;
			}else{
				alert("电话格式错误");
				return true;
			}
		}
    </script>
	<div class="addWaiterTb" style="margin: 0px auto;">
		<div style="text-align: center;width: 100%;font-family: Microsoft YaHei;font-size: 18px;font-weight: bold;">修改密码</div>
		<form action="user/updatepwd" method="post" id="userForm">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ftd">
						<input name="uid" id="uid" style="display: none;"/>账号:</td>
					<td>
						<label name="loginName" id="loginName"></label>
						<!-- <input type="text" id="waiterNo" name="waiterNo" disabled="disabled"></input> -->
					</td>
					<td class="ftd" rowspan="4">头像:</td>
					<td rowspan="4">
						<!-- <form action="attaUpload" method="post" enctype="multipart/form-data"> -->  
					        <img src="" id="headImg" style="width: 130px; height: 120px;">
					        <input name="imageUrl" id="imageUrl" type="hidden" />
					        <input name="file1" type="file" style="width: 65px;" size="20" id="fileUpload1" onchange ="uploadFile(this,1)">
    				</td>
				</tr>
				<tr>
					<td class="ftd">昵称:</td>
					<td><input type="text" id="nickName" name="nickName" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">年龄:</td>
					<td><input type="text" id="uAge" name="uAge" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">邮箱:</td>
					<td><input type="text" id="uEMail" name="uEMail" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">移动电话:</td>
					<td><input class="easyui-validatebox" id="mobilphone" type="text" name="mobilphone" data-options="required:true"></input></td>
					
					<td class="ftd">座机:</td><!-- 平台认证信息 -->
					<td><input class="easyui-validatebox" id="telephone" type="text" name="telephone" data-options="required:true"></input></td> 
				</tr>
				<tr>
					<td class="ftd">地址:</td>
					<td colspan="3">
						<input class="easyui-validatebox" id="address" type="text" name="address"></input>
					</td>
				</tr>
				<tr>
					<td class="ftd">经度:</td>
					<td><input class="easyui-validatebox" id="longitudex" type="text" name="longitudex" data-options="required:true"></input></td>
					<td class="ftd">纬度:</td>
					<td><input class="easyui-validatebox" id="latitudey" type="text" name="latitudey" data-options="required:true"></input></td>
				</tr>
			</table>
			
			<div style=" margin-top: 20px; text-align: center;">
				<input type="button" value="保存"  onclick="saveMsg()" style="width: 100px; height: 30px; ">
			</div>
		</form>
	</div>
</body>
</html>