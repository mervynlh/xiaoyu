<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>教师班课课程</title>
	<script type="text/javascript">
		//删除课程
		function delCourse(id){
			dialog('',16,'','/uc/teacher/delCourse/'+id+"?type=class");
		}
	</script>
</head>
<body>
<div class="u-m-right">
		<article class="u-m-center"> 
			<section class="u-m-c-wrap">
				<section class="u-m-c-head"> 
					<ul class="fl u-m-c-h-txt of" id="uTabTitle"> 
						<li class=" uHover">
							<a href="${ctx}/uc/teacher/ontToOne/list" onclick="" title="">一对一</a>
						</li> 
						<li class="current">
							<a href="javascript:void(0)" onclick="" title="">班课</a>
						</li>    
					</ul> 
					<div class="clear"></div> 
				</section> 
				<div id="uTabCont" class="mt30"> 
					<article style="display: block;" id="newFreeSellWayListUlId">
						<section class="u-T-head mt30">
							<div class="fl">
								<a href="${ctx}/uc/teacher/toAddClassCourse" class="u-T-head-tj">
									添加班课
								</a>
							</div>
							<div class="clear"></div>
						</section>
						<form action="${ctx}/uc/teacher/classCourse/list" id="searchForm">
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
						</form>
						<section class="u-T-body">
							<div class="u-T-body-in mt30">
								<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="u-t-coupon-tab">
									<thead> 
										<tr> 
											<th style="width:15%;">
												班课名称
											</th> 
											<th style="width:15%;">
												价格
											</th>
											<th style="width:15%;">
												专业
											</th>
											<th style="width:15%;">
												已报人数
											</th>
											<th style="width:15%;">
												课程安排
											</th>
											<th style="width:15%;">
												状态
											</th>
											<th style="width:25%;">
												操作
											</th>
										</tr> 
									</thead>
									<tbody>
										<c:forEach items="${classCourseList}" var="cou">
										<tr>
											<td class="pt20 pb20">
											<tt class="fsize14 f-fM c-666 vam">${cou.name}</tt>
											</td>
											<td class="pt20 pb20">
											<tt class="fsize14 f-fM c-666 vam">${cou.subjectMajorStr}</tt>
											</td>
											<td class="pt20 pb20">
												<p>
													<tt class="fsize14 f-fM c-999 vam">￥</tt>
													<tt class="fsize16 f-fM c-org vam">${cou.currentprice}</tt>
												</p>
											</td>
											<td class="pt20 pb20">
												<p>
													<tt class="fsize16 f-fM c-master vam">${cou.joinNum}/</tt>
													<tt class="fsize14 f-fM c-999 vam">${cou.peopleNum}</tt>
												</p>
											</td>
											<td class="pt20 pb20">
												<p>
													<tt class="fsize14 f-fM c-666 vam">${cou.coursePlan}</tt>
												</p>
											</td>
											<td class="pt20 pb20">
												<p>
													<c:if test="${cou.isavaliable==0}"><tt class="fsize16 f-fM c-red-1 vam">审核中</tt></c:if>
													<c:if test="${cou.isavaliable==1}"><tt class="fsize16 f-fM c-999 vam">已通过</tt></c:if>
												</p>
											</td>
											<td class="pt20 pb20">
												<span class="u-teaname-btn disIb vam">
													<a href="/uc/teacher/toUpdateClassCourse/${cou.id}">编辑/课节操作</a>
													<a href="javascript:delCourse(${cou.id})">删除</a>
												</span>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</article>
				</div>
				<c:if test="${!empty classCourseList}">
				<section class="mt50">
					<div class="Paging tac">
					<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
					</div>
				</section> 
				</c:if>
				
			</section>   
		</article>
	</div>
</body>
</html>
