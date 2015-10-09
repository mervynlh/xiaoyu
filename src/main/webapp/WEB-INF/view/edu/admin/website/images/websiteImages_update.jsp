<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery.bigcolorpicker.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery.bigcolorpicker.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
$(document).ready(function(){
	$("#imageColor").bigColorpicker("imageColor","L",10);
	initSimpleImageUpload("imgButton","imgpic","imagesUrl");
	initSimpleImageUpload("mobileImgButton","mobileImgpic","mobileImagesUrl");
    initSimpleImageUpload("previewButton","previewpic","previewUrl");
	$("#keyWord").bind("change",function(){
		if(this.value!='indexCenterBanner'&&this.value!=''){
			$("#backColor").hide();
		}else{
			$("#backColor").show();
		}
        //个人中心模版增加缩略图
        if(this.value=='userPersonalityImages'){
            $("#previewpic_tr").show();
            $("#previewUrl_tr").show();
        }else{
            $("#previewpic_tr").hide();
            $("#previewUrl_tr").hide();
        }
	});
	$("#keyWord").change();
});
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=bannerImages',
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
function updateSubmit(){
	if($("#imagesTitle").val()==null||$("#imagesTitle").val()==''){
		alert("请填写标题");
		return;
	}
	if($("#imagesUrl").val()==null||$("#imagesUrl").val()==''){
		alert("请上传广告图");
		return
	}
	if($("#keyWord").val()==null||$("#keyWord").val()==''){
		alert("请选择类型");
		return;
	}
	$("#updateImagesForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>广告图管理</span> &gt; <span>广告图更新</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/website/updateImages" method="post" id="updateImagesForm">
		<input type="hidden" name="websiteImages.id" value="${websiteImages.id }" />
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>广告图基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;类型</td>
					<td>
						<select name="websiteImages.keyWord" id="keyWord">
							<option value="">--请选择关键字--</option>
							<option value="indexCenterBanner" <c:if test="${websiteImages.keyWord=='indexCenterBanner'}">selected="selected"</c:if>>首页Banner图片</option>
							<%-- <option value="mobileIndexCenterBanner" <c:if test="${websiteImages.keyWord=='mobileIndexCenterBanner'}">selected="selected"</c:if>>微站首页Banner图片</option> --%>
							<option value="indexAdvertImages1" <c:if test="${websiteImages.keyWord=='indexAdvertImages1'}">selected="selected"</c:if>>首页广告图片1</option>
							<option value="indexAdvertImages2" <c:if test="${websiteImages.keyWord=='indexAdvertImages2'}">selected="selected"</c:if>>首页广告图片2</option>
							<%-- <option value="userPersonalityImages" <c:if test="${websiteImages.keyWord=='userPersonalityImages'}">selected="selected"</c:if>>个人中心模板图片</option> --%>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;广告图标题 </td>
					<td>
						<input type="text" name="websiteImages.title" value="${websiteImages.title}" id="imagesTitle" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;图片链接 </td>
					<td>
						<input type="text" name="websiteImages.imagesUrl" id="imagesUrl" value="${websiteImages.imagesUrl}" style="width: 485px"/>
						<font color="red">*图片链接,可直复制地址或者通过上传</font>
					</td>
				</tr>
				<tr>
					<td width="20%" align="center"><font color="red">*</font>广告图</td>
					<td width="80%">
						<span> 
							<img src="<%= staticImageServer%>${websiteImages.imagesUrl}" alt="" width="360px" height="200px" id="imgpic" />
							
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="button" value="上传" id="imgButton"/>
							<br/>&nbsp;*网校图片链接，支持JPG、PNG格式，尺寸（宽1000像素，高500像素）
							<br/>&nbsp;*微站图片链接，支持JPG、PNG格式，尺寸（宽720像素，高360像素）
						</span>
					</td>
				</tr>
				<tr style="display: none">
					<td align="center"><font color="red">*</font>&nbsp;微图片地址 </td>
					<td>
						<input type="text" name="websiteImages.mobileImagesUrl" id="mobileImagesUrl" value="${websiteImages.mobileImagesUrl}" style="width: 485px"/>
						<font color="red">*图片链接,可直复制地址或者通过上传</font>
					</td>
				</tr>
				<tr style="display: none">
					<td width="20%" align="center"><font color="red">*</font>微站广告图</td>
					<td width="80%">
						<span> 
							<img src="<%= staticImageServer%>${websiteImages.mobileImagesUrl}" alt="" width="360px" height="200px" id="mobileImgpic" />
							
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="button" value="上传" id="mobileImgButton"/>
							<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽720像素，高360像素）
						</span>
					</td>
				</tr>
                <tr id="previewUrl_tr" style="display: none">
                    <td align="center"><font color="red">*</font>&nbsp;缩略图地址 </td>
                    <td>
                        <input type="text" name="websiteImages.previewUrl" id="previewUrl" value="${websiteImages.previewUrl}" style="width: 485px"/>
                        <font color="red">*图片链接,可直复制地址或者通过上传</font>
                    </td>
                </tr>
                <tr id="previewpic_tr" style="display: none">
					<td align="center"><font color="red">*</font>&nbsp;缩略图</td>
					<td>
						<span> 
							<img src="<%= staticImageServer%>${websiteImages.previewUrl}" alt="" width="360px" height="200px" id="previewpic" />
							 <div id="previewUrlFileQueue" style="margin-top: 30px; border: 0px"></div>
						</span>
						<span style="border: 0px; padding-top: 2px; padding-left: 2px; ">
							<input type="button" value="上传" id="previewButton"/>
							<br/>&nbsp;*图片链接，支持JPG、PNG格式，尺寸（宽360像素，高200像素）小于256kb
						</span>
					</td>
				</tr>
				
				<tr>
					<td align="center">链接URL</td>
					<td>
						<input type="text"  name="websiteImages.linkAddress" value="${websiteImages.linkAddress}" class="{required:true,number:true,min:0,max:1000}"/>
					</td>
				</tr>
				
				<tr id="backColor">
					<td align="center">背景颜色</td>
					<td>
						<input type="text" name="websiteImages.color" id="imageColor" value="${websiteImages.color}"/>
					</td>
				</tr>
				<tr>
					<td align="center">序列号</td>
					<td>
						<input type="text" name="websiteImages.seriesNumber" value="${websiteImages.seriesNumber}" class="{required:true,number:true}" id="seriesNumber"/>
						<font style="color: red">&nbsp;&nbsp;倒序</font>
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
	<!-- /tab4 end -->
</body>
</html>