package com.yizhilu.os.edu.dao.course;

import java.util.List;

import com.yizhilu.os.edu.entity.course.CourseMinus;


/**
 * 课时优惠
 * @author Administrator
 * 2015-8-5
 */
public interface CourseMinusDao {
    /**
     * 创建课时优惠
     * @param courseMinus
     */
    public void createCourseMinus(CourseMinus courseMinus);
    /**
     * 根据id查询课时优惠
     * @param id
     * @return
     */
    public CourseMinus queryCourseMinusById(long id);
    /**
     * 修改课时优惠
     * @param courseMinus
     */
    public void updateCourseMinus(CourseMinus courseMinus);
    /**
     * 查询课程下的优惠信息
     * @param courseMinus
     * @return
     */
    public List<CourseMinus> queryCourseMinusCondition(CourseMinus courseMinus);
    /**
     * 删除课时优惠
     * @param minusId
     */
    public void delMinusById(Long minusId);
    /**
     * 查询课程下的优惠信息（限制课时倒序）
     * @param courseMinus
     * @return
     */
    public List<CourseMinus> queryCourseMinusConditionForOrder(CourseMinus courseMinus);
}