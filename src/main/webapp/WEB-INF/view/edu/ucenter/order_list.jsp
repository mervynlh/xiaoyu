<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单管理</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_order.js"></script>
<script type="text/javascript">
//订单状态
var trxStatus = '${trxStatusRe}';

// 退款弹窗
function refundDialog(id){
	dialog("申请退款的原因",11,"","javascript:refundConfirm("+id+")")
}
// 确认申请退款
function refundConfirm(id){
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/order/ajax/refund/"+id,
		type:"post",
		data:{
			"description":description
		},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/order");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
</script>
</head>
<body>
	<div class="u-m-right">
		<form id="searchForm" action="${ctx}/uc/order" method="post">
			<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			<input type="hidden" id="hiddenTrxStatus" name="trxStatus" value="${trxStatusRe}"/>
		 </form>
		<article class="u-m-center"> 
			<section class="u-m-c-wrap"> 
				<section class="u-m-c-head"> 
					<ul id="uTabTitle" class="fl u-m-c-h-txt of"> 
						<li class="current uHover" id="order_all">
							<a title="全部订单" onclick="menuToggle('all')" href="javascript:void(0)">全部订单</a>
						</li> 
						<li class="" id="order_INIT">
							<a title="待支付" onclick="menuToggle('INIT')" href="javascript:void(0)">待支付</a>
						</li> 
						<li class="" id="order_SUCCESS">
							<a title="上课中" onclick="menuToggle('SUCCESS')" href="javascript:void(0)">上课中</a>
						</li>
						<li class="" id="order_FINISH">
							<a title="已结束" onclick="menuToggle('FINISH')" href="javascript:void(0)">已结束</a>
						</li>   
					</ul> 
					<div class="clear"></div> 
				</section> 
				<div id="uTabCont" class="mt30"> 
					<!-- 全部订单开始 -->
					<article id="trxorder_list" style="display:block;">
						<section class="mb50"> 
							<div class="right-sign-list">
							<c:if test="${trxorderList != null && trxorderList.size() > 0 }">
								<ul>
									<c:forEach items="${trxorderList }" var="trxorder">
									<li>
										<span class="u-s-mord disIb vam">
											<p class="fsize14 c-999 f-fM mt10">
												<fmt:formatDate value="${trxorder.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</p>
											<p class="fsize16 c-666 f-fM mt30">
												<tt class="mr30">${trxorder.courseName }</tt>
												<tt>${trxorder.teacherName }</tt>
											</p>
											<p class="fsize14 f-fM mt30">
												<tt class="c-master">
												<c:if test="${trxorder.courseModel == 'TEACHERVISIT'}">老师上门</c:if> 
												<c:if test="${trxorder.courseModel == 'STUDENTVISIT'}">学生上门</c:if>
												<c:if test="${trxorder.courseModel == 'TALKADDRESS'}">协商地点</c:if>
												<c:if test="${trxorder.courseModel == 'ONLINEOTO'}">一对一在线教学</c:if>
												<c:if test="${trxorder.courseModel == 'ONLINECOU'}">在线授课(班课)</c:if>
												<c:if test="${trxorder.courseModel == 'LINEDOWNCOU'}">线下面授(班课)</c:if>
												</tt>
												<tt class="ml30 c-999">￥</tt>
												<tt class="c-org"><fmt:formatNumber value="${trxorder.orderAmount / trxorder.lessionNum}" maxFractionDigits="2"/></tt>
												<tt class="c-999">/小时</tt>
											</p>
										</span>
										<span class="u-s-mord-1 disIb vam">
											<p class="fsize12 c-999 f-fM tac">
												订单号：${trxorder.requestId }
											</p>
											<p class="fsize14 f-fM mt20 tac">
												<tt class="c-999">总价：</tt>
												<tt class="c-org">￥<fmt:formatNumber value="${trxorder.amount}" maxFractionDigits="2"/></tt>
												<tt class="c-999 ml5 mr5">|</tt>
												<tt class="c-999">
												<c:if test="${trxorder.trxStatus == 'INIT' }">待支付</c:if>
												<c:if test="${trxorder.trxStatus == 'SUCCESS' }">上课中</c:if>
												<c:if test="${trxorder.trxStatus == 'AUDIT' }">退款审核中</c:if>
												<c:if test="${trxorder.trxStatus == 'REFUND' }">已退款</c:if>
												<c:if test="${trxorder.trxStatus == 'OVER' }">已过期</c:if>
												<c:if test="${trxorder.trxStatus == 'FINISH' }">已完成</c:if>
												<c:if test="${trxorder.trxStatus == 'CANCEL' }">已取消</c:if>
												</tt>
											</p>
											<p class="u-s-jdt mt20">
												<em class="u-s-jdt-bg" style="width:${trxorder.lessionOver/trxorder.lessionNum*100}%;"></em>
											</p>
											<p class="tac mt10">
												<tt class="fsize14 c-999 f-fM">已完成 </tt>
												<tt class="fsize14 c-master f-fM">${trxorder.lessionOver }</tt>
												<tt class="fsize14 c-999 f-fM">/${trxorder.lessionNum}课时</tt>
											</p>
										</span>
										<span class="u-teaname-btn ml160 disIb vam">
											<a href="${ctx }/uc/order/getTrxorderDetail/${trxorder.id}">查看详情</a>
											<c:if test="${trxorder.trxStatus == 'INIT' }">
												<a href="${ctx}/order/getorder?id=${trxorder.id}">确认支付</a>
												<a href="${ctx }/cancleorderStudent/${trxorder.id}">取消订单</a>
											</c:if>
											<c:if test="${trxorder.trxStatus == 'SUCCESS' }">
												<a href="javascript:refundDialog(${trxorder.id})">申请退款</a>
											</c:if>
										</span>
										<span class="clear"></span>
									</li>
									</c:forEach>
								</ul>
							</c:if>
							
							</div>  
						</section> 
						<c:if test="${empty trxorderList }">
							<div class="u-T-body-in mt30">
								<div class="u-T-body-infor">
									<p class="c-master fsize24 f-fM tac">对不起，此条件下还没有相关订单信息</p>
								</div>
							</div>
						</c:if>
					</article>
					<!-- 全部订单结束 -->
					
				</div>
				<!-- 引入公共分页开始 -->
				<section class="mt50">
					<div class="Paging tac">
						<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
					</div>
				</section>
				<!-- 引入公共分页结束 -->
			</section>   
		</article>
	</div>
</body>
</html>
