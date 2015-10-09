<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>添加敏感词</title>
<script type="text/javascript">
	function submitFrom(){
		var word = $("#word").val();
		if(word.trim()==""){
			alert("敏感词不能为空！");
			return;
		}
		$("#searchForm").submit();
	}
</script>

</head>
<body  >
		<div class="page_head">
			<h4><em class="icon14 i_01"></em>&nbsp;<span>敏感词管理</span> &gt; <span>添加敏感词</span> </h4>
		</div>
			
		<div class="mt20">
			<div class="commonWrap">
				<form action="${ctx}/admin/keyword/add"
					name="searchForm" id="searchForm" method="post">
					<div class="mt20">
						<table width="100%" cellspacing="0" cellpadding="0" border="0"
							class="commonTab01">
							<caption>
								<div class="optionList">
									<a class="btn btn-danger" href="javascript:window.history.go(-1)">返回</a>
								</div>
							</caption>
							<thead>
								<tr>
									<th colspan="2" align="left"><span>添加敏感词<tt
												class="c_666 ml20 fsize12">
												（<font color="red">*</font>&nbsp;为必填项）
											</tt></span></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="center"><font color="red">*</font>&nbsp;敏感词</td>
									<td><input type="text" id="word" name="keyword.keyword" value=""/></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><a href="javascript:submitFrom();" title="提 交"
										class="btn btn-danger">提 交</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<!-- /commonWrap -->
		</div>
	<!-- /tab2 end-->
</body>
</html>