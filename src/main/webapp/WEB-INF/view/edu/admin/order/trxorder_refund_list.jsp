<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>订单列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	//导出
	function orderExcel(){
		$("#searchForm").prop("action","${cxt}/admin/order/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","${ctx}/admin/order/orderPageResult");
	}
	//日历空间	
	$(function() {
		$("#startCreateTime,#endCreateTime,#endPayTime,#startPayTime")
				.datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					dateFormat:"yy-mm-dd",
					timeFormat : 'HH:mm:ss',
					timeFormat : 'HH:mm:ss'
				});
		$("#payType").val("${trxorderOptRecord.payType }");
		$("#type").val("${trxorderOptRecord.type }");
		$("#userType").val("${trxorderOptRecord.userType }");
	})

	function restrat(){
			$("#requestId,#applicant,#userType,#startCreateTime,#startPayTime,#endCreateTime,#endPayTime").val("");
			$("#payType").val("");
			$("#type").val("");
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/order/orderRefundList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>订单管理</span> &gt; <span>退款列表</span>
					</h4>
				</div>
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"><font>订单编号：</font></span><input id="requestId" type="text" name="trxorderOptRecord.requestId" value="${trxorderOptRecord.requestId }"  class="ml10" /></li>
											<li>
												<span class="ddTitle"><font>用户类型：</font></span>
												<select id="userType" name="trxorderOptRecord.userType" class="ml10">
													<option value="">---请选择---</option>
													<option value="0">学生</option>
													<option value="1">教师</option>
												</select>
											</li>
											<li><span class="ddTitle"><font>用户姓名：</font></span><input type="text" id="applicant" name="trxorderOptRecord.applicant" value="${trxorderOptRecord.applicant }"  class="ml10" /></li>
											<li><span class="ddTitle"><font>退款开始时间：</font></span> <input type="text" id="startCreateTime" name="trxorderOptRecord.startCreateTime" value="<fmt:formatDate value='${trxorderOptRecord.startCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  class="ml10" /></li>
										</ul>
									</div>
									<div class="w50pre fl">
										<ul class="ddBar">
											<li>
												<span class="ddTitle"><font>支付类型：</font></span>
												<select id="payType" name="trxorderOptRecord.payType" class="ml10">
													<option value="">---请选择---</option>
													<option value="ALIPAY">支付宝</option>
													<option value="KUAIQIAN">快钱</option>
													<option value="ACCOUNT">账户余额</option>
													<option value="CARD">课程卡</option>
													<option value="FREE">赠送</option>
													<option value="INTEGRAL">积分</option>
												</select>
											</li>
											<li>
												<span class="ddTitle"><font>审核状态：</font></span>
												<select id="type" name="trxorderOptRecord.type" class="ml10">
													<option value="">---请选择---</option>
													<option value="ORDERSUCCESS">退款通过</option>
													<option value="ORDERCANCEL">退款取消</option>
													<option value="ORDERAUDIT">退款审核中</option>
												</select>
											</li>
											<li>
												<span class="ddTitle"><font>退款结束时间：</font></span>
												<input type="text" name="trxorderOptRecord.endCreateTime" id="endCreateTime" value="<fmt:formatDate value='${trxorderOptRecord.endCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' />"  class="ml10" />
											</li>
										</ul>
										</div>
										<div class="w50pre fl" style="text-align: center;">
										<ul class="ddBar" >
											<li>
                                                <input type="button" class="btn btn-danger mt10" value="查询" name="" onclick="goPage(1)" />
                                                <input type="button" onclick="restrat()" class="btn btn-danger mt10" value="清空"  />
                                                <input type="button" onclick="orderExcel()" class="btn btn-danger mt10" value="导出Excel"  />
                                             </span>
											</li>
										</ul>
									</div>
									<div class="clear"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th><span>ID</span></th>
									<th><span>用户类型</span></th>
									<th><span>姓名</span></th>
									<th><span>退单时间</span></th>
									<th><span>原始价</span></th>
									<th><span>优惠金额</span></th>
									<th><span>实际支付</span></th>
									<th><span>课程名称</span></th>
									<th><span>订单编号</span></th>
									<th><span>审核状态</span></th>
									<th><span>支付类型</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
							<tbody align="center">
								<c:if test="${not empty trxorderOptRecordList}">
									<c:forEach items="${trxorderOptRecordList}" var="trxorderOptRecord">
										<tr id="rem${trxorderOptRecord.id }">
											<td>${trxorderOptRecord.id}</td>
											<td>
												<c:if test="${trxorderOptRecord.userType==0 }">学生</c:if>
												<c:if test="${trxorderOptRecord.userType==1 }">教师</c:if>
											</td>
											<td>${trxorderOptRecord.applicant }</td>
											<td><fmt:formatDate value="${trxorderOptRecord.createtime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${trxorderOptRecord.orderAmount}</td>
											<td>${trxorderOptRecord.couponAmount}<c:if test="${empty trxorderOptRecord.couponAmount}">0</c:if></td>
											<td>${trxorderOptRecord.amount}</td>
											<td>${trxorderOptRecord.courseName}</td>
											<td>${trxorderOptRecord.requestId}</td>
											<td class="payState${trxorderOptRecord.id}">
												<c:if test="${trxorderOptRecord.type=='ORDERCANCEL'}">退单取消</c:if>
												<c:if test="${trxorderOptRecord.type=='ORDERSUCCESS'}">退单通过</c:if>
												<c:if test="${trxorderOptRecord.type=='ORDERAUDIT'}">退单审核中</c:if>
											</td>
											<td>
												<c:if test="${trxorderOptRecord.payType=='ALIPAY'}">支付宝</c:if>
												<c:if test="${trxorderOptRecord.payType=='KUAIQIAN'}">块钱</c:if> 
												<c:if test="${trxorderOptRecord.payType=='CARD'}">课程卡</c:if> 
												<c:if test="${trxorderOptRecord.payType=='FREE'}">赠送</c:if> 
												<c:if test="${trxorderOptRecord.payType=='INTEGRAL'}">积分</c:if> 
												<c:if test="${trxorderOptRecord.payType=='ACCOUNT'}">账户余额</c:if>
											</td>
											<td>
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/order/orderRefundInfo/${trxorderOptRecord.id}">详情</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty trxorderOptRecordList}">
									<tr>
										<td colspan="16" align="center">
											<div class="tips">
												<span>还没有订单数据！</span>
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
