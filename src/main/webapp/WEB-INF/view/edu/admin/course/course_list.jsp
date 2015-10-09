<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>课程列表</title>
<script type="text/javascript" src="${ctximg}/static/common/multilevel.js"></script>
<script type="text/javascript">
	$(function() {
		var isavild='${queryCourse.isavaliable}'==''?-1:'${queryCourse.isavaliable}';
		$("#isavaliable").val(isavild);
	    var majorId='${queryCourse.majorId}'==''?-1:'${queryCourse.majorId}';
	    $("#majorId").val(majorId);
	    $("#sellType").val('${queryCourse.sellType}');
	    var param={
				data: eval('('+'${subjectList}'+')'),	//处理的数据（必选）数据格式：[{object Object},{object Object}] 
				showId:'showSubjectList',//显示的数据标签ID（必选）
				idKey:'subjectId',//数据的ID（必选）
				pidKey:'parentId',//数据的父ID（必选）
				nameKey:'subjectName',//数据显示的名（必选）
				returnElement:'subjectId',//返回选中的值（必选 ）
				majorId:'',//科目id
				//-----------------------------------------------------
				returnIds:'subjectLink',//返回所有级的ID，以“,”隔开（可选）
				initVal:'${queryCourse.subjectId}',//初始默认ID（可选）
				defName:'请选择',//默认显示的选项名（可选，如果不设置默认显示“请选择”）
				defValue:'0'//默认的选项值（可选，如果不设置默认是“0”）
			};
		ML._init(param);
	});
	
	function submitSearch(){
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	//清空
	function submitclear(){
		$("input:text").val('');
		$("#sellType").val('');
		$("#isavaliable").val(-1);
		$("#majorId").val(0);
		$("#showSubjectList :first-child").next().remove();
		$("#showSubjectList").find("select").val(0);
		$("#subjectId").val('');
		$("#subjectLink").val('');
	}
	
	//审核课程
	function verify(statu,id,obj){
		var tip='确定'+$(obj).html()+'？';
		(statu == 0) ?statu=1 :statu=0;
		if(confirm(tip)){
			$.ajax({
				url:'/admin/cou/verifyCourse',
				data:{'ids':id,'isavaliable':statu},
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert("系统繁忙！");
					}
				}
			});
		}
	}
	//审核全部课程
	function verifyAll(){
		var tip='确定审核全部课程？';
		if(confirm(tip)){
			$.ajax({
				url:'/admin/cou/verifyAllCourse',
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert("系统繁忙！");
					}
				}
			});
		}
	}
	function checkAll(obj){
		$(".courseBox").prop('checked', obj.checked);
	}
	function verifyBatch(){
		var str="";
		$(".courseBox").each(function(){
			if($(this).prop("checked")){
				str+=this.value+",";
			}
		});
		
		if(isEmpty(str)){
			alert("请至少选择一条信息");
			return;
		}
		//去除最后的逗号
		str=str.substring(0,str.length-1);
		if(confirm("确定审核选中的课程吗")){
			$.ajax({
				url:'/admin/cou/verifyCourse',
				data:{'ids':str,'isavaliable':1},
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success){
						alert(result.message);
						window.location.reload();
					}else{
						alert("系统繁忙！");
					}
				}
			});
		}
	}
