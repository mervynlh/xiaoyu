<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>支付订单--小雨在线教育</title>
	<script type="text/javascript" src="${ctximg}/static/edu/js/front/order/order.js?v=${v}"></script>
	<script type="text/javascript">
		
	</script>
	<style type="text/css">
		.n-l-menu dl dd {display: none;}
		.n-l-menu dl:hover dd{display: block;}
	</style>
</head>
<body>
	<section class="main">
		<div id="aCoursesList" class="pb50">
			<section class="container">
				<!-- alipay参数 -->
				<form action="${ctx }/order/bank" name="orderForm" id="orderForm" method="post" target="_blank">
					<input id="orderId" name="orderId" type="hidden" value="${order.id}"/>
					<input id="defaultbank" name="defaultbank" type="hidden" value=""/>
					<input id="payType" name="payType" type="hidden" value="ALIPAY" />
				</form>
				<div class="mt30">					
					<section class="sele-course pl160 mt50">
						<div class="fl sub-suc-bg" style="height:250px;"></div>
						<div class="fl pl20 sub-suc-nr">
							<h3 class="sub-suc-tit">
								<tt class="fsize24 c-666 f-fM vam">订单号：</tt>
								<tt class="fsize24 c-org f-fM vam">${order.requestId }</tt>  
								<tt class="fsize24 c-666 f-fM vam">下单成功，订单总额：</tt>
								<tt class="fsize24 c-org f-fM vam">${order.amount }</tt>
								<tt class="fsize24 c-666 f-fM vam">元</tt>
							</h3>
							<h3 class="sub-suc-tit">
								<tt class="fsize24 c-666 f-fM vam">您的账户余额：</tt>
								<tt class="fsize24 c-org f-fM vam">${balance}</tt>
								<c:if test="${ not empty bankAmount }">
								<tt class="fsize24 c-666 f-fM vam">元，本次还需支付</tt>
								<tt class="fsize24 c-org f-fM vam">${bankAmount }</tt>
								<tt class="fsize24 c-666 f-fM vam">元</tt>
								</c:if>
							</h3>
							<div class="mt30 pb30">
								<span class="hLh30 fsize14 f-fM c-org">
									※ 如有疑问，可联系客服：${websitemap.web.phone}
								</span>
							</div>
						</div>
						<div class="clear"></div>
					</section>
					<c:if test="${ not empty bankAmount }">
					<section class="sele-course">
						<div class="course-title-sele">
							<div class="cou-title">
								<span class="cou-title-nub">1</span>
								<span class="fsize20 c-master f-fM">选择支付方式</span>
								<div class="clear"></div>
							</div>
						</div>
						<div>
							<div id="pay-cont" class="buyPay_con"> 
								<section> 
									<div class="hLh30 line3"> 
										<strong class="c-666 fsize16 f-fM">网上银行</strong>
										<tt class="c-999 fsize12 f-fM">（需要开通网上银行，支持绝大多数银行）</tt> 
									</div> 
									<div class="buyB_payPlat mt15"> 
										<ul class="clearfix"> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="CEB-NET"> <img src="${ctx}/static/edu/img/buy/bank_ZGGDYH.png" alt="广大银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="ICBC-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZGGSYH.png" alt="中国工商银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="CCB-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZGJSYH.png" alt="中国建设银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="ABC-NET"><img src="${ctx}/static/edu/img/buy/bank_ZGNYYH.png" alt="中国农业银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="CMBCHINA-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZSYH.png" alt="招商银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="BOC-NET"><img src="${ctx}/static/edu/img/buy/bank_ZGYH.png" alt="中国银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="BOCO-NET">
													<img src="${ctx}/static/edu/img/buy/bank_JTYH.png" alt="中国交通银行">
												</label>
											</li> 
											<li>
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="POST-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZGYZCXYH.png" alt="中国邮政储蓄银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="CIB-NET"><img src="${ctx}/static/edu/img/buy/bank_XYYH.png" alt="兴业银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="CMBC-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZGMSYH.png" alt="中国民生银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="ECITIC-NET">
													<img src="${ctx}/static/edu/img/buy/bank_ZXYH.png" alt="中兴银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="PAB-NET"><img src="${ctx}/static/edu/img/buy/bank_PAYH.png" alt="平安银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="SDB-NET"><img src="${ctx}/static/edu/img/buy/bank_SZFZYH.png" alt="深圳发展银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="SHB-NET"><img src="${ctx}/static/edu/img/buy/bank_SHYH.png" alt="上海银行">
												</label>
											</li> 
											<li class="buyB_payPlatNone">
												<label>
													<input type="radio" onclick="checkbank('ALIPAY')" name="defaultbank" value="BJRCB-NET"><img src="${ctx}/static/edu/img/buy/bank_BJNSYH.png" alt="北京农商银行">
												</label>
											</li> 
										</ul> 
									</div> 
									<div class="clear"></div> 
									<div class="hLh30 line3"> 
										<strong class="c-666 fsize16 f-fM">第三方支付平台</strong> 
									</div> 
									<div class="buyB_payPlat mt15"> 
										<ul class="clearfix"> 
											<li> 
												<label> 
													<input type="radio" onclick="checkbank('ALIPAY')" checked="checked" name="alipay" value=""> 
													<img alt="支付宝" src="${ctx}/static/edu/img/buy/buyB_pay_kuaiqian3.jpg"> 
												</label> 
											</li> 
											<li>
												<label> 
													<input type="radio" onclick="checkbank('WEIXIN')" name="weixin" value=""> 
													<img alt="微信" src="${ctx}/static/edu/img/buy/buyB_pay_wx.jpg"> 
												</label> 
											</li> 
										</ul> 
									</div> 
									<div class="clear"></div> 
								</section> 
							</div>
						</div>
					</section>
					</c:if>
					<section class="sele-course">
						<div class="course-title-sele">
							<div class="cou-title">
							<c:if test="${ not empty bankAmount }">
								<span class="cou-title-nub">2</span>
							</c:if>
								<span class="fsize20 c-master f-fM">结算信息</span>
								<div class="clear"></div>
							</div>
						</div>
						<c:if test="${empty bankAmount }">
							<div class="">
							<div class="cou-boy fr cou-boy-buy mt30">
								<span>
									<tt class="fsize14 c-666 f-fM">
										余额 ￥${balance } - 订单总价 ￥${order.amount } = ￥${balanceAmount }
									</tt>
								</span>
								<span class="">
									<tt class="fsize18 c-666 f-fM">
										应付金额：
									</tt>
									<tt class="fsize36 c-org f-fM">
										￥${order.amount }
									</tt>
								</span>
								<span class="mt20">
									<a class="buy-btn" href="javascript:void(0)" onclick="goToBank()">
										<font class="c-fff tac">立即支付</font>  
									</a>
								</span>
							</div>
							<div class="clear"></div>
						</div>
						</c:if>
						<c:if test="${ not empty bankAmount }">
						<div class="">
							<div class="cou-boy fr cou-boy-buy mt30">
								<span>
									<tt class="fsize14 c-666 f-fM">
										订单总价 ￥${order.amount } - 余额 ￥${balance } = ￥${bankAmount }
									</tt>
								</span>
								<span class="">
									<tt class="fsize18 c-666 f-fM">
										应付定金金额：
									</tt>
									<tt class="fsize36 c-org f-fM">
										￥${bankAmount }
									</tt>
								</span>
								<span class="mt20">
									<a class="buy-btn" href="javascript:void(0)" onclick="goToBank()">
										<font class="c-fff tac">立即支付</font>  
									</a>
								</span>
							</div>
							<div class="clear"></div>
						</div>
						</c:if>
					</section>
				</div>
			</section>
		</div>
	</section>
	<!-- main end -->
</body>
</html>