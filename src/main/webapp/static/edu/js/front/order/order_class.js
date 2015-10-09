$(function(){
	// 手机号
	$("#mobile").blur(function(){
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test($(this).val())) {
			$("#mobile_error").show();
			return;
		} else {
			$("#mobile_error").hide();
		}
	});
	$("#studentName").blur(function(){
		if ($(this).val()==null||$(this).val()=="") {
			$("#studentName_error").show();
			return;
		} else {
			$("#studentName_error").hide();
		}
	});
	
});
//优惠券
function couponBtnClick(object){
	if (!$(object).hasClass("c-on")) {
		$(object).addClass("c-on");
		$(object).children("tt").css({"color" : "#FF4000"});
		$(".buyCouponWrap").css({"visibility" : "visible"});
	} else {
		$(object).removeClass("c-on");
		$(object).children("tt").css({"color" : "#666"});
		$(".buyCouponWrap").css({"visibility" : "hidden"});
	}
}
//切换优惠券
function changeCoupon(type,obj){
	$(".coupon_li").removeClass("current");
	$(obj).parent(".coupon_li").addClass("current");
	if(type==1){
		$("#coupon_1").show();
		$("#coupon_2").hide();
	}else{
		$("#coupon_1").hide();
		$("#coupon_2").show();
	}
}
//输入优惠券
function inputcode()
{
 	if($("#couponCode").val()!="")
 	{
 		$('#tjcode').show();
 		$("#initcode").hide();
 	}
 	else{
 		$('#tjcode').hide();
 		$("#initcode").show();
 	}
}
//选择该教师可用优惠券
function changeCode(code){
	$("#couponCode").val(code);
	addcode();
}
//验证优惠券
function addcode(){
	var str=$("#couponCode").val();
	if(str==""||str==null){
		dialog("请输入优惠券编号！",10);
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "/ajax/checkCouponCode",
	   data: {"couponCode":str,"money":orderAfterMinusAcount},
	   dataType : "json",
	   success: function(result){
		   $("#couponCodeId").val($("#couponCode").val());
		   var couponCodeDTO=result.entity;
		   var finalAcount=$("#finalAcount").html();
		   var price;
		  
		   if(result.message!='success'||couponCodeDTO==null){
			 dialog(result.message,10);
             return;
		   }else{
			   var couponPrice =couponCodeDTO.amount;
			   if(couponCodeDTO.type==1){//折扣券
				   var couponPrice=parseFloat(orderAfterMinusAcount*couponCodeDTO.amount*0.1).toFixed(2);
				   price = accSub(finalAcount,couponPrice);
				   $("#coupon_info").html(fmoney(couponPrice));//优惠金额
				   $("#finalAcount_info").html(fmoney(price));
				   $("#finalAcount").html(fmoney(price));
			   }else{//定额券
				   var couponPrice=parseFloat(couponCodeDTO.amount).toFixed(2);
				   price=accSub(finalAcount,couponPrice);
				   $("#coupon_info").html(fmoney(couponPrice));//优惠金额
				   $("#finalAcount_info").html(fmoney(price));
				   $("#finalAcount").html(fmoney(price));
			   }
			   $("#coupon_span").show();
			   $("#couponCode").attr("disabled","disabled");
			   $("#submit_coupon").hide();
		   }
	   },
	   error : function (error) {
   		   alert(error.responseText);
	   }
	});
}
//提交订单
//判断订单,提交订单
function submitOrder() {
    if(!isLogin()){
       alert("请登录");
        return ;
    }
    var studentName = $("#studentName").val();
    if (studentName==null||studentName=="") {
		$("#studentName_error").show();
		return;
	} else {
		$("#studentName_error").hide();
	}
    var proStr = $("#provinceId").attr("lang");
    var cityStr = $("#cityId").attr("lang");
    var townStr = $("#townId").attr("lang");
    var address = $("#address").val();
    if(courseModel!='ONLINEOTO'&&areaChoose){
    	  if(isEmpty(proStr)){
    			dialog("请选择省份",10,'','');
    			return ;
    		}
    		if(isEmpty(cityStr)){
    			dialog("请选择城市",10,'','');
    			return ;
    		}
    		if(isEmpty(townStr)){
    			dialog("请选择城镇",10,'','');
    			return ;
    		}
    		if(isEmpty(address)){
    			dialog("请填写详细地址信息",10,'','');
    			return ;
    		}
    		$("#studyAddress").val(proStr+cityStr+townStr+address);
    }
   //订单提交，服务端要做验证，下单时重新查询数据库
    $.ajax({
        url: "/order_class?command=buy&type=1",
        data:$("#orderForm").serialize(),
        type:"post",
        dataType: "json",
        async : false,
        success: function(result) {
            if(result.success){
                if(isNotNull(result.entity.order)){
                    //金额页面只作为显示用，以服务端提交时重新取数据库为准
                    var orderId = result.entity.order.id;
                    window.location.href=baselocation+"/order/getorder?id="+orderId;
                }else if(result.entity.msg=='param'){
                    dialog("参数错误",10,'','');
                }else if(result.entity.msg=='amount'){
                    dialog("金额错误",10,'','');
                }else if(result.entity.msg=='courseIsNoJoin'){
                    dialog("课程不可报名",10,'','');
                }else if(result.entity.msg=='kpointIsNull'){
                    dialog("课程信息错误，请稍后再试",10,'','');
                }else if(result.entity.msg=='numIsOk'){
                    dialog("报名人数已满",10,'','');
                } else if(result.entity.msg=='courseIsNull'){
                    dialog("课程信息有误",10,'','');
                }else if(result.entity.msg=='canNotJoin'){
                    dialog("不可插班",10,'','');
                }
            }else{
            	dialog("下单异常，请稍后再试!",10,'','');
            }
        },
        error : function() {
            alert("error");
        }
    });

}
var payType='ALIPAY';//选择的支付方式
//立即支付
function goToBank(){
	if(payType=='YEEPAY'){
		var defaultbank=$("input[name='defaultbank']:checked").val();
		if(typeof(defaultbank)!="undefined"&&defaultbank!=''){
			$("#defaultbank").val(defaultbank);
		}
		document.orderForm.submit();
	}else if(payType=='KUAIQIAN'){
		document.orderForm.submit();
	}else if(payType=='ALIPAY'){
		var defaultbank=$("input[name='alipaybank']:checked").val();
		if(typeof(defaultbank)!="undefined"&&defaultbank!=''){
			$("#defaultbank").val(defaultbank);
		}
		document.orderForm.submit();
	}else if(payType=='WEIXIN'){
		document.orderForm.submit();
	}else{
		dialog("请返回选择支付方式",10,'','');
	}
}

