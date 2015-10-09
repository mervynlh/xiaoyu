package com.yizhilu.os.edu.service.course;

import java.util.List;
import com.yizhilu.os.edu.entity.course.CourseProfile;

/**
 * CourseProfile管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface CourseProfileService {

    /**
     * 添加CourseProfile
     * 
     * @param courseProfile
     *            要添加的CourseProfile
     * @return id
     */
    public java.lang.Long addCourseProfile(CourseProfile courseProfile);

    /**
     * 根据id删除一个CourseProfile
     * 
     * @param id
     *            要删除的id
     */
    public void deleteCourseProfileById(Long id);

    /**
     * 修改CourseProfile
     * 
     * @param courseProfile
     *            要修改的CourseProfile
     */
    public void updateCourseProfile(CourseProfile courseProfile);

    /**
     * 根据条件获取CourseProfile列表
     * 
     * @param courseProfile
     *            查询条件
     * @return List<CourseProfile>
     */
    public List<CourseProfile> getCourseProfileList(CourseProfile courseProfile);
    
    /**
     * 要更新的数量类型
     * 修改CourseProfile浏览次数
     * @param courseProfile 
     */
    public void updateCourseProfile(String type,Long courseId,Long count,String operation);
    
    /**
     * 删除课程的同时删除记录
     * @param id
     */
    public void delByCourse(long id);
}