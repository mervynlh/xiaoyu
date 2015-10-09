package com.yizhilu.os.edu.service.user;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserEmailMsg;

import java.util.List;

/**
 * 
 * @ClassName com.yizhilu.os.edu.service.user.UserEmailMsgService
 * @description
 * @author : 
 * @Create Date : 2015年1月6日 下午10:00:56
 */
public interface UserEmailMsgService {
	/**
	 * 添加发送用户邮件记录
	 * @return
	 */
	public void addUserEmailMsg(List<UserEmailMsg> userEmailMsgList);
    /**
     * 发送邮件和短信定时service
     */
    public void queryTimingSendEmailMsg()throws Exception;
	/**
	 * 查询记录
	 * 
	 * @param userEmailMsg
	 * @param page
	 * @return
	 */
	public List<UserEmailMsg> queryUserEmailMsgList(UserEmailMsg userEmailMsg, PageEntity page);

	/**
	 * 获得单个记录
	 * 
	 * @param id
	 * @return
	 */
	public UserEmailMsg queryUserEmailMsgById(Long id);
    /**
     * 更新 UserEmailMsg
     */
    public void updateUserEmailMsgById(UserEmailMsg userEmailMsg);
    /**
     * 删除发送邮件记录
     */
    public void delUserEmailMsgById(Long id);
    /**
     * 起四个线程批量发送邮件
     */
    public void batchSendEmail(java.lang.String[] mailto, java.lang.String text, java.lang.String title,int num);
}
