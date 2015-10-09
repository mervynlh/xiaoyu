package com.yizhilu.os.edu.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.dao.website.WebsiteTeacherDetailDao;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetail;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetailDTO;
import com.yizhilu.os.edu.service.website.WebsiteTeacherDetailService;
/**
 * WebsiteTeacherDetail管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("websiteTeacherDetailService")
public class WebsiteTeacherDetailServiceImpl implements WebsiteTeacherDetailService{
	MemCache memCache = MemCache.getInstance();
 	@Autowired
    private WebsiteTeacherDetailDao websiteTeacherDetailDao;
    
 	/**
	 * 添加推荐课程
	 * @param websiteTeacherDetail
	 */
    public void addWebsiteTeacherDetail(List<WebsiteTeacherDetail> websiteTeacherDetails){
    	websiteTeacherDetailDao.addWebsiteTeacherDetail(websiteTeacherDetails);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }

    /**
     * 查询推荐课程列表
     * @param websiteTeacherDetail
     * @param page
     * @return
     */
    public List<WebsiteTeacherDetailDTO> queryWebsiteTeacherDetailList(WebsiteTeacherDetailDTO websiteTeacherDetailDTO,PageEntity page){
       return websiteTeacherDetailDao.queryWebsiteTeacherDetailList(websiteTeacherDetailDTO, page);
    }

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteTeacherDetail(Long id){
    	websiteTeacherDetailDao.deleteWebsiteTeacherDetail(id);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacherDetailDTO queryWebsiteTeacherDetailById(Long id){
    	return websiteTeacherDetailDao.queryWebsiteTeacherDetailById(id);
    }

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteTeacherDetail(WebsiteTeacherDetail websiteTeacherDetail){
    	websiteTeacherDetailDao.updateWebsiteTeacherDetail(websiteTeacherDetail);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<WebsiteTeacherDetail> getWebsiteTeacherDetails(Long id){
    	return websiteTeacherDetailDao.getWebsiteTeacherDetails(id);
    }
}