package com.yizhilu.os.edu.dao.course;

import com.yizhilu.os.edu.entity.course.TeacherTimeTable;


/**
 * 课表安排
 * @author Administrator
 * 2015-8-5
 */
public interface TeacherTimeTableDao {
	/**
	 * 创建课表信息
	 * @param teacherTimeTable
	 */
	public void createTeacherTimeTable(TeacherTimeTable teacherTimeTable);
}