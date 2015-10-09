<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript">

</script>
<c:forEach items="${teacherList}" var="tea">
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
			<span class="c-999 fsize12 f-fM ml10">教龄：${tea.seniority}年</span>
		</div>
		<div class="mt10 hLh30 txtOf pl60">
			<span>
				<tt class="c-999 f-fM fsize12 vam">课时费：</tt>
				<tt class="c-master f-fM fsize20 vam ml10 pr">
					<em class="icon12 icon-Money pa">&nbsp;</em>
					${tea.lowPrice}
				</tt>
				<tt class="c-999 f-fM fsize12 vam">/小时</tt>
			</span>
		</div>
		<div class="mt10 hLh30 txtOf pl60">
			<span>
				<tt class="c-999 f-fM fsize12 vam">科目：</tt>
				<tt class="c-999 f-fM fsize12 vam">
					${tea.majors}
				</tt>
			</span>
		</div>
		<div class="mt10 hLh30 txtOf pl60">
			<span>
				<tt class="c-999 f-fM fsize12 vam">评论数：</tt>
				<tt class="c-master f-fM fsize12 vam">${tea.assessNum}</tt>
			</span>
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
