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


$(function(){
	var userId = ${userId};
	$("#userId").val(userId);
})

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
	
	//赠送课程
	function toFreeCourse(courseId){
		var userId = $("#userId").val();
		if(confirm("确定赠送课程吗?")){
			$.ajax({
				url:"${ctx}/admin/user/freeCourse",
				data:{
				"userId":userId,
				"courseId":courseId
				},
				dataType:"json",
				success:function (result){
					if(result.message=="赠送成功"){
						alert("赠送成功！");
					}else if(result.message=="overdue"){
						alert("该课程已过期！");
					}
				},
				error:function (error){
					alert("服务器繁忙，请稍后再试");
				}
			})
		}
	}
	

	
</script>
</head>
<body>
			<!-- 内容 开始  -->
            <div class="page_head">
                <h4><em class="icon14 i_01"></em>&nbsp;<span>课程管理</span> &gt; <span>课程列表</span> </h4>
            </div>
            <!-- /tab1 begin-->
            <div class="mt20">
                <div class="commonWrap">
                        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                <form action="${ctx}/admin/user/courseList" name="searchForm" id="searchForm" method="post">
                                    <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
                                    <input type="hidden" id="subjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId }"/>
                            			<input type="hidden" id="userId" name="queryCourse.userId" value="${userId}"  />        
                                    <div class="capHead">
                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>课程id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${queryCourse.id}" id="id" /></li>
                                            <li><span class="ddTitle"><font>课程名称：</font></span><input type="text" name="queryCourse.name" value="${queryCourse.name}" id="name" /></li>											
										</ul>
                                    </div>
                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu()"/>
                                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="optionList" align="center">
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                </form>
                            </caption>

                            <thead>
                                <tr>
                                    <th width="8%">
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
                                    <th width="5%">
                                        <span>购买数</span>
                                    </th>
                                    <th width="20%">
                                        <span>添加时间</span>
                                    </th>
                                    <th width="13%">
                                        <span>操作</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                                <c:if test="${courseList.size()>0}">
                                    <c:forEach items="${courseList}" var="cou">
                                        <tr id="rem${cou.id }">
                                            <td>${cou.id}</td>
                                            <td>${cou.name }</td>
                                            <td>
                                            <c:if test="${cou.sellType=='COURSE'}">课程</c:if>
                                            <c:if test="${cou.sellType=='PACKAGE'}">套餐</c:if>
                                            <c:if test="${cou.sellType=='LIVE'}">直播</c:if>
                                            </td>
                                            <td>${cou.currentprice}</td>
                                            <td>${cou.buycount}</td>
                                            <td>
                                                <fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </td>
                       						<td  class="c_666 czBtn" align="center">
                                                <a class="ml10 btn smallbtn btn-y" href="javascript:toFreeCourse(${cou.id})">赠送课程</a>
                                            </td>
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
                            </tbody>
                        </table>
                        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                </div>
            </div>
</body>
</html>
