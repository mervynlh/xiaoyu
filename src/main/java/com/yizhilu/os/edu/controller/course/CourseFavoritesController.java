package com.yizhilu.os.edu.controller.course;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.CourseFavorites;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;
import com.yizhilu.os.edu.service.course.CourseFavoritesService;

/**
 * CourseFavorites管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class CourseFavoritesController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseFavoritesController.class);
    @Autowired
    private CourseFavoritesService courseFavoritesService;
    
    /**
     * 收藏课程列表循环
     */
    public static final String favouriteList = getViewPath("");
    

    /**
     * 课程收藏
     * 
     * @return
     */
    @RequestMapping("/front/addfavorites")
    @ResponseBody
    public Map<String, Object> addFavorites(HttpServletRequest request, @RequestParam("courseId") Long courseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CourseFavorites courseFavorites = new CourseFavorites();
            courseFavorites.setCourseId(courseId);
            courseFavorites.setUserId(getLoginUserId(request));
            courseFavorites.setAddTime(new Date());
            String falg = courseFavoritesService.addCourseFavorites(courseFavorites);
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("CourseFavoritesController.addFavorites", e);
            map.put("message", "false");
            return map;
        }
        return map;
    }
}