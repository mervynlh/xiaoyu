function deldialog_sns(){//弹出框关闭方法
	$(".dContent .dClose,.dCancel").click();
}

 $(function() {
	//顶部设置下拉菜单效果
	 $(".gt-setting").each(function() {
		 var _this = $(this);
		 var _timer = null;
		 _this.hover(function() {
	 			if (_this.children(".gt-subtopmenulist").is(":hidden")) {
	 				_timer = setInterval(function() {
	 					_this.addClass("gt-subml-show");
	 					_this.children(".gt-subtopmenulist").show();
	 				}, 200);
	 			};
	 		}, function() {
	 			clearInterval(_timer);
 				_this.removeClass("gt-subml-show");
 				_this.children(".gt-subtopmenulist").hide();
	 		});
	 	});
 	//搜索下拉展示效果
 	$(".gt-input").bind("focus", function() {
 		$(this).parent(".gt-search").addClass("gt-checked");
 	//	$(this).siblings(".gt-topmenulist").show();
 		//$(this).focus();
 	});
 	
 	$(".gt-input").bind("keyup", function() {
 		var key = $(this).val();
 		if(key!=null&&key.trim()!=""){
 			$.ajax({//通过会员名查找会员
 	 			type : "POST",
 	 			dataType : "json",
 	 			url:baselocation+"/search/cus",
 	 			data : {
 	 				"showName" : key
 	 			},
 	 			cache : true,
 	 			async : false,
 	 			success : function(result) {
 	 				var customerList = result.customerList;
 	 				var str ='';
                    var showkey='';
                    if(key.length>10){
                        showkey = key.substring(0,6)+"...";
                    }else{
                        showkey = key;
                    }
                    str += '<dl><dt><a href="javascript:submitsearch(\'weibo\',\''+key+'\')" class="c-blue">搜“<span class="c-red">'+showkey+'</span>”相关观点>></a></dt></dl>';
                    str += '<dl><dt><a href="javascript:submitsearch(\'blog\',\''+key+'\')" class="c-blue">搜“<span class="c-red">'+showkey+'</span>”相关博文>></a></dt></dl>';
                    str += '<dl><dt><a href="javascript:submitsearch(\'dis\',\''+key+'\')" class="c-blue">搜“<span class="c-red">'+showkey+'</span>”相关小组>></a></dt></dl>';
                    str += '<dl class="gt-s-user"><dt><a href="javascript:submitsearch(\'cus\',\''+key+'\')" class="c-blue">搜“<span class="c-red">'+showkey+'</span>”相关学友>></a></dt>';
                    str += '<dd><ol >';
 	 				if (customerList != null&& customerList.length>0) {
 	 					for(var i=0;i<customerList.length;i++){
 	 						var customer = customerList[i];
 	 						var geniusUser = customerList[i];
 	 						str += "<li>";
 	 						str +='<a href="'+baselocation +'/p/'+customer.cusId+'/home" target="_blank" class="gt-search-u-pics">';
 	 						if(geniusUser==null||geniusUser.avatar==""||geniusUser.avatar==null){//如果头像不为空则显示头像 为空则显示默认头像
 	 							str +='<img src="'+imagesPath+'/static/common/images/user_default.jpg" width="40" height="40" alt=""></a>';
 	 						}else{
 	 							str +='<img src="'+staticImageServer+''+geniusUser.avatar+'" width="40" height="40" alt=""></a>';
 	 						}
 	 						str +='<h6><a href="'+baselocation +'/p/'+customer.cusId+'/home" target="_blank" title="'+customer.nickname+'" class="c-555">'+customer.nickname+'</a></h6>';
 	 						str +='<p><span class="c-888">粉丝：'+customer.fansNum+'</span></p>';
 	 						str +='</li>';
 	 					}
 	 					str += '</ol></dd></dl>';
 	 					$("#searchCus").html(str);
 	 				}else{
 	 					str += '</ol></dd></dl>';
 	 					$("#searchCus").html(str);
 	 				}
 	 				$(".gt-input").siblings(".gt-topmenulist").show();
 	 			}
 	 		});
 		}else{
 			$(".gt-input").siblings(".gt-topmenulist").hide();
 			$.ajax({//查找热词
 	 			type : "POST",
 	 			dataType : "json",
 	 			url:baselocation+"/search/hotK",
 	 			data : {},
 	 			cache : true,
 	 			async : false,
 	 			success : function(result) {
 	 				var hotKeylist = result.hotKeylist;
 	 				var str ='';
 	 				var showkey = "";
	 				str += '<dl>';
 	 				if (result.hotKeylist != null&& hotKeylist.length>0) {
 	 					
 	 					for(var i=0;i<hotKeylist.length;i++){
 	 						var hotKey = hotKeylist[i];
 	 						var word = hotKey.word;
 	 						var showkey='';
 	 	 					if(word.length>20){
 	 	 						showkey = word.substring(0,20)+"...";
 	 	 					}else{
 	 	 						showkey = word;
 	 	 					}
 	 						str += '<dt><a href="javascript:submitsearch(\'weibo\',\''+word+'\')" class="c-blue">'+showkey+'</a></dt>';
 	 					}
 	 					str += '</dl>';
 	 					$("#searchCus").html(str);
 	 					$(".gt-input").siblings(".gt-topmenulist").show();
 	 				}
 	 				
 	 			}
 	 		});
 		}
 	});
// 	$('.gt-topmenulist').live('blur',function(){  
// 		$(".gt-input").parent(".gt-search").removeClass("gt-checked");
// 		$(".gt-input").siblings(".gt-topmenulist").hide();
// 	});  
 	/*$(".gt-input").bind("blur", function(e) {
		$(this).parent(".gt-search").removeClass("gt-checked");
 		//$(this).siblings(".gt-topmenulist").hide();
 	});*/
 	document.onclick = function(e) {
		$(".gt-input").siblings(".gt-topmenulist").hide();
		$(".gt-input").parent(".gt-search").removeClass("gt-checked");
	}
 	$(".gt-input").siblings(".gt-topmenulist").click(function(e) {
		stopFunc(e);
		$(".gt-input").parent(".gt-search").addClass("gt-checked");
	})
	$(".gt-input").click(function(e) {
		stopFunc(e);
	})
 	//发微博点击textarea后效果
 	$(".s-t-detail").bind("focus", function() {
 		$(this).parent(".send-txt-input").addClass("s-tarea-checked");
 		$(this).focus();
 	});
 	$(".s-t-detail").bind("blur", function() {
 		$(this).parent(".send-txt-input").removeClass("s-tarea-checked");
 	});
 	//表格隔行效果
 	$(".tab-challenge>table>tbody>tr:odd td").css("background-color" , "#F4FBFE");
 	$(".question-list>ul>li:odd").css("background-color" , "#FFFEF4");
 	//挑战详细显示
 	$(".tab-challenge>table>tbody>tr").each(function() {
 		var oM = $(this).children("td").find("p"),
 			oBtn = $(this).children("td").find(".chanllenge-more-btn");
 		oBtn.click(function() {
 			if (oM.is(":hidden")) {
 				oM.slideDown(200);
 				oBtn.addClass("open-c-m");
 				oBtn.children("tt").text("收起");
 			} else {
 				oM.slideUp(200);
 				oBtn.removeClass("open-c-m");
 				oBtn.children("tt").text("详情");
 			}
 		});
 	});
 	//排行榜序号效果
 	$(".groupph>li:lt(3) tt").addClass("lt3");
 	$(".wenzhangph>li:lt(3) tt").addClass("lt3");
 	$(".bozhuph>li:lt(3) tt").addClass("lt3");
 	$(".ranking-list3>li:lt(3) tt").addClass("lt3");
 	$(".cj-comment-list>dl:lt(3)>dt>span").addClass("lt3");
 	$(".WB-ranking-list>ul>li:lt(3) .order-num").addClass("lt3");
 	//微博排行显隐效果
 	$(".WB-ranking-list>ul>li").each(function() {
 		$(this).hover(function() {
 			$(this).addClass("WB-show-p").siblings().removeClass("WB-show-p");
 		}, function() {
 			$(this).addClass("WB-show-p");
 		})
 		/*$(this).mouseover(function() {
 			$(this).addClass("WB-show-p").siblings().removeClass("WB-show-p");
 		})
 		$(this).mouseout(function() {
 			
 		})*/
 	});
 	//评论排行效果
 	$(".cj-comment-list>dl").each(function() {
 		$(this).mouseover(function() {
 			$(this).addClass("cj-c-d-show").siblings().removeClass("cj-c-d-show");
 		});
 	});
 	//显隐成员信息层
 	$(".QM-list>ol>li").each(function() {
 		var oTime = null;
 		$(this).hover(function() {
 			var _this = $(this);
 				oTime = setInterval(function() {
 					_this.children(".QM-into-wrap").fadeIn(80);
 				}, 300);
 				_this.addClass("zIndex98");
 		}, function() {
 			var _this = $(this);
 			clearInterval(oTime);
 			_this.children(".QM-into-wrap").fadeOut(80);
 			_this.removeClass("zIndex98");
 		});
 	});
 	answerScroll();//左侧目录跟随底部时不固定（兼容小屏幕）
 	//左侧目录跟随
 	//fixedMenu();
 	//打开关闭左侧菜单导航
 });
