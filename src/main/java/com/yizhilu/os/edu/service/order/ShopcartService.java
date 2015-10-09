package com.yizhilu.os.edu.service.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.order.Shopcart;

/**
 * Shopcart管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface ShopcartService {

    /**
     * 添加Shopcart
     * 
     * @param shopcart
     *            要添加的Shopcart
     * @return id
     */
    public List<Shopcart> addShopcart(Long goodsId,Long type,Long userId);

    /**
     * 根据id删除一个Shopcart
     * 
     * @param id
     *            要删除的id
     */
    public void deleteShopcartById(Long id,Long userId);

    /**
     * 查询用户的购物车
     * 
     * @param Long
     *            userId 用户id
     * @return List<Shopcart>
     */
    public List<Shopcart> getShopcartList(Long userId,Long type);
    /**
     * 查询购物车的课程集合(优惠券专用)
     * 
     * @param Long
     *            userId 用户id
     * @return List<Shopcart>
     */
    public List<Course> getShopcartCourseList(Long userId);
    
    /**
     * cookie中添加购物车
     * @param goodsId
     * @param type
     * @return
     */
    public String addTempShopcart(Long goodsId,Long type,String json);

    /**
     * cookie中删除购物车
     * @param goodsId
     * @param type
     */
    public String deleteTempShopcart(Long goodsId,Long type,String json);

    /**
     * 获取未登陆时用户的购物车
     * @param json
     * @return
     */
    public List<Shopcart> getTempShopcartList(String json) ;

    /**
     * 添加cookie中的购物车到数据库中
     * @param request
     * @param response
     */
    public void addTempShopCart(HttpServletRequest request,HttpServletResponse response,Long userId);
    
    /**
     * 清空数据库的购物车 
     * 
     * @param type
     *            要删除的类型
     */
    public void deleteShopcartByType(Long type,Long userId);
    
    
    
}