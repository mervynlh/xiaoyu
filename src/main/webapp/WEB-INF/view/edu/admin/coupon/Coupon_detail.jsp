<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>查看优惠券</title>

<script type="text/javascript">
	var type='${couponDTO.type}';
	$(function(){
		changeType(type);//初始化优惠券类型
	});
	function changeType(val){
		if(val==1){
			$("#reduceTr").css("display","none");
			$("#preferentialTr").css("display","");
		}else{
			$("#preferentialTr").css("display","none");
			$("#reduceTr").css("display","");
		}
	}
	function daochuBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		window.location.href="${ctx}/admin/coupon/exportcode?queryCouponCode.couponId="+id;
	   		return true;
	   	}else{
	   		alert('此优惠券无优惠编码，不能进行导出！');
	   		return false;
	   	}
   	}
	function wasteBianMa(id){
   		var codeSize='${couponDTO.couponCodes.size()}';
	   	if(codeSize>0){
	   		$.ajax({
	   			url:"${ctx}/admin/coupon/wastecoupon/"+id,
	   			type:"post",
	   			dataType:"json",
	   			success:function(result){
	   				if(result.message=='true'){
	   					alert("作废成功");
	   				}
	   			}
	   		});
	   		
	   	}else{
	   		alert('此优惠券无优惠编码，不能进行作废！');
	   	}
   	}
</script>
</head>
<body >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券管理</span> &gt; <span>查看优惠券</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>优惠券基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券名称 </td>
						<td>
							<input type="text" value="${couponDTO.title}" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券类型</td>
						<td>
							<c:if test="${couponDTO.type==1 }"><input type="text" value="折扣券" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponDTO.type==2 }"><input type="text" value="定额券" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponDTO.type==3 }"><input type="text" value="会员定额券" readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用限额</td>
						<td>
							<input type="text" value="${couponDTO.limitAmount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;元以上&nbsp;&nbsp;&nbsp;&nbsp;只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”
						</td>
					</tr>
					<tr id="preferentialTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠折扣</td>
						<td>
							<input type="text" value="${couponDTO.amount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;折&nbsp;&nbsp;&nbsp;&nbsp;该优惠券折扣额，如2折优惠券，则选择“2.0”
						</td>
					</tr>
					<tr id="reduceTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>
							<input type="text" value="${couponDTO.amount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;该优惠券金额，如5元优惠券，则请输入“5”
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用起始日期</td>
						<td>
							<input type="text" value='<fmt:formatDate value="${couponDTO.startTime}" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用截止日期</td>
						<td>
							<input type="text" value="<fmt:formatDate value="${couponDTO.endTime}" type="both" pattern="yyyy-MM-dd"/>" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;生成优惠编码数量</td>
						<td>
							<input type="text" value="${couponDTO.totalNum}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;必须是数字，数字不得大于1000
						</td>
					</tr>
					<tr>
						<td align="center">备注</td>
						<td>
							<textarea rows="3" cols="80" readonly="readonly">${couponDTO.info}</textarea>
						</td>
					</tr>
					<c:if test="${couponDTO.couponCodes!=null&&couponDTO.couponCodes.size()>0}">
						<tr>
							<td align="center" id="intro">优惠券编码</td>
							<td>
								<textarea id="couponCode" style="font-size: 9" readonly="readonly" rows="8" cols="35"></textarea>
								<a onclick="daochuBianMa(${couponDTO.id})">
									<font>导出优惠码</font>
								</a>
								<a onclick="wasteBianMa(${couponDTO.id})">
									<font>作废优惠码</font>
								</a>
								<script type="text/javascript">
									 var textareaHtml="";
									 <c:forEach items="${couponDTO.couponCodes}" var="codeString" varStatus="status">
			                             textareaHtml=textareaHtml+'${status.count}、'+'${codeString}'+'\n';
			                         </c:forEach>
		                            $("#couponCode").html(textareaHtml);
								</script>
							</td>
						</tr>
					</c:if>
					
				</tbody>
			</table>
			</div>
		</div>
		<!-- /tab4 end -->
</body>
</html>
