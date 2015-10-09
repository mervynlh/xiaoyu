<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
function selectIntegral(){
	//return CheckDate($("#startDate").val());
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
$(function(){//初始化时间
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
	if('${userIntegralRecord.status}'==null ||'${userIntegralRecord.status}'==''){
		$("#handle").val(-1);
	}else{
		$("#handle").val('${userIntegralRecord.status}');
	}
});
function cleanIntegral(){
	$("#startDate,#endDate,#name").val("");
	$("#handle").val(-1);
}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>积分兑换记录</span>
		</h4>
	</div>
	<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
					<caption>
						 <div class="capHead">
							<div class="clearfix">
							<form action="${ctx}/admin/user/exchange" method="post" id="searchForm">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
							<div class="w50pre fl">
									<ul class="ddBar">
									<li> <span class="ddTitle"><font>用户姓名:</font></span>
									<input type="text" name="userIntegralRecord.realname" id="name" value="${userIntegralRecord.realname}"/>
									</li>
									<li> <span class="ddTitle"><font>开始日期:</font></span>
									<input type="text" name="userIntegralRecord.startDate" value="${userIntegralRecord.startDate}" readonly="readonly" id="startDate" class="AS-inp"/>
									</li>
									</ul>
									</div>
									<div class="w50pre fl">
									<ul class="ddBar">
									<li> <span class="ddTitle"><font>处理状态:</font></span>
									<select name="userIntegralRecord.status" id="handle">
										<option value="-1">--全部--</option>
										<option value="0" <c:if test="${userIntegralRecord.status==0 }"></c:if>>未处理</option>
										<option value="1" <c:if test="${userIntegralRecord.status==1 }"></c:if>>已处理</option>
										</select>
									</li>
									<li>
									<span class="ddTitle"><font>结束日期:</font></span>
									<input type="text" name="userIntegralRecord.endDate" value="${userIntegralRecord.endDate}"  readonly="readonly" id="endDate" class="AS-inp"/>
									</li>
									</ul>
									</div>
									<div class="w50pre fl">
									<ul class="ddBar">
									<li>
									<span class="ddTitle"><font></font></span>
								<input type="button" class="btn btn-danger" value="查询"
											onclick="selectIntegral()" />
											<input type="button" class="btn btn-danger" value="清空"
											onclick="cleanIntegral()" />
									</li>
									</ul>
									</div>
									</form>
							</div>
						</div> 
					</caption>
					<thead>
						<tr>
							<th><span>兑换礼品</span></th>
							<th><span>用户id</span></th>
							<th><span>用户姓名</span></th>
							<th><span>用户手机号</span></th>
							<th><span>时间</span></th>
							<th><span>收货地址</span></th>
							<th><span>状态</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:forEach items="${userIntegralRecordList}" var="igRecord">
							<tr>
								<td>${igRecord.giftName}</td>
								<td>${igRecord.userId}</td>
								<td>${igRecord.realname}</td>
								<td>${igRecord.mobile}</td>
								<td><fmt:formatDate value="${igRecord.createTime }" type="both"/></td>
								<td>${igRecord.address}</td>
								<td>
								<c:if test="${igRecord.courseId==0 }">
								<c:if test="${igRecord.status==0 }"><a class="btn smallbtn btn-y" href="${ctx }/admin/user/editrecord/${igRecord.id}">未处理</a></c:if>
								<c:if test="${igRecord.status==1 }"><a class="btn smallbtn btn-y" href="javascript:void(0)">标记为已处理</a></c:if>
								</c:if>
								<c:if test="${igRecord.courseId!=0 }">
								<a class="ml10 btn smallbtn" href="javascript:void(0)" style="color:#666666">兑换课程完成</a>
								</c:if>
								</td>
								<%-- <td class="c_666 czBtn" align="center">
								<a class="btn smallbtn btn-y" onclick="deladmin(${ig.id})" href="javascript:void(0)"
									title="查看详情">查看详情</a></td> --%>
							</tr>
						</c:forEach>
						<c:if test="${userIntegralRecordList.size()==0||userIntegralRecordList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有积分兑换记录！</span>
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