<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员学习情况</title>

<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
	$(function() {
		$("#startDate,#endDate").datepicker({
			regional : "zh-CN",
			changeMonth : true,
			dateFormat : "yy-mm-dd "
		});

	});
	function submitSearch() {
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	function updateIsavalible(id, isavalible) {
		var str = "";
		if (isavalible == 1) {
			str = "是否禁用该用户";
		}
		if (isavalible == 0) {
			str = "是否恢复该用户";
		}
		if (confirm(str)) {
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/user/updateIsavalible",
				data : {
					"user.id" : id,
					"user.isavalible" : isavalible
				},
				async : false,
				success : function(result) {
					if (result.success) {
						if (isavalible == 1) {
							$(".attr" + id).attr(
									"href",
									"javascript:updateIsavalible(" + id
											+ ",0,this)");
							$(".attr" + id).html("恢复");
							$(".attr" + id).attr("title", "恢复");
							$("#isavalible" + id).html("禁用");
							alert("禁用成功");
						} else {
							$(".attr" + id).attr(
									"href",
									"javascript:updateIsavalible(" + id
											+ ",1,this)");
							$(".attr" + id).html("禁用");
							$(".attr" + id).attr("title", "禁用");
							$("#isavalible" + id).html("正常");
							alert("恢复成功");
						}

					}
				}
			});
		}
	}
	function clean() {
		$("#nickname,#useremail,#mobile,#startDate,#endDate").val("");
	}
</script>
</head>
<body  >
	
		
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>学员学习情况</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<form action="${ctx}/admin/user/studyhistory/${userId}" name="searchForm" id="searchForm" method="post">
						<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
											<div class="capHead">
												<div class=" fr">
													<input type="button" onclick="javascript:history.go(-1)" value="返回"></input>
												</div>
												<div class="clearfix"></div>
											</div>
										</caption>
										<thead>
											<tr>
												<th><span>课程名称</span></th>
												<th><span>节点名称</span></th>
												<th><span>观看次数</span></th>
												<th><span>最后观看时间</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">
											<c:if test="${list.size()>0}">
												<c:forEach items="${list}" var="studyHistory">
													<tr>
													<%-- 		id="${list.id }" />&nbsp;${list.id }</td> --%>
														<td>${studyHistory.courseName}</td>
														<td>${studyHistory.kpointName}</td>
														<td>${studyHistory.playercount}</td>
														<td><fmt:formatDate
														value="${studyHistory.updateTime}"
																pattern="yyyy/MM/dd  HH:mm:ss" /></td> 
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${list.size()==0||list==null}">
												<tr>
													<td align="center" colspan="16">
														<div class="tips">
															<span>还没有学习信息！</span>
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
								</div>
								<!-- /commonWrap -->
							</div>
						
</body>
</html>
