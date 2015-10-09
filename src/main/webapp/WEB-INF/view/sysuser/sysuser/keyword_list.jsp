<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>敏感词列表</title>
<script type="text/javascript">
function delkeyword(id){
	if(confirm("是否删除?")){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/admin/keyword/delete",
			data:{"id":id},
			async:false,
			success:function(result){
				if(result.message=="success"){ 
					$("#del"+id).remove();
					alert("删除成功");
				}
			}
		});
	 }
}

function submitFrom(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}

function toupdate(type,word){//表单提交到更新页面  防止中文乱码
	$("#updatetype").val(type);
	$("#updateword").val(word);
	$("#toupdate").submit();
}
</script>
</head>
<body  >
		<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>敏感词管理</span> &gt; <span>敏感词列表</span> </h4>
		</div>
			
<form action="${ctx}/admin/search/toupdatesearchWord" id="toupdate" method="post">
<input type="hidden" name="type" id="updatetype"/>
<input type="hidden" name="word" id="updateword"/>
</form>
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/keyword/list" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
			<caption>
				<div class="capHead">
					<div class="clearfix">
						<div class="optionList">
							<span>
							<font>敏感词：</font>
							</span>
							<span>
							<input type="text" name="keyword.keyword" value="${keyword.keyword}"/>
							</span>
						</div>
						<div class="optionList">
							<input class="btn btn-danger" type="button" onclick="submitFrom()" value="查询"/>
						</div>
					</div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
					<span>
					<a title="新建敏感词" href="${ctx}/admin/keyword/toadd">
					<em class="icon14 new"> </em>
					新建敏感词
					</a>
					</span>
					</p>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="25%"><span>敏感词</span></th>
					<th width="25%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02">
				<c:forEach items="${list}" var="keyword">
				<tr id="del${keyword.id }">
					<td align="center" valign="middle">${keyword.keyword }</td>
					<td class="c_666 czBtn" align="center">
					<a class="btn smallbtn btn-y" href="javascript:void(0)"
										onclick="delkeyword(${keyword.id })" title="删除">删除</a> 
					</td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		</form>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>