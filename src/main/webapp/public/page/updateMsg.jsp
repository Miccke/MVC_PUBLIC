<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"	href="public/easyui/css/easyui.css">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript"	src="public/easyui/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"	src="public/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		if("success" == '<%=request.getAttribute("message")%>'){
			alert("修改成功");
		}
		function conmitpwd(){
			var pwd1 = $("input[name='newpasswd']").val();
			var pwd2 = $("input[name='newpasswd2']").val();
			if(pwd1 == pwd2 && pwd1 && pwd2){
				if(pwd1.length < 6){
					$.messager.alert('操作提示','密码长度不能小于6');
				}else{
					$.ajax({
						url : "user/updatepwd",
						type : "post",
						async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）  
						dataType : "json",
						data : {
								passwd : $("input[name='passwd']").val(),
								newpasswd : $("input[name='newpasswd']").val(),
								},
						success : function(result) {
							debugger
							 if (result != null && result.SUCCESS) {
									$.messager.alert('操作提示',result.msg+"!",'info',function(){
										$.post("public/logout", null, function(data) {
											if (data) {
												window.parent.location.href= '<%=basePath%>';
											} else {
												$.messager.alert('错误','账号异常，重新登录！');
												window.parent.location.href= '<%=basePath%>';
											}
										}, "json");
									});
							} else {
								//返回的数据为空时显示提示信息  
								$.messager.alert('错误提示',result.msg+"!");
							} 
						},
						error : function(errorMsg) {
							//请求失败时执行该函数  
							$.messager.alert('错误提示',"密码修改失败，重新修改!");
						}
					});
				}
			}else{
				$.messager.alert('错误提示',"两次输入新密码不一致，请重新输入!");
			}
			$("input[name='passwd']").val("");
			$("input[name='newpasswd']").val("");
			$("input[name='newpasswd2']").val("");
			return;
		}
	</script>
	<div style="width: 350px;margin: 0px auto;">
		<div style="text-align: center;width: 100%;font-family: Microsoft YaHei;font-size: 18px;font-weight: bold;">修改密码</div>
		<form action="user/updatepwd" method="post">
			<table>
				<tr>
					<td class="ftd" style="text-align: right;width: 100px;font-size: 13px;font-family: Microsoft YaHei;">原始密码：</td>
					<td><input type="password" name="passwd" style="width: 200px"/></td>
				</tr>
				<tr>
					<td class="ftd" style="text-align: right;width: 100px;font-size: 13px;font-family: Microsoft YaHei;">新密码：</td>
					<td><input type="password" name="newpasswd" style="width: 200px"/></td>
				</tr>
				<tr>
					<td class="ftd" style="text-align: right;width: 100px;font-size: 13px;font-family: Microsoft YaHei;">再次输入新密码：</td>
					<td><input type="password" name="newpasswd2" style="width: 200px"/></td>
				</tr>
				
				<tr>
					<td colspan="2" style="text-align:center;font-size: 13px;font-family: Microsoft YaHei;">
						<!-- <input type="button" type="reset" value="重置"	style="width: 60px;"/>
						<input type="button" type="submit" value="提交" style="width: 60px;"/> -->
						
						<!-- <input type="submit" value="提交" class="inputSubmit"> -->
						<input onClick="conmitpwd()" value="提交" type="button">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>