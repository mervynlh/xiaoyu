<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>重新支付</title>
    <script type="text/javascript" src="${ctximg}/static/edu/js/front/cash/cash.js"></script>
   
</head>
<body class="scrol">

<div class="mb50">
    <section class="w1000">
        
        <!-- 内容区开始 -->
        <article class="mt30" id="order_init">

            <div  style="display: none;">
                <!-- alipay参数 -->
                <form action="${ctx }/cashorder/bank" name="orderForm" id="orderForm" method="post">
                    <input id="orderId" name="orderId" type="hidden" value="${cashOrder.id}"/>
                    <input id="defaultbank" name="defaultbank" type="hidden" value=""/>
                    <input id="payType" name="payType" type="hidden" value="ALIPAY" />
                </form>
            </div>
            <!--商品清单 end-->
				<!-- 优惠券编码 -->
				<div  style="display: none;">
					<input id="couponCodeHidden" type="hidden" value=""/>
				</div>
                <!--请选择支付方式 start-->
                <div class="buyPay" id="buy_step2" style="display: block;">
                    <div class="o-s-title of">
                        <h4 class="fl unFw"><em class="vam icon24 mr5 o-s-icon-3">&nbsp;</em><font class="c-master vam fsize18 f-fM">请选择支付方式</font></h4>
                    </div>
                    <div class="order-table pb20">
                        <section class="pay-t">
                            <ul id="pay-title" class="buyPayTit">
                                <li class="current" id="paytab1"><a href="javascript: void(0)">网上支付</a></li>
                            </ul>
                        </section>
                        <!--网上支付 start-->
                        <div class="buyPay_con" id="pay-cont">
                            <section>
                                <!--网上支付 -网银 支付开始 -->
                                <div class="hLh30 line3">
                                    <strong class="c-333">网上银行</strong><tt class="c-999">（需要开通网上银行，支持绝大多数银行）</tt>
                                </div>
                                <div class="buyB_payPlat mt15">
                                    <ul class="clearfix">
                                 
						          
						          <li><label><input type="radio" value="ICBCB2C" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /> <img alt="工商" src="/static/edu/images/buy/wal_bank07_gongShang.jpg" /></label></li>
						          <li><label><input type="radio" value="CCB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="建设" src="/static/edu/images/buy/wal_bank08_jianshe.jpg" /></label></li>
						          <li><label><input type="radio" value="BOCB2C" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="中国银行" src="/static/edu/images/buy/wal_bank10_zhongGuo.jpg" /></label></li>
						          <li><label><input type="radio" value="CMB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="招商" src="/static/edu/images/buy/wal_bank06_zhaoShang.jpg" /></label></li>
						          <li><label><input type="radio" value="COMM" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="交通银行" src="/static/edu/images/buy/wal_bank05_jaotong.jpg" /></label></li>
						          <li><label><input type="radio" value="ABC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="农业" src="/static/edu/images/buy/wal_bank09_nongYe.jpg" /></label></li>
						          <li><label><input type="radio" value="CIB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="兴业" src="/static/edu/images/buy/wal_bank03_xingYe.jpg" /></label></li>
						          <li><label><input type="radio" value="CMBC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="民生" src="/static/edu/images/buy/wal_bank03_minSheng.jpg" /></label></li>
						          <li><label><input type="radio" value="CITIC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="中信" src="/static/edu/images/buy/wal_bank03_zhongXin.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="GDB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="广发" src="/static/edu/images/buy/wal_bank03_guangFa.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="CEBBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="光大" src="/static/edu/images/buy/wal_bank03_guangDa.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="SPDB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="浦发" src="/static/edu/images/buy/wal_bank03_fuFa.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="BJBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="北京农商" src="/static/edu/images/buy/wal_bank03_beiNongShang.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="POSTGC" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="邮政" src="/static/edu/images/buy/wal_bank04_youZheng.jpg" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="SHBANK" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="上海" src="/static/edu/images/buy/wal_bank03_shanghai.jpg" /></label></li>
