<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>直播列表</title>
<script type="text/javascript">
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
	//删除直播
	function delvedio(id){
		if(confirm("是否删除?")){ 
			$.ajax({
				url:"${ctx}/admin/live/del/"+id,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						window.location.reload();
					}
				}
			});
		}
	}
</script>
</head>
<body  >
		<form action="${ctx}/admin/live/list" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>
						&nbsp;
						<span>直播管理</span>
						&gt;
						<span>直播列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span>
															<font>直播id：</font>
														</span>
														<span>
															<input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="vedioLive.id" value="${vedioLive.id}" id="id" />
														</span>
													</div>
													<div class="optionList">
														<span>
															<font>直播名称：</font>
														</span>
														<span>
															<input type="text" name="vedioLive.title" value="${vedioLive.title}" id="title" />
														</span>
													</div>
													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()" />
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="mt10 clearfix">
												<p class="fl czBtn">
													<span>
														<a href="${ctx}/admin/live/toAdd" title="新建">
															<em class="icon14 new">&nbsp;</em>
															新建直播
														</a>
													</span>
												</p>
											</div>
										</caption>
										<thead>
											<tr>
												<th width="8%">
													<span>ID</span>
												</th>
												<th>
													<span>直播标题</span>
												</th>
												<th>
													<span>直播编码</span>
												</th>
												<th>
													<span>主讲老师</span>
												</th>
												<th>
													<span>直播时间</span>
												</th>
												<th>
													<span>直播结束时间</span>
												</th>
												<th>
													<span>操作</span>
												</th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">
											<c:if test="${vedioLiveList.size()>0}">
												<c:forEach items="${vedioLiveList}" var="tc">
													<tr id="rem${tc.id }">
														<td>${tc.id}</td>
														<td>${tc.title }</td>
														<td>${tc.code}</td>
														<td>${tc.teacher}</td>
														<td>
															<fmt:formatDate type="both" value="${tc.liveTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td>
															<fmt:formatDate type="both" value="${tc.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td class="c_666 czBtn" align="center">
															<a class="ml10 btn smallbtn btn-y" title="修改" href="${ctx}/admin/live/toUpdate/${tc.id}">修改</a>
															<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="delvedio(${tc.id})">删除</a>
														</td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${vedioLiveList.size()==0||vedioLiveList==null}">
												<tr>
													<td align="center" colspan="16">
														<div class="tips">
															<span>还没有相关数据！</span>
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
								<!-- /commonWrap -->
							</div>
						</table>

					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
