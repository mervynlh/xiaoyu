<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>部门修改</title>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">

			//ztree start
			var setting = {
				check: {
					enable: true,
					chkboxType : { "Y" : "s", "N" : "s" }
				},
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
				$("#group\\.parentGroupId").val(treeNode.groupId);
				$("#group_pname").val(treeNode.groupName);
				$("#distree").hide();
			}

			//取消全部选中
			function checkNodeFalse() {
				var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
				zTree.checkAllNodes(false);
			}
			$().ready(function() {
				$.fn.zTree.init($("#ztreedemo"), setting, treedata);
				var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
				zTree.expandAll(true);//展开全部
				$("#updateForm").validate();

			});
			
			function selectRoot(){
				$("#group\\.parentGroupId").val(0);
				$("#group_pname").val("根目录");
				$("#distree").hide();
			}
			function showZtree(){
				$("#distree").show();
			}
			function closetree(){
				$("#distree").hide();
			}
			//ztree end
			
			var groupId = "${group.groupId}";
			
			function checkSubmit(){
				var fid = $("#group\\.parentGroupId").val();
				if(fid==groupId){
					alert("父级不能选择本身!");
					return false;
				}
				return true;
			}

		</script>
	</head>
<body>
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>部门管理</span> &gt; <span>部门修改</span> </h4>
</div>
			
	<form action="${ctx}/admin/group/updateGroup" name="updateForm" id="updateForm" method="post" onsubmit=" return checkSubmit();">
	<div class="mt20">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>部门修改<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" align="center"><font color="red">*</font>&nbsp;选择部门父级</td>
					<td width="80%">
						<input type="text"  id="group_pname" value="${parentGroup.groupName}" readonly="readonly" onclick="showZtree()"/>
						<div id="distree"  style="display: none">
							<a class="btn smallbtn btn-y" onclick="selectRoot()">根目录</a>
							<div id="ztreedemo" class="ztree" ></div>
							<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;部门名称</td>
					<td>
						<input type="text" name="group.groupName" id="group.groupName" class="{required:true}" value="<c:out value='${group.groupName}'/>" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="hidden" name="group.parentGroupId" id="group.parentGroupId" value="${group.parentGroupId}" />
					<input type="hidden" name="group.level" id="group.level" value="<c:out value="${group.level}"/>" />
					<input type="hidden"  value="<c:out value="${group.groupId}"/>" name="group.groupId" id="group.groupId" />
					<input type="submit" value="提交" class="btn btn-danger"/>
					<input type="button" onclick="history.go(-1)" value="返回" class="btn ml10"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /tab4 end -->
	</form>
	</body>
</html>