//左侧目录跟随
function fixedMenu() {
	 if (window.XMLHttpRequst) {
		 return false;
	 } else {
		 var oMenu = $(".l-o-menu-fixed");
		 function lmFun() {
			var sTop = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
			if (sTop > 150) {
				oMenu.css({"position" : "fixed" , "top" : "205px"});
			} else {
				oMenu.css({"position" : "absolute" , "top" : "0px"});
			}
		 };
		 $(window).bind("scroll", lmFun);
		 lmFun();
	 };
};

 //事件监听
 function stopFunc(e) {
	 document.all ? event.cancelBubble = true : e.stopPropagation();
 }
 /*
 	公共弹出提示框&会话框方法
	dTitle : 标题；
	num    : 类型；
	num = 0 : 错误信息提示框;
	num = 1 : 正确信息提示框;
	num = 2 : 确认信息提示框; 
	num = 3 : 博客评论弹出框; 
	num = 4 : 发私信弹出框;
	num = 5 : 提示操作失败，渐出提示效果;
	num = 6 : 提示操作成，渐出提示效果;
 */
 var dialog_sns = function(dTitle,num) {
	 
	 //先删除之前的，防止倒计时未删除时重叠元素
	 $("#dialog-shadow").remove();
	 $(".black-mask").remove();
	 $("#dHead").remove();
	 $(".dClose").remove();
		
 	var winW = document.documentElement.clientWidth,
 		winH = document.documentElement.clientHeight,
 		bMask = $('<div class="black-mask"></div>').appendTo($("body"));
 		bMask.css({
 			background : "#000000",
 			height : winH,
 			width : winW,
 			position : "fixed",
 			top : "0px",
 			left : "0px",
 			opacity : "0.3",
 			filter : "Alpha(opacity=30)",
 			zIndex : "10001"
 		});

 	var dCommEle = $('<div id="dialog-shadow" class="dialog-shadow"><div class="dContent"><header id="dHead" class="dHead"><span class="c-333 ml20">提示信息</span></header><a href="javascript:void(0)" title="关闭" class="dClose">&nbsp;</a><div id="dcWrap" class="dcWrap">提示</div></div></div>').appendTo($("body")).addClass("animate-enter animated flyIn5");
	 	$.ajax({
			url:baselocation+"/common/snsdialog",
			data:{"dialog.title":dTitle,
				  "dialog.index":num
				  },
			type:"post",
			dataType:"text",
			async:false,
			success:function(result){
				$("#dcWrap").html(result);
			}
		});
 	/*var dContArr = [
	 		'<section class="dca dca0"><i class="icon26 dError">&nbsp;</i><span class="fsize14 c-555 ml5">'+dTitle+'</span>'+
	 		'<div class="mt20 mb10 tac"><a href="" title="" class="comm-btn-orange queding0"><span>确定</span></a></div></section>',
	 		'<section class="dca dca1"><i class="icon26 dRight">&nbsp;</i><span class="fsize14 c-555 ml5">'+dTitle+'</span>'+
	 		'<div class="mt20 mb10 tac"><a href="" title="" class="comm-btn-green queding1"><span>确定</span></a></div></section>',
	 		'<section class="dca dca2"><i class="icon26 dAsk">&nbsp;</i><span class="fsize14 c-555 ml5">'+dTitle+'</span>'+
	 		'<div class="mt20 mb10 tac"><a href="" title="" class="comm-btn-orange queding2"><span>确定</span></a><a href="javascript:void(0)" class="dCancel comm-btn-gray ml10"><span>取消</span></a></div></section>',
 			'<section class="dca dca3">'+
 			'<section><h6><span class="fsize14 c-555">引用：</span></h6></section>'+
 			'<section class="yinY-txt"><p id="yingyong"></p></section>'+
 			'<section class="mt10"><h6><span class="fsize14 c-555">评论：</span></h6></section>'+
 			'<section class="mt10"><textarea name="" class="dTextarea" id="replyContent"></textarea></section>'+
 			'<section class="mt10 tar mb20"><span class="mr10 vam"><tt class="c-red fsize12" id="wenzi" style="display: none;">您还没输入文字！</tt></span><a class="send-btn-wrap" title="" href="javascript:void(0)" id="reply"><span>评论</span></a></section>'+
 			'</section>',
 			'<section class="dca dca4">'+ 
 			'<section><h6><span class="fsize14 c-555">收信人：</span><span class="c-555 cusName"></span></h6></section>'+
 			'<section class="mt10"><h6><span class="fsize14 c-555">信件内容：</span></h6></section>'+
 			'<section class="mt10"><textarea name="" class="dTextarea" id="letterTextarea"></textarea></section>'+
 			'<section class="mt10 tar mb20"><span class="mr10 vam"><tt class="c-red fsize12"><span id="wenzistr"></span><span id="wenzinum"></span>字</tt></span><a class="send-btn-wrap" title="发信" href="javascript:void(0)" onclick="sendLetter()"><span>发信</span></a></section>'+
 			'</section>',
 			'<section class="dca dca5"><i class="icon26 dFade">&nbsp;</i><span class="fsize14 c-555 ml5">'+dTitle+'</span>'+
	 		'</section>',
	 		'<section class="dca dca5"><i class="icon26 dRight"></i><span class="fsize14 c-555 ml5">'+dTitle+'</span>'+
	 		'</section>','<section class="dca dca7"><span class="fsize14 c-555 ml5">备注：<input type="text" value="" id="queding7"/></span>'+
	 		'<div class="mt20 mb10 tac"><a href="" title="" class="comm-btn-green queding7"><span>确定</span></a></div></section>'
 		];

 	$("#dcWrap").html(dContArr[num]);*/

	var sTop = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
 	var	dTop = (parseInt(winH, 10)/2) + (parseInt(sTop, 10)),
 	
	dcW = $(".dca" + num).width() + 46,
	dcH = dCommEle.height() + 6,
	dHead = $(".dHead");
	dCommEle.css({
		position : "absolute",
		left : "50%",
		top : dTop - (dcH/2),
		marginLeft : -(dcW/2),
		zIndex : "10001"
	});
	dHead.css("width" , dcW + "px");
	closeFun1 = function() {
		dCommEle.hide();
		bMask.hide();
	};
	closeFun = function() {
		dCommEle.removeClass("animate-enter animated flyIn5").remove();bMask.remove();
	};
	if (num == 5||num == 6) {
		$(".black-mask").remove();
		$("#dHead").remove();
		$(".dClose").remove();
		setTimeout(function() {
			dCommEle.removeClass("animate-enter animated flyIn5").fadeOut(1800);
			bMask.fadeOut(1800);
		}, 500);
		setTimeout(function() {
			dCommEle.removeClass("animate-enter animated flyIn5").remove();
			bMask.remove();
		}, 2500);
	};
 	$(".dContent .dClose,.dCancel").click(function() {closeFun();});
 };
 var closeFun ;
 var closeFun1;
