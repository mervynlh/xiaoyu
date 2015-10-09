package com.yizhilu.os.edu.dao.user;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserIntegralGift;

/**
 * UserIntegralGift管理接口 User: qinggang.liu Date: 2014-09-24
 */
public interface UserIntegralGiftDao {

	/**
	 * 添加UserIntegralGift
	 * 
	 * @param userIntegralGift
	 *            要添加的UserIntegralGift
	 * @return id
	 */
	public java.lang.Long addUserIntegralGift(UserIntegralGift userIntegralGift);

	/**
	 * 根据id删除一个UserIntegralGift
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralGiftById(Long id);

	/**
	 * 修改UserIntegralGift
	 * 
	 * @param userIntegralGift
	 *            要修改的UserIntegralGift
	 */
	public void updateUserIntegralGift(UserIntegralGift userIntegralGift);

	/**
	 * 根据id获取单个UserIntegralGift对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralGift
	 */
	public UserIntegralGift getUserIntegralGiftById(Long id);
	
	/**
	 * 根据id获取单个UserIntegralGift对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralGift
	 */                     
    public UserIntegralGift getUserIntegralAndCourseGiftById(Long id);

	/**
	 * 根据条件获取UserIntegralGift列表
	 * 
	 * @param userIntegralGift
	 *            查询条件
	 * @return List<UserIntegralGift>
	 */
	public List<UserIntegralGift> getUserIntegralGiftList(UserIntegralGift userIntegralGift);

	/**
	 * 查询礼品列表
	 * 
	 * @param userIntegralGift
	 * @param page
	 * @return
	 */
	public List<UserIntegralGift> getUserIntegralGiftListPage(UserIntegralGift userIntegralGift, PageEntity page);

	/**
	 * 获得用户礼品
	 * 
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<UserIntegralGift> getIntegralGiftListByUserId(Long userId, PageEntity page);
}