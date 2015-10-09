package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.QueryFunctionCondition;
import com.yizhilu.os.sysuser.entity.SysRole;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.dao.sysuser.FunctionDao
 * @description 权限操作类
 * @Create Date : 2013-12-17 上午9:25:05
 */
public interface FunctionDao {
    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return
     */
    public List<SysFunction> getFunctionListByParentId(Long parentId);

    /**
     * 根据roleId获得权限列表
     *
     * @param roleId
     * @return
     */
    public List<SysFunction> getFunctionListByRoleId(Long roleId);

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     */
    public Long addFunction(SysFunction function);

    /**
     * 根据id删除一个功能
     *
     * @param functionId 要删除的id
     */
    public void delFunctionById(Long functionId);

    /**
     * 修改功能
     *
     * @param function 要修改的功能
     */
    public void updateFunction(SysFunction function);

    /**
     * 根据id获取单个功能对象
     *
     * @param functionId 要查询的id
     * @return Function
     */
    public SysFunction getFunctionById(Long functionId);

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件类
     * @return List<Function>
     */
    public List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition);

    /**
     * 获取可以使用的功能权限
     *
     * @return
     */
    public List<SysFunction> getUsableFunctionList();

    /**
     * 获取第一级权限菜单列表
     *
     * @param queryFunctionCondition
     * @return
     */
    public List<SysFunction> getFirFunction(
            QueryFunctionCondition queryFunctionCondition);

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return
     */
    public List<SysFunction> getChildFunctionById(Long parentFunctionId);

    /**
     * 删除权限及子权限
     *
     * @return
     */
    public void delFunctions(List<Long> list);

    /**
     * 根据多个parentId 查询
     *
     * @param parentList
     * @return
     */
    public List<SysFunction> getFunctionListByParentIdSet(List<Long> parentList);

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    public List<SysFunction> getFunctionsByRoles(List<SysRole> roles);


}
