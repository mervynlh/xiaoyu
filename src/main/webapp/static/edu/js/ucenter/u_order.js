/**
 * 个人中心订单.js
 */

$(function(){
	if (trxStatus == null || trxStatus == '') {
		trxStatus = "all";
	}
	$("#order_" + trxStatus).siblings("li").removeClass("current");
	$("#order_" + trxStatus).addClass("current");
	
});


//菜单切换
function menuToggle(trxStatus){
	$("#hiddenTrxStatus").val(trxStatus);
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
} 