<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>资讯列表</title>
<script type="text/javascript">
$(function(){
	if('${article.type}'==''){
		$("#artype").val(0);
	}else{
		$("#artype").val('${article.type}');
	}
});
function remError(id){
	var judge=confirm("确定删除此问题？");
	if(judge){
		window.location.href="${ctx}/admin/teacher/delete/"+id+"";
	}
}
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	function clean(){
		$("#title").val("");
		$("#artype").val(0);
	}
	function delArticlebatch(){//删除资讯
		var artIds=document.getElementsByName("articleIds");
		var str="";
		var checked=true;
		$(artIds).each(function(){
			if($(this).prop("checked")){
				str+=this.value+",";
				checked=false;
			}
		});
		if(checked){
			alert("请至少选择一条信息");
			return;
		}
		$.ajax({
			url:"${ctx}/admin/article/delArticleBatch",
			data:{"artIds":str},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					window.location.reload();
				}else{
					alert("系统繁忙稍后重试");
					return;
				}
			}
		});
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/article/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>资讯管理</span> &gt; <span>资讯列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class="fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>标题：</font></span>
								<input type="text" name="article.title" value="${article.title}" id="title" />
								<span class="ddTitle"><font>分类：</font></span>
								<select name="article.type" id="artype">
								<option value="0">全部</option>
								<option value="1">资讯</option>
								<option value="2">公告</option>
								</select>
								<%--  <span class="ddTitle"><font>试卷名称：</font></span>
								<input type="text" name="paperErrorCheck.content" value="${paperErrorCheck.content}" id="paperName" /> --%>
								<%--<span class="ddTitle"><font>试题内容：</font></span>
								<input type="text" name="paperErrorCheck.qstName" value="${paperErrorCheck.qstName}" id="qstName" /> --%>
								<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
							</li>
						</ul>
						
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
						<span><a href="${ctx}/admin/article/toAdd" title="新建资讯"><em class="icon14 new">&nbsp;</em>新建资讯</a></span>
						<span class="ml10"><a href="javascript:delArticlebatch();" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
					</p>
				</div> 
			</caption>
			<thead>
				<tr>
					<th width="7%"><span>&nbsp;ID</span></th>
					<th width="8%"><span>类型</span></th>
					<th><span>标题</span></th>
					<th><span>标签</span></th>
					<th><span>简介</span></th>
					<th><span>点击数</span></th>
					<th><span>添加时间</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${articleList.size()>0}">
			<c:forEach  items="${articleList}" var="art" >
				<tr id="rem${art.id }">
					<td><input type="checkbox" name="articleIds" value="${art.id }"/>&nbsp;${art.id }</td>
					<td>
					<c:if test="${art.type==1 }">资讯</c:if>
					<c:if test="${art.type==2 }">公告</c:if>
					</td>
					<td>${art.title }</td>
					<td>${art.meta}</td>
					<td>${fn:substring(art.description,0,50)}</td>
					<td>${art.clickTimes}</td>
					<td><fmt:formatDate type="both" value="${art.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td  class="c_666 czBtn" align="center">
					<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/article/toUpdate/${art.id}">修改</a>
					<a class="ml10 btn smallbtn btn-y" title="预览" href="${ctx}/front/toArticle/${art.id}" target="_blank">预览</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${articleList.size()==0||articleList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有资讯信息！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
