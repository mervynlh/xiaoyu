<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的优惠券</title>
<script type="text/javascript">
	var status;
	var type;
	$(document).ready(function(){
		var status = ${status};
		var type = ${type};
		$("#uTabTitle li").eq(status-1).addClass("current uHover");
		$(".job-select").mouseover(function(){
			$(".j-s-option").show();
		});
		$(".job-select").mouseout(function(){
			$(".j-s-option").hide();
		});
		if(type==0){
			$(".j-s-defalt span").html("--请选择--");
		}else if(type==1){
			$(".j-s-defalt span").html("折扣券");
		}else if(type==2){
			$(".j-s-defalt span").html("代金券");
		}
	});
	function selectType(type,obj){
		$(".j-s-defalt span").html($(obj).html());
		$(".j-s-option").hide();
		$("#type").val(type);
		$("#searchForm").submit();
	}
</script>
</head>
<body>
<form action="${ctx }/uc/student/coupon" method="post" id="searchForm">
	<input id="status" type="hidden" name="status" value="${status }"/>
	<input id="type" type="hidden" name="type" value="${type }"/>
	<input id="pageCurrentPage" type="hidden" name="page.currentPage"
		value="${page.currentPage}" />
	</form>
	<div class="u-m-right">
		<article class="u-m-center">
			<section class="u-m-c-wrap">
				<section class="u-m-c-head">
					<ul id="uTabTitle" class="fl u-m-c-h-txt of">
						<li class=""><a title="未使用优惠劵"
							 href="/uc/student/coupon?status=1">未使用优惠劵(${statusMap.INIT })</a></li>
						<li class=""><a title="已使用优惠劵" 
							href="/uc/student/coupon?status=2">已使用优惠劵(${statusMap.USED })</a></li>
						<li class=""><a title="已过期优惠劵"
							href="/uc/student/coupon?status=3">已过期优惠劵(${statusMap.OVER })</a></li>
					</ul>
					<div class="clear"></div>
				</section>
				
				<c:if test="${not empty  couponCodeList}">
					<div id="select-1" class="pb5">
						<section class="teach-in-box-select mt10">
							<div class="selectUI vam dis">
								<div class="selectUI-teaname vam dis fl">请选择优惠劵种类：</div>
								<div style="width: 150px; margin-top: 10px;" class="job-select">
									<section class="j-s-defalt">
										<em class="icon14 fr mt5">&nbsp;</em> <span>--请选择--</span>
									</section>
									<section class="j-s-option" style="display: none;">
										<div class="j-s-o-box">
											<p>
												<a title="" href="javascript: void(0)" onclick="selectType(1,this)">折扣劵</a>
											</p>
											<p>
												<a title="" href="javascript: void(0)" onclick="selectType(2,this)">代金劵</a>
											</p>
										</div>
									</section>
								</div>
								<div class="clear"></div>
							</div>
						</section>
						<div class="clear"></div>
					</div>
					<div id="uTabCont" class="mt20">
						<article style="display: block;">
							<section class="mb50">
									<table cellspacing="0" cellpadding="0" border="0"
										style="width: 100%;" class="u-coupon-tab">
										<tr>
											<th style="width: 18%;">面值</th>
											<th style="width: 20%;">使用条件</th>
											<th style="width: 18%;">来源</th>
											<th style="width: 25%">优惠券码</th>
											<th style="width: 27%;">有效期</th>
										</tr>
										<c:forEach var="couponCode" items="${couponCodeList }">
											<tr>
												<c:if test="${couponCode.type==1}">
													<td class="fsize20 c-666 f-fM"><fmt:formatNumber value="${couponCode.amount }" pattern="##.#" minFractionDigits="1" />折</td>
												</c:if>
												<c:if test="${couponCode.type==2}">
													<td class="fsize20 c-666 f-fM">￥${couponCode.amount }</td>
												</c:if>
												<td>
													<p>
														<tt class="c-666 f-fM fsize14">满</tt>
														<tt class="c-org f-fM fsize14">${couponCode.limitAmount}</tt>
														<tt class="c-666 f-fM fsize14">元</tt>
													</p>
													<p class="mt5">
														<c:if test="${couponCode.teacherId==0 }"><tt class="c-666 f-fM fsize14">系统赠送</tt></c:if>
														<c:if test="${couponCode.teacherId!=0 }">
														<a href="${ctx }/front/teacher/${couponCode.teacherId }" class="c-master f-fM fsize14">${couponCode.optuserName }老师</a>
														<tt class="c-666 f-fM fsize14">主讲的课</tt>
														</c:if>
													</p>
												</td>
												<td><tt class="c-666 f-fM fsize16">
												<c:if test="${couponCode.teacherId==0 }">系统赠送</c:if>
												<c:if test="${couponCode.teacherId!=0 }">${couponCode.optuserName }老师</c:if>
												</tt>
												</td>
												<td>
													${couponCode.couponCode }
												</td>
												<td>
													<p class="c-666 f-fM fsize14">
														<fmt:formatDate value="${couponCode.startTime }" type="both" pattern="yyyy-MM-dd" />
													</p>
													<p class="c-999 f-fM fsize12">至</p>
													<p class="c-666 f-fM fsize14">
														<fmt:formatDate value="${couponCode.endTime }"	 type="both" pattern="yyyy-MM-dd" />
													</p>
												</td>
											</tr>
										</c:forEach>
									</table>
							</section>
						</article>
					</div>
				</c:if>

				<c:if test="${empty couponCodeList and status==1 }">
					<div style="margin-top:10px;">
						<p class="c-master fsize24 f-fM tac">对不起，您没有未使用的优惠券，建议去&nbsp;<a href="${ctx}/front/teacher/query/list" class="fsize12 c-master">教师列表</a>&nbsp;领取优惠券</p>
					</div>
				</c:if>
				<c:if test="${empty couponCodeList and status==2 }">
					
				</c:if>
				<c:if test="${empty couponCodeList and status==3 }">
					
				</c:if>
				<!-- 引入公共分页开始 -->
				<section class="mt50">
					<div class="Paging tac">
						<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
					</div>
				</section>
				<!-- 引入公共分页结束 -->
			</section>
		</article>
	</div>
</body>
</html>
