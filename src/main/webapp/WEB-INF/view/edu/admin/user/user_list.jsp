<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>学员列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
});
function updateIsavalible(id,isavalible){
	var str = "";
	if(isavalible==1){str= "是否禁用该用户";}
	if(isavalible==0){str= "是否恢复该用户";}
	if(confirm(str)){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/user/updateIsavalible",
			data:{"user.id":id,"user.isavalible":isavalible},
			async:false,
			success:function(result){
				if(result.success){
					if(isavalible==1){
						$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",0,this)");
						$(".attr"+id).html("恢复");
						$(".attr"+id).attr("title","恢复");
						$("#isavalible"+id).html("禁用");
						alert("禁用成功");
					}else{
						$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",1,this)");
						$(".attr"+id).html("禁用");
						$(".attr"+id).attr("title","禁用");
						$("#isavalible"+id).html("正常");
						alert("恢复成功");
					}
					
				}
			}
		});
	}
}
function clean(){
	$("#nickname,#useremail,#mobile,#startDate,#endDate").val("");
	$("#registerFrom").val("");
	$("#isavalible").val("-1");
}
function userExcel(){
	$("#searchForm").prop("action","${ctx}/admin/user/export");
	$("#searchForm").submit();
	$("#searchForm").prop("action","${ctx}/admin/user/list");
}
</script>
</head>
<body  >

<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>学员管理</span> &gt; <span>学员列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<form action="${ctx}/admin/user/list" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			
						<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
										    <span class="ddTitle"><font>学员ID：</font></span>
											<input type="text" name="user.id" value="${user.id}" id="nickname" />
										</li>
										<li>
											<span class="ddTitle"><font>注册开始日期：</font></span>
											<input type="text" name="user.startDate" value="${user.startDate}"  id="startDate" class="AS-inp"/>
										</li>
                                        <li>
                                            <span class="ddTitle"><font>手机号：</font></span>
                                            <input type="text" name="user.mobile" value="${user.mobile}" id="mobile" />
                                        </li>
                                        <li>
                                            <span class="ddTitle"><font>真实姓名：</font></span>
                                            <input type="text" name="user.realname" value="${user.realname}" id="mobile" />
                                        </li>
									</ul>
								</div>
                                <div class="w50pre fl">
                                    <ul class="ddBar">
                                       <%--  <li>
                                            <span class="ddTitle"><font>邮箱：</font></span>
                                            <input type="text" name="user.email" value="${user.email}" id="useremail" />
                                            </li>--%>
                                        <li> 
                                            <span class="ddTitle"><font>注册结束日期：</font></span>
                                            <input type="text" name="user.endDate" value="${user.endDate}"  id="endDate" class="AS-inp"/>
                                        </li>
                                        <li>
                                            <span class="ddTitle"><font>账号来源：</font></span>
                                            <select name="user.registerFrom" id="registerFrom">
                                                <option value="">请选择</option>
                                                <option value="registerFrom">注册用户</option>
                                                <option value="OpenAppRegisterFrom">第三方登陆用户</option>
                                            </select>
                                            <script>
                                                $("#registerFrom").val('${user.registerFrom}');
                                            </script>
                                        </li>
                                        <li>
                                            <span class="ddTitle"><font>状态：</font></span>
                                            <select name="user.isavalible" id="isavalible">
                                                <option value="-1">请选择</option>
                                                <option value="0">正常</option>
                                                <option value="1">冻结</option>
                                            </select>
                                            <script>
                                                $("#isavalible").val('${user.isavalible}');
                                            </script>
                                        </li>
                                        <li><input type="button"  class="btn btn-danger" value="查询" name="" onclick="goPage(1)"/>
                                            <input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
                                            <input type="button"  class="btn btn-danger" value="导出Excel" name="" onclick="userExcel()"/>
                                        </li>
                                    </ul>

                                </div>
								<div class="clearfix"></div>
							</div>
						</caption>
						<thead>
							<tr>
								<th><span>ID</span></th>
								<th><span>昵称</span></th>
								<th><span>真实姓名</span></th>
								<th><span>性别</span></th>
								<th><span>手机</span></th>
								<!-- <th><span>邮箱</span></th> -->
								<th><span>注册时间</span></th>
                                <th><span>来源</span></th>
								<th><span>状态</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${list.size()>0}">
						<c:forEach  items="${list}" var="user" >
							<tr>
								<%-- <td><input type="checkbox" class="questionIds" id="${list.id }"/>&nbsp;${list.id }</td> --%>
								<td>${user.id }</td>
								<td>${user.nickname }</td>
								<td>${user.realname}</td>
								<td>
									<c:choose>
										<c:when test="${user.gender==1}">女</c:when>
										<c:otherwise>男</c:otherwise>
									</c:choose>
								</td>
								<td>${user.mobile }</td>
								<%-- <td>${user.email }</td> --%>
								<td><fmt:formatDate value="${user.createdate }" type="both"/></td>
                                <td>
                                    <c:if test="${user.registerFrom=='registerFrom' }">注册用户</c:if>
                                    <c:if test="${user.registerFrom=='OpenAppRegisterFrom' }">第三方登陆用户</c:if>
                                </td>
								<td id="isavalible${user.id}">
								<c:if test="${user.isavalible==0 }">正常</c:if>
								<c:if test="${user.isavalible==1 }">禁用</c:if>
								</td>
								<td  class="c_666 czBtn" align="center">
								<c:if test="${user.isavalible==0}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="禁用" href="javascript:updateIsavalible(${user.id},1)">禁用</a>
							     </c:if>
							      <c:if test="${user.isavalible==1}">
							        <a class="ml10 btn smallbtn btn-y attr${user.id}" title="恢复" href="javascript:updateIsavalible(${user.id},0)">恢复</a>
							     </c:if>
								<a class="ml10 btn smallbtn btn-y" title="修改密码" href="${ctx}/admin/user/toupdatepwd/${user.id}">修改密码</a>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${list.size()==0||list==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>还没有学员信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
					</form>
					<!-- /pageBar begin -->
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
		
</body>
</html>
