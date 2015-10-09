package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryRoleCondition
 * @description 查询Role辅助类
 * @Create Date : 2013-12-14 下午2:40:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryRoleCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5221738407808555228L;
    private Long roleId;//角色id
    private int roleTypeId;//角色类别id
    private int roleApplyScopeId;//角色使用范围id
    private Long userId;//用户ID

}