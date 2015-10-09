<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>添加新用户</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
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
			jQuery.validator.addMethod("loginName", function(value, element) {
				var pattern = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_])*$/;
 					return this.optional(element) || pattern.test(value);
 				});
			jQuery.validator.addMethod("tel", function(value, element) {
				var pattern = /^1{1}[0-9]{10}$/;
				return this.optional(element) || pattern.test(value);
			});
			jQuery.extend(jQuery.validator.messages, { 
	  			equalTo : "两次密码输入不一致",
	  			tel : "请输入正确的手机号码"
			}); 
 			$("#addForm").validate({
		        rules: {
		            "user.loginName": {
		            	maxlength : 20,
		            	required : true,
		            	loginName : true,
		            	minlength : 6,
		                remote : {
		                	type : "post",
		                   data: {
		                        'queryUserCondition.searchKey': function(){
		                            return $("input[name=user.loginName]").val();
		                        }
		                    },
		                    url:"${ctx}/admin/user/checkLoginName"
		                    
		                }
		            }
		        },
		    	messages : {
		    		"user.loginName": {
		                remote: "该用户名已存在",
		                loginName : "必须以字母开头，可带字母、数字以及下划线"
		            }
		    	}
 			});
 		});
		
		function checkSubmit(){
			var userGroupId=$("#userGroupId").val();
			if(userGroupId==0){
				alert("请选择部门!");
				return false;
			}
		    document.addForm.action = "${ctx}/admin/user/addUser";
			return true;
		}
	</script>
</head>
<body>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>新建用户</span> </h4>
</div>
<div class="mt20">
	<form name="addForm" id="addForm" method="post" onsubmit="return checkSubmit();">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>新建用户<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" align="center"><font color="red">*</font>&nbsp;用户名</td>
					<td width="80%">
						<input type="text" name="user.loginName" id="user.loginName" />
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;真实姓名</td>
					<td>
						<input type="text" name="user.userName" id="user.userName" class="{required:true}" />
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;所属部门</td>
					<td>
						<input type="text"  id="userGroupName" value="" readonly="readonly" onclick="showZtree()"/>
						<div id="distree"  style="display: none">
							<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
							<div id="ztreedemo" class="ztree" ></div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;联系电话</td>
					<td>
						<input type="text" name="user.tel" id="user.tel" class="{required:true,tel:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;e_mail</td>
					<td>
						<input type="text" name="user.email" id="user.email" class="{required:true,email:true,maxlength:50}" />
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;密码</td>
					<td>
						<input type="password" name="user.loginPwd" id="loginPwd" class="{required:true,minlength:6,maxlength:20}"/>
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
						<input type="hidden" name="user.groupId" id="userGroupId" value="0"/>
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
