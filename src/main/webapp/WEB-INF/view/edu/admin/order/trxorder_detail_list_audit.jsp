<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>退课审核列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	function orderDetailExcel(){
		$("#searchForm").prop("action","${cxt}/admin/orderDetails/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/order/orderDetailList");
	}
	function clean() {
		$(	"#requestId,#applicant,#type,#userType").val('');
	}
	//日历空间
	$(function() {
		$(
				"#startAuthTime,#startPayTime,#startCreateTime,#endCreateTime,#endPayTime,#endLoseTime,#endAuthTime")
				.datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					changeYear:true,
					dateFormat:"yy-mm-dd",
					timeFormat : 'HH:mm:ss'
				})
		$("#type").val("${trxdetailOptRecordDTO.type}");
		$("#userType").val("${trxdetailOptRecordDTO.userType}");
	})

</script>
</head>
<body>
		<!-- 内容 开始  -->
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>课程管理</span> &gt; <span>退课列表</span>
				</h4>
			</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<form action="${ctx}/admin/order/orderDetailListAudit" name="searchForm" id="searchForm" method="post">
						<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"><font>用户：</font></span>
												<select id="userType" name="trxdetailOptRecordDTO.userType" class="ml10">
													<option value="">---请选择---</option>
													<option value="0">学生</option>
													<option value="1">教师</option>
												</select>
											</li>
                                            <li>
                                            	<span class="ddTitle"><font>姓名：</font></span>
                                            	<input type="text" id="applicant" name="trxdetailOptRecordDTO.applicant" value="${trxdetailOptRecordDTO.applicant}" class="ml10" />
                                            </li>
										</ul>
									</div>
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"><font>审核状态：</font></span>
												<select id="type" name="trxdetailOptRecordDTO.type" class="ml10">
													<option value="">---请选择---</option>
													<option value="COURSEAUDIT">退课审核中</option>
													<option value="COURSESUCCESS">退课通过</option>
													<option value="COURSECANCEL">退课未通过</option>
												</select>
											</li>
											<li>
												<span class="ddTitle"><font>订单编号：</font></span>
												<input id="requestId" type="text" name="trxdetailOptRecordDTO.requestId" value="${trxdetailOptRecordDTO.requestId}" class="ml10" />
											</li>
										</ul>
									</div>
									<div class="w50pre fl" style="text-align: center;">
										<ul class="ddBar">
											<li>
												<input type="button" class="btn btn-danger" value="查询" name="" onclick="goPage(1)" /> 
											 	<input type="button" onclick="clean()" class="btn btn-danger" value="清空" />
												<input type="button" onclick="orderDetailExcel()" class="btn btn-danger" value="导出Excel" />
											</li>
										</ul>
									</div>
									<div class="clear"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="10%"><span>ID</span></th>
									<th><span>用户类型</span></th>
									<th><span>姓名</span></th>
									<th><span>退课时间</span></th>
									<th><span>原始价</span></th>
									<th><span>销售价</span></th>
									<th><span>课程名</span></th>
									<th><span>订单编号</span></th>
									<th><span>订单状态</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${ not empty trxdetailOptRecordList }">
									<c:forEach items="${trxdetailOptRecordList}" var="trxdetailOptRecord">
										<tr id="rem${trxdetailOptRecord.id }">
											<td>${trxdetailOptRecord.id}</td>
											<td>
												<c:if test="${trxdetailOptRecord.userType==0 }">学生</c:if>
												<c:if test="${trxdetailOptRecord.userType==1 }">教师</c:if>
											</td>
											<td>${trxdetailOptRecord.applicant}</td>
											<td><fmt:formatDate value="${trxdetailOptRecord.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${trxdetailOptRecord.sourcePrice}</td>
											<td>${trxdetailOptRecord.currentPrice}</td>
											<td>${trxdetailOptRecord.courseName}</td>
											<td>${trxdetailOptRecord.requestId }</td>
											<td>
												<c:if test="${trxdetailOptRecord.type=='COURSEAUDIT' }">退课审核中</c:if>
												<c:if test="${trxdetailOptRecord.type=='COURSESUCCESS' }">退课通过</c:if>
												<c:if test="${trxdetailOptRecord.type=='COURSECANCEL' }">退课未通过</c:if>
											</td>
											<td>
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/order/detailRefund?detailOptId=${trxdetailOptRecord.id}">详情</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty trxdetailOptRecordList}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有退课信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</form>
				</div>
				<!-- /commonWrap -->
			</div>

		<!-- /commonWrap -->
	<!-- 内容 结束 -->
</body>
</html>
