
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>机构列表</title>
<script type="text/javascript">

function submitSearch(){
	$("#searchForm").submit();
	}
	function clean(){
		$("#name,#applicant,#mobile,#email").val("");
		$("#status").val(2);
	}
	function delInstitutionbatch(){//删除机构
		var artIds=document.getElementsByName("institutionIds");
		var str="";
		var checked=true;
		$(artIds).each(function(){
			if($(this).prop("checked")){
				str+=this.value+",";
				checked=false;
			}
		});
		if(checked){
			alert("请至少选中一个机构");
			return;
		}
		if(confirm("确认删除吗？")){
			$.ajax({
				url:"${ctx}/admin/institution/delInstitutionBatch",
				data:{"institutionIds":str},
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
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/institution/showInstitutionList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>机构管理</span> &gt; <span>机构列表</span> </h4>
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
								<span class="ddTitle"><font>机构名称：</font></span>
								<input type="text" name="institution.name" value="${institution.name }" id="name" />
							</li>
							<li>
								<span class="ddTitle"><font>申请人：</font></span>
								<input type="text" name="institution.applicant" id="applicant" value="${institution.applicant}"/>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>审核状态：</font></span>
								<select id="status" name="institution.status" class="ml10">
									<option value="2" selected>---请选择---</option>
									<option value="0" <c:if test="${institution.status==0 }">selected</c:if> >未审核</option>
									<option value="1" <c:if test="${institution.status==1 }">selected</c:if> >审核</option>
								</select>
							</li>
							<li>
								<span class="ddTitle"><font>手机号：</font></span>
								<input type="text" name="institution.mobile" id="mobile" value="${institution.mobile}"/>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul class="ddBar">
							<li>
								<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
							</li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
						<%-- <span><a href="${ctx}/admin/institution/toAddInstitution" title="添加机构"><em class="icon14 new">&nbsp;</em>添加机构</a></span> --%>
						<span class="ml10"><a href="javascript:delInstitutionbatch();" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
					</p>
				</div> 
			</caption>
			<thead>
				<tr>
					<th width="7%"><span>&nbsp;ID</span></th>
					<th width="8%"><span>机构名称</span></th>
					<th width="10%"><span>审核状态</span></th>
					<th width="8%"><span>申请人</span></th>
					<th width="8%"><span>手机号</span></th>
					<th width="20%"><span>简介</span></th>
					<th width="15%"><span>创建时间</span></th>
					<th width="15%"><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${not empty institutionList}">
			<c:forEach  items="${institutionList}" var="institution" >
				<tr id="rem${institution.id }">
					<td><input type="checkbox" name="institutionIds" value="${institution.id }"/>&nbsp;${institution.id }</td>
					<td>${institution.name }</td>
					<td>
						<c:if test="${institution.status==0}">未审核</c:if>
						<c:if test="${institution.status==1}">审核</c:if>
					</td>
					<td>${institution.applicant }</td>
					<td>${institution.mobile }</td>
					<td>${institution.description}</td>
					<td><fmt:formatDate type="both" value="${institution.createTime }"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td  class="c_666 czBtn" align="center">
					<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/institution/institutionInfo/${institution.id }">修改</a>
					<c:if test="${institution.status==0 }">
						<a class="ml10 btn smallbtn btn-y" title="审核" href="${ctx}/admin/institution/updateStatus/${institution.id }/1">审核</a>
					</c:if>
					<c:if test="${institution.status==1 }">
					<a class="ml10 btn smallbtn btn-y" title="取消审核" href="${ctx}/admin/institution/updateStatus/${institution.id }/0">取消审核</a>
					</c:if>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty institutionList }">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>未查询到相关信息！</span>
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
