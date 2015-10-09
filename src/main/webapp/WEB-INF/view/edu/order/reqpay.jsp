<%@page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/base.jsp"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" >
		<title>To YeePay Page
		</title>
		<script type="text/javascript" >
		$().ready(function() {
		$("#yeepay").submit();
	 	});
</script>
		
	</head>
	<body onLoad="document.yeepay.submit();">
	
	<form name="yeepay" action='${ybParamInfo.payUrl}'  id="yeepay" method='GET'  accept-charset="GBK" style="display: none">
			<input type="text" name="p0_Cmd"   value="${ybParamInfo.p0_Cmd}">
			<input type="text" name="p1_MerId" value="${ybParamInfo.p1_MerId}">
			<input type="text" name="p2_Order" value="${ybParamInfo.p2_Order}">
			<input type="text" name="p3_Amt"   value="${ybParamInfo.p3_Amt}">
 			<input type="text" name="p4_Cur"   value="${ybParamInfo.p4_Cur}">
			<input type="text" name="p5_Pid"   value="${ybParamInfo.p5_Pid}">
			<input type="text" name="p6_Pcat"  value="${ybParamInfo.p6_Pcat}">
			<input type="text" name="p7_Pdesc" value="${ybParamInfo.p7_Pdesc}">
			<input type="text" name="p8_Url"   value="${ybParamInfo.p8_Url}">
			<input type="text" name="p9_SAF"   value="${ybParamInfo.p9_SAF}">
			<input type="text" name="pa_MP"    value="${ybParamInfo.pa_MP}">
			<input type="text" name="pd_FrpId" value="${ybParamInfo.pd_FrpId}">
			<input type="text" name="pm_Period" value="${ybParamInfo.pm_Period}">
			<input type="text" name="pn_Unit" value="${ybParamInfo.pn_Unit}">
			<input type="text" name="pr_NeedResponse"  value="${ybParamInfo.pr_NeedResponse}">
			<input type="text" name="pt_UserName" value="${ybParamInfo.pt_UserName}">
			<input type="text" name="pt_PostalCode" value="${ybParamInfo.pt_PostalCode}">
			<input type="text" name="pt_Address" value="${ybParamInfo.pt_Address}">
			<input type="text" name="pt_TeleNo" value="${ybParamInfo.pt_TeleNo}">
			<input type="text" name="pt_Mobile" value="${ybParamInfo.pt_Mobile}">
			<input type="text" name="pt_Email" value="${ybParamInfo.pt_Email}">
			<input type="text" name="hmac"     value="${ybParamInfo.hmac}">
		</form>
	</body>
</html>
