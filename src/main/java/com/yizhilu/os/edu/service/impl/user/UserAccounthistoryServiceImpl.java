package com.yizhilu.os.edu.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.user.UserAccounthistoryDao;
import com.yizhilu.os.edu.entity.user.QueryUserAccounthistory;
import com.yizhilu.os.edu.entity.user.UserAccounthistory;
import com.yizhilu.os.edu.service.user.UserAccounthistoryService;
/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("userAccounthistoryService")
public class UserAccounthistoryServiceImpl implements UserAccounthistoryService{

 	@Autowired
    private UserAccounthistoryDao userAccounthistoryDao;
    
    /**
     * 添加UserAccounthistory
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory){
    	return userAccounthistoryDao.addUserAccounthistory(userAccounthistory);
    }

    /**
     * 根据id获取单个UserAccounthistory对象
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    public UserAccounthistory getUserAccounthistoryById(Long id){
    	return userAccounthistoryDao.getUserAccounthistoryById( id);
    }

    /**
     * 根据条件获取UserAccounthistory列表
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory){
    	return userAccounthistoryDao.getUserAccounthistoryList(userAccounthistory);
    }
    /**
     * 账户历史 不隐藏后台操作 后台查看
     * 根据条件获取UserAccounthistory分页列表
     * @param userAccounthistory, 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return userAccounthistoryDao.getUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }
    /**
     * 账户历史 隐藏后台操作 前台个人中心查看
     * 根据条件获取UserAccounthistory分页列表
     * @param userAccounthistory, 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getWebUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return userAccounthistoryDao.getWebUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }
}
