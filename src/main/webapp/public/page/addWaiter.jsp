<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div style="font-size: 25px; font-weight: bold; text-align: center; margin-top: 5px; margin-bottom: 10px;">服务人员信息</div>
	<div class="addWaiterTb">
		<form id="waiterForm" action="">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ftd">
						<input name="id" id="id" style="display: none;"/>编号:</td>
					<td>
						<input type="text" id="waiterNo" name="waiterNo" disabled="disabled"></input>
					</td>
					<td class="ftd" rowspan="4">头像:</td>
					<td rowspan="4">
						<!-- <form action="attaUpload" method="post" enctype="multipart/form-data"> -->  
					        <img src="" id="headImg" style="width: 130px; height: 120px;">
					        <input name="waiterImageurl" id="waiterImageurl" type="hidden" />
					        <input name="file1" type="file" style="width: 65px;" size="20" id="fileUpload1" onchange ="uploadFile(this,1)">
    				</td>
				</tr>
				<tr>
					<td class="ftd">姓名:</td>
					<td><input type="text" id="waiterName" name="waiterName" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">价格:</td>
					<td><input type="text" id="waiterPrice" name="waiterPrice" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">身份证:</td>
					<td><input type="text" id="waiterIdcard" name="waiterIdcard" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">年龄:</td>
					<td><input class="easyui-validatebox" id="waiterAge" type="text" name="waiterAge" data-options="required:true"></input></td>
					
					<td class="ftd">认证信息:</td><!-- 平台认证信息 -->
					<td><input class="easyui-validatebox" id="waiterPlatformMsg" type="text" name="waiterPlatformMsg" data-options="required:true"></input></td> 
				</tr>
				<tr>
					<td class="ftd">籍贯省:</td>
					<td><input class="easyui-validatebox" id="waiterProvince" type="text" name="waiterProvince" data-options="required:true"></input>省</td>
					<td class="ftd">籍贯市:</td>
					<td><input class="easyui-validatebox" id="waiterCity" type="text" name="waiterCity" data-options="required:true"></input>市</td>
				</tr>
				<tr>
					<td class="ftd">服务类型:</td>
					<td>
						<input id="waiterType" name="waiterType"  class="easyui-combobox" data-options="
								valueField: 'waiterType',
								textField: 'waiterName',
								data: [{
									waiterType: 1,
									waiterName: '月嫂'
								},{
									waiterType: 2,
									waiterName: '催乳师'
								},{
									waiterType: 3,
									waiterName: '陪护师'
								}]"></input>
					</td>
					<td class="ftd">带宝宝总数:</td>
					<td><input class="easyui-validatebox" id="waiterBabycount" type="text" name="waiterBabycount" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td class="ftd">星座:</td>
					<td><input class="easyui-combobox" id="waiterConstellation" type="text" name="waiterConstellation" data-options="
								valueField: 'waiterConstellation',
								textField: 'waiterConstellation',
								data: [
									{waiterConstellation :'水瓶座' },
				                    {waiterConstellation :'双鱼座' },
				                    {waiterConstellation :'白羊座' },
				                    {waiterConstellation :'金牛座' },
				                    {waiterConstellation :'双子座' },
				                    {waiterConstellation :'巨蟹座' },
				                    {waiterConstellation :'狮子座' },
				                    {waiterConstellation :'处女座' },
				                    {waiterConstellation :'天枰座' },
				                    {waiterConstellation :'天蝎座' },
				                    {waiterConstellation :'射手座' },
				                    {waiterConstellation :'魔蝎座' }]"></input></td>
					<td class="ftd">学历:</td>
					<td><input class="easyui-combobox" id="waiterEducation" type="text" name="waiterEducation" data-options="
									valueField: 'waiterEducation',
									textField: 'education',
									data: [{
										waiterEducation: 1,
										education: '小学'
									},{
										waiterEducation: 2,
										education: '初中'
									},{
										waiterEducation: 3,
										education: '高中'
									},{
										waiterEducation: 4,
										education: '大专'
									},{
										waiterEducation: 5,
										education: '中专'
									},{
										waiterEducation: 6,
										education: '本科'
									}]"></input></td>
				</tr>
				<tr>
					
					<td class="ftd">星级:</td>
					<td><input class="easyui-combobox" id="waiterStarlevel" type="text" name="waiterStarlevel" data-options="
									valueField: 'waiterStarlevel',
									textField: 'starlevel',
									data: [{
										waiterStarlevel: 1,
										starlevel: '一星'
									},{
										waiterStarlevel: 2,
										starlevel: '二星'
									},{
										waiterStarlevel: 3,
										starlevel: '三星'
									},{
										waiterStarlevel: 4,
										starlevel: '四星'
									},{
										waiterStarlevel: 5,
										starlevel: '五星'
									}]"></input></td>
					<td class="ftd">职称:</td>
					<td><input type="text" id="jobTitle" name="jobTitle" data-options="required:true" ></input></td>
				</tr>
				</tr>
				<tr>
					<td class="ftd">图片附件:</td>
					<td colspan="3" style="padding-left: 10px;">
						<!-- <input style=" width: 550px; " type="text" id="waiterCharacteristics" name="waiterCharacteristics" data-options="required:true"></input>(多项特征请用“,”隔开) -->
						<div>
							<input name="attachId" id="attachId" type="hidden" />
					        <input name="file2" type="file" style="width: 65px;" size="20" id="fileUpload2" multiple="multiple" onchange ="uploadFile(this,2)">
						</div>
				        <div id="attacDiv">
				        </div>
					</td>
				</tr>
				<tr>
					<td class="ftd">特征:</td><!-- 多项特征用逗号隔开（aaa,bbb,ccc） -->
					<td colspan="3">
						<input style=" width: 550px; " type="text" id="waiterCharacteristics" name="waiterCharacteristics" data-options="required:true"></input>(多项特征请用“,”隔开)
					</td>
				</tr>
				<tr>
					<td class="ftd">从业经历:</td>
					<td colspan="3">
						<textarea  id="waiterExperience" name="waiterExperience" style="height:60px;width: 700px;"></textarea>
					</td>
				</tr>
				<tr>
					<td class="ftd">备注:</td>
					<td colspan="3">
						<textarea id="remark" name="remark" style="height:60px;width: 700px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function uploadFile(obj, type) {
		    $.ajaxFileUpload({
			        url : "../../attaUpload",  
			        secureuri : false,// 一般设置为false  
			        fileElementId : "fileUpload"+type,// 文件上传表单的id <input type="file" id="fileUpload" name="file" />  
			        dataType : 'JSON',// 返回值类型 一般设置为json  
			        data:null,//{'type': type, "type2":2},  
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
			        			if(type == 1){
				        			$("#headImg").attr("src","../../imageShow?fileId="+id);
			        			}else if(type == 2){
			        				$("#attacDiv").append("<div style=\"line-height: 20px;\"><a href=\"../../attach-download?attachId="+id+"\">"+name+"</a><img src=\"../image/delete.png\" onclick=\"deletePic(this,"+id+")\" style=\"margin-left:5px;position: relative;margin-right: 20px;width: 12px;height:12px;\"></div>");
			        			}
			        			flag = true;
			        		}
			        		//判断是上传头像还是附件
			        		if(type == 1){
			        			var imageid = $("#waiterImageurl").val();
			        			if(imageid){
			        				//不为空，则拼接起来
			        				$("#waiterImageurl").val(imageid + "," + ids);
			        			}else{
			        				//为空，则直接赋值
			        				$("#waiterImageurl").val(ids);
			        			}
			        		}else{
			        			var atta = $("#attachId").val();
			        			if(atta){
			        				//不为空，则拼接起来
			        				$("#attachId").val(atta + "," + ids);
			        			}else{
			        				//为空，则直接赋值
			        				$("#attachId").val(ids);
			        			}
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