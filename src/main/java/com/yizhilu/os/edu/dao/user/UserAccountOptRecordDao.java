package com.yizhilu.os.edu.dao.user;

import com.yizhilu.os.edu.entity.user.UserAccountOptRecord;

import java.util.List;

/**
 * UserAccountOptRecord管理接口 User: qinggang.liu Date: 2014-10-23
 */
public interface UserAccountOptRecordDao {

	/**
	 * 添加UserAccountOptRecord
	 * 
	 * @param userAccountOptRecord
	 *            要添加的UserAccountOptRecord
	 * @return id
	 */
	public Long addUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord);

	/**
	 * 根据id删除一个UserAccountOptRecord
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserAccountOptRecordById(Long id);

	/**
	 * 修改UserAccountOptRecord
	 * 
	 * @param userAccountOptRecord
	 *            要修改的UserAccountOptRecord
	 */
	public void updateUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord);

	/**
	 * 根据id获取单个UserAccountOptRecord对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserAccountOptRecord
	 */
	public UserAccountOptRecord getUserAccountOptRecordById(Long id);

	/**
	 * 根据条件获取UserAccountOptRecord列表
	 * 
	 * @param userAccountOptRecord
	 *            查询条件
	 * @return List<UserAccountOptRecord>
	 */
	public List<UserAccountOptRecord> getUserAccountOptRecordList(UserAccountOptRecord userAccountOptRecord);
}