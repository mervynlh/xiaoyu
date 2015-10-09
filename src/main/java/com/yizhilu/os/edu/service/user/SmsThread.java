package com.yizhilu.os.edu.service.user;


/**
 * 
 * @ClassName  com.yizhilu.os.edu.service.user.SmsThread
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月22日 下午5:32:52
 */
public class SmsThread extends Thread{
    private final SmsServiceStub sendSms;

    public SmsThread(SmsServiceStub sendSms) {
        this.sendSms = sendSms;
    }

    @Override
    public void run() {
        sendSms.sendmsg();
    }
}
