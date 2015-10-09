<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="${ctximg}/static/common/jquery.bigcolorpicker.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery.bigcolorpicker.css?v=${v}"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
$(function(){
	$("#classifyName").keyup(function(){
		$("#classifyNameCount").text($.trim($(this).val()).length);
	});
	
});
				

function updateSubmit(){
	var flag=$('input[name="classifyChoose"]:eq(1)').prop("checked");
	if(flag){
		if($("#classifyOne").val()==0){
			alert("请选择一级分类");
			return;
		}
	}
	if($("#classifyName").val()==null||$("#classifyName").val()==''){
		alert("请填写分类名称");
		return;
	}
	$("#updateForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>视频分类管理</span> &gt; <span>视频分类添加</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/videoclassify/update" method="post" id="updateForm">
		<input type="hidden" value="${videoClassify.id}" name="videoClassify.id">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>视频分类基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr <c:if test="${videoClassify.parentId<=0||videoClassify.keyword!=null}">style="display: none"</c:if> id="classify">
					<td align="center"><font color="red">*</font>&nbsp;选择一级分类：</td>
					<td>
						<select id="classifyOne" name="videoClassify.parentId">
							<c:forEach items="${videoClassifys}" var="videoClassifyOne">
								<option value="${videoClassifyOne.id}" <c:if test="${videoClassifyOne.id==videoClassify.parentId}">selected="selected"</c:if>>${videoClassifyOne.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;分类名称：</td>
					<td>
						<input id="classifyName" type="text" name="videoClassify.name" value="${videoClassify.name}" maxlength="8" class="{required:true,number:true}"/>
						<em id="classifyNameCount" style="color: #ff0000;">0</em>/8
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>