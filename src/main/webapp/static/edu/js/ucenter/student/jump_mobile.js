//更换手机号码
function changeMobile(){
	$(".mobile").hide();
	$("#oldMobile").show();
}
//旧手机获取验证码
function getCheckCode(){
	var mobile=$("#oldmobile").val();
	$.ajax({
		url : "/getCheckCode",
		type : "post",
		data : {
			"mobile" : mobile,
			"codeType":"oldMobile"
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				$("#getCheckCode").removeAttr("onclick");
				timer('getCheckCode');
				dialog("发送成功，请稍等",8,'','');
			} else{
				dialog(result.message,10,'','');
			}
		}
	});
}
function enters(){
	if(event.keyCode==13){//13 回车键
		next();
	}
}
//下一步操作
function next(){
	$("#oldcodeErr").hide();
	var checkCode=$("#checkCode").val();
	if(isEmpty(checkCode)){
		$("#oldcodeErr").show();
		return;
	}
	$.ajax({
		url : "/uc/ajax/checkMySelf",
		type : "post",
		data : {
			"checkCode":checkCode
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				$(".mobile").hide();
				$("#newMobile").show();
			} else{
				dialog(result.message,9,'','');
			}
		}
	});
}
//新手机获取验证码
function getNewCheckCode(){
	$("#newmobileErr").hide();
	
	var mobile=$("#newmobile").val();
	if(isEmpty(mobile)){
		$("#newmobileErr").show();
		return;
	}
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if(!reg.test(mobile)){
		$("#newmobileErr").show();
		return;
	}
	$.ajax({
		url : "/getCheckCode",
		type : "post",
		data : {
			"mobile" : mobile,
			"codeType":"newMobile"
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				$("#getNewCheckCode").removeAttr("onclick");
				timer('getNewCheckCode');
				dialog("发送成功，请稍等",8,'','');
			}else if(result.message=="regMobileExist"){
				dialog("手机号码已存在",10,'','');
				$("#newmobileErr").html('<tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-130px;">手机号码已存在</tt>');
				$("#newmobileErr").show();
			} else{
				dialog(result.message,9,'','');
			}
		}
	});
}
function enterSave(){
	if(event.keyCode==13){//13 回车键
		save();
	}
}
//绑定新手机操作
function save(){
	$("#newcheckcodeErr").hide();
	var mobile=$("#newmobile").val();
	var newcheckcode=$("#newcheckcode").val();
	if(isEmpty(newcheckcode)){
		$("#newcheckcodeErr").show();
		return;
	}
	$.ajax({
		url : "/uc/ajax/student/jumpmobile",
		type : "post",
		data : {
			"checkCode":newcheckcode,
			"mobile":mobile
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				dialog("绑定成功",17,'','/uc/user/jumpmobile');
			} else{
				if(result.message=="checkNo"){
					window.location.reload();
				}else{
					dialog(result.message,10,'','');
				}
			}
		}
	});
}
// 倒计时
var time = 60;
function timer(id) {
	var service=id+"()";
	if (time < 0) {
		$("#"+id).attr("onclick", service);
		$("#"+id).text("获取手机验证码");
		time = 60;
		return;
	}
	$("#"+id).text("请稍等……" + time);
	time -= 1;
	setTimeout("timer('"+id+"');", 1000);
}