//拖动弹出框&提示框方法
function dragFun() {
	var eDrag = document.getElementById("dialog-shadow"),
	    oDrag = document.getElementById("dHead"),
        bDrag = false,
        disX = disY = 0;
	oDrag.onmousedown = function(event) {
		var event = event || window.event;
		bDrag = true;
		disX = event.clientX - eDrag.offsetLeft;
		disY = event.clientY - eDrag.offsetTop;
		this.setCapture && this.setCapture();
		return false;
	};
	document.onmousemove = function(event) {
		if(!bDrag) return;
		var event = event || window.event;
		var dL = event.clientX - disX;
		var dT = event.clientY - disY;
		var maxL = document.documentElement.clientWidth + (document.documentElement.scrollLeft || document.body.scrollLeft) - eDrag.offsetWidth;
		var maxT = document.documentElement.clientHeight + (document.documentElement.scrollTop || document.body.scrollTop) - eDrag.offsetHeight;
		dL = dL < 0 ? 0 : dL;
		dL = dL > maxL ? maxL : dL;
		dT = dT <0 ? 0 : dT;
		dT = dT > maxT ? maxT : dT;
		eDrag.style.marginTop = eDrag.style.marginLeft = 0;
		eDrag.style.left = dL + "px";
		eDrag.style.top = dT + "px";
		return false;
	};
	document.onmouseup = window.onblur = oDrag.onlosecapture = function() {
		bDrag = false;
		oDrag.releaseCapture && oDrag.releaseCapture();
	};
};
function submitsearch(tab,keyword){//搜索找人和微博的方法
	$("#searchDaotab").val(tab);//搜索分类
	$("#searchDaokeyword").val(keyword);//搜索关键词
	$("#search").submit();//提交表单
}
function submitsearchkey(tab){//搜索找人和微博的方法
	$("#searchDaotab").val(tab);//搜索分类
	var keyword = $(".searchKey").val();
	$("#searchDaokeyword").val(keyword);//搜索关键词
	$("#search").submit();//提交表单
}


