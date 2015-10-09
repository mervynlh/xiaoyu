<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
});
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function clean(){
	$("#useremail,#userId,#startDate,#endDate").val("");
}
</script>
</head>
<body  >

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>学员登陆日志</span> &gt; <span>登陆日志列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/loginlog" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			
						<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
										    <span class="ddTitle"><font>学员ID：</font></span>
											<input type="text" name="userLoginLog.userId" value="${userLoginLog.userId}" id="userId" />
										</li>
										<li>
											<span class="ddTitle"><font>登陆开始日期：</font></span>
											<input type="text" name="userLoginLog.startDate" value="${userLoginLog.startDate}"  id="startDate" class="AS-inp"/>
										</li>
									</ul>
								</div>
                               <div class="w50pre fl">
                                    <ul class="ddBar">
                                    	<li>
										    <span class="ddTitle"><font>邮箱：</font></span>
											<input type="text" name="userLoginLog.email" value="${userLoginLog.email}" id="useremail" />
										</li>
                                        <li>
                                            <span class="ddTitle"><font>登陆结束日期：</font></span>
                                            <input type="text" name="userLoginLog.endDate" value="${userLoginLog.endDate}"  id="endDate" class="AS-inp"/>
                                        </li>
                                        <li><input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
                                            <input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
                                        </li>
                                    </ul>
                                </div> 
								<div class="clearfix"></div>
							</div>
						</caption>
						<thead>
							<tr>
								<th><span>&nbsp;学员ID</span></th>
								<th><span>学员邮箱</span></th>
								<th><span>登陆时间</span></th>
								<th><span>登陆Ip</span></th>
								<th><span>登陆地址</span></th>
								<th><span>浏览器</span></th>
								<th><span>操作系统</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${userLoginLogList.size()>0}">
						<c:forEach  items="${userLoginLogList}" var="user" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${user.userId }</td>
								<td>${user.email }</td>
								<td><fmt:formatDate value="${user.loginTime }" type="both"/></td>
								<td>${user.loginIp}</td>
								<td>${user.address}</td>
								<td>${user.userAgent}</td>
								<td>${user.osname}</td>
							<%-- 	<td  class="c_666 czBtn" align="center">
								<c:if test="${user.isavalible==0}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="禁用" href="javascript:updateIsavalible(${user.id},1)">禁用</a>
							     </c:if>
							      <c:if test="${user.isavalible==1}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="恢复" href="javascript:updateIsavalible(${user.id},0)">恢复</a>
							     </c:if>
								<a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a>
							     <a class="ml10 btn smallbtn btn-y" title="赠送课程" href="${ctx}/admin/user/courseList?queryCourse.userId=${user.id}">赠送课程</a>
							     <a class="ml10 btn smallbtn btn-y" target="_blank" title="学习记录" href="${ctx}/admin/user/studyhistory/${user.id}">学习记录</a>
                                <a class="ml10 btn smallbtn btn-y" target="_blank" title="考试记录" href="${ctx}/admin/paper/toPaperRecord?queryPaperRecord.email=${user.email}">考试记录</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${userLoginLogList.size()==0||userLoginLogList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有学员登陆信息！</span>
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
