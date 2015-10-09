package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.LoginLog
 * @description 登录日志
 * @Create Date : 2013-12-14 下午2:37:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5535670276285023025L;

    private Long id; // 序号，主键，自增
    private Date loginTime; // 登录时间
    private String ip; // 登录IP
    private Long userId; // 外键_登录人ID
    private String userName; // 登录真实姓名
    private String loginName; // 登录名
    private String address;//登陆地址
    private String osname;//操作系统
    private String userAgent;//操作系统
}
