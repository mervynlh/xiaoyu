<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程笔记详情</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}"></script>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
window.onload=function(){//编辑器初始化
	initKindEditor_addblog('ArticleContent','576px','400px');
};
//添加博文页面编辑器
function initKindEditor_addblog(id, width, height) {
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
					'removeformat' ]
		});
	}
	function updateIsavalible(id, isavalible) {
		var str = "";
		if (isavalible == 1) {
			str = "是否隐藏该条信息";
		}
		if (isavalible == 0) {
			str = "是否显示该条信息";
		}
		if (confirm(str)) {
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/courseNote/update",
				data : {
					"courseNote.id" : id,
					"courseNote.status" : isavalible
				},
				async : false,
				success : function(result) {
					if (result.success) {
						if (isavalible == 1) {
							$(".attr" + id)
									.attr(
											"href",
											"javascript:updateIsavalible(" + id
													+ ",0)");
							$(".attr" + id).html("显示");
							$(".attr" + id).attr("title", "显示");
							$("#isavalible" + id).html("隐藏");
							alert("隐藏成功");
						} else {
							$(".attr" + id)
									.attr(
											"href",
											"javascript:updateIsavalible(" + id
													+ ",1)");
							$(".attr" + id).html("隐藏");
							$(".attr" + id).attr("title", "隐藏");
							$("#isavalible" + id).html("显示");
							alert("显示成功");
						}

					}
				}
			});
		}
	}
</script>
</head>
<body>
		<form action="?" method="post" id="addTeacherForm">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>课程笔记管理</span> &gt; <span>课程笔记</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>笔记基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;学员名称</td>
							<td><input type="text" name="queryCourseNote.nickname" value="${queryCourseNote.nickname}" disabled="disabled" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>学员账号</td>
							<td width="80%"><input type="text" name="queryCourseNote.email" value="${queryCourseNote.email}" disabled="disabled" /></td>
						</tr>
						<%-- <tr>
				<td align="center"><font color="red">*</font>&nbsp;课程名称</td>
				<td>
				<input type="text" name="queryCourseNote.courseName" value="${queryCourseNote.courseName}" disabled="disabled"/>	
				</td>
			</tr> --%>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;视频名称</td>
							<td><input type="text" name="queryCourseNote.content" disabled="disabled" value="${queryCourseNote.pointName}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;笔记时间</td>
							<td><fmt:formatDate value="${queryCourseNote.updateTime }" type="both" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;笔记内容</td>
							<td><textarea rows="6" cols="80" name="queryCourseNote.content" disabled="disabled" id="ArticleContent">${queryCourseNote.content}</textarea></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
