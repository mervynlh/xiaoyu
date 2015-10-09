package com.yizhilu.os.edu.dao.user;

import java.util.Date;
import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserMobileMsg;

/**
 * 
 * @ClassName com.yizhilu.os.edu.dao.user.UserMobileMsgDao
 * @description
 * @author : XuJunBao
 * @Create Date : 2014年9月21日 下午9:28:39
 */
public interface UserMobileMsgDao {
    /**
     * 添加发送用户短信记录
     * 
     * @param userMobileMsg
     * @return
     */
    public Long addUserMobileMsg(List<UserMobileMsg> userMobileMsgList);

    /**
     * 查询记录
     * 
     * @param userMobileMsg
     * @param page
     * @return
     */
    public List<UserMobileMsg> queryUserMobileMsgList(UserMobileMsg userMobileMsg,
            PageEntity page);

    /**
     * 获得单个记录
     * 
     * @param id
     * @return
     */
    public UserMobileMsg queryUserMobileMsgById(Long id);
    
    /**
     * 查询和当前时间相等的短信记录 发送
     * 
     * @param userMobileMsg
     * @return
     */
    public List<UserMobileMsg> queryNowMobileMsgList(Date nowDate);
    
    /**
     * 修改短信发送状态
     */
    public void updateMsgStatus(Long id);
    
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
