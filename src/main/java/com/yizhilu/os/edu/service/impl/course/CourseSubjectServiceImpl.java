package com.yizhilu.os.edu.service.impl.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yizhilu.os.edu.entity.course.CourseSubject;
import com.yizhilu.os.edu.dao.course.CourseSubjectDao;
import com.yizhilu.os.edu.service.course.CourseSubjectService;
/**
 * CourseSubject管理接口
 * User: qinggang.liu
 * Date: 2014-09-12
 */
@Service("CourseSubjectService")
public class CourseSubjectServiceImpl implements CourseSubjectService{

 	@Autowired
    private CourseSubjectDao CourseSubjectDao;
    
    /**
     * 添加CourseSubject
     * @param CourseSubject 要添加的CourseSubject
     * @return id
     */
    public java.lang.Long addCourseSubject(CourseSubject CourseSubject){
    	return CourseSubjectDao.addCourseSubject(CourseSubject);
    }

    /**
     * 根据id删除一个CourseSubject
     * @param id 要删除的id
     */
    public void deleteCourseSubjectById(Long id){
    	 CourseSubjectDao.deleteCourseSubjectById(id);
    }
    /**
     * 根据条件删除一个CourseSubject
     */
    public void deleteCourseSubject(CourseSubject CourseSubject){
    	CourseSubjectDao.deleteCourseSubject(CourseSubject);
    }
    /**
     * 修改CourseSubject
     * @param CourseSubject 要修改的CourseSubject
     */
    public void updateCourseSubject(CourseSubject CourseSubject){
     	CourseSubjectDao.updateCourseSubject(CourseSubject);
    }

    /**
     * 根据id获取单个CourseSubject对象
     * @param id 要查询的id
     * @return CourseSubject
     */
    public CourseSubject getCourseSubjectById(Long id){
    	return CourseSubjectDao.getCourseSubjectById( id);
    }

    /**
     * 根据条件获取CourseSubject列表
     * @param CourseSubject 查询条件
     * @return List<CourseSubject>
     */
    public List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject){
    	return CourseSubjectDao.getCourseSubjectList(CourseSubject);
    }
}