<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改讲师</title>
<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<style type="">
.nav{width: 400px;height:30px;line-height:30px;margin:10px 10px 10px 30px; float:left;padding-left: 10px;border: 1px solid #ddd;}
</style>
</head>
<body>
		
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>教师课时管理</span> &gt; <span>教师课时详情</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left"><span>日期时间<tt class="c_666 ml20 fsize12">
									</tt></span></th>
							<th align="left"><span>约课记录<tt class="c_666 ml20 fsize12">
							</tt></span></th>
						</tr>
						
					</thead>
					
					<tbody>
						<c:if test="${resultList.size() > 0 }">
						<c:forEach items="${resultList }" var="classHour">
						<tr>
							<td align="center" width="18%"><font color="red"><fmt:formatDate value="${classHour.get(0).dateDay}" type="both" pattern="yyyy-MM-dd EEEE"/></font>&nbsp;</td>
							<td style="padding:5px" width="82%">
								<c:forEach items="${classHour }" var="hour">
									<div class="nav">
										<span style="font-weight:bolder;">${hour.time}</span>
										<c:if test="${hour.status==2}"><span style="color:red;margin-left:50px">未被约课</span></c:if>
										<c:if test="${hour.status!=2}">
										<span style="margin-left: 20px"><span style="font-weight:bolder;">学生：</span>
											<c:if test="${hour.nickname==null || hour.nickname==''}">${hour.email}</c:if>
											<c:if test="${hour.nickname!=null && hour.nickname!=''}">${hour.nickname}</c:if>
										</span>
										<span style="margin-left: 20px"><span style="font-weight:bolder;">TELL：</span>${hour.mobile}</span>
										</c:if>
									</div>
								</c:forEach>
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${resultList.size()==0||resultList==null}">
							<tr>
								<td align="center" colspan="16">
									<div class="tips">
									<span>该讲师还未发布本周的课时安排信息！</span>
									</div>
								</td>
							</tr>
							</c:if>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
</body>
</html>
