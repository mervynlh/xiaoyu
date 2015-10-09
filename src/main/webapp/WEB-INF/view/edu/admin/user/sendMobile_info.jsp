<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
	$(function(){
		  $( "#sendDate" ).datetimepicker({
			  regional:"zh-CN",
		      changeMonth: true,
		      dateFormat:"yy-mm-dd ",
		      timeFormat: "HH:mm:ss"
		  });
		  var type="userMobileMsg.type";
		  var status="userMobileMsg.status";
		  if(type!=2&&status!=2){
			  //编辑器禁用
			  editor.EditorDocument.body.disabled = true;
		  }
		  
	});
	function submit(){
		var sendTime=$("#sendDate").val();
		 var nowTime=new Date().getTime();
		 var sendDate=Date.parse(sendTime.replace(/-/gi,"/"));

		 if($("#content").val()==""){
			alert("短信内容不能为空！");
			return;
		}else if(sendTime==""||sendTime==null){
             alert("发送时间不能为空");
			 return;
		 }else if(sendDate<nowTime){
			 alert("定时发送时间必须大于当前系统时间");
			 return;
		 }
		var id=$("#id").val();
		var content=$("#content").val();
		$.ajax({
			url : "${ctx}/admin/user/updateUserMsg",
			data : {
				"msgId": id ,
				"content":content,
				"sendTime": $("#sendDate").val()
			},
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.message);
			}
		})
	}

	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="textareamobile"]',{
			resizeType : 1,
			filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,//允许上传
			syncType : 'auto',
			width : '720px',
			minWidth : '10px',
			minHeight : '10px',
			height : '300px',
			urlType : 'domain',//absolute
			newlineTag : 'br',//回车换行br|p
			uploadJson : '<%=keImageUploadUrl%>' + '&param=sendEmail',// 图片上传路径
			allowFileManager : false,
			afterBlur : function() {
				editor.sync();
			},
			afterChange : function() {
			}
		});
	});

</script>
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>短信管理</span> &gt; <span>短信详情</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/user/updateUserMsg" method="post" id="addPaperForm">
	<input name="userMobileMsg.id" id="id" type="hidden" value="${userMobileMsg.id}"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>短信详情<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;短信内容</td>
				<td>
					<textarea rows="6" cols="80" name="userMobileMsg.content" id="content" <c:if test="${userMobileMsg.status!=2&&userMobileMsg.type!=2 }">readonly="readonly"</c:if>> ${userMobileMsg.content }</textarea>       
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;创建时间</td>
				<td>
				
					<input type="text" disabled="disabled" value="<fmt:formatDate value="${userMobileMsg.createTime }" type="both"/>"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;发送时间</td>
				<td>
				
					<input type="text" <c:if test="${userMobileMsg.status!=2&&userMobileMsg.type!=2 }">disabled="disabled"</c:if> readonly="readonly" id="sendDate" value="<fmt:formatDate value="${userMobileMsg.sendTime }" type="both"/>"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;发送状态</td>
				<td>
					<c:if test="${userMobileMsg.status==1 }">已发送</c:if>
					<c:if test="${userMobileMsg.status==2 }">未发送</c:if>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;发送人</td>
				<td>
					<input type="text" value="${userMobileMsg.loginName }" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;接收人</td>
				<td>
					<textarea id="textareamobile" <c:if test="${userMobileMsg.status!=2&&userMobileMsg.type!=2 }">disabled="disabled" readonly="readonly"</c:if> rows="6" cols="80" >${userMobileMsg.mobile}</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;发送方式</td>
				<td>
					<c:if test="${userMobileMsg.status==1 }">正常发送</c:if>
					<c:if test="${userMobileMsg.status==2 }">定时发送</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<c:if test="${userMobileMsg.status==2&&userMobileMsg.type==2 }">
						<a class="btn btn-danger" title="提 交" href="javascript:submit()">修改</a>
					</c:if>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	</div>
</div>
<!-- /tab4 end -->
</body>
</html>
