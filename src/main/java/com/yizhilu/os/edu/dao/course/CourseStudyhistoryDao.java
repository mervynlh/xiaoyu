package com.yizhilu.os.edu.dao.course;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseStudyhistory;

/**
 * CourseStudyhistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CourseStudyhistoryDao {

    /**
     * 添加CourseStudyhistory
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory);
    
    
    

    /**
     * 根据id删除一个CourseStudyhistory
     * @param id 要删除的id
     */
    public void deleteCourseStudyhistoryById(Long id);

    /**
     * 修改CourseStudyhistory
     * @param courseStudyhistory 要修改的CourseStudyhistory
     */
    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory);

    /**
     * 根据id获取单个CourseStudyhistory对象
     * @param id 要查询的id
     * @return CourseStudyhistory
     */
    public CourseStudyhistory getCourseStudyhistoryById(Long id);

    /**
     * 根据条件获取CourseStudyhistory列表
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory);
    
    
    public List<CourseStudyhistory> getCourseStudyhistoryListByCondition(CourseStudyhistory courseStudyhistory,PageEntity page);




	/**
     * 根据ids删除CourseStudyhistory
     * @param id 要删除的id
     */
    public void delCourseStudyhistory(String ids);

    /**
     * 根据课程ids和用户查询
     * @param list
     * @param userId
     * @return
     */
	public Long getCourseStudyhistoryCount(List<CourseDto> list, Long userId);
    
    
    
}