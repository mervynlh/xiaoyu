<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
//subject ztree start
var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'subjectId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'subjectName',
			title:'subjectName'
		}
	},
	callback: {
		onClick: subject_treeOnclick
	}
};
var subject_treedata=${subjectList};

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu();
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu();
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu() {
	var cityObj = $("#subjectNameBtn");
	var cityOffset = $("#subjectNameBtn").offset();
	$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideSubjectMenu() {
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu();
	}
}

$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
});


function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
	//删除课程
	function delCourse(id){
		if(confirm("是否删除?")){ 
			window.location.href="${ctx}/admin/cou/del/"+id;
		}
	}
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#id").val("");
		$("#name").val("");
		$("#subjectNameBtn").val("");
	}
	
	function submitCou(){
		var courseId=$("#courseId").val();
		var orderId='${param.orderDetailId}';
		var courseName=$("#courseName").val();
		if(courseId==null||courseId==""){
			alert("请选择课程");
			return false;
		}
		if(confirm("确定换课吗?")){
			$.ajax({
				  url:"${ctx}/admin/order/changgecourse",
				  data:{"trxorderDetail.courseId":courseId,"trxorderDetail.id":orderId,"trxorderDetail.courseName":courseName},
				  dataType:"json",
				  type:"post",
				  async:false,
				  success:function(result){
					  if(result.success){
						  alert(result.message);
						  window.opener.location.reload();
						  window.close();
					  }else{
						  alert(result.message);
						  return;
					  }
				  }
			});
		}
	}
	function checkCourse(id,name){
		if(id!=null){
			$("#courseId").val(id);
		}
		if(name!=null&&name!=""){
			$("#courseName").val(name);
		}
	}
	function closewin(){
		window.close();
	}
</script>
</head>
<body style="background: #F5F5F5;">
		<form action="${ctx}/admin/order/courselist?orderDetailId=${param.orderDetailId}" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
			<input type="hidden" id="courseId" name="courseId"/>
			<input type="hidden" id="courseName" name="courseName"/>
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>
						&nbsp;
						<span>课程管理</span>
						&gt;
						<span>课程列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span>
															<font>课程id：</font>
														</span>
														<span>
															<input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
														</span>
													</div>
													<div class="optionList">
														<span>
															<font>课程名称：</font>
														</span>
														<span>
															<input type="text" name="queryCourse.name" value="${course.name}" id="name" />
														</span>
													</div>
													<div class="optionList">
														<span><font>专业：</font></span>
														<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu()"/>
														<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
															<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
														</div>
													</div>
													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()" />
														<input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()" />
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="mt10 clearfix">
												<p class="fl czBtn">
													<span>
														<a href="${ctx}/admin/cou/toAddCourse" title="新建">
															<em class="icon14 new">&nbsp;</em>
															新建课程
														</a>
													</span>
												</p>
											</div>
										</caption>
										<thead>
											<tr>
												<th width="10%">
													<span>Id</span>
												</th>
												<th width="15%">
													<span>课程名称</span>
												</th>
												<th width="5%">
													<span>类型</span>
												</th>
												<th width="5%">
													<span>价格</span>
												</th>
												<th width="6%">
													<span>购买数</span>
												</th>
												<th width="6%">
													<span>浏览数</span>
												</th>
												<th width="25%">
													<span>添加时间</span>
												</th>
												
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">
											<c:if test="${courseList.size()>0}">
												<c:forEach items="${courseList}" var="cou">
													<tr id="rem${cou.id }">
														<td><input class="checkcou" type="radio" name="cou.id" value="${cou.id}" onclick="checkCourse('${cou.id}','${cou.name }')"/>
														${cou.id}</td>
														<td>${cou.name }</td>
														<td>
														<c:if test="${cou.sellType=='COURSE'}">课程</c:if>
														<c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
														</td>
														<td>${cou.currentprice}</td>
														<td>${cou.buycount}</td>
														<td>${cou.viewcount}</td>
														<td>
															<fmt:formatDate type="both" value="${cou.addtime }" />
														</td>
													</tr>
												</c:forEach>
												<tr ><td colspan="7"><input class="btn btn-y ml10" type="button" onclick="submitCou()" value="提交" />
												<input class="btn btn-y ml10" type="button" onclick="closewin()" value="关闭" />
												</td></tr>
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
										</tbody>
									</table>
									<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
								</div>
							</div>
						</table>
					</div>
				</div>
		</form>
</body>
</html>
