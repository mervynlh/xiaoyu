package com.yizhilu.os.edu.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserFeedback;
import com.yizhilu.os.edu.dao.user.UserFeedbackDao;
import com.yizhilu.os.edu.service.user.UserFeedbackService;
/**
 * UserFeedback管理接口
 * User: qinggang.liu
 * Date: 2014-10-15
 */
@Service("userFeedbackService")
public class UserFeedbackServiceImpl implements UserFeedbackService{

 	@Autowired
    private UserFeedbackDao userFeedbackDao;
    
    /**
     * 添加UserFeedback
     * @param userFeedback 要添加的UserFeedback
     * @return id
     */
    public java.lang.Long addUserFeedback(UserFeedback userFeedback){
    	return userFeedbackDao.addUserFeedback(userFeedback);
    }

    /**
     * 根据id删除一个UserFeedback
     * @param id 要删除的id
     */
    public void deleteUserFeedbackById(Long id){
    	 userFeedbackDao.deleteUserFeedbackById(id);
    }

    /**
     * 修改UserFeedback
     * @param userFeedback 要修改的UserFeedback
     */
    public void updateUserFeedback(UserFeedback userFeedback){
     	userFeedbackDao.updateUserFeedback(userFeedback);
    }

    /**
     * 根据id获取单个UserFeedback对象
     * @param id 要查询的id
     * @return UserFeedback
     */
    public UserFeedback getUserFeedbackById(Long id){
    	return userFeedbackDao.getUserFeedbackById( id);
    }

    /**
     * 根据条件获取UserFeedback列表
     * @param userFeedback 查询条件
     * @return List<UserFeedback>
     */
    public List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback){
    	return userFeedbackDao.getUserFeedbackList(userFeedback);
    }
    @Override
    public List<UserFeedback> getUserFeedbackListCondtion(UserFeedback userFeedback, PageEntity page) {
    	return userFeedbackDao.getUserFeedbackListByCondtion(userFeedback,page);
    }
}