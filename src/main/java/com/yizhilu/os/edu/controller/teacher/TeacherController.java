package com.yizhilu.os.edu.controller.teacher;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.entity.coupon.Coupon;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.teacher.TeacherStyle;
import com.yizhilu.os.edu.entity.user.UserAddress;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.service.coupon.CouponService;
import com.yizhilu.os.edu.service.course.CourseMinusService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.major.SubjectMajorService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;
import com.yizhilu.os.edu.service.teacher.TeacherStyleService;
import com.yizhilu.os.edu.service.user.UserAddressService;
import com.yizhilu.os.edu.service.user.UserAreaService;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * Teacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class TeacherController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	MemCache memCache = MemCache.getInstance();
	private static final int FIRST_DAY = Calendar.MONDAY;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final String teacher_list = getViewPath("/teacher/teacher_list");// 教师列表返回路径
	private static final String teacherinfo = getViewPath("/teacher/teacher_info");// 教师详情
	
	private static final String teachertimes_publish = getViewPath("/ucenter/teacher/teachering-times");// 教师个人中心发布课时
	
	private static final String my_teacher_list = getViewPath("/ucenter/student/my_teacher_list"); // 我的教师列表
	private static final String teacher_ingormation = getViewPath("/teacher/teacher_ingormation"); // 教师个人资料完善


	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private UserAreaService userAreaService;
	@Autowired
	private TeacherProfileService teacherProfileService;
	@Autowired
	private CourseMinusService courseMinusService;
	@Autowired
	private SubjectMajorService subjectMajorService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private TeacherStyleService teacherStyleService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private TrxorderService trxorderService;
	
	// 绑定属性 封装参数
	@InitBinder("teacher")
	public void initTeacher(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("teacher.");
	}
	// 绑定属性 封装参数
	@InitBinder("queryTeacher")
	public void initQueryTeacher(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTeacher.");
	}
	// 绑定属性 封装参数
	@InitBinder("teacherClassHour")
	public void initTeacherClassHour(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("teacherClassHour.");
	}
	
	/**
	 * 搜索教师列表
	 * @param request
	 * @param queryTeacher
	 * @return
	 */
	@RequestMapping("/front/teacher/query/list")
	public String queryTeacherList(HttpServletRequest request, QueryTeacher queryTeacher, @ModelAttribute("page") PageEntity page){
		try {
			this.setPage(page);
			this.getPage().setPageSize(6); // 每页六条数据
			// 获得全部教授阶段
			List<Subject> subjectList = subjectService.getSubjectOneList();
			request.setAttribute("subjectList", subjectList);
			// 存放搜索条件
			String queryStr = queryTeacher.getName();
			request.setAttribute("queryStr", queryStr);
			// 如果阶段不为0
			boolean flag = true; // 判断返回的查询是否返回年级编号的标识
			String querysubjectId = request.getParameter("querysubjectId");
			// 搜索条件，判断是否存在阶段、年级
			if (ObjectUtils.isNotNull(queryTeacher.getName()) && !queryTeacher.getName().equals("")){
				for (Subject subject : subjectList) {
					// 如果搜索条件中存在阶段，设置阶段ID，并替换为空字符串
					if (queryTeacher.getName().indexOf(subject.getSubjectName()) != -1){
						querysubjectId = subject.getSubjectId() + "";
						queryTeacher.setName(queryTeacher.getName().replace(subject.getSubjectName(), ""));
						break;
					}
				}
				List<Subject> gradeList = subjectService.getSubjectTwoList();
				for (Subject subject : gradeList) {
					if (queryTeacher.getName().indexOf(subject.getSubjectName()) != -1) {
						querysubjectId = subject.getParentId() + "";
						queryTeacher.setSubjectId(subject.getSubjectId());
						queryTeacher.setName(queryTeacher.getName().replace(subject.getSubjectName(), ""));
						break;
					}
				}
			}
			if (ObjectUtils.isNotNull(querysubjectId) && StringUtils.isNotEmpty(querysubjectId) && !querysubjectId.equals("0")) {
				List<Subject> gradeList = subjectService.getSubjectListByOne(Long.parseLong(querysubjectId));
				request.setAttribute("gradeList", gradeList);
			}
			// 获得科目列表
			List<Major> majorList = new ArrayList<Major>();
			if (ObjectUtils.isNotNull(queryTeacher.getSubjectId()) && queryTeacher.getSubjectId().intValue() > 0) {
				majorList = majorService.queryMajorListBySubjectId(queryTeacher.getSubjectId());
			} else if (ObjectUtils.isNotNull(querysubjectId) && StringUtils.isNotEmpty(querysubjectId) && !querysubjectId.equals("0")) {
				majorList = majorService.queryMajorListBySubjectId(Long.parseLong(querysubjectId));
				if (ObjectUtils.isNull(queryTeacher.getSubjectId()) || queryTeacher.getSubjectId().intValue() == 0){
					queryTeacher.setSubjectId(Long.parseLong(querysubjectId));
					flag = false;
				}
			} else {
				majorList = majorService.queryMajorAllList();
			}
			if (ObjectUtils.isNotNull(queryTeacher.getName()) && !queryTeacher.getName().equals("")){
				for (Major major : majorList) {
					if (queryTeacher.getName().indexOf(major.getName()) != -1){
						queryTeacher.setMajorId(major.getId());
						queryTeacher.setName(queryTeacher.getName().replace(major.getName(), ""));
						break;
					}
				}
			}
			request.setAttribute("majorList", majorList);
			// 根据市区编号获得区县信息
			Long cityId = getCurrentCityId(request);
			if (cityId == null || cityId == 0L) {
				cityId = Long.parseLong(CommonConstants.USER_DEFAULT_ATER_ID);
			}
			List<UserArea> areaList = userAreaService.queryUserAreaList();
			for (UserArea area : areaList) {
				if (area.getId().intValue() == cityId.intValue()) {
					areaList = area.getAreaList();
					break;
				}
			}
			request.setAttribute("areaList", areaList);
			queryTeacher.setCityId(cityId);
			// 获得推荐教师列表
			Map<String, List<QueryTeacher>> mapTeacherList = teacherService.getTeacherListByHomePage();
			request.setAttribute("mapTeacherList", mapTeacherList);
			// 获得教师列表
			// 设置最高价格和最低价格
			String price = request.getParameter("querylowPrice");
			if (ObjectUtils.isNotNull(price) && StringUtils.isNotEmpty(price) && !price.equals("")) {
				queryTeacher.setMinPrice(new BigDecimal((price.split("_"))[0]));
				queryTeacher.setMaxPrice(new BigDecimal((price.split("_"))[1]));
			}
			// 设置最高教龄和最低教龄
			String seniority = request.getParameter("queryseniority");
			if (ObjectUtils.isNotNull(seniority) && StringUtils.isNotEmpty(seniority) && !seniority.equals("")) {
				queryTeacher.setMinSeniority(Long.parseLong((seniority.split("_"))[0]));
				queryTeacher.setMaxSeniority(Long.parseLong((seniority.split("_"))[1]));
			}
			List<Teacher> teacherList = teacherService.queryTeacherList(queryTeacher, this.getPage());
			if (flag == false) {
				queryTeacher.setSubjectId(0L);
			}
			request.setAttribute("page", this.getPage());
			request.setAttribute("teacherList", teacherList);
			request.setAttribute("querylowPrice", price);
			request.setAttribute("queryseniority", seniority);
			request.setAttribute("querysubjectId", querysubjectId);
			request.setAttribute("queryTeacher", queryTeacher);
		} catch (Exception e) {
			logger.error("TeacherController.queryTeacherList---------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_list;
	}
	
	/**
	 * 根据年级id获取科目列表
	 * @param id 年级id
	 * @return
	 */
	@RequestMapping("/front/major/list")
	@ResponseBody
	public Map<String,Object> queryMajorListBySubjectId(@RequestParam("id")Long id){
		try{
			List<SubjectMajor> list= subjectMajorService.querySubjectMajorBySubjectId(id);
			this.setJson(true, "success", list);
		}catch(Exception e){
			logger.error("TeacherController.queryMajorListBySubjectId---------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
//	/**
//	 *  教师排课、更改排课
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/front/teacher/ajax/add")
//	@ResponseBody
//	public Map<String, Object> addTeacherClassHour(HttpServletRequest request){
//		try {
//			String classHour = request.getParameter("classHour");
//			Long userId = getLoginUserId(request);
//			teacherClassHourService.addTeacherClassHour(classHour, userId);
//		} catch (Exception e) {
//			logger.error("TeacherController.addTeacherClassHour--------------error", e);
//			this.setJson(false, "error", null);
//		}
//		return json;
//	}
	
	/**
	 * 查询教师排课时间段
	 * @param request
	 * @param teacherClassHour
	 * @return
	 */
	@RequestMapping("/front/teacher/ajax/query")
	@ResponseBody
	public Map<String, Object> queryTeacherClassHour(HttpServletRequest request, @ModelAttribute TeacherClassHour teacherClassHour){
		try {
			if (ObjectUtils.isNotNull(teacherClassHour) && teacherClassHour != null && teacherClassHour.getTeacherId() != null && teacherClassHour.getTeacherId().intValue() != 0) {
				if (ObjectUtils.isNull(teacherClassHour.getDateDay()) || teacherClassHour.getDateDay() == null ) {
					teacherClassHour.setDateDay(new Date());
				}
				// 根据传递的查询日期获得周一的日期
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(teacherClassHour.getDateDay());
		        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
		            calendar.add(Calendar.DATE, -1);
		        }
		        // 获得指定日期所在周的日期
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        List<String> dateList = new ArrayList<String>();
		        for (int i = 0; i < 7; i++) {
		        	dateList.add(sdf.format(calendar.getTime()));
		            calendar.add(Calendar.DATE, 1);
		        }
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("dateList", dateList);
				// 查询返回结果
				List<List<TeacherClassHour>> resultList = teacherClassHourService.getTeacherClassHourList(teacherClassHour);
				map.put("resultList", resultList);
				this.setJson(true, "success", map);
			} else {
				// 数据请求异常
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("TeacherController.queryTeacherClassHour--------------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 教师详情
	 * 
	 * @return
	 */
	@RequestMapping("/front/teacher/{teacherId}")
	public String teacher(HttpServletRequest request, @PathVariable long teacherId,
			RedirectAttributes redirectAttributes) {
		try {
			if(teacherId>0){
				// 查询老师
				Teacher teacher = teacherService.getTeacherInfoById(teacherId);
				if(teacher==null||teacher.getStatus()==null||teacher.getStatus().intValue()!=1){
					redirectAttributes.addAttribute("msg","对不起该教师不存在或者已删除");
					return "redirect:/front/success";
				}
				request.setAttribute("teacher", teacher);
	            //获取用户的常用地址
	            UserAddress queryAddress=new UserAddress();
	            queryAddress.setUserId(teacher.getUserId());
	            List<UserAddress> addressList= userAddressService.getUserAddressList(queryAddress);
	            if(ObjectUtils.isNotNull(addressList)&&addressList.size()>0){
	            	 for(UserAddress address:addressList){
	 	            	if(address.getIsFirst()==1){
	 	            		teacher.setLng(address.getLng());
	 	            		teacher.setLat(address.getLat());
	 	            		teacher.setArea(address.getCityStr());
	 	            		break;
	 	            	}
	 	            }
	 	            //用户未设定常用地址的情况下获取第一个地址
	 	            if(StringUtils.isEmpty(teacher.getLng())||StringUtils.isEmpty(teacher.getLat())){
	 	            	teacher.setLng(addressList.get(0).getLng());
	 	            	teacher.setLat(addressList.get(0).getLat());
	 	            	teacher.setArea(addressList.get(0).getCityStr());
	 	            }
	            }
	            //查询课时包
	            CourseMinus courseMinus=new CourseMinus();
	            courseMinus.setTeacherId(teacherId);
	            List<CourseMinus> courseMinusList=courseMinusService.queryCourseMinusCondition(courseMinus);
	            request.setAttribute("courseMinusList", courseMinusList);
	            // 获取老师创建的优惠券
				List<Coupon> couponList = this.couponService.getCouponListByTeacherId(teacherId);
				request.setAttribute("couponList", couponList);
				//教师风采
				TeacherStyle query=new TeacherStyle();
				query.setTeacherId(teacherId);
				query.setStatus(0);
				query.setType("img");
				List<TeacherStyle> picStyleList=teacherStyleService.queryTeacherStyleByCondition(query);
				request.setAttribute("picStyleList",picStyleList);
				query.setType("video");
				List<TeacherStyle> videoStyleList=teacherStyleService.queryTeacherStyleByCondition(query);
				request.setAttribute("videoStyleList",videoStyleList);
				
			}
			Long userId = getLoginUserId(request);
			request.setAttribute("userId",userId);
			Trxorder trxorder = new Trxorder();
			trxorder.setUserId(userId);
			trxorder.setTeacherId(teacherId);
			trxorder.setAssessStutotea(0L);
			trxorder.setTrxStatus(TrxOrderStatus.FINISH.toString());
			List<Trxorder> trxorderList = this.trxorderService.getTrxorderList(trxorder);
			if(ObjectUtils.isNotNull(trxorderList)){
				request.setAttribute("trxorderList",trxorderList);
			}
		} catch (Exception e) {
			logger.error("TeacherController.teacher--error", e);
			return setExceptionRequest(request, e);
		}
		return teacherinfo;
	}
	
	/**
	 * 增加教师信息的浏览量
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/ajax/addTeacherBrowseNum")
	@ResponseBody
	public Map<String,Object> addTeacherBrowseNum(HttpServletRequest request){
		try {
			String teacherId=request.getParameter("teacherId");
			if(StringUtils.isNotEmpty(teacherId)){
				teacherProfileService.updateTeacherBrowseNum(Long.parseLong(teacherId));
				this.setJson(true, null, null);
			}else{
				this.setJson(false, null, null);
			}
		} catch (Exception e) {
			logger.error("TeacherController.addTeacherBrowseNum--error", e);
			this.setJson(false, null, null);
		}
		return json;
	}
	
	/**
	 * 根据用户ID查询我的教师列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/front/myteacher/list")
	public String queryTeacherListByUserId(HttpServletRequest request, @ModelAttribute("page") PageEntity page){
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			// 获得当前登录用户ID
			Long userId = getLoginUserId(request);
			List<QueryTeacher> teacherList = teacherService.queryTeacherListByUserId(userId, page);
			request.setAttribute("page", this.getPage());
			request.setAttribute("teacherList", teacherList);
		} catch (Exception e) {
			logger.error("TeacherController.queryTeacherListByUserId---------error", e);
			return setExceptionRequest(request, e);
		}
		return my_teacher_list;
	}

	/**
	 * 跳转去教师个人资料完善页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/teacher/info/toperfect")
	public String toPerfectTeacherInfo(HttpServletRequest request){
		try {
			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			request.setAttribute("teacher", teacher);
			// 全部二级专业(年级)
			List<Subject> twoSubject = subjectService.getSubjectTwoList();
			request.setAttribute("twoSubject", twoSubject);
			// 全部科目
			List<Major> majors = majorService.queryMajorAllList();
			request.setAttribute("majors", majors);
		} catch (Exception e) {
			logger.error("TeacherController.toPerfectTeacherInfo--------error", e);
			return setExceptionRequest(request, e);
		}
		return teacher_ingormation;
	}

	/**
	 * 教师个人资料完善
	 * @return
	 */
	@RequestMapping("/front/ajax/teacher/info/perfect")
	@ResponseBody
	public Map<String, Object> perfectTeacherInfo(HttpServletRequest request, @ModelAttribute Teacher teacher){
		try {
			// 验证真实姓名是否为空
			if(StringUtils.isEmpty(teacher.getUserExpand().getRealname()) || teacher.getUserExpand().getRealname().equals("")){
				this.setJson(false, "realnameIsNull", null);
				return json;
			}
			// 验证头像是否为空
			if (StringUtils.isEmpty(teacher.getUserExpand().getAvatar()) || teacher.getUserExpand().getAvatar().equals("")){
				this.setJson(false, "avatarIsNull", null);
				return json;
			}
			// 验证学历是否为空
			if (StringUtils.isEmpty(teacher.getDegree()) || teacher.getDegree().equals("") || teacher.getDegree().equals("0")){
				this.setJson(false, "degreeIsNull", null);
				return json;
			}
			// 验证毕业院校
			if (StringUtils.isEmpty(teacher.getFinishSchool()) || teacher.getFinishSchool().equals("")){
				this.setJson(false, "finishSchoolIsNull", null);
				return json;
			}
			// 验证专业
			if (StringUtils.isEmpty(teacher.getProfession()) || teacher.getProfession().equals("")){
				this.setJson(false, "professionIsNull", null);
				return json;
			}
			// 验证生日
			if (ObjectUtils.isNull(teacher.getUserExpand().getBirthday())){
				this.setJson(false, "birthdayIsNull", null);
				return json;
			}
			// 验证教学年级
			if (StringUtils.isEmpty(teacher.getTeacherSubject()) || teacher.getTeacherSubject().equals("")){
				this.setJson(false, "subjectIsNull", null);
				return json;
			}
			// 验证教学科目
			if (StringUtils.isEmpty(teacher.getTeacherMajor()) || teacher.getTeacherMajor().equals("")){
				this.setJson(false, "majorIsNull", null);
				return json;
			}
			// 验证教龄是否为空
			if(ObjectUtils.isNull(teacher.getSeniority()) || teacher.getSeniority().intValue() < 0){
				this.setJson(false, "seniorityIsNull", null);
				return json;
			}
			// 验证教学特点是否为空
			if (ObjectUtils.isNull(teacher.getPeculiarity()) || teacher.getPeculiarity().equals("")){
				this.setJson(false, "peculiarityIsNull", null);
				return json;
			}
			// 验证教学宣言是否为空
			if (ObjectUtils.isNull(teacher.getTeachingEnounce()) || teacher.getTeachingEnounce().equals("")){
				this.setJson(false, "teachingEnounceIsNull", null);
				return json;
			}
			// 验证工作/学习经历
			if (ObjectUtils.isNull(teacher.getTeachingLive()) || teacher.getTeachingLive().equals("")){
				this.setJson(false, "teachingLiveIsNull", null);
				return json;
			}
			// 验证成功经历
			if (ObjectUtils.isNull(teacher.getTeachingSuccess()) || teacher.getTeachingSuccess().equals("")){
				this.setJson(false, "teachingSuccessIsNull", null);
				return json;
			}
			// 验证身份证图片
			if (StringUtils.isEmpty(teacher.getCard()) || teacher.getCard().equals("")){
				this.setJson(false, "cardIsNull", null);
				return json;
			}
			// 验证学历证图片
			if (StringUtils.isEmpty(teacher.getEducation()) || teacher.getEducation().equals("")){
				this.setJson(false, "educationIsNull", null);
				return json;
			}
			// 验证教师证
			if (StringUtils.isEmpty(teacher.getTeaching()) || teacher.getTeaching().equals("")){
				this.setJson(false, "teachingIsNull", null);
				return json;
			}
			// 验证专业资质证书
			if (StringUtils.isEmpty(teacher.getSpecialty()) || teacher.getSpecialty().equals("")){
				this.setJson(false, "specialtyIsNull", null);
				return json;
			}
			teacherService.updateTeacher(teacher);
			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			userJsonObject.addProperty("nickname", teacher.getUserExpand().getNickname());
			userJsonObject.addProperty("realname", teacher.getUserExpand().getRealname());
			userJsonObject.addProperty("hideStatus", teacher.getUserExpand().getHideStatus());
			userJsonObject.addProperty("avatar", teacher.getUserExpand().getAvatar());
			userJsonObject.addProperty("gender", teacher.getUserExpand().getGender());
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("TeacherController.perfectTeacherInfo--------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 教师个人中心发布课时安排
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/times/publish")
	public String toTeacherTimesPublish(HttpServletRequest request){
		try {
			// 获得登录用户ID
			Long userId = getLoginUserId(request);
			Teacher teacher = teacherService.getTeacherByUserId(userId);
			request.setAttribute("teacher", teacher);
		} catch (Exception e) {
			logger.error("TeacherController.toTeacherTimesPublish--------error", e);
			return setExceptionRequest(request, e);
		}
		return teachertimes_publish;
	}
	
	/**
	 * 教师发布课程
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/ajax/classhour/publish")
	@ResponseBody
	public Map<String, Object> teacherPublishClassHour(HttpServletRequest request){
		try {
			String message = "";
			String teacherId = request.getParameter("teacherId");
			String teacherClassHour = request.getParameter("teacherClassHour");
			if (StringUtils.isNotEmpty(teacherId) && !teacherId.equals("")) {
				message = teacherClassHourService.addTeacherClassHour(teacherClassHour, Long.parseLong(teacherId));
				this.setJson(true, message, null);
			} else {
				this.setJson(false, "dataError", null);
			}
		} catch (Exception e) {
			logger.error("TeacherController.teacherPublishClassHour--------error", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
}
	