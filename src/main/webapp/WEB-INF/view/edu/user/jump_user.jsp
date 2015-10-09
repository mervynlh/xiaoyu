<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定手机号码</title>
<script type="text/javascript">
	$(function(){
		$(".role").on("click",function(){
			$(".role").removeClass("current");
			$(this).addClass("current");
			$("#role").val($(this).attr("lang"));
		});
		// 手机号
		$("#mobile").blur(function() {
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
	});
	//获取校验码
	function getCheckCode() {
		var mobile = $("#mobile").val();
		var randomCode = $("#randomCode").val();
		$("#errorMobile").removeClass();
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test(mobile)) {
			dialog('手机格式不正确',9,'','');
			$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorMobile").addClass("Correct vam ml5 fl mt13");
		}
		$("#errorRandom").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test(randomCode)) {
			dialog('请输入图形码',9,'','');
			$("#errorRandom").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorRandom").addClass("Correct vam ml5 fl mt13");
		}
		$.ajax({
			url : "/getCheckCode",
			type : "post",
			data : {
				"mobile" : mobile,
				"randomCode":randomCode
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					$("#getCheckCode").removeAttr("onclick");
					timer();
					alert(result.entity);
					//dialog('发送成功，请稍等',8,'','');
				} else if(result.message == 'randomError'){
					$("#randomCode").val("");
					$('#randomPic').click();
					dialog('验证码错误',9,'','');
				} else if(result.message=='regMobileExist'){
					dialog('该手机号已绑定',9,'','');
					$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
				}else{
					dialog(result.message,9,'','');
				}
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
		$("#getCheckCode").text("请稍等……" + time);
		time -= 1;
		setTimeout("timer();", 1000);
	}
	// 绑定提交
	function jumpUser(){
		var userId = $("#userId").val();// 用户id
		var mobile = $("#mobile").val();// 手机号
		var checkCode = $("#checkCode").val();// 校验码
		var role = $("#role").val();// 用户角色
		
		// 手机验证
		$("#errorMobile").removeClass();
		var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
		if (!reg.test(mobile)) {
			dialog('手机格式不正确',9,'','');
			$("#errorMobile").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#errorMobile").addClass("Correct vam ml5 fl mt13");
		}
		// 校验码验证
		$("#checkCodeRandom").removeClass();
		var reg = /^\d{4}$/;
		if (!reg.test(checkCode)) {
			dialog('请输入手机验证码',9,'','');
			$("#checkCodeRandom").addClass("vam fl Wrong ml10 mt13");
			return;
		} else {
			$("#checkCodeRandom").addClass("Correct vam ml5 fl mt13");
		}
		$.ajax({
			url :"/front/jumpUser",
			type : "post",
			data : {
				"userId" : userId,
				"checkCode":checkCode,
				"mobile":mobile,
				"role":role
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					window.location.href="/uc/home"
				} else if(result.message== 'mobileIsNull'){
					$("#mobile").val("");
					$("#errorMobile").removeClass();
					dialog('请填写手机号码',9,'','');
				} else if(result.message== 'mobileFormatError'){
					$("#mobile").val("");
					$("#errorMobile").removeClass();
					dialog('手机号码格式不正确',9,'','');
				} else if(result.message == 'mobileIsHave'){
					$("#mobile").val("");
					$("#checkCodeRandom").removeClass();
					dialog('该手机号已绑定',9,'','');
				} else if(result.message == 'checkCodeIsNull'){
					$("#randomCode").val("");
					$("#checkCodeRandom").removeClass();
					dialog('校验码必填',9,'','');
				} else if(result.message == 'checkCodeError'){
					$("#randomCode").val("");
					$("#checkCodeRandom").removeClass();
					dialog('校验码错误',9,'','');
				} else{
					dialog('绑定失败，请刷新页面重试',9,'','');
				}
				$("#randomPic").click();
			}
		});
	}
</script>
</head>

<body>
	<form action="" method="post">
	<input id="userId" type="hidden" name="userId" value="${userId}"/>
	<input id="role" type="hidden" value="0"/>
	<div class="body">
		<section class="container">
			<div class="register-tit">
				
				<section class="mt50 register-boy">
					<div class="fl w50pre tac role current" lang="0">
						<img src="/static/edu/img/Student.png" alt="">
						<div class="mt30 tac">
							<span class="c-master fsize24 f-fM">A.</span>
							<a href="javascript:void(0)" class="reg-btn c-btn">我是学生</a>
						</div>
					</div>
					<div class="fl w50pre role tac" lang="1">
						<img src="/static/edu/img/Teacher.png" alt="">
						<div class="mt30 tac">
							<span class="c-master fsize24 f-fM">B.</span>
							<a href="javascript:void(0)" class="reg-btn c-btn">我是老师</a>
						</div></div>
					<div class="clear"></div>
				</section>
			</div>
			<div>
				<div class="register-body-tit tac mt50">
					
				</div>
				<div class="register-body-boy mt50 pb100">
					<section class="container">
						<div class="password-body">
							<div class="password-body-tit">
								<span class="fsize20 c-master f-fM">请输入手机号完成绑定</span>
							</div>
							<ol class="password-body-boy">
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">手机号：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input id="mobile" class="input-1 fl" type="text" value="" maxlength="11" name="" onkeyup="this.value=this.value.replace(/\D/g,'')">
										<em class="" id="errorMobile">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
								</li>
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">图形码：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input id="randomCode" class="input-2 fl" type="text" value="" maxlength="4" name="" onkeyup="this.value=this.value.replace(/\D/g,'')">
										<div class="pa-bo-boy-txt-in fl">
											<span class="">
												<img src="${ctx}/ran/random" id="randomPic" alt="图形码" onclick="this.src='${ctx}/ran/random?v='+Math.random()">
											</span>
											<a href="javascript:$('#randomPic').click()" class="fsize14 c-666 f-fM ml5">看不清</a>
										</div>
										<em class="" id="errorRandom">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
								</li>
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">效验码：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input id="checkCode" type="text" name="" maxlength="4" value="" class="input-2 fl" onkeyup="this.value=this.value.replace(/\D/g,'')">
										<div class="pa-bo-boy-txt-in fl">
											<a class="xyxx c-btn" href="javascript:void(0);" onclick="getCheckCode()" id="getCheckCode">获取手机验证码</a>
										</div>
										<em class="vam fl Wrong ml10 mt13">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
								</li>
								<li>
									<a href="javascript:void(0)" onclick="jumpUser()" class="c-btn bing-btn ml100">
										确认绑定
									</a>
									<a href="/" class="c-btn bing-btn-fq ml40">
										放弃绑定
									</a>
								</li>
							</ol>
						</div>
					</section>
				</div>
			</div>
		</section>
	</div>
	</form>
</body>
</html>
