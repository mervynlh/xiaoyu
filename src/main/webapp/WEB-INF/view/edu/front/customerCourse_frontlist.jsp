<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course_customer.js"></script>
<title>自定义课程</title>
<script type="text/javascript">
$(function (){
	$(".changesClass").keyup(function (){
		$(this).next('span').html('');
	});
});

</script>
<style type="text/css">
.cssFrom{margin: -1px -25px 7px 4px;}
</style>
</head>
<body>
	<!-- /自定义课程 -->
	<div class="custom-course-wrap">
		<section class="w1000">
			<div class="pathwray">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
					<li><span>自定义课程列表</span></li>
				</ol>
			</div>
			<div class="of">
				<section class="mt20 mb20">
					<h2 class="fsize20 f-fM c-4e unFw tac">想学什么课程，让我们来帮你实现！已有<font class="fsize24 c-orange">${page.totalResultSize}</font>人参加</h2>
				</section>
				<section class="custom-course">
					<div>
						<div class="c-c-head of">
							<span class="fr c-c-up-down hand" title="关闭"><em id="qitEmId" class="icon14 c-c-down">&nbsp;</em></span>
							<h4 class="unFw"><em class="icon18 c-c-icon vam mr5">&nbsp;</em><font class="c-666 fsize14 vam">请填写你要自定义的课程信息  （<tt class="c-orange">*</tt>为必填项）</font></h4>
						</div>
						<div class="c-c-body">
							<section class="clearfix">
								<div class="fl w50pre">
									<ul class="c-c-li">
										<li>
											<p><span><em class="c-orange">*</em> 课程主题:</span></p>
											<input class="changesClass" type="text" id="title" name="customerCourse.title"/>
											<span class="titleErrorClass"></span>
										</li>
										<li>
											<p><span><em class="c-orange">*</em> 课程讲师:</span></p>
											<input class="changesClass" type="text" id="teacherName" name="customerCourse.teacherName"/>
											<span class="teacherNameErrorClass"></span>
										</li>
									</ul>
								</div>
								<div class="fr w50pre">
									<div class="ml20">
										<ul class="c-c-li">
											<li>
												<p><span>课程描述:</span></p>
												<textarea id="content" class="changesClass" name="customerCourse.content"></textarea>
											</li>
										</ul>
									</div>
								</div>
							</section>
							<section class="c-c-b-tip">
								<span>反馈信息：我希望收到这门课程上线的通知 </span>
							</section>
							<section class="mt30">
								<section class="clearfix">
									<div class="fl w50pre">
										<ul class="c-c-li">
											<li>
												<p><span>手机号码:</span></p>
												<input type="text" class="changesClass" id="mobile" name="customerCourse.mobile"/>
												<span class="mobileErrorClass"></span>
											</li>
										</ul>
									</div>
									<div class="fr w50pre">
										<div class="ml20">
											<ul class="c-c-li">
												<li>
													<p><span>电子邮箱:</span></p>
													<input class="changesClass" type="text" id="email" name="customerCourse.email"/>
													<span class="emailErrorClass"></span>
												</li>
											</ul>
										</div>
									</div>
								</section>
							</section>
							<section class="mt20 tac">
								<label><input type="button" onclick="checkCustomCourse()" value="提交课程" class="c-c-sub-btn"></label>
							</section>
						</div>
					</div>
				</section>
			</div>
			<!-- /自定义课程 -->
			
		</section><div id="intro"></div>
	</div>
	
	<!-- /自定义课程 结束 -->
	<section class="c-all-list pt20 mb50">
		<div class="mt20">
			<header class="sort-c-head" style="padding: 0;">
				<section class="w1000 clearfix">
					<div class="fl">
						<span class="disIb c-a-l-title vam b-master">自定义课程列表</span>
						<tt class="vam ml10 c-999">共有${page.totalResultSize}个自定义课程</tt>
					</div>
					<div class="fr">
					<form id="searchForm" name="searchForm" action="${ctx}/front/customerCourse#intro" method="post">
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
						<ol class="clearfix s-c-title mt10 c-c-all-search">
							<li style="margin-bottom: 0"><tt class="vam mr5 c-666">标题：</tt><input type="text" id="courseName" name="queryCustomerCourse.title" value="${queryCustomerCourse.title}"/></li>
							<!-- <li><tt class="vam mr5 c-666">课程类型：</tt><input type="text" name="" value=""/></li> -->
							<li style="margin-bottom: 0"><input type="button" onclick="searchCourse()" value="搜索" class="c-c-a-s-btn"/></li>
						</ol>
					</form>
					</div>
				</section>
			</header>
			<section class="w1000">
			<c:if test="${courseList==null||courseList.size()==0}">
				<section class="comm-tips-1">
					<p>
						<em class="vam c-tips-1">&nbsp;</em>
						<font class="c-999 fsize12 vam">还没有自定义课程内容，填写上面表单，申请你想学的课程吧！</font>
					</p>
				</section>
			</c:if>
			<c:if  test="${courseList!=null&&courseList.size()>0}">
				<div class="c-c-all mt50">
					<ul class="clearfix c-c-l-infor">
					<c:forEach items="${courseList}" var="customerCourse">
						<li>
							<section class="fl">
								<div class="c-c-i-wrap">
									<em class="icon24 c-sj">&nbsp;</em>
									<section class="ml10 mr10">
										<h5 class="hLh20 of mt10">${customerCourse.title}</h5>
										<div class="s-c-desc pt10 pb10">
											<p class="c-999">${customerCourse.content}</p>
										</div>
										<div class="clearfix mt5">
											<span class="fr"><em class="icon16 c-p-num">&nbsp;</em><font class="c-master fsize12"><font id="span${customerCourse.id}">${customerCourse.joinNum}</font>人想学</font></span>
											<span class="fl"><font class="fsize12 c-999"><fmt:formatDate value="${customerCourse.createTime}"/></font></span>
										</div>
										<div class="clearfix mt10">
											<span class="fr"><a href="javascript:void(0)" onclick="updateCusNuber('${customerCourse.id}')" title="我要学 +1" style="padding: 0 6px;" class="green-btn">我要学 +1</a></span>
											<span class="fl" title="${customerCourse.userExpandDto.showname}">
                                                <font class="fsize12 c-999">
                                                <c:if test="${empty customerCourse.userExpandDto.showname}">
                                                    游客
                                                </c:if>
                                                <c:if test="${not empty customerCourse.userExpandDto.showname}">
                                                    ${fn:substring(customerCourse.userExpandDto.showname,0,16)}</font>
                                                </c:if>
                                                <tt class="c-orange">发起</tt></span>
										</div>
									</section>
								</div>
							</section>
							<aside class="fl">
								<div class="c-c-member">
									<c:if test="${not empty customerCourse.userExpandDto.avatar}">
									<img width="53" height="40" alt="${customerCourse.userExpandDto.showname}" src="<%=staticImageServer %>${customerCourse.userExpandDto.avatar}">
									</c:if>
									<c:if test="${empty customerCourse.userExpandDto.avatar}">
									<img width="53" height="40" alt="${customerCourse.userExpandDto.showname}" src="<%=imagesPath%>/static/common/images/user_default.jpg">
									</c:if>
									<p class="mt5 c-999">${fn:substring(customerCourse.userExpandDto.showname,0,24)}</p>
								</div>
							</aside>
						</li>
					</c:forEach>
					</ul>
				</div>
				<section>
					<div class="pagination pagination-large tac">
                  	<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
					</div>
				</section>
			</c:if>
			</section>
		</div>
	</section>
</body>
</html>
