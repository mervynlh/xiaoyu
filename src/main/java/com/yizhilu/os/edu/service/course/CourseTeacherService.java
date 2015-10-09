package com.yizhilu.os.edu.service.course;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.CourseProfile;
import com.yizhilu.os.edu.entity.course.CourseTeacher;
import com.yizhilu.os.edu.entity.course.QueryCourseProfile;

/**
 * CourseTeacher管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CourseTeacherService {

    /**
     * 添加CourseTeacher
     * @param courseTeacher 要添加的CourseTeacher
     * @return id
     */
    public java.lang.Long addCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 根据id删除一个CourseTeacher
     * @param id 要删除的id
     */
    public void deleteCourseTeacherById(Long id);

    /**
     * 修改CourseTeacher
     * @param courseTeacher 要修改的CourseTeacher
     */
    public void updateCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 根据id获取单个CourseTeacher对象
     * @param id 要查询的id
     * @return CourseTeacher
     */
    public CourseTeacher getCourseTeacherById(Long id);

    /**
     * 根据条件获取CourseTeacher列表
     * @param courseTeacher 查询条件
     * @return List<CourseTeacher>
     */
    public List<CourseTeacher> getCourseTeacherList(CourseTeacher courseTeacher);
    /**
     * 根据条件查询课程统计
     * @param map
     * @return
     */
    public List<CourseProfile> getCourseByteacher(QueryCourseProfile queryCourseProfile,PageEntity entity);
}