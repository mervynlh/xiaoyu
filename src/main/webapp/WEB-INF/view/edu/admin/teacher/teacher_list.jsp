<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>老师列表</title>
<script type="text/javascript">
$(function(){//初始化数据
	if('${queryTeacher.status}'==null||'${queryTeacher.status}'==''){
		$("#isStar").val(0);	
	}else{
		$("#isStar").val('${queryTeacher.status}');	
	}
	if('${queryTeacher.isProfessor}'==0){
		$("#isProfessor").val(0);
	}else{
		$("#isProfessor").val('${queryTeacher.isProfessor}');
	}
})
function updateStatus(id, status, isProfessor){
	if(status == 2){
		var judge=confirm("确定冻结（删除）该教师吗？");
		if(judge == false){
			return;
		}
	}
	$.ajax({
		url:'/admin/teacher/update/status',
		data:{'teacher.id':id,'teacher.status':status,'teacher.isProfessor':isProfessor},
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success){
				alert("教师状态更改成功！");
				window.location.reload();
			}else if (result.message != ''){
				alert(result.message + "，暂时不能审核");
				window.location.reload();
			}else{
				alert("系统繁忙！");
			}
		}
	});
}
function submitSearch(){//搜索
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	function tcClean(){//清空
		$("#tcName").val("");
		$("#isStar").val(0);
		$("#isProfessor").val(0);
	}
	
	function teacherExcel(){
		$("#searchForm").prop("action","${ctx}/admin/teacher/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/teacher/list");
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/teacher/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>讲师管理</span> &gt; <span>讲师列表</span> </h4>
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="fl w50pre">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>教师姓名：</font></span>
											<input type="text" name="queryTeacher.name" value="${queryTeacher.name}" id="tcName" />
										</li>
										<li>
											<span class="ddTitle"><font>讲师状态：</font></span>
											<select name="queryTeacher.status" id="isStar">
												<option value="0" >--请选择--</option>
												<option value="1" >已审核</option>
												<option value="2" >未审核</option>
											</select>
										</li>
									</ul>
								</div>
								<div class="fl w50pre">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>教师手机号：</font></span>
											<input type="text" name="queryTeacher.mobile" value="${queryTeacher.mobile}" id="tcMobile" />
										</li>
										<li>
											<span class="ddTitle"><font>韩教授认证状态：</font></span>
											<select name="queryTeacher.isProfessor" id="isProfessor">
												<option value="0" >--请选择--</option>
												<option value="1" >未认证</option>
												<option value="2" >已认证</option>
												<option value="3" >申请认证</option>
											</select>
										</li>
									</ul>
								</div>
								<div style="margin-left:45%">
									<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
									<input type="button"  class="btn btn-danger" value="清空" name="" onclick="tcClean()"/>
									<input type="button"  class="btn btn-danger" value="导出Excel" name="" onclick="teacherExcel()"/>
								</div>
								<div class="clearfix"></div>
							</div>
							<%-- <div class="mt10 clearfix">
								<p class="fl czBtn">
								 <span><a href="${ctx}/admin/teacher/toAdd" title="新建讲师"><em class="icon14 new">&nbsp;</em>新建讲师</a></span>
								</p>
							</div>  --%>
						</caption>
						<p style="margin-left: 50px"><span style="font-size: 14px;color:red">*&nbsp;&nbsp;审核教师前请确认教师资料是否填写完全，至少存在一个课程数量和常用上课地址，否则不予审核</span></p>
						<thead>
							<tr>
								<!-- <th width="5%"><span>&nbsp;ID</span></th> -->
								<th width="8%"><span>ID</span></th>
								<th width="8%"><span>名称</span></th>
								<th><span>手机</span></th>
								<%--<th><span>邮箱</span></th>--%>
								<th width="8%"><span>学历</span></th>
								<th><span>专业</span></th>
								<th><span>教龄</span></th>
								<th><span>常用上课地址</span></th>
								<th><span>韩教授认证</span></th>
								<th><span>状态</span></th>
								<th><span>入驻时间</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${teacherList.size()>0}">
						<c:forEach  items="${teacherList}" var="tc" >
							<tr id="rem${tc.id }">
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${tc.id }</td>
								<td>${tc.userExpand.showname }</td>
								<td>${tc.userExpand.mobile }</td>
								<%--<td>${tc.userExpand.email }</td>--%>
								<td>
									<c:if test="${tc.degree==0}">无</c:if>
									<c:if test="${tc.degree==1}">高中以下</c:if>
									<c:if test="${tc.degree==2}">高中或中专</c:if>
									<c:if test="${tc.degree==3}">大专</c:if>
									<c:if test="${tc.degree==4}">本科</c:if>
									<c:if test="${tc.degree==5}">研究生</c:if>
									<c:if test="${tc.degree==6}">博士及以上</c:if>
								</td>
								<td>${tc.profession }</td>
								<td>${tc.seniority }&nbsp;&nbsp;年</td>
								<td>${tc.cityName} ${tc.districtName} ${tc.address}</td>
								<td>
									<c:if test="${tc.isProfessor == 1 || tc.isProfessor == 0}">未认证</c:if>
									<c:if test="${tc.isProfessor == 2}">已认证</c:if>
									<c:if test="${tc.isProfessor == 3}">申请认证</c:if>
								</td>
								<td>${tc.status == 1 ? "已审核" : "未审核"}</td>
								<td><fmt:formatDate type="both" value="${tc.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td  class="c_666 czBtn" align="center">
								
								<c:if test="${tc.status == 2 }">
									<a class="ml10 btn smallbtn btn-y" title="审核" href="javascript:void(0)" onclick="updateStatus(${tc.id}, 1, ${tc.isProfessor})">审核</a>
								</c:if>
								<c:if test="${tc.status == 1 }">
									<a class="ml10 btn smallbtn btn-y" title="取消审核" href="javascript:void(0)" onclick="updateStatus(${tc.id}, 2, ${tc.isProfessor})">冻结</a>
								</c:if>
								<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/teacher/toUpdate/${tc.id}">修改/查看</a>
								<a class="ml10 btn smallbtn btn-y" title="本周课表" href="${ctx}/admin/teacher/classhour/query/${tc.id}">本周课表</a>
								<a class="ml10 btn smallbtn btn-y" title="推广返现" href="${ctx}/admin/extend/cashback/list/${tc.id}">推广返现</a>
								<c:if test="${tc.zoomMeetingUserId != null && tc.zoomMeetingUserId != '' }">
									<a class="ml10 btn smallbtn btn-y" title="直播列表" href="${ctx}/admin/teacher/zoom/meetings/querylist/${tc.zoomMeetingUserId}">直播列表</a>
								</c:if>
								</td>
								<%--<td class="c_666 czBtn" align="center">
									<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
									<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
									<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${teacherList.size()==0||teacherList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有讲师信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
