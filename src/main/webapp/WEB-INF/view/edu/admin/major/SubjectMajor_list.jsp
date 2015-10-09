<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>专业科目列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">	

function showNewwin(){
	
	window.open('${ctx}/admin/Subjectmajor/add/tz','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=500,left=500,width=800,height=600');
	
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
		if($("#subjectId").val()=='' || $("#subjectId").val()==0){
			return;
		}
		majorListBySubject($("#subjectId").val());
	}
	
	function majorListBySubject(subjectId){
		$.ajax({
			url:"${ctx}/admin/Subjectmajor/majorlist/"+subjectId,
			data:{},
			dataType:"json",
			type:"post",
			success:function(result){
				if(result.success){
					if(result.entity!=null|| result.entity.size()>0){
						$("#majorName").html("<option value='0'>请选择</option>"); 
						$.each(result.entity, function(key, data){
							 $("#majorName").append("<option value=" + data.majorid + " id='opt_"+data.majorid+"'>" + data.majorName + "</option>");
							 if($("#majorId").val() == data.majorid){
								 $("#opt_"+data.majorid).attr("selected","selected");
							 }
						});
					}
				}
			}
		});
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
	//清空
	function submitclear(){
		$("#subjectId").val(0);
		$("#subjectNameBtn").val("");
		$("#majorName").val(0);
	
	}
	function addSubjectMajorReload(){
		window.location.reload();
	}
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
	function deleteSubject(){
		var subjectId=$("#subjectId").val();
		var id=document.getElementsByName("ids");
		var ids="";
		var num=0;
		for(var i=0;i<id.length;i++){
			if(id[i].checked==true){
				num++;
				ids+=id[i].value+",";
			}
			
		}
		if(num==0){
			alert("请选择至少1个科目");
			return;
		}
		if(confirm("确定删除么?")){
			$.ajax({
				url:"${ctx}/admin/Subjectmajor/delete",
				data:{"ids":ids,"subjectId":subjectId},
				dataType:"json",
				type:"post",
				success:function(result){
						if(result.success){
							alert("删除成功!");
							window.location.reload();
							$("input[name='ids']:checkbox").prop("checked",false);
							$("input[name='qid']:checkbox").prop("checked",false);
						}
					
				},
				error:function(error){
						alert("删除失败!");
						return;
				}
			});
			
			
		}
	}		
		function deleteSubjectmajorByid(id){
			if(confirm("确认删除吗!")){
			$.ajax({
				url : "${ctx}/admin/Subjectmajor/delete",
				data : {
					"ids" : id,
				},
				type : "post",
				dataType : "json",
				success : function(result) {
					if (result.success) {
						alert("删除成功!");
					window.location.reload();
					}else {
						alert("操作失败!");
						
					}
				}
		
			});

			}
	}
		
	function submitindex(){
		
		$("#searchForm").submit();
		
	}
	
$(function(){
	var subjectId = $("#subjectId").val();
	majorListBySubject(subjectId);
})

</script>
</head>
<body>
<form action="${ctx}/admin/SubjectMajor/page" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<input type="hidden" id="subjectId" name="subjectMajor.subjectid" value="${subjectMajor.subjectid}"/>
<input type="hidden" id="majorId" value="${subjectMajor.majorid }"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>专业科目管理</span> &gt; <span>专业科目列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
                                 <div class="capHead">

                                        <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:200px;" onclick="showSubjectMenu()"/>
                                                <div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
                                                    <ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:200px;"></ul>
                                                </div>
                                        </li>
                                            

                                
                                    <div   align="center">
                                    
                                       <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitindex()">
                                       <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                   </div>
                                   
                                  
                                                <span class="ddTitle"><font>科目：</font></span>
                                                  <select name="subjectMajor.majorid" id="majorName" >
                                                  	<option value="0">请选择</option>                            
												</select>
                                              		
                                                </div>
                                      
                             
                               </div>
                                 <div class="mt10 clearfix">
                                    <p class="fl czBtn">
                                        <span>
                                           <a href="javascript:showNewwin()" title="新建首页课程推荐" class="ml10 btn smallbtn btn-y">新建专业科目</a>
                                        	 <a href="javascript:deleteSubject()" title="新建首页课程推荐" class="ml10 btn smallbtn btn-y">删除</a>       
                                            </a>
                                        </span>
                                    </p>
                                </div>
                                
                               </form>	
                           </caption>
		<thead>
			<tr>
				<th><span><input name="qid"  type="checkbox" onclick="allCheck(this)" />全选</span></th>							
				<th><span>ID</span></th>
				<th><span>专业名称	</span></th>
				<th><span>科目名称</span></th>
				<th><span>操作</span></th>
				
			</tr>
		</thead>
		<tbody id="tabS_02" align="center">
	
		<c:if test="${subjectMajorlist!=null && subjectMajorlist.size()>0}">
		<c:forEach  items="${subjectMajorlist}" var="subjectMajorlist" varStatus="index" >	
	<tr>
		<td><input type="checkbox" name="ids" value="${subjectMajorlist.id}"  /></td>
		<td>${subjectMajorlist.id}</td>
		<td>${subjectMajorlist.subjectName }</td>
		<td>${subjectMajorlist.majorName }</td>
		<td><a href="javascript:deleteSubjectmajorByid(${subjectMajorlist.id})" title="新建首页课程推荐" class="ml10 btn smallbtn btn-y">删除</a></td>      
	</tr>
	</c:forEach>
	</c:if>
	
		
		<c:if test="${subjectMajorlist==null||subjectMajorlist.size()==0}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有专业类型！</span>
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
