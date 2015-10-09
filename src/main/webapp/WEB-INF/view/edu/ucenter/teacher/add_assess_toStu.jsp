<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>发布评论</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript">
		var defaultSubjectId='${teacher.teacherSubjects[0].subjectId}';//第一阶段的id
		var defaultSubjectName='${teacher.teacherSubjects[0].subjectName}';//第一阶段的名称
	</script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/teacher/addAssess.js"></script>
</head>
<body>
<input id="subjectId" type="hidden" value=""/>
<input id="subjectName" type="hidden" value=""/>
<input id="teacherId" type="hidden" value="${teacher.id }"/>
<input id="userId" type="hidden" value="${user.id}"/>
<input id="majorName" type="hidden" value=""/>
<input id="courseModelValue" type="hidden" value=""/>
<input id="description" type="hidden" value=""/>
<input id="attribute" type="hidden" value=""/>
<input id="speed" type="hidden" value=""/>
<input id="orderId" type="hidden"value="${orderId }"/>
<section class="main">
	<div id="aCoursesList" class="pb50">
		<section class="container">
			<div class="mt30">
				<div class="Evaluation-box pr">
					<div id="select-1">
						<div class="selectUI fl">
							<div style="width:145px;" class="job-select">
								<section class="j-s-defalt">
									<em class="icon14 fr mt5">&nbsp;</em>
									<span>${teacher.teacherSubjects[0].subjectName}</span>
								</section>
								<section class="j-s-option" style="display: none;">
									<div class="j-s-o-box">
										<c:forEach items="${teacher.teacherSubjects}" var="sub">
											<p><a title="" href="javascript: void(0)" onclick="chooseSub('${sub.subjectId}','${sub.subjectName}')">${sub.subjectName}</a></p>
										</c:forEach>
									</div>
								</section>
							</div>
						</div>
						<div class="selectUI fl">
							<div class="job-select" style="width:145px;">
								<section id="majorOption" class="j-s-defalt">
									<em class="icon14 fr mt5">&nbsp;</em>
									<span>请选择科目</span>
								</section>
								<section class="j-s-option">
									<div id="majorCourse" class="j-s-o-box">
									</div>
								</section>
							</div>
						</div>
						<div class="selectUI fl">
							<div class="job-select" style="width:145px;">
								<section id="modelOption" class="j-s-defalt">
									<em class="icon14 fr mt5">&nbsp;</em>
									<span>授课方式</span>
								</section>
								<section class="j-s-option">
									<div class="j-s-o-box">
										<p><a id="TEACHERVISIT" class="models" title="TEACHERVISIT" lang="" onclick="chooseModel(this)" href="javascript: void(0)">老师上门</a></p>
										<p><a id="STUDENTVISIT" class="models" title="STUDENTVISIT" lang="" onclick="chooseModel(this)" href="javascript: void(0)">学生上门</a></p>
										<p><a id="TALKADDRESS" class="models" title="TALKADDRESS" lang="" onclick="chooseModel(this)" href="javascript: void(0)">协商地点</a></p>
										<p><a id="ONLINEOTO" class="models" title="ONLINEOTO" lang="" onclick="chooseModel(this)" href="javascript: void(0)">在线教学</a></p>
									</div>
								</section>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="Evaluation-box-tit mt30" id="cStar">
						<p class="hLh30">
							<tt class="fsize16 f-fM c-666 vam">发表对</tt>
							<tt class="fsize16 f-fM c-org vam">${user.realname}同学</tt>
							<tt class="fsize16 f-fM c-666 vam">的评价</tt>
						</p>
						<ul class="clearfix E-box-list mt30">
							<li>
								<div class="b-in-talk-box">
									<input type="radio" class="type" style="margin: 0 0 0 10px;"  checked="checked" placeholder="" checked="checked" value="1" id="" name="queryUser.gender">
									<em class="icon30 icon-hp vam ml10">&nbsp;</em>
									<tt class="fsize14 f-fM c-master-2 vam ml5">好评（${teacher.goodAssess}）</tt>
								</div>
								<div class="b-in-talk-box-in mt20">
									<span class="fl">
										<tt class="fsize14 f-fM c-666">课程评分：</tt> 
									</span> 
									<div class="fl"> 
										<section class="c-star-wrap-2 pr"> 
											<ol class="clearfix cStar-2" lang="description"> 
												<li class="">
													<a href="javascript:void(0)" lang="1" title="1分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="2" title="2分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="3" title="3分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="4" title="4分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="5" title="5分">&nbsp;</a>
												</li> 
											</ol>
										</section> 
									</div>
									<div class="clear"></div>
								</div>
							</li>
							<li>
								<div class="b-in-talk-box">
									<input type="radio" class="type" style="margin: 0 0 0 10px;" placeholder=""  value="2" id="" name="queryUser.gender">
									<em class="icon30 icon-zp vam ml10">&nbsp;</em>
									<tt class="fsize14 f-fM c-blue vam ml5">中评（${teacher.middleAssess}）</tt>
								</div>
								<div class="b-in-talk-box-in mt20">
									<span class="fl">
										<tt class="fsize14 f-fM c-666">教学态度：</tt> 
									</span> 
									<div class="fl"> 
										<section class="c-star-wrap-2 pr"> 
											<ol class="clearfix cStar-2" lang="attribute"> 
												<li class="">
													<a href="javascript:void(0)" lang="1" title="1分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="2" title="2分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="3" title="3分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="4" title="4分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="5" title="5分">&nbsp;</a>
												</li> 
											</ol>
										</section> 
									</div>
									<div class="clear"></div>
								</div>
							</li>
							<li>
								<div class="b-in-talk-box">
									<input type="radio" class="type" style="margin: 0 0 0 10px;" placeholder="" value="3" id="" name="queryUser.gender">
									<em class="icon30 icon-cp vam ml10">&nbsp;</em>
									<tt class="fsize14 f-fM c-999 vam ml5">差评（${teacher.badAssess}）</tt>
								</div>
								<div class="b-in-talk-box-in mt20">
									<span class="fl">
										<tt class="fsize14 f-fM c-666">相应速度：</tt> 
									</span> 
									<div class="fl"> 
										<section class="c-star-wrap-2 pr"> 
											<ol class="clearfix cStar-2" lang="speed"> 
												<li class="">
													<a href="javascript:void(0)" lang="1" title="1分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="2" title="2分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="3" title="3分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript: void(0);" lang="4" title="4分">&nbsp;</a>
												</li> 
												<li class="">
													<a href="javascript:void(0)" lang="5" title="5分">&nbsp;</a>
												</li> 
											</ol>
										</section> 
									</div>
									<div class="clear"></div>
								</div>
							</li>
						</ul>
					</div>
					<div class="Evaluation-box-pl mt30">
						<textarea id="content" maxlength="30" class="vam"></textarea>
					</div>
					<a href="javascript:uploadImg()" class="icon-sctp">
						<img src="${ctx}/static/edu/img/icon-tp.png" alt="">
					</a>
					<div class="Evaluation-box-tp" style="display: none;">
						<ul id="pics" class="clearfix e-b-tp-list">
							<li id="last" class="last">
								<section class="uploadBtnWrap pr"> 
									<div class="ke-inline-block ">
										<input width="70" type="button" value="浏览" id="fileupload" style="display: none;"> 
									</div>
									<div id="fileQueue" class="mt10"></div>
								</section>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					<ol class="password-body-boy mt50" style="width:580px;">
						<li class="tac">
							<a class="c-btn qd-btn" href="javascript:save()" style="margin-left:0;">
								提交评价
							</a>
						</li>
					</ol>
				</div>
			</div>
		</section>
	</div>
</section>
	<script src="${ctx}/static/edu/js/web/commJs.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			selFun("#select-1");// 模拟 select 下拉控件
		});
	</script>
</body>
</html>