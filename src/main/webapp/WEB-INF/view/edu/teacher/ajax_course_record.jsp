<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	<c:when test="${not empty courseRecordList }">
		<table class="teach-skjl-tab" cellspacing="0" cellpadding="0" border="0" style="width: 100%;">
			<thead> 
				<tr> 
					<th style="width:24%;">
						课程号
					</th> 
					<th style="width:19%;">
						课程类型
					</th>
					<th style="width:19%;">
						学生
					</th>
					<th style="width:19%;">
						年级
					</th>
					<th style="width:19%;">
						课时数
					</th>
				</tr> 
			</thead>
			<tbody>
				<c:forEach var="courseRecord" items="${courseRecordList }">
					<tr>
						<td>
							<span class="vam">
								<tt class="fsize14 f-fM c-999 vam mr5">${courseRecord.id }</tt>
							</span>
						</td>
						<td>
							<span class="vam">
								<tt class="fsize14 f-fM c-999 vam mr5">
									<c:if test="${courseRecord.sellType==1 }">一对一</c:if>
									<c:if test="${courseRecord.sellType==2 }">班课</c:if>
								</tt>
							</span>
						</td>
						<td>
							<span class="vam">
								<tt class="fsize14 f-fM c-999 vam mr10">${courseRecord.studentName }同学</tt>
							</span>
						</td>
						<td>
							<span class="vam">
								<tt class="fsize14 f-fM c-999 vam mr10">${courseRecord.subjectMajorStr }</tt>
							</span>
						</td>
						<td>
							<span class="vam">
								<tt class="fsize14 f-fM c-999 vam mr10">${courseRecord.lessionNum }</tt>
							</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="${ctx }/front/ajax/getCourseRecord/${courseRecord.teacherId}" name="searchForm" id="searchForm" method="post">
			<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/>
		</form>
		<div class="Paging tac">
			<jsp:include page="/WEB-INF/view/common/ajax_page.jsp"/>
		</div>
	</c:when>
	<c:otherwise>
		<section class="u-T-body">
			<div class="u-T-body-in mt30">
				<div class="u-T-body-infor">
					<p class="c-master fsize24 f-fM tac">当前老师还没有教学记录！</p>
				</div>
			</div>
		</section>
	</c:otherwise>
</c:choose>