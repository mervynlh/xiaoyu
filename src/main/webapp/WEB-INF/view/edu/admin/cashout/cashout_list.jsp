<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>提现列表</title>

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

function orderExcel(){
		$("#searchForm").prop("action","/admin/cashout/export");
		$("#searchForm").submit();
		$("#searchForm").prop("action","/admin/cashout/cashoutList");
	}

function updateStatus(id, status, userId){
	if(status == 2){
		var judge=confirm("确定给当前账户提现吗？");
		if(judge == false){
			return;
		}
	}
	if(status == 3){
		var judge=confirm("确定取消当前账户提现吗？");
		if(judge == false){
			return;
		}
	}
	$.ajax({
		url:'/admin/cashout/updatestatus',
		data:{'cashOut.id':id,'cashOut.status':status,'cashOut.userId':userId},
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success){
				alert("账户提现状态更改成功！");
				window.location.reload();
			}else{
				alert("系统繁忙！");
			}
		}
	});
}
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
		$("#userId").val("");
		$("#userMobile").val("");
		$("#bankName").val("");
		$("#alipayAccount").val("");
		$("#coStartTime").val("");
		$("#coEndTime").val("");
		$("#cashType").val("");
		$("#isStatus").val(0);
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/cashout/cashoutList" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>提现管理</span> &gt; <span>提现列表</span> </h4>
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
											<span class="ddTitle"><font>用户ID：</font></span>
											<input type="text" name="cashOut.userId" value="${cashOut.userId }" id="userId" />
											<span class="ddTitle"><font>手机号：</font></span>
											<input type="text" name="cashOut.userMobile" value="${cashOut.userMobile }" id="userMobile" />
											<span class="ddTitle"><font>开户银行：</font></span>
											<input type="text" name="cashOut.bankName" value="${cashOut.bankName }" id="bankName" />
																						
										</li>
										<li>
											<span class="ddTitle"><font>支付宝账号：</font></span>
											<input type="text" name="cashOut.userId" value="${cashOut.alipayAccount}" id="alipayAccount" />
											<span class="ddTitle"><font>查询开始时间：</font></span>
											<input type="text" name="cashOut.startTime" value="<fmt:formatDate value="${cashOut.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="coStartTime" />
											<span class="ddTitle"><font>查询结束时间：</font></span>
											<input type="text" name="cashOut.endTime" value="<fmt:formatDate value="${cashOut.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="coEndTime" />
											
										</li>
										<li>											
											<span class="ddTitle"><font>提现方式：</font></span>
											<select name="cashOut.cashType" id="cashType">
											<option value="">--请选择--</option>
											<option value="BANK" ${cashOut.cashType == "BANK" ? "selected='selected'" : ""}>银行卡</option>
											<option value="ALIPAY" ${cashOut.cashType == "ALIPAY" ? "selected='selected'" : ""}>支付宝</option>
											</select>
											<span class="ddTitle"><font>状态：</font></span>
											<select name="cashOut.status" id="isStatus">
											<option value="0">--请选择--</option>
											<option value="1" ${cashOut.status == 1 ? "selected='selected'" : ""}>正常</option>
											<option value="2" ${cashOut.status == 2 ? "selected='selected'" : ""}>已提现</option>
											<option value="3" ${cashOut.status == 3 ? "selected='selected'" : ""}>已取消</option>
											</select>
											<span style="margin-left: 125px">
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="tcClean()"/>
											<input type="button"  class="btn btn-danger" value="导出EXCEL" name="" onclick="orderExcel()"/>
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
								<th width="8%"><span>用户ID</span></th>
								<th width="8%"><span>用户名</span></th>
								<th><span>提现方式</span></th>
								<th><span>账号</span></th>
								<th><span>提现金额</span></th>
								<th><span>状态</span></th>
								<th><span>提现时间</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${cashoutList.size()>0}">
						<c:forEach  items="${cashoutList}" var="cash" >
							<tr id="rem${cash.id }">
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${cash.id }</td>
								<td>${cash.userId }</td>
								<td>${cash.openBankPerson }</td>
								<td>
									<c:if test="${cash.cashType == 'BANK' }">银行卡</c:if>
									<c:if test="${cash.cashType == 'ALIPAY' }">支付宝</c:if>
								</td>
								<td>
									<c:if test="${cash.cashType == 'BANK' }">${cash.bankCard }</c:if>
									<c:if test="${cash.cashType == 'ALIPAY' }">${cash.alipayAccount }</c:if>
								</td>
								<td>
									${cash.cashoutMoney }
								</td>
								<td>
									<c:if test="${cash.status == 1 }">正常</c:if>
									<c:if test="${cash.status == 2 }">已提现</c:if>
									<c:if test="${cash.status == 3 }">已取消</c:if>
								</td>
								<td><fmt:formatDate type="both" value="${cash.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" title="详情" href="${ctx}/admin/cashout/info/${cash.id}">详情</a>
								<c:if test="${cash.status == 1 }">
									<a class="ml10 btn smallbtn btn-y" title="确认" href="javascript:void(0)" onclick="updateStatus(${cash.id}, 2, ${cash.userId})">确认提现</a>
									<a class="ml10 btn smallbtn btn-y" title="取消" href="javascript:void(0)" onclick="updateStatus(${cash.id}, 3, ${cash.userId})">取消提现</a>
								</c:if>
								</td>
								<%--<td class="c_666 czBtn" align="center">
									<a href="${ctx}/admin/quest/lookQuestion?queryQuestion.id=${trquestion.id }" title="查看" class="btn smallbtn btn-y">查看</a>
									<a href="${ctx}/admin/quest/toUpdateQuestion?queryQuestion.id=${trquestion.id }" title="修改" class="btn smallbtn btn-y">修改</a>
									<a href="javascript:delQuestionListBatch(${trquestion.id })" title="删除" class="btn smallbtn btn-y">删除</a>
								</td> --%>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${cashoutList.size()==0||cashoutList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有教师提现信息！</span>
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
