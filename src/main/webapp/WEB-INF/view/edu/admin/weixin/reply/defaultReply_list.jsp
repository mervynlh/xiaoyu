<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>微信默认回复列表</title>
<script type="text/javascript">
function cancel(){
	$("#msgType").val(-1);
	$("#keyword").val("");
}
function selectWeixin(){
	var num=$("input[type='radio']:checked").length;
	if(num>0){
		var weixinVal=$("input[type='radio']:checked").val();
		//调用父页面的方法  
        window.opener.weixinAdd(weixinVal);   
        window.close(); 
	}else{
		alert("请选择回复素材");
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
			<form action="${ctx}/admin/weixin/defaultpage" name="searchForm" id="searchForm" method="post">
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
						<p class="fl czBtn"></p>
						<p class="fr c_666">
							<span>微信素材列表</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				<thead>
					<tr> 	 	
						<th><span>&nbsp;</span></th> 	 	 	 	
						<th width="8%">ID</th>
                           <th><span>关键字</span></th>
                           <th><span>图文标题</span></th>
                           <th><span>类型</span></th>
                           <th><span>创建时间</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${weixinReplys.size()>0}">
				<c:forEach  items="${weixinReplys}" var="weixinReply" >
					<tr>
						<td><input type="radio" value="${weixinReply.id }#${weixinReply.title }#${weixinReply.keyword }#${weixinReply.msgType }" name="idStr"/></td>
						<td>${weixinReply.id }</td>
						<td>${weixinReply.keyword }</td>
						<td>${weixinReply.title }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
						<td>
							<c:if test="${weixinReply.msgType==1 }">文本</c:if>
							<c:if test="${weixinReply.msgType==2 }">图文</c:if>
							<c:if test="${weixinReply.msgType==3 }">多图文</c:if>
						</td>
						<td>
							<fmt:formatDate value="${weixinReply.createTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
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
					<tr>
						<td align="center" colspan="6">
							<a class="btn btn-danger" title="确定" href="javascript:selectWeixin()">确定</a>
							<a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a>
						</td>
					</tr>
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