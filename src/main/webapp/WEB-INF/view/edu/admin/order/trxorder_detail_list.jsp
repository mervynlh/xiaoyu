<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水列表</title>
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
		$("#requestId,#userName,#mobile,#startCreateTime,#startPayTime,#startAuthTime,#endCreateTime,#endPayTime,#endAuthTime,#payType,#trxStatus,#status").val('');
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
		$("#trxStatus").val("${trxorderDetail.trxStatus}");
		$("#status").val("${trxorderDetail.status}");
	})

	/**
	 *关闭课程 
	 *@param detailId
	 */
	function closeCourseConfirm(detailId) {
		if (confirm("确认关闭该课程吗?")) {
			document.location.href = "${ctx}/admin/order/closeCourse?detailId="+ detailId;
		}
	}
	/* function closeCourse(id) {
		document.location.href = "${ctx}/admin/order/closeCourse?detailId="
				+ id;
	} */
</script>
</head>
<body>
		<!-- 内容 开始  -->
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>流水管理</span> &gt; <span>流水列表</span>
				</h4>
			</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<form action="${ctx}/admin/order/orderDetailList" name="searchForm" id="searchForm" method="post">
						<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"><font>订单编号：</font></span><input id="requestId" type="text" name="trxorderDetail.requestId" value="${trxorderDetail.requestId}" class="ml10" /></li>
											<li><span class="ddTitle"><font>下单开始时间：</font></span> <input type="text" id="startCreateTime" name="trxorderDetail.startCreateTime" value="<fmt:formatDate value='${trxorderDetail.startCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' /> " class="ml10" /></li>
											<li><span class="ddTitle"><font>付款开始时间：</font></span> <input type="text" name="trxorderDetail.startPayTime" id="startPayTime" value="<fmt:formatDate value='${trxorderDetail.startPayTime}' pattern='yyyy-MM-dd HH:mm:ss' /> " class="ml10" /></li>
                                             <li><span class="ddTitle"><font>姓名：</font></span><input type="text" id="userName" name="trxorderDetail.userName" value="${trxorderDetail.userName}" class="ml10" /></li>
                                            <li><span class="ddTitle"><font>手机号：</font></span><input type="text" id="mobile" name="trxorderDetail.mobile" value="${trxorderDetail.mobile}" class="ml10" /></li>
										</ul>
									</div>
									<div class="w50pre fl">
										<ul class="ddBar">
											<!-- <li><span class="ddTitle"><font>支付类型：</font></span> <select id="payType" name="trxorderDetail.payType" class="ml10">
													<option value="">---请选择---</option>
													<option value="ALIPY">支付宝</option>
													<option value="KUAIQIAN">快钱</option>
													<option value="CARD">课程卡</option>
													<option value="FREE">赠送</option>
													<option value="INTEGRAL">积分</option>
											</select></li> -->
											<li><span class="ddTitle"><font>订单状态：</font></span> <select id="trxStatus" name="trxorderDetail.trxStatus" class="ml10">
													<option value="">---请选择---</option>
													<option value="INIT">未付款</option>
													<option value="SUCCESS">已付款</option>
													<option value="REFUND">已退款</option>
													<option value="CANCEL">已取消</option>
											</select></li>
											<li><span class="ddTitle"><font>下单结束时间：</font></span> <input type="text" name="trxorderDetail.endCreateTime" id="endCreateTime" value="<fmt:formatDate value='${trxorderDetail.endCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' />" class="ml10" /></li>
											<li><span class="ddTitle"><font>付款结束时间：</font></span> <input type="text" name="trxorderDetail.endPayTime" id="endPayTime" value="<fmt:formatDate value='${trxorderDetail.endPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />" class="ml10" /></li>
											<li><span class="ddTitle"><font>课程状态：</font></span> <select id="status" name="trxorderDetail.status" class="ml10">
													<option value="">---请选择---</option>
													<option value="1">待确认时间</option>
													<option value="2">待上课</option>
													<option value="3">待确认课酬</option>
													<option value="4">未评价</option>
													<option value="5">已评价</option>
													<option value="7">取消</option>
											</select></li>
										</ul>
									</div>
									<div class="w50pre fl" style="text-align: center;">
										<ul class="ddBar">
											<li><input type="button" class="btn btn-danger" value="查询" name="" onclick="goPage(1)" /> 
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
									<th><span>姓名</span></th>
									<th><span>手机号</span></th>
									<th><span>下单时间</span></th>
									<th><span>支付时间</span></th>
									<th><span>原始价</span></th>
									<th><span>销售价</span></th>
									<th><span>课程名</span></th>
									<th><span>订单编号</span></th>
									<th><span>课程状态</span></th>
									<th><span>订单状态</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${orderDetailList.size()>0}">
									<c:forEach items="${orderDetailList}" var="orderDetail">
										<tr id="rem${orderDetail.id }">
											<td>${orderDetail.id}</td>
											<td>${orderDetail.userName}</td>
											<td>${orderDetail.mobile}</td>
											<td><fmt:formatDate value="${orderDetail.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${orderDetail.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${orderDetail.sourcePrice}</td>
											<td>${orderDetail.currentPrice}</td>
											<td>${orderDetail.courseName}</td>
											<td>${orderDetail.requestId }</td>
											<td>
												<c:if test="${orderDetail.status==1}">待确认时间</c:if>
												<c:if test="${orderDetail.status==2}">待上课</c:if>
												<c:if test="${orderDetail.status==3}">待确认课酬</c:if>
												<c:if test="${orderDetail.status==4}">未评价</c:if>
												<c:if test="${orderDetail.status==5}">已评价</c:if>
												<c:if test="${orderDetail.status==6}">已上课</c:if>
												<c:if test="${orderDetail.status==7}">取消</c:if>
											</td>
											<td>
												<c:if test="${orderDetail.trxStatus=='INIT'}">未付款</c:if> 
												<c:if test="${orderDetail.trxStatus=='SUCCESS'}">已付款</c:if> 
												<c:if test="${orderDetail.trxStatus=='REFUND'}">已退款	</c:if> 
												<c:if test="${orderDetail.trxStatus=='CLOSED'}">已关闭</c:if> 
												<c:if test="${orderDetail.trxStatus=='CANCEL'}">已取消</c:if>
												<c:if test="${orderDetail.trxStatus=='AUDIT'}">退款审核中</c:if>
												<c:if test="${orderDetail.trxStatus=='FINISH'}">已完成</c:if>
											</td>
											<td>
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/order/detailinfo?detailId=${orderDetail.id}">详情</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${orderDetailList.size()==0||orderDetailList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有订单信息！</span>
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
