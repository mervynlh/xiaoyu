package com.yizhilu.os.edu.dao.impl.teacher;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.dao.teacher.TeacherClassHourDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * TeacherClassHour
 * User: qinggang.liu bis@foxmail.com
 * Date: 2015-08-13
 */
 @Repository("teacherClassHourDao")
public class TeacherClassHourDaoImpl extends GenericDaoImpl implements TeacherClassHourDao{
	 
    public java.lang.Long addTeacherClassHour(Map<String, Object> map) {
        return this.insert("TeacherClassHourMapper.addCourseTeacherBatch",map);
    }

    public void deleteTeacherClassHourById(Map<String, Object> map){
        this.delete("TeacherClassHourMapper.deleteTeacherClassHourById",map);
    }

    public void updateTeacherClassHour(Map<String, Object> map) {
        this.update("TeacherClassHourMapper.updateTeacherClassHourStu",map);
    }

    public TeacherClassHour getTeacherClassHourById(Map<String, Object> map) {
        return this.selectOne("TeacherClassHourMapper.getTeacherClassHourById",map);
    }

    public List<TeacherClassHour> getTeacherClassHourList(Map<String, Object> map) {
        return this.selectList("TeacherClassHourMapper.getTeacherClassHourList",map);
    }
	
	public void deleteTeacherClassHourByDate(String serial) {
		this.delete("TeacherClassHourMapper.deleteTeacherClassHourByDate", serial);
	}

	@Override
	public List<TeacherClassHour> getTeacherClassHourByCondition(Map<String, Object> map) {
		return this.selectList("TeacherClassHourMapper.getTeacherClassHourByCondition", map);
	}

	@Override
	public void updateTeacherClassHourStatus(Map<String, Object> map) {
		this.update("TeacherClassHourMapper.updateTeacherClassHourStatus",map);
	}

}
