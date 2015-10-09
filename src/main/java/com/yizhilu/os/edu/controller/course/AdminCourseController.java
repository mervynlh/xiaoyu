package com.yizhilu.os.edu.controller.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);

	// 课程列表
	private static final String showCourseList = getViewPath("/admin/course/course_list");//课程列表
	private static final String showCouponCourseList = getViewPath("/admin/course/course_coupon_list");//课程列表(优惠券限制课程)
	private static final String course_infor=getViewPath("/admin/course/course_infor");//老师开课详情
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private CourseKpointsService courseKpointsService;
	
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
	 * 课程列表展示
	 * 
	 * @return
	 */
	@RequestMapping("/cou/list")
	public String showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			
			//获取专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            
            //获取科目
            List<Major> majorList=majorService.queryMajorAllList();
            request.setAttribute("majorList", majorList);
		} catch (Exception e) {
			logger.error("CourseController.showCourseList", e);
			return setExceptionRequest(request, e);
		}
		return showCourseList;
	}

	/**
	 * 课程列表(优惠券限制课程用)
	 * 
	 * @return
	 */
	@RequestMapping("/cou/couponCourseList")
	public String showCourseListByCoupon(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
		} catch (Exception e) {
			logger.error("CourseController.showCourseListByRecommend", e);
			return setExceptionRequest(request, e);
		}
		return showCouponCourseList;
	}
	
	
	/**
	 * 审核/关闭课程
	 * @return
	 */
	@RequestMapping("/cou/verifyCourse")
	@ResponseBody
	public Map<String, Object> verifyCourse(HttpServletRequest request,@RequestParam("isavaliable") Long isavaliable ,@RequestParam("ids") String ids){
		try {
			courseService.verifyCourse(isavaliable,ids);
			this.setJson(true, "操作成功",null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.verifyCourse()", e);
			this.setJson(false, null, null);
		}
		return json;
	}
	/**
	 * 审核全部课程
	 * @return
	 */
	@RequestMapping("/cou/verifyAllCourse")
	@ResponseBody
	public Map<String, Object> verifyAllCourse(HttpServletRequest request){
		try {
			courseService.verifyAllCourse();
			this.setJson(true, "操作成功",null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseController.verifyCourse()", e);
			this.setJson(false, null, null);
		}
		return json;
	}
	/**
	 * 老师开课详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/cou/infor/{id}")
	public String infor(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			Course course = courseService.getCourseById(id);
			request.setAttribute("course", course);
			
			//查询课节
			if(course.getSellType().equals("2")){
				List<CourseKpoints> kpoints= courseKpointsService.queryKpointsByCourseId(id);
				request.setAttribute("kpoints", kpoints);
			}
		} catch (Exception e) {
			logger.error("CourseController.infor--error", e);
			return setExceptionRequest(request, e);
		}
		return course_infor;
	}
}