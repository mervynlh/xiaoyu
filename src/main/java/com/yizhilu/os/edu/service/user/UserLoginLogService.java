package com.yizhilu.os.edu.service.user;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserLoginLog;

/**
 * UserLoginLog管理接口
 * User: qinggang.liu
 * Date: 2014-10-12
 */
public interface UserLoginLogService {

    /**
     * 添加UserLoginLog
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    public java.lang.Long addUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 修改UserLoginLog
     * @param userLoginLog 要修改的UserLoginLog
     */
    public void updateUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 根据id获取单个UserLoginLog对象
     * @param id 要查询的id
     * @return UserLoginLog
     */
    public UserLoginLog getUserLoginLogById(Long id);

    /**
     * 查找用户无地址的信息 
     */
    public List<UserLoginLog> getUserLoginLogList();
    /**
	 * 查询登陆log前台
	 * 
	 * @param userLoginLog
	 * @param page
	 * @return
	 */
	public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page);
}