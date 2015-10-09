package com.yizhilu.os.edu.constants.web;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.yizhilu.os.sns.entity.constans.DynamicConstans
 * @description 动态常量
 * @Create Date : 2014-3-26 上午10:12:07
 */
public class DynamicConstans {
    /**
     * 动态type常量  加关注 微博，小组，博文(发表回复), 问答(发表回复), 考试，课程（购买，学习），
     */
    public static final int DYNAMICWEB_TYPE_WEIBO = 2;// 微博动态
    public static final int DYNAMICWEB_TYPE_REWEIBO = 5;// 回复博文

    public static final int DYNAMICWEB_TYPE_DISARTICLE = 3;// 发表小组文章
    public static final int DYNAMICWEB_TYPE_REDISARTICLE = 6;// 回复小组文章

    public static final int DYNAMICWEB_TYPE_SUGSUGGEST = 8;// 建议
    public static final int DYNAMICWEB_TYPE_BLOG = 9;// 发表博文
    public static final int DYNAMICWEB_TYPE_REBLOG = 10;// 回复博文

    public static final int DYNAMICWEB_TYPE_FINBLOG = 11;// 发表财经
    public static final int DYNAMICWEB_TYPE_REFINBLOG = 12;// 回复财经

    //动态内容最多截取字数
    public static final int DYNAMICWEB_CONTENT_LENGTH = 300;


    /**
     * 会员中心首页动态切换时type
     */
    public static final int DYNAMICWEB_PAGE_TYPE_ALL = 1;// 全部动态
    public static final int DYNAMICWEB_PAGE_TYPE_DIS = 2;// 小组组动态
    public static final int DYNAMICWEB_PAGE_TYPE_FRIEND = 3;// 好友动态
    public static final int DYNAMICWEB_PAGE_TYPE_COURSE = 4;// 课程动态
    public static final int DYNAMICWEB_PAGE_TYPE_EXAM = 5;// 考试动态

}
