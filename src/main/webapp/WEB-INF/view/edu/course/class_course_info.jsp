<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>班课详情</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BUuYf2lWpYBQNVPu39PSZGBZ"></script>
<script type="text/javascript">
var lng = ${address.lng};
var lat = ${address.lat};
$(function(){
	// 百度地图API功能
	var map = new BMap.Map("map");
	var point = new BMap.Point(lng,lat);
	map.centerAndZoom(point, 11);
	var marker = new BMap.Marker(point);// 创建标注
	map.addOverlay(marker);             // 将标注添加到地图中
	marker.disableDragging();           // 不可拖拽
	
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	map.addControl(top_left_control);
	map.addControl(top_left_navigation);
	//map.panBy(215,140);
});
function mapBack(){
	map.panTo(new BMap.Point(lng, lat));
}
function joinClass(id){
	if(!isLogin()){
		dialog("",0);
		return;
	}
	$.ajax({
		url:"/order/ajax/selectUserIsJoin/"+id,
		type:"post",
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				if(result.message=="entry"){
					dialog('您已报名,请查看个人中心课程安排。',8,'','');
				}else if(result.message=="payNo"){
					dialog('您已下订单,是否继续报名？',13,'','javascript:join('+id+')');
				}else{
					join(id);
				}
			}else{
				dialog('系统繁忙',9,'','');
			}
		}
	});
}
function join(id){
	if(getCookie("userType")=="1"){//教师不可购买
		dialog("您的身份是教师不可以购买课程！",10);
	}else{
		if('${openState}'==0){
			if('${course.isJoinClass}'=='SURE'){
				dialog('该课程已开课，是否插班？',16,'','${ctx}/order/classsingle?courseId='+id);
			}else{
				dialog('该课程已开课，不允许报名',9,'','');
			}
		}else{
			window.location.href='${ctx}/order/classsingle?courseId='+id;
		}
	}
}
//切换信息
function changeClick(str,obj){
	$(".parent").removeClass("current");
	$(obj).parent("li").addClass("current");
	var pos = $("#"+str).offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
}
</script>
</head>
<body>
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<section class="container">
				<div class="mt30">
					<section>
						<div class="couese-in-tit">
							<a href="" class="tu">
								<img src="<%=staticImageServer %>${course.classImgs }" alt="">
							</a>
							<div class="wz">
								<p class="mt10">
									<tt class="fsize18 c-666 f-fM vam">课程标题：</tt>
									<tt class="fsize18 c-333 f-fM">${course.name}</tt>
								</p>
								<p class="mt10">
									<tt class="fsize18 c-666 f-fM vam">课程总价：</tt>
									<tt class="fsize24 c-org f-fM vam">￥${course.currentprice }</tt>
								</p>
								<p class="mt15">
									<tt class="fsize18 c-666 f-fM">课&nbsp;时&nbsp;数&nbsp;：</tt>
									<tt class="fsize18 c-333 f-fM">${course.lessionnum }课时</tt>
								</p>
								<p class="mt10">
									<tt class="fsize18 c-666 f-fM">适合学员：</tt>
									<tt class="fsize18 c-333 f-fM">
										<c:choose>
											<c:when test="${course.suitStudent=='top'}">优等</c:when>
											<c:otherwise>普通</c:otherwise>
										</c:choose>
									</tt>
								</p>
								<p class="mt10">
									<tt class="fsize18 c-666 f-fM">参与人数：</tt>
									<tt class="fsize18 c-333 f-fM">${course.joinNum }/${course.peopleNum}人</tt>
								</p>
								<c:if test="${empty course.courseModelMap.ONLINECOU}">
									<p class="mt10">
										<tt class="fsize18 c-666 f-fM">上课地点：</tt>
										<tt class="fsize18 c-333 f-fM">${address.provinceStr }${address.cityStr }${address.townStr }${address.address }</tt>
									</p>
								</c:if>
							</div>
							<div class="btn-cous pt50">
								<div class="u-teaname-btn tac">
									<c:if test="${course.isFinsh=='SUREJOIN'}"><a class="c-btn c-btn-2" href="javascript:joinClass(${course.id})">我要报名</a></c:if>
									<c:if test="${course.isFinsh=='NOJOIN'}"><a class="c-btn c-btn-2" href="javascript:void(0)">不可 报名</a></c:if>
									<c:if test="${course.isFinsh=='FINSHED'}"><a class="c-btn c-btn-2" href="javascript:void(0)">本班课已完成</a></c:if>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<section class="u-m-c-head mt50"> 
							<ul class="fl u-m-c-h-txt of" id="uTabTitle"> 
								<li class="current uHover parent">
									<a href="javascript:void(0);" onclick="changeClick('title',this)" title="">课程简介</a>
								</li> 
								<li class="parent">
									<a href="javascript:void(0);" onclick="changeClick('target',this)" title="">教学大纲</a>
								</li> 
								<li class="parent">
									<a href="javascript:void(0);" onclick="changeClick('address',this)" title="">上课地址</a>
								</li>
								<li class="parent">
									<a href="javascript:void(0);" onclick="changeClick('content',this)" title="">课程详情</a>
								</li>   
							</ul> 
							<div class="clear"></div> 
						</section>
						<div id="" class="mt30">
							<article style="display: block;" id="title"> 
								<section class="mb50">
									<div class="couse-in-for">
										<span class="couse-in-for-tit vam">适合人群：</span>
										<span class="couse-in-for-txt vam">
											<c:if test="${course.suitStudent=='common' }">适合一些对该课程内容感兴趣的学生</c:if>
											<c:if test="${course.suitStudent=='top' }">适合一些热爱该课程内容的学生</c:if>
										</span>
									</div>
									<div class="couse-in-for">
										<span class="couse-in-for-tit vam">教学目标：</span>
										<span class="couse-in-for-txt vam">${course.learningTarget }</span>
									</div>
									<div class="couse-in-for">
										<span class="couse-in-for-tit vam">退班规则：</span>
										<span class="couse-in-for-txt vam">开班前一小时不可以退班</span>
									</div>
									<div class="couse-in-for">
										<span class="couse-in-for-tit vam">能否插班：</span>
										<span class="couse-in-for-txt vam">
											<c:if test="${course.isJoinClass=='SURE' }">随时插班</c:if>
											<c:if test="${course.isJoinClass=='CANNOT' }">不可插班</c:if>
										</span>
									</div>
								</section>
							</article>
							<article style="display: block;" id="target">
								<c:if test="${not empty kpointsList }">
								<section class="mb50">
									<p class="hLh36 fsize16 c-master f-fM tar pr20">
										${course.coursePlan }
									</p>
									<table cellspacing="0" cellpadding="0" border="0" class="u-t-coupon-tab" style="width: 100%;">
										<thead> 
											<tr> 
												<th style="width:25%;">
													课程安排
												</th> 
												<th style="width:50%;">
													课程时间
												</th>
												<th style="width:25%;">
													主讲老师
												</th>
											</tr> 
										</thead>
										<tbody>
											<c:forEach var="kpoints" items="${kpointsList }">
												<tr>
													<td>
														<tt class="fsize14 f-fM c-999 vam">${kpoints.name }</tt>
													</td>
													<td>
														<tt class="fsize14 f-fM c-999 vam">
															<fmt:formatDate value="${kpoints.beginTime }" type="both" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
															<fmt:formatDate value="${kpoints.beginTime }" type="time" pattern="HH:mm"/>-
															<fmt:formatDate value="${kpoints.endTime }" type="time" pattern="HH:mm"/>
														</tt>
													</td>
													<td>
														<tt class="fsize14 f-fM c-999 vam">${course.teacherName }</tt>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</section>
								</c:if>
							</article>
							<article style="display: block;" id="address"> 
								<section class="mb50">
									<div class="u-t-add-tit-r-in ml30 mt30 fl" id="map"></div>
									<div class="ml50 u-t-add-tit-r-in-r fl">
										<span class="disIb">
											<tt class="fsize16 c-999 f-fM vam">详细地址：</tt>
											<tt class="fsize16 c-666 f-fM vam">${address.provinceStr }${address.cityStr }${address.townStr }${address.address }</tt>
										</span>
									</div>
									<div class="clear"></div>
								</section>
							</article>
							<article style="display: block;" id="content"> 
								<section class="mb50">
									<div class="couse-in-kcxq">
										<p>${course.context }</p>
									</div>
								</section>
							</article>
						</div>
					</section>
				</div>
			</section>
		</div>
	</section>
	<script src="${ctx}/static/edu/js/web/commJs.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
</body>
</html>