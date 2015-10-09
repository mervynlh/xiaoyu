package com.yizhilu.os.edu.service.user;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.user.UserArea;

/**
 * UserArea管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAreaService {

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
     * 根据条件获取UserArea的map集合
     */
    public Map<Long,UserArea> getMapUserAreaList(UserArea userArea);
    
    /**
     * 批量修改UserArea城市区域显示状态
     * 
     * @param ids  主键字符串集合
     */
	public void updateUserAreaShow(String ids);
	
	/**
     * 批量删除城市区域显示状态
     * 
     * @param ids  主键字符串集合
     */
	public void delUserAreaShow(String ids);
	
	/**
     * 查询省份列表
     * 
     * @return List<UserArea>
     */
    public List<UserArea> getProvinceList();
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