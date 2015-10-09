package com.yizhilu.os.edu.service.user;

import java.util.Date;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserMobileMsg;

/**
 * 
 * @ClassName com.yizhilu.os.edu.service.user.UserMobileMsgService
 * @description
 * @author : XuJunBao
 * @Create Date : 2014年9月21日 下午10:00:56
 */
public interface UserMobileMsgService {
	/**
	 * 添加发送用户短信记录
	 * 
	 * @param userMobileMsg
	 * @return
	 */
	public void addUserMobileMsg(List<UserMobileMsg> userMobileMsgList);

	/**
	 * 查询记录
	 * 
	 * @param userMobileMsg
	 * @param page
	 * @return
	 */
	public List<UserMobileMsg> queryUserMobileMsgList(UserMobileMsg userMobileMsg, PageEntity page);

	/**
	 * 获得单个记录
	 * 
	 * @param id
	 * @return
	 */
	public UserMobileMsg queryUserMobileMsgById(Long id);

	/**
	 * 发送短信
	 * 
	 * @param msgContent
	 * @param destNumber
	 * @param sendTime
	 * @param subNumber
	 * @param wapURL
	 * @throws java.lang.Exception
	 */
	public void sendEx(String msgContent, String destNumber, String sendTime, String subNumber, String wapURL) throws java.lang.Exception;
	 /**
  	 * 发送短信
  	 * 
  	 * @param type
  	 * @param destNumber
  	 * @param sendTime
  	 * @param subNumber
  	 * @param wapURL
  	 * @throws java.lang.Exception
  	 */
      public void sendEx(int type, String destNumber, String param_1, String param_2, String param_3,String param_4) throws Exception;
    /**
     * 发送短信
     */
    public void sendBatchEx(String msgContent, String[] destNumber) throws java.lang.Exception;
    /**
     * 起多个线程批量发送手机
     */
    public void batchSendMobileMsg(java.lang.String text,java.lang.String[] mailto,int num);
	/**
     * 查询和当前时间相等的短信记录 发送
     */
    public List<UserMobileMsg> queryNowMobileMsgList(Date nowDate);
    
    /**
     * 修改短信发送状态
     */
    public void updateMsgStatus(Long id);
    /**
     * 定时发送  调用的方法
     */
    public void timingSendMsg(Date nowDate) throws Exception;
    
    /**
     * 修改短信
     * @param userMobileMsg
     */
    public void updateUserMobileMsg(UserMobileMsg userMobileMsg);
    
    /**
     * 删除短信
     */
    public void delUserMobileMsg(Long id);
}
