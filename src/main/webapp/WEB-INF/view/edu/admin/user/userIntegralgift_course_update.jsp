<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改课程礼品</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
	$("#addArticleForm").validate();
});
function addArticleFormSubmit(){
	if($("#courseId").val()==0){
		alert("请选择课程");
		return;
	 }
	submitForm();
}
function submitForm(){
	$("#addArticleForm").submit();
}
function addCourse(){
	window.open('${ctx}/admin/cou/openerlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
}
function openerCourse(courseArray){
	$("#courseId").val(courseArray[0]);
	$("#giftName").val(courseArray[1]);
}
</script>
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>礼品管理</span> &gt; <span>礼品修改</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/user/updategift" method="post" id="addArticleForm">
	<input type="hidden" name="userIntegralGift.id" id="id"  value="${userIntegralGift.id}"/>
	<input type="hidden" name="userIntegralGift.courseId" id="courseId"  value="${userIntegralGift.courseId}"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>礼品基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;课程礼品名称</td>
				<td>
					<input type="text" name="userIntegralGift.name" class="{required:true}" id="giftName" value="${userIntegralGift.name}"/>&nbsp;&nbsp;<a class="btn btn-danger" title="提 交" href="javascript:addCourse()"  id="addCourse">添加课程</a>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>使用分数</td>
				<td width="80%">
				<input type="text" name="userIntegralGift.score" class="{required:true,number:true,digits:true}" id="score" value="${userIntegralGift.score}"/>
				</td>
			</tr>
			<!-- <tr class="courseGift">
				<td align="center"><font color="red">*</font>&nbsp;详细内容</td>
				<td>
					<textarea rows="" cols="" name="userIntegralGift.content" id="ArticleContent"></textarea>
				</td>
			</tr> -->
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:addArticleFormSubmit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</div>
<!-- /tab4 end -->
</div>
