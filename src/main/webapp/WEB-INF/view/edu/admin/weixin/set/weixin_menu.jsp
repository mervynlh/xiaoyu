<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
function doUpdate(countStr,parentIdStr){
	var id=0;
	if($("#id"+countStr).val()>0){
		id=$("#id"+countStr).val();
	}
	var soft=$("#soft"+countStr).val();
	var parentId=0;
	if(parentIdStr!=0){
		if($("#id"+parentIdStr+1).val()>0){
			parentId=$("#id"+parentIdStr+1).val();
		}else{
			alert("请设置一级菜单项");
			return; 
		}
		
	}
	var menuName=$("#menuName"+countStr).val();
	var keywordUrl=$("#keywordUrl"+countStr).val();
	var status=0;
	
	if($("#status"+countStr).prop('checked')){
		status=1;
	}
	if(menuName==null||menuName==""){
		alert("请填写菜单名称");
		return; 
	}
	if(keywordUrl==""&&parentId!=0){
		alert("请填写关键字或跳转链接");
		return; 
	}
	$.ajax({
		url:"${ctx}/admin/menu/update",
		data:{"id":id,"soft":soft,"parentId":parentId,"menuName":menuName,"keywordUrl":keywordUrl,"status":status},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				alert("修改成功");
				window.location.reload();
			}
		}
	});
}
function delMenu(id){
	$.ajax({
		url:"${ctx}/admin/menu/del/"+id,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				alert("删除成功");
				window.location.reload();
			}
		}
	});
}
function delOnlineMenu(){
	$.ajax({
		url:"${ctx}/web/onmenu/del",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				alert("删除线上菜单成功");
			}
		}
	});
}
function createOnlineMenu(){
	$.ajax({
		url:"${ctx}/web/onmenu/create",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				alert("创建线上菜单成功");
			}
		}
	});
}
</script>
</head>
<body>
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>微信菜单</span>
		</h4>
	</div>
	<div class="mt20">
		<div class="commonWrap">
			<input type="hidden" value="${weixinSetReply.id}" id="setId"/>
			<input type="hidden" id="replyIdHidden" value="${weixinSetReply.replyId}"/>
			<c:if test="${type=='default'}"><input type="hidden" name="weixinSetReply.type" value="default"/></c:if>
			<c:if test="${type=='add'}"><input type="hidden" name="weixinSetReply.type" value="add"/></c:if>
			<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
				<caption>
					<div class="capHead">
						<div class="clearfix">
							<div class="optionList">
								<font color="red">1.目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字。
								<br>
								2.自定义菜单中添加的关键字或url，一定要选择填写正确的关键词或url，否则无法正确触发。
								<br>
								3.设置了二级菜单，一级菜单的关键词或url将失效，点击只会触发显示二级菜单。
								<br>
								4.创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。 </frot>
							</div>
						</div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn">
							<span><a title="生成线上菜单" href="javascript:createOnlineMenu()"><em class="icon14 new"> </em>生成线上菜单</a></span>
							<span><a title="删除线上菜单" href="javascript:delOnlineMenu()"><em class="icon14 delete"> </em>删除线上菜单</a></span>
						</p>
						<p class="fr c_666"></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>显示顺序</span></th>
						<th><span>主菜单名称</span></th>
						<th><span>回复关键词或跳转网址</span></th>
						<th><span>启用</span></th>
						<th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_web" align="center">
					<c:forEach items="${weixinMenus}" var="menu" varStatus="status">
						<c:forEach items="${menu}" var="menu1" varStatus="status1">
							<input type="hidden" value="${menu1.id}" id="id${status.count}${status1.count}"/>
							<tr>
								<td>
									<c:if test="${status1.count!=1}">
										<span style="color:#bcbaba">&nbsp;&nbsp;|———</span>
									</c:if>
									<input type="text" value="${menu1.soft}" id="soft${status.count}${status1.count}" style="width:24px;"/>
									
								</td>
								<td>
									<c:if test="${status1.count!=1}">
										<span style="color:#bcbaba">&nbsp;&nbsp;|—————</span>
									</c:if>
									<input type="text" value="${menu1.menuName}" id="menuName${status.count}${status1.count}" style="width:100px;"/>
								</td>
								<td><input type="text" value="${menu1.keywordUrl}" id="keywordUrl${status.count}${status1.count}" style="width:400px;"/></td>
								<td><input type="checkbox" <c:if test='${menu1.status==1}'>checked="true"</c:if> id="status${status.count}${status1.count}"/></td>
								<td>
									<c:if test="${status1.count==1}">
										<a class="ml10 btn smallbtn btn-y" href="javascript:doUpdate(${status.count}${status1.count},0)">修改</a>
									</c:if>
									<c:if test="${status1.count!=1}">
										<a class="ml10 btn smallbtn btn-y" href="javascript:doUpdate(${status.count}${status1.count},${status.count})">修改</a>
									</c:if>
									
									<c:if test="${menu1.id!=0}">
										<a class="ml10 btn smallbtn btn-y" href="javascript:delMenu(${menu1.id})">删除</a>
							   		</c:if>
								    <c:if test="${menu1.id==0}">
								    	<span class="ml10 btn smallbtn" style="color:#666666">删除</span>
								    </c:if>
								</td>
							</tr>
								
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
			
			</div>
		</div>
</body>
</html>