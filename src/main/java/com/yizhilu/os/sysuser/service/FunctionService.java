package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.QueryFunctionCondition;
import com.yizhilu.os.sysuser.entity.SysRole;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.service.sysuser.FunctionService
 * @description 权限管理
 * @Create Date : 2013-12-17 上午9:48:03
 */
public interface FunctionService {

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     * 生成的主键
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
     * @return 年级
     */
    public SysFunction getFunctionById(Long functionId);

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件
     * @return 查询结果
     */
    public List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition);

    /**
     * 获取可以使用的功能权限
     *
     * @return List<Function>
     */
    public List<SysFunction> getUsableFunctionList();

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    public List<SysFunction> getChildFunctionById(Long parentFunctionId);

    /**
     * 删除权限及子权限
     *
     * @return
     */
    public void delFunctions(List<Long> list);

    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return List<Function>
     */
    public List<SysFunction> getFunctionListByParentId(Long parentId);

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    public List<SysFunction> getFunctionsByRoles(List<SysRole> roles);
    
    
    /**
     * 根据id获取子权限列表,左侧显示，全部的权限
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    public List<SysFunction> getLeftFunctionById(Long parentFunctionId);
    
    

}
