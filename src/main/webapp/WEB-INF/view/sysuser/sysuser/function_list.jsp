<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>角色列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>
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

//功能权限红色显示1
function getFont(treeId, node) {
	return node.functionTypeId==1 ?  {'color':'red'}:{};
}
//单击事件
function treeOnclick(e,treeId, treeNode) {
	window.location.href="${ctx}/admin/func/toUpdateFunction?function.functionId="+treeNode.functionId;
}

function treeonCheck(e,treeId, treeNode) {
	/* var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
	checkCount = zTree.getCheckedNodes(true).length,
	nocheckCount = zTree.getCheckedNodes(false).length,
	changeCount = zTree.getChangeCheckedNodes().length;
	var getCheckedNodes=zTree.getCheckedNodes(true); 
	 $.each(getCheckedNodes,function(index,val){  
  	});
	*/
}

//取消全部选中
function checkNodeFalse() {
	var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
	zTree.checkAllNodes(false);
}

$().ready(function() {
	$.fn.zTree.init($("#ztreedemo"), setting, treedata);
});
	
//ztree end


	function addFunction() {
		window.location.href = "${ctx}/admin/func/toAddFunction";
	}
	
	function delFunctions() {
	    if (confirm("确定删除选中的权限及子权限？")) {
	    	var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
			checkCount = zTree.getCheckedNodes(true).length;
	    	if(checkCount>0){
	    		var checkedNodes=zTree.getCheckedNodes(true);
	    		var arrayObj = new Array()
			  	$.each(checkedNodes,function(index,val){  
			  		arrayObj.push(val.functionId);  
		        }); 
	    		$("#ids").val(arrayObj.join(","));
	    		 $("#updateRoleFunctionForm").submit();
	    	}else{
	    		alert("未选中部门");
	    	}
	    } else {
	        return false;
	    } 
	}
  </script>
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>系统管理</span> &gt; <span>权限设置 </span> </h4>
</div>
<div class="mt20">
	<form action="${ctx}/admin/func/delFunctions" method="post" id="updateRoleFunctionForm">
	<input type="hidden" id="ids" name="ids" value=""/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>权限设置</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					
					<td width="20%" >
					<div id="ztreedemo" class="ztree"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="新建权限" onclick="addFunction();"  class="btn btn-danger"/>
						<input type="button" value="删除选中权限" onclick="delFunctions();"  class="btn btn-danger"/>
						<input type="button" onclick="checkNodeFalse();"  value="清空"  class="btn btn-danger"/>
						<font color="red">(*红字为功能权限，其他为菜单权限)</font>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>
