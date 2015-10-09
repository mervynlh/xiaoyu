<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>用户账户信息</title>
	<script type="text/javascript">
	var isTrue=false;
	var reAmount=0;
	function validAmount(amount){//验证
		var reg=/^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
		if(!reg.test(amount)){
			isTrue=false;
			alert("请输入正确的金额");
			return;
		}
		isTrue=true;
		reAmount=amount;
	}
	function rechargeForm(userId,optFlag){
		if(isTrue){
			var optInfo="";
			var optSure="";
			if(optFlag=="credit"){//充值 信息赋值
				optInfo="充值金额必须大于0";
				optSure="确定充值吗,充值后不得修改?";
			}else{
				optInfo="扣款金额必须大于0";
				optSure="确认扣款吗,操作后不得修改";
			}
			if(reAmount<=0){
				alert(optInfo);
				return;
			}
			if(confirm(optSure)){
				$.ajax({
					url:"${ctx}/admin/account/recharge",
					data:{"userId":userId,"flag":optFlag,"balance":reAmount},
					dataType:"json",
					type:"post",
					success:function(result){
						if(result.success){
							alert(result.message);
							window.location.href="${ctx}/admin/account/list";
						}else{
							alert(result.message);
						}
					}
				});
			}
		}else{
			alert("请输入正确的金额");
			return;
		}
	}
	</script>
</head>
<body >
<div id="main_content" style="margin: 10px;">
<form action="${ctx}/admin/article/updateArticle" method="post" id="rechargeForm">
</form>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>用户账户管理</span> &gt; <span>账户充值</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>账户基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;学员ID</td>
				<td>
					${userAccountDTO.userId }
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>昵称</td>
				<td width="80%">
				${userAccountDTO.nickName}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>邮箱</td>
				<td width="80%">
				${userAccountDTO.email}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>创建时间</td>
				<td width="80%">
				<fmt:formatDate value="${userAccountDTO.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>更新时间</td>
				<td width="80%">
				<fmt:formatDate value="${userAccountDTO.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>账户余额</td>
				<td width="80%">
				${userAccountDTO.balance}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>冻结金额</td>
				<td width="80%">
				${userAccountDTO.forzenAmount}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>银行入账</td>
				<td width="80%">
				${userAccountDTO.cashAmount}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>课程卡入账</td>
				<td width="80%">
				${userAccountDTO.vmAmount}
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>账户状态</td>
				<td width="80%">
				<c:if test="${userAccountDTO.accountStatus=='FROZEN'}">
				冻结
				</c:if>
				<c:if test="${userAccountDTO.accountStatus=='ACTIVE'}">
				正常
				</c:if>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>${flag=='credit'?'充值金额':'扣款金额'}</td>
				<td width="80%">
				<input type="text" value="0" name="rechargeAmount" onblur="validAmount(this.value)"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<c:if test="${flag=='credit'}">
					<a class="btn btn-danger" title="充值" href="javascript:rechargeForm(${userAccountDTO.userId },'credit')">充值</a>
					</c:if>
					<c:if test="${flag=='debit'}">
					<a class="btn btn-danger" title="扣款" href="javascript:rechargeForm(${userAccountDTO.userId },'debit')">扣款</a>
					</c:if>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- /tab4 end -->
</div>
</body>
</html>
