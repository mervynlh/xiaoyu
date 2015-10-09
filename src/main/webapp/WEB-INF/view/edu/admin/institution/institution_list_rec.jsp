
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>机构列表</title>
<script type="text/javascript">
function allCheck(th) {
	$("input[name='ids']:checkbox").prop('checked', th.checked);
}
function addSubmit() {
	var instIds = document.getElementsByName("ids");
	var num = 0;
	var ids = '';
	for (var i = 0; i < instIds.length; i++) {
		if (instIds[i].checked == true) {
			num++;
			ids += instIds[i].value + ",";
		}
	}
	if (num == 0) {
		alert("请选择要推荐的机构");
		return;
	}
	$.ajax({
		url : "${ctx}/admin/website/addWebsiteInstitution",
		data : {
			"ids" : ids
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.success) {
				alert("操作成功");
			} else {
				alert("操作失败");
			}
			window.close();
			window.opener.reload();
		}
	});
}
</script>
</head>
<body  >
<form action="${ctx}/admin/institution/getInsByName" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>机构管理</span> &gt; <span>机构列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
						
			<thead>
				<tr>
					<td  align="center"  colspan="8"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">确定</a> 
						<a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a></td>
				</tr>
				<tr>
					<th width="7%"><span><input type="checkbox" onclick="allCheck(this)"/>全选</span></th>
					<th width="8%"><span>机构名称</span></th>
					<th width="8%"><span>申请人</span></th>
					<th width="8%"><span>手机号</span></th>
					<th width="20%"><span>简介</span></th>
					<th width="15%"><span>创建时间</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${not empty institutionList}">
			<c:forEach  items="${institutionList}" var="institution" >
				<tr id="rem${institution.id }">
					<td><input type="checkbox" name="ids" value="${institution.id }"/>&nbsp;${institution.id }</td>
					<td>${institution.name }</td>
					<td>${institution.applicant }</td>
					<td>${institution.mobile }</td>
					<td>${institution.description}</td>
					<td><fmt:formatDate type="both" value="${institution.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty institutionList }">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>未查询到相关信息！</span>
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
