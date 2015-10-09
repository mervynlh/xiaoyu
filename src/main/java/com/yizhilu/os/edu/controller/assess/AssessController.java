package com.yizhilu.os.edu.controller.assess;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.assess.Assess;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.assess.QueryAssess;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.assess.AssessService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.user.UserExpandService;

/**
 * Teacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
@RequestMapping("/front")
public class AssessController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AssessController.class);
	
	private static final String teacher_assess=getViewPath("/teacher/teacher_assess");//教师详情评论
	private static final String add_assess_stuToTea=getViewPath("/teacher/add_assess");//教师详情发表评论(学生评论老师)
	private static final String add_assess_teaToStu=getViewPath("/ucenter/teacher/add_assess_toStu");//老师评论学生
	
	@Autowired
	private AssessService assessService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserExpandService userExpandService;
	// 绑定属性 封装参数
	@InitBinder("queryAssess")
	public void initQueryAssess(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("queryAssess.");
	}
	@InitBinder("assess")
	public void initAssess(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("assess.");
	}
	
	/**
	 * 教师详情评论
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/ajax/assess")
	public ModelAndView teacherAssess(HttpServletRequest request,@ModelAttribute("queryAssess") QueryAssess queryAssess,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(teacher_assess);
		try {
			String teacherId=request.getParameter("teacherId");
			if(StringUtils.isNotEmpty(teacherId)){
				this.setPage(page);
				this.getPage().setPageSize(8);
				queryAssess.setTeacherId(Long.parseLong(teacherId));
				List<AssessDto> assessList= assessService.queryAssessListByCondition(queryAssess, page);
				model.addObject("assessList", assessList);
				model.addObject("teacherId",teacherId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AssessController.teacherAssess--error", e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}
	/**
	 * 跳转发布评论页面(学生评价老师)
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddAssessStuToTea/{id}/{orderId}")
	public String toAddAssessStuToTea(HttpServletRequest request,@PathVariable("id") Long id,@PathVariable("orderId") Long orderId){
		try {
			Teacher teacher=teacherService.getTeacherInfoById(id);
			request.setAttribute("teacher", teacher);
			request.setAttribute("orderId", orderId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AssessController.toAddAssessStuToTea",e);
			return setExceptionRequest(request, e);
		}
		return add_assess_stuToTea;
	}
	/**
	 * 跳转发布评论页面(老师评价学生)
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddAssessTeaToStu/{id}/{orderId}")
	public String toAddAssessTeaToStu(HttpServletRequest request,@PathVariable("id") Long id,@PathVariable("orderId") Long orderId){
		try {
			Long userId = getLoginUserId(request);
			Teacher teacher=teacherService.getTeacherByUserId(userId);
			Teacher tea = teacherService.getTeacherInfoById(teacher.getId());
			request.setAttribute("teacher", tea);
			UserExpandDto user = userExpandService.getUserExpandByUids(id);
			request.setAttribute("user", user);
			request.setAttribute("orderId", orderId);
		} catch (Exception e) {
			logger.error("AssessController.toAddAssessTeaToStu",e);
			return setExceptionRequest(request, e);
		}
		return add_assess_teaToStu;
	}
	
	/**
	 * 提交评论
	 * @param request
	 * @param assess
	 * @return
	 */
	@RequestMapping("/assess/add")
	@ResponseBody
	public Map<String,Object> addAssess(HttpServletRequest request,@ModelAttribute("assess") Assess assess){
		try {
			Long userId = getLoginUserId(request);
			UserExpandDto user = this.userExpandService.getUserExpandByUids(userId);
			if(user.getUserType()==0){
				assess.setUserId(userId);	
				assess.setStatus(2);//对老师评价
			}else if(user.getUserType()==1){
				Teacher teacher = this.teacherService.getTeacherByUserId(userId);
				assess.setTeacherId(teacher.getId());
				assess.setStatus(1);//对学生评价
			}
			assessService.addAssess(assess);
			this.setJson(true, "评价成功", null);
		} catch (Exception e) {
			logger.error("AssessController.addAssess",e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}
}