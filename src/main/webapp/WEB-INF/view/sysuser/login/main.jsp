<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${websitemap.web.company}-${websitemap.web.title}</title>
<frameset rows="117,*,7" cols="*" frameborder="no" border="0" framespacing="0">

	<frame src="${ctx}/admin/sys/topframe" name="topFrame" frameborder="no" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	
	<frameset name="myFrame" cols="180,*" frameborder="no" border="0" framespacing="0">

<!-- 中间 -->

    	<frame src="${ctx}/admin/sys/leftframe" name="leftFrame" frameborder="no" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" > </frame>
		<frameset name="rightmyFrame" rows="" frameborder="no" border="0" framespacing="0">
           <%-- /admin/statistics/web/detail admin/sys/rightframe--%>
        <frame src="${ctx}/admin/statistics/web/detail" name="rightFrame" frameborder="no" scrolling="yes" noresize="noresize" id="rightFrame" title="rightFrame" >
		</frame>
		</frameset>

<!-- 中间 //-->
	</frameset>
	
</frameset>
</head>

</html>