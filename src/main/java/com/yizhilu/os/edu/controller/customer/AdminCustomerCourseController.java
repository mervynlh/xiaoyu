package com.yizhilu.os.edu.controller.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.common.util.DateEditor;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.customer.CustomerCourse;
import com.yizhilu.os.edu.entity.customer.QueryCustomerCourse;
import com.yizhilu.os.edu.service.customer.CustomerCourseService;

/**
 * 后台customerCourse接口管理
 * 
 * @author guozhenzhou
 * @version 2014-09-24
 */
@Controller
@RequestMapping("/admin")
public class AdminCustomerCourseController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminCustomerCourseController.class);
	private static final String customerCourseList = getViewPath("/admin/course/customerCourse_list");
	private String deleteRedirect = "redirect:/admin/customerCourse/CourseList";
	/**
	 * customerCourseService服务
	 */
	@Autowired
	private CustomerCourseService customerCourseService;

	/**
	 * 绑定CustomerCourse属性
	 */
	@InitBinder("customerCourse")
	public void initBinderCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("customerCourse.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	@InitBinder("queryCustomerCourse")
	public void initBinderqueryCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCustomerCourse.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 后台自定义课程列表
	 * 
	 * @param queryCustomerCourse
	 */
	@RequestMapping("/customerCourse/CourseList")
	public ModelAndView queryCustomerCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCustomerCourse") QueryCustomerCourse queryCustomerCourse) {
		ModelAndView model = new ModelAndView();
		try {
			this.setPage(page);
			model.setViewName(customerCourseList);
			this.getPage().setPageSize(10);
			List<QueryCustomerCourse> courseList = new ArrayList<QueryCustomerCourse>();
			courseList = customerCourseService.getCustomerCourseList(queryCustomerCourse, this.getPage());
			model.addObject("courseList", courseList);
			model.addObject("page", this.getPage());
			model.addObject("queryCustomerCourse", queryCustomerCourse);
		} catch (Exception e) {
			logger.error("queryCustomerCourseList-----", e);
		}

		return model;
	}

	/**
	 * 删除自定义课程
	 * 
	 * @param customerCourseId
	 * 
	 */
	@RequestMapping("/customerCourse/deleteCourse")
	public ModelAndView deleteCustomerCourse(@ModelAttribute("customerCourseId") long customerCourseId) {
		ModelAndView model = new ModelAndView();
		try {
			customerCourseService.deleteCustomerCourseById(customerCourseId);
			model.setViewName(deleteRedirect);
		} catch (Exception e) {
			logger.error("deleteCustomerCourse.AdminCustomerCourseController", e);
		}
		return model;
	}

	/**
	 * 更新自定义课程状态 默认显示
	 * 
	 * @param customerCourseId
	 */
	@RequestMapping("/customerCourse/updateCustomerCourse")
	@ResponseBody
	public Map<String, Object> updateCustomerCourse(HttpServletRequest request, HttpServletResponse response, @ModelAttribute() CustomerCourse customerCourse) {
		try {
			// CustomerCourse cusCourse=new CustomerCourse();
			if (customerCourse != null) {
				CustomerCourse course = customerCourseService.getCustomerCourseById(customerCourse.getId());
				course.setStatus(customerCourse.getStatus());
				customerCourseService.updateCustomerCourse(course);
				this.setJson(true, "updatesuccess", course);
			} else {
				this.setJson(false, "参数为空", null);
			}
		} catch (Exception e) {
			logger.error("updateCustomerCourse----------", e);
			this.setJson(false, "请求错误", null);
		}

		return json;
	}

}
