<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户账户历史信息</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	//日历空间	
	$(function() {
		$("#startDate,#endDate").datetimepicker({
			regional : "zh-CN",
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			timeFormat : 'HH:mm:ss',
		})
		$("#actHistoryType").val("${queryUserAccounthistory.actHistoryType}");
		$("#bizType").val("${queryUserAccounthistory.bizType}");
	})
	
	function clean(){
		
		$("#startDate").val('');
		$("#endDate").val('');
		$("#actHistoryType").val("");
		$("#bizType").val("");
	}
</script>
</head>
<body>

	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>学员账户管理</span> &gt; <span>学员历史信息列表</span>
		</h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/accout/detailed_list" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
				 <input type="hidden" name="queryUserAccounthistory.userId" value="${queryUserAccounthistory.userId}"></input>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>交易类型：</font></span> 
									<select name="queryUserAccounthistory.actHistoryType" id="actHistoryType">
									<option value="">--请选择--</option>
									<option value="SALES">消费</option>
									<option value="REFUND">退款</option>
									<option value="VMLOAD">充值卡充值</option>
									<option value="CASHLOAD">现金充值</option>
									<option value="ADMINLOAD">后台充值</option>
									<option value="ADMINREFUND">后台扣款</option>
									</select>
									</li>
									<li><span class="ddTitle"><font>操作开始日期：</font></span> <input type="text" name="queryUserAccounthistory.startTime" value="<fmt:formatDate value="${queryUserAccounthistory.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /> "  id="startDate" class="AS-inp" /></li>
								</ul>

							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
								<li><span class="ddTitle"><font>业务类型：</font></span> 
									<select name="queryUserAccounthistory.bizType" id="bizType">
									<option value="">--请选择--</option>
									<option value="COURSE">课程订单</option>
									<option value="MEMBER">会员订单</option>
									<option value="BOOK">图书订单</option>
									<option value="CARDLOAD">充值卡订单</option>
									<option value="ADMIN">后台操作</option>
									</select>
									</li>
									<li><span class="ddTitle"><font>操作结束日期：</font></span><input type="text" name="queryUserAccounthistory.endTime" value="<fmt:formatDate value="${queryUserAccounthistory.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /> "  id="endDate" class="AS-inp" /> </li>
								</ul>
<li><input type="submit" class="btn btn-danger" value="查询" name="" /> <input type="button" class="btn btn-danger" value="清空" name="" onclick="clean()" /></li>
							</div>
							<div class="clearfix"></div>
						</div>
					</caption>
					<thead>
						<tr>
							<th><span>当前余额</span></th>
							<th><span>cash余额</span></th>
							<th><span>vm余额</span></th>
							<th><span>交易金额</span></th>
							<th><span>交易类型</span></th>
							<th><span>业务类型</span></th>
							<th><span>操作时间</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${userHisoty.size()>0}">
							<c:forEach items="${userHisoty}" var="user">
								<tr>
									<td>${user.balance}</td>
									<td>${user.cashAmount}</td>
									<td>${user.vmAmount}</td>
									<td>${user.trxAmount}</td>
									<td>
										<c:if test="${user.actHistoryType =='SALES'}">
										消费
										</c:if>  
										<c:if test="${user.actHistoryType =='REFUND'}">
										退款
										</c:if> 
										<c:if test="${user.actHistoryType =='VMLOAD'}">
										充值卡充值
										</c:if> 
										<c:if test="${user.actHistoryType =='CASHLOAD'}">
										现金充值
										</c:if> 
										<c:if test="${user.actHistoryType =='ADMINLOAD'}">
										后台充值
										</c:if>
										<c:if test="${user.actHistoryType =='ADMINREFUND'}">
										后台扣款
										</c:if>  
									</td>
									<td>
										<c:if test="${user.bizType=='COURSE'}">
										课程订单
										</c:if> 
										<c:if test="${user.bizType=='MEMBER'}">
										会员订单
										</c:if> 
										<c:if test="${user.bizType=='BOOK'}">
										图书订单
										</c:if> 
										<c:if test="${user.bizType=='CARDLOAD'}">
										充值卡订单
										</c:if>
										 <c:if test="${user.bizType=='ADMIN'}">
										后台操作
										</c:if>
									</td>
									<td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both" /></td>
								</tr>
							</c:forEach>
						</c:if>

						<c:if test="${userHisoty.size()==0||userHisoty==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有用户账户历史信息！</span>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</form>
			<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div>
		<!-- /commonWrap -->
	</div>

</body>
</html>
