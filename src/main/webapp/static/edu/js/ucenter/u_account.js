$(function(){
	$("#activateCardCourse").click(function(){
		dialog('充值卡充值','',10);
		$("#activateCardCourse").hide();
		
	});
	$("#activateNow").click(function(){
		
	});
	$("#comeback").click(function(){
		$("#activateCardCourseDiv").show();
		$("#activateCardCourseFalseDiv").hide();
	});
});
function checkData(){
	var message="";
	if($("#cardCourseCode").val()==''){
		message+="卡号不能为空！\n";
	}
	if($("#cardCoursePassword").val()==''){
		message+="密码不能为空！\n";
	}
	return message;
}

function jihuo(){
	var msg=checkData();
	if(msg!=''){
		$('.dialog-shadow').remove();
		dialog('充值提示',msg,1);
	}else{
	$.ajax({
		url : baselocation + "/course/activationcard/2",
		data : {
			"cardCode.type":2,
			"cardCode.cardCode" : $.trim($("#cardCourseCode").val()),
			"cardCode.cardCodePassword" : $.trim($("#cardCoursePassword").val())
		},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			var msg="";
			if(result.entity=='passwordError'){
				msg="卡号或密码错误，请确认，谢谢！";
			}else if(result.entity=='dontActivate'){
				msg="该卡未被激活，请联系客服进行处理！谢谢";
			}else if(result.entity=='alreadyUse'){
				msg="该卡已被使用，不能再进行充值，请确认！谢谢";
			}else if(result.entity=='overDue'){
				msg="该卡已过期，不能进行充值，请确认！谢谢";
			}else if(result.entity=='close'){
				msg="该卡已作废，不能进行充值，请确认！谢谢";
			}else if(result.entity=='dateError'){
				msg="该卡不在有效期内，请确认！谢谢";
			}else{
				msg="";
				$('.dialog-shadow').remove();
				dialog('充值提示',"您的充值卡已充值成功!",16,'/uc/acc');
			}
			if(msg!=""){
				$('.dialog-shadow').remove();
				dialog('充值提示',msg,1);
			}
		},
		error : function(error) {
			$('.dialog-shadow').remove();
			dialog('充值提示',"您的充值卡发生异常，请及时联系客服人员进行处理，谢谢！",1);
		}
	});
	}
}

// 定时器
var curCount = 60;
// 获取验证码
function getVerifications(){
	var phone=$("#mobile").val();
	if(phone==null || phone.trim()==''){
		$("#mobileError").show();
		$("#mobileError").html("请输入手机号");
		return;
	}
	var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
	if(reg.test(phone)==false){//格式不正确
		$("#mobileError").show();
		$("#mobileError").html("请输入正确的手机号");
		return;
	};
	$.ajax({
		url:'/front/cashout/getVerification/' + phone,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				$("#validCodeBtn").attr("href",'javascript:void(0)');
				$("#validCodeBtn").css('color','#fff');
				$("#validCodeBtn").val('验证码已发送');
				InterValObj=window.setInterval(SetRemainTime, 1000);
			}
		}
	});
}
function SetRemainTime() {  
	if (curCount == 0) { 
		    window.clearInterval(InterValObj);// 停止计时器  
		    curCount = 60;
		    $("#validCodeBtn").attr("href","javascript:getVerifications()");
		    $("#validCodeBtn").css('color','#fff');
			$("#validCodeBtn").text('获取验证码');
	    }else { 
	        curCount--;  
	        $("#validCodeBtn").css('color','#CCC');
	        $("#validCodeBtn").text(curCount + "秒重新获取");  
	    }  
}
// 提现方式改变
function typeChange(){
	var type = $("#cashoutType").val();
	if (type == 'BANK'){
		$("[name='bank']").show();
		$("[name='alipay']").hide();
		$("#userRealName").text("开户人姓名：");
	} else {
		$("[name='bank']").hide();
		$("[name='alipay']").show();
		$("#userRealName").text("真实姓名：");
	}
}
// 申请提现
function cashOut(){
	var money = $("#cashOutMoney").val();
	if (money == null || money == "") {
		$("#cashOutMoneyError").show();
		$("#cashOutMoneyError").text("请输入要提现的金额！");
		return;
	}
	if (isNaN(money) || money <= 0) {
		$("#cashOutMoneyError").show();
		$("#cashOutMoneyError").text("请输入正确的金额！");
		return;
	}
	var type = $("#cashoutType").val();
	var bankCard = $("#bankCard").val();
	var bankPerson = $("#bankPerson").val();
	var openbankName = $("#openbankName").val();
	//var randomCode = $("#randomCode").val();
	var alipayAccount = $("#alipayAccount").val();
	if (type == 'BANK'){ // 银行卡提现
		if (bankCard == null || bankCard == '') {
			$("#bankCardError").show();
			$("#bankCardError").text("请输入银行卡号！");
			return;
		} 
		if (bankCard.length < 16 || bankCard.length > 19) {
			$("#bankCardError").show();
			$("#bankCardError").text("请输入正确的银行卡号");
			return;
		}
		if (openbankName == null || openbankName == '') {
			$("#openbankNameError").show();
			$("#openbankNameError").text("请输入开户银行名称");
			return;
		}
		if (bankPerson == null || bankPerson == '') {
			$("#bankPersonError").show();
			$("#bankPersonError").text("请输入开户人姓名");
			return;
		}
		//if (randomCode == null || randomCode == '') {
		//	$("#randomCodeError").show();
		//	$("#randomCodeError").text("请输入手机验证码");
		//	return;
		//}
	} else { // 支付宝提现
		if (alipayAccount == null || alipayAccount == '') {
			$("#alipayAccountError").show();
			$("#alipayAccountError").text("请输入支付宝账号");
			return;
		}
		if (bankPerson == null || bankPerson == '') {
			$("#bankPersonError").show();
			$("#bankPersonError").text("请输入您的姓名");
			return;
		}
	}
	$.ajax({
		url:'/front/cashout/add',
		type:'post',
		dataType:'json',
		data:{"cashOut.cashType":type,
			  "cashOut.openBankName":openbankName,
			  "cashOut.bankCard":bankCard,
			  "cashOut.openBankPerson":bankPerson,
			  "cashOut.alipayAccount":alipayAccount,
			  //"cashOut.userMobile":$("#mobile").val(),
			  "cashOut.bankName":$("#bankName").val(),
			  //"randomCode":randomCode,
			  "cashOut.cashoutMoney":money
			  },
		success:function(result){
			$('.dialog-shadow').remove();
			if(result.success == true){
				dialog("申请提现成功", 17, "提现成功", "javascript:window.location.reload()");
			} else {
				/*if (result.message == 'randomCodeError') {
					dialog("验证码错误", 9, "提现提示", "");
				} else */
				if (result.message == 'bankCardIsNull') {
					dialog("银行卡号不能为空", 9, "提现提示", "");
				} else if (result.message == 'openBankNameIsNull') {
					dialog("开户行名称不能为空", 9, "提现提示", "");
				} else if (result.message == 'openBankPersonIsNull') {
					dialog("您的姓名不能为空", 9, "提现提示", "");
				} else if (result.message == 'alipayAccountIsNull') {
					dialog("支付宝账号不能为空", 9, "提现提示", "");
				} else if (result.message == 'cashoutMoneyIsNull') {
					dialog("提现金额必须大于0元", 9, "提现提示", "");
				} else {
					dialog("系统错误", 9, "提现提示", "");
				}
			}
		}
	});
}

// 点击提现
function clickCashout(totalMoney){
	dialog(totalMoney, 3 ,"" ,"");
}
