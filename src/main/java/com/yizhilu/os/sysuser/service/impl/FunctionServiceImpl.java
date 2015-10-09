package com.yizhilu.os.sysuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.sysuser.dao.FunctionDao;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.QueryFunctionCondition;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.service.FunctionService;

/**
 * @author liuqinggang
 * @ClassName FunctionServiceImpl
 * @package com.supergenius.sns.service.impl.user
 * @description
 * @Create Date: 2013-3-3 上午12:11:13
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionDao functionDao;

    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return List<Function>
     */
    public List<SysFunction> getFunctionListByParentId(Long parentId) {
        return functionDao.getFunctionListByParentId(parentId);
    }

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     * 生成的主键
     */
    @Override
    public Long addFunction(SysFunction function) {
        return functionDao.addFunction(function);
    }

    /**
     * 根据id删除一个功能
     *
     * @param functionId 要删除的id
     */
    @Override
    public void delFunctionById(Long functionId) {
        functionDao.delFunctionById(functionId);
    }

    /**
     * 修改功能
     *
     * @param function 要修改的功能
     */
    @Override
    public void updateFunction(SysFunction function) {
        functionDao.updateFunction(function);
    }

    /**
     * 根据id获取单个功能对象
     *
     * @param functionId 要查询的id
     * @return 年级
     */
    @Override
    public SysFunction getFunctionById(Long functionId) {
        return functionDao.getFunctionById(functionId);
    }

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件
     * @return 查询结果
     */
    @Override
    public List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition) {
        return functionDao.getFunctionList(queryFunctionCondition);
    }

    /**
     * 获取可以使用的功能权限
     *
     * @return List<Function>
     */
    @Override
    public List<SysFunction> getUsableFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        return getFunctionList(queryFunctionCondition);
    }

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    @Override
    public List<SysFunction> getChildFunctionById(Long functionId) {
        return functionDao.getChildFunctionById(functionId);
    }

    /**
     * 删除权限及子权限
     *
     * @return
     */
    @Override
    public void delFunctions(List<Long> list) {
        if (list != null) {
            functionDao.delFunctions(list);
        }
    }

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    public List<SysFunction> getFunctionsByRoles(List<SysRole> roles) {
        return functionDao.getFunctionsByRoles(roles);
    }
    
    /**
     * 根据id获取子权限列表,左侧显示，全部的权限
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    public List<SysFunction> getLeftFunctionById(Long parentFunctionId){
        List<SysFunction> functions=getChildFunctionById(parentFunctionId);
        if(ObjectUtils.isNotNull(functions)){
            
        }
        return functions;
    }
}
