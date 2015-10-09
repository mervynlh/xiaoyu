package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.entity.LoginLog;
import com.yizhilu.os.sysuser.entity.AdminLoginLog;
import com.yizhilu.os.sysuser.entity.QueryLoginLogCondition;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.supergenius.sns.dao.sysuser.LoginLogDao
 * @description 登录日志
 * @Create Date : 2013-12-17 上午9:36:42
 */
public interface LoginLogDao {
	/**
	 * 添加登陆日志
	 * 
	 * @param loginLog
	 */
	public void addLoginLog(LoginLog loginLog);

	/**
	 * 后台管理日志修改
	 * 
	 * @param loginLog
	 */
	public void updateLoginLog(AdminLoginLog adminLoginLog);

	/**
	 * 获取登陆信息
	 * 
	 * @return
	 */
	public List<AdminLoginLog> getLoginLog();

	/**
	 * 查询系统用户的登陆log
	 * 
	 * @param loginLog
	 * @param page
	 * @return
	 */
	public List<LoginLog> getLoginLogList(QueryLoginLogCondition queryLoginLogCondition, PageEntity page);

}
