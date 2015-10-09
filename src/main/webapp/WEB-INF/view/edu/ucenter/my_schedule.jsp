<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>首页</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript"></script>
	<script src="${ctximg}/static/sns/js/home/home.js?v=<%=version%>" type="text/javascript"></script>
	<style type="text/css">
		.Prompt{margin:0 auto;}
	</style>
</head>
<body class="W-body">
<table>
<c:forEach items="${list}" var="schedule">
<tr><td>${schedule.id}</td>
<td>${schedule.userId}</td>
<td>${schedule.course.name}</td>
<td> 
<c:choose>
	<c:when test="${schedule.status==1}">待确认约课</c:when>
	<c:when test="${schedule.status==2}">待上课</c:when>
	<c:when test="${schedule.status==3}">待确认课酬</c:when>
	<c:when test="${schedule.status==4}">待评价</c:when>
	<c:otherwise>已取消</c:otherwise>
</c:choose>
<td>
<input type="button" value="取消约课" onclick="cancelOrderClass(${schedule.id})"/>
</td>
</tr>
</c:forEach>
</table>
<script type="text/javascript">
	function cancelOrderClass(id){
		dialog('提示',"确定取消约课？",2,"/uc/cancelOrderClass/"+id);
	}
</script>
</body>