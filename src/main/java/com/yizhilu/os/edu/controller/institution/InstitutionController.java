package com.yizhilu.os.edu.controller.institution;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PreventInfusion;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.UserExpandFrom;
import com.yizhilu.os.edu.entity.institution.Institution;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.service.institution.InstitutionService;
import com.yizhilu.os.edu.service.user.UserService;

@Controller
public class InstitutionController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);

	private static final String toAddInstitution = getViewPath("/institution/addInstitution");
	private static final String showInstitution = getViewPath("/institution/institution_info");
	
	private static final String institution_list = getViewPath("/institution/institution_list");// 入驻机构列表

	@Autowired
	private InstitutionService institutionService;
	@Autowired
	private UserService userService;
	
	@InitBinder("institution")
	public void initBinderInstitution(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("institution.");
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/front/toAddInstitution")
	public String toAddInstitution() {
		return toAddInstitution;
	}

	/**
	 * 添加机构
	 * @param model
	 * @param institution
	 * @param request
	 * @return
	 */
	@RequestMapping("/institution/addInstitution")
	@ResponseBody
	public Map<String,Object> addInstitution(@ModelAttribute("institution") Institution institution,HttpServletRequest request) {
		try {
			User user = userService.getUserByMobileNumber(institution.getMobile());
			if(ObjectUtils.isNull(user)){
				String password = request.getParameter("password"); // 密码
				String rpassword = request.getParameter("rpassword"); // 确认密码
				// 验证密码是否为空
				if (ObjectUtils.isNull(password) || StringUtils.isEmpty(password)) {
					this.setJson(false, "密码不能为空", null);
					return json;
				}
				// 验证密码一致
				if (!password.equals(rpassword)) {
					this.setJson(false, "两次输入密码不一致", null);
					return json;
				}
				// 创建用户
				user = new User();
				user.setMobile(institution.getMobile());// 手机号
				user.setPassword(password);// 密码
				String userIp = WebUtils.getIpAddr(request);// 用户注册ip
				user.setUserip(userIp);
				user.setUserType(0);// 学生类型
				user.setRegisterFrom(UserExpandFrom.registerFrom.toString());// 账号来源是通过注册生成的
				Long userId = userService.addUser(user);
				// 机构关联用户id
				institution.setUserId(userId);
				// 机构创建时间
				institution.setCreateTime(new Date());
				// 添加机构到数据库
				institutionService.addInstitution(institution);
				this.setJson(true, "机构注册成功，请等待管理员审核。", null);
			}else {
				this.setJson(false, "手机号已存在,请重新输入", null);
			}
			
		} catch (Exception e) {
			logger.error("InstitutionController.addInstitution", e);
			this.setJson(false, "系统异常,请稍后重试", null);
		}
		return json;
	}
	
	
	/**
	 * 根据机构id获取机构信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/institution/showInstitution/{id}")
	public String showInstitution(Model model,@PathVariable("id") Long id){// 机构id
		try {
			Institution institution = institutionService.getInstitutionById(id);
			model.addAttribute("institution",institution);
		} catch (Exception e) {
			logger.error("InstitutionController.showInstitution", e);
		}
		return showInstitution;
	}

	/**
	 * 更新机构信息
	 * @param institution
	 * @return
	 */
	@RequestMapping("/institution/updateInstitution")
	public String updateInstitution(@ModelAttribute("institution") Institution institution){
		
		try {
			institutionService.updateInstitution(institution);
		} catch (Exception e) {
			logger.error("InstitutionController.updateInstitution", e);
		}
		
		return null;
	}
	
	@RequestMapping("/front/institutionList")
	public String getInstitutionByStatus(Model model,@ModelAttribute("page") PageEntity page){
		try{
			page.setPageSize(12);
			this.setPage(page);
			List<Institution> institutionList = this.institutionService.getInstitutionList(this.getPage());
			model.addAttribute("institutionList", institutionList);
		}catch (Exception e) {
			logger.error("InstitutionController.getInstitutionByStatus", e);
		}
		return institution_list;
	}
}
