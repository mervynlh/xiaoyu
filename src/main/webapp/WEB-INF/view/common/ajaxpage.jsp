<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/base.jsp" %>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPageAjax(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalResultSize>0}">
				<div class="pagination pagination-large clearbottom">
					<ul>
						<!-- 首页 -->
						<c:choose>
						   <c:when test="${page.first}">
							  <li class="disabled"><a href="#">首页</a></li>
						   </c:when>
						   <c:otherwise>
						  <li><a href="javascript:goPageAjax(1);">首页</a></li>
						   </c:otherwise>
						</c:choose>
						<!-- 上一页 -->
						<c:choose>
						   <c:when test="${page.first}">
							   <li id="backpage" class="disabled"><a href="javascript:void(0)">←上一页</a></li>
						   </c:when>
						   <c:otherwise>
						  <li id="backpage"><a href="javascript:goPageAjax(${page.currentPage-1 });">←上一页</a></li>
						   </c:otherwise>
						</c:choose>
						<!-- 中间页码部分 -->
						<c:forEach items="${page.pageNums}" var="pageNums">
							<c:choose>
								<c:when test="${pageNums==page.currentPage}">
							   		<li class="active"><a href="javascript:void(0)">${pageNums}</a></li>
						  	 	</c:when>
							   <c:otherwise>
							  		<li ><a href="javascript:goPageAjax(${pageNums})">${pageNums}</a></li>
							   </c:otherwise>
							</c:choose>
						</c:forEach>
						<!--下一页 -->
						<c:choose>
						   <c:when test="${page.last}">
							 <li id="nextpage" class="disabled"><a href="javascript:void(0)">下一页→</a></li>
						   </c:when>
						   <c:otherwise>
						 <li id="nextpage" ><a href="javascript:goPageAjax(${page.currentPage+1 })">下一页→</a></li>
						   </c:otherwise>
						</c:choose>
						<!-- 尾页-->
						<c:choose>
						   <c:when test="${page.last}">
							 <li class="disabled"><a href="javascript:void(0)">尾页 </a></li>
						   </c:when>
						   <c:otherwise>
						<li ><a href="javascript:goPageAjax(${page.totalPageSize })">尾页 </a></li>
						   </c:otherwise>
						</c:choose>
		        
					</ul>
				</div>
</c:if>
