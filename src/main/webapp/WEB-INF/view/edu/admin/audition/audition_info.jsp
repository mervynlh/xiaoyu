<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>约课详情</title>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">

//加载编辑器
var editor;
KindEditor
		.ready(function(K) {
			editor = K
					.create(
							'textarea[id="teacherCareer"]',
							{
								resizeType : 1,
								filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
								allowPreviewEmoticons : false,
								allowUpload : true,//允许上传
								syncType : 'auto',
								width : '620px',
								minWidth : '10px',
								minHeight : '10px',
								height : '300px',
								urlType : 'domain',//absolute
								newlineTag : 'br',//回车换行br|p
								uploadJson : '//image.268xue.com/imgk4?base=projectName&param=teacher',//图片上传路径
								allowFileManager : false,
								/* afterFocus:function(){editor.sync();}, */
								afterBlur : function() {
									editor.sync();
								},
								items : [ 'fontname', 'fontsize', '|',
										'forecolor', 'hilitecolor', 'bold',
										'italic', 'underline',
										'removeformat', '|', 'justifyleft',
										'justifycenter', 'justifyright',
										'insertorderedlist',
										'insertunorderedlist', '|',
										'emoticons', 'image', 'link' ],
								afterChange : function() {
								}
							});
		});
		function submit(){
			$.ajax({
				url:baselocation+"/admin/audition/updateStatus",
				type:"post",
				data:{
					"id":$("#auditionId").val(),
					"description":$("#description").val()
				},
				dataType:"json",
				success:function(result){
					if(result.success){
						alert("处理成功");
						window.location.href=baselocation+"/admin/audition/getAuditionList";
					}
				}
			});
			
		}
</script>
</head>
<body>
		<form action="" method="post" id="updateForm">
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>约课管理</span> &gt; <span>约课详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<input type="hidden" value="${audition.id }" id="auditionId" />
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>约课基本信息<tt class="c_666 ml20 fsize12">
										
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
							<td>${audition.name}</td>
						</tr>
						<tr>
							<td width="20%" align="center">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
							<td width="80%">${audition.email}</td>
						</tr>
						<tr>
							<td width="20%" align="center">手机号</td>
							<td width="80%">${audition.mobile}</td>
						</tr>
						<tr>
							<td width="20%" align="center">创建时间</td>
							<td width="80%"><fmt:formatDate value="${audition.createTime }" pattern="yyyy:MM:dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td width="20%" align="center">时&nbsp;&nbsp;&nbsp;&nbsp;间</td>
							<td width="80%">
								<fmt:formatDate value="${audition.startTime }" pattern="yyyy:MM:dd HH:mm:ss" />-
								<fmt:formatDate value="${audition.endTime }" pattern="yyyy:MM:dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="center">阶&nbsp;&nbsp;&nbsp;&nbsp;段</td>
							<td width="80%">${audition.subjectName }</td>
						</tr>
						<tr>
							<td width="20%" align="center">年&nbsp;&nbsp;&nbsp;&nbsp;级</td>
							<td width="80%">${audition.gradeName }</td>
						</tr>
						<tr>
							<td width="20%" align="center">科&nbsp;&nbsp;&nbsp;&nbsp;目</td>
							<td width="80%">${audition.majorName }</td>
						</tr>
						<tr>
							<td width="20%" align="center">目&nbsp;&nbsp;&nbsp;&nbsp;标</td>
							<td width="80%">
								<c:if test="${audition.teacherId==0}">后台管理员</c:if>
								<c:if test="${audition.teacherId!=0}">${audition.teacherName }</c:if>
							</td>
						</tr>
						<tr>
							<td align="center">备&nbsp;&nbsp;&nbsp;&nbsp;注</td>
							<td><textarea rows="6" cols="80" name="" id="description" class="{required:true}">${audition.description}</textarea></td>
						</tr>
						<tr>
							<td width="20%" align="center">状&nbsp;&nbsp;&nbsp;&nbsp;态</td>
							<td width="80%">
								<c:if test="${audition.status==0}">未处理</c:if>
								<c:if test="${audition.status==1}">已处理</c:if>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<a class="btn btn-danger" title="提交处理" href="javascript:submit()">提交处理</a>
								<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
