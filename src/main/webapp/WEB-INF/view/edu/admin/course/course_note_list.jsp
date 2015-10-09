<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程笔记列表</title>
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
	    		timeFormat: "HH:mm:ss",
	    		});
	
});
function submitSearch(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
	}
function updateIsavalible(id,isavalible){
	var str = "";
	if(isavalible==1){str= "是否隐藏该条信息";}
	if(isavalible==0){str= "是否显示该条信息";}
	if(confirm(str)){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/courseNote/update",
			data:{"courseNote.id":id,"courseNote.status":isavalible},
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
					}
					
				}
			}
		});
	}
}
function clean(){
	$("#nickname,#pointName,#startDate,#endDate").val("");
}
</script>
</head>
<body  >
<form action="${ctx}/admin/courseNote/list" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>课程笔记管理</span> &gt; <span>课程笔记列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
<div class="mt20">
	<div class="commonWrap">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01" >
			<caption>
				<div class="capHead">
					<div class=" fl">
						<ul class="ddBar">
							<li>
								<span class="ddTitle"><font>用户名/邮箱：</font></span>
								<input type="text" name="queryCourseNote.keyword" value="${queryCourseNote.keyword}" id="nickname" />
								<span class="ddTitle"><font>视频名：</font></span>
								<input type="text" name="queryCourseNote.pointName" value="${queryCourseNote.pointName}" id="pointName" />
							</li>
							<li>
								<span class="ddTitle"><font>开始日期：</font></span>
								<input type="text" name="queryCourseNote.startDate" value="${queryCourseNote.startDate}"  id="startDate" class="AS-inp"/>
								<span class="ddTitle"><font>结束日期：</font></span>
								<input type="text" name="queryCourseNote.endDate" value="${queryCourseNote.endDate}"  id="endDate" class="AS-inp"/>
								<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
								<input type="button"  class="btn btn-danger" value="清空" name="" onclick="clean()"/>
							</li>
						</ul>
						
					</div>
					<div class="clearfix"></div>
				</div>
			</caption>
			<thead>
				<tr>
					<th width="6%"><span>&nbsp;ID</span></th>
					<th><span>学员昵称</span></th>
					<th><span>学员邮箱</span></th>
                    <th><span>课程名称</span></th>
					<th><span>章节名称</span></th>
					<th><span>笔记内容</span></th>
					<th><span>笔记时间</span></th>
					<th><span>状态</span></th>
					<th><span>操作</span></th>
				</tr>
			</thead>
			<tbody id="tabS_02" align="center">
			<c:if test="${queryCourseNoteList.size()>0}">
			<c:forEach  items="${queryCourseNoteList}" var="cou" >
				<tr>
					<td><%-- ${fn:substring(cou.content,0,50)}<input type="checkbox" name="courseAssessIds" value="${cou.id }"/> --%>&nbsp;${cou.id }</td>
					<td>${cou.nickname }</td>
					<td>${cou.email }</td>
                    <td>${cou.courseName }</td>
					<td>${cou.pointName }</td>
					<td>${cou.shortContent }</td>
					<td><fmt:formatDate value="${cou.updateTime }" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="isavalible${cou.id}">
					<c:if test="${cou.status==0 }">显示</c:if>
					<c:if test="${cou.status==1 }">隐藏</c:if>
					</td>
					<td  class="c_666 czBtn" align="center">
					<c:if test="${cou.status==0}">
				        <a class="ml10 btn smallbtn btn-y attr${cou.id}" title="隐藏" href="javascript:updateIsavalible(${cou.id},1)">隐藏</a>
				     </c:if>
				      <c:if test="${cou.status==1 }">
				        <a class="ml10 btn smallbtn btn-y attr${cou.id}" title="显示" href="javascript:updateIsavalible(${cou.id},0)">显示</a>
				     </c:if>
					<a class="ml10 btn smallbtn btn-y" title="查看" href="${ctx}/admin/courseNote/info/${cou.id}">查看</a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${queryCourseNoteList.size()==0||queryCourseNoteList==null}">
				<tr>
					<td align="center" colspan="16">
						<div class="tips">
						<span>还没有课程笔记信息！</span>
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
