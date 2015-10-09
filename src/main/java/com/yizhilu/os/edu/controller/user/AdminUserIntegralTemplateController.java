package com.yizhilu.os.edu.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
import com.yizhilu.os.edu.service.user.UserIntegralTemplateService;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * 
 * @ClassName 
 *            com.yizhilu.os.edu.controller.user.AdminUserIntegralTemplateController
 * @description 积分模板管理
 * @author :xujunbao
 * @Create Date : 2014年9月27日 上午9:30:30
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralTemplateController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralTemplateController.class);

	private static final String toUserIntegralTemplate = getViewPath("/admin/user/userIntegralTemplate_list");// 积分模板页
	private static final String updateUserIntegralTemplate = getViewPath("/admin/user/userIntegralTemplate_update");// 积分模板页
	private static final String toAddUserIntegralTemplate = getViewPath("/admin/user/userIntegralTemplate_add");// 添加积分模板页
	@Autowired
	private UserIntegralTemplateService userIntegralTemplateService;

	// 绑定参数变量
	@InitBinder("userIntegralTemplate")
	public void initBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userIntegralTemplate.");
	}

	/**
	 * 查询积分模板
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/integral/template/list")
	public String getUserIntegralTemplateList(HttpServletRequest request, Model model) {
		try {
			// 查询积分模板
			List<UserIntegralTemplate> integralTempateList = userIntegralTemplateService.getUserIntegralTemplateList();
			// 把查询返回的数据放到model中
			model.addAttribute("integralTempateList", integralTempateList);
		} catch (Exception e) {
			logger.error("getUserIntegralTemplateList", e);
			return setExceptionRequest(request, e);
		}
		return toUserIntegralTemplate;
	}

	/**
	 * 查询详情，跳转更新页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/integral/template/toupdate/{id}")
	public String getUserIntegralTemplate(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
		try {
			// 查询详情
			UserIntegralTemplate userIntegralTemplate = userIntegralTemplateService.getUserIntegralTemplateById(id);
			// 把查询返回的数据放到model中
			model.addAttribute("userIntegralTemplate", userIntegralTemplate);
		} catch (Exception e) {
			logger.error("getUserIntegralTemplate", e);
			return setExceptionRequest(request, e);
		}
		return updateUserIntegralTemplate;
	}

	/**
	 * 更新积分模板
	 * 
	 * @param request
	 * @param userIntegralTemplate
	 * @return
	 */
	@RequestMapping("/integral/template/update")
	public String updateUserIntegralTemplate(HttpServletRequest request, @ModelAttribute UserIntegralTemplate userIntegralTemplate) {
		try {
			if (ObjectUtils.isNotNull(userIntegralTemplate)) {
				// 获得当前操作人
				JsonObject user =  SingletonLoginUtils.getSysUser(request);;
				if (ObjectUtils.isNotNull(user)) {
					userIntegralTemplate.setCreateUser(user.get("loginName").toString());
				}
				// 更新积分模板
				userIntegralTemplate.setUpdateTime(new Date());
				userIntegralTemplateService.updateUserIntegralTemplate(userIntegralTemplate);
			}
		} catch (Exception e) {
			logger.error("updateUserIntegralTemplate", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/integral/template/list";
	}

	/**
	 * 更新模板状态
	 * 
	 * @param request
	 * @param userIntegralTemplate
	 * @return
	 */
	@RequestMapping("/integral/template/updatestatus")
	@ResponseBody
	public Map<String, Object> updateUserIntegralTemplateStatus(HttpServletRequest request, @ModelAttribute UserIntegralTemplate userIntegralTemplate) {
		try {
			if (ObjectUtils.isNotNull(userIntegralTemplate)) {
				// 更新模板状态 0正常 1停止
				userIntegralTemplateService.updateUserIntegralTemplateStatus(userIntegralTemplate);
				this.setJson(true, "修改成功", null);
			}

		} catch (Exception e) {
			logger.error("updateUserIntegralTemplateStatus", e);
			this.setJson(false, "修改失败", null);
		}
		return json;
	}

	/**
	 * 跳转的添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/integral/template/toadd")
	public String toAddUserIntegralTemplate() {
		return toAddUserIntegralTemplate;
	}

	/**
	 * 添加积分模板
	 * 
	 * @param request
	 * @param userIntegralTemplate
	 * @return
	 */
	@RequestMapping("/integral/template/add")
	public String addUserIntegralTemplate(HttpServletRequest request, @ModelAttribute UserIntegralTemplate userIntegralTemplate) {
		try {
			if (ObjectUtils.isNotNull(userIntegralTemplate)) {// 添加积分模板
				userIntegralTemplate.setStatus(0L);
				userIntegralTemplate.setCreateTime(new Date());
				userIntegralTemplate.setUpdateTime(new Date());
				userIntegralTemplate.setCreateUser(this.getSysLoginLoginName(request));//添加操作人
				userIntegralTemplateService.addUserIntegralTemplate(userIntegralTemplate);
			}
		} catch (Exception e) {
			logger.error("addUserIntegralTemplate", e);
		}
		return "redirect:/admin/integral/template/list";
	}
}
