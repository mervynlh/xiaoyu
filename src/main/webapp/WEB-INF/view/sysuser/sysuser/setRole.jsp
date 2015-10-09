<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<title>用户角色与应用范围设置修改</title>
<script type="text/javascript">
 function del(roleId, userId){
 	if(window.confirm("您确定要删除此角色吗？")) {
 		window.location.href = "${ctx}/admin/role/deleteRoleRef?roleId=" + roleId + "&userId=" + userId;
 	}
       }
     </script>
	</head>
<body>
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户角色修改</span> </h4>
</div>
<div class="mt20">			
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>用户： <c:out value="${user.userName}" /></span></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rolelist}" var="role">
				<tr>
					<td width="20%" align="center">&nbsp;角色名称</td>
					<td width="80%">
					&nbsp;<c:out value="${role.roleName}" />&nbsp;
					 <a class="btn smallbtn btn-y" href="javascript:void(0)" onclick="del(<c:out value="${role.roleId}"/>, <c:out value="${user.userId}"/>)">删除</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="center" colspan="3">
				<a 	class="btn  btn-danger" href="${ctx}/admin/role/toAddRoleRef?userId=<c:out value="${user.userId}"/>">添加角色</a>
				&nbsp;&nbsp;
				<a 	class="btn  ml10" href="${ctx}/admin/user/listAllUser?page.currentPage=1">返回</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</html>
