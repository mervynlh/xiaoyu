package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.QueryRoleFunctionCondition
 * @description 查询用户角色权限条件类
 * @Create Date : 2013-12-14 下午2:41:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryRoleFunctionCondition extends PageEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1770602655989434519L;
    private Long roleId;//角色id
    private Long functionId;//功能id
}