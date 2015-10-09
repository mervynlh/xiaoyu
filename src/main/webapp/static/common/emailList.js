(function($){
	$.fn.mailAutoComplete = function(options){
		var defaults = {
			boxClass: "mailListBox", //外部box样式
			listClass: "mailListDefault", //默认的列表样式
			focusClass: "mailListFocus", //列表选样式中
			markCalss: "mailListHlignt", //高亮样式
			zIndex: 1,
			autoClass: true, //是否使用插件自带class样式
			mailArr: ["qq.com","163.com","gmail.com","126.com","hotmail.com","yahoo.com","yahoo.com.cn","live.com","sohu.com","sina.com","139.com","189.cn","21cn.com","test.com"], //邮件数组
			textHint: false, //文字提示的自动显示与隐藏
			hintText: "",
			focusColor: "#333",
			blurColor: "#999"
		};
		var settings = $.extend({}, defaults, options || {});
		
		//页面装载CSS样式
		if(settings.autoClass && $("#mailListAppendCss").size() === 0){
			$('<style id="mailListAppendCss" type="text/css">.mailListBox{border:1px solid #369;border-radius: 6px;background:#fff; font:12px/20px Arial;}.mailListDefault{padding:0 5px;cursor:pointer;white-space:nowrap;}.mailListFocus{padding:0 5px;cursor:pointer;white-space:nowrap;background:#369;color:white;}.mailListHlignt{color:red;}.mailListFocus .mailListHlignt{color:#fff;}</style>').appendTo($("head"));	
		}
		var cb = settings.boxClass, cl = settings.listClass, cf = settings.focusClass, cm = settings.markCalss; //插件的class变量
		var z = settings.zIndex, newArr = mailArr = settings.mailArr, hint = settings.textHint, text = settings.hintText, fc = settings.focusColor, bc = settings.blurColor;
		//创建邮件内部列表内容
		$.createHtml = function(str, arr, cur){
			if(cur==-1)cur=0;
			var mailHtml = "";
			if($.isArray(arr)){
				$.each(arr, function(i, n){
					if(i === cur){
						mailHtml += '<div class="mailHover '+cf+'" id="mailList_'+i+'"><span class="'+cm+'">'+str+'</span>@'+arr[i]+'</div>';	
					}else{
						mailHtml += '<div class="mailHover '+cl+'" id="mailList_'+i+'"><span class="'+cm+'">'+str+'</span>@'+arr[i]+'</div>';	
					}
				});
			}
			return mailHtml;
		};
		//一些全局变量
		var index = 0, s;
		$(this).each(function(){
			var that = $(this), i = $(".justForJs").size();	
			if(i > 0){ //只绑定一个文本框
			 	return;	
			}
			var  h = that.outerHeight(); // w = that.outerWidth(),获取当前对象（即文本框）的宽高
			//样式的初始化
			that.wrap('<span style="display:inline-block;position:relative;"></span>')
				.before('<div id="mailListBox_'+i+'" class="justForJs '+cb+'" style="position:absolute;left:-6000px;top:'+h+'px;z-index:'+z+';"></div>');
			var x = $("#mailListBox_" + i), liveValue=""; //列表框对象
			that.focus(function(){
				//父标签的层级
				$(this).css("color", fc).parent().css("z-index", z);	
				//提示文字的显示与隐藏
				if(hint && text){
					var focus_v = $.trim($(this).val());
					if(focus_v === text){
						$(this).val("");
					}
				}
				//键盘事件
				$(this).keyup(function(e){
					s = v = $.trim($(this).val());	
					if(/@/.test(v)){
						s = v.replace(/@.*/, "");
					}
					if(v.length > 0){
						//如果按键是上下键
						if(e.keyCode === 38){
							//向上
							if(index <= 0){
								index = newArr.length;	
							}
							index--;
						}else if(e.keyCode === 40){
							//向下
							if(index >= newArr.length - 1){
								index = -1;
							}
							index++;
						}else if(e.keyCode === 13){
							//回车
							if(index > -1 && index < newArr.length){
								//如果当前有激活列表
                                if(index>-1 ){
                                    $(this).val($("#mailList_"+index).text());
                                }else if( newArr.length>0){
                                    $(this).val($("#mailList_0").text());
                                }
							}
						}else{
							if(/@/.test(v)){
								index = -1;
								//获得@后面的值
								//s = v.replace(/@.*/, "");
								//创建新匹配数组
								var site = v.replace(/.*@/, "");
								newArr = $.map(mailArr, function(n){
									var reg = new RegExp(site);	
									if(reg.test(n)){
										return n;	
									}
								});
							}else{
								newArr = mailArr;
							}
						}
						x.html($.createHtml(s, newArr, index)).css("left", 0);
						//鼠标经过列表项事件
						//鼠标经过
						$(".mailHover").on("mouseover", function(){
							index = Number($(this).attr("id").split("_")[1]);
							liveValue = $("#mailList_"+index).text();
							x.children("." + cf).removeClass(cf).addClass(cl);
							$(this).addClass(cf).removeClass(cl);
						});
						if(e.keyCode === 13){
							//回车
							if(index > -1 && index < newArr.length){
								//如果当前有激活列表
								x.css("left", "-6000px");	
							}
						}
					}else{
						x.css("left", "-6000px");	
					}
				}).blur(function(){
					if(hint && text){
						var blur_v = $.trim($(this).val());
						if(blur_v == ""){
							$(this).val(text);
						}
					}
					$(this).css("color", bc).unbind("keyup").parent().css("z-index",0);
					x.css("left", "-6000px");	
					
				});	
				//鼠标经过列表项事件
				//鼠标经过放在此处1.9版本无法读取mailHover绑定不成功
				/*$(".mailHover").on("mouseover", function(){
					index = Number($(this).attr("id").split("_")[1]);
					liveValue = $("#mailList_"+index).text();
					x.children("." + cf).removeClass(cf).addClass(cl);
					$(this).addClass(cf).removeClass(cl);
				});*/
				
			});
			//点击事件选中文本
			x.bind("mousedown", function(){
				that.val(liveValue);		
			});
		});
	};
	
})(jQuery);