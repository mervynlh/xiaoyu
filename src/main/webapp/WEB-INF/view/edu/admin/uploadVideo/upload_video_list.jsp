<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/ZeroClip/ZeroClipboard.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	$( "#startTime,#endTime" ).datepicker(
   		{regional:"zh-CN",
   		changeMonth: true,
   		dateFormat:"yy-mm-dd "
   	});
});

/* $(document).ready(
		function(){
			var classifyOne='${queryUploadVideo.classifyOne}';
			var classifyTwo='${queryUploadVideo.classifyTwo}';
			if(classifyTwo!=0&&classifyTwo!=null)
			{
				getClassifyTwoByOne(classifyOne);
				$("#classifyTwo").val(classifyTwo);
			}
		}); */

/* function getClassifyTwoByOne(classifyOne)
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
} */

/* 
function allCheck(th){
	
	$('input[name="ids"]:checkbox').prop('checked',th.checked);
} */
function cancel(){
	$("#userId").val("");
	$("#type").val("");
	$("#videoName").val("");
	$("#startTime").val("");
	$("#endTime").val("");
}


/* function delBatch(){
	var videoIds = document.getElementsByName("ids");
	var num=0;
	var ids ='';
	for(var i=0;i<videoIds.length;i++){
		if(videoIds[i].checked==true){
			num++;
			ids +=videoIds[i].value;
			if(i!=videoIds.length-1){
				ids +=",";
			}
		}
	}
	if(num==0){
		alert("请选择要删除的视频");
		return;
	}
	if (window.confirm("确认删除这些数据吗？")){
		$.ajax({
			url:"${ctx}/admin/uploadVideo/del/"+ids,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("删除成功");
					window.location.reload();
				}
			}
		});
	}
}
function delUploadVideo(id)
{
	if(confirm("确认删除？")){
		$.ajax({
			url:"${ctx}/admin/uploadVideo/del/"+id,  
			type: "post",
			dataType:"json",
			success:function(data){
				if(data.message=='true'){
					alert("删除视频成功");
					window.location.reload();
				}
			}
		});
	}
		
} */
function doCopyUrl(url)
{
	var mask = $('<div></div>').appendTo($("body")).addClass("modal-backdrop fade in");
	var oEl = $('<div></div>').appendTo($('body')).addClass("modal fade in");
	var doc = $('<div class="modalWrap"><div class="modal_head"><span class="fr"><a href="javascript:void(0)" title="关闭" class="icon14 closeBtn"></a></span><h4><span>视频链接</span></h4></div><div class="modal_body" id="modalCont"><textarea style="height:40px;width:90%;" readonly="readonly" cols="" id="fe_text" rows="">'+url+'</textarea></div><div class="modal_foot clearfix"><button class="btn btn-success" id="d_clip_button" data-clipboard-target="fe_text">复制</button>&nbsp;&nbsp;<button class="btn ml10 cancelBtn">取消</button></div></div>').prependTo($(".modal"));
	$(".closeBtn,.cancelBtn,.modal-backdrop").bind("click", function() {$(".modal").removeClass("in").remove();$(".modal-backdrop").removeClass("in").remove();});
	// 定义一个新的复制对象
	var clip = new ZeroClipboard($("#d_clip_button"), {
	  moviePath:"${ctximg}/static/common/ZeroClip/ZeroClipboard.swf"
	} );
	// 复制内容到剪贴板成功后的操作
	clip.on( 'complete', function(client, args) {
	   $(".modal").removeClass("in").remove();
	   $(".modal-backdrop").removeClass("in").remove();
	   alert("复制成功");
	} );
}




