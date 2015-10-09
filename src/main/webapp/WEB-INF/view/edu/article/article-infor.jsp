<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${article.title}</title>
</head>
<body>
	<!-- /资讯列表 -->
	<div class="mb50">
		<section class="w1000">
			<div class="mt10 pr">
				<div class="pathwray">
					<ol class="clearfix c-master f-fM fsize14">
						<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
						<li><span>${article.title}</span></li>
					</ol>
				</div>
			</div>
			<div class="mt30">
				<section class="clearfix">
					
					<article class="">
						<section class="pb20 line3">
							<h2 class="tac unFw"><font class="fsize20 c-4e f-fM">${article.title}</font></h2>
							<div class="mt5 mb5 tac">
								<span title="时间" class="c-999 vam disIb fsize12 f-fM"><em class="icon14 a-time">&nbsp;</em><fmt:formatDate value="${article.createTime}" type="both"/> </span>
								<span title="查看" class="disIb c-999 vam ml20 fsize12 f-fM"><em class="icon14 a-read">&nbsp;</em>${article.clickTimes}人</span>
								<span title="${article.author}" class="disIb c-999 vam ml20">来源：
									<c:if test="${not empty article.author}">${article.author}</c:if>
								</span>
							</div>
						</section>
						<div>
							<div class="articleText c-666">
								${article.content}
							</div>
						</div>
						<ul class="c-666 upDownBar mt30 pt20">
							<c:if test="${!empty upArticle }">
                    		<li><span>上一篇：</span><a class="c-666" href="${ctx}/front/toArticle/${upArticle.id}">${upArticle.title}</a></li>
							</c:if>
							<c:if test="${!empty downArticle }">
                    		<li class="mt20"><span>下一篇：</span><a class="c-666" href="${ctx}/front/toArticle/${downArticle.id}">${downArticle.title}</a></li>
							</c:if>
                    	</ul>
						<!-- /文章内容 结束 -->
					</article>
				</section>
			</div>
		</section>
	</div>
</body>
</html>