/**
 * 选择支付宝支付
 * @param type 用于支付接口传递参数, 此处只用到0
 */
function checkbank(type){
	if(type=='KUAIQIAN'){
		payType='KUAIQIAN';
		$("input[name='kqBank']").attr("checked",true);
		$("input[name='alipay']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='weixin']").attr("checked",false);
		$("#payType").val('KUAIQIAN');
	}else if(type=='ALIPAY'){
		payType='ALIPAY';
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='weixin']").attr("checked",false);
		$("#payType").val('ALIPAY');
	}else if(type=='ALIPAY_BANK'){
		payType='ALIPAY';
		$("input[name='alipay']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='yeepay']").attr("checked",false);
		$("#payType").val('ALIPAY');
	}else if(type=='YEEPAY'){
		payType="YEEPAY";
		$("#payType").val('YEEPAY');
		$("input[name='weixin']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='alipay']").attr("checked",false);
	}else if(type=='WEIXIN'){
		payType='WEIXIN';
		$("input[name='yeepay']").attr("checked",false);
		$("input[name='kqBank']").attr("checked",false);
		$("input[name='alipay']").attr("checked",false);
		$("#payType").val('WEIXIN');
	}
}

//下拉选择
function initArea(id, index,type,name) {
	var parentId = 1;
	if (id != null && id != 0 && !isNaN(id)) {
		parentId = id;
	}
	//只有第一个下拉框查询一级
	if (index != 0) {
		if (parentId == 1) {
			return;
		}
	}
	if(index<3){
		$.ajax({
			url : baselocation + "/area/ajax/parentid",
			data : {
				"parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if (result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for (var i = 0; i < provinces.length; i++) {
					html += '<p><a href="javascript: void(0)" onClick="initArea(this.lang, '+(index+1)+',0,this.title)" lang="'+provinces[i].id+'" title="'+ provinces[i].areaName +'">'+ provinces[i].areaName +'</a></p>';
				}
				if (index == 0) {
					$("#provinceOption").html(html);
					//修改
					if(type==1){
						$("#provinceTip").html(name);
					}
				} else if (index == 1) {
					$("#cityOption").html(html);
					//修改
					if(type==1){
						$("#cityTip").html(name);
					}else{
						$("#cityTip").html("请选择城市");
						$("#townTip").html("请选择城镇");
						$("#provinceId").val(id);
						$("#provinceId").attr("lang",name);
						$("#cityId").val('');
						$("#townId").val('');
					}
					
				} else {
					$("#townOption").html(html);
					//修改
					if(type==1){
						$("#townTip").html(name);
					}else{
						$("#townTip").html("请选择城镇");
						$("#cityId").val(id);
						$("#cityId").attr("lang",name);
						$("#townId").val('');
					}
				}
				selFun("#select-1");
			},
			error : function(error) {
				alert('error' + error.responseText);
			}
		});
	}else{
		$("#townId").val(id);
		$("#townId").attr("lang",name);
		search();
	}
	var provinceId=$("#provinceId").val();
	var cityId=$("#cityId").val();
	var townId=$("#townId").val();
	if(isNotEmpty(provinceId)&&isNotEmpty(cityId)&&isNotEmpty(townId)){
		$("#address").removeAttr("disabled");
	}else{
		$("#address").attr("disabled","disabled");
	}
}


