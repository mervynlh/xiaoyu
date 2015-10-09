<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>教师班课课程</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/edu/js/front/course/class_course_add.js"></script>
</head>
<body>
<form action="${ctx}/uc/teacher/addClassCourse" id="submitForm">
<input id="subjectMajorId" type="hidden" name="course.subjectMajorId"/>
<input id="provinceId" type="hidden" name=""/>
<input id="cityId" type="hidden" name=""/>
<input id="townId" type="hidden" name=""/>
<input id="lng" type="hidden"/>
<input id="lat" type="hidden"/>
<input id="imgs" name="course.classImgs" type="hidden"/>
<input id="allLocation" type="hidden"/>
<input id="addressId" type="hidden" name="course.addressId" value="${userAddressList[0].id}"/>
<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-t-add" id="select-1">
						<ol class="password-body-boy">
							<li>
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">班课名称：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<input id="courseName" type="text" name="course.name" maxlength="" value="" class="input-1 fl">
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">课程封面：</tt>
								</span>
								<div class="Evaluation-box-tp">
									<ul class="clearfix e-b-tp-list u-t-jsxq">
										<li>
										<img id="classpic" class="imgss" src="/static/edu/images/default/default_point.jpg" alt="">
										</li>
										<li id="last" class="last">
											<section class="uploadBtnWrap pr"> 
												<div class="ke-inline-block ">
													<span class="">
														<input type="button" id="fileupload" value="添加" class="">
													</span>
												</div>
											</section>
										</li>
									</ul>
									<div class="clear"></div>
								</div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">类目：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<div class="selectUI fl">
										<div class="job-select" style="width:150px;z-index:9999;">
											<section class="j-s-defalt">
												<em class="icon14 fr mt5">&nbsp;</em>
												<span>选择学段</span>
											</section>
											<section style="display: none;" class="j-s-option">
												<div class="j-s-o-box">
													<c:forEach items="${teacherSubject}" var="sub">
														<p><a href="javascript: void(0)" onclick="getMajor(${sub.subjectId})" title="${sub.subjectName}">${sub.subjectName}</a></p>
													</c:forEach>
												</div>
											</section>
										</div>
									</div>
									<div class="selectUI fl">
										<div style="width:150px;z-index:9999;" class="job-select">
											<section class="j-s-defalt" id="majorTip">
												<em class="icon14 fr mt5">&nbsp;</em>
												<span >选择科目</span>
											</section>
											<section class="j-s-option">
												<div class="j-s-o-box" id="majorOption"></div>
											</section>
										</div>
									</div>
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">费用：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<input id="currentprise" type="text" name="course.currentprice" maxlength="" value="1" onkeyup="onlyNumber(this,2)" class="input-2 fl">
									<div class="clear"></div>
								</label>
								<span class="vam pa-bo-boy-tit" style="text-align:left;padding-left:10px;">
									<tt class="fsize14 c-666 f-fM vam">元</tt>
								</span>
								<div class="clear"></div>
							</li>
							<li> 
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam"><font color="red">*</font>人数限制：</tt>
								</span> 
								<label class="pa-bo-boy-txt">
									<input id="courseName" type="text" name="course.peopleNum" maxlength="" value="1" class="input-1 fl" onkeyup="this.value=this.value.replace(/\D/g,'')" >
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>
							<li> 
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">上课类型：</tt>
								</span> 
								<span class="vam pa-bo-boy-txt pa-bo-boy-txt-1">
									<input type="radio" style="margin: 0 0 0 30px;" onclick="chooseModel(0)" checked="checked"  name="course.courseModel" value="LINEDOWNCOU" > 
									<label class="c-666 fsize14 f-fM" for="">线下授课</label> 
									<input type="radio" style="margin: 0 0 0 10px;" onclick="chooseModel(1)" name="course.courseModel" value="ONLINECOU" > 
									<label class="c-666 fsize14 f-fM" for="">线上授课</label> 
								</span>
								<div class="clear"></div>
							</li>
							<li id="lineModel">
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">地址：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<div class="selectUI-teaname vam dis fl fsize12 c-999 f-fM" style="padding-top:2px;">
									<input type="radio" style="margin: 0 0 0 10px;" onclick="chooseAddressType(this)" checked="checked" value="0" name="addressType">
										从地址库中选择：
									</div>
									<div class="selectUI fl" id="oldAddress">
										<div class="job-select" style="width:300px;z-index:999;">
											<section class="j-s-defalt">
												<em class="icon14 fr mt5">&nbsp;</em>
												<span>${userAddressList[0].address}</span>
											</section>
											<section style="display: none;" class="j-s-option">
												<div class="j-s-o-box">
													<c:forEach items="${userAddressList}" var="address">
														<p><a href="javascript: setAddressId(${address.id})" title="">${address.address}</a></p>
													</c:forEach>
												</div>
											</section>
										</div>
									</div>
									<div class="clear"></div>
								</label>
								<div class="fl mt10">
										<input type="radio" style="margin: 0 0 0 10px;" value="1" onclick="chooseAddressType(this)" name="addressType">
										<tt class="fsize14 c-666 f-fM vam">使用新地址：</tt>
									</div>
								<div class="clear"></div>
							</li>
							<span id="newAddress" style="display: none;">
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">新增地址：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<div class="selectUI fl">
										<div class="job-select" style="width:150px;z-index:99;">
											<section class="j-s-defalt">
												<em class="icon14 fr mt5">&nbsp;</em>
												<span id="provinceTip">请选择省份</span>
											</section>
											<section style="display: none;" class="j-s-option">
												<div class="j-s-o-box" id="provinceOption">
												</div>
											</section>
										</div>
									</div>
									<div class="selectUI fl">
										<div style="width:150px;z-index:99;" class="job-select">
											<section class="j-s-defalt">
												<em class="icon14 fr mt5">&nbsp;</em>
												<span id="cityTip">请选择城市</span>
											</section>
											<section class="j-s-option">
												<div class="j-s-o-box" id="cityOption">
												</div>
											</section>
										</div>
									</div>
									<div class="selectUI fl">
										<div style="width:150px;z-index:99;" class="job-select">
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
								</label>
								<div class="clear"></div>								
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">&nbsp;</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<input id="address" type="text" name="" maxlength="" placeholder="详细地址街道门牌" value="" class="input-1 fl">
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">&nbsp;</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<div class="u-t-add-tit-r-in">
										<a class="ckdt" herf="">
											查看大图
										</a>
										<div class="bcwz">
											
										</div>
										<div id="map" class="map">
										</div>
										<div class="map-btn">
											<a class="c-btn wz-btn" href="javascript:saveAddress()">保存位置</a>
									</div>
								</div>
								</label>
								<div class="clear"></div>
							</li>
							<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BUuYf2lWpYBQNVPu39PSZGBZ"></script>
							<script type="text/javascript">
								var lng=$('#lng').val();
								var lat=$('#lat').val();
								if(lng==null||lng==''||lat==null||lat==''){
									lng=116.404;
									lat=39.915;
								}
								var map = new BMap.Map("map");
								var point = new BMap.Point(lng, lat);
								var marker = new BMap.Marker(point);  // 创建标注
								var g=new BMap.Geocoder();//地址逆解析  根据坐标获取名称
								var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});
								$(function(){
									mapLoad();
									search();
								});
								//修改地图加载
								function updateLoad(){
									lng=$('#lng').val();
									lat=$('#lat').val();
									point = new BMap.Point(lng, lat)
									map.panTo(point);
									marker.setPosition(point);
									getLocation(point);
								}
								//地图加载
								function mapLoad(){
									map.centerAndZoom(point, 15);
									map.addOverlay(marker);
									marker.enableDragging();//标注点是否可以拖动
									//标注点拖拽监听
								    marker.addEventListener("dragend", function (e) {
								    	//检查是否选择省市县
								    	if(!checkChoose()){
								    		return;
								    	}
								    	updateLngLat(e.point);
								    	//修改文字标签
								    	getLocation(e.point);
								    	
								    	var cp = map.getCenter();
								    	$('#lat').val( e.point.lat);
								    	$('#lng').val( e.point.lng);
								    	map.panTo(new BMap.Point(e.point.lng, e.point.lat));
							        });
									g.getLocation(point,function(e){
										var addComp = e.addressComponents;
										var locationName=addComp.province + " " + addComp.city+" "+addComp.district+" </br>"+addComp.street + " " + addComp.streetNumber;
										label.setContent(locationName);
									});
									marker.setLabel(label);
									//工具条  比例尺 控件
									var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
									var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
									map.addControl(top_left_control);        
									map.addControl(top_right_navigation); 
									//地图点击事件
								    map.addEventListener("click", function(e) { 
								    	//检查是否选择省市县
								    	if(!checkChoose()){
								    		return;
								    	}
								    	updateLngLat(e.point);
								    	//修改文字标签
								    	getLocation(e.point);
								    	
								    	var cp = map.getCenter();
										marker.setPosition(e.point);
										map.panTo(new BMap.Point(e.point.lng, e.point.lat));
									});
								}
								//修改文字标签
								function getLocation(poimt){
									g.getLocation(poimt,function(e){
							    		var addComp = e.addressComponents;
							    		var locationName=addComp.street + " " + addComp.streetNumber;
							    		var allLocation=addComp.province +","+ addComp.city  +","+ addComp.district;
							    		label.setContent(locationName);
							    		if($.trim(locationName)!=''){
								    		$("#address").val(locationName);
							    		}
							    		$("#allLocation").val(allLocation);
							    	});
								}
								//修改横纵坐标
								function updateLngLat(poimt){
									$('#lng').val(poimt.lng);
									$("#lat").val(poimt.lat);
								}
								//检查是否已选择省市县
								function checkChoose(){
									var provinceId=$("#provinceId").val();
									var cityId=$("#cityId").val();
									var townId=$("#townId").val();
									var address=$("#address").val();
									if(provinceId==''||cityId==''||townId==''||address==''){
										map.panTo(new BMap.Point(116.404, 39.915));
										marker.setPosition(new BMap.Point(116.404, 39.915));
										dialog("请选择省市县详细地址",10,'','');
										return false;
									}
									return true;
								}
								
								/***搜索开始*****/
								function search(map){
									var cityName=$("#cityTip").html();
									var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
											{"input" : "address"
											,"location" : cityName
										});
									ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
										var str = "";
											var _value = e.fromitem.value;
											var value = "";
											if (e.fromitem.index > -1) {
												value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
											}    
											str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
											
											value = "";
											if (e.toitem.index > -1) {
												_value = e.toitem.value;
												value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
											}    
											str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
											$("#searchResultPanel").html(str);
										});
									ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
										var _value = e.item.value;
										myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
										$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
										setPlace();
									});
								}
								function setPlace(){
									function myFun(){
										var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
										updateLngLat(pp);
										getLocation(pp);
										map.centerAndZoom(pp, 15);
										var cp = map.getCenter();
										marker.setPosition(cp);
									}
									var local = new BMap.LocalSearch(map, { //智能搜索
									  onSearchComplete: myFun
									});
									local.search(myValue);
								}
							</script>
							
							</span>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">适合学员：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<input type="radio" name="course.suitStudent" checked="checked" value="common"/>
									<label class="c-666 fsize14 f-fM" for="">普通</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="course.suitStudent"  value="top"/>
									<label class="c-666 fsize14 f-fM" for="">优等</label> 
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">学习目标：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<input type="text" name="course.learningTarget" class="input-1 fl"/>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">课程安排：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<input type="text" name="course.coursePlan" class="input-1 fl"/>
									<tt style="top:8px;right:-225px;" class="fsize14 f-fM c-999 pa">课程安排,例如：每周六八点上课...</tt>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">是否插班：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<input type="radio" name="course.isJoinClass" onclick="chooseJoin(0)" checked="checked" value="SURE"/>
									<label class="c-666 fsize14 f-fM" for="">不可以插班</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="course.isJoinClass"  onclick="chooseJoin(1)"  value="CANNOT"/>
									<label class="c-666 fsize14 f-fM" for="">可以插班</label> 
								</label>
								<div class="clear"></div>
							</li>
							<li id="joinPrise" style="display: none;">
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">插班价格：</tt>
								</span>
								<label class="pa-bo-boy-txt">
									<input type="text" name="course.joinPrise" maxlength="" value="1" onkeyup="onlyNumber(this,2)" class="input-2 fl">
									<div class="clear"></div>
								</label>
								<span class="vam pa-bo-boy-tit" style="text-align:left;padding-left:10px;">
									<tt class="fsize14 c-666 f-fM vam">元</tt>
								</span>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<tt class="fsize14 c-666 f-fM vam">是否完成：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<input type="radio" name="course.isFinsh"  checked="checked" value="SUREJOIN"/>
									<label class="c-666 fsize14 f-fM" for="">可以报名</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="course.isFinsh"  value="NOJOIN"/>
									<label class="c-666 fsize14 f-fM" for="">不可以报名</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="course.isFinsh" value="FINSHED"/>
									<label class="c-666 fsize14 f-fM" for="">已完成</label>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">课程简介：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<textarea id="courseTitle" maxlength="100" name="course.title" class="vam" style="width:500px;"></textarea>
									<tt style="top:40px;right:-78px;" class="fsize14 f-fM c-999 pa">100字以内</tt>
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>
							<li>
								<span class="vam pa-bo-boy-tit">
									<font color="red">*</font>
									<tt class="fsize14 c-666 f-fM vam">课程详情：</tt>
								</span>
								<label class="pa-bo-boy-txt pr">
									<textarea id="courseContent" maxlength="300" name="course.context" class="vam" style="width:800px;"></textarea>
									<tt style="top:40px;right:-78px;" class="fsize14 f-fM c-999 pa">300字以内</tt>
									<div class="clear"></div>
								</label>
								<div class="clear"></div>
							</li>											
							<li class="tac">
								<a href="javascript:save()" class="c-btn bing-btn">
									确定
								</a>
								<a href="/uc/teacher/classCourse/list" class="c-btn bing-btn-fq ml40">
									取消
								</a>
							</li>
						</ol>
					</section>
				</section>   
			</article>
		</div>
</form>
		<script src="/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				selFun("#select-1");// 模拟 select 下拉控件
			});
		</script>
</body>
</html>
