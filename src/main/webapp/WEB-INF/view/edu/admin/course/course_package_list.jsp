<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程套餐列表</title>

<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>

<script type="text/javascript">



// 修改排序
function updateOrder(id){
	var orderNum = $("#orderNum_"+id).val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/cou/updateOrderNum",
		data:{"course.id":id,"course.orderNum":orderNum},
		success:function(result){
			if(result.success){ 
				alert(result.message);
				window.location.reload();
			}
		}
	})
}

//清空
function submitclear(){
	$("#name").val("");
	$("#subjectNameBtn").val("");
}

function submitSearch(){
	$("#searchForm").submit();
	}

	//删除课程
	function delCourse(id){
		if(confirm("是否删除?")){ 
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${ctx}/admin/cou/delPack/"+id+"/${courseId}",
				cache:true,
				success:function(result){
					if(result.success){ 
						location.reload() ;
					}
				}
			});
		}
	}
	//选择教师
	function showNewwin(){
		window.open('${ctx}/admin/cou/select','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	} 
	//选择教师
	function addnewArray(array){
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"${ctx}/admin/cou/addPack",
			data:{"courseId":'${courseId}',"ids":array.toString()},
			cache:true,
			success:function(result){
				if(result.success){ 
					location.reload() ;
				}
			}
		});
	} 
</script>
</head>
<body>
		<form action="${ctx}/admin/cou/pack/${course.id}" name="searchForm" id="searchForm" method="post">
			<input type="hidden" name="course.id" value="${course.id}"/>
			<input type="hidden" id="subjectId" name="course.subjectId" value="${course.subjectId }"/>
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em> &nbsp; <span>课程管理</span> &gt; <span>课程列表</span> &gt; <span>课程套餐列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="w50pre fl">
                                        <ul class="ddBar">
                                            <%-- <li><span class="ddTitle"><font>课程id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" /></li> --%>
                                            <li><span class="ddTitle"><font>课程名称：</font></span><input type="text" name="course.name" value="${course.name}" id="name" /></li>											
										</ul>
                                    </div>

                                    <%-- <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>课程ID：</font></span>
                                                <input id="subjectNameBtn" type="text" value="${course.id}" style="width:200px;" name="course.id"/>                                               
                                            </li>
                                            
                                        </ul>
                                    </div> --%>
                                     <div >
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>
									<div class="clearfix"></div>
								</div>
								<div class="mt10 clearfix">
									<p class="fl czBtn">
										<span>
											<a href="javascript:showNewwin()" title="新建">
												<em class="icon14 new">&nbsp;</em>
												添加
											</a>
										</span>
									</p>
								</div>
							</caption>
							<thead>
								<tr>
									<th width="6%"><span>Id</span></th>
									<th width="25%"><span>课程名称</span></th>
									<th width="10%"><span>价格</span></th>
									<th width="15%"><span>排序值</span></th>
									<th width="30%"><span>添加时间</span></th>
									<th width="14%"><span>操作</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								<c:if test="${CourseDtoList.size()>0}">
									<c:forEach items="${CourseDtoList}" var="cou">
										<tr id="rem${cou.id }">
											<td>${cou.id}</td>
											<td>${cou.name }</td>
											<td>${cou.currentprice}</td>
											<td><input type="text" name="course.orderNum" value="${cou.orderNum}" id="orderNum_${cou.id}" autocomplete="off"/></td>
											<td><fmt:formatDate type="both" value="${cou.addtime }" /></td>
											<td class="c_666 czBtn" align="center">
											<a class="ml10 btn smallbtn btn-y" title="修改" href="javascript:void(0)" onclick="updateOrder(${cou.id})">修改</a>
											<a class="ml10 btn smallbtn btn-y" title="删除" href="javascript:void(0)" onclick="delCourse(${cou.id})">删除</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${CourseDtoList.size()==0||CourseDtoList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有相关数据！</span>
											</div>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
		</form>
</body>
</html>
