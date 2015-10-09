<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我兑换的礼品</title>
</head>
<body>
			<article class="u-m-center">
				<!-- /u-m-c-head -->
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="javascript:void(0)" title="">我兑换的礼品</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<form action="${ctx }/uc/mygift" method="post" id="searchForm">
					<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
					</form>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl15 pr15">
							<section id="" class="">
							 <c:if test="${empty userIntegralGiftList }">
								<section class="u-T-body">
									<div class="u-T-body-in mt30">
										<div class="u-T-body-infor">
											<p class="c-666 fsize24 f-fM tac">你还没有兑换过礼品，去<a class="fsize24 c-master" href="${ctx}/uc/integift"><u style="text-decoration:underline;">礼品列表</u></a>看看</p>
										</div>
									</div>
								</section>
							</c:if>
								<ul class="u-collect-list">
								<c:forEach items="${userIntegralGiftList }" var="gift">
									<li>
									<c:if test="${gift.courseId!=0 }">
										<section class="fl u-c-img">
											<img width="154" height="116" src="<%=staticImageServer %>${gift.courseLogo}" alt="">
										</section>
										<h4 class="hLh20 of unFw"><tt class="c-666 fsize16 f-fM">${gift.courseName}</tt></h4>
										<div class="u-c-list-desc mt10">
											<p class="c-999">${gift.courseTitle }</p>
										</div>
										</c:if>
										<c:if test="${gift.courseId==0 }">
										<section class="fl u-c-img">
											<img width="154" height="116" src="<%=staticImageServer %>${gift.logo}" alt="">
										</section>
										<h4 class="hLh20 of unFw"><tt class="c-666 fsize16 f-fM">${gift.name}</tt></h4>
										<div class="u-c-list-desc mt10">
											<p class="c-999">${gift.content }</p>
										</div>
										</c:if>
										<div class="hLh20 of mt30">
											<span class="vam c-4e">
												<tt><fmt:formatDate value="${gift.createTime }" type="both"/></tt>      <tt class="ml50">兑换所需积分：${gift.score}</tt>
											</span>
										</div>
									</li>
									</c:forEach>
								</ul>
							</section><!-- / -->
						</div>
					</section>
					<c:if test="${not empty userIntegralGiftList }">
									<section class="mt5 mb5 tac">
							<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"/>
							</section>
					</c:if>
				</section>
			</article>
	<!-- /u-main end -->
</body>
</html>
