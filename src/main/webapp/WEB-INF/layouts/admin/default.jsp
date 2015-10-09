<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp" %>
<compress:html  compressJavaScript="false" >
<!DOCTYPE HTML>
<!--
    Service Support:   try    http://www.268xue.com
    -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
	<title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/admin/js/admin-268xue.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>

	<sitemesh:head ></sitemesh:head>
</head>
<body >
<div id="layout">
    <div id="main_content">
        <!-- admin_body start -->
        <sitemesh:body ></sitemesh:body>
        <!-- admin_body end -->
    </div><!-- /main_content -->
</div><!-- /layout -->
</body>
</html>
</compress:html>