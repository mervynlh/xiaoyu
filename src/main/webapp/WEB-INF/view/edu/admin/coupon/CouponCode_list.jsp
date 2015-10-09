<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券编码列表</title>

<script type="text/javascript">
function cancel(){
	$("#type").val(-1);
	$("#useType").val(-1);
	$("#status").val(-1);
	$("#couponId").val("");
	$("#requestId").val("");
	$("#couponCode").val("");
}
function allCheck(cb)
{
    $("input[name=ids]:checkbox").attr('checked', cb.checked);
}       
function waste(id){
	$.ajax({
		url:"${cxt}/admin/couponcode/waste",
		type:"post",
		data:{"ids":id},
		dataType:"json",
		success:function(result){
			if(result.message=="true"){
				alert("已作废");
				window.location.reload();
			}
		}
	});
}
function allCheck(cb)
{
	$("input[name=ids]").attr('checked', cb.checked);
}
function wasteBatch(){
	var codeIds = document.getElementsByName("ids");
	var num=0;
	var ids ='';
	for(var i=0;i<codeIds.length;i++){
		if(codeIds[i].checked==true){
			num++;
			ids +=codeIds[i].value;
			if(i!=codeIds.length-1){
				ids +=",";
			}
		}
	}
	if(num==0){
		alert("请选择要作废的优惠码");
		return;
	}
	$.ajax({
		url:"${cxt}/admin/couponcode/waste",
		type:"post",
		data:{"ids":ids},
		dataType:"json",
		success:function(result){
			if(result.message=="true"){
				alert("已作废");
				window.location.reload();
			}
		}
	});
}
//导出
function couponExcel(){
	$("#searchForm").prop("action","${ctx}/admin/coupon/exportcode");
	$("#searchForm").submit();
	$("#searchForm").prop("action","${ctx}/admin/couponcode/page");
}
</script>
</head>
<body  >

<!-- 内容 开始  -->
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券编码管理</span> &gt; <span>优惠券编码列表</span> </h4>
	</div>
		<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
				<form action="${ctx}/admin/couponcode/page" name="searchForm" id="searchForm" method="post">
				<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
					<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>优惠券ID：</font></span>
									<input type="text" name="queryCouponCode.couponId" id="couponId" value="${queryCouponCode.couponId}"/>
								</li>
								<li>
									<span class="ddTitle"><font>订单编号：</font></span>
									<input type="text" name="queryCouponCode.requestId" id="requestId" value="${queryCouponCode.requestId}"/>
								</li>
								<li>
									<span class="ddTitle"><font>优惠券编码：</font></span>
									<input type="text" name="queryCouponCode.couponCode" id="couponCode" value="${queryCouponCode.couponCode}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>优惠类型：</font></span>
									<select  name="queryCouponCode.type" id="type">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCouponCode.type==1 }">selected="selected"</c:if>>折扣券</option>
										<option value="2" <c:if test="${queryCouponCode.type==2 }">selected="selected"</c:if>>定额券</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>编码状态：</font></span>
									<select  name="queryCouponCode.status" id="status">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCouponCode.status==1 }">selected="selected"</c:if>>未使用</option>
										<option value="2" <c:if test="${queryCouponCode.status==2 }">selected="selected"</c:if>>已使用</option>
										<option value="3" <c:if test="${queryCouponCode.status==3 }">selected="selected"</c:if>>过期</option>
										<option value="4" <c:if test="${queryCouponCode.status==4 }">selected="selected"</c:if>>作废</option>
									</select>
								</li>
							</ul>
						</div>
						<div class="w50pre fl" style="text-align: center;">
							<ul class="ddBar">
								<li>
									<input class="btn btn-danger ml10" type="button" onclick="goPage(1)" value="查询">
									<input class="btn btn-danger ml10" type="button" onclick="cancel()" value="清空">
									<input type="button" onclick="couponExcel()" class="btn btn-danger ml10" value="导出Excel"  />
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span class="ml10"><a href="javascript:wasteBatch()"><em class="icon14 delete">&nbsp;</em>批量作废</a></span>
						</p>
						<p class="fr c_666"><span>优惠券编码列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
					<thead>
						<tr>
							<th width="10%"><span><input type="checkbox" onclick="allCheck(this)"/>全选</span></th>
                            <th><span>名称</span></th>
                            <th><span>优惠券编码</span></th>
                            <th><span>类型</span></th>
                            <th><span>限额</span></th>
                            <th><span>有效期</span></th>
                            <th><span>状态</span></th>
                            <th><span>创建时间</span></th>
                            <th><span>创建人</span></th>
                            <th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_02" align="center">
					<c:if test="${couponCodeDTOList.size()>0}">
					<c:forEach  items="${couponCodeDTOList}" var="couponCodeDTO" >
						<tr>
							<td><input type="checkbox" name="ids" value="${couponCodeDTO.id}" /></td>
							<td>${couponCodeDTO.title}</td>
							<td>${couponCodeDTO.couponCode}</td>
							<td>
								<c:if test="${couponCodeDTO.type==1 }">折扣券</c:if>
								<c:if test="${couponCodeDTO.type==2 }">定额券</c:if>
								<c:if test="${couponCodeDTO.type==3 }">会员定额券</c:if>
							</td>
							<td>${couponCodeDTO.limitAmount }</td>
							<td>
								<fmt:formatDate value="${couponCodeDTO.startTime}" type="both"  pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${couponCodeDTO.endTime}" type="both"  pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<c:if test="${couponCodeDTO.status==1 }">未使用</c:if>
								<c:if test="${couponCodeDTO.status==2 }">已使用</c:if>
								<c:if test="${couponCodeDTO.status==3 }">过期</c:if>
								<c:if test="${couponCodeDTO.status==4 }">作废</c:if>
							</td>
							<td>
								<fmt:formatDate value="${couponCodeDTO.createTime}" type="both"  pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${couponCodeDTO.optuserName }
							</td>
							<td  class="c_666 czBtn" align="center">
								<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/couponcode/detail/${couponCodeDTO.id}">查看</a>
								<c:if test="${couponCodeDTO.status==1 }">
                                	<a class="ml10 btn smallbtn btn-y" href="javascript:waste(${couponCodeDTO.id})">作废</a>
                                </c:if>
                                <c:if test="${couponCodeDTO.status!=1 }">
                                	<span class="ml10 btn smallbtn" style="color:#666666">作废</span>
                                </c:if>
							</td>
							
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${couponCodeDTOList.size()==0||couponCodeDTOList==null}">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有优惠券编码！</span>
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
<!-- 内容 结束 -->

</body>
</html>
