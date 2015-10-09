<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
	<!-- 顶部栏目 -->
	<header id="header">
		<div class="rl-header">
		<section class="container">
			<aside class="rl-tel">
				<span class="c-master fsize20 f-fM">
					Tel：${websitemap.web.phone}
				</span>
			</aside>
			<aside class="rl-tel mr40">
				<span class="c-666 fsize14 f-fM">
					Hi，欢迎来到${websitemap.web.company}
				</span>
				<a href="${ctx }/register" class="c-org fsize14 f-fM">免费注册</a>
			</aside>
			<h1 class="of unFw pb10">
				<a href="/" title="" class="logo-2013">
					<img src="${ctximg }/static/edu/img/logo-login.png" alt="">
				</a>
				<span>
					<img src="${ctximg}/static/edu/img/logo-right-login.png" alt="">
				</span>
			</h1>
			<div class="clear"></div>
		</section>
	</div>
	</header>
	<!-- /header -->