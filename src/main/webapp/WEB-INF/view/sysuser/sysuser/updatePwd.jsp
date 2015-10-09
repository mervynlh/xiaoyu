<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改密码</title>
<script type="text/javascript">
function submit(){
	var password = $("#password").val();
	if(password.trim()==""){
		alert("新密码不能为空");
		return;
	}
	if(password.length<6||password.length>16){
		alert("新密码长度应为6到16个字符");
		return;
	}
	var id = $("#id").val();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${ctx}/admin/user/updatepwd",
		data:{"user.id":id,"user.password":password},
		async:false,
		success:function(result){
			if(result.success){
				alert("修改成功");
				window.location.href="${ctx}/admin/user/list";			
			}
		}
	});
	
}
</script>
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>修改密码</span> </h4>
</div>
<div class="mt20">
	<form action="${ctx}/admin/user/updatePwd" method="post" id="addPaperForm">
	<input name="user.id" id="id" type="hidden" value="${user.id}"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>修改密码<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;学员id</td>
				<td>
					${user.id }
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;学员邮箱</td>
				<td>
					${user.email }
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;新密码</td>
				<td>
					<input type="password" name="user.password" id="password"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:submit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="${ctx}/admin/user/list">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
</form>
</div>
</body>
</html>
