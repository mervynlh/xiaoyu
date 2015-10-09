<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<h5 class="c-333 fsize14">获取积分记录列表</h5>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab-integral mt10">
									<thead>
										<tr>
											<th width="30%">操作得分</th>
											<th width="30%">兑换礼品</th>
											<th width="20%">时间</th>
											<th width="20%">得分</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${userIntegralRecordList}" var="itegral">
										<tr>
											<td align="center">${itegral.templateName}</td>
											<td align="center">${itegral.giftName}</td>
											<td align="center"><fmt:formatDate value="${itegral.createTime}" type="both"/> </td>
											<td align="center">${itegral.score}</td>
										</tr>
									</c:forEach>
										
									</tbody>
								</table>
								<section class="mt5 mb5 tac">
								<jsp:include page="/WEB-INF/view/common/u_ajaxpage.jsp"></jsp:include>
								</section>