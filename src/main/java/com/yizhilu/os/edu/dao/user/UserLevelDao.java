package com.yizhilu.os.edu.dao.user;

import java.util.List;
import com.yizhilu.os.edu.entity.user.UserLevel;

/**
 * UserLevel管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserLevelDao {

	/**
	 * 添加UserLevel
	 * 
	 * @param userLevel
	 *            要添加的UserLevel
	 * @return id
	 */
	public java.lang.Long addUserLevel(UserLevel userLevel);

	/**
	 * 根据id删除一个UserLevel
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserLevelById(Long id);

	/**
	 * 修改UserLevel
	 * 
	 * @param userLevel
	 *            要修改的UserLevel
	 */
	public void updateUserLevel(UserLevel userLevel);

	/**
	 * 根据id获取单个UserLevel对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserLevel
	 */
	public UserLevel getUserLevelById(Long id);

	/**
	 * 获取UserLevel列表
	 * 
	 * @param userLevel
	 * @return
	 */
	public List<UserLevel> getUserLevelList();

}