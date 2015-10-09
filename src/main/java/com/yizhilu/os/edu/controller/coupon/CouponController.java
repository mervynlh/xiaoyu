package com.yizhilu.os.edu.controller.coupon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.lang.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.coupon.CouponService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.ShopcartService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * Coupon管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class CouponController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	private static final String toAddCoupon = getViewPath("/coupon/Coupon_add");
	private static final String couponOrder = getViewPath("/order/ajax_coupon");
	private static final String teacher_coupon = getViewPath("/ucenter/teacher/teacher_coupon");// 我的优惠券(老师)

	@Autowired
	private CouponService couponService;
	@Autowired
	private CouponCodeService couponCodeService;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@InitBinder("coupon")
	public void initBinderQueryCouponCode(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("coupon.");
	}

	/**
	 * 
	 * 验证优惠券的限制范围
	 * 
	 * @return
	 */
	@RequestMapping("/ajax/checkCouponCode")
	@ResponseBody
	public Map<String, Object> checkCouponCode(HttpServletRequest request,@RequestParam BigDecimal money) {
		try {
			String couponCode = request.getParameter("couponCode");// 优惠券编码
			Long userId = getLoginUserId(request);// 用户id
			String msg="";//返回信息
			// 查询优惠券编码
			CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
			if(couponCodeDTO==null){//
				msg="优惠券不存在!";
			}else{
				if(couponCodeDTO.getUserId().longValue()!=userId.longValue()){//判断是否是用户的优惠券
					msg="您尚未拥有该优惠券！";
				}else{
					int compareResult = couponCodeDTO.getLimitAmount().compareTo(money);
					if(compareResult<=0){//，-1表示小于，0是等于，1是大于。
						msg="success";
					}else{
						msg="您的优惠券不符合条件！";
					}
				}
			}
			if (msg.equals("success")) {
				this.setJson(true, msg, couponCodeDTO);
			} else {
				this.setJson(false, msg, couponCodeDTO);
			}
		} catch (Exception e) {
			logger.error("CouponController.checkCouponCode--验证优惠券出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 
	 * 验证优惠券的限制范围
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/check")
	@ResponseBody
	public Map<String, Object> checkCouponByCode(HttpServletRequest request) {
		try {
			String couponCode = request.getParameter("couponCode");// 优惠券编码
			String memberCode = request.getParameter("memberCode");// 会员优惠券
			String requestId = request.getParameter("requestId");// 订单编号
			if (memberCode == null) {
				memberCode = "";
			}
			Long userId = getLoginUserId(request);// 用户id
			// 查询优惠券编码
			CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
			List<Course> courses = null;
			if (!memberCode.equals("memberCode")) {// 非会员订单
				if (requestId != null && requestId != "") {// 订单课程集合
					courses = trxorderService.getTrxCourseByRequestId(requestId);// 订单课程集合
				} else {// 查询购物车的课程集合
					courses = shopcartService.getShopcartCourseList(userId);
					if (courses == null || courses.size() == 0) {// 如果购物车为空
						this.setJson(false, "购物车不能为空", null);
						return json;
					}
				}
			}

			// 验证优惠券编码
			Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO,userId);
			if (map.get("msg") == "true") {
				map.put("couponCodeDTO", couponCodeDTO);
				this.setJson(true, "true", map);
			} else {
				this.setJson(false, map.get("msg").toString(), null);
			}
		} catch (Exception e) {
			logger.error("CouponController.checkCouponByCode--验证优惠券出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 修改Coupon
	 * 
	 * @param coupon
	 *            要修改的Coupon
	 */
	public void updateCoupon(Coupon coupon) {
		couponService.updateCoupon(coupon);
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/toAddCoupon")
	public String toAddCoupon() {
		return toAddCoupon;
	}

	/**
	 * 教师添加优惠券
	 * @param coupon
	 * @param request
	 * @return
	 */
	@RequestMapping("/coupon/add")
	@ResponseBody
	public Map<String,Object> addCoupon(@ModelAttribute("coupon") Coupon coupon, HttpServletRequest request) {
		try {
			Long userId = getLoginUserId(request);
			User user = this.userService.getUserById(userId);
			Teacher teacher = this.teacherService.getTeacherByUserId(userId);
			if(user.getUserType()==1){
				coupon.setCreateType(1);// 前台老师
				coupon.setOptuserName(StringUtils.isEmpty(teacher.getUserExpand().getRealname())?teacher.getUserExpand().getMobile():teacher.getUserExpand().getRealname());
				coupon.setTeacherId(teacher.getId());
			}
			coupon.setCreateTime(new Date());
			couponService.addCoupon(coupon);// 添加优惠券
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("CouponController.addCoupon--添加优惠券出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 判断教师是否可以创建优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping("/coupon/isCreateCoupon")
	@ResponseBody
	public Map<String,Object> isCreateCoupon(HttpServletRequest request){
		try {
			Long userId = getLoginUserId(request);
			Teacher teacher = this.teacherService.getTeacherByUserId(userId);
			Long l = this.couponService.getCountByTeacherId(teacher.getId());
			// 判断优惠券数量是否超过3个
			if (l < 3) {
				this.setJson(true, "success", null);
			}else {
				this.setJson(false, "notCreate", null);
			}
		} catch (Exception e) {
			logger.error("CouponController.addCoupon--添加优惠券出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 教师获取创建的优惠券
	 * 
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/coupon")
	public String getTeacherCoupon(HttpServletRequest request) {
		try {
			Long userId = getLoginUserId(request);
			Long teacherId = teacherService.getTeacherByUserId(userId).getId();
			List<Coupon> couponList = this.couponService.getCouponListByTeacherId(teacherId);
			request.setAttribute("couponList", couponList);
		} catch (Exception e) {
			logger.error("UserController.getTeacherCoupon", e);
		}
		return teacher_coupon;
	}
	
	/**
	 * 删除学习券
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/coupon/deleteCoupon/{id}")
	public String deleteCoupon(@PathVariable Long id) {
		try {
			couponService.deleteCouponById(id);
		} catch (Exception e) {
			logger.error("CouponController.deleteCoupon", e);
		}
		return "redirect:/admin/coupon/page";
	}

	/**
	 * 学生领取优惠券
	 * 
	 * @param couponId
	 *            优惠券id
	 * @param request
	 * @return
	 */
	@RequestMapping("/coupon/createcode")
	@ResponseBody
	public Map<String, Object> createCoding(@RequestParam("couponId") Long couponId, HttpServletRequest request) {
		try {
			// 用户id
			Long userId = getLoginUserId(request);
			// 用户
			User user = this.userService.getUserById(getLoginUserId(request));
			// 优惠券
			Coupon coupon = this.couponService.getCouponById(couponId);
			// 用户领取的优惠券
			CouponCode couponCode = this.couponCodeService.getCouponCodeByCouponIdAndUserId(coupon.getId(), userId);
			// 用户是否是教师
			if(user.getUserType()==1){
				this.setJson(false, "youIsTeacher", null);
			// 用户已领取优惠券
			}else if(couponCode!=null){
				this.setJson(false, "owned", null);
			// 优惠券已领完
			}else if(coupon.getReceiveNum()>=coupon.getTotalNum()){
				this.setJson(false, "isEmpty", null);
			// 用户没有领取优惠券
			}else if(couponCode==null){
				this.couponCodeService.addCouponCode(userId, couponId);
				this.setJson(true, "success", null);
			}
		} catch (Exception e) {
			logger.error("CouponController.createCoding--生成优惠券编码出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 使用优惠券
	 * 
	 * @return
	 */
	@RequestMapping("/coupon/useCoupon/{id}")
	@ResponseBody
	public Map<String, Object> useCoupon(@PathVariable Long id) {
		try {
			couponCodeService.useCouponCode(id);
		} catch (Exception e) {
			logger.error("CouponController.useCoupon", e);
		}
		return json;
	}

	/**
	 * 获取老师优惠券列表
	 * 
	 * @param teaId
	 *            教师id
	 * @return
	 */
	@RequestMapping("/coupon/getCoupon/{teaId}")
	@ResponseBody
	public Map<String, Object> getCoupon(@PathVariable("teaId") Long teaId) {
		try {
			List<Coupon> list = this.couponService.getCouponListByTeacherId(teaId);
			this.setJson(true, "success", list);
		} catch (Exception e) {
			logger.error("CouponController.getCoupon", e);
		}
		return json;
	}
	/**
	 * 获取老师优惠券列表
	 * 
	 * @param teaId
	 *            教师id
	 * @return
	 */
	@RequestMapping("/ajax/getCouponForUser/{teaId}")
	public String getCouponForUser(@PathVariable("teaId") Long teaId,@RequestParam("money") BigDecimal money,HttpServletRequest request) {
		try {
			//优惠券列表
			List<CouponCodeDTO> list = couponCodeService.getCouponCodeListByUserForOrder(getLoginUserId(request),teaId,money);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.error("CouponController.getCouponForUser", e);
		}
		return couponOrder;
	}
}