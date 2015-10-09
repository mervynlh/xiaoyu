package com.yizhilu.os.edu.service.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.service.BaseService;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.dao.course.CourseTeacherDao;
import com.yizhilu.os.edu.dao.order.ShopcartDao;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.Shopcart;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.order.ShopcartService;

/**
 * Shopcart管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("shopcartService")
public class ShopcartServiceImpl extends BaseService implements ShopcartService {

    @Autowired
    private ShopcartDao shopcartDao;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTeacherDao courseTeacherDao;

    /**
     * 添加Shopcart
     * 
     * @param shopcart
     *            要添加的Shopcart
     * @return id
     */
    public List<Shopcart> addShopcart(Long goodsId, Long type, Long userId) {
        Shopcart shopcart = new Shopcart();
        shopcart.setGoodsid(Long.valueOf(goodsId));
        shopcart.setType(Long.valueOf(type));
        shopcart.setUserid(userId);
        shopcart.setAddTime(new Date());
        List<Shopcart> list = (List<Shopcart>) memCache.get(MemConstans.SHOPCART + userId);
        if (ObjectUtils.isNull(list)) {
            list = shopcartDao.getShopcartListByUserId(userId,type);
        }
        if (ObjectUtils.isNull(list)) {
            // 购物车是空的,添加到购物车中
            shopcartDao.addShopcart(shopcart);
            list = new ArrayList<Shopcart>();
            // 查询课程信息
            shopcart.setCourse(courseService.getCourseById(shopcart.getGoodsid()));
            List<Long> ids =new ArrayList<Long>();
            ids.add(shopcart.getGoodsid());
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(ids);
            // 将讲师的list放到旧的list中
//            shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getId()));
            list.add(shopcart);
        } else {
            boolean isexsits = false;
            for (Shopcart shopcart2 : list) {
                if (shopcart.getGoodsid().longValue() == shopcart2.getGoodsid().longValue() && shopcart.getType().longValue() == shopcart2.getType().longValue()) {
                    isexsits = true;
                    break;
                }
            }
            if (!isexsits) {
                // 不存在,添加到购物车中
                shopcartDao.addShopcart(shopcart);
                // 查询课程信息
                shopcart.setCourse(courseService.getCourseById(shopcart.getGoodsid()));
                List<Long> ids =new ArrayList<Long>();
                ids.add(shopcart.getGoodsid());
                // 获取讲师的list
                Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(ids);
                // 将讲师的list放到旧的list中
//                shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getId()));
                list.add(shopcart);
            }
        }
        memCache.set(MemConstans.SHOPCART + userId, list);
        return list;

    }

    /**
     * 根据id删除一个Shopcart
     * 
     * @param id
     *            要删除的id
     */
    public void deleteShopcartById(Long id, Long userId) {
        shopcartDao.deleteShopcartById(id);
        // 移除缓存中的购物车数据
        List<Shopcart> list = (List<Shopcart>) memCache.get(MemConstans.SHOPCART + userId);
        if (ObjectUtils.isNotNull(list)) {
            for (Shopcart shopcart : list) {
                if (shopcart.getId().intValue() == id.intValue()) {
                    list.remove(shopcart);
                    break;
                }
            }
            memCache.set(MemConstans.SHOPCART + userId, list);
        }
    }

    /**
     * 根据条件获取Shopcart列表
     * 
     * @param shopcart
     *            查询条件
     * @return List<Shopcart>
     */
    public List<Shopcart> getShopcartList(Long userId,Long type) {
        List<Shopcart> list = (List<Shopcart>) memCache.get(MemConstans.SHOPCART + userId);
        if (ObjectUtils.isNull(list)) {
            list = shopcartDao.getShopcartListByUserId(userId,type);
            if (ObjectUtils.isNotNull(list)) {
                List<Long> listParam = new ArrayList<Long>();
                for (Shopcart shopcart  : list) {
                    listParam.add(shopcart.getCourse().getId());
                }
                // 获取讲师的list
                Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
                // 将讲师的list放到旧的list中
                for (Shopcart shopcart  : list) {
//                    shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getId()));
                }
            }
            
            if (ObjectUtils.isNotNull(list)) {
                memCache.set(MemConstans.SHOPCART + userId, list);
            }
        }
        return list;
    }
    /**
     * 根据条件获取购物车的课程集合(优惠券专用)
     * 
     * @param shopcart
     *            查询条件
     * @return List<Shopcart>
     */
    public List<Course> getShopcartCourseList(Long userId) {
    	return  shopcartDao.getShopcartCourseList(userId);
    }
    /**
     * cookie中添加购物车
     * 
     * @param goodsId
     * @param type
     * @return
     */
    @Override
    public String addTempShopcart(Long goodsId, Long type,String json) {
        Shopcart  shopItem= new Shopcart();
        shopItem.setId(0L);
        shopItem.setGoodsid(goodsId);
        shopItem.setType(type);
        return ShopCartUtil.addItem(shopItem, json);
    }

    /**
     * cookie中删除购物车
     * 
     * @param goodsId
     * @param type
     */
    @Override
    public String deleteTempShopcart(Long goodsId, Long type,String json) {
        return ShopCartUtil.remove(goodsId, type, json);
    }
    
    /**
     * 根据条件获取Shopcart列表
     * 
     * @param shopcart
     *            查询条件
     * @return List<Shopcart>
     */
    public List<Shopcart> getTempShopcartList(String json) {
        if(ShopCartUtil.isNull(json)){
            return null;
        }
         List<Shopcart> list2 =ShopCartUtil.query(json);
        if (ObjectUtils.isNotNull(list2)) {
            List<Long> listParam = new ArrayList<Long>();
            for(Shopcart  shopcart:list2){
                shopcart.setCourse(courseService.getCourseById(shopcart.getGoodsid()));
                listParam.add(shopcart.getGoodsid());
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
            // 将讲师的list放到旧的list中
            for (Shopcart shopcart  : list2) {
//                shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getId()));
            }
        }
        return list2;
    }
    
    /**
     * 添加cookie中的购物车到数据库中
     * @param request
     * @param response
     */
    public void addTempShopCart(HttpServletRequest request,HttpServletResponse response,Long userId){
        String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
        if (!ShopCartUtil.isNull(json)) {
            List<Shopcart> list = ShopCartUtil.query(json);
            if(ObjectUtils.isNotNull(list)){
                for(Shopcart shopcart:list){
                    addShopcart(shopcart.getGoodsid(), shopcart.getType(), userId);
                }
            }
            WebUtils.deleteCookie(request, response, OrderConstans.SHOP_CART);
        }
    }
    /**
     * 清空数据库的购物车 
     * 
     * @param type
     *            要删除的类型
     */
    public void deleteShopcartByType(Long type,Long userId){
        shopcartDao.deleteShopcartByType(type, userId);
        memCache.remove(MemConstans.SHOPCART+userId);
    }
}