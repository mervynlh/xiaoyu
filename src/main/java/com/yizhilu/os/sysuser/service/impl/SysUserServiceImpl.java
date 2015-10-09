package com.yizhilu.os.sysuser.service.impl;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.sysuser.dao.SysUserDao;
import com.yizhilu.os.sysuser.entity.QueryUserCondition;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.entity.SysUser;
import com.yizhilu.os.sysuser.service.FunctionService;
import com.yizhilu.os.sysuser.service.RoleService;
import com.yizhilu.os.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.service.impl.sysuser.UserServiceImpl
 * @description 系统用户service
 * @Create Date : 2013-12-14 下午2:28:04
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;

    /**
     * 根据登录名查询用户
     * @return User
     */
    @Override
    public SysUser getUserByLoginName(String name) {
        SysUser user = sysUserDao.getUserByName(name);
        return user;
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return User
     */
    @Override
    public SysUser getUserById(Long id) {
        SysUser user = sysUserDao.getUserById(id);
        return user;
    }

    /**
     * 查询 用户权限
     * @return List<Function>
     */
    @Override
    public List<SysFunction> getUserFunction(Long userId) {

        // 查询所拥有的role
        List<SysRole> roleList = roleService.getRoleListByUserId(userId);
        if (ObjectUtils.isNull(roleList)) {
            return null;
        } else {
            //将权限放到list中
        	List<SysFunction> functionList=functionService.getFunctionsByRoles(roleList);
        	if (ObjectUtils.isNotNull(functionList)) {
				for (SysFunction function : functionList) {
					//根据 链接地址类型：1，网校 2，社区 3，考试   拼装权限地址
					if(function.getFunctionUrlType()==1){
						function.setFunctionUrl(CommonConstants.contextPath+function.getFunctionUrl());
					}
				}
			}
            return functionList;
        }

    }

    /**
     * 查询所有用户,分页
     * @param queryUserCondition
     * @param pageEntity
     * @return List<User>
     */
    @Override
    public List<SysUser> getAllUserList(QueryUserCondition queryUserCondition,
                                        PageEntity pageEntity) {
        return sysUserDao.getAllUserList(queryUserCondition, pageEntity);
    }

    /**
     * 修改用户信息
     */
    public void updateUser(SysUser user) {
        sysUserDao.updateUser(user);
    }

    /**
     * 修改密码
     * @param user
     */
    public void updateUserPwd(SysUser user) {
        sysUserDao.updateUserPwd(user);
    }

    /**
     * 新增用户
     */
    public void addUser(SysUser user) {
        sysUserDao.addUser(user);
    }
}
