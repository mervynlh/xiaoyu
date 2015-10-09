<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>教师视频直播列表</title>
</head>
<body  >
<form action="${ctx}/admin/teacher/zoom/meetings/querylist/${zoomUserId}" name="searchForm" id="searchForm" method="post">
<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
<!-- 内容 开始  -->
<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>讲师视频直播管理</span> &gt; <span>讲师视频直播列表</span> </h4>
</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<%--<caption>
							<div class="capHead">
								<div class="fl">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>教师姓名：</font></span>
											<input type="text" name="queryTeacher.name" value="${queryTeacher.name}" id="tcName" />
											<span class="ddTitle"><font>讲师状态：</font></span>
											<select name="queryTeacher.status" id="isStar">
												<option value="0" >--请选择--</option>
												<option value="1" >已审核</option>
												<option value="2" >未审核</option>
											</select>
											<span class="ddTitle"><font>韩教授认证状态：</font></span>
											<select name="queryTeacher.isProfessor" id="isProfessor">
												<option value="0" >--请选择--</option>
												<option value="1" >未认证</option>
												<option value="2" >已认证</option>
												<option value="3" >申请认证</option>
											</select>
											&lt;%&ndash;  <span class="ddTitle"><font>试卷名称：</font></span>
											<input type="text" name="paperErrorCheck.content" value="${paperErrorCheck.content}" id="paperName" /> &ndash;%&gt;
											&lt;%&ndash;<span class="ddTitle"><font>试题内容：</font></span>
											<input type="text" name="paperErrorCheck.qstName" value="${paperErrorCheck.qstName}" id="qstName" /> &ndash;%&gt;
											<input type="button"  class="btn btn-danger" value="查询" name="" onclick="submitSearch()"/>
											<input type="button"  class="btn btn-danger" value="清空" name="" onclick="tcClean()"/>
										</li>
									</ul>

								</div>
								<div class="clearfix"></div>
							</div>
							&lt;%&ndash; <div class="mt10 clearfix">
								<p class="fl czBtn">
								 <span><a href="${ctx}/admin/teacher/toAdd" title="新建讲师"><em class="icon14 new">&nbsp;</em>新建讲师</a></span>
								</p>
							</div>  &ndash;%&gt;
						</caption>--%>

						<thead>
							<tr>
								<th width="8%"><span>直播ID</span></th>
								<th><span>URL地址</span></th>
								<th><span>直播标题</span></th>
								<th><span>开始时间</span></th>
								<th><span>持续时间(分)</span></th>
								<th><span>直播类型</span></th>
								<th><span>创建时间</span></th>
								<th><span>直播状态</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02" align="center">
						<c:if test="${meetingsList.size()>0}">
						<c:forEach  items="${meetingsList}" var="meeting" >
							<tr id="rem${meeting.id }">
								<td>${meeting.id }</td>
								<td>${meeting.start_url }</td>
								<td>${meeting.topic }</td>
								<td>${meeting.start_time}</td>
								<td>${meeting.duration}</td>
								<td>
									<c:if test="${meeting.type==1}">即时直播</c:if>
									<c:if test="${meeting.type==2}">计划直播</c:if>
								</td>
								<td>${meeting.created_at}</td>
								<td>
									<c:if test="${meeting.status == 0}">未开始</c:if>
									<c:if test="${meeting.status == 1}">已开始</c:if>
									<c:if test="${meeting.status == 2}">已结束</c:if>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${meetingsList.size()==0||meetingsList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>改讲师还没有直播视频！</span>
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
