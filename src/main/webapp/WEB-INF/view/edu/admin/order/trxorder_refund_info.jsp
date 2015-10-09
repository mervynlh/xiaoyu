<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>退款详情</title>
<script type="text/javascript">
	// 退款通过
	function refundSuccess(id){
		if(confirm("是否确认退款通过？")){
			window.location.href=baselocation+"/admin/order/orderRefundSuccess/"+id;
		}
	}
	// 退款不通过
	function refundCancel(id){
		if(confirm("是否确认退款不通过？")){
			window.location.href=baselocation+"/admin/order/orderRefundCancel/"+id;
		}
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" /> <input type="hidden" id="daoqishijian" value="<fmt:formatDate value="${queryTrxorderDetail.authTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>订单管理</span> &gt; <span>退款详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
							<th align="left" colspan="2"><span>退款信息<tt class="c_666 ml20 fsize12" ></tt>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;流水号</td>
							<td>${trxorderOptRecord.id}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;学生姓名</td>
							<td>${trxorderOptRecord.studentName}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;教师姓名</td>
							<td>${trxorderOptRecord.teacherName}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程名</td>
							<td>${trxorderOptRecord.courseName}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>${trxorderOptRecord.requestId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;退课时间</td>
							<td><fmt:formatDate value="${trxorderOptRecord.createtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单原始金额</td>
							<td>${trxorderOptRecord.orderAmount}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单优惠金额</td>
							<td>${trxorderOptRecord.couponAmount}<c:if test="${empty trxorderOptRecord.couponAmount}">0</c:if> </td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;实际支付金额</td>
							<td>${trxorderOptRecord.amount}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;退款状态</td>
							<td>
								<c:if test="${trxorderOptRecord.type=='ORDERAUDIT'}">审核中</c:if> 
								<c:if test="${trxorderOptRecord.type=='ORDERSUCCESS'}">审核通过</c:if>
								<c:if test="${trxorderOptRecord.type=='ORDERCANCEL'}">审核不通过</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;退款原因</td>
							<td>${trxorderOptRecord.desc}</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<c:if test="${trxorderOptRecord.type=='ORDERAUDIT' }">
									<a class="btn btn-danger"  href="javascript:refundSuccess(${trxorderOptRecord.id })" >通过</a>
									<a class="btn btn-danger"  href="javascript:refundCancel(${trxorderOptRecord.id })" >不通过</a>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
</body>
</html>
