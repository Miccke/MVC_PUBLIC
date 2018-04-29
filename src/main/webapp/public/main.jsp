<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.zxpublic.vo.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/public/";
	User user = (User)session.getAttribute("user");
	//判断用户是否登录，未登录则返回登录页面
	if(user == null){
		response.sendRedirect("login.jsp"); 
		return;
	}
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>信伊健康医家</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />


<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/main.js"></script>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script> 

<script type="text/javascript">

	var loginName = '<%=session.getAttribute("loginName")%>';
	var goEasy = new GoEasy({
		appkey : "BC-09d2856605de43419df8c01f1bfd590e"
	});
	//消息提醒
	goEasy.subscribe({
		channel : "zx_public_" + loginName,
		onMessage : function(message) {
			//alert(message.content);
			/* $("#messageUl").css("display","block") */
			$("#tipsImg").next()[0].style.display="block";
			var audio = document.getElementById("messageTips");
			audio.play();
			/* setTimeout(function(){
				$("#messageUl").css("display","none")
			},5000); */
		}
	});
	//打开某一菜单
	function openUrl(url, name,obj) {
		$(".active").removeClass("active")
		$("#navigationBar").text(name);
		$("#mainIf").attr('src', url);
		obj.parentNode.className = 'active';
	}
	//退出
	function loginOut() {
		$.post("logout", null, function(data) {
			if (data) {
				location.href = "login.jsp";
			} else {
				alert("账号异常，重新登录！");
			}
		}, "json");
	}
	//修改密码
	function updateMsg(obj){
		//debugger
		var ss = $("#mainIf")[0];
		ss.src = "page/updateMsg.jsp";
		$("#navigationBar").text("修改密码");
		$(".active").removeClass("active");
		obj.parentElement.parentElement.style.display = "none";
	}
	//修改个人资料
	function updateMyMessage(obj){
		var ss = $("#mainIf")[0];
		ss.src = "page/myMessage.jsp";
		$("#navigationBar").text("修改个人资料");
		$(".active").removeClass("active");
		obj.parentElement.parentElement.style.display = "none";
	}
	function testClick(){
		var status = $("#tipsImg").next()[0].style.display;
		if(status != "none"){
			var ss = $("#mainIf")[0];
			var uType = '<%=user.getuType()%>';
			if(uType == 1){
				//跳转页面
				ss.src = "page/servicOrder.jsp";
				$("#navigationBar").text("服务订单");

				$(".active").removeClass("active")
				$("li[name='menuLi']").each(function(obj){
					if($(this).children("a")[0].text.indexOf("服务订单")>=0){
						this.className = "active";
					}
				});
			}else if(uType == 2){
				//跳转页面
				ss.src = "page/order.jsp";
				$("#navigationBar").text("订单管理");
				$(".active").removeClass("active")
				$("li[name='menuLi']").each(function(){
					if($(this).children("a")[0].text.indexOf("订单管理")>=0){
						this.className = "active";
					}
				});
			}
			//隐藏红点
			$("#tipsImg").next()[0].style.display="none";
		}
 	}
</script>
</head>

