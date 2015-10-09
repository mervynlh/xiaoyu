package com.yizhilu.os.edu.controller.order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.yizhilu.os.edu.entity.user.UserExpandDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetailCourse;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderExpand;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxdetailOptRecordService;
import com.yizhilu.os.edu.service.order.TrxorderDetailAssessService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * 订单流水Controller
 * 
 * @author dingkai
 * @date 2015年8月29日
 */
@Controller
public class TrxorderDetailController extends EduBaseController {

	private Logger logger = LoggerFactory.getLogger(TrxorderDetailController.class);
	
	private static final String ajax_course_table = getViewPath("/ucenter/ajax_course_table");// 课表
	private static final String student_orderdetails = getViewPath("/ucenter/student/student_orderdetails");// 学生订单详情

	private static final String teacher_orderdetails = getViewPath("/ucenter/teacher/teacher_student_orderdetails");// 教师查看订单详情
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private TrxorderDetailAssessService trxorderDetailEvalutionService;

	// 绑定属性 封装参数
	@InitBinder("trxorderDetail")
	public void initQueryTrxorder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("trxorderDetail.");
	}

	/**
	 * 修改TrxorderDetail
	 * 
	 * @param trxorderDetail
	 *            要修改的TrxorderDetail
	 */
	public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
		trxorderDetailService.updateTrxorderDetail(trxorderDetail);
	}

	/**
	 * 根据订单id获取订单详情
	 * 
	 * @param model
	 * @param detailId
	 * @return
	 */
	@RequestMapping("/uc/order/getTrxorderDetail/{detailId}")
	public String getTrxorderDetail(Model model, @PathVariable("detailId") Long detailId) {
		try {
			// 获取订单详情
			TrxorderExpand trxorderExpand = this.trxorderService.getTrxorderExpandById(detailId);
			model.addAttribute("trxorder", trxorderExpand);
			// 获取教师详情
			Teacher teacher = this.teacherService.getTeacherById(trxorderExpand.getTeacherId());
			model.addAttribute("teacher", teacher);
			if (ObjectUtils.isNotNull(trxorderExpand)) {
				// 订单id
				Long trxorderId = trxorderExpand.getId();
				// 获取流水信息
				List<TrxorderDetail> trxorderDetailList = this.trxorderDetailService
						.getTrxorderDetailListByTrxorderId(trxorderId);
				model.addAttribute("trxorderDetailList", trxorderDetailList);
			}
		} catch (Exception e) {
			logger.error("TrxorderController.getTrxorderDetail", e);
		}
		return student_orderdetails;
	}

	/**
	 * 教师根据订单id获取订单详情
	 *
	 * @param model
	 * @param detailId
	 * @return
	 */
	@RequestMapping("/uc/order/getTrxorderDetail/teacher/{detailId}")
	public String teacherLookTrxorderDetail(Model model, @PathVariable("detailId") Long detailId) {
		try {
			// 获取订单详情
			TrxorderExpand trxorderExpand = this.trxorderService.getTrxorderExpandById(detailId);
			model.addAttribute("trxorder", trxorderExpand);
			// 获取用户详情
			UserExpandDto userExpand = userExpandService.getUserExpandByUid(trxorderExpand.getUserId());
			model.addAttribute("userExpand", userExpand);
			if (ObjectUtils.isNotNull(trxorderExpand)) {
				// 订单id
				Long trxorderId = trxorderExpand.getId();
				// 获取流水信息
				List<TrxorderDetail> trxorderDetailList = this.trxorderDetailService
						.getTrxorderDetailListByTrxorderId(trxorderId);
				model.addAttribute("trxorderDetailList", trxorderDetailList);
			}
		} catch (Exception e) {
			logger.error("TrxorderController.teacherLookTrxorderDetail", e);
		}
		return teacher_orderdetails;
	}
	
	/**
	 * 根据条件课表课程
	 * 
	 * @param detail
	 *            流水实体
	 * @param request
	 * @param page
	 *            分页实体
	 * @return
	 */
	@RequestMapping("/ajax/getCourseTable/{status}")
	public String getTrxorderDetailList(@PathVariable("status") Long status, Model model, HttpServletRequest request,
			@ModelAttribute("page") PageEntity page) {
		try {
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			// 用户id
			Long userId = getLoginUserId(request);
			User user = this.userService.getUserById(userId);
			// 课程状态
			trxorderDetail.setStatus(status);
			// 获得全部教授阶段
			List<Subject> subjectList = subjectService.getSubjectOneList();
			model.addAttribute("subjectList", subjectList);
			Map<String, Object> map = null;
			// 角色区别
			if (user.getUserType() == 0) {// 学生
				trxorderDetail.setUserId(userId);
				// 获取课程列表
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService
						.getTrxorderDetailByStatusStu(trxorderDetail, this.getPage());
				model.addAttribute("detailList", detailList);
				map = this.trxorderDetailService.getTrxorderDetailCountById(userId, null);
			} else if (user.getUserType() == 1) {// 教师
				Teacher teacher = this.teacherService.getTeacherByUserId(userId);
				trxorderDetail.setTeacherId(teacher.getId());
				// 获取课程列表
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService
						.getTrxorderDetailByStatusTea(trxorderDetail, this.getPage());
				model.addAttribute("detailList", detailList);
				map = this.trxorderDetailService.getTrxorderDetailCountById(null, teacher.getId());
			}
			model.addAttribute("userType", user.getUserType());
			// 分页
			page.setPageSize(5);
			this.setPage(page);
			model.addAttribute("page", this.getPage());

			model.addAttribute("detailStatus", map);
			model.addAttribute("status", status);
		} catch (Exception e) {
			logger.error("TrxorderDetailController.getTrxorderDetailList", e);
		}
		return ajax_course_table;
	}

	/**
	 * 根据日期获取当月有课的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@RequestMapping("/getCourseByMonth")
	@ResponseBody
	public Map<String, Object> getCourseByMonth(HttpServletRequest request, @RequestParam("year") int year,
			@RequestParam("month") int month) {
		try {
			Long userId = getLoginUserId(request);
			User user = this.userService.getUserById(userId);
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, 1);
			int lastDate = calendar.getActualMaximum(Calendar.DATE);
			Date startTime = new GregorianCalendar(year, month - 1, 1, 0, 0, 0).getTime();
			Date endTime = new GregorianCalendar(year, month - 1, lastDate, 23, 59, 59).getTime();
			Set<Integer> dates = null;
			if (user.getUserType() == 0) {
				dates = this.trxorderDetailService.getTrxorderDetailCountByTimeAndId(userId, null, startTime, endTime,
						2L);
			} else if (user.getUserType() == 1) {
				Teacher teacher = this.teacherService.getTeacherByUserId(userId);
				dates = this.trxorderDetailService.getTrxorderDetailCountByTimeAndId(null, teacher.getId(), startTime,
						endTime, 2L);
			}
			this.setJson(true, "success", dates.toString());
		} catch (Exception e) {
			logger.error("TrxorderDetailController.getTrxorderDetailList", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 根据日期查询当天的记录
	 * 
	 * @param model
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	@RequestMapping("/ajax/getCourseByDate")
	public String getDetailList(HttpServletRequest request, Model model, @RequestParam("year") int year,
			@RequestParam("month") int month, @RequestParam("date") int date) {
		try {
			Long userId = getLoginUserId(request);
			User user = this.userService.getUserById(userId);
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, 1);
			Date startTime = new GregorianCalendar(year, month - 1, date, 0, 0, 0).getTime();
			Date endTime = new GregorianCalendar(year, month - 1, date, 23, 59, 59).getTime();

			TrxorderDetail trxorderDetail = new TrxorderDetail();
			trxorderDetail.setStartTime(startTime);
			trxorderDetail.setEndTime(endTime);
			trxorderDetail.setStatus(2L);
			if (user.getUserType() == 0) {
				trxorderDetail.setUserId(userId);
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService.getTrxorderDetailByStatusStu(trxorderDetail,
						this.getPage());
				model.addAttribute("detailList", detailList);
			} else if (user.getUserType() == 1) {
				Teacher teacher = this.teacherService.getTeacherByUserId(userId);
				trxorderDetail.setTeacherId(teacher.getId());
				List<QueryTrxorderDetailCourse> detailList = trxorderDetailService.getTrxorderDetailByStatusTea(trxorderDetail,
						this.getPage());
				model.addAttribute("detailList", detailList);
			}
			model.addAttribute("userType", user.getUserType());
			
			model.addAttribute("status", 0);
		} catch (Exception e) {
			logger.error("TrxorderDetailController.getDetailList", e);
		}
		return ajax_course_table;
	}

	/**
	 * 取消约课
	 * 
	 * @param request
	 * @param id
	 *            订单流水id
	 * @return
	 */
	@RequestMapping("/trxorder/updateStatusCancelClass")
	@ResponseBody
	public Map<String, Object> updateStatusCancelClass(HttpServletRequest request, @RequestParam("id") Long id,
			@RequestParam("teacherId") Long teacherId) {
		try {
			this.trxorderDetailService.updateStatusCancelClass(id, teacherId);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("TrxorderDetailController.updateStatusCancelClass", e);
		}
		return json;
	}

	/**
	 * 申请课时退款
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/trxorder/detail/ajax/refund/{id}")
	@ResponseBody
	public Map<String, Object> trxorderDetailRefund(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id) && id.intValue() != 0) {
				TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(id);
				if (ObjectUtils.isNotNull(trxorderDetail)) {
					// 退课原因
					String description = request.getParameter("description");
					// 获取用户类型
					UserExpandDto user = userExpandService.getUserExpandByUids(getLoginUserId(request));
					// 更新订单流水状态未审核中
					trxorderDetailService.updateTrxorderDetailStatusAudit(trxorderDetail,description,new Long(user.getUserType()));
					this.setJson(true, "退课申请成功,请等待审核", null);
				}
			} else {
				this.setJson(false, "退课申请失败", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.trxorderDetailRefund", e);
			this.setJson(false, "系统异常,请稍后重试", null);
		}
		return json;
	}
	
	/**
	 * 课时付款
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/trxorder/detail/ajax/pay/{id}")
	@ResponseBody
	public Map<String,Object> trxorderDetailPay(HttpServletRequest request, @PathVariable("id") Long id){
		try{
			if(ObjectUtils.isNotNull(id) && id.intValue() != 0){
				TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(id);
				if (ObjectUtils.isNotNull(trxorderDetail)) {
					trxorderDetailService.updateTrxorderDetailStatusPay(trxorderDetail);
					this.setJson(true, "支付成功", null);
					
				}else {
					this.setJson(false, "支付失败", null);
				}
			}
		}catch(Exception e){
			logger.error("TrxorderDetailController.trxorderDetailPay", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}
	
	/**
	 * 课时评价
	 * 
	 * @param detailId
	 *            流水id
	 * @param content
	 *            内容
	 * @param teacherId
	 *            教师id
	 * @return
	 */
	@RequestMapping("/ajax/assess/assessCourse/{detailId}/{teacherId}")
	@ResponseBody
	public Map<String, Object> assessCourse(HttpServletRequest request, @PathVariable("detailId") Long detailId,
			@PathVariable("teacherId") Long teacherId) {
		try {
			String content = request.getParameter("content");
			if (StringUtils.isEmpty(content)) {// 评价内容为空
				this.setJson(false, "内容不能为空", null);
			} else {// 评价内容不为空
				this.trxorderDetailEvalutionService.addDetailAssess(detailId, content, teacherId,getLoginUserId(request));
				this.setJson(true, "评价成功", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.assessCourse", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}
	
	/**
	 * 课时小结
	 * @param request
	 * @param id 流水id
	 * @param content 小结内容
	 * @return
	 */
	@RequestMapping("/uc/ajax/course/summary")
	@ResponseBody
	public Map<String, Object> courseSummary(HttpServletRequest request, @RequestParam("id") Long id,
			@RequestParam("content") String content) {
		try {
			Long userId = getLoginUserId(request);
			User user = userService.getUserById(userId);
			if (StringUtils.isEmpty(content)) {// 评价内容为空
				this.setJson(false, "内容不能为空", null);
			} else {// 评价内容不为空
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				trxorderDetail.setId(id);
				if(user.getUserType()==0){//学生
					trxorderDetail.setUserSummary(content);
				}else if(user.getUserType()==1){//老师
					trxorderDetail.setTeacherSummary(content);
				}
				this.trxorderDetailService.updateTrxorderDetailSummaryById(trxorderDetail);
				this.setJson(true, "保存成功", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailController.evalutionCourse", e);
			this.setJson(false, "系统异常，请稍后重试", null);
		}
		return json;
	}
}