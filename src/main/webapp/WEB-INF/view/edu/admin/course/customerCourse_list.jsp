<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>自定义课程列表</title>
<script type="text/javascript">
	function submitSearch() {
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	function clean() {
		$("#title,#emailId,#teacherName").val("");
		$("#artype").val(0);
	}

	function updateStatus(id, type) {
		var str = "";
		if (id != null) {
			if (type == 1) {
				str = "<span>隐藏</span>";
			}
			if (type == 0) {
				str = "<span>显示</span>";
			}
			$
					.ajax({
						url : "${ctx}/admin/customerCourse/updateCustomerCourse",
						data : {
							"customerCourse.id" : id,
							"customerCourse.status" : type
						},
						dataType : "json",
						type : "post",
						asyncs : false,
						success : function(result) {
							if (result.success == true) {
								var str = "";
								if (type == 1) {
									$("#statusId" + id).html("<span>隐藏</span>");
									str = "<a class='ml10 btn smallbtn btn-y' title='显示' href='javascript:updateStatus("
											+ id + ",0)'>显示</a>";
								}
								if (type == 0) {
									$("#statusId" + id).html("<span>显示</span>");
									str = "<a class='ml10 btn smallbtn btn-y' title='隐藏' href='javascript:updateStatus("
											+ id + ",1)'>隐藏</a>";
								}
								$("#funId" + id).html(str);
							}

						},
						error : function(error) {
							alert("error");
						}
					});
		}

	}
</script>
</head>
<body>
		<form action="${ctx}/admin/customerCourse/CourseList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>自定义管理</span> &gt; <span>自定义课程列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="fl">
										<ul class="ddBar">
											<li><span class="ddTitle"><font>标题：</font></span> 
											<input type="text" name="queryCustomerCourse.title" value="${queryCustomerCourse.title}" id="title" /> 
											<span class="ddTitle"><font>状态：</font></span> 
											<select name="queryCustomerCourse.status" id="artype">
													<option value="0">请选择</option>
													<option value="1" <c:if test="${queryCustomerCourse.status==1}">selected="selected"</c:if>>正常</option>
													<option value="2" <c:if test="${queryCustomerCourse.status==2}">selected="selected"</c:if>>隐藏</option>
											</select>
											</li>
											<li><span class="ddTitle"><font>邮箱：</font></span> <input type="text" name="queryCustomerCourse.email" id="emailId" value="${queryCustomerCourse.email }" /> <span class="ddTitle"><font>讲师名：</font></span> <input type="text" name="queryCustomerCourse.teacherName" id="teacherName" value="${queryCustomerCourse.teacherName}" /> <input type="button" class="btn btn-danger" value="查询" name="" onclick="submitSearch()" /> <input type="button" class="btn btn-danger" value="清空" name="" onclick="clean()" /></li>

										</ul>

									</div>
									<div class="clearfix"></div>
								</div>
								<div class="mt10 clearfix">
									<p class="fl czBtn"></p>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="8%"><span>ID</span></th>
									<th width="8%"><span>标题</span></th>
									<th width="15%"><span>课程内容</span></th>
									<th  width="10%"><span>讲师名称</span></th>
									<th width="8%"><span>手机号</span></th>
									<th width="8%"><span>邮箱</span></th>
									<th><span>学员ID</span></th>
									<th><span>状态</span></th>
									<th><span>添加时间</span></th>
									<th><span>加入人数</span></th>
									<th><span>备注</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${courseList.size()>0}">
									<c:forEach items="${courseList}" var="course">
										<tr id="rem${course.id }">
											<td>${course.id }</td>
											<td>${course.title }</td>
											<td>${fn:substring(course.content,0,30)}</td>
											<td>${course.teacherName }</td>
											<td>${course.mobile }</td>
											<td>${course.email }</td>
											<td>${course.createuserId }</td>
											<td id="statusId${course.id}"><c:if test="${course.status==0}">
								正常
								</c:if> <c:if test="${course.status==1}">隐藏</c:if></td>
											<td><fmt:formatDate type="both" value="${course.createTime }" pattern="yyyy-MM-dd"/></td>
											<td>${course.joinNum}</td>
											<td>${course.remark}</td>
											<td class="c_666 czBtn" align="center"><a class="ml10 btn smallbtn btn-y" title="删除" href="${ctx}/admin/customerCourse/deleteCourse?customerCourseId=${course.id}">删除</a> <c:if test="${course.status==0}">
													<span id="funId${course.id}"> <a class="ml10 btn smallbtn btn-y" title="隐藏" href="javascript:updateStatus(${course.id},1)">隐藏</a>
													</span>
												</c:if> <c:if test="${course.status==1}">
													<span id="funId${course.id}"> <a class="ml10 btn smallbtn btn-y" title="显示" href="javascript:updateStatus(${course.id},0)">显示</a>
													</span>
												</c:if></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${courseList.size()==0||courseList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有自定义课程信息！</span>
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
