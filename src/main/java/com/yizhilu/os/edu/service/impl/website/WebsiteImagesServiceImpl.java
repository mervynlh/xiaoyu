package com.yizhilu.os.edu.service.impl.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.website.WebsiteImagesDao;
import com.yizhilu.os.edu.entity.website.WebsiteImages;
import com.yizhilu.os.edu.service.website.WebsiteImagesService;

/**
 * 
 * @author jjl
 * 
 */
@Service("websiteImagesService")
public class WebsiteImagesServiceImpl implements WebsiteImagesService {
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private WebsiteImagesDao websiteImagesDao;

	/**
	 * 查询首页banner图 ,结果封装为Map<keyword,list<WebsiteImages>>
	 * 
	 * @param websiteImages
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getIndexPageBanner(WebsiteImages websiteImages) throws Exception {
		Map<String, Object> map =(Map<String, Object>) memCache.get(MemConstans.BANNER_IMAGES);
		if (ObjectUtils.isNotNull(map)) {
			return map;
		}
		map = new HashMap<String, Object>();
		// 查询的结果必须以keyword排序
		List<WebsiteImages> websiteImagesList = websiteImagesDao.getIndexPageBanner(websiteImages);

		if (ObjectUtils.isNotNull(websiteImagesList)) {
			String keyword = websiteImagesList.get(0).getKeyWord();
			List<WebsiteImages> list = new ArrayList<WebsiteImages>();
			for (WebsiteImages images : websiteImagesList) {
				if (!keyword.equalsIgnoreCase(images.getKeyWord())) {
					map.put(keyword, list);
					keyword = images.getKeyWord();
					list = new ArrayList<WebsiteImages>();
					list.add(images);
				} else {
					list.add(images);
				}
			}
			if (ObjectUtils.isNotNull(list)) {
				map.put(keyword, list);
			}
		}
		if (ObjectUtils.isNotNull(map)) {
			memCache.set(MemConstans.BANNER_IMAGES, map,MemConstans.BANNER_IMAGES_TIME);
		}
		return map;
	}

	/**
	 * 添加广告图
	 *
	 * @param WebsiteImages
	 */
	public void insertWebsiteImages(WebsiteImages websiteImages) {
		websiteImagesDao.insertWebsiteImages(websiteImages);
		memCache.remove(MemConstans.BANNER_IMAGES);
	}

	/**
	 * 广告图分页列表
	 * 
	 * @param websiteImages
	 * @param page
	 * @return
	 */
	public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages, PageEntity page) {
		return websiteImagesDao.getImgPageList(websiteImages, page);
	}

	/**
	 * 删除广告图
	 * 
	 * @param ids
	 */
	public void deleteImgByIds(String ids) {
		websiteImagesDao.deleteImgByIds(ids);
		memCache.remove(MemConstans.BANNER_IMAGES);
	}

	/**
	 * 查询广告图
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteImages getWebsiteImagesById(Long id) {
		return websiteImagesDao.getWebsiteImagesById(id);
	}

	/**
	 * 更新广告图
	 * 
	 * @param WebsiteImages
	 */
	public void updateWebsiteImages(WebsiteImages websiteImages) {
		websiteImagesDao.updateWebsiteImages(websiteImages);
		memCache.remove(MemConstans.BANNER_IMAGES);
	}
}
