<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_account.js"></script>
</head>
<body>
	<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<div id="uTabCont" class=""> 
						<article id="newWelcomeSellWayListUlId" style="display:block;">
							<section class="mb50"> 
								<div class="right-sign-list">
									<ul>
									<c:if test="${teacherList != null && teacherList.size() > 0 }">
										<c:forEach items="${teacherList }" var="teacher">
										<li>
											<span class="disIb vam mr30">
												<a href="${ctx}/front/teacher/${teacher.id}" target="_blank" class="dis vam mb10">
													<c:if test="${empty teacher.userExpand.headPhoto || teacher.userExpand.headPhoto == '' }">
														<img src="/static/edu/images/page/tea-nan.jpg">
													</c:if>
													<c:if test="${teacher.userExpand.headPhoto != null && teacher.userExpand.headPhoto != '' }">
														<img src="<%=staticImageServer%>${teacher.userExpand.headPhoto}">
													</c:if>
												</a>
												<b class="star-level-1 star-1-${teacher.showStar == 0 ? 5 : teacher.showStar} vam mt5" title="星级：${teacher.showStar == 0 ? 5 : teacher.showStar}星">&nbsp;</b>
											</span>
											<span class="u-s-cloll-list-in disIb vam">
												<p class="fsize18 c-333 f-fM">${teacher.userExpand.showname }</p>
												<p class="fsize14 c-666 f-fM mt10" title="${teacher.majors }">
													<tt class="">主讲：${teacher.majors }</tt>
												</p>
												<p class="fsize14 c-666 f-fM mt10" title="${teacher.subjects }">
													<tt>年级：${teacher.subjects }</tt>
												</p>
												<p class="fsize14 c-666 f-fM mt10">
													<tt class="mr30 u-s-c-l-in-l">教龄：${teacher.seniority }年</tt>
													<tt>年龄：${teacher.userExpand.age }岁</tt>
												</p>
												<p class="fsize14 c-999 f-fM mt10">${teacher.teachingEnounce}
												</p>
												<p class="mt10">
													<span ${teacher.cardStatus !=2 ? "style='display: none'" : ""} title="身份认证"><em class="icon24 icon-sk-1 vam mr10">&nbsp;</em></span>
													<span ${teacher.educationStatus !=2 ? "style='display: none'" : ""} title="学历认证"><em class="icon24 icon-sk-2 vam mr10">&nbsp;</em></span>
													<span ${teacher.teachingStatus !=2 ? "style='display: none'" : ""} title="教师证"><em class="icon24 icon-sk-3 vam mr10">&nbsp;</em></span>
													<span ${teacher.specialtyStatus !=2 ? "style='display: none'" : ""} title="专业资质认证"><em class="icon24 icon-sk-4 vam mr10">&nbsp;</em></span>
													<span ${teacher.isProfessor != 2 ? "style='display: none'" : ""} title="韩教授认证"><em class="icon24 icon-sk-5 vam mr10">&nbsp;</em></span>
												</p>
											</span>
											<span class="u-teaname-btn ml50 disIb vam">
												<p class="mb20">
													<tt class="fsize14 c-666 f-fM">￥</tt>
													<tt class="fsize14 c-org f-fM">${teacher.lowPrice }</tt>
													<tt class="fsize14 c-666 f-fM">/每小时起</tt>
												</p>
												<a href="${ctx}/front/teacher/${teacher.id}" target="_blank">查看教师</a>
											</span>
											<span class="clear"></span>
										</li>
										</c:forEach>
									</c:if>
									</ul>
									<c:if test="${teacherList == null || teacherList.size() == 0 }">
										<div class="u-T-body-in mt30">
											<div class="u-T-body-infor">
												<p class="c-666 fsize24 f-fM tac">对不起，暂无教师，建议去<a href="${ctx}/front/teacher/query/list" class="fsize24 c-master"><u style="text-decoration:underline;">选择课程,添加教师！</u></a></p>
											</div>
										</div>
									</c:if>
								</div>
								<!-- 引入公共分页 -->
								<section class="mt50">
									<div class="Paging tac">
										<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
									</div>
								</section>
								<!-- 引入公共分页 -->
							</section> 
						</article>
					</div> 
				</section>   
			</article>
		</div>
</body>
</html>
