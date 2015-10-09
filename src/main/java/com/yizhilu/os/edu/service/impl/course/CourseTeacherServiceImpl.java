package com.yizhilu.os.edu.service.impl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.course.CourseTeacherDao;
import com.yizhilu.os.edu.entity.course.CourseProfile;
import com.yizhilu.os.edu.entity.course.CourseTeacher;
import com.yizhilu.os.edu.entity.course.QueryCourseProfile;
import com.yizhilu.os.edu.service.course.CourseTeacherService;
/**
 * CourseTeacher管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("courseTeacherService")
public class CourseTeacherServiceImpl implements CourseTeacherService{

 	@Autowired
    private CourseTeacherDao courseTeacherDao;
    
    /**
     * 添加CourseTeacher
     * @param courseTeacher 要添加的CourseTeacher
     * @return id
     */
    public java.lang.Long addCourseTeacher(CourseTeacher courseTeacher){
    	return courseTeacherDao.addCourseTeacher(courseTeacher);
    }

    /**
     * 根据id删除一个CourseTeacher
     * @param id 要删除的id
     */
    public void deleteCourseTeacherById(Long id){
    	 courseTeacherDao.deleteCourseTeacherById(id);
    }

    /**
     * 修改CourseTeacher
     * @param courseTeacher 要修改的CourseTeacher
     */
    public void updateCourseTeacher(CourseTeacher courseTeacher){
     	courseTeacherDao.updateCourseTeacher(courseTeacher);
    }

    /**
     * 根据id获取单个CourseTeacher对象
     * @param id 要查询的id
     * @return CourseTeacher
     */
    public CourseTeacher getCourseTeacherById(Long id){
    	return courseTeacherDao.getCourseTeacherById( id);
    }

    /**
     * 根据条件获取CourseTeacher列表
     * @param courseTeacher 查询条件
     * @return List<CourseTeacher>
     */
    public List<CourseTeacher> getCourseTeacherList(CourseTeacher courseTeacher){
    	return courseTeacherDao.getCourseTeacherList(courseTeacher);
    }
    /**
     * 根据条件查询课程统计
     * @param queryCourseProfile
     * @return
     */
    public List<CourseProfile> getCourseByteacher(QueryCourseProfile queryCourseProfile,PageEntity entity){
    	return courseTeacherDao.getCourseByteacher(queryCourseProfile,entity);
    }
    
}