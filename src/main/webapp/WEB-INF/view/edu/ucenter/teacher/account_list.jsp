<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_account.js"></script>
</head>
<body>
	<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-S-balan-head pr"> 
						<span class="vam">
							<img src="/static/edu/img/coupons.png" alt="">
						</span>
						<span class="vam ml30">
							<p class="fsize20 c-666 f-fM">余额</p>
							<p class="fsize20 c-org f-fM mt10">￥<fmt:formatNumber value="${userAccount.balance}" pattern="#,##0.00#" maxFractionDigits="2"/></p>
						</span>
						<span class="u-teaname-btn vam pa">
							<a href="javascript:void(0)" onclick="clickCashout(${userAccount.balance})">提现</a>
						</span>
					</section>
					<div id="uTabCont" class="mt20"> 
						<div class="u-us-jyjl">
							<p class="tac fsize20 c-333 f-fM">
								交易记录
							</p>
						</div>
						<c:if test="${empty accList}">
							<div class="u-T-body-in mt30">
								<div class="u-T-body-infor">
									<p class="c-master fsize24 f-fM tac">您还没有账户信息记录！</p>
								</div>
							</div>
						</c:if>
						<c:if test="${!empty accList}">
						<article id="newFreeSellWayListUlId"> 
							<section class="mb50"> 
								 <table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="u-coupon-tab">
                                      <tr>
	                                        <th style="width:20%;">时间</th>
	                                        <th style="width:20%;">收支金额</th>
	                                        <th style="width:20%;">总金额</th>
	                                        <th style="width:20%;">收支来源</th>
                                      </tr>
                                      <c:forEach var="acc" items="${accList}">
                                      <tr>
	                                        <td class="fsize14 c-666 f-fM">
	                                        	<fmt:formatDate value="${acc.createTime}"
 													pattern="yyyy/MM/dd  HH:mm:ss" /> 
	                                        </td>
	                                        <c:choose>
	                                        		<c:when test="${acc.actHistoryType == 'CASHOUT'}">
	                                        		  <td class="fsize14 c-master f-fM">
			                                        	-${acc.trxAmount}元
			                                          </td>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		  <td class="fsize14 c-org f-fM">
				                                        +${acc.trxAmount}元
				                                      </td>
	                                        		</c:otherwise>
                                        	</c:choose>
	                                        <td class="fsize14 c-666 f-fM">
	                                        	${acc.balance}元
	                                        </td>
	                                        <td class="fsize14 c-666 f-fM">
	                                        	<c:if test="${acc.actHistoryType=='CASHLOAD'}">
													学生购课收入
												</c:if> 
												<c:if test="${acc.actHistoryType=='EXTENDRETURN'}">
													推广返现收入
												</c:if>
												<c:if test="${acc.actHistoryType=='CASHOUT'}">
													提现支出
												</c:if>
												<c:if test="${acc.actHistoryType=='REFUND'}">
													退款-提现失败
												</c:if>
	                                        </td>
                                      </tr>
                                      </c:forEach>
                                    </table>
							</section> 
						</article> 
						</c:if>
					</div>
					<form action="${ctx}/uc/teacher/acc" name="searchForm" id="searchForm" method="post">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
					<section class="mt50">
						<div class="Paging tac">
							<jsp:include page="/WEB-INF/view/common/page.jsp"/>
						</div>
					</section> 
					</form>
				</section>   
			</article>
		</div>

</body>
</html>
