<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>订单详情</title>


</head>
<body>
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>订单管理</span> &gt; <span>订单详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
							<th align="left" colspan="2"><span>订单信息<tt class="c_666 ml20 fsize12" ></tt>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单id</td>
							<td>${order.id}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>${order.requestId}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;学生姓名</td>
							<td>${order.studentName}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;手机号</td>
							<td>${order.mobile}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;下单时间</td>
							<td><span><fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;支付时间</td>
							<td><span><fmt:formatDate value="${order.payTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;原始价</td>
							<td>${order.orderAmount}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;折扣券金额</td>
							<td>
								<c:if test="${not empty  order.minusAmount}">${order.minusAmount}</c:if>
								<c:if test="${empty  order.minusAmount}">0</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;优惠券金额</td>
							<td>
								<c:if test="${not empty order.couponAmount }">${order.couponAmount}</c:if>
								<c:if test="${empty order.couponAmount }">0</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;优惠券编码</td>
							<td>
								<c:if test="${not empty order.couponCodeId }">${order.couponCodeId }</c:if>
								<c:if test="${empty order.couponCodeId }">-</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;实际支付</td>
							<td>${order.amount}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;支付类型</td>
							<td>
								<c:if test="${order.payType=='ALIPAY'}">支付宝</c:if>
								<c:if test="${order.payType=='KUAIQIAN'}">块钱</c:if> 
								<c:if test="${order.payType=='CARD'}">课程卡</c:if> 
								<c:if test="${order.payType=='FREE'}">赠送</c:if> 
								<c:if test="${order.payType=='INTEGRAL'}">积分</c:if> 
								<c:if test="${order.payType=='ACCOUNT'}">账户</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单状态</td>
							<td>
								<c:if test="${order.trxStatus=='SUCCESS'}">支付成功</c:if> 
								<c:if test="${order.trxStatus=='INIT'}">未支付</c:if> 
								<c:if test="${order.trxStatus=='REFUND'}">已退款</c:if> 
								<c:if test="${order.trxStatus=='CANCEL'}">已取消</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;支付渠道</td>
							<td>
								<c:if test="${order.reqChannel=='WEB'}">WEB</c:if>
								<c:if test="${order.reqChannel=='APP'}">APP</c:if> 
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程名称</td>
							<td>${order.courseName}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;上课方式</td>
							<td>
								<c:if test="${order.courseModel == 'TEACHERVISIT'}">老师上门</c:if> 
								<c:if test="${order.courseModel == 'STUDENTVISIT'}">学生上门</c:if>
								<c:if test="${order.courseModel == 'TALKADDRESS'}">协商地点</c:if>
								<c:if test="${order.courseModel == 'ONLINEOTO'}">一对一在线教学</c:if>
								<c:if test="${order.courseModel == 'ONLINECOU'}">在线授课(班课)</c:if>
								<c:if test="${order.courseModel == 'LINEDOWNCOU'}">线下面授(班课)</c:if>
							</td>
						</tr>
						<c:if test="${order.courseModel == 'TEACHERVISIT' or order.courseModel == 'STUDENTVISIT' or order.courseModel == 'TALKADDRESS' or order.courseModel == 'LINEDOWNCOU'}">
							<td align="center"><font color="red">*</font>&nbsp;上课地址</td>
							<td>${order.studyAddress}</td>
						</c:if>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;总课时</td>
							<td>${order.lessionNum}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;已完成课时</td>
							<td>${order.lessionOver}</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;备注</td>
							<td>${order.remarks}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
</body>
</html>
