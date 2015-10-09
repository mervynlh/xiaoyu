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
	<article class="u-m-center" style="padding-top:0;">
		<section class="u-m-c-wrap">
			<div id="uTabCont" class="">
				<article id="newWelcomeSellWayListUlId" style="display:block;">
					<section class="mb50">
						<div class="right-sign-list">
							<c:if test="${studentList != null && studentList.size() > 0}">
							<ul>
								<c:forEach items="${studentList}" var="student" varStatus="index">
								<li>
									<span class="disIb vam mr30">
										<a href="javascript:void(0)" class="dis vam mb10">
											<c:if test="${empty student.headPhoto || student.headPhoto == '' }">
												<img src="${ctximg}/static/edu/img/avatar-boy.gif">
											</c:if>
											<c:if test="${student.headPhoto != null && student.headPhoto != '' }">
												<img src="<%=staticImageServer%>${student.headPhoto}">
											</c:if>
										</a>
									</span>
									<span class="u-s-cloll-list-in disIb vam">
										<p class="fsize18 c-333 f-fM">
											<c:if test="${student.showname == null || student.showname == ''}">小雨_学生_${index.index}</c:if>
											<c:if test="${student.showname != null && student.showname != ''}">${student.showname}</c:if>
										</p>
										<p class="mt10">
											<em class="icon-u-dh vam icon18">&nbsp;</em>
											<tt class="fszie12 c-master f-fM vam mr30">${student.mobile}</tt>
										</p>
										<p class="fsize14 c-666 f-fM mt10">
											<tt class="mr30 u-s-c-l-in-l">性别：${student.gender == 0 ? "男" : "女"}</tt>
											<tt>年龄：${student.age == null || student.age == 0 ? 15 : student.age}&nbsp;岁</tt>
										</p>
										<p class="fsize14 c-999 f-fM mt10">${fn:substring(student.userInfo, 0, 30)}<c:if test="${student.userInfo.length() > 30}">......</c:if>
										</p>
									</span>
									<span class="u-teaname-btn ml50 disIb vam">
										<a href="${ctx}/uc/teacher/student/study/history/list/${student.cusId}">学习记录</a>
										<c:if test="${not empty assessList }">
											<a href="${ctx}/front/toAddAssessTeaToStu/${student.cusId }/${assessList.get(index.index) }">我要评论</a>
										</c:if>
									</span>
									<span class="clear"></span>
								</li>
								</c:forEach>
							</ul>
							</c:if>
							<c:if test="${studentList == null || studentList.size() == 0 }">
								<div class="u-T-body-in mt30">
									<div class="u-T-body-infor">
										<p class="c-master fsize24 f-fM tac">对不起，暂无学生记录信息</p>
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
