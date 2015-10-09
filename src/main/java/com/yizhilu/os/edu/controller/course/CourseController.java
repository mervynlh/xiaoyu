package com.yizhilu.os.edu.controller.course;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.CourseModel;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.order.TrxorderExpand;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;
import com.yizhilu.os.edu.entity.user.UserAddress;
import com.yizhilu.os.edu.service.course.CourseFavoritesService;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.major.SubjectMajorService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherSubjectService;
import com.yizhilu.os.edu.service.user.UserAddressService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.zoomMeetings.ZoomMeetingService;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class CourseController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    //公开课详情
    private static final String one_to_one=getViewPath("/course/one_to_one");//教师一对一开课
    private static final String one_to_add=getViewPath("/course/one_to_add");//教师创建一对一课程
    private static final String one_to_one_update=getViewPath("/course/one_to_one_update");//教师修改一对一课程d
	private static String favouriteList = getViewPath("/ucenter/favourite_course_list");// 收藏列表
	private static String class_course_list=getViewPath("/course/class_course");//班课列表
	private static String class_course_add=getViewPath("/course/class_course_add");//创建班课
	private static String class_course_update=getViewPath("/course/class_course_update");//修改班课
	private static final String ajax_course_record = getViewPath("/teacher/ajax_course_record");// 教师详情页上课记录
	private static final String ajax_course = getViewPath("/teacher/ajax_course");// 教师详情页班课列表
	private static final String class_course_info = getViewPath("/course/class_course_info");// 班课详情
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired 
    UserService userService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private CourseKpointsService courseKpointsService;
	@Autowired
	private TeacherSubjectService teacherSubjectService;
	@Autowired
	private SubjectMajorService subjectMajorService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private ZoomMeetingService zoomMeetingService;
	
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
    @InitBinder("course")
    public void initBinderCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("course.");
    }

    /**
     * 开通课程验证
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/course/check/{id}")
    @ResponseBody
    public Map<String,Object> checkCourse(@PathVariable Long id, HttpServletRequest request) {
        try {
            Course course=courseService.getCourseById(id);
            if(course.getIsavaliable()!=0){
            	this.setJson(false, "课程已下架", null);
            }else if(course.getCurrentprice().doubleValue()<=0){
            	this.setJson(false, "开通课程金额不能为0", null);
            }else{
            	this.setJson(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("CourseController.checkCourse",e);
            this.setJson(false, "error", null);
        }
        return json;
    }
	/**
	 * 获取收藏列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/fav")
	public ModelAndView favouriteCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(favouriteList);
		try {
			this.setPage(page);
			//用户收藏课程
			List<FavouriteCourseDTO> courseList = courseFavoritesService.getFavouriteCourseDTO(getLoginUserId(request), this.getPage());
			//收藏最多
			List<FavouriteCourseDTO> courseMoreList = courseFavoritesService.getMoreFavouriteCourse();
			// 获得所有推荐课程
			Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
			modelAndView.addObject("courseList", courseList);
			modelAndView.addObject("courseMoreList", courseMoreList);
			modelAndView.addObject("mapCourseList", mapCourseList);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("favouriteCourseList" + e);
            return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
    /**
     * 删除收藏课程
     * @param courseIds
     * @return
     */
    @RequestMapping("/uc/del")
    public ModelAndView delCourseFav(HttpServletRequest request,@RequestParam("sellIdArr") String courseIds){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/uc/fav");
    	try {
    		//课程方法删除
    		courseFavoritesService.deleteCourseFavoritesById(courseIds);
		} catch (Exception e) {
			e.printStackTrace();
            return new ModelAndView(setExceptionRequest(request, e));
		}
    	return modelAndView;
    }
    /**
     * 教师一对一开课列表
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/ontToOne/list")
    public String oneToOne(HttpServletRequest request){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		List<Course> oneToOneList=courseService.queryOneToOneByTeacherId(teacher.getId());
    		request.setAttribute("oneToOneList", oneToOneList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.oneToOne---error" ,e);
            return setExceptionRequest(request, e);
		}
    	return one_to_one;
    }
    /**
     * 教师跳转创建一对一页面
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/ontToOne/add")
    public String toAddOneToOne(HttpServletRequest request){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		List<TeacherSubject> teacherSubject=teacherSubjectService.queryTeacherParentSubject(teacher.getId());
    		request.setAttribute("teacherSubject", teacherSubject);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.oneToOne---error" ,e);
            return setExceptionRequest(request, e);
		}
    	return one_to_add;
    }
    /**
     * 获取老师的科目
     * @param request
     * @return
     */
    @RequestMapping("/front/ajax/getTeacherMajor")
    @ResponseBody
    public Map<String,Object> getTeacherMajor(HttpServletRequest request){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
			String subjectId=request.getParameter("subjectId");
			if(StringUtils.isNotEmpty(subjectId)){
				List<SubjectMajor> sonSubjectMajor= subjectMajorService.querySonMajorByParent(Long.parseLong(subjectId),teacher.getId());
				this.setJson(true, null, sonSubjectMajor);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.getTeacherMajor---error" ,e);
			this.setJson(false, "系统繁忙", null);
		}
    	return json;
    }
    /**
     * 保存一对一课程
     * @param request
     * @return
     */
    @RequestMapping("/front/ajax/saveOneToOne")
    @ResponseBody
    public Map<String,Object> saveOneToOne(HttpServletRequest request,@ModelAttribute("course") Course course){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		course.setTeacherId(teacher.getId());
    		course.setSellType("1");
    		//查询是否存在该专业的一对一课程
    		int num=courseService.querySubjectMajorConflict(course.getSubjectMajorId(), course.getTeacherId(), course.getSellType(), 0l);
    		if(num>0){
    			this.setJson(false, "该专业课程已存在,请重新选定专业", null);
    		}else{
    			course.setIsavaliable(0l);
    			String[] modelArray=course.getCourseModel().split(",");
    			//将授课方式及价格以json串存入
    			String modelStr="{";
    			for(String model:modelArray){
    				String[] arrayStr=model.split(":");
    				modelStr+="\""+arrayStr[0]+"\":\""+formatMoney(arrayStr[1])+"\",";
    			}
    			modelStr=modelStr.substring(0,modelStr.length()-1);
    			modelStr+="}";
    			course.setCourseModel(modelStr);
    			courseService.addCourse(course);
    			this.setJson(true, "创建成功", null);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.saveOneToOne---error" ,e);
			this.setJson(false, "系统繁忙", null);
		}
    	return json;
    }
    /**
     * 跳转编辑一对一课程页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/uc/teacher/toUpdateOneToOne/{id}")
    public String toUpdateOneToOne(HttpServletRequest request,@PathVariable("id") Long id){
    	try {
    		Course oneToOne =courseService.getCourseById(id);
    		request.setAttribute("oneToOne", oneToOne);
    		
    		//查询教师的专业
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		List<TeacherSubject> teacherSubject=teacherSubjectService.queryTeacherParentSubject(teacher.getId());
    		request.setAttribute("teacherSubject", teacherSubject);
    		
    		if(ObjectUtils.isNotNull(oneToOne.getSubjectMajor())){
    			//获取教师选择专业的父级专业id
        		Subject query=new Subject();
    			query.setSubjectId(oneToOne.getSubjectMajor().getSubjectid());
        		Subject parentSubject= subjectService.getSubjectBySubjectId(query);
        		if(parentSubject.getParentId().intValue()==0){
        			request.setAttribute("subjectId", oneToOne.getSubjectMajor().getSubjectid());
        		}else{
        			request.setAttribute("subjectId", parentSubject.getParentId());
        		}
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.toUpdateOneToOne()--error",e);
			return setExceptionRequest(request, e);
		}
    	return one_to_one_update;
    }
    /**
     * 修改一对一课程信息
     * @param request
     * @param course
     * @return
     */
    @RequestMapping("/front/ajax/updateOneToOne")
    @ResponseBody
    public Map<String,Object> updateOneToOne(HttpServletRequest request,@ModelAttribute("course") Course course){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		course.setTeacherId(teacher.getId());
			course.setSellType("1");
			course.setIsavaliable(0l);
			//查询是否存在该专业的一对一课程
    		int num=courseService.querySubjectMajorConflict(course.getSubjectMajorId(), course.getTeacherId(), course.getSellType(), course.getId());
    		if(num>0){
    			this.setJson(false, "该专业课程已存在,请重新选定专业", null);
    		}else{
				String[] modelArray=course.getCourseModel().split(",");
				//将授课方式及价格以json串存入
				String modelStr="{";
				for(String model:modelArray){
					String[] arrayStr=model.split(":");
					modelStr+="\""+arrayStr[0]+"\":\""+formatMoney(arrayStr[1])+"\",";
				}
				modelStr=modelStr.substring(0,modelStr.length()-1);
				modelStr+="}";
				course.setCourseModel(modelStr);
				courseService.updateCourseTeacher(course);
				this.setJson(true, "修改成功", null);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.updateOneToOne---error" ,e);
			this.setJson(false, "系统繁忙", null);
		}
    	return json;
    }
    /**
     * 格式化金钱格式
     * @param money
     * @return
     */
    private String formatMoney(String money){
    	if(StringUtils.isNotEmpty(money)){
        	double moneyDou=Double.parseDouble(money);
            DecimalFormat myformat=new DecimalFormat("0.00");
            return myformat.format(moneyDou);
    	}else{
    		return "0.00";
    	}
    }
    /**
     * 教师删除课程
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/uc/teacher/delCourse/{id}")
    public String delCourseById(HttpServletRequest request,@PathVariable("id") Long id){
    	try {
    		String type=request.getParameter("type");
    		courseService.deleteCourseById(id);
    		if(StringUtils.isNotEmpty(type)&&type.equals("class")){
    			return "redirect:/uc/teacher/classCourse/list";
    		}else{
    			return "redirect:/uc/teacher/ontToOne/list";
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.delCourseById()---error" ,e);
			return setExceptionRequest(request, e);
		}
    }
    /**
     * 班课列表
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/classCourse/list")
    public String classCourseList(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
    	try {
    		this.setPage(page);
    		this.getPage().setPageSize(6);
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		QueryCourse queryCourse=new QueryCourse();
    		queryCourse.setTeacherId(teacher.getId());
    		List<Course> classCourseList=courseService.queryClassCourseByTeacherPage(queryCourse, page);
    		request.setAttribute("classCourseList", classCourseList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.classCourseList()---error" ,e);
			return setExceptionRequest(request, e);
		}
    	return class_course_list;
    }
    /**
     * 跳转创建班课页面 
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/toAddClassCourse")
    public String toAddClassCourse(HttpServletRequest request){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		List<TeacherSubject> teacherSubject=teacherSubjectService.queryTeacherParentSubject(teacher.getId());
    		request.setAttribute("teacherSubject", teacherSubject);
    		
    		//查询我全部的地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList = userAddressService.getUserAddressList(userAddress);
			request.setAttribute("userAddressList", userAddressList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.toAddClassCourse()---error" ,e);
			return setExceptionRequest(request, e);
		}
    	return class_course_add;
    }
    /**
     * 创建班课
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/addClassCourse")
    @ResponseBody
    public Map<String,Object> addClassCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		course.setTeacherId(teacher.getId());
			course.setSellType("2");
			//查询是否存在该专业的一对一课程
    		int num=courseService.querySubjectMajorConflict(course.getSubjectMajorId(), course.getTeacherId(), course.getSellType(), 0l);
    		if(num>0){
    			this.setJson(false, "该专业课程已存在,请重新选定专业", null);
    		}else{
				course.setIsavaliable(0l);
				//将授课方式及价格以json串存入
				String modelStr="{";
					modelStr+="\""+course.getCourseModel()+"\":\""+formatMoney(course.getCurrentprice().toString())+"\",";
				modelStr=modelStr.substring(0,modelStr.length()-1);
				modelStr+="}";
				course.setCourseModel(modelStr);
				
				//判断用户是否勾选在线授课
				if(course.getCourseModel().indexOf(CourseModel.ONLINECOU.toString())>0){
					//创建直播返回的userId
					if(StringUtils.isEmpty(teacher.getZoomMeetingUserId())){
						zoomMeetingService.createZoomUser(teacher.getId());
					}
				}
				courseService.addCourse(course);
				this.setJson(true, "操作成功", null);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.addClassCourse()---error" ,e);
			this.setJson(false, "系统繁忙", null);
		}
    	return json;
    }
    /**
     * 修改班课
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/uc/teacher/toUpdateClassCourse/{id}")
    public String toUpdateClassCourse(HttpServletRequest request,@PathVariable("id") Long id){
    	try {
    		//查询班课信息
    		Course classCourse =courseService.getCourseById(id);
    		request.setAttribute("classCourse", classCourse);
    		
    		if(ObjectUtils.isNotNull(classCourse.getSubjectMajor())){
    			//获取教师选择专业的父级专业id
        		Subject query=new Subject();
    			query.setSubjectId(classCourse.getSubjectMajor().getSubjectid());
        		Subject parentSubject= subjectService.getSubjectBySubjectId(query);
        		if(parentSubject.getParentId().intValue()==0){
        			request.setAttribute("subjectId", classCourse.getSubjectMajor().getSubjectid());
        		}else{
        			request.setAttribute("subjectId", parentSubject.getParentId());
        		}
    		}
    		
    		//查询讲师的专业科目
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		List<TeacherSubject> teacherSubject=teacherSubjectService.queryTeacherParentSubject(teacher.getId());
    		request.setAttribute("teacherSubject", teacherSubject);
    		request.setAttribute("courseModel", classCourse.getCourseModelMap().get("ONLINECOU"));
    		
    		//查询我全部的地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList = userAddressService.getUserAddressList(userAddress);
			request.setAttribute("userAddressList", userAddressList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.toUpdateClassCourse()---error" ,e);
			return setExceptionRequest(request, e);
		}
    	return class_course_update;
    }
    /**
     * 老师修改班课
     * @param request
     * @param course
     * @return
     */
    @RequestMapping("/uc/teacher/updateClassCourse")
    @ResponseBody
    public Map<String,Object> updateClassCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	try {
    		Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
    		course.setTeacherId(teacher.getId());
			course.setSellType("2");
			//查询是否存在该专业的一对一课程
    		int num=courseService.querySubjectMajorConflict(course.getSubjectMajorId(), course.getTeacherId(), course.getSellType(), course.getId());
    		if(num>0){
    			this.setJson(false, "该专业课程已存在,请重新选定专业", null);
    		}else{
				//将授课方式及价格以json串存入
				String modelStr="{";
					modelStr+="\""+course.getCourseModel()+"\":\""+formatMoney(course.getCurrentprice().toString())+"\",";
				modelStr=modelStr.substring(0,modelStr.length()-1);
				modelStr+="}";
				course.setCourseModel(modelStr);
				
				//判断用户是否勾选在线授课
				if(course.getCourseModel().indexOf(CourseModel.ONLINECOU.toString())>0){
					//创建直播返回的userId
					if(StringUtils.isEmpty(teacher.getZoomMeetingUserId())){
						zoomMeetingService.createZoomUser(teacher.getId());
					}
				}
				
				courseService.updateClassCourseTeacher(course);
				this.setJson(true, "操作成功", null);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.updateClassCourse()---error" ,e);
			this.setJson(false, "系统繁忙", null);
		}
    	return json;
    }
    
    /**
     * 教师详情选择专业获取科目课程信息
     * @param request
     * @return
     */
    @RequestMapping("/front/ajax/getMajorCourseBySubject")
    @ResponseBody
    public Map<String,Object> getMajorCourseBySubject(HttpServletRequest request,@ModelAttribute("queryCourse") QueryCourse query){
    	try {
    		if(query.getTeacherId()>0){
    			List<Map<String,Object>> courseList=courseService.queryCourseToTeacher(query);
    			this.setJson(true, null, courseList);
    		}else{
    			this.setJson(false, null, null);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			this.setJson(false, null, null);
		}
    	return json;
    }
    
    /**
     * 根据id查询课程详情
     * @param request
     * @param courseId
     * @return
     */
    @RequestMapping("/front/ajax/getCourseById/{id}")
    @ResponseBody
    public Map<String,Object> getCourseById(HttpServletRequest request,@PathVariable("id") Long courseId){
    	try {
			Course course=courseService.getCourseById(courseId);
			this.setJson(true, null, course);
		} catch (Exception e) {
			// TODO: handle exception
			this.setJson(false, "系统异常", null);
		}
    	return json;
    }
    
    /**
     * 根据教师id查询教师上课记录
     * @param model
     * @param teacherId
     * @param page
     * @return
     */
    @RequestMapping("/front/ajax/getCourseRecord/{teacherId}")
    public String getCourseRecord(Model model,@PathVariable("teacherId") Long teacherId,@ModelAttribute("page") PageEntity page){
    	try {
    		page.setPageSize(10);
    		this.setPage(page);
    		model.addAttribute("page", this.getPage());
    		TrxorderExpand trxorderExpand = new TrxorderExpand();
    		trxorderExpand.setTeacherId(teacherId);
    		List<TrxorderExpand> list = this.trxorderService.getTrxorderExpandByTeacherId(trxorderExpand, page);
    		model.addAttribute("courseRecordList", list);
		} catch (Exception e) {
			logger.error("CourseController.getCourseRecord---error" + e);
		}
    	return ajax_course_record;
    }
    
    /**
     * 根据教师id查询班课
     * @param request
     * @param teacherId 教师id
     * @return
     */
    @RequestMapping("/front/ajax/getCourseBySellType/{teacherId}")
    public String getCourseBySellType(Model model,@PathVariable("teacherId") Long teacherId,@ModelAttribute("page") PageEntity page){
    	try {
    		page.setPageSize(5);
    		this.setPage(page);
    		model.addAttribute("page", this.getPage());
    		QueryCourse queryCourse = new QueryCourse();
    		queryCourse.setTeacherId(teacherId);
    		List<Course> courseList=courseService.queryClassCourseByTeacherPage(queryCourse, this.getPage());
			model.addAttribute("courseList", courseList);
		} catch (Exception e) {
			logger.error("CourseController.getCourseBySellType---error" + e);
		}
    	return ajax_course;
    }
    
    /**
     * 根据课程id查询课程详情
     * @param courseId 课程id
     * @return
     */
    @RequestMapping("/front/course/getCourseInfo/{courseId}")
    public String getCourseInfoById(Model model,@PathVariable("courseId") Long courseId){
    	try {
    		Course course = this.courseService.getClassCourseById(courseId);
    		model.addAttribute("course", course);
    		UserAddress userAddress = this.userAddressService.getUserAddressById(course.getAddressId());
    		model.addAttribute("address", userAddress);
    		List<CourseKpoints> kpointsList = this.courseKpointsService.queryKpointsByCourseId(courseId);
    		model.addAttribute("kpointsList", kpointsList);
    		//查询班课是否开课
    		int openState=courseKpointsService.queryOpenStatu(courseId);
    		model.addAttribute("openState",openState);
		} catch (Exception e) {
			logger.error("CourseController.getCourseInfoById---error" + e);
		}
    	return class_course_info;
    }
}