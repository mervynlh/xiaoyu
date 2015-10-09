package com.yizhilu.os.edu.service.website;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.website.WebsiteNavigate;

/**
 * WebsiteNavigateTbl管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface WebsiteNavigateService {

	/**
	 * 首页导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 添加导航
	 * @param WebsiteNavigate
	 */
	public void addWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 冻结、解冻导航
	 * @param WebsiteNavigate
	 */
	public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 删除导航
	 * @param id
	 */
	public void delWebsiteNavigate(Long id);
	/**
	 * 更新导航
	 * @param WebsiteNavigate
	 */
	public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * id查询导航
	 * @param id
	 * @return
	 */
	public WebsiteNavigate getWebsiteNavigateById(Long id);
	/**
	 * 导航列表
	 * @return
	 */
	public Map<String,Object> getWebNavigate();
	
}