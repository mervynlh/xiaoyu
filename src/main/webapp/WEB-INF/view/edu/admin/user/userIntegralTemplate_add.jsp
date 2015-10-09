<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>添加积分模板</title>

<script type="text/javascript">
function submit(){
	var score=$("#score").val();//获得分数
	var name=$("#name").val();//获得模板名称
	if(name==null||name.trim()==''){
		alert("请输入模板名称");
		return;
	}
	if(score=null||score.trim()==''){//验证为空
		alert("请输入分数");
		return;
	}
	if(!/^[0-9]*$/.test($("#score").val())){//验证数字
		alert("请输入数字");
		return;
	}
	$("#addIntegralTemplateForm").submit();
}
</script> 
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>积分模板管理</span> &gt; <span>添加积分模板</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/integral/template/add" method="post" id="addIntegralTemplateForm">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>积分模板<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;模板名称</td>
				<td>
					<input type="text" name="userIntegralTemplate.name" value="${userIntegralTemplate.name}" id="name"/>      
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;使用类型</td>
				<td>
				<select name="userIntegralTemplate.type">
				<option value="0">学员</option>
				</select>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;使用场景</td>
				<td>
					<input type="text" value="${userIntegralTemplate.keyword}" name="userIntegralTemplate.keyword"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;功能分数</td>
				<td>
					<input type="text" value="${userIntegralTemplate.showScore}" name="userIntegralTemplate.score" id="score"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				    <a class="btn btn-danger" title="提 交" href="javascript:submit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	</div>
</div>
<!-- /tab4 end -->
</form>
</div>
</body>
</html>
