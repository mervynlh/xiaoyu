var payType='ALIPAY';//选择的支付方式
function showCardType(memberName,type){
	$("#defaultType").html(memberName);
	$("#type").hide();
	$.ajax({
		url:baselocation+"/member/membertype/"+type,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=="true"){
				var memberList=result.entity;
				var days="";
				$.each(memberList,function(i,val){
					if(i==0){//默认学段会员类型
						changeType(val.id,val.days,val.name,val.price);
					}
					days+='<li onclick="changeType('+val.id+',\''+val.days+'\',\''+val.name+'\','+val.price+')" id="type'+val.id+'">'+val.description+'</li>';
				});
				$("#days").html(days);
				initPrice();
			}
			
		}
	});
}
function changeType(id,days,name,price){
	$("#daysHidden").val(id);
	$("#defaultDays").html(name);
	$("#memPrice").html(fmtprice(price));
	$("#div_totalMoney").html(fmtprice(price));
	$("#days").hide();
	initPrice();
}
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


//显示页面课程价格,使用优惠卷时，只改变这3个div的显示即可
function initPrice(){
	//将优惠券的信息清除
	$("#initcode").hide();
	$("#tjcode").show();
	$("#yhmoney").html("￥0.00");
	$("#yhTypeId").html("无");
	$("#couponCode").val("");
	$("#couponCodeHidden").val("");
	$("#oldmoney").html($("#div_totalMoney").html());
	$("#paySumPrice").html($("#div_totalMoney").html());
	$("#payprice").html($("#div_totalMoney").html());
	$("#couponBtn").removeClass("c-on");
	$("#couponBtn").children("tt").css({"color" : "#666"});
	$(".buyCouponWrap").css({"visibility" : "hidden"});
	
}
//格式化价格
function fmtprice(price){
	 if(typeof(price) == 'undefined' || price == null || price ==""|| isNaN(price) ||price == Infinity){
	        return "￥0.00";
	    }else{
	        return "￥"+parseFloat(price).toFixed(2);
	    }
}

//判断订单,提交订单
function memOrder() {
	if(!isLogin()){
		dialog('登录','',3,'','1');
        $("#userEmail").mailAutoComplete({
            boxClass: "out_box", //外部box样式
            listClass: "list_box", //默认的列表样式
            focusClass: "focus_box", //列表选样式中
            markCalss: "mark_box", //高亮样式
            autoClass: false,//不使用默认的样式
            textHint: true //提示文字自动隐藏
        });
		return false;
	}
	if($("#daysHidden").val()==null||$("#daysHidden").val()==0){
		dialog('提示信息',"请选择购买会员！",1);
		return;
	}
	var couponCode=$("#couponCodeHidden").val();//优惠券编码
	//订单提交，服务端要做验证，下单时重新查询数据库
	$.ajax({
		url: "/memberorder?command=buy",
		data:{"memberId":$("#daysHidden").val(),"payType":payType,"couponcode":couponCode},
		type:"post",
		dataType: "json",
		async : false,
		success: function(result) {
			if(result.success){
				if(isNotNull(result.entity.memOrder)){
					//金额页面只作为显示用，以服务端提交时重新取数据库为准
					var memOrderId = result.entity.memOrder.id;
					$("#orderId").val(memOrderId);
					//$("#amount").html(result.entity.order.amount);
					//显示提交成功的DIV
					$("#orderId_success").html(result.entity.memOrder.requestId);
					$("#amount_success").html(fmtprice(result.entity.memOrder.amount));
					$("#balance_s").html("￥"+result.entity.balance);
					if(isNotEmpty(result.entity.bankAmount)){
						$("#bankAmount_s").html("，还需充值￥"+result.entity.bankAmount);
					}else{
						$("#bankAmount_s").html("");
					}
					$("#order_init").hide();
					$("#order_success").show();
					window.scrollTo(0,0);
					clearMemCart();
				}else if(result.entity.msg=='param'){
					dialog('错误提示',"参数错误",1);
				}else if(result.entity.msg=='amount'){
					dialog('错误提示',"会员金额错误",1);
				}
				
			}else{
				alert('下单异常，请稍后再试!');
			}
			
		},
		error : function() {
			alert("error");
		}
	});
	
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
/**
 * 跳转到银行
 */
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
		dialog('友情提示',"请返回选择支付方式",1);
	}
}

/**
 * 下单成功后，清空会员id操作
 */
function clearMemCart(){
	$("#daysHidden").val("");
}
function disOrderSuccess(){
    $("#order_init").hide();
    $("#order_success").show();
    window.scrollTo(0,0);
}
//输入优惠券
function inputMemCode()
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
	
	//验证优惠券
	function addMemcode(){
 		var str=$("#couponCode").val().trim();
 		if(str==""||str==null){
		dialog('错误提示',"请输入优惠券编号！",1);
 		return;
 		}
 		 $.ajax({
		   type: "POST",
		   url: "/coupon/check",
		   data: {"couponCode":str,"memberCode":"memberCode"},
		   dataType : "json",
		   success: function(result){
			   $("#couponCodeHidden").val($("#couponCode").val());
			   var obj=result.entity;
			   if(result.message!='true'||obj==null){
				 initPrice();
				 dialog('错误提示',result.message,1);
				 return;
			   }else{
				   $("#tjcode").hide();
				   $("#initcode").show();
				   //定额券
				   var couponPrice=parseFloat(obj.couponCodeDTO.amount).toFixed(2);
				   var totalPrice=$("#div_totalMoney").html().replace("￥","");
				   var price=totalPrice-couponPrice;
				   $("#yhmoney").html(fmtprice(couponPrice));//优惠金额
				   $("#yhTypeId").html("立减（"+fmtprice(couponPrice)+"元）");
				   $("#paySumPrice").html(fmtprice(price));
				   $("#payprice").html(fmtprice(price));
			   }
		   },
 		   error : function (error) {
 	   		   alert(error.responseText);
 		   }
 		});
 	}