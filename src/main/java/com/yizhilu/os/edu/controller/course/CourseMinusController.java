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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.service.course.CourseMinusService;
import com.yizhilu.os.edu.service.course.TeacherService;
/**
 * 教师课时优惠
 * @author Administrator
 * 2015-9-10
 */
@Controller
public class CourseMinusController extends EduBaseController{

	private static final Logger logger = LoggerFactory.getLogger(CourseMinusController.class);

	private static final String minus_list = getViewPath("/ucenter/teacher/minus_list");// 课时优惠列表
	
	@Autowired
	private CourseMinusService courseMinusService;
	@Autowired
	private TeacherService teacherService; 
	
	@InitBinder("minus")
    public void initBinderMinus(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("minus.");
    }
	
	
	/**
	 * 教师可是优惠列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/minusList")
	public String courseMinusList(HttpServletRequest request){
		try {
			CourseMinus query=new CourseMinus();
			Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
			query.setTeacherId(teacher.getId());
			List<CourseMinus> minusList=courseMinusService.queryCourseMinusCondition(query);
			request.setAttribute("minusList", minusList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseMinusController.courseMinusList()--error",e);
			return setExceptionRequest(request, e);
		}
		return minus_list;
	}
	/**
	 * 创建课时优惠
	 * @param reqeust
	 * @return
	 */
	@RequestMapping("/uc/teacher/addMinus")
	@ResponseBody
	public Map<String,Object> addMinus(HttpServletRequest request,@ModelAttribute("minus") CourseMinus minus){
		try {
			CourseMinus query=new CourseMinus();
			Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
			query.setTeacherId(teacher.getId());
			List<CourseMinus> minusList=courseMinusService.queryCourseMinusCondition(query);
			//课时优惠上限判断
			if(minusList.size()<3){
				minus.setTeacherId(teacher.getId());
				courseMinusService.createCourseMinus(minus);
				this.setJson(true, "创建成功",null);
			}else{
				this.setJson(false, "课时优惠个数已达上限", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseMinusController.addMinus()--error",e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	} 
	/**
	 * 修改课时优惠
	 * @param request
	 * @param minus
	 * @return
	 */
	@RequestMapping("/uc/teacher/updateMinus")
	@ResponseBody
	public Map<String,Object> updateMinus(HttpServletRequest request,@ModelAttribute("minus") CourseMinus minus){
		try {
			if(ObjectUtils.isNotNull(minus.getId())){
				courseMinusService.updateCourseMinus(minus);
				this.setJson(true, "修改成功",null);
			}else{
				this.setJson(false, "系统繁忙", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseMinusController.addMinus()--error",e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	} 
	/**
	 * 删除优惠
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/delMinusById/{id}")
	public String delMinusById(HttpServletRequest request,@PathVariable("id") Long id){
		try {
			courseMinusService.delMinusById(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseMinusController.delMinusById()--error",e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/teacher/minusList";
	}
}