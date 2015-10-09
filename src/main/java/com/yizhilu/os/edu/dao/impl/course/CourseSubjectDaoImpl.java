package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;
import com.yizhilu.os.edu.entity.course.CourseSubject;
import com.yizhilu.os.edu.dao.course.CourseSubjectDao;
import org.springframework.stereotype.Repository;
import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CourseSubject
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-09-12
 */
 @Repository("CourseSubjectDao")
public class CourseSubjectDaoImpl extends GenericDaoImpl implements CourseSubjectDao{

    public java.lang.Long addCourseSubject(CourseSubject CourseSubject) {
        return this.insert("CourseSubjectMapper.createCourseSubject",CourseSubject);
    }

    public void deleteCourseSubjectById(Long id){
        this.delete("CourseSubjectMapper.deleteCourseSubjectById",id);
    }
    /**
     * 根据条件删除一个CourseSubject
     */
    public void deleteCourseSubject(CourseSubject courseSubject){
    	this.delete("CourseSubjectMapper.deleteCourseSubject",courseSubject);
    }
    public void updateCourseSubject(CourseSubject CourseSubject) {
        this.update("CourseSubjectMapper.updateCourseSubject",CourseSubject);
    }

    public CourseSubject getCourseSubjectById(Long id) {
        return this.selectOne("CourseSubjectMapper.getCourseSubjectById",id);
    }

    public List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject) {
        return this.selectList("CourseSubjectMapper.getCourseSubjectList",CourseSubject);
    }
}
