<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券添加</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<link rel="stylesheet"
	href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}"
	type="text/css" />
<link rel="stylesheet"
	href="${ctximg}/static/common/ztree/css/demo.css?v=${v}"
	type="text/css" />
<script type="text/javascript"
	src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
<script type="text/javascript">
	
	function addSubmit(){
		
		$("#addCouponForm").submit();
	}
	
</script>
</head>
<body>

	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>优惠券管理</span> &gt; <span>优惠券添加</span>
		</h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/coupon/add" method="post"id="addCouponForm">
				<input type="hidden" value="2" name="coupon.usertype" />
				<input type="hidden" value="2" name="coupon.type" />
				<table width="50%" cellspacing="0" cellpadding="0" border="0"
					class="commonTab01">
					<tbody>
						<tr>
							<td>教师id：</td>
							<td><input type="text" id="teacherId" name="coupon.teacherId"/></td>
						</tr>
						<tr>
							<td>教师姓名：</td>
							<td><input type="text" id="name" name="coupon.optuserName"/></td>
						</tr>
						<tr>
							<td>面值：</td>
							<td><input type="text" id="price" name="coupon.amount" />元</td>
						</tr>
						<tr>
							<td>张数：</td>
							<td><input type="text" id="number" name="coupon.totalNum"/></td>
						</tr>
						<tr>
							<td>使用条件：</td>
							<td><input type="radio" name="radio" name="coupon.useType" value="1"/>无限制&nbsp;<input type="radio" name="radio" /><input type="text" name="coupon.limitamount"/>元以上才能使用</td>
						</tr>
						<tr>
							<td>有效期</td>
							<td><input type="text" id="startTime" name="coupon.startTime" />至<input type="text" id="coupon.endTime" name="endTime"/></td>
						</tr>
						<tr>
							<td>每人限领：1张</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><textarea cols="30" rows="5" name="coupon.info"></textarea></td>
						</tr>
						<tr>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<!-- /tab4 end -->

</body>
</html>
