var currentCourseId = 0;
var randomIndex = 1;// 讲师分页获取从第一页开始

$(function() {
	// 等级升级提示层显隐
	$(".levelSpanWrap").hover(function() {
		$(".levelTips").show();
	}, function() {
		$(".levelTips").hide();
	});
});

// u-index
function getNewFreeSell(aelm, type) {// 获得免费课程
	if ($(aelm).parent('li').hasClass('current') == false) {
		if (type == 1) {
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').next('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").css('display', 'none');// show();
			$("#newFreeSellWayListUlId").css('display', 'block');
		} else if (type == 2) {
			$(aelm).parent('li').addClass('current');
			$(aelm).parent('li').prev('li').removeClass('current');
			$("#newWelcomeSellWayListUlId").css('display', 'block');
			$("#newFreeSellWayListUlId").css('display', 'none');
		}
	}
}

$(document).ready(function() {
	$(".ckkcBtn").click(function() {
		$(this).children("tt").toggleClass("closeCkkc");
		$(this).parent().parent().parent().siblings(".ugkc").fadeToggle(100);
	});
});

function getupsizeper(psizeper) {
	if (psizeper.indexOf(".") < 0) {
		return psizeper;
	} else {
		return psizeper.substring(0, psizeper.indexOf("."));
	}
}


function floatScore() {
	$("#jfNumber").show();
	$("#jfNumber").css("opacity", 1);
	$("#jfNumber").css("bottom", "-20px");
	$("#jfNumber").animate({
		bottom : "50px",
		opacity : 0
	}, 1000);
}


function alldelete() {
	var idarr = $("input[name='sellIdArr']");
	var id = 0;
	for (var i = 0; i < idarr.length; i++) {
		if (idarr[i].checked) {
			id++;
		}
	}
	if (id == 0) {
		dialog('提示', '请选择要删除的课程', 1);
		return false;
	}
	$("#deleteAllFavorite").submit();
}

function allselect(en) {
	 if(en.checked){    
		$("input[name='sellIdArr']").attr("checked", true);
	} else {
		$("input[name='sellIdArr']").attr("checked", false);
	}
}
function expire() {
	dialog("课程提示", '课程已过期!', 1);
}
function watchCourse(courseId) {
	location.href = baselocation + '/front/playkpoint/' + courseId;
}

// 新手引导四步骤方法
function stepFun() {
	
			$.ajax({
				url : baselocation + "/user/queryUserGuideStatus",
				type : "post",
				async : false,
				success : function(data) {
					if (data.returnMessage == "true") {
						return;
					} else {
						var bShadow = $(
								'<div class="bg-shadow bg-shadow05">&nbsp;</div>')
								.appendTo($('body'));
						var stepEle = $(
								'<div class="step-box"><ul><li class="u-step-1"><span title="下一步">&nbsp;</span></li><li class="u-step-2"><span title="下一步">&nbsp;</span></li><li class="u-step-3"><span title="下一步">&nbsp;</span></li><li class="u-step-4"><span title="我知道了！">&nbsp;</span></li></ul></div>')
								.appendTo($('body'));
						$(".step-box>ul>li.u-step-1>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-2").show();
						});
						$(".step-box>ul>li.u-step-2>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-3").show();
						});
						$(".step-box>ul>li.u-step-3>span").click(function() {
							$(this).parent().remove();
							$(".step-box .u-step-4").show();
						});
						$(".step-box>ul>li.u-step-4>span").click(
								function() {
									$(".step-box,.bg-shadow05").remove();
									$.ajax({
										url : baselocation
												+ "/uc!updateUserGuide.action",
										type : "post",
										success : function(data) {
										}
									});
								});
					}
				}
			});

}

function searchClass(object,status){
	var html = "";
	for(var o in object){
		html+="<li><div class='r-s-l-title'><span class='fsize14 f-fM c-333'>"+
			object[o].realname
		+"</span><span class='ml50'><em class='icon-pho-3 vam icon24'>&nbsp;</em><tt class='fsize16 c-master f-fM'>"+
			object[o].mobile
		+"</tt></span></div><a href='javascript:void(0)' class='disIb vam mr30'><img src='"+
			object[o].picPath
		+"' alt=''></a><span class='u-teaname disIb vam'><p class='fsize18 c-333 f-fM'>"+
			object[o].courseName
		+"</p><p class='fsize14 c-999 f-fM mt10'><tt class='mr30>";
		if(object[o].courseType==1){
			html+="一对一";
		}else if(object[o].courseType==2){
			html+="大小班";
		}
		html+="</tt><tt>"+
			object[o].subjectId+object[o].gradeId
		+"</tt></p><p class='fsize14 c-999 f-fM mt5'>线上授课";
		html+="</p></span><span class='u-teaname disIb vam'><p class='fsize14 c-999 f-fM tac mt5'>"+
		object[o].startTime
		html+="</p><p class='fsize14 c-999 f-fM mt10 tac'>18:00-19:00</p></span><span class='u-teaname disIb vam u-teaname-1'><p class='fsize14 c-666 f-fM tac mt10'>距上课时间还有</p><p class='fsize14 c-org f-fM tac mt10'><tt class='fsize14 f-fM c-org vam'>01天10小时25分25秒</tt></p></span><span class='u-teaname-btn ml50 disIb vam'>";
			if(status==2){// 将要上的课
				html+="<a href='javascript:void(0)'>进入教室</a>	<a href='javascript:void(0)'>取消约课</a><a href='javascript:void(0)'>退课</a><a href='javascript:void(0)'>修改时间</a>";
			}else if(status==1){//待确认时间的课
				html+="<a href='javascript:void(0)'>确认时间</a>	<a href='javascript:void(0)'>修改时间</a><a href='javascript:void(0)'>退课</a>";
			}else if(status==3){//待确认课酬的课
				html+="<a href='javascript:void(0)'>确认课酬</a>";
			}else if(status==4){//已上的课
				html+="<a href='javascript:void(0)'>评价</a><a href='javascript:void(0)'>查看评价</a>";
			}
			
			
			html+="</span><div class='clear'></div></li>";
	}
	return html;
}

