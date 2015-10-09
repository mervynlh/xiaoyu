package com.yizhilu.os.sysuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.sysuser.dao.LoginLogDao;
import com.yizhilu.os.sysuser.entity.AdminLoginLog;
import com.yizhilu.os.sysuser.entity.LoginLog;
import com.yizhilu.os.sysuser.entity.QueryLoginLogCondition;
import com.yizhilu.os.sysuser.service.LoginLogService;

/**
 * @author liuqinggang
 * @ClassName LoginLogServiceImpl
 * @package com.supergenius.sns.service.impl.user
 * @description 用户登陆日志
 * @Create Date: 2013-3-8 下午04:31:54
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogDao loginLogDao;
    


    /**
     * 添加登陆日志
     */
    @Override
    public void addLoginLog(LoginLog loginLog) {
        loginLogDao.addLoginLog(loginLog);
    }
    /**
     * 获取登陆日志
     */
    @Override
    public List<AdminLoginLog> getLoginLog() {
    	return loginLogDao.getLoginLog();
    }
    /**
     * 修改登陆日志
     */
    @Override
    public void updateLoginLog(AdminLoginLog adminLoginLog) {
    	String address= WebUtils.getAddressByIP(adminLoginLog.getLoginIp());
    	if(address.equals("")){
    		adminLoginLog.setAddress("未知");	
    	}else{
    		adminLoginLog.setAddress(address);
    	}
    	loginLogDao.updateLoginLog(adminLoginLog);
    }
    /**
     * 定时器 
     * 修改用户登陆地址
     */
    @Override
    public void updateAdminLoginLog() {
    	List<AdminLoginLog> loginLogList = getLoginLog();
    	for(AdminLoginLog adminLoginLog :loginLogList){
    		try {
    			updateLoginLog(adminLoginLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    /**
	 * 查询系统用户的登陆log
	 * 
	 * @param loginLog
	 * @param page
	 * @return
	 */
    public List<LoginLog> getLoginLogList(QueryLoginLogCondition queryLoginLogCondition, PageEntity page) {
    	// TODO Auto-generated method stub
    	return loginLogDao.getLoginLogList(queryLoginLogCondition, page);
    }

}
