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
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/order/order_select_time_class.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/front/order/order_class.js?v=${v}"></script>
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
			//地址不可选时信息显示
			if(!areaChoose){
				$("#provinceTip").html('${teacher.provinceName}');
				$("#cityTip").html('${teacher.cityName}');
				$("#townTip").html('${teacher.districtName}');
				$("#addresss").val("${teacher.address}");
				$("#studyAddress").val('${teacher.provinceName}${teacher.cityName}${teacher.districtName}${teacher.address}');
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
			<form action="${ctx}/order_class?command=buy" method="post" id="orderForm">
			<input id="teacherId" name="trxorder.teacherId" type="hidden" value="${teacher.id}"/>
			<input id="couponCodeId" name="couponcode" type="hidden" value=""/>
			<input id="orderAfterMinusAcount" name="trxorder.orderAmount" type="hidden" value="${orderAfterMinusAcount}"/>
			<input id="courseId" name="trxorder.courseId" type="hidden" value="${trxorder.courseId}"/>
			<input id="lessionNum" name="trxorder.lessionNum" type="hidden" value="${trxorder.lessionNum}"/>
			<input id="dateValue" name="dateValue" type="hidden" value="2015-09-21 20:00:00"/>
			<input id="courseType" name="courseType" type="hidden" value="2"/>
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
								<c:if test="${trxorder.courseModel=='LINEDOWNCOU' }">
								<tt class="fsize18 f-fM c-org vam">线下面授</tt>
								</c:if>
								<c:if test="${trxorder.courseModel=='ONLINECOU' }">
								<tt class="fsize18 f-fM c-org vam">在线授课</tt>
								</c:if>
							</span>
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">讲师：</tt>
								<tt class="fsize18 f-fM c-org vam">${teacher.userExpand.realname }讲师</tt>
							</span>
					 		<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">价格：</tt>
								<tt class="fsize18 f-fM c-org vam">￥${price }</tt>
							</span>
							<span class="mr20">
								<tt class="fsize18 f-fM c-666 vam">课时：</tt>
								<tt class="fsize18 f-fM c-org vam">共${trxorder.lessionNum}课时</tt>
							</span>
						</div>
					</section>
					<section class="sele-course">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub" id="class_time_num">2</span>
								<span class="fsize20 c-master f-fM">上课安排</span>
								<div class="clear"></div>
							</div>
						</div>
						<div>
							<c:forEach items="${kpoints}" var="kpoint">
							<div class="cou-boy pl50 mt20">
								<span class="mr20">
									<tt class="fsize18 f-fM c-org vam">※</tt>
									<tt class="fsize18 f-fM c-666 vam">${kpoint.name}&nbsp;&nbsp; 
										<fmt:formatDate value="${kpoint.beginTime}" type="both" pattern="EEEE"/>&nbsp;&nbsp;
										<fmt:formatDate value="${kpoint.beginTime}" type="time"  timeStyle="short" />
										 ~<fmt:formatDate value="${kpoint.endTime}"  type="both" pattern="hh:00"/>之间上课
									</tt>
								</span>
							</div>
							</c:forEach>
						</div>
					</section>
					<section class="sele-course submit_order" >
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
													<span id="provinceTip">所在省</span>
												</section>
												<section class="j-s-option" style="display: none;">
													<div class="j-s-o-box" id="provinceOption">
														<p><a title="" href="javascript: void(0)">北京</a></p>
														<p><a title="" href="javascript: void(0)">河北</a></p>
														<p><a title="" href="javascript: void(0)">天津</a></p>
													</div>
												</section>
											</div>
										</div>
										<div class="selectUI fl">
											<div class="job-select" style="width:128px;">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="cityTip">所在市</span>
												</section>
												<section class="j-s-option" style="display: none;" >
													<div class="j-s-o-box" id="cityOption">
														<p><a title="" href="javascript: void(0)">北京</a></p>
														<p><a title="" href="javascript: void(0)">天津</a></p>
														<p><a title="" href="javascript: void(0)">石家庄</a></p>
													</div>
												</section>
											</div>
										</div>
										<div class="selectUI fl">
											<div class="job-select" style="width:128px;">
												<section class="j-s-defalt">
													<em class="icon14 fr mt5">&nbsp;</em>
													<span id="townTip">所在区</span>
												</section>
												<section class="j-s-option">
													<div class="j-s-o-box" id="townOption">
														<p><a title="" href="javascript: void(0)">朝阳区</a></p>
														<p><a title="" href="javascript: void(0)">海淀区</a></p>
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
								<textarea id="addresss" readonly="readonly" maxlength="100" placeholder="请输入详细街道、门牌号码" name="" class="vam valid" style="height:90px;border-color:#40bb8a;">请输入详细街道、门牌号码</textarea>
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
					<section class="sele-course submit_order">
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
<script type="text/javascript" src="${ctx}/static/edu/js/front/order/order_class_address_map.js"></script>
</body>
</html>