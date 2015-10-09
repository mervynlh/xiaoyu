package com.yizhilu.os.edu.controller.cash;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhilu.os.edu.service.weixin.WeixinPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.gain.GuidGeneratorService;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.alipay.CheckURL;
import com.yizhilu.os.core.util.alipay.Payment;
import com.yizhilu.os.core.util.alipay.SignatureHelper;
import com.yizhilu.os.edu.service.cash.CashHessianService;
import com.yizhilu.os.edu.service.cash.CashOrderService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.ReqChanle;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.controller.user.UserController;
import com.yizhilu.os.edu.entity.cash.CashOrder;
import com.yizhilu.os.edu.entity.cash.CashOrderDTO;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.user.UserAccount;
/**
 * CashOrder管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Controller
public class CashOrderController extends EduBaseController{
	private static final Logger logger = LoggerFactory.getLogger(CashOrderController.class);
 	@Autowired
    private CashOrderService cashOrderService;
 	@Autowired
    private CashHessianService cashHessianService;
 	@Autowired
    private UserAccountService userAccountService;
 	@Autowired
    private WebsiteProfileService websiteProfileService;
 	@Autowired
    GuidGeneratorService guidGeneratorService;
    @Autowired
    private WeixinPayService weixinPayService;
 	
 	
 	@InitBinder("cashOrder")
	public void initBinderCashOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOrder.");
	}
 	@InitBinder("cashOrderDTO")
	public void initBinderCashOrderDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOrderDTO.");
	}
    private static final String rechargeMoney = getViewPath("/cash/rechargeCart");// 个人中心账户充值
 	private static final String order_list = getViewPath("/ucenter/cash_order_list");
 	private static final String cash_repay = getViewPath("/cash/cash_repay");
    //微信支付二维码
    private static final String qcCode = getViewPath("/cash/QRCode");

    /**
     * 学生个人中心跳转去账户充值页面
     * @param request
     * @param balance
     * @return
     */
    @RequestMapping("/front/student/account/recharge")
    public String userAccountRecharge(HttpServletRequest request, @RequestParam("balance")BigDecimal balance){
        try {
            request.setAttribute("balance", balance);
        } catch (Exception e) {
            logger.error("userController.userAccountRecharge---error", e);
            return setExceptionRequest(request, e);
        }
        return rechargeMoney;
    }


 	/**
     * 创建充值订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/cashorder", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createCashorder(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("paycash", request.getParameter("paycash"));// 充值金额
            sourceMap.put("userid", getLoginUserId(request).toString());
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());
            sourceMap.put("payType", request.getParameter("payType"));
            Map<String, Object> res = cashOrderService.addCashOrder(sourceMap);
            setJson(true, "success", res);
        } catch (Exception e) {
            logger.error("CashOrderController.createTrxorder error", e);
            setJson(false, "", null);
        }
        return json;
    }

    /**
     * 跳转到支付宝银行，企业支付宝，个人未开发
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/cashorder/bank")
    public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId,
                           @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes) {
        try {
            // 查询订单
            CashOrder cashOrder = cashOrderService.getCashOrderById(orderId);
            if (ObjectUtils.isNotNull(cashOrder)) {
                if(TrxOrderStatus.SUCCESS.toString().equals(cashOrder.getTrxStatus())){
                	request.setAttribute(OrderConstans.RESMSG,"订单已支付成功");
                    return "redirect:/front/success";
                }
                if(!TrxOrderStatus.INIT.toString().equals( cashOrder.getTrxStatus())){
                	request.setAttribute(OrderConstans.RESMSG,"订单信息异常，请稍后再试");
                    return "redirect:/front/success";
                }
                // 走银行流程
                Map<String, String> sourceMap = new HashMap<String, String>();
                sourceMap.put("total_fee", cashOrder.getAmount().toString());// 充值金额
                sourceMap.put("requestId", cashOrder.getRequestId());
                if (payType.equals(PayType.ALIPAY.toString())) {
                    return gotoalipay(request, response, sourceMap);
                } else if (payType.equals(PayType.WEIXIN.toString())) {
                    // 微信扫码支付
                    String wxPayUrl = weixinPayService.getWxpayUrl(cashOrder.getRequestId(), "recharge");
                    request.setAttribute("wxPayUrl",wxPayUrl );
                    request.setAttribute("requestId",cashOrder.getRequestId());
                    request.setAttribute("orderId",cashOrder.getId());
                    return qcCode;
                }
            } else {
                request.setAttribute(OrderConstans.RESMSG, "订单信息异常，请稍后再试");
                return "redirect:/front/success";
            }
            return ERROR;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }

    }

    /**
     * 跳转到支付宝支付页面
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    public String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {

        try {
            logger.info("+++gotoalipay sourceMap:" + sourceMap);
            Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String requestId = sourceMap.get("requestId");// 支付订单号
            String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
            String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
            String sign_type = "MD5";// 文件加密机制（不可以修改）
            String out_trade_no = guidGeneratorService.gainCode("PAY",true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
            String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
            // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            // (账户内提取)
            String key = websitemap.get("alipaykey"); // 支付宝安全校验码(账户内提取)
            String body = getLoginUserId(request).toString() + "-" +requestId+"-" +out_trade_no;// 商品描述，推荐格式：商品名称（订单编号：订单编号）
            String total_fee = sourceMap.get("total_fee");// 订单总价
            String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
            String seller_email = websitemap.get("sellerEmail"); // 卖家支付宝帐户
            // subject 商品名称
            String subject = OrderConstans.companyName +requestId;
            // 扩展信息,存用户id和requestId.重要，必须存
            String extra_common_param = getLoginUserId(request).toString()+","+requestId;

            String show_url = "http://" + request.getContextPath() + "/"; // 商品地址，
            // 根据集成的网站而定
            // 回调的地址
            String path = CommonConstants.contextPath;
            String notify_url = path + "/cashorder/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
            String return_url = path + "/cashorder/alipaynotify/2"; // 支付完成后跳转返回的网址URL
            // 注意以上两个地址 要用 http://格式的完整路径
            /* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */

            String defaultbank = request.getParameter("defaultbank");
            String paymethod = "directPay";
            if (StringUtils.isNotEmpty(defaultbank)) {
                paymethod = "bankPay";
            } else {
                defaultbank = null;
                paymethod = "directPay";
            }

            String submiturl = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url,
                    return_url, paymethod, defaultbank, extra_common_param);
            logger.info("+++ submiturl:" + submiturl);
            return "redirect:" + submiturl;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }

    }

    
    /**
     * 支付宝回调
     *
     * @param rtype 异步，2同步
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cashorder/alipaynotify/{rtype}")
    public String alipayrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
        logger.info("++++ alipaynotify rtype:" + rtype);
        try {
        	Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            String privateKey = websitemap.get("alipaykey"); // 支付宝安全校key
            String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?" + "partner=" + partner + "&notify_id=" + request.getParameter("notify_id");
            String responseTxt = CheckURL.check(alipayNotifyURL);
            Map<String, Object> params = new HashMap<String, Object>();
            // 获得POST 过来参数设置到新的params中
            Map<String, Object> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                /*
                 * 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
                 * 如果mysign和sign不相等也可以使用这段代码转化
                 */
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
                params.put(name, valueStr);
            }
            // 验证加密签名
            String mysign = SignatureHelper.sign(params, privateKey);
            // 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
            if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true")) {
                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
                // 订单编号,系统内的requestId
                String out_trade_no = request.getParameter("out_trade_no");

                String trade_no = request.getParameter("trade_no"); // 支付宝交易号
                logger.info("++++ trade_no:" + trade_no);
                // 总价
                String total_fee = request.getParameter("total_fee");
                // 订单名称
                // String get_subject = new
                // String(request.getParameter("subject").getBytes("ISO-8859-1"),
                // "UTF-8");
                // 订单说明
                String get_body = new String(request.getParameter("body"));
                logger.info("+++ get_body:" + get_body);
                get_body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
                String extra_common_param = request.getParameter("extra_common_param");// 扩展信息存用户的id
                // 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                if (request.getParameter("trade_status").equals("WAIT_BUYER_PAY")) {
                    // 等待买家付款
                    this.sendMessage(request, response, "fail");
                    logger.info("alipaynotify ,WAIT_BUYER_PAY");
                } else if (request.getParameter("trade_status").equals("TRADE_FINISHED") || request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
                    // 校验好状态,在这里可以写入订单的数据处理,

                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", total_fee);
                    sourceMap.put("out_trade_no", out_trade_no);
                    sourceMap.put("requestId", extra_common_param.split(",")[1]);
                    sourceMap.put("userId", extra_common_param.split(",")[0]);
                    sourceMap.put("payType", PayType.ALIPAY.toString());
                    CashOrder cashOrder=cashOrderService.getCashOrderByRequestId(extra_common_param.split(",")[1]);
                    if(cashOrder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
                    	if(rtype==1){
                    		return null;
                    	}else{
                    		redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
                        	return "redirect:/front/success";
                    	}
                    }
                    // 必须校验支付的金额 TODO
                    Map<String, String> res = cashHessianService.noCashOrderComplete(sourceMap);
                    logger.info("/cashorder/alipaynotify/" + rtype + " res:" + res);
                    if (ObjectUtils.isNotNull(res)) {

                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (res.get(OrderConstans.BANKCODE).equalsIgnoreCase("success")) {
                            sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                        } else {
                            logger.info("alipaynotify fail chongzhi error");
                            sendMessage(request, response, "fail");// 失败
                        }
                    } else {
                        logger.info("alipaynotify fail noTrxTrxOrderComplete null");
                        sendMessage(request, response, "fail");// 失败
                    }

                }
            } else {
                logger.info("alipaynotify fail mysign error");
                redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
                sendMessage(request, response, "fail");
            }
        } catch (Exception e) {
            logger.error("order_alipaynotify_error", e);
            try {
                this.sendMessage(request, response, "fail");
                return ERROR;
            } catch (IOException e1) {

            }
        }
        // 同步时跳转到成功页面
        if (rtype.longValue() == 2) {

            return "redirect:/front/success";
        }
        return null;
    }


    /**
     * 获取支付宝 密钥
     * @return
     */
    public Map<String,String> getAlipayInfo(){
    	//获得支付配置
    	Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.alipay.toString());
    	JsonParser jsonParser= new JsonParser();
    	//获得详细info
    	JsonObject jsonObject=jsonParser.parse(gson.toJson(map.get(WebSiteProfileType.alipay.toString()))).getAsJsonObject();
    	if(!jsonObject.isJsonNull()){
    		Map<String,String> websitemap = new HashMap<String,String>();
    		websitemap.put("alipaykey", jsonObject.get("alipaykey").getAsString());//支付宝key
    		websitemap.put("alipaypartnerID", jsonObject.get("alipaypartnerID").getAsString());//支付宝合作伙伴id
    		websitemap.put("sellerEmail",jsonObject.get("sellerEmail").getAsString() );//商家
    		return websitemap;
    	}
    	return null;
    }
    
    /**
	 * 订单列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/cash/order")
	public String orderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,CashOrderDTO cashOrderDTO) {
		
		try {
			this.setPage(page);
			cashOrderDTO.setUserId(UserController.getLoginUserId(request));
			List<CashOrderDTO> cashOrderDTOs = cashOrderService.getCashOrderPage(cashOrderDTO, page);
			//订单信息
			request.setAttribute("cashOrderDTOs", cashOrderDTOs);
			request.setAttribute("page", this.getPage());
			request.setAttribute("cashOrderDTO", cashOrderDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return order_list;
	}
	
	
	
	/**
     * 取消订单 
     * @param request
     * @return
     */
    @RequestMapping("/cash/cancel/oder/{orderId}")
    @ResponseBody
    public Map<String,Object> cancleOrder(HttpServletRequest request,@PathVariable Long orderId){
    	try {
			CashOrder cashOrder = cashOrderService.getCashOrderById(orderId);
			cashOrder.setTrxStatus("CANCEL");
			cashOrderService.updateCashOrder(cashOrder);
		} catch (Exception e) {
			 logger.info("取消订单", e);
			 this.setJson(false, "error", null);
		}
    	this.setJson(true, "success", null);
    	return json;
    }
    
   
    /**
     * 跳转充值订单页
     *
     * @throws Exception
     */
    @RequestMapping("/cash/order/repay/{orderId}")
    public String cashRepay(HttpServletRequest request,@PathVariable Long orderId){
    	try {
    		// 查询订单
        	CashOrder cashOrder = cashOrderService.getCashOrderById(orderId);
            request.setAttribute("cashOrder", cashOrder);
        } catch (Exception e) {
            logger.error("CashOrderController.cashRepay error", e);
            return this.setExceptionRequest(request, e);
        }
        return cash_repay;
    }
}