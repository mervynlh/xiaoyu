<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>忘记密码--小雨在线教育</title>
	<script src="${ctx}/static/edu/js/login/forgetPwd.js" type="text/javascript" ></script>
</head>
<body>
	<div class="body">
		<section class="container">
			<div class="password-body" id="firstStep">
				<div class="password-body-tit">
					<span class="fsize20 c-org f-fM">忘记密码 > 手机方式找回密码</span>
				</div>
				<ol class="password-body-boy">
					<li>
						<span class="vam pa-bo-boy-tit">
							<tt class="fisze20 c-org f-fM vam">*</tt>
							<tt class="fsize14 c-666 f-fM vam">手机号：</tt>
						</span>
						<label class="pa-bo-boy-txt">
							<input  class="input-1 fl" type="text" value="" maxlength="11" name="" id="mobile">
							<em id="errorMobile" class="">&nbsp;</em>
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
							<input  class="input-2 fl" type="text" value="" maxlength="4" name="" id="random">
							<div class="pa-bo-boy-txt-in fl">
								<span class="">
									<img src="${ctx}/ran/random" alt="验证码，点击图片更换" id="randomPic" onclick="this.src='${ctx}/ran/random?v='+Math.random()"/>
								</span>
								
								<a href="javascript:void(0)" onclick="changePic()" class="fsize14 c-666 f-fM ml5">看不清</a>
							</div>
							<em class="" id="errorRandom">&nbsp;</em>
							<div class="clear"></div>
						</label>
						<div class="clear"></div>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="checkRandomCode()" class="c-btn qd-btn">
							下一步
						</a>
					</li>
				</ol>
			</div>
			<div class="password-body" id="nextStep">
				<div class="password-body-tit">
					<span class="fsize18 c-999 f-fM">请输入您手机 <tt class="c-333"id="showMobile">18935662222</tt>上收到的短信验证码</span>
				</div>
				<ol class="password-body-boy">
					<li>
						<span class="vam pa-bo-boy-tit">
							<tt class="fisze20 c-org f-fM vam">*</tt>
							<tt class="fsize14 c-666 f-fM vam">效验码：</tt>
						</span>
						<label class="pa-bo-boy-txt">
							<input  class="input-2 fl" type="text" value="" maxlength="4" name="" id="checkCode">
							<div class="pa-bo-boy-txt-in fl">
								<a href="javascript:void(0)" onclick="getCheckCode()" class="xyxx c-btn" id="getCheckCode">获取手机验证码</a>
							</div>
							<em class="" id="errorCheckCode">&nbsp;</em>
							<div class="clear"></div>
						</label>
						<div class="clear"></div>
					</li>
					<li>
						<span class="vam pa-bo-boy-tit">
							<tt class="fisze20 c-org f-fM vam">*</tt>
							<tt class="fsize14 c-666 f-fM vam">新密码：</tt>
						</span>
						<label class="pa-bo-boy-txt">
							<input  class="input-1 fl" type="password" value="" maxlength="16" name="" id="password">
							<em class="" id="errorPassword">&nbsp;</em>
							<div class="clear"></div>
						</label>
						<div class="clear"></div>
						<p class="tar fsize12 c-999 f-fA mt5 pr50">密码长度8-16位，数字、字母、字符至少包括两种</p>
					</li>
					<li>
						<span class="vam pa-bo-boy-tit">
							<tt class="fisze20 c-org f-fM vam">*</tt>
							<tt class="fsize14 c-666 f-fM vam">确认新密码：</tt>
						</span>
						<label class="pa-bo-boy-txt">
							<input  class="input-1 fl" type="password" value="" maxlength="16" name="" id="repassword">
							<em class="" id="errorRePassword">&nbsp;</em>
							<div class="clear"></div>
						</label>
						<div class="clear"></div>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="updatePassword()" id="updatePassword" class="c-btn qd-btn tj-btn">
							提交
						</a>
					</li>
				</ol>
			</div>
		</section>
	</div>
</body>
</html>