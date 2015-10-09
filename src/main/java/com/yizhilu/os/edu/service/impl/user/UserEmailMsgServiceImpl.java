package com.yizhilu.os.edu.service.impl.user;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.email.EmailService;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.user.UserEmailMsgDao;
import com.yizhilu.os.edu.entity.user.UserEmailMsg;
import com.yizhilu.os.edu.service.user.EmailThread;
import com.yizhilu.os.edu.service.user.UserEmailMsgService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.service.impl.user.UserEmailMsgServiceImpl
 * @description
 * @author : 
 * @Create Date : 2015年1月6日 下午
 */
@Service("userEmailMsgService")
public class UserEmailMsgServiceImpl implements UserEmailMsgService{
    @Autowired
    private UserEmailMsgDao userEmailMsgDao;

    /**
     * 添加发送用户邮箱记录
     * 
     * @param userEmailMsg
     * @return
     */
    public void addUserEmailMsg(List<UserEmailMsg> userEmailMsg) {
        userEmailMsgDao.addUserEmailMsg(userEmailMsg);
    }
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserMobileMsgService userMobileMsgService;
    /**
     * 发送邮件和短信定时service
     */
    public void queryTimingSendEmailMsg()throws Exception{
        UserEmailMsg userEmailMsg = new UserEmailMsg();
        userEmailMsg.setSendTime(new Date());
        userEmailMsg.setType(2);//类型为定时的
        userEmailMsg.setStatus(2);//找未发送的
        List<UserEmailMsg> userEmailMsgList = userEmailMsgDao.queryUserEmailList(userEmailMsg);

        if(ObjectUtils.isNotNull(userEmailMsgList)){
            for(UserEmailMsg u:userEmailMsgList){
                System.out.println(DateUtils.dateToStr(new Date(), "yyyy-MM-dd hh:mm:ss")+":发送定时邮件邮件"+u.getEmail());
                //发送邮件
                batchSendEmail(u.getEmail().split(","), u.getContent(), u.getTitle(),3);
                //emailService.sendBatchMail(u.getEmail().split(","), u.getContent(), u.getTitle());
                u.setStatus(1);
                userEmailMsgDao.updateUserEmailStatus(u);
            }
        }
        userMobileMsgService.timingSendMsg(new Date());
    }
    /**
     * 查询记录
     * 
     * @param userEmailMsg
     * @param page
     * @return
     */
    public List<UserEmailMsg> queryUserEmailMsgList(UserEmailMsg userEmailMsg,
            PageEntity page) {
        return userEmailMsgDao.queryUserEmailMsgList(userEmailMsg, page);
    }

    /**
     * 获得单个记录
     * 
     * @param id
     * @return
     */
    public UserEmailMsg queryUserEmailMsgById(Long id) {
        return userEmailMsgDao.queryUserEmailMsgById(id);
    }
    /**
     * 更新 UserEmailMsg
     */
    public void updateUserEmailMsgById(UserEmailMsg userEmailMsg){
        userEmailMsgDao.updateUserEmailMsgById(userEmailMsg);
    }
    /**
     * 删除发送邮件记录
     */
    public void delUserEmailMsgById(Long id){
        userEmailMsgDao.delUserEmailMsgById(id);
    }
    /**
     * 起四个线程批量发送邮件
     */
    public void batchSendEmail(java.lang.String[] mailto, java.lang.String text, java.lang.String title,int num){
        if(ObjectUtils.isNotNull(mailto)){
            List<String> list = new ArrayList<String>();
            list.addAll(Arrays.asList(mailto));
            EmailThread emailThread = new EmailThread(list,text,title,emailService);
            System.out.println("批量发送邮件线程启动：线程数："+num+"发送邮件数："+mailto.length);
            System.out.println("开始发送时间"+DateUtils.getNowTime());
            //启动多少线程
            for(int i=0;i<num;i++){
                new Thread(emailThread).start();
            }
        }
    }
}
