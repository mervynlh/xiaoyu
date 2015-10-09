package com.yizhilu.os.edu.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.controller.RandomCodeController;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.MD5;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PreventInfusion;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.CourseStatus;
import com.yizhilu.os.edu.constants.enums.ImagesType;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.constants.enums.UserExpandFrom;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;
import com.yizhilu.os.edu.constants.web.SnsConstants;
import com.yizhilu.os.edu.constants.web.WebContants;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.course.TeacherDto;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.letter.QueryMsgReceive;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetailCourse;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.teacher.TeacherProfile;
import com.yizhilu.os.edu.entity.teacher.TeacherStyle;
import com.yizhilu.os.edu.entity.user.QueryUserAccounthistory;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserAccounthistory;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.entity.user.UserForm;
import com.yizhilu.os.edu.entity.website.WebsiteImages;
import com.yizhilu.os.edu.entity.website.WebsiteProfile;
import com.yizhilu.os.edu.service.assess.AssessService;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.course.TeacherFavoritesService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;
import com.yizhilu.os.edu.service.teacher.TeacherStyleService;
import com.yizhilu.os.edu.service.user.LoginOnlineService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserAccounthistoryService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserIntegralRecordService;
import com.yizhilu.os.edu.service.user.UserIntegralService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.website.WebsiteImagesService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.service.zoomMeetings.ZoomMeetingService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName com.yizhilu.os.user.controller.UserController
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-1-13 上午11:32:19
 */
