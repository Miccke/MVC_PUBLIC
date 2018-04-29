<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div style="font-size: 25px; font-weight: bold; text-align: center; margin-top: 5px; margin-bottom: 10px;">商家信息</div>
	<div class="addWaiterTb">
		<form id="storeForm" action="">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ftd">餐厅名称:</td>
					<td>
						<input type="text" id="storeName" name="storeName" data-options="required:true"></input>
					</td>
					<td class="ftd" rowspan="4">餐厅logo:</td>
					<td rowspan="4">
						<!-- <form action="attaUpload" method="post" enctype="multipart/form-data"> -->  
					       	<img src="" id="headImg" style="width: 130px; height: 120px;">
					        <input name="storeUrl" id="storeUrl" type="hidden" />
					        <input name="file1" type="file" style="width: 65px;" size="20" id="fileUpload1" onchange ="uploadFile(this,1)">
    				</td>
				</tr>
				<tr>
					<td class="ftd">用户名:</td>
					<td><input type="text" id="loginName" name="loginName" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">密码:</td>
					<td><input type="text" id="passWord" name="passWord" disabled="disabled"></input></td>
				</tr>
				<tr>
					<td class="ftd">餐厅法人:</td>
					<td><input type="text" id="nickName"name="nickName" data-options="required:true"></input></td> 
				</tr>
				<tr>
					<td class="ftd">年龄:</td>
					<td><input type="text" id="uAge" name="uAge" data-options="required:true"></input></td>
					<td class="ftd">性别:</td>
					<td><input id="uSex" name="uSex"  class="easyui-combobox" data-options="
								valueField: 'uSex',
								textField: 'uSexValue',
								data: [{
									uSex: 1,
									uSexValue: '男'
								},{
									uSex: 2,
									uSexValue: '女'
								}]"></input></td>
				</tr>
				<tr>
					<td class="ftd">用户类型:</td>
					<td>
						<input id="uType" name="uType"  class="easyui-combobox" data-options="
								valueField: 'uType',
								textField: 'uName',
								data: [{
									uType: 1,
									uName: '平台人员'
								},{
									uType: 2,
									uName: '商家'
								},{
									uType: 3,
									uName: '普通用户'
								}]"></input>
					</td>
					<td class="ftd">座机:</td>
					<td><input type="text" id="telephone" name="telephone" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">联系方式:</td>
					<td><input type="text" id="mobilphone" name="mobilphone" data-options="required:true"></input></td>
					<td class="ftd">餐厅地址:</td>
					<td><input type="text" id="address" name="address" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">起送价:</td>
					<td><input type="text" id="minimum" name="minimum" data-options="required:true"></input></td>
					<td class="ftd">配送费:</td>
					<td><input type="text" id="deliveryCost"name="deliveryCost" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">经度:</td>
					<td><input type="text" id="longitudex" name="longitudex" data-options="required:true"></input></td>
					<td class="ftd">纬度:</td>
					<td><input type="text" id="latitudey" name="latitudey" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">营业时间:</td>
					
					<td class="ftd"><input class="easyui-timespinner" id="openingStart" name="openingStart"  style="width:80px;" required="required" data-options="min:'08:30',showSeconds:true">
								    至<input class="easyui-timespinner" id="openingEnd" name="openingEnd" style="width:80px;" required="required" data-options="min:'08:30',showSeconds:true">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		function uploadFile(obj, type) {
			var typeValue = 'headImg';
		    $.ajaxFileUpload({
			        url : "../../attaUpload",  
			        secureuri : false,// 一般设置为false  
			        fileElementId : "fileUpload"+type,// 文件上传表单的id <input type="file" id="fileUpload" name="file" />  
			        dataType : 'JSON',// 返回值类型 一般设置为json  
			        data:{type:type},//{'type': type, "type2":2},  
			        success : function(result){// 服务器成功响应处理函数  
			        	var res = JSON.parse(result);
			        	var ids = "";
			        	
			        	if(res.length > 0){
			        		var flag = false;
			        		for(var i=0;i<res.length;i++){
			        			var name = res[i].name;
			        			var id = res[i].id;
			        			if(i == res.length-1){
			        				ids += id;
			        			}else{
			        				ids += id + ",";	
			        			}
			        			
				        		$("#headImg").attr("src","../../imageShow?fileId="+id);
			        			
			        			flag = true;
			        		}
			        		//判断是上传头像还是附件
			        		
			        		var imageid = $("#storeUrl").val();
		        			if(imageid){
		        				//不为空，则拼接起来
		        				$("#storeUrl").val(imageid + "," + ids);
		        			}else{
		        				//为空，则直接赋值
		        				$("#storeUrl").val(ids);
		        			}	
			        	}
			        },error : function(erre){// 服务器响应失败处理函数  
			            console.log("服务器异常");
			        }
			    });  
			    return false;  
			}
		//附件移除操作
		function deletePic(obj,id){
			//移除页面内容
			obj.parentElement.remove();
			//获取隐藏input的值
			var ids = $("#attachId").val();
			var idsStr = ids.split(",");
			//新建一个数据，用于接收未被移除的数据
			var idsStr2 = [];
			for(var i=0;i<idsStr.length;i++){
				if(idsStr[i] != id){
					idsStr2.push(idsStr[i]);
				}
			}
			var ids2 = ""
			//遍历新数据，拼接id串
			for(var i=0;i<idsStr2.length;i++){
				if(i == idsStr2.length - 1){
					ids2 += idsStr2[i];
				}else{
					ids2 += idsStr2[i] + ",";
				}
			}
			console.log(ids2);
			$("#attachId").val(ids2)
			//alert(id);
		}
	</script>
</body>
</html>