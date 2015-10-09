<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教师</title>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/teacher/teacher.js"></script>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/teacher/audition.js"></script>
<script src="${ctximg}/static/edu/js/web/jquery-1.7.2.min.js" type="text/javascript"></script>

<script src="${ctximg}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
<script src="${ctximg}/static/edu/js/web/tangram-min.js" type="text/javascript"></script>
<script src="${ctximg}/static/edu/js/web/fx.js" type="text/javascript"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!-- /global nav -->
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<section class="container" id="select-1">
				<form id="searchForm" action="${ctx}/front/teacher/query/list" method="post">
					<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
					<input type="hidden" id="hiddenCityId" name="queryTeacher.cityId" value="${queryTeacher.cityId}"/>
					<input type="hidden" id="hiddenTeachername" name="queryTeacher.name" value="${queryStr}"/>
					<input type="hidden" id="subjectId" name="querysubjectId" value="${querysubjectId}"/>
					<input type="hidden" id="hiddenSubjectId" name="queryTeacher.subjectId" value="${queryTeacher.subjectId}"/>
					<input type="hidden" id="hiddenMajorId" name="queryTeacher.majorId" value="${queryTeacher.majorId}"/> 
					<input type="hidden" id="hiddenorder" name="queryTeacher.order" value="${queryTeacher.order}"/>
					<input type="hidden" id="hiddendistrictId" name="queryTeacher.districtId" value="${queryTeacher.districtId}"/>
					<input type="hidden" id="hiddensex" name="queryTeacher.sex" value="${queryTeacher.sex}"/>
					<input type="hidden" id="hiddenlowPrice" name="querylowPrice" value="${querylowPrice}"/>
					<input type="hidden" id="hiddenseniority" name="queryseniority" value="${queryseniority}"/>
					<input type="hidden" id="hiddensellType" name="queryTeacher.sellType" value="${queryTeacher.sellType}"/>
					<input type="hidden" id="hiddencourseModel" name="queryTeacher.courseModel" value="${queryTeacher.courseModel}"/>
					<input type="hidden" id="hiddensuitStudent" name="queryTeacher.suitStudent" value="${queryTeacher.suitStudent}"/>
				 </form>
				<div class="w950">
					<div>
						<section class="">
							<div class="select"> 
							<c:if test="${(querysubjectId != null && querysubjectId != 0) || (queryTeacher.subjectId != null && queryTeacher.subjectId != 0) || (queryTeacher.majorId != null && queryTeacher.majorId != 0) || (queryTeacher.sellType != null && queryTeacher.sellType != 0) || (queryTeacher.courseModel != null && queryTeacher.courseModel != '' && queryTeacher.courseModel != 'all') || (queryTeacher.districtId != null && queryTeacher.districtId != 0) || (queryTeacher.suitStudent != null && queryTeacher.suitStudent != '' && queryTeacher.suitStudent != 'all')}">
								<div class="select-box2"> 
									<div class="select-box2-left"> 
										<p class="btName">已选条件：</p> 
									</div> 
									<div class="select-box2-right">
										<c:if test="${querysubjectId != null && querysubjectId != 0}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="subject_query"></span><span onclick="clickSearch('subject',0)" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.subjectId != null && queryTeacher.subjectId != 0}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="subject_select"></span><span onclick="clickSearch('subject',${querysubjectId})" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.majorId != null && queryTeacher.majorId != 0}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="major_select"></span><span onclick="clickSearch('major',0)" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.sellType != null && queryTeacher.sellType != 0}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="sellType_select"></span><span onclick="clickSearch('sellType',0)" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.courseModel != null && queryTeacher.courseModel != '' && queryTeacher.courseModel != 'all'}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="courseModel_select"></span><span onclick="clickSearch('courseModel','all')" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.districtId != null && queryTeacher.districtId != 0}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="districtId_select"></span><span onclick="clickSearch('district',0)" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<c:if test="${queryTeacher.suitStudent != null && queryTeacher.suitStudent != '' && queryTeacher.suitStudent != 'all'}">
										<ul class="f-list"> 
											<li>
												<a href="javascript:void(0)" title=""><span class="a-text2" id="suitStudent_select"></span><span onclick="clickSearch('suitStudent','all')" class="a-img"></span></a>
											</li> 
										</ul>
										</c:if>
										<div class="select-box2-dele"> 
											<span class="select-unfolt">
												<a href="javascript:void(0);" onclick="clickSearch('clear',0)" title="全部撤销">全部撤销</a>
											</span> 
										</div> 
									</div> 
								</div> 
								</c:if>
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">学习阶段：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid" style=""> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="subject_0" onclick="clickSearch('subject',0,this)">全部</a>
											</li>
											<c:if test="${subjectList != null && subjectList.size() > 0 }">
											<c:forEach items="${subjectList }" var="subject">
												<li>
													<a href="javascript:void(0);" title="${subject.subjectName }" id="subject_${subject.subjectId}" onclick="clickSearch('subject',${subject.subjectId},this)">${subject.subjectName }</a>
												</li>
											</c:forEach>
											</c:if>
										</ul> 
										<div class="select-box2-RT"> 
											<a href="javascript:void(0)" class="select-unfolt suMore">更多↓</a> 
										</div> 
									</div> 
								</div> 
								<div class="select-box3" id="grade" style="display:none"> 
									<div class="select-box2-left"> 
										<p class="btName">学习年级：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid"> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="grade_0" onclick="clickSearch('grade',0,this)">全部</a>
											</li>
											<c:if test="${gradeList != null && gradeList.size() > 0}">
											<c:forEach items="${gradeList }" var="grade">
											<li>
												<a href="javascript:void(0);" title="${grade.subjectName }" id="grade_${grade.subjectId}" onclick="clickSearch('grade',${grade.subjectId },this)">${grade.subjectName }</a>
											</li>
											</c:forEach>
											</c:if>
										</ul> 
										<div class="select-box2-RT"> 
											<a href="javascript:void(0)" class="select-unfolt suMore" style="display: none;">更多↓</a> 
										</div> 
									</div> 
								</div>
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">学习科目：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid"> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="major_0" onclick="clickSearch('major',0,this)">全部</a>
											</li>
											<c:if test="${majorList != null && majorList.size() > 0 }">
											<c:forEach items="${majorList }" var="major">
											<li>
												<a href="javascript:void(0);" title="${major.name }" id="major_${major.id}" onclick="clickSearch('major',${major.id},this)">${major.name }</a>
											</li>
											</c:forEach>
											</c:if>
										</ul> 
										<div class="select-box2-RT"> 
											<a href="javascript:void(0)" class="select-unfolt suMore">更多↓</a>
										</div> 
									</div> 
								</div>
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">学习方式：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid"> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="sellType_0" onclick="clickSearch('sellType',0,this)">全部</a>
											</li>
											<li>
												<a href="javascript:void(0);" title="一对一" id="sellType_1"  onclick="clickSearch('sellType',1,this)">一对一</a>
											</li> 
											<li>
												<a href="javascript:void(0);" title="大班/小班" id="sellType_2"  onclick="clickSearch('sellType',2,this)">大班/小班</a>
											</li>
										</ul> 
										<div class="select-box2-RT undis"> 
											<a href="javascript:void(0)" class="select-unfolt suMore" style="display: none;">更多↓</a>
										</div> 
									</div> 
								</div> 
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">授课形式：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid" id="oneToOne"> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="courseModel_all" onclick="clickSearch('courseModel','all',this)">全部</a>
											</li>
											<li>
												<a href="javascript:void(0);" title="老师上门" id="courseModel_TEACHERVISIT" onclick="clickSearch('courseModel','TEACHERVISIT',this)">老师上门</a>
											</li> 
											<li>
												<a href="javascript:void(0);" title="学生上门" id="courseModel_STUDENTVISIT" onclick="clickSearch('courseModel','STUDENTVISIT',this)">学生上门</a>
											</li>
											<li>
												<a href="javascript:void(0);" title="协商地点" id="courseModel_TALKADDRESS" onclick="clickSearch('courseModel','TALKADDRESS',this)">协商地点</a>
											</li>
											<li>
												<a href="javascript:void(0);" title="在线教学" id="courseModel_ONLINEOTO" onclick="clickSearch('courseModel','ONLINEOTO',this)">在线教学</a>
											</li>
										</ul>
										<ul class="select-box2-mid" id="classCourse" style="display:none"> 
											<li class="current">
												<a href="javascript:void(0);" title="全部" onclick="clickSearch('courseModel','all',this)">全部</a>
											</li>
											<li>
												<a href="javascript:void(0);" title="在线授课" id="courseModel_ONLINECOU" onclick="clickSearch('courseModel','ONLINECOU',this)">在线授课</a>
											</li> 
											<li>
												<a href="javascript:void(0);" title="线下面授" id="courseModel_LINEDOWNCOU" onclick="clickSearch('courseModel','LINEDOWNCOU',this)">线下面授</a>
											</li>
										</ul>		
										<div class="select-box2-RT undis"> 
											<a href="javascript:void(0)" class="select-unfolt suMore" style="display: none;">更多↓</a> 
										</div> 
									</div> 
								</div>
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">上课区域：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid" style=""> 
											<li class="current">
												<a href="javascript:void(0);" id="district_0" title="全部" onclick="clickSearch('district',0,this)">全部</a>
											</li>
											<c:if test="${areaList != null && areaList.size() > 0}">
											<c:forEach items="${areaList }" var="area">
											<li>
												<a href="javascript:void(0);" id="district_${area.id }" title="${area.areaName }" onclick="clickSearch('district',${area.id },this)">${area.areaName }</a>
											</li>
											</c:forEach>
											</c:if>
										</ul> 
										<div class="select-box2-RT"> 
											<a href="javascript:void(0)" class="select-unfolt suMore">更多↓</a> 
										</div> 
									</div> 
								</div> 
								<div class="select-box3"> 
									<div class="select-box2-left"> 
										<p class="btName">适合学员：</p> 
									</div> 
									<div class="select-box2-right"> 
										<ul class="select-box2-mid">
											<li class="current">
												<a href="javascript:void(0);" title="全部" id="suitStudent_all" onclick="clickSearch('suitStudent','all',this)">全部</a>
											</li>  
											<li>
												<a href="javascript:void(0);" title="基础" id="suitStudent_common" onclick="clickSearch('suitStudent','common',this)">基础</a>
											</li> 
											<li>
												<a href="javascript:void(0);" title="优等" id="suitStudent_top" onclick="clickSearch('suitStudent','top',this)">优等</a>
											</li> 
										</ul> 
										<div class="select-box2-RT undis"> 
											<a href="javascript:void(0)" class="select-unfolt suMore" style="display: none;">更多↓</a> 
										</div> 
									</div> 
								</div>   
							</div>
						</section>
						<section class="comm-title-2"> 
							<div class=" pr20"> 
								<section class="fr c-999 comm-title-right">  
									<span class="vam disIb ml20"> 
										<tt class="vam c-666 fsize14">
											<font class="c-master">${page.currentPage}</font>/${page.totalPageSize}
										</tt> 
										<tt class="vam u-d-page ml5"> 
											<a class="shang c-master mr10" title="上一页" href="javascript:void(0)"><</a>
											<a title="下一页" href="javascript:void(0)" class="xia c-666">></a> 
											</tt> 
									</span> 
								</section> 
								<script type="text/javascript">
										var str = "";
										if('${page.currentPage}'>=2){
										str +='goPage(${page.currentPage-1})';
										}else{
											str +='void(0)';
										}
										$(".shang").attr("href","javascript:"+str);
										str = "";
										if('${page.currentPage+1}'<='${page.totalPageSize}'){
											str+='goPage(${page.currentPage+1})';
										}else{
											str +='void(0)';
										}
										$(".xia").attr("href","javascript:"+str);
								</script> 
								<section class="fl"> 
									<ul class="sub-sort"> 
										<li class="current" id="deft_order">
											<a href="javascript:void(0)" onclick="orderByQuery('deft')">默认排序</a>
										</li> 
										<li class="" id="human_order">
											<a href="javascript:void(0)" onclick="orderByQuery('human')">人气排序</a>
										</li> 
										<li class="" id="assess_order">
											<a href="javascript:void(0)" onclick="orderByQuery('assess')">评价排序</a>
										</li> 
										<li class="" id="price_order">
											<a href="javascript:void(0)" onclick="orderByQuery('price')">价格排序</a>
										</li>  
										<li>
											<div>
												<div class="selectUI">
													<div class="job-select" style="width:155px;">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span id="price_0">价格区间</span>
														</section>
														<section class="j-s-option">
															<div class="j-s-o-box">
																<p style="display:none" id="price0"><a href="javascript: void(0)" title="价格区间" onclick="selectPriceChange('')">价格区间</a></p>
																<p><a href="javascript: void(0)" title="0-100元" id="price_0_100" onclick="selectPriceChange('0_100')">100元以下</a></p>
																<p><a href="javascript: void(0)" title="100-200元" id="price_100_200" onclick="selectPriceChange('100_200')">100-200元</a></p>
																<p><a href="javascript: void(0)" title="200-300元" id="price_200_300" onclick="selectPriceChange('200_300')">200-300元</a></p>
																<p><a href="javascript: void(0)" title="300元以上" id="price_300_5000" onclick="selectPriceChange('300_5000')">300元以上</a></p>
															</div>
														</section>
													</div>
												</div>
												<div class="selectUI">
													<div class="job-select">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span id="seniority_0">教龄</span>
														</section>
														<section class="j-s-option">
															<div class="j-s-o-box">
																<p style="display:none" id="seniority0"><a href="javascript: void(0)" title="教龄" onclick="selectSeniorityChange('')">教龄</a></p>
																<p><a href="javascript: void(0)" title="0-3年" id="seniority_0_3" onclick="selectSeniorityChange('0_3')">0-3年</a></p>
																<p><a href="javascript: void(0)" title="3-5年" id="seniority_3_5" onclick="selectSeniorityChange('3_5')">3-5年</a></p>
																<p><a href="javascript: void(0)" title="5-7年" id="seniority_5_7" onclick="selectSeniorityChange('5_7')">5-7年</a></p>
																<p><a href="javascript: void(0)" title="7年以上" id="seniority_7_70" onclick="selectSeniorityChange('7_70')">7年以上</a></p>
															</div>
														</section>
													</div>
												</div>
												<div class="selectUI">
													<div class="job-select">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span id="sex_-1">性别</span>
														</section>
														<section class="j-s-option">
															<div class="j-s-o-box">
																<p style="display:none" id="sex0"><a href="javascript: void(0)" title="性别" onclick="selectSexChange('-1')">性别</a></p>
																<p><a href="javascript: void(0)" title="男" id="sex_0" onclick="selectSexChange('0')">男</a></p>
																<p><a href="javascript: void(0)" title="女" id="sex_1" onclick="selectSexChange('1')">女</a></p>
															</div>
														</section>
													</div>
												</div>
											</div>
										</li>
									</ul> 
								</section>
								<div class="clear"></div> 
							</div> 
						</section>
					</div>
					<div class="cours-list-box">
						<ul>
							<c:if test="${teacherList != null && teacherList.size() > 0 }">
							<c:forEach items="${teacherList }" var="teacher">
							<li>
								<div class="cou-list-box-in">
									<a href="javascript:void(0)" onclick="window.open('${ctx}/front/teacher/${teacher.id }')" class="teacher-tit fl mr40">
										<c:if test="${empty teacher.userExpand.headPhoto || teacher.userExpand.headPhoto == '' }">
											<c:if test="${teacher.sex == 0}">
												<img src="${ctx}/static/edu/images/page/tea-nan.jpg">
											</c:if>
											<c:if test="${teacher.sex == 1}">
												<img src="${ctx}/static/edu/images/page/tea-nv.jpg">
											</c:if>
										</c:if>
										<c:if test="${teacher.userExpand.headPhoto != null && teacher.userExpand.headPhoto != '' }">
											<img src="<%=staticImageServer%>${teacher.userExpand.headPhoto}" title="${fn:substring(teacher.userExpand.showname, 0, 7) }" >
										</c:if>
									</a>
									<div class="fl teacher-boy">
										<section class="tea-name mt20">
											<a href="javascript:void(0)" onclick="window.open('${ctx}/front/teacher/${teacher.id }')" class="mr20 disIb">
												<tt class="fsize18 c-333 f-fM vam">${fn:substring(teacher.userExpand.showname, 0, 7) }讲师</tt>
												<em class="icon18 icon-${teacher.sex==0?'nan':'nv' } vam">&nbsp;</em>
											</a>
											<span>
												<em class="icon18 icon-bj vam">&nbsp;</em>
												<tt class="fsize18 c-333 f-fM vam">${teacher.cityName }</tt>
											</span>
										</section>
										<section class="mt20"> 
											<span class="fsize12 c-666 f-fM vam mr20">
											主讲：
											<c:if test="${teacher.majors == null || teacher.majors == '' }">暂无主讲科目</c:if>
											<c:if test="${teacher.majors != null && teacher.majors != '' }">
												${fn:substring(fn:replace(teacher.majors, ',', ' '), 0, 8) }
											</c:if>
											</span>
											<span class="fsize12 c-666 f-fM vam mr20">年级：
											<c:if test="${teacher.subjects == null || teacher.subjects == '' }">无</c:if>
											<c:if test="${teacher.subjects != null && teacher.subjects != '' }">
												<span title="${fn:replace(teacher.subjects, ',', ' ')}">${fn:substring(fn:replace(teacher.subjects, ',', ' '), 0, 17) }
												<c:if test="${teacher.subjects.length() > 17}">...</c:if></span>
											</c:if></span>
										</section>
										<section class="mt20"> 
											<span class="fsize12 c-666 f-fM vam mr20">教龄：${teacher.seniority }&nbsp;&nbsp;年</span>
										</section>
										<section class="mt20 tea-gexing fsize12 c-666 f-fM vam ">
											<c:if test="${teacher.teachingEnounce == null || teacher.teachingEnounce == ''}">小雨在线面对面，课程辅导领导者</c:if>
											<c:if test="${teacher.teachingEnounce != null && teacher.teachingEnounce != ''}">教学宣言：${teacher.teachingEnounce}</c:if>
										</section>
									</div>
									<div class="fl">
										<section class="mt40">
											<tt class="c-org f-fM fsize24 vam ml10 pr">
												<em class="icon12 icon-Money pa">&nbsp;</em>
												${teacher.lowPrice == null ? "0.00" : teacher.lowPrice}
											</tt>
											<tt class="fsize12 c-999 f-fM">/小时</tt>
										</section>
										<section class="mt20 pl5">
											<a href="" class="size12 f-fM vam c-999 baom-in">
												${teacher.studentNum }
											</a>
											<span class="fsize12 f-fM vam c-999">人已报名</span>
										</section>
										<section class="mt20 pl5">
											<a href="" class="size12 f-fM vam c-999 pingl-in">
												${teacher.assessNum }
											</a>
											<span class="fsize12 f-fM vam c-999">条评论</span>
										</section>
									</div>
									<div class="fl ml50 pinlun">
										<span> 
											<b title="星级：${teacher.showStar == 0 ? 5 : teacher.showStar}星" class="star-level-1 star-1-${teacher.showStar == 0 ? 5 : teacher.showStar}">&nbsp;</b>
										</span>
										<span class="c-999 fsize14 f-fM mt5">
											授课满意度：${teacher.satisfy == 0 ? 100 : teacher.satisfy}<%-- <fmt:formatNumber value="${teacher.star/5*100 == 0 ? 100 : teacher.star/5*100}" maxFractionDigits="0"/> --%>&nbsp;% 
										</span>
										<span class="mt10" ${teacher.cardStatus !=2 ? "style='display: none'" : ""}>
											<em class="icon24 icon-sk-1 vam">&nbsp;</em>
											<tt class="fsize14 c-master f-fM vam ">身份认证</tt>
										</span>
										<span class="mt10" ${teacher.educationStatus !=2 ? "style='display: none'" : ""}>
											<em class="icon24 icon-sk-2 vam">&nbsp;</em>
											<tt class="fsize14 c-blue f-fM vam ">学历认证</tt>
										</span>
										<span class="mt10" ${teacher.teachingStatus !=2 ? "style='display: none'" : ""}>
											<em class="icon24 icon-sk-3 vam">&nbsp;</em>
											<tt class="fsize14 c-red f-fM vam ">教师证</tt>
										</span>
										<span class="mt10" ${teacher.specialtyStatus !=2 ? "style='display: none'" : ""}>
											<em class="icon24 icon-sk-4 vam">&nbsp;</em>
											<tt class="fsize14 c-zi f-fM vam ">专业资质认证</tt>
										</span>
										<span class="mt10" ${teacher.isProfessor != 2 ? "style='display: none'" : ""}>
											<em class="icon24 icon-sk-5 vam">&nbsp;</em>
											<tt class="fsize14 c-huan f-fM vam ">韩教授认证</tt>
										</span>
									</div>
									<div class="clear"></div>
								</div>
							</li>
							</c:forEach>
							</c:if>
						</ul>
					</div>
					<!-- 引入公共分页 -->
					<section class="mt50">
						<div class="Paging tac">
						<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
						</div>
					</section>
					<c:if test="${teacherList == null || teacherList.size()==0}">
						<div class="u-T-body-in mt30">
							<div class="u-T-body-infor">
								<p class="c-master fsize24 f-fM tac">对不起，此条件下还没有相关教师！</p>
							</div>
						</div>
					</c:if>
				</div>
				<div class="w250">
					<div class="ml50">
						<div class="mt20">
							<section> 
								<h3 class="of a-title unFw lin-1 hLh30"> 
									<font class="c-333 f-fM fsize16">为你定制</font> 
								</h3> 
							</section>
							<div class="account-set">
								<ol>
									<li> 
										<span class="vam a-lab fsize14 f-fM">姓名</span> 
										<label class="a-txt"> 
											<input type="text" value="" class="" maxlength="11" id="auditionName">
											<font color="#00ad69" id=""></font> 
										</label> 
										<div class="clear"></div>
									</li>
									<li> 
										<span class="vam a-lab fsize14 f-fM">手机</span> 
										<label class="a-txt"> 
											<input type="text" value="" class="" maxlength="11"  id="auditionMobile">
											<font color="#00ad69" id=""></font> 
										</label> 
										<div class="clear"></div>
									</li>
									<li> 
										<span class="vam a-lab fsize14 f-fM">阶段</span> 
										<label class="a-txt"> 
											<div class="selectUI">
													<div style="width:155px;z-index:999;" class="job-select">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span>--请选择--</span>
														</section>
														<section class="j-s-option" style="display: none;">
															<div class="j-s-o-box">
															<input type="hidden" id="auditionSubjectId" value="" />
															<c:if test="${subjectList != null && subjectList.size() > 0 }">
																<c:forEach items="${subjectList }" var="subject">										
																		<p><a title="${subject.subjectName }"  onclick="selectSubject(${subject.subjectId})" href="javascript: void(0)">${subject.subjectName }</a></p>
																</c:forEach>
															</c:if>
															</div>
														</section>
													</div>
												</div>
										</label> 
										<div class="clear"></div>
									</li>
									<li> 
										<span class="vam a-lab fsize14 f-fM">年级</span> 
										<label class="a-txt"> 
											<div class="selectUI">
													<div style="width:155px;z-index:990;" class="job-select">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span id="gradeName">--请选择--</span>
														</section>
														<section id="gradeSelect" class="j-s-option" style="display: none;" >
															<input type="hidden"  id="auditionGradeId" value="" />
															<div class="j-s-o-box" id="selectGradeResult">
															</div>
														</section>
													</div>
												</div>
										</label> 
										<div class="clear"></div>
									</li>
									<li> 
										<span class="vam a-lab fsize14 f-fM">科目</span> 
										<label class="a-txt"> 
											<div class="selectUI">
													<div style="width:155px;z-index:980;" class="job-select">
														<section class="j-s-defalt">
															<em class="icon14 fr mt5">&nbsp;</em>
															<span id="majorName">--请选择--</span>
														</section>
														<section id="majorSelect" class="j-s-option" style="display: none;">
															<input type="hidden" id="auditionMajorId" value="" />
															<div class="j-s-o-box" id="selectMajor">
															</div>
														</section>
													</div>
												</div>
										</label> 
										<div class="clear"></div>
									</li>
									<li>
										<a href="javascript:void(0)" onclick="submitAudition()" class="buy-btn dz-btn">
											<font class="c-fff tac">为我定制</font>  
										</a>
									</li>
								</ol>
							</div>
							<div class="mt30">
								<section> 
									<h3 class="of a-title unFw lin-1 hLh30"> 
										<font class="c-333 f-fM fsize16">为你推荐</font> 
									</h3> 
								</section>
								<div class="mt20">
									<ul class="Recommend">
										<c:if test="${mapTeacherList.index_teacher_4 != null && mapTeacherList.index_teacher_4.size() > 0}">
										<c:forEach items="${mapTeacherList.index_teacher_4 }" var="teacher">
										<li>
											<a href="javascript:void(0)" onclick="window.open('${ctx}/front/teacher/${teacher.id }')">
												<c:if test="${empty teacher.userExpand.headPhoto || teacher.userExpand.headPhoto == '' }">
													<c:if test="${teacher.sex == 0}">
														<img src="${ctx}/static/edu/images/page/tea-nan.jpg">
													</c:if>
													<c:if test="${teacher.sex == 1}">
														<img src="${ctx}/static/edu/images/page/tea-nv.jpg">
													</c:if>
												</c:if>
												<c:if test="${teacher.userExpand.headPhoto != null && teacher.userExpand.headPhoto != '' }">
													<img src="<%=staticImageServer%>${teacher.userExpand.headPhoto}" title="${fn:substring(teacher.userExpand.showname, 0, 4) }" >
												</c:if>
											</a>
											<section class="tea-name mt10">
												<a class="disIb" href="javascript:void(0)" onclick="window.open('${ctx}/front/teacher/${teacher.id }')">
													<tt class="fsize14 c-333 f-fM vam">${fn:substring(teacher.userExpand.showname, 0, 4) }</tt>
													<em class="icon18 icon-${teacher.sex==0?'nan':'nv' } vam">&nbsp;</em>
												</a>
												<span>
													<em class="icon18 icon-bj vam">&nbsp;</em>
													<tt class="fsize14 c-333 f-fM vam">${fn:substring(teacher.cityName, 0, 3) }</tt>
												</span>
											</section>
											<section class="mt5"> 
												<span class="fsize12 c-999 f-fM vam mr20">主讲：
													<c:if test="${teacher.majors == null || teacher.majors == '' }">暂无主讲科目</c:if>
													<c:if test="${teacher.majors != null && teacher.majors != '' }">
														${fn:substring(fn:replace(teacher.majors, ',', ' '), 0, 8) }
													</c:if>
												</span>
											</section>
											<section class=""> 
												<span class="fsize12 c-999 f-fM vam mr20">年级：
													<c:if test="${teacher.subjects == null || teacher.subjects == '' }">无</c:if>
													<c:if test="${teacher.subjects != null && teacher.subjects != '' }">
														<span title="${fn:replace(teacher.subjects, ',', ' ')}">
														${fn:substring(fn:replace(teacher.subjects, ',', ' '), 0, 8) }
														<c:if test="${teacher.subjects.length() > 8}">...</c:if></span>
													</c:if>
												</span>
											</section>
											<section class="mt10"> 
												<span class="dis">
													<b class="star-level-1 star-1-${teacher.showStar == 0 ? 5 : teacher.showStar}" title="星级：${teacher.showStar == 0 ? 5 : teacher.showStar}星">&nbsp;</b>
												</span>
												<span class="dis fsize12 c-999 f-fM">
													满意度:${teacher.satisfy == 0 ? 100 : teacher.satisfy}%
												</span>
											</section>
										</li>
										</c:forEach>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</section>
		</div>
	</section>
</body>
</html>
