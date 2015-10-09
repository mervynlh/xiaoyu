
///
/// order_select_time.js
/// 

// 添加时间段选择显示结果DIV
function showSelectTime(num){
	for (var i = 0; i < num; i++) {
		var content = "<div class='cou-boy pl50 mt20 pb10' id='selecttime_" + i + "' style='display:none'>" +
					  "<span class='mr10'>" + 
					  "<tt class='fsize18 f-fM c-666 vam'>第" + (i + 1) + "个预约上课时间： </tt>" +
					  "</span>" +
					  "<span class='mr10'>" +
					  "<tt class='fsize18 f-fM c-666 vam'> <span id='selecttime_day_" + i + "'></span> </tt>" +
					  "</span>" +
					  "<span class='mr20'>" +
					  "<tt class='fsize18 f-fM c-666 vam'><span id='selecttime_time_" + i + "'></span> 之间上课 </tt>" +
					  "</span>" +
					  "<input type='hidden' id='hidden_i_" + i + "'><input type='hidden' id='hidden_j_" + i + "'>" +
					  "<span class='mr20 finish_time'>" +
					  "<a href='javascript:void(0)' class='Gravity' id='reelect_" + i + "'>" +
					  "重选" +
					  "</a>" +
					  "</span>" +
					  "</div>";
		$("#show_select_times").append(content);
	}
}

function selecttime(timeId, dayId, day, obj){
	if (this._num + 1 > _lessionnum) {
		dialog("你只能向该教师发起" + _lessionnum + "次约课", 10, "", "");
		return;
	}
	var date = new Date(day.replace(/-/ig,'/'));
	var weekArray = new Array('周日','周一','周二','周三','周四','周五','周六');
	var time = "";
	if (timeId == 0) {
		time = "上午 06:00-07:00";
	} else if (timeId == 1) {
		time = "上午 07:00-08:00";
	} else if (timeId == 2) {
		time = "上午 08:00-09:00";
	} else if (timeId == 3) {
		time = "上午 09:00-10:00";
	} else if (timeId == 4) {
		time = "上午 10:00-11:00";
	} else if (timeId == 5) {
		time = "上午 11:00-12:00";
	} else if (timeId == 6) {
		time = "下午 12:00-13:00";
	} else if (timeId == 7) {
		time = "下午 13:00-14:00";
	} else if (timeId == 8) {
		time = "下午 14:00-15:00";
	} else if (timeId == 9) {
		time = "下午 15:00-16:00";
	} else if (timeId == 10) {
		time = "下午 16:00-17:00";
	} else if (timeId == 11) {
		time = "下午 17:00-18:00";
	} else if (timeId == 12) {
		time = "晚上 18:00-19:00";
	} else if (timeId == 13) {
		time = "晚上 19:00-20:00";
	} else if (timeId == 14) {
		time = "晚上 20:00-21:00";
	} else if (timeId == 15) {
		time = "晚上 21:00-22:00";
	} else if (timeId == 16) {
		time = "晚上 22:00-23:00";
	} else if (timeId == 17) {
		time = "晚上 23:00-00:00";
	}
	if (_flag == -1) {
		$("#selecttime_" + _num).show();
		$("#selecttime_day_" + _num).text(day + weekArray[date.getDay()]);
		$("#selecttime_time_" + _num).text(time);
		$("#hidden_i_" + _num).val(dayId);
		$("#hidden_j_" + _num).val(timeId);
		
		$("#reelect_" + _num).attr("onclick","newlyConsult('" + day + "', " + _teacherId + ", " + _num + ")");
	} else {
		$("#selecttime_day_" + _flag).text(day + weekArray[date.getDay()]);
		$("#selecttime_time_" + _flag).text(time);
		$("#hidden_i_" + _flag).val(dayId);
		$("#hidden_j_" + _flag).val(timeId);
		
		$("#reelect_" + _flag).attr("onclick","newlyConsult('" + day + "', " + _teacherId + ", " + _flag + ")");
		selectTeacherClassHour(_teacherId, day);
		_flag = -1;
	}
	$(obj).attr("id", "no");
	$(obj).addClass("pr");
	$(obj).addClass("current");
	var content = "<div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>我预约</div>";
	$(obj).html(content);
	$(obj).attr("onclick", "");
	_num = _num + 1;
}

