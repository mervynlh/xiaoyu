<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>课程详情</title>
	<script type="text/javascript">
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
	</script>
</head>
<body >
		<form action="${ctx}/admin/cou/update" method="post" id="updateCourse">
		<input type="hidden" value="${course.id}" name="course.id" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>课程管理</span> &gt; <span>课程详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 课程基本属性 <tt class="c_666 ml20 fsize12">
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"> &nbsp;课程名称</td>
							<td>
								${course.name}
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;销售形式</td>
							<td>
								<select id="sellType" disabled="disabled" >
									<option value="1">一对一</option>
									<option value="2">班课</option>
								</select>
							</td>
						</tr>
						<script>
							$("#sellType").val('${course.sellType}');
						</script>
						<tr>
							<td width="20%" align="center"> &nbsp;专业/科目</td>
							<td width="80%">
							${course.subjectMajor.subjectName}&nbsp;${course.subjectMajor.majorName}
							</td> 
						</tr>
						<tr>
							<td align="center"> &nbsp;课程价格</td>
							<td>${course.currentprice}</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;详细价格</td>
							<td>
								<c:forEach items="${course.courseModelMap}" var="map">
									<c:if test="${map.key=='TEACHERVISIT'}">老师上门</c:if>
									<c:if test="${map.key=='STUDENTVISIT'}">学生上门</c:if>
									<c:if test="${map.key=='TALKADDRESS'}">协商地点</c:if>
									<c:if test="${map.key=='ONLINEOTO'}">在线教学</c:if>
									<c:if test="${map.key=='ONLINECOU'}">在线授课</c:if>
									<c:if test="${map.key=='LINEDOWNCOU'}">线下面授</c:if>
									:￥${map.value}<br/>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;发布人</td>
							<td>
								${course.teacherName}
							</td>
						</tr>
						<c:if test="${course.sellType=='2'}">
						<tr>
							<td align="center"> &nbsp;报名最大人数</td>
							<td>${course.peopleNum}
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;是否可插班</td>
							<td>
								<c:if test="${course.isJoinClass=='SURE'}">可以插班</c:if>
								<c:if test="${course.isJoinClass=='CANNOT'}">不可以插班</c:if>
							</td>
						</tr>
						<c:if test="${course.isJoinClass=='SURE'}">
						<tr>
							<td align="center"> &nbsp;插班价格</td>
							<td>
								￥${course.joinPrise}
							</td>
						</tr>
						</c:if>
						<tr>
							<td align="center"> &nbsp;是否完成</td>
							<td>
								<c:if test="${course.isFinsh=='SUREJOIN'}">可以报名</c:if>
								<c:if test="${course.isFinsh=='NOJOIN'}">不可以报名</c:if>
								<c:if test="${course.isFinsh=='FINSHED'}">已完成</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;课程图片</td>
							<td>
								<img src="<%=staticImageServer%>${course.classImgs}" width="200px;" height="200px;"/>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;学习目标</td>
							<td>${course.learningTarget}</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;课程简介</td>
							<td>${course.title}</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;课程详情</td>
							<td>${course.context}</td>
						</tr>
						</c:if>
						<tr>
							<td align="center"> &nbsp;发布时间</td>
							<td>
							<fmt:formatDate value="${course.addtime}" type="both"/>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;更新时间</td>
							<td>
							<fmt:formatDate value="${course.updateTime}" type="both"/>
							</td>
						</tr>
						<tr>
							<td align="center"> &nbsp;审核状态</td>
							<td><select name="course.isavaliable" id="isavaliable" disabled="disabled">
									<option value="0">未审核</option>
									<option value="1">已审核</option>
							</select></td>
						</tr>
						<script>
							$("#isavaliable").val('${course.isavaliable}');
						</script>
						<c:if test="${course.sellType==2}">
						<tr>
							<td align="center"> &nbsp;总课时</td>
							<td>${course.lessionnum}节</td>
						</tr>
						<c:if test="${!empty kpoints}">
							<tr>
								<td align="center"> &nbsp;课节</td>
								<td>
									<c:forEach items="${kpoints}" var="kpoint">
										课节名：${kpoint.name}&nbsp;&nbsp;
										开课时间:<fmt:formatDate value="${kpoint.beginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;
										结束时间:<fmt:formatDate value="${kpoint.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
										<br/>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						<tr>
							<td align="center"> &nbsp;班课人数</td>
							<td>${course.peopleNum}人</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td align="center"> &nbsp;上课地址</td> -->
<%-- 							<td>${course.address}</td> --%>
<!-- 						</tr> -->
						</c:if>
						<tr>
							<td align="center" colspan="2">
								<c:choose>
									<c:when test="${course.isavaliable==1}">
										<a class="btn btn-danger" title="关闭课程" href="javascript:void(0)" onclick="verify(1,${course.id},this)">关闭课程</a>
									</c:when>
									<c:otherwise><a class="btn btn-danger" title="审核课程" href="javascript:void(0)" onclick="verify(0,${course.id},this)">审核课程</a></c:otherwise>
								</c:choose>
								&nbsp;<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
