/**
 * ClassName  CoreConstant
 * package    com.supergenius.sns.common.util
 * description 
 * Create User: Administrator
 * Create Date: 2013-12-9
 */
package com.yizhilu.os.edu.constants.web;

import com.yizhilu.os.core.util.PropertyUtil;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.util.SnsConstants
 * @description 项目相关常量
 * @Create Date : 2014-3-26 上午10:15:01
 */

public class SnsConstants {

    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance("memtimes");

    // 系统返回的常量定义
    public static String SUCCESS = "success";// 成功
    public static String FAILURE = "failure";

    public static String LIMITOPT = "limitOpt";
    public static String FALSE = "false";
    public static String EXSIT = "exist";
    public static String ISEMPTY = "isEmpty";
    public static String FRIEND = "friend";// 已经添加了好友
    public static String BLACKLIST = "blackList";// 已经添加了黑名单
    public static String ATTENTIONONESELF = "attentiononeSelf";// 自己不能关注自己
    public static String ONESEKFLETTER = "oneSelfLetter";// 自己不能给自己发消息
    public static String ADDMOST = "addMost";// 添加频率过快
    public static String FORWARD = "forward";//微博转发过
    public static String FORWARDSELF = "forwardSelf";//转发的微博是自己原创
    public static String DELFORWARD = "delForward";//原始微博已删除
    public static String EMPTY = "empty";//站内信清空
   
    /**
     * customer 字典常量定义
     */
    public static final String CUSTOMER_LINE_NUM = "cusLineNum";// 行数的key
    public static final String CUSTOMER_MAX_ID = "cusMaxId";// 最大id的key

    //首页显示数量控制
    public static final int PERSON_WEIBO_NUM = Integer.parseInt(propertyUtil.getProperty("PERSON_WEIBO_NUM"));// 个人空间微博数
    public static final int PERSON_BLOG_NUM = Integer.parseInt(propertyUtil.getProperty("PERSON_BLOG_NUM"));// 个人空间博客数
    public static final int PERSON_DYNAMIC_NUM = Integer.parseInt(propertyUtil.getProperty("PERSON_DYNAMIC_NUM"));// 个人空间动态数
    //发博文建议的最多次数
    public static final int MAX_TEXT_LENGTH = Integer.parseInt(propertyUtil.getProperty("MAX_TEXT_LENGTH"));

    //站内信消息过期时间
    public static int MSG_PAST_TIME = Integer.parseInt(propertyUtil.getProperty("MSGPASTTIME"));

}
