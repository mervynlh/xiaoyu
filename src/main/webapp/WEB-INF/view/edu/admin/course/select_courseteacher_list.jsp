<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程统计列表</title>
<script type="text/javascript">
	function submitSearch() {
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/selectCourseList?teacherId=${queryCourseProfile.teacherId}" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>讲师课程管理</span> &gt; <span>讲师课程统计列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<%-- <caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"> <font>讲师课程名称：</font>
											</span> <input type="text" name="teacher.name" value="" id="questionId" />  <span class="ddTitle"><font>试卷名称：</font></span>
											<input type="text" name="paperErrorCheck.content" value="${paperErrorCheck.content}" id="paperName" /> <span class="ddTitle"><font>试题内容：</font></span>
											<input type="text" name="paperErrorCheck.qstName" value="${paperErrorCheck.qstName}" id="qstName" /> <input type="button" class="btn btn-danger" value="查询" name="" onclick="submitSearch()" /></li>
										</ul>

									</div>
									<div class="clearfix"></div>
								</div>
							</caption> --%>
							<thead>
								<tr>
									<th width="10%"><span>课程ID</span></th>
									<th width="10%"><span>课程名称</span></th>
									<th width="10%"><span>购买数量</span></th>
									<th width="10%"><span>查看次数</span></th>
									<th width="10%"><span>观看次数</span></th>
									<th width="10%"><span>观看人数</span></th>
									<th width="10%"><span>评论数量</span></th>
									<th width="10%"><span>问题数量</span></th>
									<th width="10%"><span>笔记数量</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${list.size()>0}">
									<c:forEach items="${list}" var="cf">
										<tr >
											<td>${cf.courseId }</td>
											<td>${cf.name }</td>
											<td>${cf.buycount }</td>
											<td>${cf.viewcount }</td>
											<td>${cf.playcount }</td>
											<td>${cf.wacthPersonCount }</td>
											<td>${cf.commentcount }</td>
											<td>${cf.questiongcount }</td>
											<td>${cf.notecount }</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${list.size()==0||list==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有讲师课程信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
