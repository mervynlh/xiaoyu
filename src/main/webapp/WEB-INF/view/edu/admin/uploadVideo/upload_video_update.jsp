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
$(function(){
	var classifyOne='${uploadVideo.classifyOne}';
	var classifyTwo='${uploadVideo.classifyTwo}';
	if(classifyTwo!=0&&classifyTwo!=null)
	{
		getClassifyTwoByOne(classifyOne);
		$("#classifyTwo").val(classifyTwo);
	}
})

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
			async:false,
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
function updateSubmit(){
	if($("#title").val()==null||$("#title").val()==""){
		alert("请填写标题");
		return;
	}
	if($("#classifyTwo").val()==null||$("#classifyTwo").val()==0){
		alert("视频只能放在二级分类下");
		return;
	}
	
	$("#updateForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频添加</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/uploadVideo/update" method="post" id="updateForm">
		<input type="hidden" value="${uploadVideo.id }" name="uploadVideo.id">
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
						<input type="text" name="uploadVideo.title" value="${uploadVideo.title}" id="title" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;所属分类：</td>
					<td>
						<select id="classifyOne" name="uploadVideo.classifyOne" onchange="getClassifyTwoByOne(this.value)">
							<option value="0">--请选择--</option>
							<c:forEach items="${videoClassifys}" var="videoClassifyOne">
								<option value="${videoClassifyOne.id}" <c:if test="${videoClassifyOne.id==uploadVideo.classifyOne}">selected="selected"</c:if>>${videoClassifyOne.name}</option>
							</c:forEach>
						</select>
						<select id="classifyTwo" style="display: none" name="uploadVideo.classifyTwo">
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;视频链接：</td>
					<td>
						<textarea rows="3" cols="80" readonly="readonly">${uploadVideo.videoUrl}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;视频简介：</td>
					<td>
						<textarea rows="3" cols="80" name="uploadVideo.info">${uploadVideo.info}</textarea>
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