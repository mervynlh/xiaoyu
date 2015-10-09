<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>约课列表</title>
<script type="text/javascript">
	// 提交
	function submitSearch(){
		$("#searchForm").submit();
	}

	function clean(){//清空
		$("#name").val("");
		$("#selectOption").val(2);
	}
	function updateStatus(id){
		$.ajax({
			url:baselocation+"${ctx }/admin/audition/updateStatus/"+id+"/1",
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					alert("标记成功");
					submitSearch();
				}
			}
		});
	}
</script>
</head>
<body  >
<form action="${ctx}/admin/audition/getAuditionListWWZD" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>约课管理</span> &gt; <span>为我制定</span> </h4>
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="fl">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>学生姓名：</font></span>
											<input type="text" name="audition.studentName" value="${audition.studentName}" id="name" />
											<span class="ddTitle"><font>约课状态：</font></span>
											<select name="audition.status" id="selectOption">
												<option value="2">--请选择--</option>
												<option value="0" <c:if test="${audition.status==0 }">selected</c:if>>未处理</option>
												<option value="1" <c:if test="${audition.status==1 }">selected</c:if>>已处理</option>
											</select>
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
										</li>
									</ul>
									
								</div>
								<div class="clearfix"></div>
							</div>
						</caption>
						<thead>
							<tr>
								<!-- <th width="5%"><span>&nbsp;ID</span></th> -->
								<th width="8%"><span>ID</span></th>
								<th width="8%"><span>学生姓名</span></th>
								<th><span>学生手机</span></th>
								<th><span>阶段</span></th>
								<th><span>年级</span></th>
								<th><span>科目</span></th>
								<th><span>状态</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${not empty auditionList.size() }">
						<c:forEach  items="${auditionList}" var="audition" >
							<tr>
								<td>${audition.id }</td>
								<td>${audition.studentName }</td>
								<td>${audition.studentMobile }</td>

								<td>${audition.subjectName }</td>
								<td>${audition.gradeName }</td>
								<td>${audition.majorName }</td>
								<td>
									<c:if test="${audition.status==0}">未处理</c:if>
									<c:if test="${audition.status==1}">已处理</c:if>
								</td>
								<td>
									<c:if test="${audition.status==0}">
										<a class="ml10 btn smallbtn btn-y" href="javascript:updateStatus(${audition.id })">标记为处理</a>
									</c:if>
									<c:if test="${audition.status==1}">
										<a class="ml10 btn smallbtn btn-y" href="javascript:void(0)">已处理</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty auditionList }">
						<tr>
							<td align="center" colspan="16">
								<div class="tips">
								<span>还没有约课信息！</span>
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
