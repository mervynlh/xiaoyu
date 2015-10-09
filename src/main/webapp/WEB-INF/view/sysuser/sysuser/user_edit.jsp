<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %> 
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户信息修改</title>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
 
<script type="text/javascript">
	//ztree start
	var setting = {
		/* check: {
			enable: true,
			chkboxType : { "Y" : "s", "N" : "s" }
		}, */
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'groupId',
				pIdKey:'parentGroupId',
				rootPid:''
			},
			key:{
				name:'groupName',
				title:'groupName'
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};
	
	var treedata=${groupList};
	
	function treeOnclick(e,treeId, treeNode) {
		$("#userGroupId").val(treeNode.groupId);
		$("#userGroupName").val(treeNode.groupName);
		$("#distree").hide();
	}
	
	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	});
	
	function showZtree(){
		$("#distree").show();
	}
	function closetree(){
		$("#distree").hide();
	}
	//ztree end
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
	
	/*
	 提交验证
	*/
	function checkSubmit(){
		var userGroupId=$("#userGroupId").val();
		if(userGroupId==0){
			alert("请选择部门!");
			return false;
		}
		return true;
	}
	
	function sub(form){
		if(checkSubmit()){
			form.submit();
		}
	};
	
</script>
</head>
<body >

<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户信息修改</span> </h4>
</div>
<!-- 内容 开始  -->
<div class="mt20">
		<!-- form开始 -->
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<form  action="${ctx}/admin/user/editUser" id="updateForm" method="post" name="updateForm">
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
					<td width="20%" align="center"><font color="red">*</font>&nbsp;所属部门</td>
					<td width="80%">
                       	 <input type="text"  id="userGroupName" value="${user.group.groupName}" readonly="readonly" onclick="showZtree()"/>
						<div id="distree"  style="display: none">
							<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
							<div id="ztreedemo" class="ztree" ></div>
						</div>
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
					<input type="hidden" name="user.groupId" id="userGroupId" value="<c:out value="${user.groupId}"/>"/>
					<a onclick="sub(document.updateForm)" href="#" class="btn btn-danger">提 交</a>
					<a onclick="history.go(-1)" class="btn ml10">返 回</a>
					</td>
				</tr>
			</tbody>
			</form>
		</table>
        	  
	<!-- /tab4 end -->
</div><!-- zhong ml5 -->
<!-- 内容 结束 -->	
</body>
</html>
