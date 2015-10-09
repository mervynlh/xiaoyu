<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>老师-个人中心--个人设置--小雨在线教育</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="title" content="">
	<meta name="author" content="268教育软件 - 北京易知路科技有限公司">

	<script src="${ctximg}/static/edu/js/ucenter/teacher/u_teacher.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
	<script type="text/javascript">
		$(function(){
			uploadattestation();
		})
	</script>
</head>
<body>
<input type="hidden" name="teacher.id" value="${teacher.id}" id="teacherId"/>
<input type="hidden" name="teacher.userId" value="${teacher.userId}" id="teacherUserId"/>
<input type="hidden" name="teacher.userExpand.realname" value="${teacher.userExpand.realname}" id="teacherRealname"/>

<input type="hidden" name="teacher.card" id="card_imagesUrl" value="${teacher.card}" />
<input type="hidden" name="teacher.education" id="education_imagesUrl" value="${teacher.education}" />
<input type="hidden" name="teacher.teaching" id="teaching_imagesUrl" value="${teacher.teaching}" />
<input type="hidden" name="teacher.specialty" id="specialty_imagesUrl" value="${teacher.specialty}" />
		<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-t-set-tit">
						<p class="hLh36">
							<tt class="fsize18 c-666 f-fM">身份认证</tt>
							<tt class="fsize14 c-org f-fM">（必填）</tt>
						</p>
						<div class="u-t-set-box mt30">
							<div>
								<div class="u-t-set-box-l fl">
									<em class="u-tx vam mr5">
										<c:if test="${teacher.cardStatus == 1}"><img src="/static/edu/img/u-dd.png" alt=""></c:if>
										<c:if test="${teacher.cardStatus == 2}"><img src="/static/edu/img/u-xg.png" alt=""></c:if>
										<c:if test="${teacher.cardStatus == 3 || teacher.cardStatus == 0}"><img src="/static/edu/img/u-tx.png" alt=""></c:if>
									</em>
									<tt class="fsize14 c-666 f-fM vam">
										个人身份认证（
										<c:if test="${teacher.cardStatus == 1}">审核中</c:if>
										<c:if test="${teacher.cardStatus == 2}">已认证</c:if>
										<c:if test="${teacher.cardStatus == 3 || teacher.cardStatus == 0}">尚未认证</c:if>
										）
									</tt>
									<tt class="fsize12 c-999 f-fM ml50 vam">
										必须为通过身份认证的真实身份老师
									</tt>
								</div>
								<div class="u-teaname-btn fr vam">
									<a href="javascript:void(0)" id="btn_card" onclick="onclickBtn('card', 'DOWN')">${(teacher.cardStatus == 3 || teacher.cardStatus == 0) ? "认证" : "查看"}</a>
								</div>
								<div class="clear"></div>
							</div>
							<div class="mt20" id="card_attestation" style="display:none">
									<div class="sftp pr mt10 fl" style="width:200px;height:200px">
										<img alt="" src="<%=staticImageServer%>${teacher.card}" height="200" width="200" id="card_img">
										<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
											<input type="file" id="fileupload_card"/>
										</a>
									</div>
								<div class="fl ml50">
									<p class="fsize12 c-org f-fM mt50">
										请提交您的身份证明
									</p>
									<div class="u-teaname-btn vam mt20">
										<a href="javascript:void(0)" onclick="onclickConfirm('card')">确认</a>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</section>
					<section class="u-t-set-tit mt50">
						<p class="hLh36">
							<tt class="fsize18 c-666 f-fM">其他资质证</tt>
							<tt class="fsize14 c-org f-fM">（选填）</tt>
						</p>
						<div class="u-t-set-box mt30">
							<ul>
								<li>
									<div>
										<div class="u-t-set-box-l fl">
											<span class="span-1">
												<em class="u-tx vam mr5">
													<c:if test="${teacher.educationStatus == 1}"><img src="/static/edu/img/u-dd.png" alt=""></c:if>
													<c:if test="${teacher.educationStatus == 2}"><img src="/static/edu/img/u-xg.png" alt=""></c:if>
													<c:if test="${teacher.educationStatus == 3 || teacher.educationStatus == 0}"><img src="/static/edu/img/u-tx.png" alt=""></c:if>
												</em>
												<tt class="fsize14 c-666 f-fM vam">
													学历认证（
													<c:if test="${teacher.educationStatus == 1}">审核中</c:if>
													<c:if test="${teacher.educationStatus == 2}">已认证</c:if>
													<c:if test="${teacher.educationStatus == 3 || teacher.educationStatus == 0}">尚未认证</c:if>
													）
												</tt>
											</span>
											<span>
												<tt class="fsize14 c-999 f-fM ml50 vam">
													学历认证
												</tt>
											</span>
										</div>
										<div class="u-teaname-btn fr vam">
											<a href="javascript:void(0)" id="btn_education" onclick="onclickBtn('education', 'DOWN')" style="margin-bottom:0;">
											${(teacher.educationStatus == 3 || teacher.educationStatus == 0) ? "认证" : "查看"}</a>
										</div>
										<div class="clear"></div>
									</div>
									<div class="mt20 ml30 mb50" id="education_attestation" style="display:none">
										<div class="sftp pr mt10 fl" style="width:200px;height:200px">
											<img alt="" src="<%=staticImageServer%>${teacher.education}" height="200" width="200" id="education_img">
											<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
												<input type="file" id="fileupload_education"/>
											</a>
										</div>
										<div class="fl ml50">
											<p class="fsize12 c-org f-fM mt50">
												请提交您的学历证明
											</p>
											<div class="u-teaname-btn vam mt20">
												<a href="javascript:void(0)" onclick="onclickConfirm('education')">确认</a>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li>
									<div>
										<div class="u-t-set-box-l fl">
											<span class="span-1">
												<em class="u-tx vam mr5">
													<c:if test="${teacher.teachingStatus == 1}"><img src="/static/edu/img/u-dd.png" alt=""></c:if>
													<c:if test="${teacher.teachingStatus == 2}"><img src="/static/edu/img/u-xg.png" alt=""></c:if>
													<c:if test="${teacher.teachingStatus == 3 || teacher.teachingStatus == 0}"><img src="/static/edu/img/u-tx.png" alt=""></c:if>
												</em>
												<tt class="fsize14 c-666 f-fM vam">
													教师证认证（
													<c:if test="${teacher.teachingStatus == 1}">审核中</c:if>
													<c:if test="${teacher.teachingStatus == 2}">已认证</c:if>
													<c:if test="${teacher.teachingStatus == 3 || teacher.teachingStatus == 0}">尚未认证</c:if>
													）
												</tt>
											</span>
											<span>
												<tt class="fsize14 c-999 f-fM ml50 vam">
													教师证认证
												</tt>
											</span>
										</div>
										<div class="u-teaname-btn fr vam">
											<a href="javascript:void(0)" id="btn_teaching" onclick="onclickBtn('teaching', 'DOWN')" style="margin-bottom:0;">
											${(teacher.teachingStatus == 3 || teacher.teachingStatus == 0) ? "认证" : "查看"}</a>
										</div>
										<div class="clear"></div>
									</div>
									<div class="mt20 ml30 mb50" id="teaching_attestation" style="display:none">
										<div class="sftp pr mt10 fl" style="width:200px;height:200px">
											<img alt="" src="<%=staticImageServer%>${teacher.teaching}" height="200" width="200" id="teaching_img">
											<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
												<input type="file" id="fileupload_teaching"/>
											</a>
										</div>
										<div class="fl ml50">
											<p class="fsize12 c-org f-fM mt50">
												请提交您的教师证
											</p>
											<div class="u-teaname-btn vam mt20">
												<a href="javascript:void(0)" onclick="onclickConfirm('teaching')">确认</a>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li>
									<div>
										<div class="u-t-set-box-l fl">
											<span class="span-1">
												<em class="u-tx vam mr5">
													<c:if test="${teacher.specialtyStatus == 1}"><img src="/static/edu/img/u-dd.png" alt=""></c:if>
													<c:if test="${teacher.specialtyStatus == 2}"><img src="/static/edu/img/u-xg.png" alt=""></c:if>
													<c:if test="${teacher.specialtyStatus == 3 || teacher.specialtyStatus == 0}"><img src="/static/edu/img/u-tx.png" alt=""></c:if>
												</em>
												<tt class="fsize14 c-666 f-fM vam">
													专业资质认证（
													<c:if test="${teacher.specialtyStatus == 1}">审核中</c:if>
													<c:if test="${teacher.specialtyStatus == 2}">已认证</c:if>
													<c:if test="${teacher.specialtyStatus == 3 || teacher.specialtyStatus == 0}">尚未认证</c:if>
													）
												</tt>
											</span>
											<span>
												<tt class="fsize14 c-999 f-fM ml50 vam">
													专业资质认证
												</tt>
											</span>
										</div>
										<div class="u-teaname-btn fr vam">
											<a href="javascript:void(0)" id="btn_specialty" onclick="onclickBtn('specialty', 'DOWN')" style="margin-bottom:0;">
											${(teacher.specialtyStatus == 3 || teacher.specialtyStatus == 0) ? "认证" : "查看"}</a>
										</div>
										<div class="clear"></div>
									</div>
									<div class="mt20 ml30 mb50" id="specialty_attestation" style="display:none" >
										<div class="sftp pr mt10 fl" style="width:200px;height:200px">
											<img alt="" src="<%=staticImageServer%>${teacher.specialty}" height="200" width="200" id="specialty_img">
											<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
												<input type="file" id="fileupload_specialty"/>
											</a>
										</div>
										<div class="fl ml50">
											<p class="fsize12 c-org f-fM mt50">
												请提交您的专业资质证明
											</p>
											<div class="u-teaname-btn vam mt20">
												<a href="javascript:void(0)" onclick="onclickConfirm('specialty')">确认</a>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</li>
								<li>
									<div>
										<div class="u-t-set-box-l fl">
											<span class="span-1">
												<em class="u-tx vam mr5">
													<c:if test="${teacher.isProfessor == 1 || teacher.isProfessor == 0}"><img src="/static/edu/img/u-tx.png" alt=""></c:if>
													<c:if test="${teacher.isProfessor == 2}"><img src="/static/edu/img/u-xg.png" alt=""></c:if>
													<c:if test="${teacher.isProfessor == 3}"><img src="/static/edu/img/u-dd.png" alt=""></c:if>
												</em>
												<tt class="fsize14 c-666 f-fM vam">
													韩教授认证（
													<c:if test="${teacher.isProfessor == 1 || teacher.isProfessor == 0}">未认证</c:if>
													<c:if test="${teacher.isProfessor == 2}">已认证</c:if>
													<c:if test="${teacher.isProfessor == 3}">审核中</c:if>
													）
												</tt>
											</span>
											<span>
												<tt class="fsize14 c-999 f-fM ml50 vam">
													韩教授认证
												</tt>
											</span>
										</div>
										<div class="u-teaname-btn fr vam">
											<a href="javascript:void(0)"
											   <c:if test="${teacher.isProfessor == 1 || teacher.isProfessor == 0}">onclick="attestationApply(${teacher.id})" </c:if>
											   style="margin-bottom:0;">
												<c:if test="${teacher.isProfessor == 1 || teacher.isProfessor == 0}">申请认证</c:if>
												<c:if test="${teacher.isProfessor == 2}">已认证</c:if>
												<c:if test="${teacher.isProfessor == 3}">已申请认证</c:if>
											</a>
										</div>
										<div class="clear"></div>
									</div>
								</li>
							</ul>
						</div>
					</section>
				</section>   
			</article>
		</div>
</body>
</html>