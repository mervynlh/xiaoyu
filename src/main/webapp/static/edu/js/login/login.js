$(document).ready(function() {
	// 学生按钮
	$("#btnStudent").click(function(){
		$("#tea_con").removeClass("current");// 老师按钮隐藏
		$("#stu_con").addClass("current");// 学生按钮隐藏
		$("#userType").val(0);
		cleanMobile();
		cleanPassword();
	});
	// 老师按钮
	$("#btnTeacher").click(function(){
		$("#stu_con").removeClass("current");// 学生按钮隐藏
		$("#tea_con").addClass("current");// 老师按钮高亮
		$("#userType").val(1);
		cleanMobile();
		cleanPassword();
	});
	// 手机号
	$("#mobile").blur(function(){
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if($(this).val()==''){
			$("#errorMobile").html("请输入手机号");
			$("#checkMobile").addClass("Error");
		}else if (!reg.test($(this).val())) {
			$("#errorMobile").html("格式错误");
			$("#checkMobile").addClass("Error");
		} else {
			$("#errorMobile").html("");
			$("#checkMobile").removeClass();
		}
	});

});

function enters(){
  if(event.keyCode==13){//13 回车键
	  loginSubmit();
  }
 }
// 登录
function loginSubmit(){
	var mobile = $("#mobile").val();// 手机号
	var password = $("#password").val();// 密码
	var userType = $("#userType").val();// 用户类型
	var autoThirty=$("#autoThirty").prop("checked");// 保存登录状态
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if(mobile==''){
		$("#errorMobile").html("请输入手机号");
		$("#checkMobile").addClass("Error");
		return;
	}
	if (!reg.test(mobile)) {
		$("#errorMobile").html("格式错误");
		$("#checkMobile").addClass("Error");
		return;
	} else {
		$("#errorMobile").html("");
		$("#checkMobile").removeClass();
	}
	$.ajax({
		url : baselocation + "/checkMobile",
		type : "post",
		data : {
			"mobile" : mobile,
			"userType":userType
		},
		dataType : "json",
		success : function(result) {
			if (result.success == true) {
				$("#errorMobile").html("");
				// 发送登录请求
				$.ajax({
					url : baselocation + "/dologin",
					type : "post",
					data : {
						"userForm.mobile":mobile,
						"userForm.password":password,
						"userForm.userType":userType,
						"autoThirty":autoThirty
					},
					dataType : "json",
					success : function(result) {
						if (result.success == true) {
							$("#errorMobile").html("");
							window.location.href = baselocation;
						} else if(result.message == 'formDataNot'){
							$("#errorMobile").html("手机号不存在或被冻结");
							$("#checkMobile").addClass("Error");
						} else if(result.message == 'errorPassword'){
							dialog("密码错误",9,"","");
							$("#checkPassword").addClass("Error");
						} 
					}
				});
			} else if(result.message=='mobileExist'){
				$("#checkMobile").addClass("Error");
				$("#errorMobile").html("手机号不存在");
			} else if(result.message ==  'maybeTeacher'){
				$("#checkMobile").addClass("Error");
				$("#errorMobile").html("可能是老师账户");
			} else if(result.message ==  'maybeStudent'){
				$("#checkMobile").addClass("Error");
				$("#errorMobile").html("可能是学生账户");
			}
			cleanPassword();
		}
	});
}
//清空账号
function cleanMobile(){
	$("#mobile").val("");
	$("#errorMobile").html("");
	$("#checkMobile").removeClass();
}
//清空密码
function cleanPassword(){
	$("#password").val("");
	$("#checkPassword").removeClass();
}