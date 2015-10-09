<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>修改密码</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/ucenter/student/update_pwd.js"></script>
</head>
<body>
<form action="" id="searchForm">
<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-m-c-head"> 
						<ul id="uTabTitle" class="fl u-m-c-h-txt of"> 
							<li class="">
								<a title="基本信息" onclick="" href="/uc/user/uinfo">基本信息</a>
							</li> 
							<li class="current uHover">
								<a title="修改密码" onclick="" href="javascript:void(0)">修改密码</a>
							</li> 
							<li class="">
								<a title="修改头像" onclick="" href="/uc/user/avatar">修改头像</a>
							</li>
							<li class="">
								<a title="绑定手机" onclick="" href="/uc/user/jumpmobile">绑定手机</a>
							</li>   
						</ul> 
						<div class="clear"></div> 
					</section> 
					<div id="uTabCont" class="mt30"> 
						<article id="newWelcomeSellWayListUlId">
							<section class="mb50"> 
								<div class="right-person-box">
									<div class="right-person-box-infor">
										<ol class="password-body-boy">
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fisze20 c-org f-fM vam">*</tt>
													<tt class="fsize14 c-666 f-fM vam">旧密码：</tt>
												</span>
												<label class="pa-bo-boy-txt">
													<input id="oldPwd" class="input-1 fl" type="password" value="" maxlength="18" name="">
													<em id="oldPwdTip" class="tip"></em>
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
													<input id="newPwd" class="input-1 fl" type="password" value="" maxlength="18" name="">
													<em id="newPwdTip" class="tip"></em>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fisze20 c-org f-fM vam">*</tt>
													<tt class="fsize14 c-666 f-fM vam">确认密码：</tt>
												</span>
												<label class="pa-bo-boy-txt">
													<input id="newPwdConfirm" class="input-1 fl" type="password" value="" maxlength="18" name="">
													<em id="newPwdConfirmTip" class="tip"></em>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li class="tac">
												<a class="c-btn bing-btn" href="javascript:void(0)" onclick="save()">
													确定
												</a>
											</li>
										</ol>
									</div>
								</div>  
							</section> 
						</article>
					</div> 
				</section>   
			</article>
		</div>
	</form>
</body>
