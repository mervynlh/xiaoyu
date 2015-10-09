package com.yizhilu.os.edu.service.teacher;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;

import javax.sound.midi.VoiceStatus;

/**
 * TeacherClassHour管理接口
 * User: qinggang.liu
 * Date: 2015-08-13
 */
public interface TeacherClassHourService {
	
    /**
     * 添加TeacherClassHour
     * @param teacherClassHour 要添加的TeacherClassHour
     * @return id
     */
    public String addTeacherClassHour(String teacherClassHour, Long teacherId) throws ParseException;

    /**
     * 根据id删除一个TeacherClassHour
     * @param id 要删除的id
     */
    public void deleteTeacherClassHourById(Long id, String serial);
    
    /**
     * 删除本月以前的所有TeacherClassHour
     * @param id 要删除的id
     */
    public void deleteTeacherClassHourByDate(String serial);

    /**
     * 修改TeacherClassHour
     * @param teacherClassHour 要修改的TeacherClassHour
     */
    public void updateTeacherClassHour(TeacherClassHour teacherClassHour);
    
    /**
     * 教师修改TeacherClassHour
     * @param teacherClassHour 要修改的TeacherClassHour
     */
    public void updateTeacherClassHourStatus(TeacherClassHour teacherClassHour);
    
    /**
     * 根据id获取单个TeacherClassHour对象
     * @param id 要查询的id
     * @return TeacherClassHour
     */
    public TeacherClassHour getTeacherClassHourById(Long id, String serial);

    /**
     * 根据条件获取TeacherClassHour列表
     * @param teacherClassHour 查询条件
     * @return List<TeacherClassHour>
     */
    public List<List<TeacherClassHour>> getTeacherClassHourList(TeacherClassHour teacherClassHour) throws ParseException;
    
    /**
     * 根据条件查询课时列表
     * @param starDate 课时开始时间
     * @param endDate  课时结束时间
     * @param teacherId 教师ID
     * @return
     */
    public List<TeacherClassHour> getTeacherClassHourByCondition(Date starDate, Date endDate, Long teacherId) throws ParseException;

    /**
     * 教师添加发布班课时间
     * @param starDate
     * @param endDate
     * @param teacherId
     * @throws ParseException
     */
    public void classCourseHourAdd(Date starDate, Date endDate, Long teacherId) throws ParseException;

    public TeacherClassHour checkAddOrUpdate(String dateDay, String time, Long teacherId);
    
    /**
     * 学生约课
     * @param classHour
     * @param teacherId
     * @param userId
     * @return
     */
    public String updateStudentConsultClass(String classHour, Long teacherId, Long userId) throws ParseException;
    /**
     * 是否可约
     * @param dateValue
     * @return
     * @throws ParseException 
     */
	public Map<String, Object> checkIfSelect(String dateValue,Long teacherId) throws ParseException;
	
}