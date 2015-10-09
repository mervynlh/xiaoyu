<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提示信息</title>
<script type="text/javascript" >
	$().ready(function() {
		$("li>img").click(function (){
			var ra = $(this).prev(':radio');
			if(ra!=null){
				$(':radio').attr('checked',false);
				$(ra).attr('checked',true);
			}
		});
 	});
</script>
</head>
<body class="scrol">

	<div class="mb50">
		<section class="w1000">
			<div class="pathwray">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
					<li><span>提示信息</span></li> 
				</ol>
			</div>
			 <!--提示信息-->
			 <article class="mt30" id="order_success">
				 <div class="order-table pb20"  >
				 	<section class="mt20 mr20 mb20 ml20">
						<div class="orderSuccess pr"  >
							<ol>
								<li><h2 class="line3 pb10"><strong class="c-333 fsize18"><font class="ml5 mr5 c-org" id="orderId_success">提示信息：</font>${msg}</strong></h2></li>
								<li class="mt20">
									<span class="c-333 fsize14">您可以：
										<span class="vam">
                                            <a class="order-submit" title="返回首页" href="${ctx }/">首页</a>
											<a class="order-submit" title="进入个人中心" href="${ctx }/uc/home">个人中心</a>
										 	<a class="order-submit" title="我的订单" href="${ctx }/uc/order">我的订单</a>
										 </span>
									</span>
								</li>
							</ol>
							<span class="succIcon pa">
								<img src="${ctx}/static/edu/img/tc.4.png">
							</span>
						</div>
					</section>
				</div>
			</article>
				
		</section>
	</div>
</body>
</html>
