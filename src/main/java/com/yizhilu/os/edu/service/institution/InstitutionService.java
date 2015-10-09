package com.yizhilu.os.edu.service.institution;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.institution.Institution;

public interface InstitutionService {
	
	/**
	 * 根据条件获取Institution列表
	 * @param institution 查询条件
	 * @return List<Institution> 机构列表
	 * @throws Exception
	 */
	public List<Institution> queryInstitutionList(Institution institution,PageEntity page) throws Exception;
	
	/**
	 * 添加机构
	 * @param institution 机构信息
	 * @throws Exception
	 */
	public void addInstitution(Institution institution) throws Exception;
	
	/**
	 * 批量删除机构
	 * @param institutionIds 机构id 集合
	 * @throws Exception
	 */
	public void deleteInstitutionBatch(String institutionIds) throws Exception;
	
	/**
	 * 根据id获取机构信息
	 * @param id 机构id
	 * @return 机构信息
	 * @throws Exception
	 */
	public Institution getInstitutionById(Long id) throws Exception;
	
	/**
	 * 更新机构信息
	 * @param institution
	 * @throws Exception
	 */
	public void updateInstitution(Institution institution) throws Exception;
	
	/**
	 * 更新机构审核状态
	 * @param institution
	 * @throws Exception
	 */
	public void updateInstitutionStatus(Institution institution) throws Exception;
	
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
