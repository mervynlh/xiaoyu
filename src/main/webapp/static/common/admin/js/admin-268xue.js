	$(function() {
		mainContentHeight();
		navFun();
		leftMenu();
		tdFun();
	});

	$(window).resize(function() {
		navFun();
	});

	function kill_ie6() {
		if (!-[1,] && !window.XMLHttpRequest) {
			alert("您的浏览器版本太低，快升级到IE7及以上版本来访问！");
            window.location.href="/static/common/kill-ie.html";
			return false;
		}
	}

	function mainContentHeight() {	//兼容不同屏幕显示
		var winH = parseInt(document.documentElement.offsetHeight, 10) + parseInt(document.documentElement.scrollTop || document.body.scrollTop),
			mc = $("#main_content"),
			mcH = mc.height();
		if ((mcH + 108) < winH) {	//这里108px是header的高+1px边线
			mc.css({"min-height" : winH-148 , "_height" : winH-148});
		} else {
			mc.css({"min-height" : "auto" , "_height" : "auto"});
		}
	}

	function leftMenuFixed() {	//左侧目录跟随
		var mTop = document.body.scrollTop || document.documentElement.scrollTop;
		if (!-[1,] && !window.XMLHttpRequest) {	//判断是否IE6
			return false;
		} else {
			var lMenu = $("#left_menu");
			function lmfFun() {
				var mTop = document.body.scrollTop || document.documentElement.scrollTop;
				if (mTop > 108) {
					lMenu.css({"position" : "fixed" , "top" : 0});
				} else {
					lMenu.css({"position" : "absolute" , "top" : 118 + "px"});
				};
			}
			$(window).bind("scroll", lmfFun);
			lmfFun();
		}
	}
	//leftMenuFixed();

	function slideScroll() {	//头部导航菜单滚动
		var prev = $(".prev"),
			next = $(".next"),
			oUl = $(".navList>ul"),
			w = oUl.find("li").outerWidth(true),
			l = oUl.find("li").length;
			oUl.css("width" , w * l + "px");
		//click left
		prev.click(function() {
			if(!oUl.is(":animated")) {
				oUl.animate({"margin-left" : -w}, function() {
					oUl.find("li").eq(0).appendTo(oUl);
					oUl.css("margin-left" , 0);
				});
			};
		});
		//click right
		next.click(function() {
			if(!oUl.is(":animated")) {
				oUl.find("li:last").prependTo(oUl);
				oUl.css("margin-left" , -w);
				oUl.animate({"margin-left" : 0});
			};
		});
	}

	function navFun() {
		var winW = parseInt(document.documentElement.clientWidth, 10) + parseInt(document.documentElement.scrollLeft || document.body.scrollLeft, 10),
			nlW = winW - 400,
			ulW = $(".navList>ul").width(),
			oPN = $('<a href="javascript: void(0)" title="左" class="prev">&nbsp;</a><a href="javascript: void(0)" title="右" class="next">&nbsp;</a>');
		$(".navList").css("width" , nlW);
		if (nlW > ulW) {
			$(".prev,.next").remove();
		} else {
			oPN.appendTo(".navWrap");	
			slideScroll();
		}
	}
	//navFun();
	function leftMenu() {
		$("#lMenu dl dt").each(function() {
			$(this).click(function() {
				if (!$(this).hasClass("current")) {
					$(this).addClass("current").children(".sjWrap").show();
					$(this).next("dd").slideDown(150);
				} else {
					$(this).removeClass("current").children(".sjWrap").hide();
					$(this).next("dd").slideUp(150);
				}
				return false;
			});
		});
	}

	function tdFun() {
		$("#tabS_01>tr:nth-child(2n)").children("td").css({"background-color" : "#F9F9F9"});
		$("#tabS_02>tr").hover(function() {$(this).toggleClass("hover");});
		$(".testWrapList>li:nth-child(2n)").css({"background-color" : "#EFEFEF"});
		var t_set = $('');
		$(".testPagerTitle>h5").hover(function() {
			$(this).find("span").show();
		}, function() {
			$(".testPagerTitle .czBtn").hide();
		})
		$(".testPagerTitle .czBtn").hide();
	}

	function dialog() {
		var mask = $('<div></div>').appendTo($("body")).addClass("modal-backdrop fade in");
		var oEl = $('<div></div>').appendTo($('body')).addClass("modal fade in");
		var doc = $('<div class="modalWrap"><div class="modal_head"><span class="fr"><a href="javascript:void(0)" title="关闭" class="icon14 closeBtn"></a></span><h4><span>提示信息</span></h4></div><div class="modal_body" id="modalCont">文字位置</div><div class="modal_foot clearfix"><button class="btn btn-success">确定</button><button class="btn ml10 cancelBtn">取消</button></div></div>').prependTo($(".modal"));

		$(".closeBtn,.cancelBtn,.modal-backdrop").bind("click", function() {$(".modal").removeClass("in").remove();$(".modal-backdrop").removeClass("in").remove();});
	}
	
	function kpointDialog(kpoints) {
		var mask = $('<div id="backdrop_dialog"></div>').appendTo($("body")).addClass("modal-backdrop fade in");
		var oEl = $('<div id="course_id_dialog"></div>').appendTo($('body')).addClass("modal fade in");
		var doc = $('<div class="modalWrap"><div class="modal_head"><span class="fr"><a href="javascript:void(0)" title="关闭" class="icon14 closeBtn"></a></span><h4><span>请输入课程id</span></h4></div><div class="modal_body" id="modalCont"><input id="copyCourseId" type="text" style="width:80px;"/><input id="copyKpoints" type="hidden" value="'+kpoints+'"/></div><div class="modal_foot clearfix"><button class="btn btn-success" onclick="copyKpoint()">确定</button><button class="btn ml10 cancelBtn">取消</button></div></div>').prependTo($(".modal"));

		$(".closeBtn,.cancelBtn,.modal-backdrop").bind("click", function() {$(".modal").removeClass("in").remove();$(".modal-backdrop").removeClass("in").remove();});
	}
