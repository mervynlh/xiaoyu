var year;
var month;
var day;
var dates;// 当前月有课记录
$(document).ready(function() {
	dates = $("#dates").val();// 当前月有课记录
	backToday();// 返回今天
});
// 月份变换
function changeMonth(value) {
	if (value == 'up') {// 上月
		month--;
	} else if (value == 'down') {// 下月
		month++;
	}
	if (month < 1) {
		month = 12;
		year--;
	} else if (month > 12) {
		month = 1;
		year++;
	}
	$.ajax({
		url : baselocation + "/getCourseByMonth",
		type : "post",
		data : {
			"year" : year,
			"month" : month
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				dates = result.entity;
				createCalendar(year, month);
				selectDay(zerofill(day));
			}
		}
	});
}
// 返回今天
function backToday() {
	year = new Date().getFullYear();
	month = new Date().getMonth() + 1;
	day = new Date().getDate();
	createCalendar(year, month, day);
	selectDay(zerofill(day));
}
// 选中某一天
function selectDay(id) {
	$($(".dateTable").find("li")).removeClass("current");
	$($(".dateTable").find("a[id='" + id + "']")).parent().addClass("current");
	$.ajax({
		url : baselocation + "/ajax/getCourseByDate",
		type : "post",
		async : false,
		data : {
			"year" : year,
			"month" : month,
			"date" : id
		},
		dataType : "text",
		success : function(data) {
			$("#uTable").html(data);
		}
	});
}
// 生成日历
function createCalendar(year, month, day) {
	$("#dateYM").html(year + "年" + zerofill(month) + "月");
	drawCalendar(year, month, day);
	// 高亮当天
	selectDay(day);
}
// 绘制日历
function drawCalendar(year, month, day) {
	var array = getDateTable(year, month, day);
	// 日期
	for (var row = 0; row < 6; row++) {
		for (var col = 0; col < 7; col++) {
			var d = array[7 * row + col];
			$($(".dateTable:eq(" + row + ")").find("a:eq(" + col + ")")).html(d);
			if (dates.indexOf(zeroCut(d)) > -1 && d != " ") {
				$($(".dateTable:eq(" + row + ")").find("a:eq(" + col + ")")).html(d + "<span class='pa'></span>");
				$($(".dateTable:eq(" + row + ")").find("a:eq(" + col + ")")).addClass("pr");
			}
			$($(".dateTable:eq(" + row + ")").find("a:eq(" + col + ")")).attr("onclick", "selectDay('" + d + "')");
			$($(".dateTable:eq(" + row + ")").find("a:eq(" + col + ")")).attr("id", d);
		}
	}
}