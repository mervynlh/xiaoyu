package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.user.UserAccounthistoryDao;
import com.yizhilu.os.edu.entity.user.QueryUserAccounthistory;
import com.yizhilu.os.edu.entity.user.UserAccounthistory;

/**
 *
 * UserAccounthistory
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("userAccounthistoryDao")
public class UserAccounthistoryDaoImpl extends GenericDaoImpl implements UserAccounthistoryDao{

    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory) {
        return this.insert("UserAccounthistoryMapper.createUserAccounthistory",userAccounthistory);
    }

    public UserAccounthistory getUserAccounthistoryById(Long id) {
        return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryById",id);
    }

    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory) {
        return this.selectList("UserAccounthistoryMapper.getUserAccounthistoryList",userAccounthistory);
    }
  
    public List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return this.queryForListPage("UserAccounthistoryMapper.getUserAccounthistoryListByCondition", queryUserAccounthistory, page);
    }

	public UserAccounthistory getUserAccounthistoryByOutTtradeNo( String outTradeNo) {
		
		return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryByOutTtradeNo",outTradeNo);
	}
	public List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
		
		return this.queryForListPage("UserAccounthistoryMapper.getWebUserAccounthistoryListByCondition", queryUserAccounthistory, page);
	}
}
