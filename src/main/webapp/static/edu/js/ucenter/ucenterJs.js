var windsShowState = 0;
var registerFrom="";
var user_type = 0;//用户类型 0学生 1老师
var leftUrlAndClassArr = [];
$(function() {
	showuserLoginInfo();
	upSlideFun("#iQuestion"); // 向上滚动互动
	goTop();// 右侧悬浮
	gtFun();
	areaFun();
	aSortFun();//全部课程分类一二级分类效果
	updatePwdIsShow();
	lMenu();// 打开关闭左侧菜单导航
	menuControl();// 左侧 跟随效果
});
//查看用户是否是第三方用户  第三方用户不需要修改密码
function updatePwdIsShow(){
	if($.trim(registerFrom)=='OpenAppRegisterFrom'){
		$(".updatePwd").hide();
		return true;
	}else{
		return false;
	}
}
//选择头部地域下拉效果
function areaFun() {
	$(".area-wrap").hover(function() {
		$(this).addClass("hover");
		$(".addRessCont").stop().slideDown(150);
	}, function() {
		$(this).removeClass("hover");
		$(".addRessCont").stop().slideUp(150);
	})
}
// 模拟 select 下拉控件
function selFun(op) {
	var _sel = $(op).find(".selectUI"), _timer = null;
	_sel
			.each(function() {
				var _this = $(this), _selU = _this.children(".job-select"), _opt = _this
						.find(".j-s-option"), _sTxt = _selU.find(".j-s-defalt")
						.find("span"), _oTxt = _opt.find(".j-s-o-box")
						.find("p");
				_this.hover(function() {
					if (_opt.is(":hidden")) {
						_selU.addClass("selected");
						_timer = setInterval(function() {
							_opt.stop().slideDown(50);
						}, 100)
					}
					;
				}, function() {
					clearInterval(_timer);
					_selU.removeClass("selected");
					_opt.hide();
				});
				_oTxt.each(function() {
					var __this = $(this);
					__this.click(function() {
						_sTxt.html(__this.children("a").text());
						_opt.hide();
					})
				})
			})
}

//全部课程分类一二级分类效果
function aSortFun() {
	var _oLi = $(".n-l-m-subMenu>li"),
		_timer = null;
	_oLi.each(function() {
		var _this = $(this),
			_oSub = _this.children(".nlm-subm");
		_this.hover(function() {
			if (_oSub.is(":hidden")) {
				_timer = setTimeout(function() {
					_this.addClass("hover");
					_oSub.show();
				}, 300);
			};
		}, function() {
			_this.removeClass("hover");
			clearTimeout(_timer);
			_oSub.hide();
		})
	})
}
//向上滚动方法
var upSlideFun = function(od) {
	var _upWrap = $(od),
		_sTime = 5000,
		_moving;
	_upWrap.hover(function() {
		clearInterval(_moving);
	}, function() {
		_moving = setInterval(function() {
			var _mC = _upWrap.find("li:first");
			var _mH = _mC.height();
			_mC.animate({"margin-top" : -_mH + "px"}, 600, function() {
				_mC.css("margin-top", 0).appendTo(_upWrap);
			});
		}, _sTime);
	}).trigger("mouseleave");
}

function goTop(){
	$("#goTop").click(function() {$("html, body").animate({scrollTop : 0}, 500);});
	if (!window.XMLHttpRequest) {alert("亲！您的浏览器太古老了，请升级到高级版本体验。");};
}
function gtFun() {
	var gtB = $(".onlineConsult-gld>dl>dt");
	gtB.hide();
	var uGt = function() {
		var sTop = document.documentElement.scrollTop + document.body.scrollTop;
		if (sTop > 0) {
			gtB.show();
		} else {
			gtB.hide();
		}
	}
	$(window).bind("scroll",uGt);
}

/**
 * 个人中心显示邮箱 头像
 */
