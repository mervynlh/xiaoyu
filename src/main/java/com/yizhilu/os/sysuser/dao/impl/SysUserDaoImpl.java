package com.yizhilu.os.sysuser.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.sysuser.dao.SysUserDao;
import com.yizhilu.os.sysuser.entity.QueryUserCondition;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.dao.impl.sysuser.UserDaoImpl
 * @description 系统用户管理
 * @Create Date : 2013-12-17 上午9:44:12
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends GenericDaoImpl implements SysUserDao {

    /**
     * 根据登录名查询
     *
     * @param name
     * @return User
     */
    @Override
    public SysUser getUserByName(String name) {
        return this.selectOne("SysUserMapper.getUserByLoginName", name);
    }

    /**
     * 查询所有用户,分页
     *
     * @param QueryUserCondition queryUserCondition
     * @param PageEntity         pageEntity
     * @return List<User>
     */
    @Override
    public List<SysUser> getAllUserList(QueryUserCondition queryUserCondition,
                                        PageEntity pageEntity) {
        return this.queryForListPage("SysUserMapper.getAllUserList", queryUserCondition,
                pageEntity);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return User
     */
    public SysUser getUserById(Long id) {
        return this.selectOne("SysUserMapper.getUserById", id);
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    public void updateUser(SysUser user) {
        this.update("SysUserMapper.updateUser", user);
    }

    /**
     * 批量删除用户（修改用户状态为删除）
     *
     * @param list
     */
    public void updateUserStatusByGroupId(List<Long> list) {
        this.update("SysUserMapper.updateUserStatusByGroupId", list);
    }

    /**
     * 修改密码
     *
     * @param user
     */
    public void updateUserPwd(SysUser user) {
        this.update("SysUserMapper.updateUserPwd", user);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    public void addUser(SysUser user) {
        this.insert("SysUserMapper.createUser", user);
    }

}
