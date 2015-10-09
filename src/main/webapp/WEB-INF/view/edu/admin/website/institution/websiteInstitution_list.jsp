<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript">
// 清空
function cancel(){
	$("#name").val("");
	$("#applicant").val("");
	$("#mobile").val("");
	$("#email").val("");
}
// 批量删除推荐机构
function delInstitutionbatch(){
	var instIds = document.getElementsByName("institutionIds");
	var str="";
	var checked=true;
	$(instIds).each(function(){
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
		url:baselocation+"/admin/website/delWebsiteInstitution",
		type:"post",
		data:{"instIds":str},
		dataType:"json",
		success:function(result){
			if(result.message){
				alert("删除成功");
				$("input[name='institutionIds']:checkbox").prop('checked',false);
				window.location.reload();
			}
		}
	});
}
function reload(){
	window.location.reload();
}
function showNewwin(){
	window.open('${ctx}/admin/institution/getInstitution','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
}

</script>
</head>
<body>
<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐机构管理</span> &gt; <span>推荐机构列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/websiteInstitutionPage" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>机构名称：</font></span>
										<input type="text" name="websiteInstitutionDTO.name" id="name" value="${websiteInstitutionDTO.name}"/>
									</li>
									<li>
										<span class="ddTitle"><font>申请人：</font></span>
										<input type="text" name="websiteInstitutionDTO.applicant" id="applicant" value="${websiteInstitutionDTO.applicant}"/>
									</li>
								</ul>
							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li>
										<span class="ddTitle"><font>手机号：</font></span>
										<input type="text" name="websiteInstitutionDTO.mobile" id="mobile" value="${websiteInstitutionDTO.mobile}"/>
									</li>
									<li>
										<span class="ddTitle"><font>邮箱：</font></span>
										<input type="text" name="websiteInstitutionDTO.email" id="email" value="${websiteInstitutionDTO.email}"/>
									</li>
									<li >
										<input type="button"  class="btn btn-danger" value="查询" name="" onclick="goPage(1)"/>
										<input type="button"  class="btn btn-danger" value="清空" name="" onclick="cancel()"/>
									</li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="mt10 clearfix">
							<p class="fl czBtn">
								<span class="ml10"><a href="javascript:showNewwin()"><em class="icon14 new">&nbsp;</em>新建推荐机构</a></span>
								<span class="ml10"><a href="javascript:delInstitutionbatch();" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
							</p>
							<p class="fr c_666"><span>推荐机构列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
						</div>
					</caption>
				<thead>
					<tr>
						<th  width="8%"><span>ID</span></th>
                           <th><span>机构名称</span></th>
                           <th><span>申请人</span></th>
                           <th><span>手机号</span></th>
                           <th><span>邮箱</span></th>
                           <th><span>排序值</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
					<c:if test="${not empty institutionList}">
						<c:forEach  items="${institutionList}" var="institution" >
							<tr>
								<td><input type="checkbox" name="institutionIds" value="${institution.id }"/>&nbsp;${institution.id }</td>
								<td>${institution.name }</td>
								<td>${institution.applicant }</td>
								<td>${institution.mobile }</td>
								<td>${institution.email }</td>
								<td>${institution.sort }</td>
								<td  class="c_666 czBtn" align="center">
									<a class="ml10 btn smallbtn btn-y" href="${ctx }/admin/website/getWebsiteInstitutionById/${institution.id}">修改</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty institutionList}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有推荐机构！</span>
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