<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>积分列表</title>
<script type="text/javascript">
function updateIntegralTemplate(id,status,obj){//更新状态
	$.ajax({
		url:"${ctx}/admin/integral/template/updatestatus",
		data:{"userIntegralTemplate.id":id,"userIntegralTemplate.status":status},
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.success){
				if(status==0){//正常
					$("#status"+id).html("正常");
					$(obj).html("停止");
					$(obj).attr("title","停止");
					$(obj).attr("onclick","updateIntegralTemplate("+id+",1,this)");
					alert("已恢复正常");
				}else{//停止
					$("#status"+id).html("停止");
					$(obj).html("正常");
					$(obj).attr("title","正常");
					$(obj).attr("onclick","updateIntegralTemplate("+id+",0,this)");
					alert("已停止使用");
				}
			}else{
				alert("系统繁忙，请稍后重试");
				return;
			}
		}
	});
}

</script>
</head>
<body  >


<!-- 内容 开始  -->

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>积分管理</span> &gt; <span>积分列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="?" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<thead>
							<tr>
								<th width="5%"><span>ID</span></th>
								<th><span>模板名称</span></th>
								<th><span>使用类型</span></th>
								<th><span>使用场景</span></th>
								<th><span>功能分数</span></th>
								<th><span>创建时间</span></th>
								<th><span>修改人</span></th>
								<th><span>状态</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${integralTempateList.size()>0}">
						<c:forEach  items="${integralTempateList}" var="ig" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${ig.id }</td>
								<td>${ig.name}</td>
								<td>
								<c:if test="${ig.type==0}">
								学员
								</c:if>
								</td>
								<td>${ig.keyword }</td>
								<td>${ig.showScore}</td>
								<td><fmt:formatDate value="${ig.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${ig.createUser}</td>
								<td id="status${ig.id}">
								<c:if test="${ig.status==0}">正常</c:if>
								<c:if test="${ig.status==1}">停止</c:if>
								</td>
								<td  class="c_666 czBtn" align="center">
								<c:if test="${ig.status==1}">
								<a class="ml10 btn smallbtn btn-y" title="正常" href="javascript:void(0)" onclick="updateIntegralTemplate(${ig.id},0,this)">正常</a>
								</c:if>
								<c:if test="${ig.status==0}">
								<a class="ml10 btn smallbtn btn-y" title="停止" href="javascript:void(0)" onclick="updateIntegralTemplate(${ig.id},1,this)">停止</a>
								</c:if>
							    <a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/integral/template/toupdate/${ig.id}">修改</a>
								<%-- <a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a> --%>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${integralTempateList.size()==0||integralTempateList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有积分模板信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					<%-- <!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end --> --%>
				</div><!-- /commonWrap -->
			</div>
		</table>
		</form>
	</div><!-- /commonWrap -->
</div>
</body>
</html>
