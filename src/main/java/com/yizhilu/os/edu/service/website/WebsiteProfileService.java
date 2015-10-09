package com.yizhilu.os.edu.service.website;

import java.util.Map;

import com.yizhilu.os.edu.entity.website.WebsiteProfile;

/**
 * 
 * @ClassName com.yizhilu.os.edu.service.website.WebsiteProfileService
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月12日 上午9:28:43
 */
public interface WebsiteProfileService {
	/**
	 * 修改WebsiteProfile
	 * 
	 * @param websiteProfile
	 * @throws Exception
	 */
	public void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception;

	/**
	 * 查询所有网站配置
	 * 
	 * @return 无参
	 * @throws Exception
	 */
	public Map<String, Object> getWebsiteProfileList() throws Exception;
	/**
	 * 根据type查询网站配置
	 * 
	 * @param type
	 * @return
	 */
	public Map<String,Object> getWebsiteProfileByType(String type);


}