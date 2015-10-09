<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>讲师详情</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}"></script>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />

</head>
<body>
		<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm">
			<input type="hidden" name="teacher.picPath" id="imagesUrl" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>讲师管理</span> &gt; <span>讲师添加</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>讲师基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师名称</td>
							<td><input type="text" name="teacher.name" value="${teacher.name}" disabled="disabled" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>讲师资历</td>
							<td width="80%"><input type="text" name="teacher.education" style="width: 500px;" value="${teacher.education}" disabled="disabled" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师等级</td>
							<td><select name="teacher.isStar" disabled="disabled">
									<option value="0" <c:if test="${teacher.isStar==0}">selected='selected'</c:if>>高级讲师</option>
									<option value="1" <c:if test="${teacher.isStar==1}">selected='selected'</c:if>>首席讲师</option>
							</select></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师简介</td>
							<td><textarea rows="6" cols="80" name="teacher.career" disabled="disabled">${teacher.career}</textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;头像</td>
							<td><img src="<%=staticImageServer %>${teacher.picPath}" alt="" id="subjcetpic" width="400px" height="300px" /> <!-- <input type="button" value="上传" id="fileuploadButton"/> --></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<!-- <a class="btn btn-danger" title="提 交" href="javascript:addTeacherFormSubmit()">提 交</a> --> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
