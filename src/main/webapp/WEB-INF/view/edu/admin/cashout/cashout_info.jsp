<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>提现详情</title>
</head>
<body >
		
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>提现管理</span> &gt; <span>提现详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 提现基本属性 <tt class="c_666 ml20 fsize12">
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"> &nbsp;用户ID</td>
							<td>${cashOut.userId}</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;提现形式</td>
							<td>
								<select id="cashType" disabled="disabled" >
									<option value="BANK">银行卡</option>
									<option value="ALIPAY">支付宝</option>
								</select>
							</td>
						</tr>
						<script>
							$("#cashType").val('${cashOut.cashType}');
						</script>
						<c:if test="${cashOut.cashType == 'BANK' || cashOut.cashType == 'bank'}">
						<tr>
							<td width="20%" align="center"> &nbsp;手机号</td>
							<td width="80%">${cashOut.userMobile}</td> 
						</tr>
						<tr>
							<td width="20%" align="center"> &nbsp;银行</td>
							<td width="80%">${cashOut.bankName}</td> 
						</tr>
						<tr>
							<td width="20%" align="center"> &nbsp;开户行名称</td>
							<td width="80%">
								${cashOut.openBankName }
							</td>
						</tr>
						<tr>
							<td width="20%" align="center">开户人姓名</td>
							<td width="80%">${cashOut.openBankPerson}</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;银行卡号</td>
							<td>${cashOut.bankCard}</td>
						</tr>
						</c:if>
						<c:if test="${cashOut.cashType == 'ALIPAY' || cashOut.cashType == 'alipay'}">
						<tr>
							<td align="center"> &nbsp;支付宝账号</td>
							<td>
								${cashOut.alipayAccount }
							</td>
						</tr>
						</c:if>
						<tr>
							<td align="center"> &nbsp;提现金额</td>
							<td>${cashOut.cashoutMoney}&nbsp;&nbsp;元</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;提现状态</td>
							<td>
								<select id="isStatus" disabled="disabled" >
									<option value="1">正常</option>
									<option value="2">已提现</option>
									<option value="3">已取消</option>
								</select>
							</td>
						</tr>
						<script>
							$("#isStatus").val('${cashOut.status}');
						</script>
						<c:if test="${!empty cashOut.optusername && cashOut.optusername != '' }">
						<tr>
							<td align="center"> &nbsp;操作者</td>
							<td>${cashOut.optusername}</td>
						</tr>
						</c:if>
						<tr>
							<td align="center"> &nbsp;提现时间</td>
							<td>
							<fmt:formatDate value="${cashOut.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		
</body>
</html>
