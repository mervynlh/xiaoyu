<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<%@ page buffer="1000kb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_left.css?v=${v}"/>
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/common/admin/js/admin-268xue.js?v=${v}" type="text/javascript" charset="utf-8"></script>
</head>
<body style="background: #404040;overflow-x : hidden;" >
<div id="left_menu" style="top: 0;">
	<menu type="context toolbar" id="lMenu">
		<dl>
			<c:forEach items="${leftTabList}" varStatus="status"  var="tabList" >
				<c:choose>
					<c:when test="${status.index==0}"><!-- 第一个展开 -->
						<dt class="current"><a href="" title=""><tt class="icon12">&nbsp;</tt><span>${tabList[0].functionName}</span></a><em class="sjWrap">◆</em></dt>
						<dd style="display:block ">
							<ol>
								<c:forEach items="${tabList}" var="tfunction" >
									<c:if test="${tfunction.parentFunctionId!=0 }">
										<c:if test="${tfunction.functionTypeId==2}">
											<c:if test="${tfunction.functionUrl!=''&&tfunction.functionUrl!=null}">
											<li><a href="${tfunction.functionUrl}" target="rightFrame" title="${tfunction.functionName}">${tfunction.functionName}</a></li>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</ol>
						</dd>
					</c:when>
					<c:otherwise>
						<dt ><a href="" title=""><tt class="icon12">&nbsp;</tt><span>${tabList[0].functionName}</span></a><em class="sjWrap">◆</em></dt>
						<dd style="display: none">
							<ol>
								<c:forEach items="${tabList}" var="tfunction" >
									<c:if test="${tfunction.parentFunctionId!=0 }">
										<c:if test="${tfunction.functionTypeId==2}">
											<c:if test="${tfunction.functionUrl!=''&&tfunction.functionUrl!=null}">
											<li><a href="${tfunction.functionUrl}" target="rightFrame" title="${tfunction.functionUrl}">${tfunction.functionName}</a></li>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</ol>
						</dd>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</dl>
	</menu>
</div><!-- /left_menu -->

</body>
</html>
