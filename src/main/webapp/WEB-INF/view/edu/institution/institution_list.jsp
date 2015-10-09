<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
	<title>入驻机构--小雨在线教育</title>
	<style type="text/css">
		.n-l-menu dl dd {display: none;}
		.n-l-menu dl:hover dd{display: block;}
	</style>
</head>
<body>
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<section class="container">
				<div class="mt50">
					<div class="rzjg-list">
						<ul class="clearfix">
							<c:if test="${not empty institutionList }">
								<c:forEach var="institution" items="${institutionList }">
									<li>
										<div class="rzjg-list-box mr30">
											<a href="" class="rzjg-list-tu mr20">
												<img src="<%=staticImageServer %>${institution.instPictureUrl }" alt="">
											</a>
											<div class="rzjg-list-wz">
												<a href="" class="fsize16 c-666 f-fM dis txtOf hLh30">${institution.name }</a>
												<p class="fsize12 c-999 f-fM">${institution.description }</p>
											</div>
											<div class="clear"></div>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
				<c:if test="${empty institutionList }">
					<section class="mt50">
						<div class="Paging tac">
							<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
						</div>
					</section>
				</c:if>
			</section>
		</div>
	</section>
</body>
</html>