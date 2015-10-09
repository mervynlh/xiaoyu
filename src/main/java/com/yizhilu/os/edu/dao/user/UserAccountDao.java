package com.yizhilu.os.edu.dao.user;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserAccountDTO;

/**
 * UserAccount管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserAccountDao {

	/**
	 * 添加UserAccount
	 * 
	 * @param userAccount
	 *            要添加的UserAccount
	 * @return id
	 */
	public java.lang.Long addUserAccount(UserAccount userAccount);

	/**
	 * 修改UserAccount
	 * 
	 * @param userAccount
	 *            要修改的UserAccount
	 */
	public void updateUserAccount(UserAccount userAccount) throws StaleObjectStateException;

	/**
	 * 根据id获取单个UserAccount对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserAccount
	 */
	public UserAccount getUserAccountByUserId(Long userId);

	/**
	 * 根据条件获取UserAccount列表
	 * 
	 * @param userAccount
	 *            查询条件
	 * @return List<UserAccount>
	 */
	public List<UserAccount> getUserAccountList(UserAccount userAccount);

	/**
	 * 获取用户账户信息
	 * 
	 * @param pageEntity
	 * @return
	 */
	public List<UserAccountDTO> getuserAccountListByCondition(User user, PageEntity pageEntity) throws StaleObjectStateException;

	/**
	 * 更新账户状态
	 * 
	 * @param userId
	 * @param status
	 */
	public void updateUserAccountStatus(Long userId, String status);

	/**
	 * 根据用户id获得详情
	 * 
	 * @param userId
	 * @return
	 */
	public UserAccountDTO getuserAccountInfo(Long userId);
}