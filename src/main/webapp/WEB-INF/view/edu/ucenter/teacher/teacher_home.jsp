<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
	<script src="${ctximg}/static/edu/js/ucenter/teacher/u_teacher.js" type="text/javascript"></script>
<script>
var entity;
$(function(){
	entity = eval("(" + '${teacher}' + ")");
	finishShow();
})
</script>
</head>
<body>
<div class="u-m-right">
	<article class="u-m-center" style="padding-top:0;">
		<section class="u-m-c-wrap">
			<section class="u-t-home-tit mt50">
				<div class="home-l">
					<div class="btn mt10">
						<div class="btn-l mt5">
							<tt class="fsize14 c-999 f-fM">账户资料完整度：</tt>
							<tt class="fsize18 c-master f-fM" id="integrities">0%</tt>
							<tt class="fsize12 c-999 f-fM ml50" id="explain">您现在不能被学生搜到，因为缺少以下资料</tt>
						</div>
						<p class="u-s-jdt mt20">
							<em style="width:50%;" class="u-s-jdt-bg"></em>
						</p>
					</div>
					<div class="u-h-list-tit">
						<ul class="clearfix">
							<li>
								<p class="fsize16 f-fM c-666 ml20">基本信息</p>
								<a href="${ctx}/uc/teacher/material/tosetting?materialType=base" class="u-h-t-btn-tx mt15" id="basis_info">
									修&nbsp;&nbsp;&nbsp;&nbsp;改
								</a>
							</li>
							<li>
								<p class="fsize16 f-fM c-666 ml20">高级信息</p>
								<a href="${ctx}/uc/teacher/material/tosetting?materialType=senior" class="u-h-t-btn-tx mt15" id="senior_info">
									修&nbsp;&nbsp;&nbsp;&nbsp;改
								</a>
							</li>
							<li>
								<p class="fsize16 f-fM c-666 ml20">认证信息</p>
								<a href="${ctx}/uc/teacher/aptitude/toattestation" class="u-h-t-btn-tx mt15" id="identification_info">
									修&nbsp;&nbsp;&nbsp;&nbsp;改
								</a>
							</li>
							<li>
								<p class="fsize16 f-fM c-666 ml20">课程信息</p>
								<a href="${ctx}/uc/teacher/ontToOne/list" class="u-h-t-btn-tx mt15" id="course_info">
									开始填写
								</a>
							</li>
							<li>
								<p class="fsize16 f-fM c-666 ml20">地址信息</p>
								<a href="${ctx}/uc/address" class="u-h-t-btn-tx mt15" id="address_info">
									开始填写
								</a>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
			</section>
			<section class="u-t-home-boy mt50">
				<div class="u-t-h-boy-in">
					<div class="u-t-h-boy-in-tit">
						<em class="u-icon-numb vam">&nbsp;</em>
						<tt class="fsize18 c-master f-fM vam">消息提醒</tt>
					</div>
				</div>
				<div class="u-t-h-boy-infor">
					<ul class="clearfix u-t-h-boy-infor-list">
						<li>
							<div class="tac mt40">
								<em class="u-icon-kb">&nbsp;</em>
							</div>
							<p class="hLh36 tac fsize24 f-fM c-666 mt5">课表</p>
							<p class="hLh20 tac fsize14 f-fM c-999 mt5">${msgReceive.courseMsgNum}个约课</p>
							<a href="${ctx}/uc/letter?msgReceive.type=2&page.currentPage=1" class="btn mt10">立即查看</a>
						</li>
						<li>
							<div class="tac mt40">
								<em class="u-icon-dd">&nbsp;</em>
							</div>
							<p class="hLh36 tac fsize24 f-fM c-666 mt5">订单</p>
							<p class="hLh20 tac fsize14 f-fM c-999 mt5">${msgReceive.orderMsgNum}条订单信息</p>
							<a href="${ctx}/uc/letter?msgReceive.type=3&page.currentPage=1" class="btn mt10">立即查看</a>
						</li>
						<li>
							<div class="tac mt40">
								<em class="u-icon-xy">&nbsp;</em>
							</div>
							<p class="hLh36 tac fsize24 f-fM c-666 mt5">信用</p>
							<p class="hLh20 tac fsize14 f-fM c-999 mt5">
								<c:if test="${msgReceive.assessMsgNum != null && msgReceive.assessMsgNum != 0}">${msgReceive.assessMsgNum}条评价信息</c:if>
								<c:if test="${msgReceive.assessMsgNum == null || msgReceive.assessMsgNum == 0}">暂无评价</c:if>
							</p>
							<a href="${ctx}/uc/letter?msgReceive.type=4&page.currentPage=1" class="btn mt10">立即查看</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</section>
			<section class="u-t-home-boy mt50 pb50">
				<div class="u-t-h-boy-in">
					<div class="u-t-h-boy-in-tit">
						<em class="u-icon-jrkc vam">&nbsp;</em>
						<tt class="fsize18 c-master f-fM vam">今日课程</tt>
					</div>
				</div>
				<div class="u-t-h-boy-infor">
					<c:if test="${empty detailList }">
						<div class="u-T-body-in mt30">
							<div class="u-T-body-infor">
								<p class="c-master fsize24 f-fM tac">您今天没有课程安排</p>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty detailList }">
					<ul class="u-t-h-boy-list">
						<c:forEach var="detail" items="${detailList }">
						<li>
							<div class="u-t-h-boy-list-tit">
										<span class="fl">
											<tt class="fszie12 c-333 f-fM vam ml30"><fmt:formatDate value="${detail.startTime }" pattern="yyyy-MM-dd"/></tt>
											<tt class="fszie12 c-333 f-fM vam ml50">约课编号：${100000000+detail.id }</tt>
										</span>
										<span class="fr">
											<tt class="fszie12 c-333 f-fM vam mr50">学生：${detail.studentName }</tt>
											<em class="icon-u-dh vam icon18">&nbsp;</em>
											<tt class="fszie12 c-master f-fM vam mr30">${detail.studentMobile }</tt>
										</span>
							</div>
							<div class="u-t-h-boy-list-boy pt30">
								<span>
									<p class="hLh20 tac">
										<tt class="fsize18 c-333 f-fM">
											${detail.courseName }
										</tt>
									</p>
									<p class="hLh20 tac mt10">
										<tt class="fsize14 c-999 f-fM mr30">
											<c:if test="${detail.courseType==1 }">一对一</c:if>
											<c:if test="${detail.courseType==2 }">班课</c:if>
										</tt>
										<tt class="fsize14 c-999 f-fM">
											<c:choose>
												<c:when test="${detail.courseModel=='TEACHERVISIT' }">老师上门</c:when> 
												<c:when test="${detail.courseModel=='STUDENTVISIT' }">学生上门</c:when> 
									 			<c:when test="${detail.courseModel=='TALKADDRESS' }">协商地点</c:when> 
												<c:when test="${detail.courseModel=='ONLINEOTO' }">在线教学</c:when> 
												<c:when test="${detail.courseModel=='ONLINECOU' }">在线授课</c:when> 
												<c:when test="${detail.courseModel=='LINEDOWNCOU' }">线下面授</c:when> 
											</c:choose>
										</tt>
									</p>
								</span>
								<span>
									<p class="hLh20 tac">
										<tt class="fsize16 c-999 f-fM">
											<fmt:formatDate value="${detail.startTime }" pattern="yyyy-MM-dd"/>
										</tt>
									</p>
									<p class="hLh20 tac mt10">
										<tt class="fsize14 c-999 f-fM">
											<fmt:formatDate value="${detail.startTime }" pattern="HH:mm"/>-<fmt:formatDate value="${detail.endTime }" pattern="HH:mm"/>
										</tt>
									</p>
								</span>
								<span>
									<p class="hLh20 tac">
										<tt class="fsize16 c-666 f-fM">
											待上课
										</tt>
									</p>
								</span>
								<span class="u-teaname-btn">
									<c:choose>
										<c:when test="${detail.courseModel=='ONLINEOTO' or  detail.courseModel=='ONLINECOU' }">
											<a href="" style="display:inline-block;margin-bottom:0;">进入教室</a>
										</c:when>
										<c:otherwise>
											${detail.studyAddress }
										</c:otherwise>
									</c:choose>
								</span>
							</div>
						</li>
						</c:forEach>
					</ul>
					</c:if>
				</div>
			</section>
		</section>
	</article>
</div>
</body>
</html>
