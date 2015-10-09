<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta property="qc:admins" content="547534654110117156375" />
	<meta property="wb:webmaster" content="fc4c7317eca14b12" />
	<title>首页--小雨在线教育</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/web/index.js"></script>
	<script type="text/javascript">
		function getSubjectTeacher(subjectId,majorId,showId){
			showId=showId+majorId;
			if(majorId!=null&&majorId!=''){
				$.ajax({
					url:'/front/ajax/getTeacherBySubjectMajor',
					type:'post',
					dataType:'text',
					data:{"subjectId":subjectId,"majorId":majorId},
					async:false,
					success:function(result){
						$("#"+showId).html(result);
					}
				});
			}
		};
	</script>
</head>
<body>
	<!-- /banner begin -->
<section class="sliderKbWrap">
		<div class="sliderBox pr">
			<section id="hotSlide" class="hotSlideWrap">
				<div class="hotSlide">
					<c:forEach var="image" items="${websiteImages.indexCenterBanner}">
					<div class="hotpic">
						<li <c:if test="${status.index==0}">class='oShow'</c:if>>
							<a  target="_blank" title="${image.title}" href="<c:if test='${image.linkAddress!=null&&image.linkAddress!=""}'>${image.linkAddress}</c:if>" name="${image.color}"><img src="<%=staticImageServer%>${image.imagesUrl}" /></a>
						</li>
					</div>
					</c:forEach>
				</div>
				<div class="container r-notice-box-warp pa">
					<div class="r-notice-box">
						<div class="r-notice-box-tit">
							<ul id="lr-title">
								<li class="current"><a href="javascript: void(0);">平台公告</a></li>
								<li><a href="javascript: void(0);">最新新闻</a></li>
							</ul>
						</div>
						<div class="r-notice-box-boy" id="lr-cont">
							<section>
								<ul class="r-notice-box-boy-list">
									<c:forEach items="${noticeList}" var="art">
									<li>
										<a href="/front/toArticle/${art.id}" target="_blank" title="${art.title}" class="c-fff fsize12 f-fM txtOf">
											<em class="icon-dian vam mr5">&nbsp;</em>
											<tt class="c-fff vam fsize12 f-fM">${art.title}</tt>
										</a>
									</li>
									</c:forEach>
								</ul>
							</section>
							<section>
								<ul class="r-notice-box-boy-list">
									<c:forEach items="${articleList}" var="art">
									<li>
										<a href="/front/toArticle/${art.id}" target="_blank" title="${art.title}" class="c-fff fsize12 f-fM txtOf">
											<em class="icon-dian vam mr5">&nbsp;</em>
											<tt class="c-fff vam fsize12 f-fM">${art.title}</tt>
										</a>
									</li>
									</c:forEach>
								</ul>
							</section>
						</div>
					</div>
				</div>
				<a target="_self" class="prev" href="javascript:void(0)">Prev</a>
				<a target="_self" class="next" href="javascript:void(0)">next</a>
			</section>
		</div>
		<!-- sliderBox -->
		<div class="clear"></div>
	</section>
	<!-- slider kb -->
	<!-- banner over -->
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<div class="index-nubmer">
				<section class="container">
					<div class="pl50 pr50"> 
						<div class="regnum clearfix fl">
							<span>超过</span> 
							<span class="num-wrap">
								<em id="numberL" class="numAinmate"></em>
							</span> 
							<span>名老师加入小雨在线</span>
						</div>
						<div class="regnum clearfix fr">
							<span>超过</span> 
							<span class="num-wrap">
								<em id="numberR" class="numAinmate"></em>
							</span> 
							<span>位家长找到好老师</span>
						</div>
						<div class="clear"></div>
					</div>
				</section>
			</div>
			<div>
				<section class="container">
					<header class="comm-title">
						<h2 class="tac pr">
							<span class="c-master">推荐老师</span>
							<a href="javascript:void(0)" id="recomandChange" onclick="randRecommandTeacher()" class="fr dis more">
								<em class="icon18 icon-more vam"></em>
								<tt class="fsize14 c-master vam f-fM">换一换</tt>
							</a>
						</h2>	
					</header>
					<div>
						<article class="i-teacher-list">
							<ul class="clearfix" id="recomandTea">
								<c:forEach items="${recommand}" var="tea" begin="0" end="3">
								<li>
									<section class="i-teach-wrap">
										<div class="i-teach-pic">
											<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
												<c:choose>
													<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer %>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
													<c:otherwise>
														<c:if test="${tea.sex == 0}">
															<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
														</c:if>
														<c:if test="${tea.sex == 1}">
															<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
														</c:if>
													</c:otherwise>
												</c:choose>
											</a>
										</div>
										<div class="mt10 hLh30 txtOf tac">
											<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
										</div>
										<div class="hLh30 txtOf tac">
											<span class="fsize14 c-999">${tea.profession}</span>
										</div>
										<div class="mt15 i-q-txt">
											<p class="c-999 f-fA hLh30 txtOf">辅导年级：
												${tea.subjects}
											</p>
											<p class="c-999 f-fA hLh30 txtOf">辅导科目：
												${tea.majors}
											</p>
										</div>
										<div class="i-teach-hover">
											<p class="c-666 f-fA fsize14">
												${tea.career}
											</p>
											<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
										</div>
									</section>
								</li>
								</c:forEach>
							</ul>
						</article>
					</div>
				</section>
			</div>
			<!-- 推荐老师over -->
			<div class="Grey-bg">
				<section class="container">
					<article class="comm-title" style="margin-bottom:0;">
						<div class="fl">
							<div class="c-tit-heard">
								<span class="big">高中/</span>
								<span class="small">High school</span>
							</div>
							<div class="c-tit-body">
								<img src="${ctx}/static/edu/img/pic/gz-bg.jpg" alt="">
							</div>
						</div>
						<div class="fl c-in-list-higbox">
							<div class="c-in-list-tit pr">
								<ul class="c-tit-name clearfix">
									<c:forEach items="${subjectList[2].majorList}" var="major" varStatus="statu">
										<li <c:if test="${statu.first}">class="current"</c:if> id="${major.majorid}">
											<a href="javascript:void(0)" onmouseover="getSubjectTeacher(${subjectList[2].subjectId},${major.majorid},'gaozhong')">${major.majorName}</a>
										</li>
									</c:forEach>
								</ul>
								<a href="javascript:void(0)" class="fr dis more pa" onclick="randomTeacher(${subjectList[2].subjectId},this,'gaozhong')">
									<em class="icon18 icon-more vam"></em>
									<tt class="fsize14 c-master vam f-fM">换一换</tt>
								</a>
							</div>
							<div class="c-in-list-body pb50">
								<article class="i-teacher-list c-in-list-body-infor pr">
									<c:forEach items="${subjectList[2].majorList}" var="major" varStatus="statu">
									<ul id="gaozhong${major.majorid}" class="clearfix" style='margin-left:0;<c:if test="${!statu.first}">display: none;</c:if>' >
										<c:forEach items="${teacherList.teacher_233}" var="tea">
										<li>
											<section class="i-teach-wrap">
												<div class="i-teach-pic">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
														<c:choose>
															<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer %>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
															<c:otherwise>
																<c:if test="${tea.sex == 0}">
																	<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																</c:if>
																<c:if test="${tea.sex == 1}">
																	<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																</c:if>
															</c:otherwise>
														</c:choose>
													</a>
												</div>
												<div class="mt10 hLh30 txtOf tac">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
													<span class="c-999 fsize12 f-fM ml10">教龄：${tea.seniority}年</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">课时费：</tt>
														<tt class="c-master f-fM fsize20 vam ml10 pr">
															<em class="icon12 icon-Money pa">&nbsp;</em>
															${tea.lowPrice}
														</tt>
														<tt class="c-999 f-fM fsize12 vam">/小时</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">科目：</tt>
														<tt class="c-999 f-fM fsize12 vam">
															${tea.majors}
														</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">评论数：</tt>
														<tt class="c-master f-fM fsize12 vam">${tea.assessNum}</tt>
													</span>
												</div>
												<div class="i-teach-hover">
													<p class="c-666 f-fA fsize14">
														${tea.career}
													</p>
													<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
												</div>
											</section>
										</li>
										</c:forEach>
										
									</ul>
									</c:forEach>
								</article>
							</div>
							<div class="clear"></div>
						</div>	
					</article>
				</section>
			</div>
			
			<!-- 高中over -->
			<div class="">
				<section class="container">
					<article class="comm-title" style="margin-bottom:0;">
						<div class="fl">
							<div class="c-tit-heard">
								<span class="big">初中/</span>
								<span class="small">Middle school</span>
							</div>
							<div class="c-tit-body">
								<img src="${ctx}/static/edu/img/pic/cz-bg.jpg" alt="">
							</div>
						</div>
						<div class="fl c-in-list-higbox">
							<div class="c-in-list-tit pr">
								<ul class="c-tit-name clearfix">
									<c:forEach items="${subjectList[1].majorList}" var="major" varStatus="statu">
										<li <c:if test="${statu.first}">class="current"</c:if> id="${major.majorid}">
											<a href="javascript:void(0)"  onmouseover="getSubjectTeacher(${subjectList[1].subjectId},${major.majorid},'chuzhong')">${major.majorName}</a>
										</li>
									</c:forEach>
								</ul>
								<a href="JavaScript:void(0)" class="fr dis more pa" onclick="randomTeacher(${subjectList[1].subjectId},this,'chuzhong')">
									<em class="icon18 icon-more vam"></em>
									<tt class="fsize14 c-master vam f-fM">换一换</tt>
								</a>
							</div>
								<div class="c-in-list-body pb50">
								<article class="i-teacher-list c-in-list-body-infor pr">
									<c:forEach items="${subjectList[1].majorList}" var="major" varStatus="statu">
									<ul id="chuzhong${major.majorid}" class="clearfix" style='margin-left:0;<c:if test="${!statu.first}">display: none;</c:if>' >
										<c:forEach items="${teacherList.teacher_210}" var="tea">
										<li>
											<section class="i-teach-wrap">
												<div class="i-teach-pic">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
														<c:choose>
															<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer %>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
															<c:otherwise>
																<c:if test="${tea.sex == 0}">
																	<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																</c:if>
																<c:if test="${tea.sex == 1}">
																	<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																</c:if>
															</c:otherwise>
														</c:choose>
													</a>
												</div>
												<div class="mt10 hLh30 txtOf tac">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
													<span class="c-999 fsize12 f-fM ml10">教龄：${tea.seniority}年</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">课时费：</tt>
														<tt class="c-master f-fM fsize20 vam ml10 pr">
															<em class="icon12 icon-Money pa">&nbsp;</em>
															${tea.lowPrice}
														</tt>
														<tt class="c-999 f-fM fsize12 vam">/小时</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">科目：</tt>
														<tt class="c-999 f-fM fsize12 vam">
															${tea.majors}
														</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">评论数：</tt>
														<tt class="c-master f-fM fsize12 vam">${tea.assessNum}</tt>
													</span>
												</div>
												<div class="i-teach-hover">
													<p class="c-666 f-fA fsize14">
														${tea.career}
													</p>
													<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
												</div>
											</section>
										</li>
										</c:forEach>
									</ul>
									</c:forEach>
								</article>
							</div>
							<div class="clear"></div>
						</div>	
					</article>
				</section>
			</div>
			<!-- 初中over -->
			<div class="Grey-bg">
				<section class="container">
					<article class="comm-title" style="margin-bottom:0;">
						<div class="fl">
							<div class="c-tit-heard">
								<span class="big">小学/</span>
								<span class="small">Primary school</span>
							</div>
							<div class="c-tit-body">
								<img src="${ctx}/static/edu/img/pic/xx-bg.jpg" alt="">
							</div>
						</div>
						<div class="fl c-in-list-higbox">
							<div class="c-in-list-tit pr">
								<ul class="c-tit-name clearfix">
									<c:forEach items="${subjectList[0].majorList}" var="major" varStatus="statu">
										<li <c:if test="${statu.first}">class="current"</c:if> id="${major.majorid}">
											<a href="javascript:void(0)"  onmouseover="getSubjectTeacher(${subjectList[0].subjectId},${major.majorid},'xiaoxue')">${major.majorName}</a>
										</li>
									</c:forEach>
								</ul>
								<a href="javascript:void(0)" class="fr dis more pa" onclick="randomTeacher(${subjectList[0].subjectId},this,'xiaoxue')">
									<em class="icon18 icon-more vam"></em>
									<tt class="fsize14 c-master vam f-fM">换一换</tt>
								</a>
							</div>
								<div class="c-in-list-body pb50">
								<article class="i-teacher-list c-in-list-body-infor pr">
									<c:forEach items="${subjectList[0].majorList}" var="major" varStatus="statu">
									<ul id="xiaoxue${major.majorid}" class="clearfix" style='margin-left:0;<c:if test="${!statu.first}">display: none;</c:if>' >
										<c:forEach items="${teacherList.teacher_208}" var="tea">
										<li>
											<section class="i-teach-wrap">
												<div class="i-teach-pic">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
														<c:choose>
															<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer %>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
															<c:otherwise>
																<c:if test="${tea.sex == 0}">
																	<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																</c:if>
																<c:if test="${tea.sex == 1}">
																	<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																</c:if>
															</c:otherwise>
														</c:choose>
													</a>
												</div>
												<div class="mt10 hLh30 txtOf tac">
													<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
													<span class="c-999 fsize12 f-fM ml10">教龄：${tea.seniority}年</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">课时费：</tt>
														<tt class="c-master f-fM fsize20 vam ml10 pr">
															<em class="icon12 icon-Money pa">&nbsp;</em>
															${tea.lowPrice}
														</tt>
														<tt class="c-999 f-fM fsize12 vam">/小时</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">科目：</tt>
														<tt class="c-999 f-fM fsize12 vam">
															${tea.majors}
														</tt>
													</span>
												</div>
												<div class="mt10 hLh30 txtOf pl60">
													<span>
														<tt class="c-999 f-fM fsize12 vam">评论数：</tt>
														<tt class="c-master f-fM fsize12 vam">${tea.assessNum}</tt>
													</span>
												</div>
												<div class="i-teach-hover">
													<p class="c-666 f-fA fsize14">
														${tea.career}
													</p>
													<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
												</div>
											</section>
										</li>
										</c:forEach>
										
									</ul>
									</c:forEach>
								</article>
							</div>
							<div class="clear"></div>
						</div>	
					</article>
				</section>
			</div>
			<!-- 小学over -->
			<div>
				<section class="container">
					<header class="comm-title">
						<h2 class="tac">
							<span class="c-master">最新入驻</span>
						</h2>	
					</header>
					<section class="sliderKbWrap">
						<div class="sliderBox">
							<section id="hotSlideFun" class="hotSlideWrapFun mb30">
								<div class="hotSlideFun">
									<div class="hotpicFun">
										<article class="i-teacher-list i-new-list">
											<ul class="clearfix">
												<c:forEach items="${newReg}" var="tea" begin="0" end="3">
												<li>
													<section class="i-teach-wrap">
														<div class="i-teach-pic">
															<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
																<c:choose>
																	<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer%>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
																	<c:otherwise>
																		<c:if test="${tea.sex == 0}">
																			<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																		<c:if test="${tea.sex == 1}">
																			<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</a>
														</div>
														<div class="mt10 hLh30 txtOf tac">
															<a href="/front/teacher/${tea.id}" title="${tea.name}" class="fsize18 c-666">${tea.userExpand.showname}</a>
														</div>
														<div class="hLh30 txtOf tac">
															<span class="fsize14 c-999">${tea.profession}</span>
														</div>
														<div class="mt15 i-q-txt">
															<p class="c-999 f-fA hLh30 txtOf">辅导年级：
																${tea.subjects}
															</p>
															<p class="c-999 f-fA hLh30 txtOf">辅导科目：
																${tea.majors}
															</p>
														</div>
														<div class="i-teach-hover">
															<p class="c-666 f-fA fsize14">
																${tea.career}
															</p>
															<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
														</div>
													</section>
												</li>
												</c:forEach>
											</ul>
										</article>
									</div>
									<c:if test="${fn:length(newReg)>4}">
									<div class="hotpicFun">
										<article class="i-teacher-list i-new-list">
											<ul class="clearfix">
												<c:forEach items="${newReg}" var="tea" begin="4" end="7">
												<li>
													<section class="i-teach-wrap">
														<div class="i-teach-pic">
															<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
																<c:choose>
																	<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer%>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
																	<c:otherwise>
																		<c:if test="${tea.sex == 0}">
																			<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																		<c:if test="${tea.sex == 1}">
																			<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</a>
														</div>
														<div class="mt10 hLh30 txtOf tac">
															<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
														</div>
														<div class="hLh30 txtOf tac">
															<span class="fsize14 c-999">${tea.profession}</span>
														</div>
														<div class="mt15 i-q-txt">
															<p class="c-999 f-fA hLh30 txtOf">辅导年级：
																${tea.subjects}
															</p>
															<p class="c-999 f-fA hLh30 txtOf">辅导科目：
																${tea.majors}
															</p>
														</div>
														<div class="i-teach-hover">
															<p class="c-666 f-fA fsize14">
																${tea.career}
															</p>
															<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
														</div>
													</section>
												</li>
												</c:forEach>
											</ul>
										</article>
									</div>
									</c:if>
									<c:if test="${fn:length(newReg)>8}">
									<div class="hotpicFun">
										<article class="i-teacher-list i-new-list">
											<ul class="clearfix">
												<c:forEach items="${newReg}" var="tea" begin="8" end="11">
												<li>
													<section class="i-teach-wrap">
														<div class="i-teach-pic">
															<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}">
																<c:choose>
																	<c:when test="${!empty tea.userExpand.headPhoto}"><img alt="${tea.userExpand.showname}" src="<%=staticImageServer%>${tea.userExpand.headPhoto}" xsrc="img/list-bg.gif"></c:when>
																	<c:otherwise>
																		<c:if test="${tea.sex == 0}">
																			<img src="${ctx}/static/edu/images/page/tea-nan.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																		<c:if test="${tea.sex == 1}">
																			<img src="${ctx}/static/edu/images/page/tea-nv.jpg" alt="${tea.userExpand.showname}">
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</a>
														</div>
														<div class="mt10 hLh30 txtOf tac">
															<a href="/front/teacher/${tea.id}" title="${tea.userExpand.showname}" class="fsize18 c-666">${tea.userExpand.showname}</a>
														</div>
														<div class="hLh30 txtOf tac">
															<span class="fsize14 c-999">${tea.profession}</span>
														</div>
														<div class="mt15 i-q-txt">
															<p class="c-999 f-fA hLh30 txtOf">辅导年级：
																${tea.subjects}
															</p>
															<p class="c-999 f-fA hLh30 txtOf">辅导科目：
																${tea.majors}
															</p>
														</div>
														<div class="i-teach-hover">
															<p class="c-666 f-fA fsize14">
																${tea.career}
															</p>
															<a href="/front/teacher/${tea.id}" class="c-btn c-btn-2">查看详情</a>
														</div>
													</section>
												</li>
												</c:forEach>
											</ul>
											
										</article>
									</div>
									</c:if>
								</div>
								<a target="_self" class="prev" href="javascript:void(0)">Prev</a>
								<a target="_self" class="next" href="javascript:void(0)">next</a>
							</section>
						</div>
						<!-- sliderBox -->
						<div class="clear"></div>
					</section>
				</section>
			</div>
			<!-- 最新入驻over -->
			<div class="Grey-bg">
				<section class="container">
					<article class="fl w50pre">
						<div class="mr45 mb10">
							<header class="comm-title" style="margin-bottom:0;">
								<h2 class="fl tac">
									<span class="c-master">家长评价</span>
								</h2>
							</header>
							<div class="i-question-list">
								<ul id="iQuestion">	
									<c:forEach items="${assessList}" var="assess">
									<li style="margin-top: 0px;">
										<section class="i-q-l-wrap">
											<div class="u-face">
												<c:choose>
													<c:when test="${!empty assess.avatar}"><img width="50" height="50" alt="${assess.nickname}" src="<%=staticImageServer%>${assess.avatar}"></c:when>
													<c:otherwise><img width="50" height="50" alt="${assess.nickname}" src="${ctx}/static/edu/img/headPhoto-boy.gif"></c:otherwise>
												</c:choose>
											</div>
											<div class="u-txt-index">
												<em class="u-t-in">
													&nbsp;
												</em>
												<section class="hLh20 of">
													<span class="fr"><tt class="c-ccc f-fG"><fmt:formatDate value="${assess.createTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></tt></span>
													<span class="fsize16 c-666">
														<c:choose>
															<c:when test="${!empty assess.nickname}">${assess.nickname}</c:when>
															<c:otherwise>${assess.email}</c:otherwise>
														</c:choose>
													</span>
													<span class="fsize14 c-999 ml5 f-fA ">评论${assess.courseName}：</span>
												</section>
												<section class="i-q-txt i-q-txt-1">
													<p>
														<a href="javascript:void(0)" title="${assess.content}">
														<span class="c-999 f-fA">${assess.content}</span>
														</a>
													</p>
												</section>
											</div>
										</section>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</article>
					<article class="fl w50pre">
						<div class="ml45 mb10">
							<header class="comm-title" style="margin-bottom:0;">
								<h2 class="fl tac">
									<span class="c-master">购课动态</span>
								</h2>
							</header>
							<div class="i-question-list i-question-list-index">
								<em class="icon-34 icon-dh-top"></em>
								<em class="icon-34 icon-dh-bottom"></em>
								<div id="i-q-in-box" class="pr">
									<div class="i-q-in-box-1" id="i-q-in-box-1">
										<ul>
											<c:forEach items="${orderList}" var="order">
											<li>
												<a href="${ctx}/front/teacher/${order.teacherId}" title='${order.studentName}${order.mobile}）向${order.teacherName}老师购买${order.courseName}课程<c:if test="${order.lessionNum>0}">${order.lessionNum}个课时</c:if>' class="txtOf">
													<tt class="c-master fsize14 f-fM">${order.studentName}</tt>
													<tt class="c-999 fsize14 f-fM">（${order.mobile}）向</tt>
													<tt class="c-master fsize14 f-fM">${order.teacherName}老师</tt>
													<tt class="c-999 fsize14 f-fM">购买${order.courseName}课程
														<c:if test="${order.lessionNum>0}">
															${order.lessionNum}个课时
														</c:if>
													</tt>
												</a>
											</li>
											</c:forEach>
										</ul>
									</div>
									<div class="i-q-in-box-1" id="i-q-in-box-2"></div>
								</div>
							</div>
						</div>
					</article>
					<div class="clear"></div>
				</section>
			</div>
			<!-- 家长评价over -->
			<div>
				<section class="container">
					<header class="comm-title">
						<h2 class="tac">
							<span class="c-master">平台特色</span>
						</h2>	
					</header>
					<article class="i-teacher-list pt20">
						<ul class="clearfix">
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/yx.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt10">
										<span class="c-333 fsize24 f-fH tac dis hLh30">1、有效</span>
										<p class="fsize14 c-999 f-fA mt10">随时随地的在线试听，老师好不好？一听便知道。</p>
									</div>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/fb.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt10">
										<span class="c-333 fsize24 f-fH tac dis hLh30">2、方便</span>
										<p class="fsize14 c-999 f-fA mt10">学生、教师不用出门，在家里就可以通过网络实现课程辅导</p>
									</div>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/tx.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt10">
										<span class="c-333 fsize24 f-fH tac dis hLh30">3、贴心</span>
										<p class="fsize14 c-999 f-fA mt10">贴身“信息保姆”，开课前一天和前半个小时短信、邮件</p>
									</div>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/sq.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt10">
										<span class="c-333 fsize24 f-fH tac dis hLh30">4、省钱</span>
										<p class="fsize14 c-999 f-fA mt10">实行最高限价，去掉中间环节，费用可以节省30%—50%</p>
									</div>
								</section>
							</li>
						</ul>
					</article>
				</section>
			</div>
			<!-- 平台特色over -->
			<div>
				<section class="container">
					<header class="comm-title">
						<h2 class="tac">
							<span class="c-master">平台流程</span>
						</h2>	
					</header>
					<article class="i-teacher-list pt20">
						<ul class="clearfix ptlc-list">
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/ss.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt20">
										<span class="c-master fsize24 f-fH tac dis hLh30">搜索老师</span>
									</div>
									<em class="icon-34 icon-right">&nbsp;</em>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/gmkc.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt20">
										<span class="c-master fsize24 f-fH tac dis hLh30">购买课程</span>
									</div>
									<em class="icon-34 icon-right">&nbsp;</em>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/yysk.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt20">
										<span class="c-master fsize24 f-fH tac dis hLh30">约课上课</span>
									</div>
									<em class="icon-34 icon-right">&nbsp;</em>
								</section>
							</li>
							<li>
								<section class="i-Platform-wrap">
									<div class="tac">
										<img src="${ctx}/static/edu/img/kcpj.png" alt="">
									</div>
									<div class="i-p-wrap-txt mt20">
										<span class="c-master fsize24 f-fH tac dis hLh30">课程评价</span>
									</div>
								</section>
							</li>
						</ul>
					</article>
				</section>
			</div>
			<!-- 平台流程over -->
			<div>
				<section class="container">
					<header class="comm-title">
						<h2 class="tac pr">
							<span class="c-master">入驻机构</span>
							<a class="fr dis more" href="${ctx }/front/institutionList">
								<tt class="fsize14 c-master vam f-fM">更多>></tt>
							</a>
						</h2>	
					</header>
					<section class="sliderKbWrap">
						<div class="sliderBox">
							<section id="hotSlideFunjg" class="hotSlideWrapFunjg mb30">
								<div class="hotSlideFunjg">
									<c:if test="${not empty institutionList and institutionList.size()>0 }">
										<div class="hotpicFunjg">
											<article class="i-teacher-list i-new-list rzjg-in-list">
												<ul class="clearfix">
													<c:forEach var="inst" items="${institutionList }" begin="0" end="4"> 
														<li>
															<section class="rzjg-in-wrap">
																<a href="javascript:void(0)">
																	<img src="<%=staticImageServer %>${inst.instPictureUrl }" title="${inst.name }">
																</a>
															</section>
														</li>
													</c:forEach>
												</ul>
											</article>
										</div>
									</c:if>
									<c:if test="${not empty institutionList and institutionList.size()>5 }">
										<div class="hotpicFunjg">
											<article class="i-teacher-list i-new-list rzjg-in-list">
												<ul class="clearfix">
													<c:forEach var="inst" items="${institutionList }" begin="5" end="9"> 
														<li>
															<section class="rzjg-in-wrap">
																<a href="javascript:void(0)">
																	<img src="<%=staticImageServer %>${inst.instPictureUrl }" title="${inst.name }">
																</a>
															</section>
														</li>
													</c:forEach>
												</ul>
											</article>
										</div>
									</c:if>
									<c:if test="${not empty institutionList and institutionList.size()>10 }">
										<div class="hotpicFunjg">
											<article class="i-teacher-list i-new-list rzjg-in-list">
												<ul class="clearfix">
													<c:forEach var="inst" items="${institutionList }" begin="10" end="14"> 
														<li>
															<section class="rzjg-in-wrap">
																<a href="javascript:void(0)">
																	<img src="<%=staticImageServer %>${inst.instPictureUrl }" title="${inst.name }">
																</a>
															</section>
														</li>
													</c:forEach>
												</ul>
											</article>
										</div>
									</c:if>
								</div>
								<a target="_self" class="prev" href="javascript:void(0)">Prev</a>
								<a target="_self" class="next" href="javascript:void(0)">next</a>
							</section>
						</div>
						<!-- sliderBox -->
						<div class="clear"></div>
					</section>
					<div class="pt30 pb50 rzjg-btn tac">
						<a href="javascript:addInst()">我 要 入 驻</a>
					</div>
				</section>
			</div>
			<!-- 入驻机构over -->
		</div>
	</section>
	<!-- main end -->
	<script src="${ctx}/static/edu/js/web/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/tangram-min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/fx.js" type="text/javascript"></script>
		<script type="text/javascript">
		var numShow = function(op,ot,date,total) {
			function show(val, val2,number) {
				for (var i = 0; i < number;i++) {
					$(op).append("<span>0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9</span>");
				}
				var numbers = T.dom.children(T.dom.g(ot));
					for (var i = number-1; i >= 0; i--) {
						var m = val % 10;
						var m2 = val2 % 10;
						numbers[i].style.top = (-m * 40) + 'px';
						val = Math.floor(val / 10);
						val2 = Math.floor(val2 / 10);
						baidu.fx.moveTo(numbers[i], {y: -m2 * 40}, {duration: 1000});
					}
			}
						//用户数滚动开并
			var numberOffsetTop = $(op).offset().top,
				isScroll = true;
			if ($(document).scrollTop() > numberOffsetTop - $(window).height()) {
				show('6037618', date, total); //大尺寸屏幕可见直接执行动画；
			} else {
				//小尺寸屏幕滚动到屏幕可见区域再执行动画；
				$(window).bind("scroll", function() {
					if (isScroll) {
						if ($(document).scrollTop() > numberOffsetTop - $(window).height()) {
							show('6037618', date, total);
							isScroll = false;
						};
					};
				})
			};
		}
		$(function() {
			numShow('#numberL','numberL','${indexTotal.allTeacherCount}','7');//前两个参数不需要动 第三个是统计数字  第四个是统计数位
			numShow('#numberR','numberR','${indexTotal.allStudentCount}','6');
			upSlideFun("#iQuestion"); //向上滚动互动
			goTop();//右侧悬浮
			gtFun()
			cardChange("#lr-title>li" , "#lr-cont>section" , "current");//登录注册切换
			$(".c-tit-name li").hover(function() {
				$(this).addClass('current').siblings().removeClass('current');
				$(this).parent().parent().siblings(0).find("ul").eq($(this).index()).show().siblings().hide();
			});
		});
		var marquestFun = function() {
			var speed=100;
			var m1=document.getElementById("i-q-in-box-1");
			var m2=document.getElementById("i-q-in-box-2");
			var m=document.getElementById("i-q-in-box");
			m2.innerHTML=m1.innerHTML; //克隆colee1为colee2
			function Marquee1(){
				m.scrollTop++;
			//当滚动至colee1与colee2交界时
				if(m.scrollTop==m1.offsetHeight){
						m.scrollTop=0;
				}
			}
			var MyMar1=setInterval(Marquee1,speed)//设置定时器
			//鼠标移上时清除定时器达到滚动停止的目的
			m.onmouseover=function() {clearInterval(MyMar1)}
			//鼠标移开时重设定时器
			m.onmouseout=function(){MyMar1=setInterval(Marquee1,speed)}
		}
		marquestFun();
	</script>
</body>
</html>