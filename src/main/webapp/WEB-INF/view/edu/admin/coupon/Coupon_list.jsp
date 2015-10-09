<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startCreateTime,#endCreateTime" ).datepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd "
	    		});
	
});
function cancel(){
	$("#type").val(-1);
	$("#keyWord").val("");
	$("#keyWordType").val(-1);
	$("#startCreateTime").val("");
	$("#endCreateTime").val("");
	$("#isCouponCode").val(-1);
}
       
</script>
</head>
<body  >
<form action="${ctx}/admin/coupon/page" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券管理</span> &gt; <span>优惠券列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>关键字：</font></span>
									<input type="text" name="queryCoupon.keyWord" id="keyWord" value="${queryCoupon.keyWord}" style="width: 100px"/>
									<select  name="queryCoupon.keyWordType" id="keyWordType" style="width: 100px">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCoupon.keyWordType==1 }">selected="selected"</c:if>>优惠券名称</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>创建开始时间：</font></span>
									<input type="text" name="queryCoupon.startCreateTime" class="AS-inp" readonly="readonly" id="startCreateTime" value="${queryCoupon.startCreateTime}"/>
								</li>
								<li>
									<span class="ddTitle"><font>创建结束时间：</font></span>
									<input type="text" readonly="readonly" name="queryCoupon.endCreateTime" class="AS-inp" id="endCreateTime" value="${queryCoupon.endCreateTime}"/>
								</li>
							</ul>
						</div>
						<div class="w50pre fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>优惠类型：</font></span>
									<select  name="queryCoupon.type" id="type">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCoupon.type==1 }">selected="selected"</c:if>>折扣券</option>
										<option value="2" <c:if test="${queryCoupon.type==2 }">selected="selected"</c:if>>定额券</option>
									</select>
								</li>
								<li>
									<span class="ddTitle"><font>生成编码：</font></span>
									<select  name="queryCoupon.isCouponCode" id="isCouponCode">
										<option value="-1">--请选择--</option>
										<option value="1" <c:if test="${queryCoupon.isCouponCode==1 }">selected="selected"</c:if>>未生成</option>
										<option value="2" <c:if test="${queryCoupon.isCouponCode==2 }">selected="selected"</c:if>>已生成</option>
									</select>
								</li>
							</ul>
						</div>
						<div class="w50pre fl" style="text-align: center;">
							<ul class="ddBar">
								<li>
									<input class="btn btn-danger ml10" type="button" onclick="goPage(1)" value="查询">
									<input class="btn btn-danger ml10" type="button" onclick="cancel()" value="清空">
								</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span class="ml10"><a href="${cxt}/admin/coupon/doadd"><em class="icon14 new">&nbsp;</em>新建优惠卷</a></span>
						</p>
						<p class="fr c_666"><span>优惠券编码列表</span><span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="8%"><span>ID</span></th>
                           <th ><span>名称</span></th>
                           <th><span>类型</span></th>
                           <th><span>使用限额</span></th>
                           <th><span>有效期</span></th>
                           <th><span>生成量/使用量</span></th>
                           <th><span>创建时间</span></th>
                           <th><span>创建人</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${couponDTOList.size()>0}">
				<c:forEach  items="${couponDTOList}" var="coupon" >
					<tr>
						<td>${coupon.id }</td>
						<td>${coupon.title }</td>
						<td>
							<c:if test="${coupon.type==1 }">折扣券</c:if>
							<c:if test="${coupon.type==2 }">定额券</c:if>
							<c:if test="${coupon.type==3 }">会员定额券</c:if>
						</td>
						<td>${coupon.limitAmount }</td>
						<td>
							<fmt:formatDate value="${coupon.startTime}" type="both"  pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${coupon.endTime}" type="both"  pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${coupon.totalNum}/${coupon.userNum}
						</td>
						
						<td>
							<fmt:formatDate value="${coupon.createTime}" type="both"  pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${coupon.optuserName }
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/coupon/detail/${coupon.id}">查看</a>
							<c:if test="${coupon.isCouponCode==0 }">
                               	<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/coupon/createcode/${coupon.id}">生成编码</a>
                             </c:if>
                             <c:if test="${coupon.isCouponCode==1 }">
                             	<span class="ml10 btn smallbtn" style="color:#666666">已生成</span>
                             </c:if>
						</td>
						
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${couponDTOList.size()==0||couponDTOList==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有优惠券！</span>
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
