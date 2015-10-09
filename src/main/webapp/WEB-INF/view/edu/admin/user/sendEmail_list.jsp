<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>邮箱列表</title>

<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datepicker(
		{regional:"zh-CN",
		changeMonth: true,
		dateFormat:"yy-mm-dd "
		});
	
});
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function clean(){
	$("#nickname,#useremail,#email,#startDate,#endDate").val("");
    $("#type").val(0);
}
</script>
</head>
<body  >


<!-- 内容 开始  -->

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>邮件管理</span> &gt; <span>邮件列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/sendEmaillist" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
						<caption>
							<div class="capHead">
								<div class=" fl">
									<ul class="ddBar">
										<li style="width: 100%">
											<span class="ddTitle"><font>邮箱：</font></span>
											<input type="text" name="userEmailMsg.email" value="${userEmailMsg.email}" id="email" />
                                            <span class="ddTitle"><font>类型：</font></span>
                                            <select name="userEmailMsg.type" id="type">
                                                <option value="0">请选择</option>
                                                <option value="1">普通</option>
                                                <option value="2">定时</option>
                                            </select>
                                            <script>
                                                $("#type").val('${userEmailMsg.type}');
                                            </script>
											<span class="ddTitle"><font>是否发送：</font></span>
											<select name="userEmailMsg.status" id="status">
												<option value="0">请选择</option>
												<option value="1">已发送</option>
												<option value="2">未发送</option>
											</select>
											<script>
												$("#status").val('${userEmailMsg.status}');
											</script>
										</li>
										<li>
											<span class="ddTitle"><font>发送时间：</font></span>
											<input type="text" name="userEmailMsg.startDate" value="${userEmailMsg.startDate}"  id="startDate" class="AS-inp"/>
											<span class="ddTitle"><font>结束时间：</font></span>
											<input type="text" name="userEmailMsg.endDate" value="${userEmailMsg.endDate}"  id="endDate" class="AS-inp"/>
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
										</li>
									</ul>
									
								</div>
								<div class="clearfix"></div>
							</div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
									<span><a href="${ctx}/admin/user/toEmailMsg" title="发送邮件"><em class="icon14 new">&nbsp;</em>发送邮件</a></span>
								</p>
							</div> 
						</caption>
						<thead>
							<tr>
								<th><span>ID</span></th>
                                <th><span>邮件类型</span></th>
                                <th><span>是否发送</span></th>
								<th><span>邮件标题</span></th>
								<th><span>邮箱</span></th>
								<th><span>创建时间</span></th>
                                <th><span>发送时间</span></th>
								<th><span>操作人</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="msg" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${msg.id }</td>
                                <td>${msg.type==1?'普通':'定时' }</td>
                                <td>${msg.status==1?'已发送':'未发送' }</td>
								<td>${msg.title }</td>
								<td>${fn:substring(msg.email, 0, 50)}</td>
								<td><fmt:formatDate value="${msg.createTime }" type="both"/></td>
                                    <td><fmt:formatDate value="${msg.sendTime }" type="both"/></td>
								<td>${msg.loginName}</td>
								<td  class="c_666 czBtn" align="center">
                                    <a class="ml10 btn smallbtn btn-y" title="查看" href="${ctx}/admin/user/sendEmailMsgInfo/${msg.id }">查看</a>
                                    <c:if test="${msg.type==2&&msg.status==2}">
                                    <a class="ml10 btn smallbtn btn-y" title="删除" href="${ctx}/admin/sendEmail/del?id=${msg.id }">删除</a>
                                    </c:if>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有邮件信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					</form>
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				</div><!-- /commonWrap -->
			</div>
</body>
</html>
