<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>管理员操作记录列表</title>
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
	    		changeYear: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
});
function clean(){
	$("#optusername,#type,#startDate,#endDate").val("");
}
</script>
</head>
<body >

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>管理员操作记录列表</span> </h4>
	</div>
<div class="mt20">
<form action="${ctx}/admin/user/optRecord" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
		
			<caption>
			<!-- <div class="capHead"> </div> -->
				<div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>操作人：</font></span><input type="text" name="userOptRecord.optusername" id="optusername" value="${userOptRecord.optusername }"/></li>
                                            <li>
												<span class="ddTitle"><font>操作起始日期：</font></span>
												<input type="text" name="userOptRecord.startDate" value="${userOptRecord.startDate}"  id="startDate" class="AS-inp"/>
											</li>
										</ul>
                                    </div>
			
			     <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>操作类型：</font></span>
                                               	<select name="userOptRecord.type" id="type">
                                               		<option value="">全部</option>
                                               		<option value="REFUND" <c:if test="${userOptRecord.type=='REFUND' }">selected</c:if>>退费</option>
                                               		<option value="AUDIT" <c:if test="${userOptRecord.type=='AUDIT' }">selected</c:if>>审核</option>
                                               		<option value="DISABLE" <c:if test="${userOptRecord.type=='DISABLE' }">selected</c:if>>禁用</option>
                                               		<option value="ACTIVE" <c:if test="${userOptRecord.type=='ACTIVE' }">selected</c:if>>正常</option>
                                               		<option value="CHANGECOURSE" <c:if test="${userOptRecord.type=='CHANGECOURSE' }">selected</c:if>>换课</option>
                                               		<option value="GIVECOURSE" <c:if test="${userOptRecord.type=='GIVECOURSE' }">selected</c:if>>赠送课程</option>
                                               		<option value="ADMINLOAD" <c:if test="${userOptRecord.type=='ADMINLOAD' }">selected</c:if>>后台充值</option>
                                               		<option value="CHANGEPWD" <c:if test="${userOptRecord.type=='CHANGEPWD' }">selected</c:if>>修改密码</option>
                                               		<option value="SYSACTIVE" <c:if test="${userOptRecord.type=='SYSACTIVE' }">selected</c:if>>冻结用户</option>
                                               		<option value="ADMINREFUND" <c:if test="${userOptRecord.type=='ADMINREFUND' }">selected</c:if>>后台扣款</option>
                                               		<option value="CLOSED" <c:if test="${userOptRecord.type=='CLOSED' }">selected</c:if>>关闭课程</option>
                                               		<option value="SYSDELETE" <c:if test="${userOptRecord.type=='SYSDELETE' }">selected</c:if>>后台用户删除</option>
                                               	</select>
                                            </li>
                                            <li>
	                                            <span class="ddTitle"><font>操作结束日期：</font></span>
	                                            <input type="text" name="userOptRecord.endDate" value="${userOptRecord.endDate}"  id="endDate" class="AS-inp"/>
                                       		 </li>
                                        </ul>
                                    </div>
                                     <div   align="center">
                                     <input type="button" value="查询" class="btn btn-danger ml10" onclick="goPage(1)"/>
                                     <input type="button" name="" value="清空" class="btn btn-danger" onclick="clean()">
                                    </div>
		</caption>
			<thead>
				<tr>
					<th><span>ID</span></th>
					<th><span>操作人</span></th>
					<th><span>操作类型</span></th>
					<th><span>业务ID</span></th>
					<th><span>描述</span></th>
					<th><span>操作时间</span></th>
				</tr>
			</thead>
			<tbody id="tabS_01">
			<c:forEach items="${list }" var="opt">
	                <tr   bgcolor="#FFFFFF" align="center">
		                  <td class="yx2"><c:out value="${opt.id }" /></td>
		                  <td class="yx2"><c:out value="${opt.optusername }" /></td>
		                  <td class="yx2">
		                  <c:if test="${opt.type=='REFUND' }">退费</c:if>
		                  <c:if test="${opt.type=='AUDIT' }">审核</c:if>
		                  <c:if test="${opt.type=='GIVECOURSE' }">赠送课程</c:if>
		                  <c:if test="${opt.type=='ADMINLOAD' }">后台充值</c:if>
		                  <c:if test="${opt.type=='CHANGEPWD' }">修改密码</c:if>
		                  <c:if test="${opt.type=='SYSACTIVE' }">冻结用户</c:if>
		                  <c:if test="${opt.type=='ADMINREFUND' }">后台扣款</c:if>
		                  <c:if test="${opt.type=='DISABLE' }">禁用</c:if>
		                  <c:if test="${opt.type=='ACTIVE' }">正常</c:if>
		                  <c:if test="${opt.type=='CLOSED' }">关闭课程</c:if>
		                  <c:if test="${opt.type=='CHANGECOURSE' }">换课</c:if>
		                  <c:if test="${opt.type=='SYSDELETE' }">后台用户删除</c:if>
		                  </td>
		                  <td class="yx2"><c:out value="${opt.bizId }" /></td>
		                  <td class="yx2"><c:out value="${opt.description }" /></td>
		                  <td class="yx2"><fmt:formatDate value="${opt.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		            </tr>
              </c:forEach>
			</tbody>
		</table>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
	</form>
</div>

</body>
</html>
