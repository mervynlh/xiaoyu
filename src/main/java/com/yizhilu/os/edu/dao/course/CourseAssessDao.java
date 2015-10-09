package com.yizhilu.os.edu.dao.course;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.CourseAssess;
import com.yizhilu.os.edu.entity.course.QueryCourseAssess;

/**
 * CourseAssess管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface CourseAssessDao {

	/**
	 * 添加CourseAssess
	 * 
	 * @param courseAssess
	 *            要添加的CourseAssess
	 * @return id
	 */
	public java.lang.Long addCourseAssess(CourseAssess courseAssess);

	/**
	 * 根据id删除一个CourseAssess
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseAssessById(Long id);

	/**
	 * 修改CourseAssess
	 * 
	 * @param courseAssess
	 *            要修改的CourseAssess
	 */
	public void updateCourseAssess(CourseAssess courseAssess);

	/**
	 * 根据id获取单个QueryCourseAssess对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return QueryCourseAssess
	 */
	public QueryCourseAssess getCourseAssessById(Long id);

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<CourseAssess> getCourseAssessList(CourseAssess courseAssess);

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page);

	/**
	 * 后台查询课程评论列表
	 * 
	 * @param queryCourseAssess
	 * @param page
	 * @return
	 */
	public List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page);

	/**
	 * 删除课程评论
	 * 
	 * @param courseAssessIds
	 */
	public void delCourseAssessBatch(String courseAssessIds);
}