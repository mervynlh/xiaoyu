<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">

function showClassify(id)
{
	var title=$("#"+id).attr("title");
	if(title=="展开")
	{
		$("#"+id).attr("title","收起");
		$("#"+id).html("-&nbsp;&nbsp;");
		$('tr[name="'+id+'s"]').show();
	}
	else
	{
		$("#"+id).attr("title","展开");
		$("#"+id).html("+&nbsp;&nbsp;");
		$('tr[name="'+id+'s"]').hide();
	}
	
}
function longClassify(id)
{
	
	$("#"+id).attr("onclick","shortClassify('"+id+"')");
	$("#"+id).html("-&nbsp;&nbsp;");
	
}
function doDel(id)
{
	if(confirm("确认删除？")){
		$.ajax({
			url:"${ctx}/admin/videoclassify/del/"+id,  
			type: "post",
			dataType:"json",
			success:function(data){
				if(data.message=='true'){
					alert("删除分类成功");
					window.location.reload();
				}
			}
		});
	}
		
}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>视频管理</span> &gt; <span>视频分类</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/navigates" name="searchForm" id="searchForm" method="post">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="clearfix">
							<div class="optionList">
								<font color="red">1.分类1和默认分类是预置的，只能编辑类名，不可删除。<br/>
						2.分类下有视频，该分类不可删除。<br/>
					    3.在编辑中，非预置的二级分类可以移动至其它一级分类下。 </frot>
							</div>
						</div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn"><span class="ml10"><a href="${cxt}/admin/videoclassify/doadd"><em class="icon14 new">&nbsp;</em>添加</a></span></p>
						<p class="fr c_666"></p>
					</div>
				</caption>
				<tbody id="tabS_02" align="center">
					<c:if test="${videoClassifys.size()>0}">
						<c:forEach items="${videoClassifys}" var="classifys" varStatus="status">
							<c:forEach items="${classifys}" var="classifys1" varStatus="status1">
								<c:choose>
									<c:when test="${classifys1.parentId==0}">
										<tr>
											<td style="background-color:#f3f3f3" width="20%" align="left">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="${status.index}id" style="cursor: pointer;" onclick="showClassify('${status.index}id')" title="收起">
													-&nbsp;&nbsp;
												</span>
												${classifys1.name}
											</td>
											<td style="background-color:#f3f3f3;color:#6e6e6e">${fn:length(classifys)-1}个子分类</td>
											<td  class="c_666 czBtn" align="center" width="30%" style="background-color:#f3f3f3;">
												<a class="ml10 btn smallbtn btn-y"  href="${ctx}/admin/videoclassify/doupdate/${classifys1.id}">编辑</a>
												<c:choose>
													<c:when test="${fn:length(classifys)==1&&classifys1.keyword==null}">
														<a class="ml10 btn smallbtn btn-y" href="javascript:doDel(${classifys1.id})">删除</a>
       												</c:when>
													<c:otherwise>
														<span class="ml10 btn smallbtn" style="color:#666666">删除</span>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr name="${status.index}ids">
											<td align="left" style="padding-left: 40px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${classifys1.name}</td>
											<td>
												<c:if test="${classifys1.videoNum>0}">
													<a href="${ctx}/admin/uploadVideo/list?queryUploadVideo.classifyOne=${classifys1.parentId}&queryUploadVideo.classifyTwo=${classifys1.id}">
														<span style="color:#3585da">视频（${classifys1.videoNum}）</span>
													</a>
												</c:if>
												<c:if test="${classifys1.videoNum<=0}">
													<span style="color:#3585da">视频（${classifys1.videoNum}）</span>
												</c:if>
											</td>
											<td>
												<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/videoclassify/doupdate/${classifys1.id}">编辑</a>
												<c:choose>
													<c:when test="${classifys1.videoNum==0&&classifys1.keyword==null}">
														<a class="ml10 btn smallbtn btn-y" href="javascript:doDel(${classifys1.id})">删除</a>
       												</c:when>
													<c:otherwise>
														<span class="ml10 btn smallbtn" style="color:#666666">删除</span>
													</c:otherwise>
												</c:choose>
												
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:forEach>
					</c:if>
					<c:if test="${videoClassifys.size()==0||videoClassifys==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有视频分类！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</form>
		</div><!-- /commonWrap -->
	</div>
</body>
</html>