<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>礼品管理</title>
<script type="text/javascript">
function selectIntegral(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function delGift(id){
	if(confirm("确定删除该礼品吗？")){
		$.ajax({
			url:"${ctx}/admin/user/deletegift",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					$("#rem"+id).remove();
					alert(result.message);
				}else{
					alert(result.message);
					return;
				}
			}
		});
	}
}
function cleanIntegral(){
	$("#name").val('');
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>礼品管理</span>
		</h4>
	</div>
	<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
						<div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/user/giftlist" method="post" id="searchForm">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
									<div class="optionList">
										<span><font>礼品名称：</font></span> <span> <input
											type="text" name="userIntegralGift.name" id="name" value="${userIntegralGift.name}"/>
										</span>

									</div>
									<div class="optionList">
										<input type="button" class="btn btn-danger" value="查询"
											onclick="selectIntegral()" />
											<input type="button" class="btn btn-danger" value="清空"
											onclick="cleanIntegral()" />
									</div>
									</form>
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
								<span><a href="${ctx}/admin/user/toaddgift" title="新建礼品"><em class="icon14 new">&nbsp;</em>新建礼品</a></span>
								</p>
							</div>
					</caption>
					<thead>
						<tr>
							<th width="5%"><span>ID</span></th>
							<th><span>礼品名称</span></th>
							<th><span>使用积分</span></th>
							<th><span>物品图片</span></th>
							<th><span>创建时间</span></th>
							<th><span>更新时间</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${userIntegralGiftList}" var="ig">
							<tr id="rem${ig.id}">
								<td>${ig.id}</td>
								<td>${ig.name}</td>
								<td>${ig.score}</td>
								<c:if test="${ig.courseId!=0}">
								<td>${ig.courseLogo}</td>
								</c:if>
								<c:if test="${ig.courseId==0}">
								<td>${ig.logo}</td>
								</c:if>
								<td><fmt:formatDate value="${ig.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${ig.updateTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y"  href="${ctx }/admin/user/toupdategift/${ig.id}"
									title="修改">修改</a>
									<a class="btn smallbtn btn-y"  href="javascript:delGift(${ig.id})"
									title="删除">删除</a>
									</td>
							</tr>
						</c:forEach>
						<c:if test="${userIntegralGiftList.size()==0||userIntegralGiftList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有礼品信息！</span>
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