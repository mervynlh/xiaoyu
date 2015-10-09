<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>反馈详情</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
</head>
<!-- 将图片的大小默认设置成80px*80px  @Author wangzhuang   -->
<body >
<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm">
<input type="hidden" name="teacher.picPath" id="imagesUrl"  />
<div class="page_head">
			<em class="icon14 i_01"></em>&nbsp;<span>反馈管理</span> &gt; <span>反馈详情</span>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>反馈基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>邮箱</td>
				<td width="80%">
				<input type="text" id="useremail" name="userFeedback.email"  value="${queryUserFeedback.email}" disabled="disabled"/>
				</td>
			</tr>
			
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;电话</td>
				<td>
						<input type="text" id="mobile" name="userFeedback.mobile" disabled="disabled" value="${queryUserFeedback.mobile}"/>
				</td>
			</tr>
			
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;反馈类型</td>
			    <td>
			<c:if test="${queryUserFeedback.type==0}">
 						<input type="text" disabled="disabled" value="意见"/>
			</c:if>	
			<c:if test="${queryUserFeedback.type==1}">
						<input type="text" disabled="disabled" value="投诉"/>
			</c:if>
			    </td>	
			</tr>
			
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;反馈时间</td>
				<td>
				<fmt:formatDate value="${queryUserFeedback.createTime}" type="both" />	
				</td>
			</tr>
			
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;图片显示</td>
				<td>
				<c:if test="${empty queryUserFeedback.img}">
					无图片反馈
				</c:if>
				<c:if test="${not empty queryUserFeedback.img}">
				<img src="<%=staticImageServer%>${queryUserFeedback.img}" alt="图片显示" height="300px" weight="400px"/>
				</c:if>
				</td>
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<!-- <a class="btn btn-danger" title="提交" href="javascript:addTeacherFormSubmit()">提 交</a> -->
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
