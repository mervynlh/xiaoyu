package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.entity.QueryUserCondition;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * @author liuqinggang
 * @ClassName UserService
 * @package com.supergenius.sns.service.user
 * @description
 * @Create Date: 2013-3-1 下午10:18:56
 */
public interface SysUserService {
    /**
     * 根据登录名查询用户
     *
     * @param name 用户LoginName
     * @return User
     */
    public SysUser getUserByLoginName(String name);

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    public SysUser getUserById(Long id);


    /**
     * 查询 用户权限
     *
     * @param Long userId
     * @return List<SysFunction>
     */
    public List<SysFunction> getUserFunction(Long userId);

    /**
     * 查询所有用户,分页
     *
     * @param queryUserCondition
     * @param pageEntity
     * @return List<User>
     */
    public List<SysUser> getAllUserList(QueryUserCondition queryUserCondition,
                                        PageEntity pageEntity);

    /**
     * 修改用户信息
     *
     * @param user
     */
    public void updateUser(SysUser user);

    /**
     * 修改密码
     *
     * @param user
     */
    public void updateUserPwd(SysUser user);

    /**
     * 新增用户
     *
     * @param SysUser
     */
    public void addUser(SysUser user);

}
