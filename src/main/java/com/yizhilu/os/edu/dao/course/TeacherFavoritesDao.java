package com.yizhilu.os.edu.dao.course;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.TeacherDto;
import com.yizhilu.os.edu.entity.course.TeacherFavorites;

/**
 * TeacherFavouriteDao 
 * @author wangzhuang
 */
public interface TeacherFavoritesDao {

    /**
     * 添加TeacherFavorites
     * @param teacherFavorites
     * @return id
     */
    public java.lang.Long addTeacherFavorite(TeacherFavorites teacherFavorites);
    
    /**
     * 通过用户Id和课程Id检查是否收藏过
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    public List<TeacherFavorites> checkTeacherFavoritesByUserIdAndTeacherId(TeacherFavorites teacherFavorites);
    /**
     * 查询教师收藏列表
     * @param userId 用户id
     * @return
     */
    public List<TeacherDto> getTeacherFavoritesByUserId(Long userId,PageEntity page);
    
    /**
     * 删除教师收藏
     * @param id 收藏id
     */ 
    public void deleteTeacherFavorites(Long id);
}