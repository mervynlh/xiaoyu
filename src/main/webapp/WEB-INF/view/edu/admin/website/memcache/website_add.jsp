<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Memcache管理</title>
<script type="text/javascript">
function submit(){
	var memKey = $("#memKey").val();
	var memDesc=$("#memDesc").val();
	if(memKey.trim()==""){
		alert("Memcache-key不能为空");
		return;
	}
	if(memDesc.trim()==""){
		alert("请输入Memcache-key描述");
		return;
	}
	 var judge=confirm("确定添加？");
	 if(judge==true){
		 $.ajax({
				url:"${ctx}/admin/websitemem/add",
				data:{"websiteMemcache.memKey":memKey,"websiteMemcache.memDesc":memDesc},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(!result.success){
						alert("该key已存在");
					}else{
						window.location.href="${ctx}/admin/websitemem/list"
					}
				}
			});
	 }else{
		 return false;
	 }
	//$("#addMemForm").submit();
}
</script>
</head>
<body >
<form action="?" method="post" id="addMemForm">
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>Memcache管理</span> &gt; <span>添加Memcache</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>添加Memcache<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;Memcache-key</td>
				<td>
					<input type="text" name="websiteMemcache.memKey" id="memKey"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;描述</td>
				<td>
					<input type="text" name="websiteMemcache.memDesc" id="memDesc" style="width: 450px"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:submit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
