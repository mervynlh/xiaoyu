///// teacher_times.js
///// 教师课时安排



// ajax 获得教师课时安排
function getTeacherClassHour(teacherId, queryDay){
	$.ajax({
		url:'/front/teacher/ajax/query',
		type:'post',
		data:{'teacherClassHour.teacherId':teacherId,'teacherClassHour.dateDay':queryDay},
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success){
				var list = result.entity.resultList;// 获得结果集
				if(list != null && list.length > 0){
					// 获得年月日期（结果集合第4个元素中的结果）
					var resut_year = list[3][0].dateDay.replace(/-/ig,'/');
					var _resutDate = new Date(resut_year);
					// 年份
					$("#_year").html(_resutDate.getFullYear());
					// 月份
					$("#_month").html(_resutDate.getMonth() + 1);
					// 遍历外层List
					for(var i = 0; i < list.length; i++){  
						var week_day = list[i][0].dateDay.replace(/-/ig,'/');
						_resutDate = new Date(week_day);
						// 拼装日期 (月-日:  08-25)
						$("#week_" + i).html(_resutDate.getMonth() + 1 + "-" + _resutDate.getDate());
						var _fullDate = $("#_year").html() + "-" + $("#week_" + i).html();
						// 遍历每天的时间段
						_list = list[i];
						for(var j = 0; j < _list.length; j++){ 
							var _status = _list[j].status;
							if (_status == 2) { // 教师已发布
								$("[lang='" + i + "-" + j +"']").attr("id", "yes");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								var content = "<div class='yes tac'>已发布</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
								$("[lang='" + i + "-" + j +"']").attr("onclick", "teacherPublishClassHour(" + teacherId + ", " + j + ", '" + _fullDate + "', 1, this)");
							} else if (_status == 3) { // 已预约
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								$("[lang='" + i + "-" + j +"']").addClass("current");
								var content = "<div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>已预约</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							} else if (_status == 0 || _status == 1){ // 教师未发布课时
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").removeClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								var content = "<div class='no tac'>未发布</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
								$("[lang='" + i + "-" + j +"']").attr("onclick", "teacherPublishClassHour(" + teacherId + ", " + j + ", '" + _fullDate + "', 2, this)");
							} else if (_status == 5) { // 已发布班课时间
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").removeClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								var content = "<div class='yes yes-1 tac'>班课时间</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							}
						}
					}
				}
			}
		}
	});
}

// 计算日期
function addDays(date, n){
    var time = date.getTime();
    var newTime = time + n*24*60*60*1000;
    return new Date(newTime);
};

// 切换到下一周
function changeNextWeek(){
	if($("#classHourStr").val() != ''){
		publishClass(_teacherId, 0);
	}
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
	getTeacherClassHour(_teacherId, _queryDate);
}


//切换到上一周
function changeUpWeek(){
	if($("#classHourStr").val() != ''){
		publishClass(_teacherId, 0);
	}
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
	getTeacherClassHour(_teacherId, _queryDate);
}


// 切换到上一个月
function changeUpMonth(){
	if($("#classHourStr").val() != ''){
		publishClass(_teacherId, 0);
	}
	var _monthNum = $("#_month").html();
	var _year = $("#_year").html() ;
	if(_monthNum == 1){
		_year = parseInt(_year) - 1;
		_monthNum = 12;
	} else {
		_monthNum = parseInt(_monthNum) - 1;
	}
	var _queryDate = _year + "-" + _monthNum + "-5";
	getTeacherClassHour(_teacherId, _queryDate);
	
}

//切换到下一个月
function changeNextMonth(){
	if($("#classHourStr").val() != ''){
		publishClass(_teacherId, 0);
	}
	var _monthNum = $("#_month").html();
	var _year = $("#_year").html() ;
	if(_monthNum == 12){
		_year = parseInt(_year) + 1;
		_monthNum = 1;
	} else {
		_monthNum = parseInt(_monthNum) + 1;
	}
	var _queryDate = _year + "-" + _monthNum + "-5";
	getTeacherClassHour(_teacherId, _queryDate);
	
}

 
/**
 * 教师发布课时安排
 * 
 * @param timeId : 时间段编号
 * 
 * @param dateDay : 日期(年-月-日)
 * 
 * @param status : 状态(1：取消发布  2：发布)
 * 
 */
function teacherPublishClassHour(teacherId, timeId, dateDay, status, obj){
	var classHourStr = $("#classHourStr").val();
	var time = "";
	if (timeId == 0) {
		time = "06:00-07:00";
	} else if (timeId == 1) {
		time = "07:00-08:00";
	} else if (timeId == 2) {
		time = "08:00-09:00";
	} else if (timeId == 3) {
		time = "09:00-10:00";
	} else if (timeId == 4) {
		time = "10:00-11:00";
	} else if (timeId == 5) {
		time = "11:00-12:00";
	} else if (timeId == 6) {
		time = "12:00-13:00";
	} else if (timeId == 7) {
		time = "13:00-14:00";
	} else if (timeId == 8) {
		time = "14:00-15:00";
	} else if (timeId == 9) {
		time = "15:00-16:00";
	} else if (timeId == 10) {
		time = "16:00-17:00";
	} else if (timeId == 11) {
		time = "17:00-18:00";
	} else if (timeId == 12) {
		time = "18:00-19:00";
	} else if (timeId == 13) {
		time = "19:00-20:00";
	} else if (timeId == 14) {
		time = "20:00-21:00";
	} else if (timeId == 15) {
		time = "21:00-22:00";
	} else if (timeId == 16) {
		time = "22:00-23:00";
	} else if (timeId == 17) {
		time = "23:00-00:00";
	}
	classHourStr = classHourStr + dateDay + " " + time + "DAY";
	$("#classHourStr").val(classHourStr);
	if (status == 1) {
		$(obj).attr("id", "no");
		$(obj).removeClass("pr");
		$(obj).removeClass("current");
		var content = "<div class='no tac'>未发布</div>";
		$(obj).html(content);
		$(obj).attr("onclick", "teacherPublishClassHour(" + teacherId + ", " + timeId + ", '" + dateDay + "', 2, this)");
	} else if (status == 2) {
		$(obj).attr("id", "yes");
		$(obj).addClass("pr");
		var content = "<div class='yes tac'>已发布</div>";
		$(obj).html(content);
		$(obj).attr("onclick", "teacherPublishClassHour(" + teacherId + ", " + timeId + ", '" + dateDay + "', 1, this)");
	}
}

/**
 * 发布课时 
 */
function publishClass(teacherId, clickEvn){
	var teacherClassHour = $("#classHourStr").val();
	if (teacherClassHour == "") {
		dialog("您未做任何修改", 10 , "", "");
		return;
	}
	$.ajax({
		url:'/uc/teacher/ajax/classhour/publish',
		type:'post',
		data:{'teacherId':teacherId,'teacherClassHour':teacherClassHour},
		dataType:'json',
		async:false,
		success:function(result){
			if(result.success){
				$("#classHourStr").val("");
				if (result.message == null || result.message == "") {
					if (clickEvn == 1) {
						dialog("课时发布成功", 8 , "", "");
					}
				} else {
					if (clickEvn == 1) {
						dialog(result.message, 9 , "", "");
					} else if (result.message != "您未对课程做出任何修改！") {
						dialog(result.message, 10, "", "");
					}
				}
			}
		}
	});
}