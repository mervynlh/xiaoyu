<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>教师一对一课程</title>
	<script type="text/javascript">
		//删除课程
		function delCourse(id){
			dialog('',16,'','/uc/teacher/delCourse/'+id);
		}
	</script>
</head>
<body>
<div class="u-m-right">
	<article class="u-m-center"> 
		<section class="u-m-c-wrap">
			<section class="u-m-c-head"> 
				<ul class="fl u-m-c-h-txt of" id="uTabTitle"> 
					<li class="current uHover">
						<a href="javascript:void(0)" onclick="" title="全部订单">一对一</a>
			</li> 
			<li class="">
				<a href="${ctx}/uc/teacher/classCourse/list" onclick="" title="待支付">班课</a>
			</li>    
		</ul> 
		<div class="clear"></div> 
	</section> 
	<section class="u-T-head mt30">
		<div class="fl">
			<a href="${ctx}/uc/teacher/ontToOne/add" class="u-T-head-tj">
				添加课程
			</a>
		</div>
		<div class="btn-r fr">
			<a class="btn-in-1 btn-in" href="/uc/teacher/times/publish">设置授课时间</a>
			<a href="${ctx}/uc/teacher/minusList" class="btn-in-1 btn-in">课时折扣</a>
		</div>
		<div class="clear"></div>
	</section>
	<section class="u-T-body">
		<div class="u-T-body-in mt30">
			<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="u-t-coupon-tab">
				<thead> 
					<tr> 
						<th style="width:15%;">
							上课方式
						</th> 
						<th style="width:15%;">
							科目信息
						</th>
						<th style="width:15%;">
							价格
						</th>
						<th style="width:15%;">
							已售课时
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
					<c:forEach items="${oneToOneList}" var="oneToOne">
					<tr>
						<td class="pt20 pb20">
							<c:forEach items="${oneToOne.courseModelMap}" var="model" > 
							<p>
								<tt class="fsize14 f-fM c-999 vam">
									<c:choose>
										<c:when test="${model.key=='TEACHERVISIT'}">老师上门</c:when>
										<c:when test="${model.key=='STUDENTVISIT'}">学生上门</c:when>
										<c:when test="${model.key=='TALKADDRESS'}">协商地点</c:when>
										<c:otherwise>在线教学</c:otherwise>
									</c:choose>
								</tt>
							</p>
							</c:forEach> 
						</td>
						<td class="pt20 pb20">
							<p>
								<tt class="fsize14 f-fM c-999 vam">${oneToOne.subjectMajorStr}</tt>
							</p>
						</td>
						<td class="pt20 pb20">
							<c:forEach items="${oneToOne.courseModelMap}" var="model" > 
							<p>
								<tt class="fsize16 f-fM c-org vam">${model.value}/</tt>
								<tt class="fsize14 f-fM c-999 vam">小时</tt>
							</p>
							</c:forEach>
						</td>
						<td class="pt20 pb20">
							<p>
								<tt class="fsize16 f-fM c-666 vam">0</tt>
							</p>
						</td>
						<td class="pt20 pb20">
							<p>
								<c:choose>
									<c:when test="${oneToOne.isavaliable==1}"><tt class="fsize16 f-fM c-master vam">已通过</tt></c:when>
									<c:otherwise><tt class="fsize16 f-fM c-red-1 vam">审核中</tt></c:otherwise>
								</c:choose>
							</p>
						</td>
						<td class="pt20 pb20">
							<span class="u-teaname-btn disIb vam">
								<a href="/uc/teacher/toUpdateOneToOne/${oneToOne.id}">编辑</a>
								<a href="javascript:void(0)" onclick="delCourse(${oneToOne.id})">删除</a>
							</span>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>
