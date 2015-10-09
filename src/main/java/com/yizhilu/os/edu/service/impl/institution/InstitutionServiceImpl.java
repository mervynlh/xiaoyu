package com.yizhilu.os.edu.service.impl.institution;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.dao.institution.InstitutionDao;
import com.yizhilu.os.edu.entity.institution.Institution;
import com.yizhilu.os.edu.service.institution.InstitutionService;
import com.yizhilu.os.edu.service.website.WebsiteInstitutionService;


@Service("institutionService")
public class InstitutionServiceImpl implements InstitutionService {
	private MemCache memCache = MemCache.getInstance();
	@Autowired
	private InstitutionDao institutionDao;
	@Autowired
	private WebsiteInstitutionService websiteInstitutionService;
	/**
	 * 查询所有机构
	 */
	@Override
	public List<Institution> queryInstitutionList(Institution institution,PageEntity page) throws Exception {
		 return this.institutionDao.queryInstitutionList(institution,page);
	}
	/**
	 * 添加机构
	 */
	@Override
	public void addInstitution(Institution institution) throws Exception {
		this.institutionDao.addInstitution(institution);
	}
	/**
	 * 批量删除机构
	 */
	@Override
	public void deleteInstitutionBatch(String institutionIds) throws Exception {
		this.websiteInstitutionService.deleteWebsiteInstitution(institutionIds);
		this.institutionDao.deleteInstitutionBatch(institutionIds);
	}

	/**
	 * 根据id获取机构信息
	 */
	@Override
	public Institution getInstitutionById(Long id) throws Exception {
		return this.institutionDao.getInstitutionById(id);
	}
	
	/**
	 * 更新机构信息
	 */
	@Override
	public void updateInstitution(Institution institution) throws Exception {
		this.memCache.remove(MemConstans.RECOMMEND_INSTITUTION_ALL);
		this.institutionDao.updateInstitution(institution);
	}
	
	/**
	 * 更新机构审核状态
	 */
	@Override
	public void updateInstitutionStatus(Institution institution) throws Exception {
		this.institutionDao.updateInstitutionStatus(institution);
	}
	
	@Override
	public List<Institution> getInstitutionByStatusAndRec(PageEntity page) {
		return this.institutionDao.getInstitutionByStatusAndRec(page);
	}
	@Override
	public List<Institution> getInstitutionList(PageEntity page) {
		return this.institutionDao.getInstitutionList(page);
	}
}
