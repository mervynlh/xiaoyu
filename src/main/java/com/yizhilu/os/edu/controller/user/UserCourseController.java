package com.yizhilu.os.edu.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;

/**
 * @ClassName com.yizhilu.os.edu.controller.user.UserCourseController
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-8-4 下午5:01:24
 */
@Controller
public class UserCourseController extends EduBaseController {

	private static Logger logger = LoggerFactory.getLogger(UserCourseController.class);

	private static String my_schedule = getViewPath("/ucenter/my_schedule");// 我的课表

	@Autowired
	TrxorderDetailService trxorderDetailService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private TeacherService teacherService;
	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryTrxorderDetail")
	public void initBinderQueryTrxorderDetail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTrxorderDetail.");
	}

	/**
	 * 我的课程
	 * 
	 * @return
	 */
	@RequestMapping("/course")
	public String course(Model model, HttpServletRequest request) {
		try {
			List<TrxorderDetail> list = trxorderDetailService.getTrxorderDetailListBuy(getLoginUserId(request));
			model.addAttribute("courselist", list);
		} catch (Exception e) {
			logger.error("course", e);
		}

		return getViewPath("/ucenter/course");
	}

	/**
	 * 我的课表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/mySchedule")
	public ModelAndView mySchedule(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView model = new ModelAndView(my_schedule);
		try {
			this.setPage(page);
			this.getPage().setPageSize(12);
			QueryTrxorderDetail query = new QueryTrxorderDetail();
			query.setStatus(2l);
			query.setStartTime(DateUtils.strToDate("2015-08-15"));
			query.setUserId(this.getLoginUserId(request));
			query.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
			List<TrxorderDetail> list = trxorderDetailService.queryMySchedulePage(query, page);
			model.addObject("list", list);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TrxorderDetailController.mySchedule()----error", e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}

	/**
	 * 学生取消约课
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/cancelOrderClass/{id}")
	public ModelAndView cancelOrderClass(HttpServletRequest request, @PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("redirect:/uc/mySchedule");
		try {
			if (id > 0) {
				trxorderDetailService.updateStatusCancelClass(id, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TrxorderDetailController.cancelOrderClass---error", e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}

	/**
	 * 学生修改时间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/ajax/studentUpdateTimeAgain")
	@ResponseBody
	public Map<String, Object> studentUpdateTimeAgain(HttpServletRequest request,
			@ModelAttribute("queryTrxorderDetail") QueryTrxorderDetail query) {
		try {
			if (ObjectUtils.isNotNull(query)) {
				Date nowDate = new Date();
				if (query.getStartTime().after(nowDate) && query.getEndTime().after(nowDate)) {
					QueryTrxorderDetail trxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(query.getId());
					query.setUserId(getLoginUserId(request));
					List<TrxorderDetail> conflictTime = trxorderDetailService.queryTimeStudentConflict(query);
					Teacher teacher = teacherService.getTeacherById(trxorderDetail.getTeacherId());
					if (ObjectUtils.isNotNull(conflictTime) || conflictTime.size() > 0) {
						this.setJson(false, "该时间与当前时间或其他时间冲突", null);
					} else {
						// 修改约课时间
						trxorderDetailService.updateTimeStuById(query.getId(),
								DateUtils.dateToStr(query.getStartTime(), "yyyy-MM-dd HH:mm:ss"),
								DateUtils.dateToStr(query.getEndTime(), "yyyy-MM-dd HH:mm:ss"),trxorderDetail.getUserId(), teacher.getId());
						this.setJson(true, "预约成功，请等待老师的确认", null);
						
					}
				} else {
					this.setJson(false, "请选择当前以后的时间", null);
				}
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.studentUpdateOrderTime---error", e);
			this.setJson(false, "系统繁忙，请稍后再试", null);
		}
		return json;
	}

	/**
	 * 老师修改时间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/ajax/teacherUpdateTimeAgain")
	@ResponseBody
	public Map<String, Object> teacherUpdateTimeAgain(HttpServletRequest request,
			@ModelAttribute("queryTrxorderDetail") QueryTrxorderDetail query) {
		try {
			if (ObjectUtils.isNotNull(query)) {
				Date nowDate = new Date();
				if (query.getStartTime().after(nowDate) && query.getEndTime().after(nowDate)) {
					QueryTrxorderDetail trxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(query.getId());
					query.setUserId(getLoginUserId(request));
					List<TrxorderDetail> conflictTime = trxorderDetailService.queryTimeStudentConflict(query);
					if (ObjectUtils.isNotNull(conflictTime) || conflictTime.size() > 0) {
						this.setJson(false, "该时间与其他时间冲突，请重新选择", null);
					} else {
						Teacher teacher = teacherService.getTeacherById(trxorderDetail.getTeacherId());
						// 修改约课时间
						trxorderDetailService.updateTimeTeaById(query.getId(),
								DateUtils.dateToStr(query.getStartTime(), "yyyy-MM-dd HH:mm:ss"),
								DateUtils.dateToStr(query.getEndTime(), "yyyy-MM-dd HH:mm:ss"), trxorderDetail.getUserId(),teacher.getId());
						this.setJson(true, "修改成功，请等待学生的确认", null);
					}
				}else {
					this.setJson(false, "请选择当前以后的时间", null);
				}
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.teacherUpdateTimeAgain---error", e);
			this.setJson(false, "系统繁忙，请稍后再试", null);
		}
		return json;
	}

	/**
	 * 学生确认约课
	 * 
	 * @param teacherId
	 *            教师id
	 * @param id
	 *            流水id
	 * @return
	 */
	@RequestMapping("/trxorder/confirmCourseStudent")
	@ResponseBody
	public Map<String, Object> confirmCourseStudent(HttpServletRequest request, @RequestParam("id") Long id) {
		try {
			// 确认约课前查询上课时间是否跟其他冲突
			QueryTrxorderDetail trxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(id);
			List<TrxorderDetail> confirmTime = trxorderDetailService.queryTimeTeacherConflict(trxorderDetail);
			if (ObjectUtils.isNotNull(confirmTime) || confirmTime.size() > 0) {
				this.setJson(false, "该时间与其他预约时间冲突，请联系协商后在试", null);
			} else {
				
				this.trxorderDetailService.updateConfirmCourseStudent(id, trxorderDetail.getUserId(),trxorderDetail.getTeacherId());
				this.setJson(true, "时间确认成功！", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.confirmCourseStudent", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 老师确认约课
	 * 
	 * @param userId
	 *            用户id
	 * @param id
	 *            流水id
	 * @return
	 */
	@RequestMapping("/trxorder/confirmCourseTeacher")
	@ResponseBody
	public Map<String, Object> confirmCourseTeacher(@RequestParam("id") Long id) {
		try {
			// 确认约课前查询上课时间是否跟其他冲突
			QueryTrxorderDetail trxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(id);
			List<TrxorderDetail> conflictTime = trxorderDetailService.queryTimeTeacherConflict(trxorderDetail);
			if (ObjectUtils.isNotNull(conflictTime) || conflictTime.size() > 0) {
				this.setJson(false, "该时间与其他预约时间冲突，请联系协商后在试", null);
			} else {
				
				this.trxorderDetailService.updateConfirmCourseTeacher(id, trxorderDetail.getUserId(),trxorderDetail.getTeacherId());
				this.setJson(true, "时间确认成功！", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.confirmCourseTeacher", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
}
