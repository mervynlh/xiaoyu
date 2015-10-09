<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
	function cancel(){
		$("#type").val("");
	}
	function delNavigate(id){
		$.ajax({
			url:"${cxt}/admin/website/delNavigate/"+id,
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					alert("删除成功");
					window.location.reload();
				}
			}
		});
	} 
	function freezeNavigate(id,status){
		$.ajax({
			url:"${cxt}/admin/website/freezeNavigate",
			type:"post",
			data:{"websiteNavigate.id":id,"websiteNavigate.status":status},
			dataType:"json",
			success:function(result){
				if(result.message=="true"){
					if(status==0){
						alert("解冻成功");
					}else{
						alert("冻结成功");
					}
					window.location.reload();
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>导航管理</span> &gt; <span>导航列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/website/navigates" name="searchForm" id="searchForm" method="post">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>导航类型：</font></span>
									<select name="websiteNavigate.type" id="type">
										<option value="">--请选择--</option>
										<option value="INDEX" <c:if test="${websiteNavigate.type=='INDEX'}">selected="selected"</c:if>>首页</option>
										<%-- <option value="USER" <c:if test="${websiteNavigate.type=='USER'}">selected="selected"</c:if>>个人中心</option>
										<option value="SNS" <c:if test="${websiteNavigate.type=='SNS'}">selected="selected"</c:if>>社区</option> --%>
										<option value="FRIENDLINK" <c:if test="${websiteNavigate.type=='FRINEDLINK'}">selected="selected"</c:if>>尾部友链</option>
										<option value="TAB" <c:if test="${websiteNavigate.type=='TAB'}">selected="selected"</c:if>>尾部标签</option>
									</select>
									<input type="submit"  class="btn btn-danger ml10" value="查询"/>
									<input type="button"  class="btn btn-danger ml10" value="清空" onclick="cancel()"/>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn"><span class="ml10"><a href="${cxt}/admin/website/doAddNavigates"><em class="icon14 new">&nbsp;</em>新建导航</a></span></p>
						<p class="fr c_666"></p>
					</div>
				</caption>
				<thead>
					<tr>
						<th><span>名称</span></th>
                           <th><span>跳转地址</span></th>
                           <th><span>在新页面打开</span></th>
                           <th><span>类型</span></th>
                           <th><span>排序（由大到小显示）</span></th>
                           <th><span>状态</span></th>
                           <th><span>操作</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${websiteNavigates.size()>0}">
				<c:forEach  items="${websiteNavigates}" var="navigate" >
					<tr>
						<td>${navigate.name }</td>
						<td>${navigate.url }</td>
						<td>
							<c:if test="${navigate.newPage==0 }">是</c:if>
							<c:if test="${navigate.newPage==1 }">否</c:if>
						</td>
						<td>
							<c:if test="${navigate.type=='INDEX' }">首页</c:if>
							<c:if test="${navigate.type=='USER' }">个人中心</c:if>
							<c:if test="${navigate.type=='SNS' }">社区</c:if>
							<c:if test="${navigate.type=='FRIENDLINK' }">尾部友链</c:if>
							<c:if test="${navigate.type=='TAB' }">尾部标签</c:if>
						</td>
						<td>${navigate.orderNum }</td>
						<td>
							<c:if test="${navigate.status==0 }">正常</c:if>
							<c:if test="${navigate.status==1 }">冻结</c:if>
						</td>
						<td  class="c_666 czBtn" align="center">
							<a class="ml10 btn smallbtn btn-y" href="${cxt}/admin/website/doUpdateNavigate/${navigate.id}" >修改</a>
							<a class="ml10 btn smallbtn btn-y" href="javascript:delNavigate(${navigate.id})" >删除</a>
							<c:if test="${navigate.status==0}">
								<a class="ml10 btn smallbtn btn-y" href="javascript:freezeNavigate(${navigate.id},1)" >冻结</a>
							</c:if>
							<c:if test="${navigate.status==1}">
								<a class="ml10 btn smallbtn btn-y" href="javascript:freezeNavigate(${navigate.id},0)" >解冻</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${websiteNavigates.size()==0||websiteNavigates==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有导航！</span>
							</div>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			</form>
		</div><!-- /commonWrap -->
	</div>
</body>
</html>