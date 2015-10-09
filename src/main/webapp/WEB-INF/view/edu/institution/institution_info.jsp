<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台_机构查看</title>
</head>
<body>
	<input type="hidden" value="${institution.id }"  name="institution.id" id="inst_id"/>
	<table>
		<tr>
			<td colspan="2"><h1>机构信息</h1></td>
		</tr>
		<tr>
			<td>机构图片</td>
			<td><img src="<%=staticImageServer %>${institution.inst_pic_url}"width="130px" width="130px" /></td>
		</tr>

		<tr>
			<td>机构名称</td>
			<td><input type="text" value="${institution.name }"  name="institution.name" id="inst_name"/></td>
		</tr>
		<tr>
			<td>机构简介</td>
			<td><textarea cols="20" rows="5"  name="institution.description" id="inst_desc">${institution.description }</textarea></td>
		</tr>
		<tr>
			<td>主营类目</td>
			<td><input type="text" value="${institution.category }" name="institution.category" id="inst_cate"/></td>
		</tr>
		<tr>
			<td>主页域名</td>
			<td><input type="text" value="${institution.homePage }" name="institution.homePage" id="inst_home"/></td>
		</tr>
		<!-- 平台存根 -->
		<tr>
			<td colspan="2"><h1>平台存根</h1></td>
		</tr>
		<tr>
			<td>公司名称</td>
			<td><input type="text" value="${institution.company }" name="institution.company" id="inst_company"/></td>
		</tr>
		<tr>
			<td>营业执照</td>
			<td><img src="<%=staticImageServer %>${institution.license_pic_url}" width="200px" height="100px" /></td>
		</tr>
		<tr>
			<td>申请人</td>
			<td><input type="text" value="${institution.applicant}" name="institution.applicant" id="inst_applicant"/></td>
		</tr>
		<tr>
			<td>身份证</td>
			<td><img src="<%=staticImageServer %>${institution.ID_pic_url}"	width="200px" height="100px" /></td>
		</tr>
		<tr>
			<td>手机</td>
			<td><input type="text" value="${institution.mobile}" name="institution.mobile" id="mobile"/>	</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" value="${institution.email}" name="institution.email" id="email"/></td>
		</tr>
		<tr>
			<td><input type="button" value="提交" id="onSubmit" /></td>
		</tr>
	</table>
</body>
</html>