<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
$(function(){
	$("#menuName").keyup(function(){
		$("#menuNameCount").text($.trim($(this).val()).length);
	});
	//初始化 Kindeditor
	KindEditor.create('#content',{
	   resizeType : 1,
	   allowPreviewEmoticons : false,
	   allowUpload : true,
	   syncType : 'auto',
	   urlType : 'absolute',
	   uploadJson : '<%=keImageUploadUrl%>&param=help',
		  afterBlur : function() {
			this.sync();
		  },
	   allowFileManager : false,
	   items : ['source','cut', 'copy', 'paste','plainpaste','|','fontname', 'fontsize', '|','forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright','justifyfull',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image', 'link']
	});
});
				

function updateSubmit(){
	if($("#menuName").val()==null||$("#menuName").val()==''){
		alert("请填写菜单名称");
		return;
	}
	var parentId='${helpMenu.parentId}';
	if(parentId>0){
		if($("#menuOne").val()==0){
			alert("请选择一级菜单");
			return;
		}
		if($("#content").val()==null||$("#content").val()==''){
			alert("请填写帮助内容");
			return;
		}
	}
	if(isNaN($("#sort").val())){
		alert("排序只能为数字");
		return;
	}
	$("#updateForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>帮助中心管理</span> &gt; <span>帮助菜单编辑</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/helpMenu/update" method="post" id="updateForm">
		<input type="hidden" value="${helpMenu.id}" name="helpMenu.id">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>帮助菜单基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr <c:if test="${helpMenu.parentId<=0}">style="display: none"</c:if>>
					<td align="center"><font color="red">*</font>&nbsp;选择一级菜单：</td>
					<td>
						<select id="menuOne" name="helpMenu.parentId">
							<option value="0">--请选择--</option>
							<c:forEach items="${helpMenus}" var="helpMenuOne">
								<option value="${helpMenuOne.id}" <c:if test="${helpMenuOne.id==helpMenu.parentId}">selected="selected"</c:if>>${helpMenuOne.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;菜单名称：</td>
					<td>
						<input id="menuName" type="text" name="helpMenu.name" maxlength="9" value="${helpMenu.name}" class="{required:true,number:true}"/>
						<em id="menuNameCount" style="color: #ff0000;">0</em>/9
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;帮助内容：</td>
					<td>
						<textarea id="content" name="helpMenu.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">${helpMenu.content}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;排序：</td>
					<td>
						<input id="sort" type="text" name="helpMenu.sort" value="${helpMenu.sort}" class="{required:true,number:true}"/>
						<font color="red">倒序</font>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red"></font>&nbsp;外链：</td>
					<td>
						<input id="linkBuilding" type="text" name="helpMenu.linkBuilding" value="${helpMenu.linkBuilding }" />
						<font color="red">(选填)</font>
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