// 重新预约时间段  flag:重新预约的时间段标识ID
function newlyConsult(date, teacherId, flag){
	// 预约次数-1
	_num = _num - 1;
	_flag = flag;
	// 跳转到重新预约的时间段所在的周
	selectTeacherClassHour(teacherId, date);
	window.location.href="#selec_classhour";
}
//完成选课时间
function consultTeacherTime(){
	if (_num != _lessionnum && _flag != -1) {
		dialog("您购买了 " + _lessionnum + "个课时，只预约了 " + _num + " 个时间段。", 9, "", "");
		return;
	}
	// 拼装字符串
	var value = "";
	for (var i = 0; i < _lessionnum; i++) {
		var day = ($("#selecttime_day_" + i).text().split("周"))[0];
		var time = ($("#selecttime_time_" + i).text().split(" "))[1];
		value = value + day + " " + time + "DAY";
	}
//	$("#dateValue").val(value);
	$("#class_time_num").html("2");
	$(".finish_time").hide();
	$(".submit_order").show();
	var teaId = $("#teacherId").val();
	var lessionNum = $("#lessionNum").val();
	var money = $("#lessionNum").val();
	//加载地址
	if(areaChoose){
		initArea(1, 0,0);
	}
	$.ajax({
		url:'/ajax/getCouponForUser/'+teaId,
		type:'post',
		data:{"money":orderAfterMinusAcount},//总额
		dataType:'html',
		async:false,
		success:function(result){
			$("#coupon_ul").html(result);
		}
	});
	if(courseModel=='ONLINEOTO'){
		$(".course-address").hide();
	}else{
		$(".course-address").show();
	}
	window.location.href="#aCoursesList";
	/*$.ajax({
		url:'/front/ajax/consult/teacher/time',
		type:'post',
		data:{'datavalue':value,'teacherId':_teacherId},
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success){
				if (result.message == null || result.message == "") {
					dialog("预约课时成功！", 8,"","");
					$("#select-times").hide();
				} else {
					dialog(result.message,9,"","");
				}
			} else {
				if (result.message == 'dataError') {
					dialog("请求数据错误",9,"","");
				} else {
					dialog("系统错误，请稍后重试",9,"","");
				}
			}
		}
	});*/
}

//切换到下一周
function orderchangeNextWeek(){
	var _monthNum = $("#week_6").html().split("-")[0];
	var _dayNum = $("#week_6").html().split("-")[1];
	var _year = $("#_year").html() ;
	if(_monthNum == 1 && _dayNum < 7 ){
		_year = parseInt(_year) + 1;
	}
	// 获得本周日的时间
	var _day = _year + "-" + $("#week_6").html();
	var _newDate = new Date(_day.replace(/-/ig,'/'));
	var _returnDate = addDays(_newDate, 1);
	var _queryDate = _returnDate.getFullYear() + "-" + (_returnDate.getMonth() + 1) + "-" + _returnDate.getDate();
	selectTeacherClassHour(_teacherId, _queryDate);
}

//切换到上一周
function orderchangeUpWeek(){
	var _monthNum = $("#week_0").html().split("-")[0];
	var _dayNum = $("#week_0").html().split("-")[1];
	var _year = $("#_year").html() ;
	if(_monthNum == 12 && _dayNum > 23 ){
		_year = parseInt(_year) - 1;
	}
	// 获得本周一的时间
	var _day = _year + "-" + $("#week_0").html();
	var _newDate = new Date(_day.replace(/-/ig,'/'));
	var _returnDate = addDays(_newDate, -1);
	var _queryDate = _returnDate.getFullYear() + "-" + (_returnDate.getMonth() + 1) + "-" + _returnDate.getDate();
	selectTeacherClassHour(_teacherId, _queryDate);
}

// 切换到上一个月
function orderchangeUpMonth(){
	var _monthNum = $("#_month").html();
	var _year = $("#_year").html() ;
	if(_monthNum == 1){
		_year = parseInt(_year) - 1;
		_monthNum = 12;
	} else {
		_monthNum = parseInt(_monthNum) - 1;
	}
	var _queryDate = _year + "-" + _monthNum + "-5";
	selectTeacherClassHour(_teacherId, _queryDate);
}

//切换到下一个月
function orderchangeNextMonth(){
	var _monthNum = $("#_month").html();
	var _year = $("#_year").html() ;
	if(_monthNum == 12){
		_year = parseInt(_year) + 1;
		_monthNum = 1;
	} else {
		_monthNum = parseInt(_monthNum) + 1;
	}
	var _queryDate = _year + "-" + _monthNum + "-5";
	selectTeacherClassHour(_teacherId, _queryDate);
}