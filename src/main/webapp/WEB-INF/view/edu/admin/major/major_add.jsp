<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建科目</title>
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">

function mysubmit(){
	
	$("#addMajorForm").submit();
	
}

</script>
<style>
.tdLabel{align="center"}
.label{color:red}
</style>
</head>
<body  >

<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>科目管理</span> &gt; <span>新建科目 </span> </h4>
</div>
<div class="mt20">

<form id="addMajorForm" action="${ctx}/admin/magor/tj" method="post">
	<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
	
		<thead>
			<tr>
				<th colspan="2" align="left"><span>新建科目 <tt class="c_666 ml20 fsize12">  </tt></span></th>
			</tr>
		</thead>
		<tbody>
		
			
			
			
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;科目名称</td>
				<td width="80%">
					<input name="major.name" id="magorname" type="text"/>
				</td>
			</tr>
			
			
			
			<tr>
				<td colspan="2" align="center">
					<input class="btn btn-danger" type="button" onclick="mysubmit()" value="提 交"/>
					<input class="btn ml10" type="button" onclick="history.go(-1)" value="返 回"/>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</form>
</div>
</body>
</html>