function showuserLoginInfo() {
	var bannerUrl = '';// 获取图片做为背景图片
	$
			.ajax({
				url : baselocation + "/user/loginuser",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if (result.success) {
						var entity = result.entity;
						user_type = entity.userType;
						if (isNotNull(entity)) {
							var showname = "";
							//注册来源
							registerFrom=entity.registerFrom;
							if (entity.realname != null && $.trim(entity.realname) != ''){
								showname = entity.realname;
							} else if (entity.nickname != null && entity.nickname != '') {
								showname = entity.nickname;
							} else if ((entity.nickname == null || entity.nickname == '')
									&& (entity.email != null && entity.email != '')) {
								showname = entity.email;
							} else if ((entity.nickname == null || entity.nickname == '')
									&& (entity.email == null || entity.email == '')
									&& (entity.mobile != null || entity.mobile != '')) {
								showname = entity.mobile;
							}
							//// 如果登录用户是教师，且未开启隐私模式，且真实姓名不为空
							//if (entity.realname != null && $.trim(entity.realname) != '' && entity.userType == 1 && entity.hideStatus == 0 ){
							//	showname = entity.realname;
							//}
							$("#nickname").html(showname);
							$("#usernickname").html(showname.substring(0,5));
							$("#u_id").html(entity.id);
							if (entity.gender != 1) {
								$("#user_gender").removeClass("icon-nv").addClass("icon-nan");
							}
							$("#userintegift").html(entity.integift);
							$(".login-infor").removeClass("undis");
							$("#unameheader").html(showname);
							$("#unameright").text(showname);
							if (entity.userType == 1) { // 如果为教师
								$("#menu_teacher").show();
								$("#menu_student").hide();
								$("#ucenter_banner").removeClass("u-banner").addClass("u-T-banner");
								if (entity.hideStatus == 1) {
									$("#isOpenHidden").show();
								}
							} else { // 如果为学生
								$("#menu_teacher").hide();
								$("#menu_student").show();
								$("#ucenter_banner").removeClass("u-T-banner").addClass("u-banner");
							}
							if (entity.avatar != '' && isNotEmpty(entity.avatar)) {
								//if ((entity.userType == 1 && entity.hideStatus == 0) || entity.userType != 1) {
									$("#cusImg").attr("src",staticImageServer+entity.avatar);
									$("#userImgId").attr("src",staticImageServer + entity.avatar);
								//} else {
								//	$("#updateavatar").attr("href", "javascript:void(0)");
								//	if (entity.gender != 1) {
								//		$("#cusImg").attr("src","/static/edu/images/page/tea-nan.jpg");
								//		$("#userImgId").attr("src","/static/edu/images/page/tea-nan.jpg");
								//	} else {
								//		$("#cusImg").attr("src","/static/edu/images/page/tea-nv.jpg");
								//		$("#userImgId").attr("src","/static/edu/images/page/tea-nv.jpg");
								//	}
								//}
							} else {
								if (entity.gender != 1) {
									$("#cusImg").attr("src","/static/edu/images/page/tea-nan.jpg");
									$("#userImgId").attr("src","/static/edu/images/page/tea-nan.jpg");
								} else {
									$("#cusImg").attr("src","/static/edu/images/page/tea-nv.jpg");
									$("#userImgId").attr("src","/static/edu/images/page/tea-nv.jpg");
								}
							}
							signStr=entity.signDate;
							$("#contSignIn").html(entity.contSignin);
						}
					}
				}
			});
	// ******
	// 获取Cookie中的图片做为背景图片
	// var imgUrl =getCookie('usercookieuserimg');
	// if(isNotEmpty(imgUrl) && imgUrl!='notimg'&&imgUrl!='undefined'){
	// imgUrl='url("'+staticImageServer+imgUrl+'") no-repeat scroll 50% 50px
	// #FAFAFA';
	// $('body').css("background",imgUrl);
	// }else if(bannerUrl!=''&&typeof(bannerUrl)!='undefined'){
	// imgUrl='url("'+staticImageServer+bannerUrl+'") no-repeat scroll 50% 50px
	// #FAFAFA';
	// SetCookie('usercookieuserimg',bannerUrl);
	// $('body').css("background",imgUrl);
	// }else{//如果Cookie中没有图片就是使用默认图片
	// //
	// imgUrl='url('+imagesPath+'/static/edu/images/u-center/u-banner/u-b-1.jpg)
	// no-repeat 50% 50px #FAFAFA';
	// // $('body').css("background",imgUrl);
	// }

}

