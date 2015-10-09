package com.yizhilu.os.edu.service.website;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.website.WebsiteInstitution;
import com.yizhilu.os.edu.entity.website.WebsiteInstitutionDTO;

/**
 * 推荐机构Service
 * @author dingkai
 * @date 2015年9月22日
 */
public interface WebsiteInstitutionService {
	
	/**
	 * 批量添加推荐机构
	 * @param map 
	 */
	public void addWebsiteInstitution(String ids);
	
	/**
	 * 批量删除推荐机构
	 * @param ids 机构id集合
	 */
	public void deleteWebsiteInstitution(String ids);
	
	/**
	 * 修改推荐机构排序
	 * @param websiteInstitution
	 */
	public void updateWebsiteInstitution(WebsiteInstitution websiteInstitution);
	
	/**
	 *  根据id查询推荐机构
	 * @return 推荐机构详情
	 */
	public WebsiteInstitutionDTO getWebsiteInstitutionById(Long id);
	
	/**
	 *  查询推荐机构列表 前台
	 * @return 推荐机构列表
	 */
	public List<WebsiteInstitutionDTO> getWebsiteInstitutionList();
	
	/**
	 * 查询推荐机构列表 后台
	 * @param websiteInstitution 推荐机构
	 * @param page 分页
	 * @return
	 */
	public List<WebsiteInstitutionDTO> queryWebsiteInstitutionList(WebsiteInstitution websiteInstitution,PageEntity page);
}