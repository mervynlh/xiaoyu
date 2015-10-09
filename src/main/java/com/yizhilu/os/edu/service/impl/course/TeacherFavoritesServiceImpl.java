package com.yizhilu.os.edu.service.impl.course;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.web.WebContants;
import com.yizhilu.os.edu.dao.course.TeacherFavoritesDao;
import com.yizhilu.os.edu.entity.course.TeacherDto;
import com.yizhilu.os.edu.entity.course.TeacherFavorites;
import com.yizhilu.os.edu.service.course.TeacherFavoritesService;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;


/**
 * @author wangzhuang
 */
@Service("teacherFavoritesService")
public class TeacherFavoritesServiceImpl implements TeacherFavoritesService {

	@Autowired
	private TeacherFavoritesDao teacherFavoritesDao;
	@Autowired
	private TeacherProfileService teacherProfileService;
	
	public String addTeacherFavorites(Long userId,Long teacherId) {
		// 检查是否收藏过
		TeacherFavorites teacherFavorites = new TeacherFavorites();
		teacherFavorites.setUserId(userId);
		teacherFavorites.setTeacherId(teacherId);
		List<TeacherFavorites> teacherFavoritesList = teacherFavoritesDao
				.checkTeacherFavoritesByUserIdAndTeacherId(teacherFavorites);
		if (ObjectUtils.isNotNull(teacherFavoritesList)) {
			// 收藏过
			return WebContants.OWNED;
		}
		teacherFavorites.setAddTime(new Date());
		teacherFavoritesDao.addTeacherFavorite(teacherFavorites);
		teacherProfileService.updateTeacherCollectAdd(teacherId);
		return WebContants.SUCCESS;
	}
	
    /**
     * 通过用户id和教师id检查是否收藏过
     *  
     */

	public List<TeacherFavorites> checkTeacherFavoritesByUserIdAndTeacherId(
			TeacherFavorites teacherFavorites) {
		return teacherFavoritesDao
				.checkTeacherFavoritesByUserIdAndTeacherId(teacherFavorites);
	}

	@Override
	public List<TeacherDto> getTeacherFavoritesByUserId(Long userId,PageEntity page) {
		return this.teacherFavoritesDao.getTeacherFavoritesByUserId(userId,page);
	}

	@Override
	public void deleteTeacherFavorites(Long id,Long teacherId) {
		this.teacherFavoritesDao.deleteTeacherFavorites(id);
		this.teacherProfileService.updateTeacherCollectSub(teacherId);
	}
	
}