<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<%@page import="com.yizhilu.os.common.constants.WeixinConstants"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>注册--小雨在线教育</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/login/register.js"></script>
	<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
	<script type="text/javascript">
		 $(function(){
			 var obj = new WxLogin({
		         id:"weixinverify", 
		         appid: "<%=WeixinConstants.appid %>", 
		         scope: "snsapi_login", 
		         redirect_uri: "<%=WeixinConstants.redirect_uri %>",
		         state: "",
		         style: "",
		         href: "<%=WeixinConstants.href%>"
		       });
		 });
	</script>
</head>
<body>
	<div class="body">
		<section class="container">
			<div class="register-tit">
				<h3 class="hLh36 tac unFw c-master fsize24 f-fM mt50">亲，你是什么角色</h3>
				<section class="mt50 register-boy">
					<div class="fl w50pre tac current" id="stu_con">
						<img src="${ctximg}/static/edu/img/Student.png" alt="学生">
						<div class="mt30 tac">
							<span class="c-master fsize24 f-fM">A.</span>
							<a href="javascript:void(0)" class="reg-btn c-btn" id="btnStudent">我是学生</a>
						</div>
					</div>
					<div class="fl w50pre tac" id="tea_con">
						<img src="${ctximg}/static/edu/img/Teacher.png" alt="老师">
						<div class="mt30 tac">
							<span class="c-master fsize24 f-fM">B.</span>
							<a href="javascript:void(0)" class="reg-btn c-btn" id="btnTeacher">我是老师</a>
						</div></div>
					<div class="clear"></div>
				</section>
			</div>
			<div id="studentBox">
				<div class="register-body-tit tac mt50">
					<span class="fsize18 c-333 f-fM" id="typeName">学生注册</span>
				</div>
				<input type="hidden" id="userType" value="0"/>
				<form action="#" method="post" id="registerSubmit">
				<div class="register-body-boy mt50 pb100">
					<div class="fl w50pre">
						<div class="box-in pl100">
							<ol class="password-body-boy">
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">手机号：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input  class="input-1 fl" type="text" value="" maxlength="11" name="" id="mobile">
										<em class="" id="errorMobile">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
								</li>
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">验证码：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input  class="input-2 fl" type="text" value="" maxlength="4" name="" id="randomCode">
										<div class="pa-bo-boy-txt-in fl">
											<span class="">
												<img src="${ctx}/ran/random" alt="验证码，点击图片更换" id="randomPic" onclick="this.src='${ctx}/ran/random?v='+Math.random()"/>
											</span>
											<a href="javascript:void(0)" onclick="changePic()"class="fsize14 c-666 f-fM ml5">看不清</a>
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
										<input  class="input-2 fl" type="text" value="" maxlength="4" name="checkCode" id="checkCode">
										<div class="pa-bo-boy-txt-in fl">
											<a href="javascript:void(0)" onclick="getCheckCode()" id="getCheckCode" class="xyxx c-btn">获取手机验证码</a>
										</div>
										<em class="" id="checkCodeRandom">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
								</li>
								<li>
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">输入密码：</tt>
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
										<tt class="fsize14 c-666 f-fM vam">确认密码：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input  class="input-1 fl" type="password" value="" maxlength="16" name="" id="repassword">
										<em class="" id="errorRepassword">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
									<div class="mt10 pr50 pl100">
										<span class="inpCb hand">
											<input type="checkbox" name="autoThirty"  class="c-icon" id="selectCode">
											<tt for="forget" class="vam c-666 fsize12 f-fH">我有邀请码</tt>
										</span>
										<span class="ml160">
											<tt for="forget" class="vam c-666 fsize12 f-fH">已有账号,</tt>
										</span>
										<span class="">
											<a href="${ctx }/login" title="已有账号，去登录" class="c-org fsize12">
												<u>去登录</u>
											</a>
										</span>
									</div>
								</li>
								<li>
									<div  id="extendCodePage" class="undis">
									<span class="vam pa-bo-boy-tit">
										<tt class="fisze20 c-org f-fM vam">*</tt>
										<tt class="fsize14 c-666 f-fM vam">输入邀请码：</tt>
									</span>
									<label class="pa-bo-boy-txt">
										<input  class="input-1 fl" type="text" value="" maxlength="" name="" id="extendCode">
										<em class="" id="errorExtendCode">&nbsp;</em>
										<div class="clear"></div>
									</label>
									<div class="clear"></div>
									</div>
									<div class="mt10 pr50 pl100">
										<span class="inpCb hand">
											<input type="checkbox" name="autoThirty" checked="checked" class="c-icon" id="agreement">
											<tt for="forget" class="vam c-666 fsize12 f-fH">我已同意并阅读</tt>
										</span>
										<span class="">
											<a href="${ctx }/help?id=191" title="用户注册协议" class="c-org fsize12">
												<u>《用户注册协议》</u>
											</a>
										</span>
									</div>
								</li>
								<li>
									<a href="javascript:void(0)" onclick="doRegister()" class="c-btn qd-btn tj-btn">
										立即注册
									</a>
								</li>
							</ol>
						</div>
					</div>
					<div class="fl w50pre">
						<div class="box-in-r pl140 pt100 ml30">
							<p class="fsize14 c-333 f-fM ">微信扫描二维码快速注册</p>
							<p class="fsize14 c-999 f-fM mt10">扫描>关注>确认>安全注册成功</p>
							<span id="weixinverify" style="width: 100px;height: 20px;">
							
							</span>
<!-- 							<img src="img/pic/ewm.jpg" alt="" class="ewm mt20"> -->
							<div class="rl-r-t-c-r-box" id="thirdLogin">
								<section class="tac">
									<span class="">
										<tt class="c-master">&mdash;&mdash;&mdash;&mdash;&mdash; </tt>
										<tt class="c-333">使用第三方账号快捷登录</tt>
										<tt class="c-master">&mdash;&mdash;&mdash;&mdash;&mdash; </tt>
									</span>
								</section>
								<section class="mt10 tac">
									<a href="javascript:oauthLogin('QQ')" title="QQ">
										<img alt="" src="${ctximg}/static/edu/img/qq.png">
									</a>
									<a href="javascript:oauthLogin('WEIXIN')" title="微信">
										<img alt="" src="${ctximg}/static/edu/img/wx.png">
									</a>
									<a href="javascript:oauthLogin('SINA')" title="新浪">
										<img alt="" src="${ctximg}/static/edu/img/xl.png">
									</a>
								</section>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				</form>
			</div>
		</section>
	</div>
</body>
</html>