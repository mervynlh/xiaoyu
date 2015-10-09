package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.course.CourseKpointsDao;
import com.yizhilu.os.edu.entity.course.CourseKpoints;

@Repository("courseKpointsDao")
public class CourseKpointsDaoImpl extends GenericDaoImpl implements CourseKpointsDao {

	@Override
	public void createCourseKpoints(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		this.insert("CourseKpointsMapper.createCoursekpoints", courseKpoints);
	}

	@Override
	public List<CourseKpoints> queryKpointsByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return selectList("CourseKpointsMapper.queryKpointsByCourseId", courseId);
	}

	@Override
	public void delKpointById(Long id) {
		// TODO Auto-generated method stub
		delete("CourseKpointsMapper.delKpointById", id);
	}

	@Override
	public int queryConflictTime(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		return selectOne("CourseKpointsMapper.queryConflictTime", courseKpoints);
	}

	@Override
	public CourseKpoints queryKpointsById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("CourseKpointsMapper.queryKpointsById",id);
	}

	@Override
	public void updateKpoint(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		update("CourseKpointsMapper.updateKpoint", courseKpoints);
	}

	@Override
	public void delByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		delete("CourseKpointsMapper.delByCourseId", courseId);
	}

	@Override
	public int queryOpenStatu(Long courseId) {
		// TODO Auto-generated method stub
		return selectOne("CourseKpointsMapper.queryOpenStatu", courseId);
	}
}
