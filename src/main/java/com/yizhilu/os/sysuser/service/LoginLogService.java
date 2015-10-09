/**
 * @ClassName LoginLogService
 * @package com.supergenius.sns.service.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-1 下午10:19:18
 *
 */
package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.entity.AdminLoginLog;
import com.yizhilu.os.sysuser.entity.LoginLog;
import com.yizhilu.os.sysuser.entity.QueryLoginLogCondition;

/**
 * @author liuqinggang
 * @ClassName LoginLogService
 * @package com.supergenius.sns.service.user
 * @description
 * @Create Date: 2013-3-1 下午10:19:18
 */
public interface LoginLogService {

	/**
	 * 添加登陆日志
	 * 
	 * @param loginLog
	 */
	public void addLoginLog(LoginLog loginLog);

	public List<AdminLoginLog> getLoginLog();

	public void updateLoginLog(AdminLoginLog loginLog);

	/**
	 * 修改系统用户登陆日志
	 */
	public void updateAdminLoginLog();

	/**
	 * 查询系统用户的登陆log
	 * 
	 * @param loginLog
	 * @param page
	 * @return
	 */
	public List<LoginLog> getLoginLogList(QueryLoginLogCondition queryLoginLogCondition, PageEntity page);

}
