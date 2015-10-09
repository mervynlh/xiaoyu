<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>选择课程--小雨在线教育</title>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/teacher/teacher_times.js"></script>
	<script type="text/javascript" src="${ctx}/static/edu/js/web/jquery.slides.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/edu/js/web/commJs.js"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/order/order_select_time.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/front/order/order.js?v=${v}"></script>
	<script type="text/javascript">
		// 将教师ID存入全局变量
		var _teacherId = ${trxorder.teacherId};
		var _lessionnum = ${trxorder.lessionNum};
		var _num = 0;
		var _flag = -1;
		var date = new Date();
		var year = date.getFullYear();   // 获得年份
		var month = date.getMonth() + 1; // 获得月份
		var day = date.getDate();        // 获得日期
		var defaultDay = year + "-" + month + "-" + day; // 默认查询时间(当前时间)
		var orderAfterMinusAcount = '${orderAfterMinusAcount}';
		var courseModel = '${trxorder.courseModel}';
		//判断是否可以选择地址
		var areaChoose = ('${teacher.lng}'==''?true:false);
		$(function(){
			showSelectTime(_lessionnum);
			selectTeacherClassHour(_teacherId, defaultDay);
			
			//地址不可选时信息显示
			if(!areaChoose){
				$("#provinceTip").html('${teacher.provinceName}');
				$("#cityTip").html('${teacher.cityName}');
				$("#townTip").html('${teacher.districtName}');
				$("#address").attr("readonly","readonly");
				$("#address").val('${teacher.address}');
				$("#studyAddress").val('${teacher.address}');
			}
		});
	</script>
	<style type="text/css">
		.n-l-menu dl dd {display: none;}
		.n-l-menu dl:hover dd{display: block;}
		#triangle-bottomleft{border-right-width: 125px;}
		#triangle-topright{border-left-width: 125px;}
	</style>
