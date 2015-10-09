<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
function delTruncate(){
	var ids ="";
	$(".tableIds:checked").each(function(){
		ids+=$(this).val()+",";
	});
	
	if(ids.length==0){
		alert("请选择要删除的清空表");
		return;
	}
	if (window.confirm("确认删除这些数据吗？")){
		$.ajax({
			url:"${ctx}/admin/website/delTruncate/"+ids,
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

function truncateTable(){
	var ids ="";
	$(".tableIds:checked").each(function(){
		ids+=$(this).val()+",";
	});
	
	if(ids.length==0){
		alert("请选择要清空的表");
		return;
	}
	if (window.confirm("确认清空这些表吗？")){
		$.ajax({
			url:"${ctx}/admin/website/truncate/table/"+ids,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("清空成功");
					window.location.reload();
				}
			}
		});
	}
}

function allCheck(cb)
{
	$("input[name=ids]").prop('checked', cb.checked);
}


function cancel(){
	$("#tableName,#type").val("");
}
function delTruncateById(id){
	if(confirm("确认删除吗？")){
		$.ajax({
			url:"${ctx}/admin/website/delTruncate/"+id,
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
function truncateTableById(id){
	if(confirm("确认清空吗？")){
		$.ajax({
			url:"${ctx}/admin/website/truncate/table/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("清空成功");
					window.location.reload();
				}
			}
		});
	}
}
function truncateTableType(type){
	if(confirm("确认清空吗？")){
		$.ajax({
			url:"${ctx}/admin/website/truncate/table/type/"+type,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("清空成功");
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
		<h4><em class="icon14 i_01"></em>&nbsp;<span>清空表管理</span> &gt; <span>清空表列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/truncatePage" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>表名：</font></span>
									<input type="text" name="websiteTruncate.tableName" id="tableName" value="${websiteTruncate.tableName}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>类型：</font></span>
									<select name="websiteTruncate.type" id="type">
										<option value="">--请选择关键字--</option>
										<option value="web" <c:if test="${websiteTruncate.type=='web'}">selected="selected"</c:if>>网校</option>
										<option value="exam" <c:if test="${websiteTruncate.type=='exam'}">selected="selected"</c:if>>考试</option>
										<option value="sns" <c:if test="${websiteTruncate.type=='sns'}">selected="selected"</c:if>>社区</option>
									</select>
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
							<span class="ml10"><a href="${cxt}/admin/website/doAddTruncate"><em class="icon14 new">&nbsp;</em>添加</a></span>
							<span class="ml10"><a href="javascript:delTruncate()"><em class="icon14 delete">&nbsp;</em>批量删除</a></span>
							<span class="ml10"><a href="javascript:truncateTable()"><em class="icon14 delete">&nbsp;</em>批量清空</a></span>
							<span class="ml10"><a href="javascript:truncateTableType('web')"><em class="icon14 delete">&nbsp;</em>清空网校</a></span>
							<span class="ml10"><a href="javascript:truncateTableType('exam')"><em class="icon14 delete">&nbsp;</em>清空考试</a></span>
							<span class="ml10"><a href="javascript:truncateTableType('sns')"><em class="icon14 delete">&nbsp;</em>清空社区</a></span>
							<span class="ml10"><a href="javascript:truncateTableType('')"><em class="icon14 delete">&nbsp;</em>全部清空</a></span>
						</p>
						<p class="fr c_666"><span>清空表列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="12%"><span>&nbsp;<input type="checkbox" onclick="allCheck(this)"/>全选</span></th>
                        <th><span>表名</span></th>
                        <th><span>类型</span></th>
                        <th><span>描述</span></th>
                        <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${websiteTruncateList.size()>0}">
				<c:forEach  items="${websiteTruncateList}" var="truncate" >
					<tr>
						<td><input type="checkbox" name="ids" class="tableIds" value="${truncate.id}" /></td>
						<td>${truncate.tableName }</td>
						<td>
							<c:if test="${truncate.type=='web'}">网校</c:if>
							<c:if test="${truncate.type=='exam'}">考试</c:if>
							<c:if test="${truncate.type=='sns'}">社区</c:if>
						</td>
						<td>${truncate.desc}</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${ctx}/admin/website/doUpdateTruncate/${truncate.id}">修改</a>
                            <a class="ml10 btn smallbtn btn-y" href="javascript:delTruncateById(${truncate.id})">删除</a>
                            <a class="ml10 btn smallbtn btn-y" href="javascript:truncateTableById(${truncate.id})">清空</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${websiteTruncateList.size()==0||websiteTruncateList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有清空表！</span>
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