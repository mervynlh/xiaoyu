package com.yizhilu.os.edu.service.impl.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yizhilu.os.edu.entity.user.UserLevel;
import com.yizhilu.os.edu.dao.user.UserLevelDao;
import com.yizhilu.os.edu.service.user.UserLevelService;

/**
 * UserLevel管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("userLevelService")
public class UserLevelServiceImpl implements UserLevelService {

	@Autowired
	private UserLevelDao userLevelDao;

	/**
	 * 添加UserLevel
	 * 
	 * @param userLevel
	 *            要添加的UserLevel
	 * @return id
	 */
	public java.lang.Long addUserLevel(UserLevel userLevel) {
		return userLevelDao.addUserLevel(userLevel);
	}

	/**
	 * 根据id删除一个UserLevel
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserLevelById(Long id) {
		userLevelDao.deleteUserLevelById(id);
	}

	/**
	 * 修改UserLevel
	 * 
	 * @param userLevel
	 *            要修改的UserLevel
	 */
	public void updateUserLevel(UserLevel userLevel) {
		userLevelDao.updateUserLevel(userLevel);
	}

	/**
	 * 根据id获取单个UserLevel对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserLevel
	 */
	public UserLevel getUserLevelById(Long id) {
		return userLevelDao.getUserLevelById(id);
	}

	/**
	 * 获取UserLevel列表
	 * 
	 * @return
	 */
	public List<UserLevel> getUserLevelList() {
		return userLevelDao.getUserLevelList();
	}
}