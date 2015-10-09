function addCoupon() {
	$.ajax({
		url:baselocation+"/coupon/isCreateCoupon",
		type:"post",
		data:{},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog("", 13, "", "");
			}else if(result.message=='notCreate'){
				dialog("最多只能创建3种优惠券", 8, "", "");
			}
		}
	});
	
}

function saveCoupon() {
	var amount = $("#amount").val();// 面值1-2000
	if (amount < 1 || amount > 2000) {
		dialog("请输入面值在1-2000之间的数字", 10, "", "");
		return;
	}
	var count = $("#count").val();// 數量不超過100
	if (count < 0 || count > 100) {
		dialog("请输入张数不超过100的数字", 10, "", "");
		return;
	}
	var limitAmount;
	if ($("input[name='radio']:eq(0)").prop("checked")) {
		limitAmount = 0;
	} else if ($("input[name='radio']:eq(1)").prop("checked")) {
		limitAmount = $("#limitAmount").val();
		if (limitAmount <= 0) {
			dialog("限额不能小于等于0", 10, "", "");
		}
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(startTime==''||startTime==null||endTime==''||endTime==null){
		dialog("时间不能为空", 10, "", "");
		return;
	}
	var info = $("#info").val();
	$.ajax({
		url : baselocation + "/coupon/add",
		type : "post",
		data : {
			"coupon.amount" : amount,
			"coupon.totalNum" : count,
			"coupon.limitAmount" : limitAmount,
			"coupon.info" : info,
			"coupon.startTime":startTime,
			"coupon.endTime":endTime,
			"coupon.type" : 2
		},
		dataType : "json",
		success : function(result) {
			window.location.href=baselocation+"/uc/teacher/coupon";
		}
	});
}
function selectAmount(type) {
	if (type == 1) {
		$("#limitAmount").attr("disabled", "disabled");
	} else if (type == 2) {
		$("#limitAmount").removeAttr("disabled");
	}
}