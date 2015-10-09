package com.yizhilu.os.edu.controller.order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.gain.GuidGeneratorService;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.alipay.CheckURL;
import com.yizhilu.os.core.util.alipay.Payment;
import com.yizhilu.os.core.util.alipay.SignatureHelper;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.CourseModel;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.ReqChanle;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;
import com.yizhilu.os.edu.controller.user.UserController;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.KqParamInfo;
import com.yizhilu.os.edu.entity.order.QueryTrxorder;
import com.yizhilu.os.edu.entity.order.TrxOrderDTO;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.YbParamInfo;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserAddress;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseMinusService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxHessianService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserAddressService;
import com.yizhilu.os.edu.service.user.UserEmailMsgService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.service.weixin.WeixinPayService;

/**
 * Trxorder管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class TrxorderController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(TrxorderController.class);

    @Autowired
    private TrxorderService trxorderService;

    @Autowired
    private TrxHessianService trxHessianService;
    @Autowired
	private CouponCodeService couponCodeService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TrxorderDetailService trxorderDetailService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    @Autowired
    private UserEmailMsgService userEmailMsgService;
    @Autowired
    private UserMobileMsgService userMobileMsgService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private CourseMinusService courseMinusService;
    @Autowired
    private WeixinPayService weixinPayService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private CourseKpointsService courseKpointsService;
    
    //快钱提交页面
    private String kq_jsp = getViewPath("/order/kqbank");
    //重新支付显示
    private String repay = getViewPath("/order/repay");
    
    //课程详情
    private String oDetails = getViewPath("/order/u_order_info");
	private String orderList = getViewPath("/ucenter/order_list");// 订单列表
	// 教师订单列表
	private static final String teacherOrderList = getViewPath("/ucenter/teacher/order_list");// 订单列表
	
	// 易宝支付信息
	private String yeepay = getViewPath("/order/reqpay");
	//微信支付二维码
	private String qcCode = getViewPath("/order/QRCode");
	//订单信息确认页
	private String grab_single = getViewPath("/order/order_select_time");
	//班课订单信息确认页
	private String class_single = getViewPath("/order/order_class");
	//支付订单
	private String payOrder = getViewPath("/order/pay_order");
	
	

	
	@Getter@Setter
	private static String encodingCharset = "UTF-8";
	// 绑定属性 封装参数
	@InitBinder("trxorder")
	public void initTrxorder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("trxorder.");
	}
    
	 /**
     * 立即购买
     *	param : teacherId 教师id获取教师课表，及信息
     * @throws Exception
     */
    @RequestMapping("/order/grabsingle")
    public String grabSingle(Model model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        try {
            //教师课表列表
        	Course course = courseService.getCourseById(trxorder.getCourseId());
        	Teacher teacher = teacherService.getTeacherInfoById(trxorder.getTeacherId());
        	Map<String, String> map = gson.fromJson(course.getCourseModel(),new TypeToken<Map<String, String>>(){}.getType());
        	//用户信息
        	UserExpandDto user = userExpandService.getUserExpandByUids(getLoginUserId(request));
        	
        	//订单原始总价格
        	BigDecimal orderAcount = new BigDecimal(map.get(trxorder.getCourseModel())).multiply(new BigDecimal(trxorder.getLessionNum()));
        	//折扣优惠的价格
        	BigDecimal minusAcount =  new BigDecimal(0l);
        	//课时折扣后的价格
        	BigDecimal orderAfterMinusAcount = orderAcount;
        	//扣除余额后的价格
        	BigDecimal orderMinusAfterBalanceAcount = minusAcount;
        	CourseMinus courseMinus = new CourseMinus();
        	courseMinus.setTeacherId(teacher.getId());
        	courseMinus.setMinusNum(trxorder.getLessionNum().intValue());
        	//课时折扣
        	List<CourseMinus> minusList = courseMinusService.queryCourseMinusConditionForOrder(courseMinus);
        	if(minusList!=null&&minusList.size()>0){
        		CourseMinus minus = minusList.get(0);
        		BigDecimal minusBig = new BigDecimal(minus.getDiscount()*0.1);
        		//折扣后金额
        		orderAfterMinusAcount = orderAcount.multiply(minusBig).setScale(2, RoundingMode.HALF_UP);
        		//优惠金额
        		minusAcount = orderAcount.subtract(orderAfterMinusAcount);
        	}
        	//账户余额
        	UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
        	//扣除余额后的价格
        	orderMinusAfterBalanceAcount = orderAfterMinusAcount.subtract(userAccount.getBalance());
            request.setAttribute("balance", userAccount.getBalance());// 账户余额
            request.setAttribute("price", map.get(trxorder.getCourseModel()));//原课程价格
            request.setAttribute("orderAcount", orderAcount);//原课程价格
            request.setAttribute("minusAcount", minusAcount);//折扣优惠价格
            request.setAttribute("orderAfterMinusAcount", orderAfterMinusAcount);//折扣后价格
            request.setAttribute("orderMinusAfterBalanceAcount", orderMinusAfterBalanceAcount);//扣除余额后的价格
        	request.setAttribute("course", course);
            request.setAttribute("trxorder", trxorder);
            request.setAttribute("user", user);
            //set讲师地址
            seTeacherAddress(trxorder,teacher);
            request.setAttribute("teacher", teacher);
        } catch (Exception e) {
            logger.error("grabSingle error", e);
            setJson(false, "", null);
        }
        return grab_single;
    }
    //set教师地址
    private void seTeacherAddress(Trxorder trxorder,Teacher teacher){
    	 //教师地址坐标
        if(trxorder.getCourseModel().equals(CourseModel.STUDENTVISIT.toString())){
        	//获取用户的常用地址
            UserAddress queryAddress=new UserAddress();
            queryAddress.setUserId(teacher.getUserId());
            List<UserAddress> addressList= userAddressService.getUserAddressList(queryAddress);
            if(ObjectUtils.isNotNull(addressList)&&addressList.size()>0){
            	 for(UserAddress address:addressList){
 	            	if(address.getIsFirst()==1){
 	            		teacher.setProvinceName(address.getProvinceStr());
 	            		teacher.setCityName(address.getCityStr());
 	            		teacher.setDistrictName(address.getTownStr());
 	            		teacher.setLng(address.getLng());
 	            		teacher.setLat(address.getLat());
 	            		teacher.setAddress(address.getProvinceStr()+address.getCityStr()+address.getTownStr()+address.getAddress());
 	            		break;
 	            	}
 	            }
 	            //用户未设定常用地址的情况下获取第一个地址
 	            if(StringUtils.isEmpty(teacher.getLng())||StringUtils.isEmpty(teacher.getLat())){
 	            	teacher.setProvinceName(addressList.get(0).getProvinceStr());
	            		teacher.setCityName(addressList.get(0).getCityStr());
	            		teacher.setDistrictName(addressList.get(0).getTownStr());
 	            	teacher.setLng(addressList.get(0).getLng());
 	            	teacher.setLat(addressList.get(0).getLat());
 	            	teacher.setAddress(addressList.get(0).getProvinceStr()+addressList.get(0).getCityStr()
 	            			+addressList.get(0).getTownStr()+addressList.get(0).getAddress());
 	            }
            }
        }
    }
    
    /**
     * 班课立即购买
     * @param model
     * @param request
     * @param response
     * @param trxorder
     * @return
     * @throws Exception
     */
    @RequestMapping("/order/classsingle")
    public String classSingle(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam("courseId") Long courseId) throws Exception {
        try {
        	Trxorder trxorder=new Trxorder();
        	trxorder.setCourseId(courseId);
            //教师课表列表
        	Course course = courseService.getCourseById(courseId);
        	trxorder.setLessionNum(course.getLessionnum());
        	trxorder.setCourseModel(getKry(course.getCourseModelMap()));
        	trxorder.setTeacherId(course.getTeacherId());
        	Teacher teacher = teacherService.getTeacherInfoById(trxorder.getTeacherId());
        	//用户信息
        	UserExpandDto user = userExpandService.getUserExpandByUids(getLoginUserId(request));
        	
        	//订单原始总价格
        	BigDecimal orderAcount =course.getCurrentprice();
        	int openState=courseKpointsService.queryOpenStatu(courseId);
        	if(openState<=0){
        		orderAcount=course.getJoinPrise();
        	}
        	//课时折扣后的价格
        	BigDecimal orderAfterMinusAcount = orderAcount;
        	//扣除余额后的价格
        	BigDecimal orderMinusAfterBalanceAcount = new BigDecimal(0l);
        	//账户余额
        	UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
        	//扣除余额后的价格
        	orderMinusAfterBalanceAcount = orderAfterMinusAcount.subtract(userAccount.getBalance());
        	
        	//查询课程安排
        	List<CourseKpoints> kpoints=courseKpointsService.queryKpointsByCourseId(courseId);
        	//上课地址
        	setTeacherAddress(course,teacher);
        	
            request.setAttribute("balance", userAccount.getBalance());// 账户余额
            request.setAttribute("price", course.getCurrentprice());//原课程价格
            request.setAttribute("orderAcount", orderAcount);//原课程价格
            request.setAttribute("orderAfterMinusAcount", orderAfterMinusAcount);//折扣后价格
            request.setAttribute("orderMinusAfterBalanceAcount", orderMinusAfterBalanceAcount);//扣除余额后的价格
        	request.setAttribute("course", course);
            request.setAttribute("trxorder", trxorder);
            request.setAttribute("user", user);
            request.setAttribute("teacher", teacher);
            request.setAttribute("kpoints", kpoints);
        } catch (Exception e) {
            logger.error("grabSingle error", e);
            setJson(false, "", null);
        }
        return class_single;
    }
    //set老师上课地址
    private void setTeacherAddress(Course course,Teacher teacher) throws Exception{
    	UserAddress address= userAddressService.getUserAddressById(course.getAddressId());
    	teacher.setProvinceName(address.getProvinceStr());
 		teacher.setCityName(address.getCityStr());
 		teacher.setDistrictName(address.getTownStr());
 		teacher.setAddress(address.getAddress());
 		teacher.setLng(address.getLng());
 		teacher.setLat(address.getLat());
    }
    //获取集合中的key值
    private static String getKry(Map m){
    	String keyValue="";
    	Iterator i=m.entrySet().iterator();
    	while(i.hasNext()){//只遍历一次,速度快
    		Map.Entry e=(Map.Entry)i.next();
    		keyValue=e.getKey().toString();
    		break;
    	}
    	return keyValue;
	}
    
    /**
     * 创建订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/order", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createTrxorder(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam("dateValue") String dateValue , @ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        try {
        	Teacher teacher = teacherService.getTeacherInfoById(trxorder.getTeacherId());
        	request.setAttribute("teacher", teacher);
        	// 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("type", request.getParameter("type"));// 下单类型
            sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
            sourceMap.put("userid", getLoginUserId(request).toString());//用户Id
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());//web购买
            sourceMap.put("payType", request.getParameter("payType"));//支付类型
            sourceMap.put("reqIp",WebUtils.getIpAddr(request));//用户ip
            sourceMap.put("courseModel",trxorder.getCourseModel());//上课类型
            sourceMap.put("studentName",trxorder.getStudentName());//学生姓名
            sourceMap.put("lessionNum",trxorder.getLessionNum().toString());//总课时
            sourceMap.put("mobile",trxorder.getMobile());//手机
            sourceMap.put("remarks",trxorder.getRemarks());//备注
            sourceMap.put("studyAddress",trxorder.getStudyAddress());//上课地址
            sourceMap.put("teacherId",trxorder.getTeacherId().toString());//教师id
            sourceMap.put("courseId",trxorder.getCourseId().toString());//课程id 
            sourceMap.put("dateValue",request.getParameter("dateValue"));//上课时间字符串
            Map<String, Object> res = trxorderService.addTrxorder(sourceMap);
            if(res.get("msg")!=null){
            	setJson(true, "success", res);
            	return json;
            }
            setJson(true, "success", res);
        } catch (Exception e) {
            logger.error("TrxorderController.createTrxorder error", e);
            setJson(false, "", null);
        }
        return json;
    }
    /**
     * 创建班课订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/order_class", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createClassTrxorder(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam("dateValue") String dateValue, @ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        try {
        	Teacher teacher = teacherService.getTeacherInfoById(trxorder.getTeacherId());
        	request.setAttribute("teacher", teacher);
        	// 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("type", request.getParameter("type"));// 下单类型
            sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
            sourceMap.put("userid", getLoginUserId(request).toString());//用户Id
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());//web购买
            sourceMap.put("payType", request.getParameter("payType"));//支付类型
            sourceMap.put("reqIp",WebUtils.getIpAddr(request));//用户ip
            sourceMap.put("courseModel",trxorder.getCourseModel());//上课类型
            sourceMap.put("studentName",trxorder.getStudentName());//学生姓名
            sourceMap.put("lessionNum",trxorder.getLessionNum().toString());//总课时
            sourceMap.put("mobile",trxorder.getMobile());//手机
            sourceMap.put("remarks",trxorder.getRemarks());//备注
            sourceMap.put("studyAddress",trxorder.getStudyAddress());//上课地址
            sourceMap.put("teacherId",trxorder.getTeacherId().toString());//教师id
            sourceMap.put("courseId",trxorder.getCourseId().toString());//课程id 
            sourceMap.put("dateValue",request.getParameter("dateValue"));//上课时间字符串
            Map<String, Object> res = trxorderService.addClassTrxorder(sourceMap);
            if(res.get("msg")!=null){
            	setJson(true, "success", res);
            	return json;
            }
            setJson(true, "success", res);
        } catch (Exception e) {
            logger.error("TrxorderController.createTrxorder error", e);
            setJson(false, "", null);
        }
        return json;
    }
    /**
     * 获取订单-立即支付
     *
     * @throws Exception
     */
    @RequestMapping(value = "/order/getorder")
    public String getTrxorder(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        try {
        	Trxorder trxorder2 = trxorderService.getTrxorderById(trxorder.getId());
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            request.setAttribute("order", trxorder2);
            request.setAttribute("balance", userAccount.getBalance().toString());// 账户余额
            if (userAccount.getBalance().compareTo(trxorder2.getAmount()) < 0) {
                // 还需支付的金额
            	request.setAttribute("bankAmount", trxorder2.getAmount().subtract(userAccount.getBalance()).toString());
            }else{
            	// 余额支付的金额
            	request.setAttribute("balanceAmount", userAccount.getBalance().subtract(trxorder2.getAmount()).toString());
            }
        } catch (Exception e) {
            logger.error("TrxorderController.getTrxorder error", e);
            setJson(false, "", null);
        }
        return payOrder;
    }

    /**
     * 跳转到支付宝银行，企业支付宝，个人未开发 , 易宝支付
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/order/bank")
    public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId,
                           @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes) {
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (ObjectUtils.isNotNull(trxorder)) {
                // 先查询账户的金额是否足够支付本次订单的，如果购，直接走扣账流程
                Map<String, String> sourceMap = new HashMap<String, String>();
                sourceMap.put("total_fee", "0.00");// 充值金额，先设置为0.尝试账户余额直接支付
                sourceMap.put("requestId", trxorder.getRequestId());
                sourceMap.put("userId", getLoginUserId(request).toString());
                sourceMap.put("payType", PayType.ACCOUNT.toString());
                Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                // 余额支付成功,直接返回支付成功页面
                if (res.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)) {
                    redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    User user = userService.getUserById(trxorder.getUserId());
                    // 购买课程成功
                    
                    // 发送邮件
                    
                    // 发送短信
            		try {
            				Teacher teacher = this.teacherService.getTeacherById(trxorder.getTeacherId());
            				userMobileMsgService.sendEx(SendMsgConstans.SEND_BUY_COURSE, teacher.getUserExpand().getMobile(), user.getRealname(), trxorder.getCourseName(), null,null); 
            		} catch (Exception e) {
            			logger.info("send gotobank error ");
            		}
                    return "redirect:/front/success";
                } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                    // 不够时，走银行流程，支付的金额为差的金额
                    if (payType.equals(PayType.ALIPAY.toString())) {
                        return gotoalipay(request, response, res);
                    }else if (payType.equals(PayType.WEIXIN.toString())) {
						// 尝试支付余额不足,进行微信扫码支付
					    Trxorder trxOrder= trxorderService.getTrxorderByRequestId(trxorder.getRequestId());
					    String wxPayUrl = weixinPayService.getWxpayUrl(trxOrder.getRequestId(), "course");
					    request.setAttribute("wxPayUrl",wxPayUrl );
					    request.setAttribute("requestId",trxorder.getRequestId());
					    request.setAttribute("orderId",trxorder.getId());
						return qcCode;
					}
                } else {// 优惠券错误信息
                	redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/front/success";
                }
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
     * @param sourceMap
     * @return
     */
    public String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {

        try {
            logger.info("+++gotoalipay sourceMap:" + sourceMap);
            Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String requestId = sourceMap.get("requestId");// 订单支付订单号
            String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
            String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
            String sign_type = "MD5";// 文件加密机制（不可以修改）
            String out_trade_no = guidGeneratorService.gainCode("PAY",true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
            String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
            // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            // (账户内提取)
            String key = websitemap.get("alipaykey"); // 支付宝安全校验码(账户内提取)
            String body =  getLoginUserId(request).toString() + "-" +requestId+"-" +out_trade_no;;// 商品描述，推荐格式：商品名称（订单编号：订单编号）
            String total_fee = sourceMap.get("bankAmount");// 订单总价,差价尚须银行支付的金额
            // total_fee = String.valueOf(total_fee); // 订单总价
            String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
            String seller_email = websitemap.get("sellerEmail"); // 卖家支付宝帐户
            // subject 商品名称
            String subject = OrderConstans.companyName + requestId;
            // 扩展信息,存用户id和requestId.重要，必须存
            String extra_common_param = getLoginUserId(request).toString()+","+requestId;

            String show_url = "http://" + request.getContextPath() + "/"; // 商品地址，
            // 根据集成的网站而定
            // 回调的地址
            String path = CommonConstants.contextPath;
            String notify_url = path + "/order/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
            String return_url = path + "/order/alipaynotify/2"; // 支付完成后跳转返回的网址URL
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
    @RequestMapping("/order/alipaynotify/{rtype}")
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
                    sourceMap.put("userId", extra_common_param.split(",")[0]);
                    sourceMap.put("requestId", extra_common_param.split(",")[1]);
                    sourceMap.put("payType", PayType.ALIPAY.toString());
                    Trxorder trxorder=trxorderService.getTrxorderByRequestId(extra_common_param.split(",")[1]);
                    if(trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
                    	if(rtype==1){
                    		return null;
                    	}else{
                    		redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
                        	return "redirect:/front/success";
                    	}
                    }
                    // 必须校验支付的金额
                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                    logger.info("/order/alipaynotify/" + rtype + " res:" + res);
                    if (ObjectUtils.isNotNull(res)) {

                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) &&  res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
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
     * 学生取消订单 
     * @param request
     * @return
     */
    @RequestMapping("/cancleorderStudent/{orderId}")
    public String cancleOrder(HttpServletRequest request,@PathVariable("orderId") Long orderId){
    	try {
			UserExpandDto user = this.userExpandService.getUserExpandByUids(getLoginUserId(request));
			trxorderDetailService.updateTrxorderDetailCancel(orderId,new Long(user.getUserType()));
		} catch (Exception e) {
		     e.printStackTrace();
            logger.info("取消订单", e);
		}
    	return "redirect:/uc/order";
    }
    /**
     * 老师取消订单 
     * @param request
     * @return
     */
    @RequestMapping("/cancleorderTeacher/{orderId}")
    public String cancleOrderTeacher(HttpServletRequest request,@PathVariable("orderId") Long orderId){
    	try {
			UserExpandDto user = this.userExpandService.getUserExpandByUids(getLoginUserId(request));
			trxorderDetailService.updateTrxorderDetailCancel(orderId,new Long(user.getUserType()));
		} catch (Exception e) {
		     e.printStackTrace();
            logger.info("取消订单", e);
		}
    	return "redirect:/uc/teacherOrder";
    }
    /**
     * 订单详情
     * @param request
     * @return
     */
    @RequestMapping("/uc/odetail/{orderId}")
    public  ModelAndView toOrderDetailse(HttpServletRequest request,@ModelAttribute("page") PageEntity page,@PathVariable("orderId") Long orderId){
    	ModelAndView modelAndView = new ModelAndView();
		QueryTrxorder queryTrxorder = new QueryTrxorder();
    	modelAndView.setViewName(oDetails);
    	try {
			queryTrxorder.setId(orderId);
			TrxOrderDTO trxOrderDTO= trxorderService.queryOrderForWebUc(queryTrxorder, page).get(0);
			modelAndView.addObject("trxorder", trxOrderDTO);
		} catch (Exception e) {
		     e.printStackTrace();
            logger.info("取消订单", e);
		}
    	return modelAndView;
    }
    
    
	/**
	 * 订单列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/order")
	public ModelAndView orderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryTrxorder queryTrxorder = new QueryTrxorder();
		modelAndView.setViewName(orderList);
		try {
			page.setPageSize(5);
			this.setPage(page);
			queryTrxorder.setUserId(UserController.getLoginUserId(request));
			//queryTrxorder.setPayType(request.getParameter("payType"));
			queryTrxorder.setTrxStatus(request.getParameter("trxStatus")); // 订单状态
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, this.getPage());
			//订单信息
			modelAndView.addObject("trxorderList", trxorderList);
			modelAndView.addObject("page", this.getPage());
			//modelAndView.addObject("payStatusRe", queryTrxorder.getPayType());
			modelAndView.addObject("trxStatusRe", queryTrxorder.getTrxStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
    
	/**
	 * 订单列表(教师)
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/teacherOrder")
	public ModelAndView teacherOrderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryTrxorder queryTrxorder = new QueryTrxorder();
		modelAndView.setViewName(teacherOrderList);
		try {
			page.setPageSize(5);
			this.setPage(page);
			modelAndView.addObject("page", this.getPage());
            Teacher teacher = teacherService.getTeacherByUserId(getLoginUserId(request));
            queryTrxorder.setTeacherId(teacher.getId());
			queryTrxorder.setTrxStatus(request.getParameter("trxStatus")); // 订单状态
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, this.getPage());
			//订单信息
			modelAndView.addObject("trxorderList", trxorderList);
			modelAndView.addObject("page", this.getPage());
			modelAndView.addObject("trxStatusRe", queryTrxorder.getTrxStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	public void orderList(){
		
	}
	
    /**
     * 获取快钱返回参数
     */
    private KqParamInfo initKqReturnParms(HttpServletRequest request) {
        KqParamInfo kqParamInfo = new KqParamInfo();
        kqParamInfo.setMerchantAcctId((String) request.getParameter("merchantAcctId").trim());
        kqParamInfo.setVersion((String) request.getParameter("version").trim());
        kqParamInfo.setLanguage((String) request.getParameter("language").trim());
        kqParamInfo.setSignType((String) request.getParameter("signType").trim());
        kqParamInfo.setPayType((String) request.getParameter("payType").trim());
        kqParamInfo.setBankId((String) request.getParameter("bankId").trim());
        kqParamInfo.setOrderId((String) request.getParameter("orderId").trim());
        kqParamInfo.setOrderTime((String) request.getParameter("orderTime").trim());
        kqParamInfo.setOrderAmount((String) request.getParameter("orderAmount").trim());
        kqParamInfo.setDealId((String) request.getParameter("dealId").trim());
        kqParamInfo.setBankDealId((String) request.getParameter("bankDealId").trim());
        kqParamInfo.setDealTime((String) request.getParameter("dealTime").trim());
        kqParamInfo.setPayAmount((String) request.getParameter("payAmount").trim());
        kqParamInfo.setFee((String) request.getParameter("fee").trim());
        kqParamInfo.setExt1((String) request.getParameter("ext1").trim());
        kqParamInfo.setExt2((String) request.getParameter("ext2").trim());
        kqParamInfo.setPayResult((String) request.getParameter("payResult").trim());
        kqParamInfo.setErrCode((String) request.getParameter("errCode").trim());
        kqParamInfo.setSignMsg((String) request.getParameter("signMsg").trim());
        return kqParamInfo;
    }
    /**
     * 重新支付验证
     *
     * @param request
     * @return String
     */
    @RequestMapping("/front/repaycheck/{orderId}")
    @ResponseBody
    public Map<String,Object> repayCheck(HttpServletRequest request,@PathVariable Long orderId) {
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (trxorder==null) {//为空
                this.setJson(false, "订单不存在", null);
               return json;
            } else if(!trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {//订单状态不为INIT
               this.setJson(false, "订单状态异常，请稍后再试", null);
               return json;
            }
            this.setJson(true, "true", null);
        } catch (Exception e) {
        	logger.error("TrxorderController.repayCheck", e);
        	this.setJson(false, "error", null);
        }
        return json;
    }
    /**
     * 重新支付显示页面
     *
     * @param request
     * @param orderId
     * @return String
     */
    @RequestMapping("/front/repay/{orderId}")
    public String repay(HttpServletRequest request,@PathVariable Long orderId) {
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (ObjectUtils.isNotNull(trxorder)&&trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {//不为空且订单状态为INIT
                UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
                request.setAttribute("trxorder", trxorder);
                request.setAttribute("userAccount", userAccount);
                TrxorderDetail trxorderDetail = new TrxorderDetail();
                trxorderDetail.setRequestId(trxorder.getRequestId());
                //订单流水 购物车显示
                List<TrxorderDetail> orderList= trxorderDetailService.getTrxorderDetailList(trxorderDetail);
                request.setAttribute("orderList",orderList);
                if(trxorder.getCouponCodeId()>0){//查询优惠券编码信息
                	// 查询优惠券编码
                	CouponCode couponCode = couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
                	request.setAttribute("couponCode",couponCode.getCouponCode());
                }
                //需要支付的金额
                request.setAttribute("bankAmount",trxorder.getOrderAmount());
                
            } else {
                return ERROR;
            }

        } catch (Exception e) {
            return setExceptionRequest(request, e);
            
        }
        return repay;
    }
    
    /**
	 * 易宝支付信息
	 * 
	 * @param request
	 * @param response
	 * @param sourceMap
	 * @return
	 */
	public String gotoyp(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {
		try {
			logger.info("+++ gotoyp sourceMap" + sourceMap);
			request.setCharacterEncoding("GBK");
			Map<String, String> websitemap = getYeePayInfo();// 获得支付宝配置
			String keyValue = websitemap.get("keyValue"); // 易宝valueKey
			String p1_MerId = websitemap.get("p1_MerId"); // 易宝P1MerId
			String requestId = sourceMap.get("requestId");// 订单支付订单号
			String bankId = request.getParameter("bankId");
        
			// 易宝参数定义
			YbParamInfo ybParamInfo = new YbParamInfo();
				
			ybParamInfo.setP0_Cmd("Buy");// 固定类型 切勿改动
			ybParamInfo.setP1_MerId(p1_MerId);
			ybParamInfo.setPayUrl("https://www.yeepay.com/app-merchant-proxy/node");// 支付地址了
			ybParamInfo.setP2_Order(requestId);
			ybParamInfo.setP3_Amt(sourceMap.get("bankAmount"));
	/*		ybParamInfo.setP3_Amt("0.01");*/
			ybParamInfo.setP4_Cur("CNY");// 固定类型 切勿改动
			ybParamInfo.setP8_Url(CommonConstants.contextPath + "/order/ybReturn"); // 回调地址
			ybParamInfo.setPa_MP(getLoginUserId(request).toString() + "," + requestId);
			ybParamInfo.setPr_NeedResponse("1");
			ybParamInfo.setP9_SAF("0");
			if(bankId!=null){
			ybParamInfo.setPd_FrpId(bankId);
			}
			
/*			ybParamInfo.setP0_Cmd("Buy");
			ybParamInfo.setP1_MerId(p1_MerId);
			ybParamInfo.setPayUrl("https://www.yeepay.com/app-merchant-proxy/node");// 支付地址了
			ybParamInfo.setP4_Cur("CNY");
			ybParamInfo.setP2_Order("403504587qwe");
			ybParamInfo.setP3_Amt("0.01");
			ybParamInfo.setP8_Url("www.baidu.com");
			ybParamInfo.setP9_SAF("0");
			ybParamInfo.setPr_NeedResponse("1");
			ybParamInfo.setPt_Email("邮件地址");*/
			//密钥生成
			String  hmac =getReqMd5HmacForOnlinePayment(ybParamInfo.getP0_Cmd(),ybParamInfo.getP1_MerId(),ybParamInfo.getP2_Order(),ybParamInfo.getP3_Amt(),ybParamInfo.getP4_Cur(),ybParamInfo.getP5_Pid(),ybParamInfo.getP6_Pcat(),ybParamInfo.getP7_Pdesc(),ybParamInfo.getP8_Url(),ybParamInfo.getP9_SAF(),ybParamInfo.getPa_MP(),
					ybParamInfo.getPd_FrpId(),ybParamInfo.getPm_Period(),ybParamInfo.getPn_Unit(),ybParamInfo.getPr_NeedResponse(),ybParamInfo.getPt_UserName(),ybParamInfo.getPt_PostalCode(),ybParamInfo.getPt_Address()
					,ybParamInfo.getPt_TeleNo(),ybParamInfo.getPt_Mobile(),ybParamInfo.getPt_Email(),keyValue);
			ybParamInfo.setHmac(hmac);
			System.out.println(hmac);
			request.setAttribute("ybParamInfo", ybParamInfo);
		} catch (Exception e) {
			logger.error("gotoyp" + e);
			return ERROR;
		}
		return yeepay;
	}
	
	/**
	 * 生成hmac方法 业务类型
	 * 
	 * @param p0_Cmd
	 *            商户编号
	 * @param p1_MerId
	 *            商户订单号
	 * @param p2_Order
	 *            支付金额
	 * @param p3_Amt
	 *            交易币种
	 * @param p4_Cur
	 *            商品名称
	 * @param p5_Pid
	 *            商品种类
	 * @param p6_Pcat
	 *            商品描述
	 * @param p7_Pdesc
	 *            商户接收支付成功数据的地址
	 * @param p8_Url
	 *            送货地址
	 * @param p9_SAF
	 *            商户扩展信息
	 * @param pa_MP
	 *            银行编码
	 * @param pd_FrpId
	 *            订单有效期
	 * @param pm_Period
	 *            订单有效期单位
	 * @param pn_Unit
	 *            应答机制
	 * @param pr_NeedResponse
	 *            考生/学员姓名
	 * @param pt_UserName
	 *            身份证号
	 * @param pt_PostalCode
	 *            地区
	 * @param pt_Address
	 *            报考序号
	 * @param pt_TeleNo
	 *            手机号
	 * @param pt_Mobile
	 *            邮件地址
	 * @param pt_Email
	 *            商户密钥
	 * @param keyValue
	 * @return
	 */
	public static String getReqMd5HmacForOnlinePayment(String p0_Cmd, String p1_MerId, String p2_Order, String p3_Amt, String p4_Cur, String p5_Pid, String p6_Pcat, String p7_Pdesc, String p8_Url, String p9_SAF,
			String pa_MP, String pd_FrpId, String pm_Period, String pn_Unit, String pr_NeedResponse, String pt_UserName, String pt_PostalCode, String pt_Address, String pt_TeleNo, String pt_Mobile, String pt_Email, String keyValue) {
		StringBuffer sValue = new StringBuffer();
		// 业务类型
		sValue.append(p0_Cmd);
		// 商户编号
		if(p1_MerId==null){
			p1_MerId="";
		}
		sValue.append(p1_MerId);
		// 商户订单号
		if(p2_Order==null){
			p2_Order="";
		}
		sValue.append(p2_Order);
		// 支付金额
		sValue.append(p3_Amt);
		// 交易币种
		sValue.append(p4_Cur);
		if(p5_Pid==null){
			p5_Pid="";
		}
		// 商品名称
		sValue.append(p5_Pid);
		// 商品种类
		if(p6_Pcat==null){
			p6_Pcat="";
		}
		sValue.append(p6_Pcat);
		// 商品描述
		if(p7_Pdesc==null){
			p7_Pdesc="";
		}
		sValue.append(p7_Pdesc);
		// 商户接收支付成功数据的地址
		if(p8_Url==null){
			p8_Url="";
		}
		sValue.append(p8_Url);
		// 送货地址
		if(p9_SAF==null){
			p9_SAF="";
		}
		sValue.append(p9_SAF);
		// 商户扩展信息
		if(pa_MP==null){
			pa_MP="";
		}
		sValue.append(pa_MP);
		// 银行编码
		if(pd_FrpId==null){
			pd_FrpId ="";
		}
		sValue.append(pd_FrpId);
		// 有效期
		if(pm_Period==null){
			pm_Period="";
		}
		sValue.append(pm_Period);
		// 有效期单位
		if(pn_Unit==null){
			pn_Unit="";
		}
		sValue.append(pn_Unit);
		// 应答机制
		sValue.append(pr_NeedResponse);
		// 考生/学员姓名
		if(pt_UserName==null){
			pt_UserName="";
		}
		sValue.append(pt_UserName);
		// 身份证号
		if(pt_PostalCode==null){
			pt_PostalCode="";
		}
		sValue.append(pt_PostalCode);
		// 地区
		if(pt_Address==null){
			pt_Address="";
		}
		sValue.append(pt_Address);
		// 报考序号
		if(pt_TeleNo==null){
			pt_TeleNo="";
		}
		sValue.append(pt_TeleNo);
		// 手机号
		if(pt_Mobile==null){
			pt_Mobile="";
		}
		sValue.append(pt_Mobile);
		// 邮件地址
		if(pt_Email==null){
			pt_Email="";
		}
		sValue.append(pt_Email);

		String sNewString = null;
		
		/*System.out.println(sValue.toString());*/
 		sNewString = hmacSign(sValue.toString(), keyValue);
		return (sNewString);
	}
    

	
	public static String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encodingCharset);
			value = aValue.getBytes(encodingCharset);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}

		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {

			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return toHex(dg);
	}

	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}

	/**
	 * 
	 * @param args
	 * @param key
	 * @return
	 */
	public static String getHmac(String[] args, String key) {
		if (args == null || args.length == 0) {
			return (null);
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i]);
		}
		return (hmacSign(str.toString(), key));
	}

	/**
	 * @param aValue
	 * @return
	 */
	public static String digest(String aValue) {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encodingCharset);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return toHex(md.digest(value));

	}

	
    
    
    /**
     * 重新支付修改订单信息
     *
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping("/front/repayupdate")
    @ResponseBody
    public Map<String,Object> repay(HttpServletRequest request) {
        try {
        	String couponCode=request.getParameter("couponcode");//优惠编码
        	String requestId=request.getParameter("requestId");//订单请求号
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderByRequestId(requestId);
            CouponCodeDTO couponCodeDTO=null;
            Map<String, Object> mapCode=null;
            if (couponCode != null &&couponCode != "") {// 重新支付使用了优惠券
     			// 查询优惠券编码
        		couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
        		List<Course> courses=trxorderService.getTrxCourseByRequestId(trxorder.getRequestId());//订单课程集合
     			// 验证优惠券编码
     			mapCode = couponCodeService.checkCode(couponCodeDTO,getLoginSubjectId(request));
     			if(mapCode.get("msg")!="true"){//优惠券编码验证不通过
     				this.setJson(false,mapCode.get("msg").toString(), null);
     				return json;
     			}
        	}
            UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
            //优惠金额
    		BigDecimal couponAmount=new BigDecimal(0);
    		//还需要支付的金额
            BigDecimal amount=new BigDecimal(0);
            //计算金额，更新订单
            if (ObjectUtils.isNotNull(trxorder)&&trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {//不为空且订单状态为INIT
            	if(trxorder.getCouponCodeId()<0&&couponCode.equals("")){//订单未使用优惠券,重新支付也未使用优惠券 不更新
            		this.setJson(true,"true", null);
                }
            	//订单使用了优惠券并且未更改优惠券 不更新
            	else if(trxorder.getCouponCodeId()>0&&couponCodeDTO!=null&&couponCodeDTO.getId().equals(trxorder.getCouponCodeId())){
            		this.setJson(true,"true", null);
                }else{//更新订单的优惠券列并重新计算价格
                	if(couponCode.equals("")){//重新支付无优惠券
                		trxorder.setCouponCodeId(0L);//优惠券编码id
                		trxorder.setCouponAmount(new BigDecimal(0));//优惠金额
                		trxorder.setDescription("无");
                		trxorder.setAmount(trxorder.getOrderAmount());//实际支付金额等于订单金额
                	}else{//重新支付有优惠券
                		trxorder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
                		
                		if (couponCodeDTO.getType() == 1) {// 折扣券
        					BigDecimal tempPrice = new BigDecimal(Double.parseDouble(mapCode.get("tempPrice").toString()) * 0.1);
        					couponAmount = tempPrice.multiply(couponCodeDTO.getAmount());// 折扣优惠金额
        				} else {// 定额券
        					couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
        				}
                		trxorder.setCouponAmount(couponAmount);//优惠金额
        				// 实际需要支付的金额,四舍五去取2位
        				amount = trxorder.getOrderAmount().subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
        				trxorder.setAmount(amount);// 实际支付金额
        				if(mapCode.get("limitCourseIds")!=null){// 订单描述，限制的课程id字符串
        					trxorder.setDescription("课程限制：" + mapCode.get("limitCourseIds").toString());
        				}else{
        					trxorder.setDescription("课程限制：所有课程");
        				}
                	}
                	trxorderService.updateTrxorder(trxorder);
                }
            	Map<String,Object> map=new HashMap<String, Object>();
            	map.put("balance", userAccount.getBalance());
            	map.put("amount", trxorder.getAmount());
            	if (userAccount.getBalance().compareTo(trxorder.getAmount()) < 0) {
                    // 还需支付的金额
            		map.put("bankAmount", trxorder.getAmount().subtract(userAccount.getBalance()).toString());
                }
            	this.setJson(true, "true", map);
            } else {
            	this.setJson(false, "false", null);
            }

        } catch (Exception e) {
        	logger.error("TrxorderController.repayupdate", e);
        	this.setJson(false, "error", null);
        }
        return json;
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
	 * 获取易宝密钥
	 * 
	 * @return
	 */
	public Map<String, String> getYeePayInfo() {
		Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.yee.toString());
		JsonParser jsonParser = new JsonParser();
		// 获得详细info
		JsonObject jsonObject = jsonParser.parse(gson.toJson(map.get(WebSiteProfileType.yee.toString()))).getAsJsonObject();
		if (!jsonObject.isJsonNull()) {
			Map<String, String> websitemap = new HashMap<String, String>();
			websitemap.put("p1_MerId", jsonObject.get("p1_MerId").getAsString());// 支付宝key
			websitemap.put("keyValue", jsonObject.get("keyValue").getAsString());// 支付宝合作伙伴id
			return websitemap;
		}
		return null;
	}
	
	@RequestMapping("/order/review")
	@ResponseBody
	public Map<String,Object> reviewTrxOrderByRequestId(HttpServletRequest request){
		try {
			String requestId=request.getParameter("requestId");
			Trxorder trxorder=trxorderService.getTrxorderByRequestId(requestId);
			if(trxorder.getTrxStatus().equalsIgnoreCase(TrxOrderStatus.SUCCESS.toString())){//订单支付成功
				this.setJson(true, "true", null);
			}else{
				this.setJson(true, "false", null);
			}
		} catch (Exception e) {
			logger.error("eviewTrxOrderByRequestId---------e", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	
	/**
	 * 订单申请退款
	 * @param request
	 * @param requestId
	 * @return
	 */
	@RequestMapping("/order/ajax/refund/{orderId}")
	@ResponseBody
	public Map<String, Object> updateTrxorderRefund(HttpServletRequest request, @PathVariable("orderId") Long orderId){
		try {
			if (ObjectUtils.isNotNull(orderId) && orderId.intValue()>0) {
				Trxorder trxorder = trxorderService.getTrxorderById(orderId);
				if (ObjectUtils.isNotNull(trxorder)) {
					// 退款原因
					String description = request.getParameter("description");
					UserExpandDto user = this.userExpandService.getUserExpandByUids(getLoginUserId(request));
					trxorderService.updateTrxorderRefundAudit(orderId,description,new Long(user.getUserType()));
				}
				this.setJson(true, "订单申请退款成功,请等待审核", null);
			} else {
				this.setJson(false, "订单申请退款失败", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderController.updateTrxorderRefund", e);
			this.setJson(false, "系统异常,请稍后重试", null);
		}
		return json;
	}
	/**
	 * 查询用户是否已报名
	 * @param request
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/order/ajax/selectUserIsJoin/{courseId}")
	@ResponseBody
	public Map<String,Object> selectUserIsJoin(HttpServletRequest request,@PathVariable("courseId") Long courseId){
		try {
			String message="ok";
			int count=trxorderService.queryUserIsJoin(getLoginUserId(request), courseId,TrxOrderStatus.SUCCESS.toString());
			if(count>0){//用户已报名
				message="entry";
			}else{//用户已下订单 未支付
				int count1=trxorderService.queryUserIsJoin(getLoginUserId(request), courseId,TrxOrderStatus.INIT.toString());
				if(count1>0){
					message="payNo";
				}
			}
			this.setJson(true, message, null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TrxorderController.selectUserIsJoin--error",e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}
}