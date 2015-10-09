<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的评价</title>
<script>
$(function(){
	var status=$("#status").val();
	var type=$("#type").val();
	$("#uTabTitle li").removeClass("current");
	$("#evaTitle li").removeClass("current");
	$("#uTabTitle li:eq("+status+")").addClass("current");
	$("#evaTitle li:eq("+type+")").addClass("current");
});

function searchEvaluation(status,type){
	$("#status").val(status);
	$("#type").val(type);
	$("#searchForm").submit();
}
</script>
</head>
<div id="evaCon">
	<form action="${ctx }/uc/evaluation" method="post" id="searchForm">
	<input type="hidden" id="status" name="status" value="${status }"/>
	<input type="hidden" id="type" name="type" value="${type }"/>
	<article class="u-m-center">
		<section class="u-m-c-wrap">
			<section class="u-m-c-head">
				<ul id="uTabTitle" class="fl u-m-c-h-txt of">
					<c:if test="${userType==0 }">
						<li class=""><a href="javascript:searchEvaluation(0,0)">来自老师的评价</a></li>
						<li class=""><a href="javascript:searchEvaluation(1,0)">给老师的评价</a></li>
					</c:if>
					<c:if test="${userType==1 }">
						<li class=""><a href="javascript:searchEvaluation(0,0)">来自学生的评价</a></li>
						<li class=""><a href="javascript:searchEvaluation(1,0)">给学生的评价</a></li>
					</c:if>
				</ul>
				<div class="clear"></div>
			</section>
			<div id="uTabCont" class="mt30">
				<article id="newWelcomeSellWayListUlId" style="display: block;">
					<section class="mb50">
						<div class="Evaluation-box">
							<div class="Eva-box-tit">
								<ul class="clearfix" id="evaTitle">
									<li onclick="searchEvaluation(${status },0)">
										<div class="b-in-talk-box tac">
											<em class="icon30 vam">&nbsp;</em>
											<tt class="fsize14 f-fM c-666 vam">全部评价（${mapType.GOOD+mapType.AVERAGE+mapType.BAD }）</tt>
										</div>
									</li>
									<li onclick="searchEvaluation(${status },1)">
										<div class="b-in-talk-box">
											<em class="icon30 icon-hp vam">&nbsp;</em>
											<tt class="fsize14 f-fM c-master-2 vam ml5">好评（${mapType.GOOD }）</tt>
										</div>
									</li>
									<li onclick="searchEvaluation(${status },2)">
										<div class="b-in-talk-box">
											<em class="icon30 icon-zp vam">&nbsp;</em>
											<tt class="fsize14 f-fM c-blue vam ml5">中评（${mapType.AVERAGE }）</tt>
										</div>
									</li>
									<li onclick="searchEvaluation(${status },3)">
										<div class="b-in-talk-box">
											<em class="icon30 icon-cp vam">&nbsp;</em>
											<tt class="fsize14 f-fM c-999 vam ml5">差评（${mapType.BAD }）</tt>
										</div>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="Eva-box-boy">
								<c:if test="${assessList!=null&&assessList.size()>0 }">
									<ul>
										<c:forEach var="assess" items="${assessList}">
											<li>
												<div class="box-1">
													<span>
														<tt class="fsize14 f-fM c-666">描述相符</tt>
														<tt class="fsize14 f-fM c-org mr50">${assess.description }</tt>
														<tt class="fsize14 f-fM c-666">教学态度</tt>
														<tt class="fsize14 f-fM c-org mr50">${assess.attribute }</tt>
														<tt class="fsize14 f-fM c-666">响应速度</tt>
														<tt class="fsize14 f-fM c-org mr50">${assess.speed }</tt>
													</span>
													<span>
														<tt class="fsize12 c-999 f-fM">${assess.content }</tt>
													</span>
													<c:if test="${not empty assess.imgs }">
														<span class="mt10" >
															${assess.imgs }
															<span class="clear"></span>
														</span>
													</c:if>
												</div>
												<div class="box-2">
													<span class="tac">
														<tt class="fsize14 f-fM c-666">${assess.subjectMajorName }</tt>
													</span>
													<span class="tac">
														<tt class="fsize14 f-fM c-666">${assess.courseModel }</tt>
													</span>
												</div>
												<div class="box-3">
													<span class="tac">
														<tt class="fsize14 f-fM c-666">
															<fmt:formatDate value="${assess.createTime }" type="both" pattern="yyyy-MM-dd" />
														</tt>
														<tt class="fsize14 f-fM c-666 ml30">
															<fmt:formatDate value="${assess.createTime }" type="time" pattern="HH:mm:ss" />
														</tt>
													</span>
													<span class="tac">
														<c:if test="${userType==0 }">
															<tt class="fsize14 f-fM c-666">${assess.teacherName }老师</tt>
														</c:if>
														<c:if test="${userType==1 }">
															<tt class="fsize14 f-fM c-666">${assess.studentName }同学</tt>
														</c:if>
													</span>
												</div>
												<div class="clear"></div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</div>
					</section>
				</article>
			</div>
			<!-- 引入公共分页开始 -->
			<section class="mt50">
				<div class="Paging tac">
					<jsp:include page="/WEB-INF/view/common/page.jsp" />
				</div>
			</section>
			<!-- 引入公共分页结束 -->
		</section>
	</article>
</form>
</div>
</html>
