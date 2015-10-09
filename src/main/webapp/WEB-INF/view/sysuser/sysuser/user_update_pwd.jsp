<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户密码修改</title>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<script type="text/javascript">
		$().ready(function() {
			jQuery.extend(jQuery.validator.messages, { 
	  			equalTo : "两次密码输入不一致"
			}); 
			
 			$("#updateForm").validate({
 				rules : {
 					"user.loginPwd" : {
 						required : true,
 						minlength : 6,
 						maxlength : 20
 					}
 				},
 				messages : {
 					"user.loginPwd" : {
 						required : "请填写新密码",
 						minlength : "密码不能低于6位",
 						maxlength : "密码不能超过20位"
 					}
 				}
 			});
 		});
	</script>
</head>
<body >
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户密码修改</span> </h4>
</div>
			
<form action="${ctx}/admin/user/updatePwd" name="updateForm" id="updateForm" method="post">
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>用户密码修改<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;用户名</td>
				<td>
					<input type="text" readonly="readonly" value="<c:out value="${user.loginName}"/>"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;真实姓名</td>
				<td width="80%">
				<input type="text" readonly="readonly" value="<c:out value="${user.userName}"/>"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;新密码</td>
				<td>
					<input type="password" name="user.loginPwd" id="loginPwd" maxlength="25"/>
				</td>
			</tr>
			<tr>
					<td align="center"><font color="red">*</font>&nbsp;确认密码</td>
					<td>
						<input type="password"  id="user.loginPwdConfirm" equalTo="#loginPwd" />
					</td>
				</tr>
				
			<tr>
				<td colspan="2" align="center">
				<input type="hidden" value="<c:out value="${user.userId}"/>" name="user.userId"/>
				<input type="submit" value="提交" class="btn btn-danger"/>
				<input type="button" value="返回" onclick="history.go(-1)" class="btn ml10"/>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</form>
	</body>
</html>
