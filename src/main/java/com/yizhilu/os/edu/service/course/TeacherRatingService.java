package com.yizhilu.os.edu.service.course;

import com.yizhilu.os.edu.entity.course.TeacherRating;

/**
 * @author wangzhuang
 */
public interface TeacherRatingService {

	/**
	 * 通过星级获得
	 * @param id
	 * @return
	 */
	public TeacherRating getTeacherRatingByRating(Long id);
	
	/**
	 * 修改teacherRating
	 * @param teacherRating
	 */
	public void updateTeacherRating(TeacherRating teacherRating);

	/**
	 * 根据id删除一个星级评定
	 * @param id
	 */
	public void deleteTeacherRatingById(Long id);
	
}
