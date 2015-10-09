<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>科目列表</title>
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
    <link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
    <script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript">
		function submitclear(){
			$("#id").val("");
			$("#status").val("-1");
			$("#name").val("");
		}
		function submitSearch(){
			
			$("#searchForm").submit();
		
		}
		function  updateStatus(id,status){
			   $.ajax({
				  url:"${ctx}/admin/mgor/update", 
				  data:{"id":id,"status":status} ,
				  dataType:"json",
				  type:"post",
				  success:function(result){
					  	if(result.success){
					  		alert("冻结成功！");
					  		window.location.reload();
					  		
					  	}
					  
				  },
				  error:function(error){
					  
					  alert("冻结失败！");
				  }
			   });
		}
	    
	    function  thawStatus(id,status){
		    $.ajax({
		    	url:"${ctx}/admin/mgor/update",
		    	data:{"id":id,"status":status},
		    	dataType:"json",
		    	type:"post",
		    	success:function(result){
		    		  if(result.success){    			  
		    			  alert("解冻成功！");
		    			  window.location.reload();
		    		  }		
		    	},	    
		    	error:function(error){
		    		alert("解冻失败！");
		    		return;
		    	}
		    	
		    });	
	    }
	    
	    function  deleteStatus(id,status){
	    	if(confirm("确定删除么？")){
		    $.ajax({
		    	url:"${ctx}/admin/mgor/update",
		    	data:{"id":id,"status":status},
		    	dataType:"json",
		    	type:"post",
		    	success:function(result){
		    		  if(result.success){    			  
		    			  alert("删除成功！");
		    			  window.location.reload();
		    		  }
		    		  
		    	},	    
		    	error:function(error){
		    		alert("删除失败！");
		    		return;
		    	}
		    	
		    });	
		    
	    	}
	    }

	

</script>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head">
                <h4><em class="icon14 i_01"></em>&nbsp;<span>科目管理</span> &gt; <span>科目列表</span> </h4>
            </div>
            <!-- /tab1 begin-->
            <div class="mt20">
                <div class="commonWrap">
                		<form action="${ctx}/admin/magor/page" name="searchForm" id="searchForm" method="post">
                        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                 <div class="capHead">

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>科目id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="major.id"  id="id" /></li>

										
										</ul>
                                    </div>

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>科目名称：</font></span><input type="text" name="major.name" value="${major.name}" id="name" />
                                            </li>                                     
                                        </ul>
                                    </div>
                                     <div  align="center">
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                <div class="mt10 clearfix">
                                    <p class="fl czBtn">
                                        <span>
                                            <a href="${ctx}/admin/mgor/add/tz" title="新建">
                                                <em class="icon14 new">&nbsp;</em>
                                                新建科目
                                            </a>
                                        </span>
                                    </p>
                                </div>
                                </form>
                            </caption>

                            <thead>
                                <tr>
                                    <th width="8%">
                                        <span>ID</span>
                                    </th>
                                    <th>
                                        <span>科目名称</span>
                                    </th>
                        
                                    <th>
                                        <span>排序</span>
                                    </th>
                                   
                                    <th>
                                        <span>操作</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                             
                                <c:if test="${maList.size()>0}">
                               
                                    <c:forEach varStatus="index" items="${maList}" var="maList" >
                                    	<c:if test="${ maList.status=='0'}">
                                        <tr id="rem${maList.id }">
                                            <td>${maList.id}</td>
                                            <td>${maList.name }</td>                                 
                                            <td> ${maList.sort}</td>
                                            
                                            <td class="c_666 czBtn" align="center">
                                            <c:if test="${maList.status=='0'}">
                                            	<a class="ml10 btn smallbtn btn-y" title="冻结"  onclick="deleteStatus(${maList.id},2)">删除</a>
                                            </c:if>
                                            </td>
                                        </tr>
                                        </c:if>
                                    </c:forEach>
                              
                                </c:if>
                             
                                <c:if test="${maList.size()==0||maList==null}">
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
                        <jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
                </div>
            </div>

</body>
</html>
