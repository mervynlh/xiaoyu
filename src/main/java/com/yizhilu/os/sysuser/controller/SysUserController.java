package com.yizhilu.os.sysuser.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.MD5;
import com.yizhilu.os.edu.constants.enums.UserOptType;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.sysuser.entity.QueryGroupCondition;
import com.yizhilu.os.sysuser.entity.QueryUserCondition;
import com.yizhilu.os.sysuser.entity.SysGroup;
import com.yizhilu.os.sysuser.entity.SysRole;
import com.yizhilu.os.sysuser.entity.SysUser;
import com.yizhilu.os.sysuser.service.GroupService;
import com.yizhilu.os.sysuser.service.RoleService;
import com.yizhilu.os.sysuser.service.SysUserService;

/**
 * @author liuqinggang
 * @ClassName UserAction
 * @package com.supergenius.sns.action.user
 * @description 后台用户管理
 * @Create Date: 2013-12-15 上午10:34:09
 */
@Controller
@RequestMapping("/admin")
public class SysUserController extends SysBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Getter
    @Setter
    private SysUser user;
    @Autowired
    private SysUserService sysUserService;
    private QueryUserCondition queryUserCondition;
    @Autowired
    private GroupService groupService;
    private QueryGroupCondition queryGroupCondition = new QueryGroupCondition();
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    // 路径
    private String toUserList = getViewPath("/sysuser/sysuser_list");// 用户列表页
    private String toAdduser = getViewPath("/sysuser/user_add");// 添加用户页面
    private String toEditUser = getViewPath("/sysuser/user_edit");// 修改用户页
    private String toEditUserRole = getViewPath("/sysuser/setRole");// 设置用户角色页面
    private String toUpdateUserPwd = getViewPath("/sysuser/user_update_pwd");// 修改用户密码
    private String userManager = getViewPath("/sysuser/userManager");//修改用户信息页面

    /**
     * 后台统一操作成功提示页面
     *
     * @return
     */
    @RequestMapping("/sys/success")
    public String toAdminSuccess() {
        return "/common/success";
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 用户列表
     */
    @RequestMapping("/user/listAllUser")
    public ModelAndView listAllUser(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition,
                                    @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toUserList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(20);// 设置每页为20，默认10
            List<SysUser> userList = sysUserService.getAllUserList(queryUserCondition,
                    this.getPage());
            List<SysGroup> groupList = groupService.getGroupList();
            modelAndView.addObject("groupList", gson.toJson(groupList));
            modelAndView.addObject("userList", userList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("UserAction.listAllUser", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改用户信息显示
     */
    @RequestMapping("/user/toEditUser")
    public ModelAndView toEditUser(@ModelAttribute("user") SysUser user) {
        ModelAndView modelAndView = new ModelAndView(toEditUser);
        try {
            // 此处查询一次查询出所有的页面来操作
            List<SysGroup> groupList = this.groupService.getGroupList();
            // 获得用户信息
            user = this.sysUserService.getUserById(user.getUserId());
            SysGroup userGroup = groupService.getGroupById(user.getGroupId());// 当前用户组
            user.setGroup(userGroup);
            modelAndView.addObject("groupList", gson.toJson(groupList));
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("UserAction.toEditUser", e);
        }
        return modelAndView;
    }

    /**
     * 用户组级联联动
     */
    @RequestMapping(value = "/user/getAllGroup")
    @ResponseBody
    public Map<String, Object> getAllGroup(@RequestParam String groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SysGroup> groupList = this.groupService.getChildGroupById(Long
                    .valueOf(groupId));
            map.put("entity", groupList);
        } catch (Exception e) {
            logger.error("UserAction.getAllGroup", e);
        }
        return map;
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("/user/editUser")
    public ModelAndView editUser(@ModelAttribute("user") SysUser user) {
        try {
            logger.info("++++ user" + user.getGroupId());
            sysUserService.updateUser(user);
        } catch (Exception e) {
            logger.error("UserAction.editUser", e);
        }
        return new ModelAndView("redirect:/admin/user/listAllUser");
    }

    /**
     * 冻结、解冻用户
     */
    @RequestMapping("/user/freezeUser")
    @ResponseBody
    public Map<String, Object> freezeUser(@ModelAttribute("user") SysUser user,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	//当前系统用户
        	SysUser sysuser=getSysLoginedUser(request);
        	Long userId=sysuser.getUserId();
        	if(userId==user.getUserId()){
        		map.put("result", "不能冻结自己");
        		return map;
        	}
            user = this.sysUserService.getUserById(user.getUserId());
            // 冻结和解冻状态
            String userOptType;//操作类型
            if (user.getStatus() == SysUser.USER_DEFAULT_STATUS) {
                user.setStatus(SysUser.USER_FREEZE_STATUS);
                userOptType=UserOptType.SYSFROZEN.toString();//冻结
            } else {
                user.setStatus(SysUser.USER_DEFAULT_STATUS);
                userOptType=UserOptType.SYSACTIVE.toString();//解冻
            }
            this.sysUserService.updateUser(user);
            //记录系统用户操作
            Map<String,Object> descMap=new HashMap<String,Object>();
            descMap.put("optuser", "操作id_"+userId);
            descMap.put("optType", "操作_执行"+userOptType.equals(UserOptType.SYSFROZEN.toString()) != null?"冻结":"解冻");
            descMap.put("userId", "用户id_"+user.getUserId());
            userService.addUserOptRecord(user.getUserId(),userOptType, userId, sysuser.getLoginName(), user.getUserId(), gson.toJson(descMap));
            map.put("result", "success");
        } catch (Exception e) {
            map.put("result", "error");
            logger.error("UserAction.freezeUser", e);
        }
        return map;
    }

    /**
     * 删除用户
     */
    @RequestMapping("/user/deleteUser")
    public ModelAndView deleteUser(@ModelAttribute("user") SysUser user,HttpServletRequest request) {
        try {// 软删除状态设置为2
        	//当前系统用户
        	SysUser sysuser=getSysLoginedUser(request);
        	Long userId=sysuser.getUserId();
        	if(userId==user.getUserId()){
        		return new ModelAndView("redirect:/admin/user/listAllUser?page.currentPage=1");
        	}
            user = this.sysUserService.getUserById(user.getUserId());
            user.setStatus(SysUser.USER_DELETE_STATUS);
            this.sysUserService.updateUser(user);
            //记录系统用户操作
            Map<String,Object> descMap=new HashMap<String,Object>();
            descMap.put("optuser", "操作id_"+userId);
            descMap.put("optType", "操作_"+UserOptType.SYSDELETE.toString()+"_执行删除");
            descMap.put("userId", "用户id_"+user.getUserId());
            userService.addUserOptRecord(user.getUserId(),UserOptType.SYSDELETE.toString(), userId, sysuser.getLoginName(), user.getUserId(), gson.toJson(descMap));
        } catch (Exception e) {
            logger.error("UserAction.deleteUser", e);
        }
        return new ModelAndView("redirect:/admin/user/listAllUser?page.currentPage=1");
    }

    /**
     * 修改密码显示页面(自己修改)
     */
    @RequestMapping("/user/toUpdatePwd")
    public ModelAndView toUpdatePwd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toUpdateUserPwd);
        try {
            user = this.getSysLoginedUser(request);
            user = this.sysUserService.getUserById(user.getUserId());
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("UserAction.toUpdatePwd", e);
        }
        return modelAndView;
    }

    /**
     * 修改密码显示页面
     * 修改其他用户
     */
    @RequestMapping("/user/toUpdateUserPwd")
    public ModelAndView toUpdateUserPwd(@ModelAttribute("user") SysUser user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toUpdateUserPwd);
        try {
            user = this.sysUserService.getUserById(user.getUserId());
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("UserAction.toUpdateUserPwd", e);
        }
        return modelAndView;
    }

    /**
     * 用户修改密码
     */
    @RequestMapping("/user/updatePwd")
    public ModelAndView updatePwd(@ModelAttribute("user") SysUser user) {
        try {
            SysUser u = sysUserService.getUserById(user.getUserId());
            u.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
            sysUserService.updateUser(u);
        } catch (Exception e) {
            logger.error("UserAction.updatePwd", e);
        }
        return new ModelAndView(ADMIN_SUCCESS);
    }

    /**
     * 用户角色修改(权限)
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/toEditUserGradeSubjectRoleSec")
    public ModelAndView toEditUserGradeSubjectRoleSec(@ModelAttribute("user") SysUser user)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView(toEditUserRole);
        try {
            // 查询用户信息
            user = this.sysUserService.getUserById(user.getUserId());
            // 用户的角色list
            List<SysRole> roleList = roleService.getRoleListByUserId(user.getUserId());
            user.setRoleList(roleList);
            modelAndView.addObject("user", user);
            modelAndView.addObject("rolelist", roleList);
        } catch (Exception e) {
            logger.error("UserAction.toEditUserGradeSubjectRoleSec", e);
        }
        return modelAndView;
    }

    /**
     * 用户信息自修改
     *
     * @return
     */
    @RequestMapping("/user/userManagerSubmit")
    public String userManagerSubmit(@ModelAttribute("user") SysUser user, HttpServletRequest request) {
        try {
            SysUser sysUser = getSysLoginedUser(request);
            sysUser.setUserName(user.getUserName());
            sysUser.setEmail(user.getEmail());
            sysUser.setTel(user.getTel());
            sysUserService.updateUser(sysUser);
        } catch (Exception e) {
            logger.error("UserAction.userManagerSubmit", e);
            return ERROR;
        }
        return ADMIN_SUCCESS;
    }

    /**
     * 个人用户信息修改
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/userManager")
    public String userManager(HttpServletRequest request, HttpServletResponse response) {
        //从session中获取信息
        user = this.getSysLoginedUser(request);
        request.setAttribute("user", user);
        return userManager;
    }

    /**
     * 修改密码
     *
     * @param changePwd
     */
    public String changePwdSubmit(HttpServletRequest request, HttpServletResponse response) {
        try {
            SysUser user = getSysLoginedUser(request);
            if (user.getLoginPwd().equals(MD5.getMD5(this.user.getLoginPwd()))) {
                String changePwd = request.getParameter("changePwd");
                user.setLoginPwd(MD5.getMD5(changePwd));
                sysUserService.updateUserPwd(user);
                // addActionError("密码修改成功!");
            } else {
                // addActionError("原密码输入错误！");
            }
        } catch (Exception e) {
            logger.error("UserAction.changePwdSubmit", e);
            return "";
        }
        return "changePwd";
    }

    /**
     * 新增用户显示
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/user/toAddUser")
    public ModelAndView toAddUser() {
        ModelAndView modelAndView = new ModelAndView(toAdduser);
        // 一级组
        try {
            List<SysGroup> groupList = this.groupService.getGroupList();
            modelAndView.addObject("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("UserAction.toAddUser", e);
        }
        return modelAndView;
    }

    /**
     * 添加新用户
     *
     * @return
     */
    @RequestMapping("/user/addUser")
    public ModelAndView addUser(@ModelAttribute("user") SysUser user) {
        try {
            user.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
            user.setCreateTime(new Date());
            this.sysUserService.addUser(user);
        } catch (Exception e) {
            logger.error("UserAction.addUser", e);
        }
        return new ModelAndView("redirect:/admin/user/listAllUser");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryUserCondition")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUserCondition.");
    }

    /**
     * 检查用户名是否可用
     *
     * @return
     */
    @RequestMapping("/user/checkLoginName")
    public String checkLoginName(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String loginName = queryUserCondition.getSearchKey();
            if (StringUtils.isNotEmpty(loginName)) {
                SysUser user2 = sysUserService.getUserByLoginName(loginName.trim());
                if (user2 == null) {
                    this.sendMessage(request, response, "true");
                    return null;
                }
            }
            this.sendMessage(request, response, "false");
        } catch (Exception e) {
            logger.error("UserAction.checkLoginName", e);
            return "";
        }
        return null;
    }
    
    public QueryGroupCondition getQueryGroupCondition() {
        if (queryGroupCondition == null) {
            queryGroupCondition = new QueryGroupCondition();
        }
        return queryGroupCondition;
    }

    public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
        this.queryGroupCondition = queryGroupCondition;
    }

    public QueryUserCondition getQueryUserCondition() {
        if (queryUserCondition == null) {
            queryUserCondition = new QueryUserCondition();
            queryUserCondition.setUserType(-1);
        }
        return queryUserCondition;
    }

    public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
        this.queryUserCondition = queryUserCondition;
    }

}
