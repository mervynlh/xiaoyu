<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户积分管理</title>
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
			<em class="icon14 i_01"></em> <span>用户积分管理</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
					<caption>
						<div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/user/integralist" method="post" id="searchForm">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
									<div class="optionList">
										<span><font>用户姓名：</font></span> <span> <input
											type="text" name="userIntegral.realname" id="name" value="${userIntegral.realname}"/>
										</span>
										<span><font>手机号码：</font></span> <span> <input
											type="text" name="userIntegral.mobile" id="name" value="${userIntegral.mobile}"/>
										</span>
									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="查询"
											onclick="selectIntegral()" />
									</div>
									</form>
							</div>
						</div>
					</caption>
					<thead>
						<tr>
							<th><span>ID</span></th>
							<th><span>用户姓名</span></th>
							<th><span>手机号码</span></th>
							<th><span>当前积分</span></th>
							<th><span>经验值</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${userIntegralList}" var="ig">
							<tr>
								<td>${ig.id}</td>
								<td>${ig.realname}</td>
								<td>${ig.mobile}</td>
								<td>${ig.currentScore}</td>
								<td>${ig.totalScore}</td>
								<td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y"  href="${ctx }/admin/user/integralrecordlist/${ig.userId}"
									title="查看详情">查看详情</a></td>
							</tr>
						</c:forEach>
						<c:if test="${userIntegralList.size()==0||userIntegralList==null}">
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