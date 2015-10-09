package com.yizhilu.os.edu.service.impl.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.course.TeacherTimeTableDao;
import com.yizhilu.os.edu.entity.course.TeacherTimeTable;
import com.yizhilu.os.edu.service.course.TeacherTimeTableService;

@Service("teacherTimeTableService")
public class TeacherTimeTableServiceImpl implements TeacherTimeTableService {

	@Autowired
	private TeacherTimeTableDao teacherTimeTableDao;

	@Override
	public void createTeacherTimeTable(TeacherTimeTable teacherTimeTable) {
		// TODO Auto-generated method stub
		teacherTimeTableDao.createTeacherTimeTable(teacherTimeTable);
	}
}