package com.yizhilu.os.edu.dao.impl.course;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.course.TeacherTimeTableDao;
import com.yizhilu.os.edu.entity.course.TeacherTimeTable;

@Repository("teacherTimeTableDao")
public class TeacherTimeTableDaoImpl extends GenericDaoImpl implements TeacherTimeTableDao {

	@Override
	public void createTeacherTimeTable(TeacherTimeTable teacherTimeTable) {
		// TODO Auto-generated method stub
		insert("TeacherTimeTableMapper.createTeacherTimeTable", teacherTimeTable);
	}

}
