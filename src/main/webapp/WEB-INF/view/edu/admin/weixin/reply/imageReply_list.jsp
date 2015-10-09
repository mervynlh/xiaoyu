<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript">
//存放数据的数组   
var myArrayMoveStock=new Array();   
//将小页面被选中的入库明细信息带到大页面   
function selectList() {   
    var imageChecked =document.getElementsByName("ids");   
    // 定义是否有产品被选中   
    var notSelect = true;   
    // 把被选中的入库明细传入数组   
    for(var i=0;i<imageChecked.length;i++){   
        if(imageChecked[i].checked==true){   
            toParentsValue(imageChecked[i]);   
            notSelect = false ;   
        }   
    }   
    //没有入库明细被选择   
    if(notSelect){   
        alert("请选择图文素材");   
        return;   
    }     
    //调用父页面的方法  
    window.opener.getImageList(myArrayMoveStock);   
    window.close();   
}   
 // 把选中产品的一条记录放到数组中   
function toParentsValue(obj){   
    var records = obj.value;   
    var instockmsg = new Array();   
    instockmsg = records.split("#");//以#分开获得数组   
    var imageId = instockmsg[0];   
    var imageName = instockmsg[1];   
      
    if(obj.checked==true) {   
        myArrayMoveStock.push([imageId,imageName]);   
    }    
}
function cancel(){
	$("#title").val("");
}
function allCheck(th){
	$("input[name='ids']:checkbox").prop('checked',th.checked);
}
</script>
</head>
<body>
<!-- 内容 开始  -->

	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>微信管理</span> &gt; <span>微信图文素材列表</span> </h4>
	</div>
	<!-- /tab1 begin-->
	<div class="mt20">
		<div class="commonWrap">
			<form action="${ctx}/admin/weixin/imagepage" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
				<caption>
					<div class="capHead">
						<div class="fl">
							<ul class="ddBar">
								<li>
									<span class="ddTitle"><font>标题：</font></span>
									<input type="text" name="queryWeixinReply.title" id="title" value="${queryWeixinReply.title}"/>
									<input type="button"  class="btn btn-danger" value="查询"  onclick="goPage(1)"/>
									<input type="button"  class="btn btn-danger" value="清空"  onclick="cancel()"/>
								</li>
							</ul>
							
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="mt10 clearfix">
						<p class="fl czBtn"></p>
						<p class="fr c_666">
							<span>图文素材列表</span>
							<span class="ml10">共查询到&nbsp;${page.totalResultSize }&nbsp;条记录，当前第&nbsp;${page.currentPage }/${page.totalPageSize }&nbsp;页</span>
						</p>
					</div>
				</caption>
				<thead>
					<tr> 	 	 	
						<th><span><input type="checkbox" onclick="allCheck(this)"/>全选</span></th> 	 	 	
						<th><span>ID</span></th>
                           <th><span>关键字</span></th>
                           <th><span>标题</span></th>
                           <th><span>创建时间</span></th>
					</tr>
				</thead>
				<tbody id="tabS_02" align="center">
				<c:if test="${weixinReplys.size()>0}">
				<c:forEach  items="${weixinReplys}" var="weixinReply" >
					<tr>
						<td><input type="checkbox" name="ids" value="${weixinReply.id}#${weixinReply.title}" /></td>
						<td>${weixinReply.id }</td>
						<td>${weixinReply.keyword }</td>
						<td><font color="red">${weixinReply.title }</font></td>
						<td>
							<fmt:formatDate value="${weixinReply.createTime}" type="both"  pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<c:if test="${weixinReplys.size()==0||weixinReplys==null}">
					<tr>
						<td align="center" colspan="16">
							<div class="tips">
							<span>还没有微信图文素材！</span>
							</div>
						</td>
					</tr>
					</c:if>
					<tr>
						<td align="center" colspan="5">
							<a class="btn btn-danger" title="确定" href="javascript:selectList()">确定</a>
							<a class="btn btn-danger" title="返 回" href="javascript:window.close();">取消</a>
						</td>
					</tr>
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