//社区左侧导航定位
var leftUrlAndClassArr = [
    'leftdl_sns_index@/u/home',
    'leftdl_sns_weibo@/weibo',
    'leftdl_sns_friend@/friend',
    'leftdl_sns_sug@/sug',
    'leftdl_sns_dis@/dis',
    'leftdl_sns_letter@/letter',
    'leftdl_sns_blog@/blog'
];

/**
 * 个人中心打开关闭左侧菜单导航
 */
function lMenu() {
	/*$(".u-menu-list>dl>dt").each(function() {
		$(this).click(function() {
			if (!$(this).next("dd").is(":hidden")) {
				$(this).addClass("current");
				$(this).next("dd").slideUp(100);
				$(this).parent().siblings("dl").children("dt").removeClass("current");
			} else {
				$(this).removeClass("current");
				$(this).next("dd").slideDown(100);
				$(this).parent().siblings("dl").children("dd").slideUp(100);
				$(this).parent().siblings("dl").children("dt").addClass("current");
			};
		});
	});*/

};
/**
 * 社区左侧菜单栏快到窗口底部时控制不固定（兼容小屏幕）
 */
function answerScroll() {
	var marginBot = 0;
	var aS = function() {
		if (document.compatMode === "CSS1Compat") {
			marginBot = document.documentElement.scrollHeight - (document.documentElement.scrollTop + document.body.scrollTop) - document.documentElement.clientHeight;
		} else {
			marginBot = document.body.scrollHeight - document.body.scrollTop - document.body.clientHeight;
		};
		if (marginBot <= 220) {
			$(".W-main-l-fixed").css({"position" : "absolute", "top" : "10px"});
		} else {
			$(".W-main-l-fixed").css({"position" : "fixed", "top" : "70px"});
		};
	};
	$(window).bind("scroll" , aS);
}
/**
 * 社区左则菜单栏伸展控制
 */
function menuControl(){
    var onwUrl =window.location+'';
    for(var i=0;i<leftUrlAndClassArr.length;i++){
        var lefturlArr = leftUrlAndClassArr[i].split('@');
        if(onwUrl.indexOf(lefturlArr[1])!=-1){
            $('a[id^=leftdl_sns_]').removeClass("current");
            $("#"+lefturlArr[0]).addClass("current");
        }else{
        }
    }

}

/**
 * 左侧目录菜单定位
 * @returns {Boolean}
 */
function lMenuFun() {
	if(window.XMLHttpRequst) {
		return false;
	} else {
		var lMenu = $(".u-m-left");
		function lmFun() {
			var sTop = document.body.scrollTop || document.documentElement.scrollTop;
			var clientTop =document.body.clientHeight;
			var poorValue=clientTop-sTop;
			if(sTop > 90/*&&poorValue>768*/) {
				lMenu.css({"position" : "fixed" , "top" : "60px"});
			} else {
				lMenu.css({"position" : "absolute" , "top" : "-154px"});
			}
		};
		$(window).bind("scroll" , lmFun);
		lmFun();
	}
}

