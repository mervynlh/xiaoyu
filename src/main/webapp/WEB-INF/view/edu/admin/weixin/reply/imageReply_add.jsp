<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/weixin/weixin.js?v=${v}"></script>
<script type="text/javascript">
$(document).ready(function(){
	//初始化 Kindeditor
	KindEditor.create('#content',{
	   resizeType : 1,
	   allowPreviewEmoticons : false,
	   allowUpload : true,
	   syncType : 'auto',
	   urlType : 'absolute',
	   uploadJson : '<%=keImageUploadUrl%>&param=weixin',
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
	
    $("#fileupload").uploadify({
        'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
        'method' : 'GET', 
        'script'  :'<%=uploadSwfUrl%>',
        'scriptData':{"base":"<%=CommonConstants.projectName%>","param":"weixin"},
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
        	$("#weixinpic").attr("src","<%=staticImageServer%>"+response);
        	$("#imageUrl").val(response);	
        },
        onError: function(event, queueID, fileObj,errorObj)
        {
        	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
        }
    });
    
});
	function addSubmit(){
		if($("#keyword").val()==null||$("#keyword").val()==""){
			alert("关键词不能为空");
			return;
		}
		if($("#title").val()==null||$("#title").val()==""){
			alert("标题不能为空");
			return;
		}
		if($("#imageUrl").val()==null||$("#imageUrl").val()==''){
			alert("请上传封面");
			return
		}
		if($("#content").val()==null||$("#content").val()==""){
			alert("内容不能为空");
			return;
		}
		
		$("#addForm").submit();
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>新建图文素材</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/weixin/add" method="post" id="addForm">
		<input type="hidden" name="weixinReply.imageUrl" id="imageUrl"/>
		<input type="hidden" name="weixinReply.msgType" value="2"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>图文素材基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;关键词 </td>
					<td>
						<input type="text" name="weixinReply.keyword" id="keyword" class="{required:true}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*可设置多个关键字用单个空格隔开，如网校 教育 在线 </font>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;标题 </td>
					<td>
						<input type="text" name="weixinReply.title" id="title" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;封面 </td>
					<td>
						<span> 
							<img src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="360px" height="200px" id="weixinpic" />
							<div id="fileQueue" style="margin-top: 30px; border: 0px"></div>
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="file" id="fileupload"/>
							<input type="button" onclick="uploadifyUpload()" value="上传" style="width: 100px; height: 30px; padding-top: 0px;margin-left:10px;position:absolute;" />
							<br/>&nbsp;<font color="red">*图片链接，支持JPG、PNG格式，尺寸（宽360像素，高200像素）小于512kb</font>
						</span>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;内容</td>
					<td>
						<textarea id="content" name="weixinReply.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;"></textarea>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>