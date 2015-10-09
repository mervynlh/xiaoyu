<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建项目</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">

//ztree start
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
			rootPid:'1'
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

$(function(){
	$.fn.zTree.init($("#pztree"), setting, treedata);
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

//ztree end
$(function(){
	initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
});

function mysubmit(){
	if($("#subject\\.subjectName").val()==""){
		alert("请输入项目名称！");
		return false;
	}
	$("#addSubjectForm").submit();
}
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=subject',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');//absolute,domain
					K('#'+urlId+'').attr("src",data.url);
					$("#"+urlId).show();
					$('#'+valSet+'').val(url);
				} else {
					alert(data.message);
				}
			},
			afterError : function(str) {
				alert('自定义错误信息: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
	});
}

</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body  >

<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>项目管理</span> &gt; <span>新建项目 </span> </h4>
</div>
<div class="mt20">

<form id="addSubjectForm" action="${ctx}/admin/subj/saveSubject" method="post">
<input type="hidden" name="subject.image" id="imagesUrl"  />
<input type="hidden" name="subject.level" id="subjectlevel" value="1" />
<input type="hidden" name="subject.parentId" id="subjectpid" value="0" />
	<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>新建项目 <tt class="c_666 ml20 fsize12"> <font color="red">(树级结构最多支持二级)</font> </tt></span></th>
			</tr>
		</thead>
		<tbody>
		
			<tr  id="oneGrade">
					<td align="center"><font color="red">*</font>&nbsp;上级</td>
					
					<td> 
					<input type="text"  id="subjectpname" value="根目录" readonly="readonly" onclick="showZtree()"/>
					
					<div id="distree"  style="display: none">
					<a class="btn smallbtn btn-y" onclick="selectRoot()">根目录</a>
					<a class="btn smallbtn btn-y" onclick="closetree()">关闭</a>
					<div id="pztree" class="ztree" ></div>
					</div>
					
					</td>
			</tr>
			
			
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;项目名称</td>
				<td width="80%">
					<input name="subject.subjectName" id="subject.subjectName" type="text"/>
				</td>
			</tr>
			
			
			<tr style="display: none">
				<td align="center"><font color="red">*</font>&nbsp;状态</td>
				<td>
					<select name="subject.status" >
					<option value="0" selected="selected">正常</option>
					<option value="1">删除</option>
					<!-- <option value="2">删除</option> -->
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn btn-danger" type="button" onclick="mysubmit()" value="提 交"/>
					<input class="btn ml10" type="button" onclick="history.go(-1)" value="返 回"/>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</form>
</div>
</body>
</html>
