package com.yizhilu.os.edu.controller.cashOut;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.cashOut.CashOut;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.cashOut.CashOutOptRecordService;
import com.yizhilu.os.edu.service.cashOut.CashOutService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * 提现接口管理 CashOutController
 * 
 * @author guozhenzhou
 * @date 2014-09-26
 */
@Controller
@RequestMapping("/front")
public class CashOutController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(CashOutController.class);
	
	MemCache memCache = MemCache.getInstance();

	//private static final String cashOutAdd = getViewPath("/cashout/cashout_add");
	@Autowired
	private CashOutService cashOutService;
	@Autowired
	private CashOutOptRecordService cashOutOptRecordService;
	@Autowired
	private UserService userService;
	// 绑定属性 封装参数
	@InitBinder("cashOut")
	public void initCashOut(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("cashOut.");
	}
	
//	/**
//	 * 跳转去添加提现页面
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/cashout/toadd")
//	public String toAddCashOut(HttpServletRequest request){
//		try {
//			// 获得登录用户ID
//			Long userId = getLoginUserId(request);
//			UserExpandDto user = userService.queryUserExpand(userId);
//			request.setAttribute("mobile", user.getMobile());
//		} catch (Exception e) {
//			logger.error("CashOutController.toAddCashOut-----------error", e);
//			return setExceptionRequest(request, e);
//		}
//		return cashOutAdd;
//	}
	
	/**
	 * 添加提现
	 * @param request
	 * @param cashOut
	 * @return
	 */
	@RequestMapping("/cashout/add")
	@ResponseBody
	public Map<String, Object> addCashOut(HttpServletRequest request, @ModelAttribute CashOut cashOut){
		try {
			if (cashOut.getCashType().equalsIgnoreCase("bank")) { // 提现方式为银行卡
//				// 验证码
//				String randomCode = request.getParameter("randomCode");
//				// 短信验证码是否一致
//				if (memCache.get(MemConstans.MOBILE_CODE + cashOut.getUserMobile())==null||!randomCode.equals(memCache.get(MemConstans.MOBILE_CODE+cashOut.getUserMobile()))) {
//					this.setJson(false, "randomCodeError", null);
//					return json;
//				}
				// 验证银行卡号是否为空
				if (ObjectUtils.isNull(cashOut.getBankCard()) || StringUtils.isEmpty(cashOut.getBankCard())) {
					this.setJson(false, "bankCardIsNull", null);
					return json;
				}
				// 验证开户行是否为空
				if (ObjectUtils.isNull(cashOut.getOpenBankName()) || StringUtils.isEmpty(cashOut.getOpenBankName())) {
					this.setJson(false, "openBankNameIsNull", null);
					return json;
				}
				// 验证开户人姓名是否为空
				if (ObjectUtils.isNull(cashOut.getOpenBankPerson()) || StringUtils.isEmpty(cashOut.getOpenBankPerson())) {
					this.setJson(false, "openBankPersonIsNull", null);
					return json;
				}
			} else if (cashOut.getCashType().equalsIgnoreCase("alipay")) {
				// 验证支付宝账号是否为空
				if (ObjectUtils.isNull(cashOut.getAlipayAccount()) || StringUtils.isEmpty(cashOut.getAlipayAccount())) {
					this.setJson(false, "alipayAccountIsNull", null);
					return json;
				}
			}
			// 获得登录用户ID
//			Long userId = getLoginUserId(request);
//			UserExpandDto user = userService.queryUserExpand(userId);
//			// 验证手机号是否正确
//			if (!user.getMobile().equals(cashOut.getUserMobile())) {
//				this.setJson(false, "mobileIsError", null);
//				return json;
//			}
			// 验证提现金额是否正确
			if (ObjectUtils.isNull(cashOut.getCashoutMoney()) || cashOut.getCashoutMoney() == new BigDecimal(0.00)) {
				this.setJson(false, "cashoutMoneyIsNull", null);
				return json;
			}
			// 用户ID
			cashOut.setUserId(getLoginUserId(request));
			cashOut.setCreatetime(new Date());
			cashOut.setStatus(1); // 状态1 正常
			//cashOut.setUserMobile(user.getMobile()); // 设置用户手机号
			cashOutService.createCashOut(cashOut);
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("CashOutController.addCashOut-----------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	
	/**
	 * 检查是否已经获取过验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/cashout/getVerification/{phone}")
	@ResponseBody
	public Map<String, Object> getVerification(HttpServletRequest request,@PathVariable("phone") String phone) {
		try {

//			if (StringUtils.isEmpty(randomCode) ||request.getSession().getAttribute(RandomCodeController.RAND_CODE)==null || !randomCode.equalsIgnoreCase(request.getSession().getAttribute(RandomCodeController.RAND_CODE).toString())) {
//				this.setJson(false, "验证码错误", null);
//				return json;
//			}
			// 验证手机是否为空
			if (ObjectUtils.isNull(phone) || StringUtils.isEmpty(phone)) {
				this.setJson(false,"请输入手机号", null);
				return json;
			}
			// 在手机不为空的情况下，验证手机格式是否正确
			if (ObjectUtils.isNotNull(phone) && StringUtils.isNotEmpty(phone) && !phone.matches("^1{1}[0-9]{10}$")) {
				this.setJson(false, "手机号码格式不正确", null);
				return json;
			}
			String randCode = createRandom(6);
			memCache.set(MemConstans.MOBILE_CODE+phone, randCode, MemConstans.MOBILE_CODE_TIME);
//			String contents = "尊敬的用户，您正进行“手机号码验证操作”，请输入动态验证码" + randCode + "，5分钟内有效";
//			//发送手机验证码
//		 	boolean isOk = MobileMessageSendUtil.sendMobileMessage(phone, contents);
//		 	if(isOk){
//		 		logger.info("randCode-------------" + randCode);
//		 		this.setJson(true, "验证码已经发送到" + phone + "手机上，如200秒内未收到，请刷新页面重试！", null);
//				return json;
//		 	}
//			this.setJson(false, "验证码发送失败,请刷新页面重试", null);
//			return json;
			logger.info("randCode-------------" + randCode);
			this.setJson(true, "验证码已发送", null);
			return json;
		} catch (Exception e) {
			logger.error("getVerification----error", e);
			this.setJson(false, "系统繁忙", null);
			return json;
		}
	}
	/** 
	  * 创建指定数量的随机字符串 
	  * @param numberFlag 是否是数字 
	  * @param length 
	  * @return 
	  */ 
	private String createRandom(int length){  
		Random ran = new Random();
		String str="";
		for(int i=0;i<length;i++){
			str+=ran.nextInt(9);
		}
		System.out.println(str);
		return str;
	}

}
