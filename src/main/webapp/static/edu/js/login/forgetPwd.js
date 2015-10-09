$(document).ready(function() {
	$("#nextStep").hide();
	// 手机号
	$("#mobile").blur(function() {
		$("#errorMobile").removeClass();
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test($(this).val())) {
			$("#errorMobile").addClass(
					"vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorMobile").addClass(
					"Correct vam ml5 fl mt13");
		}
	});
	// 图形码
	$("#random").blur(function() {
		$("#errorRandom").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test($(this).val())) {
			$("#errorRandom").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorRandom").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 验证码
	$("#checkCode").blur(	function() {
		$("#errorCheckCode").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test($(this).val())) {
			$("#errorCheckCode").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorCheckCode").addClass("Correct vam ml5 fl mt13");
		}
	});
	// 密码
	$("#password")	.blur(function() {
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
	$("#repassword").blur(function() {
		$("#errorRePassword").removeClass();
		var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
		if (reg.test($(this).val())&& $("#repassword").val() == $("#password").val()) {
			$("#errorRePassword").addClass(	"Correct vam ml5 fl mt13");
		} else {
			$("#errorRePassword").addClass(	"vam fl Wrong ml10 mt13");
			return;
		}
	});
});
// 图形码验证
function checkRandomCode() {
	var mobile = $("#mobile").val();
	var random = $("#random").val();
	$("#errorMobile").removeClass();
	$("#errorRandom").removeClass();
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if (!reg.test($("#mobile").val())) {

		$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorMobile").addClass("Correct vam ml5 fl mt13");
	}
	var reg = /^\d{4}$/;
	if (!reg.test($("#random").val())) {
		$("#errorRandom").addClass("vam fl Wrong ml10 mt13");
		return;
	} else {
		$("#errorRandom").addClass("Correct vam ml5 fl mt13");
	}

	$.ajax({
		url : baselocation + "/checkRandomCode",
		type : "post",
		data : {
			"mobile" : mobile,
			"randomCode" : random
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				$("#firstStep").hide();
				$("#nextStep").show();
				$("#showMobile").html(mobile);
			} else if (result.message == 'mobileIsExist') {
				dialog("手机号码不存在",9,"","");
				$("#mobile").val("");
				$("#random").val("");
				$("#errorMobile").removeClass();
				$("#errorRandom").removeClass();
			} else if (result.message == 'randomCodeIsError') {
				dialog("图形码错误",9,"","");
				$("#random").val("");
				$("#errorRandom").removeClass();
				changePic();
			}
		}
	});
}
// 刷新图形码
function changePic() {
	var url = baselocation + "/ran/random?v=Math.random()";
	$("#randomPic").attr("src", url);
}
// 获取校验码
function getCheckCode() {
	var mobile = $("#showMobile").html();
	$.ajax({
		url : baselocation + "/user/mobileCodeForgetCheck",
		type : "post",
		data : {
			"mobile" : mobile
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				$("#getCheckCode").removeAttr("onclick");
				timer();
				dialog("发送成功，请稍等",8,"","");
			} else if (result.message == 'checkCodeMoreThanThree') {
				dialog("发送验证码超过3次，请24小时后重试",10,"","");
			}
		}
	});
}
var time = 60;
function timer() {
	if (time < 0) {
		$("#getCheckCode").attr("onclick", "getCheckCode()");
		$("#getCheckCode").text("获取手机验证码");
		time = 60;
		return;
	}
	$("#getCheckCode").text(time+"秒后重新获取验证码");
	time -= 1;
	setTimeout("timer();", 1000);
}
// 修改密码
function updatePassword() {
	var checkCode = $("#checkCode").val();
	var mobile = $("#showMobile").text();
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	// 验证码验证
	var reg = /^\d{4}$/;
	if (!reg.test($("#checkCode").val())) {
		$("#checkCode").val("");
		$("#errorCheckCode").removeClass();
		$("#errorCheckCode").addClass("vam fl Wrong ml10 mt13");
		return;
	}
	// 密码验证
	var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
	if (!reg.test($("#password").val())) {
		$("#password").val("");
		$("#errorPassword").removeClass();
		$("#errorPassword").addClass("vam fl Wrong ml10 mt13");
		return;
	}
	// 确认密码
	var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
	if (!reg.test($("#repassword").val())) {
		$("#errorRePassword").removeClass();
		$("#errorRePassword").addClass("vam fl Wrong ml10 mt13");
		return;
	}
	// 两次密码匹配
	if ($("#repassword").val() != $("#password").val()) {
		dialog("两次密码不同，请重新输入",9,"","");
		return;
	}
	// 发送请求
	$.ajax({
		url : baselocation + "/user/updatePassword",
		type : "post",
		data : {
			"checkCode" : checkCode,
			"mobile" : mobile,
			"password" : password,
			"repassword" : repassword
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				dialog("密码修改成功",13,"","/login");
			} else if (result.message == 'checkCodeError') {
				dialog("校验码错误",9,"","");
			}
			$("#checkCode").val("");
			$("#password").val("");
			$("#repassword").val("");
		}
	});
}
