<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>id</td>
			<td>试听者姓名</td>
			<td>试听者邮箱</td>
			<td>试听者手机</td>
			<td>时间</td>
			<td>阶段</td>
			<td>年级</td>
			<td>科目</td>
			<td>状态</td>
			<td>备注</td>
		</tr>
		<c:if test="${auditionList!=null&&auditionList.size()>0 }">
			<c:forEach var="audition" items="${auditionList }">
				<tr>
					<td>${audition.id }</td>
					<td>${audition.name }</td>
					<td>${audition.email }</td>
					<td>${audition.mobile }</td>
					<td><fmt:formatDate value="${audition.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />-
							<fmt:formatDate value="${audition.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${audition.subjectName }</td>
					<td>${audition.gradeName }</td>
					<td>${audition.majorName }</td>
					<td>
						<c:if test="${audition.status==0}">未处理</c:if>
						<c:if test="${audition.status==1}">已处理</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>