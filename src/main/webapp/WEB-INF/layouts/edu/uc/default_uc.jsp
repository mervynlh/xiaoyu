<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html compressJavaScript="false" >
<!DOCTYPE HTML>
<!--
    Service Support:   try    http://www.268xue.com
-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
	<title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}"/>
	<meta name="description" content="${websitemap.web.description}"/>
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"> 
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/theme.css">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/ucenter.css">
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js"></script>
	<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/ucenterJs.js"  charset="utf-8"></script>
	<script type="text/javascript" src="${ctximg}/static/common/header_msg.js"  charset="utf-8"></script>
	<script type="text/javascript" src="${ctx }/static/edu/js/ucenter/sign_calendar.js"></script>

	<script type="text/javascript">
		var baselocation = "${ctx}";
		var imagesPath = "${ctximg}";
		var usercookiekey="<%=usercookiekey%>";
		var mydomain="<%=mydomain%>";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var uploadSwfUrl="<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
        var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
		var uploadServerUrl ="<%=uploadServerUrl%>";
	</script>
	<sitemesh:head ></sitemesh:head>
</head>
<body class="W-body U-body">
	<!-- 公共头引入，开始 -->
	<jsp:include page="/WEB-INF/layouts/edu/uc/header_uc.jsp"></jsp:include>
	<!-- 公共头引入，结束 -->
	<!-- /u-main -->
	<section id="u-main" class="container">
		<!-- 公共左边引入，开始 -->
		<jsp:include page="/WEB-INF/layouts/edu/uc/left_uc.jsp"></jsp:include>
		<!-- 公共左边引入，结束 -->
		<div class="clearfix">
			<sitemesh:body></sitemesh:body>
		</div>
	</section>
	<!-- 公共尾引入，开始 -->
	<jsp:include page="/WEB-INF/layouts/edu/uc/footer_uc.jsp"></jsp:include>
	<!-- 公共尾引入，结束 -->
	<!-- 统计代码 -->
	${tongjiemap.censusCode.censusCodeString}
</body>

</html>
</compress:html>