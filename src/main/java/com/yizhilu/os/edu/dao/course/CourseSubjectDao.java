package com.yizhilu.os.edu.dao.course;
import java.util.List;
import com.yizhilu.os.edu.entity.course.CourseSubject;

/**
 * CourseSubject管理接口
 * User: qinggang.liu
 * Date: 2014-09-12
 */
public interface CourseSubjectDao {

    /**
     * 添加CourseSubject
     * @param CourseSubject 要添加的CourseSubject
     * @return id
     */
    public java.lang.Long addCourseSubject(CourseSubject CourseSubject);

    /**
     * 根据id删除一个CourseSubject
     * @param id 要删除的id
     */
    public void deleteCourseSubjectById(Long id);
    /**
     * 根据条件删除一个CourseSubject
     */
    public void deleteCourseSubject(CourseSubject CourseSubject);
    /**
     * 修改CourseSubject
     * @param CourseSubject 要修改的CourseSubject
     */
    public void updateCourseSubject(CourseSubject CourseSubject);

    /**
     * 根据id获取单个CourseSubject对象
     * @param id 要查询的id
     * @return CourseSubject
     */
    public CourseSubject getCourseSubjectById(Long id);

    /**
     * 根据条件获取CourseSubject列表
     * @param CourseSubject 查询条件
     * @return List<CourseSubject>
     */
    public List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject);
}