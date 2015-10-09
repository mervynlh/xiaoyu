<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程管理</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_home.js"></script>
<script>
	$(function() {
		//stepFun();//新手引导四步骤方法
	});
	selectRondomTeacher();
</script>
</head>

<body>
			<article class="u-m-center">
				<!-- /u-m-c-head -->
				<section class="u-m-c-wrap">
					<section class="u-m-c-head">
						<span class="fr"><em class="right-go icon-2-16">&nbsp;</em><a href="${ctx}/front/showcoulist" title="" class="vam c-666 ml5">去选课</a></span>
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="javascript:void(0)" title="我购买的课程" class="vam">我购买的课程</a><tt class="fsize12 vam" id="collectcount">(${empty buycourses?0:buycourses.size() })</tt></li>
						</ul>
					</section>
					<section class="line1">
						<c:if test="${empty buycourses }">
							<section class="comm-tips-1">
							<p>
								<em class="vam c-tips-1"> </em>
								<font class="c-999 fsize12 vam">暂时没有购买任何课程. . .</font>
							</p>
							</section>
							<section class="u-m-c-head">
								<ul class="fl u-m-c-h-txt">
									<li class="current"><a href="" title="" class="vam">推荐课程</a></li>
								</ul>
								<div class="clear"></div>
							</section>
							<section class="u-tj-cour">
								<ol class="clearfix">
								 	<c:forEach items="${mapCourseList.index_course_6}" var="tjCourse">
			                            <li>
			                                <div class="u-tj-cour-img">
			                                    <a href="${ctx}/front/couinfo/${tjCourse.id}" title="${tjCourse.name }">
			                                        <c:choose>
			                                            <c:when test="${not empty tjCourse.logo}">
			                                                <img src="<%=staticImageServer %>${tjCourse.logo}" width="150" height="113" class="dis" />
			                                            </c:when>
			                                            <c:otherwise>
			                                                <img src="${ctximg }/static/edu/images/default/default_goods.jpg" width="150" height="113" class="dis" />
			                                            </c:otherwise>
			                                        </c:choose>
			                                    </a>
			                                    <div class="pa u-tjc-name">
			                                        <a class="fsize14 c-fff" title="${tjCourse.name }" href="${ctx}/front/couinfo/${tjCourse.id}">${tjCourse.name }</a>
			                                    </div>
			                                </div>
			                                <div class="hLh20 of mt5">
			                                    <span class="c-999 fr"><tt class="vam">浏览量：</tt><b title="浏览量:${tjCourse.viewcount}" class="vam">${tjCourse.viewcount}</b></span>
			                                    <span class="fl"><a class="c-brow" title=""  href="${ctx}/front/couinfo/${tjCourse.id}">查看课程</a></span>
			                                </div>
			                            </li>
			                        </c:forEach>
								</ol>
							</section>
						</c:if>
						<c:if test="${not empty buycourses }">
						<div class="pl10 pr10">
							<ul class="u-buying-list u-collect-list">
							 <c:forEach items="${buycourses}" var="buyCourseList" varStatus="indexCourse">
								<li>
									<section class="fl u-c-img pr">
										<span class="play-1 pa"  onclick="watchCourse(${buyCourseList.id})">&nbsp;</span>
										<img width="154" height="116" alt="" src="<%=staticImageServer %>${buyCourseList.logo}">
									</section>
									<h4 class="hLh20 of unFw"><a class="c-666 fsize16 f-fM" title="${buyCourseList.name}" href="${ctx}/front/playkpoint/${buyCourseList.id}">${buyCourseList.name}</a></h4>
									<div class="u-c-list-desc mt10">
										<p class="c-999">${buyCourseList.title}</p>
									</div>
									<div class="hLh20 of mt5">
										<span>
											<tt class="c-4e mr10"> 购买有效期至：<c:if test="${not empty buyCourseList.authTime }"><b class="unFw"><fmt:formatDate value="${buyCourseList.authTime }" pattern="yyyy-MM-dd"/></b></c:if></tt>
											(<tt  class="c-red">剩</tt> <b>${buyCourseList.remainDays<=0?0:buyCourseList.remainDays}</b> <tt>天</tt>)<%-- (${buyCourseList.studyhistoryNum}/${buyCourseList.kpointNum }) --%>
										</span>
									</div>
									<div></div>
									<div class="hLh20 of mt5 pr">
										<span class="vam c-999">
										<c:choose>
											<c:when test="${buyCourseList.remainDays>0}">
												<a class="c-blue mr10" target="_blank" title="" href="${ctx}/front/playkpoint/${buyCourseList.id}"  class="commBtn002 ml20 disIb grayCol">直接观看</a>
											</c:when>
											<c:otherwise>
												<a class="c-blue mr10" title="" href="javascript:void(0);" onclick="expire()" class="commBtn002 ml20 disIb grayCol">已过期</a>
											</c:otherwise>
										</c:choose>
										|</span>
										<span class="vam"><a class="c-blue ml10" title="" target="_blank" href="${ctx}/front/couinfo/${buyCourseList.id}">查看课程</a></span>
										 <span class="syD uSyd c-999">已学<tt class="c-red">${buyCourseList.studyhistoryNum}</tt>/共<tt class="c-666">${buyCourseList.kpointNum}</tt>个视频</span>
									</div>
									
								</li>
								</c:forEach >
							</ul>
						</div>
						</c:if>
					</section>
				</section>
				<!-- /u-m-c-head -->
			</article>
			<aside class="u-m-right">
				<!--推荐 课程 -->
				<div class="couses-tj">
					<section class="pl15 pr15">
						<h5 class="hLh30 unFw of line2"><span class="c-666 fsize16 f-fM u-c-title">可能您想学的</span></h5>
						<div class="line1">
							<ul class="u-c-list-1 mt20">
								<c:forEach items="${mapCourseList.index_course_7}" var="courseList" varStatus="index1">
								<li>
									<a  href="${ctx}/front/couinfo/${courseList.id}"  target="_blank" class="dis">
										<c:if test="${ not empty courseList.logo  }">
											<img src="<%=staticImageServer %>${courseList.logo}" width="220" alt="" class="dis" />
										</c:if>
										<c:if test="${  empty courseList.logo  }">
											<img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="220" alt="" class="dis" />
										</c:if>
									</a>
										<h6 class="unFw hLh20 of"><a href="${ctx}/front/couinfo/${courseList.id}" target="_blank" title="${courseList.name}" class="c-4e">${courseList.name}</a></h6>
										<div class="mt5 u-c-desc-1">
										<p class="c-999">${courseList.title }</p>
										</div>
								</li>
							</c:forEach>
							</ul>
						</div>
					</section>
				</div>
			
				<!-- 推荐讲师  -->
				<div class="mt30 mb30">
					<section class="pl15 pr15">
						<h5 class="hLh30 unFw of line2"><a href="javascript:void(0)" onclick="selectRondomTeacher();" class="fr fresh icon-2-18 mt5" title="换一换"></a><span class="c-666 fsize16 f-fM u-c-title">名师推荐</span></h5>
						<div class="line1 pt15">
							<ol class="clearfix u-teacher-list" id="randomteacher">
							</ol>
						</div>
					</section>
				</div>
			</aside>
	<!-- /u-main end -->
</body>
</html>
