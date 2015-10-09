<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
<title>我的积分</title>
<script type="text/javascript">
$(function(){
	ajaxPage("/uc/ajax/intrecord","",1,integralRecord);
});
function integralRecord(result){
	$(".inteRecord").html(result);
}
</script>
</head>
<body>
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="#" title="我的积分管理">我的积分管理</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl20 pr15">
							<section class="mt40 pb20 line2">
								<h4 class="hLh30">
									<span class="c-333 fsize14">我的积分：</span>
									<strong class="c-red f-fG fsize24">${userIntegral.currentScore==null?0:userIntegral.currentScore}</strong>
									<a href="${ctx}/uc/mygift" title="我的礼品" class="ml50 c-red fsize12 unFw"><u>我的礼品&gt;&gt;</u></a>
								</h4>
							</section>
							<section class="inteRecord mt30">
								
							</section>
							<section class="mt15">
								<h5 class="c-333 fsize14">我的积分有什么用途？</h5>
								<p class="c-666 mt5 fsize14">您的积分可以通过<a href="${ctx}/uc/integift" title="" class="c-red"><u>兑换礼品</u></a>来换取相应奖品， 现在就通过在快乐的学习过程中去获取积分吧！</p>
							</section>
							<!-- 已做完 -->
							<section class="mt30">
								<h5 class="c-333 fsize14">我都可以通过哪些操作来获得积分？</h5>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab-integral mt10">
									<thead>
										<tr>
											<th width="40%">以下操作来获得积分</th>
											<th width="30%">获得积分</th>
											<!-- <th width="30%">备注(每日)</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userIntegralTemplateList}" var="integralTemplate">
										<!-- 只显示状态为正常的模板 -->
										<c:if test="${integralTemplate.status==0}">
										<tr>
											<td>${integralTemplate.name}</td>
											<td>${integralTemplate.showScore}</td>
										</tr>
										</c:if>
										</c:forEach>
									</tbody>
								</table>
							</section>
						</div>
					</section>
				</section>
			</article>
	<!-- /u-main end -->
	
</body>
</html>
