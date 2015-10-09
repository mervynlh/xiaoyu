package com.yizhilu.os.edu.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.website.WebsiteTruncateDao;
import com.yizhilu.os.edu.entity.website.WebsiteTruncate;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.service.website.WebsiteTruncateService;

/**
 * 
 * @author jjl
 * 
 */
@Service("websiteTruncateService")
public class WebsiteTruncateServiceImpl implements WebsiteTruncateService {
	@Autowired
	private WebsiteTruncateDao websiteTruncateDao;

	@Autowired
	private WebsiteProfileService websiteProfileService;
	/**
	 * 添加清空表
	 *
	 * @param WebsiteTruncate
	 */
	public void insertWebsiteTruncate(WebsiteTruncate websiteTruncate) {
		websiteTruncateDao.insertWebsiteTruncate(websiteTruncate);
	}

	/**
	 * 清空表分页列表
	 * 
	 * @param websiteTruncate
	 * @param page
	 * @return
	 */
	public List<WebsiteTruncate> getTruncatePageList(WebsiteTruncate websiteTruncate, PageEntity page) {
		return websiteTruncateDao.getTruncatePageList(websiteTruncate, page);
	}

	/**
	 * 删除清空表
	 * 
	 * @param ids
	 */
	public void delTruncateByIds(String ids) {
		websiteTruncateDao.delTruncateByIds(ids);
	}

	/**
	 * 查询清空表
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteTruncate getWebsiteTruncateById(Long id) {
		return websiteTruncateDao.getWebsiteTruncateById(id);
	}

	/**
	 * 更新清空表
	 * 
	 * @param WebsiteTruncate
	 */
	public void updateWebsiteTruncate(WebsiteTruncate websiteTruncate) {
		websiteTruncateDao.updateWebsiteTruncate(websiteTruncate);
	}

	/**
	 * 清空表
	 * 
	 * @param ids
	 */
	public void truncateTableByIds(String ids,String type) {
		//清空表集合
		List<WebsiteTruncate> websiteTruncates =websiteTruncateDao.getTruncateList(ids,type);
		for(WebsiteTruncate websiteTruncate: websiteTruncates){
			if(websiteTruncate.getType().equals("web")){
				websiteTruncateDao.truncateTable(websiteTruncate.getTableName());
			}
		}
	}
}
