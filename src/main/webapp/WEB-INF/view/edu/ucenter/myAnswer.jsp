<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的问答</title>
<%-- <script type="text/javascript" src="${ctx}/static/edu/js/ucenter/u_suggest.js"></script> --%>
<script type="text/javascript">
function showReply(id){
	if($("#reply"+id).css("display")!='none'){
		$("#reply"+id).slideUp(150);
	}else{
	$.ajax({
		url:baselocation+'/front/ajax/answerReplyList',
		type:'post',
		dataType:'json',
		async:false,
		data:{'answerId':id},
		success:function (result){
			if(result.success){
				var obj = result.entity;
				var reProblemContext='';
					for(var i=0;i<obj.length;i++){
						var reProName = obj[i].showName;
						reProblemContext+='<div class="mt10 pl10 pr10 pb10"><dl class="n-reply-list"><dd><div class="of"><span class="fl"><font class="fsize12 c-blue">'+reProName+'</font><font class="fsize12 c-999 ml5">于'+obj[i].addTime+'</font><font class="fsize12 c-999 ml5">回复：</font></span></div>';
						reProblemContext+='<div class="noter-txt mt5"><p class="c-999">'+obj[i].content+'</p></div><div class="of mt5"></div></dd></dl></div>';
					}
				$("#reply"+id).slideDown(150).html(reProblemContext);
			}
		}
	});
	}
}
</script>
</head>
<body>
<form id="searchForm" action="${ctx }/uc/myCouAnswer" method="post">
<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current"><a href="javascript:void(0)" title="课程提问">课程提问</a></li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				
				<!-- /u-m-sub-head -->
				<div class="pl15 pr15 questlist">
					<c:if test="${empty answerList }">
						<section class="comm-tips-1">
							<p>
								<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">还没有课程提问！</font>
							</p>
						</section>
					</c:if>
					<c:if test="${!empty answerList}">
						<article class="s-record-wrap">
							<fmt:formatDate value="${today}" pattern="yyyy/MM/dd  HH:mm:ss" />
							<section>
								<div class="pt30" id="allRes">
									<c:forEach items="${answerList}" var="answer" varStatus="index">
										<script type="text/javascript">
											var lastdate=' <fmt:formatDate value="${answerList[index.index-1].addTime}" pattern="yyyy" />'; 
											var nowdate=' <fmt:formatDate value="${answer.addTime}" pattern="yyyy" />';
											if(lastdate!=nowdate){
												document.write('<aside class="pr hLh20"><span class="s-r-year c-fff"><big>'+nowdate+'</big><small>年</small></span></aside>');
											}
										</script> 
										<article class="mt20 s-record">
											<span class="s-r-sj">&nbsp;</span>
											<aside class="s-r-c-time">
												<span class="c-999 mr10"> <fmt:formatDate value="${answer.addTime}" pattern="MM-dd hh:mm" />
												</span> <em class="icon-2-16 s-r-c-t">&nbsp;</em>
											</aside>
											<div>
					               		 			<span class="mr5 c-orange">提出疑问：&nbsp;&nbsp;&nbsp;有 ${answer.replyCount}&nbsp;个回答&nbsp;&nbsp;<a href="${ctx}/front/playkpoint/${answer.parentId}?kpointId=${answer.sonId}" class="c-blue" target="_blank" title="课程详情">课程详情</a>
					               		 			</span>
					               		 			<section class="mt10 s-r-c-desc">
														<p class="c-999">${answer.content}</p>
													</section>
													<c:if test="${answer.replyCount>0}">
														<span class="mr5 c-orange"><a class="c-orange" title="回答" onclick="showReply(${answer.id })" href="javascript:void(0)">
															查看回答
														</a></span>
													</c:if>
													<section id="reply${answer.id }" class="mt10 s-r-c-desc" style="display: none;height: auto;">
													</section>
											</div>
										</article>
									</c:forEach>
								</div>
							</section>
						</article>
						<section class="mt5 mb5">
							<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"></jsp:include>
						</section>
					</c:if>
				</div>
			</section>
		</section>
	</article>
</form>
	<!-- /u-main end -->
</body>
</html>
