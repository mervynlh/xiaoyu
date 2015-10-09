package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuqinggang
 * @ClassName Role
 * @package com.supergenius.sns.entity.user
 * @description 角色
 * @Create Date: 2013-3-1 下午10:14:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3334987436609134494L;

    public static int ROLE_APPLY_SCOPE_ALL = 1;// 角色适用范围：普通用户默认1.系统管理员。数据库初始时手动改为0
    public static int ROLE_TYPE_DEFAULT = 1;// 角色默认类型
    public static int ROLE_STATUS_DEFAULT = 1;// 角色默认状态
    private Long roleId;// 角色id
    private String roleName;// 角色名称
    private int status;//
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 修改时间
    private List<SysFunction> functionList = new ArrayList<SysFunction>();// 功能列表
    //下面两个字段临时未用，可以作为区分普通人员和系统管理员扩展使用
    private int roleTypeId;// 角色类别
    private int roleApplyScopeId;// 角色适用范围id,默认1

}
