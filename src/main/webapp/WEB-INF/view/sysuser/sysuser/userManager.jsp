<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %> 
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户信息修改1</title>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<script type="text/javascript">
	$().ready(function() {
		//提交页面增加验证
		jQuery.validator.addMethod("tel", function(value, element) {
			var pattern = /^[0-9]{1}[0-9-]*$/;
					return this.optional(element) || pattern.test(value);
				});
		jQuery.extend(jQuery.validator.messages, { 
  			equalTo : "两次密码输入不一致",
  			tel : "只能输入数字和中划线"
		}); 
		$("#updateForm").validate();
	});
	
</script>
</head>
<body  >
<!-- 内容 开始  -->
<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户信息修改</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
	          <!-- form开始 -->
	          <form  action="${ctx}/admin/user/userManagerSubmit" id="updateForm" method="post" name="updateForm">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th colspan="2" align="left"><span>用户信息修改<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;用户名</td>
							<td width="80%">
								<input type="text" readonly="readonly" name="user.loginName" id="user.loginName"	value="<c:out value="${user.loginName }"/>" />
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;真实姓名</td>
							<td>
					    	<input type="text" name="user.userName" id="user.userName"
												value="<c:out value="${user.userName }"/>" class="{required:true,maxlength:20}"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;联系电话</td>
							<td width="80%">
								<input type="text" name="user.tel" id="user.tel"
												value="<c:out value="${user.tel }"/>"  class="{required:true,tel:true}"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;email</td>
							<td width="80%">
								<input type="text" name="user.email" id="user.email"
												value="<c:out value="${user.email }"/>" class="{required:true,email:true,maxlength:50}" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input type="hidden" name="user.loginPwd" id="user.loginPwd" value="<c:out value="${user.loginPwd }"/>"/>
							<input type="hidden" name="user.userId" id="user.userId" value="<c:out value="${user.userId }"/>"/>
							<input type="hidden" name="user.status" id="user.status" value="<c:out value="${user.status }"/>"/>
							<input type="hidden" name="user.groupId" id="user.groupId" value="<c:out value="${user.groupId}"/>"/>
							<input type="submit" value="提交"  class="btn btn-danger"/> 
							<a onclick="history.go(-1)" class="btn ml10">返 回</a>
							</td>
						</tr>
					</tbody>
				</table>
			<!-- /tab4 end -->
          	  </form>
	          <!-- form结束 -->
	       
	  </div>
</div>
<!-- 内容 结束 -->	
</body>
</html>
