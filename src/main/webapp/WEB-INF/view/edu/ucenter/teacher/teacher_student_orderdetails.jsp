<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<script type="text/javascript">
	// 课时评价
	function classAssess(teacherId,detailId){
		dialog("",4,"","javascript:confirmClassAssess("+teacherId+","+detailId+")");
	}
	// 确认评价
	function confirmClassAssess(teacherId,detailId){
		var content = $("#userInfo").val();
		$.ajax({
			url:baselocation+"/ajax/assess/assessCourse",
			type:"post",
			data:{
				"content":content,
				"detailId":detailId,
				"teacherId":teacherId
			},
			dataType:"json",
			success:function(result){
				if(result.success){
					dialog(result.message,17,"","/uc/teacherOrder");
				}else{
					dialog(result.message,9,"","");
				}
			}
		});
	}
	// 退款弹窗
	function refundDialog(id){
		dialog("申请退款的原因",11,"","javascript:refundConfirm("+id+")")
	}
	// 确认申请退款
	function refundConfirm(id){
		var description = $("#description").val();
		$.ajax({
			url:baselocation+"/order/ajax/refund/"+id,
			type:"post",
			data:{
				"description":description
			},
			dataType:"json",
			success:function(result){
				if(result.success){
					dialog(result.message,17,"","/uc/teacherOrder");
				}else {
					dialog(result.message,9,"","");
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<div class="u-s-ord-details-tit">
						<a href="javascript:void(0)" class="u-s-d-t-img">
							<img src="<%=staticImageServer %>${userExpand.avatar}" alt="">
						</a>
						<div class="u-s-d-t-wz">
							<p>购课学生：${userExpand.realname }</p>
							<p>
								<tt class="ml30">
									电话：${userExpand.mobile }
								</tt>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<section>
						<table cellspacing="0" cellpadding="0" border="0" class="u-order-table u-order-table-deat" style="width: 100%;"> 
							<thead> 
								<tr> 
									<th colspan="4">
										<span> 
											<span class="vam fsize14 mr40 f-fM c-666">
												订单号： ${trxorder.requestId }
											</span> 
										</span> 
										<span class="dislb mr40 f-fM c-666 fsize14">
											下单时间： <fmt:formatDate value="${trxorder.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
										</span> 
										<span class="dislb mr20 f-fM c-red-1 fsize14">
											状态:     <c:if test="${trxorder.trxStatus=='INIT' }">待支付</c:if>
											<c:if test="${trxorder.trxStatus=='SUCCESS' }">上课中</c:if>
											<c:if test="${trxorder.trxStatus=='FINISH' }">已结束</c:if>
											<c:if test="${trxorder.trxStatus=='AUDIT' }">退款审核中</c:if>
											<c:if test="${trxorder.trxStatus=='REFUND' }">已退款</c:if>
											<c:if test="${trxorder.trxStatus=='CANCEL' }">已取消</c:if>
										</span> 
									</th> 
								</tr> 
							</thead> 
							<tbody> 
								<tr> 
									<td valign="middle" align="center" style="border: none;width:250px"> 
										<p class="fsize14 c-666 f-fM tac">课程名称</p>
									</td> 
									<td valign="middle" align="center" class="" style="width:170px;border:none;"> 
										<p class="fsize14 c-666 f-fM tac">订单课时</p> 
									</td> 
									<td valign="middle" align="center" style="width:170px;border: none;">
									  <p class="fsize14 c-666 f-fM tac">订单价格</p>
									</td> 
									<td align="center" class="td25749td td25749" style="width:144px;border:none;">
										 <p class="fsize14 c-666 f-fM tac">订单操作</p>
									</td> 
								</tr>
								<tr>
									<td valign="middle" align="center" style="border: none;width:250px"> 
										<p class="fsize14 c-999 f-fM tac">${trxorder.subjectMajorStr}</p>
										<div class="u-teaname-btn vam u-teaname-btn-ordsu">
											<a href="javascript:void(0)" class="mt15" style="margin-bottom:0;padding:0;">
											<c:if test="${trxorder.sellType==1}">一对一</c:if>
											<c:if test="${trxorder.sellType==2}">班课</c:if>
											</a>
										</div>
									</td>
									<td valign="middle" align="center" class="" style="width:170px;border:none;">
										<p class="tac">
											<tt class="fsize14 c-master f-fM">${trxorder.lessionNum }</tt>
											<tt class="fsize14 c-999 f-fM">次课时</tt>
										</p>
									</td>
									<td valign="middle" align="left" style="width:100px;border: none;padding-left:50px;">
										<p class="">
											<tt class="fsize12 c-999 f-fM">单价：</tt>
											<tt class="fsize12 c-999 f-fM">￥</tt>
											<tt class="fsize14 c-red-1 f-fM">${trxorder.amount/trxorder.lessionNum }</tt>
											<tt class="fsize12 c-999 f-fM">/小时</tt>
										</p>
										<p class="mt10">
											<tt class="fsize12 c-999 f-fM">总价：</tt>
											<tt class="fsize14 c-red-1 f-fM">￥${trxorder.amount }</tt>
										</p>
									</td>
									<td align="center" class="td25749td td25749" style="width:144px;border:none;">
										 <div class="u-teaname-btn vam">
										 	<c:choose>
										 		<c:when test="${trxorder.trxStatus=='SUCCESS'}">
										 			<a href="javascript:refundDialog(${trxorder.id})" style="margin-top:10px;">申请退款</a>
										 		</c:when>
										 		<c:when test="${trxorder.trxStatus=='INIT'}">
										 			<a href="${ctx }/cancleorderStudent/${trxorder.id}" style="margin-top:10px;">取消订单</a>
										 		</c:when>
										 		<c:when test="${trxorder.trxStatus=='CANCEL'}">
										 			<a href="javascript:void(0)" style="margin-top:10px;">订单已取消</a>
										 		</c:when>
										 		<c:when test="${trxorder.trxStatus=='AUDIT'}">
										 			<a href="javascript:void(0)" style="margin-top:10px;">退款审核中</a>
										 		</c:when>
											</c:choose>
										</div>
									</td> 
								</tr> 
							</tbody> 
						</table>
					</section> 
					<c:if test="${not empty trxorderDetailList }">
					<section class="kesxx mt30">
						<div class="kesxx-tit">
							课时信息
						</div>
						<table cellspacing="0" cellpadding="0" border="0" class="kesxx-table" style="width: 100%;">
							<thead>
								<tr>
									<th style="width:25%">课程日期</th>
									<th style="width:25%">课程时段</th>
									<th style="width:25%">课程状态</th>
									<th style="width:25%">课程评价</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="trxorderDetail" items="${trxorderDetailList }">
								<tr>
									<td><fmt:formatDate value="${trxorderDetail.startTime }" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${trxorderDetail.startTime }" pattern="HH:mm"/>-<fmt:formatDate value="${trxorderDetail.endTime }" pattern="HH:mm"/></td>
									<td>
										<c:if test="${trxorderDetail.status==1 }">待确认时间</c:if>
										<c:if test="${trxorderDetail.status==2 }">待上课</c:if>
										<c:if test="${trxorderDetail.status==3 }">待确认课酬</c:if>
										<c:if test="${trxorderDetail.status==4 }">未评价</c:if>
										<c:if test="${trxorderDetail.status==5 }">已评价</c:if>
										<c:if test="${trxorderDetail.status==7 }">已取消</c:if>
									</td>
									<td>
										<div class="u-teaname-btn vam">
											<c:choose>
												<c:when test="${trxorderDetail.status==1 or trxorderDetail.status==2 or trxorderDetail.status==3 }">
													<a href="javascript:void(0)" class="mt15 ml50">课程未完成</a>
												</c:when>
												<c:when test="${trxorderDetail.status==4 }">
													<a href="javascript:classAssess(${teacher.id },${trxorderDetail.id })" class="mt15 ml50">课时评价</a>
												</c:when>
												<c:when test="${trxorderDetail.status==5 }">
													<a href="" class="mt15 ml50">查看课时评价</a>
												</c:when>
												<c:when test="${trxorderDetail.status==7 }">
													<a href="javascript:void(0)" class="mt15 ml50">课程已取消</a>
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
					</c:if>
				</section>
			</article>  
		</div>
</body>
</html>