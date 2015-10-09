<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
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
			alert("请选择课程");
			return;
		}
		qstChecked.each(function() {
			toParentsValue($(this).val());
		});
		opener.addnewArray(myArrayMoveStock);
		quxiao();
	}
	// 把选中试题一条记录放到数组中
	function toParentsValue(obj) {
		myArrayMoveStock.push(obj);
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/cou/select" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>课程管理</span> &gt; <span>课程列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="clearfix">
										<div class="optionList" style="margin: 10px 0 10px 2em;">
											<span> <font>课程id：</font>
											</span> <span> <input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
											</span>
										</div>
										<div class="optionList" style="margin: 10px 0 10px 2em;">
											<span> <font>课程名称：</font>
											</span> <span> <input type="text" name="queryCourse.name" value="${course.name}" id="name" />
											</span>
										</div>
										<div class="optionList" style="margin: 10px 0 10px 2em;">
											<input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()" />
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
								<%-- <div class="mt10 clearfix">
												<p class="fl czBtn">
													<span>
														<a href="${ctx}/admin/cou/toAddCourse" title="新建">
															<em class="icon14 new">&nbsp;</em>
															新建课程
														</a>
													</span>
												</p>
											</div> --%>
							</caption>
							<thead>
								<tr>
									<th width="6%"><span>Id</span></th>
									<th width="10%"><span>课程名称</span></th>
									<th width="10%"><span>价格</span></th>
									<th width="30%"><span>添加时间</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${courseList.size()>0}">
									<c:forEach items="${courseList}" var="cou">
										<tr id="rem${cou.id }">
											<td><input type="checkbox" id="${cou.id }" name="checkbox" value="${cou.id }" class="questionIds" /> ${cou.id}</td>
											<td>${cou.name }</td>
											<td>${cou.currentprice}</td>
											<td><fmt:formatDate type="both" value="${cou.addtime }" /></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${courseList.size()==0||courseList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有相关数据！</span>
											</div>
										</td>
									</tr>
								</c:if>
								<tr class="">
									<td colspan="9"><a href="javascript:selectQstList();" title="确定" class="btn smallbtn btn-y">确定</a> <a href="javascript:quxiao();" title="取消" class="btn smallbtn btn-y">取消</a></td>
								</tr>
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					</div>
				</div>
		</form>
</body>
</html>
