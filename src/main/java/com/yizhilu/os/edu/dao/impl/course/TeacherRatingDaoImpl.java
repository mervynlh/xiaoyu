package com.yizhilu.os.edu.dao.impl.course;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.course.TeacherRatingDao;
import com.yizhilu.os.edu.entity.course.TeacherRating;

/**
 * @author wangzhuang
 */
@Repository("teacherRatingDao")
public class TeacherRatingDaoImpl extends GenericDaoImpl implements TeacherRatingDao {

	public TeacherRating getTeacherRatingByRating(Long id) {
		return this.selectOne("TeacherRatingMapper.getTeacherRatingByRating", id);
	}
	
	public void updateTeacherRating(TeacherRating teacherRating) {
		this.update("TeacherRating.updateTeacherRating",teacherRating);
	}
	
	public void deleteTeacherRatingById(Long id) {
		this.delete("TeacherRatingMapper.deleteTeacherRatingById", id);
	}

}
