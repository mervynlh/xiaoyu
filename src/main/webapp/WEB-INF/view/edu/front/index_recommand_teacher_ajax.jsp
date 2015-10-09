<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:forEach items="${recommandList}" var="tea" begin="0" end="3">
<li>
	<section class="i-teach-wrap">
		<div class="i-teach-pic">
			<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
				<c:choose>
					<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer %>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
					<c:otherwise>
						<c:if test="${tea.sex == 0}">
							<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
						</c:if>
						<c:if test="${tea.sex == 1}">
							<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
						</c:if>
					</c:otherwise>
				</c:choose>
			</a>
		</div>
		<div class="mt10 hLh30 txtOf tac">
			<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
		</div>
		<div class="hLh30 txtOf tac">
			<span class="fsize14 c-999">${tea.profession}</span>
		</div>
		<div class="mt15 i-q-txt">
			<p class="c-999 f-fA hLh30 txtOf">辅导年级：
				${tea.subjects}
			</p>
			<p class="c-999 f-fA hLh30 txtOf">辅导科目：
				${tea.majors}
			</p>
		</div>
		<div class="i-teach-hover">
			<p class="c-666 f-fA fsize14">
				${tea.career}
			</p>
			<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
		</div>
	</section>
</li>
</c:forEach>