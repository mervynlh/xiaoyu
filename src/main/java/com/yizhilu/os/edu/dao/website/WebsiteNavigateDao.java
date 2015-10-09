package com.yizhilu.os.edu.dao.website;
import java.util.List;


import com.yizhilu.os.edu.entity.website.WebsiteNavigate;

/**
 * WebsiteNavigateTbl管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface WebsiteNavigateDao {
	
	/**
	 * 导航列表
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
	 * 查询未冻结的导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebNavigate();
	
}