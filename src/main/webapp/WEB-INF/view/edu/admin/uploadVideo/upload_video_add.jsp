<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>

<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
<script type="text/javascript">
var fileuploadIndex=0;
function uploadifyUpload(){
	 if(fileuploadIndex==0)
	 {
		 alert("请选择文件 ");
		 return;
	 }	
	$('#uploadify').uploadifyUpload();

}

$(document).ready(function() {  
	
    $("#uploadify").uploadify({  
        'uploader'       : '${ctximg}/static/common/uploadify/uploadify.swf',  
        'method' 		 : 'GET', 
        'script' 		 : '<%=videoUpload%>',
        'scriptData'	 : {"base":"<%=CommonConstants.projectName%>","param":"video","ukey":"<%=CommonConstants.UKEY%>"},
        'cancelImg'      : '${ctximg}/static/common/uploadify/cancel.png',  
        'buttonImg'		 : '${ctximg}/static/common/uploadify/liulan.png',
        'fileDataName'   : 'fileupload',
        'fileDesc'       : '支持格式:*.mp4;*.flv;',
        'fileExt'        : '*.mp4;*.flv;',
        'queueID'        : 'fileQueue',  
        'auto'           : false,  
        'multi'          : false,  
        'queueSizeLimit' : 1,
        'simUploadLimit' : 1,  
        'buttonText'     : 'Browse',  
        'sizeLimit'      : 10485760000,
        onSelect:function(event, queueID, fileObj){
        	$('#uploadify').uploadifyUpload();
        	fileuploadIndex=1;
       		$("#fileQueue").html("");
       		if(fileObj.size>10485760000){
       			alert("文件太大,最大上传1G");
       			return;
       		}
    	},
        onComplete: function (event, queueID, fileObj, response, data)
        { 
        	$("#videoSize").val(fileObj.size);//视频大小
        	$("#videoUrl").val(response);//视频地址
        	return false;
        },
        onError: function(event, queueID, fileObj,errorObj)
        {
        	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
        }
    });  
}); 
function getClassifyTwoByOne(classifyOne)
{
	$("#classifyTwo").html('');
	$("#classifyTwo").hide();
	if(classifyOne>0)
	{
		$.ajax({
			url:"${ctx}/admin/videoclassify/two/"+classifyOne,  
			type: "post",
			dataType:"json",
			success:function(data){
				if(data.message=='true'&&data.entity!=null&&data.entity.length>0){
					var str="<option value='0'>--请选择--</option>";
					$.each(data.entity,function(i,val){
						str+="<option value='"+val.id+"'>"+val.name+"</option>";
					});
					$("#classifyTwo").html(str);
					$("#classifyTwo").show();
				}
			}
		});	
	}
}
function addSubmit(){
	if($("#title").val()==null||$("#title").val()==""){
		alert("请填写标题");
		return;
	}
	if($("#classifyOne").val()==null||$("#classifyOne").val()==0){
		alert("请选择一级分类");
		return;
	}
	if($("#classifyTwo").val()==null||$("#classifyTwo").val()==0){
		alert("请选择二级分类");
		return;
	}
	if($(".fileName").html()==""||$(".fileName").html()==null)
	{
		alert("请上传视频");
		return;
	}
	if($(".percentage").html().indexOf("100")==-1)
	{
		alert("请等待视频上传完成");
		return;
	}
	$("#addForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频添加</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/uploadVideo/add" method="post" id="addForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>视频基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;视频标题： </td>
					<td>
						<input type="text" name="uploadVideo.title" id="title" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;所属分类：</td>
					<td>
						<select id="classifyOne" name="uploadVideo.classifyOne" onchange="getClassifyTwoByOne(this.value)">
							<option value="0">--请选择--</option>
							<c:forEach items="${videoClassifys}" var="videoClassifyOne">
								<option value="${videoClassifyOne.id}" <c:if test="${videoClassifyOne.id==queryUploadVideo.id}">selected="selected"</c:if>>${videoClassifyOne.name}</option>
							</c:forEach>
						</select>
						<select id="classifyTwo" style="display: none" name="uploadVideo.classifyTwo">
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;上传视频：</td>
					<td>
						<input type="hidden" name="uploadVideo.videoUrl" id="videoUrl"/>
						<input type="hidden" name="uploadVideo.size" id="videoSize"/>
						<div id="fileQueue"></div> 
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="file" name="uploadify" id="uploadify" />
							<input type="button" onclick="uploadifyUpload()" value="开始上传" style="width: 100px; height: 30px; padding-top: 0px;margin-left:10px;position:absolute;" />
							<br/>&nbsp;<font color="red">*支持MP4、FLV格式</font>
						</span>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;视频简介：</td>
					<td>
						<textarea rows="3" cols="80" name="uploadVideo.info"></textarea>
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
</body>
</html>