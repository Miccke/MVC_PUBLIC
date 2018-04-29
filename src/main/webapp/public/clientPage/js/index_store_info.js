
Window.onload=$.get("../../storeInfo/storlist",function(data){
		var storelist = "";
		for(var i=0;i<data.length;i++){	
			storelist += '<div class="sp_pr gls_index_dianjia_div"><a href="business.jsp?store_id='+data[i].storeId+'"><img src="../'+data[i].storeUrl+'" class="index_dianjia"><div class="text_p">'
						+'<p>'+data[i].storeName+'</p>'
						+'<div class="xing"><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span></div>'
						+'<span class="yue">月销量<em>'+data[i].salesVolume+'</em>件</span>'
						+'<span class="yue gls_index_bottom">起送价<em class="em_s">￥'+data[i].minimum+'</em>配送<em>￥'+data[i].deliveryCost+'</em></span>'
						+'</div><div class="jul">'+data[i].distance+'米</div></a></div>';
		}
		$(".product").html(storelist);
	},"json");