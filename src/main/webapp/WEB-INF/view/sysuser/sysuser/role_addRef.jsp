<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户角色添加</title>
<script type="text/javascript">
     function submitResult(){
     	if(document.getElementById("roleId").value<1) {
     		alert("请选择角色！");
     		return false;
     	}
	}
	
</script>
</head>
<body>

<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>角色管理</span> &gt; <span>用户角色修改</span> </h4>
</div>
	
<div class="mt20">
	<form action="${ctx}/admin/role/addRoleRef" method="post" onsubmit="return submitResult()">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>[用户角色修改]用户： <c:out value="${user.userName}" /></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="20%" align="center">&nbsp;角色名称</td>
				<td width="80%">
				<select name="roleId" id="roleId" >
					<option value="-1">请选择</option>
					<c:forEach items="${roleList }" var="roleList">
						<option value="${roleList.roleId}">${roleList.roleName }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input name="userId"  id="userId" type="hidden" value="${userId}"/>
			    <input type="submit" value="提交" class="btn btn-danger"/>
				<input type="button" value="返回" onclick="history.go(-1)" class="btn ml10"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</div>
	</body>
</html>
