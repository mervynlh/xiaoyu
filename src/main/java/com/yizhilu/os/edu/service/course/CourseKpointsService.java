package com.yizhilu.os.edu.service.course;

import java.util.List;

import com.yizhilu.os.edu.entity.course.CourseKpoints;


/**
 * 大小班课时安排
 * @author Administrator
 * 2015-8-5
 */
public interface CourseKpointsService {
	/**
	 * 创建大小班课时安排
	 */
	public void createCourseKpoints(CourseKpoints courseKpoints);
	/**
	 * 根据课程id查询课节
	 * @param courseId
	 * @return
	 */
	public List<CourseKpoints> queryKpointsByCourseId(Long courseId);
	/**
	 * 根据id查询课节信息
	 * @param id
	 * @return
	 */
	public CourseKpoints queryKpointsById(Long id);
	/**
	 * 根据id删除课节
	 * @param id
	 */
	public void delKpointById(Long id);
	/**
	 * 查询时间是否冲突
	 * @param courseKpoints
	 * @return
	 */
	public int queryConflictTime(CourseKpoints courseKpoints);
	/**
	 * 修改课节信息
	 * @param courseKpoints
	 */
	public void updateKpoint(CourseKpoints courseKpoints);
	/**
	 * 根据课程id删除课节
	 * @param courseId
	 */
	public void delByCourseId(Long courseId);
	/**
	 * 查询班课开课状态
	 * @param courseId
	 * @return
	 */
	public int queryOpenStatu(Long courseId);
}