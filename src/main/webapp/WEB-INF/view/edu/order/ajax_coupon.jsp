<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:forEach items="${list }" var="couponCode">
<li>
	<label for="">
		<input type="radio" onchange="changeCode('${couponCode.couponCode}')" name="trxorder.couponCodeId" value="${couponCode.couponCode}">
		<span class="fsize12 c-999 f-fM name ml10">
		<c:if test="${couponCode.limitAmount>0 }">
			满￥${couponCode.limitAmount}&nbsp;优惠
		</c:if>
		<c:if test="${couponCode.type==1 }">
			${couponCode.amount}折
		</c:if>
		<c:if test="${couponCode.type==2 }">
			￥${couponCode.amount}
		</c:if>
		</span>
		<span class="fsize12 c-999 f-fM time">有效期：
		<fmt:formatDate value="${couponCode.startTime}" pattern="yyyy-MM-dd"/>-
		<fmt:formatDate value="${couponCode.endTime}" pattern="yyyy-MM-dd"/>
		</span>
		<div class="clear"></div>
	</label>
</li>
</c:forEach>

