<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>城市列表</title>
<script type="text/javascript">
	
	function allCheck(th) {
		$("input[name='ids']:checkbox").prop('checked', th.checked);
	}
	// 搜索
	function searchFormSubmit(){
		$("#searchForm").submit();
	}
	function addSubmit() {
		
		var cityIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
		for (var i = 0; i < cityIds.length; i++) {
			if (cityIds[i].checked == true) {
				num++;
				ids += cityIds[i].value + ",";
			}
		}
		if (num == 0) {
			alert("请选择要添加显示的城市");
			return;
		}
		$.ajax({
			url : "${ctx}/admin/teacher/area/updateShow",
			data : {
				"ids" : ids
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.message == 'success') {
					alert("操作成功");
					window.close();
					window.opener.addCityReload();
				} else {
					alert("操作失败");
					window.close();
					window.opener.addCityReload();
				}
			}
		});

	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/select/area/list" name="searchForm" id="searchForm" method="post">
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>选取城市</span> &gt; <span>城市列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span class="ddTitle"><font>省份：</font></span>
														<select id="provinceId" name="userArea.parentId">
															<option value="-1" selected="selected">--请选择省份--</option>
															<c:forEach items="${provinceList }" var="province">
																<option value="${province.id }" ${province.id == userArea.parentId ? "selected='selected'" : ""}>${province.areaName }</option>
															</c:forEach>
														</select>  
													</div>

													<div class="optionList">
														<input type="button" name="" value="查询" class="btn btn-danger" onclick="searchFormSubmit()" />
													</div>
													
												</div>
												<div class="clearfix"></div>
											</div>
										</caption>

										<thead>

											<tr>
												<th><span><input type="checkbox" onclick="allCheck(this)"/>&nbsp;全选</span></th>											
												<th><span>城市名</span></th>
												<th><span>英文名</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">										
												<c:forEach items="${cityList}" var="area">
													<tr>
														<td><input type="checkbox" name="ids" value="${area.id}" ${area.checkShow == 2 ? "checked='checked'" : ""} />&nbsp;&nbsp;${area.id}</td>
														<td>${area.areaName}</td>
														<td>${area.areaNameEn}</td>
													</tr>
												</c:forEach>
											<tr>
												<td align="center" colspan="5"><a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">确定</a> 
												<a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a></td>
											</tr>
										</tbody>
									</table>
									
								</div>
							</div>
						</table>
					</div>
				</div>
		</form>
</body>
</html>