</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/uploadVideo/list" name="searchForm" id="searchForm" method="post">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>视频标题：</font></span>
									<input type="text" value="${queryUploadVideo.title}" name="queryUploadVideo.title" id="videoName"/>
								</li>
								<li>
									<span class="ddTitle"><font>用户ID：</font></span>
									<input type="text" value="${queryUploadVideo.userId}" name="queryUploadVideo.userId" id="userId"/>
								</li>
								<li>
									<span class="ddTitle"><font>用户类型：</font></span>
									
									<select id="type" style="width: 100px" name="queryUploadVideo.type" >
										<option value="" selected="selected">--请选择--</option>
										<option value="TEACHER" ${queryUploadVideo.type == "TEACHER" ? "selected='selected'" : ""}>--教师--</option>
										<option value="STUDENT" ${queryUploadVideo.type == "STUDENT" ? "selected='selected'" : ""}>--学生--</option>
									</select>
								</li>
								<%-- <li>
									<span class="ddTitle"><font>视频分类：</font></span>
									<select id="classifyOne" style="width: 100px" name="queryUploadVideo.classifyOne" onchange="getClassifyTwoByOne(this.value)">
										<option value="0">--请选择--</option>
										<c:forEach items="${videoClassifys}" var="videoClassifyOne">
											<option value="${videoClassifyOne.id}" <c:if test="${videoClassifyOne.id==queryUploadVideo.classifyOne}">selected="selected"</c:if>>${videoClassifyOne.name}</option>
										</c:forEach>
									</select>
									<select id="classifyTwo" style="width: 100px;display: none" name="queryUploadVideo.classifyTwo">
									</select>
								</li> --%>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>上传开始时间：</font></span>
									<input type="text" name="queryUploadVideo.startTime" readonly id="startTime" class="AS-inp"
									value="${queryUploadVideo.startTime}"/>
								</li>
								<li>
									<span class="ddTitle"><font>上传结束时间：</font></span>
									<input type="text" name="queryUploadVideo.endTime" readonly="readonly" id="endTime"
									class="AS-inp" value="${queryUploadVideo.endTime}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fr" style="">
							<ul class="ddBar">
								<li>
									<input class="btn btn-danger ml10" type="button" onclick="goPage(1)" value="查询">
									<input class="btn btn-danger ml10" type="button" onclick="cancel()" value="清空">
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mt10 clearfix">
						<%-- <p class="fl czBtn">
							<span class="ml10"><a href="${cxt}/admin/uploadVideo/doadd"><em class="icon14 new">&nbsp;</em>添加</a></span>
							<span class="ml10"><a href="javascript:delBatch()"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
						</p> --%>
						<p class="fr c_666"><span>视频列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>ID</span></th>
						<th><span>上传者邮箱</span></th>
					    <th><span>视频标题</span></th>
                        <th><span>视频链接</span></th>
                        <th><span>占用空间</span></th>
                        <th><span>上传时间</span></th>
                        <th><span>上传者类型</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${uploadVideos.size()>0}">
				<c:forEach items="${uploadVideos}" var="uploadVideo" >
					<tr>
						<td>${uploadVideo.id }</td>
						<td>${uploadVideo.userEmail }</td>
						<td>${uploadVideo.title }</td>
						<td>${uploadVideo.videoUrl }</td>
						<td>${uploadVideo.size}M</td>
						<td><fmt:formatDate value="${uploadVideo.createTime }" type="both" pattern="yyyy-MM-dd"/></td>
						<td>
							<c:if test="${uploadVideo.type == 'TEACHER'}">
				  	  			教师
				  	  	  	</c:if>
				  	  	  	<c:if test="${uploadVideo.type == 'STUDENT'}">
				  	  			学生
				  	  	  	</c:if>
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="javascript:doCopyUrl('${uploadVideo.videoUrl}')">复制链接</a>
							<%-- <a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/uploadVideo/doUpdate/${uploadVideo.id}" >修改</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:delUploadVideo(${uploadVideo.id})" >删除</a> --%>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${uploadVideos.size()==0||uploadVideos==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有上传视频！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
			<!-- /pageBar end -->
		</div><!-- /commonWrap -->
	</div>
</body>
</html>