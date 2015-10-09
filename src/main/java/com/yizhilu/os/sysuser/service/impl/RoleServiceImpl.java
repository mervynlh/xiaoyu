package com.yizhilu.os.sysuser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.sysuser.dao.FunctionDao;
import com.yizhilu.os.sysuser.dao.RoleDao;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.QueryRoleCondition;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.entity.RoleFunction;
import com.yizhilu.os.sysuser.service.RoleService;

/**
 * @author liuqinggang
 * @ClassName RoleServiceImpl
 * @package com.supergenius.sns.service.impl.user
 * @description
 * @Create Date: 2013-3-3 上午12:10:51
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private FunctionDao functionDao;

    /**
     * 根据用户id获得roleList
     *
     * @param userId
     * @return List<Role>
     */
    @Override
    public List<SysRole> getRoleListByUserId(Long userId) {
        List<SysRole> roles = roleDao.getRoleListByUserId(userId);
        if (roles != null && roles.size() > 0) {
            for (SysRole role : roles) {
                try {
                    List<SysFunction> functionList = functionDao
                            .getFunctionListByRoleId(role.getRoleId());
                    role.setFunctionList(functionList);
                } catch (Exception e) {
                }

            }
            return roles;
        }
        return null;
    }

    /**
     * 获得所有roleList
     *
     * @return List<Role>
     */
    public List<SysRole> getRoleList() {
        return roleDao.getRoleList();
    }

    /**
     * 根据id获得有role
     *
     * @param roleId
     * @return Role
     */
    public SysRole getRoleById(Long roleId) {
        SysRole role = roleDao.getRoleById(roleId);
        List<SysFunction> functionList = functionDao.getFunctionListByRoleId(role
                .getRoleId());
        role.setFunctionList(functionList);
        return role;
    }

    /**
     * 更新角色对应的权限。先删除后增加
     *
     * @param roleId
     * @param functions
     */
    public void updateRoleFunction(Long roleId, Set<Long> functions) {
        roleDao.deleteRoleFunctionByRoleId(roleId);// 删除
        if (functions != null && functions.size() > 0) {
            List<RoleFunction> list = new ArrayList<RoleFunction>();
            for (Long functionId : functions) {
                RoleFunction roleFunction = new RoleFunction();
                roleFunction.setCreateTime(new Date(System.currentTimeMillis()));
                roleFunction.setFunctionId(functionId);
                roleFunction.setRoleId(roleId);
                roleFunction.setStatus(RoleFunction.ROLE_FUNCTION_STATUS_DEFAULT);
                list.add(roleFunction);
            }
            roleDao.createRoleFunctionBatch(list);// 增加
        }
    }

    /**
     * 根据id删除role
     *
     * @param roleId
     */
    public void deleteRoleById(Long roleId) {
        // 删除角色和权限的对应关系表
        roleDao.deleteRoleFunctionByRoleId(roleId);
        // 删除用户和角色对应关系表
        roleDao.deleteUserRoleByRoleId(roleId);
        // 删除角色表
        roleDao.deleteRoleById(roleId);
    }

    /**
     * 添加角色
     *
     * @param SysRole role
     */
    public void addRole(SysRole role) {
        roleDao.addRole(role);
    }

    /**
     * 删除用户对应的角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    public void deleteUserRoleRef(Long userId, Long roleId) {
        roleDao.deleteUserRoleRef(userId, roleId);
    }

    /**
     * 根据条件获得roleList
     *
     * @param queryRoleCondition
     * @return List<Role>
     */
    public List<SysRole> getRoleListByCondition(QueryRoleCondition queryRoleCondition) {
        return roleDao.getRoleListByCondition(queryRoleCondition);
    }

    /**
     * 用户添加角色对应关系
     *
     * @param userId
     * @param roleId
     */
    public void addUserRoleRef(Long userId, Long roleId) {
        List<SysRole> list = getRoleListByUserId(userId);
        //判断之前是否有此权限
        boolean isEx = false;
        if (!ObjectUtils.isNull(list)) {
            for (SysRole role : list) {
                if (roleId.longValue() == role.getRoleId().longValue()) {
                    isEx = true;
                    break;
                }
            }
        }
        //不存在时添加
        if (!isEx) {
            roleDao.addUserRoleRef(userId, roleId);
        }
    }

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     */
    public void updateRoleName(String roleName, Long roleId) {
        roleDao.updateRoleName(roleName, roleId);
    }
}
