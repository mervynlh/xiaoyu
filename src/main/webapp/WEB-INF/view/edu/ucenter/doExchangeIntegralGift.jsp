<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>积分兑换</title>
<script type="text/javascript">
function exchangeIntegral(){
	var giftId=$("#giftIdHidden").val();
	var addressId=$('input[name="address"]:checked').val();
	if(addressId==null||addressId==0){
		dialog('请选择收货地址',10,'','');
		return;
	}
	$.ajax({
		url:baselocation+"/uc/exchange/"+giftId+"/"+addressId,
		dataType:"json",
		type:"post",
		async:false,
		success:function(result){
			if(result.success){
				dialog(result.message,17,'','/uc/mygift');
			}else{
				dialog(result.message,10,'','');
				return;
			}
		}
	});
}
</script>
</head>
<body>
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="javascript:void(0)" title="">积分兑换</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl15 pr15">
							<section class="integral-list pb50 line2">
		        				<input type="hidden" id="giftIdHidden" value="${giftId}"/>
								<ul class="clearfix">
								<li>
									
									<section class="inte-l-img">
										 <a href="javascript:void(0)" title="${gift.logo}" class="giftWrap">
										 	<c:if test="${gift.courseId==0 }">
										 	<img src="<%=staticImageServer %>${gift.logo}" height="133" width="178"  />
										 	</c:if>
											<c:if test="${gift.courseId!=0 }">
											<img src="<%=staticImageServer %>${gift.courseLogo}" height="133" width="178"  />
											</c:if>
					                     </a>
									</section>
									
									<h6 class="hLh30 of unFw line2"><tt class="c-blue">${gift.name}</tt></h6>
									<div class="of">
										<span class="fl c-red mt5">所需积分：${gift.score}</span>
									</div>
								</li>
								</ul>
							</section>
							<section class="u-add-list">
							<ol>
								<c:forEach items="${userAddressList}" var="address">
									<li>
										<span class="address-attr"></span> 
										<input type="radio" id="add-1" name="address" <c:if test="${address.isFirst==1}">checked="checked"</c:if> value="${address.id}"  style="margin: 0;"> 
										<label for="add-1" class="vam">${address.receiver}&nbsp;&nbsp;${address.provinceStr} , ${address.cityStr} , ${address.townStr} , ${address.address}</label>
									</li>
								</c:forEach>
							</ol>
						</section>
						<section class="mt40 pb20 line2">
							<span class="vam ml50">
							<label class="u-a-set-btn">
							<input type="button" value="确认兑换" onclick="exchangeIntegral()">
							</label>
							</span>
						</section>
						</div>
					</section>
				</section>
				
			</article>
	<!-- /u-main end -->
</body>
</html>
