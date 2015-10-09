<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>推广返现列表</title>

<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>

<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){//初始化数据
	//时间插件
    $("#coStartTime,#coEndTime").datetimepicker(
     {regional:"zh-CN",
         changeMonth: true,
         dateFormat:"yy-mm-dd ",
         timeFormat: "HH:mm:ss"
     });
})


function submitSearch(){//搜索
	var startTime = $("#coStartTime").val();
	var endTime = $("#coEndTime").val();
	// 判断结束日期不能大于开始日期
	if(startTime != null && startTime != '' && endTime != null && endTime != ''){
		if(startTime >= endTime){
			alert("查询开始时间必须早于结束时间");
			return;
		}
	}
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
	function tcClean(){//清空
		$("#email").val("");
		$("#userName").val("");
		$("#userType").val(-1);
		$("#coStartTime").val("");
		$("#coEndTime").val("");
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/extend/cashback/list/${teacherId}" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>返现管理</span> &gt; <span>返现列表</span> </h4>
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="fl">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>用户手机号：</font></span>
											<input type="text" name="teacherExtendCashback.mobile" value="${teacherExtendCashback.mobile }" id="email" />
											<span class="ddTitle"><font>用户名：</font></span>
											<input type="text" name="teacherExtendCashback.userName" value="${teacherExtendCashback.userName }" id="userName" />
											<span class="ddTitle"><font>用户类型：</font></span>
											<select name="teacherExtendCashback.userType" id="userType">
											<option value="-1">--请选择--</option>
											<option value="0" ${teacherExtendCashback.userType == 0 ? "selected='selected'" : ""}>学生</option>
											<option value="1" ${teacherExtendCashback.userType == 1 ? "selected='selected'" : ""}>教师</option>
											</select>
										</li>
										<li>
											<span class="ddTitle"><font>查询开始时间：</font></span>
											<input type="text" name="teacherExtendCashback.startTime" value="<fmt:formatDate value="${teacherExtendCashback.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="coStartTime" />
											<span class="ddTitle"><font>查询结束时间：</font></span>
											<input type="text" name="teacherExtendCashback.endTime" value="<fmt:formatDate value="${teacherExtendCashback.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="coEndTime" />
											<span style="margin-left: 125px">
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="tcClean()"/>
											</span>
										</li>
									</ul>
									
								</div>
								<div class="clearfix"></div>
							</div>	
							<%-- <div class="mt10 clearfix">
								<p class="fl czBtn">
								 <span><a href="${ctx}/admin/teacher/toAdd" title="新建讲师"><em class="icon14 new">&nbsp;</em>新建讲师</a></span>
								</p>
							</div>  --%>
						</caption>
						<thead>
							<tr>
								<!-- <th width="5%"><span>&nbsp;ID</span></th> -->
								<th width="8%"><span>ID</span></th>
								<th width="8%"><span>用户名</span></th>
								<th width="8%"><span>用户手机号</span></th>
								<th><span>用户类型</span></th>
								<th><span>返现金额</span></th>
								<th><span>添加时间</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${cashbackList.size()>0}">
						<c:forEach  items="${cashbackList}" var="cashback" >
							<tr id="rem${cashback.id }">
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${cashback.id }</td>
								<td>${cashback.userName }</td>
								<td>${cashback.mobile }</td>
								<td>
									<c:if test="${cashback.userType == 0 }">学生</c:if>
									<c:if test="${cashback.userType == 1 }">教师</c:if>
								</td>
								<td>
									${cashback.cashbackMoney}
								</td>
								<td><fmt:formatDate type="both" value="${cashback.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<%--<td class="c_666 czBtn" align="center">
									<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
									<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
									<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${cashbackList.size()==0||cashbackList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有教师推广返现信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
