function againDingdan(again) {
		var dataObject = new Object();
		dataObject.orderNum = again.id;
		orderNum = again.id;
		dataObject.strorId = again.className;
		$.ajax({
			url : "../../orderInfo/insertAgainOrder",
			data : {
				"dataObject" : JSON.stringify(dataObject)
			},
			type : 'post',
			async : true,
			success : function(result) {
				alert('新的订单已经生成');
				location.href = "convenience.jsp";
			}
		});
	}