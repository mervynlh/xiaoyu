<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta charset="utf-8" http-equiv="Content-Type"/>
<title>${websitemap.web.company}-${websitemap.web.title}</title>
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"/> 
<meta name="application-name" content=""/> 
<meta name="description" content=""/> 
<meta name="application-url" content=""/>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_top.css?v=${v}"/>
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
<script src="${ctximg}/static/common/admin/js/admin-268xue.js?v=${v}" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<!-- 头部 -->

<div id="header">
	<div class="topWrap">
		<div id="logo">
			<h1><a href="javascript:window.parent.location='${ctx}/admin/sys/main';" title="${websitemap.web.company}"><img src="<%=staticImageServer %>${logomap.logo.url}" width="146" height="92" alt="${websitemap.web.company}" /></a></h1>
		</div>
		<div id="nav">
			<div class="navWrap">
				<div class="navList">
						<ul>
						
							<c:forEach items="${topTabList}" var="tfunction" varStatus="status">
									<c:if test="${tfunction.parentFunctionId==0 && tfunction.functionTypeId==2}">
									<li>
										<a target="leftFrame" href="${ctx}/admin/func/showchild?parentId=${tfunction.functionId}">
										<span class="icon30 n_0${status.index+1 }">&nbsp;</span>
										<div class="n_txt"> <strong>${tfunction.functionName }</strong></div>
										</a>
									</li>
									</c:if>
								</c:forEach>
								<c:forEach items="${userFunctionList }"  var="tabList" >
						</c:forEach>
						</ul>
					</div>
					
			</div>
		</div><!-- /nav -->
		<div id="topRight">
			<div class="twoBtn">
				<span><em class="icon12 goHome"></em><a href="javascript:window.parent.location='${ctx}/admin/sys/main';" title="网站首页">网站首页</a></span>
				<span><em class="icon12 quitS"></em><a href="/admin/sys/logout" title="退出系统">退出系统</a></span>
			</div>
			<div class="adminUser">
				<span>你好<b><c:out value="${userName}" /></b>欢迎你！</span>
			</div>
		</div>
	</div>
	</div><!-- /header -->
<!-- 头部 //-->
<script type="text/javascript">
	$(function() {
		navFun();
	});
</script>
</body>
</html>