window.onload = function(){
	var request=new UrlSearch(); //实例化
	var waiterType = 1+"";   	
	var waiterAge = $("#age").find("option:selected").text();
	var waiterStarlevel = $.trim($("#star").val());
	var waiterEducation = $.trim($("#culture").val());
	var waiterConstellation = $("#zodiac").find("option:selected").text();
	var waiterProvince = $("#sheng").find("option:selected").text();
	var waiterCity = $("#shi").find("option:selected").text();

	alert('age: '+waiterAge+' star： '+waiterStarlevel+' culture: '+waiterEducation+' zodiac: '+waiterConstellation+' sheng: '+waiterProvince+' shi： '+waiterCity);
	$.ajax({
		url:'../../swaiter/swaiterlist',
		data:{waiterType:waiterType,waiterAge:waiterAge,waiterStarlevel:waiterStarlevel,waiterEducation:waiterEducation,waiterConstellation:waiterConstellation,waiterProvince:waiterProvince},
		dataType:'json',
		async:false,
		type:'post',
		success:function(data){
			var servelist = "";
			for(var i=0;i<data.length;i++){
				servelist +='<div class="server_list"> '
					+'<div class="list_content">'
					+'<a href="../../swaiter/swaiterMessage?waiterNo='+data[i].waiterNo+'&waiterType=waiterType">'
					+'<img class="header_img_serve" src="../../imageShow?fileId='+data[i].waiterImageurl+'" style="width:5.5em;height:5.5em"/>'
					+'<div class="content_text">'
					+'<p style="padding-top:15px;"><span style="font-size:16px; color:#000">'+data[i].waiterName+'</span><span style="padding-left:30px;">'+data[i].jobTitle+'</span></p>'
					+'<p style="color: #ED7779"><span style="font-size:18px;">'+data[i].waiterPrice+'</span>元/26天</p>'
					+'<p class="jian" style="margin-top:10px;"><span>'+data[i].waiterAge+'岁</span><span style="margin-left:10px;margin-right:10px">|</span><span>'+data[i].waiterProvince+data[i].waiterCity+'人</span><span style="margin-left:10px;margin-right:10px">|</span><span><span>带过'+data[i].waiterBabycount+'个宝宝</span></p>	'	
					+'</div>'
					+'</a>'
					+'</div>'
					+'</div>';
			}
			$('.showServelist').html(servelist);
		}
	})
} 		


 