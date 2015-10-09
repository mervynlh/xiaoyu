package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.RoleFunction
 * @description 角色功能
 * @Create Date : 2013-12-14 下午2:44:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleFunction implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5263220256016186014L;
    public static int ROLE_FUNCTION_STATUS_DEFAULT = 1;// RoleFunction的默认状态
    private Long roleId;// 角色id
    private Long functionId;// 功能id
    private int status;// 功能
    private java.util.Date createTime;// 创建时间

}
