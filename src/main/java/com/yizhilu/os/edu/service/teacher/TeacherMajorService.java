package com.yizhilu.os.edu.service.teacher;

import java.util.List;

import com.yizhilu.os.edu.entity.teacher.TeacherMajor;

/**
 * TeacherMajor管理类
 * @author WangKaiping
 * 
 */
public interface TeacherMajorService {
	
	/**
	 * 批量添加教师科目
	 * @param teacherMajor
	 */
	public void addTeacherMajorBatch(List<TeacherMajor> teacherMajor);
	
	/**
	 * 根据教师ID查询该教师教授的科目列表
	 * @param teacherId
	 * @return
	 */
	public List<TeacherMajor> queryTeacherMajorByTeacherId(Long teacherId);
	
	/**
	 * 根据教师ID删除科目
	 * @param teacherId
	 */
	public void delTeacherMajorByTeacherId(Long teacherId);
}
