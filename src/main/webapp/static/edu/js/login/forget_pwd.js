$().ready(function() {
	var forgotMessage = getParameter("forgotMessage");
	if (forgotMessage == "forgotSuccessMail") {
		$("#message_email").html("邮件已发送，请尽快查收");
	} else if (forgotMessage == "forgotFailEmail") {
		$("#message_email").html("该注册邮箱不存在");
	}
	if (forgotMessage == "forgotSuccessMobile") {
		$("message_mobile").html("短信已发送，请尽快查收");
	} else if (forgotMessage == "forgotFailMobile") {
		$("message_mobile").html("该手机号不存在");
	}
});
function clearCodeError() {
	$("#email_Randomcode").html("");
}
function clearEmailError() {
	$("#message_email").html("");
}

function forgotPwdEmail(callback) {
	var forgot_email = $("#forgot_email").val();
	if (forgot_email == "") {
		$("#message_email").html("请输入邮箱");
		return;
	}
	var reg = /^\w+@\w+(\.\w+){1,3}$/;
	if (!reg.test(forgot_email)) {
		$("#message_email").html("请输入正确的邮箱格式!");
		return;
	}
	var code = $("#regRandomcode2").val();
	if (code === null || code === "") {
		$("#email_Randomcode").html("请输入验证码!");
		return;
	} else {
		getEcode();
		$.ajax({
			url : baselocation + "/user/forgetpwd",
			data : {
				"email" : $("#forgot_email").val(),
				"randomCode" : $("#regRandomcode2").val()
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				/*	showEmail();
				  change();
				  $("#retrieve_pwd").hide();$("#getmail").show();*/
				if (result.success) {
					showEmail();
					change();
					$("#retrieve_pwd").hide();
					$("#getmail").show();
				} else {
					$("#message_email").html(result.message);
					changecode();
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	}
}
function logins() {
	var verifyLogin = 'ON';
	if (verifyLogin == 'ON') {
		//alert("s");
		window.location.href = baselocation + "/login";
	} else {
		dialog('提示', "对不起，登录系统已关闭!", 1);

	}
}
function showEmail() {

	$("#retrieve_pwd").hide();
	$("#getmail").show();

}
function clearMobileCodeError() {
	$("#mobile_Randomcode").html("");
}
function clearMobileError() {
	$("#message_mobile").html("");
}
function forgotPwdMobile() {
	var forgot_mobile = $("#forgot_mobile").val();
	if (forgot_mobile == "") {
		$("#message_mobile").html("请输入手机号");
		return;
	}
	var code = $("#regRandomcode").val();
	if (code === null || code === "") {
		$("#mobile_Randomcode").html("请输入验证码!");
		return;
	} else {
		getMcode();
		$.ajax({
			url : baselocation + "/user/forgetpwd",
			data : {
				"mobile" : $("#forgot_mobile").val(),
				"randomCode" : $("#regRandomcode").val()
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.returnMessage == "forgotSuccessMobile") {
					showMobile();
				} else if (result.returnMessage == "forgotFailMobile") {
					$("#message_mobile").html("该手机号不存在");
				} else if (result.returnMessage == "failMobile") {
					$("#message_mobile").html("请输入手机号");
				} else if (result.returnMessage == "forgotManyMobile") {
					var emailList = result.entity;
					if (emailList != null) {
						var displayInfo = "您的手机对应以下邮箱，请选择：<br>";
						$.each(emailList, function(key, val) {
							displayInfo += "<input type='radio'  id='radio"
									+ key + "' name='email2' value = '" + val
									+ "'/>" + val + "<br>"
						});
					}
					$("#message_mobile").html(displayInfo);
					//防止用户修改手机号和验证码
					$("#forgot_mobile").attr("readonly", "readonly");
					$("#regRandomcode").attr("readonly", "readonly");
				} else if (result.returnMessage == "forgotMobile") {
					$("#message_mobile").html("该手机号格式不对");
				} else if (result.returnMessage == "err.randCode") {
					$("#mobile_Randomcode").html("验证码错误!");
					changecode();
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	}
}
function showMobile() {
	$("#retrieve_pwd").hide();
	$("#getphone").show();
}

function showLogin() {
	$("#login_div").show();
}
//源代码如下  在forget_pwd.jsp中没有找到code 故
function changecode(str) {
	$("#code" + str).click();
}

function change() {
	$("#code").attr("src", baselocation + "/ran/random");
}

//	@Author wangzhuang 修改了
//	function changecode(str){
//			$("#code2"+str).click();
//	}
//	function change(){
//			$("#code2").attr("src", baselocation + "/ran/random");
//	}

function getEcode() {//这个方法我用不到!!!!
	var ecode = $("#forgot_email").val();
	$("#getecode").html(ecode);
}

function getMcode() {
	var mcode = $("#forgot_mobile").val();
	$("#getmcode").html(mcode);
}

/**
 * 常规邮件地址
 */
function myemail() {
	var mail = $("#forgot_email").val();
	var arry = mail.split("@");
	if (arry[1] == "163.com") {
		window.open("http://mail.163.com/");
	} else if (arry[1] == "126.com") {
		window.open("http://mail.126.com/");
	} else if (arry[1] == "qq.com") {
		window.open("http://mail.qq.com/");
	} else if (arry[1] == "sina.com") {
		window.open("http://mail.sina.com.cn/");
	} else if (arry[1] == "sohu.com") {
		window.open("http://mail.sohu.com/");
	} else if (arry[1] == "Gmail.com") {
		window.open("http://mail.Gmail.com/");
	} else if (arry[1] == "yahoo.com.cn") {
		window.open("http://mail.cn.yahoo.com//");
	} else if (arry[1] == "yahoo.cn") {
		window.open("http://mail.cn.yahoo.com//");
	} else if (arry[1] == "139.com") {
		window.open("http://mail.139.com/");
	} else if (arry[1] == "yeah.net") {
		window.open("http://www.yeah.net/");
	} else if (arry[1] == "263.com") {
		window.open("http://mail.263.com/");
	} else if (arry[1] == "21cn.com") {
		window.open("http://mail.21cn.com/");
	} else if (arry[1] == "sogou.com") {
		window.open("http://mail.sogou.com/");
	} else if (arry[1] == "189.cn") {
		window.open("http://webmail1.189.cn/webmail/");
	}
}


function updatepwd(code){
	var password  = $("input[name='password']").val();
	var confirmPwd =$("input[name='confirmPwd']").val();
	
	var pattern = /(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	if(pattern.test(password)==false){
		alert("请不要输入非法关键字");
		return;
	}
	if(password.length<6 || password!=confirmPwd){
		alert("密码不能小于6位，确认密码必须相同");
		return;
	}
	if(password.indexOf("")!=-1){
		alert('密码不能包含空格');
		return;
	}
	$.ajax({
		url :  "/user/updatePwd",
		data : {
			"password" : password,
			"confirmPwd": confirmPwd ,
			"code":code
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			if(result.success){
				$("#retrieve_pwd").hide();
				$("#getmail").show();
			}else{
				alert(result.message);
			}
		},
		error : function(error) {alert(error);}
	});
}

//+++++++++++++++++++++++
//+++++++++++++++++++++++
//+++++++++++++++++++++++
//+++++++++++++++++++++++
//修改密码 @Author  wangzhuang
function updatepwdPhone() {
	var mobile = $("#mobile_2").val();
	var newpwd = $("#pwd1").val();
	var confirmPwd = $("#pwd2").val();

	var pattern = /(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	if (pattern.test(newpwd) == false) {
		alert("请不要输入非法关键字");
		return;
	}
	if (newpwd.length < 6 || newpwd != confirmPwd) {
		alert("密码不能小于6位，确认密码必须相同");
		return;
	}
	if (newpwd.indexOf(" ") != -1) {
		alert('密码不能包含空格');
		return;
	}
	$.ajax({
		url : "/user/updatapwdByforgot",
		data : {
			"newpwd" : newpwd,
			"mobile" : mobile
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			alert("111")
		},
		error : function(error) {
			alert(error + "qwe");
		}
	});
}
//@Author wangzhuang 
//判断验证码是否正确
function testVerificationCode() {
	var verificationCode_one = $("#verificationCode").val();
	var mobile = $("#mobile").val();
	if (mobile == "") {
		$("#message_verificationCode").html("请输入手机号");
	}
	if (verificationCode == "") {
		$("#message_verificationCode").html("请输入验证码");
		return;
	} else {
		$.ajax({
			url : baselocation + "/user/checkVerificationCode",
			data : {
				"verificationCode_one" : verificationCode_one,
				"mobile" : mobile
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				$("#retrieve_pwd").hide();
				$("#getphone").hide();
				$("#getPWD").show();

			}
		});
	}
}

//添加的一些个额外方法 @Author wangzhuang
function showPhone() {
	$("#retrieve_pwd").hide();
	$("#getphone").show();
}

function jumpPWD() {
	Window.location.href = "http://bianceng.cn/";
}

//		通过手机验证码修改密码@Author wangzhuang
function forgotPwdPhone() {
	var forgot_phone = $("#forgot_phone").val();
	if (forgot_phone == "") {
		$("#message_phone").html("请输入手机号码");
	}
	var reg = /^[1][3-8]\\d{9}$/;
	if (reg.test(forgot_phone)) {
		$("#message_phone").html("请输入正确的手机格式");
		return;
	}
	var code = $("#regRandomcode2").val();
	if (code === null || code === "") {
		$("#phone_Randomcode").html("请输入验证码");
		return;
	} else {
		$.ajax({
			url : baselocation + "/user/mobileCodeForgetCheck",
			data : {
				"randomCode" : $("#regRandomcode2").val(),
				"mobile" : $("#forgot_phone").val()
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.success) {
					showPhone();
					change();//这个方法至今没有弄明白
					$("#retrieve_pwd").hide();
					$("#getphone").show();
				} else {
					$("#message_phone").html(result.message);
					changecode();//这个方法好像没有什么作用
				}
			},
		});
	}
}
