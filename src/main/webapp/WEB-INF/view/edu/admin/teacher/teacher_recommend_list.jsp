<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>教师列表</title>
<script type="text/javascript">
	function addSubmit() {
		var recommendId = $("#recommendId").val();
		if (recommendId < 0) {
			alert("请选择推荐类型");
			return;
		}
		var imgIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
		for (var i = 0; i < imgIds.length; i++) {
			if (imgIds[i].checked == true) {
				num++;
				ids += imgIds[i].value + ",";
			}
		}
		if (num == 0) {
			alert("请选择要推荐的教师");
			return;
		}
		$.ajax({
			url : "${ctx}/admin/website/addTeacherDetail",
			data : {
				"ids" : ids,
				"recommendId" : recommendId
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.message == 'true') {
					alert("推荐成功");
					window.opener.addTeacherReload();
					window.close();
				} else if (result.message == 'than') {
					var recommendName = $("#recommendId").find(
							"option:selected").text();
					alert(recommendName + "模块只能添加" + result.entity.teacherNum
							+ "位教师");
				} else {
					alert("操作失败");
					window.close();
				}
			}
		});

	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/recommendTeacherList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>选取教师</span> &gt; <span>教师列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span> <font>教师id：</font></span> 
														<span> 
															<input type="text" style="width:60px;" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryTeacher.id" value="${queryTeacher.id}" id="id" />
														</span>
														
		                                                <span class="ddTitle"><font>教师姓名：</font></span>
		                                               <span> 
															<input type="text" style="width:60px;" name="queryTeacher.name" value="${queryTeacher.name}" id="teachername" />
														</span>
													</div>

													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="goPage(1)" />
													</div>
													<div class="optionList">
														<span> <font>推荐类型：</font>
														</span> <span> <select name="recommendId" id="recommendId">
																<option value="-1">--请选择--</option>
																<c:forEach items="${websiteTeachers }" var="websiteTeacher">
																	<option value="${websiteTeacher.id}">${websiteTeacher.name}</option>
																</c:forEach>
														</select>
														</span>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</caption>

										<thead>

											<tr>
												<th><span><input type="checkbox" onclick="allCheck(this)" />全选</span></th>
												<th><span>ID</span></th>
												<th><span>教师姓名</span></th>
												<th><span>教师昵称</span></th>
												<th><span>教师学历</span></th>
												<th><span>入驻时间</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">

											<c:if test="${teacherList.size()>0}">
												<c:forEach items="${teacherList}" var="teacher">
													<tr>
														<td><input type="checkbox" name="ids" value="${teacher.id}" /></td>
														<td>${teacher.id}</td>
														<td>${teacher.userExpand.realname }</td>
														<td>${teacher.userExpand.nickname }</td>
														<td>
															<c:if test="${teacher.degree==0}">无</c:if>
															<c:if test="${teacher.degree==1}">高中以下</c:if>
															<c:if test="${teacher.degree==2}">高中或中专</c:if>
															<c:if test="${teacher.degree==3}">大专</c:if>
															<c:if test="${teacher.degree==4}">本科</c:if>
															<c:if test="${teacher.degree==5}">研究生</c:if>
															<c:if test="${teacher.degree==6}">博士及以上</c:if>
														</td>
														<td><fmt:formatDate type="both" value="${teacher.createTime }" pattern="yyyy-MM-dd" /></td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${teacherList.size()==0||teacherList==null}">
												<tr>
													<td align="center" colspan="16">
														<div class="tips">
															<span>还没有教师数据！</span>
														</div>
													</td>
												</tr>
											</c:if>
											<tr>
												<td align="center" colspan="6"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">确定</a> <a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a></td>
											</tr>
										</tbody>
									</table>
									<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
								</div>
							</div>
						</table>
					</div>
				</div>
		</form>
</body>
</html>
