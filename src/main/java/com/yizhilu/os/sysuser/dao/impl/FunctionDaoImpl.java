package com.yizhilu.os.sysuser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.sysuser.dao.FunctionDao;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.QueryFunctionCondition;
import com.yizhilu.os.sysuser.entity.SysRole;

/**
 * @author liuqinggang
 * @ClassName FunctionDaoImpl
 * @package com.supergenius.sns.dao.impl.user
 * @description
 * @Create Date: 2013-3-3 上午12:14:10
 */
@Repository("functionDao")
public class FunctionDaoImpl extends GenericDaoImpl implements FunctionDao {
    /**
     * 根据roleId获得权限列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysFunction> getFunctionListByRoleId(Long roleId) {
        List<SysFunction> list = this.selectList("FunctionMapper.getFunctionListByRoleId",
                roleId);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     */
    public Long addFunction(SysFunction function) {
        return this.insert("FunctionMapper.createFunction", function);
    }

    /**
     * 根据id删除一个功能
     *
     * @param functionId 要删除的id
     */
    public void delFunctionById(Long functionId) {
        this.delete("FunctionMapper.deleteFunctionById", functionId);
    }

    /**
     * 修改功能
     *
     * @param function 要修改的功能
     */
    public void updateFunction(SysFunction function) {
        this.update("FunctionMapper.updateFunction", function);
    }

    /**
     * 根据id获取单个功能对象
     *
     * @param functionId 要查询的id
     * @return Function
     */
    public SysFunction getFunctionById(Long functionId) {
        return this.selectOne("FunctionMapper.getFunctionById", functionId);
    }

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件类
     * @return List<Function>
     */
    public List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition) {
        return this.selectList("FunctionMapper.getFunctionList", queryFunctionCondition);
    }

    public List<SysFunction> getUsableFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setStatus(1);
        return getFunctionList(queryFunctionCondition);
    }

    /**
     * 获取第一级权限菜单列表
     *
     * @param queryFunctionCondition
     * @return
     */
    public List<SysFunction> getFirFunction(QueryFunctionCondition queryFunctionCondition) {
        return this.selectList("FunctionMapper.getFirFunction", queryFunctionCondition);
    }

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return
     */
    public List<SysFunction> getChildFunctionById(Long functionId) {
        return this.selectList("FunctionMapper.getChildFunctionById", functionId);
    }

    /**
     * 根据多个parentId 查询
     *
     * @param parentList
     * @return
     */
    public List<SysFunction> getFunctionListByParentIdSet(List<Long> parentList) {
        return this.selectList("FunctionMapper.getFunctionListByParentIdSet", parentList);
    }

    /**
     * 删除权限及子权限
     *
     * @return
     */
    @Override
    public void delFunctions(List<Long> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Long id : list) {

            // 删除role角色中的对应关系
            this.delete("RoleFunctionMapper.deleteRoleFunctionByFunctionId", id);
            // 删除function
            delFunctionById(id);
            // 循环查询节点
            List<Long> childListParam = new ArrayList<Long>();
            childListParam.add(id);
            List<SysFunction> functionlist = this.selectList(
                    "FunctionMapper.getFunctionListByParentIdSet", childListParam);
            while (functionlist != null && functionlist.size() > 0) {
                // 批量删除role角色中的对应关系
                this.delete("RoleFunctionMapper.deleteRoleFunctionByFunctionIdBatch",
                        functionlist);
                // 批量删除function
                this.delete("FunctionMapper.deleteFunctionByIdBatch", functionlist);
                // 查询参数
                childListParam = new ArrayList<Long>();
                for (SysFunction function : functionlist) {
                    childListParam.add(function.getFunctionId());
                }
                // 继续查询，为空时代表删除完成
                functionlist = this.selectList(
                        "FunctionMapper.getFunctionListByParentIdSet", childListParam);
            }
        }

    }

    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysFunction> getFunctionListByParentId(Long parentId) {
        return this.selectList("FunctionMapper.getFunctionListByParentId", parentId);
    }

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    public List<SysFunction> getFunctionsByRoles(List<SysRole> roles) {
        return this.selectList("FunctionMapper.getFunctionsByRoles", roles);
    }

}
