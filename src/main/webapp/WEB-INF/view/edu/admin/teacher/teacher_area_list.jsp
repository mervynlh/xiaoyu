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
	
	// 取消
	function cleanCheck() {
		$("input[name='ids']:checkbox").prop('checked', false);
	}
	//选择城市
	function showNewwin(){
		window.open('${ctx}/admin/teacher/select/area/list','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	}
	// 搜索
	function searchFormSubmit(){
		$("#searchForm").submit();
	}
	function addCityReload(){
		window.location.reload();
	}
	// 修改显示
	function updateHide(){
		var cityIds = document.getElementsByName("ids");
		var num = 0;
		var ids = '';
		for (var i = 0; i < cityIds.length; i++) {
			if (cityIds[i].checked == true) {
				num++;
				ids += cityIds[i].value + ",";
			}
		}
		$.ajax({
			url : "${ctx}/admin/teacher/area/updateHide",
			data : {
				"ids" : ids
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.message == 'success') {
					alert("操作成功");
					$("[name='ids']").prop('checked',false);
					window.location.reload();
				} else {
					alert("操作失败");
				}
			}
		});
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/area/list" name="searchForm" id="searchForm" method="post">
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>城市列表</span> &gt; <span>城市列表</span>
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
											<div class="mt10 clearfix">
												<p class="fl czBtn">
												 <span><a href="javascript:void(0)" onclick="showNewwin()" title="添加城市"><em class="icon14 new">&nbsp;</em>添加城市</a></span>
												 <span class="ml10"><a title="删除" href="javascript:updateHide();"><em class="icon14 delete">&nbsp;</em>删除</a></span>
												</p>
											</div>
										</caption>

										<thead>

											<tr>
												<th><span><input type="checkbox" onclick="allCheck(this)"/>&nbsp;全选</span></th>											
												<th><span>城市名</span></th>
												<th><span>英文名</span></th>
												<th><span>省份名</span></th>
											</tr>
										</thead>
										<tbody id="tabS_02" align="center">										
												<c:forEach items="${cityList}" var="area">
													<tr>
														<td><input type="checkbox" name="ids" value="${area.id}"/>&nbsp;&nbsp;${area.id}</td>
														<td>${area.areaName}</td>
														<td>${area.areaNameEn}</td>
														<td>${area.proName}</td>
													</tr>
												</c:forEach>
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
