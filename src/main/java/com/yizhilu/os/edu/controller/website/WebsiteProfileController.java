package com.yizhilu.os.edu.controller.website;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
/**
 * 
 * @ClassName  com.yizhilu.os.edu.controller.website.WebsiteProfileController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月11日 下午3:25:29
 */
@Controller
public class WebsiteProfileController extends EduBaseController{
	
	private static final Logger logger=LoggerFactory.getLogger(WebsiteProfileController.class);
 	@Autowired
    private WebsiteProfileService websiteProfileService;
 	
 	/**
	 * 查询在线咨询
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/websiteProfile/online")
	@ResponseBody
	public Map<String,Object> getAjaxWebsiteOnline(HttpServletRequest request) {
		try {
			// 查询在线咨询详情
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.online.toString());
			this.setJson(true, "success", map);
		} catch (Exception e) {
			logger.error("getWebsiteOnline", e);
			this.setJson(false, "online is null", null);
		}
		return json;
	}
}