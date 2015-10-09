<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>科目列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

<script type="text/javascript">

	function allCheck(th){
		$("input[name='ids']:checkbox").prop("checked",th.checked);
		
	}
	
	function addSubmit(){
		var subjectId=$("#subjectId").val();
		if(subjectId==""){
			alert("请选择专业");
			return;
		}
		var id=document.getElementsByName("ids");
		var ids='';
		var num=0;
		for(var i=0;i<id.length;i++){
			if(id[i].checked==true){
				num++;	
				ids+=id[i].value+",";
			}
		}
		if(num==0){
			alert("请选择至少1条科目");
			return;	
		}
		$.ajax({
			url:"${ctx}/admin/Subjectmajor/addlist",
			data:{"ids":ids,"subjectId":subjectId},
			dataType:"json",
			type:"post",
			success:function(result){
					if(result.success){
						alert("添加成功");
						window.close();
						window.opener.location.reload();
					} else if (result.message == 'subjectIdError') {
						alert("添加失败,请选择二级专业再添加科目");
					}
			},
			error:function(error){
					alert("添加失败");
					return;
			}
		});
	}
	
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
</script>
</head>
<body>
		<form action="${ctx}/admin/Subjectmajor/add/tz" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
<input type="hidden" id="subjectId" name="subjectMajor.subjectid" value="${subjectMajor.subjectid}"/>
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>选取科目</span> &gt; <span>添加专业科目列表</span>
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
														<span> <font>科目id：</font></span> 
														<span> 
															<input type="text" style="width:60px;" onkeyup="value=value.replace(/[^\d]/g,'')" name="major.id" value="${major.id}" id="id" />
														</span>
														
		                                              
                                           
													</div>

													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="goPage(1)" />
													</div>
													<div class="optionList">
														 <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
                                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
                                                </div>
                                            </li>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</caption>

										<thead>

											<tr>
												<th><span><input type="checkbox" onclick="allCheck(this)" />全选</span></th>
												<th><span>ID</span></th>
												<th><span>科目名称</span></th>
		
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">

											<c:if test="${maList.size()>0}">
												<c:forEach items="${maList}" var="maList">
													<tr>
														<td><input type="checkbox" name="ids" value="${maList.id}" /></td>
														<td>${maList.id}</td>
														<td>${maList.name }</td>
													</tr>
												</c:forEach>
											</c:if>
											<c:if test="${maList.size()==0||maList==null}">
												<tr>
													<td align="center" colspan="16">
														<div class="tips">
															<span>还没有科目数据！</span>
														</div>
													</td>
												</tr>
											</c:if>
											<tr>
												<td align="center" colspan="5"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">确定</a> <a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a></td>
											</tr>
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
