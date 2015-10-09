package com.yizhilu.os.edu.service.impl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.web.WebContants;
import com.yizhilu.os.edu.dao.course.CourseFavoritesDao;
import com.yizhilu.os.edu.entity.course.CourseFavorites;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;
import com.yizhilu.os.edu.service.course.CourseFavoritesService;

/**
 * CourseFavorites管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("courseFavoritesService")
public class CourseFavoritesServiceImpl implements CourseFavoritesService {

	@Autowired
	private CourseFavoritesDao courseFavoritesDao;

	/**
	 * 添加CourseFavorites
	 * 
	 * @param courseFavorites
	 *            要添加的CourseFavorites
	 * @return id
	 */
	public String addCourseFavorites(CourseFavorites courseFavorites) {
		// 检查是否收藏过
		List<CourseFavorites> courseFavoritesList = courseFavoritesDao
				.checkCourseFavoritesByUserIdAndCourseId(courseFavorites);
		if (ObjectUtils.isNotNull(courseFavoritesList)) {
			// 收藏过
			return WebContants.OWNED;
		}
		courseFavoritesDao.addCourseFavorites(courseFavorites);
		return WebContants.SUCCESS;
	}

	/**
	 * 根据id删除一个CourseFavorites
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseFavoritesById(String ids) {
		
		courseFavoritesDao.deleteCourseFavoritesById(ids);
	}

	/**
	 * 修改CourseFavorites
	 * 
	 * @param courseFavorites
	 *            要修改的CourseFavorites
	 */
	public void updateCourseFavorites(CourseFavorites courseFavorites) {
		courseFavoritesDao.updateCourseFavorites(courseFavorites);
	}

	/**
	 * 根据id获取单个CourseFavorites对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CourseFavorites
	 */
	public CourseFavorites getCourseFavoritesById(Long id) {
		return courseFavoritesDao.getCourseFavoritesById(id);
	}

	/**
	 * 根据条件获取CourseFavorites列表
	 * 
	 * @param courseFavorites
	 *            查询条件
	 * @return List<CourseFavorites>
	 */
	public List<CourseFavorites> getCourseFavoritesList(
			CourseFavorites courseFavorites) {
		return courseFavoritesDao.getCourseFavoritesList(courseFavorites);
	}

	/**
	 * 通过用户Id和课程Id检查是否收藏过
	 * 
	 * @param courseFavorites
	 *            查询条件
	 * @return List<CourseFavorites>
	 */
	public List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(
			CourseFavorites courseFavorites) {
		return courseFavoritesDao
				.checkCourseFavoritesByUserIdAndCourseId(courseFavorites);
	}

	@Override
	public List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId,
			PageEntity page) {
		return courseFavoritesDao.getFavouriteCourseDTO(userId, page);
	}

	@Override
	public List<FavouriteCourseDTO> getMoreFavouriteCourse() {
		// TODO Auto-generated method stub
		return courseFavoritesDao.getMoreFavouriteCourse();
	}

}