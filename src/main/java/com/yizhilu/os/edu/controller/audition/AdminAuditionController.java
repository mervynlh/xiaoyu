package com.yizhilu.os.edu.controller.audition;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.audition.Audition;
import com.yizhilu.os.edu.service.audition.AuditionService;

/**
 * 后台查看约课记录
 * @author dingkai
 * @date 2015年8月15日
 */
@Controller
@RequestMapping("/admin")
public class AdminAuditionController extends EduBaseController{

	private static final Logger logger = LoggerFactory.getLogger(AdminAuditionController.class);
	
	private static final String getAuditionListWWZD = getViewPath("/admin/audition/audition_list_wwzd");// 为我制定
	private static final String getAuditionListWYYK = getViewPath("/admin/audition/audition_list_wyyk");//我要约课
	private static final String getAuditionInfo = getViewPath("/admin/audition/audition_info");
	
	@Autowired
	private AuditionService auditionService;
	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("audition")
	public void initBinderAudition(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("audition.");
	}
	
	/**
	 * 我要约课
	 * @return
	 */
	@RequestMapping("/audition/getAuditionListWYYK")
	public ModelAndView getAuditionListWYYK(HttpServletRequest request,@ModelAttribute("audition") Audition audition,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getAuditionListWYYK);
		try{
			page.setPageSize(10);
			this.setPage(page);
			modelAndView.addObject("page", this.getPage());
			audition.setType(1);
			List<Audition> list = auditionService.getAuditionList(audition,this.getPage());
			modelAndView.addObject("audition", audition);
			modelAndView.addObject("auditionList", list);
		}catch(Exception e){
			logger.error("AdminAuditionController.getAuditionListWYYK", e);
		}
		return modelAndView;
	}
	/**
	 * 为我制定
	 * @return
	 */
	@RequestMapping("/audition/getAuditionListWWZD")
	public ModelAndView getAuditionListWWZD(HttpServletRequest request,@ModelAttribute("audition") Audition audition,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getAuditionListWWZD);
		try{
			page.setPageSize(10);
			this.setPage(page);
			modelAndView.addObject("page", this.getPage());
			audition.setType(0);
			List<Audition> list = auditionService.getAuditionList(audition,this.getPage());
			modelAndView.addObject("audition", audition);
			modelAndView.addObject("auditionList", list);
		}catch(Exception e){
			logger.error("AdminAuditionController.getAuditionListWWZD", e);
		}
		return modelAndView;
	}
	
	/**
	 * 根据id更新状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/audition/updateStatus/{id}/{status}")
	@ResponseBody
	public Map<String,Object> updateStatus(@PathVariable("id")Long id,@PathVariable("status") int status){
		try{
			this.auditionService.updateStatus(id,status);
			this.setJson(true, "success", null);
		}catch(Exception e){
			logger.error("AdminAuditionController.updateStatus", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 根据id获取约课记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/audition/getAuditionById/{id}")
	public String getAuditionById(Model model,@PathVariable Long id) {
		try{
			Audition audition = this.auditionService.getAuditionById(id);
			model.addAttribute("audition", audition);
		}catch(Exception e){
			logger.error("AdminAuditionController.getAuditionById", e);
		}
		return getAuditionInfo;
	}
	
	/**
	 * 根据id删除约课记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/audition/deleteAuditionById/{id}")
	@ResponseBody
	public Map<String,Object> deleteAuditionById(@PathVariable Long id){
		try{
			if(StringUtils.isEmpty(id)){
				this.setJson(false, "id为空", null);
				return json;
			}
			this.auditionService.deleteAuditionById(id);
			this.setJson(true, "删除成功", null);
		}catch(Exception e){
			logger.error("AdminAuditionController.deleteAuditionById",e);
		}
		return json;
	}
	
	/**
	 * 根据年级查询约课记录
	 * @param model
	 * @param grade
	 * @return
	 */
	@RequestMapping("/audition/getAuditionListByGrade/{gradeId}")
	public String getAuditionListByGrade(Model model,@PathVariable Long gradeId){
		try{
			List<Audition> auditionList = this.auditionService.getAuditionByGrade(gradeId);
			model.addAttribute("auditionList", auditionList);
			System.out.println(auditionList);
		}catch(Exception e){
			logger.error("AdminAuditionController.getAuditionListByGrade",e);
		}
		return null;
	}
}
