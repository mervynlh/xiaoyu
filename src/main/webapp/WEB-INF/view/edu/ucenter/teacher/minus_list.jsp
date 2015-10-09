<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>课时优惠列表</title>
	<script type="text/javascript">
		//添加课时
		function addMinus(){
			$("#detailsError").html("");
			var details=$("#details").val();
			var minusNum=$("#minusNum").val();
			var discount=$("#discount").val();
			if(details==null||details==''){
				$("#detailsError").html("请输入简介");
				return;
			}
			if(discount==null || discount==''){
				discount=='0';
			}
			$.ajax({
				url : baselocation + "/uc/teacher/addMinus",
				data : {
					"minus.details" : details,
					"minus.minusNum" : minusNum,
					"minus.discount" : discount
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(result) {
					dialogClose();
					if(result.success){
						dialog(result.message,17,'','/uc/teacher/minusList');
					}else{
						dialog(result.message,10,'','');
					}
				}
			});
		}
		//修改课时优惠
		function updateMinus(){
			$("#detailsError").html("");
			var details=$("#details").val();
			var minusNum=$("#minusNum").val();
			var discount=$("#discount").val();
			if(details==null||details==''){
				$("#detailsError").html("请输入简介");
				return;
			}
			if(discount==null || discount==''){
				discount=='0';
			}
			$.ajax({
				url : baselocation + "/uc/teacher/updateMinus",
				data : {
					"minus.id":$("#minusId").val(),
					"minus.details" : details,
					"minus.minusNum" : minusNum,
					"minus.discount" : discount
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(result) {
					dialogClose();
					if(result.success){
						dialog(result.message,17,'','/uc/teacher/minusList');
					}else{
						dialog(result.message,10,'','');
					}
				}
			});
		}
		//删除课时优惠
		function delMinus(id){
			dialog('',16,'','/uc/teacher/delMinusById/'+id);
		}
	</script>
</head>
<body>
<div class="u-m-right">
		<article class="u-m-center"> 
			<section class="u-m-c-wrap"> 
				<section class="u-T-head">
					<a href="javascript:dialog('',14,'','');" class="u-T-head-tj fl">
						添加课时折扣
					</a>
					<p class="mt10 fsize12 c-999 f-fM fl ml30">课时折扣适用于您提供的所有授课课程，合理的折扣能够吸引学生购买更多课时，建议越长的课时优惠包提供越大的折扣！<br/>课时优惠最多可添加三个，请合理安排！</p>
					<div class="clear"></div>
				</section>
				<c:if test="${empty minusList}">
				<section class="u-T-body">
					<div class="u-T-body-in mt30">
						<div class="u-T-body-infor tac pb20 lin-2">
							<p class="c-999 fsize18 f-fM tac disIb">暂无折扣，如需添加，请</p>
							<a href="javascript:dialog('',14,'','');" class="c-master fsize20 f-fM tac disIb">添加折扣</a>
							<div class="clear"></div>
						</div>
					</div>
				</section>
				</c:if>
				<c:if test="${!empty minusList}">
				<section class="u-T-body">
					<div class="u-T-body-in mt30">
						<ul class="u-t-add-dis-list">
							<c:forEach items="${minusList}" var="minus">
							<li>
								<div class="fl w50pre of">
									<p>
										<tt class="fsize18 c-333 f-fM ml5">${minus.minusNum}小时</tt>
										<c:choose>
											<c:when test="${!empty minus.discount && minus.discount!=0.00}">
											<tt class="fsize12 c-org f-fM ml20">${minus.discount}折</tt>
											</c:when>
											<c:otherwise><tt class="fsize12 c-999 f-fM ml20">无折扣</tt></c:otherwise>
										</c:choose>
										
									</p>
									<p class="mt10">
										<tt class="fsize12 c-666 f-fM ">
											${minus.details}
										</tt>
									</p>
								</div>
								<div class="btn-r fr">
									<a href="javascript:dialog('',15,'${minus.id}','');" class="btn-in-1 btn-in">编辑</a>
									<a class="btn-in-1 btn-in" href="javascript:delMinus(${minus.id})">删除</a>
								</div>
								<div class="clear"></div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</section>
				</c:if>
			</section>   
		</article>
	</div>
</body>
</html>