/**
 * 翻页
 * 
 * @param type
 */
function pageGo(type) {
	var page = currentPage;
	page = parseInt(page);
	if (type == '1') {
		if (page > 1 && (page - 1) > 0) {
			getUserPersonalityImages(page - 1);
		}
	} else if (type == '2') {
		if (page < totalPage && (page + 1) <= totalPage) {
			getUserPersonalityImages(page + 1);
		}
	}
}

// 个人中心左侧导航定位
var stuLeftUrlAndClassArr = [
		// 学生
		'leftdl_order_student@/uc/order?trxStatus=all',
		'leftdl_order_student@/uc/order?trxStatus=INIT',
		'leftdl_order_student@/uc/order?trxStatus=SUCCESS',
		'leftdl_order_student@/uc/order?trxStatus=FINISH',
		'leftdl_schedule_student@/uc/student/home',
		'leftdl_setting_student@/uc/user/uinfo',
		'leftdl_setting_student@/uc/user/uppwd',
		'leftdl_setting_student@/uc/user/avatar',
		'leftdl_setting_student@/uc/user/jumpmobile',
		'leftdl_setting_student@/uc/address',
		'leftdl_wallet_student@/uc/student/acc',
		'leftdl_wallet_student@/uc/student/coupon',
		'leftdl_wallet_student@/uc/student/coupon?status=1',
		'leftdl_wallet_student@/uc/student/coupon?status=2',
		'leftdl_wallet_student@/uc/student/coupon?status=3',
		'leftdl_wallet_student@/uc/myinte',
		'leftdl_wallet_student@/uc/integift',
		'leftdl_news_student@/uc/letter',
		'leftdl_studyhistory@/uc/my/study/history/list',
		'leftdl_myteacher@/uc/front/myteacher/list',
		'leftdl_Collectteacher@/uc/collectTeacherList',
		'leftdl_evaluation@/uc/evaluation',

];
//个人中心左侧导航定位
var teaLeftUrlAndClassArr = [

		// 教师
		'leftdl_schedule_teacher@/uc/teacher/myclass',
		'leftdl_news_teacher@/uc/letter',
		'leftdl_wallet_teacher@/uc/teacher/acc',
		'leftdl_wallet_teacher@/uc/myinte',
		'leftdl_wallet_teacher@/uc/teacher/coupon',
		'leftdl_teaching_journal@/uc/teacher/mystudent/list',
		'leftdl_teaching_journal@/uc/teacher/student/study/history/list/',
		'leftdl_honorteacher@/uc/evaluation',
		'leftdl_Coursevideo@/uc/user/uinfo',
		'leftdl_Coursevideo@/uc/user/uppwd',
		'leftdl_Coursevideo@/uc/user/avatar',
		'leftdl_Coursevideo@/uc/user/jumpmobile',
		'leftdl_news_teacher@/uc/letter',
		'leftdl_setting_teacher@/uc/teacher/material/tosetting?materialType=base',
		'leftdl_setting_teacher@/uc/teacher/aptitude/toattestation',
		'leftdl_setting_teacher@/uc/address',
		'leftdl_setting_teacher@/uc/teacherOrder?trxStatus=all',
		'leftdl_setting_course@/uc/teacher/ontToOne/list',
		'leftdl_setting_course@/uc/teacher/classCourse/list',
		'leftdl_setting_course@/uc/teacher/times/publish',
		'leftdl_order_teacher@/uc/teacherOrder?trxStatus=all',
		'leftdl_order_teacher@/uc/teacherOrder?trxStatus=INIT',
		'leftdl_order_teacher@/uc/teacherOrder?trxStatus=SUCCESS',
		'leftdl_order_teacher@/uc/teacherOrder?trxStatus=FINISH',
];
/**
 * 个人中心打开关闭左侧菜单导航
 */
