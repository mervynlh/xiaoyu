<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>登录--小雨在线教育</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/login/login.js"></script>
</head>
<body>
	<!-- /登录 -->
	<div class="rl-wrap-bg fromflag" >
		<section class="container">
			<div class="rl-r-box">
				<section class="mt100">
					<div class="rl-r-tab-title">
						<ul id="lr-title">
							<li class="current" id="stu_con">
								<a href="javascript: void(0);" id="btnStudent">学生登录</a>
							</li>
							<li class="" id="tea_con">
								<a href="javascript: void(0);" id="btnTeacher">教师登录</a>
							</li>
						</ul>
					</div>
					<input type="hidden" id="userType" value="0"/>
					<div class="rl-r-tab-cont" id="lr-cont">
						<section style="display: block;">
							<div class="rl-r-tab-c-l">
								<div class="hLh20 of undis">
									<span class="enwErrorMsg">
										<tt id="requestErrorID" class="o-wrong"></tt>
									</span>
								</div>
								<ol>
									<li class="" id="checkMobile">
										<label>
											<span class="rl-r-tab-ol-tit">
												<em class="icon-login-mz">&nbsp;</em>
											</span>
                                            <input type="text" id="mobile" placeholder="请输入手机号" maxlength="11">
                                            <a href="javascript:void(0)" onclick="cleanMobile()" class="rl-r-Close"></a>
                                            <span class="Error-ts fsize14 c-red f-fM undis" id="errorMobile"></span>
                                        </label>
									</li>
									<li class="" id="checkPassword">
										<label>
											<span class="rl-r-tab-ol-tit">
												<em class="icon-login-mm">&nbsp;</em>
											</span>
                                            <input type="password" placeholder="请输入您的密码" class="lTxt" value="" onkeypress="enters()" maxlength="16" name="" id="password" />
                                            <a href="javascript:void(0)" onclick="cleanPassword()" class="rl-r-Close"></a>
                                        </label>
									</li>
									<li class="pt10">
										<span class="inpCb hand">
											<input type="checkbox" id="autoThirty" class="c-icon" checked="checked" name="autoThirty">
											<tt class="vam c-666 fsize12 f-fH" for="forget">自动登录</tt>
										</span>
										<span class="ml10">
											<a class="c-org fsize12"" title="忘记密码？" href="${ctx }/forget_passwd">
												<u>忘记密码？</u>
											</a>
										</span>
										<span class="ml50">
											<tt class="vam c-666 fsize12 f-fH" for="forget">没有账号,</tt>
										</span>
										<span class="">
											<a class="c-org fsize12" title="注册账号" href="${ctx }/register">
												<u>免费注册</u>
											</a>
										</span>
									</li>
								</ol>
								<section class="rl-login-btn mt20 tac">
									<a title="登录" onclick="loginSubmit()" href="javascript:void(0)">登 录</a>
								</section>
							</div>
							<div class="rl-r-tab-c-r">
								<div id="thirdLogin" class="rl-r-t-c-r-box">
									<section class="tac">
										<span class="">
											<tt class="c-master">&mdash;&mdash;&mdash;&mdash;&mdash; </tt>
											<tt class="c-333">使用第三方账号快捷登录</tt>
											<tt class="c-master">&mdash;&mdash;&mdash;&mdash;&mdash; </tt>
										</span>
									</section>
									<section class="mt10 tac">
										<a href="javascript:oauthLogin('QQ')" title="QQ">
											<img src="${ctximg}/static/edu/img/qq.png" alt="">
										</a>
										<a href="javascript:oauthLogin('WEIXIN')" title="微信">
											<img src="${ctximg}/static/edu/img/wx.png" alt="">
										</a>
										<a href="javascript:oauthLogin('SINA')" title="新浪">
											<img src="${ctximg}/static/edu/img/xl.png" alt="">
										</a>
									</section>
								</div>
							</div>
							<div class="clear"></div>
						</section>
					</div>
				</section>
			</div>
		</section>
	</div>
</body>
</html>