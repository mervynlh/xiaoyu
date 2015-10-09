<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>机构修改</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
	$("#updateInstitutionForm").validate();
	initSimpleImageUpload("inst_btn","inst_pic","inst_url"); 
	initSimpleImageUpload("lice_btn","lice_pic","lice_url");
	initSimpleImageUpload("id_btn","id_pic","id_url");
});
function updateInstitutionFormSubmit(){
	
	$("#updateInstitutionForm").submit();
}
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=institution',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');//absolute,domain
					K('#'+urlId+'').attr("src",'<%=staticImageServer%>'+data.url);
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
window.onload=function(){//编辑器初始化
	initKindEditor_addblog('InstitutionContent','576px','200px');
};
//添加博文页面编辑器
function initKindEditor_addblog(id, width, height) {
	EditorObject = KindEditor.create('textarea[id=' + id + ']', {
		resizeType : 1,
		filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		width : width,
		height : height,
		minWidth : '10px',
		minHeight : '10px',
		uploadJson : '<%=keImageUploadUrl%>' + '&param=institution',// 图片上传路径
		afterBlur : function() {
			this.sync();
		},
		allowFileManager : false,
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image', 'link','plainpaste' ]
	});
	

}
</script>
</head>
<body >
<form action="${ctx}/admin/institution/updateInstitution" method="post" id="updateInstitutionForm">
	<input type="hidden" name="institution.id" value="${institution.id}"/>
	<input type="hidden" name="institution.instPictureUrl" id="inst_url"  value="${institution.instPictureUrl }"/>
	<input type="hidden" name="institution.licensePictureUrl" id="lice_url"  value="${institution.licensePictureUrl }"/>
	<input type="hidden" name="institution.idPictureUrl" id="id_url"  value="${institution.idPictureUrl }"/>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>机构管理</span> &gt; <span>机构修改</span> </h4>
</div>
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>机构基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;机构图片</td>
				<td>
					<img src="<%=staticImageServer%>${institution.instPictureUrl}" id="inst_pic" width="150px" height="150px"/>
					<input type="button" value="上传" id="inst_btn"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;机构名称</td>
				<td>
					<input type="text" name="institution.name" class="{required:true}" value="${institution.name }"/>
				</td>
			</tr>	
			<tr>
				<td align="center"><font color="red">*</font>机构简介</td>
				<td>
					<textarea rows="5" cols="50" name="institution.description" class="{required:true}">${institution.description }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;营业执照</td>
				<td>
					<img src="<%=staticImageServer%>${institution.licensePictureUrl}"  id="lice_pic" width="150px" height="150px"/>
					<input type="button" value="上传" id="lice_btn"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>申请人</td>
				<td>
					<input type="text" name="institution.applicant" class="{required:true}" value="${institution.applicant }"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;身份证</td>
				<td>
					<img src="<%=staticImageServer%>${institution.idPictureUrl}"  id="id_pic" width="150px" height="150px"/>
					<input type="button" value="上传" id="id_btn"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>手机号</td>
				<td>
					<input type="text" name="institution.mobile" class="{required:true}" value="${institution.mobile }"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>企业邮箱</td>
				<td>
					<input type="text" name="institution.email" class="{required:true}" value="${institution.email }"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:updateInstitutionFormSubmit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
