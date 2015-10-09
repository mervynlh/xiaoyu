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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.website.WebsiteMemcache;
import com.yizhilu.os.edu.service.website.WebsiteMemcacheService;

/**
 * 
 * @ClassName 
 *            com.yizhilu.os.edu.controller.website.AdminWebsiteMemcacheController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月24日 上午10:11:40
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteMemcacheController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteMemcacheController.class);

	private static final String toWebsiteMemcache = getViewPath("/admin/website/memcache/website_memcache");// Memcache列表页
	private static final String toAddwebsiteMemcache = getViewPath("/admin/website/memcache/website_add");// 添加页面
	@Autowired
	private WebsiteMemcacheService websiteMemcacheService;

	// 绑定变量名参数
	@InitBinder("websiteMemcache")
	public void initBinderWebsiteMemcache(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteMemcache.");
	}

	/**
	 * 跳转到添加页
	 * 
	 * @return
	 */
	@RequestMapping("/websitemem/toAdd")
	public String toAddwebsiteMemcache() {
		return toAddwebsiteMemcache;
	}

	/**
	 * 添加Memcachekey
	 * 
	 * @param request
	 * @param websiteMemcache
	 * @return
	 */
	@RequestMapping("/websitemem/add")
	@ResponseBody
	public Map<String, Object> addWebsiteMemcache(HttpServletRequest request, @ModelAttribute WebsiteMemcache websiteMemcache) {
		try {
			if (ObjectUtils.isNotNull(websiteMemcache) && StringUtils.isNotEmpty(websiteMemcache.getMemKey())) {
				// 判断key是否存在
				boolean flag = websiteMemcacheService.queryWebsiteMemcacheIsExsit(websiteMemcache.getMemKey().replaceAll(" ", ""));
				if (flag) {
					websiteMemcache.setMemKey(websiteMemcache.getMemKey().replaceAll(" ", ""));
					websiteMemcacheService.addWebsiteMemcache(websiteMemcache);
					this.setJson(true, "", null);
				} else {
					this.setJson(false, "该Memcache-key已存在", null);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.addWebsiteMemcache", e);
		}
		return json;
	}

	/**
	 * 查询memcache管理
	 * 
	 * @param request
	 * @param model
	 * @param websiteMemcache
	 * @param page
	 * @return
	 */
	@RequestMapping("/websitemem/list")
	public String getWebsiteMemcacheList(HttpServletRequest request, Model model, @ModelAttribute WebsiteMemcache websiteMemcache, @ModelAttribute("page") PageEntity page) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			// 查詢WebsiteMemcache
			List<WebsiteMemcache> websiteMemcacheList = websiteMemcacheService.queryWebsiteMemcacheList(websiteMemcache, this.getPage());
			model.addAttribute("websiteMemcacheList", websiteMemcacheList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.getWebsiteMemcacheList", e);
			return setExceptionRequest(request, e);
		}
		return toWebsiteMemcache;
	}

	/**
	 * 删除memcache缓存
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/websitemem/removeMemKey")
	@ResponseBody
	public Map<String, Object> removeMemKey(HttpServletRequest request, @RequestParam("id") Long id) {
		try {
			// 获得WebsiteMemcache的result
			WebsiteMemcache websiteMemcache = websiteMemcacheService.queryWebsiteMemcacheById(id);
			if (ObjectUtils.isNotNull(websiteMemcache)) {
				// 移除memcache
				websiteMemcacheService.removeMemcacheKey(MemConstans.MEMFIX+websiteMemcache.getMemKey());
				this.setJson(true, "", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.removeMemKey", e);
			this.setJson(false, "", null);
		}
		return json;
	}

	/**
	 * 删除此条信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/websitemem/del")
	@ResponseBody
	public Map<String, Object> deleteWebsiteMemcache(@RequestParam("id") Long id) {
		try {
			//删除此条信息
			websiteMemcacheService.deleteWebsiteMemcacheById(id);
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.deleteWebsiteMemcache", e);
			this.setJson(false, "", null);
		}
		return json;
	}
}
