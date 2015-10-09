package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.dao.user.UserAreaDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * UserArea
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("userAreaDao")
public class UserAreaDaoImpl extends GenericDaoImpl implements UserAreaDao{

    public java.lang.Long addUserArea(UserArea userArea) {
        return this.insert("UserAreaMapper.createUserArea",userArea);
    }

    public void deleteUserAreaById(Long id){
        this.delete("UserAreaMapper.deleteUserAreaById",id);
    }

    public void updateUserArea(UserArea userArea) {
        this.update("UserAreaMapper.updateUserArea", userArea);
    }

    public UserArea getUserAreaById(Long id) {
        return this.selectOne("UserAreaMapper.getUserAreaById",id);
    }

    public List<UserArea> getUserAreaList(UserArea userArea) {
    	return this.selectList("UserAreaMapper.getUserAreaList", userArea);
    }

	/**
	 * 批量修改城市区域显示状态
	 */
	public void updateUserAreaShow(Map<String, Object> map) {
		this.update("UserAreaMapper.updateUserAreaShow", map);
	}
	
	/**
     * 查询省份列表
     * 
     * @return List<UserArea>
     */
    public List<UserArea> getProvinceList(){
    	return this.selectList("UserAreaMapper.getProvinceList", null);
    }
    
    /**
     * 查询所有城市
     * @return
     */
    public List<UserArea> getAllUserAreaList(){
    	return this.selectList("UserAreaMapper.getAllUserAreaList", null);
    }

	@Override
	public List<UserArea> queryUserAreaByCondition(Map<String, Object> query) {
		// TODO Auto-generated method stub
		return selectList("UserAreaMapper.queryUserAreaByCondition", query);
	}

    /**
     * 首页展示城市列表(前台页面用)
     * @return
     */
    public List<UserArea> queryUserAreaList(){
        return this.selectList("UserAreaMapper.queryUserAreaList", null);
    }
}
