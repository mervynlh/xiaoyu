package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;

import com.yizhilu.os.edu.entity.user.UserFeedback;
import com.yizhilu.os.edu.dao.user.UserFeedbackDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * UserFeedback
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-10-15
 */
 @Repository("userFeedbackDao")
public class UserFeedbackDaoImpl extends GenericDaoImpl implements UserFeedbackDao{

	 /**
	  * 添加userFeed
	  * 
	  */
    public java.lang.Long addUserFeedback(UserFeedback userFeedback) {
        return this.insert("UserFeedbackMapper.createUserFeedback",userFeedback);
    }

    public void deleteUserFeedbackById(Long id){
        this.delete("UserFeedbackMapper.deleteUserFeedbackById",id);
    }

    public void updateUserFeedback(UserFeedback userFeedback) {
        this.update("UserFeedbackMapper.updateUserFeedback",userFeedback);
    }

    public UserFeedback getUserFeedbackById(Long id) {
        return this.selectOne("UserFeedbackMapper.getUserFeedbackById",id);
    }

    public List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback) {
/*        return this.selectList("UserFeedbackMapper.getUserFeedbackList",userFeedback);*/
    	return null;
    }
    @Override
    public List<UserFeedback> getUserFeedbackListByCondtion(UserFeedback userFeedback, PageEntity page) {
    	// TODO Auto-generated method stub
    	return this.queryForListPage("UserFeedbackMapper.getUserFeedbackList", userFeedback, page);
    }
}
