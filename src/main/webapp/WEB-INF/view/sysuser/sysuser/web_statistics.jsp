<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>


<script type="text/javascript">
	$(function(){
		  $( "#startTime,#endTime" ).datepicker(
		    		{regional:"zh-CN",
		    		changeMonth: true,
		    		dateFormat:"yy-mm-dd "
		    		});
		
	});
	function doSubmit(){
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var operate=$("#operate").val();
		var url='';
		if(operate==0){
			alert("请选择操作");
			return;
		}else if(operate==1){
			url='/admin/statistics/create/batch';
		}else if(operate==2){
			url='/admin/statistics/del/batch';
		}
		if(startTime==null||startTime==''){
			alert("请输入开始日期");
			return;
		}
		if(endTime==null||endTime==''){
			alert("请输入结束日期");
			return;
		}
		var begin=new Date($("#startTime").val().replace(/-/g,"/"));
	    var end=new Date($("#endTime").val().replace(/-/g,"/"));
		if(begin>end){
			alert("结束日期必须大于开始日期");
			return;
		}
		var date = new Date();
		date.setDate(date.getDate()-1);
		if(date<end){
			alert("结束日期不能大于等于当前时间");
			return;
		}
		$.ajax({
			url:'${ctx}'+url,
			type:"post",
			data:{"startTime":startTime,"endTime":endTime},
			dataType:"json",
			success:function(result){
				if(result.message=='true'){
					alert("操作成功");
				}else if (result.message=='exists'){
					alert("时间段内已有数据，请删除后再操作");
				}
			}
		})
	}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>网站统计管理</span> &gt; <span>网站统计操作</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="" method="post" id="statisticsForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>网站统计基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;开始日期</td>
					<td>
						<input type="text"  readonly="readonly" id="startTime" class="AS-inp"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;结束日期</td>
					<td>
						<input type="text"  readonly="readonly" id="endTime" class="AS-inp"/>
					</td>
				</tr>
				<tr>
					<td align="center">操作</td>
					<td>
						<select id="operate">
							<option value="0">--请选择--</option>
							<option value="1">生成统计</option>
							<option value="2">删除统计</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="执行" href="javascript:doSubmit()">执行</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<!-- /tab4 end -->
</body>
</html>