<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
	function addSubmit(){
		if($("#navigateName").val()==null||$("#navigateName").val()==""){
			alert("名称不能为空");
			return;
		}
		if($("#navigateUrl").val()==null||$("#navigateUrl").val()==""){
			alert("跳转链接不能为空");
			return;
		}
		if(isNaN($("#orderNum").val())){
			alert("排序只能为数字");
			return;
		}
		$("#addNavigateForm").submit();
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>导航管理</span> &gt; <span>新建导航</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/website/addNavigate" method="post" id="addNavigateForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>导航基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;导航名称 </td>
					<td>
						<input type="text" name="websiteNavigate.name" id="navigateName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;跳转链接</td>
					<td>
						<input type="text"  name="websiteNavigate.url" id="navigateUrl" class="{required:true,number:true,min:0,max:1000}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;在新页面打开</td>
					<td>
						<select name="websiteNavigate.newPage">
							<option value="1">否</option>
							<option value="0">是</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;类型</td>
					<td>
						<select name="websiteNavigate.type">
							<option value="INDEX">首页</option>
							<!-- <option value="USER">个人中心</option>
							<option value="SNS">社区</option> -->
							<option value="FRIENDLINK">尾部友链</option>
							<option value="TAB">尾部标签</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;排序（由大到小显示）</td>
					<td>
						<input type="text" name="websiteNavigate.orderNum" class="{required:true,number:true}" value="0" id="orderNum"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>