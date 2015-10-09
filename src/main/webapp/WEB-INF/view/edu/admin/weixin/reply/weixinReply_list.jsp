<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>微信回复列表</title>
<script type="text/javascript">
function cancel(){
	$("#msgType").val(-1);
	$("#keyword").val("");
}
function delReply(id,msgType){
	if(confirm("确认删除吗")){
		$.ajax({
			url:"${cxt}/admin/weixin/del",
			data:{"weixinReply.id":id,"weixinReply.msgType":msgType},
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
<!-- 内容 开始  -->
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>微信素材列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/weixin/page" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>类型：</font></span>
									<select  name="queryWeixinReply.msgType" id="msgType">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryWeixinReply.msgType==1 }">selected="selected"</c:if>>文本</option>
										<option value="2" <c:if test="${queryWeixinReply.msgType==2 }">selected="selected"</c:if>>图文</option>
										<option value="3" <c:if test="${queryWeixinReply.msgType==3 }">selected="selected"</c:if>>多图文</option>
									</select>
									<span class="ddTitle"><font>关键字：</font></span>
									<input type="text" name="queryWeixinReply.keyword" id="keyword" value="${queryWeixinReply.keyword}"/>
									<input type="button"  class="btn btn-danger" value="查询"  onclick="goPage(1)"/>
									<input type="button"  class="btn btn-danger" value="清空"  onclick="cancel()"/>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span><a title="新建文本" href="${cxt}/admin/weixin/doadd/1"><em class="icon14 new"> </em>新建文本</a></span>
							<span><a title="新建图文" href="${cxt}/admin/weixin/doadd/2"><em class="icon14 new"> </em>新建图文</a></span>
							<span><a title="新建多图文" href="${cxt}/admin/weixin/doadd/3"><em class="icon14 new"> </em>新建多图文</a></span>
						</p>
						<p class="fr c_666">
							<span>会员素材列表</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				
				<thead>
					<tr> 	 	 	 	 	 	
						<th  width="8%"><span>ID</span></th>
                           <th><span>关键字</span></th>
                           <th><span>图文标题</span></th>
                           <th><span>类型</span></th>
                           <th><span>封面url</span></th>
                           <th><span>创建时间</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${weixinReplys.size()>0}">
				<c:forEach  items="${weixinReplys}" var="weixinReply" >
					<tr>
						<td>${weixinReply.id }</td>
						<td>${weixinReply.keyword }</td>
						<td>${weixinReply.title }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
						<td>
							<c:if test="${weixinReply.msgType==1 }">文本</c:if>
							<c:if test="${weixinReply.msgType==2 }">图文</c:if>
							<c:if test="${weixinReply.msgType==3 }">多图文</c:if>
						</td>
						<td>${weixinReply.imageUrl }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
						<td>
							<fmt:formatDate value="${weixinReply.createTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/weixin/doupdate/${weixinReply.id}">修改</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:delReply(${weixinReply.id},${weixinReply.msgType })">删除</a>
                           </td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${weixinReplys.size()==0||weixinReplys==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有微信素材！</span>
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