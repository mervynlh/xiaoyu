package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.course.TeacherFavoritesDao;
import com.yizhilu.os.edu.entity.course.TeacherDto;
import com.yizhilu.os.edu.entity.course.TeacherFavorites;
import com.yizhilu.os.edu.entity.user.User;
/**
 * 
 * 
 * @author wangzhuang
 *
 */

@Repository("TeacherFavoritesDao")
public class TeacherFavoritesDaoImpl extends GenericDaoImpl implements TeacherFavoritesDao{
    /**
     * 增加收藏记录
     * 
     */
	public java.lang.Long addTeacherFavorite(TeacherFavorites teacherFavorites) {
		return this.insert("TeacherFavoritesMapper.createTeacherFavorites", teacherFavorites);
	}
	/**
	 * 通过用户id和教师id检查是否收藏过
	 * 
	 */
	public List<TeacherFavorites> checkTeacherFavoritesByUserIdAndTeacherId(TeacherFavorites teacherFavorites) {
		return this.selectList("TeacherFavoritesMapper.checkTeacherFavoritesByUserIdAndTeacherId", teacherFavorites);
	}
	@Override
	public List<TeacherDto> getTeacherFavoritesByUserId(Long userId,PageEntity page) {
		User user = new User();
		user.setId(userId);
		return this.queryForListPage("TeacherFavoritesMapper.getTeacherFavoritesByUserId", user,page);
	}
	@Override
	public void deleteTeacherFavorites(Long id) {
		this.delete("TeacherFavoritesMapper.deleteTeacherFavorites", id);
	}
	
}