<body>
	<audio id="messageTips">
	    <source = src="page/message.mp3" type="audio/mp3">
	    <!-- <source = src="hangge.ogg" type="audio/ogg"> -->
	</audio>
	<div class="navbar navbar-default" id="navbar">

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<div class="navbar-brand">
					信伊健康医家后台管理系统
				</div>
			</div>

			<div class="navbar-header pull-right" role="navigation" style="padding-top: 25px;">
				<ul class="nav ace-nav">
					<li class="purple" style=" width: 40px;text-align: center;">
						<img alt="" width="21" height="24" src="../image/orderTips.png" onclick="testClick()" id="tipsImg" >
						<i style="display: none;background: #FC0000;border-radius: 50%;width: 7px;height: 7px;top: 9px;right: 9px;position:absolute;"></i>
					</li>

					<li class="light-blue"><a data-toggle="dropdown"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="../imageShow?fileId=${sessionScope.user.imageUrl}" /> 
							<!-- ../../imageShow?fileId= -->
							<span class="user-info"> <small>欢迎光临,</small>
								${sessionScope.user.nickName}
						</span> <i class="icon-caret-down"></i>
					</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- <li>
							<a href="#"> <i class="icon-cog"></i> 设置</a>
							</li>

							<li>
							<a href="#"> <i class="icon-user"></i> 个人资料	</a>
							</li> -->

							<!-- <li class="divider"></li> -->
							<li>
								<a onclick="updateMyMessage(this)"><i class="icon-user"></i>个人资料</a>
							</li>
							<li>
								<a onclick="updateMsg(this)"> <i class="icon-edit"></i> 修改密码	</a>
							</li>
							<li>
								<a onclick="loginOut()"> <i class="icon-off"></i> 退出</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			/* try{ace.settings.check('main-container' , 'fixed')}catch(e){} */
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					/* try{ace.settings.check('sidebar' , 'fixed')}catch(e){} */
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<!-- <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div> -->

					<!-- <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div> -->
				</div>
				<!-- #sidebar-shortcuts -->
				<div>
					<ul class="nav nav-list">
						<li class="active" name="menuLi">
						<a onclick="openUrl('page/firstPage.jsp','首页',this)"> 
							<i class="icon-dashboard"></i> <span class="menu-text"> 首页 </span>
						</a></li>
	
						<c:forEach items="${user.menus }" var="menus">
							<li name="menuLi">
								<a onclick="openUrl('${menus.surl}','${menus.stitle}',this)" > 
									<i class="icon-text-width"></i> 
									<span class="menu-text"> 
										${menus.stitle}
									</span>
								</a>
							</li>
						</c:forEach>
						<!-- <li>
							<a onclick="openUrl('page/showScore.jsp','上传图片',this)">
								<i class="icon-text-width"></i>
								<span class="menu-text">
									上传图片
								</span>
							</a>
						</li>
						<li>
							<a onclick="openUrl('page/showWaiter.jsp','服务人员管理',this)">
								<i class="icon-text-width"></i>
								<span class="menu-text">
									服务人员管理
								</span>
							</a>
						</li>
						<li>
							<a onclick="openUrl('page/showStore.jsp','商家管理',this)">
								<i class="icon-text-width"></i>
								<span class="menu-text">
									商家管理
								</span>
							</a>
						</li>
						<li>
							<a onclick="openUrl('page/showDishType.jsp','菜品类型管理',this)">
								<i class="icon-text-width"></i>
								<span class="menu-text">
									菜品类型管理
								</span>
							</a>
						</li>
						<li>
							<a onclick="openUrl('page/showDish.jsp','菜品管理',this)">
								<i class="icon-text-width"></i>
								<span class="menu-text">
									菜品管理
								</span>
							</a>
						</li> -->
					</ul>
				</div>
				<div>
					<!-- <img alt="" src="../image/leftlowerbg.png" style="background-color: #3dc6b6;"> -->
				</div>
				<!-- <div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div> -->

			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a>首页</a></li>
						<li class="active" id="navigationBar">控制台</li>
					</ul>
					<!-- .breadcrumb -->

					<!-- <div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div> -->
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<!-- <div class="page-header">
						<h1>
							控制台 <small> <i class="icon-double-angle-right"></i> 查看
							</small>
						</h1>
					</div> -->

					<div class="row">
						<IFRAME runat="server" id="mainIf" name=nb
							src="page/firstPage.jsp" min-width="1100px" width="100%" style="min-height: 500px;"
							frameborder="no" border="0" marginwidth="0" marginheight="0"
							scrolling="no" allowtransparency="yes"></IFRAME>
					</div>
				</div>
			</div>

			<div class="ace-settings-container" id="ace-settings-container">
				<!-- <div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div> -->

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-navbar" /> <label class="lbl"
							for="ace-settings-navbar"> 固定导航条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-sidebar" /> <label class="lbl"
							for="ace-settings-sidebar"> 固定滑动条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-breadcrumbs" /> <label class="lbl"
							for="ace-settings-breadcrumbs">固定面包屑</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-rtl" /> <label class="lbl"
							for="ace-settings-rtl">切换到左边</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-add-container" /> <label class="lbl"
							for="ace-settings-add-container"> 切换窄屏 <b></b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
	</div>
	
</body>
</html>

