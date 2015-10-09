<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
function clearImageText(){
	$("#replyIdHidden").val(0);
	$("#keyword").html("");
	$("#title").html("");
	$("#msgType").html("");
}
function addWeixinReply(){
	window.open('${cxt}/admin/weixin/defaultpage?page.currentPage=1',
			+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
		);
}

function weixinAdd(replyStr){
	var myArray=replyStr.split("#");
	$("#title").html(myArray[1]);
	$("#keyword").html(myArray[2]);
	var msgType=myArray[3];
	if(msgType==1){
		$("#msgType").html("文本");
		$("#title").html("--");
	}else if(msgType==2){
		$("#msgType").html("图文");
	}else if(msgType==3){
		$("#msgType").html("多图文");
	}  
	
	$("#replyIdHidden").val(myArray[0]);
}
function doSubmit(){
	var setId=$("#setId").val();
	var replyIdHidden=$("#replyIdHidden").val();
	$.ajax({
		url:"${ctx}/admin/default/update",
		type:"post",
		data:{"weixinSetReply.id":setId,"weixinSetReply.replyId":replyIdHidden},
		dataType:"json",
		success:function(result){
			if(result.message=='true'){
				alert("修改成功");
			}
		}
	});
}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>微信常规回复管理</span></h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<input type="hidden" value="${weixinSetReply.id}" id="setId"/>
				<input type="hidden" id="replyIdHidden" value="${weixinSetReply.replyId}"/>
				<c:if test="${type=='tolerate'}"><input type="hidden" name="weixinSetReply.type" value="tolerate"/></c:if>
				<c:if test="${type=='add'}"><input type="hidden" name="weixinSetReply.type" value="add"/></c:if>
				<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
					<caption>
						<div class="capHead">
							<div class="clearfix">
								<div class="optionList">
									<a href="${ctx}/admin/default/reply/tolerate"><span><font>默认回复</font></span></a>
									&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/admin/default/reply/add"><span><font>关注时回复</font></span></a>
								</div>
							</div>
						</div>
					</caption>
					<thead>
						<tr>
							<th><span>类型</span></th>
							<th><span>关键字</span></th>
							<th><span>标题</span></th>
							<th><span>素材类型</span></th>
							<th><span>操作</span></th>
						</tr>
					</thead>
					<tbody id="tabS_web" align="center">
						<tr>
							<td>
								<c:if test="${type=='tolerate'}">默认回复</c:if>
								<c:if test="${type=='add'}">关注时回复</c:if>
							</td>
							<td id="keyword">${weixinReply.keyword }</td>
							<td id="title">${weixinReply.title }<c:if test="${weixinReply.msgType==1 }">--</c:if></td>
							<td id="msgType">
								<c:if test="${weixinReply.msgType==1 }">文本</c:if>
								<c:if test="${weixinReply.msgType==2 }">图文</c:if>
								<c:if test="${weixinReply.msgType==3 }">多图文</c:if>
							</td>
							<td>
								<a href="javascript:addWeixinReply()" style="cursor: pointer;" class="btn btn-danger">设置回复</a>
								<a href="javascript:clearImageText()" style="cursor: pointer;" class="btn btn-danger">删除</a>
							</td>
						</tr>
					</tbody>																	
					<tr>
						<td colspan="5" align="center">
							<a class="btn btn-danger" title="提 交" href="javascript:doSubmit()">修改</a>
							<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
						</td>
					</tr>  
				</table>
			
			</div>
		</div>
</body>
</html>