$(document).ready(function() {
	initSimpleImageUpload("inst_btn","inst_pic","inst_url");
	initSimpleImageUpload("license_btn","license_pic","license_url");
	initSimpleImageUpload("ID_btn","ID_pic","ID_url");

	// 机构名称
	$("#name").blur(function(){
		if($(this).val()==''){
			$("#nameError").addClass("vam fl Wrong ml10 mt13 pr");
		}else{
			$("#nameError").removeClass();
		}
	});
	// 机构简介
	$("#description").blur(function(){
		if($(this).val()==''){
			$("#descriptionError").html("机构简介不能为空");
		}else{
			$("#descriptionError").html("");
		}
	});
	// 申请人
	$("#applicant").blur(function(){
		if($(this).val()==''){
			$("#applicantError").addClass("vam fl Wrong ml10 mt13 pr");
		}else{
			$("#applicantError").removeClass();
		}
	});
	// 手机号
	$("#mobile")	.blur(function() {
		$("#errorMobile").removeClass();
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test($(this).val())) {
			$("#errorMobile").addClass("vam fl Wrong ml10 mt13 pr");
			return;
		} else {
			$("#errorMobile").addClass("vam fl Wrong ml10 mt13 pr");
		}
	});
	// 邮箱
	$("#email").blur(function(){
		if(!isTrueEmail($(this).val())){
			$("#emailError").addClass("vam fl Wrong ml10 mt13 pr");
		}else{
			$("#emailError").removeClass();
		}
	});
	// 密码
	$("#password").blur(function() {
		$("#errorPassword").removeClass();
		var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
		if (!reg.test($(this).val())) {
			$("#passwordError").addClass("vam fl Wrong ml10 mt13 pr");
		}else{
			$("#passwordError").removeClass();
		}
	});
	// 确认密码
	$("#rpassword")	.blur(function() {
		$("#rpasswordError").removeClass();
		var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
		if ((!reg.test($(this).val()))||($("#rpassword").val() != $("#password").val())) {
			$("#rpasswordError").addClass("vam fl Wrong ml10 mt13 pr");
		}else{
			$("#rpasswordError").removeClass();
		}
	});
});
// 下一步
function nextStep(){
	if(!$("#agreeArticle").attr("checked")){
		dialog("请阅读入驻协议，并确认",10,"","");
		return;
	}
	$(".u-t-mechan-bg").removeClass("u-t-mechan-bg-3");
	$(".u-t-mechan-bg").addClass("u-t-mechan-bg-2");
	$(".step").hide();
	$(".step:eq(1)").show();
}
// 提交数据
function addInstitution(){
	// 机构图片			
	if($("#inst_url").val()==''){
		dialog("请上传机构图片",10,"","");
		return;
	}
	// 机构名称
	if($("#name").val()==''){
		dialog("请填写机构名称",10,"","");
		return;
	}
	// 机构简介
	if($("#description").val()==''){
		dialog("请填写机构简介",10,"","");
		return;
	}
	// 营业执照
	if($("#license_url").val()==''){
		dialog("请上传营业执照",10,"","");
		return;
	}
	// 申请人
	if($("#applicant").val()==''){
		dialog("请填写申请人",10,"","");
		return;
	}
	// 身份证
	if($("#ID_url").val()==''){
		dialog("请上传身份证图片",10,"","");
		return;
	}
	// 手机
	if(!isTrueMobile($("#mobile").val())){
		dialog("请填写正确的手机号码",10,"","");
		return;
	}
	// 邮箱
	if(!isTrueEmail($("#email").val())){
		dialog("请填写正确邮箱",10,"","");
		return;
	}
	// 密码验证
	if (!isTruePwd($("#password").val())) {
		dialog("密码格式错误",10,"","");
		return;
	}
	// 确认密码验证
	if ($("#rpassword").val()!=$("#password").val()) {
		dialog("两次输入密码不一致",10,"","");
		return;
	}
	// 提交机构
	$.ajax({
		url:baselocation+"/institution/addInstitution",
		type:"post",
		data:{
			"institution.instPictureUrl":$("#inst_url").val(),
			"institution.name":$("#name").val(),
			"institution.description":$("#description").val(),
			"institution.licensePictureUrl":$("#license_url").val(),
			"institution.applicant":$("#applicant").val(),
			"institution.idPictureUrl":$("#ID_url").val(),
			"institution.mobile":$("#mobile").val(),
			"institution.email":$("#email").val(),
			"password":$("#password").val(),
			"rpassword":$("#rpassword").val()
		},
		dataType:"json",
		success:function(result){
			if(result.success){
				$(".u-t-mechan-bg").removeClass("u-t-mechan-bg-2");
				$(".u-t-mechan-bg").addClass("u-t-mechan-bg-1");
				$(".step").hide();
				$(".step:eq(2)").show();
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
// 验证手机号码格式是否正确
function isTrueMobile(mobile) {
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
	if (reg.test(mobile)) {
		return true;//正确
	} else {
		return false;//错误
	}
}
// 验证邮箱格式是否正确
function isTrueEmail(email) {
	var reg = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
	if (reg.test(email)) {
		return true;//正确
	} else {
		return false;//错误
	}
}
// 验证网址格式是否正确
function isTrueUrl(url){
	var reg = /^((https?|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
	if(reg.test(url)){
		return true;// 正确
	}else {
		return false;//错误
	}
}
// 验证密码格式是否正确
function isTruePwd(password){
	var reg = /((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/;
	if (reg.test(password)) {
		return true;
	} else{
		return false;
	}
}
