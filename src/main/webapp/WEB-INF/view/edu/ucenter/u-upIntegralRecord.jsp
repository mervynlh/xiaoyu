<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<script type="text/javascript">
	$(function() {
		
		var url = '${ctx}';
		url = url+"?"+'${userId}';
		$("#url").html(url);
		var payStatus = '${payStatusRe}';
		if (payStatus != null && payStatus != '') {
			$("#querySelect").val(payStatus);
		}
	});
	function gotoPay(id){
		$.ajax({
			url:"${ctx}/front/repaycheck/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					window.location.href='${ctx}/front/repay/'+id;
				}else{
					dialog('错误提示',result.message,17,'javascript:window.location.reload()');
				}
			}
		})
	}
	
</script>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_order.js"></script>
</head>
<body>

	
	复制下面链接发送给朋友，注册成功后即可获得${score}积分
	<textarea rows="" cols="" id="url" readonly="readonly" >
	</textarea>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current"><strong><a href="${ctx}/uc/order" class="whiteCol">推广信息</a></strong></li>
				</ul>
				<div class="clear"></div>
			</section>
			<form action="${ctx}/uc/order" id="searchForm" method="post">
			<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			</form>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div>
					<div class="mt10">
						<section>
							<input id="orderId" type="hidden" value=""><input id="orderno" type="hidden" value="">
							<c:if test="${empty  userIntergralRecordList}">
								<section class="comm-tips-1">
									<p>
										<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">暂无成功推广信息</font>
									</p>
								</section>
							</c:if>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="u-order-table">
									<thead>
										<tr>
										<th>好友账号 </th>
										<th> 注册时间</th>
										<th>获得推荐积分</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userIntergralRecordList}" var="list" >
										<tr>
												<td>${list.email}</td>
												<td>
													  <fmt:formatDate value="${list.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
												</td>
												<td>${list.score}</td>	
										</tr>									
										</c:forEach>
									</tbody>
								</table>
						</section>
					</div>
					<section class="mt50 mb50">
						<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
					</section>
				</div>
			</section>
		</section>
	</article>
	<div id="actdivWinId" style="display: none; background: none repeat scroll 0 0 #000000; height: 100%; left: 0; opacity: 0.2; position: fixed; top: 0; width: 100%; z-index: 9999999;"></div>
</body>
</html>
