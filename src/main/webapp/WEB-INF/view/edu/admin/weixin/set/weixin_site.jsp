<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
	function doUpdate(){
		if($("#wxToken").val()==null||$("#wxToken").val()==""){
			alert("token不能为空");
			return;
		}
		if($("#wxAppID").val()==null||$("#wxAppID").val()==""){
			alert("appId不能为空");
			return;
		}
		if($("#wxAppSecret").val()==null||$("#wxAppSecret").val()==""){
			alert("appSecret不能为空");
			return;
		}
		
		$.ajax({
			url:"${ctx}/admin/weixinsite/update",
			type:"post",
			data:{"wxToken":$("#wxToken").val(),"wxAppID":$("#wxAppID").val(),"wxAppSecret":$("#wxAppSecret").val(),"wxMchId":$("#wxMchId").val(),"wxPayKey":$("#wxPayKey").val(),"encodingAESKey":$("#encodingAESKey").val()},
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					alert("修改成功");
					window.location.reload();
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>开发者配置</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>微信配置属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;Token </td>
					<td>
						<input type="text" id="wxToken" value="${weixinMap.weixin.wxToken}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;AppId</td>
					<td>
						<input type="text" id="wxAppID" value="${weixinMap.weixin.wxAppID}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;AppSecret</td>
					<td>
						<input type="text" id="wxAppSecret" value="${weixinMap.weixin.wxAppSecret}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;EncodingAESKey</td>
					<td>
						<input type="text" id="encodingAESKey" value="${weixinMap.weixin.encodingAESKey}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;PayKey</td>
					<td>
						<input type="text" id="wxPayKey" value="${weixinMap.weixin.wxPayKey}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;MchId</td>
					<td>
						<input type="text" id="wxMchId" value="${weixinMap.weixin.wxMchId}" class="{required:true}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:doUpdate()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /tab4 end -->
</body>
</html>