$(function() {
	menuToggle();
	addTeacherBrowseNum();
	//一级专业默认选择第一个
	chooseSub(defaultSubjectId);
	checkBuyNum();
});

//菜单切换
function menuToggle(){
	$(".expandClass").click(function() {
		$(".expandClass").parent().removeClass('current');
		$(this).parent().addClass('current');
		var id = $(this).attr('lang');
		if (id == 'sellWayComment') {
			getTeacherClassHour(_teacherId, defaultDay);
		}
		$(".publicClass").hide();
		$("#" + id).show();
	});
} 
//评论区显示
function assessSellWay(id) {
	ajaxPage("/front/teacher/ajax/assess","&teacherId="+id,1,callBackassess);
}
function callBackassess(result){
	$("#assessList").html(result);
}
//锚点定位
function position(id){
	var pos = $("#"+id).offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
}

//清空下拉框显示的信息
var emptyValue='<em class="icon14 fr mt5">&nbsp;</em><span>--请选择--</span>';
//选择一级专业
function chooseSub(subjectId){
	$("#subjectId").val(subjectId);
	//清空科目
	$("#majorOption").html(emptyValue);
	//价格显示老师信息中的价格
	$("#showPrice").html(price);
	$("#courseId").val('');
	showModel();
	selectMajorCourse();
}
//选择科目
function chooseMajor(id,price){
	$("#showPrice").html(price.toFixed(2));
	$("#courseId").val(id);
	$("#modelOption").html(emptyValue);
	$(".models").parent().show();
	$(".models").removeAttr("lang");
	$("#courseModelValue").val('');
	$.ajax({
		url:'/front/ajax/getCourseById/'+id,
		type:'post',
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success){
				var entity=result.entity;
				if(entity.courseModel!=null&&entity.courseModel!=''){
					//处理上课方式价格显示
					var models=eval('(' + entity.courseModel + ')');
					$.each(models, function(key,value){
						$("#"+key).attr("lang",value);
					});
					$(".models").each(function(){
						var money=$(this).attr("lang");
						if(money==null||money==''){
							$(this).parent().hide();
						}
					});
					showModel();
				}
			}else{
				alert(result.message);
			}
		}
	});
	selFun("#select-1");// 模拟 select 下拉控件
}
//选择上课方式
function chooseModel(obj){
	$("#courseModelValue").val($(obj).attr('title'));
	$("#showPrice").html($(obj).attr("lang"));
}

//显示上课方式
function showModel(){
	var subjectId=$("#subjectId").val();
	var courseId=$("#courseId").val();
	$("#showModel").hide();
	if(subjectId!=null&&subjectId!=''&&courseId!=null&&courseId!=''){
		$("#showModel").show();
	}
}
//查询科目课程
function selectMajorCourse(){
	$("#majorCourse").html('');
	var teacherId= $("#teacherId").val();
	var subjectId=$("#subjectId").val();
	
	$.ajax({
		url:'/front/ajax/getMajorCourseBySubject',
		type:'post',
		data:{'queryCourse.teacherId':teacherId,'queryCourse.subjectId':subjectId,'queryCourse.sellType':1},
		dataType:'json',
		success:function(result){
			if(result.success){
				var list=result.entity;
				if(list!=null &&list.length>0){
					var content='';
					//科目默认加载第一
					$("#majorOption").html('<em class="icon14 fr mt5">&nbsp;</em><span>'+list[0].subjectName+' '+list[0].majorName+'</span>');
					chooseMajor(list[0].courseId,list[0].currentPrice);
					
					for(i=0;i<list.length;i++){
						var currentPrice=list[i].currentPrice;
						if(currentPrice==null||currentPrice==''){
							currentPrice='100.00';
						}
						currentPrice=currentPrice.toFixed(2);
						content+='<p><a title="" href="javascript: void(0)" onclick="chooseMajor('+list[i].courseId+','+currentPrice+')">'+list[i].subjectName+' '+list[i].majorName+'</a></p>';
					}
					$("#majorCourse").html(content);
					selFun("#select-1");// 模拟 select 下拉控件
				}
			}
		}
	});
}

//增加教师信息浏览量
function addTeacherBrowseNum(){
	var teacherId=$("#teacherId").val();
	if(teacherId!=null && teacherId!=0){
		$.ajax({
			url:'/front/ajax/addTeacherBrowseNum',
			type:'post',
			data:{'teacherId':teacherId},
			dataType:'json',
			success:function(result){
			}
		});
	}
}
//增加减少购买数量
function changeBuyNum(num){
	if(classhourNum>0){
		var buyNum=$("#buyNum").val();
		var count= parseInt(buyNum)+num;
		if(count>0){
			// 购课数量不能大于教师安排的未过期的课时数量
			if (count > classhourNum) {
				dialog("根据教师授课时间安排，<br/>您最多只能购买" + classhourNum + "个课时",10,"","");
				$("#buyNum").val(classhourNum);
				return;
			}
			$("#buyNum").val(count);
			checkMinus(count);
		}else{
			$("#buyNum").val(1);
			checkMinus(count);
		}
	}
}
//手动输入购买课节数
function checkBuyNum(){
	var buyNum=$("#buyNum").val();
	if(buyNum<=0){
		$("#buyNum").val(1);
	}
	// 购课数量不能大于教师安排的未过期的课时数量
	if (parseInt(buyNum) > parseInt(classhourNum)) {
		$("#buyNum").val(classhourNum);
		return;
	}
	checkMinus(buyNum);
}

