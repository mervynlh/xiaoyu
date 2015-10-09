package com.yizhilu.os.edu.service.teacher;

import java.util.List;

import com.yizhilu.os.edu.entity.teacher.TeacherSubject;

/**
 * TeacherSubject管理类
 * @author WangKaiping
 * 
 */
public interface TeacherSubjectService {
	
	/**
	 * 批量添加教师科目
	 * @param teacherSubject
	 */
	public void addTeacherSubjectBatch(List<TeacherSubject> teacherSubject);
	
	/**
	 * 根据教师ID查询该教师教授的科目列表
	 * @param teacherId
	 * @return
	 */
	public List<TeacherSubject> queryTeacherSubjectByTeacherId(Long teacherId);
	
	/**
	 * 根据教师ID删除科目
	 * @param teacherId
	 */
	public void delTeacherSubjectByTeacherId(Long teacherId);
	/**
	 * 查询教师专业的父专业(开课用)
	 * @param teacherId
	 * @return
	 */
	public List<TeacherSubject> queryTeacherParentSubject(Long teacherId);
	/**查询教师专业的父专业(详情用)
	 * @param teacherId
	 * @return
	 */
	public List<TeacherSubject> queryTeacherSubjectByOK(Long teacherId);
}
