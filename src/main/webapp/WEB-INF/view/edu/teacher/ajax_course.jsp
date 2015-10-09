<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	<c:when test="${ not empty courseList }">
	<ul class="teach-bk-list">
		<c:forEach var="course" items="${courseList }">
			<li>
				<a href="javascript:void(0)" class="tu">
					<img src="<%=staticImageServer %>${course.classImgs }"	alt="">
				</a>
				<div class="wz">
					<p class="fsize16 f-fM c-333">${course.name }</p>
					<p class="mt10">
						<tt class="fsize14 c-999 f-fM">课时数:</tt>
						<tt class="fsize14 c-666 f-fM">${course.lessionnum }课时</tt>
					</p>
					<p class="mt5">
						<tt class="fsize14 c-999 f-fM">班级人数:</tt>
						<tt class="fsize14 c-666 f-fM">${course.peopleNum }人</tt>
					</p>
					<p class="mt5">
						<tt class="fsize14 c-999 f-fM">课程安排:</tt>
						<tt class="fsize14 c-666 f-fM">${course.coursePlan }</tt>
					</p>
					<p class="mt5">
						<tt class="fsize14 c-999 f-fM">上课方式:</tt>
						<tt class="fsize14 c-666 f-fM">
							<c:choose>
								<c:when test="${empty course.courseModelMap.LINEDOWNCOU}">在线授课</c:when>
								<c:otherwise>线下授课</c:otherwise>
							</c:choose>
						</tt>
					</p>
					<c:if test="${course.courseModel=='LINEDOWNCOU' }">
						<p class="mt5">
							<tt class="fsize14 c-999 f-fM">上课地点:</tt>
							<tt class="fsize14 c-666 f-fM">${course.address}</tt>
						</p>
					</c:if>
				</div>
				<div class="btn pt50">
					<p class="tac">
						<tt class="fsize14 c-666 f-fM vam">课程总价：</tt>
						<tt class="fsize20 c-org f-fM vam">￥${course.currentprice }</tt>
					</p>
					<div class="u-teaname-btn tac">
						<a class="c-btn c-btn-2" href="${ctx }/front/course/getCourseInfo/${course.id}">查看详情</a>
					</div>
				</div>
				<div class="clear"></div></li>
		</c:forEach>
	</ul>
	</c:when>
	<c:otherwise>
		<section class="u-T-body">
			<div class="u-T-body-in mt30">
				<div class="u-T-body-infor">
					<p class="c-master fsize24 f-fM tac">当前教师暂无开设班课！</p>
				</div>
			</div>
		</section>
	</c:otherwise>
</c:choose>