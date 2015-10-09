/*  
Version:1.1
Author:westdrug
Update @ 2015-04-02
*/

$(function() {
	areaFun(); //首页选择头部地域下拉效果
	aSortFun(); //全部课程分类一二级分类效果
})
//首页选择头部地域下拉效果
function areaFun() {
	$(".area-wrap").hover(function() {
		$(this).addClass("hover");
		$(".addRessCont").stop().slideDown(150);
	}, function() {
		$(this).removeClass("hover");
		$(".addRessCont").stop().slideUp(150);
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

	//右侧悬浮方法
	function fixedR() {
		$(".onlineC-item>ul>li>div").each(function() {
			var _this = $(this);
			_this.hover(function() {
				_this.find(".onlineC").stop().animate({"width" : _this.attr("name") + "px"}, 300);
			}, function() {
				_this.find(".onlineC").stop().animate({"width" : "0px"}, 300);
			});
		});
	}

//选项卡公共方法
	function cardChange(oTitle, oCont, current) {
		var oTitle = $(oTitle),
			oCont = $(oCont),
			_index;
		oTitle.click(function() {
			_index = oTitle.index(this);
			$(this).addClass(current).siblings().removeClass(current);
			oCont.eq(_index).show().siblings().hide();
		}).eq(0).click();
	}
// 模拟 select 下拉控件
function selFun(op) {
	var _sel = $(op).find(".selectUI"),
		_timer = null;
	_sel.each(function() {
		var _this = $(this),
			_selU = _this.children(".job-select"),
			_opt = _this.find(".j-s-option"),
			_sTxt = _selU.find(".j-s-defalt").find("span"),
			_oTxt = _opt.find(".j-s-o-box").find("p"); 
		_this.hover(function() {
			if (_opt.is(":hidden")) {
				_selU.addClass("selected");
				_timer = setInterval(function() {
					_opt.stop().slideDown(50);
				}, 100)
			};
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

//星级评论
	function cStar() {
		var sWrap = $(".cStar"),
			sLi = sWrap.find("li"),
			sTxt = sWrap.find("span"),
			i = iScore = iStar = 0;
		sLi.hover(function() {
			var _index = $(this).index() + 1;
			curPoint(_index);
		}, function() {
			curPoint();
		});
		sLi.click(function() {
			var _index = $(this).index() + 1;
			var sT = $(this).find("a").attr("title");
			curPoint(_index);
			iStar = $(this).index() + 1;
			sTxt.text(sT);
		});
		function curPoint(cNum) {
			if (cNum) {
				iScore = cNum;
			} else {
				iScore = iStar;
			};
			for (i=0; i<sLi.length; i++) {
				if (i<iScore) {
					sLi.eq(i).addClass("current");
				} else {
					sLi.eq(i).removeClass("current");
				};
			};
		}

	}
	function cStarf() {
		var sWrap = $(".cStar-1"),
			sLi = sWrap.find("li"),
			sTxt = sWrap.find("span"),
			i = iScore = iStar = 0;
		sLi.hover(function() {
			var _index = $(this).index() + 1;
			curPoint(_index);
		}, function() {
			curPoint();
		});
		sLi.click(function() {
			var _index = $(this).index() + 1;
			var sT = $(this).find("a").attr("title");
			curPoint(_index);
			iStar = $(this).index() + 1;
			sTxt.text(sT);
		});
		function curPoint(cNum) {
			if (cNum) {
				iScore = cNum;
			} else {
				iScore = iStar;
			};
			for (i=0; i<sLi.length; i++) {
				if (i<iScore) {
					sLi.eq(i).addClass("current");
				} else {
					sLi.eq(i).removeClass("current");
				};
			};
		}

	}
	function cStare() {
		var sWrap = $(".cStar-2"),
			sLi = sWrap.find("li"),
			sTxt = sWrap.find("span"),
			i = iScore = iStar = 0;
		sLi.hover(function() {
			var _index = $(this).index() + 1;
			curPoint(_index);
		}, function() {
			curPoint();
		});
		sLi.click(function() {
			var _index = $(this).index() + 1;
			var sT = $(this).find("a").attr("title");
			curPoint(_index);
			iStar = $(this).index() + 1;
			sTxt.text(sT);
		});
		function curPoint(cNum) {
			if (cNum) {
				iScore = cNum;
			} else {
				iScore = iStar;
			};
			for (i=0; i<sLi.length; i++) {
				if (i<iScore) {
					sLi.eq(i).addClass("current");
				} else {
					sLi.eq(i).removeClass("current");
				};
			};
		}

	}
//公共弹出框
	function dialog1(num) {
		//先删除之前的
		$(".d-tips-2").remove();
		$(".dialog-shadow").remove();
		$(".bg-shadow").remove();
		
		var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")),
			dialogEle = $('<div id="dialog-shadow" class="dialog-shadow"><div class="dialog-ele"><div id="dcWrap" class="of">内容位置</div></div></div>').appendTo($("body"));

		var dCont = [
			//快速登录弹出框
			'<div class="d-tips-0">' +
			'<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">用户登录</span></h4>' +
			'<div class="mt40 mb40 pl90 pr90">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li><p id="requestErrorID"></p></li>' +
			'<li><label class="vam">用户名：</label>' +
			'<span style="display:inline-block;position:relative;">' +
			'<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>' +
			'<input type="text" id="" value="" class="lTxt">' +
			'</span>' +
			'<p class="disIb ml5 c-orange" id="userNameError"></p></li>' +
			'<li class="mt30"><label class="vam">密&nbsp;&nbsp;&nbsp;码：</label><input type="password" class="lTxt" id="userPassword"><p class="disIb ml5 c-orange" id="passwordError"></p></li>' +
			'<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-orange" title="忘记密码？" href=""><u>忘记密码？</u></a></span><span class="ml50"><a class="c-master" title="还没账号，去注册！" target="_blank" href=""><u>还没账号，去注册！</u></a></span></li>' +
			'<li class="mt30"><label class="vam">&nbsp;</label><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>' +
			'<li class="mt10 tac"><p class="hLh30 line2"><span class="vam c-666">可以使用以下方式快速登录</span></p>' +
			'<div class="mt30"><a title="QQ账号登录" class="vam ml10 mr10" href=""><img width="40" height="40" src="img/qq.png" class="vam" alt="QQ账号登录"></a><a title="微信账号登录" class="vam ml10 mr10" href=""><img width="40" height="40" src="img/wx.png" class="vam" alt="微信账号登录"></a><a title="微信账号登录" class="vam ml10 mr10" href=""><img width="40" height="40" src="img/xl.png" class="vam" alt="微信账号登录"></a></div></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//QQ登录
			'<div class="d-tips-1">' +
			'<h4 class="d-s-head pr" style="width:545px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">QQ登录</span></h4>' +
			'<div class="mt40 mb40 pl70 pr90">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li><p id="requestErrorID"></p></li>' +
			'<li><label class="vam">Q&nbsp;&nbsp;&nbsp;Q：</label>' +
			'<span style="display:inline-block;position:relative;">' +
			'<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>' +
			'<input type="text" id="" value="" class="lTxt">' +
			'</span>' +
			'<p class="disIb ml5 c-orange" id="userNameError"></p></li>' +
			'<li class="mt30"><label class="vam">密&nbsp;&nbsp;&nbsp;码：</label><input type="password" class="lTxt" id="userPassword"><p class="disIb ml5 c-orange" id="passwordError"></p></li>' +
			'<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-orange" title="忘记密码？" href=""><u>忘记密码？</u></a></span></li>' +
			'<li class="mt30"><label class="vam">&nbsp;</label><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//修改上课时间
			'<div class="d-tips-2">' +
			'<h4 class="d-s-head pr" style="width:510px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb40 pl90 pr50">' +
			'<div><div class="xgsksj">' +
			'<a href="" class="fl xgsksj-tit"><img src="img/pic/tea-nv.jpg" alt="" width="70" height="70" class="dis"><p class="tac fsize14 c-666">老师：万全</p></a>'+
			'<div class="xgsksj-boy ml30 fl"><p class="fsize14 c-333 f-fM">课程名称：小学五年级数学</p>' +
			'<p><tt class="fsize14 f-fM c-666 vam">一对一</tt><tt class="fsize14 f-fM c-666 vam ml30">小学五年级数学</tt></p>' +
			'<p class="fsize14 c-666 f-fM">线上授课</p>' +
			'</div>' +
			'<div class="clear"></div>' +
			'<div class="xgsksj-bot mt20"><ul>' +
			'<li><span class="vam">请选择上课日期</span><label class="vam"><select name="" id=""><option value="">2015-08-20</option><option value="">2015-08-21</option><option value="">2015-08-22</option></select></label><p class="c-red-1 pl160 fsize12 mt5">*请选择上课时间</p></li>' +
			'<li><span class="vam">请选择上课时间</span><label class="vam"><select name="" id=""><option value="">10:00-12:00</option><option value="">06:00-07:00</option><option value="">08:00-09:00</option></select></label></li>' +
			'<li class=""><a href="" class="u-btn u-xgsksj-btn">提交修改</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'</ul></div>' +
			'</div></div>' +
			'</div>',
			//提现
			'<div class="d-tips-3">' +
			'<h4 class="d-s-head pr" style="width:610px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">提现</span><span class="ml20 unFw"><tt class="fsize12 c-999 f-fM vam">你可以提现的金额为：</tt><tt class="fsize18 c-master f-fM vam">￥422.00</tt><tt class="fsize12 c-999 f-fM vam">元</tt></span></h4>' +
			'<div class="mt40 mb40 pl90 pr50">' +
			'<ol class="xgsksj-bot">' +
			'<li><span class="tx-list-tit">提现金额：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li><span class="vam">选择银行：</span><label class="vam"><select name="" id=""><option value="">中国银行</option><option value="">中国邮政</option><option value="">中国华夏</option></select></label></li>' +
			'<li><span class="tx-list-tit">银行卡号：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li><span class="tx-list-tit">开户行名称：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li><span class="tx-list-tit">开户人姓名：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li><span class="tx-list-tit">账号(注册人手机号)：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li><span class="tx-list-tit">手机验证码：</span><label class="tx-list-bod"><input type="text" /></label></li>' +
			'<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交修改</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'<li class="pt30"><p class="c-red">温馨提示：用户您好！提现金额根据不同银行一般为1-3个工作日</p></li>' +
			'</ol>' +
			'<div>' +
			'</div></div>' +
			'</div>',
			//评论框
			'<div class="d-tips-4">' +
			'<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb40 pl90 pr50">' +
			'<ol class="all-ts-bot">' +
			'<li><p class="mb20 fsize16 c-master f-fM">亲！请输入您要评论的内容：</p><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam">268教育</textarea></li>' +
			'<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交评论</a><a href="" class="u-btn u-xgsksj-btn">取消评论</a></li>' +
			'</ol>' +
			'<div>' +
			'</div></div>' +
			'</div>',
			//微信登录
			'<div class="d-tips-5">' +
			'<h4 class="d-s-head pr" style="width:490px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">微信登录</span></h4>' +
			'<div class="mt40 mb40 pl90 pr90">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li class="tac"><img src="img/pic/ewm-wx.jpg" alt="" class="ewm"><p class="fsize16 c-666 f-fM mt20">扫一扫,安全登录</p></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//新浪登录
			'<div class="d-tips-6">' +
			'<h4 class="d-s-head pr" style="width:720px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">新浪登录</span></h4>' +
			'<div class="mt40 mb40 pl70 pr90">' +
			'<div style="">' +
			'<div class="fl mr30 pr20 tac xlewm"><img src="img/pic/ewm-xl.jpg" alt=""><p class="mt20 fsize16 c-999 f-fM">扫描新浪二维码，安全登录微博</p></div>' +
			'<ul class="l-r-w-Inpt fl pt20">' +
			'<li><p id="requestErrorID"></p></li>' +
			'<li><label class="vam">新浪账号：</label>' +
			'<span style="display:inline-block;position:relative;">' +
			'<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>' +
			'<input type="text" id="" value="" class="lTxt" style="width:200px;">' +
			'</span>' +
			'<p class="disIb ml5 c-orange" id="userNameError"></p></li>' +
			'<li class="mt30"><label class="vam">新浪密码：</label><input type="password" class="lTxt" id="userPassword" style="width:200px;"><p class="disIb ml5 c-orange" id="passwordError"></p></li>' +
			'<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-orange" title="忘记密码？" href=""><u>忘记密码？</u></a></span></li>' +
			'<li class="mt30"><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>' +
			'</ul>' +
			'<div class="clear"></div>' +
			'</div>' +
			'</div>',
			//约课弹框
			'<div class="d-tips-7">' +
			'<h4 class="d-s-head pr" style="width:620px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb40 pl30">' +
			'<div><div class="xgsksj">' +
			'<a href="" class="fl xgsksj-tit pl90"><img src="img/pic/tea-nv.jpg" alt="" width="70" height="70" class="dis"></a>'+
			'<div class="xgsksj-boy ml30 fl"><p class="fsize14 c-333 f-fM">郭襄</p>' +
			'<p><tt class="fsize14 f-fM c-666 vam">女</tt><tt class="fsize14 f-fM c-666 vam ml50">小学五年级数学</tt><tt class="fsize14 f-fM c-666 vam ml50">240元/小时</tt></p>' +
			'<p class="fsize14 c-666 f-fM">上课区域：六里桥，人民大学周边</p>' +
			'</div>' +
			'<div class="clear"></div>' +
			'<div class="xgsksj-bot mt20"><ul>' +
			'<li><span class="vam yltk-span"><tt class="fsize14 c-org f-fM mr5">*</tt><tt class="fsize14 c-666 f-fM">期望上课时间：</tt></span>' +
			'<label class="vam"><select name="" id="" class="yltk-select"><option value="">选择日期</option><option value="">2015-08-21</option><option value="">2015-08-22</option></select><select name="" id="" class="yltk-select"><option value="">选择开始时间</option><option value="">2015-08-21</option><option value="">2015-08-22</option></select><select name="" id="" class="yltk-select"><option value="">选择结束时间</option><option value="">2015-08-21</option><option value="">2015-08-22</option></select></label></li>' +
			'<li><span class="vam yltk-span"><tt class="fsize14 c-666 f-fM ml40">备注信息：</tt></span><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam">268教育</textarea></li>' +
			'<li class="pt20 tac"><a href="" class="u-btn u-xgsksj-btn">提交约课</a></li>' +
			'</ul></div>' +
			'</div></div>' +
			'</div>',
			//正确或成功
			'<div class="d-tips-8">' +
			'<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb50">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li class="tac"><img src="img/tc.1.png" alt="" class="zqcg vam"><tt class="fsize20 c-master f-fM vam ml20">恭喜您！支付成功</tt></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//错误或失败
			'<div class="d-tips-9">' +
			'<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb50">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li class="tac"><img src="img/tc.2.png" alt="" class="zqcg vam"><tt class="fsize20 c-red-1 f-fM vam ml20">对不起！支付失败</tt></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//警告或提示
			'<div class="d-tips-10">' +
			'<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb50">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li class="tac"><img src="img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-666 f-fM vam ml20">提示：请输入正确格式</tt></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//退课弹框
			'<div class="d-tips-11">' +
			'<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>' +
			'<div class="mt40 mb40 pl90 pr50">' +
			'<ol class="all-ts-bot">' +
			'<li><p class="mb20 fsize16 c-666 f-fM">请输入退课原因：</p><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam">268教育</textarea></li>' +
			'<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'</ol>' +
			'<div>' +
			'</div></div>' +
			'</div>',
			//添加优惠劵
			'<div class="d-tips-12">' +
			'<h4 class="d-s-head pr" style="width:600px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加优惠券</span></h4>' +
			'<div class="mt40 mb40 pl50 pr50">' +
			'<div class="tjyhj-bot mt20"><ul>' +
			'<li><span class="vam tjyhj-span"><tt class="fsize14 c-666 f-fM">面值：</tt></span>' +
			'<label class="vam tjyhj"><input type="text" /><tt class="fsize14 f-fM c-999 ml10 vam">元</tt><tt class="fsize12 f-fM c-red-1 ml30 vam">请输入1-2000元以内的整数金额</tt></label></li>' +
			'<li><span class="vam tjyhj-span"><tt class="fsize14 c-666 f-fM">张数：</tt></span>' +
			'<label class="vam tjyhj"><input type="text" /><tt class="fsize14 f-fM c-999 ml10 vam">张</tt><tt class="fsize12 f-fM c-red-1 ml30 vam">不能超过100张</tt></label></li>' +
			'<li><span class="vam tjyhj-span"><tt class="fsize14 c-666 f-fM">使用条件：</tt></span>' +
			'<label class="vam tjyhj"><input type="radio" name="queryUser.gender" id="gender0" value="1" placeholder="" style="width:16px;" class="vam"><tt class="fsize14 c-333 f-fM vam mr50">无限制</tt><input type="radio" name="queryUser.gender" id="gender0" value="1" placeholder="" style="width:16px;" class="vam"><input type="text" /><tt class="fsize14 f-fM c-333 ml10 vam">元以上可用</tt></label></li>' +
			'<li><span class="vam tjyhj-span"><tt class="fsize14 c-666 f-fM">备注信息：</tt></span><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam">最多30个字</textarea></li>' +
			'<li class="ml50 pt30 tac"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'</ul></div>' +
			'</div>' +
			'</div>',
			//添加折扣
			'<div class="d-tips-13">' +
			'<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加折扣</span></h4>' +
			'<div class="mt40 mb40 pl90 pr90">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li><p id="requestErrorID"></p></li>' +
			'<li><label class="vam">简介：</label>' +
			'<span style="display:inline-block;position:relative;">' +
			'<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>' +
			'<input type="text" id="" value="" class="lTxt" style="width:200px">' +
			'</span>' +
			'<p class="disIb ml5 c-red-1 fsize12 f-fM" id="userNameError">30字以内</p></li>' +
			'<li class="mt30"><label class="vam">课时：</label><input type="password" class="lTxt" id="userPassword" style="width:200px"><p class="disIb ml5 c-red-1 fsize12 f-fM" id="passwordError">输入1-99的整数</p></li>' +
			'<li class="mt30"><label class="vam">折扣：</label><input type="password" class="lTxt" id="userPassword" style="width:100px"><p class="disIb ml5 c-red-1 fsize12 f-fM" id="passwordError">输入1-9.9对应1-9.9折，不填写表示没有</p></li>' +
			'<li class="pt30 tac"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			//编辑折扣
			'<div class="d-tips-14">' +
			'<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">编辑折扣</span></h4>' +
			'<div class="mt40 mb40 pl90 pr90">' +
			'<div style=""><ul class="l-r-w-Inpt">' +
			'<li><p id="requestErrorID"></p></li>' +
			'<li><label class="vam">简介：</label>' +
			'<span style="display:inline-block;position:relative;">' +
			'<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>' +
			'<input type="text" id="" value="" class="lTxt" style="width:200px">' +
			'</span>' +
			'<p class="disIb ml5 c-red-1 fsize12 f-fM" id="userNameError">30字以内</p></li>' +
			'<li class="mt30"><label class="vam">课时：</label><input type="password" class="lTxt" id="userPassword" style="width:200px"><p class="disIb ml5 c-red-1 fsize12 f-fM" id="passwordError">输入1-99的整数</p></li>' +
			'<li class="mt30"><label class="vam">折扣：</label><input type="password" class="lTxt" id="userPassword" style="width:100px"><p class="disIb ml5 c-red-1 fsize12 f-fM" id="passwordError">输入1-9.9对应1-9.9折，不填写表示没有</p></li>' +
			'<li class="pt30 tac"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>' +
			'</ul>' +
			'</div>' +
			'</div>',
			];
		$("#dcWrap").html(dCont[num]);

		var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			dH = dialogEle.height(),
			dW = dialogEle.width(),
			timer = null,
			dClose;
		dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
		dClose = function() {dialogEle.remove();oBg.remove();};
		
		$(".dtClose").bind("click", dClose);
		$(".dClose").bind("click", function() {dialogEle.remove();oBg.remove();});
	}

	// 公共弹窗关闭事件
	function dialogClose() {
		$(".d-tips-2").remove();
		$(".dialog-shadow").remove();
		$(".bg-shadow").remove();
	}

//搜索
function getSearch(){
	var searchStr=$("#teacherName").val();
	if(searchStr!="请输入关键字..."){
		$("#searteacherName").val(searchStr);
	}
	$("#searchcityId").val($("#searCityId").val());
	$("#formSearch").submit();
}
// 选择城市
function selectCity(cityId){
	$("#searCityId").val(cityId);
	$("#select_area").html($("#area_"+cityId).html());
	$("#updateCity").submit();
}