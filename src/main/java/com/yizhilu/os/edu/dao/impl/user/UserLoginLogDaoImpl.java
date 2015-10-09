package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;

import com.yizhilu.os.edu.entity.user.UserLoginLog;
import com.yizhilu.os.edu.dao.user.UserLoginLogDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * UserLoginLog
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-10-12
 */
 @Repository("userLoginLogDao")
public class UserLoginLogDaoImpl extends GenericDaoImpl implements UserLoginLogDao{

    public java.lang.Long addUserLoginLog(UserLoginLog userLoginLog) {
        return this.insert("UserLoginLogMapper.createUserLoginLog",userLoginLog);
    }

    public void updateUserLoginLog(UserLoginLog userLoginLog) {
        this.update("UserLoginLogMapper.updateUserLoginLog",userLoginLog);
    }

    public UserLoginLog getUserLoginLogById(Long id) {
        return this.selectOne("UserLoginLogMapper.getUserLoginLogById",id);
    }

    public List<UserLoginLog> getUserLoginLogList() {
        return this.selectList("UserLoginLogMapper.getUserLoginLogList",null);
    }
    /**
	 * 查询登陆log前台
	 * 
	 * @param userLoginLog
	 * @param page
	 * @return
	 */
    public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page) {
    	return this.queryForListPage("UserLoginLogMapper.getUserLoginLogListPage", userLoginLog, page);
    }
}
