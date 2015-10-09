package com.yizhilu.os.edu.dao.user;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.QueryUserAccounthistory;
import com.yizhilu.os.edu.entity.user.UserAccounthistory;

/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAccounthistoryDao {

    /**
     * 添加UserAccounthistory
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory);


    /**
     * 根据id获取单个UserAccounthistory对象
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    public UserAccounthistory getUserAccounthistoryById(Long id);

    /**
     * 根据条件获取UserAccounthistory列表
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory);
    /**
     * 根据条件获取UserAccounthistory列表
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory,PageEntity page);
    
    /**
     * 支付宝订单号查询账户历史，防止同一订单号多次充值
     * @param out_trade_no
     */
	public UserAccounthistory getUserAccounthistoryByOutTtradeNo(String outTradeNo);
	
	public List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page);
}