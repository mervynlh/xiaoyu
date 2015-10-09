<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改直播</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#addVedioLiveForm").validate();
		$("#endTime,#liveTime").datetimepicker({
			regional:"zh-CN",
			changeMonth: true,
			dateFormat:"yy-mm-dd",
			timeFormat : 'HH:mm:ss',
			timeFormat : 'HH:mm:ss'
		});
		//initKindEditor('content','576px','200px');
	});
	//添加博文页面编辑器
	function initKindEditor(id, width, height) {
		EditorObject = KindEditor.create('textarea[id=' + id + ']', {
			resizeType : 1,
			filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,// 允许上传
			urlType : 'domain',// absolute
			newlineTag : 'br',// 回车换行br|p
			width : width,
			height : height,
			minWidth : '10px',
			minHeight : '10px',
			uploadJson : '<%=keImageUploadUrl%>' + '&param=article',// 图片上传路径
			afterBlur : function() {
				this.sync();
			},
			allowFileManager : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'formatblock', 'lineheight',
					'removeformat', '|', 'justifyleft', 'justifycenter',
					'justifyright', 'insertorderedlist', 'insertunorderedlist',
					'|', 'emoticons', 'image', 'link', 'plainpaste' ]
		});
	}
	//添加直播
	function addLiveFormSubmit() {
		$("#addVedioLiveForm").submit();
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/live/update" method="post" id="addVedioLiveForm">
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>修改管理</span> &gt; <span>直播修改</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<input type="hidden" name="vedioLive.id" value="${vedioLive.id}" />
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 直播基本属性 <tt class="c_666 ml20 fsize12">
										（ <font color="red">*</font> &nbsp;为必填项）
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;直播标题</td>
							<td><input type="text" name="vedioLive.title" required value="${vedioLive.title}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;直播编码</td>
							<td><input type="text" name="vedioLive.code" value="${vedioLive.code}" class="required" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;主讲老师</td>
							<td><input type="text" value="${vedioLive.teacher}" name="vedioLive.teacher" class="required" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;直播时间</td>
							<td><input readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${vedioLive.liveTime}"/>" type="text" id="liveTime" name="vedioLive.liveTime" class="required" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;参加人数</td>
							<td><input type="text" name="vedioLive.joinNum" value="${vedioLive.joinNum}" class="required number" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;直播内容</td>
							<td><textarea id="content" rows="6" cols="80" name="vedioLive.content" required>${vedioLive.content}</textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;直播结束时间</td>
							<td><input readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${vedioLive.endTime}"/>" type="text" id="endTime" name="vedioLive.endTime" class="required" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:addLiveFormSubmit()">提 交</a> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
</body>
</html>
