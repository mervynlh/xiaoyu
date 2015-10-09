package com.yizhilu.os.edu.dao.course;

import com.yizhilu.os.edu.entity.course.TeacherRating;

/**
 * @author wangzhuang
 */

public interface TeacherRatingDao {

	/**
	 * 通过几星 查询teacherRating
	 * @param id
	 * @return
	 */
	public TeacherRating getTeacherRatingByRating(Long id);
	
	/**
	 * 修改TeacherRating 
	 */
	public void updateTeacherRating(TeacherRating teacherRating);
	/**
	 * 通过id删除星级评定
	 * @param id
	 */
	public void deleteTeacherRatingById(Long id);
	
}
