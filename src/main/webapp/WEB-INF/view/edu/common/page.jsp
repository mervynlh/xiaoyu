<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<script type="text/javascript" src="${ctximg}/static/edu/js/common/page.js?v=<%=version%>"></script>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalResultSize>0}">
				<div class="pagination pagination-large">
					<ul>
						<c:choose>
						   <c:when test="${page.first}">
							  <li class="disabled"><a href="#">首页</a></li>
						   </c:when>
						   <c:otherwise>
						  <li><a href="javascript:goPage(1);">首页</a></li>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.first}">
							   <li id="backpage" class="disabled"><a href="javascript:void(0)">←上一页</a></li>
						   </c:when>
						   <c:otherwise>
						  <li id="backpage"><a href="javascript:goPage(${page.currentPage-1 });">←上一页</a></li>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.last}">
							 <li id="nextpage" class="disabled"><a href="javascript:void(0)">下一页→</a></li>
						   </c:when>
						   <c:otherwise>
						 <li id="nextpage" ><a href="javascript:goPage(${page.currentPage+1 })">下一页→</a></li>
						   </c:otherwise>
						</c:choose>
						<c:choose>
						   <c:when test="${page.last}">
							 <li class="disabled"><a href="javascript:void(0)">尾页 </a></li>
						   </c:when>
						   <c:otherwise>
						<li ><a href="javascript:goPage(${page.totalPageSize })">尾页 </a></li>
						   </c:otherwise>
						</c:choose>
		        
					</ul>
				</div>
			<div class="pageDesc fl">
				<%-- <span>共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span> --%>
			</div>
	
</c:if>
<script type="text/javascript">	
var totalPageSize=${page.totalPageSize};
var currentPage = ${page.currentPage-1}<1?1:${page.currentPage};
var totalPage = ${page.totalPageSize};
showPageNumber();
</script>