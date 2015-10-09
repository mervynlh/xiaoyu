<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>课程信息统计</title>
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
	<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
	<script type="text/javascript">
		$(function(){
			var queryType='${queryType}';
			datepickerLoad();
			$("#queryType").on({
				change: function() {
					var type=$("#queryType").val();
					$(".datepocker").attr('disabled','disabled');
					$(".datepocker").val('');
					if(type=="other"){
						$(".datepocker").removeAttr('disabled');
					}
				}
			});
			$("#queryType").val(queryType);
			$("#orderBy").val('${orderBy}');
			if(queryType=="other"){
				$(".datepocker").removeAttr('disabled');
			}
		});
		//日期插件加载
		function datepickerLoad(){
			$( "#startDate" ).datepicker({
				regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		onSelect: function(dateText, inst) {
					$('#endDate').datepicker('option', 'minDate', new Date(dateText.replace('-',',')));}
	    	});
			$( "#endDate" ).datepicker({
				regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		onSelect: function(dateText, inst) {
					$('#startDate').datepicker('option', 'maxDate', new Date(dateText.replace('-',',')));}
	    	});
		}
	</script>
</head>
<body>
		<form action="/admin/statistics/course/class" name="searchForm" id="searchForm" method="post">
		<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>统计管理</span> &gt; <span>班课课程统计</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0"  cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="clearfix">
										<div class="optionList">
											<span><font>时间段：</font>
											<select id="queryType" style="width: 200px;" name="queryType">
												<option value="">统计所有</option>
                                                <option value="today">今天</option>
                                                <option value="yesterday">昨天</option>
                                                <option value="thisweek">本周</option>
                                                <option value="lastweek">上周</option>
                                                <option value="thismonth">本月</option>
                                                <option value="lastmonth">上月</option>
                                                <option value="other">自定义时间段查询</option>
											</select>
											</span>
											<span>
											<font>起始时间：</font>
											<input type="text" name="startDate" value="${startDate}" id="startDate" class="datepocker" disabled="disabled"/>
											<font>结束时间：</font> 
											<input type="text" name="endDate" value="${endDate}"  id="endDate" class="datepocker" disabled="disabled"/>
											</span>
											排序方式：
											<select id="orderBy" style="width: 200px;" name="orderBy">
												<option value="countDesc">按销售量排序（倒序）</option>
												<option value="countAsc">按销售量排序（正序）</option>
												<option value="amountDesc">按销售金额排序（倒序）</option>
												<option value="amountAsc">按销售金额排序（正序）</option>
											</select>
										</div>
										<div class="optionList">
											<input type="submit" class="btn btn-danger" value="查询" />
										</div>
									</div>
								</div>
							</caption>
						</table>
					</div>
				</div>
				<!-- /commonWrap -->
		</form>
        <div class="mt20">
            <div class="commonWrap">
					<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0" style="text-align: center;">
                    <thead>
                    <tr>
                    	<th><span>课程ID</span></th>
                        <th><span>课程</span></th>
                        <th><span>老师</span></th>
                        <th><span>课程类型</span></th>
                        <th><span>销售量</span></th>
                        <th><span>销售总金额</span></th>
                        <th><span>操作</span></th>
                    </tr>
                    </thead>
					<tbody>
					<c:if test="${!empty courseStatisticsList}">
					<c:forEach items="${courseStatisticsList}" var="statistics">
						<tr>
						<td>${statistics.courseId}</td>
						<td>${statistics.courseName}</td>
						<td>${statistics.teacherName}</td>
						<td>
							<c:if test="${statistics.sellType==1}">一对一</c:if>
							<c:if test="${statistics.sellType==2}">班课</c:if>
						</td>
						<td>${statistics.count}</td>
						<td>${statistics.totalAmount}</td>
						<td><a class="ml10 btn smallbtn btn-y" href="${ctx}/admin//order/statisticsorderlist?queryTrxorder.courseId=${statistics.courseId}">查看订单</a></td>
						</tr>		
					</c:forEach>
					</c:if>
					 <c:if test="${empty courseStatisticsList}">
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
					<c:if test="${!empty courseStatisticsList}">
						<div class="pagination pagination-large tac">
	                  		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" /> 
						</div>
					</c:if>
				</div>
		</div>
</body>
</html>


