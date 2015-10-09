package com.yizhilu.os.sysuser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.entity.sysuser.User
 * @description 系统用户
 * @Create Date : 2013-12-14 下午2:45:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {
    private static final long serialVersionUID = 4773758518239195423L;
    public static int USER_DEFAULT_STATUS = 0;//用户默认状态
    public static int USER_FREEZE_STATUS = 1;//冻结
    public static int USER_DELETE_STATUS = 2;//伪删除
    private Long userId;//用户id
    private String loginName;//登录名称
    private String loginPwd;//登录密码
    private String userName;//用户姓名
    private int status;//状态,参照上面的常量
    private java.util.Date lastLoginTime;//最后登录时间
    private String lastLoginIp;//最后登录ip
    private java.util.Date createTime;//创建时间
    private String email;//邮箱
    private String tel;//手机
    private SysGroup group;//组
    private String groupName;//组名称
    private int userType;//用户类型,默认0.备用字段,现在全部为0.以备后台用户扩展
    private Long groupId;//组id
    private List<SysRole> roleList = new ArrayList<SysRole>();//所有的角色

}