function lMenu() {
	$(".u-menu-list>dl>dt").each(
			function() {
				var _this = $(this);
				_this
						.click(function() {
							if (_this.next("dd").is(":hidden")) {
								_this.addClass("curr");
								_this.next("dd").slideDown(100);
								_this.parent().siblings("dl").children("dt")
										.removeClass("curr");
								_this.parent().siblings("dl").children("dd")
										.slideUp(100);
							} else {
								_this.removeClass("curr");
								_this.next("dd").slideUp(100);
								_this.parent().next("dl").children("dt")
										.addClass("curr");
								_this.parent().next("dl").children("dd")
										.slideDown(100);
							}
						});
			});
};

/**
 * 个人中心左则菜单栏伸展控制
 */
function menuControl() {
	var onwUrl = window.location + '';
	if(user_type==0){
		leftUrlAndClassArr = stuLeftUrlAndClassArr;
	}else{
		leftUrlAndClassArr = teaLeftUrlAndClassArr;
		
	}
	for (var i = 0; i < leftUrlAndClassArr.length; i++) {
		var lefturlArr = leftUrlAndClassArr[i].split('@');
		if (onwUrl.indexOf(lefturlArr[1]) != -1) {
			var dtEvent = $("#" + lefturlArr[0] + '>dt').attr("onclick");
			if (dtEvent != null) {
				$("#" + lefturlArr[0] + '>dt').addClass('current');
				return;
			} else {
				if (onwUrl == baselocation + lefturlArr[1]) {
					$("#" + lefturlArr[0] + '>dd').show();
					$("a[href='" + baselocation + lefturlArr[1] + "']").addClass("current");
					return;
				}
			}

		}
	}
}

function initSimpleImageUpload(btnId, urlId, valSet) {
	KindEditor.create('');
	var uploadbutton = KindEditor.uploadbutton({
		button : KindEditor('#' + btnId + '')[0],
		fieldName : "fileupload",
		url : uploadSimpleUrl + '&param=ucusercover',
		afterUpload : function(data) {
			if (data.error === 0) {
				var url = KindEditor.formatUrl(data.url, 'absolute');// absolute,domain
				KindEditor('#' + urlId + '').attr("src",
						staticImageServer + data.url);
				$("#" + urlId).show();
				// $('#'+valSet+'').val(url);
				upImagesUrl = url;
				setBodyImg('url("' + staticImageServer + '' + upImagesUrl
						+ '")', '#FAFAFA');
			} else {
				dialog('提示', '系统繁忙，请稍后再操作！', 1);
			}
		},
		afterError : function(str) {
			alert('自定义错误信息: ' + str);
		}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
}

// 公共弹出框
function dialog(content, num, param, url) {
	// 先删除之前的
	$(".d-tips-2").remove();
	$(".dialog-shadow").remove();
	$(".bg-shadow").remove();

	var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")), dialogEle = $(
			'<div id="dialog-shadow" class="dialog-shadow"><div class="dialog-ele"><div id="dcWrap" class="of">内容位置</div></div></div>')
			.appendTo($("body"));
	$.ajax({
		url : '/common/uc/dialog',
		type : "post",
		dataType : "text",
		async : false,
		data : {
			"dialog.index" : num,
			"dialog.content" : content,
			"dialog.params" : param,
			"dialog.url" : url
		},
		success : function(result) {
			$("#dcWrap").html(result);
		}
	});

	var dTop = (parseInt(document.documentElement.clientHeight, 10) / 2)
			+ (parseInt(document.documentElement.scrollTop
					|| document.body.scrollTop, 10)), dH = dialogEle.height(), dW = dialogEle
			.width(), timer = null, dClose;
	dialogEle.css({
		"top" : (dTop - (dH / 2)),
		"margin-left" : -(dW / 2)
	});
	dClose = function() {
		dialogEle.remove();
		oBg.remove();
	};

	$(".dialogClose").bind("click", dClose);
	$(".dtClose").bind("click", dClose);
}
// 公共弹窗关闭事件
function dialogClose() {
	$(".d-tips-2").remove();
	$(".dialog-shadow").remove();
	$(".bg-shadow").remove();
}