@Controller
public class UserController extends EduBaseController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	@Autowired
	private UserService userService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private UserIntegralRecordService userIntegralRecordService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccounthistoryService userAccounthistoryService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private WebsiteImagesService websiteImagesService;
	@Autowired
	private LoginOnlineService loginOnliceService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private TeacherFavoritesService teacherFavoritesService;
	@Autowired
	private CouponCodeService couponCodeService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeacherProfileService teacherProfileService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private AssessService assessService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private TeacherStyleService teacherStyleService;
	@Autowired
	private ZoomMeetingService zoomMeetingService;
	@Autowired
	private UserMobileMsgService userMobileMsgService;
	
	MemCache memCache = MemCache.getInstance();
	private String toUpdatePwd = getViewPath("/user/change_password");
	private static final String registerJsp = getViewPath("/user/register");// 注册
	private static final String loginjsp = getViewPath("/user/login");// 登录
	private static final String forgetpwdjsp = getViewPath("/user/forget_password");// 忘记密码
	private static final String teacher_home = getViewPath("/ucenter/teacher/teacher_home");// 教师个人中心首页
	private static final String teacher_course = getViewPath("/ucenter/teacher/teacher_course");// 教师课表
	private static final String my_student_list = getViewPath("/ucenter/teacher/my_student_list"); // 我的学生列表
	private static final String teacher_accout_list = getViewPath("/ucenter/teacher/account_list"); // 教师账户历史

	private static final String student_home = getViewPath("/ucenter/student/student_home");// 学生个人中心首页
	private static final String collect_teacher = getViewPath("/ucenter/student/collect_teacher");// 教师收藏
	private static final String student_coupon = getViewPath("/ucenter/student/student_coupon");// 我的优惠券(学生)
	private String to_update_pwd = getViewPath("/ucenter/update_pwd");// 修改密码
	private String uinfo = getViewPath("/ucenter/uinfo");// 基本资料
	private String avatar = getViewPath("/ucenter/avatar");// 修改头像
	private String student_jumpmobile = getViewPath("/ucenter/student/jump_mobile");// 学生绑定手机页面
	private String student_accout_list = getViewPath("/ucenter/student/account_list");// 学生账户历史
	private String queryUserLetter = getViewPath("/ucenter/u_letter_inbox");// 用户消息
	private String jump_user = getViewPath("/user/jump_user");// 绑定邮箱手机页面

	private static final String evaluation = getViewPath("/ucenter/u_evaluation_list");// 评价列表

	private static final String teacher_material_setting = getViewPath("/ucenter/teacher/teacher_setting"); // 教师个人中心资料修改
	private static final String teacher_aptitude_attestation = getViewPath(
			"/ucenter/teacher/teacher_aptitude_attestation"); // 教师资质认证
	// 绑定变量名字和属性，参数封装进类

	@InitBinder("userForm")
	public void initBinder1(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userForm.");
	}

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("teacher")
	public void initBinderTeacher(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("teacher.");
	}

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("teacherStyle")
	public void initBinderTeacherStyle(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("teacherStyle.");
	}

	@InitBinder("queryUser")
	public void initBinderqueryUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryUser.");
	}

	@InitBinder("msgReceive")
	public void initBinderMsgReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("msgReceive.");
	}

	// 注册
	@RequestMapping("/register")
	public String regist(HttpServletRequest request) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("regist----error",e);
			return setExceptionRequest(request, e);
		}
		return registerJsp;
	}

	// 忘记密码
	@RequestMapping("/forget_passwd")
	public String forgotpwd() {
		return forgetpwdjsp;
	}

	// 登录
	@RequestMapping("/login")
	public String toIndex() {
		return loginjsp;
	}

	// 修改mina
	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd() {
		return toUpdatePwd;
	}

	/**
	 * 用户操作受限制提示
	 * 
	 * @return
	 */
	@RequestMapping("/limitVerifyError")
	public String limitVerifyError() {
		return "/admin/login/limitVerifyError";
	}

	public void sendValidateMessage(String message, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * 注册用户
	 * 
	 * @param userForm
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/doregister")
	@ResponseBody
	public Map<String, Object> doregister(@ModelAttribute UserForm userForm, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 验证密码是否为空
			if (ObjectUtils.isNull(userForm.getPassword()) || StringUtils.isEmpty(userForm.getPassword())) {
				this.setJson(false, "pwdIsNull", null);
				return json;
			}
			// 验证密码一致
			if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
				this.setJson(false, "pwdNotEqual", null);
				return json;
			}
			// 验证手机是否为空
			if (ObjectUtils.isNull(userForm.getMobile()) || StringUtils.isEmpty(userForm.getMobile())) {
				this.setJson(false, "mobileIsNull", null);
				return json;
			}
			// 在手机不为空的情况下，验证手机格式是否正确
			if (ObjectUtils.isNotNull(userForm.getMobile()) && StringUtils.isNotEmpty(userForm.getMobile()) && !userForm
					.getMobile().matches("^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$")) {
				this.setJson(false, "false", null);
				return json;
			}
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(userForm.getEmail()) || PreventInfusion.sql_inj(userForm.getPassword())) {
				this.setJson(false, "regDangerWord", null);
				return json;
			}
			// 验证码
			String checkCode = request.getParameter("checkCode");
			checkCode = MD5.getMD5(checkCode);
			String mem = (String) memCache.get(MemConstans.VERIFICATION_CODE + userForm.getMobile());
			if (ObjectUtils.isNull(checkCode) || !checkCode.equals(mem)) {
				this.setJson(false, "checkCodeIsError", null);
				return json;
			}
			User user = new User();
			user.setMobile(userForm.getMobile());
			int ismobile = userService.getUserByMobile(user);
			// 验证手机唯一
			if (ismobile != 0) {
				this.setJson(false, "regMobileExist", null);
				return json;
			}

			String userIp = WebUtils.getIpAddr(request);
			user.setPassword(userForm.getPassword());
			user.setUserip(userIp);
			user.setExtendCord(userForm.getExtendCord());// 推广码
			user.setUserType(userForm.getUserType());// 用户类型0学生 1老师

			user.setRegisterFrom(UserExpandFrom.registerFrom.toString());// 账号来源是通过注册生成的
			Long upUserId = userService.addUser(user);
			// 返还上线用户积分
			if (upUserId != 0L) {
				userIntegralService.addUserIntegral(IntegralKeyword.rebate.toString(), upUserId, 0L, user.getId(), "");
			}
			// 注册送积分
			userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), user.getId(), 0L, 0L, "");
			// 注册时发送系统消息
			Map<String, Object> websitemap = websiteProfileService
					.getWebsiteProfileByType(WebSiteProfileType.web.toString());
			Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
			String company = web.get("company").toString();

			String conent = "欢迎来到" + company + ",希望您能够快乐的学习";
			msgReceiveService.addSystemMessageByCusId(conent, user.getId());
			// 执行登录操作
			userService.setLoginStatus(user, "true", request, response);
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("userRegist error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 用户登录
	 * 
	 * @param userForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dologin")
	@ResponseBody
	public Map<String, Object> dologin(@ModelAttribute UserForm userForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(userForm.getMobile()) || PreventInfusion.sql_inj(userForm.getPassword())) {
				this.setJson(false, "inputIllegal", null);
				return json;
			}
			User user = new User();
			user.setMobile(userForm.getMobile());
			user.setUserType(userForm.getUserType());
			List<User> list = null;

			// 如果是手机格式则按手机查询
			String regEx = "^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$"; // 表示a或F
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(user.getMobile());
			boolean rs = mat.find();
			if (rs) {
				list = userService.getUserListForTelLogin(user);
			}
			// 通过数据库，验证用户是否存在
			if (ObjectUtils.isNull(list)) {
				this.setJson(false, "formDataNot", null);
				return json;
			} else {
				user = list.get(0);
				// 验证密码是否正确
				if (checkIsRight(user.getPassword(), userForm.getPassword(), user.getCustomerkey())) {
					String autoThirty = request.getParameter("autoThirty");// 是否30天自动登录
					// 执行登录操作
					userService.setLoginStatus(user, autoThirty, request, response);
					this.setJson(true, "success", "登录成功");
				} else {
					this.setJson(false, "errorPassword", "密码错误");
				}
			}
			return json;
		} catch (Exception e) {
			logger.error("Usercontroller.dologin", e);
			this.setJson(false, "error", "系统异常");
			return json;
		}
	}

	/**
	 * 检查email是否存在
	 * 
	 * @param userForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkEmail")
	public Map<String, Object> checkEmail(@ModelAttribute UserForm userForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (ObjectUtils.isNotNull(userForm)) {
				User user = new User();
				user.setEmail(userForm.getEmail().toLowerCase());
				List<User> list = userService.getUserList(user);
				// 用户已经存在
				if (ObjectUtils.isNotNull(list) && list.size() > 0) {
					sendValidateMessage("false", response);
				} else {
					sendValidateMessage("true", response);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.checkEmail", e);
			this.setJson(false, "", null);
		}
		return null;
	}

	/**
	 * 验证验证码
	 * 
	 * @param randomCode
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkRandomCode")
	@ResponseBody
	public Map<String, Object> checkRandomCode(@RequestParam("randomCode") String randomCode,
			@RequestParam("mobile") String mobile, HttpServletRequest request) {
		try {
			if (ObjectUtils.isNull(randomCode)
					|| !randomCode.toUpperCase().equals(request.getSession().getAttribute("COMMON_RAND_CODE"))) {
				this.setJson(false, "randomCodeIsError", null);
			} else if (ObjectUtils.isNull(this.userService.getUserByMobileNumber(mobile))) {
				this.setJson(false, "mobileIsExist", null);
			} else {
				this.setJson(true, "success", null);
			}
		} catch (Exception e) {
			logger.error("UserController.checkRandomCode", e);
		}
		return json;
	}

	/**
	 * 验证手机是否存在
	 * 
	 * @param mobile
	 *            手机号
	 * @param userType
	 *            用户类型
	 * @return
	 */
	@RequestMapping("/checkMobile")
	@ResponseBody
	public Map<String, Object> checkMobile(@RequestParam("mobile") String mobile,
			@RequestParam("userType") int userType) {
		try {
			if (StringUtils.isNotEmpty(mobile)) {
				User user = userService.getUserByMobileNumber(mobile);
				if (ObjectUtils.isNotNull(user)) {
					if (user.getUserType() == userType) {
						this.setJson(true, "success", null);
					} else if (userType == 0) {
						this.setJson(false, "maybeTeacher", null);
					} else if (userType == 1) {
						this.setJson(false, "maybeStudent", null);
					}
				} else {
					this.setJson(false, "mobileExist", null);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.checkMobile", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 检查密码是否正确
	 * 
	 * @param dbPassword
	 * @param userPassword
	 * @param userkey
	 * @return
	 */
	public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
		String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
		return despassword.equals(dbPassword);
	}

	/**
	 * 用户退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exit")
	public Object exit(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long userId = SingletonLoginUtils.getLoginUserId(request);
			loginOnliceService.deleteLoginOnlineById(userId);
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			if (StringUtils.isNotEmpty(sid)) {
				memCache.remove(sid);
			}
			memCache.remove(MemConstans.USEREXPAND_INFO + userId);
			memCache.remove(MemConstans.MSGRECEIVE_UNREAD + userId);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_TYPE);
			WebUtils.deleteCookie(request, response, "usercookieuserimg");
			WebUtils.deleteCookie(request, response, "e.subject");
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return "redirect:/index";
	}

	/**
	 * 查询登陆用户id
	 * 
	 * @return
	 */
	@RequestMapping("/user/loginuser")
	@ResponseBody
	public Object loginuser(HttpServletRequest request) {
		try {
			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			if (ObjectUtils.isNotNull(userJsonObject)) {
				JsonElement jsonElement = userJsonObject.get("lastSignTime");
				if(ObjectUtils.isNotNull(jsonElement)){
					String dateStr =jsonElement.getAsString();
					Calendar c1 = new GregorianCalendar();
					Calendar c2 = new GregorianCalendar();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date lastSignDate = sdf.parse(dateStr);
					Date nowDate = new Date();
					c1.setTime(lastSignDate);
					int lastYear = c1.get(Calendar.YEAR);
					int lastMonth = c1.get(Calendar.MONTH);
					c2.setTime(nowDate);
					int nowYear = c2.get(Calendar.YEAR);
					int nowMonth = c2.get(Calendar.MONTH);
					if((lastYear==nowYear)&&(lastMonth!=nowMonth)){
						UserExpand user = new UserExpand();
						user.setSignDate("");
						user.setContSignin(userJsonObject.get("contSignin").getAsInt());
						user.setLastSignTime(nowDate);
						user.setCusId(userJsonObject.get("cusId").getAsLong());
						userExpandService.updateUserSign(user);
						userJsonObject.addProperty("signDate", "");					
					}
				}
				userJsonObject.addProperty("password", "");
				userJsonObject.addProperty("customerkey", "");
				this.setJson(true, null, userJsonObject);
			} else {
				this.setJson(false, null, null);
			}
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return json;
	}

	/**
	 * 跳转到提示成功页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/front/success")
	public String gosuccess(Model model, HttpServletRequest request) {
		model.addAttribute(OrderConstans.RESMSG, request.getParameter(OrderConstans.RESMSG));
		return "/common/msg_success";
	}

	/**
	 * 跳转个人中心
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/uc/home")
	public String ucenter(HttpServletRequest request) {
		String returnUrl = "";
		try {
			String userType = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_TYPE);
			if (StringUtils.isNotEmpty(userType) && !userType.equals("")) {
				int type = Integer.parseInt(userType);
				if (type == 1) { // 用户类型为1 老师
					returnUrl = "redirect:/uc/teacher/home";
				} else if (type == 0) { // 用户类型为0 学生
					returnUrl = "redirect:/uc/student/home";
				}
			}
		} catch (Exception e) {
			logger.error("UserController.ucenter--------------error", e);
			return setExceptionRequest(request, e);
		}
		return returnUrl;
	}

	/**
	 * 教师课表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/teacher/myclass")
	public String myclass(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			boolean flag = false;
			String status = request.getParameter("status");

			if (StringUtils.isEmpty(status) || "0".equals(status)) {
				status = "2";
				model.addAttribute("status", 0);
			} else {
				flag = true;
				model.addAttribute("status", status);
			}
			String subjectMajor = request.getParameter("subjectMajor");// 阶段年级科目
			if (StringUtils.isNotEmpty(subjectMajor)) {
				String[] sm = subjectMajor.split(",");
				model.addAttribute("subjectName", sm[0]);// 阶段
				model.addAttribute("gradeName", sm[1]); // 年级
				model.addAttribute("majorName", sm[2]);// 科目
			}

			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			// 获得教师信息
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			// 获得课程数量
			Map<String, Object> map = this.trxorderDetailService.getTrxorderDetailCountById(null, teacher.getId());
			model.addAttribute("detailStatus", map);
			// 获取当前课表信息
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int datesOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			Date startTime = new GregorianCalendar(year, month, 1, 0, 0, 0).getTime();
			Date endTime = new GregorianCalendar(year, month, datesOfMonth, 23, 59, 59).getTime();
			// 当前月份有课的天数（待上课）
			Set<Integer> dates = this.trxorderDetailService.getTrxorderDetailCountByTimeAndId(null, teacher.getId(),
					startTime, endTime, Long.parseLong(status));
			model.addAttribute("dates", dates);
			// 分页
			page.setPageSize(5);
			this.setPage(page);
			model.addAttribute("page", this.getPage());
			// 查询待上课的记录
			if (flag) {
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				// 阶段
				String subjectId = request.getParameter("subjectId");
				model.addAttribute("subjectId", subjectId);
				if (StringUtils.isNotEmpty(subjectId)) {
					trxorderDetail.setSubjectId(Long.parseLong(subjectId));
				}
				// 年级
				String gradeId = request.getParameter("gradeId");
				model.addAttribute("gradeId", gradeId);
				if (StringUtils.isNotEmpty(gradeId)) {
					trxorderDetail.setGradeId(Long.parseLong(gradeId));
				}
				// 科目
				String majorId = request.getParameter("majorId");
				model.addAttribute("majorId", majorId);
				if (StringUtils.isNotEmpty(majorId)) {
					trxorderDetail.setMajorId(Long.parseLong(majorId));
				}
				trxorderDetail.setStatus(Long.parseLong(status));
				trxorderDetail.setTeacherId(teacher.getId());
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService
						.getTrxorderDetailByStatusTea(trxorderDetail, this.getPage());
				model.addAttribute("detailList", detailList);
				// 查询阶段列表
				QuerySubject querySubject = new QuerySubject();
				querySubject.setSubjectIds("0");
				List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
				model.addAttribute("subjectList", subjectList);
			}
		} catch (Exception e) {
			logger.error("UserController.myclass--------------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_course;
	}

	/**
	 * 教师个人中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/home")
	public String teacherCenter(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			// 获得教师信息
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			Teacher teac = new Teacher();
			teac.setUserExpand(teacher.getUserExpand()); // 用户拓展信息
			teac.setAddressNum(teacher.getAddressNum()); // 用户地址信息数量
			teac.setAssessNum(teacher.getAssessNum()); // 评价数量
			teac.setStatus(teacher.getStatus());// 状态
			teac.setDegree(teacher.getDegree()); // 学历
			teac.setProfession(teacher.getProfession()); // 专业
			// 教学特点
			if (StringUtils.isEmpty(teacher.getPeculiarity()) || teacher.getPeculiarity().equals("")) {
				teac.setPeculiarity("");
			} else {
				teac.setPeculiarity(teacher.getPeculiarity().replace("\r\n", "<br>&nbsp;&nbsp;")
						.replace("\n", "<br>&nbsp;&nbsp;").replace("\"", "\\" + "\""));
			}
			teac.setTeachingEnounce(teacher.getTeachingEnounce()); // 教学宣言
			// 城市ID
			teac.setCityId(teacher.getCityId());
			teac.setMajorsNum(teacher.getMajorsNum()); // 科目数量
			teac.setSubjectsNum(teacher.getSubjectsNum()); // 教授年级总数
			teac.setCard(teacher.getCard()); // 身份证图片地址
			teac.setEducation(teacher.getEducation()); // 学历图片地址
			teac.setTeaching(teacher.getTeaching()); // 教师证图片
			teac.setSpecialty(teacher.getSpecialty()); // 专业资质图片
			teac.setIsProfessor(teacher.getIsProfessor()); // 韩教授认证
			teac.setCourseNum(teacher.getCourseNum()); // 课程总数
			teac.setSeniority(teacher.getSeniority()); // 教龄
			teac.setCardStatus(teacher.getCardStatus()); // 身份证认证状态
			teac.setEducationStatus(teacher.getEducationStatus()); // 学历认证状态
			teac.setTeachingStatus(teacher.getTeachingStatus()); // 教师证认证状态
			teac.setSpecialtyStatus(teacher.getSpecialtyStatus()); // 专业资质认证状态
			teac.setFinishSchool(teacher.getFinishSchool()); // 毕业院校
			teac.setTeachingLive(teacher.getTeachingLive().replace("\r\n", "<br>&nbsp;&nbsp;")
					.replace("\n", "<br>&nbsp;&nbsp;").replace("\"", "\\" + "\"")); // 工作/学习经历
			String teacherJson = gson.toJson(teac);
			request.setAttribute("teacher", teacherJson);
			// 获得通知消息数量
			QueryMsgReceive msgReceive = msgReceiveService.getUnReadMsgReceiveNumByCusId(userId);
			request.setAttribute("msgReceive", msgReceive);
			// 获取当天最后时间
			Calendar todayEnd = new GregorianCalendar();
			todayEnd.set(Calendar.HOUR_OF_DAY, 23);
			todayEnd.set(Calendar.MINUTE, 59);
			todayEnd.set(Calendar.SECOND, 59);
			// 获取今天课程(待上课)
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			trxorderDetail.setStatus(CourseStatus.CLASS);
			trxorderDetail.setTeacherId(teacher.getId());
			trxorderDetail.setStartTime(new Date());
			trxorderDetail.setEndTime(todayEnd.getTime());
			List<QueryTrxorderDetailCourse> detailList = trxorderDetailService
					.getTrxorderDetailByStatusTea(trxorderDetail, this.getPage());
			model.addAttribute("detailList", detailList);
		} catch (Exception e) {
			logger.error("UserController.teacherCenter--------------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_home;
	}

	/**
	 * 跳转去教师个人中心资料修改
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/material/tosetting")
	public String teacherMaterialToSetting(HttpServletRequest request,
			@RequestParam("materialType") String materialType) {
		try {
			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			// 获得单个教师对象信息
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			// 教师风采--图片
			List<TeacherStyle> imageList = new ArrayList<TeacherStyle>();
			// 教师风采--视频
			List<TeacherStyle> videoList = new ArrayList<TeacherStyle>();
			// 获得教师风采
			TeacherStyle teacherStyle = new TeacherStyle();
			teacherStyle.setTeacherId(teacher.getId());
			List<TeacherStyle> list = teacherStyleService.queryTeacherStyleByCondition(teacherStyle);
			if (ObjectUtils.isNotNull(list) && list.size() > 0) {
				for (TeacherStyle style : list) {
					if (style.getType().equalsIgnoreCase("img")) {
						imageList.add(style);
					} else {
						videoList.add(style);
					}
				}
			}
			request.setAttribute("imageList", imageList);
			request.setAttribute("videoList", videoList);
			request.setAttribute("teacher", teacher);
			// 获得网站配置：保利威视的writetoken
			String type = "polyv";
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
			Map<String, Object> _map = (Map<String, Object>)map.get("polyv");
			request.setAttribute("webSiteMap", _map.get("writetoken"));
			// 全部二级专业(年级)
			List<Subject> twoSubject = subjectService.getSubjectTwoList();
			String[] subjectname = teacher.getSubjects().split(",");
			for (int i = 0; i < subjectname.length; i++) {
				for (Subject subject : twoSubject) {
					if (subjectname[i].equals(subject.getSubjectName())) {
						subject.setCheckSelected("true");
						break;
					}
				}
			}
			request.setAttribute("twoSubject", twoSubject);
			// 全部科目
			List<Major> majors = majorService.queryMajorAllList();
			String[] majorname = teacher.getMajors().split(",");
			for (int i = 0; i < majorname.length; i++) {
				for (Major major : majors) {
					if (majorname[i].equals(major.getName())) {
						major.setCheckSelected("true");
						break;
					}
				}
			}
			request.setAttribute("majors", majors);
			request.setAttribute("materialType", materialType);
		} catch (Exception e) {
			logger.error("UserController.teacherMaterialSetting--------------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_material_setting;
	}

	/**
	 * 教师个人中心基本资料设置修改
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/base/material/setting")
	@ResponseBody
	public Map<String, Object> teacherMaterialSetting(HttpServletRequest request, @ModelAttribute Teacher teacher) {
		try {
			// 验证真实姓名是否为空
			if (StringUtils.isEmpty(teacher.getUserExpand().getRealname())
					|| teacher.getUserExpand().getRealname().equals("")) {
				this.setJson(false, "realnameIsNull", null);
				return json;
			}
			// 验证学历是否为空
			if (StringUtils.isEmpty(teacher.getDegree()) || teacher.getDegree().equals("")
					|| teacher.getDegree().equals("0")) {
				this.setJson(false, "degreeIsNull", null);
				return json;
			}
			// 验证毕业院校
			if (StringUtils.isEmpty(teacher.getFinishSchool()) || teacher.getFinishSchool().equals("")) {
				this.setJson(false, "finishSchoolIsNull", null);
				return json;
			}
			// 验证专业
			if (StringUtils.isEmpty(teacher.getProfession()) || teacher.getProfession().equals("")) {
				this.setJson(false, "professionIsNull", null);
				return json;
			}
			// 验证生日
			if (ObjectUtils.isNull(teacher.getUserExpand().getBirthday())) {
				this.setJson(false, "birthdayIsNull", null);
				return json;
			}
			// 验证教学年级
			if (StringUtils.isEmpty(teacher.getTeacherSubject()) || teacher.getTeacherSubject().equals("")) {
				this.setJson(false, "subjectIsNull", null);
				return json;
			}
			// 验证教学科目
			if (StringUtils.isEmpty(teacher.getTeacherMajor()) || teacher.getTeacherMajor().equals("")) {
				this.setJson(false, "majorIsNull", null);
				return json;
			}
			teacherService.updateTeacherBaseInfo(teacher);
			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			userJsonObject.addProperty("nickname", teacher.getUserExpand().getNickname());
			userJsonObject.addProperty("realname", teacher.getUserExpand().getRealname());
			userJsonObject.addProperty("hideStatus", teacher.getUserExpand().getHideStatus());
			if (teacher.getUserExpand().getHideStatus() == 0){
				UserExpandDto user = userExpandService.getUserExpandByUid(teacher.getUserId());
				userJsonObject.addProperty("avatar", user.getAvatar());
			}
			userJsonObject.addProperty("gender", teacher.getSex());
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("UserController.teacherMaterialSetting----------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师个人中心高级资料设置修改
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/senior/material/setting")
	@ResponseBody
	public Map<String, Object> teacherSeniorMaterialSetting(@ModelAttribute Teacher teacher) {
		try {
			// 验证教龄是否为空
			if (ObjectUtils.isNull(teacher.getSeniority()) || teacher.getSeniority().intValue() < 0) {
				this.setJson(false, "seniorityIsNull", null);
				return json;
			}
			// 验证教学特点是否为空
			if (ObjectUtils.isNull(teacher.getPeculiarity()) || teacher.getPeculiarity().equals("")) {
				this.setJson(false, "peculiarityIsNull", null);
				return json;
			}
			// 教学宣言
			if (ObjectUtils.isNull(teacher.getTeachingEnounce()) || teacher.getTeachingEnounce().equals("")) {
				this.setJson(false, "teachingEnounceIsNull", null);
				return json;
			}
			// 验证工作/学习经历
			if (ObjectUtils.isNull(teacher.getTeachingLive()) || teacher.getTeachingLive().equals("")) {
				this.setJson(false, "teachingLiveIsNull", null);
				return json;
			}
			// 教学成功经历
			if (ObjectUtils.isNull(teacher.getTeachingSuccess()) || teacher.getTeachingSuccess().equals("")) {
				this.setJson(false, "teachingSuccessIsNull", null);
				return json;
			}
			teacherService.updateTeacherSeniorInfo(teacher);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("UserController.teacherSeniorMaterialSetting----------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 跳转去教师个人中心资质认证页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/aptitude/toattestation")
	public String toTeacherAptitudeAttestation(HttpServletRequest request) {
		try {
			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			// 获得单个教师对象信息
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			request.setAttribute("teacher", teacher);
		} catch (Exception e) {
			logger.error("UserController.teacherMaterialSetting--------------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_aptitude_attestation;
	}

	/**
	 * 教师个人中心资质认证
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/aptitude/attestation")
	@ResponseBody
	public Map<String, Object> teacherAptitudeAttestation(HttpServletRequest request, @ModelAttribute Teacher teacher) {
		try {
			// 验证身份证图片是否为空
			if (StringUtils.isEmpty(teacher.getCard()) || teacher.getCard().equals("")) {
				this.setJson(false, "cardIsNull", null);
				return json;
			}
			String type = request.getParameter("type");
			String msg = teacherService.updateTeacherAttestationInfo(teacher, type);
			this.setJson(true, "success", msg);
		} catch (Exception e) {
			logger.error("UserController.teacherMaterialSetting--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师申请韩教授认证
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/aptitude/attestation/apply")
	@ResponseBody
	public Map<String, Object> teacherAttestationApply(@ModelAttribute Teacher teacher) {
		try {
			if (ObjectUtils.isNotNull(teacher.getId()) && teacher.getId().intValue() != 0) {
				teacher.setIsProfessor(3); // 申请韩教授认证
				teacherService.updateTeacherStatus(teacher);
				this.setJson(true, "success", null);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("UserController.teacherAttestationApply--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师个人中心风采图片上传
	 * 
	 * @param teacherStyle
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/style/picupload")
	@ResponseBody
	public Map<String, Object> teacherStylePicUpload(@ModelAttribute TeacherStyle teacherStyle) {
		try {
			if (StringUtils.isNotEmpty(teacherStyle.getImageUrl()) && !teacherStyle.getImageUrl().equals("")) {
				teacherStyle.setName("图片展示");
				teacherStyle.setCreateTime(new Date());
				teacherStyle.setStatus(0);
				teacherStyle.setType("img");
				teacherStyle = teacherStyleService.addTeacherStyle(teacherStyle);
				this.setJson(true, "success", teacherStyle);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("UserController.teacherStylePicUpload--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师个人中心风采图片删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/style/picdel")
	@ResponseBody
	public Map<String, Object> teacherStylePicDel(@RequestParam("ids") String ids) {
		try {
			if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
				teacherStyleService.delTeacherStyle(ids);
				this.setJson(true, "success", null);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("UserController.teacherStylePicDel--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师个人中心视频上传
	 * 
	 * @param teacherStyle
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/style/videoupload")
	@ResponseBody
	public Map<String, Object> teacherStyleVideoUpload(@ModelAttribute TeacherStyle teacherStyle) {
		try {
			if (StringUtils.isNotEmpty(teacherStyle.getImageUrl()) && !teacherStyle.getImageUrl().equals("")) {
				teacherStyle.setCreateTime(new Date());
				teacherStyle.setStatus(0);
				teacherStyle.setType("video");
				teacherStyle = teacherStyleService.addTeacherStyle(teacherStyle);
				this.setJson(true, "success", teacherStyle);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("UserController.teacherStyleVideoUpload--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师个人中心风采图片删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/style/videodel")
	@ResponseBody
	public Map<String, Object> teacherStyleVideoDel(@RequestParam("ids") String ids) {
		try {
			if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
				teacherStyleService.delTeacherStyle(ids);
				this.setJson(true, "success", null);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("UserController.teacherStyleVideoDel--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 学生个人中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/student/home")
	public String studentCenter(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			boolean flag = false;
			String status = request.getParameter("status");
			String subjectMajor = request.getParameter("subjectMajor");// 阶段年级科目
			if (StringUtils.isNotEmpty(subjectMajor)) {
				String[] sm = subjectMajor.split(",");
				model.addAttribute("subjectName", sm[0]);// 阶段
				model.addAttribute("gradeName", sm[1]); // 年级
				model.addAttribute("majorName", sm[2]);// 科目
			}

			if (StringUtils.isEmpty(status) || "0".equals(status)) {
				status = "2";
				model.addAttribute("status", 0);
			} else {
				flag = true;
				model.addAttribute("status", status);
			}
			Long userId = getLoginUserId(request);
			// 获取课程数量
			Map<String, Object> map = this.trxorderDetailService.getTrxorderDetailCountById(userId, null);
			model.addAttribute("detailStatus", map);
			// 获取当前课表信息
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int datesOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			Date startTime = new GregorianCalendar(year, month, 1, 0, 0, 0).getTime();
			Date endTime = new GregorianCalendar(year, month, datesOfMonth, 23, 59, 59).getTime();
			// 当前月份有课的天数（待上课）
			Set<Integer> dates = this.trxorderDetailService.getTrxorderDetailCountByTimeAndId(userId, null, startTime,
					endTime, Long.parseLong(status));
			model.addAttribute("dates", dates);
			// 分页
			page.setPageSize(5);
			this.setPage(page);
			model.addAttribute("page", this.getPage());
			// 查询待上课的记录
			if (flag) {
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				// 阶段
				String subjectId = request.getParameter("subjectId");
				model.addAttribute("subjectId", subjectId);
				if (StringUtils.isNotEmpty(subjectId)) {
					trxorderDetail.setSubjectId(Long.parseLong(subjectId));
				}
				// 年级
				String gradeId = request.getParameter("gradeId");
				model.addAttribute("gradeId", gradeId);
				if (StringUtils.isNotEmpty(gradeId)) {
					trxorderDetail.setGradeId(Long.parseLong(gradeId));
				}
				// 科目
				String majorId = request.getParameter("majorId");
				model.addAttribute("majorId", majorId);
				if (StringUtils.isNotEmpty(majorId)) {
					trxorderDetail.setMajorId(Long.parseLong(majorId));
				}
				trxorderDetail.setStatus(Long.parseLong(status));
				trxorderDetail.setUserId(userId);
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService
						.getTrxorderDetailByStatusStu(trxorderDetail, this.getPage());
				model.addAttribute("detailList", detailList);
				// 查询阶段列表
				QuerySubject querySubject = new QuerySubject();
				querySubject.setSubjectIds("0");
				List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
				model.addAttribute("subjectList", subjectList);
			}
		} catch (Exception e) {
			logger.error("UserController.studentCenter--------------error", e);
			return setExceptionRequest(request, e);
		}
		return student_home;
	}

	/**
	 * 个人账户信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/student/acc")
	public ModelAndView perssonAccout(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
		modelAndView.setViewName(student_accout_list);
		try {
			this.setPage(page);
			queryUserAccounthistory.setUserId(getLoginUserId(request));
			UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
			List<UserAccounthistory> accList = userAccounthistoryService
					.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			modelAndView.addObject("userAccount", userAccount);
			modelAndView.addObject("accList", accList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 教师个人账户信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/teacher/acc")
	public ModelAndView teacherAccoutInfo(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
		modelAndView.setViewName(teacher_accout_list);
		try {
			this.setPage(page);
			queryUserAccounthistory.setUserId(getLoginUserId(request));
			UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
			List<UserAccounthistory> accList = userAccounthistoryService
					.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			modelAndView.addObject("userAccount", userAccount);
			modelAndView.addObject("accList", accList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping("/uc/user/uppwd")
	public String toPwd(HttpServletRequest request) {
		return to_update_pwd;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/uc/user/updatepwd")
	@ResponseBody
	public Object updatepwd(HttpServletRequest request, @RequestParam("newpwd") String newpwd,
			@RequestParam("oldpwd") String oldpwd) {
		try {
			User user = userService.getUserById(getLoginUserId(request));
			if (checkIsRight(user.getPassword(), oldpwd, user.getCustomerkey())) {
				user.setPassword(newpwd);
				userService.updatePwdById(user, null);
				this.setJson(true, "修改成功", "");
			} else {
				this.setJson(false, "密码不正确", null);
			}
			return json;
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			this.setJson(false, "系统异常，请稍后操作", null);
			return json;
		}
	}

	/**
	 * 基本信息
	 */
	@RequestMapping("/uc/user/uinfo")
	public String userInfo(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			return setExceptionRequest(request, e);
		}
		return uinfo;
	}

	/**
	 * 用户修改基本信息
	 */
	@RequestMapping("/uc/user/updateInfo")
	@ResponseBody
	public Object userUpdateInfo(HttpServletRequest request, @ModelAttribute("queryUser") UserExpandDto queryUser) {
		try {
			// 修改用户信息
			queryUser.setId(getLoginUserId(request));
			String falg = userService.updateQueryUser(queryUser);
			if ("success".equals(falg)) {
				JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
				userJsonObject.addProperty("nickname", queryUser.getNickname());
				userJsonObject.addProperty("realname", queryUser.getRealname());
				userJsonObject.addProperty("gender", queryUser.getGender());
				String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
				memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
			}

			this.setJson(true, "操作成功", falg);
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			this.setJson(false, "", "");
		}
		return json;
	}

	/**
	 * 修改头像
	 */
	@RequestMapping("/uc/user/avatar")
	public String toUserUpdateAvatar(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.userUpdateAvatar", e);
			return setExceptionRequest(request, e);
		}
		return avatar;
	}

	/**
	 * 修改头像
	 */
	@RequestMapping("/uc/user/updateavatar")
	@ResponseBody
	public Object userUpdateAvatar(HttpServletRequest request, @RequestParam("userId") Long userId,
			@RequestParam("avatar") String avatar) {
		try {

			UserExpand userExpand = userExpandService.getUserExpandByUserId(getLoginUserId(request));
			userExpand.setAvatar(avatar);
			userExpand.setCusId(userId);
			userExpandService.updateUserExpand(userExpand);

			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			userJsonObject.addProperty("avatar", avatar);
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);

			this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("UserController.userUpdateAvatar", e);
			this.setJson(false, "", "");
		}
		return json;
	}

	/**
	 * 绑定手机
	 */
	@RequestMapping("/uc/user/jumpmobile")
	public String tojumpmobile(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.tojumpmobile", e);
			return setExceptionRequest(request, e);
		}
		return student_jumpmobile;
	}

	/**
	 * 检查是否是本人操作（验证码）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/ajax/checkMySelf")
	@ResponseBody
	public Map<String, Object> checkMySelf(HttpServletRequest request) {
		try {
			String checkCode = request.getParameter("checkCode");
			if (StringUtils.isNotEmpty(checkCode)) {
				UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
				checkCode = MD5.getMD5(checkCode);
				String memverification = (String) memCache
						.get(MemConstans.VERIFICATION_CODE + userExpandDto.getMobile());
				if (checkCode.equals(memverification)) {
					// 修改新密码时检查是否通过旧手机验证
					memCache.set(MemConstans.VERIFICATION_CODE + userExpandDto.getMobile() + "check", 1,
							MemConstans.VERIFICATION_CODE_TIME);
					this.setJson(true, "", null);
				} else {
					// 修改新密码时检查是否通过旧手机验证
					memCache.set(MemConstans.VERIFICATION_CODE + userExpandDto.getMobile() + "check", 0,
							MemConstans.VERIFICATION_CODE_TIME);
					this.setJson(false, "验证码错误", null);
				}
			} else {
				this.setJson(false, "请输入验证码", null);
			}
		} catch (Exception e) {
			logger.error("UserController.checkMySelf----error", e);
			this.setJson(false, "系统繁忙，请稍后再试", null);
		}
		return json;
	}

	/**
	 * 用户绑定新手机
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/ajax/student/jumpmobile")
	@ResponseBody
	public Map<String, Object> studentjumpmobile(HttpServletRequest request) {
		try {
			String checkCode = request.getParameter("checkCode");
			String mobile = request.getParameter("mobile");
			if (StringUtils.isNotEmpty(checkCode)) {
				UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
				String isCheck = memCache.get(MemConstans.VERIFICATION_CODE + userExpandDto.getMobile() + "check") + "";
				// 未通过本人验证
				if (StringUtils.isEmpty(isCheck) || isCheck.equals("0")) {
					this.setJson(false, "checkNo", null);
				} else {
					checkCode = MD5.getMD5(checkCode);
					String memverification = (String) memCache.get(MemConstans.VERIFICATION_CODE + mobile);
					if (checkCode.equals(memverification)) {
						User user = userService.getUserById(getLoginUserId(request));
						user.setMobile(mobile);
						user.setMobileIsavalible(0);
						userService.jumpUser(user);

						// 删除本人操作验证缓存
						memCache.remove(MemConstans.VERIFICATION_CODE + userExpandDto.getMobile() + "check");
						// 删除缓存 重新获取用户信息
						memCache.remove(MemConstans.USEREXPAND_INFO + user.getId());
						this.setJson(true, "绑定成功", null);
					} else {
						this.setJson(false, "验证码错误", null);
					}
				}
			} else {
				this.setJson(false, "请输入验证码", null);
			}
		} catch (Exception e) {
			logger.error("UserController.studentjumpmobile--error", e);
			this.setJson(false, "系统繁忙,请稍后重试", null);
		}
		return json;
	}

	/**
	 * 判断是否登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/islogin")
	@ResponseBody
	public Map<String, Object> isuserLogin(HttpServletRequest request) {
		if (this.isLogin(request)) {
			setJson(true, null, null);
		}
		return json;
	}

	/**
	 * 获得个人中心个人模板
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/usercover")
	@ResponseBody
	public Map<String, Object> getUserPersonalityImages(HttpServletRequest request,
			@ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页
			this.setPage(page);
			this.getPage().setPageSize(12);
			WebsiteImages websiteImages = new WebsiteImages();
			websiteImages.setKeyWord(ImagesType.userPersonalityImages.toString());// 个人中心keyword
			// 方法公用
			List<WebsiteImages> websiteImagesList = websiteImagesService.getImgPageList(websiteImages, page);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("websiteImagesList", websiteImagesList);
			map.put("page", this.getPage());
			this.setJson(true, "success", map);
		} catch (Exception e) {
			logger.error("UserController.getUserPersonalityImages", e);
			this.setJson(false, "Images is error", null);
		}
		return json;
	}

	/**
	 * 更新个人中心用户封面
	 * 
	 * @param request
	 * @param bannerUrl
	 * @return
	 */
	@RequestMapping("/uc/updateusercover")
	@ResponseBody
	public Map<String, Object> updateUserCover(HttpServletRequest request,
			@RequestParam("bannerUrl") String bannerUrl) {
		try {
			if (StringUtils.isNotEmpty(bannerUrl)) {// 更新
				userExpandService.updateUserExpandBannerUrl(getLoginUserId(request), bannerUrl);
				this.setJson(true, " 更新封面成功", bannerUrl);
			} else {
				this.setJson(false, " request parameter is null", null);
			}
		} catch (Exception e) {
			logger.error("", e);
			this.setJson(false, "updated is error", null);
		}
		return json;
	}

	/**
	 * 删除站内信收件箱
	 *
	 * @param msgReceive
	 * @return
	 */
	@RequestMapping(value = "/letter/delLetterInbox")
	@ResponseBody
	public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			msgReceive.setReceivingCusId(getLoginUserId(request));// set 用户id
			Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
			if (num.intValue() == 1) {
				map.put("message", SnsConstants.SUCCESS);// 成功
			} else {
				map.put("message", SnsConstants.FALSE);// 失败
			}
		} catch (Exception e) {
			logger.error("LetterAction.delLetterInbox", e);
			setExceptionRequest(request, e);
		}
		return map;
	}

	/**
	 * 批量删除站内信收件箱
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/letter/delLetterInbox/batch")
	@ResponseBody
	public Map<String, Object> delLetterInboxBatch(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String ids = request.getParameter("ids");
			if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
				Long num = msgReceiveService.delMsgReceiveByids(ids.substring(0, ids.length() - 1));
				if (num.intValue() == 1) {
					map.put("message", SnsConstants.SUCCESS);// 成功
				} else {
					map.put("message", SnsConstants.FALSE);// 失败
				}
			} else {
				map.put("message", SnsConstants.FALSE);// 失败
			}
		} catch (Exception e) {
			logger.error("LetterAction.delLetterInbox", e);
			setExceptionRequest(request, e);
		}
		return map;
	}

	/**
	 * 查询站内信收件箱（无社区）
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/uc/letter")
	public ModelAndView queryUserLetter(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute MsgReceive msgReceive) {
		ModelAndView modelAndView = new ModelAndView(queryUserLetter);
		try {
			this.setPage(page);
			page.setPageSize(6);// 分页页数为6
			msgReceive.setReceivingCusId(getLoginUserId(request));
			// 更新所有收件箱为已读
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive,
					this.getPage());// 查询站内信收件箱
			modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
			modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
			modelAndView.addObject("msgReceive", msgReceive);
		} catch (Exception e) {
			logger.error("LetterAction.queryLetterByInbox", e);
			setExceptionRequest(request, e);
			ModelAndView modelAndViewError = new ModelAndView(ERROR);
			return modelAndViewError;// 返回404页面
		}
		return modelAndView;
	}

	/**
	 * 查询该用户有多少未读消息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/letter/queryUnReadLetter")
	@ResponseBody
	public Map<String, Object> queryUnReadLetter(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 未登录不可操作
			if (getLoginUserId(request).longValue() == 0) {
				return map;
			}
			Map<String, String> queryletter = msgReceiveService
					.queryUnReadMsgReceiveNumByCusId(getLoginUserId(request));// 查询该用户有多少未读消息
			map.put("entity", queryletter);// 把值放入map中返回json
		} catch (Exception e) {
			logger.error("LetterAction.queryUnReadLetter", e);
			setExceptionRequest(request, e);
		}
		return map;
	}

	@RequestMapping("/front/pro")
	@ResponseBody
	public Object frontpro(@RequestParam String type, @RequestParam(required = false) String key,
			@RequestParam(required = false) String value) {
		try {
			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(type);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				websitemap.put(key, value);
				// 将map转化json串
				JsonObject jsonObject = jsonParser.parse(gson.toJson(websitemap)).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
			}
			this.setJson(true, null, websitemap);
		} catch (Exception e) {

		}
		return json;
	}

	public Map<String, Object> getLoginAndRegKeyword() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> keywordmap = websiteProfileService
					.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
			if (ObjectUtils.isNotNull(keywordmap)) {
				JsonObject jsonObject = jsonParser
						.parse(gson.toJson(keywordmap.get(WebSiteProfileType.keyword.toString()))).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					map.put("verifyLogin", jsonObject.get("verifyLogin").getAsString());
					map.put("verifyRegister", jsonObject.get("verifyRegister").getAsString());
				}
			}
		} catch (Exception e) {
			logger.error("getLoginAndRegKeyword", e);
		}
		return map;
	}

	/**
	 * 发送手机校验码--找回密码
	 * 
	 * @author wangzhuang
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/mobileCodeForgetCheck")
	@ResponseBody
	public Map<String, Object> mobileCodeForgetCheck(HttpServletRequest request) {
		try {

			String mobile = request.getParameter("mobile");// 手机号
			User user = this.userService.getUserByMobileNumber(mobile);
			// 验证手机唯一
			if (ObjectUtils.isNull(user)) {
				this.setJson(false, "mobileIsExist", null);
				return json;
			}
			// 登录数量
			int regiterNum = memCache.get(MemConstans.FIND_PHONE + mobile) == null ? 0 // regiterNum代表的是发送校验码的次数
					: Integer.parseInt(memCache.get(MemConstans.FIND_PHONE + mobile).toString());
			if (regiterNum < 3) {
				if (ObjectUtils.isNotNull(mobile)) {
					Random random = new Random();
					String verificationCode = "";
					for (int i = 0; i < 4; i++) {
						int num = random.nextInt(10);
						verificationCode += num + "";
					} // 生成验证码
					String content = "您的验证码为" + verificationCode + ",此码仅用于密码找回";
					String serialNumber = DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS") + "000";
					System.out.println(content);
					// 等待发送接口
					userMobileMsgService.sendEx(SendMsgConstans.SEND_FORGET_PASSWORD,mobile, verificationCode,"3", null,null); 
					
					verificationCode = MD5.getMD5(verificationCode);
					memCache.set(MemConstans.VERIFICATION_CODE + mobile, verificationCode,
							MemConstans.VERIFICATION_CODE_TIME);
					regiterNum = regiterNum + 1;
					memCache.set(MemConstans.FIND_PHONE + mobile, regiterNum, MemConstans.REGISTER_PHONE_TIME);
					this.setJson(true, "success", content);
				} else {
					this.setJson(false, "请输入手机号码", null);
				}
			} else {
				this.setJson(false, "checkCodeMoreThanThree", null);
			}
		} catch (Exception e) {
			logger.error("mobileNumCheck", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}

	/**
	 * 修改密码
	 * 
	 * @param password
	 *            第一次输入密码
	 * @param repassword
	 *            第二次输入密码
	 * @param mobile
	 *            手机号
	 * @return
	 */
	@RequestMapping("/user/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(@RequestParam("password") String password,
			@RequestParam("repassword") String repassword, @RequestParam("mobile") String mobile,
			@RequestParam("checkCode") String checkCode) {
		try {
			// 验证手机号是否为空
			if (StringUtils.isEmpty(mobile)) {
				this.setJson(false, "mobileEmpty", null);
			}
			// 校验码 验证
			checkCode = MD5.getMD5(checkCode);
			String mem = (String) memCache.get(MemConstans.VERIFICATION_CODE + mobile);
			if (ObjectUtils.isNull(checkCode) || !checkCode.equals(mem)) {
				this.setJson(false, "checkCodeError", null);
			}
			// 验证密码和确认密码是否为空
			if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repassword)) {
				this.setJson(false, "passwordEmpty", null);
			}
			// 验证两次输入密码是否相等
			if (!password.equals(repassword)) {
				this.setJson(false, "passwordError", null);
			}
			User user = userService.getUserByMobileNumber(mobile);
			user.setPassword(repassword);
			userService.updatePwdById(user, null);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("UserController.updatapwd", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 第三方登录跳转绑定手机页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/jump_user")
	public ModelAndView toJumpUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			String userId = request.getParameter("userId");
			// 检查用户是否绑定手机
			User user = userService.getUserById(Long.parseLong(userId));
			if (user.getMobileIsavalible() != 1) {
				// 用户登录
				if (ObjectUtils.isNotNull(user)) {
					userService.setLoginStatus(user, "true", request, response);
				}
				model.setViewName("redirect:/");
			} else {
				model.addObject("userId", userId);
				model.setViewName(jump_user);
			}
		} catch (Exception e) {
			logger.error("UserController.updatapwd", e);
		}
		return model;
	}

	/**
	 * 第三方登录绑定手机号码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/jumpUser")
	@ResponseBody
	public Map<String, Object> jumpUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("userId");
			String mobile = request.getParameter("mobile");
			String checkCode = request.getParameter("checkCode");
			String role = request.getParameter("role");

			// 验证手机是否为空
			if (StringUtils.isEmpty(mobile)) {
				this.setJson(false, "mobileIsNull", null);
			}
			// 在手机不为空的情况下，验证手机格式是否正确
			else if (StringUtils.isNotEmpty(mobile)
					&& !mobile.matches("^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$")) {
				this.setJson(false, "mobileFormatError", null);
			}
			// 查询手机号码是否存在
			else if (ObjectUtils.isNotNull(userService.getUserByMobileNumber(mobile))) {
				this.setJson(false, "mobileIsHave", null);
			}
			// 校验码是否为空
			else if (StringUtils.isEmpty(checkCode)) {
				this.setJson(false, "checkCodeIsNull", null);
			}
			// 检查用户是否存在
			else if (StringUtils.isEmpty(userId)
					|| ObjectUtils.isNull(userService.getUserById(Long.parseLong(userId)))) {
				this.setJson(false, "jumpError", null);
			} else {
				checkCode = MD5.getMD5(checkCode);
				String memverification = (String) memCache.get(MemConstans.VERIFICATION_CODE + mobile);
				// 比较校验码是否正确
				if (memverification.equals(checkCode)) {
					User user = new User();
					user.setId(Long.parseLong(userId));
					user.setMobile(mobile);
					user.setMobileIsavalible(0);
					user.setUserType(Integer.parseInt(role));

					userService.jumpUser(user);
					// 创建教师拓展表
					if (user.getUserType() == 1) {
						Teacher teacher = new Teacher();
						teacher.setUserId(user.getId());// 关联userId
						teacherService.addTeacher(teacher);
						TeacherProfile teacherProfile = new TeacherProfile();
						teacherProfile.setTeacherId(teacher.getId());// 关联教师id
						teacherProfile.setStar(0);// 教师星级
						teacherProfile.setAssessNum(0L);// 教师评价数
						teacherProfile.setStudentNum(0L);// 学生数量
						teacherProfile.setBrowseNum(0L);
						teacherProfile.setCollectNum(0L);
						teacherProfile.setCardStatus(0);
						teacherProfile.setSpecialtyStatus(0);
						teacherProfile.setTeachingStatus(0);
						teacherProfile.setEducationStatus(0);
						teacherProfileService.addTeacherProfile(teacherProfile);
					}

					// 执行登录操作
					User user1 = userService.getUserById(user.getId());
					if (ObjectUtils.isNotNull(user1)) {
						userService.setLoginStatus(user1, "true", request, response);
						this.setJson(true, "jumpOk", null);
					} else {
						this.setJson(false, "jumpError", null);
					}
				} else {
					this.setJson(false, "checkCodeError", null);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.jumpUser---error", e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}

	/**
	 * 获取收藏教师列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/collectTeacherList")
	public String getTeacherList(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			// 分页
			page.setPageSize(5);
			this.setPage(page);
			model.addAttribute("page", this.getPage());
			// 教师收藏列表
			List<TeacherDto> list = this.teacherFavoritesService.getTeacherFavoritesByUserId(getLoginUserId(request),this.getPage());
			model.addAttribute("teacherList", list);
		} catch (Exception e) {
			logger.error("UserController.getTeacherList", e);
		}
		return collect_teacher;
	}

	/**
	 * 删除教师收藏
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/uc/deleteTeacher")
	public String deleteTeacher(@RequestParam("id") Long id, @RequestParam("teacherId") Long teacherId) {
		try {
			this.teacherFavoritesService.deleteTeacherFavorites(id, teacherId);
		} catch (Exception e) {
			logger.error("UserController.deleteTeacher", e);
		}
		return "redirect:/uc/collectTeacherList";
	}

	/**
	 * 根据条件获取我的优惠券
	 * 
	 * @param model
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/student/coupon")
	public String getMyCoupon(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			// 分页
			page.setPageSize(5);
			this.setPage(page);
			model.addAttribute("page", this.getPage());
			// 优惠券类型(1折扣券2定额券)
			int type = StringUtils.isEmpty(request.getParameter("type")) ? 0
					: Integer.parseInt(request.getParameter("type"));
			model.addAttribute("type", type);
			// 优惠券使用状态(1未使用2已使用3过期)
			int status = StringUtils.isEmpty(request.getParameter("status")) ? 1
					: Integer.parseInt(request.getParameter("status"));
			model.addAttribute("status", status);
			// 优惠券不同状态的数量
			Map<String, Object> statusMap = this.couponCodeService.getCouponCodeStatus(getLoginUserId(request));
			model.addAttribute("statusMap", statusMap);
			// 优惠券列表
			List<CouponCodeDTO> couponCodeList = this.couponCodeService.getMyCouponList(getLoginUserId(request), status,
					type, this.getPage());
			model.addAttribute("couponCodeList", couponCodeList);
		} catch (Exception e) {
			logger.error("UserController.getCouponList", e);
		}
		return student_coupon;
	}

	/**
	 * 我的评价
	 * 
	 * @param
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/evaluation")
	public String evaluation(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			Long userId = getLoginUserId(request);
			User user = this.userService.getUserById(userId);
			request.setAttribute("userType", user.getUserType());
			// 分页
			page.setPageSize(10);
			this.setPage(page);
			request.setAttribute("page", this.getPage());
			// 状态 1对学生的评价2对老师评价
			String status = request.getParameter("status");
			if (StringUtils.isEmpty(status)) {
				status = "0";
			}
			request.setAttribute("status", status);
			// 类型1好评2中评3差评
			String type = request.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				type = "0";
			}
			request.setAttribute("type", type);
			// 区别用户
			if (user.getUserType() == 0) {// 学生
				status = "0".equals(status) ? "1" : "2";
				// 查询评价数量
				Map<String, Object> mapType = this.assessService.getCountByType(userId, 0L, Long.parseLong(status));
				request.setAttribute("mapType", mapType);
				// 查询评价列表
				List<AssessDto> list = this.assessService.getAssessListByTypeStatus(userId, 0L, Long.parseLong(type),
						Long.parseLong(status), this.getPage());
				request.setAttribute("assessList", list);
			} else if (user.getUserType() == 1) {// 教师
				Teacher teacher = this.teacherService.getTeacherByUserId(userId);
				status = "0".equals(status) ? "2" : "1";
				// 查询评价数量
				Map<String, Object> mapType = this.assessService.getCountByType(0L, teacher.getId(),
						Long.parseLong(status));
				request.setAttribute("mapType", mapType);
				// 查询评价列表
				List<AssessDto> list = this.assessService.getAssessListByTypeStatus(0L, teacher.getId(),
						Long.parseLong(type), Long.parseLong(status), this.getPage());
				request.setAttribute("assessList", list);
			}
		} catch (Exception e) {
			logger.error("UserController.evaluation", e);
		}
		return evaluation;
	}

	/**
	 * 校验码生成
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCheckCode")
	@ResponseBody
	public Map<String, Object> getCheckCode(@RequestParam("mobile") String mobile, HttpServletRequest request) {
		try {
			// 重新绑定手机（不需要验证码） 验证码类型(空 为注册时 oldMobile为用户个人中心旧手机
			// newMobile为用户个人中心新手机)
			String codeType = request.getParameter("codeType");

			// codeType=空 为注册时需要
			if (StringUtils.isEmpty(codeType) || codeType.equals("newMobile")) {
				// 验证手机号码是否存在并唯一
				User user = userService.getUserByMobileNumber(mobile);
				// 验证手机唯一
				if (ObjectUtils.isNotNull(user)) {
					this.setJson(false, "regMobileExist", null);
					return json;
				}
				if (StringUtils.isEmpty(codeType)) {
					// 验证码验证
					String randomCode = request.getParameter("randomCode");
					System.out.println(request.getSession().getAttribute(RandomCodeController.RAND_CODE));
					if (StringUtils.isNotEmpty(randomCode) && ObjectUtils.isNotNull(randomCode)) {
						if (request.getSession().getAttribute(RandomCodeController.RAND_CODE) == null
								|| !randomCode.equalsIgnoreCase(
										request.getSession().getAttribute(RandomCodeController.RAND_CODE).toString())) {
							this.setJson(false, "randomError", null);
							return json;
						}
					}
				}
			}

			// 注册数量
			int regiterNum = memCache.get(MemConstans.CHECK_CODE + mobile) == null ? 0 // regiterNum代表的是发送校验码的次数
					: Integer.parseInt(memCache.get(MemConstans.CHECK_CODE + mobile).toString());
			if (regiterNum < 10) {
				if (ObjectUtils.isNotNull(mobile)) {
					Random random = new Random();
					String verificationCode = "";
					for (int i = 0; i < 4; i++) {
						int num = random.nextInt(10);
						verificationCode += num + "";
					}
					String content = "";

					// 注册时发送的短信信息
					if (StringUtils.isEmpty(codeType)) {
						content = "您的校验码为" + verificationCode + ",此码仅用于用户注册";
					} else {
						content = "您的校验码为" + verificationCode + "【小雨在线】";
					}

					System.out.println(content);

					// 短信发送
					 userMobileMsgService.sendEx(SendMsgConstans.SEND_REGISTER, mobile,verificationCode,"5",null,null);

					verificationCode = MD5.getMD5(verificationCode);
					memCache.set(MemConstans.VERIFICATION_CODE + mobile, verificationCode,
							MemConstans.VERIFICATION_CODE_TIME);
					regiterNum = regiterNum + 1;
					memCache.set(MemConstans.CHECK_CODE + mobile, regiterNum, MemConstans.REGISTER_PHONE_TIME);
					this.setJson(true, "success", content);
				} else {
					this.setJson(false, "请输入手机号码", null);
				}
			} else {
				this.setJson(false, "获取校验码次数已达上限，请24小时后再试", null);
			}
		} catch (Exception e) {
			logger.error("mobileNumCheck", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}

	/**
	 * 根据教师id收藏教师
	 * 
	 * @param request
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("/user/addTeacherFavorites")
	@ResponseBody
	public Map<String, Object> addTeacherFavorites(HttpServletRequest request,
			@RequestParam("teacherId") Long teacherId) {
		try {
			User user = this.userService.getUserById(getLoginUserId(request));
			if (user.getUserType() == 1) {
				this.setJson(false, "youIsTeacher", null);
			} else {
				String message = this.teacherFavoritesService.addTeacherFavorites(getLoginUserId(request), teacherId);
				if (WebContants.OWNED.equals(message)) {// 收藏过
					this.setJson(false, "owned", null);
				} else if (WebContants.SUCCESS.equals(message)) {
					this.setJson(true, "success", null);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.addTeacherFavorites", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 根据教师ID查询我的学生列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/teacher/mystudent/list")
	public String queryStudentListByUserId(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			request.setAttribute("page", this.getPage());
			// 获得当前登录用户ID
			Long userId = getLoginUserId(request);
			List<UserExpand> studentList = userService.queryStudentListByTeacherId(userId, page);
			request.setAttribute("studentList", studentList);
			List<String> list = new ArrayList<String>();
			if(studentList!=null&&studentList.size()>0){
				for(int i=0;i<studentList.size();i++){
					String[] str1 = studentList.get(i).getAssessTeatostu().split(",");
					String[] str2 = studentList.get(i).getOrderId().split(",");
					for(int x = 0;x<str1.length;x++){
						if(str1[x].indexOf("0")>-1){
							list.add(str2[str1[x].indexOf("1")]);
						}
					}
				}
			}
			request.setAttribute("assessList", list);
		} catch (Exception e) {
			logger.error("UserController.queryStudentListByUserId---------error", e);
			return setExceptionRequest(request, e);
		}
		return my_student_list;
	}

	/**
	 * 获得登录教师推广返现列表
	 * 
	 * @param request
	 * @return
	@RequestMapping("/front/getTeacherCashBack")
	public String getTeacherCashBack(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			Long teacherId = getLoginUserId(request);
			if (ObjectUtils.isNotNull(teacherId)) {
				this.setPage(page);
				this.getPage().setPageSize(12);
				TeacherExtendCashback query = new TeacherExtendCashback();
				query.setTeacherId(teacherId);
				List<TeacherExtendCashback> cashBackList = teacherExtendCashbackService
						.queryTeacherCashBackByCondition(query, page);
				request.setAttribute("cashBackList", cashBackList);

				// 查询总数（totalNum 推广的人数 totalMoney 返现的总金额）
				Map<String, Object> totalCashBack = teacherExtendCashbackService.queryTeacherCashBackToTal(teacherId);
				request.setAttribute("totalCashBack", totalCashBack);
			}
		} catch (Exception e) {
			logger.error("TeacherController.getTeacherCashBack---error", e);
			return setExceptionRequest(request, e);
		}
		return null;
	}

	/**
	 * 学生预约老师时间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/ajax/consult/teacher/time")
	@ResponseBody
	public Map<String, Object> userConsultTeacherTime(HttpServletRequest request) {
		try {
			String datavalue = request.getParameter("datavalue");
			String teacherId = request.getParameter("teacherId");
			if (StringUtils.isNotEmpty(datavalue) && !datavalue.equals("") && StringUtils.isNotEmpty(teacherId)
					&& !teacherId.equals("") && !teacherId.equals("0")) {
				String message = teacherClassHourService.updateStudentConsultClass(datavalue, Long.parseLong(teacherId),
						getLoginUserId(request));
				this.setJson(true, message, null);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("userController.userConsultTeacherTime---error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 签到增加积分
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/userSignIn")
	@ResponseBody
	public Map<String, Object> userSignIn(HttpServletRequest request) {
		try {
			// 是否已签到
			boolean isSign = userIntegralService.addUserIntegral(IntegralKeyword.sign_in.toString(), getLoginUserId(request),0L, 0L, "");
			if (isSign) {
				memCache.remove(MemConstans.USEREXPAND_INFO + getLoginUserId(request));
				Calendar calendar = Calendar.getInstance();
				UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
				String dates = userExpandDto.getSignDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse(sdf.format(new Date()));
				if (ObjectUtils.isNull(userExpandDto.getLastSignTime())) {
					userExpandDto.setLastSignTime(new Date());
				}
				Date date2 = sdf.parse(sdf.format(userExpandDto.getLastSignTime()));
				Long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
				int nowDay = calendar.get(Calendar.DATE);
				if(nowDay<10){
					dates = dates + "0" +calendar.get(Calendar.DATE) + ",";
				}else {
					dates = dates + calendar.get(Calendar.DATE) + ",";
				}
				if (day.intValue() > 1) {//不是连续签到
					userExpandDto.setContSignin(0);
				} 
				UserExpand userExpand = new UserExpand();
				userExpand.setCusId(getLoginUserId(request));
				userExpand.setContSignin(userExpandDto.getContSignin() + 1);
				userExpand.setSignDate(dates);
				userExpand.setLastSignTime(new Date());
				userExpandService.updateUserSign(userExpand);
				userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
				// 修改缓存的积分，连续签到天数，日期字符串
				SingletonLoginUtils.updateWebIntegift(userExpandDto, request);
				this.setJson(true, "success", userExpandDto);
			} else {
				this.setJson(false, "alreadySign", null);
			}
		} catch (Exception e) {
			logger.error("userSignIn", e);
		}
		return json;
	}

	/**
	 * 获取当月签到天数
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/getSignByMonth")
	@ResponseBody
	public Map<String, Object> getSignByMonth(@RequestParam("year") int year, @RequestParam("month") int month,
			HttpServletRequest request) {
		try {
			Calendar calendar = Calendar.getInstance();
			int y = calendar.get(Calendar.YEAR);
			int m = calendar.get(Calendar.MONTH);
			if (y == year && m == month - 1) {
				UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
				this.setJson(true, "success", userExpandDto.getSignDate());
			} else {
				calendar.set(year, month - 1, 1);
				int lastDate = calendar.getActualMaximum(Calendar.DATE);
				Date startTime = new GregorianCalendar(year, month - 1, 1, 0, 0, 0).getTime();
				Date endTime = new GregorianCalendar(year, month - 1, lastDate, 23, 59, 59).getTime();
				List<Integer> signDate = this.userIntegralRecordService.getSignCountByTime(getLoginUserId(request),
						startTime, endTime);
				this.setJson(true, "success", signDate);
			}
		} catch (Exception e) {
			logger.error("UserController.getSignByMonth", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 创建zoom视频用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacher/createzoomuser")
	@ResponseBody
	public Map<String, Object> teacherCreateZoomUser(HttpServletRequest request) {
		try {
			String teacherId = request.getParameter("teacherId");
			if (StringUtils.isNotEmpty(teacherId) && !teacherId.equals("") && !teacherId.equals("")) {
				// 获得返回结果
				Map<String, Object> map = zoomMeetingService.createZoomUser(Long.parseLong(teacherId));
				// 如果返回无错误信息
				if (ObjectUtils.isNull(map.get("error"))) {
					String zoomUserId = (String) map.get("id");
					teacherService.updateZoomMeetingUserId(Long.parseLong(teacherId), zoomUserId);
					this.setJson(true, "success", zoomUserId);
				} else {
					// 返回错误信息
					this.setJson(false, "error", null);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.teacherCreateZoomUser", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
}
