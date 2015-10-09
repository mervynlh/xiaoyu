package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetail;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetailDTO;
import com.yizhilu.os.edu.dao.website.WebsiteTeacherDetailDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * WebsiteTeacherDetail
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("websiteTeacherDetailDao")
public class WebsiteTeacherDetailDaoImpl extends GenericDaoImpl implements WebsiteTeacherDetailDao{

	/**
	 * 添加推荐课程
	 * @param websiteTeacherDetail
	 */
    public void addWebsiteTeacherDetail(List<WebsiteTeacherDetail> websiteTeacherDetails){
    	this.insert("WebsiteTeacherDetailMapper.createWebsiteTeacherDetail", websiteTeacherDetails);
    }

    /**
     * 查询推荐课程列表
     * @param websiteTeacherDetail
     * @param page
     * @return
     */
    public List<WebsiteTeacherDetailDTO> queryWebsiteTeacherDetailList(WebsiteTeacherDetailDTO websiteTeacherDetailDTO,PageEntity page){
    	return this.queryForListPage("WebsiteTeacherDetailMapper.queryWebsiteTeacherDetailList",websiteTeacherDetailDTO ,page );
    }

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteTeacherDetail(Long id){
    	this.delete("WebsiteTeacherDetailMapper.delWebsiteTeacherDetailById", id);
    }

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacherDetailDTO queryWebsiteTeacherDetailById(Long id){
    	return this.selectOne("WebsiteTeacherDetailMapper.getWebsiteTeacherDetailDTOById", id);
    }

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteTeacherDetail(WebsiteTeacherDetail websiteTeacherDetail){
    	this.update("WebsiteTeacherDetailMapper.updateWebsiteTeacherDetail", websiteTeacherDetail);
    }
    
    /**
     * @param id
     */
    public List<WebsiteTeacherDetail> getWebsiteTeacherDetails(Long id){
    	return this.selectList("WebsiteTeacherDetailMapper.getWebsiteTeacherDetails", id);
    }
    /**
     * web推荐课程集合
     * @param id
     */
    public List<WebsiteTeacherDetailDTO> getWebWebsiteTeacherDetails(){
    	return this.selectList("WebsiteTeacherDetailMapper.getWebWebsiteTeacherDetails",0);
    }
    /**
     * 根据条件删除推荐课程
     */
    public void deleteWebsiteTeacherDetail(WebsiteTeacherDetailDTO websiteTeacherDetailDTO){
    	this.delete("WebsiteTeacherDetailMapper.deleteWebsiteTeacherDetail", websiteTeacherDetailDTO);
    }
}
