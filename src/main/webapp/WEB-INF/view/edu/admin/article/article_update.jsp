<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>新建资讯</title>
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
initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
});
function updateArticleFormSubmit(){
	if($("#imagesUrl").val()==''){
		alert("请上传头像");
		return ;
	}
	$("#updateArticleForm").submit();
}
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=article',
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
	initKindEditor_addblog('ArticleContent','576px','400px');
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
		uploadJson : '<%=keImageUploadUrl%>' + '&param=article',// 图片上传路径
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
<form action="${ctx}/admin/article/updateArticle" method="post" id="updateArticleForm">
<input type="hidden" name="article.picture" id="imagesUrl"  value="${article.picture}"/>
<input type="hidden" name="article.id" value="${article.id}"/>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>资讯管理</span> &gt; <span>资讯修改</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>资讯基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;标题</td>
				<td>
					<input type="text" name="article.title" class="{required:true}" value="${article.title }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>标签</td>
				<td width="80%">
				<input type="text" name="article.meta" class="{required:true}" value="${article.meta }"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;分类</td>
				<td>
					<select name="article.type">
					<option value="1" <c:if test="${article.type==1 }">selected='selected'</c:if>>资讯</option>
					<option value="2" <c:if test="${article.type==2 }">selected='selected'</c:if>>公告</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>作者</td>
				<td width="80%">
				<input type="text" name="article.author" class="{required:true}" value="${article.author}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;资讯封面图片</td>
				<td>
				<img src="<%=staticImageServer %>${article.picture}" alt="" id="subjcetpic" width="400px" height="300px"/>
					<input type="button" value="上传" id="fileuploadButton"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>点击量</td>
				<td width="80%">
				<input type="text" name="article.clickTimes" class="{required:true,number:true}" value="${article.clickTimes}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;内容</td>
				<td>
					<textarea rows="" cols="" name="article.content" class="{required:true}" id="ArticleContent">${article.content}</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:updateArticleFormSubmit()">提 交</a>
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
