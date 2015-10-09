<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>帮助中心</title>
	<script type="text/javascript">
		$(function(){
			var id='${id}';
			$("#"+id).addClass("current");
		});
	</script>
</head>

<body>
<section class="main">
	<div id="aCoursesList" class="pb50">
		<section class="container">
			<div class="mt30 mb100">
				<aside class="menu-2015 fl">
					<menu>
						<dl>
							<dt class="header">
								<a href="#">
									帮助中心
								</a>
							</dt>
						</dl>
						<c:forEach items="${helpMenus}" var="helpMenu1" varStatus="index">
						<dl class="body">
							<dt>
								<a href="${ctx}/help?id=${helpMenu1[0].id}">
									${helpMenu1[0].name}
								</a>
							</dt>
							<dd>
								<ol>
									<c:forEach items="${helpMenu1}" var="helpMenu" varStatus="index1">
									<c:if test="${!index1.first}">
										<li id="${helpMenu.id}">
										<a title="${helpMenu.name}" class="xs-1" href="${ctx}/help?id=${helpMenu.id}">${helpMenu.name}</a>
										</li>
									</c:if>
									</c:forEach>
								</ol>
							</dd>
						</dl>
						</c:forEach>
					</menu>
				</aside>
				<aside class="help-boy fl">
					<div class="ml30">
						<div class="tit">
							<a href="#">
								${helpMenuContent.name}
							</a>
						</div>
						<div class="boy">
							${helpMenuContent.content}
						</div>
					</div>
				</aside>
				<div class="clear"></div>
			</div>
		</section>
	</div>
</section>
<script src="${ctx}/static/edu/js/web/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/tangram-min.js" type="text/javascript"></script>
	<script src="${ctx}/static/edu/js/web/fx.js" type="text/javascript"></script>
		<script type="text/javascript">
		var numShow = function(op,ot,date,total) {
			function show(val, val2,number) {
				for (var i = 0; i < number;i++) {
					$(op).append("<span>0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9</span>");
				}
				var numbers = T.dom.children(T.dom.g(ot));
					for (var i = number-1; i >= 0; i--) {
						var m = val % 10;
						var m2 = val2 % 10;
						numbers[i].style.top = (-m * 40) + 'px';
						val = Math.floor(val / 10);
						val2 = Math.floor(val2 / 10);
						baidu.fx.moveTo(numbers[i], {y: -m2 * 40}, {duration: 1000});
					}
			}
						//用户数滚动开并
			var numberOffsetTop = $(op).offset().top,
				isScroll = true;
			if ($(document).scrollTop() > numberOffsetTop - $(window).height()) {
				show('6037618', date, total); //大尺寸屏幕可见直接执行动画；
			} else {
				//小尺寸屏幕滚动到屏幕可见区域再执行动画；
				$(window).bind("scroll", function() {
					if (isScroll) {
						if ($(document).scrollTop() > numberOffsetTop - $(window).height()) {
							show('6037618', date, total);
							isScroll = false;
						};
					};
				})
			};
		}
		$(function() {
			numShow('#numberL','numberL','${indexTotal.allTeacherCount}','7');//前两个参数不需要动 第三个是统计数字  第四个是统计数位
			numShow('#numberR','numberR','${indexTotal.allStudentCount}','6');
			upSlideFun("#iQuestion"); //向上滚动互动
			goTop();//右侧悬浮
			gtFun()
			cardChange("#lr-title>li" , "#lr-cont>section" , "current");//登录注册切换
			$(".c-tit-name li").hover(function() {
				$(this).addClass('current').siblings().removeClass('current');
				$(this).parent().parent().siblings(0).find("ul").eq($(this).index()).show().siblings().hide();
			});
		});
		var marquestFun = function() {
			var speed=100;
			var m1=document.getElementById("i-q-in-box-1");
			var m2=document.getElementById("i-q-in-box-2");
			var m=document.getElementById("i-q-in-box");
			m2.innerHTML=m1.innerHTML; //克隆colee1为colee2
			function Marquee1(){
				m.scrollTop++;
			//当滚动至colee1与colee2交界时
				if(m.scrollTop==m1.offsetHeight){
						m.scrollTop=0;
				}
			}
			var MyMar1=setInterval(Marquee1,speed)//设置定时器
			//鼠标移上时清除定时器达到滚动停止的目的
			m.onmouseover=function() {clearInterval(MyMar1)}
			//鼠标移开时重设定时器
			m.onmouseout=function(){MyMar1=setInterval(Marquee1,speed)}
		}
		marquestFun();
	</script>
</body>
</html>