<!-- 						          <li class="buyB_payPlatNone"><label><input type="radio" value="GCB" name="alipaybank" onclick="checkbank('ALIPAY_BANK')" /><img alt="广州" src="/static/edu/images/buy/wal_bank03_guangZhou.jpg" /></label></li>
 -->						         
                                    </ul>
                                </div>
                                <div class="clear"></div>
                                <!--网上支付 -网银 支付结束 -->

                                <!--网上支付 -第三方开始 -->
                                <div class="hLh30 line3">
                                    <strong class="c-333 fsize14">第三方支付平台</strong>
                                </div>
                                <div class="buyB_payPlat mt15">
                                    <ul class="clearfix">
                                    	<%-- <c:if test="${keywordmap.keyword.yee=='ON' }">
										<li>
										<label><input type="radio" value="00" name="yeepay" onclick="checkbank('YEEPAY')"   style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_yibao.jpg" alt="易宝"  /></label></li>
										</c:if> --%>
                                        <li>
                                          <label><input type="radio" value="" name="alipay" checked="checked" onclick="checkbank('ALIPAY')"  />
                                           <img src="/static/edu/images/buy/buyB_pay_kuaiqian3.jpg"  alt="支付宝" /> </label>
                                        </li>
                                        <%-- <c:if test="${keywordmap.keyword.verifykq=='ON' }">
										<li><label><input type="radio" value="00" name="kqBank" onclick="checkbank('KUAIQIAN')"   style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_kuaiqian1.jpg" alt="快钱"  /></label></li>
										</c:if>
										<c:if test="${keywordmap.keyword.verifywx=='ON' }">
										<li><label>
										<input type="radio" value="" name="weixin" onclick="checkbank('WEIXIN')"  />
										<img src="/static/edu/images/buy/buyB_pay_wx.jpg"  alt="微信" /> </label>
										</li>
										</c:if> --%>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                                <!--网上支付 -第三方结束 -->
                            </section>
                        </div>
                        <!--网上支付 end-->
                    </div>
                </div>
                <!--请选择支付方式 end-->
                <!--结算信息 start--->
                <div class="buyPay mt15" id="priceinfo">
                    <div class="o-s-title of">
                        <h4 class="fl unFw">
                            <em class="vam icon24 mr5 o-s-icon-1"> </em>
                            <font class="c-master vam fsize18 f-fM">结算信息</font>
                        </h4>
                    </div>
                </div>
                <div class="buySettle clearfix pt20 pb20 pl20 pr20 order-table">
                    <!--优惠券 start-->
                    <section class="fl">
                        <div class="buyCoupon">
                            

                        </div>
                    </section>
                    <section class="fl">
                        <div style="display: none;" id="couponPicDiv">
                            <input type="hidden" value="<%=staticImageServer %>" id="couponStaticPath"/>
                            <img src=""  width="120" height="100" id="couponPicPath"/>
                        </div>
                    </section>
                    <!--优惠券 end-->
                    <section class="fr tar">
                        <div class="fsize18 mt10">
                            <span class="c-666 f-fM">应付订单金额：</span>
                            <strong class="c-orange ml5" id="payprice">￥${cashOrder.amount}</strong>
                        </div>
                        <div class="buySubmitBox">
                            <a href="javascript:void(0)" onclick="javascript:disOrderSuccess()" class="btn-big btn-orange order-submit mt10">确认支付</a>
                        </div>
                    </section>
                </div>
                <!--结算信息 end--->
        </article>
        <!-- 内容区 //-->

        <article class="mt30" id="order_success" style="display: none">
            <div class="order-table pb20"  >
                <section class="mt20 mr20 mb20 ml20">
                    <div class="orderSuccess pr ml20"  >
                        <ol>
                            <li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>订单号:</tt><font class="ml5 mr5 c-orange" id="orderId_success">${cashOrder.requestId}</font>下单成功，订单总额<font class="ml5 c-orange" id="amount_success">￥${cashOrder.amount}</font></strong></h2></li>
                            <li class="mt10">
								<span class="vam"><a class="order-submit" title="" id="payNow" href="javascript:void(0)" onclick="javascript:goToBank()">立即支付</a></span>
							</li>
                            <li class="mt20"><span class="c-333 fsize14">您现在可以：<a class="c-333 mr10" title="重新选择支付方式" href="${ctx }/cash/order/repay/${cashOrder.id}"><u>重新选择支付方式</u></a> | <a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/home"><u>进入学习中心</u></a> | <a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u></a></span></li>
                        </ol>
                        <span class="succIcon pa"></span>
                    </div>
                </section>
            </div>
        </article>


    </section>
</div>
</body>
</html>
