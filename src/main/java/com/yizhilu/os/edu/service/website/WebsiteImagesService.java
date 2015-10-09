package com.yizhilu.os.edu.service.website;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.website.WebsiteImages;

/**
 * 
 * @ClassName com.yizhilu.os.edu.service.website.WebsiteImagesService
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月9日 下午2:15:10
 */
public interface WebsiteImagesService {
	/**
	 * 查询首页banner图
	 * 
	 * @param websiteImages
	 * @return List<WebsiteImages>
	 * @throws Exception
	 */
	public Map<String, Object> getIndexPageBanner(WebsiteImages websiteImages) throws Exception;

	/**
	 * 添加广告图
	 * 
	 * @param WebsiteImages
	 */
	public void insertWebsiteImages(WebsiteImages websiteImages);

	/**
	 * 广告图分页列表
	 * 
	 * @param websiteImages
	 * @param page
	 * @return
	 */
	public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages, PageEntity page);

	/**
	 * 删除广告图
	 * 
	 * @param ids
	 */
	public void deleteImgByIds(String ids);

	/**
	 * 查询广告图
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteImages getWebsiteImagesById(Long id);

	/**
	 * 更新广告图
	 * 
	 * @param WebsiteImages
	 */
	public void updateWebsiteImages(WebsiteImages websiteImages);
}
