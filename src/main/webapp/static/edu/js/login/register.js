$(document).ready(function() {
	// 学生按钮
	$("#btnStudent").click(function(){
		$("#tea_con").removeClass("current");// 老师按钮隐藏
		$("#stu_con").addClass("current");// 学生按钮隐藏
		$("#typeName").html("学生注册");
		$("#registerSubmit")[0].reset();
		$("#userType").val(0);
		changePic();
		$("#errorMobile").removeClass();
		$("#errorRandom").removeClass();
		$("#errorRandom").removeClass();
		$("#checkCodeRandom").removeClass();
		$("#errorPassword").removeClass();
		$("#errorRepassword").removeClass();
		$("#errorExtendCode").removeClass();
		$("#extendCodePage").addClass("undis");
	});
	// 老师按钮
	$("#btnTeacher").click(function(){
		$("#stu_con").removeClass("current");// 学生按钮隐藏
		$("#tea_con").addClass("current");// 老师按钮高亮
		$("#typeName").html("老师注册");
		$("#registerSubmit")[0].reset();
		$("#userType").val(1);
		changePic();
		$("#errorMobile").removeClass();
		$("#errorRandom").removeClass();
		$("#errorRandom").removeClass();
		$("#checkCodeRandom").removeClass();
		$("#errorPassword").removeClass();
		$("#errorRepassword").removeClass();
		$("#errorExtendCode").removeClass();
		$("#extendCodePage").addClass("undis");
	});
	// 手机号
	$("#mobile")	.blur(function() {
		$("#errorMobile").removeClass();
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test($(this).val())) {
			$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorMobile").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 验证码
	$("#randomCode").blur(function() {
		$("#errorRandom").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test($(this).val())) {
			$("#errorRandom").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorRandom").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 校验码
	$("#checkCode").blur(function() {
		$("#checkCodeRandom").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test($(this).val())) {
			$("#checkCodeRandom").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#checkCodeRandom").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 密码
	$("#password").blur(function() {
		$("#errorPassword").removeClass();
		var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
		if (!reg.test($(this).val())) {
			$("#errorPassword").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorPassword").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 确认密码
	$("#repassword")	.blur(function() {
		$("#errorRepassword").removeClass();
		var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
		if ((!reg.test($(this).val()))||($("#repassword").val() != $("#password").val())) {
			$("#errorRepassword").addClass("vam fl Wrong ml10 mt13");
			return;
		} else{
			$("#errorRepassword").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 邀请码按钮
	$("#selectCode").change(function(){
		if($(this).is(':checked')){
			$("#extendCodePage").removeClass("undis");
		}else{
			$("#extendCodePage").addClass("undis");
		}
		$("#errorExtendCode").removeClass();
	});
	// 邀请码
	$("#extendCode").blur(function() {
		$("#errorExtendCode").removeClass();
		var reg = /^[0-9]*$/;
		if (!reg.test($(this).val())||$(this).val()=='') {
			$("#errorExtendCode").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorExtendCode").addClass("Correct vam ml5 fl mt13");
		}
	});
}); 

//获取校验码
function getCheckCode() {
	var mobile = $("#mobile").val();
	var randomCode = $("#randomCode").val();
	$("#errorMobile").removeClass();
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if (!reg.test(mobile)) {
		$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorMobile").addClass("Correct vam ml5 fl mt13");
	}
	$("#errorRandom").removeClass();
	var reg = /^\d{4}$/;
	if (!reg.test(randomCode)) {
		$("#errorRandom").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorRandom").addClass("Correct vam ml5 fl mt13");
	}
	$.ajax({
		url : baselocation + "/getCheckCode",
		type : "post",
		data : {
			"mobile" : mobile,
			"randomCode":randomCode
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				$("#getCheckCode").removeAttr("onclick");
				timer();
				//alert(result.entity);
				//dialog("发送成功",8,"","");
			} else if(result.message == 'randomError'){
				$("#randomCode").val("");
				changePic();
				dialog("验证码错误",9,"","");
			} else if(result.message=='regMobileExist'){
				dialog("手机号已存在",9,"","");
				$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
			}
		}
	});
}

// 注册提交
function doRegister(){
	var mobile = $("#mobile").val();// 手机号
	var checkCode = $("#checkCode").val();// 校验码
	var password = $("#password").val();// 密码
	var repassword = $("#repassword").val();// 确认密码
	var extendCode = $("#extendCode").val();// 邀请码
	var userType = $("#userType").val();// 用户类型
	// 手机验证
	$("#errorMobile").removeClass();
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if (!reg.test(mobile)) {
		$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorMobile").addClass("Correct vam ml5 fl mt13");
	}
	// 校验码验证
	$("#checkCodeRandom").removeClass();
	var reg = /^\d{4}$/;
	if (!reg.test(checkCode)) {
		$("#checkCodeRandom").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#checkCodeRandom").addClass("Correct vam ml5 fl mt13");
	}
	// 密码验证
	$("#errorPassword").removeClass();
	var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
	if (!reg.test(password)) {
		$("#errorPassword").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorPassword").addClass("Correct vam ml5 fl mt13");
	}
	// 确认密码验证
	$("#errorRepassword").removeClass();
	var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
	if ((!reg.test(repassword))||(repassword != password)) {
		$("#errorRepassword").addClass("vam fl Wrong ml10 mt13");
		return;
	} else{
		$("#errorRepassword").addClass("Correct vam ml5 fl mt13");
	}
	// 邀请码
	if($("#selectCode").is(':checked')){
		$("#errorExtendCode").removeClass();
		var reg = /^[0-9]*$/;
		if (!reg.test(extendCode)||extendCode=='') {
			$("#errorExtendCode").addClass("vam fl Wrong ml10 mt13");
			return;
		}else{
			$("#errorExtendCode").addClass("Correct vam ml5 fl mt13");
		}
	}
	// 阅读协议
	if(!$("#agreement").is(':checked')){
		dialog("请阅读协议并勾选",10,"","");
		return;
	}
	$.ajax({
		url : baselocation + "/doregister",
		type : "post",
		data : {
			"userForm.mobile" : mobile,
			"checkCode":checkCode,
			"userForm.password":password,
			"userForm.confirmPassword":repassword,
			"userForm.extendCord":extendCode,
			"userForm.userType":userType
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				dialog("注册成功",13,"","/index");
			} else if(result.message== 'mobileIsNull'){
				$("#mobile").val("");
				$("#errorMobile").removeClass();
				dialog("手机号已存在",9,"","");
			} else if(result.message == 'checkCodeIsError'){
				$("#randomCode").val("");
				$("#checkCodeRandom").removeClass();
				dialog("校验码错误",9,"","");
			} else if(result.message=='pwdNotEqual'){
				dialog("密码不一致",10,"","");
			}
			changePic();
			$("#errorPassword").removeClass();
			$("#errorRepassword").removeClass();
			$("#password").val("");
			$("#repassword").val("");
		}
	});
}

// 倒计时
var time = 60;
function timer() {
	if (time < 0) {
		$("#getCheckCode").attr("onclick", "getCheckCode()");
		$("#getCheckCode").text("获取手机验证码");
		time = 60;
		return;
	}
	$("#getCheckCode").text(zerofill(time)+"秒后重新获取验证码");
	time -= 1;
	setTimeout("timer();", 1000);
}
//刷新图形码
function changePic(){
	var url = baselocation+"/ran/random?v=Math.random()";
	$("#randomPic").attr("src",url);
}
function zerofill(number){
	if(number<0){
		return "0"+number;
	}else {
		return number;
	}
}