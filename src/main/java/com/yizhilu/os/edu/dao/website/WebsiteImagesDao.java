package com.yizhilu.os.edu.dao.website;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.website.WebsiteImages;


/**
 * 
 * @author jjl
 *
 */
public interface WebsiteImagesDao {
	/**
	 * 查询首页banner图
	 * 
	 * @param websiteWebsiteImages
	 * @return List<WebsiteWebsiteImages>
	 */
	public List<WebsiteImages> getIndexPageBanner(WebsiteImages websiteWebsiteImages);
	/**
	 * 添加广告图
	 * @param WebsiteImages
	 */
	public void insertWebsiteImages(WebsiteImages websiteImages);

	/**
	 * 广告图分页列表
	 * @param websiteImages
	 * @param page
	 * @return
	 */
	public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages,PageEntity page);

	/**
	 * 删除广告图
	 * @param ids
	 */
	public void deleteImgByIds(String ids);

	/**
	 * 查询广告图
	 * @param id
	 * @return
	 */
	public WebsiteImages getWebsiteImagesById(Long id) ;

	/**
	 * 更新广告图
	 * @param WebsiteImages
	 */
	public void updateWebsiteImages(WebsiteImages websiteImages);
}
