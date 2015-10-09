<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>支付课程</title>
    <script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/shopcart/shopcart.js"></script>
    <script src="${ctx }/static/common/qrcode/qrcode-light.js" type="text/javascript"></script> 
	<script src="${ctx }/static/common/qrcode/qrgen.js" type="text/javascript"></script> 
	<script src="${ctx }/static/common/qrcode/doqrgen.js" type="text/javascript"></script> 
    <script type="text/javascript" >
        $().ready(function() {
        	var couponCode='${couponCode}';
        	var requestId='${trxorder.requestId}';
            if(couponCode!=null&&couponCode!=''){
            	couponBtnClick();//显示优惠券编码div
            	$("#couponCode").val(couponCode);
            	addcode(requestId);//验证优惠券
            }
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
                <li><span>订单支付</span></li>
            </ol>
        </div>
        <!-- 内容区开始 -->
        <article class="mt30" id="order_init">
            <div class="order-step-bg-3 order-step" id="order_step_img"></div>
            <!--商品清单 start-->
            <section class="mt30 comm-shadow-2" id="sellHaedSecid">
                <div class="o-s-title of">
                    <h4 class="fl unFw"><em class="vam icon24 mr5 o-s-icon-2">&nbsp;</em><font class="c-master vam fsize18 f-fM">我的结算课程</font></h4>
                </div>
                <div class="order-table pb20" id="shopingcart">
                    <!-- 购物车信息 -->
                    <table width="100%" cellspacing="0" cellpadding="0" border="0" >
                        <thead>
                        <tr>
                            <th width="30%" align="center">课程名称</th>
                            <th width="40%" align="center">主讲教师</th>
                            <th width="30%" align="center">价格</th>

                        </tr>
                        </thead>
                        <tbody >
                            <c:forEach items="${orderList }" var="sc">
                                <tr>
                                    <td align="center">${sc.courseName }</td>
                                    <td align="center">
                                    	<c:forEach items="${sc.teacherList}" var="teacher">
                                    		${teacher.name}&nbsp;
                                    	</c:forEach>
                                    </td>
                                    <td align="center">￥<samp id="sampCourse_${sc.id}">${sc.currentPrice }</samp></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="buyCom_price c-666 fr tar">
                        <span>课程数量：<span class="fsize14 c-orange mr5" id="buyCount">${orderList.size()}</span>&nbsp;&nbsp;</span>
                        课程金额：<span class="fsize14 c-orange mr5" id="div_totalMoney">￥${bankAmount }</span>
                    </div>
                </div>
            </section>

            <div  style="display: none;">
                <!-- alipay参数 -->
                <form action="${ctx }/order/bank" name="orderForm" id="orderForm" method="post">
                    <input id="orderId" name="orderId" type="hidden" value="${trxorder.id}"/>
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
                                  <c:if test="${keywordmap.keyword.yee=='ON' }">
								  <li><label><input type="radio" value="CEB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /> <img alt="广大银行" src="/static/edu/images/buy/bank_ZGGDYH.png" /></label></li>
						          <li><label><input type="radio" value="ICBC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国工商银行" src="/static/edu/images/buy/bank_ZGGSYH.png" /></label></li>
						          <li><label><input type="radio" value="CCB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国建设银行" src="/static/edu/images/buy/bank_ZGJSYH.png" /></label></li>
						          <li><label><input type="radio" value="ABC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国农业银行" src="/static/edu/images/buy/bank_ZGNYYH.png" /></label></li>
						          <li><label><input type="radio" value="CMBCHINA-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="招商银行" src="/static/edu/images/buy/bank_ZSYH.png" /></label></li>
						          <li><label><input type="radio" value="BOC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国银行" src="/static/edu/images/buy/bank_ZGYH.png" /></label></li>
						          <li><label><input type="radio" value="BOCO-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国交通银行" src="/static/edu/images/buy/bank_JTYH.png" /></label></li>
						          <li><label><input type="radio" value="POST-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国邮政储蓄银行" src="/static/edu/images/buy/bank_ZGYZCXYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="CIB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="兴业银行" src="/static/edu/images/buy/bank_XYYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="CMBC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中国民生银行" src="/static/edu/images/buy/bank_ZGMSYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="ECITIC-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="中兴银行" src="/static/edu/images/buy/bank_ZXYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="PAB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="平安银行" src="/static/edu/images/buy/bank_PAYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="SDB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="深圳发展银行" src="/static/edu/images/buy/bank_SZFZYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="SHB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="上海银行" src="/static/edu/images/buy/bank_SHYH.png" /></label></li>
						          <li class="buyB_payPlatNone"><label><input type="radio" value="BJRCB-NET" name="defaultbank" onclick="checkbank('YEEPAY')" /><img alt="北京农商银行" src="/static/edu/images/buy/bank_BJNSYH.png" /></label></li>
						          </c:if>
						          
						          <c:if test="${keywordmap.keyword.yee=='OFF' }">
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
 -->						          </c:if>
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
                                    	<c:if test="${keywordmap.keyword.yee=='ON' }">
										<li>
										<label><input type="radio" value="00" name="yeepay" onclick="checkbank('YEEPAY')"   style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_yibao.jpg" alt="易宝"  /></label></li>
										</c:if>
                                        <li>
                                          <label><input type="radio" value="" name="alipay" checked="checked" onclick="checkbank('ALIPAY')"  />
                                           <img src="/static/edu/images/buy/buyB_pay_kuaiqian3.jpg"  alt="支付宝" /> </label>
                                        </li>
                                        <c:if test="${keywordmap.keyword.verifykq=='ON' }">
										<li><label><input type="radio" value="00" name="kqBank" onclick="checkbank('KUAIQIAN')"   style="margin-top:5px;" />
										<img src="/static/edu/images/buy/buyB_pay_kuaiqian1.jpg" alt="快钱"  /></label></li>
										</c:if>
										<c:if test="${keywordmap.keyword.verifywx=='ON' }">
										<li><label>
										<input type="radio" value="" name="weixin" onclick="checkbank('WEIXIN')"  />
										<img src="/static/edu/images/buy/buyB_pay_wx.jpg"  alt="微信" /> </label>
										</li>
										</c:if>
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
                            <div class="buyCoupon_tit fo hand" id="couponBtn" onclick="couponBtnClick()"><em class="icon18 bcIcon"></em><tt class="c-666 vam ml5">使用优惠券抵消部分金额</tt></div>
                            <div class="buyCoupon_con buyCouponWrap mt5">
                                <div class="hei28 mt10 mr10 mb10 ml10">
                                    <label class='c-666'>请输入优惠券编码：</label>
                                    <input class="buyText01"  name="" class="couponTxt" type="text" id="couponCode" onkeyup="inputcode()" onclick="inputcode()" />
                                    <a href="javascript:addcode('${trxorder.requestId}')" class="buyCoupon_add2 pageSub" id="tjcode">添加</a>
	      							<a href="javascript:initPrice()" class="buyCoupon_add2 pageSub"  style="display:none" id="initcode">取消</a>
                                </div>
                                <p class="c-orange mt5">注：优惠券使用后将不能撤销</p>
                            </div>

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
                        <span class="c-666">订单总价</span> <span class="c-orange ml5" id="oldmoney">￥${trxorder.amount}</span>&nbsp;-
                        <span class="c-666">优惠金额</span> <span class="c-orange ml5" id="yhmoney">￥0.00</span>&nbsp;=
                        <span class="c-orange ml5" id="paySumPrice">￥${trxorder.amount}</span>
                        <span class="c-666">优惠类型：</span><span class="c-orange ml5" id="yhTypeId">无</span>
                        <div class="fsize18 mt10">
                            <span class="c-666 f-fM">应付订单金额：</span>
                            <strong class="c-orange ml5" id="payprice">￥${trxorder.amount}</strong>
                        </div>
                        <div class="buySubmitBox">
                            <a href="javascript:void(0)" onclick="javascript:disOrderSuccess()" class="btn-big btn-orange order-submit mt10">确认订单</a>
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
                            <li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>订单号:</tt><font class="ml5 mr5 c-orange" id="orderId_success">${trxorder.requestId}</font>下单成功，订单总额<font class="ml5 c-orange" id="amount_success">￥${trxorder.amount}</font></strong></h2></li>
                            <li><h2 class="hLh30 line3 pb10"><strong class="c-333 fsize20"><tt>帐户余额:</tt><font class="ml5 mr5 c-orange" id="balance_s" >￥0.00</font><font class="ml5 c-orange" id="bankAmount_s">，需充值￥${bankAmount}</font></strong></h2></li>
                            <li class="mt10">
								<span class="vam"><a class="order-submit" title="" id="payNow" href="javascript:void(0)" onclick="javascript:goToBank()">立即支付</a></span>
							</li>
                            <li class="mt20"><span class="c-333 fsize14">您现在可以：<a class="c-333 mr10" title="重新选择支付方式" href="${ctx }/front/repay/${trxorder.id}"><u>重新选择支付方式</u></a> | <a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/home"><u>进入学习中心</u></a> | <a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u></a></span></li>
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
