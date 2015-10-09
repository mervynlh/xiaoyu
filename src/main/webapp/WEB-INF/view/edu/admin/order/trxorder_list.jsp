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
		$("#payType").val("${queryTrxorder.payType}");
		$("#trxStatus").val("${queryTrxorder.trxStatus}");
	})

	//审核订单
	function verifyOrder(orderId, em) {
		if (orderId != null) {
			if(confirm("确定要审核吗")){
			$.ajax({
				url : "${ctx}/admin/order/verify",
				data : {
					"trxorder.id" : orderId
				},
				dataType : "json",
				type : "post",
				async : false,
				success : function(result) {
					if (result.success == true) {
						alert("审核成功");
						$(".payState" + orderId).html("<span>已支付</span>");
						$(em).remove();
					}
				},
				error : function(error) {
					alert("error");
				}
			});
			}
		}
	}
	function closethis() {
		$(".modal").removeClass("in").remove();
		$(".modal-backdrop").removeClass("in").remove();
	}
	function restrat(){
			$("#requestId,#email,#userName,#mobile,#startCreateTime,#startPayTime,#endCreateTime,#endPayTime").val("");
			$("#payType").val("");
			$("#trxStatus").val("");
	}

	//*退费 ， 只有状态为success 才可以退费 
	function refund(id, em, status) {
		if (status != null && status != "") {
			if (status == "INIT") {
				alert("该课程暂未付款,不能退款");
				return false;
			}
			if (status == "REFUND") {
				alert("该课程已经退款，不能重复申请");
				return false;
			}
			if (status == "CANCEL") {
				alert("该课程已经关闭");
				return false;
			} else {
				if(confirm("您确定要退费，款项退回到网站学员账号中？")){
					$.ajax({
						url : "${ctx}/admin/order/refund",
						data : {
							"trxorder.id" : id
						},
						dataType : "json",
						type : "post",
						success : function(result) {
							if (result.success == true) {
								alert("退费成功");
								$(em).remove();
								$(".payState" + id).html("<span>退款</span>");
							}
						},
						error : function(error) {
							alert("error");
						}
					});
				};
				
			}
		}
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/order/orderPageResult" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>订单管理</span> &gt; <span>订单列表</span>
					</h4>
				</div>
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"><font>订单编号：</font></span><input id="requestId" type="text" name="queryTrxorder.requestId" value="${queryTrxorder.requestId}"  class="ml10" /></li>
											<li><span class="ddTitle"><font>用户姓名：</font></span><input type="text" id="userName" name="queryTrxorder.userName" value="${queryTrxorder.userName}"  class="ml10" /></li>
											<li><span class="ddTitle"><font>手机号：</font></span><input type="text" id="mobile" name="queryTrxorder.mobile" value="${queryTrxorder.mobile}"  class="ml10" /></li>
											<li><span class="ddTitle"><font>下单开始时间：</font></span> <input type="text" id="startCreateTime" name="queryTrxorder.startCreateTime" value="<fmt:formatDate value='${queryTrxorder.startCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  class="ml10" /></li>
											<li><span class="ddTitle"><font>付款开始时间：</font></span> <input type="text" name="queryTrxorder.startPayTime" id="startPayTime" value="<fmt:formatDate value='${queryTrxorder.startPayTime}' pattern='yyyy-MM-dd HH:mm:ss' /> "  class="ml10" /></li>
										</ul>
									</div>
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"><font>支付类型：</font></span> <select id="payType" name="queryTrxorder.payType" class="ml10">
											<option value="">---请选择---</option>
											<option value="ALIPAY">支付宝</option>
											<option value="KUAIQIAN">快钱</option>
											<option value="ACCOUNT">账户余额</option>
											<option value="CARD">课程卡</option>
											<option value="FREE">赠送</option>
											<option value="INTEGRAL">积分</option>
											</select></li>
											<li><span class="ddTitle"><font>订单状态：</font></span> <select id="trxStatus" name="queryTrxorder.trxStatus" class="ml10">
											<option value="">---请选择---</option>
											<option value="INIT">未付款</option>
											<option value="SUCCESS">已付款</option>
											<option value="REFUND">已退款</option>
											<option value="CANCEL">已取消</option>
											<option value="OVER">已过期</option>
											
											</select></li>
											<li><span class="ddTitle"><font>下单结束时间：</font></span> <input type="text" name="queryTrxorder.endCreateTime" id="endCreateTime" value="<fmt:formatDate value='${queryTrxorder.endCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' />"  class="ml10" /></li>
											<li><span class="ddTitle"><font>付款结束时间：</font></span> <input type="text" name="queryTrxorder.endPayTime" id="endPayTime" value="<fmt:formatDate value='${queryTrxorder.endPayTime}' pattern='yyyy-MM-dd HH:mm:ss' />"  class="ml10" /></li>
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
									<th width="10%"><span>ID</span></th>
									<th width="8%"><span>姓名</span></th>
									<th width="10%"><span>手机号</span></th>
									<th width=""><span>下单时间</span></th>
									<th width=""><span>支付时间</span></th>
									<th width=""><span>原始价</span></th>
									<th width=""><span>优惠金额</span></th>
									<th width="10%"><span>实际支付</span></th>
									<th width="10%"><span>课程名称</span></th>
									<th width="15%"><span>订单编号</span></th>
									<th width=""><span>交易状态</span></th>
									<th width=""><span>支付类型</span></th>
									<th><span>操作</span></th>
								</tr>
							</thead>
							<tbody align="center">
								<c:if test="${orderList.size()>0}">
									<c:forEach items="${orderList}" var="order">
										<tr id="rem${order.id }">
											<td>${order.id}</td>
											<td>${order.userName }</td>
											<td>${order.mobile}</td>
											<td><fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${order.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${order.orderAmount}</td>
											<td>${order.couponAmount}</td>
											<td>${order.amount}</td>
											<td>${order.courseName}</td>
											<td>${order.requestId}</td>
											<td class="payState${order.id}">
												<c:if test="${order.trxStatus=='INIT'}">
												未支付
												</c:if> <c:if test="${order.trxStatus=='SUCCESS'}">
												已支付
												</c:if> <c:if test="${order.trxStatus=='REFUND'}">
												退款
												</c:if> <c:if test="${order.trxStatus=='CANCEL'}">
												取消
												</c:if>
												<c:if test="${order.trxStatus=='OVER'}">
												已过期
												</c:if>
											</td>
											<td>
												<c:if test="${order.payType=='ALIPAY'}">
												支付宝
												</c:if> <c:if test="${order.payType=='KUAIQIAN'}">
												块钱
												</c:if> <c:if test="${order.payType=='CARD'}">
												课程卡
												</c:if> <c:if test="${order.payType=='FREE'}">
												赠送
												</c:if> <c:if test="${order.payType=='INTEGRAL'}">
												积分
												</c:if> <c:if test="${order.payType=='ACCOUNT'}">
												账户
												</c:if>
											</td>
											<td>
												<c:if test="${order.trxStatus=='INIT'}">
													<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="verifyOrder('${order.id}',this)">审核</a>
												</c:if> <c:if test="${order.trxStatus=='SUCCESS'}">
													<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)" onclick="refund('${order.id}',this,'${order.trxStatus}')">退费</a>
												</c:if> 
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/order/orderinfo/${order.id}">详情</a>
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/order/orderDetailList?requestId=${order.requestId}" onclick="">查看流水</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty orderList}">
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
