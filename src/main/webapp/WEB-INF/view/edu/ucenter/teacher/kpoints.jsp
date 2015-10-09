<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript">
	//删除课程课节
	function delKpoint(id){
		dialog('',16,'','/uc/teacher/delKpoint?kpointId='+id+"&courseId="+$("#courseId").val());
	}
</script>
<div class="u-t-add-boy mt50">
		<div class="mb20">
			<div class="fl mr10 fsize14 f-fM c-666 hLh30">
				课程安排：
			</div>
			<div class="right-person-box-in-r fl u-teaname-btn">
				<a href="javascript:dialog('',18,'','');">添加课时</a>
			</div>	
			<div class="clear"></div>
		</div>
		<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="u-t-coupon-tab">
		<thead> 
			<tr> 
				<th style="width:5%;">
					
				</th> 
				<th style="width:50%;">
					课时名称
				</th>
				<th style="width:30%;">
					上课时间
				</th>
				<th style="width:15%;">
					编辑
				</th>
			</tr> 
		</thead>
		<tbody>
			<c:forEach items="${kpoints}" var="kpoints" varStatus="statu">
			<tr>
				<td>
					<span class="vam">
						<tt class="fsize14 f-fM c-999 vam mr5">${statu.index+1}</tt>
					</span>
				</td>
				<td>
					<span class="vam">
						<tt class="fsize14 f-fM c-999 vam mr5">${kpoints.name}</tt>
					</span>
				</td>
				<td>
					<span class="vam">
						<tt class="fsize14 f-fM c-999 vam mr10"><fmt:formatDate value="${kpoints.beginTime}" type="both" pattern="yyyy-MM-dd"/></tt>
						<tt class="fsize14 f-fM c-999 vam"><fmt:formatDate value="${kpoints.beginTime}" type="both" pattern="HH:mm"/>-<fmt:formatDate value="${kpoints.endTime}" type="both" pattern="HH:mm"/></tt>
					</span>
				</td>
				<td>
					<div class="right-person-box-in-r u-teaname-btn"> 
						<a href="javascript:dialog('',19,'${kpoints.id}','')" style="margin-bottom:0;">编辑</a> 
						<a href="javascript:delKpoint(${kpoints.id})" style="margin-bottom:0;">删除</a> 
					</div>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
