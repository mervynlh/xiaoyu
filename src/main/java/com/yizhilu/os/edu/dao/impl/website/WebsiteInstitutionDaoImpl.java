package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.website.WebsiteInstitutionDao;
import com.yizhilu.os.edu.entity.website.WebsiteInstitution;
import com.yizhilu.os.edu.entity.website.WebsiteInstitutionDTO;

/**
 * 推荐机构Dao实现类
 * @author dingkai
 * @date 2015年9月22日
 */
 @Repository("websiteInstitutionDao")
public class WebsiteInstitutionDaoImpl extends GenericDaoImpl implements WebsiteInstitutionDao{

	@Override
	public void addWebsiteInstitution(String ids) {
		this.insert("WebsiteInstitutionMapper.addWebsiteInstitution", ids.replaceAll(" " , "").split(","));
	}

	@Override
	public void deleteWebsiteInstitution(String ids) {
		this.delete("WebsiteInstitutionMapper.deleteWebsiteInstitution", ids.replaceAll(" " , "").split(","));
	}

	@Override
	public void updateWebsiteInstitution(WebsiteInstitution websiteInstitution) {
		this.update("WebsiteInstitutionMapper.updateWebsiteInstitution", websiteInstitution);
	}

	@Override
	public WebsiteInstitutionDTO getWebsiteInstitutionById(Long id){
		return this.selectOne("WebsiteInstitutionMapper.getWebsiteInstitutionById", id);
	}
	
	@Override
	public List<WebsiteInstitutionDTO> queryWebsiteInstitutionList(WebsiteInstitution websiteInstitution,
			PageEntity page) {
		return this.queryForListPage("WebsiteInstitutionMapper.queryWebsiteInstitutionList", websiteInstitution, page);
	}

	@Override
	public List<WebsiteInstitutionDTO> getWebsiteInstitutionList() {
		return this.selectList("WebsiteInstitutionMapper.getWebsiteInstitutionList", null);
	}
}
