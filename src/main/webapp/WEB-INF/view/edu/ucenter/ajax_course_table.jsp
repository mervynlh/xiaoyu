<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${detailList!=null && detailList.size()>0 }">
	<!-- 学生 -->
	<c:if test="${userType==0 }">
		<ul>
			<c:forEach var="detail" items="${detailList }">
				<li>
					<div class="r-s-l-title">
						<input type="hidden" value="${detail.teacherId }" id="teacherId" />
						<input type="hidden" value="${detail.id}" id="detailId" />
						<span 	class="fsize14 f-fM c-333"> 老师：${detail.teacherName } </span>
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
							<fmt:formatDate value="${detail.startTime }" type="both" pattern="yyyy年MM月dd日" />
						</p>
						<p class="fsize14 c-999 f-fM mt10 tac">
							<fmt:formatDate value="${detail.startTime }" type="time" pattern="HH:mm" />
							-
							<fmt:formatDate value="${detail.endTime }" type="time" pattern="HH:mm" />
						</p>
					</span>
					<span class="u-teaname disIb vam u-teaname-1">
						<p class="fsize12 c-org f-fM tac mt10">
							<tt class="fsize12 f-fM c-666 vam format-date" data-diff="${detail.startTimeLong }"></tt>
						</p>
					</span>
					<span class="u-teaname-btn ml50 disIb vam">
						<c:if test="${detail.courseModel=='ONLINEOTO' or detail.courseModel=='ONLINECOU' }"> 
							<a href="">进入教室</a>
						</c:if>
						<a href="javascript:editTime(${detail.id})">修改时间</a>
						<a href="javascript:refund(${ detail.id})">退课</a>
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
	<!-- 老师 -->
	<c:if test="${userType==1 }">
		<ul>
			<c:forEach var="detail" items="${detailList }">
				<li>
					<div class="r-s-l-title">
						<span 	class="fsize14 f-fM c-333">学生：${detail.studentName } </span>
						<span 	class="ml50">
							<em class="icon-pho-3 vam icon24">&nbsp;</em>
							<tt class="fsize16 c-master f-fM">${detail.studentMobile }</tt>
						</span>
					</div>
					<a href="javascript:void(0)" class="disIb vam mr30">
						<c:choose>
							<c:when test="${not empty detail.studentAvatar}">
								<img src="<%=staticImageServer %>${detail.studentAvatar}" style="width: 100px; height: 100px" alt="" title="" />
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
							<fmt:formatDate value="${detail.startTime }" type="both" pattern="yyyy年MM月dd日" />
						</p>
						<p class="fsize14 c-999 f-fM mt10 tac">
							<fmt:formatDate value="${detail.startTime }" type="time" pattern="HH:mm" />
							-
							<fmt:formatDate value="${detail.endTime }" type="time" pattern="HH:mm" />
						</p>
					</span>
					<span class="u-teaname disIb vam u-teaname-1">
						<p class="fsize12 c-org f-fM tac mt10">
							<tt class="fsize12 f-fM c-666 vam format-date" data-diff="${detail.startTimeLong }"></tt>
						</p>
					</span>
					<span class="u-teaname-btn ml50 disIb vam">
						<c:if test="${detail.courseModel=='ONLINEOTO' or detail.courseModel=='ONLINECOU' }"> 
							<a href="">进入教室</a>
						</c:if>
						<a href="javascript:void(0)" onclick="editTime(${detail.id})">修改时间</a>
						<a href="javascript:refund(${ detail.id})">退课</a>
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
</c:if>
<script>
	$(function() {
		$('.format-date').countdown({
			tmpl : '<span>%{d}</span>天<span>%{h}</span>小时<span>%{m}</span>分<span>%{s}</span>秒'
		});
	});
</script>