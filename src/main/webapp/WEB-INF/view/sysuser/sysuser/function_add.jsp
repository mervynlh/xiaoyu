<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建权限</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">


//ztree start
var setting = {
	/* check: {
		enable: false,
		chkboxType : { "Y" : "s", "N" : "s" }
	}, */
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		fontCss: getFont
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'functionId',
			pIdKey:'parentFunctionId',
			rootPid:''
		},
		key:{
			name:'functionName',
			title:'functionName'
		}
	},
	callback: {
		onClick: treeOnclick
	}
};

var treedata=${functionList};
//功能权限 1 红色显示
function getFont(treeId, node) {
	return node.functionTypeId==1 ?  {'color':'red'}:{};
}

function treeOnclick(e,treeId, treeNode) {
	$("#parentFunctionId").val(treeNode.functionId);
	$("#parentName").val(treeNode.functionName);
	$("#distree").hide();
}


$().ready(function() {
	$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	$("#addForm").validate();

});

function selectRoot(){
	$("#parentFunctionId").val(0);
	$("#parentName").val("系统权限根");
	$("#distree").hide();
}
function showZtree(){
	$("#distree").show();
}
function closetree(){
	$("#distree").hide();
}


//ztree end
	
	
	function checkSubmit(){
		var fid = document.getElementById('parentFunctionId').value;
		if(fid==null || fid<=0){
			document.getElementById('parentFunctionId').value=0;
		}
	}
	
</script>
</head>
<body>
<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>权限管理</span> &gt; <span>新建权限</span> </h4>
</div>
<div class="mt20">
	<form action="${ctx}/admin/func/addFunction" name="addForm" id="addForm" method="post" onsubmit="checkSubmit();">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>新建权限 <tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" align="center"><font color="red">*</font>&nbsp;选择父级权限</td>
					<td width="80%">
						<input id="parentName" type="text" name="pName" value="系统权限根"  onclick="showZtree()" readonly="readonly" />
						<div id="distree"  style="display: none">
							<a class="btn smallbtn btn-y" onclick="selectRoot()">根目录</a>
							<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
							<div id="ztreedemo" class="ztree" ></div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;权限名称</td>
					<td>
						<input type="text" name="function.functionName" id="functionName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;权限类型</td>
					<td>
						<select name="function.functionTypeId" id="functionTypeId">
							<option value="2">菜单权限</option>
							<option value="1">功能权限</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red"></font>&nbsp;权限URL</td>
					<td>
						<input type="text" name="function.functionUrl" id="functionUrl" />
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>显示排序(由大到小显示)</td>
					<td>
						<input type="text" name="function.sort" id="sort" class="{required:true,digits:true}" value="0"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="function.parentFunctionId" id="parentFunctionId" value="0"/>
						<input type="submit" value="提交" class="btn btn-danger"/>
						<a class="btn ml10" onclick="history.go(-1)">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
