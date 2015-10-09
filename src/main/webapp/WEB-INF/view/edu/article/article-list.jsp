<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>资讯</title>
<script type="text/javascript">
$(function (){
	var articleMark ='${type}';
	if(articleMark=='1'){
		$("#tgAtriceleId").addClass('onClick');
	}else if(articleMark=='2'){
		$("#newAtricleId").addClass('onClick');
	}else{
		$("#allAtricleId").addClass('onClick');
	}
});
</script>
</head>
<body>
<form id="searchForm" action="${ctx}/front/articlelist/${type}" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
 </form>
							 
	<!-- /资讯列表 -->
	<div class="mb50">
		<section class="w1000">
			<div class="mt30">
				<div class="w1000">
					<c:forEach var="image2" items="${websiteImages.indexAdvertImages2}" >
						<a href="${image2.linkAddress}"><img src="<%=staticImageServer %>${image2.imagesUrl}" class="dis ads-1" height="120" width="1000" ></a>
					</c:forEach>
				</div>
			</div>
			<div class="mt10 pr">
				<div class="pathwray">
					<ol class="clearfix c-master f-fM fsize14">
						<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
						<li><span>资讯公告列表</span></li>
					</ol>
				</div>
			</div>
			<div class="mt30">
				<section class="clearfix">
					<aside class="fr w300">
						<div>
							<section>
								<h3 class="of a-title unFw"><font class="c-333 f-fM fsize18">点击排行</font></h3>
							</section>
							<section class="article-list-1">
								<ol>
								<c:forEach items="${articleListOrderclickTimes}" var="circuitRanking" varStatus="index">
									<li>
										<tt class="order b-master c-fff"><font class="f-fM fsize16">${index.index+1}</font></tt>
										<a href="${ctx}/front/toArticle/${circuitRanking.id}" title="${circuitRanking.title}">${circuitRanking.title}</a>
									</li>
								</c:forEach>
								</ol>
							</section>
						</div>
						
					</aside>
					<article class="fl w650">
						<section class="pr articleListTitle">
							<h3 class="fl f-fM mt5"><strong class="c-333 fsize16">资讯公告</strong></h3>
							<h5 class="fl ml50">
								<a id="allAtricleId" target="_self" href="${ctx}/front/articlelist/0">全部</a>
								<a id="newAtricleId" target="_self" href="${ctx}/front/articlelist/2">公告</a>
								<a id="tgAtriceleId" target="_self" href="${ctx}/front/articlelist/1">资讯</a>
							</h5>
							<div class="clear"></div><!-- /clear float -->
						</section>
						<section>
							<ul class="article-list-wrap">
							<c:if test="${articleList==null || articleList.size()==0 }">
								<section class="comm-tips-1">
								<p>
								<em class="vam c-tips-1"> </em>
								<c:choose>
									<c:when test="${articleMark==1 }">
										<font class="c-999 fsize12 vam">暂无任何公告. . .</font>
									</c:when>
									<c:when test="${articleMark==2 }">
										<font class="c-999 fsize12 vam">暂无任何资讯 . . .</font>
									</c:when>
									<c:otherwise><font class="c-999 fsize12 vam">暂无任何资讯 公告. . .</font></c:otherwise>
								</c:choose>
								
								</p>
								</section>
							</c:if>
							<c:if test="${articleList!=null && articleList.size()>0 }">
								<c:forEach items="${articleList}" var="Atricle" varStatus="index">
									<li>
										<a class="aPlot" title="${Atricle.title}" href="${ctx}/front/toArticle/${Atricle.id}">
										<c:if test="${Atricle.picture!=null && Atricle.picture!=''}">
											<img alt="${Atricle.title}" height="100" width="133" src="<%=staticImageServer%>${Atricle.picture}"/>
										</c:if>
										</a>
										<h5 class="hLh30 of unFw"><em class="vam mr5 icon30 a-t-icon">&nbsp;</em><a title="${Atricle.title}" href="${ctx}/front/toArticle/${Atricle.id}" class="c-4e fsize16 vam">${Atricle.title}</a></h5>
										<div class="a-l-desc-txt"> ${Atricle.description} </div>
										<section class="of mt10">
											<span class="c-999 vam disIb fsize12 f-fM" title="时间"><em class="icon14 a-time">&nbsp;</em><fmt:formatDate value="${Atricle.updateTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
											<span class="disIb c-999 vam ml50 fsize12 f-fM" title="查看"><em class="icon14 a-read">&nbsp;</em> ${Atricle.clickTimes}人</span>
										</section>
									</li>
								</c:forEach>
							</c:if>
							</ul>
						</section>
						<!-- /文章列表 结束 -->
							<section class="mt50">
							<div class="pagination pagination-large tac">
		                  		<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
							</div>
							</section>
					</article>
				</section>
			</div>
		</section>
	</div>
</body>
</html>
