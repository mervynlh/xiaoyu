<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>查看优惠券编码</title>

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
</script>
</head>
<body >
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券编码管理</span> &gt; <span>查看优惠券编码</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
		<div class="commonWrap">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>优惠券编码基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券编码 </td>
						<td>
							<input type="text" value="${couponCode.couponCode}" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;优惠券名称 </td>
						<td>
							<input type="text" value="${couponDTO.title}" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>类型</td>
						<td width="80%">
							<c:if test="${couponDTO.type==1 }"><input type="text" value="折扣券" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponDTO.type==2 }"><input type="text" value="定额券" readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;状态</td>
						<td>
							<c:if test="${couponCode.status==1 }"><input type="text" value="未使用" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponCode.status==2 }"><input type="text" value="已使用" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponCode.status==3 }"><input type="text" value="过期" readonly="readonly" class="{required:true}"/></c:if>
							<c:if test="${couponCode.status==4 }"><input type="text" value="作废" readonly="readonly" class="{required:true}"/></c:if>
						</td>
					</tr>
					<c:if test="${couponCode.status==2 }">
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;订单编号</td>
							<td>
								<input type="text" value="${couponCode.requestId }" readonly="readonly" class="{required:true}"/>
							</td>
						</tr>
					</c:if>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;使用限额</td>
						<td>
							<input type="text" value="${couponDTO.limitAmount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;元以上&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red">只有订单总金额达到这个数的订单才能使用此折扣优惠券，如300元，则请输入“300”</font>
						</td>
					</tr>
					<tr id="preferentialTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠折扣</td>
						<td>
							<input type="text" value="${couponDTO.amount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;折&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:red">该优惠券折扣额，如2折优惠券，则选择“2.0”</font>
						</td>
					</tr>
					<tr id="reduceTr">
						<td align="center"><font color="red">*</font>&nbsp;优惠金额</td>
						<td>
							<input type="text" value="${couponDTO.amount}" readonly="readonly" class="{required:true,number:true,min:0,max:1000}"/>&nbsp;&nbsp;<font style="color:red">该优惠券金额，如5元优惠券，则请输入“5”</font>
						</td>
					</tr>
					<c:if test="${couponDTO.subjectName!=null&&couponDTO.subjectName!=''}">
					<tr id="limitSubject">
						<td align="center">&nbsp;</td>
						<td>
							<input type="text" value="${couponDTO.subjectName}" readonly="readonly" class="{required:true}"/>
						</td>
					</tr>
					</c:if>
					<c:if test="${couponDTO.courses!=null&&couponDTO.courses.size()>0}">
					<tr id="limitCourse">
						<td align="center">&nbsp;</td>
						<td>
							<c:forEach items="${couponDTO.courses}" var="course">
								<input type="text" value="${course.name}" readonly="readonly" class="{required:true}"/><br/>
							</c:forEach>
						</td>
					</tr>
					</c:if>
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
				</tbody>
			</table>
			</div>
		</div>
		<!-- /tab4 end -->
</body>
</html>
