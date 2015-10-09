<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>老师列表</title>
<script type="text/javascript">
	function submitSearch() {
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	function quxiao() {
		window.close();
	}
	//存放数据的数组
	var myArrayMoveStock = new Array();
	//将小页面被选中的入库明细信息带到大页面
	function selectQstList() {
		if (initArray()) {
			//调用父页面的方法
			window.close();
		}
	}
	function initArray() {
		var qstChecked = $(".questionIds:checked");
		if (qstChecked.length == 0) {
			alert("请选择教师");
			return;
		}
		qstChecked.each(function() {
			toParentsValue($(this).val());
		});
		opener.addnewTeacherId(myArrayMoveStock);
		quxiao();
	}
	// 把选中试题一条记录放到数组中
	function toParentsValue(obj) {
		myArrayMoveStock.push(obj);
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/selectlist" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>讲师管理</span> &gt; <span>讲师列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
										<ul class="ddBar">
											<li><span class="ddTitle"> <font>讲师名称：</font>
											</span> <input type="text" name="teacher.name" value="" id="questionId" /> <%--  <span class="ddTitle"><font>试卷名称：</font></span>
											<input type="text" name="paperErrorCheck.content" value="${paperErrorCheck.content}" id="paperName" /> --%> <%--<span class="ddTitle"><font>试题内容：</font></span>
											<input type="text" name="paperErrorCheck.qstName" value="${paperErrorCheck.qstName}" id="qstName" /> --%> <input type="button" class="btn btn-danger" value="查询" name="" onclick="submitSearch()" /></li>
										</ul>

									</div>
									<div class="clearfix"></div>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="6%"><span>Id</span></th>
									<th width="10%"><span>讲师名称</span></th>
									<th width="10%"><span>讲师头衔</span></th>
									<th width="20%"><span>资历</span></th>
									<th width="30%"><span>讲师简介</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${teacherList.size()>0}">
									<c:forEach items="${teacherList}" var="tc">
										<tr id="rem${tc.id }">
											<td><input type="${teacher.checkboxFalg=='radio'?'radio':'checkbox' }" id="${tc.id }" name="checkbox" value="${tc.id }" class="questionIds" /> ${tc.id }</td>
											<td>${tc.name }</td>
											<td><c:if test="${tc.isStar==0 }">
								高级讲师
								</c:if> <c:if test="${tc.isStar==1 }">
								首席讲师
								</c:if></td>
											<td>${tc.education }</td>
											<td>${fn:substring(tc.career,0,30)}</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${teacherList.size()==0||teacherList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有讲师信息！</span>
											</div>
										</td>
									</tr>
								</c:if>
								<tr class="">
									<td colspan="9"><a href="javascript:selectQstList();" title="确定" class="btn smallbtn btn-y">确定</a> <a href="javascript:quxiao();" title="取消" class="btn smallbtn btn-y">取消</a></td>
								</tr>
							</tbody>
						</table>
						<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
						<!-- /pageBar end -->
					</div>
					<!-- /commonWrap -->
				</div>
			<!-- 内容 结束 -->
		</form>
</body>
</html>
