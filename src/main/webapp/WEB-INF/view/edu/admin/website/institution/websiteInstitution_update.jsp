<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
	function updateSubmit(){
		
		if(isNaN($("#sort").val())){
			alert("排序值只能为数字");
			return;
		}
		$("#updateWebsiteInstitutionForm").submit();
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐机构管理</span> &gt; <span>推荐机构修改</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/website/updateWebsiteInstitution" method="post" id="updateWebsiteInstitutionForm">
		<input type="hidden" name="websiteInstitution.id" value="${websiteInstitutionDTO.id}"/>
		<table width="100%" cellspacing="0" cellpupdateing="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>推荐机构基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;机构名称 </td>
					<td>
						${websiteInstitutionDTO.name }
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;排序值</td>
					<td>
						<input type="text" name="websiteInstitution.sort" value="${websiteInstitutionDTO.sort }" id="sort" value="0" class="{required:true}"/>
						<font color="red">倒序</font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>