package com.yizhilu.os.edu.dao.impl.website;

import java.util.List;

import com.yizhilu.os.edu.entity.website.WebsiteTeacher;
import com.yizhilu.os.edu.dao.website.WebsiteTeacherDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * WebsiteTeacher
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("websiteTeacherDao")
public class WebsiteTeacherDaoImpl extends GenericDaoImpl implements WebsiteTeacherDao{

    
    /**
     * 推荐课程分类列表
     * @param websiteTeacher
     * @param page
     * @return
     */
    public List<WebsiteTeacher> queryWebsiteTeacherList(){
    	return this.selectList("WebsiteTeacherMapper.queryWebsiteTeacherList",0);
    }

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacher queryWebsiteTeacherById(Long id){
    	return this.selectOne("WebsiteTeacherMapper.getWebsiteTeacherById", id);
    }
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    public void updateWebsiteTeacherById(WebsiteTeacher websiteTeacher){
    	this.update("WebsiteTeacherMapper.updateWebsiteTeacher", websiteTeacher);
    }
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    public void addWebsiteTeacher(WebsiteTeacher websiteTeacher){
    	this.insert("WebsiteTeacherMapper.createWebsiteTeacher", websiteTeacher);
    	
    }

    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteTeacherDetailById(Long id){
    	this.delete("WebsiteTeacherMapper.deleteWebsiteTeacherById", id);
    	this.delete("WebsiteTeacherDetailMapper.delWebsiteTeacherDetails", id);
    }
}
