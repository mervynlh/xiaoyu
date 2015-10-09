<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>基本信息</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/ucenter/student/u_info.js"></script>
</head>

<body>
<form action="" id="searchForm">
<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-m-c-head"> 
						<ul id="uTabTitle" class="fl u-m-c-h-txt of"> 
							<li class="current uHover">
								<a title="基本信息" onclick="" href="javascript:void(0)">基本信息</a>
							</li> 
							<li class="updatePwd">
								<a title="修改密码" onclick="" href="/uc/user/uppwd">修改密码</a>
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
						<article id="newWelcomeSellWayListUlId" style="">
							<section class="mb50"> 
								<div class="right-person-box">
									<div class="right-person-box-in mb30" id="showInfo">
										<div class="right-person-box-in-l fl">
											<p>
												<tt>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</tt>
												<tt>${queryUser.realname}</tt>
											</p>
											<p>
												<tt>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</tt>
												<tt>${queryUser.nickname}</tt>
											</p>
											<p>
												<tt>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</tt>
												<tt>
													<c:choose>
														<c:when test="${queryUser.gender==0}">男</c:when>
														<c:otherwise>女</c:otherwise>
													</c:choose>
												</tt>
											</p>
											<p>
												<tt>手机号码：</tt>
												<tt>${queryUser.mobile}</tt>
											</p>
											<p>
												<tt>邮箱：</tt>
												<tt>${queryUser.email}</tt>
											</p>
											<p>
												<tt>个人简介：</tt>
												<tt>${queryUser.userInfo}</tt>
											</p>
										</div>
										<div class="right-person-box-in-r fr u-teaname-btn">
											<a href="javascript:void(0)" onclick="editInfo()">编辑</a>
										</div>
										<div class="clear"></div>
									</div>
									<div id="editInfo" class="right-person-box-infor" style="display: none;">
										<ol class="password-body-boy">
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fisze20 c-org f-fM vam">*</tt>
													<tt class="fsize14 c-666 f-fM vam">姓名：</tt>
												</span>
												<label class="pa-bo-boy-txt">
													<input  class="input-1 fl" id="realname" type="text" value="${queryUser.realname}" maxlength="5" name="queryUser.realname" >
													<em id="realnameTip" class="tip"></em>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fisze20 c-org f-fM vam">*</tt>
													<tt class="fsize14 c-666 f-fM vam">昵称：</tt>
												</span>
												<label class="pa-bo-boy-txt pr">
													<input id="nicknamme" class="input-1 fl" type="text" value="${queryUser.nickname}" maxlength="7" name="queryUser.nickname">
<!-- 													<tt class="fsize14 f-fM c-999 pa" style="top:13px;right:-185px;">昵称在隐藏模式开启后显示</tt> -->
													<em id="nicknameTip" class="tip"></em>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fsize14 c-666 f-fM vam">邮箱：</tt>
												</span>
												<label class="pa-bo-boy-txt pr">
													<input id="email" class="input-1 fl" type="text" value="${queryUser.email}" maxlength="20" name="queryUser.email">
													<em id="emailTip" class="tip"></em>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li> 
												<span class="vam pa-bo-boy-tit">
													<tt class="fisze20 c-org f-fM vam">*</tt>
													<tt class="fsize14 c-666 f-fM vam">性别：</tt>
												</span> 
												<span class="vam pa-bo-boy-txt pa-bo-boy-txt-1">
													<input type="radio" style="margin: 0 0 0 10px;" placeholder=""  <c:if test="${queryUser.gender == 0}">checked="checked" </c:if> value="0" id="gender1" name="queryUser.gender"> 
													<label class="c-666 fsize14 f-fM" for="boy">男</label> 
													<input type="radio" style="margin: 0 0 0 30px;" placeholder="" <c:if test="${queryUser.gender == 1}">checked="checked" </c:if> value="1" id="gender0" name="queryUser.gender"> 
													<label class="c-666 fsize14 f-fM" for="girl">女</label> 
												</span>
												<div class="clear"></div>
											</li>
											<li>
												<span class="vam pa-bo-boy-tit">
													<tt class="fsize14 c-666 f-fM vam">个人简介：</tt>
												</span>
												<label class="pa-bo-boy-txt">
													<textarea style="height:213px;width: 429px;" class="input-1 fl" name="queryUser.userInfo" maxlength="300" id="userInfo">${queryUser.userInfo}</textarea>
													<div class="clear"></div>
												</label>
												<div class="clear"></div>
											</li>
											<li>
												<a class="c-btn bing-btn ml100" href="javascript:void(0)" onclick="save()">
													确定
												</a>
												<a class="c-btn bing-btn-fq ml40" href="javascript:cancel()">
													取消
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
</html>
