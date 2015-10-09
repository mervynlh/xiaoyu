package com.yizhilu.os.edu.controller.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.entity.course.CourseAssess;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.QueryCourseAssess;
import com.yizhilu.os.edu.service.course.CourseAssessService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.user.UserIntegralService;

/**
 * CourseAssess管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class CourseAssessController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseAssessController.class);
    // 课程详情课程评价
    private static final String assess = getViewPath("/course/to_course_assess_ajax");
    // 播放页面课程评价
    private static final String assesskpoint = getViewPath("/course/to_kpoint_assess_ajax");
    
    @Autowired
    private CourseAssessService courseAssessService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
	private CourseService courseService;

    /**
     * 课程详情咨询列表
     * 
     * @return
     */
    @RequestMapping("/front/ajax/assess")
    public String assess(Model model,HttpServletRequest request, @RequestParam("courseId") Long courseId,@ModelAttribute("page") PageEntity page) {
        try {
            //分页页数
            page.setPageSize(6);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assess;
        } catch (Exception e) {
            logger.error("CourseAssessController.assess", e);
            return setExceptionRequest(request, e);
        }
    }
    /**
     * 课程详情咨询列表
     * 
     * @return
     */
    @RequestMapping("/front/ajax/consultation")
    public String consultation(Model model,HttpServletRequest request, @RequestParam("courseId") Long courseId,@ModelAttribute("page") PageEntity page) {
        try {
            //分页页数
            page.setPageSize(6);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            courseAssess.setKpointId(0L);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assess;
        } catch (Exception e) {
            logger.error("CourseAssessController.assess", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 播放页面评价列表
     * 
     * @return
     */
    @RequestMapping("/front/ajax/assesskpoint")
    public String assesskpoint(Model model,HttpServletRequest request, @RequestParam("kpointId") Long kpointId,@ModelAttribute("page") PageEntity page) {
        try {
        	 //分页页数
            page.setPageSize(6);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setKpointId(kpointId);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assesskpoint;
        } catch (Exception e) {
            logger.error("CourseAssessController.assesskpoint", e);
            return setExceptionRequest(request, e);
        }
    }
    
    /**
     * 课程详情评价列表
     * 
     * @return
     */
    @RequestMapping("/front/addassess")
    @ResponseBody
    public Map<String, Object> addassess(HttpServletRequest request, @ModelAttribute("courseAssess") CourseAssess courseAssess) {
        try {
        		courseAssess.setCreateTime(new Date());
	            courseAssess.setUserId(getLoginUserId(request));
	            courseAssess.setStatus(0);
	            courseAssess.setContent(WebUtils.replaceTagHTML(courseAssess.getContent()));
	            courseAssessService.addCourseAssess(courseAssess);
	            //添加课程评论添加积分
	            userIntegralService.addUserIntegral(IntegralKeyword.assess.toString(), getLoginUserId(request), courseAssess.getCourseId(), 0L, "");
	            this.setJson(true, "", null);
	            return json;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            this.setJson(false, "", null);
            return json;
        }
    }
}