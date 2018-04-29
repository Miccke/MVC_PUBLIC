<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
function uploadFile(obj, type) {
	debugger
    $.ajaxFileUpload({
	        url : "attaUpload",  
	        secureuri : false,// 一般设置为false  
	        fileElementId : "fileUpload"+type,// 文件上传表单的id <input type="file" id="fileUpload" name="file" />  
	        dataType : 'JSON',// 返回值类型 一般设置为json  
	        data:null,//{'type': type, "type2":2},  
	        success : function(result){// 服务器成功响应处理函数  
	        	debugger
	        	var res = JSON.parse(result);
	        	if(res.length > 0){
	        		for(var i=0;i<res.length;i++){
	        			var name = res[i].name;
	        			var id = res[i].id;
	        		}
	        	}
	        },error : function(erre){// 服务器响应失败处理函数  
	            console.log("服务器异常");
	        }
	    });  
	    return false;  
	}
</script>
<style>
	#uploadImg{ font-size:12px; overflow:hidden; position:absolute}
	#file{ position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;}
</style>
<title>文件上传下载</title>  
</head>  
<body>  
    <form action="attaUpload" method="post" enctype="multipart/form-data">  
        选择文件:<input type="file" name="file" width="120px">  
        <input type="submit" value="上传1">  
    </form>  
    
	    <input name="file1" type="file" size="20" id="fileUpload1" multiple="multiple" onchange ="uploadFile(this,1)">

    
    <form action="attaUpload" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="file" multiple="multiple" width="120px">  
        <input type="submit" value="上传2">
    </form>  
    <hr>  
    <form action="file/down" method="get"  enctype="multipart/form-data">  
        <input type="submit" value="下载">  
    </form>  
    <a href="attach-download?attachId=6">下载东西</a>
    <center>
        <h2>${message}</h2>
    </center>
    
    <img alt="" src="imageShow?fileId=5">
</body>  
</html>  