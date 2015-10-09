package com.yizhilu.os.edu.service.impl.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.website.WebsiteInstitutionDao;
import com.yizhilu.os.edu.entity.website.WebsiteInstitution;
import com.yizhilu.os.edu.entity.website.WebsiteInstitutionDTO;
import com.yizhilu.os.edu.service.website.WebsiteInstitutionService;

/**
 * 推荐机构Service实现类
 * 
 * @author dingkai
 * @date 2015年9月22日
 */
@Repository("websiteInstitutionService")
public class WebsiteInstitutionServiceImpl implements WebsiteInstitutionService {

	private MemCache memCache = MemCache.getInstance();

	@Autowired
	private WebsiteInstitutionDao websiteInstitutionDao;

	@Override
	public void addWebsiteInstitution(String ids) {
		this.memCache.remove(MemConstans.RECOMMEND_INSTITUTION_ALL);
		this.websiteInstitutionDao.addWebsiteInstitution(ids);
	}

	@Override
	public void deleteWebsiteInstitution(String ids) {
		this.memCache.remove(MemConstans.RECOMMEND_INSTITUTION_ALL);
		this.websiteInstitutionDao.deleteWebsiteInstitution(ids);
	}

	@Override
	public void updateWebsiteInstitution(WebsiteInstitution websiteInstitution) {
		this.memCache.remove(MemConstans.RECOMMEND_INSTITUTION_ALL);
		this.websiteInstitutionDao.updateWebsiteInstitution(websiteInstitution);
	}

	@Override
	public WebsiteInstitutionDTO getWebsiteInstitutionById(Long id){
		return this.websiteInstitutionDao.getWebsiteInstitutionById(id);
	}
	
	@Override
	public List<WebsiteInstitutionDTO> queryWebsiteInstitutionList(WebsiteInstitution websiteInstitution,
			PageEntity page) {
			return this.websiteInstitutionDao.queryWebsiteInstitutionList(websiteInstitution, page);
	}

	@Override
	public List<WebsiteInstitutionDTO> getWebsiteInstitutionList() {
		List<WebsiteInstitutionDTO> list = (List) this.memCache.get(MemConstans.RECOMMEND_INSTITUTION_ALL);
		if (ObjectUtils.isNull(list)) {
			list = this.websiteInstitutionDao.getWebsiteInstitutionList();
			this.memCache.set(MemConstans.RECOMMEND_INSTITUTION_ALL,list,MemConstans.RECOMMEND_INSTITUTION_ALL_TIME);
		}
		return list;
	}
}
