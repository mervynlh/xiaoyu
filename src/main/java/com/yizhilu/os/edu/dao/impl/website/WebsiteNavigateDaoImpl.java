package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteNavigate;
import com.yizhilu.os.edu.dao.website.WebsiteNavigateDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * WebsiteNavigateTbl
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("websiteNavigateDao")
public class WebsiteNavigateDaoImpl extends GenericDaoImpl implements WebsiteNavigateDao{

	/**
	 * 导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate){
		return this.selectList("WebsiteNavigateMapper.getWebsiteNavigate", websiteNavigate);
	}
	/**
	 * 添加导航
	 * @param WebsiteNavigate
	 */
	public void addWebsiteNavigate(WebsiteNavigate websiteNavigate){
		this.insert("WebsiteNavigateMapper.createWebsiteNavigate", websiteNavigate);
	}
	/**
	 * 冻结、解冻导航
	 * @param WebsiteNavigate
	 */
	public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate){
		this.update("WebsiteNavigateMapper.freezeWebsiteNavigate", websiteNavigate);
	}
	/**
	 * 删除导航
	 * @param id
	 */
	public void delWebsiteNavigate(Long id){
		this.delete("WebsiteNavigateMapper.delWebsiteNavigate",id);
	}
	/**
	 * 更新导航
	 * @param WebsiteNavigate
	 */
	public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate){
		this.update("WebsiteNavigateMapper.updateWebsiteNavigate", websiteNavigate);
	}
	/**
	 * id查询导航
	 * @param id
	 * @return
	 */
	public WebsiteNavigate getWebsiteNavigateById(Long id){
		return this.selectOne("WebsiteNavigateMapper.getWebsiteNavigateById", id);
	}
	/**
	 * 查询未冻结的导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebNavigate(){
		return this.selectList("WebsiteNavigateMapper.getWebNavigate",0);
	}

}
