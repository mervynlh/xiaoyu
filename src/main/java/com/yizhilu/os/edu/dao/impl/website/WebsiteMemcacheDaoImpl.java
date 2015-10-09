package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.website.WebsiteMemcacheDao;
import com.yizhilu.os.edu.entity.website.WebsiteMemcache;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.dao.impl.website.WebsiteMemcacheDaoImpl
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月23日 下午6:14:51
 */
@Repository("websiteMemcacheDao")
public class WebsiteMemcacheDaoImpl extends GenericDaoImpl implements WebsiteMemcacheDao{

	/**
	 * 添加WebsiteCourse
	 * 
	 * @param websiteCourse
	 *            要添加的WebsiteCourse
	 * @return id
	 */
	public void addWebsiteMemcache(WebsiteMemcache websiteMemcache) {
		this.insert("WebsiteMemcacheMapper.addWebsiteMemcache", websiteMemcache);
	}

	/**
	 * 查询memcache管理list
	 * 
	 * @param websiteMemcache
	 * @param page
	 * @return
	 */
	public List<WebsiteMemcache> queryWebsiteMemcacheList(WebsiteMemcache websiteMemcache, PageEntity page) {
		return this.queryForListPage("WebsiteMemcacheMapper.queryWebsiteMemcacheList", websiteMemcache, page);
	}
	/**
	 * 查詢Memcachekey是否存在
	 * 
	 * @param memKey
	 * @return
	 */
	public Integer queryWebsiteMemcacheIsExsit(String memKey){
		List<Integer> integers=this.selectList("WebsiteMemcacheMapper.queryWebsiteMemcacheIsExsit", memKey);
		if(ObjectUtils.isNotNull(integers)&&integers.size()>0){
			return integers.get(0);
		}
		return 0;
	};
	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteMemcache queryWebsiteMemcacheById(Long id) {
		return this.selectOne("WebsiteMemcacheMapper.getWebsiteMemcacheById", id);
	}
	/**
	 * 删除memcache
	 * 
	 * @param id
	 */
	public void deleteWebsiteMemcacheById(Long id) {
		this.delete("WebsiteMemcacheMapper.deleteWebsiteMemcacheById", id);
	}
}
