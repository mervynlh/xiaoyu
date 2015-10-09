<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的优惠券</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctx }/static/edu/js/ucenter/teacher/teacher_coupon.js"></script>

</head>
<body>
	<article class="u-m-center">
		<section class="u-m-c-wrap">
			<section class="u-T-head">
				<a href="javascript:addCoupon()" class="u-T-head-tj"> 添加优惠券 </a>
				<p class="mt10 fsize12 c-666 f-fM">我的优惠券（每位教师只能创建3种优惠券）</p>
			</section>
			<c:if test="${couponList!=null&&couponList.size()>0 }">
			<section class="u-T-body">
				<div class="u-T-body-in mt30">
					<table cellspacing="0" cellpadding="0" border="0"
						style="width: 100%;" class="u-t-coupon-tab">
						<thead>
							<tr>
								<th style="width: 15%;">面额</th>
								<th style="width: 25%;">有效期</th>
								<th style="width: 20%;">条件</th>
								<th style="width: 15%;">已领取</th>
								<th style="width: 25%;">备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="coupon" items="${couponList }">
							<tr>
								<td><tt class="fsize14 f-fM c-999 vam">￥</tt> <tt
										class="fsize20 f-fM c-org vam">${coupon.amount }</tt></td>
								<td><tt class="fsize14 f-fM c-999 vam"><fmt:formatDate value="${coupon.startTime }" pattern="yyyy-MM-dd"/> 至
										<fmt:formatDate value="${coupon.endTime }" pattern="yyyy-MM-dd"/></tt></td>
								<td><tt class="fsize14 f-fM c-999 vam">满</tt> <tt
										class="fsize16 f-fM c-org vam">${coupon.limitAmount }</tt> <tt
										class="fsize14 f-fM c-999 vam">元可用</tt></td>
								<td><tt class="fsize16 f-fM c-master vam">${coupon.receiveNum }</tt> <tt
										class="fsize16 f-fM c-666 vam">/${coupon.totalNum }个</tt></td>
								<td><tt class="fsize14 f-fM c-999 vam">优惠券只有 ${coupon.totalNum }张</tt></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
			</c:if>
			<c:if test="${couponList==null||couponList.size()<=0 }">
			<section class="u-T-body">
				<div class="u-T-body-in mt30">
					<div class="u-T-body-infor">
						<p class="c-master fsize24 f-fM tac">暂无优惠券！</p>
					</div>
				</div>
			</section>
			</c:if>
	</article>
</body>
</html>
