<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收藏教师列表</title>
<script type="text/javascript">
	function deleteTeacher(id, teacherId) {
		dialog("是否确认删除收藏教师", 12, "", "/uc/deleteTeacher?id=" + id + "&teacherId="+ teacherId);
	}
</script>
</head>
<body>
	<form action="${ctx }/uc/collectTeacherList" method="post" id="searchForm">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage"value="${page.currentPage}" />
	</form>
	<div class="u-m-right">
		<article class="u-m-center">
			<section class="u-m-c-wrap">
				<div id="uTabCont" class="">
					<article id="newWelcomeSellWayListUlId" style="display: block;">
						<section class="mb50">
							<div class="right-sign-list">
								<c:if test="${not empty teacherList }">
									<ul>
										<c:forEach var="teacher" items="${teacherList }">
											<li>
												<span class="disIb vam mr30">
													<a href="${ctx }/front/teacher/${teacher.id}"class="dis vam mb10">
														<img src="<%=staticImageServer%>${teacher.picPath}" alt="">
													</a>
													<b class="star-level-1 star-1-4 vam mt5" title="星级：4星">&nbsp;</b>
												</span>
												<span class="u-s-cloll-list-in disIb vam">
													<p class="fsize18 c-333 f-fM">${teacher.name }</p>
													<p class="fsize14 c-666 f-fM mt10" title="${teacher.major }">
														<tt class="">主讲：${teacher.major }</tt>
													</p>
													<p class="fsize14 c-666 f-fM mt10" title="${teacher.subject }">
														<tt>年级：${teacher.subject }</tt>
													</p>
													<p class="fsize14 c-666 f-fM mt10">
														<tt class="mr30 u-s-c-l-in-l">教龄：${teacher.seniority }年</tt>
														<tt>年龄：${teacher.age }岁</tt>
													</p>
													<p class="fsize14 c-999 f-fM mt10">${teacher.peculiarity }</p>
													<p class="mt10">
														<span ${teacher.cardStatus != 2 ? "style='display : none'" : ""}>
															<em class="icon24 icon-sk-3 vam mr10" title="身份证认证"></em>
														</span>
														<span ${teacher.educationStatus != 2 ? "style='display : none'" : ""}>
															<em class="icon24 icon-sk-2 vam mr10" title="教师学历认证"></em>
														</span>
														<span ${teacher.teachingStatus != 2 ? "style='display : none'" : ""}>
															<em class="icon24 icon-sk-1 vam mr10" title="教学证认证"></em>
														</span>
														<span ${teacher.specialtyStatus != 2 ? "style='display : none'" : ""}>
															<em class="icon24 icon-sk-4 vam mr10" title="专业资质认证"></em>
														</span>
														<span ${teacher.isProfessor != 2 ? "style='display : none'" : ""}>
															<em class="icon24 icon-sk-5 vam mr10" title="韩教授在线认证"></em>
														</span>
													</p>
												</span>
												<span class="u-teaname-btn ml50 disIb vam">
													<p class="mb20">
														<tt class="fsize14 c-666 f-fM">￥</tt>
														<tt class="fsize14 c-org f-fM">${teacher.lowPrice }</tt>
														<tt class="fsize14 c-666 f-fM">/每小时起</tt>
													</p>
													<a href="${ctx }/front/teacher/${teacher.id}">查看详情</a>
													<a href="javascript:deleteTeacher('${teacher.favoritesId }','${teacher.id}')">删除收藏</a>
												</span>
												<span class="clear"></span>
											</li>
										</c:forEach>
									</ul>
								</c:if>
								<c:if test="${empty teacherList }">
									<p class="c-666 fsize24 f-fM tac">对不起，暂无收藏教师，建议去<a href="${ctx}/front/teacher/query/list" class="fsize24 c-master">收藏教师！</a></p>
								</c:if>
							</div>
							<c:if test="${not empty teacherList }">
								<!-- 引入公共分页开始 -->
								<section class="mt50">
									<div class="Paging tac">
										<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
									</div>
								</section>
								<!-- 引入公共分页结束 -->
							</c:if>
						</section>
					</article>
				</div>
			</section>
		</article>
	</div>
</body>
</html>