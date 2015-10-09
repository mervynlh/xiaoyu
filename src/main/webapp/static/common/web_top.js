$(function() {
	// 头部登陆未登录状态是否显示
	showLoginInfo();
	headerShow();
});
//联合登录,重新打开窗口
function oauthLogin(appType){
    window.location.href=baselocation+"/app/authlogin?appType="+appType;
}
/** 前台页面显示登录层* */
function showLoginInfo() {
		$.ajax({
			url : "/user/loginuser",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.success){
					$(".newsLi,.outLi,.userNameLi").removeClass('undis');
					var entity = result.entity;
					var showname = "";
                    if(isNotNull(entity)){
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
                        if(entity.avatar && isNotEmpty(entity.avatar)){
							//if ((entity.userType == 1 && entity.hideStatus == 0) || entity.userType != 1) {
								$("#cusImg").attr("src",staticImageServer+entity.avatar);
							//} else {
							//	if (entity.gender != 1) {
							//		$("#cusImg").attr("src","/static/edu/images/page/tea-nan.jpg");
							//	} else {
							//		$("#cusImg").attr("src","/static/edu/images/page/tea-nv.jpg");
							//	}
							//}
                        }else{
							if (entity.gender != 1) {
								$("#cusImg").attr("src","/static/edu/images/page/tea-nan.jpg");
							} else {
								$("#cusImg").attr("src","/static/edu/images/page/tea-nv.jpg");
							}
                        }
                        $(".login-infor").removeClass('undis');
                    }
				}else{
					$(".login").removeClass('undis');
					$(".login-infor").addClass('undis');
				}
			}
		});
}

// 继续购买
function goCorder() {
	if (isLogin()) {
		window.location.href = baselocation
				+ "/order?queryContractCondition.currentPage=1";
	} else {
		window.location.href = baselocation + '/login';
	}
}
// 提示消息
function showDialog(dTitle, conent) {
	var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")), dialogEle = $('<div class="dialog-shadow"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span class="d-s-head-txt">'
			+ dTitle
			+ '</span></h4><div id="dcWrap" class="pt20 pb20 pl20 pr20 of" style=""></div></div></div>')
			.appendTo($("body"));

	var dCont = [
			"<div class='d-tips-1'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"
					+ conent
					+ "</p><div class='tac mt20'><a href='javascript:void(0);' title='' class='blue-btn ml10 dClose'>确定</a></div><p class='tar mt20 c-666'></p></div>",
			"<div></div>", "<div></div>"];
	$("#dcWrap").html(dCont[0]);

	var dTop = (parseInt(document.documentElement.clientHeight, 10) / 2)
			+ (parseInt(document.documentElement.scrollTop
							|| document.body.scrollTop, 10)), dH = dialogEle
			.height(), dW = dialogEle.width();
	dialogEle.css({
				"top" : (dTop - (dH / 2)),
				"margin-left" : -(dW / 2)
			});
	$(".dClose").bind("click", function() {
				dialogEle.remove();
				oBg.remove();
			});
}
// 已完成支付
function goOrder() {
	window.location.href = baselocation + '/order';
}

// 录输入用户名或密码时，会清空错误提示信息
function userOrPwdChange(type, id) {
	$("#requestErrorID").html('');
	if (type == 1) {
		var userName = $("#" + id).val();
		if (userName != null && userName.trim() != '') {
			$("#userNameError").html('');
			return false;
		}
	} else if (type == 2) {
		var pwd = $("#" + id).val();
		if (pwd != null && pwd.trim() != '') {
			$("#passwordError").html('');
			return false;
		}
	}
}

/**
 * 头部点击哪一个，就改其中的样式
 */
function headerShow() {
	var index = '/index';
	var course = '/front/showcoulist';
	var teahcer = '/front/teacherlist';
	var article = '/front/articlelist';
	var atricleInfo = '/front/toArticle';
	var courseInfo = '/front/couinfo';

	var pageUrl = window.location;
	pageUrl = pageUrl + '';
	if (urlindexOf(pageUrl, index)) {
		$("#headerindex").addClass('current');
	} else if (urlindexOf(pageUrl, course) || urlindexOf(pageUrl, courseInfo)) {
		$("#headercourse").addClass('current');
	} else if (urlindexOf(pageUrl, teahcer)) {
		$("#headerteacher").addClass('current');
	} else if (urlindexOf(pageUrl, article) || urlindexOf(pageUrl, atricleInfo)) {
		$("#headerarticle").addClass('current');
	}
}
// str1是否包含str2
function urlindexOf(str1, str2) {
	return str1.indexOf(str2) != -1;
}

