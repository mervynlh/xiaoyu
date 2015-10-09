package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryLoginLogCondition
 * @description 查询登录辅助类
 * @Create Date : 2013-12-14 下午2:39:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryLoginLogCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -516196625928174274L;
    private Long userId;            //外键_登录人ID
    private String userName;    //登录人

}
