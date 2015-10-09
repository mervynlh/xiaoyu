<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<title>支付课程</title>
<head>
<script type="text/javascript" >
	$().ready(function() {
		document.kqform.submit();
 	});
</script>
</head>

	<body >
	<!-- onLoad="javascript:document.kqform.submit()" -->
	<form name="kqform" action="https://www.99bill.com/gateway/recvMerchantInfoAction.htm" method="post">
		<!-- <form name="kqform" action="https://www.99bill.com/gateway/recvMerchantInfoAction.htm" method="post"> -->
			<input type="hidden" name="inputCharset" value="${kqParamInfo.inputCharset}"/>
			<input type="hidden" name="bgUrl" value="${kqParamInfo.bgUrl}"/>
			<input type="hidden" name="pageUrl" value="${kqParamInfo.pageUrl}"/>
			<input type="hidden" name="version" value="${kqParamInfo.version}"/>
			<input type="hidden" name="language" value="${kqParamInfo.language}"/>
			<input type="hidden" name="signType" value="${kqParamInfo.signType}"/>
			<input type="hidden" name="signMsg" value="${kqParamInfo.signMsg}"/>
			<input type="hidden" name="merchantAcctId" value="${kqParamInfo.merchantAcctId}"/>
			<input type="hidden" name="payerName" value="${kqParamInfo.payerName}"/>
			<input type="hidden" name="payerContactType" value="${kqParamInfo.payerContactType}"/>
			<input type="hidden" name="payerContact" value="${kqParamInfo.payerContact}"/>
			<input type="hidden" name="orderId" value="${kqParamInfo.orderId}"/>
			<input type="hidden" name="orderAmount" value="${kqParamInfo.orderAmount}"/>
			<input type="hidden" name="orderTime" value="${kqParamInfo.orderTime}"/>
			<input type="hidden" name="productName" value="${kqParamInfo.productName}"/>
			<input type="hidden" name="productNum" value="${kqParamInfo.productNum}"/>
			<input type="hidden" name="productId" value="${kqParamInfo.productId}"/>
			<input type="hidden" name="productDesc" value="${kqParamInfo.productDesc}"/>
			<input type="hidden" name="ext1" value="${kqParamInfo.ext1}"/>
			<input type="hidden" name="ext2" value="${kqParamInfo.ext2}"/>
			<input type="hidden" name="payType" value="${kqParamInfo.payType}"/>
			<input type="hidden" name="bankId" value="${kqParamInfo.bankId}"/>
			<input type="hidden" name="redoFlag" value="${kqParamInfo.redoFlag}"/>
			<input type="hidden" name="pid" value="${kqParamInfo.pid}"/>
		</form>
	</body>
</html>