// commonjs
// 收藏本站
function addFavorite() {
	var _url = baselocation, _tit = '268xue在线';
	if (window.sidebar && window.sidebar.addPanel) {// 新版火狐不再支持window.sidebar.addPanel
		try {
			window.sidebar.addPanel(_tit, _url, "");
		} catch (e) {
			showDialog('提示消息',
					'您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	} else if (document.all) {// IE类浏览器
		try {
			var external = window.external;
			external.AddFavorite(_url, _tit);
		} catch (e) {
			showDialog('提示消息',
					'国内开发的360浏览器等不支持主动加入收藏\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	} else {
		showDialog('提示消息', '您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
	}
}
//购买商品
function BuyNow(courseId){
    //添加到购物车
	$.ajax({//验证课程金额
		url:baselocation+"/course/check/"+courseId,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message!='true'){
				dialog('提示信息',result.message,1);
			}else{
				window.location.href="/shopcart?goodsid="+courseId+"&type=1&command=addShopitem";
			}
		}
	})
	
}


// 加入收藏
function addFavorite(){
	var _url = baselocation,
	_tit = '268xue在线';
	if(window.sidebar && window.sidebar.addPanel) {//新版火狐不再支持window.sidebar.addPanel
		try{
			window.sidebar.addPanel(_tit, _url, "");
		}catch (e) {
			showDialog('提示消息','您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	}else if(document.all) {//IE类浏览器
		try{
		var external = window.external;
		external.AddFavorite(_url, _tit);
		}catch(e){
			showDialog('提示消息','国内开发的360浏览器等不支持主动加入收藏\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	}else {
		showDialog('提示消息','您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
	}
}

//课程收藏
 function house(couresId){
 		if(isLogin()){
 			$.ajax({
 				url:baselocation+"/front/addfavorites",
 				data:{'courseId':couresId},
 				type : "post", 
 				dataType : "json",
 				success: function (result){
 					if(result.message=="success"){
 						dialog('收藏提示',"收藏成功",4);
 					}
 					if(result.message=="false"){
 						dialog('收藏提示',"收藏失败",1);
 					}
 					if(result.message=="owned"){
 						dialog('收藏提示',"您已收藏过，请不要重复收藏",1);
 					}
 				}
 			});
 		}else{
 			//显示登录弹出窗口
 			dialog('登录','',3,'',1);
 			$("#userEmail").mailAutoComplete({
 			    boxClass: "out_box", //外部box样式
 			    listClass: "list_box", //默认的列表样式
 			    focusClass: "focus_box", //列表选样式中
 			    markCalss: "mark_box", //高亮样式
 			    autoClass: false,//不使用默认的样式
 			    textHint: true //提示文字自动隐藏
 			});
 		}
 	}

/**
 * 公共ajax登录方法
 * @param type 登录类型 1重新加载本页面,2跳转到首页，3跳转到传来的URL
 * @param url 要转向的路径
 */
function pageLogin(type,url){
    /*$("#requestErrorID").parent().parent().hide();*/
    var userName=$("#userMobile").val();
    var pwd = $("#userPassword").val();
    var autoThirty=$("#autoThirty").prop("checked")
    $("#passwordError").html('');
    $("#userNameError").html('');
    $("#requestErrorID").html('');
    if(!isNotEmpty(userName)){
        $("#userNameError").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">输入用户名</font>');
        return false;
    }
    if(!isNotEmpty(pwd)){
        $("#passwordError").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">请输入密码</font>');
        return false;
    }
    $.ajax({
        url : baselocation + "/dologin",
        data : {'userForm.mobile':userName,'userForm.password':pwd,"autoThirty":autoThirty},
        type : "post",
        dataType : "json",
        cache : false,
        async : false,
        success : function(result) {
            if(result.success){
                //如果登录成功，则刷新页面
                var forwordURL=getCookieFromServer("redirect");
                if (typeof(forwordURL) != "undefined" && forwordURL) {
                    DeleteCookie("forward");
                    window.location.href = forwordURL.replaceAll('"','');
                    return;
                }
                if(type==1){
                    window.location.reload();
                }else if(type==2){
                    window.location.href = baselocation+'/';
                }else if(type==3){
                    window.location=url;
                }
            }else{
                $("#requestErrorID").parent().parent().show();
                if(result.message=='formDataNot'){
                    $("#requestErrorID").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">用户不存在</font>');
                	dialog("用户不存在",9,"","");
                }else if(result.message=='inputIllegal'){
                    $("#requestErrorID").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">请不要输入非法数据</font>');
                	dialog("非法数据",9,"","");
                }else if(result.message=='freezed'){
                    //$("#requestErrorID").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">您的帐号已被冻结，请联系客服</font>');
                }else if(result.message=='false'){
                    //$("#requestErrorID").html('<em class="icon18 vam disIb newIcon18Cs"></em><font class="fsize12">用户名或者密码不正确</font>');
                	dialog("用户名或密码错误",9,"","");
                }
            }
        }
    });
}



// 根据年级、科目查询
function selectBySubjectMajor(subjectId, gradeId, majorId){
	$("#seartsubjectid").val(subjectId);
	$("#seartgradeid").val(gradeId);
	$("#seartmajorid").val(majorId);
	$("#formSearch").submit();
}
//公共弹出框
function dialog(content,num,param,url) {
	//先删除之前的
	$(".d-tips-2").remove();
	$(".dialog-shadow").remove();
	$(".bg-shadow").remove();
	
	var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")),
		dialogEle = $('<div id="dialog-shadow" class="dialog-shadow"><div class="dialog-ele"><div id="dcWrap" class="of">内容位置</div></div></div>').appendTo($("body"));
	$.ajax({
		url:'/common/dialog',
			type : "post",
   		dataType : "text",
   		async:false,
   		data:{"dialog.index":num,"dialog.content":content,"dialog.params":param,"dialog.url":url},
			success:function (result){
				$("#dcWrap").html(result);
   		}
	});
	
	var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
		dH = dialogEle.height(),
		dW = dialogEle.width(),
		timer = null,
		dClose;
	dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
	dClose = function() {dialogEle.remove();oBg.remove();};
	
	$(".dialogClose").bind("click", dClose);
	$(".dtClose").bind("click", dClose);
}
//公共弹窗关闭事件
function dialogClose(){
	$(".d-tips-2").remove();
	$(".dialog-shadow").remove();
	$(".bg-shadow").remove();
}