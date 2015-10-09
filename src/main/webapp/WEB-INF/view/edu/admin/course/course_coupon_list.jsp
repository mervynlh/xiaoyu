<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
<script type="text/javascript">
//存放数据的数组   
var myArrayMoveStock=new Array();   
//将小页面被选中的入库明细信息带到大页面   
function selectList() {   
    var courseChecked =document.getElementsByName("ids");   
    // 定义是否有产品被选中   
    var notSelect = true;  
    var overdue=false;
    var courseNames="";
    // 把被选中的入库明细传入数组   
    myArrayMoveStock=new Array();
    for(var i=0;i<courseChecked.length;i++){   
        if(courseChecked[i].checked==true){
        	 var records = courseChecked[i].value;   
        	    var instockmsg = new Array();   
        	    instockmsg = records.split("#");//以#分开获得数组   
        	    var courseId = instockmsg[0];   
        	    var courseName = instockmsg[1];  
        	    $.ajax({
        	    	url:"${ctx}/admin/cou/courseOverdue",
        	    	data:{"courseId":courseId},
        	    	type : "post",
        	    	dataType:"json",
        	    	async : false,
        	    	success:function(result){
        	    		if(result.message=='overdue'){
        	    			courseChecked[i].checked=false;
        	    			overdue=true;
        	    			courseNames+=courseName+",";
        	    		}
        	    	}
        	    });
        	    
        	    if(courseChecked[i].checked==true) { 
        	      	 toParentsValue(courseId,courseName);  
        	      	 notSelect = false ;   
        	     }
        	      
        }   
    }   
    
    if(overdue){
    	courseNames=courseNames.substring(0,courseNames.length-1);
    	alert("您选择的课程'"+courseNames+"'已过期，请重新选择")
    	return;
    }
  //没有入库明细被选择 
    if(notSelect){   
        alert("请选择课程");   
        return;   
    } 
   
    //调用父页面的方法  
    window.opener.getCourseList(myArrayMoveStock);   
    window.close();   
}   
 // 把选中产品的一条记录放到数组中   
function toParentsValue(courseId,courseName){   
      myArrayMoveStock.push([courseId,courseName]);   
}
function allCheck(th){
	$("input[name='ids']:checkbox").prop('checked',th.checked);
}
</script>
</head>
<body  >
		<form action="${ctx}/admin/cou/couponCourseList" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>
						&nbsp;
						<span>选取课程</span>
						&gt;
						<span>课程列表</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="clearfix">
										<div class="optionList">
											<span>
												<font>课程id：</font>
											</span>
											<span>
												<input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${course.id}" id="id" />
											</span>
										</div>
										<div class="optionList">
											<span>
												<font>课程名称：</font>
											</span>
											<span>
												<input type="text" name="queryCourse.name" value="${course.name}" id="name" />
											</span>
										</div>
										<div class="optionList">
											<input type="button" name="" value="查询" class="btn btn-danger" onclick="goPage(1)" />
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</caption>
							
							<thead>
								
								<tr>
									<th><span><input type="checkbox" onclick="allCheck(this)"/>全选</span></th>
									<th><span>ID</span></th>
		                            <th><span>课程名称</span></th>
		                            <th><span>价格</span></th>
		                            <th><span>添加时间</span></th>
								</tr>
							</thead>
							<tbody id="tabS_02" align="center">
								
								<c:if test="${courseList.size()>0}">
									<c:forEach items="${courseList}" var="cou">
										<tr>
											<td><input type="checkbox" name="ids" value="${cou.id}#${cou.name}" /></td>
											<td>${cou.id}</td>
											<td>${cou.name }</td>
											<td>${cou.currentprice}</td>
											<td>
												<fmt:formatDate type="both" value="${cou.addtime }" />
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${courseList.size()==0||courseList==null}">
									<tr>
										<td align="center" colspan="16">
											<div class="tips">
												<span>还没有课程数据！</span>
											</div>
										</td>
									</tr>
								</c:if>
								<tr>
									<td align="center" colspan="5">
										<a class="btn btn-danger" title="提 交" href="javascript:selectList()">确定</a>
										<a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a>
									</td>
								</tr>
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
					</div>
				</div>
		</form>
</body>
</html>
