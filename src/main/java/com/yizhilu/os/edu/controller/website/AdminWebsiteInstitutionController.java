package com.yizhilu.os.edu.controller.website;


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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.website.WebsiteInstitution;
import com.yizhilu.os.edu.entity.website.WebsiteInstitutionDTO;
import com.yizhilu.os.edu.service.website.WebsiteInstitutionService;

/**
 * 推荐机构模块
 * @author dingkai
 * @date 2015年9月22日
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteInstitutionController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteInstitutionController.class);

	@Autowired
	private WebsiteInstitutionService websiteInstitutionService;


	private static final String updateWebsiteInstitution = getViewPath("/admin/website/institution/websiteInstitution_update");
	private static final String getWebsiteInstitutionList = getViewPath("/admin/website/institution/websiteInstitution_list");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteInstitutionDTO")
	public void initBinderWebsiteInstitutionDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteInstitutionDTO.");
	}
	@InitBinder("websiteInstitution")
	public void initBinderWebsiteInstitution(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteInstitution.");
	}
	
	/**
	 * 批量添加推荐机构 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/addWebsiteInstitution")
	@ResponseBody
	public Map<String,Object> addWebsiteInstitution(HttpServletRequest request){
		try {
			String ids = request.getParameter("ids");
			this.websiteInstitutionService.addWebsiteInstitution(ids);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminWebsiteInstitutionController.addWebsiteInstitution", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 查询推荐机构
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/website/getWebsiteInstitutionById/{id}")
	public String getWebsiteInstitutionById(@PathVariable("id") Long id,Model model){
		try{
			if(ObjectUtils.isNotNull(id)){
				WebsiteInstitutionDTO websiteInstitutionDTO = this.websiteInstitutionService.getWebsiteInstitutionById(id);
				model.addAttribute("websiteInstitutionDTO", websiteInstitutionDTO);
			}
		}catch (Exception e) {
			logger.error("AdminWebsiteInstitutionController.getWebsiteInstitutionById", e);
		}
		return updateWebsiteInstitution;
	}
	/**
	 * 查询推荐机构列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/websiteInstitutionPage")
	public ModelAndView getWebsiteInstitutionService(HttpServletRequest request,WebsiteInstitutionDTO websiteInstitutionDTO,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getWebsiteInstitutionList);
		try {
			// 分页
			this.setPage(page);
			this.getPage().setPageSize(10);
			modelAndView.addObject("page",this.getPage());
			List<WebsiteInstitutionDTO> queryWebsiteInstitutionList = this.websiteInstitutionService.queryWebsiteInstitutionList(websiteInstitutionDTO, this.getPage());
			modelAndView.addObject("institutionList", queryWebsiteInstitutionList);
			modelAndView.addObject("websiteInstitutionDTO", websiteInstitutionDTO);
		} catch (Exception e) {
			logger.error("AdminWebsiteInstitutionController.getWebsiteInstitutionService", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
	 * 批量删除推荐机构
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/delWebsiteInstitution")
	@ResponseBody
	public Map<String,Object> delWebsiteInstitution(HttpServletRequest request){
		try {
			String instIds  = request.getParameter("instIds");
			this.websiteInstitutionService.deleteWebsiteInstitution(instIds);
			this.setJson(true, "删除成功", null);
		} catch (Exception e) {
			logger.error("AdminWebsiteInstitutionController.getWebsiteInstitutionService", e);
			this.setJson(false, "系统异常,请稍后重试", null);
		}
		return json;
	}
	
	/**
	 * 修改推荐机构
	 * 
	 * @param request
	 * @param websiteTeacher
	 * @return
	 */
	@RequestMapping("/website/updateWebsiteInstitution")
	public String updateWebsiteInstitution(HttpServletRequest request, @ModelAttribute("websiteInstitution") WebsiteInstitution websiteInstitution) {
		try {
			if (ObjectUtils.isNotNull(websiteInstitution)) {
				this.websiteInstitutionService.updateWebsiteInstitution(websiteInstitution);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteTeacherController.updateWebsiteInstitution", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteInstitutionPage";
	}
}
