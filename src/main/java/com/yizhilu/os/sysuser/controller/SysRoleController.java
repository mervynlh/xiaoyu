package com.yizhilu.os.sysuser.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.sysuser.entity.QueryRoleCondition;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.entity.SysUser;
import com.yizhilu.os.sysuser.service.FunctionService;
import com.yizhilu.os.sysuser.service.RoleService;
import com.yizhilu.os.sysuser.service.SysUserService;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.action.sysuser.SysRoleAction
 * @description 角色管理
 * @Create Date : 2013-12-14 下午6:00:30
 */
@Controller
@RequestMapping("/admin")
public class SysRoleController extends SysBaseController {

    // log
    private static final Logger logger = LoggerFactory.getLogger(SysGroupController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private SysUserService sysUserService;

    // 路径
    String to_add_ref = getViewPath("/sysuser/role_addRef");// 添加角色
    String role_list = getViewPath("/sysuser/role_list");// 角色列表

    @Getter
    @Setter
    private SysRole role;// 页面传递角色对象使用变量
    @Getter
    @Setter
    private SysRole addRole;
    @Getter
    @Setter
    private SysUser user;

    /**
     * 角色管理显示列表
     */
    @RequestMapping("/role/roleList")
    public ModelAndView roleList(@ModelAttribute("role") SysRole role) {
        ModelAndView modelAndView = new ModelAndView(role_list);
        try {
            List<SysRole> roleList = roleService.getRoleList();
            if (roleList == null || roleList.size() == 0) {
                return modelAndView;
            }
            // 默认显示所有角色中的第一个
            if (role != null && role.getRoleId() != null && role.getRoleId().longValue() != 0) {
                role = roleService.getRoleById(role.getRoleId());
            } else {
                if (!ObjectUtils.isNull(roleList)) {
                    role = roleService.getRoleById(roleList.get(0).getRoleId());// 选中某个角色时显示此角色
                }

            }
            List<SysFunction> firstLevelFunctionList = functionService.getFunctionList(null);

            modelAndView.addObject("roleList", roleList);
            modelAndView.addObject("functionList", gson.toJson(firstLevelFunctionList));
            modelAndView.addObject("myfunctionList", gson.toJson(role.getFunctionList()));

            modelAndView.addObject("role", role);
        } catch (Exception e) {
            logger.error(" ++ SysRoleAction.getAllRoleList ", e);
        }
        return modelAndView;
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("role")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    /**
     * json获得role.页面下拉用
     *
     * @return
     */
    @RequestMapping("/role/getJsonRoleById")
    @ResponseBody
    public Map<String, Object> getJsonRoleById(@ModelAttribute("role") SysRole role) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            role = roleService.getRoleById(role.getRoleId());
            map.put("entity", role);
        } catch (Exception e) {
            logger.error(" ++ getJsonRoleById ", e);
        }
        return map;
    }

    /**
     * 更新角色下拥有的权限 selectedFunctionIds 为权限
     */
    @RequestMapping("/role/updateRoleFunction")
    public ModelAndView updateRoleFunction(@ModelAttribute("role") SysRole role,
                                           @RequestParam("selectedFunctionIds") Set<Long> selectedFunctionIds) {
        try {
            // 更新角色权限
            logger.info("role.getRoleId:" + role.getRoleId() + ",selectedFunctionIds:"
                    + selectedFunctionIds);
            roleService.updateRoleFunction(role.getRoleId(), selectedFunctionIds);
        } catch (Exception e) {
            logger.error("RoleAction.updateRoleFunction", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList?role.roleId=" + role.getRoleId());
    }

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     * @return
     */
    @RequestMapping("/role/updateRoleName")
    public ModelAndView updateRoleName(@RequestParam("updateRoleName") String roleName,
                                       @RequestParam("updateRoleId") Long roleId) {
        try {
            // 更新角色名称
            logger.info("role.getRoleId:" + roleId + ",updateRoleName:"
                    + roleName);
            if (ObjectUtils.isNull(roleName) || ObjectUtils.isNull(roleId)) {
                return new ModelAndView(ADMIN_SUCCESS);
            }
            roleService.updateRoleName(roleName, roleId);
        } catch (Exception e) {
            logger.error("RoleAction.updateRoleFunction", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList?role.roleId=" + roleId);
    }


    /**
     * 删除角色
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/role/delRole")
    public ModelAndView delRole(@ModelAttribute("role") SysRole role) {
        try {
            roleService.deleteRoleById(role.getRoleId());
            role = null;
        } catch (Exception e) {
            logger.error("RoleAction.delRole", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList");
    }

    /**
     * 添加角色
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/role/addRole")
    public ModelAndView addRole(@ModelAttribute("role") SysRole role) {
        try {
            role.setCreateTime(new Date(System.currentTimeMillis()));
            role.setRoleApplyScopeId(SysRole.ROLE_APPLY_SCOPE_ALL);
            role.setRoleTypeId(SysRole.ROLE_TYPE_DEFAULT);
            role.setStatus(SysRole.ROLE_STATUS_DEFAULT);
            // 添加角色(添加方法中要返回主键)
            roleService.addRole(role);
        } catch (Exception e) {
            logger.error("RoleAction.addRole", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList");
    }

    /**
     * 删除 用户对应的role(包括相关关联) 必须参数roleId userId
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/deleteRoleRef")
    public ModelAndView deleteRoleRef(@RequestParam("roleId") String roleId,
                                      @RequestParam("userId") String userId) {

        try {
            if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(roleId)) {
                //删除用户角色中间表
                this.roleService.deleteUserRoleRef(Long.valueOf(userId), Long.valueOf(roleId));
            }
        } catch (Exception e) {
            logger.error("RoleAction.deleteRoleRef", e);
        }
        return new ModelAndView(
                "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId=" + userId);
    }

    /**
     * 用户添加新角色显示
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/toAddRoleRef")
    public ModelAndView toAddRoleRef(HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView(to_add_ref);
        try {
            QueryRoleCondition queryRoleCondition = new QueryRoleCondition();
            //queryRoleCondition.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);// 不显示超级管理
            // 查询所有的角色list
            List<SysRole> roleList = this.roleService
                    .getRoleListByCondition(queryRoleCondition);
            user = sysUserService.getUserById(Long.valueOf(userId));
            modelAndView.addObject("roleList", roleList);
            modelAndView.addObject("userId", userId);
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("RoleAction.toAddRoleRef", e);
        }
        return modelAndView;
    }

    /**
     * 用户添加角色 需要参数 用户id 角色id
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/addRoleRef")
    public ModelAndView addRoleRef(HttpServletRequest request,
                                   HttpServletResponse response, @RequestParam("userId") String userId,
                                   @RequestParam("roleId") String roleId) {
        try {
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleId)) {
                return new ModelAndView(
                        "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId="
                                + userId);
            }
            // 添加
            this.roleService.addUserRoleRef(Long.valueOf(userId),
                    Long.valueOf(roleId));
        } catch (Exception e) {
            logger.error("RoleAction.addRoleRef", e);
        }
        return new ModelAndView(
                "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId=" + userId);
    }

}
