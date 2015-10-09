package com.yizhilu.os.edu.service.user;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserIntegral;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;

/**
 * UserIntegral管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserIntegralService {

	/**
	 * 添加积分
	 * 
	 * @param keyWord
	 *            关键字
	 * @param userId
	 *            用户id
	 * @param other
	 *            辅助id
	 * @param fromUserId
	 *            来源id
	 * @param otherScore
	 *            其它分数 辅助
	 */
	public boolean addUserIntegral(String keyWord, Long userId, Long other, Long fromUserId, String otherScore);

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

	/**
	 * 获得用户积分和等级
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUserIntegralAndLevel(Long userId);

	/**
	 * 查询积分模板，检查用户操作类型
	 * 
	 * @param userId
	 *            用户id
	 * @param keyWord
	 *            关键字
	 * @param other
	 *            其它id
	 * @param fromUserId
	 *            来源id
	 * @return
	 */
	public UserIntegralTemplate checkScore(Long userId, String keyWord, Long other, Long fromUserId);
}