package com.yizhilu.os.edu.controller.course;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.VedioLive;
import com.yizhilu.os.edu.service.course.VedioLiveService;

/**
 * VedioLive管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class VedioLiveController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	// 直播列表
	private static final String liveList = getViewPath("/live/live_list");
	// 直播详情
	private static final String liveInfo = getViewPath("/live/live_info");

	@Autowired
	private VedioLiveService vedioLiveService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("vedioLive")
	public void initBinderVedioLive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("vedioLive.");
	}

	/**
	 * 直播列表
	 */
	@RequestMapping("/uc/live")
	public String liveList(HttpServletRequest request, @ModelAttribute("vedioLive") VedioLive vedioLive,
			@ModelAttribute("page") PageEntity page) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			// 搜索课程列表
			List<VedioLive> vedioLiveList = vedioLiveService.queryVedioLiveListPage(vedioLive, page);
			request.setAttribute("page", this.getPage());
			request.setAttribute("vedioLiveList", vedioLiveList);
			request.setAttribute("date", new Date());
		} catch (Exception e) {
			logger.error("VedioLiveController.liveList", e);
			return setExceptionRequest(request, e);
		}
		return liveList;
	}

	/**
	 * 直播详情
	 */
	@RequestMapping("/live/info/{id}")
	public String liveInfo(HttpServletRequest request, @PathVariable Long id,RedirectAttributes redirectAttributes) {
		try {
			// 直播详情
			VedioLive vedioLive = vedioLiveService.getVedioLiveById(id);
			if(ObjectUtils.isNull(vedioLive)){
				redirectAttributes.addAttribute("msg","对不起该直播已经下架或者删除");
		        return "redirect:/front/success";
			}
			request.setAttribute("vedioLive", vedioLive);
		} catch (Exception e) {
			logger.error("VedioLiveController.liveInfo", e);
			return setExceptionRequest(request, e);
		}
		return liveInfo;
	}

}