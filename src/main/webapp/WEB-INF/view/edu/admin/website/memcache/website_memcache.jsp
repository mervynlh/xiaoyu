<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memcache管理</title>
<script type="text/javascript">
function selectMemcache(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function cleanEmpty(memId){
	if(memId==0||memId==''){
		alert("该Memcache-key不存在");
		return;
	}
	var judge=confirm("确定清空缓存吗？");
	if(judge==true){
	$.ajax({
		url:"${ctx}/admin/websitemem/removeMemKey",
		type:"post",
		dataType:"json",
		data:{"id":memId},
		success:function(result){
			if(result.success){
				alert("清除成功");
				return;
			}else{
				alert("请刷新重试");
				return;
			}
		}
	});
	}
}
 function deladmin(id){
		var judge=confirm("确定删除吗？");
		if(judge==true){
		$.ajax({
			url:"${ctx}/admin/websitemem/del",
			type:"post",
			dataType:"json",
			data:{"id":id},
			cache:true,
			async:false,
			success:function(result){
				if(result.success){
					alert("删除成功");
					$("#rem"+id).remove();
				}else{
					alert("请刷新重试");
					return;
				}
			}
		});
		}
	} 
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>Memcache管理</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
						<div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/websitemem/list" method="post" id="searchForm">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
									<div class="optionList">
										<span><font>Memcache-Key：</font></span> <span> <input
											type="text" name="websiteMemcache.memKey" id="name"/>
										</span>

									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="查询"
											onclick="selectMemcache()" />
									</div>
									</form>
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
								<span><a href="${ctx}/admin/websitemem/toAdd" title="添加"><em class="icon14 new">&nbsp;</em>添加</a></span>
								</p>
							</div> 
					</caption>
					<thead>
						<tr>
							<th width="3%"><span>ID</span></th>
							<th width="8%"><span>memcache值</span></th>
							<th width="8%"><span>描述</span></th>
							<th width="10%"><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${websiteMemcacheList}" var="mem">
							<tr id="rem${mem.id }">
								<td>${mem.id}</td>
								<td>${mem.memKey}</td>
								<td>${mem.memDesc}</td>
								<td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y" onclick="cleanEmpty(${mem.id })" href="javascript:void(0)"
									title="清空">清空</a>
								<a class="btn smallbtn btn-y" onclick="deladmin(${mem.id})" href="javascript:void(0)"
									title="删除">删除</a>
									</td>
							</tr>
						</c:forEach>
						<c:if test="${websiteMemcacheList.size()==0||websiteMemcacheList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有Memcache信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
					</tbody>
				</table>
				<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
			</div>
		</div>
</body>
</html>