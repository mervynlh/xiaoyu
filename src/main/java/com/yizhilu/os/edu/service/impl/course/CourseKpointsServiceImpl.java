package com.yizhilu.os.edu.service.impl.course;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yizhilu.os.edu.dao.course.CourseKpointsDao;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseService;
@Service("courseKpointsService")
public class CourseKpointsServiceImpl implements CourseKpointsService {

	@Autowired
	private CourseKpointsDao courseKpointsDao;
	@Autowired
	private CourseService courseService;
	
	@Override
	public void createCourseKpoints(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		courseKpoints.setStatus(0);
		courseKpoints.setAddTime(new Date());
		courseKpoints.setUpdateTime(new Date());
		courseKpointsDao.createCourseKpoints(courseKpoints);
		courseService.updateLessionnum(courseKpoints.getCourseId());
	}
	@Override
	public List<CourseKpoints> queryKpointsByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return courseKpointsDao.queryKpointsByCourseId(courseId);
	}
	@Override
	public void delKpointById(Long id) {
		// TODO Auto-generated method stub
		courseKpointsDao.delKpointById(id);
	}
	@Override
	public int queryConflictTime(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		return courseKpointsDao.queryConflictTime(courseKpoints);
	}
	@Override
	public CourseKpoints queryKpointsById(Long id) {
		// TODO Auto-generated method stub
		return courseKpointsDao.queryKpointsById(id);
	}
	@Override
	public void updateKpoint(CourseKpoints courseKpoints) {
		// TODO Auto-generated method stub
		courseKpointsDao.updateKpoint(courseKpoints);
	}
	@Override
	public void delByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		courseKpointsDao.delByCourseId(courseId);
	}
	@Override
	public int queryOpenStatu(Long courseId) {
		// TODO Auto-generated method stub
		return courseKpointsDao.queryOpenStatu(courseId);
	}
}