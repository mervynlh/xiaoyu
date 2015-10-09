package com.yizhilu.os.edu.controller.course;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.CourseStudyhistoryService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseStudyhistory;
import com.yizhilu.os.edu.entity.order.TrxorderDetailDTO;
/**
 * CourseStudyhistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class CourseStudyhistoryController extends EduBaseController{

 	@Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
 	private String studyHistory = getViewPath("/ucenter/study_list");// 学习记录
 	private String ajax_studyHistory = getViewPath("/course/ajax_study_history");// 学习记录ajax
 	
 	private static final String studyHistoryList = getViewPath("/ucenter/study_list");
    private static final String myStudentStudyHistoryList = getViewPath("/ucenter/teacher/student_study_list");

    private Logger logger = LoggerFactory.getLogger(CourseStudyhistoryController.class);
 	@Autowired
    private CourseService courseService;
 	@Autowired
 	private TrxorderDetailService trxorderDetailService;
 	
 	/**
     * 学习记录
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/study")
    public ModelAndView studyHistory(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(studyHistory);
        CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
        try {
        	page.setPageSize(3);
        	this.setPage(page);
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
			// 获得所有推荐课程
			Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            modelAndView.addObject("studylist", studylist);
			modelAndView.addObject("mapCourseList", mapCourseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    /**
     * 学习记录 ajax
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/course/ajax/his")
    public Object studyHistory(HttpServletRequest request ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            PageEntity page = new PageEntity();
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            modelAndView.addObject("studylist", studylist);
            modelAndView.setViewName(ajax_studyHistory);
        } catch (Exception e) {
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }
    

	/**
	 * 查询我的学习记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/my/study/history/list")
	public String queryMyStudyHistoryByTrxorderDetail(HttpServletRequest request,@ModelAttribute("page")PageEntity page){
		try {
            this.setPage(page);
            this.getPage().setPageSize(50);
			// 获得登录用户ID 
			Long userId = getLoginUserId(request);
			List<List<TrxorderDetailDTO>> trxorderDetailDTOList = trxorderDetailService.queryMyStudyHistoryByTrxorderDetail(userId, this.getPage());
			request.setAttribute("studyHistoryList", trxorderDetailDTOList);
		} catch (Exception e) {
			logger.error("TrxorderDetailController.queryMyStudyHistoryByTrxorderDetail-------error", e);
			return setExceptionRequest(request, e);
		}
    	return studyHistoryList;
    }

    /**
     * 查询我的学生学习记录
     * @param request
     * @return
     */
    @RequestMapping("/uc/teacher/student/study/history/list/{userId}")
    public String queryStudentStudyHistoryByTrxorderDetail(HttpServletRequest request, @ModelAttribute("page")PageEntity page, @PathVariable("userId")Long userId){
        try {
            this.setPage(page);
            this.getPage().setPageSize(10);
            List<List<TrxorderDetailDTO>> trxorderDetailDTOList = trxorderDetailService.queryMyStudyHistoryByTrxorderDetail(userId, this.getPage());
            request.setAttribute("studyHistoryList", trxorderDetailDTOList);
            request.setAttribute("page", this.getPage());
            request.setAttribute("userId", userId);
        } catch (Exception e) {
            logger.error("TrxorderDetailController.queryMyStudyHistoryByTrxorderDetail-------error", e);
            return setExceptionRequest(request, e);
        }
        return myStudentStudyHistoryList;
    }
	
}