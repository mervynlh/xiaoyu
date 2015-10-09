package com.yizhilu.os.edu.dao.teacher;

import java.util.List;

import com.yizhilu.os.edu.entity.teacher.TeacherStyle;

public interface TeacherStyleDao {
	/**
	 * 教师批量添加风采信息
	 * @param styles
	 */
	public void addTeacherStyleBatch(List<TeacherStyle> styles);
	/**
	 * 查询风采列表
	 * @param query
	 * @return
	 */
	public List<TeacherStyle> queryTeacherStyleByCondition(TeacherStyle query);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delTeacherStyle(String ids);

	/**
	 * 添加单个教师风采信息
	 * @param teacherStyle
	 * @return
	 */
	public Long addTeacherStyle(TeacherStyle teacherStyle);
	/**
	 * 获取风采信息
	 * @param styleId
	 * @return
	 */
	public TeacherStyle queryStyleById(Long styleId);
}