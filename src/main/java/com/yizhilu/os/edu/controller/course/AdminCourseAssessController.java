package com.yizhilu.os.edu.controller.course;

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

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.CourseAssess;
import com.yizhilu.os.edu.entity.course.QueryCourseAssess;
import com.yizhilu.os.edu.service.course.CourseAssessService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.course.AdminCourseAssessController
 * @description 课程评论管理
 * @author xujunbao
 * @Create Date : 2014年9月23日 上午11:56:13
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseAssessController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminCourseAssessController.class);

	private static final String toCourseAssessList = getViewPath("/admin/course/course_assess_list");// 返回课程评论页面
	private static final String toCourseAssessInfo = getViewPath("/admin/course/courseAssess_info");// 课程评论详情
	@Autowired
	private CourseAssessService courseAssessService;

	// 绑定变量参数
	@InitBinder("queryCourseAssess")
	public void initBinderQueryCourseAssess(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourseAssess.");
	}

	// 绑定变量参数
	@InitBinder("courseAssess")
	public void initBinderCourseAssess(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("courseAssess.");
	}

	/**
	 * 查询课程评论列表
	 * 
	 * @param model
	 * @param request
	 * @param queryCourseAssess
	 * @param page
	 * @return
	 */
	@RequestMapping("/courseAssess/list")
	public String getCourseAssessList(Model model, HttpServletRequest request, @ModelAttribute QueryCourseAssess queryCourseAssess, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页
			this.setPage(page);
			this.getPage().setPageSize(10);
			// 查询课程评论列表
			List<QueryCourseAssess> courseAssessList = courseAssessService.getAdminCourseAssessList(queryCourseAssess, this.getPage());
			model.addAttribute("courseAssessList", courseAssessList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("getCourseAssessList", e);
		}
		return toCourseAssessList;
	}

	/**
	 * 批量刪除課程评论
	 * 
	 * @param request
	 * @param courseAssessIds
	 * @return
	 */
	@RequestMapping("/courseAssess/del")
	@ResponseBody
	public Map<String, Object> delCourseAssessBatch(HttpServletRequest request, @RequestParam("courseAssessIds") String courseAssessIds) {
		try {
			if (StringUtils.isNotEmpty(courseAssessIds)) {// 删除课程评论
				courseAssessService.delCourseAssessBatch(courseAssessIds);
				this.setJson(true, "success", null);
			} else {
				this.setJson(false, "error", null);
			}
		} catch (Exception e) {
			logger.error("delCourseAssessBatch", e);
		}
		return json;
	}

	/**
	 * 获得详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/courseAssess/info/{id}")
	public String getCourseAssessById(Model model, @PathVariable("id") Long id) {
		try {
			// 查询课程评论详情
			QueryCourseAssess queryCourseAssess = courseAssessService.getCourseAssessById(id);
			model.addAttribute("queryCourseAssess", queryCourseAssess);
		} catch (Exception e) {
			logger.error("getCourseAssessById", e);
		}
		return toCourseAssessInfo;
	}

	/**
	 * 更新 显示隐藏
	 * 
	 * @param request
	 * @param courseAssess
	 * @return
	 */
	@RequestMapping("/courseAssess/update")
	@ResponseBody
	public Map<String,Object> updateCourseAssess(HttpServletRequest request, @ModelAttribute("courseAssess") CourseAssess courseAssess) {
		try {
			if (ObjectUtils.isNotNull(courseAssess)) {// 更新状态
				courseAssessService.updateCourseAssess(courseAssess);
				this.setJson(true, "", null);
			}
		} catch (Exception e) {
			logger.error("updateCourseAssess", e);
			this.setJson(false, "", null);
		}
		return json;
	}
}
