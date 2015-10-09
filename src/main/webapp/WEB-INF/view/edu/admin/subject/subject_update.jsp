<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>更新项目</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">
	
	
	//ztree
	var setting = {
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
				title:'subjectName'
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};

	var treedata=${subjectList};
	
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
	});
	
	function treeOnclick(e,treeId, treeNode) {
		$("#subjectpid").val(treeNode.subjectId);
		$("#subjectpname").val(treeNode.subjectName);
		$("#distree").hide();
	}

	function selectRoot(){
		$("#subjectpid").val(0);
		$("#subjectpname").val("根目录");
		$("#distree").hide();
	}
	function showZtree(){
		$("#distree").show();
	}
	function closetree(){
		$("#distree").hide();
	}
	//ztree
	
	var thisLevle=<c:out  value="${thissubject.level}"/>;
	var thisPid=<c:out  value="${thissubject.parentId}"/>;
	var thisSubjectId=<c:out  value="${thissubject.subjectId}"/>;
	$().ready(function(){
		$("#status").val(<c:out  value="${thissubject.status}"/>);
		initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
	});
	
		
	function mysubmit(){
		if($("#subject\\.subjectName").val()==""){
			alert("请输入项目名称！");
			return false;
		}
		if(thisSubjectId==$("#subjectpid").val()){
			alert("上级不能选择本身节点！");
			return false;
		}
		$("#updateSubjectForm").submit();
	}
	
	
</script>
<style>
.tdLabel {align ="center"
}
.label {
	color: red
}
</style>
</head>
<body  >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>项目管理</span> &gt; <span>更新项目</span> </h4>
</div>
	<div  class="mt20">
		<div class="commonWrap">
		<form id="updateSubjectForm" action="${ctx}/admin/subj/updateSubject" method="post">
			<input type="hidden" name="subject.parentId" id="subjectpid" value="${thissubject.parentId}" /> 
			<input type="hidden" value="${thissubject.subjectId }" name="subject.subjectId" /> 
			<input type="hidden" name="subject.level" id="subjectlevel" value="${thissubject.level}" /> 
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th colspan="2" align="left"><span>更新项目<tt
									class="c_666 ml20 fsize12">
									（<font color="red">*</font>&nbsp;为必填项）
								</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr  id="oneGrade">
					<td align="center"><font color="red">*</font>&nbsp;上级</td>
					<td> 
						<input type="text"  id="subjectpname" value="${parentSubject.subjectName}" readonly="readonly" onclick="showZtree()"/>
						<div id="distree"  style="display: none">
						<a class="btn smallbtn btn-y" onclick="selectRoot()">根目录</a>
						<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
						<div id="ztreedemo" class="ztree" ></div>
						</div>
					
					</td>
				</tr>
					
					<tr>
						<td width="20%" align="center"><font color="red">*</font>&nbsp;项目名称</td>
						<td width="80%">
						<input name="subject.subjectName" id="subject.subjectName" type="text"
							value="<c:out value="${thissubject.subjectName}"/>" />
							</td>
					</tr>

					<tr>
						<td align="center"><font color="red">*</font>&nbsp;状态</td>
						<td><select name="subject.status" id="status">
								<option value="0">正常</option>
								 <option value="1">删除</option>
								<!-- <option value="2">删除</option> -->
						</select></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						<input class="btn btn-danger" type="button" onclick="mysubmit()" value="提 交" />
						<input class="btn ml10" type="reset" onclick="window.history.go(-1)" value="返 回" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		</div>
	</div>
</body>
</html>
