package com.yizhilu.os.edu.service.course;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.user.UserExpand;

import java.util.List;
import java.util.Map;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface CourseService {

    /**
     * 添加Course
     *
     * @param course 要添加的Course
     * @return id
     */
    public java.lang.Long addCourse(Course course);

    /**
     * 根据id删除一个Course
     *
     * @param id 要删除的id
     */
    public void deleteCourseById(Long id);

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    public Course getCourseById(Long id);


    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<Course> getCourseList(Course course);
    
    
    /**
     * @param course
     * @return
     */
    public List<Course> getCourseListByBro();
    

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page);
    
    /**
     * 首页获取课程
     *
     * @param recommendId
     * @return List<Course>
     * @throws Exception
     */
    public Map<String, List<CourseDto>> getCourseListByHomePage(Long recommendId) throws Exception;

    /**
     * 获得项目专业限制的所有课程
     * @param subjectId
     * @param courseIds
     * @return
     */
    public List<Course> getCouponSubjectCourse(Long subjectId,String courseIds);
    /**
     * 过滤直播的课程
     */
    public List<CourseDto> filtrationLive(List<CourseDto> courseDtoList);
    /**
     * 删除收藏课程
     * @param courseIds
     */
    public void delFavouriteCourseByIds(String courseIds);
    
    /**
    * app查询课程列表专用 
    * @param map 查询条件
    * @param page 分页条件
    * @return
    */
   public List<Map<String,Object>> queryAppAllCourse(Map<String,Object> map,PageEntity page);

   
   /**
    * 根据课程ID串，查询课程列表
    * @param courseIds
    * @return
    */
   public List<Course> queryCourseListByIds(String courseIds);
   
   /**
    * 根据课程ID，查询用户列表信息
    * @param courseIds
    * @return
    */
   public List<UserExpand> queryUserExpandListByCourseId (Long courseId);
   /**
    * 审核/关闭课程
    * @param course
    */
   public void verifyCourse(Long isavaliable, String ids);
   /**
    * 审核全部课程
    */
   public void verifyAllCourse();
   /**
    * 教师详情选课查询课程信息 
    * @return
    */
   public List<Map<String,Object>> queryCourseToTeacher(QueryCourse course);
   
   /**
    * 根据教师id查询一对一 
    * @param teacherId
    * @return
    */
   public List<Course> queryOneToOneByTeacherId(Long teacherId);
   /**
    * 老师修改一对一课程信息
    * @param course
    */
   public void updateCourseTeacher(Course course);
   /**
    * 根据教师id查询班课
    * @param teacherId
    * @return
    */
   public List<Course> queryClassCourseByTeacherPage(QueryCourse queryCourse,PageEntity page);
   /**
    * 讲师修改班课信息
    * @param course
    */
   public void updateClassCourseTeacher(Course course);
  
   /**
    * 根据id查询班课信息
    * @param courseId 班课id
    * @return
    */
   public Course getClassCourseById(Long courseId);
   /**
    * 更新课节数
    * @param courseId
    */
   public void updateLessionnum(Long courseId);
   /**
    * 查询是否有课程使用该地址
    * @param addressId
    * @return
    */
   public int quertyCourseNumByAddress(Long addressId);
   /**
    * 更新班课报名人数
    * @param courseId
    */
   public void updateJoinNum(Long courseId);
   /**
    * 查询教师开课专业是否已存在课程
    * @param map
    * @return
    */
   public int querySubjectMajorConflict(Long subjectMajorId,Long teacherId,String sellType,Long courseId);
   /**
    * 更新班课课程报名状态
    * @param map
    */
   public void updateFinshStatu(String isFinsh,Long courseId);
}