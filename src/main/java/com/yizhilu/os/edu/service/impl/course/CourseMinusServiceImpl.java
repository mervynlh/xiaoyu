package com.yizhilu.os.edu.service.impl.course;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.course.CourseMinusDao;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.service.course.CourseMinusService;

@Service("courseMinusService")
public class CourseMinusServiceImpl implements CourseMinusService {

	@Autowired
	private CourseMinusDao courseMinusDao;
	
	@Override
	public void createCourseMinus(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		courseMinus.setCreateTime(new Date());
		courseMinusDao.createCourseMinus(courseMinus);
	}

	@Override
	public CourseMinus queryCourseMinusById(long id) {
		// TODO Auto-generated method stub
		return courseMinusDao.queryCourseMinusById(id);
	}

	@Override
	public void updateCourseMinus(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		courseMinusDao.updateCourseMinus(courseMinus);
	}

	@Override
	public List<CourseMinus> queryCourseMinusCondition(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		return courseMinusDao.queryCourseMinusCondition(courseMinus);
	}

	@Override
	public void delMinusById(Long minusId) {
		// TODO Auto-generated method stub
		courseMinusDao.delMinusById(minusId);
	}
	@Override
	public List<CourseMinus> queryCourseMinusConditionForOrder(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		return courseMinusDao.queryCourseMinusConditionForOrder(courseMinus);
	}
}