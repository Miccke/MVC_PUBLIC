<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String openid = session.getAttribute("openid").toString();
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css">
<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css"
	rel="stylesheet" type="text/css" />
<title>催乳师服务</title>
</head>

<body>

    <div class="top_c">
    	<div style="width:60%; float:left">
        <a href="index.jsp" class="iconfont icon-jiantou-copy-copy"></a>
        <p class="titi" style="float:right">催乳师选择</p>
        </div>
        <div style="float:right; margin-top:0.714em; margin-right:0.714em; color:#F93; font-size:1.1em"  onClick="submit();">提交</div>
    </div>
	<!-- 头部 -->
	<div class="head_img">
		<img class="h_img" src="images/cuirushi/top_image.png">
	</div>
	<form>

		<div class="check" id="check_box"  style="float:left">
			<img src="images/cuirushi/cr_requirement.png"
				style="margin: 0 15px 0px 10px; width: 1.5em; height: 1.5em" /> <span>催乳师要求</span>
				<ul class="yuesao_ul_li" style="font-family: '微软雅黑'">
					<li class="check_li"><span onClick="check_li(this);"><input
							type="checkbox" value="个人素养" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" /> 个人素养</span>
					</li>
					<li><span onClick="check_li(this);"><input
							type="checkbox" value="性格" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" />性格</span></li>
					<li><span onClick="check_li(this);"><input
							type="checkbox" value="经验" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" /> 经验</span></li>
					<li><span onClick="check_li(this);"><input
							type="checkbox" value="专业" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" /> 专业</span></li>
					<li><span onClick="check_li(this);"><input
							type="checkbox" value="口碑" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" /> 口碑</span></li>
					<li><span onClick="check_li(this);"><input
							type="checkbox" value="个性定制" name="choose" id="geren" /><img
							src="cr_requirement_nochoose.png"
							style="width: 1em; margin-right: 1em" class="check_img" /> 个性定制</span>
					</li>
				</ul>
		</div>

		<div class="yuesao_age" style="margin-top:20px; float:left; width:100%; border-top:1px  #E1E1E1 solid" >
        	<br/>
			<img src="images/cuirushi/cr_age.png"
				style="margin: 0 15px 0px 10px; width: 1.5em; height: 1.5em"/> <span>催乳师年龄</span>
			<div style="margin: -22px 0px 0px 120px; width: 60%">
				<select id="age"
					style="-webkit-appearance: none; border: none;  font-size: 16px; background: #FFF; margin-left:2em">
					<option value="28-38岁">28-38岁</option>
					<option value="38-48岁">38-48岁</option>
					<option value="48-58岁">48-58岁</option>
				</select>
			</div>
			<div style="margin: -21px 0px 0px 325px; float: right">
				<i class="iconfont icon-jiantou"></i>
			</div>
		</div>
        
        <div class="yuesao_age" style="margin-top:20px; float:left; width:100%; border-top:1px  #E1E1E1 solid" >
        	<br/>
			<img src="images/cuirushi/cr_reason.png"
				style="margin: 0 15px 0px 10px; width: 1.5em; height: 1.5em"/> <span>催乳需求</span>
			<div style="margin: -22px 0px 0px 120px; width: 60%">
				<select id="lactationdemand"
					style="-webkit-appearance: none; border: none;  font-size: 16px; background: #FFF; margin-left:2em">
					<option value="乳汁淤积">乳汁淤积</option>
					<option value="乳汁匮乏">乳汁匮乏</option>
					<option value="无乳汁">无乳汁</option>
				</select>
			</div>
			<div style="margin: -21px 0px 0px 325px; float: right">
				<i class="iconfont icon-jiantou"></i>
			</div>
		</div>
        
		<div class="yuesao_age" style="margin-top:20px; float:left; width:100%; border-top:1px #E1E1E1 solid" >
        	<br/>
			<img src="images/cuirushi/cr_worktime.png"
				style="margin: 0 15px 0px 10px; width: 1.5em; height: 1.5em"/> <span>到岗时间</span>
			<div style="margin: -24px 0px 0px 120px; width: 60%">
				<input type="date" id="dutytime"
					style="-webkit-appearance: none; border: none; width: 100%; font-size: 16px" />
			</div>
			<div style="margin: -21px 0px 0px 325px; float: right">
				<i class="iconfont icon-jiantou"></i>
			</div>
            <br/>
		</div>


		<div class="person_info" style="padding: 20px 0 10px 0px; border-top:1px #E1E1E1 solid; float:left; width:100%">
			<p style="text-align: center; color: black; font-weight: 200;">————
				个人信息 ————</p>
			<div class="person_info" id="info" style="margin: 20px 0 0 0">
            	
						<p><span style="font-size: 16px; color: gray; margin-left:1em">姓名</span> 
                        <input type="text" placeholder="请输入姓名"
							style="-webkit-appearance: none; border: none; margin-left: 40px; font-size: 16px;"></p>
						<p style="border-bottom:1px #E1E1E1 solid; width:100%"></p>
						<p><span style="font-size: 16px; color: gray; margin-left:1em">手机号</span> <input
							type="text" placeholder="请输入手机号"
							style="-webkit-appearance: none; border: none; margin-left: 25px; font-size: 16px;"></p>
						<p style="border-bottom:1px #E1E1E1 solid; width:100%"></p>
						<p><span style="font-size: 16px; color: gray; margin-left:1em">备注</span> 
                        <input type="text" placeholder="备注信息..."
							style="-webkit-appearance: none; border: none; margin-left: 40px; font-size: 16px;"></p>
						<p style="border-bottom:1px #E1E1E1 solid; width:100%"></p>
			
			</div>
	</div>

	</form>
	<script src="js/jquery.min.js" type="text/javascript"></script>
	
	
	<script type="text/javascript">
		function check_li(a) {
			if (a.childNodes[1].getAttribute("src", 2) == "cr_requirement_nochoose.png") {
				a.childNodes[1].src = "cr_requirement_choose.png";
				a.childNodes[0].checked = true;
			} else {
				a.childNodes[1].src = "cr_requirement_nochoose.png";
				a.childNodes[0].checked = false;
			}
		}
		
		function submit(){
			var dataObject = new Object();
			dataObject.serveType = "2";//1月嫂，2催乳师，3陪护师
			
			var form = document.getElementById("check_box");
			var input_elements = form.getElementsByTagName("input")
			var cheak_value_arr = new Array();//此数组存储CheckBox的value
			
			for (var i=0;i<input_elements.length;i++){
				if(input_elements[i].checked){
					cheak_value_arr.push(input_elements[i].value);
				}
			}
			dataObject.personalNeed = cheak_value_arr+"";
			
			var select = document.getElementById("age");
			dataObject.ageBracket = select.value;//年龄段
			
			dataObject.lactationdemand = document.getElementById("lactationdemand").value; //陪护对象
			
			var danggang = document.getElementById("dutytime");
			dataObject.dutytime = danggang.value;//产期
			
			var person_info = document.getElementById("info");
			var infos = person_info.getElementsByTagName("input");
			
			dataObject.userName = infos[0].value+"";
			dataObject.phoneNum = infos[1].value+"";
			dataObject.remark = infos[2].value+"";
			dataObject.openid = '<%=openid%>';

			$.ajax({
				url : "../../serveInfo/insertServe",
				//data : JSON.stringify({'dataObject':dataObject}),
				data : {"dataObject":JSON.stringify(dataObject)},
				type  : 'post',
				async : true,
				success : function(result) {
					location.href='convenience.jsp'; 	
				}
			});		
		//	alert("cheak_value_arr:"+cheak_value_arr+"select_value:"+select_value+"time_value:"+time_value+"info_value_arr:"+info_value_arr);	
		}
	</script>
</body>
</html>
