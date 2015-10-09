var payType='ALIPAY';//选择的支付方式


//格式化价格
function fmtprice(price){
	 if(typeof(price) == 'undefined' || price == null || price ==""|| isNaN(price) ||price == Infinity){
	        return "￥0.00";
	    }else{
	        return "￥"+parseFloat(price).toFixed(2);
	    }
}

//判断订单,提交订单
function cashOrder() {
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
		return;
	}
	var payCash=$("#payCash").val();
	var reg = /^0\.([1-9]|\d[1-9])$|^[1-9]\d{0,8}\.\d{0,2}$|^[1-9]\d{0,8}$/;
	if(payCash==null||payCash==''||isNaN(payCash)||!reg.test(payCash)){
		dialog("请输入充值金额", 10, "", "");
		return;
	}
	//订单提交，服务端要做验证，下单时重新查询数据库
	$.ajax({
		url: "/cashorder?command=buy",
		data:{"payType":payType,"paycash":payCash},
		type:"post",
		dataType: "json",
		async : false,
		success: function(result) {
			if(result.success){
				if(isNotNull(result.entity.cashOrder)){
					//金额页面只作为显示用，以服务端提交时重新取数据库为准
					var cashOrderId = result.entity.cashOrder.id;
					$("#orderId").val(cashOrderId);
					//$("#repayA").attr("href","/cash/order/repay/"+cashOrderId)
					//显示提交成功的DIV
					//$("#orderId_success").html(result.entity.cashOrder.requestId);
					//$("#amount_success").html(fmtprice(result.entity.cashOrder.amount));
					//$("#order_init").hide();
					//$("#order_success").show();
					//window.scrollTo(0,0);
					clearMemCart();
					goToBank();
				}else if(result.entity.msg=='param'){
					dialog('错误提示',"参数错误",1);
				}else if(result.entity.msg=='userId'){
					dialog('错误提示',"请登录后再次尝试",1);
				}else if(result.entity.msg=='payCash'){
					dialog('错误提示',"充值金额不正确，请稍后再试",1);
				}
				
			}else{
				dialog('错误提示',"下单异常，请稍后再试!",1);
			}
			
		},
		error : function() {
			dialog('错误提示',"下单异常，请稍后再试!",1);
		}
	});
	
}

function repayOrder(){//重新支付验证
	$("#order_init").hide();
	$("#order_success").show();
	window.scrollTo(0,0);
			
	
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
	$("#payCash").val("");
}
function disOrderSuccess(){
    $("#order_init").hide();
    $("#order_success").show();
    window.scrollTo(0,0);
}

	
	