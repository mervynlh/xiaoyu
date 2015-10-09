package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.course.CourseMinusDao;
import com.yizhilu.os.edu.entity.course.CourseMinus;

@Repository("courseMinusDao")
public class CourseMinusDaoImpl extends GenericDaoImpl implements CourseMinusDao {

	@Override
	public void createCourseMinus(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		insert("CourseMinusMapper.createCourseMinus", courseMinus);
	}

	@Override
	public CourseMinus queryCourseMinusById(long id) {
		// TODO Auto-generated method stub
		return selectOne("CourseMinusMapper.queryCourseMinusById", id);
	}

	@Override
	public void updateCourseMinus(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		update("CourseMinusMapper.updateCourseMinus", courseMinus);
	}

	@Override
	public List<CourseMinus> queryCourseMinusCondition(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		return selectList("CourseMinusMapper.queryCourseMinusCondition", courseMinus);
	}
	@Override
	public List<CourseMinus> queryCourseMinusConditionForOrder(CourseMinus courseMinus) {
		// TODO Auto-generated method stub
		return selectList("CourseMinusMapper.queryCourseMinusConditionForOrder", courseMinus);
	}

	@Override
	public void delMinusById(Long minusId) {
		// TODO Auto-generated method stub
		delete("CourseMinusMapper.delMinusById",minusId);
	}

   
}
