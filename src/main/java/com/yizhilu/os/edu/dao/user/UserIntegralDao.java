package com.yizhilu.os.edu.dao.user;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserIntegral;

/**
 * UserIntegral管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserIntegralDao {

	/**
	 * 添加UserIntegral
	 * 
	 * @param userIntegral
	 *            要添加的UserIntegral
	 * @return id
	 */
	public java.lang.Long addUserIntegral(UserIntegral userIntegral);

	/**
	 * 根据id删除一个UserIntegral
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralById(Long id);

	/**
	 * 修改UserIntegral
	 * 
	 * @param userIntegral
	 *            要修改的UserIntegral
	 */
	public void updateUserIntegral(UserIntegral userIntegral);

	/**
	 * 根据id获取单个UserIntegral对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegral
	 */
	public UserIntegral getUserIntegralById(Long id);

	/**
	 * 根据条件获取UserIntegral列表
	 * 
	 * @param userIntegral
	 *            查询条件
	 * @return List<UserIntegral>
	 */
	public List<UserIntegral> getUserIntegralList(UserIntegral userIntegral);

	/**
	 * 查詢用戶积分列表分頁
	 * 
	 * @param userIntegral
	 * @param page
	 * @return
	 */
	public List<UserIntegral> getUserIntegralListPage(UserIntegral userIntegral, PageEntity page);

	/**
	 * 根据用户Id获得积分
	 * 
	 * @param userId
	 * @return
	 */
	public UserIntegral getUserIntegralByUserId(Long userId);
}