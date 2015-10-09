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
		if($("#keyword").val()==null||$("#keyword").val()==""){
			alert("关键词不能为空");
			return;
		}
		if($("#content").val()==null||$("#content").val()==""){
			alert("内容不能为空");
			return;
		}
		
		$("#updateForm").submit();
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>更新文本素材</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/weixin/update" method="post" id="updateForm">
		<input type="hidden" name="weixinReply.id" value="${weixinReply.id}"/>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>文本素材基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;关键词 </td>
					<td>
						<input type="text" name="weixinReply.keyword" id="keyword" value="${weixinReply.keyword}" class="{required:true}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*可设置多个关键字用单个空格隔开，如网校 教育 在线 </font>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;内容</td>
					<td>
						<textarea rows="4" maxlength="600" cols="80" name="weixinReply.content" id="content">${weixinReply.content}</textarea>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*请不要多于600字</font>
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
	</div>
	<!-- /tab4 end -->
</body>
</html>