</script>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head">
                <h4><em class="icon14 i_01"></em>&nbsp;<span>课程管理</span> &gt; <span>课程列表</span> </h4>
            </div>
            <!-- /tab1 begin-->
            <div class="mt20">
                <div class="commonWrap">
                		<form action="${ctx}/admin/cou/list" name="searchForm" id="searchForm" method="post">
                        <input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                 <div class="capHead">

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li><span class="ddTitle"><font>课程id：</font></span><input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="queryCourse.id" value="${queryCourse.id}" id="id" /></li>
                                            <li><span class="ddTitle"><font>老师姓名：</font></span><input type="text" name="queryCourse.teacherName" value="${queryCourse.teacherName}" /></li>
											 <li>
                                                <span class="ddTitle"><font>状态：</font></span>
                                                <select name="queryCourse.isavaliable" id="isavaliable" >
                                                    <option value="-1">请选择</option>
                                                    <option value="0">未审核</option>
                                                    <option value="1">已审核</option>
                                                </select>
                                            </li>
										</ul>
                                    </div>

                                    <div class="w50pre fl">
                                        <ul class="ddBar">
                                            <li>
                                                <span class="ddTitle"><font>专业：</font></span>
                                                <input type="hidden" name="queryCourse.subjectId" id="subjectId" value="${queryCourse.subjectId}"/>
												<input type="hidden" id="subjectLink"/>
												<span id="showSubjectList"></span>
                                            </li>
 											<li>
                                                <span class="ddTitle"><font>科目：</font></span>
                                                <select name="queryCourse.majorId" id="majorId" >
                                                    <option value="0">请选择</option>
                                                    <c:forEach items="${majorList}" var="maj">
                                                       <option value="${maj.id}">${maj.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                            <li>
                                                <span class="ddTitle"><font>课程类型：</font></span>
                                                <select name="queryCourse.sellType" id="sellType" >
                                                     <option value="">请选择</option>
                                                     <option value="1">一对一</option>
                                                     <option value="2">班课</option>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>
                                     <div   align="center">
                                        <input type="button" name="" value="查询" class="btn btn-danger" onclick="submitSearch()">
                                        <input type="button" name="" value="清空" class="btn btn-danger" onclick="submitclear()">
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                <div class="mt10 clearfix">
                                    <p class="fl czBtn">
                                        <span>
                                        &nbsp;
                                        </span>
                                    </p>
                                </div>
                                </form>
                                <div class="mt10 clearfix">
                                	<p class="fl czBtn">
										<span class="ml10"><a href="javascript:void(0)" onclick="verifyBatch()"><em class="icon14 new">&nbsp;</em>批量审核</a></span>
									</p>
									<p class="fl czBtn">
										<span class="ml10"><a href="javascript:void(0)" onclick="verifyAll()"><em class="icon14 new">&nbsp;</em>全部审核</a></span>
									</p>
								</div>
                            </caption>
                            <thead>
                                <tr>
                                    <th width="8%">
                                        <span><input type="checkbox" onclick="checkAll(this)"/>&nbsp;ID</span>
                                    </th>
                                    <th>
                                        <span>课程名称</span>
                                    </th>
                                    <th>
                                        <span>专业/科目</span>
                                    </th>
                                    <th>
                                        <span>课程类型</span>
                                    </th>
                                    <th>
                                        <span>发布人</span>
                                    </th>
                                    <th>
                                        <span>是否审核</span>
                                    </th>
                                    <th>
                                        <span>价格</span>
                                    </th>
                                    <th>
                                        <span>添加时间</span>
                                    </th>
                                    <th>
                                        <span>操作</span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="tabS_02" align="center">
                                <c:if test="${courseList.size()>0}">
                                    <c:forEach items="${courseList}" var="cou">
                                        <tr id="rem${cou.id }">
                                            <td><input type="checkbox" class="courseBox" value="${cou.id}"/>&nbsp;&nbsp;${cou.id}</td>
                                            <td>
                                            ${cou.name}
                                            </td>
                                            <td>${cou.subjectMajor.subjectName}&nbsp;${cou.subjectMajor.majorName}</td>
                                            <td>
                                            <c:if test="${cou.sellType=='1'}">一对一</c:if>
                                            <c:if test="${cou.sellType=='2'}">班课</c:if>
                                            </td>
                                            <td>
                                            	${cou.nickname}
                                            </td>
                                            <td>
                                                <c:if test="${cou.isavaliable==0}">未审核</c:if>
                                                <c:if test="${cou.isavaliable==1}">已审核</c:if>
                                            </td>
                                            <td>${cou.currentprice}</td>
                                            <td>
                                                <fmt:formatDate type="both" value="${cou.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </td>
                                            <td class="c_666 czBtn" align="center">
                                            	
                                            	<a id="verify" class="ml10 btn smallbtn btn-y" title="审核课程" href="javascript:void(0)" onclick="verify(${cou.isavaliable},${cou.id},this)">
                                            		<c:choose >
                                            			<c:when test="${cou.isavaliable==1}">关闭课程</c:when>
                                            			<c:otherwise>审核课程</c:otherwise>
                                            		</c:choose>
                                            	</a>
                                            	
                                                <a class="ml10 btn smallbtn btn-y" title="详情" href="${ctx}/admin/cou/infor/${cou.id}">详情</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${courseList.size()==0||courseList==null}">
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
