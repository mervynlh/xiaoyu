<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<section class="u-m-c-head">
				<span class="fr"><em class="right-go icon-2-16">&nbsp;</em><a class="vam c-666 ml5" title="返回订单列表" href="javascript:window.history.go(-1)">返回订单列表</a></span>
				<ul class="fl u-m-c-h-txt">
					<li class="current"><strong><a href="" class="whiteCol">订单详情</a></strong></li>
				</ul>
				<div class="clear"></div>
			</section>
			<section class="line1">
				<div class="mt30 pl20 pr20">
					<h6 class="hLh30">
						<span class="c-4e fsize14">订单信息：</span>
					</h6>
					<section class="u-add-list u-order-infor-wrap-1">
						<ol>
							<li><span class="w50per disIb">订单号：<b>${trxorder.requestId}</b></span><span class="w50per disIb"> 订单状态：<span class="c-orange"> <c:if test="${trxorder.trxStatus=='INIT'}">
										未支付
										</c:if> <c:if test="${trxorder.trxStatus=='SUCCESS'}">
										已支付
										</c:if> <c:if test="${trxorder.trxStatus=='REFUND'}">
										已退款
										</c:if> <c:if test="${trxorder.trxStatus=='CANCEL'}">
										已取消
										</c:if>
								</span></span></li>
							<li><span class="w50per disIb">下单时间： <fmt:formatDate value="${trxorder.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></span> <span class="w50per disIb">应付订单金额：￥ ${trxorder.orderAmount}</span></li>
							<li><span class="w50per disIb">付款方式： <c:if test="${trxorder.payType=='KUAIQIAN'}">
												块钱
											</c:if> <c:if test="${trxorder.payType=='ALIPY'}">
												支付宝
											</c:if> <c:if test="${trxorder.payType=='CARD'}">
												块钱
											</c:if> <c:if test="${trxorder.payType=='FREE'}">
												赠送
											</c:if> <c:if test="${trxorder.payType=='INTEGRAL'}">
												未支付
											</c:if>
							</span><span class="w50per disIb">&nbsp;</span></li>
						</ol>
					</section>
					<div class="mt20">
						<h6 class="hLh30">
							<span class="c-4e fsize14">购买课程详情：</span>
						</h6>


						<c:forEach var="detail" items="${trxorder.trxorderDetailList}">
							<section class="mt10">
								<h4 class="u-pageBar unFw of">
									<span class="fr"><font class="fsize14 f-fM c-orange">￥${detail.currentPirce}</font></span><font class="fsize14 f-fM c-orange">购买${detail.courseName}</font>
								</h4>
								<table class="tab-integral mt10" width="100%" cellspacing="0" cellpadding="0" border="0">
									<thead>
										<tr>
											<th width="60%">包含课程</th>
											<th width="20%">描述</th>
											<th width="20%">课时</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${detail.courseName}</td>
											<td align="center">${detail.courseTitle}</td>
											<td align="center">${detail.lessionNum}</td>	
										</tr>
									</tbody>
								</table>
							</section>
						</c:forEach>
					</div>
					<div class="mt20 order-top-pagebar">
						<section class="mt5 mb5 of tar c-666">
							<p class=" fsize14 mt20">
								<span class="ml50">课程数量：<span class="f-fM">￥ ${fn:length(trxorder.trxorderDetailList)}</span>件
								</span><span class="ml50">课程金额：<span class="f-fM">￥${trxorder.orderAmount}</span></span> <span class="ml50">-优惠金额：<span class="f-fM">￥${trxorder.couponAmount}</span></span>
							</p>
							<p class="fsize18 mt10 mb20">
								<tt>应付订单总金额：</tt>
								<tt class="c-orange m10 fsize20 f-fM" id="zongjiaId">￥${trxorder.orderAmount}</tt>
							</p>
						</section>
					</div>
				</div>
			</section>
		</section>
	</article>
	<div id="actdivWinId" style="display: none; background: none repeat scroll 0 0 #000000; height: 100%; left: 0; opacity: 0.2; position: fixed; top: 0; width: 100%; z-index: 9999999;"></div>
</body>
</html>
