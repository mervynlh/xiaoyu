\<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户账户信息</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	function submitSearch() {
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	$(function(){
		 $( "#startDate,#endDate" ).datetimepicker(
		    		{regional:"zh-CN",
		    		changeMonth: true,
		    		dateFormat:"yy-mm-dd ",
		    		timeFormat: "HH:mm:ss"
		    		});
	})
	function editAccount(userId,status,obj){
		var judge="";
		if(status=="ACTIVE"){//正常
			judge="确定恢复该账户吗？";
		}else{
			judge="确定冻结该账户吗？";
		}
		if(confirm(judge)){
			$.ajax({
				url:"${ctx}/admin/account/update/"+userId,
				data:{"status":status},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.success){
						if(status=="ACTIVE"){
							$(obj).html("冻结");
							$(obj).attr("onclick","editAccount("+userId+",'FROZEN',this)");
							$("#status"+userId).html("正常");
							alert(result.message);
						}else{
							$(obj).html("正常");
							$(obj).attr("onclick","editAccount("+userId+",'ACTIVE',this)");
							$("#status"+userId).html("冻结");
							alert(result.message);
						}
					}
				}
			});
		}
	}
	function clean(){
		$("#nickname,#useremail,#startDate,#endDate").val("");
	}
</script>
</head>
<body>


	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>学员账户</span>
		</h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/account/list" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
						<div class="capHead">
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>手机号：</font></span> <input type="text" name="user.mobile" value="${user.mobile}" id="nickname" /></li>
									<li><span class="ddTitle"><font>注册开始日期：</font></span> <input type="text" name="user.startDate" value="${user.startDate}" id="startDate" class="AS-inp" /></li>
									<%-- <li><span class="ddTitle"><font>手机号：</font></span> <input type="text" name="user.mobile" value="${user.mobile}" id="mobile" /></li> --%>
								</ul>

							</div>
							<div class="w50pre fl">
								<ul class="ddBar">
									<li><span class="ddTitle"><font>邮箱：</font></span> <input type="text" name="user.email" value="${user.email}" id="useremail" /></li>
									<li><span class="ddTitle"><font>注册结束日期：</font></span> <input type="text" name="user.endDate" value="${user.endDate}" id="endDate" class="AS-inp" /></li>
									<li><input type="button" class="btn btn-danger" value="查询" name="" onclick="submitSearch()" /> <input type="button" class="btn btn-danger" value="清空" name="" onclick="clean()" /></li>
								</ul>

							</div>
							<div class="clearfix"></div>
						</div>
					</caption>
					<thead>
						<tr>
							<th width="10%"><span>学员ID</span></th>
							<th width="8%"><span>&nbsp;昵称</span></th>
							<th><span>&nbsp;邮箱</span></th>
							<th><span>&nbsp;手机号</span></th>
							<th><span>创建时间</span></th>
							<th><span>更新时间</span></th>
							<th><span>账户余额</span></th>
							<th><span>冻结金额</span></th>
							<!-- <th><span>银行入账</span></th>
							<th><span>课程卡入账</span></th> -->
							<th><span>账户状态</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
						<c:if test="${userAccountList.size()>0}">
							<c:forEach items="${userAccountList}" var="user">
								<tr>
									<td>${user.userId}</td>
									<td>${user.nickName}</td>
									<td>${user.email}</td>
									<td>${user.mobile}</td>
									<td>
									<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
									<fmt:formatDate value="${user.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>${user.balance}</td>
									<td>${user.forzenAmount}</td>
									<%-- <td>${user.cashAmount}</td>
									<td>${user.vmAmount}</td> --%>
									<td id="status${user.userId}">
									<c:if test="${user.accountStatus=='ACTIVE'}">
										正常
									</c:if>
									<c:if test="${user.accountStatus=='FROZEN'}">
										冻结
									</c:if>									
									</td>
									<td>
									<c:if test="${user.accountStatus=='ACTIVE'}">
									<a class="ml10 btn smallbtn btn-y attr762" href="javascript:void(0)" onclick="editAccount(${user.userId},'FROZEN',this)">冻结</a>
									</c:if>
									<c:if test="${user.accountStatus=='FROZEN'}">
									<a class="ml10 btn smallbtn btn-y attr762" href="javascript:void(0)" onclick="editAccount(${user.userId},'ACTIVE',this)">正常</a>
									</c:if>
									<a class="ml10 btn smallbtn btn-y attr762" href="${ctx}/admin/account/info/${user.userId}/credit">充值</a>
									<a class="ml10 btn smallbtn btn-y attr762" href="${ctx}/admin/account/info/${user.userId}/debit">扣款</a>
									<a class="ml10 btn smallbtn btn-y attr762" href="${ctx}/admin/accout/detailed_list?queryUserAccounthistory.userId=${user.userId}">详情</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${userAccountList.size()==0||userAccountList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
										<span>还没有用户账户信息！</span>
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
					</form>
				</table>
				<!-- /pageBar begin -->
				<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
				<!-- /pageBar end -->
		</div>
		<!-- /commonWrap -->
	</div>

</body>
</html>

