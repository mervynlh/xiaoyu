package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.sysuser.entity.QueryRoleCondition;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.entity.RoleFunction;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.dao.sysuser.RoleDao
 * @description 角色管理
 * @Create Date : 2013-12-17 上午9:38:17
 */
public interface RoleDao {

    /**
     * 根据用户id查询所有role
     *
     * @param userId
     * @return List<Role>
     */
    public List<SysRole> getRoleListByUserId(Long userId);

    /**
     * 获得所有roleList
     *
     * @return List<Role>
     */
    public List<SysRole> getRoleList();

    /**
     * 根据id获得有role
     *
     * @param roleId 角色id
     * @return Role
     */
    public SysRole getRoleById(Long roleId);

    /**
     * 根据roleId删除角色权限对应关系表
     *
     * @param roleId
     */
    public void deleteRoleFunctionByRoleId(Long roleId);

    /**
     * 批量添加角色权限
     *
     * @param List<RoleFunction> list
     *                           角色list
     */
    public void createRoleFunctionBatch(List<RoleFunction> list);

    /**
     * 删除角色
     *
     * @param roleId
     */
    public void deleteRoleById(Long roleId);

    /**
     * 添加角色
     *
     * @param SysRole Role
     */
    public void addRole(SysRole role);

    /**
     * 删除用户对应的角色
     *
     * @param userId
     * @param roleId
     */
    public void deleteUserRoleRef(Long userId, Long roleId);

    /**
     * 根据roleID删除用户角色对应表
     *
     * @param roleId
     */
    public void deleteUserRoleByRoleId(Long roleId);

    /**
     * 根据条件获得roleList
     *
     * @param queryRoleCondition
     * @return List<Role>
     */
    public List<SysRole> getRoleListByCondition(QueryRoleCondition queryRoleCondition);

    /**
     * 用户添加角色对应关系
     *
     * @param userId
     * @param roleId
     */
    public void addUserRoleRef(Long userId, Long roleId);

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     */
    public void updateRoleName(String roleName, Long roleId);


}
