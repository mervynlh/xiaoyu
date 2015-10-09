<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>反馈列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<!-- 后续需要添加一些其他功能 比如查看查看日志文件 查看图片等 查询QQ 姓名原数据等仍然保留     @Author wangzhuang -->
<script type="text/javascript">
// 清空 clean()
function clean(){
	$("#useremail,#mobile").val("");
	$("#type_select").val(-1);
};
function submitSearch(){ 
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
</script>
</head>
<body>
<form action="${ctx}/admin/feed/feedList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>系统</span> &gt; <span>反馈列表</span>
		</h4>
	</div>
<!-- /tab1 begin-->
<!-- SEELCT BEGIN -->
<!-- LEFT -->
	<caption>
		<div class="capHead">
			<div class="w50pre fl">
				<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>手机号码：</font></span>
								<input type="text" name="userFeedback.mobile" value="${userFeedback.mobile}" id="mobile" />
							</li>
							<li>
								<span class="ddTitle"><font>反馈类型：</font></span>
								<select id="type_select" name="userFeedback.type">
									<option value="-1" <c:if test="${userFeedback.type==0}">selected</c:if>>全部</option>
									<option value="0" <c:if test="${userFeedback.type==0}">selected</c:if>>意见</option>
									<option value="1" <c:if test="${userFeedback.type==1}">selected</c:if>>投诉</option>
								</select>
								
							</li>
				</ul>
			</div>
<!-- RIGHT -->
			<div class="w50pre fl">
				<ul class="ddBar">
					<li>
					<span class="ddTitle"><font>邮箱：</font></span> 
					<input type="text" name="userFeedback.email" value="${userFeedback.email}" id="useremail" />
					</li>
					<li>
                         <input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
                         <input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	<!-- 	SELECT END -->
	<div class="mt20">
		<div class="commonWrap">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
					</caption>
					<thead>
						<tr>
							<th width="5%">
							<span>ID</span></th>
							<th><span>反馈信息</span></th>
							<th><span>电子邮件</span></th>
							<th><span>手机号码</span></th>
							<th><span>反馈分类</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${userFeedbackList.size()>0}">
							<c:forEach items="${userFeedbackList}" var="feed">
								<tr>
								<td>${feed.id}</td>
								<td>${feed.content}</td>
								<td>${feed.email}</td>
								<td>${feed.mobile}</td>
					     <c:if test="${feed.type==0}">
					     		<td>
					     		  意见
					     		</td>
					     </c:if>	
					    <c:if test="${feed.type!=0}">
					     		<td>
					     		投诉
					     		</td>
					     </c:if>	
								<td>
								<span>
             <a class="ml10 btn smallbtn btn-y" title="查看" href="${ctx}/admin/feed/feedInfo/${feed.id}">查看</a>
								</span>
								</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${userFeedbackList.size()==0||userFeedbackList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有反馈信息！</span>
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
</form>
</body>
</html>
