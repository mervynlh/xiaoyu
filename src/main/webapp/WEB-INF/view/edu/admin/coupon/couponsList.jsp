<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>

<script type="text/javascript">
function closeWin() {
	window.close();
}
function selectCoupon(id){
	opener.queryCoupon(id);
	closeWin();
}
</script>
</head>
<body  >
<!-- 内容 开始  -->
<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>优惠券管理</span> &gt; <span>优惠券列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="mt10 clearfix">
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
                           <th><span>生成编码</span></th>
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
							<c:if test="${coupon.isCouponCode==0 }">未生成</c:if>
							<c:if test="${coupon.isCouponCode==1 }">已生成</c:if>
						</td>
						
						<td>
							<fmt:formatDate value="${coupon.createTime}" type="both"  pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${coupon.optuserName }
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="javascript:selectCoupon(${coupon.id})">确定</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:closeWin()">取消</a>
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
