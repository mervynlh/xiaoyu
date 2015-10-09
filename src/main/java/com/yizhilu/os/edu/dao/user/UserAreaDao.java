package com.yizhilu.os.edu.dao.user;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.user.UserArea;

/**
 * UserArea管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAreaDao {

    /**
     * 添加UserArea
     * @param userArea 要添加的UserArea
     * @return id
     */
    public java.lang.Long addUserArea(UserArea userArea);

    /**
     * 根据id删除一个UserArea
     * @param id 要删除的id
     */
    public void deleteUserAreaById(Long id);

    /**
     * 修改UserArea
     * @param userArea 要修改的UserArea
     */
    public void updateUserArea(UserArea userArea);

    /**
     * 根据id获取单个UserArea对象
     * @param id 要查询的id
     * @return UserArea
     */
    public UserArea getUserAreaById(Long id);

    /**
     * 根据条件获取UserArea列表
     * @param userArea 查询条件
     * @return List<UserArea>
     */
    public List<UserArea> getUserAreaList(UserArea userArea);
    
    /**
     * 批量修改城市区域显示状态
     * @param map
     */
    public void updateUserAreaShow(Map<String, Object> map);
    
    /**
     * 查询省份列表
     * 
     * @return List<UserArea>
     */
    public List<UserArea> getProvinceList();
    
    /**
     * 查询所有城市
     * @return
     */
    public List<UserArea> getAllUserAreaList(); 
    /**
     * 根据id串查询地区集合
     * @param query
     * @return
     */
    public List<UserArea> queryUserAreaByCondition(Map<String,Object> query);

    /**
     * 首页展示城市列表(前台页面用)
     * @return
     */
    public List<UserArea> queryUserAreaList();
}