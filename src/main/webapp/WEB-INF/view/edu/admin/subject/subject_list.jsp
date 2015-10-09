<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>项目列表</title>
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
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'subjectId',
				pIdKey:'parentId',
				rootPid:''
			},
			key:{
				name:'subjectName',
				title:'subjectNameAndId'
			}
		},
		callback: {
			onClick: treeOnclick
			/* 
			onCheck: treeonCheck,
			beforeMouseDown:beforeMouseDown,
			beforeMouseUp: beforeMouseUp,
			beforeRightClick: beforeRightClick,
			onMouseDown: onMouseDown,
			onMouseUp: onMouseUp,
			onRightClick: onRightClick */
		}
	};
	var treedata=${subjectList};
	
	function treeOnclick(e,treeId, treeNode) {
		window.location.href="${ctx}/admin/subj/toUpdateSubject?subject.subjectId="+treeNode.subjectId;
	}
	
	function treeonCheck(e,treeId, treeNode) {
		/*var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
		checkCount = zTree.getCheckedNodes(true).length,
		nocheckCount = zTree.getCheckedNodes(false).length,
		changeCount = zTree.getChangeCheckedNodes().length;
		var getCheckedNodes=zTree.getCheckedNodes(true);
	  	 $.each(getCheckedNodes,function(index,val){  
        });  */
	}
	
	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}
	
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNodeFalse);
	});
	//ztree end

	function addSubject() {
		window.location.href = "${ctx}/admin/subj/saveSubjectInit";
	}
	
	function delSubjects() {
		
	    if (confirm("确定删除选中的项目？")) {
	    	var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
			checkCount = zTree.getCheckedNodes(true).length;
	    	if(checkCount>0){
	    		var checkedNodes=zTree.getCheckedNodes(true);
	    		var arrayObj = new Array()
			  	$.each(checkedNodes,function(index,val){  
			  		arrayObj.push(val.subjectId);  
		        }); 
	    		$("#ids").val(arrayObj.join(","));
		        document.updateSubjectForm.submit();
	    	}else{
	    		alert("未选中项目");
	    	}
			
	    } else {
	        return false;
	    }
	}
  </script>
</head>
<body  >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>项目管理</span> &gt; <span>项目列表</span> </h4>
</div>
<div class="mt20">
	<div class="commonWrap">
	<form action="/admin/subj/delSubjects" method="post" id="updateSubjectForm" name="updateSubjectForm">
	<input type="hidden" name="ids" id="ids"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>项目列表<font color="red">(树级结构最多支持二级)</font></span></th>
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
						<input type="button" value="新建项目 " onclick="addSubject();"  class="btn btn-danger"/>
						<input type="button" value="删除选中项目" onclick="delSubjects();"  class="btn btn-danger"/>
						<input type="reset" id="checkAllFalse" value="清空选中"  class="btn btn-danger"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</div>
</body>
</html>
