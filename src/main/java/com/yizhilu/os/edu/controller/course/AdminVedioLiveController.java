package com.yizhilu.os.edu.controller.course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.VedioLive;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.course.VedioLiveService;

/**
 * 直播
 * 
 * @ClassName com.yizhilu.os.edu.controller.course.AdminVedioLiveController
 * @description
 * @author : xiaokun
 * @Create Date : 2014年9月20日 上午10:27:41
 */
@Controller
@RequestMapping("/admin")
public class AdminVedioLiveController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminVedioLiveController.class);

	private static final String toAddLive = getViewPath("/admin/course/add_vedio_live");// 添加直播页面
	private static final String queryliveList = getViewPath("/admin/course/vedio_live_list");// 直播列表
	private static final String toUpdateLive = getViewPath("/admin/course/update_vedio_live");// 讲师详情页
	//private static final String toUpdateTeacher = getViewPath("/admin/course/teacher_update");// 更新页面

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private VedioLiveService vedioLiveService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("vedioLive")
	public void initBinderVedioLive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("vedioLive.");
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * 跳转到添加直播页面
	 */
	@RequestMapping("/live/toAdd")
	public String toAddLive() {
		return toAddLive;
	}

	/**
	 * 后台添加直播
	 */
	@RequestMapping("/live/add")
	public String addLive(HttpServletRequest request, @ModelAttribute("vedioLive") VedioLive vedioLive) {
		try {
			vedioLiveService.addVedioLive(vedioLive);
		} catch (Exception e) {
			logger.error("AdminTeacherController.addTeacher", e);
			setExceptionRequest(request, e);
		}
		return "redirect:/admin/live/list";
	}

	/**
	 * 修改直播
	 */
	@RequestMapping("/live/toUpdate/{id}")
	public String toUpdateLive(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			VedioLive vedioLive = vedioLiveService.getVedioLiveById(id);
			request.setAttribute("vedioLive", vedioLive);
		} catch (Exception e) {
			logger.error("AdminTeacherController.toUpdate", e);
			setExceptionRequest(request, e);
		}
		return toUpdateLive;
	}

	/**
	 * 修改直播
	 */
	@RequestMapping("/live/update")
	public String toUpdateLive(HttpServletRequest request, @ModelAttribute("vedioLive") VedioLive vedioLive) {
		try {
			vedioLiveService.updateVedioLive(vedioLive);
		} catch (Exception e) {
			logger.error("AdminTeacherController.toUpdate", e);
			setExceptionRequest(request, e);
		}
		return "redirect:/admin/live/list";
	}



	/**
	 * 直播列表
	 */
	@RequestMapping("/live/list")
	public String queryliveList(HttpServletRequest request, @ModelAttribute("vedioLive") VedioLive vedioLive,
			@ModelAttribute("page") PageEntity page) {
		try {
			this.setPage(page);
			List<VedioLive> vedioLiveList = vedioLiveService.queryVedioLiveListPage(vedioLive, this.getPage());
			// 把返回的数据放到model中
			request.setAttribute("vedioLiveList", vedioLiveList);
			request.setAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryliveList", e);
			setExceptionRequest(request, e);
		}
		return queryliveList;
	}
	/**
	 * 删除直播
	 */
	@RequestMapping("/live/del/{id}")
	@ResponseBody
	public Object delLive(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			vedioLiveService.deleteVedioLiveById(id);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminTeacherController.delLive", e);
			setExceptionRequest(request, e);
		}
		return json;
	}
}
