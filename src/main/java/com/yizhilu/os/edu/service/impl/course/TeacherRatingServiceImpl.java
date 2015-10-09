package com.yizhilu.os.edu.service.impl.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.course.TeacherRatingDao;
import com.yizhilu.os.edu.entity.course.TeacherRating;
import com.yizhilu.os.edu.service.course.TeacherRatingService;

/**
 * @author wangzhuang
 */
@Service("teacherRatingService")
public class TeacherRatingServiceImpl implements TeacherRatingService{

	@Autowired
	private TeacherRatingDao teacherRatingDao;

	public TeacherRating getTeacherRatingByRating(Long id) {
		return teacherRatingDao.getTeacherRatingByRating(id);
	}
	public void updateTeacherRating(TeacherRating teacherRating){
        teacherRatingDao.updateTeacherRating(teacherRating);		
	}
	
	public void deleteTeacherRatingById(Long id) {
		teacherRatingDao.deleteTeacherRatingById(id);
	}


}
