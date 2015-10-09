package com.yizhilu.os.edu.service.course;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.CourseFavorites;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;

/**
 * CourseFavorites管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CourseFavoritesService {

    /**
     * 添加CourseFavorites
     * @param courseFavorites 要添加的CourseFavorites
     * @return id
     */
    public String addCourseFavorites(CourseFavorites courseFavorites);

    /**
     * 根据id删除一个CourseFavorites
     * @param id 要删除的id
     */
    public void deleteCourseFavoritesById(String ids);

    /**
     * 修改CourseFavorites
     * @param courseFavorites 要修改的CourseFavorites
     */
    public void updateCourseFavorites(CourseFavorites courseFavorites);

    /**
     * 根据id获取单个CourseFavorites对象
     * @param id 要查询的id
     * @return CourseFavorites
     */
    public CourseFavorites getCourseFavoritesById(Long id);

    /**
     * 根据条件获取CourseFavorites列表
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    public List<CourseFavorites> getCourseFavoritesList(CourseFavorites courseFavorites);
    /**
     * 通过用户Id和课程Id检查是否收藏过
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    public List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(CourseFavorites courseFavorites);
    
    /**
     * 我的收藏分页
     * @param userId
     * @param page
     * @return
     */
    public List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId,PageEntity page);
    
    
    public List<FavouriteCourseDTO> getMoreFavouriteCourse();
}