<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程评论列表</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
// 控制日期显示的IQUERY
$(function(){
	  $( "#startDate,#endDate" ).datetimepicker(
	    		{regional:"zh-CN",
                changeYear: true,
	    		changeMonth: true,
	    		dateFormat:"yy-mm-dd ",
	    		timeFormat: "HH:mm:ss"
	    		});
	
});
//提交查询
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
//对评论内容进行隐藏显示
function updateIsavalible(id,isavalible){
	var str = "";
	if(isavalible==1){str= "是否隐藏该条信息";}
	if(isavalible==0){str= "是否显示该条信息";}
	if(confirm(str)){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/courseAssess/update",
			data:{"courseAssess.id":id,"courseAssess.status":isavalible},
			async:false,
			success:function(result){
				if(result.success){
					if(isavalible==1){
						$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",0)");
						$(".attr"+id).html("显示");
						$(".attr"+id).attr("title","显示");
						$("#isavalible"+id).html("隐藏");
						alert("隐藏成功");
					}else{
						$(".attr"+id).attr("href","javascript:updateIsavalible("+id+",1)");
						$(".attr"+id).html("隐藏");
						$(".attr"+id).attr("title","隐藏");
						$("#isavalible"+id).html("显示");
						alert("显示成功");
					}}}
		});
	}
}
//清空
function clean(){
	$("#nickname,#useremail,#courseName,#pointName,#startDate,#endDate").val("");
}
//删除资讯
function delCourseAssessbatch(){
	var artIds=document.getElementsByName("courseAssessIds");
	var str="";
	var checked=true;
	$(artIds).each(function(){
		if($(this).prop("checked")){
			str+=this.value+",";
			checked=false;
		}
	});
	if(checked){
		alert("请至少选择一条信息");
		return;
	}
	if(confirm("确定删除所选信息吗？")){
		$.ajax({
			url:"${ctx}/admin/courseAssess/del",
			data:{"courseAssessIds":str},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.success){
					window.location.reload();
				}else{
					alert("系统繁忙稍后重试");
					return;
				}
			}
		});
	}
}
</script>
</head>
<body  >
<form action="${ctx}/admin/courseAssess/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>课程评论管理</span> &gt; <span>课程评论列表</span> </h4>
</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class="w50pre fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>用户名：</font></span>
								<input type="text" name="queryCourseAssess.nickname" value="${queryCourseAssess.nickname}" id="nickname" />
							</li>
                            <li>
                                <span class="ddTitle"><font>课程名：</font></span>
                                <input type="text" name="queryCourseAssess.courseName" value="${queryCourseAssess.courseName}" id="courseName" />
                            <li>
                            </li>
							<li>
								<span class="ddTitle"><font>开始日期：</font></span>
								<input type="text" name="queryCourseAssess.startDate" value="${queryCourseAssess.startDate}"  id="startDate" class="AS-inp"/>
							</li>
						</ul>
						
					</div>

                    <div class="w50pre fl">
                        <ul class="ddBar">
                            <li>
                                <span class="ddTitle"><font>邮箱：</font></span>
                                <input type="text" name="queryCourseAssess.email" value="${queryCourseAssess.email}" id="useremail" />
                            </li>
                            <li>
                                <span class="ddTitle"><font>视频名：</font></span>
                                <input type="text" name="queryCourseAssess.pointName" value="${queryCourseAssess.pointName}" id="pointName" />
                            <li>
                            <li>
                                <span class="ddTitle"><font>结束日期：</font></span>
                                <input type="text" name="queryCourseAssess.endDate" value="${queryCourseAssess.endDate}"  id="endDate" class="AS-inp"/>
                            </li>
                            <li>
                                <input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
                                <input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
                            </li>

                        </ul>

                    </div>
                    <div class="clearfix"></div>
				</div>
				<div class="mt10 clearfix">
					<p class="fl czBtn">
					<span class="ml10"><a href="javascript:delCourseAssessbatch();" title="删除"><em class="icon14 delete">&nbsp;</em>删除</a></span>
					</p>
				</div> 
			</caption>
			<thead>
				<tr>
					<th><span>&nbsp;ID</span></th>
					<!-- <th  width="30%"><span>试题内容</span></th> -->
					<th><span>学员昵称</span></th>
					<th><span>学员邮箱</span></th>
					<!-- <th><span>学员年龄</span></th> -->
					<th><span>课程名称</span></th>
					<th><span>视频名称</span></th>
					<th><span>评论内容</span></th>
					<!-- <th><span>学员学校</span></th> -->
					<th><span>评论时间</span></th>
					<th><span>状态</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${courseAssessList.size()>0}">
			<c:forEach  items="${courseAssessList}" var="cou" >
				<tr>
					<td><input type="checkbox" name="courseAssessIds" value="${cou.id }"/>&nbsp;${cou.id }</td>
					<td>${cou.nickname }</td>
					<td>${cou.email }</td>
					<td>${cou.courseName }</td>
					<td>${cou.pointName }</td>
					<td>
					${fn:substring(cou.shortContent,0,50)}


					</td>
					<td><fmt:formatDate value="${cou.createTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="isavalible${cou.id}">
					<c:if test="${cou.status==0 }">显示</c:if>
					<c:if test="${cou.status==1 }">隐藏</c:if>
					</td>
					<td  class="c_666 czBtn" align="center">
					<c:if test="${cou.status==0}">
				        <a class="ml10 btn smallbtn btn-y attr${cou.id}" title="隐藏" href="javascript:updateIsavalible('${cou.id}',1)">隐藏</a>
				     </c:if>
				      <c:if test="${cou.status==1 }">
				        <a class="ml10 btn smallbtn btn-y attr${cou.id}" title="显示" href="javascript:updateIsavalible('${cou.id}',0)">显示</a>
				     </c:if> 
					<a class="ml10 btn smallbtn btn-y" title="查看" href="${ctx}/admin/courseAssess/info/${cou.id}">查看</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${courseAssessList.size()==0||courseAssessList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有课程评论信息！</span>
						</div>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<!-- /pageBar begin -->
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		<!-- /pageBar end -->
	</div><!-- /commonWrap -->
</div>
<!-- 内容 结束 -->
</form>
</body>
</html>
