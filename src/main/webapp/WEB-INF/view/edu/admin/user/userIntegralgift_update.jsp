<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改积分礼品</title>

<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
	initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
});
function updateGiftFormSubmit(){
	if($("#giftName").val()==''){
		alert("请填写礼品名称");
		return ;
	 }
	if($("#score").val()==''){
		alert("请填写使用积分");
		return ;
	 }
	if($("#imagesUrl").val()==''){
		alert("请上传礼品图片");
		return ;
	 }
	if($("#ArticleContent").val()==''){
		alert("请填写内容");
		return ;
	}
	
	submitForm();
}
function submitForm(){
	$("#addArticleForm").submit();
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
	initKindEditor_addblog('ArticleContent','576px','151px');
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


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>礼品管理</span> &gt; <span>礼品修改</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/user/updategift" method="post" id="addArticleForm">
	<input type="hidden" name="userIntegralGift.id" id="id"  value="${userIntegralGift.id}"/>
	<input type="hidden" name="userIntegralGift.logo" id="imagesUrl"  value="${userIntegralGift.logo}"/>
	<input type="hidden" name="userIntegralGift.courseId" id="courseId"  value="0"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>礼品基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;积分礼品名称</td>
				<td>
					<input type="text" name="userIntegralGift.name" class="{required:true}" id="giftName" value="${userIntegralGift.name}"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>使用分数</td>
				<td width="80%">
				<input type="text" name="userIntegralGift.score" class="{required:true,number:true,digits:true}" id="score" value="${userIntegralGift.score}"/>
				</td>
			</tr>
			<!-- <tr>
				<td align="center"><font color="red">*</font>&nbsp;礼品分类</td>
				<td>
					<select name="userIntegralGift.flag" onchange="chooseGift(this.value)" id="giftType">
					<option value="1">积分礼品</option>
					<option value="2">课程礼品</option>
					</select>
				</td>
			</tr> -->
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;礼品图片</td>
				<td>
				<img src="<%=staticImageServer %>${userIntegralGift.logo}" alt="" id="subjcetpic" width="178px" height="133px"/>
					<input type="button" value="上传" id="fileuploadButton"/>
					<font color="red">*图片链接，支持JPG、PNG格式，尺寸（宽178像素，高133像素）小于256kb</font>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;详细内容</td>
				<td>
					<textarea rows="" cols="" name="userIntegralGift.content" id="ArticleContent">${userIntegralGift.content}</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:updateGiftFormSubmit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	</div>
</div>
</body>
</html>
