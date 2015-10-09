package com.yizhilu.os.edu.service.course;

import com.yizhilu.os.edu.entity.course.TeacherTimeTable;


/**
 * 课表安排
 * @author Administrator
 * 2015-8-5
 */
public interface TeacherTimeTableService {
	/**
	 * 创建课表信息
	 * @param teacherTimeTable
	 */
	public void createTeacherTimeTable(TeacherTimeTable teacherTimeTable);
}