package com.yizhilu.os.edu.controller.website;


import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.website.WebsiteTeacher;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetail;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetailDTO;
import com.yizhilu.os.edu.service.website.WebsiteTeacherDetailService;
import com.yizhilu.os.edu.service.website.WebsiteTeacherService;

/**
 * 推荐模块课程分类
 * 
 * @ClassName com.yizhilu.os.edu.controller.website.AdminWebsiteTeacherController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月7日 上午9:47:26
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteTeacherController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteTeacherController.class);

	@Autowired
	private WebsiteTeacherService websiteTeacherService;
	@Autowired
	private WebsiteTeacherDetailService websiteTeacherDetailService;

	private static final String getWebsiteTeacherList = getViewPath("/admin/website/teacher/websiteTeacher_list");
	private static final String updateWebsiteTeacher = getViewPath("/admin/website/teacher/websiteTeacher_update");
	private static final String addWebsiteTeacher = getViewPath("/admin/website/teacher/websiteTeacher_add");
	private static final String getWebsiteTeacherDetailList = getViewPath("/admin/website/teacher/websiteTeacherDetail_list");
	private static final String updateWebsiteTeacherDetail = getViewPath("/admin/website/teacher/websiteTeacherDetail_update");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteTeacher")
	public void initBinderWebsiteTeacher(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteTeacher.");
	}
	@InitBinder("websiteTeacherDetail")
	public void initBinderWebsiteTeacherDetail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteTeacherDetail.");
	}
	@InitBinder("websiteTeacherDetailDTO")
	public void initBinderWebsiteTeacherDetailDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteTeacherDetailDTO.");
	}
	/**
	 * 添加推荐教师分类
	 * 
	 * @param request
	 * @param websiteTeacher
	 * @return
	 */
	@RequestMapping("/website/addTeacher")
	public String addWebsiteTeacher(HttpServletRequest request,WebsiteTeacher websiteTeacher) {
		try {
			if (ObjectUtils.isNotNull(websiteTeacher)) {
				websiteTeacherService.addWebsiteTeacher(websiteTeacher);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.addWebsiteNavigates--添加推荐教师分类出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteTeacherPage";
	}

	/**
	 * 查询推荐教师分类
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/websiteTeacherPage")
	public ModelAndView getWebsiteTeacherList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(getWebsiteTeacherList);
		try {
			// 查询模块课程分类
			List<WebsiteTeacher> websiteTeacherList = websiteTeacherService.queryWebsiteTeacherList();
			// 把websiteTeacherList放到modelAndView
			modelAndView.addObject("websiteTeacherList", websiteTeacherList);
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.getWebsiteTeacherList--查询推荐教师分类列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 删除课程分类
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/delWebsiteTeacherById/{id}")
	@ResponseBody
	public Map<String, Object> delWebsiteTeacherById(HttpServletRequest request, @PathVariable Long id) {
		try {
			if (StringUtils.isNotEmpty(id.toString())) {
				// 删除课程分类
				websiteTeacherService.deleteWebsiteTeacherDetailById(id);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.deleteWebsiteTeacherById--删除推荐教师分类出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 添加课程分类跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doAddWebsiteTeacher")
	public ModelAndView getWebsiteTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addWebsiteTeacher);
		return modelAndView;
	}
	/**
	 * 更新课程分类跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doUpdateWebsiteTeacher/{id}")
	public ModelAndView doUpdateWebsiteTeacher(HttpServletRequest request, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteTeacher);
		try {
			// 获得websiteTeacher
			WebsiteTeacher websiteTeacher = websiteTeacherService.queryWebsiteTeacherById(id);
			modelAndView.addObject("websiteTeacher", websiteTeacher);
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.doUpdateWebsiteTeacher--更新推荐教师分类跳转出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 修改推荐教师分类
	 * 
	 * @param request
	 * @param websiTeacherse
	 * @return
	 */
	@RequestMapping("/website/updateWebsiteTeacher")
	public String updateWebsiteTeacher(HttpServletRequest request, WebsiteTeacher websiteTeacher) {
		try {
			if (ObjectUtils.isNotNull(websiteTeacher)) {
				websiteTeacherService.updateWebsiteTeacherById(websiteTeacher);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiTeacherseController.updateWebsiteTeacher--修改推荐教师分类出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteTeacherPage";
	}
	
	/**
	 * 添加推荐教师
	 * 
	 * @param request
	 * @param WebsiteTeacherDetail
	 * @return
	 */
	@RequestMapping("/website/addTeacherDetail")
	@ResponseBody
	public Map<String,Object> addWebsiteTeacher(HttpServletRequest request) {
		try {
			String ids=request.getParameter("ids");
			Long id=Long.parseLong(request.getParameter("recommendId"));
			int exitSize=websiteTeacherDetailService.getWebsiteTeacherDetails(id).size();
			WebsiteTeacher websiteTeacher=websiteTeacherService.queryWebsiteTeacherById(id);
			if (ObjectUtils.isNotNull(ids)){//添加推荐课程
				String[] idsArry=ids.split(",");
				if (websiteTeacher.getTeacherNum()>=(idsArry.length+exitSize)) {//未超过该分类教师上限
					List<WebsiteTeacherDetail> websiteTeacherDetails=new ArrayList<WebsiteTeacherDetail>();
					for(int i=0;i<idsArry.length;i++){
						WebsiteTeacherDetail websiteTeacherDetail=new WebsiteTeacherDetail();
						websiteTeacherDetail.setTeacherId(Long.parseLong(idsArry[i]));//课程id
						websiteTeacherDetail.setOrderNum(0);//排序
						websiteTeacherDetail.setRecommendId(id);//分类id
						websiteTeacherDetails.add(websiteTeacherDetail);
					}
					websiteTeacherDetailService.addWebsiteTeacherDetail(websiteTeacherDetails);
					this.setJson(true, "true", null);
				}else{
					this.setJson(false, "than", websiteTeacher);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.addWebsiteNavigates--添加推荐教师分类出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 查询推荐教师列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/websiteTeacherDetailPage")
	public ModelAndView getWebsiteTeacherDetailList(HttpServletRequest request,WebsiteTeacherDetailDTO websiteTeacherDetailDTO,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getWebsiteTeacherDetailList);
		try {
			// 查询推荐课程
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<WebsiteTeacherDetailDTO> websiteTeacherDetailDTOList = websiteTeacherDetailService.queryWebsiteTeacherDetailList(websiteTeacherDetailDTO, page);
			modelAndView.addObject("websiteTeacherDetailDTOList", websiteTeacherDetailDTOList);
			List<WebsiteTeacher> websiteTeachers=websiteTeacherService.queryWebsiteTeacherList();
			request.setAttribute("websiteTeachers", websiteTeachers);
			modelAndView.addObject("page",this.getPage());
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.getWebsiteTeacherDetailList--查询推荐教师列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 删除推荐教师
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/delWebsiteTeacherDetailById/{id}")
	@ResponseBody
	public Map<String, Object> deleteWebsiteTeacherDetailById(HttpServletRequest request, @PathVariable Long id) {
		try {
			if (StringUtils.isNotEmpty(id.toString())) {
				// 删除课程分类
				websiteTeacherDetailService.deleteWebsiteTeacherDetail(id);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.delWebsiteTeacherDetailById--删除教师分类出错", e);
			this.setJson(false, "false", null);
		}
		return json;
	}

	/**
	 * 更新推荐教师跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doUpdateWebsiteTeacherDetail/{id}")
	public ModelAndView doUpdateWebsiteTeacherDetail(HttpServletRequest request, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteTeacherDetail);
		try {
			// 获得websiteTeacher
			WebsiteTeacherDetailDTO websiteTeacherDetailDTO = websiteTeacherDetailService.queryWebsiteTeacherDetailById(id);
			modelAndView.addObject("websiteTeacherDetailDTO", websiteTeacherDetailDTO);
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.doUpdateWebsiteTeacherDetail--更新推荐教师跳转出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 修改推荐教师
	 * 
	 * @param request
	 * @param websiteTeacher
	 * @return
	 */
	@RequestMapping("/website/updateWebsiteTeacherDetail")
	public String updateWebsiteTeacherDetail(HttpServletRequest request, WebsiteTeacherDetail websiteTeacherDetail) {
		try {
			if (ObjectUtils.isNotNull(websiteTeacherDetail)) {
				websiteTeacherDetailService.updateWebsiteTeacherDetail(websiteTeacherDetail);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.updateWebsiteTeacher--修改推荐教师出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteTeacherDetailPage";
	}
}
