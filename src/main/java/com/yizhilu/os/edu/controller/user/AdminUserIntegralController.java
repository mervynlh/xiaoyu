package com.yizhilu.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserIntegral;
import com.yizhilu.os.edu.service.user.UserIntegralService;

/**
 * 用户积分
 * 
 * @ClassName com.yizhilu.os.edu.controller.user.AdminUserIntegralController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月28日 下午1:31:51
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralController.class);
	
	private static final String userIntegralList = getViewPath("/admin/user/userIntegral_list");// 用户积分列表

	@Autowired
	private UserIntegralService userIntegralService;

	// 绑定变量参数
	@InitBinder("userIntegral")
	public void initBindUserIntegral(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userIntegral.");
	}

	/**
	 * 查询用户积分列表
	 * 
	 * @param request
	 * @param model
	 * @param userIntegral
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/integralist")
	public String getUserIntegralList(HttpServletRequest request, Model model, @ModelAttribute UserIntegral userIntegral, @ModelAttribute("page") PageEntity page) {
		try {
			//设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(10);
			//查询用户积分列表
			List<UserIntegral> userIntegralList=userIntegralService.getUserIntegralListPage(userIntegral, this.getPage());
			model.addAttribute("userIntegralList", userIntegralList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("getUserIntegralList", e);
			setExceptionRequest(request, e);
		}
		return userIntegralList;
	}
}
