<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>角色列表</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js?v=${v}"></script>

<script type="text/javascript">
		
	//ztree start
	var setting = {
		check: {
			enable: true,
			chkboxType : {"Y" : "ps", "N" : "ps"}
		},
		view:{
			showLine: true,
			showIcon: true,
			selectedMulti: false,
			fontCss: getFont
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'functionId',
				pIdKey:'parentFunctionId',
				rootPid:''
			},
			key:{
				name:'functionName',
				title:'functionName'
			}
		},
		callback: {
			onCheck: treeonCheck
		}
	};
	var treedata=${functionList};

	//功能权限红色显示1
	function getFont(treeId, node) {
		return node.functionTypeId==1 ?  {'color':'red'}:{};
	}
	
	function treeonCheck(e,treeId, treeNode) {
		
	}
	
	//取消全部选中
	function checkNodeFalse() {
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);
	}
	
	$().ready(function() {
		$.fn.zTree.init($("#ztreedemo"), setting, treedata);
		$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNodeFalse);
	});
	//ztree end
		
	var old_rId;//记录上一次切换的下拉值。
	$().ready(function() {
		old_rId="<c:out value='${role.roleId}'/>";
		$("#roleId").val(old_rId);
		//默认显示权限选中
		checkFunctionSelect(${myfunctionList});
		//cancelAddRole();
	});
	
	//将权限选中
	function checkFunctionSelect(functionList){
		var zTree = $.fn.zTree.getZTreeObj("ztreedemo");
		zTree.checkAllNodes(false);//全部取消
		$.each(functionList,function(index,val){  
			var treeNode = zTree.getNodeByParam("functionId", val.functionId, null);
			zTree.checkNode(treeNode, true, false, false)
	  	});
	} 
    
      //切换下拉效果
      function onchangeRole(rId){
      	if(old_rId==rId){
      		return ;
      	}
      	old_rId=rId;
      	$("#roleId").val(rId);
      	$.ajax({
			url:"${ctx}/admin/role/getJsonRoleById",   
			data : {"role.roleId": rId},  // 参数  
			type : "post",  
			cache : false,  
			dataType : "json",  //返回json数据 
			error: function(){ 
				alert('error');      
			}, 
			success:function(result){
				var role=result.entity;
				if(role ==null) return;
				var functionList=role.functionList;
				checkFunctionSelect(functionList);//切换选中的权限
			}
		}); 
     	}
     
     	function updateRole(){
     		if ($("#roleId").val()==-1){
     			alert("请选择角色");
     			return false;
     		}
     		var zTree = $.fn.zTree.getZTreeObj("ztreedemo"),
			checkCount = zTree.getCheckedNodes(true).length;
	    	if(checkCount>0){
	    		var checkedNodes=zTree.getCheckedNodes(true);
	    		var arrayObj = new Array();
			  	$.each(checkedNodes,function(index,val){  
			  		arrayObj.push(val.functionId);  
		        }); 
	    		$("#selectedFunctionIds").val(arrayObj.join(","));
		    }
		    return true;
     	}
     	
     	
     	 function addRole() {
             $("#overlay").width($(document).width());
             $("#overlay").height($(document).height());
             $("#overlay").show();
             $("#addRole").show();
         }
         
    	function cancelAddRole() {
    		$("#addRoleForm_addRole_roleName").val("");	
            $("#overlay").hide();
            $("#addRole").hide();
    	}
    	
    	function cancelUpdateRoleName() {
    		$("#updateRoleNameForm_roleName").val("");	
            $("#overlay").hide();
            $("#updateRoleName").hide();
    	}
    	
    	
        function delRole() {
         	if ($("#roleId").val()==-1){
        			alert("请选择角色");
        			return false;
        		}
             if (confirm("确定删除角色 "+$("#roleId").find("option:selected").text()+" ？")) {
                 document.location.href = "${ctx}/admin/role/delRole?role.roleId="+$("#roleId").val();
             } else {
                 return false;
             }   
             return true;
         }
        //修改角色名称
        function changeName() {
         	if ($("#roleId").val()==-1 || $("#roleId").val()==0){
        			alert("请选择角色");
        			return false;
        	}
         	$("#updateRoleId").val($("#roleId").val());
         	 $("#overlay").width($(document).width());
             $("#overlay").height($(document).height());
             $("#overlay").show();
             $("#updateRoleName").show();
             $("#updateRoleNameForm_roleName").val($("#roleId").find("option:selected").text());	
         }
        
        function checkUpdateForm(){
        	if( $.trim($("#updateRoleNameForm_roleName").val())==""){
        		alert("请输入角色名称");
        		return false;
        	}
        	return true;
        }
        
</script>
  	
 <!--  添加角色 弹窗  -->   	
 <style type="text/css" >
		#overlay {
			background: #303030;
			opacity: 0.50;
			filter: Alpha(opacity =   50);
			display: none;
			position: absolute;
			top: 0px;
			left: 0px;
			z-index: 100;
		}
		
		#addRole {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
		
		#updateRoleName {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
		
	</style>
	    
</head>
<body >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>系统管理</span> &gt; <span>角色管理 </span> </h4>
</div>
<div class="mt20">
	<form action="${ctx}/admin/role/updateRoleFunction" method="post" id="updateRoleFunctionForm" onsubmit="return updateRole();">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th colspan="2" align="left"><span>角色管理 <tt class="c_666 ml20 fsize12"></tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="20%" align="center"> 
						<select name="role.roleId" id="roleId" onclick="onchangeRole(this.value);">
							<c:forEach items="${roleList}" var="roleList">
								<option value="${roleList.roleId }">${roleList.roleName}</option>
							</c:forEach>
						</select>
					</td>
					
					<td width="80%">
						<input type="hidden" value="" name="selectedFunctionIds" id="selectedFunctionIds"/>
						<div id="ztreedemo" class="ztree"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="修改角色名称" onclick="changeName()" class="btn btn-danger"/>
						<input type="submit" value="保存" class="btn btn-danger"/>
						<input type="button" value="添加角色" onclick="addRole();" class="btn btn-danger"/>
						<input type="button" value="删除选中角色" onclick="delRole();" class="btn btn-danger"/>
						<font color="red">(*红字为功能权限，其他为菜单权限)</font>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<!-- 添加角色 -->
<div id="addRole" class="addRole" style="display: none">
	<form action="${ctx}/admin/role/addRole" method="post" id="addRoleForm">
		<table border="0">
		<tbody>
			<tr>
				<td>
					新角色名称：
				</td>
				<td>
				<input name="role.roleName" id="addRoleForm_addRole_roleName" />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="确定" class="btn btn-danger"/>
					<input type="button" onclick="cancelAddRole();" value="取消" class="btn btn-danger"/>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>

<!-- 修改角色名称 -->
<div id="updateRoleName" class="addRole" style="display: none">
	<form action="${ctx}/admin/role/updateRoleName" method="post" id="updateRoleNameForm" onsubmit="return checkUpdateForm();">
		<table border="0">
		<tbody>
			<tr>
				<td>
					角色名称：
				</td>
				<td>
				<input name="updateRoleName" id="updateRoleNameForm_roleName" />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="确定" class="btn btn-danger"/>
					<input type="hidden" id="updateRoleId"  name="updateRoleId" />
					<input type="button" onclick="cancelUpdateRoleName();" value="取消" class="btn btn-danger"/>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<div>
   <div id="overlay" class="overlay" style="display: none"/>
</div>
<!-- 添加角色 -->
</body>
</html>
