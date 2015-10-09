package com.yizhilu.os.edu.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.dao.website.WebsiteMemcacheDao;
import com.yizhilu.os.edu.entity.website.WebsiteMemcache;
import com.yizhilu.os.edu.service.website.WebsiteMemcacheService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.service.impl.website.WebsiteMemcacheServiceImpl
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月23日 下午6:40:14
 */
@Service("websiteMemcacheService")
public class WebsiteMemcacheServiceImpl implements WebsiteMemcacheService {
	@Autowired
	private WebsiteMemcacheDao websiteMemcacheDao;
	private MemCache memcache=MemCache.getInstance();

	/**
	 * 添加memcache key
	 * 
	 * @param websiteMemcache
	 */
	public void addWebsiteMemcache(WebsiteMemcache websiteMemcache) {
		websiteMemcacheDao.addWebsiteMemcache(websiteMemcache);
	}

	/**
	 * 查询memcache管理list
	 * 
	 * @param websiteMemcache
	 * @param page
	 * @return
	 */
	public List<WebsiteMemcache> queryWebsiteMemcacheList(WebsiteMemcache websiteMemcache, PageEntity page) {
		return websiteMemcacheDao.queryWebsiteMemcacheList(websiteMemcache, page);
	}
	/**
	 * 查詢Memcachekey是否存在
	 * 
	 * @param memKey
	 * @return
	 */
	public boolean queryWebsiteMemcacheIsExsit(String memKey) {
		int flag =websiteMemcacheDao.queryWebsiteMemcacheIsExsit(memKey);
		if(flag==0){
			return true;
		}
		return false;
	}
	/**
	 * 删除Memcache缓存
	 * 
	 * @param memKey
	 * @return
	 */
	public boolean removeMemcacheKey(String memKey) {
		return memcache.remove(memKey);
	}
	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteMemcache queryWebsiteMemcacheById(Long id) {
		return websiteMemcacheDao.queryWebsiteMemcacheById(id);
	}
	/**
	 * 删除memcache
	 * 
	 * @param id
	 */
	public void deleteWebsiteMemcacheById(Long id) {
		websiteMemcacheDao.deleteWebsiteMemcacheById(id);		
	}
}
