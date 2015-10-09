package com.yizhilu.os.edu.controller.teacher;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhilu.os.common.util.FileExportImportUtil;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.service.zoomMeetings.ZoomMeetingService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.teacher.TeacherMajor;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;
import com.yizhilu.os.edu.entity.website.WebsiteTeacher;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.teacher.TeacherMajorService;
import com.yizhilu.os.edu.service.teacher.TeacherSubjectService;
import com.yizhilu.os.edu.service.website.WebsiteTeacherService;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.course.AdminTeacherController
 * @description
 * @author : XuJunBao
 * @Create Date : 2014年9月15日 下午9:53:08
 */
@Controller
@RequestMapping("/admin")
public class AdminTeacherController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminTeacherController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String toTeacherList = getViewPath("/admin/teacher/teacher_list");// 讲师列表页面
	private static final String toUpdateTeacher = getViewPath("/admin/teacher/teacher_update");// 更新页面
	private static final String teacherClasshour = getViewPath("/admin/teacher/teacher_class_hour");// 教师课时页面
	private static final String showRecommendTeacherList = getViewPath("/admin/teacher/teacher_recommend_list");//课程列表(推荐课程)

	private static final String teacherMeetingList = getViewPath("/admin/teacher/teacher_meeting_list"); // 教师的视频直播列表

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private TeacherMajorService teacherMajorService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeacherSubjectService teacherSubjectService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private WebsiteTeacherService websiteTeacherService;
	@Autowired
	private ZoomMeetingService zoomMeetingService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryTeacher")
	public void initBinderQueryTeacher(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTeacher.");
	}
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("teacher")
	public void initBinderTeacher(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("teacher.");
	}
	
	
	/**
	 * 教师列表(推荐教师用)
	 * 
	 * @return
	 */
	@RequestMapping("/teacher/recommendTeacherList")
	public String showCourseListByRecommend(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryTeacher") QueryTeacher queryTeacher) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			// 只查询已审核的教师
			queryTeacher.setStatus(1L);
			// 搜索教师列表
			List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(queryTeacher, page);
			request.setAttribute("teacherList", teacherList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("queryTeacher", queryTeacher);
			List<WebsiteTeacher> websiteTeachers = websiteTeacherService.queryWebsiteTeacherList();
			request.setAttribute("websiteTeachers", websiteTeachers);
		} catch (Exception e) {
			logger.error("CourseController.showCourseListByRecommend", e);
			return setExceptionRequest(request, e);
		}
		return showRecommendTeacherList;
	}

	/**
	 * 查询讲师列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/list")
	public String queryTeacherList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page, @ModelAttribute QueryTeacher queryTeacher) {
		try {
			this.setPage(page);
			// 查詢讲师
			List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(queryTeacher, this.getPage());
			// 把返回的数据放到model中
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("queryTeacher", queryTeacher);
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherList", e);
			return setExceptionRequest(request, e);
		}
		return toTeacherList;
	}

	/**
	 * 根据老师id获得详情（跳转去修改页面）
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/toUpdate/{id}")
	public String toUpdateTeacher(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id)) {
				// 查詢老師
				Teacher teacher = teacherService.getTeacherById(id);
				// 把返回的数据放到model中
				model.addAttribute("teacher", teacher);
				
				// 获得全部科目列表
				Major major = new Major();
				List<Major> majorList = majorService.queryMagorListByPgae(major);
				// 获得当前教师所教授科目
				List<TeacherMajor> teacherMajor = teacherMajorService.queryTeacherMajorByTeacherId(id);
				for (Major major_0 : majorList) {
					for (TeacherMajor teacherMajor2 : teacherMajor) {
						if (teacherMajor2.getMajorId().intValue() == major_0.getId().intValue()) {
							major_0.setCheckSelected("true");
							break;
						}
					}
				}
				model.addAttribute("majorList", majorList);
				
				// 获得全部阶段列表
//				Subject subject = new Subject();
//				subject.setParentId(0L); // 查询一级分类
//				List<Subject> subjectList = subjectService.getSubjectOneList();
				List<TeacherSubject> teacherSubjectList = teacherSubjectService.queryTeacherSubjectByTeacherId(id);
//				for (Subject subject_0 : subjectList) {
//					for (TeacherSubject teacherSubject : teacherSubjectList) {
//						if (teacherSubject.getSubjectId().intValue() == subject_0.getSubjectId().intValue()) {
//							subject_0.setCheckSelected("true");
//							break;
//						}
//					}
//				}
//				model.addAttribute("subjectList", subjectList);

				// 获得全部年级列表
				List<Subject> subjectTwoList = subjectService.getSubjectTwoList();
				for (Subject subject_0 : subjectTwoList) {
					for (TeacherSubject teacherSubject : teacherSubjectList) {
						if (teacherSubject.getSubjectId().intValue() == subject_0.getSubjectId().intValue()) {
							subject_0.setCheckSelected("true");
							break;
						}
					}
				}
				model.addAttribute("subjectTwoList", subjectTwoList);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherById", e);
			return setExceptionRequest(request, e);
		}
		return toUpdateTeacher;
	}

	/**
	 * 更新讲师
	 * 
	 * @param request
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/update")
	public String updateTeacher(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher) {
		try {
			if (ObjectUtils.isNotNull(teacher)) {
//				String isProfessor = request.getParameter("teacherisProfessor");
//				if (teacher.getIsProfessor() == 1) {
//					if (StringUtils.isEmpty(isProfessor) || isProfessor.equals("")) {
//						teacher.setIsProfessor(0);
//					} else {
//						teacher.setIsProfessor(Integer.parseInt(isProfessor));
//					}
//				}
				teacher.setAuditingDate(new Date());
				teacherService.updateTeacherAdmin(teacher);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.updateTeacher", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/teacher/list";
	}

	/**
	 * 刪除讲师
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteTeacher(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id)) {
				Teacher teacher = new Teacher();
				teacher.setId(id);
				teacher.setIsProfessor(1); // 韩教师认证 1为否
				teacher.setStatus(2L);// 2未审核（冻结、删除）
				teacherService.updateTeacherStatus(teacher);// 刪除讲师
				this.setJson(true, "success", null);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.deleteTeacher", e);
			this.setJson(false, "error", null);;
		}
		return json;
	}

	/**
	 * 更改教师状态
	 * @param request
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/update/status")
	@ResponseBody
	public Map<String, Object> updateTeacherStatus(HttpServletRequest request, Teacher teacher){
		try {
			if (ObjectUtils.isNotNull(teacher.getId()) && teacher.getId().intValue() != 0) {
				String message = teacherService.checkTeaCourseNumAndAddress(teacher.getId());
				if (StringUtils.isEmpty(message) || message.equals("")){ // 检查是否可以审核教师
					teacher.setAuditingDate(new Date());
					if (teacher.getStatus() == 2) {
						teacher.setIsProfessor(1); // 韩教师认证 1为否
					}
					teacherService.updateTeacherStatus(teacher);
					this.setJson(true, null, null);
				} else {
					this.setJson(false, message, null);
				}
			} else {
				this.setJson(false, "请求数据错误", null);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.updateTeacherStatus", e);
			this.setJson(false, "系统错误，请稍后重试", null);
		}
		return json;
	}
	
	/**
	 * 查询教师当前一周课时安排
	 * @return
	 */
	@RequestMapping("/teacher/classhour/query/{teacherId}")
	public String queryTeacherWeekClassHour(HttpServletRequest request, @PathVariable("teacherId") Long teacherId){
		try {
			TeacherClassHour teacherClassHour = new TeacherClassHour();
			teacherClassHour.setTeacherId(teacherId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			teacherClassHour.setDateDay(sdf.parse(sdf.format(new Date())));
			List<List<TeacherClassHour>> resultList = teacherClassHourService.getTeacherClassHourList(teacherClassHour);
			request.setAttribute("resultList", resultList);
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherWeekClassHour", e);
			return setExceptionRequest(request, e);
		}
		return teacherClasshour;
	}

	/**
	 * 获得教师的直播视频列表
	 * @param request
	 * @param zoomUserId
	 * @param page
	 * @return
	 */
	@RequestMapping("/teacher/zoom/meetings/querylist/{zoomUserId}")
	public String queryTeacherMeetingList(HttpServletRequest request, @PathVariable("zoomUserId") String zoomUserId, @ModelAttribute("page")PageEntity page){
		try {
			this.setPage(page);
			// 封装查询参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("host_id", zoomUserId); // 用户ID
			map.put("page_size", "10"); // 每页条数
			map.put("page_number", this.getPage().getCurrentPage() + ""); // 当前页数
			Map<String, Object> resultMap = zoomMeetingService.queryMeetingList(map);
			this.getPage().setTotalPageSize((int) resultMap.get("page_count"));
			this.getPage().setTotalResultSize((int) resultMap.get("total_records"));
			this.getPage().setCurrentPage((int) resultMap.get("page_number"));
			this.getPage().setPageSize((int) resultMap.get("page_size"));
			request.setAttribute("page", this.getPage());
			List<Map<String, Object>> meetingsList = (List<Map<String, Object>>)resultMap.get("meetings");
			Calendar calendar = Calendar.getInstance();
			for (Map<String, Object> objectMap : meetingsList) {
				// 设置开始时间为北京时间
				calendar.setTime(sdf.parse((String) objectMap.get("start_time")));
				calendar.add(Calendar.HOUR_OF_DAY, 8);
				objectMap.put("start_time", calendar.getTime());
				// 设置创建时间
				calendar.setTime(sdf.parse((String) objectMap.get("created_at")));
				calendar.add(Calendar.HOUR_OF_DAY, 8);
				objectMap.put("created_at", calendar.getTime());
			}
			request.setAttribute("meetingsList", meetingsList);
			request.setAttribute("zoomUserId", zoomUserId);
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherMeetingList", e);
			return setExceptionRequest(request, e);
		}
		return teacherMeetingList;
	}
	/**
	 * 老师导出
	 */
	@RequestMapping("/teacher/export")
	public void teacherListExport(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("page") PageEntity page, @ModelAttribute QueryTeacher queryTeacher) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/excelfile/teacher");
			//文件名
			String expName = "讲师信息_" + DateUtils.getStringDateShort();
			//表头信息
	        String[] headName = { "ID","名称","手机","学历","专业", "教龄","常用上课地址","韩教授认证","状态","入驻时间"};

	        //拆分为一万条数据每Excel，防止内存使用太大
			page.setPageSize(10000);
			teacherService.queryTeacherListPage(queryTeacher, page);
			System.out.println(page.getTotalResultSize());
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(queryTeacher, page);
				List<List<String>> list=teacherJoint(teacherList);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
	        FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		/**
		 * 讲师信息excel格式拼接
		 * @return
		 */
		public List<List<String>> teacherJoint(List<QueryTeacher> teacherList){
			List<List<String>> list = new ArrayList<List<String>>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < teacherList.size(); i++) {
				List<String> small = new ArrayList<String>();
				small.add(teacherList.get(i).getId() + "");
				small.add(teacherList.get(i).getUserExpand().getShowname());
				small.add(teacherList.get(i).getUserExpand().getMobile());
				String degree="";
				switch (teacherList.get(i).getDegree()){ 
					case "1" : degree="高中以下"; break; 
					case "2" : degree="高中或中专"; break; 
					case "3" : degree="大专"; break; 
					case "4" : degree="本科"; break; 
					case "5" : degree="研究生"; break; 
					case "6" : degree="博士及以上"; break; 
					default :degree="无"; break; 
				} 
				small.add(degree);
				small.add(teacherList.get(i).getProfession());
				small.add(teacherList.get(i).getSeniority().toString()+"年");
				small.add(teacherList.get(i).getCityName()+" "+teacherList.get(i).getDistrictName()+" "+teacherList.get(i).getAddress());
				String isProfessor="";
				switch (teacherList.get(i).getIsProfessor()){ 
					case 2 : isProfessor="已认证"; break; 
					case 3 : isProfessor="高中或中专"; break; 
					default :isProfessor="申请认证"; break; 
				} 
				small.add(isProfessor);
				if (teacherList.get(i).getStatus() == 1) {
					small.add("已审核");
				} else {
					small.add("未审核");
				}
				small.add(format.format(teacherList.get(i).getCreateTime()));
				list.add(small);
			}
			return list;
		}
}
