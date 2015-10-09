package com.yizhilu.os.edu.dao.impl.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.website.WebsiteTruncateDao;
import com.yizhilu.os.edu.entity.website.WebsiteTruncate;

/**
 * 
 * @author jjl
 * 
 */
@Repository("websiteTruncateDao")
public class WebsiteTruncateDaoImpl extends GenericDaoImpl implements WebsiteTruncateDao{



	/**
	 * 添加清空表
	 *
	 * @param WebsiteTruncate
	 */
	public void insertWebsiteTruncate(WebsiteTruncate websiteTruncate){
		this.insert("WebsiteTruncateMapper.createTruncate", websiteTruncate);
	}

	/**
	 * 清空表分页列表
	 * 
	 * @param websiteTruncate
	 * @param page
	 * @return
	 */
	public List<WebsiteTruncate> getTruncatePageList(WebsiteTruncate websiteTruncate, PageEntity page){
		return this.queryForListPage("WebsiteTruncateMapper.getTruncatePageList", websiteTruncate, page);
	}

	/**
	 * 删除清空表
	 * 
	 * @param ids
	 */
	public void delTruncateByIds(String ids){
		this.delete("WebsiteTruncateMapper.delTruncateByIds", ids);
	}

	/**
	 * 查询清空表
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteTruncate getWebsiteTruncateById(Long id){
		return this.selectOne("WebsiteTruncateMapper.getTruncateById", id);
	}

	/**
	 * 更新清空表
	 * 
	 * @param WebsiteTruncate
	 */
	public void updateWebsiteTruncate(WebsiteTruncate websiteTruncate){
		this.update("WebsiteTruncateMapper.updateTruncate", websiteTruncate);
	}

	/**
	 * 清空表
	 * 
	 * @param ids
	 */
	public void truncateTable(String tableName){
		this.delete("WebsiteTruncateMapper.truncateTable", tableName);
	}
	
	/**
	 * 查询清空表集合
	 * 
	 * @param ids
	 */
	public List<WebsiteTruncate> getTruncateList(String ids,String type){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("type", type);
		return this.selectList("WebsiteTruncateMapper.getTruncateList", map);
	}
}
