package com.yizhilu.os.edu.dao.impl.institution;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.institution.InstitutionDao;
import com.yizhilu.os.edu.entity.institution.Institution;

@Repository("institutionDao")
public class InstitutionDaoImpl extends GenericDaoImpl implements InstitutionDao {
	
	/**
	 * 查询所有机构
	 */
	@Override
	public List<Institution> queryInstitutionList(Institution institution,PageEntity page) {
		
		return this.queryForListPage("InstitutionMapper.queryInstitutionList", institution,page);
	}

	/**
	 * 添加机构
	 */
	@Override
	public void addInstitution(Institution institution) {
		this.insert("InstitutionMapper.addInstitution", institution);
	}

	/**
	 * 批量删除机构
	 */
	@Override
	public void deleteInstitutionBatch(String institutionIds) {
		this.delete("InstitutionMapper.delInstitutionBatch", institutionIds.replace(" " , "").split(","));
	}

	/**
	 * 根据ID获取机构信息
	 */
	@Override
	public Institution getInstitutionById(Long id) {
		return this.selectOne("InstitutionMapper.getInstitutionById", id);
	}

	/**
	 * 更新机构信息
	 */
	@Override
	public void updateInstitution(Institution institution) {
		this.update("InstitutionMapper.updateInstitution", institution);
	}

	/**
	 * 更新机构审核状态
	 */
	@Override
	public void updateInstitutionStatus(Institution institution) {
		this.update("InstitutionMapper.updateInstitutionStatus", institution);
	}

	@Override
	public List<Institution> getInstitutionByStatusAndRec(PageEntity page) {
		return this.queryForListPage("InstitutionMapper.getInstitutionByStatusAndRec", null, page);
	}

	@Override
	public List<Institution> getInstitutionList(PageEntity page) {
		return this.queryForListPage("InstitutionMapper.getInstitutionList", null, page);
	}	
	
}
