package com.yizhilu.os.edu.dao.userprofile;

import java.util.List;

import com.yizhilu.os.edu.entity.userprofile.UserProfile;

/**
 * UserProfile管理接口 User: qinggang.liu Date: 2014-01-13
 */
public interface UserProfileDao {

	/**
	 * 添加UserProfile
	 * 
	 * @param userProfile
	 *            要添加的UserProfile
	 * @return id
	 */
	public Long addUserProfile(UserProfile userProfile);

	/**
	 * 根据id删除一个UserProfile
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserProfileById(Long id);

	/**
	 * 修改UserProfile
	 * 
	 * @param userProfile
	 *            要修改的UserProfile
	 */
	public void updateUserProfile(UserProfile userProfile);

	/**
	 * 根据id获取单个UserProfile对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserProfile
	 */
	public List<UserProfile> getUserProfileByUserId(Long userid);

	/**
	 * 根据条件获取UserProfile列表
	 * 
	 * @param userProfile
	 *            查询条件
	 * @return List<UserProfile>
	 */
	public List<UserProfile> getUserProfileList(UserProfile userProfile);
}