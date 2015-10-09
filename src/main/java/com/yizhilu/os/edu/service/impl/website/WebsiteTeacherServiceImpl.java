package com.yizhilu.os.edu.service.impl.website;

import java.util.List;

import com.yizhilu.os.common.constants.MemConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.dao.website.WebsiteTeacherDao;
import com.yizhilu.os.edu.entity.website.WebsiteTeacher;
import com.yizhilu.os.edu.service.website.WebsiteTeacherService;

/**
 * WebsiteTeacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("websiteTeacherService")
public class WebsiteTeacherServiceImpl implements WebsiteTeacherService {
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private WebsiteTeacherDao websiteTeacherDao;

	 /**
     * 推荐课程分类列表
     * @param websiteTeacher
     * @param page
     * @return
     */
    public List<WebsiteTeacher> queryWebsiteTeacherList(){
    	return websiteTeacherDao.queryWebsiteTeacherList();
    }

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public WebsiteTeacher queryWebsiteTeacherById(Long id){
    	return websiteTeacherDao.queryWebsiteTeacherById(id);
    }
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    public void updateWebsiteTeacherById(WebsiteTeacher websiteTeacher){
    	websiteTeacherDao.updateWebsiteTeacherById(websiteTeacher);
    	memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    public void addWebsiteTeacher(WebsiteTeacher websiteTeacher){
    	websiteTeacherDao.addWebsiteTeacher(websiteTeacher);
    	memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }
    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteTeacherDetailById(Long id){
    	websiteTeacherDao.deleteWebsiteTeacherDetailById(id);
    	memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
    }
}