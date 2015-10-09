<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript" src="${ctximg}/static/common/page.js?v=<%=version%>"></script>
<%-- 页面数据必须放到from中，以下form的名称和id不能改动。
page.currentPage的ID不能改动
查询方法直接调用goPage(1);
<form action="/xx.do" name="searchForm" id="searchForm" method="post">
<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/> --%>
<c:if test="${page != null && page.totalPageSize>1}">

	<div class="u-pageBar tac clearfix">
		<span id="pager_con">
			<c:choose>
				<c:when test="${page.first}">
					<a href="javascript:void(0)" title="首页">
						<em class="left-go icon-2-16">&nbsp;</em>
					</a>
					<a href="javascript:void(0)" title="上一页" class="page-up b-fff">上一页</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:goPage(1)" title="首页">
						<em class="left-go icon-2-16">&nbsp;</em>
					</a>
					<a href="javascript:goPage(${page.currentPage-1})" title="上一页" class="page-up b-fff">上一页</a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page.last}">
					<a href="javascript:void(0)" title="下一页" class="page-down b-fff">下一页</a>
					<a href="javascript:void(0)" title="末页">
						<em class="right-go icon-2-16">&nbsp;</em>
					</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:goPage(${page.currentPage+1 })" title="下一页" class="page-down b-fff">下一页</a>
					<a href="javascript:goPage(${page.totalPageSize })" title="末页">
						<em class="right-go icon-2-16">&nbsp;</em>
					</a>
				</c:otherwise>
			</c:choose>
		</span>
		<span class="ml50">
			<tt class="c-666">第${page.currentPage}页/共${page.totalPageSize }页</tt>
		</span>
	</div>

</c:if>
<script type="text/javascript">	
var totalPageSize=${page.totalPageSize};
var currentPage = ${page.currentPage-1}<1?1:${page.currentPage};
var totalPage = ${page.totalPageSize};
showPageNumber();
</script>