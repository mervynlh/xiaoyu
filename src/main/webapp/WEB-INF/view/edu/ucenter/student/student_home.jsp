<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的课表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/calendar.js"></script>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/student/u_student.js"></script>
<script type="text/javascript" src="${ctximg }/static/common/jquery.countdown.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(function() {
		$('.format-date').countdown({
			tmpl : '<span>%{d}</span>天<span>%{h}</span>小时<span>%{m}</span>分<span>%{s}</span>秒'
		});
	});
</script>
</head>
<body>
	<form action="${ctx }/uc/student/home" method="post" id="searchForm">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"	value="${page.currentPage}" />
		<input type="hidden" id="subjectId"	value="${subjectId }" name="subjectId" />
		<input type="hidden" value="${gradeId }"id="gradeId" name="gradeId" />
		<input type="hidden" value="${majorId }"id="majorId" name="majorId" />
		<input type="hidden"value="${status }" id="status" name="status" />
		<input type="hidden" value="" id="subjectMajor" name="subjectMajor" />
	
	<input type="hidden" value="${dates }" id="dates" />
	<div class="u-m-right">
		<article class="u-m-center">
			<section class="u-m-c-wrap">
				<section class="u-m-c-head">
					<ul id="uTabTitle" class="fl u-m-c-h-txt of">
						<li class=""><a title="课表日历"
							href="${ctx }/uc/student/home?status=0">课表日历</a></li>
						<li class=""><a title="将要上的课"
							href="${ctx }/uc/student/home?status=2"">将要上的课(${detailStatus.CLASS })</a>
						</li>
						<li class=""><a title="待确认时间的课"
							href="${ctx }/uc/student/home?status=1"">待确认时间的课(${detailStatus.TIME })</a>
						</li>
						<li class=""><a title="待确认课酬的课"
							href="${ctx }/uc/student/home?status=3"">待确认课酬的课(${detailStatus.REWARD })</a>
						</li>
						<li class=""><a title="已上的课"
							href="${ctx }/uc/student/home?status=6"">已上的课(${detailStatus.OVER+detailStatus.NOTEVALUTE+detailStatus.EVALUTE})</a>
						</li>
					</ul>
					<div class="clear"></div>
				</section>
				<div id="uTabCont" class="mt30">
					<c:if test="${status==0 }">
						<!-- 课表日历 -->
						<article id="calendar">
							<section class="mb50">
								<div class="right-sign-rl">
									<div class="u-s-rl-tit u-s-r-tit">
										<p class="fsize24 c-666 f-fM" id="dateYM"
											onclick="backToday()"></p>
										<a class="up" href="javascript:void(0)"
											onclick="changeMonth('up')"></a> <a class="down"
											href="javascript:void(0)" onclick="changeMonth('down')"></a>
									</div>
									<div class="u-s-rl-boy" style="border-left: 1px solid #e5e5e5;">
										<div class="u-s-rl-boy-for">
											<ul class="clearfix">
												<li><span class="fsize24 f-fM c-666">一</span></li>
												<li><span class="fsize24 f-fM c-666">二</span></li>
												<li><span class="fsize24 f-fM c-666">三</span></li>
												<li><span class="fsize24 f-fM c-666">四</span></li>
												<li><span class="fsize24 f-fM c-666">五</span></li>
												<li><span class="fsize24 f-fM c-red">六</span></li>
												<li><span class="fsize24 f-fM c-red">日</span></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
										<div class="u-s-rl-boy-for dateTable">
											<ul class="clearfix">
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
												<li><a href="javascript:void(0)"
													class="fsize24 f-fM c-666"></a></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
								</div>
								<div class="right-sign-list" id="uTable"></div>
							</section>
						</article>
					</c:if>
					<c:if test="${status!=0 }">
						<article>
							<section class="mb50">
								<div id="select-1" class="pb5 line2">
									<section class="teach-in-box-select mt10 fl">
										<div class="selectUI vam dis fl">
											<div style="width: 150px;" class="job-select">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em><span id="subjectName">
														<c:if test="${ not empty subjectName }">${subjectName }</c:if>
														<c:if test="${empty subjectName }">--请选择--</c:if>
													</span>
												</section>
												<section class="j-s-option" style="">
													<div class="j-s-o-box">
														<c:if test="${not empty subjectList }">
															<c:forEach items="${subjectList }" var="subject">
																<p>
																	<a title="${subject.subjectName}"
																		href="javascript: void(0)" id="${subject.subjectId }">${subject.subjectName }</a>
																</p>
															</c:forEach>
														</c:if>
													</div>
												</section>
											</div>
										</div>
									</section>
									<section class="teach-in-box-select mt10 fl ml50">
										<div class="selectUI vam dis fl">
											<div style="width: 150px;" class="job-select">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="gradeName">
														<c:if test="${ not empty gradeName }">${gradeName }</c:if>
														<c:if test="${empty gradeName }">--请选择--</c:if>	
													</span>
												</section>
												<section class="j-s-option" style="display: none;">
													<div class="j-s-o-box"></div>
												</section>
											</div>
										</div>
									</section>
									<section class="teach-in-box-select mt10 fl ml50">
										<div class="selectUI vam dis fl">
											<div style="width: 150px;" class="job-select">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="majorName">
														<c:if test="${ not empty majorName }">${majorName }</c:if>
														<c:if test="${empty majorName }">--请选择--</c:if>
													</span>
												</section>
												<section class="j-s-option" style="display: none;">
													<div class="j-s-o-box"></div>
												</section>
											</div>
										</div>
									</section>
									<section class="teach-in-box-select mt10 fl ml100">
										<a href="javascript:void(0)" onclick="searchCourse()" id="searchBtn" class="c-btn u-cx-btn"> 查询 </a>
										<a href="javascript:void(0)" onclick="cleanSelect()" class="c-btn u-cx-btn"> 清空 </a>
									</section>
									<div class="clear"></div>
								</div>
								<div class="right-sign-list">
									<c:if test="${not empty detailList }">
										<ul>
											<c:forEach var="detail" items="${detailList }">
												<li>
													<div class="r-s-l-title">
														<input type="hidden" value="${detail.teacherId }"	id="teacherId" /> 
														<input type="hidden" value="${detail.id}" id="detailId" />
														<span 	class="fsize14 f-fM c-333">老师：${detail.teacherName } </span>
														<span class="ml50">
															<em class="icon-pho-3 vam icon24">&nbsp;</em>
															<tt class="fsize16 c-master f-fM">${detail.teacherMobile }</tt>
														</span>
													</div>
													<a href="javascript:void(0)" class="disIb vam mr30">
														<c:choose>
															<c:when test="${not empty detail.teacherAvatar}">
																<img src="<%=staticImageServer %>${detail.teacherAvatar}" style="width: 100px; height: 100px" alt="" title="" />
															</c:when>
															<c:otherwise>
																<img src="${ctx }/static/edu/img/avatar-boy.gif" style="width: 100px; height: 100px" alt="" title="" />
															</c:otherwise>
														</c:choose>
													</a>
													<span class="u-teaname disIb vam">
														<p class="fsize18 c-333 f-fM">${detail.courseName }</p>
														<p class="fsize14 c-999 f-fM mt10">
															<tt class="mr30">
																<c:choose>
																	<c:when test="${detail.courseType==1 }">一对一</c:when>
																	<c:when test="${detail.courseType==2 }">班课</c:when>
																</c:choose>
															</tt>
																<tt>${detail.gradeName }${detail.majorName }</tt>
														</p>
														<p class="fsize14 c-999 f-fM mt5">
															<c:choose>
																<c:when test="${detail.courseModel=='TEACHERVISIT' }">老师上门</c:when> 
																<c:when test="${detail.courseModel=='STUDENTVISIT' }">学生上门</c:when> 
										 						<c:when test="${detail.courseModel=='TALKADDRESS' }">协商地点</c:when> 
																<c:when test="${detail.courseModel=='ONLINEOTO' }">在线教学</c:when> 
																<c:when test="${detail.courseModel=='ONLINECOU' }">在线授课</c:when> 
																<c:when test="${detail.courseModel=='LINEDOWNCOU' }">线下面授</c:when> 
															</c:choose>
														</p>
													</span>
													<span class="u-teaname disIb vam">
														<p class="fsize14 c-999 f-fM tac mt5">
															<fmt:formatDate value="${detail.startTime }" type="both"	pattern="yyyy年MM月dd日" />
														</p>
														<p class="fsize14 c-999 f-fM mt10 tac">
															<fmt:formatDate value="${detail.startTime }" type="time" pattern="HH:mm" />
															-
															<fmt:formatDate value="${detail.endTime }" type="time"  pattern="HH:mm" />
														</p>
													</span>
													<span class="u-teaname disIb vam u-teaname-1">
														<p class="fsize12 c-org f-fM tac mt10">
															<c:choose>
																<c:when test="${status==2 or status==0 }">
																	<tt class="fsize12 f-fM c-666 vam format-date" data-diff="${detail.startTimeLong }"></tt>
																</c:when>
																<c:when test="${status==1 }">
																	<c:if test="${detail.ifConfirm=='STUUNCONFIRM' }">
																		<tt class="fsize12 f-fM c-666 vam">待确认时间</tt>
																	</c:if>
																	<c:if test="${detail.ifConfirm=='TEAUNCONFIRM' }">
																		<tt class="fsize12 f-fM c-666 vam">等待老师确认时间</tt>
																	</c:if>
																</c:when>
																<c:when test="${status==3 }">
																	<tt class="fsize12 f-fM c-666 vam">待确认课酬</tt>
																</c:when>
																<c:when test="${status==6 }">
																	<tt class="fsize12 f-fM c-666 vam">已上课</tt>
																</c:when>
															</c:choose>
														</p>
													</span>
													<span class="u-teaname-btn disIb vam">
														<c:choose>
															<c:when test="${status==2 or status==0  }">
																<c:if test="${detail.courseModel=='ONLINEOTO' or detail.courseModel=='ONLINECOU' }"> 
																	<a href="">进入教室</a>
																</c:if>
																<a href="javascript:editTime(${detail.id})">修改时间</a>
																<a href="javascript:refund(${ detail.id})">退课</a>
															</c:when>
															<c:when test="${status==1 }">
																<c:if test="${detail.ifConfirm=='STUUNCONFIRM' }">
																	<a href="javascript:confirmDialog(${detail.id})" >确认时间</a>
																</c:if>
																<a href="javascript:editTime(${detail.id})">修改时间</a>
															</c:when>
															<c:when test="${status==3 }">
																<a href="javascript:pay(${detail.id})">确认课酬</a>
															</c:when>
															<c:when test="${detail.status==4}">
																<a href="javascript:assessDialog(${detail.id},${detail.teacherId })">评价</a>
															</c:when>
															<c:when test="${detail.status==5}">
																<a href="javascript:lookAssessDialog(${detail.id})">查看评价</a>
															</c:when>
														</c:choose>
													</span>
													<div class="clear"></div>
													<c:if test="${detail.courseModel=='LINEDOWNCOU' or detail.courseModel=='TEACHERVISIT' or detail.courseModel=='STUDENTVISIT' or detail.courseModel=='TALKADDRESS'}">
														<div class="r-s-l-foot">
															<span class="fsize14 c-999 f-fM">上课地址：${detail.studyAddress }</span>
														</div>
													</c:if>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</div>
							</section>
						</article>
						<section class="mt50">
							<div class="Paging tac">
								<jsp:include page="/WEB-INF/view/common/page.jsp"/>
							</div>
						</section> 
					</c:if>
				</div>
			</section>
		</article>
	</div>
	</form>
</body>
</html>
