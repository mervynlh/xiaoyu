<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户等级规则列表</title>

<script type="text/javascript">
	function checkExp(lv){//验证经验值
		var exp=$("#"+lv+"exp").val();//当前填写经验
		var previousExp=$("#"+(lv-1)+"exp").val();//上一级经验
		if(parseInt(exp)<=parseInt(previousExp)){
			$("#"+lv+"Error").html("*必须大于上一等级经验");
		}else{
			$("#"+lv+"Error").html("");
		}
	}
	function checkTitle(lv){//验证头衔
		var title=$("#"+lv+"Title").val();
		var length=title.replace(/[^\x00-\xff]/g,"zh").length;
		if(title==''&&lv<10){
			$("#"+lv+"TitleError").html("等级头衔不能为空");
		}else if(length>12){
			$("#"+lv+"TitleError").html("*不能超过12个字符");
		}else{
			$("#"+lv+"TitleError").html("");
		}
	}
	function userLevelSubmit(){
		var flag=false;
		var levelElements=document.getElementsByName("errorClass");//获得所有exp错误判断
		for(var i=0;i<levelElements.length;i++){
			if(levelElements[i].innerHTML!=""){
				flag=true;
				break;
			}
		}
		var titleElements=document.getElementsByName("titleErrorClass");//获得所有title错误判断
		for(var i=0;i<titleElements.length;i++){
			if(titleElements[i].innerHTML!=""){
				flag=true;
				break;
			}
		}
		if(flag){
			alert('请检查页面错误');
			return false;
			}
		else{
			$.ajax({
				url:"${ctx}/admin/user/levelupdate",
				data:$("#searchForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload(true);
					}else{
						alert(result.message);
					}
				}
			});
		}
	}
</script>
</head>
<body  >

<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>用户等级规则管理</span> &gt; <span>等级规则列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="?" name="searchForm" id="searchForm" method="post">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			
						<caption>
							<div class="capHead">
							<span style="color: red">说明：<br>
							1.用户等级规则数至少为9个，最大12个。即最少9个等级，最大12个等级</br>
							2.设置下一等级所需经验必须大于当前等级经验
							</span>
							</div>
						</caption> 
						<thead>
							<tr>
								<th><span>等級</span></th>
								<th align="left"><span>等級头衔(<font color="red">*最多12个字符，英文占一个，中文两个，不能写入符号</font>)</span></th>
								<th align="left"><span>所需经验(<font color="red">*正整数</font>)</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${userLevelist.size()>0}">
						<c:forEach  items="${userLevelist}" var="level" varStatus="index">
						<input type="hidden" name="userLevel[${index.index }].id" value="${level.id}"></input>
						<input type="hidden" name="userLevel[${index.index }].level" value="${level.level}"></input>
							<tr>
							<c:choose>
							<c:when test="${index.index==0}">
								<td><font color="red">LV${index.index+1}</font></td>
								<td align="left"><input name="userLevel[${index.index }].title" type="text" value="${level.title}"/></td>
								<td align="left"><input name="userLevel[${index.index }].exp" type="text" value="${level.exp}" readonly="readonly"/><span style="color:red">*用户初始经验为0，LV1经验不可编辑</span></td>
							</c:when>
							<c:otherwise>
								<td><font color="red">LV${index.index+1}</font></td>
								<td align="left"><input name="userLevel[${index.index }].title" type="text" value="${level.title}" onkeyup="checkTitle(${index.index+1})" id="${index.index+1}Title"/><span style="color:red" name="titleErrorClass" id="${index.index+1}TitleError"></span></td>
								<td align="left"><input name="userLevel[${index.index }].exp" type="text" value="${level.exp!=0?level.exp:''}" onkeyup="checkExp(${index.index+1})" id="${index.index+1}exp"/><span style="color:red" name="errorClass" id="${index.index+1}Error"></span></td>
							</c:otherwise>
							</c:choose>
							</tr>
							</c:forEach>
							<tr>
							<td  class="c_666 czBtn" align="center" colspan="3">
							    <input class="btn btn-danger" value="保存" type="button" onclick="userLevelSubmit()"/>
							</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					</form>
					<%-- <!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end --> --%>
				</div><!-- /commonWrap -->
			</div>
		
</body>
</html>
