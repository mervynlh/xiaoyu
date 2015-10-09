<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学习记录</title>
</head>
<body>
	<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<div class="u-s-history">
						<div class="u-s-history-box">
							<c:if test="${studyHistoryList != null && studyHistoryList.size() > 0}">
							<ul>
								<c:forEach items="${studyHistoryList }" var="studenthistory">
									<c:if test="${studenthistory != null && studenthistory.size() > 0}">
										<c:forEach items="${studenthistory}" var="history" varStatus="index">
										<li class="pr li-fist" ${index.index != 0 ? "style='display:none'" : ""} >
											<span class="u-s-h-bg pa">
												${history.year }
											</span>
										</li>
										<li>
											<div class="u-s-history-l pt50">
												<span class="u-s-h-l-nr vam mr5"><fmt:formatDate value="${history.lastUpdateTime }" pattern="MM-dd HH:mm"/></span>
												<span class="u-s-h-l-dian vam"></span>
											</div>
											<div class="u-s-history-r ml20 pt50">
												<span class="u-s-h-r-nr vam">
													<tt class="fsize14 c-999 f-fM vam">已学</tt>
													<tt class="fsize14 c-master f-fM vam ml10 mr10">|</tt>
													<tt class="fsize14 c-999 f-fM vam">${history.titleName }-${history.courseName }（
														<c:if test="${history.courseType == 1 }">一对一</c:if>
														<c:if test="${history.courseType == 2 }">班课</c:if>
														）${fn:replace(history.subjectMajorName, ',', ' ')}</tt>
													<tt class="fsize14 c-master f-fM vam ml10 mr10">|</tt>
													<tt class="fsize14 c-999 f-fM vam">
														<c:if test="${history.courseModel == 'TEACHERVISIT' }">老师上门</c:if>
														<c:if test="${history.courseModel == 'STUDENTVISIT' }">学生上门</c:if>
														<c:if test="${history.courseModel == 'TALKADDRESS' }">协商地点</c:if>
														<c:if test="${history.courseModel == 'ONLINEOTO' }">在线教学</c:if>
														<c:if test="${history.courseModel == 'ONLINECOU' }">在线授课</c:if>
														<c:if test="${history.courseModel == 'LINEDOWNCOU' }">线下面授</c:if>
													</tt>
													<c:if test="${history.courseModel != 'ONLINECOU' && history.courseModel != 'ONLINEOTO'}">
														<tt class="fsize14 c-master f-fM vam ml10 mr10">|</tt>
														<tt class="fsize14 c-999 f-fM vam u-s-h-r-dz">上课地址：${history.studyAddress }</tt>
													</c:if>
													<tt class="fsize14 c-master f-fM vam ml10 mr10">|</tt>
													<tt class="fsize14 c-999 f-fM vam">时间：${history.studyTime}
													</tt>
												</span>
											</div>
											<div class="clear"></div>
										</li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</ul>
							</c:if>
							
							<c:if test="${studyHistoryList == null || studyHistoryList.size()==0}">
								<div class="u-T-body-in mt30">
									<div class="u-T-body-infor">
										<p class="c-master fsize24 f-fM tac">您还没有相关学习记录</p>
									</div>
								</div>
							</c:if>
					
						</div>
					</div>
				</section>   
			</article>
		</div>
</body>
</html>
