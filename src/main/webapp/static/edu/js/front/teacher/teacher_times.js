///// teacher_times.js
///// 教师课时安排

// ajax 获得教师课时安排(教师详情页面展示用)
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
						// 遍历每天的时间段
						_list = list[i];
						for(var j = 0; j < _list.length; j++){ 
							var _status = _list[j].status;
							var user_id = _list[j].userId;
							if (_status == 2) { // 教师已发布，学生可预约
								$("[lang='" + i + "-" + j +"']").attr("id", "yes");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								$("[lang='" + i + "-" + j +"']").children(".tac").removeClass("no").addClass("yes").children("em").removeClass("icon-c").addClass("icon-d");
								var content = "<div class='tac yes'> <em class='icon-d'>&nbsp;</em> </div><div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>可预约</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							} else if (_status == 3) { // 已预约
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								$("[lang='" + i + "-" + j +"']").addClass("current");
								if (user_id == _userId) {
									var content = "<div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>我预约</div>";
								} else {
									var content = "<div class='tea-tab-table-1-box tea-tab-table-1-box-1 pa'><em id='triangle-bottomright-1'>&nbsp;</em>已预约</div>";
								}
								$("[lang='" + i + "-" + j +"']").html(content);
							} else if (_status == 0 || _status == 1){ // 教师未发布课时
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").removeClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								var content = "<div class='no tac'><em class='icon-c'>&nbsp;</em></div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							}
						}
					}
				}
			}
		}
	});
}

// 学生购课选择课时
function selectTeacherClassHour(teacherId, queryDay){
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
							if (_status == 2) { // 教师已发布，学生可预约
								$("[lang='" + i + "-" + j +"']").attr("id", "yes");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								var content = "<div class='tac yes'> <em class='icon-d'>&nbsp;</em> </div><div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>可预约</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
								$("[lang='" + i + "-" + j +"']").attr("onclick", "selecttime(" + j + ", " + i + ", '" + _fullDate + "', this)");
							} else if (_status == 3) { // 已预约
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").addClass("pr");
								$("[lang='" + i + "-" + j +"']").addClass("current");
								var content = "<div class='tea-tab-table-1-box tea-tab-table-1-box-1 pa'><em id='triangle-bottomright-1'>&nbsp;</em>已预约</div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							} else if (_status == 0 || _status == 1){ // 教师未发布课时
								$("[lang='" + i + "-" + j +"']").attr("id", "no");
								$("[lang='" + i + "-" + j +"']").removeClass("pr");
								$("[lang='" + i + "-" + j +"']").removeClass("current");
								var content = "<div class='no tac'><em class='icon-c'>&nbsp;</em></div>";
								$("[lang='" + i + "-" + j +"']").html(content);
							}
						}
					}
				}
				// 预约状态被选中
				var _list = result.entity.dateList;// 获得一周日期结果集
				for (var i = 0; i < _lessionnum; i++) {
					var date = ($("#selecttime_day_" + i).text().split("周"))[0];
					var day = new Date(date.split("-")[0], parseInt(date.split("-")[1]) - 1, date.split("-")[2], "00", "00" ,"00");
					for (var j = 0; j < _list.length; j++) {
						var _day = new Date(_list[j].split("-")[0], parseInt(_list[j].split("-")[1]) - 1, _list[j].split("-")[2], "00", "00" ,"00");
						if (day.getTime() == _day.getTime()) {
							var x = $("#hidden_i_" + i).val();
							var y = $("#hidden_j_" + i).val();
							$("[lang='" + x + "-" + y +"']").attr("id", "no");
							$("[lang='" + x + "-" + y +"']").addClass("pr");
							$("[lang='" + x + "-" + y +"']").addClass("current");
							$("[lang='" + x + "-" + y +"']").attr("onclick", "");
							var content = "<div class='tea-tab-table-1-box pa'><em id='triangle-bottomright'>&nbsp;</em>我预约</div>";
							$("[lang='" + x + "-" + y +"']").html(content);
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
