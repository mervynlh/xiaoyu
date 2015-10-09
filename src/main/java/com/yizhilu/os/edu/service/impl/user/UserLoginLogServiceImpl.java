package com.yizhilu.os.edu.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.entity.user.UserLoginLog;
import com.yizhilu.os.edu.dao.user.UserLoginLogDao;
import com.yizhilu.os.edu.service.user.UserLoginLogService;

/**
 * UserLoginLog管理接口 User: qinggang.liu Date: 2014-10-12
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService {

	@Autowired
	private UserLoginLogDao userLoginLogDao;

	/**
	 * 添加UserLoginLog
	 * 
	 * @param userLoginLog
	 *            要添加的UserLoginLog
	 * @return id
	 */
	public java.lang.Long addUserLoginLog(UserLoginLog userLoginLog) {
		return userLoginLogDao.addUserLoginLog(userLoginLog);
	}

	/**
	 * 修改UserLoginLog
	 * 
	 * @param userLoginLog
	 *            要修改的UserLoginLog
	 */
	public void updateUserLoginLog(UserLoginLog userLoginLog) {
		String address = WebUtils.getAddressByIP(userLoginLog.getLoginIp());
		if (address.equals("")) {
			address = "未知";
		}
		userLoginLog.setAddress(address);
		userLoginLogDao.updateUserLoginLog(userLoginLog);
	}

	/**
	 * 分页查询用户的登录记录 Long userId,PageEntity page
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserLoginLog
	 */
	public UserLoginLog getUserLoginLogById(Long id) {
		return userLoginLogDao.getUserLoginLogById(id);
	}

	/**
	 * address is null limit 10000
	 * 
	 * @param userLoginLog
	 *            查询条件
	 * @return List<UserLoginLog>
	 */
	public List<UserLoginLog> getUserLoginLogList() {
		return userLoginLogDao.getUserLoginLogList();
	}
	/**
	 * 查询登陆log前台
	 * 
	 * @param userLoginLog
	 * @param page
	 * @return
	 */
	public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page) {
		return userLoginLogDao.getUserLoginLogListPage(userLoginLog, page);
	}
}