</head>
<body>
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<form action="${ctx}/order?command=buy" method="post" id="orderForm">
			<input id="teacherId" name="trxorder.teacherId" type="hidden" value="${teacher.id}"/>
			<input id="couponCodeId" name="couponcode" type="hidden" value=""/>
			<input id="orderAfterMinusAcount" name="trxorder.orderAmount" type="hidden" value="${orderAfterMinusAcount}"/>
			<input id="courseId" name="trxorder.courseId" type="hidden" value="${trxorder.courseId}"/>
			<input id="lessionNum" name="trxorder.lessionNum" type="hidden" value="${trxorder.lessionNum}"/>
			<input id="dateValue" name="dateValue" type="hidden" value=""/>
			<input id="courseModelValue" name="trxorder.courseModel" type="hidden" value="${trxorder.courseModel }"/>
			<section class="container">
				<div class="mt30">
					<section class="sele-course">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub">1</span>
								<span class="fsize20 c-master f-fM">我选择的课程</span>
								<div class="clear"></div>
							</div>
						</div>
						<div class="cou-boy pl50 mt20 pb20">
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">课程名称：</tt>
								<tt class="fsize18 f-fM c-org vam">${course.name}</tt>
							</span>
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">上课方式：</tt>
								<c:if test="${trxorder.courseModel=='TEACHERVISIT' }">
								<tt class="fsize18 f-fM c-org vam">老师上门</tt>
								</c:if>
								<c:if test="${trxorder.courseModel=='STUDENTVISIT' }">
								<tt class="fsize18 f-fM c-org vam">学生上门</tt>
								</c:if>
								<c:if test="${trxorder.courseModel=='TALKADDRESS' }">
								<tt class="fsize18 f-fM c-org vam">协商地点</tt>
								</c:if>
								<c:if test="${trxorder.courseModel=='ONLINEOTO' }">
								<tt class="fsize18 f-fM c-org vam">在线教学</tt>
								</c:if>
							</span>
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">讲师：</tt>
								<tt class="fsize18 f-fM c-org vam">${teacher.userExpand.realname }讲师</tt>
							</span>
					 		<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">价格：</tt>
								<tt class="fsize18 f-fM c-org vam">￥${price }/节</tt>
							</span>
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">课时：</tt>
								<tt class="fsize18 f-fM c-org vam">共${trxorder.lessionNum}课时</tt>
							</span>
						</div>
					</section>
					<section class="sele-course finish_time" id="selec_classhour">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub">2</span>
								<span class="fsize20 c-master f-fM">选择上课日期</span>
								<div class="clear"></div>
							</div>
						</div>
						<div class="cou-boy pl50 mt20 pb20">
							<div class="Details">
								<div class="Details-boy mt40" id="select-times">
									<dl>
											<dd class="teach-sktime mt10">
												<div class="teach-sktime-tab mt50">
													<em class="teach-sktime-tab-bg">&nbsp;</em>
													<div class="tea-tab-yy">
														<span class="mr80">
															<em class="icon-d-g vam">&nbsp;</em>
															<tt class="fsize16 f-fM c-666 vam">代表可预约</tt>
														</span>
														<span>
															<em class="icon-c vam mr">&nbsp;</em>
															<tt class="fsize16 f-fM c-666 vam">代表不可预约</tt>
														</span>
														<div class="clear"></div>
													</div>
													<div class="tea-tab-tit mt20 tac">
														<p class="fsize24 c-master f-fM" id="yser_month"><tt id="_year">2015</tt>年<tt id="_month">08</tt>月</p>
														<a href="javascript:void(0)" onclick="orderchangeUpMonth()" class="up"></a>
														<a href="javascript:void(0)" onclick="orderchangeNextMonth()" class="down"></a>
													</div>
													<table class="tea-tab-table mt20" cellspacing="0" cellpadding="0" border="0" style="width: 100%;">
														<thead>
															<tr class="tac">
																<th style="width:12.5%;border-right:1px solid #dcdcdc;" class="pr">
																	<span id="triangle-topright" class="pa"></span>
																	<span id="triangle-bottomleft" class="pa"></span>
																	<span class="fsize12 c-666 f-fM pa riqi">日期</span>
																	<span class="fsize12 c-666 f-fM pa shijian">时间</span>
																</th>
																<th colspan="7" width="87.5%"  class="teatime-tit-xg">
																	<a href="javascript:void(0)" onclick="orderchangeUpWeek()" class="up pa"></a>
																	<a href="javascript:void(0)" onclick="orderchangeNextWeek()" class="down pa"></a>
																	<table width="100%" border="0">
																		<tr>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-666 f-fM">周一</p>
																				<p class="fsize12 c-999 f-fM mt10 week-day" id="week_0">08-10</p>
																			</th>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-666 f-fM">周二</p>
																				<p class="fsize12 c-999 f-fM mt10 week-day" id="week_1">08-11</p>
																			</th>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-666 f-fM">周三</p>
																				<p class="fsize12 c-999 f-fM mt10 week-day" id="week_2">08-12</p>
																			</th>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-666 f-fM">周四</p>
																				<p class="fsize12 c-999 f-fM mt10 week-day" id="week_3">08-13</p>
																			</th>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-666 f-fM">周五</p>
																				<p class="fsize12 c-999 f-fM mt10 week-day" id="week_4">08-14</p>
																			</th>
																			<th width="12.5%" style="border-right:1px solid #dcdcdc;">
																				<p class="fsize20 c-red f-fM">周六</p>
																				<p class="fsize12 c-red f-fM mt10 week-day" id="week_5">08-15</p>
																			</th>
																			<th width="12.5%">
																				<p class="fsize20 c-red f-fM">周日</p>
																				<p class="fsize12 c-red f-fM mt10 week-day" id="week_6">08-16</p>
																			</th>
																		</tr>
																	</table>
																</th>
															</tr>
														</thead>
													</table>
													<div class="table-tite-time">
														<em class="icon30 icon-sw">&nbsp;</em>
														<tt>上午</tt>
													</div>
													<div class="tea-tab-table-1" style="width: 100%;">
														<div class="tea-tab-table-1-in">
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	6:00-7:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	7:00-8:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	8:00-9:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	9:00-10:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	10:00-11:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	11:00-12:00
																</p>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="0-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="1-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="2-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="3-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="4-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="5-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in" style="border-right:0;">
															<div id="no" lang="6-0" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-1" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-2" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-3" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-4" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-5" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="table-tite-time table-tite-time-xw">
														<em class="icon30 icon-xw">&nbsp;</em>
														<tt>下午</tt>
													</div>
													<div class="tea-tab-table-1" style="width: 100%;">
														<div class="tea-tab-table-1-in">
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	12:00-13:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	13:00-14:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	14:00-15:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	15:00-16:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	16:00-17:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	17:00-18:00
																</p>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="0-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="1-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="2-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="3-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="4-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="5-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in" style="border-right:0;">
															<div id="no" lang="6-6" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-7" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-8" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-9" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-10" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-11" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="table-tite-time table-tite-time-ws">
														<em class="icon30 icon-ws">&nbsp;</em>
														<tt>晚上</tt>
													</div>
													<div class="tea-tab-table-1" style="width: 100%;">
														<div class="tea-tab-table-1-in">
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	18:00-19:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	19:00-20:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	20:00-21:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	21:00-22:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	22:00-23:00
																</p>
															</div>
															<div class="tea-tab-table-1-in-box tac">
																<p>
																	23:00-00:00
																</p>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="0-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="0-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="1-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="1-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="2-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="2-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="3-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="3-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="4-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="4-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in">
															<div id="no" lang="5-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="5-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="tea-tab-table-1-in" style="border-right:0;">
															<div id="no" lang="6-12" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-13" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-14" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-15" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-16" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
															<div id="no" lang="6-17" class="tea-tab-table-1-in-box">
																<div class="no tac">
																	<em class="icon-c">&nbsp;</em>
																</div>
															</div>
														</div>
														<div class="clear"></div>
													</div>
												</div>
											</dd>
										</dl>
								</div>
							</div>
						</div>
					</section>
					<section class="sele-course">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub" id="class_time_num">3</span>
								<span class="fsize20 c-master f-fM">你预约的上课时间</span>
								<div class="clear"></div>
							</div>
						</div>
						<span id="show_select_times">
							
						</span>
						<div class="cou-boy pl50 mt20 pb10 finish_time">
							<span class="mr10">
								<tt class="fsize14 f-fM c-org vam">※  如果你已经预约好上课时间，请点击下面的确定按钮提交吧！</tt>
							</span>
						</div>
					</section>
					<section class="sele-course mt30 finish_time">
						<a class="buy-btn Submit" href="javascript:void(0)" onclick="consultTeacherTime()">
							<font class="c-fff tac">已 选 择 并 确 认 提 交</font>  
						</a>
					</section>
					<section class="sele-course submit_order" style="display: none">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub">3</span>
								<span class="fsize20 c-master f-fM">填写信息</span>
								<div class="clear"></div>
							</div>
						</div>
						<div class="pb40 pt20 pr">
							<div class="cou-boy pl20 mt20">
								<span class="mr20 s-a-tit">
									<tt class="fsize18 f-fM c-org vam">*</tt>
									<tt class="fsize18 f-fM c-666 vam">学生姓名</tt>
								</span>
								<label class="s-a-txt"> 
									<input type="text" id="studentName" value="${user.realname }"  class="mobile" maxlength="10" name="trxorder.studentName"> 
										<font color="#40bb8a" id="mobile-1"></font> 
								</label>
								<span class="ml20" id="studentName_error" style="display: none">
									<tt class="fsize18 f-fM c-org vam">*</tt>
									<tt class="fsize14 f-fM c-org vam" >请输入真实姓名</tt>
								</span>
							</div>
							<div class="cou-boy pl20 mt20">
								<span class="mr20 s-a-tit">
									<tt class="fsize18 f-fM c-org vam">*</tt>
									<tt class="fsize18 f-fM c-666 vam">手机号</tt>
								</span>
								<label class="s-a-txt"> 
									<input type="text" id="mobile" value="${user.mobile }"  class="mobile" maxlength="11" name="trxorder.mobile"> 
										<font color="#40bb8a" id="mobile-1"></font> 
								</label>
								<span class="ml20" id="mobile_error" style="display: none">
									<tt class="fsize18 f-fM c-org vam">*</tt>
									<tt class="fsize14 f-fM c-org vam" id="mobile_msg">请输入11位正确手机号码</tt>
								</span>
							</div>
							<div class="cou-boy pl20 mt20 cou-boy-1 course-address">
								<span class="mr20 s-a-tit fl">
									<tt class="fsize18 f-fM c-org vam">*</tt>
									<tt class="fsize18 f-fM c-666 vam">上课地址</tt>
								</span>
								<label class="s-a-txt fl"> 
									<div class="" id="select-1">
										<input id="provinceId" type="hidden" name="" value="" lang=""/>
										<input id="studyAddress" type="hidden" name="trxorder.studyAddress" value="" lang=""/>
										<input id="cityId" type="hidden" name="" value="" lang=""/>
										<input id="townId" type="hidden" name="" value="" lang=""/>
										<input id="lng" type="hidden" name="" value="${teacher.lng}" lang=""/>
										<input id="lat" name="" type="hidden" value="${teacher.lat}" lang=""/>
										<input id="allLocation" type="hidden" name="" value="" lang=""/>
										<div class="selectUI fl">
											<div style="width:128px;" class="job-select">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="provinceTip">请选择省份</span>
												</section>
												<section class="j-s-option" style="display: none;">
													<div class="j-s-o-box" id="provinceOption">
													</div>
												</section>
											</div>
										</div>
										<div class="selectUI fl">
											<div class="job-select" style="width:128px;">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="cityTip">请选择城市</span>
												</section>
												<section class="j-s-option" style="display: none;" >
													<div class="j-s-o-box" id="cityOption">
													</div>
												</section>
											</div>
										</div>
										<div class="selectUI fl">
											<div class="job-select" style="width:128px;">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="townTip">请选择城镇</span>
												</section>
												<section class="j-s-option">
													<div class="j-s-o-box" id="townOption">
													</div>
												</section>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</label>
								<div class="clear"></div>
							</div>
							<div class="cou-boy pl20 mt10 course-address">
								<span class="mr20 s-a-tit">
									<tt class="fsize18 f-fM c-666 vam">&nbsp;</tt>
								</span>
								<textarea id="address" maxlength="100" onfocus="this.value=''; this.onfocus=null;" placeholder="请输入详细街道、门牌号码" name="" class="vam valid" style="height:90px;border-color:#40bb8a;">请输入详细街道、门牌号码</textarea>
								<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
							</div>
							<div class="cou-boy pl20 mt20">
								<span class="mr20 s-a-tit">
									<tt class="fsize18 f-fM c-666 vam">备注信息</tt>
								</span>
								<textarea id="userInfo" maxlength="500" name="trxorder.remarks" class="vam valid" style="height:90px;"></textarea>
							</div>
							<div id="sub-map" class="sub-map" style="display: none;">
								<img src="${ctx }/static/edu/img/pic/map.jpg" alt="" width="500px;" height="280px;">
							</div> 
						</div>
					</section>
					<section class="sele-course submit_order" style="display: none">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub">4</span>
								<span class="fsize20 c-master f-fM">结算信息</span>
								<div class="clear"></div>
							</div>
						</div>
						<div class="">
							<div class="cou-boy-ye">
								<!-- <input type="checkbox" id="autoThirty" class="c-icon" checked="checked" name="autoThirty"> -->
								<tt class="vam c-666 fsize14 f-fM" for="forget">使用账户余额</tt>
								<tt class="vam c-org fsize18 f-fM" for="forget" id="userAccount">${balance }</tt>
								<tt class="vam c-666 fsize14 f-fM" for="forget">元</tt>
							</div>
							<div class="cou-boy fl pl30 cou-boy-buy mt30">
								<!-- 
								 -->
								<div class="buyCoupon"> 
									<div onclick="couponBtnClick(this)" id="couponBtn" class="buyCoupon_tit fo hand">
										<img src="${ctx }/static/edu/img/buy/yhj.png" alt="">
									</div> 
									<div class="buyCoupon_con buyCouponWrap mt5" style="visibility: hidden;">
										<div class="buy-sub-in-tit mt10">
											<ul class="clear">
												<li class="current coupon_li">
													<a href="javascript:void(0)" onclick="changeCoupon(1,this)">可用优惠劵</a>
												</li>
												<li class="coupon_li">
													<a href="javascript:void(0)" onclick="changeCoupon(2,this)">输入优惠编码</a>
												</li>
											</ul>
										</div>
										<div class="buy-sub-in" style="display:block;" id="coupon_1">
											<div class="infor">
												<ul id="coupon_ul">
													
												</ul> 
											</div>
										</div> 
										<div class="buy-sub-in" style="display:none;" id="coupon_2">
											<div class="infor">
												<p class="mt10 mb10 c-master fsize16">请输入优惠券验证码：</p>
												<input type="text" id="couponCode">
												<div class="svb-btn mt20">
													<a id="submit_coupon" href="javascript:addcode()" class="current buy">确认</a>
													<!-- <a href="javascript:void(0)" class="buy">取消</a> -->
													<div class="clear"></div>
												</div>
												<p class="c-red-1 mt5 fsize12 f-fM">注：优惠券使用后将不能撤销</p> 
											</div>
										</div>
									</div> 
								</div>
							</div>
							<div class="cou-boy fr cou-boy-buy mt30">
								<span>
									<tt class="fsize14 c-666 f-fM">
										订单总价 ￥<font id="orderAcount_info">${orderAcount }</font> 
										<c:if test="${minusAcount>0}">
										- 课时折扣优惠 ￥<font>${minusAcount}</font> 
										</c:if>
										<span id="coupon_span" style="display: none">
										- 优惠券优惠 ￥<font id="coupon_info">0.00</font>
										</span>
										 = ￥<font id="finalAcount_info">${orderAfterMinusAcount}</font>  
									</tt>
								</span>
								<span class="">
									<tt class="fsize18 c-666 f-fM">
										应付金额：
									</tt>
									<tt class="fsize36 c-org f-fM">
										￥<font id="finalAcount">${orderAfterMinusAcount }</font>
									</tt>
								</span>
								<span class="mt20">
									<a class="buy-btn" href="javascript:void(0)" onclick="submitOrder()">
										<font class="c-fff tac">提 交 订 单</font>  
									</a>
								</span>
							</div>
							<div class="clear"></div>
						</div>
					</section>
				</div>
			</section>
			</form>
		</div>
	</section>
	<!-- main end -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BUuYf2lWpYBQNVPu39PSZGBZ"></script>
<script type="text/javascript" src="${ctx}/static/edu/js/front/order/order_address_map.js"></script>
</body>
</html>