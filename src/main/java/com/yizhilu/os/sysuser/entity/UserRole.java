package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.UserRole
 * @description 用户户对应权限
 * @Create Date : 2013-12-14 下午2:48:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8971435894866426993L;
    private Long roleId;
    private Long userId;
    private Date createTime;
}
