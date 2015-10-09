<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>发送系统消息</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript">
KindEditor.ready(function(K) {
	window.EditorObject = K.create('textarea[id="message"]', {
			resizeType  : 1,
	       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
	       allowPreviewEmoticons : false,
	       allowUpload : true,//允许上传
	       syncType : 'auto',
	       urlType : 'domain',//absolute
	       newlineTag :'br',//回车换行br|p
	       uploadJson : '<%=keImageUploadUrl%>&param=question',//图片上传路径
	       allowFileManager : false,
	       afterBlur:function(){EditorObject.sync();}, 
	       items : ['emoticons']
	});
});
	function sendmessage(){
		var content = $("#message").val();
		if(content==null||content.trim()==""){
			alert("请填写消息内容在发送");
			return false;
		}
		 $.ajax({
             url:"${ctx}/admin/letter/sendJoinGroup",
             type:"post",
             data:{"content":content,"type":0},
             dataType:"json",
             success:function(result){
             	if(result.message=='success'){
             		KindEditor.html('#message', '');
             		 alert("发送成功");
             	}
             }
         });
	}
</script>

</head>
<body  >

	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>系统管理</span> &gt; <span>发送系统消息</span> </h4>
	</div>
			
<div class="mt20">
	<div class="commonWrap">
		<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th colspan="2" align="left"><span>发送系统消息</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td align="center"><font color="red">*</font>&nbsp;系统消息要发送的内容</td>
							<!-- <td>
								<textarea name="" style="width: 48%;height: 68px;" id="message"></textarea>
							</td> -->
						</tr>
						<tr>
							<!-- <td align="center"><font color="red">*</font>&nbsp;系统消息要发送的内容</td> -->
							<td align="center">
								<textarea name="" style="width: 48%;height: 68px;" id="message"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="javascript:void(0)" title="发 送" onclick="sendmessage()" class="btn btn-danger">发 送</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>