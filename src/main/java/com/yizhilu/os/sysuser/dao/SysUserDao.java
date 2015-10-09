/**
 * @ClassName UserDao
 * @package com.supergenius.sns.dao
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-2 上午11:44:42
 *
 */
package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.sysuser.entity.QueryUserCondition;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.dao.sysuser.UserDao
 * @description 系统 用户管理
 * @Create Date : 2013-12-17 上午9:44:25
 */
public interface SysUserDao {
    /**
     * 根据登录名查询
     *
     * @param name
     * @return User
     */
    public SysUser getUserByName(String name);

    /**
     * 查询所有用户,分页
     *
     * @param QueryUserCondition queryUserCondition
     * @param PageEntity         pageEntity
     * @return List<User>
     */
    public List<SysUser> getAllUserList(QueryUserCondition queryUserCondition, PageEntity pageEntity);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return User
     */
    public SysUser getUserById(Long id);

    /**
     * 修改用户信息
     *
     * @param user
     */
    public void updateUser(SysUser user);

    /**
     * 批量删除用户（修改用户状态为删除）
     *
     * @param list
     */
    public void updateUserStatusByGroupId(List<Long> list);

    /**
     * 修改密码
     *
     * @param user
     */
    public void updateUserPwd(SysUser user);

    /**
     * 新增用户
     *
     * @param user
     */
    public void addUser(SysUser user);

}
