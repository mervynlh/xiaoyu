<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户权限限制</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}"></script> 
<script type="text/javascript">
		$().ready(function() {
 			
 		});
	</script>
</head>
<body  >
<div class="">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th colspan="2" align="left"><span>没有此功能的权限<tt class="c_666 ml20 fsize12"></tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="100%" align="center">
					对不起，您没有访问此功能的权限！
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="返回" onclick="history.go(-1)" class="btn ml10"/>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
