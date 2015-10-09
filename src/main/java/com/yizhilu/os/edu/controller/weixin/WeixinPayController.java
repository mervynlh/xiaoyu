package com.yizhilu.os.edu.controller.weixin;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.edu.constants.enums.AccountStatus;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.service.order.TrxHessianService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.service.gain.GuidGeneratorService;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.controller.weixin.util.HttpUtil;
import com.yizhilu.os.edu.controller.weixin.util.MessageUtil;
import com.yizhilu.os.edu.controller.weixin.util.PackageUtil;
import com.yizhilu.os.edu.controller.weixin.util.XMLParse;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.service.cash.CashHessianService;
import com.yizhilu.os.edu.service.cash.CashOrderService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;

/**
 * Trxorder管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class WeixinPayController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(WeixinPayController.class);

	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private TrxHessianService trxHessianService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private GuidGeneratorService guidGeneratorService;
	@Autowired
	private CashOrderService cashOrderService;
	@Autowired
	private CashHessianService cashHessianService;
	@Autowired
	private UserAccountService userAccountService;


	@Getter@Setter
	private static String encodingCharset = "UTF-8";



	/**
	 * 获取微信 密钥
	 * @return
	 */
	public Map<String,String> getWxpayInfo(){
		//获得微信支付配置
		Map<String,Object> weixinMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
		//获得详细info
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String, Object>) weixinMap.get(WebSiteProfileType.weixin.toString());
		if(map != null){
			Map<String,String> websitemap = new HashMap<String,String>();
			websitemap.put("appid", map.get("wxAppID").toString());//appId
			websitemap.put("payKey", map.get("wxPayKey").toString());//支付密钥
			websitemap.put("mchid",map.get("wxMchId").toString() );//商户id
			return websitemap;
		}
		return null;
	}
	/**
	 * 微信支付获取商品package
	 *
	 * @param request
	 * @param response
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/bizpaygetpackage")
	public void bizpaygetpackage(HttpServletRequest request, HttpServletResponse response) {
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String productId=requestMap.get("product_id");
			Map<String,String> websitemap = getWxpayInfo();//获得微信支付配置
			String payKey=websitemap.get("payKey");
			if(checkSignature(requestMap,payKey)){//验证微信回调的签名
				String requestId=productId.split("#")[0];
				String type = productId.split("#")[1];
				//请求参数包
				SortedMap<String, String> packageParams = null;
				if(type.equals("recharge")){
					packageParams = createPackage(requestId,productId,websitemap);
				} else if (type.equals("course")) {
					packageParams = createCoursePackage(requestId, productId, websitemap);
				}
				//sign签名
				String sign = PackageUtil.createSign(packageParams,payKey);
				//统一支付请求参数xml
				String xmlPackage=XMLParse.generateXmlPackage(packageParams,sign);
				System.out.println(xmlPackage);
				//预支付订单创建结果
				String resultXmlStr = HttpUtil.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlPackage);
				Map<String, String> resultMap = MessageUtil.parseStringXml(resultXmlStr);
				String returnCode=resultMap.get("return_code");//通信标识返回状态码
				String returnMsg=resultMap.get("return_msg");//返回状态消息
				if(returnCode.equalsIgnoreCase("FAIL")){
					logger.info("return_code-----fail:"+returnMsg);
				}else{
					String result_code = resultMap.get("result_code");//业务结果
					if(result_code.equalsIgnoreCase("FAIL")){
						logger.info("result_code-----fail:err_code"+resultMap.get("err_code")+"/n"+"err_code_des"+resultMap.get("result_code"));
						logger.info("err_code:"+resultMap.get("err_code"));
						logger.info("err_code_des:"+resultMap.get("err_code_des"));
					}else{
						String prepay_id = resultMap.get("prepay_id");
						//返回微信消息
						//请求参数包
						SortedMap<String, String> returnPackageParams=null;
						if(type.equals("recharge")){
							returnPackageParams = returnPackage(requestId,prepay_id,websitemap);
						} else if (type.equals("course")) {
							returnPackageParams = returnPackageCourse(requestId, prepay_id, websitemap);
						}
						//sign签名
						String returnSign = PackageUtil.createSign(returnPackageParams,payKey);
						//统一支付请求参数xml
						String xmlReturnPackage=XMLParse.returnXmlPackage(returnPackageParams,returnSign);
						OutputStream os = response.getOutputStream();
						BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
						resBr.write(xmlReturnPackage);
						resBr.flush();
						resBr.close();
					}
				}
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
		}

	}

	/**
	 * 验证回调签名
	 * @return
	 */
	public boolean checkSignature(Map<String, String> requestMap,String payKey){
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		String signature=requestMap.get("sign");
		if(signature==null||signature.equals("")){
			return false;
		}
		requestMap.remove("sign");
		for(Entry<String, String> entry: requestMap.entrySet()){//循环放入树Map
			signParams.put(entry.getKey(),entry.getValue() );
		}
		try {
			String sign=PackageUtil.createSign(signParams,payKey);
			if(signature.equalsIgnoreCase(sign)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}



	/**
	 * 微信告警通知
	 * @return
	 */
	@RequestMapping("/alarmReport")
	public void alarmReport(HttpServletRequest request, HttpServletResponse response) {
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			logger.info("wxpayalarm ErrorType"+requestMap.get("ErrorType")+"Description"+
					requestMap.get("Description")+"AlarmContent"+requestMap.get("AlarmContent")+"TimeStamp"+requestMap.get("TimeStamp"));
			this.sendMessage(request, response, "success");
		} catch (Exception e) {
			logger.error("alarmReport_error", e);
			try {
				this.sendMessage(request, response, "success");
			} catch (IOException e1) {

			}
		}

	}

	public void sendXmlMessage(HttpServletRequest request, HttpServletResponse response, String content)throws IOException
	{
		try
		{
			System.out.println("--------------------------------------------"+content);
			String contentXml="<xml><return_code><![CDATA["+content+"]]></return_code></xml>";
			OutputStream os = response.getOutputStream();
			BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
			resBr.write(contentXml);
			resBr.flush();
			resBr.close();
		} catch (Exception e) {
			logger.error("sendMessage", e);
		}
	}

//------------------------------学生个人中心账户充值-------------------------------------
	/**
	 * 学生个人中心账户充值订单微信预支付请求参数
	 * @param requestMap
	 * @param request
	 * @return
	 */
	public SortedMap<String, String> createPackage(String requestId,String productId,Map<String,String> websitemap){
		CashOrder cashOrder= cashOrderService.getCashOrderByRequestId(requestId);
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = cashOrder.getAmount();// 订单所需支付的金额
//		try {
//			// userAccount要重新查询一次，否则乐观锁版本号异常
//			UserAccount userAccount = userAccountService.getUserAccountByUserId(cashOrder.getUserId());
//			if (AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
//				BigDecimal balance = userAccount.getBalance();// 账户余额
//				amount = amount.subtract(balance);
//			}
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}
		String notify_url = CommonConstants.contextPath + "/order/wxpaynotify";//微信支付通知url
		String noncestr = PackageUtil.getNonceStr();//随机字符串
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		//必须字段
		packageParams.put("appid", websitemap.get("appid"));  //appid
		packageParams.put("mch_id", websitemap.get("mchid"));  //商户号id
		packageParams.put("body","学生账户充值"); //商品描述
		packageParams.put("notify_url", notify_url); //通知地址  
		packageParams.put("trade_type", "NATIVE"); //交易类型 JSAPI、NATIVE、APP
		packageParams.put("out_trade_no", guidGeneratorService.gainCode("PAY",true));// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复） 
		String total_fee = amount.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString();
		packageParams.put("total_fee",total_fee); //订单总金额，单位为分，不能带小数点
		packageParams.put("spbill_create_ip", cashOrder.getReqIp()); //订单生成的机器IP，指用户浏览器端IP
		packageParams.put("product_id",productId);
		packageParams.put("nonce_str",noncestr);
		//非必需字段
		//附加数据   订单id  用户id
		packageParams.put("attach",requestId + "," + cashOrder.getUserId());
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		packageParams.put("time_start", s.format(date)); //订单生成时间，格式为yyyyMMddHHmmss

		//订单3天后失效
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) +3);
		packageParams.put("time_expire", s.format(c.getTime())); //订单失效时间，格式为yyyyMMddHHmmss

		return packageParams;
	}
	/**
	 * 学生个人中心账户充值订单微信支付通知
	 *
	 * @param rtype 异步，2同步
	 * @return
	 */
	@RequestMapping("/order/wxpaynotify")
	public void wxpayrtn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			logger.info("-----------------/order/wxpaynotify");
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			Map<String,String> websitemap = getWxpayInfo();//获得微信支付配置
			//验证签名，参数签名及xml签名、通信成功
			if(checkSignature(requestMap,websitemap.get("payKey")) && requestMap.get("return_code").equals("SUCCESS")){
				if(requestMap.get("result_code").equals("SUCCESS")){
	                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
					// 订单编号,非requestId，给微信的payId
					String out_trade_no = requestMap.get("out_trade_no");
					logger.info("++++ out_trade_no:" + out_trade_no);
					// 微信交易号
					String transaction_id = requestMap.get("transaction_id");
					logger.info("++++ transaction_id:" + transaction_id);
					// 总价 微信是分为单位，需要转化为元
					String total_fee = requestMap.get("total_fee");
					total_fee = new BigDecimal(total_fee).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).toString();
					//附加数据  用户id，订单requestId,订单类型
					String attach = requestMap.get("attach");
					// 校验好状态,在这里可以写入订单的数据处理,
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", total_fee);
					sourceMap.put("out_trade_no", out_trade_no);
					sourceMap.put("requestId", attach.split(",")[0]);
					sourceMap.put("userId", attach.split(",")[1]);
					sourceMap.put("payType", PayType.WEIXIN.toString());
					Trxorder trxorder= trxorderService.getTrxorderByRequestId(attach.split(",")[0]);
					if(trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
						sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
					}else{
						// 必须校验支付的金额
						Map<String, String> res = cashHessianService.noCashOrderComplete(sourceMap);
						if (ObjectUtils.isNotNull(res)) {
							// 根据返回的结果，订单支付成功或者充值成功时，代表成功
							if (res.get(OrderConstans.BANKCODE).equalsIgnoreCase("success")) {
								sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
							} else {
								logger.info("alipaynotify fail chongzhi error");
								sendXmlMessage(request, response, "FAIL");// 失败
							}
						} else {
							logger.info("alipaynotify fail noTrxTrxOrderComplete null");
							sendXmlMessage(request, response, "FAIL");// 失败
						}
					}

				}else{
					logger.info("wxpaynotify fail result_code error");
					logger.info("err_code:"+requestMap.get("return_code"));
					logger.info("err_code_des:"+requestMap.get("err_code_des"));
					sendXmlMessage(request, response, "FAIL");
				}
			} else {
				logger.info("wxpaynotify fail AppSignature or return_code error");
				redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
				sendXmlMessage(request, response, "FAIL");
			}
		} catch (Exception e) {
			logger.error("order_wxpaynotify_error", e);
			try {
				this.sendXmlMessage(request, response, "FAIL");
			} catch (IOException e1) {

			}
		}
	}
	/**
	 * 微信预支付订单返回参数
	 * @param requestId
	 * @param prepay_id
	 * @param websitemap
	 * @return
	 */
	public SortedMap<String, String> returnPackage(String requestId,String prepay_id,Map<String,String> websitemap){
		//设置package参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("return_code", "SUCCESS");//通信状态
		String noncestr = PackageUtil.getNonceStr();//随机字符串
		packageParams.put("appid",  websitemap.get("appid"));  //appid
		packageParams.put("mch_id",  websitemap.get("mchid"));  //商户号id
		packageParams.put("nonce_str",noncestr);
		packageParams.put("prepay_id",prepay_id);
		String retErrMsg="";
		Trxorder trxOrder= trxorderService.getTrxorderByRequestId(requestId);
		if(trxOrder == null){
			retErrMsg="订单已失效";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){//支付成功
			retErrMsg="订单已支付完成";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.REFUND.toString())){//退款
			retErrMsg="订单已退款";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.CANCEL.toString())){//取消cancel
			retErrMsg="订单已取消";
		}
		packageParams.put("err_code_des",retErrMsg); //错误信息
		String resultCode="SUCCESS";
		if(!retErrMsg.equals("")){//错误消息不为空，返回失败状态，直接显示错误消息
			resultCode = "FAIL";
		}
		packageParams.put("result_code",resultCode);
		return packageParams;
	}


	//------------------------------学生购买课程-------------------------------------
	/**
	 * 学生购买课程订单微信预支付请求参数
	 * @param
	 * @param
	 * @return
	 */
	public SortedMap<String, String> createCoursePackage(String requestId,String productId,Map<String,String> websitemap){
		Trxorder trxorder= trxorderService.getTrxorderByRequestId(requestId);
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = trxorder.getAmount();// 订单所需支付的金额
		try {
			// userAccount要重新查询一次，否则乐观锁版本号异常
			UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
			if (AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
				BigDecimal balance = userAccount.getBalance();// 账户余额
				amount = amount.subtract(balance);
			}
		} catch (AccountException e) {
			e.printStackTrace();
		}
		String notify_url = CommonConstants.contextPath + "/order/course/wxpaynotify";//微信支付通知url
		String noncestr = PackageUtil.getNonceStr();//随机字符串
		//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		//必须字段
		packageParams.put("appid", websitemap.get("appid"));  //appid
		packageParams.put("mch_id", websitemap.get("mchid"));  //商户号id
		packageParams.put("body","学生购买课程"); //商品描述
		packageParams.put("notify_url", notify_url); //通知地址
		packageParams.put("trade_type", "NATIVE"); //交易类型 JSAPI、NATIVE、APP
		packageParams.put("out_trade_no", guidGeneratorService.gainCode("PAY",true));// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
		String total_fee = amount.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString();
		packageParams.put("total_fee",total_fee); //订单总金额，单位为分，不能带小数点
		packageParams.put("spbill_create_ip", trxorder.getReqIp()); //订单生成的机器IP，指用户浏览器端IP
		packageParams.put("product_id",productId);
		packageParams.put("nonce_str",noncestr);
		//非必需字段
		//附加数据   订单id  用户id
		packageParams.put("attach",requestId + "," + trxorder.getUserId());
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		packageParams.put("time_start", s.format(date)); //订单生成时间，格式为yyyyMMddHHmmss

		//订单3天后失效
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) +3);
		packageParams.put("time_expire", s.format(c.getTime())); //订单失效时间，格式为yyyyMMddHHmmss

		return packageParams;
	}
	/**
	 * 学生购买课程订单微信支付通知
	 *
	 * @param rtype 异步，2同步
	 * @return
	 */
	@RequestMapping("/order/course/wxpaynotify")
	public void wxpayrtnCourse(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			logger.info("-----------------/order/wxpaynotify");
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			Map<String,String> websitemap = getWxpayInfo();//获得微信支付配置
			//验证签名，参数签名及xml签名、通信成功
			if(checkSignature(requestMap,websitemap.get("payKey")) && requestMap.get("return_code").equals("SUCCESS")){
				if(requestMap.get("result_code").equals("SUCCESS")){
	                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
					// 订单编号,非requestId，给微信的payId
					String out_trade_no = requestMap.get("out_trade_no");
					logger.info("++++ out_trade_no:" + out_trade_no);
					// 微信交易号
					String transaction_id = requestMap.get("transaction_id");
					logger.info("++++ transaction_id:" + transaction_id);
					// 总价 微信是分为单位，需要转化为元
					String total_fee = requestMap.get("total_fee");
					total_fee = new BigDecimal(total_fee).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).toString();
					//附加数据  用户id，订单requestId,订单类型
					String attach = requestMap.get("attach");
					// 校验好状态,在这里可以写入订单的数据处理,
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", total_fee);
					sourceMap.put("out_trade_no", out_trade_no);
					sourceMap.put("requestId", attach.split(",")[0]);
					sourceMap.put("userId", attach.split(",")[1]);
					sourceMap.put("payType", PayType.WEIXIN.toString());
					Trxorder trxorder= trxorderService.getTrxorderByRequestId(attach.split(",")[0]);
					if(trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
						sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
					}else{
						// 必须校验支付的金额
						Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
						if (ObjectUtils.isNotNull(res)) {
							// 根据返回的结果，订单支付成功或者充值成功时，代表成功
							if (res.get(OrderConstans.BANKCODE).equalsIgnoreCase("success")) {
								sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
							} else {
								logger.info("alipaynotify fail chongzhi error");
								sendXmlMessage(request, response, "FAIL");// 失败
							}
						} else {
							logger.info("alipaynotify fail noTrxTrxOrderComplete null");
							sendXmlMessage(request, response, "FAIL");// 失败
						}
					}

				}else{
					logger.info("wxpaynotify fail result_code error");
					logger.info("err_code:"+requestMap.get("return_code"));
					logger.info("err_code_des:"+requestMap.get("err_code_des"));
					sendXmlMessage(request, response, "FAIL");
				}
			} else {
				logger.info("wxpaynotify fail AppSignature or return_code error");
				redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
				sendXmlMessage(request, response, "FAIL");
			}
		} catch (Exception e) {
			logger.error("order_wxpaynotify_error", e);
			try {
				this.sendXmlMessage(request, response, "FAIL");
			} catch (IOException e1) {

			}
		}
	}
	/**
	 * 微信预支付订单返回参数
	 * @param requestId
	 * @param prepay_id
	 * @param websitemap
	 * @return
	 */
	public SortedMap<String, String> returnPackageCourse(String requestId,String prepay_id,Map<String,String> websitemap){
		//设置package参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("return_code", "SUCCESS");//通信状态
		String noncestr = PackageUtil.getNonceStr();//随机字符串
		packageParams.put("appid",  websitemap.get("appid"));  //appid
		packageParams.put("mch_id",  websitemap.get("mchid"));  //商户号id
		packageParams.put("nonce_str",noncestr);
		packageParams.put("prepay_id",prepay_id);
		String retErrMsg="";
		Trxorder trxOrder= trxorderService.getTrxorderByRequestId(requestId);
		if(trxOrder == null){
			retErrMsg="订单已失效";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){//支付成功
			retErrMsg="订单已支付完成";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.REFUND.toString())){//退款
			retErrMsg="订单已退款";
		}else if(trxOrder.getTrxStatus().equals(TrxOrderStatus.CANCEL.toString())){//取消cancel
			retErrMsg="订单已取消";
		}
		packageParams.put("err_code_des",retErrMsg); //错误信息
		String resultCode="SUCCESS";
		if(!retErrMsg.equals("")){//错误消息不为空，返回失败状态，直接显示错误消息
			resultCode = "FAIL";
		}
		packageParams.put("result_code",resultCode);
		return packageParams;
	}

}