<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分兑换</title>
<script type="text/javascript">
function initExchangeIntegral(giftId){
	$.ajax({
		url:baselocation+"/uc/exchange/check/"+giftId,
		dataType:"json",
		type:"post",
		async:false,
		success:function(result){
			if(result.success){
				window.location.href="/uc/gift/doexchange/"+giftId;
			}else{
				dialog(result.message,10,'','');
				return;
			}
		}
	});
}
</script>
</head>
<body>
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="javascript:void(0)" title="">积分兑换</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl15 pr15">
							<section class="pb10 mt20 line2">
								<span><em class="icon-2-16 integral-icon">&nbsp;</em><strong class="vam c-333 fsize14">礼品商店</strong></span>
							</section>
							<section class="integral-list pb50 line2">
							 <form action="${ctx}/uc/integift" method="post" id="searchForm">
		        				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
								<ul class="clearfix">
								<c:forEach items="${userIntegralGiftList }" var="gift">
								<li>
									<c:if test="${gift.courseId==0 }">
									<section class="inte-l-img">
										 <a href="javascript:void(0)" title="${gift.logo}" class="giftWrap">
										 	<img src="<%=staticImageServer %>${gift.logo}" height="133" width="178"  />
					                     </a>
									</section>
									</c:if>
									<c:if test="${gift.courseId!=0 }">
									<section class="inte-l-img">
										 <a href="javascript:void(0)" title="${gift.courseName}" class="giftWrap">
										 	<img src="<%=staticImageServer %>${gift.courseLogo}" height="133" width="178"  />
					                     </a>
									</section>
									</c:if>
									<h6 class="hLh30 of unFw line2"><tt class="c-blue">${gift.name}</tt></h6>
									<div class="of">
										<span class="fr"><a href="javascript:void(0)" onclick="initExchangeIntegral(${gift.id})" title="" class="brow-btn">兑换</a></span>
										<span class="fl c-red mt5 fsize12 f-fM">所需积分：${gift.score}</span>
									</div>
								</li>
								</c:forEach>
								</ul>
								</form>
							</section>
							<!-- /pageBar begin -->
							<c:if test="${userIntegralGiftList.size()>0 }">
							<section class="mt5 mb5 tac">
							<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"/>
							</section>
							</c:if>
							<!-- /pageBar end -->
						</div>
					</section>
				</section>
				
			</article>
	<!-- /u-main end -->
</body>
</html>
