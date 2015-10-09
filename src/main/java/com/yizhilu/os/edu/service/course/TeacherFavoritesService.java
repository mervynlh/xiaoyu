package com.yizhilu.os.edu.service.course;

import java.util.List;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.TeacherDto;
import com.yizhilu.os.edu.entity.course.TeacherFavorites;

/**
 * TeacherFavourite管理接口
 * User: wangzhuang
 */
public interface TeacherFavoritesService {

    /**
     * 添加TeacherFavorites
     */
    public String addTeacherFavorites(Long userId,Long teacherId);

    /**
     * 通过用户Id和教师Id检查是否收藏过
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
     * @param teacherId 教师id
     */ 
    public void deleteTeacherFavorites(Long id,Long teacherId);
}