package com.yizhilu.os.edu.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.dao.user.UserAreaDao;
import com.yizhilu.os.edu.service.user.UserAreaService;
/**
 * UserArea管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("userAreaService")
public class UserAreaServiceImpl implements UserAreaService{

	MemCache memcache = MemCache.getInstance();
 	@Autowired
    private UserAreaDao userAreaDao;
    
    /**
     * 添加UserArea
     * @param userArea 要添加的UserArea
     * @return id
     */
    public java.lang.Long addUserArea(UserArea userArea){
    	return userAreaDao.addUserArea(userArea);
    }

    /**
     * 根据id删除一个UserArea
     * @param id 要删除的id
     */
    public void deleteUserAreaById(Long id){
    	 userAreaDao.deleteUserAreaById(id);
    }

    /**
     * 修改UserArea
     * @param userArea 要修改的UserArea
     */
    public void updateUserArea(UserArea userArea){
     	userAreaDao.updateUserArea(userArea);
    }

    /**
     * 根据id获取单个UserArea对象
     * @param id 要查询的id
     * @return UserArea
     */
    public UserArea getUserAreaById(Long id){
    	return userAreaDao.getUserAreaById( id);
    }

    /**
     * 根据条件获取UserArea列表
     * @param userArea 查询条件
     * @return List<UserArea>
     */
    public List<UserArea> getUserAreaList(UserArea userArea){
		List<UserArea> userAreaList = userAreaDao.getUserAreaList(userArea);
    	return userAreaList;
    }
    /**
     * 根据条件获取UserArea的map集合
     */
    public Map<Long,UserArea> getMapUserAreaList(UserArea userArea){
    	//把查询出的userArea转换成map
    	Map<Long,UserArea> map = new HashMap<Long,UserArea>();
    	List<UserArea> userAreaList = getUserAreaList(userArea);
    	//判断不为空
    	if(ObjectUtils.isNotNull(userAreaList)){
    		for(UserArea area:userAreaList){
        		map.put(area.getId(), area);
        	}
    	}
    	return map;
    }

	/**
	 * 批量修改城市区域显示状态
	 */
	public void updateUserAreaShow(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
			// 根据ids修改相应的城市区域显示
			map.put("checkShow", 2);
			map.put("ids", ids.substring(0, ids.length() - 1));
			userAreaDao.updateUserAreaShow(map);
			memcache.remove(MemConstans.SEARCH_CITY);// 清除缓存
		}
	}

	@Override
	public void delUserAreaShow(String ids) {
		// 全部取消显示
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
			map.put("checkShow", 1);
			map.put("ids", ids.substring(0, ids.length() - 1));
			userAreaDao.updateUserAreaShow(map);
			memcache.remove(MemConstans.SEARCH_CITY);// 清除缓存
		}
	}
	
	/**
	 * 查询省份列表
	 */
	public List<UserArea> getProvinceList() {
		return userAreaDao.getProvinceList();
	}

	@Override
	public List<UserArea> queryUserAreaByCondition(Map<String, Object> query) {
		// TODO Auto-generated method stub
		return userAreaDao.queryUserAreaByCondition(query);
	}

	/**
	 * 首页展示城市列表(前台页面用)
	 * @return
	 */
	public List<UserArea> queryUserAreaList(){
		List<UserArea> userAreaList = (List<UserArea>)memcache.get(MemConstans.SEARCH_CITY);
		if (ObjectUtils.isNull(userAreaList) || userAreaList.size() == 0) {
			userAreaList = userAreaDao.queryUserAreaList();
			memcache.set(MemConstans.SEARCH_CITY, userAreaList, MemConstans.SEARCH_CITY_TIME);
		}
		return userAreaList;
	}
}