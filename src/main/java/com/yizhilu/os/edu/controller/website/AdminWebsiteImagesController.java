package com.yizhilu.os.edu.controller.website;

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
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.website.WebsiteImages;
import com.yizhilu.os.edu.service.website.WebsiteImagesService;

/**
 * 
 * @author jjl
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteImagesController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteImagesController.class);

	@Autowired
	private WebsiteImagesService websiteImagesService;

	private static final String getWebsiteImagesList = getViewPath("/admin/website/images/websiteImages_list");
	private static final String updateWebsiteImages = getViewPath("/admin/website/images/websiteImages_update");
	private static final String addWebsiteImages = getViewPath("/admin/website/images/websiteImages_add");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteImages")
	public void initBinderWebsiteImages(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteImages.");
	}

	/**
	 * 添加广告图
	 * 
	 * @return
	 */
	@RequestMapping("/website/addImages")
	public String addWebsiteImages(WebsiteImages websiteImages,HttpServletRequest request) {
		try {
			if (websiteImages != null) {
				// 判断广告图路径和广告图关键是否不为空
				if (websiteImages.getImagesUrl() != null&&!websiteImages.getImagesUrl().equals("")
					&& websiteImages.getKeyWord() != null&& !websiteImages.getKeyWord().equals("")) {
					// 插入广告图
					if(websiteImages.getLinkAddress()==null || websiteImages.getLinkAddress().trim().length()==0){
						websiteImages.setLinkAddress("/");
					}
					if(websiteImages.getColor()==null || websiteImages.getColor().trim().length()==0){
						websiteImages.setColor("");
					}
					websiteImagesService.insertWebsiteImages(websiteImages);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteImagesController.addWebsiteImages--添加广告图出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/imagesPage";
	}
	/**
	 * 跳转添加广告图
	 * 
	 * @return
	 */
	@RequestMapping("/website/doAddImages")
	public String doAddWebsiteImages() {
		return addWebsiteImages;
	}
	/**
	 * 广告图分页列表
	 * @return
	 */
	@RequestMapping("/website/imagesPage")
	public ModelAndView getWebsiteImagesPage(WebsiteImages websiteImages,@ModelAttribute("page") PageEntity page,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getWebsiteImagesList);
		try{
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<WebsiteImages> websiteImagesList = websiteImagesService.getImgPageList(websiteImages, page);
			modelAndView.addObject("websiteImagesList",websiteImagesList);
			modelAndView.addObject("page",this.getPage());
		}catch (Exception e) {
			logger.error("AdminWebsiteImagesController.getWebsiteImagesPage--广告图分页列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
	 * 删除广告图
	 * @return
	 */
	@RequestMapping("/website/delImages/{ids}")
	@ResponseBody
	public Map<String,Object> deleteWebsiteImages(@PathVariable String ids){
		try{
			if(ids!=null){
				if(ids.trim().endsWith(",")){
					ids = ids.trim().substring(0,ids.trim().length()-1);
				}
				websiteImagesService.deleteImgByIds(ids);
				this.setJson(true, "true", null);
			}
		}catch (Exception e) {
			logger.error("AdminWebsiteImagesController.deleteWebsiteImages--删除广告图出错",e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 跳转广告图修改
	 * @return
	 */
	@RequestMapping("/website/doUpdateImages/{id}")
	public ModelAndView doUpdateImages(@PathVariable Long id,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteImages);
		try{
			WebsiteImages websiteImages = websiteImagesService.getWebsiteImagesById(id);
			modelAndView.addObject("websiteImages", websiteImages);
		}catch (Exception e) {
			logger.error("AdminWebsiteImagesController.doUpdateImages--跳转广告图修改",e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	
	/**
	 * 更新广告图
	 * @return
	 */
	@RequestMapping("/website/updateImages")
	public String updatewebsiteImages(WebsiteImages websiteImages,HttpServletRequest request){
		try{
			if(websiteImages!=null){
				if(websiteImages.getLinkAddress()==null || websiteImages.getLinkAddress().trim().length()==0){
					websiteImages.setLinkAddress("/");
				}
				if(websiteImages.getColor()==null || websiteImages.getColor().equals("")){
					websiteImages.setColor("#FFFFFF");
				}
				websiteImagesService.updateWebsiteImages(websiteImages);
			}
			
		}catch (Exception e) {
			logger.error("AdminWebsiteImagesController.updateImages--更新广告图出错",e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/imagesPage";
	}
	
}
