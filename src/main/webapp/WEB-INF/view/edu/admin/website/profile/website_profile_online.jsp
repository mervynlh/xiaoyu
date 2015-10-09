<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>在线咨询</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	$("#fileupload").uploadify({
        'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
        'method' : 'GET', 
        'script'  :'<%=uploadSwfUrl%>',
        'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"online"},
        'queueID':'fileQueue',
        'fileDataName':'fileupload',
        'auto':false,
        'multi':false,
        'hideButton':false,
        'buttonText':'Browse',
        'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
        'width':105,
        'simUploadLimit' : 3,
        'sizeLimit'      : 512000,
        'queueSizeLimit' : 2,
        'fileDesc'       : '支持格式:jpg/png.',
        'fileExt'        : '*.jpg;*.png;',
        'folder' : '/upload',
        'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
         onSelect:function(event, queueID, fileObj){$('#fileupload').uploadifyUpload();
         	fileuploadIndex=1;
        	$("#fileQueue").html("");
        	if(fileObj.size>512000){
        		alert("文件太大最大上传512kb");
        		return;
        	}
        	
        	},
        onComplete: function (event, queueID, fileObj, response, data)
        { 
        	$("#onlinepic").attr("src","<%=staticImageServer%>"+response);
        	$("#imageUrl").val(response);	
        },
        onError: function(event, queueID, fileObj,errorObj)
        {
        	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
        }
    });
});
function uploadifyUpload(){
	 if(fileuploadIndex==0)
	 {
		 alert("请选择图片 ");
		 return;
	 }	
	$('#fileupload').uploadifyUpload();

}
function doSubmit(){
	if($("#link").val()==null||$("#link").val()==''){
		alert("请填写咨询链接");
		return;
	}
	if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
		alert("请上传二维码");
		return ;
	 }
	$("#addOnlineForm").submit();
}
</script>
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>在线咨询管理</span> &gt; <span>在线咨询</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/websiteProfile/online/update" method="post" id="addOnlineForm">
	<input type="hidden" name="onlineImageUrl" id="imageUrl"  value="${websiteonlinemap.online.onlineImageUrl}"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>在线咨询基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;咨询开关</td>
				<td>
					<select name="onlineKeyWord">
					<option value="ON" <c:if test="${websiteonlinemap.online.onlineKeyWord=='ON'}">selected='selected'</c:if>>ON</option>
					<option value="OFF" <c:if test="${websiteonlinemap.online.onlineKeyWord=='OFF'}">selected='selected'</c:if>>OFF</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>咨询链接</td>
				<td width="80%">
				<input type="text" name="onlineUrl" class="{required:true}" id="link" value="${websiteonlinemap.online.onlineUrl}"/>
				</td>
			</tr>
			
						
				
			
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;二维码</td>
				<td>
					<span> 
						<img src="<%=staticImageServer %>${websiteonlinemap.online.onlineImageUrl}" alt="" width="200px" height="200px" id="onlinepic" />
						<div id="fileQueue" style="margin-top: 30px; border: 0px"></div>
					</span>
					<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
						<input type="file" id="fileupload"/>
						<input type="button" onclick="uploadifyUpload()" value="上传" style="width: 100px; height: 30px; padding-top: 0px;margin-left:10px;position:absolute;" />
						<br/>&nbsp;<font color="red">*图片链接，支持JPG、PNG格式，尺寸（200*200像素）小于512kb</font>
					</span>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:doSubmit()">修改</a>
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
