package com.yizhilu.os.edu.service.website;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetail;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetailDTO;

/**
 * WebsiteTeacherDetail管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface WebsiteTeacherDetailService {

	/**
	 * 添加推荐课程
	 * @param websiteTeacherDetail
	 */
    public void addWebsiteTeacherDetail(List<WebsiteTeacherDetail> websiteTeacherDetails);

    /**
     * 查询推荐课程列表
     * @param websiteTeacherDetail
     * @param page
     * @return
     */
    public List<WebsiteTeacherDetailDTO> queryWebsiteTeacherDetailList(WebsiteTeacherDetailDTO websiteTeacherDetailDTO,PageEntity page);

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteTeacherDetail(Long id);

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacherDetailDTO queryWebsiteTeacherDetailById(Long id);

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteTeacherDetail(WebsiteTeacherDetail websiteTeacherDetail);
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<WebsiteTeacherDetail> getWebsiteTeacherDetails(Long id);
}