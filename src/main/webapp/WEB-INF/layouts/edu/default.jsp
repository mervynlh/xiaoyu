<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html  compressJavaScript="false" >
<!DOCTYPE HTML>
<!--
    Service Support:   try    http://www.268xue.com
-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/> 
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"> 
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/header_msg.js?v=<%=version%>"></script>
    <script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>
	<script src="${ctximg}/static/edu/js/web/commJs.js" type="text/javascript"></script>
    
    <link rel="stylesheet" href="${ctx}/static/edu/css/common.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/edu/css/theme.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/edu/css/page-style.css" type="text/css">
	<!--[if lt IE 9]><script src="${ctx}/static/edu/js/web/html5.js"></script><![endif]-->
	
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var baselocationsns = "${ctxsns}";
		var baselocationexam = "${ctxexam}";
		var imagesPath = "${ctximg}";
		var usercookiekey="<%=usercookiekey%>";
		var mydomain="<%=mydomain%>";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var uploadSwfUrl="<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
        var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
        var uploadServerUrl ="<%=uploadServerUrl%>";//上传后返回路径
        var loginkeyword='${keywordmap.keyword.verifyLogin}';
        var upUserId = "<%=CommonConstants.UP_USER_ID%>";
	</script>
	<sitemesh:head ></sitemesh:head>
</head>
<body class="W-body">
	<!-- 公共头引入 -->
	<jsp:include page="/WEB-INF/layouts/edu/header.jsp"/>
	<!-- 公共头引入 -->
	<sitemesh:body></sitemesh:body>
	<!-- 公共底引入 -->
	<jsp:include page="/WEB-INF/layouts/edu/footer.jsp"/>
	<!-- 公共底引入 -->
	<!-- 统计代码 -->
	${tongjiemap.censusCode.censusCodeString}
</body>
</html>
</compress:html>