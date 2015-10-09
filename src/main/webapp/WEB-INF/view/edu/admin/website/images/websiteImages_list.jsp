<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
function deleteImg(){
	var imgIds = document.getElementsByName("ids");
	var num=0;
	var ids ='';
	for(var i=0;i<imgIds.length;i++){
		if(imgIds[i].checked==true){
			num++;
			ids +=imgIds[i].value;
			if(i!=imgIds.length-1){
				ids +=",";
			}
		}
	}
	if(num==0){
		alert("请选择要删除的广告图");
		return;
	}
	if (window.confirm("确认删除这些数据吗？")){
		$.ajax({
			url:"${ctx}/admin/website/delImages/"+ids,
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

function allCheck(cb)
{
	$("input[name=ids]").attr('checked', cb.checked);
}


function cancel(){
	$("#title").val("");
	$("#imagesUrl").val("");
	$("#linkAddress").val("");
	$("#keyWord").val("");
}
function delImg(id){
	if(confirm("确认删除吗？")){
		$.ajax({
			url:"${ctx}/admin/website/delImages/"+id,
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
</script>

</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>广告图管理</span> &gt; <span>广告图列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/imagesPage" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>标题：</font></span>
									<input type="text" name="websiteImages.title" id="title" value="${websiteImages.title}"/>
								</li>
								<li>
									<span class="ddTitle"><font>类型：</font></span>
									<select name="websiteImages.keyWord" id="keyWord">
										<option value="">--请选择关键字--</option>
										<option value="indexCenterBanner" <c:if test="${websiteImages.keyWord=='indexCenterBanner'}">selected="selected"</c:if>>首页Banner图片</option>
										<%-- <option value="mobileIndexCenterBanner" <c:if test="${websiteImages.keyWord=='mobileIndexCenterBanner'}">selected="selected"</c:if>>微站首页Banner图片</option> --%>
										<option value="indexAdvertImages1" <c:if test="${websiteImages.keyWord=='indexAdvertImages1'}">selected="selected"</c:if>>首页广告图片1</option>
										<option value="indexAdvertImages2" <c:if test="${websiteImages.keyWord=='indexAdvertImages2'}">selected="selected"</c:if>>首页广告图片2</option>
										<%-- <option value="userPersonalityImages" <c:if test="${websiteImages.keyWord=='imagesPersonalityImages'}">selected="selected"</c:if>>个人中心模板图片</option> --%>
									</select>
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>图片URL：</font></span>
									<input type="text" name="websiteImages.imagesUrl" id="imagesUrl" value="${websiteImages.imagesUrl}"/>
								</li>
								<li>
									<span class="ddTitle"><font>链接URL：</font></span>
									<input type="text" name="websiteImages.linkAddress" id="linkAddress" value="${websiteImages.linkAddress}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fl" style="text-align: center;">
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
						<p class="fl czBtn">
							<span class="ml10"><a href="${cxt}/admin/website/doAddImages"><em class="icon14 new">&nbsp;</em>添加</a></span>
							<span class="ml10"><a href="javascript:deleteImg()"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
						</p>
						<p class="fr c_666"><span>广告图列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="12%"><span>&nbsp;<input type="checkbox" onclick="allCheck(this)"/>全选</span></th>
                           <th><span>图片标题</span></th>
                           <th><span>图片URL</span></th>
                           <th><span>链接URL</span></th>
                           <th><span>背景颜色</span></th>
                           <th><span>类型</span></th>
                           <th><span>序列号</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${websiteImagesList.size()>0}">
				<c:forEach  items="${websiteImagesList}" var="images" >
					<tr>
						<td><input type="checkbox" name="ids" value="${images.id}" /></td>
						<td>${images.title }</td>
						<td>${images.imagesUrl }</td>
						<td>${images.linkAddress }</td>
						<td>${images.color }</td>
						<td>
							<c:if test="${images.keyWord=='indexCenterBanner'}">首页banner图</c:if>
							<c:if test="${images.keyWord=='mobileIndexCenterBanner'}">微站首页banner图</c:if>
							<c:if test="${images.keyWord=='indexAdvertImages1'}">首页广告位1</c:if>
							<c:if test="${images.keyWord=='indexAdvertImages2'}">首页广告位2</c:if>
							<c:if test="${images.keyWord=='userPersonalityImages'}">个人中心自定义皮肤</c:if>
						</td>
						<td>${images.seriesNumber}</td>
					
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/website/doUpdateImages/${images.id}">修改</a>
                               <a class="ml10 btn smallbtn btn-y" href="javascript:delImg(${images.id})">删除</a>
                               <a class="ml10 btn smallbtn btn-y" target="_blank" href="<%= staticImageServer%>${images.imagesUrl}">预览</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${websiteImagesList.size()==0||websiteImagesList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有广告图！</span>
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