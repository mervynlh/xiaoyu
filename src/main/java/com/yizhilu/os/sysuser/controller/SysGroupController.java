package com.yizhilu.os.sysuser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.sysuser.entity.SysGroup;
import com.yizhilu.os.sysuser.service.GroupService;

/**
 * @author liuqinggang
 * @ClassName GroupAction
 * @package com.supergenius.sns.action.user
 * @description 用户组管理
 * @Create Date: 2013-12-13 下午06:08:42
 */
@Controller
@RequestMapping("/admin")
public class SysGroupController extends SysBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysGroupController.class);

    private SysGroup group;
    @Autowired
    private GroupService groupService;

    /**
     * 返回值
     */

    private String toAddGroup = getViewPath("/sysuser/group_add");// 添加用户组
    private String toGroupList = getViewPath("/sysuser/group_list");// 用户组管理。
    private String updateGroup = getViewPath("/sysuser/group_update");// 更新用户组

    /**
     * 用户组管理显示
     */
    @RequestMapping("/group/groupList")
    public ModelAndView toDisGroup(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toGroupList);
        try {
            // 所有的组
            List<SysGroup> groupList = this.groupService.getGroupList();
            modelAndView.addObject("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("GroupAction.toDisGroup", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 删除用户组 以及组下用户，软删除 只改状态
     *
     * @param groupIds groupIds以逗号隔开的
     * @param request
     * @return
     */
    @RequestMapping("/group/deleteGroup")
    public ModelAndView deleteGroup(@RequestParam("groupIds") String groupIds,
                                    HttpServletRequest request) {
        try {
            // 页面传递拼装的参数 groupIds以逗号隔开的
            this.groupService.deleteGroups(groupIds);
        } catch (Exception e) {
            logger.error("GroupAction.deleteGroup", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return new ModelAndView("redirect:/admin/group/groupList");
    }

    /**
     * 添加组显示跳转
     */
    @RequestMapping("/group/toAddGroup")
    public ModelAndView toAddGroup(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toAddGroup);
        try {
            // 全部组
            List<SysGroup> groupList = this.groupService.getGroupList();
            // 所有的组
            modelAndView.addObject("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("GroupAction.toAddGroup", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("group")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("group.");
    }

    /**
     * 添加组
     */
    @RequestMapping("/group/addGroup")
    public ModelAndView addGroup(@ModelAttribute("group") SysGroup group,
                                 HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_SUCCESS);
        try {
            // 页面拼装组名称和父id
            this.groupService.addGroup(group);
        } catch (Exception e) {
            logger.error("GroupAction.addGroup", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }

        return modelAndView;
    }

    /**
     * 修改组显示
     */
    @RequestMapping("/group/toUpdateGroup")
    public ModelAndView toUpdateGroup(@RequestParam("groupId") Long paramGroupId,
                                      HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(updateGroup);
        try {

            List<SysGroup> groupList = this.groupService.getGroupList();

            SysGroup group = this.groupService.getGroupById(paramGroupId);
            SysGroup parentGroup;
            if (group.getParentGroupId() == 0) {
                parentGroup = new SysGroup();
                parentGroup.setGroupName("根目录");
            } else {
                parentGroup = groupService.getGroupById(group.getParentGroupId());
            }

            modelAndView.addObject("group", group);
            modelAndView.addObject("parentGroup", parentGroup);
            modelAndView.addObject("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("GroupAction.toUpdateGroup", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改组
     */
    @RequestMapping("/group/updateGroup")
    public String updateGroup(@ModelAttribute("group") SysGroup group,
                              HttpServletRequest request) {
        try {
            // 修改群组信息
            this.groupService.updateGroup(group);
        } catch (Exception e) {
            logger.error("GroupAction.updateGroup", e);
            setExceptionRequest(request, e);
            return "redirect:/admin/group/groupList";
        }
        return "redirect:/admin/group/groupList";
    }

    /**
     * 修改组
     */
    @RequestMapping("/group/success")
    public String toSuccess(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 修改群组信息
            this.groupService.updateGroup(group);
        } catch (Exception e) {
            logger.error("GroupAction.updateGroup", e);
            setExceptionRequest(request, e);
            return "redirect:/admin/group/groupList";
        }
        return ADMIN_SUCCESS;
    }

}
