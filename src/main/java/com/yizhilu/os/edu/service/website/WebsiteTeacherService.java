package com.yizhilu.os.edu.service.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteTeacher;

/**
 * WebsiteTeacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface WebsiteTeacherService {

	 /**
     * 推荐课程分类列表
     * @param websiteTeacher
     * @param page
     * @return
     */
    public List<WebsiteTeacher> queryWebsiteTeacherList();

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacher queryWebsiteTeacherById(Long id);
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    public void updateWebsiteTeacherById(WebsiteTeacher websiteTeacher);
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    public void addWebsiteTeacher(WebsiteTeacher websiteTeacher);

    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteTeacherDetailById(Long id);
}