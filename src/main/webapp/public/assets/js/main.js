jQuery(function(a) {
	// 绑定click事件
	$(".dropdown-toggle").on('click', function() {
		if($(this).next("ul").css("display") != "block"){
			$(".pull-right.dropdown-menu.dropdown-close.dropdown-caret").css("display","none");
			$(this).next("ul").css("display","block"); 
		}else{
			$(this).next("ul").css("display","none"); 
		}
	})
})