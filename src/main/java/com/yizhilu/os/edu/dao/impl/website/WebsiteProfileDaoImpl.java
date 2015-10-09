package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteProfile;
import com.yizhilu.os.edu.dao.website.WebsiteProfileDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
/**
 * 
 * @ClassName  com.yizhilu.os.edu.dao.impl.website.WebsiteProfileDaoImpl
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月12日 上午9:46:05
 */
 @Repository("websiteProfileDao")
public class WebsiteProfileDaoImpl extends GenericDaoImpl implements WebsiteProfileDao{

	/**
	 * 根据type查询网站配置
	 * 
	 * @param type
	 * @return
	 */
	public WebsiteProfile getWebsiteProfileByType(String type) {
		return this.selectOne("WebsiteProfileMapper.getWebsiteProfileByType", type);
	}

	/**
	 * 更新网站配置管理
	 * 
	 * @param websiteProfile
	 */
	public void updateWebsiteProfile(WebsiteProfile websiteProfile) {
		this.update("WebsiteProfileMapper.updateWebsiteProfile", websiteProfile);
	}

	/**
	 * 查询网站配置
	 * 
	 * @return List<WebsiteProfile>
	 */
	public List<WebsiteProfile> getWebsiteProfileList() {
		return this.selectList("WebsiteProfileMapper.getWebsiteProfileList", null);
	}

}
