<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<style type="text/css">
	.n-l-menu dl dd {display: none;}
	.n-l-menu dl:hover dd{display: block;}
</style>
<script type="text/javascript">

var cityId = '${cityId}';
$(function(){
	var url=window.document.location.pathname;
	$("a[href$='"+url+"']").parent().addClass("current");
	$("#searchcityId").val(cityId);
});
</script>
	<!-- /顶部栏目 -->
	<header id="header">
		<div class="container">
			<div class="">
				<section class="fr headr-right">
					<section class="topSearchWrap">
						<div class="tsTabCont">
							<section class="tsTabContInp">
								<input type="text" class="tscInp" x-webkit-speech="" placeholder="请输入关键字..." id="teacherName" onkeyup="enterSubmit(event,'getSearch()')">
								<a class="tscBtn" onclick="getSearch()"></a>
								<form id="formSearch" method="post" action="${ctx}/front/teacher/query/list">
									<input id="searteacherName"  type="hidden" name="queryTeacher.name" value="${queryStr}"/>
									<input id="searchcityId"  type="hidden" name="queryTeacher.cityId" value=""/>
									<input type="hidden" name="querysubjectId" id="seartsubjectid">
									<input type="hidden" name="queryTeacher.subjectId" id="seartgradeid">
									<input type="hidden" name="queryTeacher.majorId" id="seartmajorid">
								</form>
								<form id="updateCity" method="post" action="/">
									<input id="searCityId"  type="hidden" name="cityId" value=""/>
								</form>
							</section>
						</div>
					</section>
					<!-- /search -->
					<section class="topEntranceWrap">
						<section class="topbar">
							<section class="fr">
								<aside class="login undis fr">
									<a href="/login" class="">登录</a>
									<span>|</span>
									<a href="/register">注册</a>
								</aside>
								<aside class="login-infor undis login fr">
									<span class="welcome fsize12 f-fM">欢迎来到小雨在线教育</span>
									<a href="${ctx}/uc/home" class="">
										<img class="vam" id="cusImg" width="24" height="24" src="${ctx}/static/edu/img/avatar-boy.gif">
										<tt id="nickname" class="ml5 fsize12 f-fM"></tt>
									</a>
									<span>|</span>
									<a href="${ctx}/uc/letter" class="pr">
										消息
										<tt id="msgCountId" class="tip-news pa undis" title="未读消息"></tt>
									</a>
									<span>|</span>
									<a href="javascript:exit()">退出</a>
								</aside>
							</section>
							<div class="clear"></div>
						</section>
						<div class="teCont clearfix">
							<a href="#">
			                    <b class="icon-34 icon-te-1"></b>
			                    <span>教师认证</span>
			                </a>
			                <a href="#">
			                    <b class="icon-34 icon-te-2"></b>
			                    <span>免费试听</span>
			                </a>
			                <a href="#">
			                    <b class="icon-34 icon-te-3"></b>
			                    <span>1对1定制</span>
			                </a>
			                <div class="clear"></div>
						</div>
					</section>
				</section>
				<section class="fl">
					<h1 class="logo fl">
						<a href="/" title="" class="clearfix">
							<img src="${ctx}/static/edu/img/logo.png" alt="">
						</a>
						<span>
							<img src="${ctx}/static/edu/img/logo-right.png" alt="">
						</span>
						<div class="clear"></div>
					</h1>
					<div class="fl mt75">
						<aside class="area-wrap pr">
							<a href="" title="" class="c-btn c-btn-1" style="z-index:9999;">
								<span class="f-fM vam fsize16" id="select_area">${(cityName == null || cityName == '') ? "西安市" : cityName}</span>
								<em class="icon12 down-ico ml5 vam">&nbsp;</em>
								<section class="arBg" style="z-index:9999;">&nbsp;</section>
							</a>
							<div class="addRessCont">
								<div class="addRessCList">
									<ol class="clearfix">
										<c:if test="${areaLiat != null && areaLiat.size() > 0}">
										<c:forEach items="${areaLiat }" var="area">
										<li><a href="javascript:void(0)" id="area_${area.id}" onclick="selectCity(${area.id})">${area.areaName }</a>|</li>
										</c:forEach>
										</c:if>
									</ol>
								</div>
							</div>

						</aside>
					</div>
				</section>
				<!-- /logo -->
				<div class="clear"></div>
			</div>	
		</div>
	</header>
	<!-- /header -->
	<nav id="nav">
		<section class="container">
			<section class="nav-wrap">
				<!-- 课程分类目录 -->
				<menu class="n-l-menu">
					<dl>
						<dt class="f-fM">
							<em class="icon30 n-menu-ico mr5">&nbsp;</em>
							<span class="fsize18 c-fff vam">全部科目</span>
						</dt>
						<dd>
							<ul class="n-l-m-subMenu">
								<c:forEach items="${navSubjectList}" var="sub" begin="0" end="3">
								<c:if test="${sub.parentId==0}">
								<li>
									<div>
										<small>&gt;</small>
										<a href="javascript:void(0)" onclick="selectBySubjectMajor(${sub.subjectId}, 0, 0)" title="" class="nlms-at">${sub.subjectName}</a>
									</div>
									<div class="subMenu-two">
										<c:forEach items="${sub.childSubjectList}" var="child" begin="0" end="3">
											<a href="javascript:void(0)" onclick="selectBySubjectMajor(${sub.subjectId}, ${child.subjectId }, 0)" >${child.subjectName}</a>
										</c:forEach>
									</div>
									<section class="nlm-subm">
										<div class="nlm-sElem">
											<section class="clearfix nlm-sLi">
												<c:forEach items="${sub.childSubjectList}" var="child">
													<div class="nlm-sLi-box">
														<div class="nlm-sLi-box-tit">
															<a href="javascript:void(0)" onclick="selectBySubjectMajor(${sub.subjectId}, ${child.subjectId }, 0)" >${child.subjectName}</a>
														</div>
														<div class="nlm-sLi-box-body">
															<c:forEach items="${child.majorList}" var="major">
																<a href="javascript:void(0)" onclick="selectBySubjectMajor(${sub.subjectId}, ${child.subjectId }, ${major.majorid })" >${major.majorName}</a>
															</c:forEach>
														</div>
														<div class="clear"></div>
													</div>
												</c:forEach>
											</section>
										</div>
									</section>
								</li>
								</c:if>
								</c:forEach>
							</ul>
							<!-- 此处一级目录分类限制只调出4条 -->
						</dd>
					</dl>
				</menu>
				<!-- 课程分类目录 -->
				<!-- 主导航 -->
				<ul class="clearfix g-nav pl30">
					<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
       					<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
					</c:forEach>
				</ul>
				<!-- 主导航 -->
				<aside class="n-r-tel">
					<span class="ml5 c-genyel fsize20 f-fM">Tel：${websitemap.web.phone}</span>
				</aside>
			</section>
		</section>
	</nav>
	<!-- /global nav -->