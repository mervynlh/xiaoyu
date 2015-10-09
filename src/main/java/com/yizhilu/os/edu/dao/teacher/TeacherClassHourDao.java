package com.yizhilu.os.edu.dao.teacher;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;

/**
 * TeacherClassHour管理接口
 * User: qinggang.liu
 * Date: 2015-08-13
 */
public interface TeacherClassHourDao {
	
    /**
     * 批量添加TeacherClassHour
     * @param teacherClassHour 要添加的TeacherClassHour
     * @return id
     */
    public java.lang.Long addTeacherClassHour(Map<String, Object> map);

    /**
     * 根据id删除一个TeacherClassHour
     * @param id 要删除的id
     */
    public void deleteTeacherClassHourById(Map<String, Object> map);
    
    /**
     * 删除本月以前的所有TeacherClassHour
     * @param id 要删除的id
     */
    public void deleteTeacherClassHourByDate(String serial);

    /**
     * 修改TeacherClassHour
     * @param teacherClassHour 要修改的TeacherClassHour
     */
    public void updateTeacherClassHour(Map<String, Object> map);
    
    /**
     * 教师修改TeacherClassHour
     * @param teacherClassHour 要修改的TeacherClassHour
     */
    public void updateTeacherClassHourStatus(Map<String, Object> map);
   

    /**
     * 根据id获取单个TeacherClassHour对象
     * @param id 要查询的id
     * @return TeacherClassHour
     */
    public TeacherClassHour getTeacherClassHourById(Map<String, Object> map);

    /**
     * 根据条件获取TeacherClassHour列表
     * @param teacherClassHour 查询条件
     * @return List<TeacherClassHour>
     */
    public List<TeacherClassHour> getTeacherClassHourList(Map<String, Object> map);
    
    /**
     * 根据时间段查询
     * @param map
     * @return
     */
    public List<TeacherClassHour> getTeacherClassHourByCondition(Map<String, Object> map);
   
}