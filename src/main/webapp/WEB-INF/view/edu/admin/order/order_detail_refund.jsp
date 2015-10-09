<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>流水详情</title>
<script type="text/javascript">
	// 退款通过
	function refundSuccess(id){
		if(confirm("是否确认退课通过？")){
			window.location.href=baselocation+"/admin/order/trxorderDetailRefundSuccess/"+id;
		}
	}
	// 退款不通过
	function refundCancel(id){
		if(confirm("是否确认退课不通过？")){
			window.location.href=baselocation+"/admin/order/trxorderDetailRefundCancel/"+id;
		}
	}
</script>
</head>
<body>
			<input type="hidden" name="teacher.picPath" id="imagesUrl" /> <input type="hidden" id="daoqishijian" value="<fmt:formatDate value="${queryTrxorderDetail.authTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>退课管理</span> &gt; <span>退课详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
							<th align="left" colspan="2"><span>退课信息<tt class="c_666 ml20 fsize12" ></tt>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp订单号</td>
							<td>${trxdetailOptRecord.trxorderId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;流水号</td>
							<td>${trxdetailOptRecord.trxorderDetailId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>${trxdetailOptRecord.requestId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;退课时间</td>
							<td><fmt:formatDate value="${trxdetailOptRecord.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;原价</td>
							<td>${trxdetailOptRecord.sourcePrice}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;售价</td>
							<td>${trxdetailOptRecord.currentPrice}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单状态</td>
							<td>
								<c:if test="${trxdetailOptRecord.type=='COURSEAUDIT' }">退课审核中</c:if>
								<c:if test="${trxdetailOptRecord.type=='COURSESUCCESS' }">退课通过</c:if>
								<c:if test="${trxdetailOptRecord.type=='COURSECANCEL' }">退课未通过</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;退课描述</td>
							<td>${trxdetailOptRecord.desc}</td>
						</tr>
						<c:if test="${trxdetailOptRecord.type=='COURSEAUDIT' }">
							<tr>
								<td align="center" colspan="2">
									<a class="btn btn-danger"  href="javascript:refundSuccess(${trxdetailOptRecord.id })" >通过</a>
									<a class="btn btn-danger"  href="javascript:refundCancel(${trxdetailOptRecord.id })" >不通过</a>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
</body>
</html>
