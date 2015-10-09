<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分记录管理</title>

<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>积分记录管理</span>
		</h4>
	</div>
	<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/user/integralrecordlist/${userId}" method="post" id="searchForm">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			</form>
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
					<input type="button" class="btn btn-danger" value="返回"
											onclick="javascript:history.go(-1)" />
						<%-- <div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/user/integralist" method="post" id="searchForm">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
									<div class="optionList">
										<span><font>用户邮箱：</font></span> <span> <input
											type="text" name="userIntegral.email" id="name"/>
										</span>

									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="查詢"
											onclick="selectIntegral()" />
									</div>
									</form>
							</div>
						</div> --%>
					<%-- 	<div class="clearfix"></div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
								<span><a href="${ctx}/admin/websitemem/toAdd" title="添加"><em class="icon14 new">&nbsp;</em>添加</a></span>
								</p>
							</div>  --%>
					</caption>
					<thead>
						<tr>
							<th><span>ID</span></th>
							<th><span>用户名</span></th>
							<th><span>用户手机号</span></th>
							<th><span>积分类型</span></th>
							<th><span>分数</span></th>
							<th><span>时间</span></th>
							<th><span>当前总分数</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${userIntegralRecordList}" var="igRecord">
							<tr>
								<td>${igRecord.id}</td>
								<td>${igRecord.nickname}</td>
								<td>${igRecord.mobile}</td>
								<td>${igRecord.templateName}</td>
								<td>${igRecord.score}</td>
								<td><fmt:formatDate value="${igRecord.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${igRecord.currentScore }</td>
								<%-- <td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y" onclick="deladmin(${ig.id})" href="javascript:void(0)"
									title="查看详情">查看详情</a></td> --%>
							</tr>
						</c:forEach>
						<c:if test="${userIntegralRecordList.size()==0||userIntegralRecordList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有用户积分信息！</span>
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
		</div>
	
</body>
</html>