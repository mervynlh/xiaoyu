<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>显示/隐藏左侧导航栏</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/admin/css/css.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/admin/css/lib.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/admin/css/mid.css" />
<script language="JavaScript">
function Submit_onclick(){
	if(parent.myFrame.cols == "177,7,*") {
		parent.myFrame.cols="0,10,*";
		document.getElementById("mid").style.background="url(${ctx}/images/display_left_show.gif) no-repeat";
		document.getElementById("mid").alt="打开左侧导航栏";
	} else {
		parent.myFrame.cols="177,7,*";
		document.getElementById("mid").style.background="url(${ctx}/images/display_left.gif) no-repeat";
		document.getElementById("mid").alt="隐藏左侧导航栏";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.midFrame.document.getElementById("mid").style.background="url(${ctx}/images/display_left_show.gif) no-repeat";
	}
}
</script>

</head>
<center>
<body class="manage">
    	<div id="mid" class="display_left" onclick="javascript:Submit_onclick()"></div>
</body>
</center>
</html>
