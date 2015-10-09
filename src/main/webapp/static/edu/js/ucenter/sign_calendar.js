var sign_year;
var sign_month;
var sign_day;
var signStr;//字符串类型的当月签到每一天的日期 例如 18,19
$(document).ready(function(){
	sign_backToday();
});
//返回今天
function sign_backToday(){
	sign_year = new Date().getFullYear();
	sign_month = new Date().getMonth()+1;
	sign_day = new Date().getDate();
	
	sign_drawCalendar(sign_year,sign_month,sign_day);
	// 判断今日是否签到
	if(signStr.indexOf(zerofill(sign_day))!=-1){
		$("#sign_title").html("今日已签到");
		$("#sign_info").html("已签到");
		$("#sign_info").removeAttr("onclick");
	}
}
//签到
function sign() {
	$.ajax({
		url : baselocation + "/uc/userSignIn",
		type : "post",
		data : {},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				var sign_day;
				dialog("签到成功！",17,"","");
				
				/*sign_changeMonth('ok');
				sign_day = $("#contSignIn").html();
				$("#contSignIn").html(parseInt(sign_day)+1)
				$("#sign").html("已签到");
				$("#userintegift").html(result.entity.integift);
				$("#sign").removeAttr("onclick");
				$("#sign_title").html("今日已签到");*/
			}
		}
	});
}
//月份变换
function sign_changeMonth(value){
	if(value=='up'){// 上月
		sign_month--;
	}else if(value=='down'){// 下月
		sign_month++;
	}
	if(sign_month<1){
		sign_month = 12;
		sign_year--;
	}else if(sign_month>12){
		sign_month = 1;
		sign_year++;
	}
	$.ajax({
		url:baselocation+"/uc/getSignByMonth",
		type:"post",
		data:{"year":sign_year,"month":sign_month},
		dataType:"json",
		success:function(result){
			if(result.success){
				if(result.entity.indexOf(",") != -1){
					signStr=result.entity;
				}else {
					signStr=result.entity;
				}
				sign_drawCalendar(sign_year,sign_month,sign_day);
			}
		}
	});
	
}
//绘制日历
function sign_drawCalendar(sign_year,sign_month,sign_day){
	$("#sign_date").html(sign_year+"-"+zerofill(sign_month)+"-"+zerofill(sign_day));
	var array = getDateTable(sign_year,sign_month,sign_day);
	//日期
	for(var row=0;row<6;row++){
		$($(".signTable:eq(" + row + ")").find("li")).removeClass("current");
		for(var col=0;col<7;col++){
			var d = array[7 * row + col];
			$($(".signTable:eq(" + row + ")").find("a:eq(" + col + ")")).html(d);
			if (signStr.indexOf(d) > -1) {
				$($(".signTable:eq(" + row + ")").find("li:eq(" + col + ")")).addClass("current");
			}
		}
	}
}
//获取日期数组(月)
function getDateTable(year,month){
	var dayArray = new Array(42);
	//本月天数
	var days = getDates(year,month);	
	//当前月第一天星期几
	var firstDay = getDays(year,month,1);
	if(firstDay==0){
		firstDay=7;
	}
	for(var i=firstDay-2;i>=0;i--){
		dayArray[i]=" ";
	}
	//补全本月天数
	for(var i=0;i<days;i++){
		dayArray[firstDay-1+i] = zerofill(i+1);
	}
	//补全下月天数
	var l = dayArray.length-(days+firstDay-1);
	for(var i=0;i<l;i++){
		dayArray[days+firstDay-1+i] = " ";
	}
	return dayArray;	
}
//获取某月天数
function getDates(year,month){
	return new Date(year,month,0).getDate();
}
//获取某天是星期几
function getDays(year,month,day){
	return new Date(year,month-1,day).getDay();
}
//个位数补零
function zerofill(number){
	if(number<10){
		number = "0"+number;
	}
	return number;
}
// 去零
function zeroCut(number){
	if(number<10){
		number = number.replace(/\b(0+)/gi,"")
	}
	return number;
}