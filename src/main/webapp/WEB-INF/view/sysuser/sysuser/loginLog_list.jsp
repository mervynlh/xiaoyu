<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>登录日志列表</title>
		<script type="text/javascript">
			function search(){
				var $searchKey = $("#searchKey").val();
				var searchKey = $.trim($searchKey) 
				if(searchKey==""){
					alert("请输入用户名/姓名！");
				}else{
					searchKey = encodeURIComponent(encodeURIComponent(searchKey));
					window.location="${ctx}/admin/sys/loginLog!search?queryLoginLogCondition.currentPage=1&userName="+searchKey;
				}
			}
       </script>
	</head>
<body>

	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户登录记录</span> </h4>
	</div>
	
<div class="mt20">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="commonTab01">
		<tr >
			<td class="td_wid_l">
			<img src="${ctximg}/static/common/admin/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">登录日志列表</font>
				<font class="lists_fright">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
							按用户名/姓名检索：
							<input type="text" name="searchKey" id="searchKey" value="${userName}"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0">
					<tr>
						<td>
						<img src="${ctximg}/static/common/admin/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a>
						</td>
					</tr>
					</table>
				</font>
			</td>
			<td class="td_wid_l">
			<img src="${ctximg}/static/common/admin/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
					<tr class="lists_infobg">
						<td>
							序号
						</td>
						<td>
							用户名
						</td>
						<td>
							真实姓名
						</td>
						<td>
							登录IP
						</td>
						<td>
							登录时间
						</td>
					</tr>
					<c:forEach items="${page.pageResult}"  var="loginlog" varStatus="status">
						<tr>
							<td>
								<c:out value="#status.index+(page.currentPage-1)*page.pageSize+1" />
							</td>
							<td>
								<c:out value="#loginLog.loginName" />
							</td>
							<td>
								<c:out value="#loginLog.userName" />
							</td>
							<td>
								<c:out value="#loginLog.ip" />
							</td>
							<td>
								<s:date name="#loginLog.loginTime" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
					</c:forEach>
			</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="${ctximg}/static/common/admin/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
				 <jsp:include page="/WEB-INF/view/common/admin_page.jsp" /> 
			</td>
			<td>
				<img src="${ctximg}/static/common/admin/images/tab_20.gif" />
			</td>
		</tr>
	</table>
</div>
	</body>
</html>
