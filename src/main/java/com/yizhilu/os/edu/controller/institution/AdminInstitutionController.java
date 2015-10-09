package com.yizhilu.os.edu.controller.institution;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.institution.Institution;
import com.yizhilu.os.edu.service.institution.InstitutionService;

/**
 * 
 * @author yzl268
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminInstitutionController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminInstitutionController.class);

	private static final String showInstitutionList = getViewPath("/admin/institution/institution_list");// 机构列表
	private static final String institution_info = getViewPath("/admin/institution/institution_info"); // 机构详情
	private static final String institutionList = getViewPath("/admin/institution/institution_list_rec"); // 推荐机构查询
	
	@Autowired
	private InstitutionService institutionService;

	@InitBinder("institution")
	public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("institution.");
	}

	/**
	 * 查询机构列表
	 * @return
	 */
	@RequestMapping("/institution/showInstitutionList")
	public ModelAndView showInstitutionList(@ModelAttribute("institution") Institution institution,@ModelAttribute("page") PageEntity page){
		ModelAndView modelAndView = new ModelAndView(showInstitutionList);
		try {
			page.setPageSize(10);
			this.setPage(page);
			modelAndView.addObject("page", this.getPage());
			modelAndView.addObject("institution", institution);
			List<Institution> institutionList = institutionService.queryInstitutionList(institution,this.getPage());
			modelAndView.addObject("institutionList", institutionList);
		} catch (Exception e) {
			logger.error("InstitutionController.showInstitutionList", e);
		}
		return modelAndView;
	}
	/**
	 * 查询机构详情
	 * @return
	 */
	@RequestMapping("/institution/institutionInfo/{id}")
	public ModelAndView institutionInfo(@PathVariable("id") Long id,@ModelAttribute("page") PageEntity page){
		ModelAndView modelAndView = new ModelAndView(institution_info);
		try {
			Institution institution = this.institutionService.getInstitutionById(id);
			modelAndView.addObject("institution", institution);
		} catch (Exception e) {
			logger.error("AdminInstitutionController.institutionInfo", e);
		}
		return modelAndView;
	}
	/**
	 * 添加机构
	 * @param request
	 * @param institution
	 * @return
	 */
	@RequestMapping("/institution/addInstitution")
	public String addInstitution(HttpServletRequest request, @ModelAttribute("institution") Institution institution) {
		try {
			if (ObjectUtils.isNotNull(institution) && !StringUtils.isEmpty(institution.getName())) {
				institution.setCreateTime(new Date());
				institutionService.addInstitution(institution);
			}
		} catch (Exception e) {
			logger.error("AdminInstitutionController.addInstitution", e);
		}
		return "redirect:/admin/institution/showInstitutionList";
	}

	/**
	 * 删除机构
	 * @param request
	 * @param institutionIds
	 * @return
	 */
	@RequestMapping("/institution/delInstitutionBatch")
	@ResponseBody
	public Map<String, Object> delInstitutionBatch(HttpServletRequest request,
			@RequestParam("institutionIds") String institutionIds) {
		try {
			if (!StringUtils.isEmpty(institutionIds)) {
				// 批量删除
				institutionService.deleteInstitutionBatch(institutionIds);
				this.setJson(true, "success", null);
			} else {
				this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("AdminInstitutionController.delInstitutionBatch", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	
	/**
	 * 更新机构
	 * @param request
	 * @param institution
	 * @return
	 */
	@RequestMapping("/institution/updateInstitution")
	public String updateInstitution(HttpServletRequest request, @ModelAttribute("institution") Institution institution){
		if(ObjectUtils.isNotNull(institution)/*&&!StringUtils.isEmpty(institution.getName())*/){
			try {
				
				this.institutionService.updateInstitution(institution);
				
			} catch (Exception e) {
				logger.error("AdminInstitutionController.updateInstitution",e);
			}
		}
		return "redirect:/admin/institution/showInstitutionList";
	}

	/**
	 * 更新审核状态
	 * @param request
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/institution/updateStatus/{id}/{status}")
	public String updateStatus( @PathVariable("id") Long id,@PathVariable("status") int status){
		if(status==0||status==1){
			try{
				Institution institution = new Institution();
				institution.setId(id);
				institution.setStatus(status);
				this.institutionService.updateInstitutionStatus(institution);
			}catch(Exception e){
				logger.error("AdminInstitutionController.updateStatus", e);
			}
		}
		return "redirect:/admin/institution/showInstitutionList"; 
	}
	
	@RequestMapping("/institution/getInstitution")
	public String getInstitution(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		try{
			page.setPageSize(10);
			this.setPage(page);
			request.setAttribute("page", this.getPage());
			List<Institution> institutionList = this.institutionService.getInstitutionByStatusAndRec(page);
			request.setAttribute("institutionList", institutionList);
		}catch(Exception e){
			logger.error("AdminInstitutionController.getInstitution", e);
		}
		return institutionList;
	}
}
