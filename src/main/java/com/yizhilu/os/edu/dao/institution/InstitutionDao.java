package com.yizhilu.os.edu.dao.institution;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.institution.Institution;

public interface InstitutionDao {
	
	/**
	 * 根据id获取机构信息
	 * @param id
	 * @return
	 */
	public Institution getInstitutionById(Long id);
	
	/**
	 * 根据条件获取Institution列表
	 * @return
	 * @throws Exception
	 */
	public List<Institution> queryInstitutionList(Institution institution,PageEntity page);
	
	/**
	 * 添加机构
	 * @param institution 机构实体
	 */
	public void addInstitution(Institution institution);
	
	/**
	 *  批量删除机构
	 * @param institutionIds
	 */
	public void deleteInstitutionBatch(String institutionIds);
	
	/**
	 * 更新机构信息
	 * @param institution
	 */
	public void updateInstitution(Institution institution);
	
	/**
	 * 更新机构审核状态
	 * @param institution
	 */
	public void updateInstitutionStatus(Institution institution);
	
	/**
	 * 查询审核未推荐的机构
	 * @return
	 */
	public List<Institution> getInstitutionByStatusAndRec(PageEntity page);
	
	/**
	 * 获取审核通过的机构列表
	 * @param page 分页
	 * @return List<Institution> 机构列表
	 */
	public List<Institution> getInstitutionList(PageEntity page);
}
