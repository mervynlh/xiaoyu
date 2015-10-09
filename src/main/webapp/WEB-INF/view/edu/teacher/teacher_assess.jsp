<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
	<form action="${ctx}/front/teacher/ajax/assess" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		<input type="hidden" name="teacherId" value="${teacherId}"/>
	</form>
	<!-- /global nav -->
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<section class="container">
				<div class="mt30">
					<div class="w950">
						<div>
							<section class="mt30"> 
								<div class="Details mr50">
									<div class="Details-boy mt40">
										<div class="Details-boy-infor">
											<ul id="assessContent">
												<c:forEach items="${assessList}" var="assess">
												<li>
													<div class="fl toux">
														<a href="" title="${assess.nickname}" class="dis">
															<c:choose>
																<c:when test="${!empty assess.avatar}"><img src="<%=staticImageServer%>${assess.avatar}" alt="${assess.nickname}" ></c:when>
																<c:otherwise><img src="${ctx}/static/edu/images/default/default_head.jpg" alt="${assess.nickname}" ></c:otherwise>
															</c:choose>
															<p>${assess.nickname}</p>
														</a>
													</div>
													<div class="fl nr">
														<section class="mt10">
															<span class="fl">
																<tt class="fsize16 c-666 f-fM">${assess.subjectMajorName}</tt>
																<tt class="fsize14 c-999 f-fM ml20 mr20">|</tt>
																<tt class="fsize16 c-666 f-fM">
																${assess.courseModel}
																</tt>
															</span>
															<span  class="fr fsize12 c-999 f-fM">
																<fmt:formatDate value="${assess.createTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>
															</span>
															<div class="clear"></div>
														</section>
														<section class="mt10">
															<span class="fl fsize14 c-org f-fM vam">
																<c:choose>
																	<c:when test="${assess.type==1}">好评</c:when>
																	<c:when test="${assess.type==2}">中评</c:when>
																	<c:otherwise>差评</c:otherwise>
																</c:choose>
																${assess.star}分
															</span>
															<span class="fl ml40 vam">
																<tt class="fsize14 f-fM c-999 vam">教学描述</tt>
																<b title="星级：${assess.description}星" class="star-level-1 star-1-${assess.description} vam">&nbsp;</b>
															</span>
															<span class="fl ml40 vam">
																<tt class="fsize14 f-fM c-999 vam">教学态度</tt>
																<b title="星级：${assess.attribute}星" class="star-level-1 star-1-${assess.attribute} vam">&nbsp;</b>
															</span>
															<span class="fl ml40 vam">
																<tt class="fsize14 f-fM c-999 vam">响应速度</tt>
																<b title="星级：${assess.speed}星" class="star-level-1 star-1-${assess.speed} vam">&nbsp;</b>
															</span>
															<div class="clear"></div>
														</section>
														${assess.content}<br/>
														${assess.imgs}
													</div>
													<div class="clear"></div>
												</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</section>
						</div>
						<section class="mt50">
							<div class="Paging tac">
								<jsp:include page="/WEB-INF/view/common/ajax_page.jsp"/>
							</div>
						</section>	
					</div>
					<div class="clear"></div>
				</div>
			</section>
		</div>
	</section>
