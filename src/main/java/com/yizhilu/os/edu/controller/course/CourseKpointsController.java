package com.yizhilu.os.edu.controller.course;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.zoomMeetings.ZoomMeetingService;

/**
 * 班课课节信息
 * @author Administrator
 * 2015-8-12
 */
@Controller
public class CourseKpointsController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseKpointsController.class);
	
	private static String kpoints=getViewPath("/ucenter/teacher/kpoints");//课节列表
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private CourseKpointsService courseKpointsService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ZoomMeetingService zoomMeetingService;
	
	// 绑定变量参数
	@InitBinder("kpoint")
	public void initBinderKpoint(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("kpoint.");
	}
	
	/**
	 * 讲师课程课节
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/front/ajax/teacher/kpoints/{id}")
	public String kpoints(HttpServletRequest request,@PathVariable("id") Long id){
		try {
			List<CourseKpoints> kpoints= courseKpointsService.queryKpointsByCourseId(id);
			request.setAttribute("kpoints", kpoints);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseKpointsController.kpoints--error()",e);
			setExceptionRequest(request, e);
		}
		return kpoints;
	}
	/**
	 * 创建课节
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/addKpoint")
	@ResponseBody
	public Map<String,Object> addKpoint(HttpServletRequest request,@ModelAttribute("kpoint") CourseKpoints kpoint){
		try {
			Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
			kpoint.setTeacherId(teacher.getId());
			//查看开课时间是否冲突
			String returnvalue=checkOpened(kpoint);
			if(returnvalue.equals("ok")){
				kpoint.setTeacherId(teacher.getId());
				//将一对一开课列表设置为不可开
				teacherClassHourService.classCourseHourAdd(kpoint.getBeginTime(), kpoint.getEndTime(), teacher.getId());
				// 创建班课课时直播视频
				Map<String, String> map = new HashMap<String, String>();
				map.put("host_id", teacher.getZoomMeetingUserId()); // zoom视频UserID
				map.put("topic", kpoint.getName()); // 直播视频课程名称
				map.put("type", "2"); // 会议类型。1指即时会议。2指计划会议。3意味着经常性会议。
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(kpoint.getBeginTime());// 北京时间早于UTC时间8小时
				calendar.add(Calendar.HOUR_OF_DAY, -8);
				map.put("start_time", sdf.format(calendar.getTime())); // 会议开始时间--UTC时间(世界协调时间)
				long timeInterval = (kpoint.getEndTime().getTime() - kpoint.getBeginTime().getTime())/(1000*60);
				map.put("duration", timeInterval + ""); // 持续时间
				Map<String, Object> resultMap = zoomMeetingService.createMeeting(map);
				kpoint.setMeetingId((String)resultMap.get("id"));
				kpoint.setMeetingStatus(0);
				kpoint.setMeetingUrl((String)resultMap.get("start_url"));
				courseKpointsService.createCourseKpoints(kpoint);
				this.setJson(true, "创建成功", null);
			}else{
				this.setJson(false, returnvalue, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseKpointsController.addKpoint--error()",e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}
	/**
	 * 查看开课时间是否冲突
	 * @param kpoint
	 * @return
	 */
	private String checkOpened(CourseKpoints kpoint) throws Exception{
		//时间段无效
		if(!kpoint.getEndTime().after(kpoint.getBeginTime())){
			return "timeError";
		}
		//查询是否与其他班课时间冲突
		int count=courseKpointsService.queryConflictTime(kpoint);
		if(count>0){
			return "classError";
		}
		//查询是否与一对一开课冲突
		List<TeacherClassHour> classList= teacherClassHourService.getTeacherClassHourByCondition(kpoint.getBeginTime(),kpoint.getEndTime(),kpoint.getTeacherId());
		if(ObjectUtils.isNotNull(classList)){
			return "oneToOneError";
		}
		return "ok";
	}
	/**
	 * 修改课节
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/teacher/updateKpoint")
	@ResponseBody
	public Map<String,Object> updateKpoint(HttpServletRequest request,@ModelAttribute("kpoint") CourseKpoints kpoint){
		try {
			Teacher teacher=teacherService.getTeacherByUserId(getLoginUserId(request));
			kpoint.setTeacherId(teacher.getId());
			//查看开课时间是否冲突
			String returnvalue=checkOpened(kpoint);
			if(returnvalue.equals("ok")){
				//将一对一开课列表设置为不可开
				teacherClassHourService.classCourseHourAdd(kpoint.getBeginTime(),kpoint.getEndTime(),teacher.getId());
				// 修改班课课时直播视频
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", kpoint.getMeetingId());
				map.put("host_id", teacher.getZoomMeetingUserId()); // zoom视频UserID
				map.put("topic", kpoint.getName()); // 直播视频课程名称
				map.put("type", "2"); // 会议类型。1指即时会议。2指计划会议。3意味着经常性会议。
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(kpoint.getBeginTime());// 北京时间早于UTC时间8小时
				calendar.add(Calendar.HOUR_OF_DAY, -8);
				map.put("start_time", sdf.format(calendar.getTime())); // 会议开始时间--UTC时间(世界协调时间)
				long timeInterval = (kpoint.getEndTime().getTime() - kpoint.getBeginTime().getTime())/(1000*60);
				map.put("duration", timeInterval + ""); // 持续时间
				zoomMeetingService.updateMeeting(map);
				courseKpointsService.updateKpoint(kpoint);
				this.setJson(true, "修改成功", null);
			}else{
				this.setJson(false, returnvalue, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseKpointsController.addKpoint--error()",e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}
	/**
	 * 删除课节
	 * @param request
	 * @param kpoint
	 * @return
	 */
	@RequestMapping("/uc/teacher/delKpoint")
	public String delKpoint(HttpServletRequest request){
		String courseId=request.getParameter("courseId");
		try {
			Teacher teacher = teacherService.getTeacherByUserId(getLoginUserId(request));
			String kpointId=request.getParameter("kpointId");
			if(StringUtils.isNotEmpty(courseId)&&StringUtils.isNotEmpty(kpointId)){
				CourseKpoints kpoint = courseKpointsService.queryKpointsById(Long.parseLong(kpointId));
				// 修改班课课时直播视频
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", kpoint.getMeetingId());
				map.put("host_id", teacher.getZoomMeetingUserId()); // zoom视频UserID
				zoomMeetingService.deleteMeeting(map);
				courseKpointsService.delKpointById(Long.parseLong(kpointId));
				//更新课程表的课节数
				courseService.updateLessionnum(Long.parseLong(courseId));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CourseKpointsController.delKpoints--error()",e);
			setExceptionRequest(request, e);
		}
		return "redirect:/uc/teacher/toUpdateClassCourse/"+courseId+"#kpointsList";
	}
}