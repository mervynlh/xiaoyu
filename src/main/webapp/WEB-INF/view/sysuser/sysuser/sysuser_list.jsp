<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>用户列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
var setting = {
		/* check: {
			enable: true,
			chkboxType : { "Y" : "s", "N" : "s" }
		}, */
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'groupId',
				pIdKey:'parentGroupId',
				rootPid:''
			},
			key:{
				name:'groupName',
				title:'groupName'
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};
	
	var treedata=${groupList};
	
	function treeOnclick(e,treeId, treeNode) {
		$("#userGroupId").val(treeNode.groupId);
		$("#userGroupName").val(treeNode.groupName);
		$("#distree").hide();
	}
	
	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		if($("#userGroupId").val()!="" && $("#userGroupId").val()!=0){
			var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
			var treeNode=zTree.getNodeByParam("groupId",$("#userGroupId").val(),null);
			if(treeNode!=null){
				$("#userGroupName").val(treeNode.groupName);
			}
		}
		//$("#userGroupId").val();
	});
	
	function showZtree(){
		$("#distree").show();
	}
	function closetree(){
		$("#distree").hide();
	}
		 //冻结、解冻
		 function freeze(userId,obj){
		 var id= "#freeze"+userId;
		 var tdid="#tdstatus"+userId;
		 		var ss = $(id).html();		 		
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 			tt="冻结";
		 		}else{
		 			vv = '冻结';
		 			tt="正常";
		 			ss='解冻';
		 		}
		 		if(confirm("确定"+ss+"该用户吗?")){
		 			  $.ajax({
		                    url:"${ctx}/admin/user/freezeUser",
		                    data:"user.userId="+userId,
		                    dataType:"json",
		                    success:function(msg){
		                    	if(msg.result=='success'){
		                    		 $(id).html(vv);
		                             $(tdid).html(tt); 
		                    	}else{
		                    		alert(msg.result);
		                    	}
		                    }
		                });
		 		}
            }
			//搜索
			function search(){
				$("#pageCurrentPage").val(1);
				$("#searchForm").submit();
			}
			function delUser(userId){
				if(confirm("确定删除该用户吗?")){
					window.location.href="${ctx}/admin/user/deleteUser?user.userId="+userId;
				}
			}
			function submitclear(){
				$("#userGroupId").val("");
				$("#userGroupName").val("");
				$("#searchKey").val("");
			}
			//清空专业的查询条件时同时清除考点
			function subject_cleantreevalue(){
				hideSubjectMenu();
				$("#subjectId").val(0);
				$("#userGroupName").val("");
			}
			function showSubjectMenu() {
				var cityObj = $("#userGroupName");
				var cityOffset = $("#userGroupName").offset();
				$("#distree").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("body").bind("mousedown", onBodyDown);
			}
			function hideSubjectMenu() {
				$("#distree").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if (!(event.target.id == "userGroupName" || event.target.id == "distree" || $(event.target).parents("#distree").length>0)) {
					hideSubjectMenu();
				}
			}
    </script>
    </head>
<body >

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户列表</span> </h4>
	</div>
<div class="mt20">
<form action="${ctx}/admin/user/listAllUser" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input type="hidden" name="queryUserCondition.groupId" id="userGroupId" value="${queryUserCondition.groupId}"/>
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
		
			<caption>
			<!-- <div class="capHead"> </div> -->
				<div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>按户名/姓名检索：</font></span><input type="text" name="queryUserCondition.searchKey" id="searchKey" value="${queryUserCondition.searchKey}"/></li>
										</ul>
                                    </div>
			
			     <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>按部门检索：</font></span>
                                                <input type="text"  id="userGroupName" value="" readonly="readonly" onclick="showSubjectMenu()" />
                                                  <div id="distree"  style="display: none;position: absolute;" class="menuContent">
												<!-- <a class="btn smallbtn btn-y" onclick="closetree()">关闭</a> -->
												<ul id="ztreedemo" class="ztree"></ul>
												</div>
                                            </li>
                                        </ul>
                                    </div>
                                     <div   align="center">
                                     <input type="button" value="查询" class="btn btn-danger ml10" onclick="goPage(1)"/>
									<input type="button" value="添加新用户" class="btn btn-danger ml10" onclick="window.location.href='${ctx}/admin/user/toAddUser'"/>
                                     <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>
		</caption>
			<thead>
				<tr>
					<th><span>用户名</span></th>
					<th><span>真实姓名</span></th>
					<th><span>部门</span></th>
					<th><span>状态</span></th>
					<th><span>联系电话</span></th>
					<th><span>创建时间</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_01">
			<c:forEach items="${userList }" var="user">
	                <tr   bgcolor="#FFFFFF" align="center">
		                  <td class="yx2"><c:out value="${user.loginName }" /></td>
		                  <td class="yx2"><c:out value="${user.userName }" /></td>
	                 	  <td class="yx2">${user.groupName}</td>
		                  <td class="yx2" id="tdstatus${user.userId}">
		                  <c:if test="${user.status==0 }">正常</c:if>
		                  <c:if test="${user.status==1 }">冻结</c:if>
						  </td>
		                  <td class="yx2"><c:out value="${user.tel }" /></td>
		                  <td class="yx2"><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                   
		                  <td class="yx2 lineheight180">
								<a	class="btn smallbtn btn-y" href='${ctx}/admin/user/toEditUserGradeSubjectRoleSec?user.userId=<c:out value="${user.userId }"/>'>用户角色修改</a>
								<a	class="btn smallbtn btn-y" href='${ctx}/admin/user/toEditUser?user.userId=<c:out value="${user.userId }"/>'>修改</a>
								<c:if test="${user.status==0 }"><a class="btn smallbtn btn-y" id="freeze${user.userId}" href='#' onclick="freeze(${user.userId},this);">冻结</a>	</c:if>
								<c:if test="${user.status==1 }"><a class="btn smallbtn btn-y" href='#' id="freeze${user.userId}" onclick="freeze(${user.userId},this);">解冻</a>	</c:if>
								<a class="btn smallbtn btn-y"  href='javascript:void(0)' onclick="delUser(${user.userId })">删除</a>
								
								<a  class="btn smallbtn btn-y" href='${ctx}/admin/user/toUpdateUserPwd?user.userId=<c:out value="${user.userId }"/>'>修改密码</a>
								<a  class="btn smallbtn btn-y" href='${ctx}/admin/sys/loginlog/${user.userId }'>查询登陆日志</a>
								<!--
								<a href="loginLog!getByUserId?queryLoginLogCondition.currentPage=1&queryLoginLogCondition.userId=<c:out value="userId"/>">查看登录日志</a>
								-->
						</td>
		            </tr>
              </c:forEach>
			</tbody>
		</table>
		<!-- /pageBar begin -->
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
	</form>
</div>

</body>
</html>
