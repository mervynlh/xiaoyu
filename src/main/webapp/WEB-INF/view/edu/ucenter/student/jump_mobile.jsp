<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>绑定手机</title>
	<style type="text/css">
		.mobile {margin-top:50px}
	</style>
	<script type="text/javascript" src="${ctx}/static/edu/js/ucenter/student/jump_mobile.js"></script>
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
							<li class="updatePwd">
								<a title="修改密码" onclick="" href="/uc/user/uppwd">修改密码</a>
							</li> 
							<li class="">
								<a title="修改头像" onclick="" href="/uc/user/avatar">修改头像</a>
							</li>
							<li class="current uHover">
								<a title="绑定手机" onclick="" href="javascript:void(0)">绑定手机</a>
							</li>   
						</ul> 
						<div class="clear"></div> 
					</section> 
					<!-- 中间内容开始 -->
					<article id="newWelcomeSellWayListUlId">
						<section class="mb50"> 
							<div class="right-person-box">
								<div class="right-person-box-infor-tit mb30 mobile" id="showMobile" >
									<p class="tac mb30">
										<tt class="fsize18 c-999 f-fM">当前手机号：</tt>
										<tt class="fsize18 c-master f-fM">${queryUser.mobile}</tt>
									</p>
									<div class="u-teaname-btn tac u-teaname-btn-per">
										<a href="javascript:void(0)" onclick="changeMobile()">更换号码</a>
									</div>
								</div>
								<div class="right-person-box-infor mobile" id="oldMobile" style="display: none">
									<ol class="password-body-boy">
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">当前手机号：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input id="oldmobile" class="input-1 fl" type="text" value="${queryUser.mobile}" disabled="disabled" maxlength="" name="">
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
												<input id="checkCode" type="text" class="input-2 fl" value="" maxlength="4" name="" onkeypress="enters()">
												<div class="pa-bo-boy-txt-in fl">
													<a id="getCheckCode" href="javascript:void(0)" onclick="getCheckCode()" class="xyxx c-btn">获取手机验证码</a>
												</div>
												<em id="oldcodeErr" style="display: none" class="vam fl Wrong ml10 mt13">&nbsp;</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<a class="c-btn bing-btn ml100" href="javascript:void(0)" onclick="next()">
												下一步
											</a>
										</li>
									</ol>
								</div>
								<div class="right-person-box-infor mobile" id="newMobile" style="display: none">
									<ol class="password-body-boy">
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">新手机号：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input id="newmobile" class="input-1 fl" type="text" value="" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" name="">
												<em id="newmobileErr" style="display: none" class="vam fl Wrong ml10 mt13 pr">&nbsp;
												<tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-130px;">请输入11位手机号码</tt>
												</em>
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
												<input id="newcheckcode" type="text" class="input-2 fl" value="" maxlength="4" name="" onkeypress="enterSave()">
												<div class="pa-bo-boy-txt-in fl">
													<a href="javascript:void(0)" id="getNewCheckCode" onclick="getNewCheckCode()" class="xyxx c-btn">获取手机验证码</a>
												</div>
												<em id="newcheckcodeErr" style="display: none" class="vam fl Wrong ml10 mt13">&nbsp;</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<a class="c-btn bing-btn ml100" href="javascript:void(0)" onclick="save()">
												绑定手机
											</a>
										</li>
									</ol>
								</div>
							</div>  
						</section> 
					</article>
		</div>
	</form>
</body>
</html>
