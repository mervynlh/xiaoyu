/**
 * @ClassName RoleService
 * @package com.supergenius.sns.service.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-1 下午10:19:50
 *
 */
package com.yizhilu.os.sysuser.service;

import java.util.List;
import java.util.Set;

import com.yizhilu.os.sysuser.entity.QueryRoleCondition;
import com.yizhilu.os.sysuser.entity.SysRole;

/**
 * @author liuqinggang
 * @ClassName RoleService
 * @package com.supergenius.sns.service.user
 * @description
 * @Create Date: 2013-3-1 下午10:19:50
 */
public interface RoleService {
    /**
     * 根据用户id获得roleList
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
     * @param roleId
     * @return Role
     */
    public SysRole getRoleById(Long roleId);

    /**
     * 更新角色对应的权限。先删除后增加
     *
     * @param roleId
     * @param functions
     */
    public void updateRoleFunction(Long roleId, Set<Long> functions);

    /**
     * 根据id删除role
     *
     * @param roleId
     */
    public void deleteRoleById(Long roleId);

    /**
     * 添加角色
     *
     * @param SysRole role
     */
    public void addRole(SysRole role);

    /**
     * 删除用户对应的角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    public void deleteUserRoleRef(Long userId, Long roleId);

    /**
     * 根据条件获得roleList
     *
     * @param queryRoleCondition
     * @return List<Role>
     */
    public List<SysRole> getRoleListByCondition(
            QueryRoleCondition queryRoleCondition);

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
