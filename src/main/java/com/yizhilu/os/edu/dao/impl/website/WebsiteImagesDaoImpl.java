package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.website.WebsiteImagesDao;
import com.yizhilu.os.edu.entity.website.WebsiteImages;

/**
 * 
 * @author jjl
 *
 */
@Repository("websiteImagesDao")
public class WebsiteImagesDaoImpl extends GenericDaoImpl implements WebsiteImagesDao{

	/**
	 * 查询首页banner图
	 * 
	 * @param websiteImages
	 * @return List<WebsiteImages>
	 */
	public List<WebsiteImages> getIndexPageBanner(WebsiteImages websiteImages) {
		return this.selectList("WebsiteImagesMapper.getIndexPageBanner", websiteImages);
	}
	/**
	 * 添加广告图
	 * @param WebsiteImages
	 */
	public void insertWebsiteImages(WebsiteImages websiteImages){
		this.insert("WebsiteImagesMapper.createImages", websiteImages);
	}

	/**
	 * 广告图分页列表
	 * @param websiteImages
	 * @param page
	 * @return
	 */
	public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages,PageEntity page){
		return this.queryForListPage("WebsiteImagesMapper.getImgPageList",websiteImages, page);
	}

	/**
	 * 删除广告图
	 * @param ids
	 */
	public void deleteImgByIds(String ids){
		this.delete("WebsiteImagesMapper.deleteImg", ids);
	}

	/**
	 * 查询广告图
	 * @param id
	 * @return
	 */
	public WebsiteImages getWebsiteImagesById(Long id){
		return this.selectOne("WebsiteImagesMapper.getImageseById", id);
	}
	
	/**
	 * 更新广告图
	 * @param WebsiteImages
	 */
	public void updateWebsiteImages(WebsiteImages websiteImages){
		this.selectOne("WebsiteImagesMapper.updateImages", websiteImages);
	}
}
