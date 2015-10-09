package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.course.CourseFavoritesDao;
import com.yizhilu.os.edu.entity.course.CourseFavorites;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;

/**
 *
 * CourseFavorites
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("courseFavoritesDao")
public class CourseFavoritesDaoImpl extends GenericDaoImpl implements CourseFavoritesDao{

    public java.lang.Long addCourseFavorites(CourseFavorites courseFavorites) {
        return this.insert("CourseFavoritesMapper.createCourseFavorites",courseFavorites);
    }

    public void deleteCourseFavoritesById(String ids){
        this.delete("CourseFavoritesMapper.deleteCourseFavoritesById",ids);
    }

    public void updateCourseFavorites(CourseFavorites courseFavorites) {
        this.update("CourseFavoritesMapper.updateCourseFavorites",courseFavorites);
    }

    public CourseFavorites getCourseFavoritesById(Long id) {
        return this.selectOne("CourseFavoritesMapper.getCourseFavoritesById",id);
    }

    public List<CourseFavorites> getCourseFavoritesList(CourseFavorites courseFavorites) {
        return this.selectList("CourseFavoritesMapper.getCourseFavoritesList",courseFavorites);
    }
    /**
     * 通过用户Id和课程Id检查是否收藏过
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    public List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(CourseFavorites courseFavorites){
        return this.selectList("CourseFavoritesMapper.checkCourseFavoritesByUserIdAndCourseId",courseFavorites);
    }
    
    @Override
    public List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId,
    		PageEntity page) {
    	return this.queryForListPage("CourseFavoritesMapper.getCourseFavoritesByUserId", userId, page);
    }
    @Override
    public List<FavouriteCourseDTO> getMoreFavouriteCourse() {
    	return this.selectList("CourseFavoritesMapper.getMoreFavouriteCourse", null);
    }
    
}
