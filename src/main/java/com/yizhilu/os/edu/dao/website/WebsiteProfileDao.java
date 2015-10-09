package com.yizhilu.os.edu.dao.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteProfile;

/**
 * 网站配置
 * 
 * @ClassName com.yizhilu.os.edu.dao.website.WebsiteProfileDao
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月12日 上午9:42:53
 */
public interface WebsiteProfileDao {
	/**
	 * 根据type查询网站配置
	 * 
	 * @param type
	 * @return
	 */
	public WebsiteProfile getWebsiteProfileByType(String type);

	/**
	 * 更新网站配置管理
	 * 
	 * @param websiteProfile
	 */
	public void updateWebsiteProfile(WebsiteProfile websiteProfile);

	/**
	 * 查询网站配置
	 * 
	 * @return List<WebsiteProfile>
	 */
	public List<WebsiteProfile> getWebsiteProfileList();
}