//课时包优惠选择
function checkMinus(count){
	$(".minus").removeClass("current");
	var minusId='';
	$(".minus").each(function(){
		 if(parseInt(count)>=parseInt($(this).attr('lang'))){
			 minusId= $(this).attr('lang');
		 }
	});
	if(minusId!=''){
		$("#m"+minusId).addClass("current");
	}
}
// 收藏教师
function addTeacherFavorites(){
	if(!isLogin()){
		dialog("您未登录，请先登录",10,"","");
		return;
	}
	var teacherId = $("#teacherId").val();
		$.ajax({
			url:baselocation+"/user/addTeacherFavorites",
			type:"post",
			data:{"teacherId":teacherId},
			dateType:"json",
			success:function(result){
				if(result.success){
					dialog("收藏成功",8,"","");
				}else if(result.message=='owned'){
					dialog("您已经收藏,请勿重复操作",10,"","");
				}else if(result.message=='youIsTeacher'){
					dialog("对不起,您是教师,无法收藏教师",10,"","");
				}
			}
		});
}
// 领取优惠券
function getCoupon(couponId){
	if(!isLogin()){
		dialog("您未登录，请先登录",10,"","");
		return;
	}
	$.ajax({
		url:baselocation+"/coupon/createcode",
		type:"post",
		data:{"couponId":couponId},
		dateType:"json",
		success:function(result){
			if(result.success){
				dialog("领取成功",8,"","");
			}else if(result.message=='isEmpty'){
				dialog("对不起,优惠券已经没有了",10,"","");
			}else if(result.message=='owned'){
				dialog("您已领取,请勿重复操作",10,"","");
			}else if(result.message=='youIsTeacher'){
				dialog("对不起,您是教师,无法领取优惠券",10,"","");
			}
		}
	});
}
//立即购课
function buyCourse(){
	if(isLogin()){
		if(getCookie("userType")=="1"){//教师不可购买
			dialog("您的身份是教师不可以购买课程！",10);
		}else{
			var courseId = $("#courseId").val();
			if(courseId==null||courseId==""){
				dialog("请选择年级科目！",10);
				return;
			}
			var courseModelValue = $("#courseModelValue").val();
			if(courseModelValue==null||courseModelValue==""){
				dialog("请选择上课方式！",10);
				return;
			}
			$("#buyCourse").submit();
		}
	}else{
		dialog("",0);
	}
}
//查询教学记录
function selectRecord(id){
	$.ajax({
		url:baselocation+"/front/ajax/getCourseRecord/"+id,
		type:"post",
		data:{
			"page":$("#pageCurrentPage").val()
		},
		dataType:"text",
		success:function(result){
			$("#showRecord").html(result);
		}
	});
}
// 查询班课
function selectClass(id) {
	$.ajax({
		url:baselocation+"/front/ajax/getCourseBySellType/"+id,
		type:"post",
		data:{
			"page":$("#pageCurrentPage").val()
		},
		dataType:"text",
		success:function(result){
			$("#showClass").html(result);
		}
	});
}
//我要预约
function audition(teacherId){
	if(!isLogin()){
		dialog("您未登录，请先登录",10,"","");
		return;
	}
	dialog('',7,teacherId);
}
//我要约课
function submitInfo(){
	var teacherName = $("#audi_teacherName").val();
	var teacherMobile = $("#audi_teacherMobile").val();
	var startDate = $("#startDate").val();
	var timeArray = $("#startTime").val().split("-");
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/audition/addAudition",
		type:"post",
		data:{
			"audition.type":1,
			"audition.teacherName":teacherName,
			"audition.teacherMobile":teacherMobile,
			"audition.startTime":startDate+" "+timeArray[0],
			"audition.endTime":startDate+" "+timeArray[1],
			"audition.description":description
		},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,13,"","javascript:window.location.reload()");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
/////////////////////百度地图处理开始
var lng=$('#lng').val();
var lat=$('#lat').val();
if(isEmpty(lng)&&isEmpty(lat)){
	lng=116.404;
	lat=39.915;
}
var map = new BMap.Map("map", {enableMapClick:false});
$(function(){
	var point = new BMap.Point(lng, lat);
	map.centerAndZoom(point, 15);
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);
	var g=new BMap.Geocoder();
	g.getLocation(point,function(e){
		var addComp = e.addressComponents;
		var locationName=addComp.province + " " + addComp.city+" "+addComp.district+" </br>"+addComp.street + " " + addComp.streetNumber;
		label.setContent(locationName);
	});
	var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);
	//工具条  比例尺 控件
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	map.addControl(top_left_control);        
	map.addControl(top_right_navigation); 
});
function mapBack(){
	map.panTo(new BMap.Point(lng, lat));
}		
/////////////////////百度地图处理结束